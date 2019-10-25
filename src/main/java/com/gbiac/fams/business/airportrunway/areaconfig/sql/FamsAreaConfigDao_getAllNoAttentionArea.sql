SELECT 
	fc.*
FROM 
	fams_area_config fc
LEFT JOIN
	fams_attention_area fa
	ON fa.attention_area = fc.id
WHERE 
	fc.id not in 
	(SELECT attention_area FROM fams_attention_area 
	LEFT JOIN
		t_s_base_user user
		ON user.username = create_by
	WHERE
		user.realname = :userName
	)
AND 
	fc.area LIKE CONCAT('%',:searchInput , '%')