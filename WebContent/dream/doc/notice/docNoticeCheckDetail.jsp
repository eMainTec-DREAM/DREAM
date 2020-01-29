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

<%@ page import="dream.doc.notice.action.DocNoticeDetailAction" %>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>

<html:html>
<head>
<!-- 공지사항 디테일 -->
<title><bean:message key='MENU.NOTICE' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var empAc, equipAc, eqLocAc, assBaseListAc;

function loadPage() 
{	
	setDisableAll();
	
    setTitle("docNoticeDetailDTO.noticeNo", "docNoticeDetailDTO.title");
    //For Save
    setForUpdate();
}

function goTabPage(pageId) 
{
    var form = document.docNoticeDetailForm;
    
    if(pageId == "maDocLibList" || pageId == "docNoticeCheckDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['docNoticeDetailDTO.noticeId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "NOTICE";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['docNoticeDetailDTO.title'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
       
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/docNoticeDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="docNoticeCommonDTO.noticeId" />          <!-- Key -->
        <html:hidden property="docNoticeDetailDTO.noticeId" />          <!-- Key -->
        <html:hidden property="docNoticeDetailDTO.noticeStatusId" />    <!-- 상태ID -->
        <html:hidden property="docNoticeDetailDTO.regDeptId" />         <!-- 작성부서ID -->
        <html:hidden property="docNoticeDetailDTO.writeById" />         <!-- 작성자ID -->
                
        <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
		<html:hidden property="maDocLibCommonDTO.objectType" />
		<html:hidden property="maDocLibCommonDTO.description" />
        
        <div class="article_box">
            <div class="form_box">
                <!-- 공지# -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.noticeNo"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeDetailDTO.noticeNo" tabindex="10"/>
                    </div>
                </div>
                <!-- 상태 -->
                <div class="field">
                    <label><bean:message key="LABEL.status"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.noticeStatus" tabindex="20" readonly="true" />
                    </div>
                </div>
                <!-- 제목 -->
                <div class="field_long">
                    <label class="check"><bean:message key="LABEL.title"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeDetailDTO.title" tabindex="30"/>
                    </div>
                </div>
                <!-- 공지기한 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.noticePeriod"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeDetailDTO.noticePeriod" tabindex="40"/>
                    </div>
                </div>
                <!-- 공지일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.noticeDate"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.noticeDate" tabindex="50" readonly="true" />
                    </div>
                </div>
                <!-- 작성부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.regDept"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.regDept" tabindex="60" readonly="true" />
                    </div>
                </div>
                <!-- 작성자 -->
                <div class="field">
                    <label><bean:message key="LABEL.writeBy"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.writeBy" tabindex="70" readonly="true" />
                    </div>
                </div>
                <!-- 내용 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.contents"/></label>
                    <div class="input_box">
                        <html:textarea property="docNoticeDetailDTO.remark" styleClass="ta150" tabindex="80" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
