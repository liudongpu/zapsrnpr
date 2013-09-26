
#设置所有超级管理员菜单
[sql]
use zapdata;
insert into za_rolemenu (uid,role_code,menu_code)
select replace(uuid(),'-',''),'4677031800010001',
menu_code from za_menu  a where a.menu_code 
not in (select b.menu_code from za_rolemenu b) 
and length(a.menu_code)=24;
[/sql]

#更新所有字段的备注名称
[sql]
use zapdata;
update zw_field b set b.field_note=
(select column_note from zd_column a 
where a.table_name=(select table_name 
from zw_view c where c.view_code=b.view_code)
and a.column_name=b.column_name
) where b.field_note='';
[/sql]






