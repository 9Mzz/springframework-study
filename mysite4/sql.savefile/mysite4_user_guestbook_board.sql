select * from users;

insert into users 
values(seq_users_no.nextval, 'op', '90', '이정재', 'male');

update	    users
set		    password= '12',
 			name= 'aa', 
 			gender= 'female' 
where	    no= 7;

select no, id, password, name, gender
from users
where id= 'qwer'
and password= '1234';

/*********************************************************************/

select * from guestbook;

select      no,
            name,
            password,
            content,
            reg_date
from        guestbook
order by    reg_date desc;


insert into guestbook
values(seq_guestbook_no.nextval, 'ddd', 'ddd', 'ddd', sysdate);


select      no,
            name,
            password,
            content,
            reg_date
from        guestbook
where       no= 30;


delete from guestbook
where	   no= 30;

/******************************************************************/




select      b.no no, 
            title, 
            content, 
            hit, 
            to_char(reg_date, 'YYYY-MM-DD HH:MI:SS') regDate,
            user_no, 
            u.name username
from        users u, board b 
where       u.no= b.user_no 
order by    reg_date desc;


update   board 
set      hit= hit+1
where    no= 9;


select      b.no no, 
            title, 
            content, 
            hit, 
            to_char(reg_date, 'YYYY-MM-DD HH:MI:SS') regDate,
            user_no, 
            u.name username
from        users u, board b 
where       u.no= b.user_no
and         b.no= 9;


update      board
set         title= '안녕',
            content= '안녕123' 
where       no= 9;

delete from board 
where	    no= 9; 

insert into board
values      (seq_board_no.nextval, '안녕', '안녕하세요', 0, sysdate, 2);

rollback;