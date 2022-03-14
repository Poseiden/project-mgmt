create table project_mgmt.location
(
    id varchar(255) not null primary key
);


create table project_mgmt.project
(
    id varchar(255) not null primary key,
    name varchar(255),
    location_id varchar(255),
    FOREIGN KEY (location_id) REFERENCES project_mgmt.location(id)
);
