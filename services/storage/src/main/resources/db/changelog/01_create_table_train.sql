CREATE TABLE train
(
    id               SERIAL PRIMARY KEY,
    series           varchar(3) not null,
    name_series      varchar(20) not null,
    train_number     int4 not null unique,
    building_mileage int4 not null,
    data_build       timestamp not null,
    last_repair      varchar(6),
    condition        varchar(10) not null,
    image_url        varchar(1500)
);