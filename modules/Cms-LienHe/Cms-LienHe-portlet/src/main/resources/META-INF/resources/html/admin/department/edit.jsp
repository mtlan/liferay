<%@page import="com.contact.model.Department"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.contact.service.DepartmentLocalServiceUtil"%>
<%@ include file="../init.jsp"%>
<portlet:defineObjects />

<portlet:actionURL name="updateDepartment" var="updateDepartmentActionURL"/>

<%
	long dptId = ParamUtil.getLong(request, "dptId");
	Department department = DepartmentLocalServiceUtil.getDepartment(dptId);
	request.setAttribute("department", department);
%>
<br>
<h2>Cập nhật phòng ban</h2>
<br>
<aui:form action="<%= updateDepartmentActionURL %>" method="post" autocomplete="off" onSubmit="return validateForm(event)">
	<aui:input name="dptId" type="hidden" value="<%=dptId%>"/>
	<aui:input label="Tên phòng ban" name="dptName" value="${department.dptName}">
 		<aui:validator name="required" errorMessage="Làm ơn hãy nhập tên phòng ban"/>
 		<aui:validator name="string"/>
	</aui:input>
	<aui:input label="Mô tả phòng ban" name="dptDesc" value="${department.dptDesc}">
 		<aui:validator name="required" errorMessage="Làm ơn hãy nhập mô tả phòng ban"/>
 		<aui:validator name="string"/>
	</aui:input>
	<label class="form-label">Tình trạng phòng ban<span style="color: red;font-weight: bold;"> *</span></label>
	<div class="form-validator-stack" id="alertCheckbox" style="color: #da1414;display: none;font-size: 0.875rem;"></div>
	<aui:input id="check_hide" label="Hiển thị" name="dptStatus" type="checkbox" value="1" checked="true"/>
	<aui:button type="submit" name="" value="Cập nhật"></aui:button>
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