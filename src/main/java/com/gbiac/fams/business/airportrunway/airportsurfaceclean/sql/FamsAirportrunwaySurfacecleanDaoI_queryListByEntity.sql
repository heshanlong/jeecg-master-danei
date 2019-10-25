SELECT
	*
FROM
	fams_airportrunway_surfaceclean
WHERE
	1 = 1
AND CLEAN_LOCATION LIKE CONCAT('%',:searchInput , '%')
ORDER BY
	end_time DESC
LIMIT :pageApp,
 :pageSize