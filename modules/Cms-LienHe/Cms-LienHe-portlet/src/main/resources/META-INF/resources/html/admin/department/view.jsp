<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.contact.comparator.DepartmentOrderByComparator"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.model.Department"%>
<%@page import="java.util.List"%>
<%@ page import="com.liferay.portal.kernel.servlet.SessionMessages" %>
<%@ include file="../init.jsp"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<%
String currentURL = PortalUtil.getCurrentURL(request);
%>

<%
String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", GetterUtil.DEFAULT_STRING);
	String orderByCol = ParamUtil.getString(renderRequest, "orderByCol","dptName"); 
	String orderByType = ParamUtil.getString(renderRequest, "orderByType","asc");

	OrderByComparator<Department> comparator = new DepartmentOrderByComparator(orderByType.equals("asc"));
	String keydpt = (String)renderRequest.getAttribute("keydpt");
	
	List<Department> listDpt = (List<Department>)renderRequest.getAttribute("listDpt");
%>
  	
<!-- gọi trang create.jsp -->
<portlet:renderURL var="addDepartmentRenderURL">
    <portlet:param name="mvcPath" value="/html/admin/department/create.jsp"/>
</portlet:renderURL>

<portlet:renderURL var="trashDepartmentRenderURL">
    <portlet:param name="mvcPath" value="/html/admin/department/trash.jsp"/>
</portlet:renderURL>

<portlet:actionURL name="searchDepartment" var="searchDepartmentActionURL">
</portlet:actionURL>
<portlet:actionURL name="clearSearch" var="clearSearchActionURL">
</portlet:actionURL>
<h2>DANH SÁCH PHÒNG BAN</h2>
<div class="container mt-4">
  <!-- Button Group -->
  <div class="btn-group" role="group">
  	<a href="${addDepartmentRenderURL}" class="btn btn-primary btn-default">Thêm mới</a>
	<a href="${trashDepartmentRenderURL}" class="btn btn-secondary ml-3">Thùng rác</a>
	<%
		if(!keydpt.isEmpty()) {
	%>
	<a href="${clearSearchActionURL}" class="btn btn-link">Xóa từ khóa tìm kiếm</a>
	<%
		}
	%>
  </div>
  <!-- Dropdowns và Input -->
  <div class="d-flex justify-content-between mt-3">
	<!-- Dropdown Lọc phòng ban, name filterPerson là processaction bên portlet  -->
	<portlet:actionURL name="filterDpt" var="filterDptActionURL"/>
	<aui:form cssClass="form-inline" action="<%=filterDptActionURL %>" method="POST">
		<!-- Dropdown Lọc status -->
		<aui:select name="filterStatus" label="" onChange="this.form.submit()">
			<aui:option value="">------Lọc---------</aui:option>
			<aui:option value="0" selected="${filterStatus == '0'}">Ẩn</aui:option>
			<aui:option value="1" selected="${filterStatus == '1'}">Hiện</aui:option>
		</aui:select>
	</aui:form>
	<aui:form cssClass="form-inline" action="<%=searchDepartmentActionURL %>" method="POST">
		<aui:input cssClass="form-control mr-sm-2" label="" name="keydpt" type="text" placeholder="Tìm kiếm phòng ban..." value="<%=keydpt %>"></aui:input>
		<aui:button cssClass="btn btn-primary my-2 my-sm-0" type="submit" value="Tìm kiếm"></aui:button>
	</aui:form>
	</div>
</div>
<br>
<%
	//Kiểm tra xem thuộc tính "departmentAddedSuccess" có tồn tại không
	Boolean isDepartmentAddedSuccess = (Boolean) request.getAttribute("departmentAddedSuccess");
	Boolean isDepartmentUpdatedSuccess = (Boolean) request.getAttribute("departmentUpdatedSuccess");
	Boolean isDepartmentRemovedSuccess = (Boolean) request.getAttribute("departmentRemovedSuccess");
	Boolean isDepartmentRecoveryedSuccess = (Boolean) request.getAttribute("departmentRecoveryedSuccess");
	Boolean isDepartmentActivedSuccess = (Boolean) request.getAttribute("departmentActivedSuccess");
	Boolean isDepartmentUnactivedSuccess = (Boolean) request.getAttribute("departmentUnactivedSuccess");
	
	if (isDepartmentAddedSuccess == null) {
	 isDepartmentAddedSuccess = false;
	}
	if (isDepartmentUpdatedSuccess == null) {
		isDepartmentUpdatedSuccess = false;
	}
	if (isDepartmentRemovedSuccess == null) {
		isDepartmentRemovedSuccess = false;
	}
	if (isDepartmentRecoveryedSuccess == null) {
		isDepartmentRecoveryedSuccess = false;
	}
	if (isDepartmentActivedSuccess == null) {
		isDepartmentActivedSuccess = false;
	}
	if (isDepartmentUnactivedSuccess == null) {
		isDepartmentUnactivedSuccess = false;
	}
%>

