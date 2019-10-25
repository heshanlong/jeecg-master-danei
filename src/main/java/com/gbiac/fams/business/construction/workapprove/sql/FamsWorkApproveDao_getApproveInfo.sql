SELECT
	t.id,
	t.number,
	t.work_name workname,
	t.work_area workarea,
	t.work_depart workdepart,
	IFNULL(( SELECT DATE_FORMAT( option_time, '%Y-%m-%d %H:%i:%S' ) FROM fams_common_progress WHERE business_id = t.id AND state = 's-in' LIMIT 0, 1 ), DATE_FORMAT( t.work_start_time, '%Y-%m-%d %H:%i:%S' )) starttime,
	IFNULL(( SELECT DATE_FORMAT( option_time, '%Y-%m-%d %H:%i:%S' ) FROM fams_common_progress WHERE business_id = t.id AND state = 's-out' LIMIT 0, 1 ), DATE_FORMAT( t.work_end_time, '%Y-%m-%d %H:%i:%S' )) endtime
FROM
	fams_work_approve t
WHERE
	t.id =:approveId