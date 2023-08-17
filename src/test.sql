select table_name from user_tables;

select * from alpha;
select distinct bg, fg from alpha order by bg;
select line, line+10 from alpha;

select * from emp;
select ename, nvl(comm, 0) from emp;
select ename || job || '님' from emp;
select * from emp where sal > 500;
select * from emp where sal between 400 and 500;
select * from emp where sal in (400, 450, 500);
select * from emp where ename like '_동%';
select * from emp where comm is null;

select * from group_star;
select * from single_star;
select * from group_star union select * from single_star;
select * from group_star union all select * from single_star;

select 24*4 from dual;
select round(3.5) from dual;
select trunc(3.5) from dual;
select trunc(-3.2) from dual;
select floor(-3.2) from dual;
select trunc(34.5678, 2) from dual;

select round(stddev(line), 2), round(stddev(col), 2) from alpha;


