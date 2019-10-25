SELECT DISTINCT
	t.id,
	t.number,
	t.work_name,
  IFNULL(( SELECT DATE_FORMAT( option_time, '%Y-%m-%d %H:%i' ) FROM fams_common_progress WHERE business_id = t.id AND state = 's-in' LIMIT 0, 1 ), DATE_FORMAT( t.work_start_time, '%Y-%m-%d %H:%i' )) work_start_time,
  IFNULL(( SELECT DATE_FORMAT( option_time, '%Y-%m-%d %H:%i' ) FROM fams_common_progress WHERE business_id = t.id AND state = 's-out' LIMIT 0, 1 ), DATE_FORMAT( t.work_end_time, '%Y-%m-%d %H:%i' )) work_end_time,
	t.work_area,
	t.work_depart
FROM
	fams_work_approve t
LEFT JOIN
  <#if workType == "check">
    fams_work_check
  </#if>
  <#if workType == "unionCheck">
    fams_work_unioncheck
  </#if>
  <#if workType == "vindicateCheck">
    fams_work_vindicatecheck
  </#if>
  t1 ON t.id = t1.bid
WHERE
	t1.id IS NULL
AND t.work_name LIKE '%${keyword}%'
AND t.work_type_id IN (
	SELECT
		a.id
	FROM
		fams_work_type a
	WHERE
		a.type_name IN (
		  <#if workType == "check">
        '不停航施工'
      </#if>
      <#if workType == "unionCheck">
        '不停航施工'
      </#if>
      <#if workType == "vindicateCheck">
        '日常维护','临时维护'
      </#if>
		)
)
AND t.task_key IN (
  's-out','y-approve-out'
)
GROUP BY
	t.work_name
ORDER BY
	t.create_date DESC
LIMIT 0,
 ${maxNum}