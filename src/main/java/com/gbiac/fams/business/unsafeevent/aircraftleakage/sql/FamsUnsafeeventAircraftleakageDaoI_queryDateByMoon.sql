SELECT
	*
FROM
	fams_unsafeevent_aircraftleakage
WHERE
	DATE_FORMAT( create_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) and del = '0';