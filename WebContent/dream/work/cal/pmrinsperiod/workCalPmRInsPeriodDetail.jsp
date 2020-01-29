<%--===========================================================================
예방작업일정(기간) - 상세
author  kim21017
version $Id: workCalPmRInsPeriodDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.cal.pmrinsperiod.action.WorkCalPmRInsPeriodDetailAction"%>
<html:html>
<head>
<!-- PM번호 -->
<title><bean:message key='LABEL.pmNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action

/** 자동완성 변수  */
var pmAc;
function loadPage()
{
	if(ckCreate(currentPageId)) goInput();
	else
	{
		goUpdate();
	}

	setTitle("workCalPmRInsPeriodDetailDTO.pmNo","workCalPmRInsPeriodDetailDTO.pmDesc");
	//For Save
	setForUpdate();
	
	pmAc = new autoC({"workCalPmRInsPeriodDetailDTO.pmNo":"pmNo"});
    pmAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    pmAc.setTable("TAPMLST");
    pmAc.setAcResultMap({
        "workCalPmRInsPeriodDetailDTO.pmId":"pmId"
        ,"workCalPmRInsPeriodDetailDTO.pmDesc":"description"
        ,"workCalPmRInsPeriodDetailDTO.equipDesc":"equipDesc"
        ,"workCalPmRInsPeriodDetailDTO.scheduleType":"scheduleType"
        ,"workCalPmRInsPeriodDetailDTO.deptDesc":"deptDesc"
        ,"workCalPmRInsPeriodDetailDTO.cycle":"cycle"
        ,"workCalPmRInsPeriodDetailDTO.usage":"USAGE"
        ,"workCalPmRInsPeriodDetailDTO.periodType":"periodType"
        ,"workCalPmRInsPeriodDetailDTO.pmType":"pmType"
        ,"workCalPmRInsPeriodDetailDTO.remark":"remark"
    });
    pmAc.setKeyName("workCalPmRInsPeriodDetailDTO.pmId"); 
    pmAc.init();
}

function goUpdate()
{
    //수정시 readonly설정
    //maPttRtnDetailForm.elements['maPttRtnDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";
    // 버튼 활성화

	    // 입력 Form disable
        setEnable($(".form_box"));
	$('#pmPopup').hide();
}

function goInput()
{
	workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.schedDate'].value = getToday();
	/* sequenceNextVal('SQAPTRTNLIST_ID');

	maPttRtnDetailForm.elements['maPttRtnDetailDTO.rtnDate'].value = getToday();
	maPttRtnDetailForm.elements['maPttRtnDetailDTO.rtnDept'].value = loginUser.deptId;
    maPttRtnDetailForm.elements['maPttRtnDetailDTO.rtnDeptDesc'].value = loginUser.deptDesc;

    maPttRtnDetailForm.elements['maPttRtnDetailDTO.wcodeId'].value = loginUser.twcodeId;
    maPttRtnDetailForm.elements['maPttRtnDetailDTO.wname'].value = loginUser.twname;
	//maPttRtnDetailForm.elements['maPttRtnDetailDTO.recQty'].value = "0";
	//maPttRtnDetailForm.elements['maPttRtnDetailDTO.unitPrice'].value = "0";
	//maPttRtnDetailForm.elements['maPttRtnDetailDTO.totPrice'].value = "0";

	// 출고상태 : W=작성중
	maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnStatus'].value = "W";
	valSysDirCode('maPttRtnDetailDTO.ptRtnStatus', 'maPttRtnDetailDTO.ptRtnStatusDesc', 'PTRTN_STATUS', true); */

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

	if(workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.pmSchedStatus'].value=="C"&&!ckCreate(currentPageId)){
		//완료된 작업은 수정할 수 없습니다.
		alertMessage1('<bean:message key="MESSAGE.MSG0016"/>');
		closeModal();
		return;
	}
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) workCalPmRInsPeriodDetailForm.strutsAction.value = '<%=WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_INPUT%>';
	else workCalPmRInsPeriodDetailForm.strutsAction.value = '<%=WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_UPDATE%>';

	var actionUrl = contextPath + "/workCalPmRInsPeriodDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workCalPmRInsPeriodDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.pmInsSchedId'].value = workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value;
    parent.goSearch();

    getTopPage().afterSaveAll(currentPageId);
}

function goOpen(){
	if(workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.pmSchedStatus'].value!="P"){
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
	    		workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.wkorId'].value;

	    window.open(url + param, "WO_DETAIL", pos);
	}else{
		alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
	}
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.pmInsSchedId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.param'].value;
	var pmId     = workCalPmRInsPeriodDetailForm.elements['workCalPmRInsPeriodDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workCalPmRInsPeriodDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workCalPmRInsPeriodCommonDTO.pmInsSchedId" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.pmInsSchedId" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.pmSchedStatus" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.wkorId" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.pmId" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.pmInsListId" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.param" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.pmType" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.scheduleType" />
	<html:hidden property="workCalPmRInsPeriodDetailDTO.periodType" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label><bean:message key="LABEL.planSchedule"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.planDate" readonly="true"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.schedChange"/></label>
             	 	<div class="input_box">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.schedDate" tabindex="10"/>
						<p class="open_calendar"><span>날짜</span></p>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.pmNo"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.pmNo" readonly="true" />
             	 		<p class="open_spop">
                        <a id="pmPopup">
                            <span>조회</span>
                        </a>
                    </p>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.equipment"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.equipDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.pmDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.pmDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.scheduleTypeDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.scheduleTypeDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.dept"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.deptDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.manager"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.empDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.cycleDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.cycle" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.usage"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.usage" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.periodType"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.periodTypeDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.pmType"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.pmTypeDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.measureTime"/></label>
             	 	<div class="input_read">
             	 		<html:text property="workCalPmRInsPeriodDetailDTO.measureTime" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.remark"/></label>
             	 	<div class="input_box">
             	 		<html:textarea property="workCalPmRInsPeriodDetailDTO.remark" styleClass="ta50" />
             	 	</div>
             	 </div>
             	 <c:set var="filePath" value="enhance/${compName}/work/cal/pmrinsperiod/workCalPmRInsPeriodDetail_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/cal/pmrinsperiod/workCalPmRInsPeriodDetail_${compNo}.jsp"></c:import>
				</c:if>
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
