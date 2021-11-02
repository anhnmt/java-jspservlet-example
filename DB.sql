create
database DB_JSPServlet_Lab15;

go

use DB_JSPServlet_Lab15;

go

create table categories
(
    CateId      int primary key identity,
    CateName    nvarchar(225) not null,
    Description nvarchar( max),
    Status      tinyint,
);

go

create table products
(
    ProId      int primary key identity,
    ProName    nvarchar(225) not null,
    CateId     int not null,
    Producer   nvarchar(225),
    YearMaking int,
    ExpireDate date,
    Quantity   int,
    Price      float,
    Status     tinyint,
    FOREIGN KEY (CateId) REFERENCES categories (CateId)
);