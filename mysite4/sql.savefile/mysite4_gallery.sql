/*******************************************
*                 초기화
*******************************************/

-- 기존 테이블 삭제
drop table gallery;

-- 기존 SEQUENCE 삭제
drop sequence seq_gallery_no;


-- SEQUENCE 생성
create sequence seq_gallery_no
increment by 1
start with 1
nocache;

-- gallery 테이블 생성
create table gallery(
    no              number(5),
    user_no         number(5),
    content         varchar2(1000),
    file_path       varchar2(500),
    org_name        varchar2(200),
    save_name       varchar2(500),
    file_size       number,
    primary key(no),
    constraint gallery_fk foreign key(user_no)
    references users(no)); 

/************************************************************/

insert into gallery
values(seq_gallery_no.nextval, 2, '이효리 사진입니다.', '파일경로', '오리지널파일명', '저장파일명', 10);

delete from gallery
where no=2;

commit;


select * from gallery;


select      g.no,
            user_no,
            u.name,
            content,
            file_path,
            org_name,
            save_name,
            file_size
from        users u, gallery g
where       u.no= g.user_no
order by    no desc;


select      g.no,
            user_no,
            u.name,
            content,
            file_path,
            org_name,
            save_name,
            file_size
from        users u, gallery g
where       u.no= g.user_no
and         g.no= 3;




select * from users;