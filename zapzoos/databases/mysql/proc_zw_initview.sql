DELIMITER $
DROP PROCEDURE IF EXISTS `proc_zw_initview`$
CREATE PROCEDURE `proc_zw_initview`(in p_view_code varchar(100))
begin


INSERT INTO `zw_field`
(
`uid`,
`view_code`,
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
,zdc.column_name as column_name
,zdc.column_note as field_note
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end)  as sort_add
,(case zdc.column_name when 'zid' then 0 else 100000+100*zdc.column_sort end) as sort_edit
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_chart
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_book
,(case zdc.column_name when 'zid' then 0 when 'uid' then 0 else 100000+100*zdc.column_sort end) as sort_inquery
,(case zdc.column_name when 'zid' then 104005008 when 'uid' then 104005008 else 104005009 end) as field_type_aid
from zapdata.zd_column zdc left join zw_view zwv
on zdc.table_name=zwv.table_name
where concat(zwv.view_code,zdc.column_name)
not in(select concat(view_code,column_name) from zw_field)
and zwv.view_code=p_view_code;



#chart
INSERT INTO `zapdata`.`zw_page`
(
`uid`,
`page_code`,
`page_name`,
`page_template`,
`view_code`,
`page_type_aid`,
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_chart_',zwv.view_code) as page_code
,concat(zwv.view_name,'-列表') as page_name
,'../zappage/chart' as page_template
,zwv.view_code as view_code
,116016003 as page_type_aid
,116022003 as view_type_aid
,concat('grouppage_',zwv.view_code) as page_code
from zw_view zwv
where 
(select count(1) from zapdata.zw_page where page_code=concat('page_chart_',zwv.view_code))=0
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
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_add_',zwv.view_code) as page_code
,concat(zwv.view_name,'-新增') as page_name
,'../zappage/add' as page_template
,zwv.view_code as view_code
,116016001 as page_type_aid
,116022001 as view_type_aid
,concat('grouppage_',zwv.view_code) as page_code
from zw_view zwv
where 
(select count(1) from zapdata.zw_page where page_code=concat('page_add_',zwv.view_code))=0
and zwv.view_code=p_view_code;

#edit
INSERT INTO `zapdata`.`zw_page`
(
`uid`,
`page_code`,
`page_name`,
`page_template`,
`view_code`,
`page_type_aid`,
`view_type_aid`,
`page_group`)
select replace(uuid(),'-','') as uid
,concat('page_edit_',zwv.view_code) as page_code
,concat(zwv.view_name,'-修改') as page_name
,'../zappage/edit' as page_template
,zwv.view_code as view_code
,116016005 as page_type_aid
,116022005 as view_type_aid
,concat('grouppage_',zwv.view_code) as page_code
from zw_view zwv
where 
(select count(1) from zapdata.zw_page where page_code=concat('page_edit_',zwv.view_code))=0
and zwv.view_code=p_view_code;





INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`)
select
replace(uuid(),'-','') as uid
,'添加' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016001 and view_code=zwp.view_code) as operate_link
,'1' as flag_enable
,'' as operate_func
,116001020 as area_type_aid
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('添加',zwp.page_code))=0
and zwp.page_type_aid=116016003 and  zwp.view_code=p_view_code;

INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`)
select
replace(uuid(),'-','') as uid
,'修改' as operate_name
,116015012 as operate_type_aid
,zwp.page_code as page_code
,(select page_code from zw_page where page_type_aid=116016003 and view_code=zwp.view_code) as operate_link
,'1' as flag_enable
,'' as operate_func
,116001003 as area_type_aid
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('修改',zwp.page_code))=0
and zwp.page_type_aid=116016003 and  zwp.view_code=p_view_code;


INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`)
select
replace(uuid(),'-','') as uid
,'删除' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_delete(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncDelete' as operate_func
,116001003 as area_type_aid
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('删除',zwp.page_code))=0
and zwp.page_type_aid=116016003 and  zwp.view_code=p_view_code;


INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`)
select
replace(uuid(),'-','') as uid
,'查询' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_inquire(this)' as operate_link
,'1' as flag_enable
,'' as operate_func
,116001009 as area_type_aid
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('查询',zwp.page_code))=0
and zwp.page_type_aid=116016003 and  zwp.view_code=p_view_code;




INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`)
select
replace(uuid(),'-','') as uid
,'提交新增' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_add(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncAdd' as operate_func
,116001016 as area_type_aid
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('提交新增',zwp.page_code))=0
and zwp.page_type_aid=116016001 and  zwp.view_code=p_view_code;


INSERT INTO zw_operate
(
`uid`,
`operate_name`,
`operate_type_aid`,
`page_code`,
`operate_link`,
`flag_enable`,
`operate_func`,
`area_type_aid`)
select
replace(uuid(),'-','') as uid
,'提交修改' as operate_name
,116015010 as operate_type_aid
,zwp.page_code as page_code
,'zapjs.zw.func_edit(this)' as operate_link
,'1' as flag_enable
,'com.srnpr.zapweb.webfunc.FuncEdit' as operate_func
,116001016 as area_type_aid
from zw_page zwp
where 
(select count(1) from zw_operate where  concat(operate_name,page_code)=concat('提交修改',zwp.page_code))=0
and zwp.page_type_aid=116016005 and  zwp.view_code=p_view_code;























































end$
DELIMITER ;