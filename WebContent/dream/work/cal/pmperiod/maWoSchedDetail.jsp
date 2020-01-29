<%--===========================================================================
예방작업일정(기간) - 상세
author  kim21017
version $Id: maWoSchedDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.cal.pmperiod.action.MaWoSchedDetailAction"%>
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
	
	setTitle("maWoSchedDetailDTO.pmNo","maWoSchedDetailDTO.pmDesc");
	//For Save
	setForUpdate();

	pmAc = new autoC({"maWoSchedDetailDTO.pmNo":"pmNo"});
    pmAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    pmAc.setTable("TAPMLST");
    pmAc.setAcResultMap({
        "maWoSchedDetailDTO.pmId":"pmId"
        ,"maWoSchedDetailDTO.pmDesc":"description"
        ,"maWoSchedDetailDTO.equipDesc":"equipDesc"
        ,"maWoSchedDetailDTO.scheduleType":"scheduleType"
        ,"maWoSchedDetailDTO.deptDesc":"deptDesc"
        ,"maWoSchedDetailDTO.cycle":"cycle"
        ,"maWoSchedDetailDTO.usage":"USAGE"
        ,"maWoSchedDetailDTO.periodType":"periodType"
        ,"maWoSchedDetailDTO.pmType":"pmType"
        ,"maWoSchedDetailDTO.remark":"remark"
    });
    pmAc.setKeyName("maWoSchedDetailDTO.pmId"); 
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
	maWoSchedDetailForm.elements['maWoSchedDetailDTO.schedDate'].value = getToday(); 
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

	if(maWoSchedDetailForm.elements['maWoSchedDetailDTO.pmSchedStatus'].value!="P"&&!ckCreate(currentPageId)){
		alertMessage1('<bean:message key="MESSAGE.MSG0016"/>');
		return;
	}
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maWoSchedDetailForm.strutsAction.value = '<%=MaWoSchedDetailAction.MONTH_SCHED_DETAIL_INPUT%>';
	else maWoSchedDetailForm.strutsAction.value = '<%=MaWoSchedDetailAction.MONTH_SCHED_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maWoSchedDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoSchedDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maWoSchedDetailForm.elements['maWoSchedDetailDTO.pmSchedId'].value = maWoSchedDetailForm.elements['maWoSchedCommonDTO.pmSchedId'].value;
    parent.goSearch();

    getTopPage().afterSaveAll(currentPageId);
}

function goOpen(){
	if(maWoSchedDetailForm.elements['maWoSchedDetailDTO.pmSchedStatus'].value!="P"){
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
	    		maWoSchedDetailForm.elements['maWoSchedDetailDTO.wkorId'].value;
	  
	    window.open(url + param, "WO_DETAIL", pos);
	}else{
		alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
	}
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maWoSchedDetailForm.elements['maWoSchedDetailDTO.pmSchedId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = maWoSchedDetailForm.elements['maWoSchedDetailDTO.param'].value;
	var pmId     = maWoSchedDetailForm.elements['maWoSchedDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maWoSchedDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoSchedCommonDTO.pmSchedId" />
	<html:hidden property="maWoSchedDetailDTO.pmSchedId" />
	<html:hidden property="maWoSchedDetailDTO.pmSchedStatus" />
	<html:hidden property="maWoSchedDetailDTO.wkorId" />
	<html:hidden property="maWoSchedDetailDTO.pmId" />
	<html:hidden property="maWoSchedDetailDTO.param" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label><bean:message key="LABEL.planSchedule"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.planDate" readonly="true"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.schedChange"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maWoSchedDetailDTO.schedDate" tabindex="10"/>
						<p class="open_calendar"><span>날짜</span></p>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.pmNo"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.pmNo" readonly="true" />
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
             	 		<html:text property="maWoSchedDetailDTO.equipDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.pmDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.pmDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.scheduleTypeDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.scheduleType" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.dept"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.deptDesc" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.cycleDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.cycle" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.usage"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.usage" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.periodType"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.periodType" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.pmType"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maWoSchedDetailDTO.pmType" readonly="true" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.remark"/></label>
             	 	<div class="input_box">
             	 		<html:textarea property="maWoSchedDetailDTO.remark" styleClass="ta50" />
             	 	</div>
             	 </div>
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
