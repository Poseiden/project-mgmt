create table project_mgmt.sub_project_type
(
    id varchar(255) not null primary key
);

ALTER TABLE project_mgmt.sub_project add column sub_project_type_id varchar(255);

ALTER TABLE project_mgmt.sub_project
    ADD
    FOREIGN KEY (sub_project_type_id)
    REFERENCES sub_project_type(id);

