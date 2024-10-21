package com.contact.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.contact.constants.UserKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import org.osgi.service.component.annotations.Reference;

import com.contact.model.Department;
import com.contact.model.Person;
import com.contact.service.DepartmentLocalService;
import com.contact.service.PersonLocalService;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/style.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Thông tin liên hệ",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/user/viewUser.jsp",
		"javax.portlet.name=" + UserKeys.USER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class UserPortlet extends MVCPortlet {
	
	@Reference
	PersonLocalService personLocalService;
	@Reference
	DepartmentLocalService departmentLocalService;
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		List<Department> dptList = departmentLocalService.getDepartments(-1, -1);
		List<Person> psList = personLocalService.getPersons(-1, -1);
		renderRequest.setAttribute("dptList", dptList);
		renderRequest.setAttribute("psList", psList);
		System.out.println("---------start frondend-----------");
		super.doView(renderRequest, renderResponse);
	}
}
