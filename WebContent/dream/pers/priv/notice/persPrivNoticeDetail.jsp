<%--===========================================================================
Notice 설정 - 상세 
author  nhkim8548
version $Id: persPrivNoticeDetail.jsp, V 1.0 2019/06/12 16:23:00 nhkim8548 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.notice.action.PersPrivNoticeDetailAction"%>
<html>
<head>
<!--메뉴명 -->
<title><bean:message key="LABEL.persPrivNoticeDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
<script language="javascript">
function loadPage() 
{
	setTitle("persPrivNoticeDetailDTO.alarmCode", "persPrivNoticeDetailDTO.alarmName");
	setForUpdate();
	
	// 알람 여부
	acSysDesc("persPrivNoticeDetailDTO.isNoticeDesc", "persPrivNoticeDetailDTO.isNotice", "IS_USE");
	
}

function goSave()
{
 //================================
 // 필수 항목 체크한다.
	if(checkValidation()) return;
 //================================
	if(ckCreate(currentPageId)) persPrivNoticeDetailForm.strutsAction.value = '<%=PersPrivNoticeDetailAction.DETAIL_INPUT%>';
	else persPrivNoticeDetailForm.strutsAction.value = '<%= PersPrivNoticeDetailAction.DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/persPrivNoticeDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(persPrivNoticeDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
  //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
  //=====================================
    if (parent.findGridRow)	parent.findGridRow(persPrivNoticeDetailForm.elements['persPrivNoticeDetailDTO.alarmEmpSetId'].value);
    setTitle("persPrivNoticeDetailDTO.alarmCode", "persPrivNoticeDetailDTO.alarmName");
    
    getTopPage().afterSaveAll(currentPageId);
}
</script>
</head>

<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/persPrivNoticeDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="persPrivNoticeDetailDTO.alarmEmpSetId"/> <!-- Key -->
<html:hidden property="persPrivNoticeDetailDTO.isNotice"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
        	<!-- 알림코드 -->
        	<div class="field">
            	<label class="check"><bean:message key="LABEL.alarmCode"/></label>
                <div class="input_read">
                	<html:text property="persPrivNoticeDetailDTO.alarmCode" tabindex="10" readonly="true"/>
                </div>
            </div>
            <!-- 알림명 -->
            <div class="field_long">
                <label class="check"><bean:message key="LABEL.alarmName"/></label>
                <div class="input_read">
                    <html:text property="persPrivNoticeDetailDTO.alarmName" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 알림여부 -->
            <div class="field">
                <label><bean:message key="LABEL.alarmViewYn"/></label>
                <div class="input_box">
                    <html:text property="persPrivNoticeDetailDTO.isNoticeDesc" tabindex="30"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>