<%--===========================================================================
예산관리 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.budget.plan.list.action.MaPtBudgetDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 예산관리 -->
<title><bean:message key='LABEL.accntNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var accountAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maPtBudgetDetailDTO.accountNo", "maPtBudgetDetailDTO.accountDesc");
    
    setForUpdate();
    
    accountAc = new autoC({"maPtBudgetDetailDTO.accountDesc":"description"});
    accountAc.setAcDisplay("DESCRIPTION");
    accountAc.setAcConditionMap({
        	"list_type":"ACCNT_NO",
        	"is_use":"Y"
  	   });
    accountAc.setTable("TACDSYSD");
    accountAc.setKeyName("maPtBudgetDetailDTO.accountNo");
    accountAc.setAcResultMap({
        "maPtBudgetDetailDTO.accountNo":"cdsysd_no"
    });
    accountAc.init();
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQABGTPLAN_ID');
	
	maPtBudgetDetailForm.elements['maPtBudgetDetailDTO.updById'].value    = loginUser.userId;
	maPtBudgetDetailForm.elements['maPtBudgetDetailDTO.updBy'].value      = loginUser.userName;
	maPtBudgetDetailForm.elements['maPtBudgetDetailDTO.updDate'].value    = getToday();
}

/**
 * 수정
 */
function goUpdate()
{
	goTabPage("maPtDeptBgList");
}

function setSequenceVal(sequenceVal)
{
	maPtBudgetDetailForm.elements['maPtBudgetDetailDTO.bgtPlanId'].value = sequenceVal;
    maPtBudgetDetailForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = sequenceVal;

	goTabPage("maPtDeptBgList");
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId)
{
    var form = document.maPtBudgetDetailForm;
    
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
    if(ckCreate(currentPageId)) maPtBudgetDetailForm.strutsAction.value = "<%=MaPtBudgetDetailAction.PTBUDGET_DETAIL_INPUT%>";
    else maPtBudgetDetailForm.strutsAction.value = '<%=MaPtBudgetDetailAction.PTBUDGET_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maPtBudgetDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPtBudgetDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maPtBudgetDetailForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = maPtBudgetDetailForm.elements['maPtBudgetDetailDTO.bgtPlanId'].value;
     if (parent.findGridRow)	parent.findGridRow(maPtBudgetDetailForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value);
     getTopPage().afterSaveAll(currentPageId);
     
     goUpdate();
     setTitle("maPtBudgetDetailDTO.accountNo", "maPtBudgetDetailDTO.accountDesc");
     
 }

function setTotalAmt(planAmtSum)
{
	maPtBudgetDetailForm.elements['maPtBudgetDetailDTO.assignAmt'].value = setNumberFormat(planAmtSum);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtBudgetDetailForm.elements['maPtBudgetDetailDTO.bgtPlanId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtBudgetDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtBudgetCommonDTO.compNo" />
	<html:hidden property="maPtBudgetCommonDTO.bgtPlanId" />
    <html:hidden property="maPtBudgetDetailDTO.bgtPlanId" />
    <html:hidden property="maPtBudgetDetailDTO.accountNo" />
    <html:hidden property="maPtBudgetDetailDTO.updById" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.yyyymm"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtBudgetDetailDTO.yyyymm" readonly="true" tabindex="10"/>
				    <p class="open_mon_calendar"><span>날짜</span></p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.accountNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtBudgetDetailDTO.accountDesc" tabindex="20"
                        onblur="valSysDir('maPtBudgetDetailDTO.accountNo', 'maPtBudgetDetailDTO.accountDesc', 'ACCNT_NO', true);"/>
                    <p class="open_spop">
                        <a href="javascript:lovSysDir('maPtBudgetDetailDTO.accountNo', 'maPtBudgetDetailDTO.accountDesc', 'ACCNT_NO');">
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
				<label><bean:message key="LABEL.planAmt"/></label>
				<div class="input_box">
					<html:text property="maPtBudgetDetailDTO.planAmt" tabindex="30" styleClass="num"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.assignAmt"/></label>
				<div class="input_read">
					<html:text property="maPtBudgetDetailDTO.assignAmt" tabindex="40" styleClass="num" readonly="true"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.updBy"/></label>
				<div class="input_read">
					<html:text property="maPtBudgetDetailDTO.updBy" tabindex="50" readonly="true"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.updDate"/></label>
				<div class="input_read">
					<html:text property="maPtBudgetDetailDTO.updDate" tabindex="50" readonly="true"/>
				</div>
			</div>
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPtBudgetDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>