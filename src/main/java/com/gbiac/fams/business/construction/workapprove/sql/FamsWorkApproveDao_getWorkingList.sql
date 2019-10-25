/*查询待审批和进行中的记录*/
<#if workingState!=2>
SELECT * FROM (
  SELECT
    t.id,
    t.work_name workName,
    t.work_depart workDepart,
    (SELECT departname FROM t_s_depart WHERE id=t.work_approve_depart) workApproveDepartStr,
    t.work_start_time startTime,
    t.number,
    t.work_end_time endTime,
    c.task_state workingStatus
  FROM
    fams_work_approve t,
    act_ru_execution a,
    act_ru_task b,
    fams_work_node_info c
  WHERE
    <#if userId??>
    t.create_user_id=:userId AND
    </#if>
  t.procinst_id = a.ID_
  AND a.ID_ = b.EXECUTION_ID_
  AND t.task_key IN (
    <#list flags as flag>
      '${flag}'
      <#if flag_has_next>
        ,
      </#if>
    </#list>
  )
  AND t.task_key = c.task_key
  ORDER BY
    t.task_key,
    t.work_start_time DESC
) abc where (abc.workName LIKE '%${searchInput}%' OR abc.workDepart LIKE '%${searchInput}%' OR abc.workApproveDepartStr LIKE '%${searchInput}%' OR abc.number LIKE '%${searchInput}%') LIMIT :pageEntity.pageNum,:pageEntity.pageSize
</#if>
/*查询已结束的记录*/
<#if workingState==2>
SELECT  * FROM (
  SELECT
	t.id,
	t.work_name workName,
	t.work_depart workDepart,
	(SELECT departname FROM t_s_depart WHERE id=t.work_approve_depart) workApproveDepartStr,
	t.number,
	t.work_start_time startTime,
	t.work_end_time endTime,
	'已离场' workingStatus
FROM
	fams_work_approve t
WHERE
    <#if userId??>
    t.create_user_id=:userId AND
    </#if>
	t.procinst_id NOT IN (
		SELECT
			ID_
		FROM
			act_ru_execution
	)
ORDER BY
  t.task_key,
	t.work_start_time DESC
) abc where (abc.workName LIKE '%${searchInput}%' OR abc.workDepart LIKE '%${searchInput}%' OR abc.workApproveDepartStr LIKE '%${searchInput}%' OR abc.number LIKE '%${searchInput}%') LIMIT :pageEntity.pageNum,:pageEntity.pageSize
</#if>
