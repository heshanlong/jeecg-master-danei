SELECT
	*
FROM
	fams_unsafeevent_birdstrike
WHERE
	thedate > DATE_SUB(CURDATE(), INTERVAL :day DAY) 
	and thedate <= DATE_SUB(CURDATE(), INTERVAL 0 DAY)
AND del = '0';