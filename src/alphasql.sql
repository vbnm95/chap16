select table_name from user_tables;

drop table dept2;

create table alpha (
    line number(2),
    col number(2),
    fg varchar2(7 char),
    bg varchar2(7 char),
    ch char(1)
);

desc alpha;

select * from alpha;
select count(*) from alpha;

select line, col, count(*)
  from alpha
group by line, col;

select count(*)
  from alpha
 where line = 11
   and col =5;

select *
  from alpha
order by col desc, line desc;

insert into alpha values(10, 10, 'Black', 'White', 'A');

commit;
