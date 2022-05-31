insert into usr (social, email, pwd, nm, ph_num, cash, use_yn, rgs_dt, upd_dt) values ('NONE' ,'hijinhoon@naver.com', 'admin', '관리자', '01050188147', 0, true, now(), now());
insert into usr (social, email, pwd, nm, ph_num, cash, use_yn, rgs_dt, upd_dt) values ('NAVER' ,'hijinhoon1@naver.com', null, '관리자1', '01050188147', 0, true, now(), now());
insert into usr (social, email, pwd, nm, ph_num, cash, use_yn, rgs_dt, upd_dt) values ('GOOGLE' ,'hijinhoon2@naver.com', null, '관리자2', '01050188147', 0, true, now(), now());

insert into agree (text, use_yn, rgs_dt, upd_dt) values ('이용약관 동의', true, now(), now());
insert into agree (text, use_yn, rgs_dt, upd_dt) values ('개인정보 처리방침동의', true, now(), now());
insert into agree (text, use_yn, rgs_dt, upd_dt) values ('이용약관 동의', true, now(), now());

insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (1, 1, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (1, 2, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (1, 3, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (2, 1, false, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (2, 2, false, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (2, 3, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (3, 1, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (3, 2, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (3, 3, false, now(), now());

insert into usr_cash (usr_id, cash_type, charging_amount, remaining_amount, expiry_time, rgs_dt, upd_dt) values (1, 'CHARGING', 10000, 0, TIMESTAMPADD(DAY, 1, now()), now(), now());
insert into usr_cash (usr_id, cash_type, charging_amount, remaining_amount, expiry_time, rgs_dt, upd_dt) values (1, 'CHARGING', 20000, 0, TIMESTAMPADD(DAY, 1, now()), now(), now());
insert into usr_cash (usr_id, cash_type, charging_amount, remaining_amount, expiry_time, rgs_dt, upd_dt) values (1, 'SAVING', 5000, 0, TIMESTAMPADD(DAY, 1, now()), now(), now());

insert into usr_cash_log (usr_id, order_num, occur_type, occur_cash, occur_start_time, occur_finish_time, sum_cash, charging_cash, saving_cash, description, rgs_dt, upd_dt) values (1, 11000001, 'CHARGING_COMPLETE', 10000, now(), now(), 10000, 10000, 0, '충전완료', now(), now());
insert into usr_cash_log (usr_id, order_num, occur_type, occur_cash, occur_start_time, occur_finish_time, sum_cash, charging_cash, saving_cash, description, rgs_dt, upd_dt) values (1, 11000002, 'CHARGING_COMPLETE', 20000, now(), now(), 30000, 30000, 0, '충전완료', now(), now());
insert into usr_cash_log (usr_id, order_num, occur_type, occur_cash, occur_start_time, occur_finish_time, sum_cash, charging_cash, saving_cash, description, rgs_dt, upd_dt) values (1, 11000003, 'SAVING_COMPLETE', 5000, now(), now(), 35000, 30000, 5000, '적립완료', now(), now());
insert into usr_cash_log (usr_id, order_num, occur_type, occur_cash, occur_start_time, occur_finish_time, sum_cash, charging_cash, saving_cash, description, rgs_dt, upd_dt) values (1, 11000004, 'USE_COMPLETE', 35000, now(), now(), 0, 0, 0, '사용완료', now(), now());
