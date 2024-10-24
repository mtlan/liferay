<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="../init.jsp"%>
<portlet:defineObjects />
<portlet:actionURL name="addDepartment" var="addDepartmentActionURL"/>
<br>
<h2>THÊM PHÒNG BAN</h2>
<br>
<liferay-ui:error key="departmentAddedFail" message="Tên phòng ban đã tồn tại!"></liferay-ui:error>
<aui:form action="<%=addDepartmentActionURL %>" name="DepartmentForm" method="POST" autocomplete="off" onSubmit="return validateForm(event)">
	<aui:input label="Tên phòng ban" name="dptName" value="<%= ParamUtil.getString(request, "dptName") %>">
 		<aui:validator name="required" errorMessage="Làm ơn hãy nhập tên phòng ban"/>
 		<aui:validator name="string"/>
	</aui:input>
	<aui:input label="Mô tả phòng ban" name="dptDesc" value="<%= ParamUtil.getString(request, "dptDesc") %>">
 		<aui:validator name="required" errorMessage="Làm ơn hãy nhập mô tả phòng ban"/>
 		<aui:validator name="string"/>
	</aui:input>
	<label class="form-label">Tình trạng phòng ban<span style="color: red;font-weight: bold;"> *</span></label>
	<div class="form-validator-stack" id="alertCheckbox" style="color: #da1414;display: none;font-size: 0.875rem;"></div>
<!--<aui:input id="check_hide" onClick="checkCheckboxes()" label="Ẩn" name="dptStatus" type="checkbox" value="0"/>-->
    <aui:input id="check_show" label="Hiển thị" name="dptStatus" type="checkbox" value="1" checked="true"/>
    
	<aui:button type="submit" name="addDepartment" value="Thêm"></aui:button>
</aui:form>

<aui:script>
/*function checkCheckboxes() {
	    let checkbox1 = document.getElementById('_com_contact_ContactPortlet_check_hide');
	    let checkbox2 = document.getElementById('_com_contact_ContactPortlet_check_show');
	    let alertCheckbox = document.getElementById('alertCheckbox');
	
	    if (checkbox1.checked && checkbox2.checked) {
	        alertCheckbox.textContent = "Làm ơn hãy chọn 1";
        	alertCheckbox.style.display = 'block';
            setTimeout(() => {
            	alertCheckbox.style.display = 'none';
        	}, 3000);
	        checkbox1.checked = false; // Bỏ tick checkbox đầu tiên
	        checkbox2.checked = false; // Bỏ tick checkbox thứ hai
	    }
	} */
	
	// xử lý khi không tick chọn
	function validateForm(e) {
        let checkbox = document.getElementById('<portlet:namespace/>check_show');
        let alertCheckbox = document.getElementById('alertCheckbox');

        if (!checkbox.checked) {
        	alertCheckbox.textContent = "Làm ơn hãy chọn tình trạng phòng ban.";
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