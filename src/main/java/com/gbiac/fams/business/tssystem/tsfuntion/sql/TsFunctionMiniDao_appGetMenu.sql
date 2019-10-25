SELECT
	tsf.*
FROM
	t_s_role_user tsr,
  t_s_role_function tsrf,
  t_s_function tsf
WHERE 
  tsr.userid = :userid
AND tsr.roleid = tsrf.roleid
AND tsrf.functionid = tsf.ID
AND tsf.parentfunctionid = :pid

