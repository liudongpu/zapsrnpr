DELIMITER $
DROP PROCEDURE IF EXISTS `proc_zw_getcode`$
CREATE PROCEDURE `proc_zw_getcode`(in p_code_start varchar(100))
begin

declare p_date char(6);
declare p_now char(6);
declare p_return varchar(30);

declare p_nowno int;

set p_now=DATE_FORMAt(now(), '%y%m%d') ;


#查询系统时间  如果表中该参数不为6位则忽略该标记
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

#判断是否更新日期
 if(p_date!=p_now) then

	#如果日期标记位的长度不为6位  则直接使用该标记位作为序列参数
	if length(p_date)!=6 then
		set p_now='';
	else
		update zw_webcode set now_number=min_number,date_apply=p_now where code_start=p_code_start and flag_date=1;
	end if;

end if;

start transaction; 
set p_return=(select now_number from zw_webcode zwwc  where zwwc.code_start=p_code_start for update);
set p_return=p_return+1;
update zw_webcode set now_number=p_return where code_start=p_code_start;

commit;

select concat(p_code_start,p_now,p_return) as webcode;



end
$
DELIMITER ;