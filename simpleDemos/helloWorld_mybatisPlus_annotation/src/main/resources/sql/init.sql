CREATE TABLE Users
(
    id        INT primary key AUTO_INCREMENT,
    username  VARCHAR(100),
    password  VARCHAR(100),
    authority varchar(1000)
);
insert into Users(username, password, authority)
values ('lucia', '$2a$10$G.3nHW2dfsou8C0Wr8PsV.sWSzWugFNJT6vzB.XhvnuApaJ.Tv5r6', 'ROLE_admin,addPerson,getPerson');
insert into Users(username, password)
values ('userA', '$2a$10$Ne20F4ggG/CvbOmnYt8O4uX/c6t/vfGWoNOwQxhrGZ7sDsbWIracK');


CREATE TABLE Person
(
    id   INT primary key AUTO_INCREMENT,
    name VARCHAR(100),
    age  INT
);
insert into Person(`name`, age)
values ('lucia', 19);
insert into Person(`name`, age)
values ('lifu', 18);
