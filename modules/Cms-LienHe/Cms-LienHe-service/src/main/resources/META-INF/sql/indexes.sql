create index IX_19E2F3CC on Contact_Department (dptName[$COLUMN_LENGTH:75$]);
create index IX_441A66CB on Contact_Department (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B214240D on Contact_Department (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_608E507C on Contact_Person (psName[$COLUMN_LENGTH:75$]);
create index IX_443E96CE on Contact_Person (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_327A78D0 on Contact_Person (uuid_[$COLUMN_LENGTH:75$], groupId);