select tfirst.*,@rownum := @rownum +1 AS rownum from (
 select  t.id as id,
 CONCAT(t.AFSS_AWCD,t.AFSS_FLNO) as flightno,
t.AFSS_FLID as ffid,
t.AFSS_AFID as afid,
t.AFSS_AWCD as twocharcode,
a.AWAY_CNNM as airlinescn,
t.AFSS_CFTP as crafttype,
t.AFSS_CFNO as craftno,
stls.STLS_STND_CODE as craftsite,
t.AFSS_FLIO
from di_aomip_flight_snapshot t
 left join  di_aomip_away_info a on t.afss_awcd=a.AWAY_CODE
 left join  di_aomip_flight_stls_snapshot stls on t.AFSS_FLID=STLS_FLID
where  t.afss_fexd >='${dateStart}' and t.afss_fexd <='${dateEnd}'
 ) tfirst,(SELECT   @rownum := 0) r
 where
 1=1
 <#if keyword ?? && keyword ?length gt 0>
   and  tfirst.flightno   like CONCAT('%', '${keyword}','%')
</#if>
limit 0,10

