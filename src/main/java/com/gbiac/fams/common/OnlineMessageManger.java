package com.gbiac.fams.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.jeecgframework.web.system.service.impl.EhcacheService;
import org.jeecgframework.web.system.service.impl.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.gbiac.fams.common.Entity.MessageEntity;
import com.gbiac.fams.common.Entity.MessageType;

/**
 * 用于消息的异步推送给用户推送
 * 
 * @author Mchen
 *
 */
@ServerEndpoint("/message/{userName}")
public class OnlineMessageManger {
	private static final Logger logger = LoggerFactory.getLogger(OnlineMessageManger.class);

	// 用于管理websocketSession
	private static final Map<String, Session> map = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("userName") String userName) {
		// 如果用户已经存在了就将其剔除下线
		map.put(userName, session);
	}

	@OnClose
	public void OnClose(Session session) {
		String key = null;
		for(Map.Entry<String, Session> se:map.entrySet()) {
			if (se.getValue()==session) {
				key = se.getKey();
				break;
			}
		}
		map.remove(key);
		logger.info("Websocket连接关闭");
	}

	@OnError
	public void OnError(Session session, Throwable throwable) {
		logger.error("Websocket连接关闭" + throwable);
	}

	/**
	 * 为在线用户推送消息
	 * 
	 * @param messageType 消息实体
	 * @param userName    被推送的用户名称
	 */
	public void sendMessage(MessageType messageType, String userName) {
		if (map.containsKey(userName) && map.get(userName).isOpen()) {
			Session session = map.get(userName);
			session.getAsyncRemote().sendText(messageType.getMessage());
		}
	}

	/**
	 * 为在线用户推送消息和消息的Id
	 * 
	 * @param messageType 消息实体
	 * @param userName    被推送的用户名称
	 */
	public void sendMessageAndTsmId(MessageType messageType, String userName, String tId) {
		if (map.containsKey(userName) && map.get(userName).isOpen()) {
			Session session = map.get(userName);
			StringBuilder sb = new StringBuilder();
			sb.append("{").append("\"id\":\"").append(tId).append("\",\"content\":\"").append(messageType.getMessage())
					.append("\"}");
			session.getAsyncRemote().sendText(sb.toString());
		}
	}

	/**
	 * 剔除用户下线并发送通知
	 * 
	 * @param userName
	 */
	public void kickOutUser(String userName) {

		Session session = map.get(userName);
		try {
			ClientManager clientManager = ClientManager.getInstance();
			CacheServiceI cacheService = ApplicationContextUtil.getContext().getBean(CacheServiceI.class);
			HashMap<String, Client> onLineClients = (HashMap<String, Client>) cacheService
					.get(CacheServiceI.FOREVER_CACHE, "online_client_users");

			List<String> sessionList = new ArrayList<String>();
			if (onLineClients != null) {
				for (Entry<String, Client> entry : onLineClients.entrySet()) {
					if (entry.getValue() != null && entry.getValue().getUser() != null
							&& entry.getValue().getUser().getUserName() != null
							&& entry.getValue().getUser().getUserName().equals(userName)) {
						sessionList.add(entry.getKey());
					}
				}
			}
			for (String sessionid : sessionList) {
				HttpSession httpsession = ContextHolderUtils.getSession(sessionid);
				onLineClients.remove(sessionid);
				httpsession.invalidate();
			}
			cacheService.put(CacheServiceI.FOREVER_CACHE, "online_client_users", onLineClients);

			if (session != null && session.isOpen()) {
				MessageEntity alert = new MessageEntity();
				alert.setType(MessageEntity.ALERT);
				alert.setMessage("您的账号已经在另一处登录了,您被迫下线!");
				session.getAsyncRemote().sendText("1");
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if(session !=null)
				session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
