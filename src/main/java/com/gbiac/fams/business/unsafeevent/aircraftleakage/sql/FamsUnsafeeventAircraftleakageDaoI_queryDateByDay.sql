SELECT
	*
FROM
	fams_unsafeevent_aircraftleakage
WHERE
	thedate > DATE_SUB(CURDATE(), INTERVAL :day DAY) 
	and thedate <= DATE_SUB(CURDATE(), INTERVAL 0 DAY)
AND del = '0';