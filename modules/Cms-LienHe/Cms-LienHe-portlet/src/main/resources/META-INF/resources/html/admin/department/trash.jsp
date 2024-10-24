<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.contact.comparator.DepartmentOrderByComparator"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.model.Department"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<portlet:defineObjects />

<%
	String currentURL = PortalUtil.getCurrentURL(request);
%>

<portlet:actionURL name="searchDepartment" var="searchDepartmentActionURL"/>

<portlet:renderURL var="backve">
    <portlet:param name="backURL" value="<%=currentURL %>"/>
</portlet:renderURL>
<br>
<h2>THÙNG RÁC</h2>
<br>
<nav class="navbar justify-content-between">
	<h2><a href="<%=backve %>">Quay về</a></h2>
	<aui:form cssClass="form-inline" action="<%=searchDepartmentActionURL%>" method="POST">
		<aui:input cssClass="form-control mr-sm-2" label="" name="keydpt" type="text" placeholder="Tìm kiếm phòng ban..."></aui:input>
		<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
	</aui:form>
</nav>
<br>

<%


	String orderByCol = ParamUtil.getString(request, "orderByCol","dptName"); 
	String orderByType = ParamUtil.getString(request, "orderByType","asc");
	
	OrderByComparator<Department> comparator = new DepartmentOrderByComparator(orderByType.equals("asc"));
	
	//cập nhật lại list khi delete = 1
	List<Department> dptDelBy1 = DepartmentLocalServiceUtil.getDepartments(-1, -1, comparator);
	List<Department> dptNewBy1 = dptDelBy1.stream().filter(psDel->psDel.getDptDelete()==1).collect(Collectors.toList());
%>

<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="Xin lỗi, không có dòng để hiển thị" total="<%=dptNewBy1.size() %>" orderByCol="<%= orderByCol %>" orderByType="<%= orderByType %>">

	<liferay-ui:search-container-results 
	results="<%= ListUtil.subList(dptNewBy1, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
	
	<liferay-ui:search-container-row className="Department" keyProperty="dptId" modelVar="department" indexVar="rowIndex">
		
		<portlet:actionURL name="recoveryDepartment" var="recoveryDepartmentActionURL">
	        <portlet:param name="dptId" value="${department.getDptId()}"/>
	        <portlet:param name="dptDelete" value="${department.getDptDelete()}"/>
	    </portlet:actionURL>

	    <portlet:actionURL name="deleteDepartment" var="deleteDepartmentActionURL">
	        <portlet:param name="dptId" value="${department.getDptId()}"/>
	    </portlet:actionURL>
	    
	    <c:choose>
	        <c:when test="${department.getDptDelete() == 1}">
	        	<liferay-ui:search-container-column-text name="STT" value="${rowIndex + 1}"/>
	            <liferay-ui:search-container-column-text property="dptName" name="Tên phòng ban"/>
				<liferay-ui:search-container-column-text property="dptDesc" name="Mô tả phòng ban"/>
				<!-- 0: ẩn, 1: hiển thị -->
				<liferay-ui:search-container-column-text name="Tình trạng phòng ban">
					<c:choose>
						<c:when test="${department.getDptStatus() == 1}">
							<a style="color:blue;"><i class="far fa-eye"></i></a>
						</c:when>
						<c:otherwise>
							<a style="color:red;"><i class="far fa-eye-slash"></i></a>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>
		
				<liferay-ui:search-container-column-text property="" name="Quản lý">
					<a style="color: #fff" href="<%=recoveryDepartmentActionURL%>" class="btn btn-success btn-sm px-2 py-1" >Khôi phục</a>
					<a style="color: #fff" href="<%=deleteDepartmentActionURL%>" class="btn btn-danger btn-sm px-2 py-1" onclick="return confirm('Bạn có muốn xóa phòng ban này không');">Xóa</a>
				</liferay-ui:search-container-column-text>
	        </c:when>
	        <c:otherwise>
	            <div class="alert alert-primary" role="alert">Thùng rác trống!</div>
	        </c:otherwise>
	    </c:choose>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator searchContainer="<%=searchContainer %>"></liferay-ui:search-iterator>
	
</liferay-ui:search-container>