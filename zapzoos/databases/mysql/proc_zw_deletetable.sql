-- --------------------------------------------------------------------------------
-- 删除一个表格
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------

DELIMITER $
DROP PROCEDURE IF EXISTS `proc_zw_deletetable`$
CREATE PROCEDURE `proc_zw_deletetable`(in p_tablename varchar(100))
begin

delete from zw_field where view_code in(select view_code from zw_view where table_name=p_tablename);
delete from zw_operate where page_code in( select page_code from zw_page where view_code in  (select view_code from zw_view where table_name=p_tablename));
delete from zw_page where  view_code in(select view_code from zw_view where table_name=p_tablename);
delete from zw_view where table_name=p_tablename;
delete from zd_column where table_name=p_tablename;
delete from zd_tables where table_name=p_tablename;

end$
DELIMITER ;