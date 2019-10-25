SELECT 
	*
FROM
	fams_work_approve_nopass
WHERE
	work_name LIKE  '%${searchInput}%'
	AND
	push_by = '${userName}'
ORDER BY
	create_date DESC
LIMIT ${pageNum},${pageSize}