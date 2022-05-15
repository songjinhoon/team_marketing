insert into usr (email, pwd, nm, ph_num, use_at, rgs_dt, upd_dt) values ('hijinhoon@naver.com', 'admin', '관리자', '01050188147', true, now(), now());
insert into usr (email, pwd, nm, ph_num, use_at, rgs_dt, upd_dt) values ('hijinhoon1@naver.com', 'admin', '관리자1', '01050188147', true, now(), now());
insert into usr (email, pwd, nm, ph_num, use_at, rgs_dt, upd_dt) values ('hijinhoon2@naver.com', 'admin', '관리자2', '01050188147', true, now(), now());

insert into agree (text, use_at, rgs_dt, upd_dt) values ('이용약관 동의', true, now(), now());
insert into agree (text, use_at, rgs_dt, upd_dt) values ('개인정보 처리방침동의', true, now(), now());
insert into agree (text, use_at, rgs_dt, upd_dt) values ('이용약관 동의', true, now(), now());

insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (1, 1, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (1, 2, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (1, 3, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (2, 1, false, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (2, 2, false, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (2, 3, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (3, 1, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (3, 2, true, now(), now());
insert into usr_agree (usr_id, agree_id, agree_yn, rgs_dt, upd_dt ) values (3, 3, false, now(), now());
