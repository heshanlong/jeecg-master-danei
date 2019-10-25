SELECT
	*
FROM
	fams_airportrunway_clean
WHERE
	1 = 1
AND CLEAN_RUNWAY LIKE CONCAT('%',:searchInput , '%')
ORDER BY
	end_time DESC
LIMIT :pageApp,
 :pageSize