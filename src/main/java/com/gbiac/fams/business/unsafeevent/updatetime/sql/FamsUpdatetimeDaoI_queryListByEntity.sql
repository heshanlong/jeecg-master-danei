SELECT
	*
FROM
	fams_updatetime
WHERE
	no = :searchInput 
ORDER BY
	create_date DESC
LIMIT :pageApp,
 :pageSize