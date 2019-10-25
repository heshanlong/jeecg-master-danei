SELECT violation.* FROM fams_aircontrol_violation violation
WHERE
1=1
<#if entity.dateStart ?? && entity.dateEnd ??  >
and  create_date >='${entity.dateStart}' and create_date <='${entity.dateEnd}'
</#if>
<#if entity.status ??>
and violation.status in (${entity.status})
</#if>
<#if entity.dutyId ??>
and violation.duty_id='${entity.dutyId}'
</#if>

<#if entity.keyWord ?? && entity.keyWord ?length gt 0>
     /*违章行为*/
     and
     (
      violation.violation_name  like CONCAT('%', '${entity.keyWord}','%')
     or violation.duty_company like CONCAT('%', '${entity.keyWord}','%')
     or violation.duty_person_name like CONCAT('%', '${entity.keyWord}','%')
     )
</#if>
<#if entity.orderType ?? && entity.orderType = '1'>
     /*1先按状态排列，“待处理”>“已撤销”；再按时间顺序排列显示。*/
   order by status asc ,decide_date desc

</#if>
<#if entity.orderType ?? && entity.orderType = '2'>
     /*2按派发时间顺序排列显示。*/
   order by create_date asc
</#if>
<#if entity.orderType ?? && entity.orderType = '3'>
     /*3按派发时间倒序排列显示，最新的排最前面。*/
   order by create_date desc
</#if>
<#if entity.orderType ?? && entity.orderType = '4'>
     /*4按更新时间倒序排列显示，最新的排最前面。*/
  order by update_date desc
</#if>

<#if entity.pageNoApp ?? && entity.pageSizeApp  ??>
LIMIT :entity.pageNoApp,:entity.pageSizeApp
</#if>


