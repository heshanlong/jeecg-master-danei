SELECT
	t.EXECUTION_ID_ executionids
FROM
	act_ru_task t
WHERE
	t.TASK_DEF_KEY_ IN (
		SELECT DISTINCT
			c.task_key
		FROM
			fams_work_role a,
			fams_work_role_detail b,
			fams_work_node_info c
		WHERE
			a.id = b.work_role_id
		AND b.info_id = c.id
		AND a.role_id IN (
			SELECT
				d.roleid
			FROM
				t_s_role_user d
			WHERE
				d.userid = :userId
		)
	)