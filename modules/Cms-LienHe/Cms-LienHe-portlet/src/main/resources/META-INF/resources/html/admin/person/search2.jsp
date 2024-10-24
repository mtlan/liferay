<%@page import="javax.portlet.PortletSession"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="java.util.Collections"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.model.Department"%>
<%@page import="com.contact.model.Person"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ include file="../init.jsp"%>
<portlet:defineObjects />
<portlet:actionURL name="searchPerson" var="searchPersonActionURL"/>

<%
	PortletSession renderSession = renderRequest.getPortletSession();
	String keyps = (String) renderSession.getAttribute("keyps", PortletSession.PORTLET_SCOPE);
	List<Person> ps_key = (List<Person>) renderSession.getAttribute("ps_key", PortletSession.PORTLET_SCOPE);
	String orderByCol = ParamUtil.getString(request, "orderByCol","psName"); 
	String orderByType = ParamUtil.getString(request, "orderByType","asc");
	String currentURL = PortalUtil.getCurrentURL(request);
%>

<portlet:renderURL var="backve">
    <portlet:param name="backURL" value="<%=currentURL %>"/>
</portlet:renderURL>

<nav class="navbar justify-content-between">
  	<h2><a href="<%= backve %>">Quay về</a></h2>
	<aui:form cssClass="form-inline" action="<%=searchPersonActionURL %>" method="POST">
		<aui:input cssClass="form-control mr-sm-2" label="" name="keyps" type="text" placeholder="Tìm kiếm người liên hệ..." value="<%=keyps %>"></aui:input>
		<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
	</aui:form>
</nav>
<br>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="backURL" value="<%=currentURL %>"/>
</liferay-portlet:renderURL>

<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="Xin lỗi, không có dòng để hiển thị" total="<%=ps_key.size() %>" orderByCol="<%= orderByCol %>" orderByType="<%= orderByType %>">
		
	<liferay-ui:search-container-results results="<%= ListUtil.subList(ps_key, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
	
	<liferay-ui:search-container-row className="Person" keyProperty="psId" modelVar="person" indexVar="rowIndex">
		
	    <%	
		    Department department = DepartmentLocalServiceUtil.fetchDepartment(person.getDptId());
			String dptName = (department!=null) ? department.getDptName():"Chưa xác định";
	    %>
	    <!-- if...else... -->
        <c:choose>
	        <c:when test="${not empty person}">
	        	<liferay-ui:search-container-column-text name="STT" value="${rowIndex + 1}"/>
	            <liferay-ui:search-container-column-text name="Tên người liên hệ" value="<%=person.getPsName() %>" orderable="true"/>
				<liferay-ui:search-container-column-text name="Chức vụ" value="<%=person.getPsPosition() %>"/>
				<liferay-ui:search-container-column-text name="Phòng ban" value="<%=dptName %>" />
				<liferay-ui:search-container-column-text name="Số điện thoại" value="<%=person.getPsPhone() %>"/>
				<liferay-ui:search-container-column-text name="Email" value="<%=person.getPsEmail() %>"/>
				<!-- 0: ẩn, 1: hiển thị -->
	        </c:when>
	        <c:otherwise>
	            <div class="alert alert-primary" role="alert">Dữ liệu chưa thêm mới hoặc đang ở trong thùng rác!</div>
	        </c:otherwise>
	    </c:choose>
	</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator/>
	
</liferay-ui:search-container>