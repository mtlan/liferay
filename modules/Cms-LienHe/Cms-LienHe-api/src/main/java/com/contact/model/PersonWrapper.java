/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Person}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Person
 * @generated
 */
public class PersonWrapper
	extends BaseModelWrapper<Person> implements ModelWrapper<Person>, Person {

	public PersonWrapper(Person person) {
		super(person);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("psId", getPsId());
		attributes.put("psName", getPsName());
		attributes.put("psPosition", getPsPosition());
		attributes.put("dptId", getDptId());
		attributes.put("psPhone", getPsPhone());
		attributes.put("psEmail", getPsEmail());
		attributes.put("psStatus", getPsStatus());
		attributes.put("psDelete", getPsDelete());
		attributes.put("pscreate", getPscreate());
		attributes.put("psmodifiedDate", getPsmodifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Long psId = (Long)attributes.get("psId");

		if (psId != null) {
			setPsId(psId);
		}

		String psName = (String)attributes.get("psName");

		if (psName != null) {
			setPsName(psName);
		}

		String psPosition = (String)attributes.get("psPosition");

		if (psPosition != null) {
			setPsPosition(psPosition);
		}

		Long dptId = (Long)attributes.get("dptId");

		if (dptId != null) {
			setDptId(dptId);
		}

		String psPhone = (String)attributes.get("psPhone");

		if (psPhone != null) {
			setPsPhone(psPhone);
		}

		String psEmail = (String)attributes.get("psEmail");

		if (psEmail != null) {
			setPsEmail(psEmail);
		}

		Long psStatus = (Long)attributes.get("psStatus");

		if (psStatus != null) {
			setPsStatus(psStatus);
		}

		Long psDelete = (Long)attributes.get("psDelete");

		if (psDelete != null) {
			setPsDelete(psDelete);
		}

		Date pscreate = (Date)attributes.get("pscreate");

		if (pscreate != null) {
			setPscreate(pscreate);
		}

		Date psmodifiedDate = (Date)attributes.get("psmodifiedDate");

		if (psmodifiedDate != null) {
			setPsmodifiedDate(psmodifiedDate);
		}
	}

	/**
	 * Returns the company ID of this person.
	 *
	 * @return the company ID of this person
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the dpt ID of this person.
	 *
	 * @return the dpt ID of this person
	 */
	@Override
	public long getDptId() {
		return model.getDptId();
	}

	/**
	 * Returns the group ID of this person.
	 *
	 * @return the group ID of this person
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this person.
	 *
	 * @return the primary key of this person
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the pscreate of this person.
	 *
	 * @return the pscreate of this person
	 */
	@Override
	public Date getPscreate() {
		return model.getPscreate();
	}

	/**
	 * Returns the ps delete of this person.
	 *
	 * @return the ps delete of this person
	 */
	@Override
	public long getPsDelete() {
		return model.getPsDelete();
	}

	/**
	 * Returns the ps email of this person.
	 *
	 * @return the ps email of this person
	 */
	@Override
	public String getPsEmail() {
		return model.getPsEmail();
	}

	/**
	 * Returns the ps ID of this person.
	 *
	 * @return the ps ID of this person
	 */
	@Override
	public long getPsId() {
		return model.getPsId();
	}

	/**
	 * Returns the psmodified date of this person.
	 *
	 * @return the psmodified date of this person
	 */
	@Override
	public Date getPsmodifiedDate() {
		return model.getPsmodifiedDate();
	}

	/**
	 * Returns the ps name of this person.
	 *
	 * @return the ps name of this person
	 */
	@Override
	public String getPsName() {
		return model.getPsName();
	}

	/**
	 * Returns the ps phone of this person.
	 *
	 * @return the ps phone of this person
	 */
	@Override
	public String getPsPhone() {
		return model.getPsPhone();
	}

	/**
	 * Returns the ps position of this person.
	 *
	 * @return the ps position of this person
	 */
	@Override
	public String getPsPosition() {
		return model.getPsPosition();
	}

	/**
	 * Returns the ps status of this person.
	 *
	 * @return the ps status of this person
	 */
	@Override
	public long getPsStatus() {
		return model.getPsStatus();
	}

	/**
	 * Returns the user ID of this person.
	 *
	 * @return the user ID of this person
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this person.
	 *
	 * @return the user name of this person
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this person.
	 *
	 * @return the user uuid of this person
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this person.
	 *
	 * @return the uuid of this person
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this person.
	 *
	 * @param companyId the company ID of this person
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the dpt ID of this person.
	 *
	 * @param dptId the dpt ID of this person
	 */
	@Override
	public void setDptId(long dptId) {
		model.setDptId(dptId);
	}

	/**
	 * Sets the group ID of this person.
	 *
	 * @param groupId the group ID of this person
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this person.
	 *
	 * @param primaryKey the primary key of this person
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the pscreate of this person.
	 *
	 * @param pscreate the pscreate of this person
	 */
	@Override
	public void setPscreate(Date pscreate) {
		model.setPscreate(pscreate);
	}

	/**
	 * Sets the ps delete of this person.
	 *
	 * @param psDelete the ps delete of this person
	 */
	@Override
	public void setPsDelete(long psDelete) {
		model.setPsDelete(psDelete);
	}

	/**
	 * Sets the ps email of this person.
	 *
	 * @param psEmail the ps email of this person
	 */
	@Override
	public void setPsEmail(String psEmail) {
		model.setPsEmail(psEmail);
	}

	/**
	 * Sets the ps ID of this person.
	 *
	 * @param psId the ps ID of this person
	 */
	@Override
	public void setPsId(long psId) {
		model.setPsId(psId);
	}

	/**
	 * Sets the psmodified date of this person.
	 *
	 * @param psmodifiedDate the psmodified date of this person
	 */
	@Override
	public void setPsmodifiedDate(Date psmodifiedDate) {
		model.setPsmodifiedDate(psmodifiedDate);
	}

	/**
	 * Sets the ps name of this person.
	 *
	 * @param psName the ps name of this person
	 */
	@Override
	public void setPsName(String psName) {
		model.setPsName(psName);
	}

	/**
	 * Sets the ps phone of this person.
	 *
	 * @param psPhone the ps phone of this person
	 */
	@Override
	public void setPsPhone(String psPhone) {
		model.setPsPhone(psPhone);
	}

	/**
	 * Sets the ps position of this person.
	 *
	 * @param psPosition the ps position of this person
	 */
	@Override
	public void setPsPosition(String psPosition) {
		model.setPsPosition(psPosition);
	}

	/**
	 * Sets the ps status of this person.
	 *
	 * @param psStatus the ps status of this person
	 */
	@Override
	public void setPsStatus(long psStatus) {
		model.setPsStatus(psStatus);
	}

	/**
	 * Sets the user ID of this person.
	 *
	 * @param userId the user ID of this person
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this person.
	 *
	 * @param userName the user name of this person
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this person.
	 *
	 * @param userUuid the user uuid of this person
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this person.
	 *
	 * @param uuid the uuid of this person
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected PersonWrapper wrap(Person person) {
		return new PersonWrapper(person);
	}

}