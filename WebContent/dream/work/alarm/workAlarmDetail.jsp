<%--===========================================================================
Alarm List - 상세 
author  nhkim8548
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.alarm.action.WorkAlarmAction" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key='TAB.workAlarmDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

/** 자동완성 변수  */

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate(); 
	
	setTitle("workAlarmDTO.alarmStartTime", "workAlarmDTO.alarmMcDesc");
	
	setForUpdate();
}
	
function goUpdate()
{

}
 
/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
	var form = document.workAlarmForm;

	goCommonTabPage(form, '' , pageId);
}

/**
 * 저장
 */ 
function goSave()
{
    //================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
    
    //strutsAction 셋팅.
    workAlarmForm.strutsAction.value = "<%=WorkAlarmAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/workAlarmDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workAlarmForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workAlarmForm.elements['workAlarmDTO.alarmListId'].value);

    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("workAlarmDTO.alarmStartTime", "workAlarmDTO.alarmMcDesc");
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/workAlarmDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workAlarmDTO.alarmListId" /><!-- key -->
<html:hidden property="workAlarmDTO.equipId" />
<html:hidden property="workAlarmDTO.equipDesc" />
<html:hidden property="workAlarmDTO.eqLocId" />
<html:hidden property="workAlarmDTO.eqLocDesc" />
<div class="article_box">
	<div class="form_box">
		<!-- 발생시간 -->
		<div class="field">
			<label>발생시간</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmStartTime" readonly="true" tabindex="10"/>
			</div>
		</div>
		<!-- 종료시간 -->
		<div class="field">
			<label>종료시간</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmEndTime" readonly="true" tabindex="20"/>
			</div>
		</div>
		<!-- Alarm 설비코드 -->
		<div class="field">
			<label>Alarm 설비코드</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmMcCode" readonly="true" tabindex="30"/>
			</div>
		</div>
		<!-- Alarm 설비명 -->
		<div class="field">
			<label>Alarm 설비명</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmMcDesc" readonly="true" tabindex="40"/>
			</div>
		</div>
		<!-- Alarm 포인트 -->
		<div class="field">
			<label>Alarm 포인트</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmPoint" readonly="true" tabindex="50"/>
			</div>
		</div>
		<!-- Alarm 유형 -->
		<div class="field">
			<label>Alarm 유형</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmType" readonly="true" tabindex="60"/>
			</div>
		</div>
		<!-- Alarm 코드 -->
		<div class="field">
			<label>Alarm 코드</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmCode" readonly="true" tabindex="70"/>
			</div>
		</div>
		<!-- Alarm 등급 -->
		<div class="field">
			<label>Alarm 등급</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmGrade" readonly="true" tabindex="80"/>
			</div>
		</div>
		<!-- Alarm 명 -->
		<div class="field_long">
			<label>Alarm 명</label>
			<div class="input_read">
				<html:text property="workAlarmDTO.alarmName" readonly="true" tabindex="90"/>
			</div>
		</div>
		<!-- Alarm 내용 -->
		<div class="field_long">
			<label>Alarm 내용</label>
			<div class="input_read">
				<html:textarea property="workAlarmDTO.alarmDesc" styleClass="ta50" readonly="true" tabindex="100"/>
			</div>
		</div>
		<!-- 비고 -->
		<div class="field_long">
			<label>비고</label>
			<div class="input_box">
				<html:textarea property="workAlarmDTO.remark" styleClass="ta50" tabindex="110" />
			</div>
		</div>
	</div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>