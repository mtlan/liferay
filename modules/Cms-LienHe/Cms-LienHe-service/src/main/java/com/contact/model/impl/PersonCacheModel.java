/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.model.impl;

import com.contact.model.Person;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Person in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PersonCacheModel implements CacheModel<Person>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PersonCacheModel)) {
			return false;
		}

		PersonCacheModel personCacheModel = (PersonCacheModel)object;

		if (psId == personCacheModel.psId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, psId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

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
		sb.append(", psId=");
		sb.append(psId);
		sb.append(", psName=");
		sb.append(psName);
		sb.append(", psPosition=");
		sb.append(psPosition);
		sb.append(", dptId=");
		sb.append(dptId);
		sb.append(", psPhone=");
		sb.append(psPhone);
		sb.append(", psEmail=");
		sb.append(psEmail);
		sb.append(", psStatus=");
		sb.append(psStatus);
		sb.append(", psDelete=");
		sb.append(psDelete);
		sb.append(", pscreate=");
		sb.append(pscreate);
		sb.append(", psmodifiedDate=");
		sb.append(psmodifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Person toEntityModel() {
		PersonImpl personImpl = new PersonImpl();

		if (uuid == null) {
			personImpl.setUuid("");
		}
		else {
			personImpl.setUuid(uuid);
		}

		personImpl.setGroupId(groupId);
		personImpl.setCompanyId(companyId);
		personImpl.setUserId(userId);

		if (userName == null) {
			personImpl.setUserName("");
		}
		else {
			personImpl.setUserName(userName);
		}

		personImpl.setPsId(psId);

		if (psName == null) {
			personImpl.setPsName("");
		}
		else {
			personImpl.setPsName(psName);
		}

		if (psPosition == null) {
			personImpl.setPsPosition("");
		}
		else {
			personImpl.setPsPosition(psPosition);
		}

		personImpl.setDptId(dptId);

		if (psPhone == null) {
			personImpl.setPsPhone("");
		}
		else {
			personImpl.setPsPhone(psPhone);
		}

		if (psEmail == null) {
			personImpl.setPsEmail("");
		}
		else {
			personImpl.setPsEmail(psEmail);
		}

		personImpl.setPsStatus(psStatus);
		personImpl.setPsDelete(psDelete);

		if (pscreate == Long.MIN_VALUE) {
			personImpl.setPscreate(null);
		}
		else {
			personImpl.setPscreate(new Date(pscreate));
		}

		if (psmodifiedDate == Long.MIN_VALUE) {
			personImpl.setPsmodifiedDate(null);
		}
		else {
			personImpl.setPsmodifiedDate(new Date(psmodifiedDate));
		}

		personImpl.resetOriginalValues();

		return personImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();

		psId = objectInput.readLong();
		psName = objectInput.readUTF();
		psPosition = objectInput.readUTF();

		dptId = objectInput.readLong();
		psPhone = objectInput.readUTF();
		psEmail = objectInput.readUTF();

		psStatus = objectInput.readLong();

		psDelete = objectInput.readLong();
		pscreate = objectInput.readLong();
		psmodifiedDate = objectInput.readLong();
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

		objectOutput.writeLong(psId);

		if (psName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(psName);
		}

		if (psPosition == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(psPosition);
		}

		objectOutput.writeLong(dptId);

		if (psPhone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(psPhone);
		}

		if (psEmail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(psEmail);
		}

		objectOutput.writeLong(psStatus);

		objectOutput.writeLong(psDelete);
		objectOutput.writeLong(pscreate);
		objectOutput.writeLong(psmodifiedDate);
	}

	public String uuid;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long psId;
	public String psName;
	public String psPosition;
	public long dptId;
	public String psPhone;
	public String psEmail;
	public long psStatus;
	public long psDelete;
	public long pscreate;
	public long psmodifiedDate;

}