SELECT
	*
FROM
	fams_rulesregulations
WHERE
	del_type = 0

<#if rulesregulationsAppDTO.rulesName ?? && rulesregulationsAppDTO.rulesName != "" && rulesregulationsAppDTO.keyword ?? && rulesregulationsAppDTO.keyword != "">
	AND rules_type = 1
	AND rules_name LIKE CONCAT('%', :rulesregulationsAppDTO.rulesName, '%') OR keyword LIKE CONCAT('%', :rulesregulationsAppDTO.keyword, '%')
<#elseif rulesregulationsAppDTO.rulesName ?? && rulesregulationsAppDTO.rulesName != "">
	AND rules_type = 1
	AND rules_name LIKE CONCAT('%', :rulesregulationsAppDTO.rulesName, '%')
<#elseif rulesregulationsAppDTO.keyword ?? && rulesregulationsAppDTO.keyword != "">
	AND rules_type = 1
	AND keyword LIKE CONCAT('%', :rulesregulationsAppDTO.keyword, '%')
<#else>
	AND rules_type = 0
	AND pid is null
</#if>

<#if roleCode == "admin" || roleCode == "anquan">
	AND (visible = 1 or visible = 0)
<#else>
	<#if orgCode == "A39A15">
		AND (visible = 1 or visible = 0)
	<#else>
		AND visible = 0
	</#if>
</#if>

ORDER BY create_date DESC