SELECT reform.* FROM fams_aircontrol_reform reform
WHERE
1=1
<#if entity.dateStart ?? && entity.dateEnd ?? >
and  create_date >='${entity.dateStart}' and create_date <='${entity.dateEnd}'
</#if>
<#if entity.status ??>
and reform.status in (${entity.status})
</#if>
<#if entity.dutyId ??>
and reform.duty_id='${entity.dutyId}'
</#if>
<#if entity.keyWord ?? && entity.keyWord ?length gt 0>
     /*违章行为*/
     and
     (
     reform.violation_name  like CONCAT('%', '${entity.keyWord}','%')
     or reform.duty_company like CONCAT('%', '${entity.keyWord}','%')
     or reform.duty_person_name like CONCAT('%', '${entity.keyWord}','%')
     )
</#if>
<#if entity.orderType ?? && entity.orderType = '1'>
     /*1先按状态排列，“待接收”>“整改中”>“验收不通过”；再按派发时间顺序排列显示*/
   order by status asc ,decide_date asc

</#if>
<#if entity.orderType ?? && entity.orderType = '2'>
     /*2按派发时间顺序排列显示。*/
   order by create_date asc
</#if>
<#if entity.orderType ?? && entity.orderType = '3'>
     /*3先按状态排列，“验收不通过”>“整改中”>“待验收”；再按派发时间顺序排列显示。*/
   order by status desc ,decide_date asc
</#if>
<#if entity.orderType ?? && entity.orderType = '4'>
     /*4按派发时间顺序倒序显示。*/
   order by create_date desc
</#if>
<#if entity.orderType ?? && entity.orderType = '5'>
     /*5按更新时间倒序排列显示，最新的排最前面。*/
  order by update_date desc
</#if>


<#if entity.pageNoApp ?? && entity.pageSizeApp  ??>
LIMIT :entity.pageNoApp,:entity.pageSizeApp
</#if>

