SELECT
	*
FROM
	fams_unsafeevent_aircraftleakage
WHERE del = '0' and (
	UPPER(FLIGHTNO) LIKE CONCAT('%',:searchInput , '%')
OR OILTYPES LIKE CONCAT('%',:searchInput , '%')
)
ORDER BY
	create_date DESC
LIMIT :pageApp,
 :pageSize