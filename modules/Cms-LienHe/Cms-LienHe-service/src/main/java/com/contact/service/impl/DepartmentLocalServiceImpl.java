/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.service.impl;

import com.contact.model.Department;
import com.contact.service.base.DepartmentLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.contact.model.Department",
	service = AopService.class
)
public class DepartmentLocalServiceImpl extends DepartmentLocalServiceBaseImpl {
	public List<Department> getDepartments(int start, int end, OrderByComparator<Department> comparator) {
	    return departmentPersistence.findAll(start, end, comparator);
	}
	public List<Department> findBydptName(String dptName) {
	    return departmentPersistence.findBydptName(dptName);
	}
}