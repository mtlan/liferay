/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.contact.service.http.DepartmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class DepartmentSoap implements Serializable {

	public static DepartmentSoap toSoapModel(Department model) {
		DepartmentSoap soapModel = new DepartmentSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setDptId(model.getDptId());
		soapModel.setDptName(model.getDptName());
		soapModel.setDptDesc(model.getDptDesc());
		soapModel.setDptStatus(model.getDptStatus());
		soapModel.setDptDelete(model.getDptDelete());
		soapModel.setDptcreateDate(model.getDptcreateDate());
		soapModel.setDptmodifiedDate(model.getDptmodifiedDate());

		return soapModel;
	}

	public static DepartmentSoap[] toSoapModels(Department[] models) {
		DepartmentSoap[] soapModels = new DepartmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DepartmentSoap[][] toSoapModels(Department[][] models) {
		DepartmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DepartmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DepartmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DepartmentSoap[] toSoapModels(List<Department> models) {
		List<DepartmentSoap> soapModels = new ArrayList<DepartmentSoap>(
			models.size());

		for (Department model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DepartmentSoap[soapModels.size()]);
	}

	public DepartmentSoap() {
	}

	public long getPrimaryKey() {
		return _dptId;
	}

	public void setPrimaryKey(long pk) {
		setDptId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public long getDptId() {
		return _dptId;
	}

	public void setDptId(long dptId) {
		_dptId = dptId;
	}

	public String getDptName() {
		return _dptName;
	}

	public void setDptName(String dptName) {
		_dptName = dptName;
	}

	public String getDptDesc() {
		return _dptDesc;
	}

	public void setDptDesc(String dptDesc) {
		_dptDesc = dptDesc;
	}

	public long getDptStatus() {
		return _dptStatus;
	}

	public void setDptStatus(long dptStatus) {
		_dptStatus = dptStatus;
	}

	public long getDptDelete() {
		return _dptDelete;
	}

	public void setDptDelete(long dptDelete) {
		_dptDelete = dptDelete;
	}

	public Date getDptcreateDate() {
		return _dptcreateDate;
	}

	public void setDptcreateDate(Date dptcreateDate) {
		_dptcreateDate = dptcreateDate;
	}

	public Date getDptmodifiedDate() {
		return _dptmodifiedDate;
	}

	public void setDptmodifiedDate(Date dptmodifiedDate) {
		_dptmodifiedDate = dptmodifiedDate;
	}

	private String _uuid;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private long _dptId;
	private String _dptName;
	private String _dptDesc;
	private long _dptStatus;
	private long _dptDelete;
	private Date _dptcreateDate;
	private Date _dptmodifiedDate;

}