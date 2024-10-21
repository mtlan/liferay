create table Contact_Department (
	uuid_ VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	dptId LONG not null primary key,
	dptName VARCHAR(75) null,
	dptDesc VARCHAR(75) null,
	dptStatus LONG,
	dptDelete LONG,
	dptcreateDate DATE null,
	dptmodifiedDate DATE null
);

create table Contact_Person (
	uuid_ VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	psId LONG not null primary key,
	psName VARCHAR(75) null,
	psPosition VARCHAR(75) null,
	dptId LONG,
	psPhone VARCHAR(75) null,
	psEmail VARCHAR(75) null,
	psStatus LONG,
	psDelete LONG,
	pscreate DATE null,
	psmodifiedDate DATE null
);