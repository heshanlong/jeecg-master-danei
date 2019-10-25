
SELECT
	count(0)
FROM
	fams_rulesregulations
WHERE
	rules_name = :rulesName
	AND rules_type = :rulesType
	AND del_type = 0
<#if pid ?? && pid != "">
	AND pid = :pid
<#else>
	AND pid is null
</#if>	