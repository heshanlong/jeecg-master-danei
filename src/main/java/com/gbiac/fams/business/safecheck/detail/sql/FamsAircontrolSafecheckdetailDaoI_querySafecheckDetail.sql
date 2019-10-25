
SELECT sa.id,
        check_id,
        check_configi_id,
        sa.result,
        sa.arrayTime,
        sa.completeTime,
        sa.dealResult,
        scc.check_project,
        scc.project_detail,
        scc.eventName
FROM fams_aircontrol_safecheckdetail sa
left join fams_aircontrol_safecheckconfig  scc
on sa.check_configi_id=scc.id
where
1=1
<#if checkId ??>
and sa.check_id= '${checkId}'
</#if>
<#if checkProject ??>
and scc.check_project= '${checkProject}'
</#if>
