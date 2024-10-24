<%@page import="com.contact.constants.LienHeKeys"%>
<%@page import="com.contact.constants.PhongBanKeys"%>
<%@page import="javax.portlet.RenderResponse"%>
<%@ include file="init.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<div class="container">
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#dpt">Phòng ban</a></li>
    <li><a data-toggle="tab" href="#ps">Liên hệ</a></li>
  </ul>
  <div class="tab-content">
    <div id="dpt" class="tab-pane fade in active">
    	<liferay-portlet:runtime portletName="<%=PhongBanKeys.DEPARTMENT %>"/>
    </div>
    <div id="ps" class="tab-pane fade">
      	<liferay-portlet:runtime portletName="<%=LienHeKeys.PERSON %>"/>
    </div>
  </div>
</div>