<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.service.DepartmentLocalService"%>
<%@page import="com.contact.service.PersonLocalServiceUtil"%>
<%@ include file="../../init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.contact.model.Person"%>
<%@page import="com.contact.model.Department"%>

<%
	List<Department> dptList = (List<Department>)request.getAttribute("dptList");
	List<Person> psList = (List<Person>)request.getAttribute("psList");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông tin liên hệ</title>
</head>
<body>
	<c:forEach var="dpt" items="${dptList}">
		<c:if test="${dpt.getDptStatus()==1 && dpt.getDptDelete()==0}">
			<div class="Cus_EmBoxHeader">
		        <span><a href="">${dpt.getDptName()}</a></span>
		    </div>
		    <div class="Cus_EmBoxContainer">
				    <c:forEach var="ps" items="${psList}">
				    	<c:if test="${ps.getPsStatus()==1 && ps.getPsDelete()==0}">
					    	<c:if test="${dpt.getDptId() == ps.getDptId()}">
					    		<div class="clear "></div>
						        <div class="Cus_EmBoxItem ">
						            <span class="IName" style="display:block;">${ps.getPsName()}</span> 
						            <span class="IPosion" style="display:block;">${ps.getPsPosition()}</span>                     
						            <span class="IPhone" id="phone" style="display:block;">${ps.getPsPhone()}</span>
						            <span class="IEmail" style="display:block;"><a href="mailto:${ps.getPsEmail()}">${ps.getPsEmail()}</a></span>
						        </div>
					        	<div class="clear "></div>
				        	</c:if>
				        </c:if>
					</c:forEach>
    		</div>
    	</c:if>
	</c:forEach>
</body>
</html>