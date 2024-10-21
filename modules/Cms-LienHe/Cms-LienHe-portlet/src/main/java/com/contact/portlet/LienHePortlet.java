package com.contact.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.contact.comparator.PersonOrderByComparator;
import com.contact.constants.LienHeKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.contact.model.Department;
import com.contact.model.Person;
import com.contact.service.DepartmentLocalService;
import com.contact.service.DepartmentLocalServiceUtil;
import com.contact.service.PersonLocalService;
import com.contact.service.PersonLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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

import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/style.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Liên hệ",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/admin/person/view.jsp",
		"javax.portlet.name=" + LienHeKeys.PERSON,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class LienHePortlet extends MVCPortlet {
	
	private Log log = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	CounterLocalService counterLocalService;
	@Reference
	PersonLocalService personLocalService;
	@Reference
	DepartmentLocalService departmentLocalService;
	
	
	@ProcessAction(name = "addPerson")
	public void addPerson(ActionRequest actionRequest,ActionResponse actionResponse) {
		try {
			long psId = counterLocalService.increment(Person.class.getName());
			String psName = ParamUtil.getString(actionRequest, "psName");
			String psPosition = ParamUtil.getString(actionRequest, "psPosition");
			long dptId = ParamUtil.getLong(actionRequest, "dptId");
			String psPhone = ParamUtil.getString(actionRequest, "psPhone");
			String psEmail = ParamUtil.getString(actionRequest, "psEmail");
			long psStatus = ParamUtil.getLong(actionRequest, "psStatus");
			Date currentDate = new Date();
			
			Person person = personLocalService.createPerson(psId);
			person.setPsName(psName);
			person.setPsPosition(psPosition);
			person.setDptId(dptId);
			person.setPsPhone(psPhone);
			person.setPsEmail(psEmail);
			person.setPsStatus(psStatus);
			// 0: remove, 1: recovery 
			person.setPsDelete(0);
			person.setPscreate(currentDate);
			
			Collection<Person> persons = PersonLocalServiceUtil.findBypsName(psName);
			if (persons.isEmpty()) {
			    // Thêm phòng mới
				personLocalService.addPerson(person);
				// Sử dụng setAttribute để lưu thông báo
		        actionRequest.setAttribute("personAddedSuccess", true);
			} else {
			    // Xử lý khi tên phòng đã tồn tại
				SessionErrors.add(actionRequest, "personAddedFail");
				actionResponse.setRenderParameter("mvcPath", "/html/admin/person/create.jsp");
			}
	        
	        actionResponse.sendRedirect("mvcPath", "/html/admin/person/view.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@ProcessAction(name = "updatePerson")
    public void updatePerson(ActionRequest actionRequest,  ActionResponse actionResponse) {
        long psId = ParamUtil.getLong(actionRequest, "psId", GetterUtil.DEFAULT_LONG);
        String psName = ParamUtil.getString(actionRequest, "psName", GetterUtil.DEFAULT_STRING);
		String psPosition = ParamUtil.getString(actionRequest, "psPosition", GetterUtil.DEFAULT_STRING);
		long dptId = ParamUtil.getLong(actionRequest, "dptId", GetterUtil.DEFAULT_LONG);
		String psPhone = ParamUtil.getString(actionRequest, "psPhone", GetterUtil.DEFAULT_STRING);
		String psEmail = ParamUtil.getString(actionRequest, "psEmail", GetterUtil.DEFAULT_STRING);
		long psStatus = ParamUtil.getLong(actionRequest, "psStatus", GetterUtil.DEFAULT_LONG);
		Date currentDate = new Date();
        
        Person person = null;
        
        try {
        	person = personLocalService.getPerson(psId);
        } catch (Exception e) {
            log.error(e.getCause(), e);
        }

        if(Validator.isNotNull(person)) {
        	person.setPsName(psName);
			person.setPsPosition(psPosition);
			person.setDptId(dptId);
			person.setPsPhone(psPhone);
			person.setPsEmail(psEmail);
			person.setPsStatus(psStatus);
			person.setPsmodifiedDate(currentDate);

			personLocalService.updatePerson(person);
        }
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("personUpdatedSuccess", true);
    }
	
	@ProcessAction(name = "removePerson")
    public void removePerson(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException{
        long psId = ParamUtil.getLong(actionRequest, "psId", GetterUtil.DEFAULT_LONG);
        long psDelete = ParamUtil.getLong(actionRequest, "psDelete", GetterUtil.DEFAULT_LONG);
        String backURL = ParamUtil.getString(actionRequest, "backURL");
        
        Person person = null;
        try {
        	person = personLocalService.getPerson(psId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        person.setPsStatus(0);
        person.setPsDelete(1);
        personLocalService.updatePerson(person);
        
     // Sử dụng setAttribute để lưu thông báo
    //  actionRequest.setAttribute("personRemovedSuccess", true);
        SessionMessages.add(actionRequest, "personRemovedSuccess", true);
        actionResponse.sendRedirect(backURL);
    }
	
	@ProcessAction(name = "recoveryPerson")
    public void recoveryPerson(ActionRequest actionRequest, ActionResponse actionResponse){
		long psId = ParamUtil.getLong(actionRequest, "psId", GetterUtil.DEFAULT_LONG);
        long psDelete = ParamUtil.getLong(actionRequest, "psDelete", GetterUtil.DEFAULT_LONG);       
        
        Person person = null;
        try {
        	person = personLocalService.getPerson(psId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        person.setPsDelete(0);
        personLocalService.updatePerson(person);
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("personRecoveryedSuccess", true);
    }
	
	@ProcessAction(name = "activePerson")
    public void activePerson(ActionRequest actionRequest, ActionResponse actionResponse){
		long psId = ParamUtil.getLong(actionRequest, "psId", GetterUtil.DEFAULT_LONG);
               
		Person person = null;
        try {
        	person = personLocalService.getPerson(psId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        person.setPsStatus(0);
        personLocalService.updatePerson(person);
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("personActivedSuccess", true);
    }
	
	@ProcessAction(name = "unactivePerson")
    public void unactivePerson(ActionRequest actionRequest, ActionResponse actionResponse){
		long psId = ParamUtil.getLong(actionRequest, "psId", GetterUtil.DEFAULT_LONG);
        
		Person person = null;
        try {
        	person = personLocalService.getPerson(psId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        person.setPsStatus(1);
        personLocalService.updatePerson(person);
        
        // Sử dụng setAttribute để lưu thông báo
        actionRequest.setAttribute("personUnactivedSuccess", true);
    }
	
	@ProcessAction(name = "deletePerson")
    public void deletePerson(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException{
		long psId = ParamUtil.getLong(actionRequest, "psId", GetterUtil.DEFAULT_LONG);
        try {
        	personLocalService.deletePerson(psId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        actionResponse.setRenderParameter("mvcPath", "/html/admin/person/trash.jsp");
    }
	
	@ProcessAction(name = "filterPerson")
    public void filterPerson(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException{
		// get filterName and filterStatus in JSP
		long filterName = ParamUtil.getLong(actionRequest, "filterName", GetterUtil.DEFAULT_LONG);
		String filterStatus = ParamUtil.getString(actionRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
		
		// Lưu các tham số lọc vào PortletSession
	    PortletSession actionSession = actionRequest.getPortletSession();
	    
	    actionSession.setAttribute("filterName", filterName, PortletSession.PORTLET_SCOPE);
	    actionSession.setAttribute("filterStatus", filterStatus, PortletSession.PORTLET_SCOPE);
	    
	    // remove keyps để lấy bộ lọc
//	    actionSession.removeAttribute("keyps", PortletSession.PORTLET_SCOPE);

	    // Đồng thời set render parameters để đảm bảo giá trị được truyền qua các request, xem phần điều kiện ko có session
	    actionResponse.setRenderParameter("filterName", String.valueOf(filterName));
	    actionResponse.setRenderParameter("filterStatus", filterStatus);
    }
	
	public void handleFilter(RenderRequest renderRequest, RenderResponse renderResponse) {
		
		// sắp xếp cột psname
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol","psName"); 
		String orderByType = ParamUtil.getString(renderRequest, "orderByType","asc");
		
		OrderByComparator<Person> comparator = new PersonOrderByComparator(orderByType.equals("asc"));
		
		// lấy dữ liệu từ session đã gửi từ action
		PortletSession renderSession = renderRequest.getPortletSession();
		Long filterName = (Long) renderSession.getAttribute("filterName", PortletSession.PORTLET_SCOPE);
        String filterStatus = (String) renderSession.getAttribute("filterStatus", PortletSession.PORTLET_SCOPE);
        
        // Tạo truy vấn động
		DynamicQuery dynamicQuery = PersonLocalServiceUtil.dynamicQuery();
     	dynamicQuery.add(RestrictionsFactoryUtil.eq("psDelete", 0L));
     			
     	// Sắp xếp theo cột 'name' theo thứ tự tăng dần
 //   	OrderByComparator<Person> orderByComparator = OrderByComparatorFactoryUtil.create("Person", "psName", true);
    	
//		if (filterName == null && filterStatus == null) {
//			filterName = ParamUtil.getLong(renderRequest, "filterName", GetterUtil.DEFAULT_LONG);
//        	filterStatus = ParamUtil.getString(renderRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
//		} else if(filterName != GetterUtil.DEFAULT_LONG && filterStatus == GetterUtil.DEFAULT_STRING) {
//			dynamicQuery.add(RestrictionsFactoryUtil.eq("dptId", filterName));
//		} else if(filterName == GetterUtil.DEFAULT_LONG && filterStatus != GetterUtil.DEFAULT_STRING) {
//			dynamicQuery.add(RestrictionsFactoryUtil.eq("psStatus", Long.parseLong(filterStatus)));
//		} else if(filterName != GetterUtil.DEFAULT_LONG && filterStatus != GetterUtil.DEFAULT_STRING) {
//			dynamicQuery.add(RestrictionsFactoryUtil.eq("dptId", filterName));
//			dynamicQuery.add(RestrictionsFactoryUtil.eq("psStatus", Long.parseLong(filterStatus)));
//		} else {
//			dynamicQuery.add(RestrictionsFactoryUtil.eq("psDelete", 0L));
//		}
     	
     	if (filterName == null && filterStatus == null) {
			filterName = ParamUtil.getLong(renderRequest, "filterName", GetterUtil.DEFAULT_LONG);
        	filterStatus = ParamUtil.getString(renderRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
		} 
     	
     	if(filterName != GetterUtil.DEFAULT_LONG && filterStatus == GetterUtil.DEFAULT_STRING) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("dptId", filterName));
		} else if(filterName == GetterUtil.DEFAULT_LONG && filterStatus != GetterUtil.DEFAULT_STRING) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("psStatus", Long.parseLong(filterStatus)));
		} else if(filterName != GetterUtil.DEFAULT_LONG && filterStatus != GetterUtil.DEFAULT_STRING) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("dptId", filterName));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("psStatus", Long.parseLong(filterStatus)));
		}
		
		List<Person> resultList = PersonLocalServiceUtil.dynamicQuery(dynamicQuery,-1,-1,comparator);
		renderRequest.setAttribute("resultList", resultList);
		renderSession.setAttribute("sessionList", resultList, PortletSession.PORTLET_SCOPE);
		renderRequest.setAttribute("filterName", filterName);
	    renderRequest.setAttribute("filterStatus", filterStatus);
	}
	
	private void resetToDefaultList(RenderRequest renderRequest) {
		// sắp xếp cột psname
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol","psName"); 
		String orderByType = ParamUtil.getString(renderRequest, "orderByType","asc");
		
		OrderByComparator<Person> comparator = new PersonOrderByComparator(orderByType.equals("asc"));
		DynamicQuery dynamicQuery = PersonLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq("psDelete", 0L));
		List<Person> defaultList = PersonLocalServiceUtil.dynamicQuery(dynamicQuery,-1,-1,comparator);
	    renderRequest.setAttribute("resultList", defaultList);
	    
	    PortletSession portletSession = renderRequest.getPortletSession();
	    portletSession.setAttribute("sessionList", defaultList, PortletSession.PORTLET_SCOPE);
//	    portletSession.removeAttribute("keyps", PortletSession.PORTLET_SCOPE);
	}
	
	@ProcessAction(name = "searchPerson")
	public void searchPerson(ActionRequest actionRequest,ActionResponse actionResponse) throws IOException {
		
 		String keyps = ParamUtil.getString(actionRequest, "keyps", GetterUtil.DEFAULT_STRING);
 		
 		// Lưu từ khóa tìm kiếm vào session
 	    PortletSession actionSession = actionRequest.getPortletSession();
 	    actionSession.setAttribute("keyps", keyps, PortletSession.PORTLET_SCOPE);
 	    
	    // Đồng thời set render parameters để đảm bảo giá trị được truyền qua các request, xem phần điều kiện ko có session
	    actionResponse.setRenderParameter("keyps", keyps);
	}
	
	public void handleSearch(RenderRequest renderRequest, RenderResponse renderResponse) {
		
		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol","psName"); 
		String orderByType = ParamUtil.getString(renderRequest, "orderByType","asc");
		
		OrderByComparator<Person> comparator = new PersonOrderByComparator(orderByType.equals("asc"));
		
		PortletSession renderSession = renderRequest.getPortletSession();
	    
//		if (searchList == null) {
//		    searchList = new ArrayList<>(); // Initialize if the session does not have the list
//		}
		
	    final String keyps;
	    String sessionKeyps = (String) renderSession.getAttribute("keyps", PortletSession.PORTLET_SCOPE);
	    if(sessionKeyps != null && !sessionKeyps.isEmpty()) {
	    	keyps = sessionKeyps;
	    } else {
	    	keyps = ParamUtil.getString(renderRequest, "keyps", GetterUtil.DEFAULT_STRING);
	    }
	    
	    List<Person> sessionList = (List<Person>) renderSession.getAttribute("sessionList", PortletSession.PORTLET_SCOPE);
	    if (sessionList != null) {
	        List<Person> searchList = sessionList.stream()
	            .filter(person -> person.getPsName().toLowerCase().contains(keyps.toLowerCase()) ||
	                              person.getPsEmail().toLowerCase().contains(keyps.toLowerCase()) ||
	                              person.getPsPosition().toLowerCase().contains(keyps.toLowerCase()))
	            .sorted(comparator)
	            .collect(Collectors.toList());
	        renderRequest.setAttribute("keyps", keyps);
			renderRequest.setAttribute("resultList", searchList);
			renderSession.setAttribute("searchList", searchList, PortletSession.PORTLET_SCOPE);
	    }
	}
		
	@ProcessAction(name = "clearSearch")
	public void clearSearch(ActionRequest actionRequest,ActionResponse actionResponse) throws IOException {
		PortletSession actionSession = actionRequest.getPortletSession();
		actionSession.removeAttribute("keyps", PortletSession.PORTLET_SCOPE);
	}
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
//	        long filterName = ParamUtil.getLong(renderRequest, "filterName", GetterUtil.DEFAULT_LONG);
//	        String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
//	        String keyps = ParamUtil.getString(renderRequest, "keyps", GetterUtil.DEFAULT_STRING);
//	        String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "psName");
//	        String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
	        
	        PortletSession portletSession = renderRequest.getPortletSession();

//	        boolean isFiltering = filterName != GetterUtil.DEFAULT_LONG || !filterStatus.isEmpty();
//	        boolean isSearching = !keyps.isEmpty();
//	        boolean isSorting = !orderByCol.equals("psName") || !orderByType.equals("asc");
	        
	        List<Person> sessionList = (List<Person>) portletSession.getAttribute("sessionList", PortletSession.PORTLET_SCOPE);
	        if (sessionList != null) {
	        	resetToDefaultList(renderRequest);
	        }
	       
	        handleFilter(renderRequest, renderResponse);
	        handleSearch(renderRequest, renderResponse);
   
		    // lỗi phân trang
//	        if (isFiltering) {
//	            handleFilter(renderRequest, renderResponse);
//	            // Xóa keyps khỏi session khi lọc
//	            portletSession.removeAttribute("keyps", PortletSession.PORTLET_SCOPE);
//	        }
	        
	        // mặc định asc, thay đổi chế độ desc trả về list mặc định nên cần isSorting để ko trả về
	        // lỗi phân trang, sắp xếp khi tìm kiếm
//	        if (isSearching || isSorting) {
//	        	handleSearch(renderRequest, renderResponse);
//	        }
	            
	    } catch (Exception e) {
	        log.error(e.getMessage(), e);
	    }
	    super.doView(renderRequest, renderResponse);
	}
	
}
