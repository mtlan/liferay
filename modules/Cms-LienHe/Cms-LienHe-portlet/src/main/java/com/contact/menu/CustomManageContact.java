package com.contact.menu;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.contact.constants.ManageKeys;
import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

@Component(
	    immediate = true,
	    property = {
	        "panel.app.order:Integer=0",  // Vị trí hiển thị trong menu, 0 hiển thị trên đầu
	        "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_NAVIGATION   // Đưa vào mục Control Panel Apps
	    },
	    service = PanelApp.class
	)

public class CustomManageContact extends BasePanelApp {
	
	@Override
    public String getPortletId() {
		return ManageKeys.MANAGE; // Tên portlet
    }

    @Override
    @Reference(
        target = "(javax.portlet.name=" + ManageKeys.MANAGE + ")",
        unbind = "-"
    )
    public void setPortlet(Portlet portlet) {
        super.setPortlet(portlet);
    }
	
}
