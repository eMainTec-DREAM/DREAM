<%--===========================================================================
라인가동계획 - 상세
author kim21017
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.loc.run.action.MaLineRunPlanAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 라인가동계획 -->
<title><bean:message key='MENU.LNRUNPLAN' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//
var plantTypeAc;
var eqLocDescAc;
var lnWrkAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    setTitle("maLineRunPlanDTO.wrkDate", "maLineRunPlanDTO.lnWrkListDesc");
    setForUpdate();
    
    //가동달력 자동완성
    lnWrkAc = new autoC({"maLineRunPlanDTO.lnWrkListDesc":"description"});
    lnWrkAc.setTable("TALNWRKLIST");
    lnWrkAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
    })
    lnWrkAc.setAcResultMap({
        "maLineRunPlanDTO.lnWrkListId":"lnWrkListId"
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
	maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = sequenceVal;
}


/**
 * 공장 라인 일자 중복체크
 */
function valLineRunPlan()
{
    isValid = 0;
    var actionUrl = contextPath + "/maLineRunPlanDetail.do";
    var param =  "&strutsAction=" + '<%= MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(maLineRunPlanForm);
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
        maLineRunPlanForm.elements['maLineRunPlanDTO.wrkDate'].value = '';
        maLineRunPlanForm.elements['maLineRunPlanDTO.wrkDate'].focus();
        closeModal();
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0046' />");
    }else{
    	maLineRunPlanForm.strutsAction.value = "<%=MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_INPUT%>";
    	var actionUrl = contextPath + "/maLineRunPlanDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(maLineRunPlanForm), 'afterSave');
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
    	maLineRunPlanForm.strutsAction.value = '<%=MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_UPDATE%>';
    	var actionUrl = contextPath + "/maLineRunPlanDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(maLineRunPlanForm), 'afterSave');
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
     if (parent.findGridRow) parent.findGridRow(maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value);
     getTopPage().afterSaveAll(currentPageId);
     
     setTitle("maLineRunPlanDTO.wrkDate", "maLineRunPlanDTO.lnWrkListDesc");
     goUpdate();
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/maLineRunPlanDetail" >
    <html:hidden property="strutsAction"/>
    <html:hidden property="currentPageId"/>
    <html:hidden property="maLineRunPlanDTO.lnWrkTimeId" />
    <html:hidden property="maLineRunPlanDTO.lnWrkListId" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        
            <!-- 가동달력명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="maLineRunPlanDTO.lnWrkListDesc" tabindex="10"/>
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
					<html:text property="maLineRunPlanDTO.wrkDate" tabindex="10" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			
			<!-- 1교대 가동시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.dayRunTime"/></label>
				<div class="input_box">
					<html:text property="maLineRunPlanDTO.dayRunTime" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 2교대 가동시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.nightRunTime"/></label>
				<div class="input_box">
					<html:text property="maLineRunPlanDTO.nightRunTime" tabindex="30" styleClass="num"/>
				</div>
			</div>
			<!-- 3교대 가동시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.extraRunTime"/></label>
				<div class="input_box">
					<html:text property="maLineRunPlanDTO.evenRunTime" tabindex="40" styleClass="num"/>
				</div>
			</div>
			<!-- 사용횟수 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.ucnt"/></label>
				<div class="input_box">
					<html:text property="maLineRunPlanDTO.ucnt" tabindex="45" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maLineRunPlanDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
			
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>