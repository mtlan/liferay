/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.service.impl;

import com.contact.model.Person;
import com.contact.service.base.PersonLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.contact.model.Person",
	service = AopService.class
)
public class PersonLocalServiceImpl extends PersonLocalServiceBaseImpl {
	public List<Person> getPersons(int start, int end, OrderByComparator<Person> comparator) {
	    return personPersistence.findAll(start, end, comparator);
	}
	public List<Person> findBypsName(String psName) {
	    return personPersistence.findBypsName(psName);
	}
}