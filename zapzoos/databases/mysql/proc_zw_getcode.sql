DELIMITER $
DROP PROCEDURE IF EXISTS `proc_zw_getcode`$
CREATE PROCEDURE `proc_zw_getcode`(in p_code_start varchar(100))
begin

declare p_date char(6);
declare p_now char(6);
declare p_return varchar(30);

declare p_nowno int;

set p_now=DATE_FORMAt(now(), '%y%m%d') ;


#查询系统时间
set p_date=ifnull((select a.date_apply from zw_webcode a where  a.code_start=p_code_start),'');

if(p_date='')
THEN 
	INSERT INTO `zw_webcode`
	(
	`uid`,
	`code_start`,
	`date_apply`,
	`min_number`,
	`now_number`)
	VALUES
	(
		replace(uuid(),'-',''),
		p_code_start,
		p_now,
		100000,
		100000
	);
	set p_date=p_now;

end if;

 if(p_date!=p_now) then

 update zw_webcode set now_number=min_number where code_start=p_code_start;

end if;


set p_return=(select now_number from zw_webcode zwwc  where zwwc.code_start=p_code_start for update);
set p_return=p_return+1;
update zw_webcode set now_number=p_return where code_start=p_code_start;





select concat(p_code_start,p_now,p_return) as webcode;


end
$
DELIMITER ;