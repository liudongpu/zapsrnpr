DELIMITER $
DROP PROCEDURE IF EXISTS `proc_zw_initview`$
CREATE PROCEDURE `proc_zw_initview`(in p_view_code varchar(100))
begin


declare p_exit int;
set p_exit=(select count(1) from zw_field where view_code=p_view_code);





INSERT INTO `zw_field`
(
`uid`,
`view_code`,
`field_name`,
`column_name`,
`field_note`,
`sort_add`,
`sort_edit`,
`sort_chart`,
`sort_book`,
`sort_inquery`,
`field_type_aid`)

select replace(uuid(),'-','') as uid
,zwv.view_code as view_code
,zdc.column_name as field_name
,zdc.column_name as column_name
,zdc.column_note as field_note
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end)  as sort_add
,(case zdc.column_name when 'zid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end) as sort_edit
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end) as sort_chart
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else ( case p_exit when 0 then 100000+100*zdc.column_sort else 0 end) end) as sort_book
#,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_inquery
,0 as sort_inquery
,(case zdc.column_name when 'zid' then 104005008 when 'uid' then 104005008 else 104005009 end) as field_type_aid
from zapdata.zd_column zdc left join zw_view zwv
on zdc.table_name=zwv.table_name
where concat(zwv.view_code,zdc.column_name)
not in(select concat(view_code,column_name) from zw_field)
and zwv.view_code=p_view_code;




#add
INSERT INTO `zapdata`.`zw_page`
(
`uid`,
`page_code`,
`page_name`,
`page_template`,
`view_code`,
`page_type_aid`,
`view_type_aid`,
`page_group`,
`project_aid`)
select replace(uuid(),'-','') as uid
,concat(zwd.define_two,zwv.view_code) as page_code
,concat(zwv.view_name,zwd.define_one) as page_name
,zwd.define_three as page_template
,zwv.view_code as view_code
,zwd.define_four as page_type_aid
,zwd.define_five as view_type_aid
,concat('grouppage_',zwv.view_code) as page_code
,project_aid
from zw_view zwv
join zw_define zwd
on zwd.define_dids in('469916161601','469916161603','469916161605')
where

(select count(1) from zapdata.zw_page where page_code=concat(zwd.define_two,zwv.view_code))=0 and  
zwv.view_code=p_view_code;






INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`,
`init_type_did`)
select
replace(uuid(),'-','') as uid
,zwd.define_note as operate_name
,zwd.define_one as operate_type_aid
,zwp.page_code as page_code
,
	(case zwd.define_dids 
	when 469916151601 then (select page_code from zw_page where page_type_aid=116016001 and view_code=zwp.view_code) 
	when 469916151605 then concat((select page_code from zw_page where page_type_aid=116016005 and view_code=zwp.view_code),'?zw_f_uid=[@this$uid]')
	else zwd.define_five end)
 as operate_link
,'0' as flag_enable
,zwd.define_four as operate_func
,zwd.define_three as area_type_aid
,zwd.define_dids as init_type_did

from zw_page zwp 
 join zw_define zwd
on zwp.page_type_aid=zwd.define_two

where

(select count(1) from zw_operate zwo where concat(zwo.init_type_did,zwo.page_code)=concat(zwd.define_dids,zwp.page_code))=0 
and zwp.view_code=p_view_code;
























































end$
DELIMITER ;