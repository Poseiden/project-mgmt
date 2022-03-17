create table project_mgmt.assignment
(
    id varchar(255) not null primary key,
    assignee varchar(255),
    project_role varchar(255),
    rate int,
    start datetime(6),
    end datetime(6),
    client_project_id varchar(255),
    FOREIGN KEY (client_project_id) REFERENCES project_mgmt.project(id)
);
