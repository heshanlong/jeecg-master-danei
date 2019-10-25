SELECT
	*
FROM
	fams_unsafeevent_tiredamage
WHERE del = '0' and (
	UPPER(FLIGHTNO) LIKE CONCAT('%',:searchInput , '%')
OR DAMAGEDLOCATION LIKE CONCAT('%',:searchInput , '%')
)
ORDER BY
	create_date DESC
LIMIT :pageApp,
 :pageSize