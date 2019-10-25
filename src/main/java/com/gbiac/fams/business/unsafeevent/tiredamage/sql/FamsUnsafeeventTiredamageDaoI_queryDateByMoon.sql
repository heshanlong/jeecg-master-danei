SELECT
	*
FROM
	fams_unsafeevent_tiredamage
WHERE
	DATE_FORMAT( create_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) and del = '0';