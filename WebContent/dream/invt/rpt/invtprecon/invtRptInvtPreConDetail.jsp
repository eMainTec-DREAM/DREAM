<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="dream.invt.rpt.invtprecon.action.InvtRptInvtPreConDetailAction" %>

<html:html>
<head>
<!-- 투자현황 디테일 -->
<title><bean:message key='LABEL.year' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{	
    setTitle("invtRptInvtPreConDetailDTO.year", "invtRptInvtPreConDetailDTO.deptDesc");
    
}

function goTabPage(pageId) 
{
    var form = document.invtRptInvtPreConDetailForm;
    goCommonTabPage(form, '' , pageId);
       
}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/invtRptInvtPreConDetail">
        <html:hidden property="strutsAction" />
        
        <html:hidden property="invtRptInvtPreConCommonDTO.deptId"/><!-- Key -->
		<html:hidden property="invtRptInvtPreConCommonDTO.year"/><!-- Key -->
        
        <html:hidden property="invtRptInvtPreConDetailDTO.deptId"/>    
        
        <div class="article_box">
            <div class="form_box">
                <!-- 년 -->
                <div class="field">
                    <label><bean:message key="LABEL.year"/></label>
                    <div class="input_read">
                        <html:text property="invtRptInvtPreConDetailDTO.year" tabindex="10" readonly="true"/>
                    </div>
                </div>
                <!-- 부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.dept"/></label>
                    <div class="input_read">
                        <html:text property="invtRptInvtPreConDetailDTO.deptDesc" tabindex="20" readonly="true"/>
                    </div>
                </div>
                <!-- 예정금액 -->
                <div class="field">
                    <label><bean:message key="LABEL.planAmt"/></label>
                    <div class="input_read">
                        <html:text property="invtRptInvtPreConDetailDTO.planAmt" tabindex="30" styleClass="num" readonly="true"/>
                    </div>
                </div>
                <!-- 현재실적 -->
                <div class="field">
                    <label><bean:message key="LABEL.curResult"/></label>
                    <div class="input_read">
                        <html:text property="invtRptInvtPreConDetailDTO.curResult" tabindex="40" styleClass="num" readonly="true"/>
                    </div>
                </div>
                <!-- 잔액 -->
                <div class="field">
                    <label><bean:message key="LABEL.balance"/></label>
                    <div class="input_read">
                        <html:text property="invtRptInvtPreConDetailDTO.balance" tabindex="50" styleClass="num" readonly="true"/>
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
