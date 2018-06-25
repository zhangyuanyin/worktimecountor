drop table excel_tab;

create table excel_tab (
    id varchar(30) not null,
    clock_date date NOT NULL,
    clock_time time not null,
    times int(6),
    flag char(2),
    name varchar(32) not null,
    col1 char(2),
    col2 char(2),
    col3 char(2)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;