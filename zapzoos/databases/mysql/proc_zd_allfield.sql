DELIMITER $
DROP PROCEDURE IF EXISTS `proc_zd_allfield`$
CREATE PROCEDURE `proc_zd_allfield`()
begin


#delete from zapdata.zd_tables;
#delete from zapdata.zd_column;


########  初始化表
insert into zapdata.zd_tables (`uid`,`server_name`,`table_name`,`table_remark`)
select replace(uuid(),'-',''),TABLE_SCHEMA,TABLE_NAME,TABLE_COMMENT from information_schema.TABLES where TABLE_SCHEMA in
(select server_name from zapdata.zd_server)
and TABLE_NAME not in(select table_name from zapdata.zd_tables);




insert into zapdata.zd_column
(`uid`,`server_name`,`table_name`,`column_name`,`null_able_aid`,`column_type_aid`,`column_length`,`length_scale`,`column_note`,`column_sort`)
SELECT 
replace(uuid(),'-',''),
a.TABLE_SCHEMA server_name,
a.TABLE_NAME table_name,
a.column_name column_name,
(case a.IS_Nullable when 'YES' then 104014001 else 104014002 end)  null_able_aid,
(select b.abstract_aids from zapdata.zd_abstract b where rtrim(b.abstract_value)=rtrim(a.Data_type) and left(b.abstract_aids,6)=104003) column_type_aid,
ifnull(a.CHARACTER_MAXIMUM_LENGTH,ifnull(NUMERIC_PRECISION,0)) column_length,
ifnull(a.NUMERIC_SCALE,0) length_scale,
a.COLUMN_COMMENT column_note,
a.ORDINAL_POSITION column_sort

FROM information_schema.COLUMNS a
where a.TABLE_SCHEMA in
(select server_name from zapdata.zd_server)
and concat(a.TABLE_NAME,a.column_name) not in
(select  concat(table_name,column_name)  from  zapdata.zd_column );


end$
DELIMITER ;