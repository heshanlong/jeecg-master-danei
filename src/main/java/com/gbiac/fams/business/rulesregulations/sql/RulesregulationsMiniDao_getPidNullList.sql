SELECT
	fr_1.*,(select count(0) from fams_rulesregulations fr_2 where fr_2.pid = fr_1.id and fr_2.del_type = 0) fileCount
FROM
	fams_rulesregulations fr_1
WHERE
  fr_1.del_type = 0
AND	fr_1.pid IS NULL
ORDER BY
	fr_1.create_date DESC