SELECT fasf.* FROM fams_aircontrol_safecheck fasf
WHERE
1=1
<#if entity.dateStart ?? && entity.dateEnd ?? >
and  create_date >='${entity.dateStart}' and create_date <='${entity.dateEnd}'
</#if>
<#if entity.result ??>
and fasf.result in (${entity.result})
</#if>
<#if entity.airNumber ?? && entity.airNumber ?length gt 0>
     /*航班号*/
        and UPPER(fasf.air_number) like  UPPER(CONCAT('%', '${entity.airNumber}','%'))

</#if>

order by create_date desc

<#if entity.pageNoApp ?? && entity.pageSizeApp  ??>
LIMIT :entity.pageNoApp,:entity.pageSizeApp
</#if>

