
#剔除不存在菜单并且设置所有超级管理员和店铺管理员菜单
[sql]
use zapdata;
delete from za_rolemenu where menu_code 
not in (select menu_code from za_menu) or length(menu_code)<24;
insert into za_rolemenu (uid,role_code,menu_code)
select replace(uuid(),'-',''),'4677031800010001',
menu_code from za_menu  a where a.menu_code 
not in (select b.menu_code from za_rolemenu b) 
and length(a.menu_code)=24;
insert into za_rolemenu (uid,role_code,menu_code)
select replace(uuid(),'-',''),'4677031800020001',
menu_code from za_menu  a where a.menu_code 
not in (select b.menu_code from za_rolemenu b where b.role_code='4677031800020001') 
and length(a.menu_code)=24 and a.menu_code like '467703130002%';
[/sql]

#更新所有字段的备注名称
[sql]
use zapdata;
update zd_column b set b.column_note=
ifnull((select 
a.COLUMN_COMMENT FROM information_schema.COLUMNS a
where a.table_name=b.table_name
and a.COLUMN_NAME =b.COLUMN_NAME
),'') where b.column_note='';
update zw_field b set b.field_note=
(select column_note from zd_column a 
where a.table_name=(select table_name 
from zw_view c where c.view_code=b.view_code)
and a.column_name=b.column_name
) where b.field_note='';
[/sql]

#更新uid
[sql]
update zw_define set uid=replace(uuid(),'-','');
[/sql]



