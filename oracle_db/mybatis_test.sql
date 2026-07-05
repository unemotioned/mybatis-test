-- user: mybatis_test
-- pw: 1234

drop table tbl_member cascade constraints;
drop table tbl_board;
drop table tbl_level;

drop sequence seq_member;
drop sequence seq_board;

create table tbl_member (
    member_no number primary key,
    member_id varchar2(20) unique not null,
    member_pw varchar2(30) not null,
    member_name varchar2(30) not null,
    member_email varchar2(100) unique not null,
    member_phone char(13) not null,
    member_addr varchar2(200) not null,
    member_level number default 3 not null,
    enroll_date date default sysdate not null
);

create sequence seq_member;

insert into tbl_member values
(
    seq_member.nextval, 
    'admin',
    '4444', 
    '관리자', 
    'test@naver.com', 
    '010-1234-1234', 
    '서울', 
    1, 
    sysdate
);

create or replace procedure procedure_ins_member as
begin
    for i in 1..75 loop
        insert into tbl_member values (
            seq_member.nextval, 
            'user' || i, 
            '1234', 
            '유저' || i, 
            'test' || i || '@naver.com', 
            '010-1234-5678', 
            'addr' || i, 
            2, 
            sysdate
        );
    end loop;
    commit;
end;
/

begin 
    procedure_ins_member;
end;
/

insert into tbl_member values ( seq_member.nextval, 'user77', '1234', '유저77', 'test77@naver.com', '010-1234-1234', '서울 강감구 삼성동', 2, sysdate);
insert into tbl_member values ( seq_member.nextval, 'user78', '1234', '유저78', 'test78@naver.com', '010-1234-1234', '경기 가평군', 2, sysdate);
insert into tbl_member values ( seq_member.nextval, 'user79', '1234', '유저79', 'test79@naver.com', '010-1234-1234', '부산광역시 금정구', 2, sysdate);
insert into tbl_member values ( seq_member.nextval, 'user80', '1234', '유저80', 'test80@naver.com', '010-1234-1234', '전라남도 목포시', 2, sysdate);
insert into tbl_member values ( seq_member.nextval, 'user81', '1234', '유저81', 'test81@naver.com', '010-1234-1234', '서울 강서구 화곡동', 2, sysdate);
insert into tbl_member values ( seq_member.nextval, 'user82', '1234', '유저82', 'test82@naver.com', '010-1234-1234', '부산광역시 해운대구', 2, sysdate);
insert into tbl_member values ( seq_member.nextval, 'user83', '1234', '유저83', 'test83@naver.com', '010-1234-1234', '경기 성남시 분당구', 2, sysdate);

-- 오늘 가입한 회원 조회
select * 
    from tbl_member
where to_char(enroll_date, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd');

-- 서울, 경기, 부산, 목포

-- 서울 체크하고서 조회
select *
    from tbl_member
    where member_addr like '%서울%';

-- 서울, 부산 체크하고서 조회
select * 
    from tbl_member
    where member_addr like '%서울%'
    or member_addr like '%부산%';
    
-- 전부 체크하고서 조회 : 전부 or 로 연결

create table tbl_level (
    level_code number primary key,
    level_name varchar2(20)
);

insert into tbl_level values (1, '관리자');
insert into tbl_level values (2, '정회원');
insert into tbl_level values (3, '준회원');

select * 
    from tbl_member, tbl_level 
    where member_level = level_code;

-- join
select * 
    from tbl_member
    join tbl_level on (member_level = level_code);
    
-- sub query
select a.*,
        (select level_name from tbl_level z where z.level_code = a.member_level) level_name
    from tbl_member a;
    
create table tbl_board (
    board_no number primary key,
    board_title varchar2(100) not null,
    board_content varchar2(2000) not null,
    board_writer number references tbl_member(member_no) on delete cascade,
    read_count number default 0,
    reg_date date default sysdate
);

create sequence seq_board;

create or replace procedure tbl_board_ins_procedure as
begin
    for i in 1..153 loop
        insert into tbl_board values
        (
            seq_board.nextval,
            'title' || i,
            'content' || i,
            1,
            default, 
            default
        );
    end loop;
    commit;
end;
/

begin
    tbl_board_ins_procedure;
end;
/

select * from tbl_board;

commit;
