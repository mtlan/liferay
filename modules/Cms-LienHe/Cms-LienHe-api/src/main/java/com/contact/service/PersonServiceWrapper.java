/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.contact.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PersonService}.
 *
 * @author Brian Wing Shun Chan
 * @see PersonService
 * @generated
 */
public class PersonServiceWrapper
	implements PersonService, ServiceWrapper<PersonService> {

	public PersonServiceWrapper(PersonService personService) {
		_personService = personService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _personService.getOSGiServiceIdentifier();
	}

	@Override
	public PersonService getWrappedService() {
		return _personService;
	}

	@Override
	public void setWrappedService(PersonService personService) {
		_personService = personService;
	}

	private PersonService _personService;

}