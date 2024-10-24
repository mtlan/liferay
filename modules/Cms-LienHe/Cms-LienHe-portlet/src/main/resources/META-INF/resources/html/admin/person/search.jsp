<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.model.Department"%>
<%@page import="com.contact.model.Person"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="java.util.List"%>
<%@ include file="../init.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<portlet:defineObjects />
<portlet:actionURL name="searchPerson" var="searchPersonActionURL"/>

<%  
	String keyps = (String)request.getAttribute("keyps");
	List<Person> ps_key = (List<Person>)request.getAttribute("ps_key"); 
	String currentURL = PortalUtil.getCurrentURL(request);
%>

<portlet:renderURL var="backve">
    <portlet:param name="backURL" value="<%=currentURL %>"/>
</portlet:renderURL>

<nav class="navbar justify-content-between">
  	<h2><a href="<%= backve %>">Quay về</a></h2>
	<aui:form cssClass="form-inline" action="<%=searchPersonActionURL %>" method="POST">
		<aui:input cssClass="form-control mr-sm-2" label="" name="keyps" type="text" placeholder="Tìm kiếm người liên hệ..."></aui:input>
		<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
	</aui:form>
</nav>
<br>
<table class="table table-striped">
    <tr>
        <th>Tên người liên hệ</th>
        <th>Chức vụ</th>
        <th>Phòng ban</th>
        <th>Số điện thoại</th>
        <th>Email</th>
        <th>Tình trạng</th>
    </tr>
    <c:if test="${not empty ps_key}">
		<c:forEach var="ps" items="${ps_key}">
			<%
			 	Person person = (Person) pageContext.getAttribute("ps");
	        	long dptId = person.getDptId();
	        	Department department = DepartmentLocalServiceUtil.fetchDepartment(dptId);
	 			String dptName = (department!=null) ? department.getDptName():"Chưa xác định";
			%>
			<portlet:actionURL name="activePerson" var="activePersonActionURL">
		    		<portlet:param name="psId" value="${ps.getPsId()}"/>
				</portlet:actionURL>
				
			<portlet:actionURL name="unactivePerson" var="unactivePersonActionURL">
	    		<portlet:param name="psId" value="${ps.getPsId()}"/>
			</portlet:actionURL>
	    	<tr>
	            <td>${ps.getPsName()}</td>
	            <td>${ps.getPsPosition()}</td>
	            <td><%=dptName %></td>
	            <td>${ps.getPsPhone()}</td>
	            <td>${ps.getPsEmail()}</td>
	            <!-- if...else... -->
	            <c:choose>
					<c:when test="${ps.getPsStatus() == 1}">
						<td><a style="color:blue;" href="<%=activePersonActionURL %>"><i class="far fa-eye"></i></a></td>
					</c:when>
					<c:otherwise>
						<td><a style="color:red;" href="<%=unactivePersonActionURL %>"><i class="far fa-eye-slash"></i></a></td>
					</c:otherwise>
				</c:choose>
         	</tr>
	    </c:forEach>
	</c:if>    
</table>