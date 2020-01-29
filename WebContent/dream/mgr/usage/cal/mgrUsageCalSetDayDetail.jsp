<%--===========================================================================
사용달력일별횟수설정 - 상세
author youngjoo38
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usage.cal.action.MgrUsageCalSetDayAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 사용달력일별횟수설정 -->
<title><bean:message key='MENU.LNRUNPLAN' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var lnWrkAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    setTitle("mgrUsageCalSetDayDTO.wrkDate", "mgrUsageCalSetDayDTO.lnWrkListDesc");
    setForUpdate();
    
    //사용달력 자동완성
    lnWrkAc = new autoC({"mgrUsageCalSetDayDTO.lnWrkListDesc":"description"});
    lnWrkAc.setTable("TAUSAGEWRKLIST");
    lnWrkAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
    })
    lnWrkAc.setAcResultMap({
        "mgrUsageCalSetDayDTO.lnWrkListId":"lnWrkListId"
    });
    lnWrkAc.init();
    
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQALNWRKTIME_ID');
}

/**
 * 수정
 */
function goUpdate()
{
}

function setSequenceVal(sequenceVal)
{
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = sequenceVal;
}


/**
 * 공장 라인 일자 중복체크
 */
function valLineRunPlan()
{
    isValid = 0;
    var actionUrl = contextPath + "/mgrUsageCalSetDayDetail.do";
    var param =  "&strutsAction=" + '<%= MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(mgrUsageCalSetDayForm);
    XMLHttpPostVal(actionUrl, param, 'setValidLineRunPlan');
}

/**
 * valLineRunPlanNo()실행 후 호출
 */
var isValid = 0;
function setValidLineRunPlan(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.wrkDate'].value = '';
        mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.wrkDate'].focus();
        closeModal();
        // 일자가 중복되었습니다..
        alertMessage1("<bean:message key='MESSAGE.MSG0230' />");
    }else{
    	mgrUsageCalSetDayForm.strutsAction.value = "<%=MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_INPUT%>";
    	var actionUrl = contextPath + "/mgrUsageCalSetDayDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(mgrUsageCalSetDayForm), 'afterSave');
    }
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
    if(ckCreate(currentPageId)){
    	valLineRunPlan();
    }else{
    	mgrUsageCalSetDayForm.strutsAction.value = '<%=MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_UPDATE%>';
    	var actionUrl = contextPath + "/mgrUsageCalSetDayDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(mgrUsageCalSetDayForm), 'afterSave');
    }
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     if (parent.findGridRow) parent.findGridRow(mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value);
     getTopPage().afterSaveAll(currentPageId);
     
     setTitle("mgrUsageCalSetDayDTO.wrkDate", "mgrUsageCalSetDayDTO.lnWrkListDesc");
     goUpdate();
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
    <html:form action="/mgrUsageCalSetDayDetail" >
    <html:hidden property="strutsAction"/>
    <html:hidden property="currentPageId"/>
    <html:hidden property="mgrUsageCalSetDayDTO.lnWrkTimeId" />
    <html:hidden property="mgrUsageCalSetDayDTO.lnWrkListId" />
    <div class="article_box" id="detailBox">
        <div class="form_box">
            <!-- 가동달력명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDayDTO.lnWrkListDesc" tabindex="10"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			
            <div class="field">
				<!-- 일자 -->
				<label class="check"><bean:message key="LABEL.workDate"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDayDTO.wrkDate" tabindex="10" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 사용횟수 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.ucnt"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDayDTO.ucnt" tabindex="45" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="mgrUsageCalSetDayDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
			
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>