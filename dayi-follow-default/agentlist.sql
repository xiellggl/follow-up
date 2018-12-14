


------------------------------本地dayi_spot-----------------------------------------

SELECT * from dev_dayi_spot.agent a

left join ( select id,invite_code,link_person,disable from dev_dayi_spot.flow_up )b on a.flow_id=b.id

left join (
	select id, link_person , bank_name , create_date , card_valid_date ,bank_sign_date , mobile, bank_sign , record_invite_code ,id_card, account_id,uc_id,bank_account from dev_dayi_spot.agent where del_status != -1
)c on c.id = a.id

left join dev_dayi_spot.finance_account d on d.id = c.account_id

left join (
		SELECT agent, IFNULL(SUM(amount), 0) amount from dev_dayi_spot.protocol WHERE `status` in (1,2) GROUP BY agent
)e on e.agent=c.id

left join (
SELECT pay_uc_id, MAX( CASE WHEN pay_type = '1' THEN accrual END ) AS inCash, MAX(CASE WHEN pay_type = '-114' THEN accrual END ) AS outCash, MAX(CASE WHEN pay_type = '1' THEN createDate END ) AS dayLastInCashTime, MAX(CASE WHEN pay_type = '-114' THEN createDate END ) AS dayLastOutCashTime FROM ( SELECT pay_uc_id,`pay_type`,SUM(accrual) accrual,MAX(`create_date`)createDate FROM dev_dayi_spot.finance_account_log WHERE create_date BETWEEN '2018-12-14 00:00:00' and '2018-12-14 23:59:59' AND pay_type IN (1,-114) and account_type = 0 GROUP BY pay_uc_id,`pay_type` )f1 GROUP BY `pay_uc_id`
)f ON c.uc_id = f.pay_uc_id

left join (
SELECT agent_id,GROUP_CONCAT( bank_id ORDER BY bank_id ) bank_ids FROM dev_dayi_spot.finance_vitrual_account GROUP BY agent_id
)g ON c.id = g.agent_id

WHERE 1 = 1 and b.disable != -1  AND b.id =36 ORDER BY a.flow_date DESC LIMIT 0 , 20



------------------------------线上-----------------------------------------


EXPLAIN SELECT * from agent a

left join ( select id,invite_code,link_person,disable from flow_up )b on a.flow_id=b.id

left join (
	select id, link_person , bank_name , create_date , card_valid_date ,bank_sign_date , mobile, bank_sign , record_invite_code ,id_card, account_id,uc_id,bank_account from agent where del_status != -1
)c on c.id = a.id


left join finance_account d on d.id = c.account_id

left join (
		SELECT agent, IFNULL(SUM(amount), 0) amount from protocol WHERE `status` in (1,2) GROUP BY agent
)e on e.agent=c.id

left join (
SELECT pay_uc_id, MAX( CASE WHEN pay_type = '1' THEN accrual END ) AS inCash, MAX(CASE WHEN pay_type = '-114' THEN accrual END ) AS outCash, MAX(CASE WHEN pay_type = '1' THEN createDate END ) AS dayLastInCashTime, MAX(CASE WHEN pay_type = '-114' THEN createDate END ) AS dayLastOutCashTime FROM ( SELECT pay_uc_id,`pay_type`,SUM(accrual) accrual,MAX(`create_date`)createDate FROM finance_account_log WHERE create_date BETWEEN '2018-12-13 00:00:00' and '2018-12-13 23:59:59' AND pay_type IN (1,-114) and account_type = 0 GROUP BY pay_uc_id,`pay_type` )f1 GROUP BY `pay_uc_id`
)f ON c.uc_id = f.pay_uc_id


left join (
SELECT agent_id,GROUP_CONCAT( bank_id ORDER BY bank_id ) bank_ids FROM finance_vitrual_account GROUP BY agent_id
)g ON c.id = g.agent_id


WHERE 1 = 1 and b.disable != -1  AND b.id =36 ORDER BY a.flow_date DESC LIMIT 0 , 20