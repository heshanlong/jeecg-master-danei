SELECT
	t.id,
	t1.imei
FROM
	t_s_base_user t,
	t_s_user_detail t1
WHERE
	t.id = t1.id
AND t1.id IN (
  <#list userIds as p>
    <#if p_has_next==true>
        '${p}',
    </#if>
    <#if p_has_next!=true>
        '${p}'
    </#if>
  </#list>
)
AND t1.imei IS NOT NULL
AND t1.imei !='null'
AND t1.imei !='undefined'
AND t1.imei !=''