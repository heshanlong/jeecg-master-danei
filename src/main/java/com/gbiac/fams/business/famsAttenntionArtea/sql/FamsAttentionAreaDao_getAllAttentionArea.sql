SELECT 
	fa.* 
FROM 
	fams_attention_area fa 
LEFT JOIN
	fams_area_config fc
	on fc.id = fa.attention_area
LEFT JOIN
	t_s_base_user user
	on user.username = fa.create_by
WHERE 
	user.realname = :userName
AND
	fc.area LIKE CONCAT('%',:searchInput , '%')
ORDER BY 
	fa.create_date DESC