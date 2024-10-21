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
 * This class is used by SOAP remote services, specifically {@link com.contact.service.http.PersonServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class PersonSoap implements Serializable {

	public static PersonSoap toSoapModel(Person model) {
		PersonSoap soapModel = new PersonSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setPsId(model.getPsId());
		soapModel.setPsName(model.getPsName());
		soapModel.setPsPosition(model.getPsPosition());
		soapModel.setDptId(model.getDptId());
		soapModel.setPsPhone(model.getPsPhone());
		soapModel.setPsEmail(model.getPsEmail());
		soapModel.setPsStatus(model.getPsStatus());
		soapModel.setPsDelete(model.getPsDelete());
		soapModel.setPscreate(model.getPscreate());
		soapModel.setPsmodifiedDate(model.getPsmodifiedDate());

		return soapModel;
	}

	public static PersonSoap[] toSoapModels(Person[] models) {
		PersonSoap[] soapModels = new PersonSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PersonSoap[][] toSoapModels(Person[][] models) {
		PersonSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PersonSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PersonSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PersonSoap[] toSoapModels(List<Person> models) {
		List<PersonSoap> soapModels = new ArrayList<PersonSoap>(models.size());

		for (Person model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PersonSoap[soapModels.size()]);
	}

	public PersonSoap() {
	}

	public long getPrimaryKey() {
		return _psId;
	}

	public void setPrimaryKey(long pk) {
		setPsId(pk);
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

	public long getPsId() {
		return _psId;
	}

	public void setPsId(long psId) {
		_psId = psId;
	}

	public String getPsName() {
		return _psName;
	}

	public void setPsName(String psName) {
		_psName = psName;
	}

	public String getPsPosition() {
		return _psPosition;
	}

	public void setPsPosition(String psPosition) {
		_psPosition = psPosition;
	}

	public long getDptId() {
		return _dptId;
	}

	public void setDptId(long dptId) {
		_dptId = dptId;
	}

	public String getPsPhone() {
		return _psPhone;
	}

	public void setPsPhone(String psPhone) {
		_psPhone = psPhone;
	}

	public String getPsEmail() {
		return _psEmail;
	}

	public void setPsEmail(String psEmail) {
		_psEmail = psEmail;
	}

	public long getPsStatus() {
		return _psStatus;
	}

	public void setPsStatus(long psStatus) {
		_psStatus = psStatus;
	}

	public long getPsDelete() {
		return _psDelete;
	}

	public void setPsDelete(long psDelete) {
		_psDelete = psDelete;
	}

	public Date getPscreate() {
		return _pscreate;
	}

	public void setPscreate(Date pscreate) {
		_pscreate = pscreate;
	}

	public Date getPsmodifiedDate() {
		return _psmodifiedDate;
	}

	public void setPsmodifiedDate(Date psmodifiedDate) {
		_psmodifiedDate = psmodifiedDate;
	}

	private String _uuid;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private long _psId;
	private String _psName;
	private String _psPosition;
	private long _dptId;
	private String _psPhone;
	private String _psEmail;
	private long _psStatus;
	private long _psDelete;
	private Date _pscreate;
	private Date _psmodifiedDate;

}