<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
<%@page import="com.contact.portlet.PersonOrderByComparator"%>
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
	List<Department> dptDy = (List<Department>)request.getAttribute("dptDy");
%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Thông tin người liên hệ</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
	<body>
		<!-- gọi trang create.jsp -->
		<portlet:renderURL var="addPersonRenderURL">
		    <portlet:param name="mvcPath" value="/html/admin/person/create.jsp"/>
		</portlet:renderURL>
		
		<portlet:renderURL var="trashPersonRenderURL">
		    <portlet:param name="mvcPath" value="/html/admin/person/trash.jsp"/>
		</portlet:renderURL>
		
		<portlet:actionURL name="searchPerson" var="searchPersonActionURL"/>
		<br>
		<h2>DANH SÁCH THÔNG TIN NGƯỜI LIÊN HỆ</h2>
		<div class="container mt-4">
		  <!-- Button Group -->
		  <div class="btn-group" role="group">
		  	<a href="${addPersonRenderURL}" class="btn btn-primary">Thêm mới</a>
			<a href="${trashPersonRenderURL}" class="btn btn-secondary ml-3">Thùng rác</a>
		  </div>
		  <!-- Dropdowns và Input -->
		  <div class="d-flex justify-content-between mt-3">
		    <!-- Dropdown Lọc phòng ban, name filterPerson là processaction bên portlet  -->
		    <portlet:actionURL name="filterPerson" var="filterPersonActionURL"/>
		    <aui:form cssClass="form-inline" action="<%=filterPersonActionURL %>" method="POST">
		    	<aui:select name="filterName" label="" onChange="this.form.submit()">
		    		<aui:option>------Lọc---------</aui:option>
		    		<c:forEach var="dpt" items="${dptDy}">	
						<aui:option value="${dpt.getDptId()}">${dpt.getDptName()}</aui:option>
					</c:forEach>
		    	</aui:select>
		    <!-- Dropdown Lọc status -->
		    	<aui:select name="filterStatus" label="" onChange="this.form.submit()">
		    		<aui:option>------Lọc---------</aui:option>
		    		<aui:option value="0">Ẩn</aui:option>
		    		<aui:option value="1">Hiện</aui:option>
		    	</aui:select>
		    </aui:form>
		    <aui:form cssClass="form-inline" action="<%=searchPersonActionURL %>" method="POST">
				<aui:input cssClass="form-control mr-sm-2" label="" name="keyps" type="text" placeholder="Tìm kiếm..."></aui:input>
				<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
			</aui:form>
		  </div>
		</div>
		<br>
		
		<%
			//Kiểm tra xem thuộc tính "departmentAddedSuccess" có tồn tại không
			Boolean isPersonAddedSuccess = (Boolean) request.getAttribute("personAddedSuccess");
			Boolean isPersonUpdatedSuccess = (Boolean) request.getAttribute("personUpdatedSuccess");
			Boolean isPersonRemovedSuccess = (Boolean) request.getAttribute("personRemovedSuccess");
			Boolean isPersonRecoveryedSuccess = (Boolean) request.getAttribute("personRecoveryedSuccess");
			Boolean isPersonActivedSuccess = (Boolean) request.getAttribute("personActivedSuccess");
			Boolean isPersonUnactivedSuccess = (Boolean) request.getAttribute("personUnactivedSuccess");
			
			if (isPersonAddedSuccess == null) {
				isPersonAddedSuccess = false;
			}
			if (isPersonUpdatedSuccess == null) {
				isPersonUpdatedSuccess = false;
			}
			if (isPersonRemovedSuccess == null) {
				isPersonRemovedSuccess = false;
			}
			if (isPersonRecoveryedSuccess == null) {
				isPersonRecoveryedSuccess = false;
			}
			if (isPersonActivedSuccess == null) {
				isPersonActivedSuccess = false;
			}
			if (isPersonUnactivedSuccess == null) {
				isPersonUnactivedSuccess = false;
			}
		%>
		
		<%
			String orderByCol = ParamUtil.getString(request, "orderByCol","psName"); 
			String orderByType = ParamUtil.getString(request, "orderByType","asc");
			
			OrderByComparator<Person> comparator = new PersonOrderByComparator(orderByType.equals("asc"));
						
			DynamicQuery dynamicQuery = PersonLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.eq("psDelete", 0L));
			List<Person> psNewBy0 = PersonLocalServiceUtil.dynamicQuery(dynamicQuery,-1,-1,comparator);
			
		%>
		
		<%
			List<Person> resultList = (List<Person>)request.getAttribute("resultList");
		%>
		
		<!-- Thông báo Bootstrap -->
		<div id="alertContainer" class="alert alert-success fade show" role="alert" style="display: none;"></div>
		<c:choose>
			<c:when test="${not empty resultList}">
				<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="Xin lỗi, không có dòng để hiển thị" total="<%=resultList.size() %>" orderByCol="<%= orderByCol %>" orderByType="<%= orderByType %>">
		
					<liferay-ui:search-container-results 
					results="<%= ListUtil.subList(resultList, searchContainer.getStart(), searchContainer.getEnd()) %>" 
					/>
					
					<liferay-ui:search-container-row className="Person" keyProperty="psId" modelVar="person" indexVar="rowIndex">
						
						<portlet:renderURL var="updatePersonRenderURL">
				           <portlet:param name="psId" value="${person.psId}"/>
				           <portlet:param name="mvcPath" value="/html/admin/person/edit.jsp"/>
				       	</portlet:renderURL>
				
					    <portlet:actionURL name="removePerson" var="removePersonActionURL">
					        <portlet:param name="psId" value="${person.getPsId()}"/>
					        <portlet:param name="psDelete" value="${person.getPsDelete()}"/>
					    </portlet:actionURL>
					    
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
					    <!-- if...else... -->
				        <c:choose>
					        <c:when test="${not empty person}">
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
								<liferay-ui:search-container-column-text property="" name="Quản lý">
									<a style="color: #fff" href="<%=updatePersonRenderURL%>" class="btn btn-success btn-sm px-2 py-1" >Sửa</a>
									<a style="color: #fff" href="<%=removePersonActionURL%>" class="btn btn-danger btn-sm px-2 py-1" onclick="return confirm('Bạn có muốn di chuyển dữ liệu vào thùng rác không');">Xóa</a>
								</liferay-ui:search-container-column-text>
					        </c:when>
					        <c:otherwise>
					            <div class="alert alert-primary" role="alert">Dữ liệu chưa thêm mới hoặc đang ở trong thùng rác!</div>
					        </c:otherwise>
					    </c:choose>
					</liferay-ui:search-container-row>
					
					<liferay-ui:search-iterator/>
					
				</liferay-ui:search-container>
			</c:when>
			<c:otherwise>
				<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="Xin lỗi, không có dòng để hiển thị" total="<%=psNewBy0.size() %>" orderByCol="<%= orderByCol %>" orderByType="<%= orderByType %>">
		
					<liferay-ui:search-container-results 
					results="<%= ListUtil.subList(psNewBy0, searchContainer.getStart(), searchContainer.getEnd()) %>" 
					/>
					
					<liferay-ui:search-container-row className="Person" keyProperty="psId" modelVar="person" indexVar="rowIndex">
						
						<portlet:renderURL var="updatePersonRenderURL">
				           <portlet:param name="psId" value="${person.psId}"/>
				           <portlet:param name="mvcPath" value="/html/admin/person/edit.jsp"/>
				       	</portlet:renderURL>
				
					    <portlet:actionURL name="removePerson" var="removePersonActionURL">
					        <portlet:param name="psId" value="${person.getPsId()}"/>
					        <portlet:param name="psDelete" value="${person.getPsDelete()}"/>
					    </portlet:actionURL>
					    
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
					    <!-- if...else... -->
				        <c:choose>
					        <c:when test="${not empty person}">
					        	<liferay-ui:search-container-column-text name="STT" value="${rowIndex + 1}"/>
					            <liferay-ui:search-container-column-text property="psName" name="Tên người liên hệ" orderable="<%=true %>"/>
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
								<liferay-ui:search-container-column-text property="" name="Quản lý">
									<a style="color: #fff" href="<%=updatePersonRenderURL%>" class="btn btn-success btn-sm px-2 py-1" >Sửa</a>
									<a style="color: #fff" href="<%=removePersonActionURL%>" class="btn btn-danger btn-sm px-2 py-1" onclick="return confirm('Bạn có muốn di chuyển dữ liệu vào thùng rác không');">Xóa</a>
								</liferay-ui:search-container-column-text>
					        </c:when>
					        <c:otherwise>
					            <div class="alert alert-primary" role="alert">Dữ liệu chưa thêm mới hoặc đang ở trong thùng rác!</div>
					        </c:otherwise>
					    </c:choose>
					</liferay-ui:search-container-row>
					
					<liferay-ui:search-iterator/>
					
				</liferay-ui:search-container>
			</c:otherwise>
		</c:choose>
		
		<aui:script>
		    AUI().ready(function() {
		        if (<%= isPersonAddedSuccess %>) {
		        	let alertContainer = document.getElementById('alertContainer');
		        	alertContainer.textContent = "Thêm thông tin liên hệ thành công!"
		        	alertContainer.style.display = 'block';
		            setTimeout(() => {
		            	alertContainer.style.display = 'none';
		        	}, 3000);
		        } else if(<%= isPersonUpdatedSuccess %>) {
		        	let alertContainer = document.getElementById('alertContainer');
		        	alertContainer.textContent = "Cập nhật thông tin liên hệ thành công!"
		        	alertContainer.style.display = 'block';
		            setTimeout(() => {
		            	alertContainer.style.display = 'none';
		        	}, 3000);
		        } else if(<%= isPersonRemovedSuccess %>) {
		        	let alertContainer = document.getElementById('alertContainer');
		        	alertContainer.textContent = "Di chuyển thông tin liên hệ thành công!"
		        	alertContainer.style.display = 'block';
		            setTimeout(() => {
		            	alertContainer.style.display = 'none';
		        	}, 3000);
		        } else if(<%= isPersonRecoveryedSuccess %>) {
		        	let alertContainer = document.getElementById('alertContainer');
		        	alertContainer.textContent = "Khôi phục thông tin liên hệ thành công!"
		        	alertContainer.style.display = 'block';
		            setTimeout(() => {
		            	alertContainer.style.display = 'none';
		        	}, 3000);
		        } else if(<%= isPersonActivedSuccess %>) {
		        	let alertContainer = document.getElementById('alertContainer');
		        	alertContainer.textContent = "Ẩn thông tin liên hệ thành công!"
		        	alertContainer.style.display = 'block';
		            setTimeout(() => {
		            	alertContainer.style.display = 'none';
		        	}, 3000);
		        }else if(<%= isPersonUnactivedSuccess %>) {
		        	let alertContainer = document.getElementById('alertContainer');
		        	alertContainer.textContent = "Hiện thông tin liên hệ thành công!"
		        	alertContainer.style.display = 'block';
		            setTimeout(() => {
		            	alertContainer.style.display = 'none';
		        	}, 3000);
		        }
		    });
		</aui:script>
	</body>
</html>

