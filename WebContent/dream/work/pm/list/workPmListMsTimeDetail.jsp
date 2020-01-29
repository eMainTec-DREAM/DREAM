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
<%@page import="dream.work.pm.list.action.WorkPmListMsTimeDetailAction"%>
<html>
<head>
<!-- 작업시간 -->
<title><bean:message key="TAB.measureTime"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var msTimeAc;

var isValid;

function loadPage() 
{
	setTitle("workPmListMsTimeDetailDTO.measureTime");
	setForUpdate();
	
	/** 작업시간 AC */
	msTimeAc = new autoC({"workPmListMsTimeDetailDTO.measureTime":"measureTime"});
	msTimeAc.setAcConditionMap({
    	"is_use":"Y"
  	   });
    msTimeAc.setTable("TAMEASURETIME");
    msTimeAc.setKeyName("workPmListMsTimeDetailDTO.measureTimeId");
    msTimeAc.setAcResultMap({
        "workPmListMsTimeDetailDTO.measureTimeId":"measureTimeId"
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
	workPmListMsTimeDetailForm.elements['workPmListMsTimeDetailDTO.pmMsTimeId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;
	
	// 시간 중복 체크
	if(ckCreate(currentPageId)){
		validTime();
		if(isValid!='0') return;
	}
	
	if(ckCreate(currentPageId)) workPmListMsTimeDetailForm.strutsAction.value = '<%=WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_INPUT%>';
	else workPmListMsTimeDetailForm.strutsAction.value = '<%= WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workPmListMsTimeDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmListMsTimeDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(workPmListMsTimeDetailForm.elements['workPmListMsTimeDetailDTO.pmMsTimeId'].value);
	if(parent.parent.parent.findGridRow) parent.parent.parent.findGridRow(workPmListMsTimeDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
	setTitle("workPmListMsTimeDetailDTO.measureTime");
    getTopPage().afterSaveAll(currentPageId);
}


function validTime(){
	var actionUrl = contextPath + "/workPmListMsTimeDetail.do";
	var param =  "&strutsAction=" + '<%= WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_CHECK %>'
	          +  "&" + FormQueryString(workPmListMsTimeDetailForm);
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
		workPmListMsTimeDetailForm.elements['workPmListMsTimeDetailDTO.measureTimeId'].value = '';
		workPmListMsTimeDetailForm.elements['workPmListMsTimeDetailDTO.measureTime'].value = '';
    }
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = workPmListMsTimeDetailForm.elements['workPmListMsTimeDetailDTO.pmMsTimeId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmListMsTimeDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workPmListMsTimeListDTO.pmMsTimeId"/>
	<html:hidden property="workPmListMsTimeDetailDTO.pmMsTimeId"/>
	<html:hidden property="workPmListMsTimeDetailDTO.measureTimeId"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 작업시간 -->
            <div class="field">
                <label><bean:message key="LABEL.msPlanTime"/></label>
                <div class="input_box">
                    <html:text property="workPmListMsTimeDetailDTO.measureTime" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
		
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workPmListMsTimeDetailDTO.remark" styleClass="ta50" tabindex="100" />
				</div>
			</div>
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>