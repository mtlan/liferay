<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@page import="com.contact.model.Department"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="../init.jsp"%>
<portlet:defineObjects />

<portlet:actionURL name="addPerson" var="addPersonActionURL"/>

<% 
	DynamicQuery dynamicQuery = DepartmentLocalServiceUtil.dynamicQuery();
	dynamicQuery.add(RestrictionsFactoryUtil.eq("dptStatus", 1L));
	dynamicQuery.add(RestrictionsFactoryUtil.eq("dptDelete", 0L));
	List<Department> dptDy = DepartmentLocalServiceUtil.dynamicQuery(dynamicQuery);
	request.setAttribute("dptDy", dptDy);
%>
<br>
<h2>THÊM THÔNG TIN LIÊN HỆ</h2>
<br>
<liferay-ui:error key="personAddedFail" message="Tên liên hệ đã tồn tại!"></liferay-ui:error>
<aui:form action="<%=addPersonActionURL %>" name="PersonForm" method="POST" autocomplete="off" onSubmit="return validateForm(event)">
	<aui:input label="Tên người liên hệ" name="psName" value="<%= ParamUtil.getString(request, "psName") %>">
 		<aui:validator name="required" errorMessage="Làm ơn hãy nhập tên người liên hệ"/>
 		<aui:validator name="string"/>
	</aui:input>
	<aui:input label="Chức vụ người liên hệ" name="psPosition" value="<%= ParamUtil.getString(request, "psPosition") %>">
 		<aui:validator name="required" errorMessage="Làm ơn hãy nhập chức vụ người liên hệ"/>
 		<aui:validator name="string"/>
	</aui:input>
	<aui:select label="Phòng ban" name="dptId" value="<%= ParamUtil.getString(request, "dptId") %>">
		<aui:option value="">---Chọn---
		<aui:validator name="required" errorMessage="Làm ơn hãy chọn phòng ban"></aui:validator>
	</aui:option>
	<c:forEach var="dpt" items="${dptDy}">	
		<aui:option value="${dpt.getDptId()}">${dpt.getDptName()}</aui:option>
	</c:forEach>
	</aui:select>	
	<aui:input label="Số điện thoại" name="psPhone" value="<%= ParamUtil.getString(request, "psPhone") %>">
 		<aui:validator name="required"/>
 		<aui:validator name="number" errorMessage="Làm ơn hãy nhập số điện thoại người liên hệ"/>
	</aui:input>
	<aui:input label="Email" name="psEmail" value="<%= ParamUtil.getString(request, "psEmail") %>">
 		<aui:validator name="required" errorMessage="Làm ơn hãy nhập email người liên hệ"/>
 		<aui:validator name="email"/>
	</aui:input>
	<label class="form-label">Tình trạng người liên hệ<span style="color: red;font-weight: bold;"> *</span></label>
	<div class="form-validator-stack" id="alertCheckbox" style="color: #da1414;display: none;font-size: 0.875rem;"></div>
    <aui:input id="check_show" label="Hiển thị" name="psStatus" type="checkbox" value="1" checked="true"/>

	<aui:button type="submit" name="addPerson" value="Thêm"></aui:button>
</aui:form>

<aui:script>
	
	// xử lý khi không tick chọn
	function validateForm(e) {
        let checkbox = document.getElementById('<portlet:namespace/>check_show');
        let alertCheckbox = document.getElementById('alertCheckbox');

        if (!checkbox.checked) {
        	alertCheckbox.textContent = "Làm ơn hãy chọn tình trạng.";
        	alertCheckbox.style.display = 'block';
            setTimeout(() => {
            	alertCheckbox.style.display = 'none';
        	}, 3000);
            e.preventDefault();
            return false; // Ngăn form không gửi đi
        }

        return true; // Cho phép gửi form nếu hợp lệ
    }
	
</aui:script>