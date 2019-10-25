SELECT
	fr_1.*,(select count(0) from fams_rulesregulations fr_2 where fr_2.pid = fr_1.id and fr_2.del_type = 0) fileCount
FROM
	fams_rulesregulations fr_1
WHERE
  fr_1.del_type = 0
AND	fr_1.pid  = :pId

<#if roleCode == "admin" || roleCode == "anquan">
	AND (fr_1.visible = 1 or fr_1.visible = 0)
<#else>
	<#if orgCode == "A39A15">
		AND (fr_1.visible = 1 or fr_1.visible = 0)
	<#else>
		AND fr_1.visible = 0
	</#if>
</#if>

ORDER BY
	fr_1.rules_type ASC,
	fr_1.create_date DESC