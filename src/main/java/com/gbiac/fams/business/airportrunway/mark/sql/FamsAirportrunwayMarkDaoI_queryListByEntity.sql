SELECT
	*
FROM
	fams_airportrunway_mark
WHERE
	1 = 1
AND LOCATION LIKE CONCAT('%',:searchInput , '%')
ORDER BY
	end_time DESC
LIMIT :pageApp,
 :pageSize