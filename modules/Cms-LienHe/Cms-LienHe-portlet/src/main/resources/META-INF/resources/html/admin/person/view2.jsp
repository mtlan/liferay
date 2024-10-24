<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="javax.portlet.PortletSession"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
<%@page import="com.contact.comparator.PersonOrderByComparator"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.model.Department"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.contact.service.PersonLocalServiceUtil"%>
<%@page import="com.contact.model.Person"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp"%>

<%
	DynamicQuery dynamicQuery = DepartmentLocalServiceUtil.dynamicQuery();
%>

<!-- get list department, show select option -->
<% 
	dynamicQuery.add(RestrictionsFactoryUtil.eq("dptStatus", 1L));
	dynamicQuery.add(RestrictionsFactoryUtil.eq("dptDelete", 0L));
	List<Department> dptDy = DepartmentLocalServiceUtil.dynamicQuery(dynamicQuery);
	request.setAttribute("dptDy", dptDy);
%>

<% 
	//Đọc giá trị từ render parameters trước
	long filterName = ParamUtil.getLong(renderRequest, "filterName", GetterUtil.DEFAULT_LONG);
	String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
	String keyps = ParamUtil.getString(renderRequest, "keyps", GetterUtil.DEFAULT_STRING);
	
	List<Person> resultList = (List<Person>)renderRequest.getAttribute("resultList");
	
	if(resultList == null) {
		DynamicQuery dynamicQuery1 = PersonLocalServiceUtil.dynamicQuery();
		dynamicQuery1.add(RestrictionsFactoryUtil.eq("psDelete", 0L));		
		resultList = PersonLocalServiceUtil.dynamicQuery(dynamicQuery1);
	}
	
	
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<portlet:actionURL name="searchPerson" var="searchPersonActionURL"/>
<br>
<h2>DANH SÁCH THÔNG TIN NGƯỜI LIÊN HỆ</h2>
<div class="container mt-4">
	<div class="d-flex justify-content-between mt-3">
	  	<portlet:actionURL name="filterPerson" var="filterPersonActionURL"/>
	  	<aui:form cssClass="form-inline" action="<%=filterPersonActionURL%>" method="POST">
	  		<aui:select name="filterName" label="" onChange="this.form.submit()">
	  			<aui:option value="">------Lọc---------</aui:option>
	  			<c:forEach var="dpt" items="${dptDy}">
				<aui:option value="${dpt.getDptId()}" selected="${dpt.getDptId() == filterName}">${dpt.getDptName()}</aui:option>
				</c:forEach>
	  		</aui:select>
		  	<aui:select name="filterStatus" label="" onChange="this.form.submit()">
		  		<aui:option value="">------Lọc---------</aui:option>
		  		<aui:option value="0" selected="${filterStatus == '0'}">Ẩn</aui:option>
		  		<aui:option value="1" selected="${filterStatus == '1'}">Hiện</aui:option>
		  	</aui:select>
  		</aui:form>
  		<aui:form cssClass="form-inline" action="<%=searchPersonActionURL %>" method="POST">
			<aui:input cssClass="form-control mr-sm-2" label="" name="keyps" type="text" placeholder="Tìm kiếm..."></aui:input>
			<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
		</aui:form>
  	</div>
</div>
<br>	

<liferay-ui:search-container delta="5" total="<%=resultList.size() %>">
	<liferay-ui:search-container-results 
	results="<%= ListUtil.subList(resultList, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
	
	<portlet:renderURL var="iteratorURL">
        <portlet:param name="filterName" value="<%= String.valueOf(filterName) %>" />
        <portlet:param name="filterStatus" value="<%= filterStatus %>" />
    </portlet:renderURL>
	
	<liferay-ui:search-container-row className="Person" keyProperty="psId" modelVar="person" indexVar="rowIndex" >
	    <portlet:actionURL name="activePerson" var="activePersonActionURL">
    		<portlet:param name="psId" value="${person.getPsId()}"/>
		</portlet:actionURL>
		
		<portlet:actionURL name="unactivePerson" var="unactivePersonActionURL">
    		<portlet:param name="psId" value="${person.getPsId()}"/>
		</portlet:actionURL>
    
    <%	
	    Department department = DepartmentLocalServiceUtil.fetchDepartment(person.getDptId());
		String dptName = (department!=null) ? department.getDptName():"Chưa xác định";
    %>
    
       	<liferay-ui:search-container-column-text name="STT" value="${rowIndex + 1}"/>
           <liferay-ui:search-container-column-text property="psName" name="Tên người liên hệ"/>
		<liferay-ui:search-container-column-text property="psPosition" name="Chức vụ"/>
		<liferay-ui:search-container-column-text value="<%=dptName %>" name="Phòng ban"/>
		<liferay-ui:search-container-column-text property="psPhone" name="Số điện thoại"/>
		<liferay-ui:search-container-column-text property="psEmail" name="Email"/>
		<!-- 0: ẩn, 1: hiển thị -->
		<liferay-ui:search-container-column-text name="Tình trạng" cssClass="ct_icon">
			<c:choose>
				<c:when test="${person.getPsStatus() == 1}">
					<a style="color:blue;" href="<%=activePersonActionURL %>"><i class="far fa-eye"></i></a>
				</c:when>
				<c:otherwise>
					<a style="color:red;" href="<%=unactivePersonActionURL %>"><i class="far fa-eye-slash"></i></a>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator/>
</liferay-ui:search-container>


