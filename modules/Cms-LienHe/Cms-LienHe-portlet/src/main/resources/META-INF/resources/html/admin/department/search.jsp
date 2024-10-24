<%@page import="com.contact.model.Department"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<portlet:defineObjects />
<portlet:actionURL name="searchDepartment" var="searchDepartmentActionURL"/>

<%  
	List<Department> listKey = (List<Department>)request.getAttribute("listKey"); 
	String currentURL = PortalUtil.getCurrentURL(request);
%>

<portlet:renderURL var="backve">
    <portlet:param name="backURL" value="<%=currentURL %>"/>
</portlet:renderURL>

<nav class="navbar justify-content-between">
  	<h2><a href="<%= backve %>">Quay về</a></h2>
	<aui:form cssClass="form-inline" action="<%=searchDepartmentActionURL %>" method="POST">
		<aui:input cssClass="form-control mr-sm-2" label="" name="keydpt" type="text" placeholder="Tìm kiếm phòng ban..."></aui:input>
		<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
	</aui:form>
</nav>
<br>
<table class="table table-striped">
    <tr>
        <th>Tên phòng ban</th>
        <th>Mô tả phòng ban</th>
        <th>Tình trạng phòng ban</th>
    </tr>
    <c:if test="${not empty listKey}">
		<c:forEach var="dpt" items="${listKey}">
	    	<tr>
	            <td>${dpt.getDptName()}</td>
	            <td>${dpt.getDptDesc()}</td>
	            <!-- if...else... -->
	            <c:choose>
			        <c:when test="${dpt.getDptStatus() == 0}">
			            <td><a style="color:blue;"><i class="far fa-eye"></i></a></td>
			        </c:when>
			        <c:otherwise>
			            <td><a style="color:red;"><i class="far fa-eye-slash"></i></a></td>
			        </c:otherwise>
			    </c:choose>
         	<tr>
	    </c:forEach>
	</c:if>    
</table>