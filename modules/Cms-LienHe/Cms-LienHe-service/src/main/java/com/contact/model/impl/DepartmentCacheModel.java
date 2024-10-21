/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.model.impl;

import com.contact.model.Department;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Department in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DepartmentCacheModel
	implements CacheModel<Department>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DepartmentCacheModel)) {
			return false;
		}

		DepartmentCacheModel departmentCacheModel =
			(DepartmentCacheModel)object;

		if (dptId == departmentCacheModel.dptId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dptId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", dptId=");
		sb.append(dptId);
		sb.append(", dptName=");
		sb.append(dptName);
		sb.append(", dptDesc=");
		sb.append(dptDesc);
		sb.append(", dptStatus=");
		sb.append(dptStatus);
		sb.append(", dptDelete=");
		sb.append(dptDelete);
		sb.append(", dptcreateDate=");
		sb.append(dptcreateDate);
		sb.append(", dptmodifiedDate=");
		sb.append(dptmodifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Department toEntityModel() {
		DepartmentImpl departmentImpl = new DepartmentImpl();

		if (uuid == null) {
			departmentImpl.setUuid("");
		}
		else {
			departmentImpl.setUuid(uuid);
		}

		departmentImpl.setGroupId(groupId);
		departmentImpl.setCompanyId(companyId);
		departmentImpl.setUserId(userId);

		if (userName == null) {
			departmentImpl.setUserName("");
		}
		else {
			departmentImpl.setUserName(userName);
		}

		departmentImpl.setDptId(dptId);

		if (dptName == null) {
			departmentImpl.setDptName("");
		}
		else {
			departmentImpl.setDptName(dptName);
		}

		if (dptDesc == null) {
			departmentImpl.setDptDesc("");
		}
		else {
			departmentImpl.setDptDesc(dptDesc);
		}

		departmentImpl.setDptStatus(dptStatus);
		departmentImpl.setDptDelete(dptDelete);

		if (dptcreateDate == Long.MIN_VALUE) {
			departmentImpl.setDptcreateDate(null);
		}
		else {
			departmentImpl.setDptcreateDate(new Date(dptcreateDate));
		}

		if (dptmodifiedDate == Long.MIN_VALUE) {
			departmentImpl.setDptmodifiedDate(null);
		}
		else {
			departmentImpl.setDptmodifiedDate(new Date(dptmodifiedDate));
		}

		departmentImpl.resetOriginalValues();

		return departmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();

		dptId = objectInput.readLong();
		dptName = objectInput.readUTF();
		dptDesc = objectInput.readUTF();

		dptStatus = objectInput.readLong();

		dptDelete = objectInput.readLong();
		dptcreateDate = objectInput.readLong();
		dptmodifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(dptId);

		if (dptName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dptName);
		}

		if (dptDesc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dptDesc);
		}

		objectOutput.writeLong(dptStatus);

		objectOutput.writeLong(dptDelete);
		objectOutput.writeLong(dptcreateDate);
		objectOutput.writeLong(dptmodifiedDate);
	}

	public String uuid;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long dptId;
	public String dptName;
	public String dptDesc;
	public long dptStatus;
	public long dptDelete;
	public long dptcreateDate;
	public long dptmodifiedDate;

}