<!-- Thông báo Bootstrap -->
<div id="alertContainer" class="alert alert-success fade show" role="alert" style="display: none;"></div>
<liferay-ui:success key="departmentRemovedSuccess" message="Xóa phòng ban thành công!" />
<liferay-ui:search-container delta="5" deltaConfigurable="true" emptyResultsMessage="Xin lỗi, không có dòng để hiển thị" total="<%=listDpt.size() %>" orderByCol="<%= orderByCol %>" orderByType="<%= orderByType %>">

	<liferay-ui:search-container-results 
	results="<%= ListUtil.subList(listDpt, searchContainer.getStart(), searchContainer.getEnd()) %>" 
	/>
	
	<liferay-ui:search-container-row className="Department" keyProperty="dptId" modelVar="department" indexVar="rowIndex">
		
		<portlet:renderURL var="updateDepartmentRenderURL">
           <portlet:param name="dptId" value="${department.dptId}"/>
           <portlet:param name="mvcPath" value="/html/admin/department/edit.jsp"/>
       	</portlet:renderURL>

	    <portlet:actionURL name="removeDepartment" var="removeDepartmentActionURL">
	        <portlet:param name="dptId" value="${department.getDptId()}"/>
	        <portlet:param name="dptDelete" value="${department.getDptDelete()}"/>
	        <portlet:param name="backURL" value="<%=currentURL %>"/>
	    </portlet:actionURL>
	    
	    <portlet:actionURL name="activeDepartment" var="activeDepartmentActionURL">
    		<portlet:param name="dptId" value="${department.getDptId()}"/>
		</portlet:actionURL>
		
		<portlet:actionURL name="unactiveDepartment" var="unactiveDepartmentActionURL">
    		<portlet:param name="dptId" value="${department.getDptId()}"/>
		</portlet:actionURL>
	    
	    <!-- if...else... -->
        <c:choose>
	        <c:when test="${not empty department}">
	        	<liferay-ui:search-container-column-text name="STT" value="${rowIndex + 1}"/>
	            <liferay-ui:search-container-column-text property="dptName" name="Tên phòng ban" orderable="<%=true %>"/>
				<liferay-ui:search-container-column-text property="dptDesc" name="Mô tả phòng ban" orderable="<%=true %>"/>
				<!-- 0: ẩn, 1: hiển thị -->
				<liferay-ui:search-container-column-text name="Tình trạng phòng ban" cssClass="ct_icon">
					<c:choose>
						<c:when test="${department.getDptStatus() == 1}">
							<a style="color:blue;" href="<%=activeDepartmentActionURL %>"><i class="far fa-eye"></i></a>
						</c:when>
						<c:otherwise>
							<a style="color:red;" href="<%=unactiveDepartmentActionURL %>"><i class="far fa-eye-slash"></i></a>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text property="" name="Quản lý">
					<a style="color: #fff" href="<%=updateDepartmentRenderURL%>" class="btn btn-success btn-sm px-2 py-1" >Sửa</a>
					<a style="color: #fff" href="<%=removeDepartmentActionURL%>" class="btn btn-danger btn-sm px-2 py-1" onclick="return confirm('Bạn có muốn di chuyển dữ liệu vào thùng rác không');">Xóa</a>
				</liferay-ui:search-container-column-text>
	        </c:when>
	        <c:otherwise>
	            <div class="alert alert-primary" role="alert">Dữ liệu chưa thêm mới hoặc đang ở trong thùng rác!</div>		
	        </c:otherwise>
	    </c:choose>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator/>
</liferay-ui:search-container>

<aui:script>
    AUI().ready(function() {
        if (<%= isDepartmentAddedSuccess %>) {
        	let alertContainer = document.getElementById('alertContainer');
        	alertContainer.textContent = "Thêm phòng ban thành công!"
        	alertContainer.style.display = 'block';
            setTimeout(() => {
            	alertContainer.style.display = 'none';
        	}, 3000);
        } else if(<%= isDepartmentUpdatedSuccess %>) {
        	let alertContainer = document.getElementById('alertContainer');
        	alertContainer.textContent = "Cập nhật phòng ban thành công!"
        	alertContainer.style.display = 'block';
            setTimeout(() => {
            	alertContainer.style.display = 'none';
        	}, 3000);
        } else if(<%= isDepartmentRemovedSuccess %>) {
        	let alertContainer = document.getElementById('alertContainer');
        	alertContainer.textContent = "Di chuyển phòng ban thành công!"
        	alertContainer.style.display = 'block';
            setTimeout(() => {
            	alertContainer.style.display = 'none';
        	}, 3000);
        } else if(<%= isDepartmentRecoveryedSuccess %>) {
        	let alertContainer = document.getElementById('alertContainer');
        	alertContainer.textContent = "Khôi phục phòng ban thành công!"
        	alertContainer.style.display = 'block';
            setTimeout(() => {
            	alertContainer.style.display = 'none';
        	}, 3000);
        } else if(<%= isDepartmentActivedSuccess %>) {
        	let alertContainer = document.getElementById('alertContainer');
        	alertContainer.textContent = "Ẩn phòng ban thành công!"
        	alertContainer.style.display = 'block';
            setTimeout(() => {
            	alertContainer.style.display = 'none';
        	}, 3000);
        }else if(<%= isDepartmentUnactivedSuccess %>) {
        	let alertContainer = document.getElementById('alertContainer');
        	alertContainer.textContent = "Hiện phòng ban thành công!"
        	alertContainer.style.display = 'block';
            setTimeout(() => {
            	alertContainer.style.display = 'none';
        	}, 3000);
        }
    });
</aui:script>



