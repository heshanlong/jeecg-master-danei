SELECT
	t.map_points location,
	t1.work_name workingTitle
FROM
	fams_common_map t,
	fams_work_approve t1
WHERE
	t.business_id = t1.id
AND t1.procinst_id IN (
	SELECT
		b.ID_
	FROM
		act_ru_execution b
)