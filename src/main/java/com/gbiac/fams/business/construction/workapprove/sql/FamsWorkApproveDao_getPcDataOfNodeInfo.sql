SELECT
	a.id,
	c.task_key taskkey,
	c.task_state taskstate,
	c.task_name taskname,
	c.funname,
	c.flag,
	c.jumpurl,
	c.only_see onlysee
FROM
	fams_work_approve a
LEFT JOIN act_ru_task b ON a.procinst_id = b.EXECUTION_ID_
LEFT JOIN (
	SELECT DISTINCT t2.task_key task_key, t2.task_state task_state, t2.task_name task_name, t2.funname funname, t2.flag flag, t2.jumpurl jumpurl, t1.only_see only_see FROM fams_work_role t, fams_work_role_detail t1, fams_work_node_info t2 WHERE t.id = t1.work_role_id AND t1.info_id = t2.id AND t.role_code=:roleCode
) c ON b.TASK_DEF_KEY_ = c.task_key
WHERE
	a.id in(
    <#list famsWorkApproveEntitys as p>
      <#if p_has_next==true>
          '${p.id}',
      </#if>
      <#if p_has_next!=true>
          '${p.id}'
      </#if>
    </#list>
	)