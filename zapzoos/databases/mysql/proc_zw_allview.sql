DELIMITER $
DROP PROCEDURE IF EXISTS `proc_zw_allview`$
CREATE PROCEDURE `proc_zw_allview`()
begin

declare p_min int;
declare p_vc varchar(100);

set p_min=(select min(zid) from zw_view);

while p_min>0 do
set p_vc=(select view_code from zw_view where zid=p_min);

call proc_zw_initview(p_vc);
set p_min=ifnull((select min(zid) from zw_view where zid>p_min),0);
end while;

end
$
DELIMITER ;