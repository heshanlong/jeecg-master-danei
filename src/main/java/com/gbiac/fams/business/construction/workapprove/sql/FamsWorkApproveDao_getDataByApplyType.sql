SELECT
	*
FROM
	fams_work_approve t
WHERE
	t.apply_type = :applyType
AND t.task_key != 'y-approve-apply'
AND DATE_FORMAT(
	DATE_SUB(now(), INTERVAL - 1 DAY),
	'%Y-%m-%d'
) = DATE_FORMAT(
	t.work_end_time,
	'%Y-%m-%d'
)

