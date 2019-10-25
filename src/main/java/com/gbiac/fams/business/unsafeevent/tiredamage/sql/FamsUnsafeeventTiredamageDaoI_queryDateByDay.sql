SELECT
	*
FROM
	fams_unsafeevent_tiredamage
WHERE
	thedate > DATE_SUB(CURDATE(), INTERVAL :day DAY) 
	and thedate <= DATE_SUB(CURDATE(), INTERVAL 0 DAY);