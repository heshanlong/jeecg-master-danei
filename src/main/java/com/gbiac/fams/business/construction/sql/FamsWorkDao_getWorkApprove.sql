SELECT
	t.*
FROM
	fams_work_approve t
WHERE
	t.id = :approveId
AND t.task_key NOT IN (
	's-out',
	'y-approve-out',
	'end'
)