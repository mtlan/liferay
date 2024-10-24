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
	DynamicQuery dynamicQuery1 = PersonLocalServiceUtil.dynamicQuery();
	dynamicQuery1.add(RestrictionsFactoryUtil.eq("psDelete", 0L));
	List<Person> resultList1 = PersonLocalServiceUtil.dynamicQuery(dynamicQuery1);
	
	DynamicQuery dynamicQuery2 = PersonLocalServiceUtil.dynamicQuery();
	dynamicQuery2.add(RestrictionsFactoryUtil.eq("psDelete", 0L));
	dynamicQuery2.add(RestrictionsFactoryUtil.eq("psStatus", 1L));
	List<Person> resultList2 = PersonLocalServiceUtil.dynamicQuery(dynamicQuery2);
	
	String conmemay = ParamUtil.getString(renderRequest, "conmemay", "1");
    out.println("cur1param: " + conmemay);
    
    String table2Cur = ParamUtil.getString(renderRequest, "table2Cur", "1");
    out.println("table2Cur: " + table2Cur);

%>
<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="mvcPath" value="/html/admin/person/view3.jsp"/>
</liferay-portlet:renderURL>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- DANH SÁCH 1 -->
<liferay-ui:search-container curParam="conmemay" deltaParam="del1param" delta="5" total="<%=resultList1.size() %>" iteratorURL="<%=portletURL %>">
	<liferay-ui:search-container-results 
	results="<%= ListUtil.subList(resultList1, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
	<portlet:renderURL var="iteratorURL">
	    <portlet:param name="filterName" value="<%= ParamUtil.getString(request, "filterName", "") %>" />
	    <portlet:param name="filterStatus" value="<%= ParamUtil.getString(request, "filterStatus", "") %>" />
	    <portlet:param name="filterCurParam" value="<%= searchContainer.getCurParam() %>" />
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
<br>
<!-- DANH SÁCH 2 -->
<liferay-ui:search-container curParam="table2Cur" deltaParam="del2param" delta="5" total="<%=resultList2.size() %>" iteratorURL="<%=portletURL %>">
	<liferay-ui:search-container-results 
	results="<%= ListUtil.subList(resultList2, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
	<portlet:renderURL var="iteratorURL">
	    <portlet:param name="filterName" value="<%= ParamUtil.getString(request, "filterName", "") %>" />
	    <portlet:param name="filterStatus" value="<%= ParamUtil.getString(request, "filterStatus", "") %>" />
	    <portlet:param name="filterCurParam" value="<%= searchContainer.getCurParam() %>" />
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


