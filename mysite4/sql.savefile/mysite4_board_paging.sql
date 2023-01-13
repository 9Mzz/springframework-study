select  rn,
        no,
        title,
        content,
        hit,
        regDate,
        userNo,
        userName
from (select  rownum rn,
              no,
              title,
              content,
              hit,
              regDate,
              userNo,
              userName
     from    (select  b.no no,
                      title,
                      content,
                      hit,
                      to_char(reg_date, 'YYYY-MM-DD HH24:MI:SS') regDate,
                      u.no userNo,
                      u.name userName
             from     board b, users u
             where    b.user_no= u.no
             order by b.no desc) ot) rt
where   rn between 1 and 10;


select  count(*)
from    board b, users u
where   b.user_no= u.no;
