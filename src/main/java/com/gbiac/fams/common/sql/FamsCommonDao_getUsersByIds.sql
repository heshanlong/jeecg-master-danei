select * from t_s_base_user where id in(
    <#list userIds as p>
      <#if p_has_next==true>
          '${p}',
      </#if>
      <#if p_has_next!=true>
          '${p}'
      </#if>
    </#list>
)