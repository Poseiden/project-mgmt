create table project_mgmt.client
(
    id varchar(255) not null primary key
);

ALTER TABLE project_mgmt.contract add column client_id varchar(255);

ALTER TABLE project_mgmt.contract
    ADD
    FOREIGN KEY (client_id)
    REFERENCES client(id);

