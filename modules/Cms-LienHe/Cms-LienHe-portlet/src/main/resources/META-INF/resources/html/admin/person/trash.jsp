<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.model.Department"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.contact.service.PersonLocalServiceUtil"%>
<%@page import="com.contact.model.Person"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<portlet:defineObjects />

<%
	String currentURL = PortalUtil.getCurrentURL(request);
%>

<portlet:actionURL name="searchPerson" var="searchPersonActionURL"/>

<portlet:renderURL var="backve">
    <portlet:param name="backURL" value="<%=currentURL %>"/>
</portlet:renderURL>

<h2>THÙNG RÁC</h2>
<br>
<nav class="navbar justify-content-between">
	<h2><a href="<%= backve %>">Quay về</a></h2>
	<aui:form cssClass="form-inline" action="<%=searchPersonActionURL %>" method="POST">
		<aui:input cssClass="form-control mr-sm-2" label="" name="keyps" type="text" placeholder="Tìm kiếm người liên hệ..."></aui:input>
		<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
	</aui:form>
</nav>
<br>

<%		
	// lấy ra danh sách có delete = 1
	List<Person> personDelBy1 = PersonLocalServiceUtil.getPersons(-1, -1);
	List<Person> psNewBy1 = personDelBy1.stream().filter(psDel->psDel.getPsDelete()==1).collect(Collectors.toList());
%>

<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="Xin lỗi, không có dòng để hiển thị" total="<%=psNewBy1.size() %>">

	<liferay-ui:search-container-results 
	results="<%= ListUtil.subList(psNewBy1, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
	
	<liferay-ui:search-container-row className="Person" keyProperty="psId" modelVar="person" indexVar="rowIndex">
		
		<portlet:actionURL name="recoveryPerson" var="recoveryPersonActionURL">
	        <portlet:param name="psId" value="${person.getPsId()}"/>
	        <portlet:param name="psDelete" value="${person.getPsDelete()}"/>
	    </portlet:actionURL>

	    <portlet:actionURL name="deletePerson" var="deletePersonActionURL">
	        <portlet:param name="psId" value="${person.getPsId()}"/>
	    </portlet:actionURL>
	 	
	 	<%	
		    Department department = DepartmentLocalServiceUtil.fetchDepartment(person.getDptId());
			String dptName = (department!=null) ? department.getDptName():"Chưa xác định";
	    %>
	 	
	    <!-- if...else... -->
           <c:choose>
	        <c:when test="${person.getPsDelete() == 1}">
	        	<liferay-ui:search-container-column-text name="STT" value="${rowIndex + 1}"/>
	            <liferay-ui:search-container-column-text property="psName" name="Tên người liên hệ"/>
				<liferay-ui:search-container-column-text property="psPosition" name="Chức vụ"/>
				<liferay-ui:search-container-column-text value="<%=dptName %>" name="Phòng ban"/>
				<liferay-ui:search-container-column-text property="psPhone" name="Số điện thoại"/>
				<liferay-ui:search-container-column-text property="psEmail" name="Email"/>
				<!-- 0: ẩn, 1: hiển thị -->
				<liferay-ui:search-container-column-text name="Tình trạng">
					<c:choose>
						<c:when test="${person.getPsStatus() == 1}">
							<a style="color:blue;"><i class="far fa-eye"></i></a>
						</c:when>
						<c:otherwise>
							<a style="color:red;"><i class="far fa-eye-slash"></i></a>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>
		
				<liferay-ui:search-container-column-text property="" name="Quản lý">
					<a style="color: #fff" href="<%=recoveryPersonActionURL%>" class="btn btn-success btn-sm px-2 py-1" >Khôi phục</a>
					<a style="color: #fff" href="<%=deletePersonActionURL%>" class="btn btn-danger btn-sm px-2 py-1" onclick="return confirm('Bạn có muốn xóa thông tin người liên hệ này không');">Xóa</a>
				</liferay-ui:search-container-column-text>
	        </c:when>
	        <c:otherwise>
	            <div class="alert alert-primary" role="alert">Thùng rác trống!</div>
	        </c:otherwise>
	    </c:choose>
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator searchContainer="<%=searchContainer %>"></liferay-ui:search-iterator>
	
</liferay-ui:search-container>