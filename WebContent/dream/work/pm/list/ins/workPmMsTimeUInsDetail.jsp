<%--===========================================================================
작업시간 Detail
author  js.lee
version $Id: Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.WorkPmMsTimeUInsDetailAction"%>
<html>
<head>
<!-- 작업시간 -->
<title><bean:message key="PAGE.workPmMsTimeUInsDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var msTimeAc;

var isValid;

function loadPage() 
{
	setTitle("workPmMsTimeUInsDetailDTO.measureTime");
	setForUpdate();
	
	/** 작업시간 AC */
	msTimeAc = new autoC({"workPmMsTimeUInsDetailDTO.measureTime":"measureTime"});
	msTimeAc.setAcConditionMap({
    	"is_use":"Y"
  	   });
    msTimeAc.setTable("TAMEASURETIME");
    msTimeAc.setKeyName("workPmMsTimeUInsDetailDTO.measureTimeId");
    msTimeAc.setAcResultMap({
        "workPmMsTimeUInsDetailDTO.measureTimeId":"measureTimeId"
    });
    msTimeAc.init();

    if(ckCreate(currentPageId)) goInput();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPMMEASURETIME_ID');
	
}

function setSequenceVal(sequenceVal)
{
	workPmMsTimeUInsDetailForm.elements['workPmMsTimeUInsDetailDTO.pmMsTimeId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;
	
	var oldMsTime = workPmMsTimeUInsDetailForm.elements['workPmMsTimeUInsDetailDTO.oldMeasureTime'].value;
	var msTime = workPmMsTimeUInsDetailForm.elements['workPmMsTimeUInsDetailDTO.measureTime'].value;
	
	// 시간 중복 체크
	if(ckCreate(currentPageId) || oldMsTime != msTime){
		validTime();
		if(isValid!='0') return;
	}
	
	if(ckCreate(currentPageId)) workPmMsTimeUInsDetailForm.strutsAction.value = '<%=WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_INPUT%>';
	else workPmMsTimeUInsDetailForm.strutsAction.value = '<%= WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workPmMsTimeUInsDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmMsTimeUInsDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(workPmMsTimeUInsDetailForm.elements['workPmMsTimeUInsDetailDTO.pmMsTimeId'].value);
	if(parent.parent.parent.findGridRow) parent.parent.parent.findGridRow(workPmMsTimeUInsDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
	setTitle("workPmMsTimeUInsDetailDTO.measureTime");
    getTopPage().afterSaveAll(currentPageId);
}


function validTime(){
	var actionUrl = contextPath + "/workPmMsTimeUInsDetail.do";
	var param =  "&strutsAction=" + '<%= WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_CHECK %>'
	          +  "&" + FormQueryString(workPmMsTimeUInsDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidTime');
}

function afterValidTime(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0233'/>");
		workPmMsTimeUInsDetailForm.elements['workPmMsTimeUInsDetailDTO.measureTimeId'].value = '';
		workPmMsTimeUInsDetailForm.elements['workPmMsTimeUInsDetailDTO.measureTime'].value = '';
    }
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = workPmMsTimeUInsDetailForm.elements['workPmMsTimeUInsDetailDTO.pmMsTimeId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmMsTimeUInsDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workPmMsTimeUInsListDTO.pmMsTimeId"/>
	<html:hidden property="workPmMsTimeUInsDetailDTO.pmMsTimeId"/>
	<html:hidden property="workPmMsTimeUInsDetailDTO.measureTimeId"/>
	<html:hidden property="workPmMsTimeUInsDetailDTO.oldMeasureTime"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 계획시간 -->
            <div class="field">
                <label>계획시간</label>
                <div class="input_box">
                    <html:text property="workPmMsTimeUInsDetailDTO.measureTime" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
		
			<!-- 비고 -->
			<div class="field_long">
				<label>비고</label>
				<div class="input_box">
					<html:textarea property="workPmMsTimeUInsDetailDTO.remark" styleClass="ta50" tabindex="100" />
				</div>
			</div>
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>