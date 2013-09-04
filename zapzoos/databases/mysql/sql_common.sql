
#更新所有字段的状态
update zw_field b set b.field_note=
(select column_note from zd_column a where a.table_name=(select table_name from zw_view c where c.view_code=b.view_code)
and a.column_name=b.column_name
) where b.field_note='';