package com.contact.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import com.contact.constants.PhongBanKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import com.contact.model.Department;
import com.contact.model.Person;
import com.contact.service.DepartmentLocalService;
import com.contact.service.DepartmentLocalServiceUtil;
import com.contact.service.PersonLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.contact.comparator.DepartmentOrderByComparator;

import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/style.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Phòng ban",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/admin/department/view.jsp",
		"javax.portlet.name=" + PhongBanKeys.DEPARTMENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class PhongBanPortlet extends MVCPortlet {
	private Log log = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	CounterLocalService counterLocalService;
	@Reference
	DepartmentLocalService departmentLocalService;
	
	@ProcessAction(name = "addDepartment")
	public void addDepartment(ActionRequest actionRequest,ActionResponse actionResponse) {
		
		try {
			long dptId = counterLocalService.increment(Department.class.getName());
			String dptName = ParamUtil.getString(actionRequest, "dptName");
			String dptDesc = ParamUtil.getString(actionRequest, "dptDesc");
			long dptStatus = ParamUtil.getLong(actionRequest, "dptStatus");
			Date currentDate = new Date();
			
			Department department = departmentLocalService.createDepartment(dptId);
			department.setDptId(dptId);
			department.setDptName(dptName);
			department.setDptDesc(dptDesc);
			department.setDptStatus(dptStatus);
			department.setDptcreateDate(currentDate);
			// 0: chưa xóa, 1: khôi phục
			department.setDptDelete(0);
			
			Collection<Department> departments = DepartmentLocalServiceUtil.findBydptName(dptName);
			if (departments.isEmpty()) {
			    // Thêm phòng mới
				departmentLocalService.addDepartment(department);
				actionRequest.setAttribute("departmentAddedSuccess", true);
			} else {
			    // Xử lý khi tên phòng đã tồn tại
				SessionErrors.add(actionRequest, "departmentAddedFail");
				actionResponse.setRenderParameter("mvcPath", "/html/admin/department/create.jsp");
			}
			
//			departmentLocalService.addDepartment(department);
			
			// Sử dụng setAttribute để lưu thông báo
//	        actionRequest.setAttribute("departmentAddedSuccess", true);
//	     
//			actionResponse.sendRedirect("mvcPath", "/html/admin/department/view.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@ProcessAction(name = "updateDepartment")
    public void updateDepartment(ActionRequest actionRequest,  ActionResponse actionResponse) {
        long dptId = ParamUtil.getLong(actionRequest,"dptId", GetterUtil.DEFAULT_LONG);
        String dptName = ParamUtil.getString(actionRequest, "dptName", GetterUtil.DEFAULT_STRING);
        String dptDesc = ParamUtil.getString(actionRequest, "dptDesc", GetterUtil.DEFAULT_STRING);
        long dptStatus = ParamUtil.getLong(actionRequest, "dptStatus", GetterUtil.DEFAULT_LONG);
        Date currentDate = new Date();
        
        Department department = null;
        
        try {
        	department = departmentLocalService.getDepartment(dptId);
        } catch (Exception e) {
            log.error(e.getCause(), e);
        }

        if(Validator.isNotNull(department)) {
        	department.setDptName(dptName);
			department.setDptDesc(dptDesc);
			department.setDptStatus(dptStatus);
			department.setDptmodifiedDate(currentDate);

			departmentLocalService.updateDepartment(department);
        }
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("departmentUpdatedSuccess", true);
    }
	
	@ProcessAction(name = "removeDepartment")
    public void removeDepartment(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException{
        long dptId = ParamUtil.getLong(actionRequest, "dptId", GetterUtil.DEFAULT_LONG);
        long dptDelete = ParamUtil.getLong(actionRequest, "dptDelete", GetterUtil.DEFAULT_LONG);
        String backURL = ParamUtil.getString(actionRequest, "backURL");
        
        Department department = null;
        try {
        	department = departmentLocalService.getDepartment(dptId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        department.setDptStatus(0);
        department.setDptDelete(1);
        departmentLocalService.updateDepartment(department);
        
        SessionMessages.add(actionRequest, "departmentRemovedSuccess", true);
    }
	
	@ProcessAction(name = "recoveryDepartment")
    public void recoveryDepartment(ActionRequest actionRequest, ActionResponse actionResponse){
        long dptId = ParamUtil.getLong(actionRequest, "dptId", GetterUtil.DEFAULT_LONG);
        long dptDelete = ParamUtil.getLong(actionRequest, "dptDelete", GetterUtil.DEFAULT_LONG);       
        
        Department department = null;
        try {
        	department = departmentLocalService.getDepartment(dptId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        department.setDptDelete(0);
        departmentLocalService.updateDepartment(department);
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("departmentRecoveryedSuccess", true);
    }
	
	@ProcessAction(name = "activeDepartment")
    public void activeDepartment(ActionRequest actionRequest, ActionResponse actionResponse){
        long dptId = ParamUtil.getLong(actionRequest, "dptId", GetterUtil.DEFAULT_LONG);
               
        Department department = null;
        try {
        	department = departmentLocalService.getDepartment(dptId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        department.setDptStatus(0);
        departmentLocalService.updateDepartment(department);
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("departmentActivedSuccess", true);
    }
	
	@ProcessAction(name = "unactiveDepartment")
    public void unactiveDepartment(ActionRequest actionRequest, ActionResponse actionResponse){
        long dptId = ParamUtil.getLong(actionRequest, "dptId", GetterUtil.DEFAULT_LONG);
               
        Department department = null;
        try {
        	department = departmentLocalService.getDepartment(dptId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        department.setDptStatus(1);
        departmentLocalService.updateDepartment(department);
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("departmentUnactivedSuccess", true);
    }
	
	@ProcessAction(name = "deleteDepartment")
    public void deleteDepartment(ActionRequest actionRequest, ActionResponse actionResponse) {
		long dptId = ParamUtil.getLong(actionRequest, "dptId", GetterUtil.DEFAULT_LONG);
        try {
        	departmentLocalService.deleteDepartment(dptId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        actionResponse.setRenderParameter("mvcPath", "/html/admin/department/trash.jsp");
    }
	
	@ProcessAction(name = "filterDpt")
    public void filterDpt(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException{
	
		String filterStatus = ParamUtil.getString(actionRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
		
		// Lưu các tham số lọc vào PortletSession
	    PortletSession actionSession = actionRequest.getPortletSession();
	    actionSession.setAttribute("filterStatus", filterStatus, PortletSession.PORTLET_SCOPE);

	    // Đồng thời set render parameters để đảm bảo giá trị được truyền qua các request, xem phần điều kiện ko có session
	    actionResponse.setRenderParameter("filterStatus", filterStatus);
    }
	
	public void handleFilter(RenderRequest renderRequest, RenderResponse renderResponse) {
		
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol"); 
		String orderByType = ParamUtil.getString(renderRequest, "orderByType","asc");
		OrderByComparator<Department> comparator = new DepartmentOrderByComparator(orderByType.equals("asc"));
		PortletSession renderSession = renderRequest.getPortletSession();
        String filterStatus = (String) renderSession.getAttribute("filterStatus", PortletSession.PORTLET_SCOPE);
        
        DynamicQuery filterQuery = DepartmentLocalServiceUtil.dynamicQuery();
        if(filterStatus == null) {
        	filterStatus = ParamUtil.getString(renderRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
        } 
        
        if(!filterStatus.isEmpty()) {
        	filterQuery.add(RestrictionsFactoryUtil.eq("dptStatus", Long.parseLong(filterStatus)));
			filterQuery.add(RestrictionsFactoryUtil.eq("dptDelete", 0L));
        } else {
        	filterQuery.add(RestrictionsFactoryUtil.eq("dptDelete", 0L));
        }
        
		List<Department> listDpt = DepartmentLocalServiceUtil.dynamicQuery(filterQuery,-1,-1,comparator);
	    renderRequest.setAttribute("listDpt", listDpt);
	    renderSession.setAttribute("sessionList", listDpt, PortletSession.PORTLET_SCOPE);
	    renderRequest.setAttribute("filterStatus", filterStatus);
	}
	
	public void resetToDefaultList(RenderRequest renderRequest) {
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol","dptName"); 
		String orderByType = ParamUtil.getString(renderRequest, "orderByType","asc");
		
		OrderByComparator<Department> comparator = new DepartmentOrderByComparator(orderByType.equals("asc"));
		DynamicQuery dynamicQuery = DepartmentLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq("dptDelete", 0L));
		List<Department> defaultList = DepartmentLocalServiceUtil.dynamicQuery(dynamicQuery,-1,-1,comparator);
	    renderRequest.setAttribute("listDpt", defaultList);
	    
	    PortletSession portletSession = renderRequest.getPortletSession();
	    portletSession.setAttribute("sessionList", defaultList, PortletSession.PORTLET_SCOPE);
//	    portletSession.removeAttribute("keydpt", PortletSession.PORTLET_SCOPE);
	}
	
	@ProcessAction(name = "searchDepartment")
	public void searchDepartment(ActionRequest actionRequest,ActionResponse actionResponse) {
		String keydpt = ParamUtil.getString(actionRequest, "keydpt", GetterUtil.DEFAULT_STRING);
//		actionRequest.setAttribute("keydpt", keydpt);
		
 		// Lưu từ khóa tìm kiếm vào session
 	    PortletSession actionSession = actionRequest.getPortletSession();
 	    actionSession.setAttribute("keydpt", keydpt, PortletSession.PORTLET_SCOPE);
 	    
	    // Đồng thời set render parameters để đảm bảo giá trị được truyền qua các request, xem phần điều kiện ko có session
	    actionResponse.setRenderParameter("keydpt", keydpt);
	}
	
	public void handleSearch(RenderRequest renderRequest, RenderResponse renderResponse) {
		
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol","dptName"); 
		String orderByType = ParamUtil.getString(renderRequest, "orderByType","asc");
		
		OrderByComparator<Department> comparator = new DepartmentOrderByComparator(orderByType.equals("asc"));
		// lấy dữ liệu từ session đã gửi từ action
		PortletSession renderSession = renderRequest.getPortletSession();
		
		final String keydpt;
		String sessionKeydpt = (String) renderSession.getAttribute("keydpt", PortletSession.PORTLET_SCOPE);
		if(sessionKeydpt !=null && !sessionKeydpt.isEmpty()) {
			keydpt = sessionKeydpt;
		} else {
			keydpt = ParamUtil.getString(renderRequest, "keydpt", GetterUtil.DEFAULT_STRING);
		}
		List<Department> sessionList = (List<Department>) renderSession.getAttribute("sessionList", PortletSession.PORTLET_SCOPE);
//		String keydpt = (String) renderRequest.getAttribute("keydpt");
        
        if(sessionList !=null) {
        	List<Department> listDpt = sessionList.stream().filter(department -> department.getDptName().contains(keydpt)).sorted(comparator).collect(Collectors.toList());
        	renderRequest.setAttribute("keydpt", keydpt);
        	renderRequest.setAttribute("listDpt", listDpt);
			renderSession.setAttribute("sessionList", listDpt, PortletSession.PORTLET_SCOPE);
        }
	}
	
	@ProcessAction(name = "clearSearch")
	public void clearSearch(ActionRequest actionRequest,ActionResponse actionResponse) throws IOException {
		PortletSession actionSession = actionRequest.getPortletSession();
		actionSession.removeAttribute("keydpt", PortletSession.PORTLET_SCOPE);
	}
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		try {
			String keydpt = ParamUtil.getString(renderRequest, "keydpt", GetterUtil.DEFAULT_STRING);
			String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "dptName");
	        String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
	        
			PortletSession portletSession = renderRequest.getPortletSession();
			boolean isSearching = !keydpt.isEmpty();
			boolean isSorting = !orderByCol.equals("dptName") || !orderByType.equals("asc");
			
			handleFilter(renderRequest, renderResponse);
	        handleSearch(renderRequest, renderResponse);
	        
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		super.doView(renderRequest, renderResponse);
	}
}
