select * from
fams_common_reply reply
where
1=1
<#if entity.module ??>
AND reply.module = '${entity.module}'
</#if>

<#if entity.businessId ??>
AND reply.business_id = '${entity.businessId}'
</#if>

<#if entity.state ??>
AND reply.state = '${entity.state}'
</#if>
<#if entity.replyContent ?? && entity.replyContent ?length gt 0>
/*违章行为*/
and reply.reply_content  like CONCAT('%', '${entity.replyContent}','%')
</#if>
order by create_time desc

<#if entity.pageNo ?? && entity.pageSize  ??>
LIMIT :entity.pageNo,:entity.pageSize
</#if>
