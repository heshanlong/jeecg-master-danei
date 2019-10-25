SELECT
	*
FROM
	fams_work_approve t
WHERE
	t.task_key IN  ${inStr}
AND t.create_name = '${createName}'
AND t.work_name LIKE  '%${searchInput}%'
ORDER BY
	t.create_date DESC
limit ${page},${pageSize}