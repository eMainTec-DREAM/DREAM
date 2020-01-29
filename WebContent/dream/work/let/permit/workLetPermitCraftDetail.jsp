<%--===========================================================================
안전작업허가서유형 - 작업자 상세
author  syyang
version $Id: maWoResultBmRplCraftDetail.jsp,v 1.0 2015/12/04 07:26:18 syyang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.let.permit.action.WorkLetPermitCraftDetailAction"%>
<html>
<head>
<!-- 작업자-->
<title><bean:message key="LABEL.order"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변수  */
var mainMngAc;
function loadPage() 
{
	setTitle("workLetPermitCraftDetailDTO.stepNum","workLetPermitCraftDetailDTO.workName");
	setForUpdate();
	
	$("input[name='workLetPermitCraftDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='workLetPermitCraftDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });

    // 내부/외부
    acSysDesc("workLetPermitCraftDetailDTO.craftTypeDesc","workLetPermitCraftDetailDTO.craftType","CRAFT_TYPE",true);
    
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

function goInput()
{
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOLETLISTCRAFT_ID');
	
	//날짜,시간값이 비어있으면 작업상세의 값을 넣는다.
	var dateTimes = parent.getDateTime();
	dateTimes = dateTimes.split(",");
	if(workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startDate'].value=='') 
		workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startDate'].value = dateTimes[0];
	if(workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startTime'].value=='') 
		workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startTime'].value = dateTimes[1];
	if(workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endDate'].value=='') 
		workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endDate'].value = dateTimes[2];
	if(workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endTime'].value=='') 
		workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endTime'].value = dateTimes[3];
	
	setWorkTime();
}

function setSequenceVal(sequenceVal)
{
	workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.woLetListCraftId'].value = sequenceVal;
}

function goUpdate(){
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(M$('workLetPermitCraftDetailDTO.workTime').value == '') setWorkTime();
	
	if(ckCreate(currentPageId)) {
		workLetPermitCraftDetailForm.strutsAction.value = '<%=WorkLetPermitCraftDetailAction.WO_LET_PERMIT_CRAFT_DETAIL_INPUT%>';
	}
	else {
		workLetPermitCraftDetailForm.strutsAction.value = '<%= WorkLetPermitCraftDetailAction.WO_LET_PERMIT_CRAFT_DETAIL_UPDATE %>';
	}
	
	var actionUrl = contextPath + "/workLetPermitCraftDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workLetPermitCraftDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.woLetListCraftId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function setWorkTime()
{
	var startDate = workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startDate'].value;
 	var startTime = workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startTime'].value;
 	var endDate = workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endDate'].value;
 	var endTime = workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endTime'].value;
 	
 	workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.workTime'].value = setNumberFormat(getMinInterval(workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startDate'], workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.startTime'], workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endDate'],workLetPermitCraftDetailForm.elements['workLetPermitCraftDetailDTO.endTime']));
 }

/**
 * Time 세팅되고 호출되는 Callback
 */
function afterSetTime(_name)
{
	setWorkTime();
}

/**
 * Date 세팅되고 호출되는 Callback
 */
function afterSetDate(_name)
{
	setWorkTime();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workLetPermitCraftDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="workLetPermitDetailDTO.woLetListId"/>
	<html:hidden property="workLetPermitCraftListDTO.woLetListCraftId"/>
	<html:hidden property="workLetPermitCraftDetailDTO.woLetListCraftId"/>
	<html:hidden property="workLetPermitCraftDetailDTO.craftType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 순서 -->
			<div class="field">
				<label><bean:message key="LABEL.order"/></label>
				<div class="input_box">
					<html:text property="workLetPermitCraftDetailDTO.stepNum" tabindex="10" styleClass="num"/>
				</div>
			</div>		
			<!-- 내부/외부 -->				
			<div class="field">
				<label><bean:message key="LABEL.craftType" /></label>
				<div class="input_box">
					<html:text property="workLetPermitCraftDetailDTO.craftTypeDesc" tabindex="20" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>				
				</div>
			</div>
			<!-- 작업자명 -->
			<div class="field">
				<label><bean:message key="LABEL.woCraftDesc"/></label>
				<div class="input_box">
					<html:text property="workLetPermitCraftDetailDTO.workName" tabindex="30"/>
				</div>
			</div>
			
			<!-- 소요시간 -->
			<div class="field">
				<label><bean:message key="LABEL.woTimeMin"/></label>
				<div class="input_box">
					<html:text property="workLetPermitCraftDetailDTO.workTime" tabindex="40" styleClass="num"/>
				</div>
			</div>			
			<!-- 작업시작시간 -->
			<div class="field">
				<label><bean:message key="LABEL.woFromTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workLetPermitCraftDetailDTO.startDate" tabindex="50" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workLetPermitCraftDetailDTO.startTime"  tabindex="60"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업종료시간 -->
			<div class="field">
				<label><bean:message key="LABEL.woToTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workLetPermitCraftDetailDTO.endDate" tabindex="70" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workLetPermitCraftDetailDTO.endTime" tabindex="80" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>			
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workLetPermitCraftDetailDTO.remark" styleClass="ta50" tabindex="100" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>