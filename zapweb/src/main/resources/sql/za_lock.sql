
-- 加锁的存储过程。

DROP PROCEDURE IF EXISTS proc_lock_or_unlock_somekey;

/* 
* 2013-07-26 yanzj  加锁的存储过程。
* cardnostr somekey 字符串 如 ： 31R40RSW6VTC,31TLAC1R9CQA
* cardsplit   somekey 之间的分隔符 ','
* lockflag 1 加锁， 2 解锁 
* outFlag 1 成功，2 插入失败，表明，有些key是使用中的
* error 如果出错了，输出错误信息。
*
*/
CREATE PROCEDURE proc_lock_or_unlock_somekey(in somekey VARCHAR(50000),in keysplit VARCHAR(2),in timeoutsecond INT,in lockflag INT,in uuid VARCHAR(50))
BEGIN
	
	DECLARE i int DEFAULT 0;
	DECLARE lockid VARCHAR(32) DEFAULT '';
	DECLARE createTime VARCHAR(50) DEFAULT '';
	DECLARE lockCurrentKey VARCHAR(50) DEFAULT '';
	DECLARE lockzid INT DEFAULT 0; 
	DECLARE outFlag INT DEFAULT 2;
	

	/** 标记是否出错 */ 
	DECLARE t_error int default 0; 
	/** 如果出现sql异常，则将t_error设置为1后继续执行后面的操作 */ 
	DECLARE continue handler for sqlexception set t_error=1; -- 出错处理 
	SET outFlag=2;

	-- 加锁
	IF lockflag =1 THEN

				-- SET global log_bin_trust_function_creators=1;
				/** 设置时间 */ 
				SET createTime=CONCAT(current_timestamp,'');
				-- SELECT  uuid;
				
				-- SELECT lockid;
				-- 循环加入到 cc_cardlock

				DELETE FROM zd_lock where keycode=somekey and (UNIX_TIMESTAMP(createTime) - UNIX_TIMESTAMP(create_time))>timeoutsecond;


				SELECT zid INTO lockzid from zd_lock where keycode=somekey;

					IF FOUND_ROWS()<=0 THEN


						-- START TRANSACTION;
							-- 插入锁
								INSERT INTO zd_lock (uid,keycode,keyid,create_time)
									SELECT REPLACE(UUID(),'-',''),somekey,uuid,createTime ;

								IF ROW_COUNT()<=0 THEN
									-- SELECT 3;
									set outFlag=2;

								ELSE
									
									SET outFlag=1;
								END IF;

					ELSE
						SET uuid=lockid;
						SET outFlag=2;
					END IF;

		
	-- 解锁 
	ELSE 

			IF lockflag =2 THEN
				-- SELECT lockid;
				-- 循环加入到 cc_cardlock
				START TRANSACTION;
				
				 DELETE FROM zd_lock where keyid=uuid;

				-- 如果 插入失败了 ，
				IF t_error=1 THEN 
					SET uuid='';
					SET outFlag=2;
					ROLLBACK;
				ELSE
					SET uuid='';
					SET outFlag=1;
					COMMIT;			
				END IF;	
			ELSE
				SET uuid='';
				SET outFlag=2;

			END IF;
	END IF;

	SELECT outFlag;

END;