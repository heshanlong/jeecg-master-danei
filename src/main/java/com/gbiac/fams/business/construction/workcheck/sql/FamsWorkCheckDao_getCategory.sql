SELECT
	t.id,
	t.`code`,
	substring(
		t.`code`,
		1,
		LENGTH(t.`code`) - LENGTH(
			SUBSTRING_INDEX(t.`code`, 'A', - 1)
		) - 1
	) pcode,
	t.`name`
	<#if bid ??>
  ,t1.check_result result
  </#if>
FROM
	t_s_category t
	<#if bid ??>
  LEFT JOIN fams_work_check_item t1 ON t.id = t1.category_id AND t1.check_id = :bid
  </#if>
WHERE
	t.`code` LIKE :code
  ORDER BY t.`code`