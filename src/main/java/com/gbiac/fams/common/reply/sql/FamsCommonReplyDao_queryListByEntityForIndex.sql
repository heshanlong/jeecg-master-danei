select
reply.*,
violation_name,
number from
fams_common_reply reply
<#if entity.module ?? && entity.module='reform'>
left join fams_aircontrol_reform  rf on rf.id=reply.business_id
</#if>
<#if entity.module ?? && entity.module='violation'>
left join fams_aircontrol_violation  vo on vo.id=reply.business_id
</#if>
where
1=1
<#if entity.module ??>
AND reply.module = '${entity.module}'
</#if>
<#if entity.module ?? && entity.module='reform'>
and rf.duty_id='${entity.dutyId}'
</#if>
<#if entity.module ?? && entity.module='violation'>
and vo.duty_id='${entity.dutyId}'
</#if>

<#if entity.replyContent ?? && entity.replyContent ?length gt 0>
/*违章行为*/
and reply.reply_content  like CONCAT('%', '${entity.replyContent}','%')
</#if>
order by reply.create_time desc

<#if entity.pageNo ?? && entity.pageSize  ??>
LIMIT :entity.pageNo,:entity.pageSize
</#if>
