#修复2019-01-11,23:30定时任务的manage_growth_fund统计错误，减去了9号的，应该减10号的，本脚本还没有执行
drop PROCEDURE if EXISTS circle_xll;

delimiter //
CREATE PROCEDURE circle_xll ( )
BEGIN
DECLARE id VARCHAR(30) DEFAULT NULL;
DECLARE fund DECIMAL(15,2) DEFAULT 0;
DECLARE done INT DEFAULT false;

DECLARE cur CURSOR FOR
SELECT follow_id as id
FROM follow_up_log
WHERE date(create_time) = '2019-01-11';

DECLARE CONTINUE HANDLER FOR NOT FOUND
SET done = true;

OPEN cur;
FETCH cur INTO id;

	WHILE ( NOT done ) DO
		SELECT
			t11.manage_fund - t10.manage_fund into fund
		FROM
			follow_up_log t11
			LEFT JOIN ( SELECT * FROM follow_up_log WHERE date( create_time ) = '2019-01-10' ) t10 ON t10.follow_id = t11.follow_id
		WHERE
			date( t11.create_time ) = '2019-01-11'
			AND t11.follow_id = id;

		UPDATE follow_up_log
		SET manage_growth_fund = fund
		WHERE
			follow_id = id
			AND date( create_time ) = '2019-01-11';
		FETCH cur INTO id;
	END WHILE;

	close cur;

END;
//