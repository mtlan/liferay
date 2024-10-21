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
 * This class is a wrapper for {@link Department}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Department
 * @generated
 */
public class DepartmentWrapper
	extends BaseModelWrapper<Department>
	implements Department, ModelWrapper<Department> {

	public DepartmentWrapper(Department department) {
		super(department);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("dptId", getDptId());
		attributes.put("dptName", getDptName());
		attributes.put("dptDesc", getDptDesc());
		attributes.put("dptStatus", getDptStatus());
		attributes.put("dptDelete", getDptDelete());
		attributes.put("dptcreateDate", getDptcreateDate());
		attributes.put("dptmodifiedDate", getDptmodifiedDate());

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

		Long dptId = (Long)attributes.get("dptId");

		if (dptId != null) {
			setDptId(dptId);
		}

		String dptName = (String)attributes.get("dptName");

		if (dptName != null) {
			setDptName(dptName);
		}

		String dptDesc = (String)attributes.get("dptDesc");

		if (dptDesc != null) {
			setDptDesc(dptDesc);
		}

		Long dptStatus = (Long)attributes.get("dptStatus");

		if (dptStatus != null) {
			setDptStatus(dptStatus);
		}

		Long dptDelete = (Long)attributes.get("dptDelete");

		if (dptDelete != null) {
			setDptDelete(dptDelete);
		}

		Date dptcreateDate = (Date)attributes.get("dptcreateDate");

		if (dptcreateDate != null) {
			setDptcreateDate(dptcreateDate);
		}

		Date dptmodifiedDate = (Date)attributes.get("dptmodifiedDate");

		if (dptmodifiedDate != null) {
			setDptmodifiedDate(dptmodifiedDate);
		}
	}

	/**
	 * Returns the company ID of this department.
	 *
	 * @return the company ID of this department
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the dptcreate date of this department.
	 *
	 * @return the dptcreate date of this department
	 */
	@Override
	public Date getDptcreateDate() {
		return model.getDptcreateDate();
	}

	/**
	 * Returns the dpt delete of this department.
	 *
	 * @return the dpt delete of this department
	 */
	@Override
	public long getDptDelete() {
		return model.getDptDelete();
	}

	/**
	 * Returns the dpt desc of this department.
	 *
	 * @return the dpt desc of this department
	 */
	@Override
	public String getDptDesc() {
		return model.getDptDesc();
	}

	/**
	 * Returns the dpt ID of this department.
	 *
	 * @return the dpt ID of this department
	 */
	@Override
	public long getDptId() {
		return model.getDptId();
	}

	/**
	 * Returns the dptmodified date of this department.
	 *
	 * @return the dptmodified date of this department
	 */
	@Override
	public Date getDptmodifiedDate() {
		return model.getDptmodifiedDate();
	}

	/**
	 * Returns the dpt name of this department.
	 *
	 * @return the dpt name of this department
	 */
	@Override
	public String getDptName() {
		return model.getDptName();
	}

	/**
	 * Returns the dpt status of this department.
	 *
	 * @return the dpt status of this department
	 */
	@Override
	public long getDptStatus() {
		return model.getDptStatus();
	}

	/**
	 * Returns the group ID of this department.
	 *
	 * @return the group ID of this department
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this department.
	 *
	 * @return the primary key of this department
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this department.
	 *
	 * @return the user ID of this department
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this department.
	 *
	 * @return the user name of this department
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this department.
	 *
	 * @return the user uuid of this department
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this department.
	 *
	 * @return the uuid of this department
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
	 * Sets the company ID of this department.
	 *
	 * @param companyId the company ID of this department
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the dptcreate date of this department.
	 *
	 * @param dptcreateDate the dptcreate date of this department
	 */
	@Override
	public void setDptcreateDate(Date dptcreateDate) {
		model.setDptcreateDate(dptcreateDate);
	}

	/**
	 * Sets the dpt delete of this department.
	 *
	 * @param dptDelete the dpt delete of this department
	 */
	@Override
	public void setDptDelete(long dptDelete) {
		model.setDptDelete(dptDelete);
	}

	/**
	 * Sets the dpt desc of this department.
	 *
	 * @param dptDesc the dpt desc of this department
	 */
	@Override
	public void setDptDesc(String dptDesc) {
		model.setDptDesc(dptDesc);
	}

	/**
	 * Sets the dpt ID of this department.
	 *
	 * @param dptId the dpt ID of this department
	 */
	@Override
	public void setDptId(long dptId) {
		model.setDptId(dptId);
	}

	/**
	 * Sets the dptmodified date of this department.
	 *
	 * @param dptmodifiedDate the dptmodified date of this department
	 */
	@Override
	public void setDptmodifiedDate(Date dptmodifiedDate) {
		model.setDptmodifiedDate(dptmodifiedDate);
	}

	/**
	 * Sets the dpt name of this department.
	 *
	 * @param dptName the dpt name of this department
	 */
	@Override
	public void setDptName(String dptName) {
		model.setDptName(dptName);
	}

	/**
	 * Sets the dpt status of this department.
	 *
	 * @param dptStatus the dpt status of this department
	 */
	@Override
	public void setDptStatus(long dptStatus) {
		model.setDptStatus(dptStatus);
	}

	/**
	 * Sets the group ID of this department.
	 *
	 * @param groupId the group ID of this department
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this department.
	 *
	 * @param primaryKey the primary key of this department
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this department.
	 *
	 * @param userId the user ID of this department
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this department.
	 *
	 * @param userName the user name of this department
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this department.
	 *
	 * @param userUuid the user uuid of this department
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this department.
	 *
	 * @param uuid the uuid of this department
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected DepartmentWrapper wrap(Department department) {
		return new DepartmentWrapper(department);
	}

}