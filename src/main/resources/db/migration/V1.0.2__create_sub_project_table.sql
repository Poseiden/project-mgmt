create table project_mgmt.sub_project
(
    id varchar(255) not null primary key,
    name varchar(255),
    project_id varchar(255),
    FOREIGN KEY (project_id) REFERENCES project_mgmt.project(id)
);

