SELECT
	*
FROM
	fams_airportrunway_repair
WHERE
	DAMAGE_LOCATION LIKE CONCAT('%',:searchInput , '%') 
ORDER BY
	end_time DESC
LIMIT :pageApp,
 :pageSize