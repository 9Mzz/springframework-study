/*******************************************
*                 초기화
*******************************************/

-- 기존 테이블 삭제
drop table rboard;

-- 기존 SEQUENCE 삭제
drop sequence seq_rboard_no;
drop sequence seq_group_no;

-- SEQUENCE 생성
create sequence seq_rboard_no
increment by 1
start with 1
nocache;

create sequence seq_group_no
increment by 1
start with 1
nocache;


-- board 테이블 생성
create table rboard(
    no              number(5),
    user_no         number(5) not null,
    title           varchar2(500),
    content         varchar2(4000),
    hit             number(5),
    reg_date        date,
    group_no        number(5),
    order_no        number(5),
    depth           number(5),
    primary key(no),
    constraint rboard_fk foreign key(user_no)
    references users(no)); 

/************************************************************/

insert into rboard
values(seq_rboard_no.nextval, 2, '안녕하세요', '안녕하세요', 0, sysdate, seq_group_no.nextval, 1, 0);

insert into rboard
values(seq_rboard_no.nextval, 1, '반갑습니다', '반갑습니다', 0, sysdate, seq_group_no.nextval, 1, 0);

insert into rboard
values(seq_rboard_no.nextval, 1, '회식합시다', '회식합시다', 0, sysdate, seq_group_no.nextval, 1, 0);

insert into rboard
values(seq_rboard_no.nextval, 1, '날씨가 좋아요!', '날씨가 좋아요!', 0, sysdate, seq_group_no.nextval, 1, 0);

insert into rboard
values(seq_rboard_no.nextval, 2, '봄입니다.', '봄입니다.', 0, sysdate, seq_group_no.nextval, 1, 0);

select * from rboard;


commit;


select * from board;


select      r.no,
            user_no userNo,
            title,
            content,
            hit,
            to_char(reg_date, 'YYYY-MM-DD HH:MI:SS') regDate,
            u.name userName,
            group_no groupNo,
            order_no orderNo,
            depth
from        users u, rboard r
where       u.no= r.user_no
order by    groupNo desc, orderNo asc, depth desc;



update			rboard
set 			order_no= 1+1,
     			depth= 0+1
where			group_no= 3
and 			order_no> 1;

rollback;