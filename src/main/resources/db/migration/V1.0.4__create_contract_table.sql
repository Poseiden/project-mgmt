create table project_mgmt.contract
(
    id varchar(255) not null primary key
);

ALTER TABLE project_mgmt.project add column contract_id varchar(255);

ALTER TABLE project_mgmt.project
    ADD
    FOREIGN KEY (contract_id)
    REFERENCES contract(id);

