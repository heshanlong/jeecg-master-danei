SELECT
	*
FROM
	fams_unsafeevent_aircraftleakage
WHERE
	FLIGHTNO LIKE CONCAT('%',:searchInput , '%')
OR routes LIKE CONCAT('%',:searchInput , '%')
OR SOURCEFORMATION LIKE CONCAT('%',:searchInput , '%')
ORDER BY
	create_date DESC
LIMIT :pageApp,
 :pageSize