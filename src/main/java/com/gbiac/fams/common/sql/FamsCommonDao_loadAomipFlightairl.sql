select
r.AIRL_ARPT_APCD as starstation,
t.APOT_CNNM as city,
r.AIRL_ARPT_FPTT as sst,
r.AIRL_ARPT_FRTT as sat,
r.AIRL_ARPT_FPLT as est,
r.AIRL_ARPT_FRLT as eat,
r.airl_arpt_apno
 from di_aomip_flight_airl_snapshot r
left join di_aomip_apot_info  t on r.AIRL_ARPT_APCD=t.APOT_CODE
where r.AIRL_FLID=:airlFlid   order by airl_arpt_apno asc