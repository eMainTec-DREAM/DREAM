<%--===========================================================================
연간작업일정 - 상세
author  kim21017
version $Id: workCalPmDInsMonthDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.cal.pmdinsmonth.action.WorkCalPmDInsMonthDetailAction"%>
<html:html>
<head>
<!-- PM번호 -->
<title><bean:message key='LABEL.pmNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action

function loadPage()
{
	goUpdate();
	
	setTitle("workCalPmDInsMonthDetailDTO.pmNo","workCalPmDInsMonthDetailDTO.pmDesc");
	
	//For Save
	setForUpdate();
}


function goUpdate()
{
    //수정시 readonly설정 
    //maPttRtnDetailForm.elements['maPttRtnDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  
    // 버튼 활성화 
	
    // 입력 Form disable
	setEnable($(".form_box"));
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

	if(workCalPmDInsMonthDetailForm.elements['workCalPmDInsMonthDetailDTO.pmSchedStatus'].value!="P"){
		alertMessage1('<bean:message key="LABEL.MSG0016"/>');
	}
	//strutsAction 셋팅.
	workCalPmDInsMonthDetailForm.strutsAction.value = "<%=WorkCalPmDInsMonthDetailAction.MONTH_SCHED_DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/workCalPmDInsMonthDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workCalPmDInsMonthDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    workCalPmDInsMonthDetailForm.elements['workCalPmDInsMonthDetailDTO.pmInsDSchedId'].value = workCalPmDInsMonthDetailForm.elements['workCalPmDInsMonthCommonDTO.pmInsDSchedId'].value;
    parent.goSearch();
    parent.searchList();

    getTopPage().afterSaveAll(currentPageId);
}

function goOpen(){
	if(workCalPmDInsMonthDetailForm.elements['workCalPmDInsMonthDetailDTO.pmSchedStatus'].value!="P"){
		var url   = contextPath + "/maWoResultMstrDetail.do";

		var popWidth = 1010;
		var popHeight = 640;

	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=no";

	    var param = "?" + "strutsAction=1001&maWoResultMstrCommonDTO.wkOrId="+
	    		workCalPmDInsMonthDetailForm.elements['workCalPmDInsMonthDetailDTO.wkorId'].value;

	    window.open(url + param, "WO_DETAIL", pos);
	}else{
		alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
	}
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = workCalPmDInsMonthDetailForm.elements['workCalPmDInsMonthDetailDTO.param'].value;
	var pmId     = workCalPmDInsMonthDetailForm.elements['workCalPmDInsMonthDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workCalPmDInsMonthDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workCalPmDInsMonthCommonDTO.pmInsDSchedId" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.pmInsDSchedId" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.pmSchedStatus" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.pmType" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.scheduleType" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.periodType" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.wkorId" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.param" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.pmId" />
	<html:hidden property="workCalPmDInsMonthDetailDTO.pmInsDListId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label><bean:message key="LABEL.planSchedule"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.planDate" readonly="true"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.schedChange"/></label>
             	 	<div class="input_box">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.schedDate" tabindex="10"/>
						<p class="open_calendar"><span>날짜</span></p>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.pmNo"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.pmNo" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.equipment"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.equipDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.pmDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.pmDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.scheduleTypeDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.scheduleTypeDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.dept"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.deptDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.cycleDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.cycle" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.usage"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.usage" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.periodType"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.periodTypeDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.pmType"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.pmTypeDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.remark"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmDInsMonthDetailDTO.remark" readonly="true" />
             	 	</div>
             	 </div>
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
