ALTER TABLE project_mgmt.project add column client_id varchar(255);

ALTER TABLE project_mgmt.project
    ADD
    FOREIGN KEY (client_id)
    REFERENCES client(id);

