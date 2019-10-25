package com.gbiac.fams.common.Util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JpushUtil {
    private static final Logger LOG = LoggerFactory.getLogger("JpushLogger");
    // 飞行 极光申请的应用appKey唯一
    private static final String f_appKey = "dd1bdb5b78265f10f740541b";
    // 飞行 极光申请的应用masterSecret唯一
    private static final String f_masterSecret = "9a433783bc7a4d7f0348feb9";
    // 掌汇 极光申请的应用appKey唯一
    private static final String z_appKey = "d5314b6122ba4ec29b09e280";
    // 掌汇 极光申请的应用masterSecret唯一
    private static final String z_masterSecret = "1c8fd03ae56f24d5ab9216bd";

    /**
     * 给一个IMEI号推送消息
     *
     * @param imei
     * @param title     标题
     * @param content   内容
     * @param extrasMap 额外信息
     */
    public static void sendMessage(String imei, String title, String content, Map<String, String> extrasMap) {
        List<String> imeis = new ArrayList<String>();
        imeis.add(imei);
        send(f_appKey,f_masterSecret,imeis, title, content, extrasMap,false);
        send(z_appKey,z_masterSecret,imeis, title, content, extrasMap,false);
    }

    /**
     * 给多个IMEI号推送消息
     *
     * @param imeis
     * @param title     标题
     * @param content   内容
     * @param extrasMap 额外信息
     */
    public static void sendMessage(Collection<String> imeis, String title, String content, Map<String, String> extrasMap) {
        send(f_appKey,f_masterSecret,imeis, title, content, extrasMap,false);
        send(z_appKey,z_masterSecret,imeis, title, content, extrasMap,false);
    }

    /**
     * 给所有人推送消息
     *
     * @param title     标题
     * @param content   内容
     * @param extrasMap 额外信息
     */
    public static void sendMessageToAll(String title, String content, Map<String, String> extrasMap) {
        send(f_appKey,f_masterSecret,null, title, content, extrasMap,true);
        send(z_appKey,z_masterSecret,null, title, content, extrasMap,true);
    }

    /**
     * 给多个imei号推送消息
     *
     * @param imeis     imei集合
     * @param title     标题
     * @param content   内容
     * @param extrasMap 额外信息
     * @param isToAll   是否全员发送
     */
    private static void send(String appKey,String masterSecret,Collection<String> imeis, String title, String content, Map<String, String> extrasMap,boolean isToAll) {
        Audience audience=isToAll?Audience.all():Audience.alias(new HashSet(imeis));
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(audience)
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .setNotification(
                        Notification
                                .newBuilder()
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                                .setTitle(title)
                                                .setAlert(content)
                                                .addExtras(extrasMap).build())
                                .addPlatformNotification(IosNotification.newBuilder().setBadge(1)
                                        .setAlert(title + "\n" + content)
                                        .setSound("default")
                                        .addExtras(extrasMap).build())
                                .build())
                .build();
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("\r\nIMIE: " + imeis + ",\r\ntitle: " + title + ",\ncontent： " + content + ",\r\nextrasMap: " + extrasMap);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

}
