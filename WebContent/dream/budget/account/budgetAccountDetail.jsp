<%--===========================================================================
예산계정 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.budget.account.action.BudgetAccountDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 예산계정 -->
<title><bean:message key='LABEL.BUDACCNT' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

function loadPage() 
{
	
	if(ckCreate(currentPageId)) goInput();
	else 
    {
        goUpdate();
    }
	
    setTitle("budgetAccountDetailDTO.accntNo", "budgetAccountDetailDTO.accntDesc");
    //For Save
    setForUpdate();
    
    /**예산계정구분  */
    acSysDesc("budgetAccountDetailDTO.accntTypeDesc","budgetAccountDetailDTO.accntType","ACCNT_TYPE",true);
    /**사용여부 */
    acSysDesc("budgetAccountDetailDTO.isUse","budgetAccountDetailDTO.isUse","IS_USE",true);
    
}

/**
 * 코드 중복 체크
 */
function valAccntNo()
{
	var actionUrl = contextPath + "/budgetAccountDetail.do";
	var param =  "&strutsAction=" + '<%= BudgetAccountDetailAction.DETAIL_CHECK %>'
			  +  "&" + FormQueryString(budgetAccountDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidAccntNo');
}

/**
 * valAccntNo()실행 후 호출
 */
var isValid;
function setValidAccntNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
        budgetAccountDetailForm.elements['budgetAccountDetailDTO.accntNo'].value = '';
        budgetAccountDetailForm.elements['budgetAccountDetailDTO.accntNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
    else
    {
    	//strutsAction 셋팅.
        if(ckCreate(currentPageId)) budgetAccountDetailForm.strutsAction.value = "<%=BudgetAccountDetailAction.DETAIL_INPUT%>";
        else budgetAccountDetailForm.strutsAction.value = "<%=BudgetAccountDetailAction.DETAIL_UPDATE%>";
        
    	var actionUrl = contextPath + "/budgetAccountDetail.do";
    	XMLHttpPost(actionUrl, FormQueryString(budgetAccountDetailForm), 'afterSave');
    }
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAACCNT_ID');
    
    budgetAccountDetailForm.elements['budgetAccountDetailDTO.isUse'].value = 'Y';
    
}

/**
 * 수정
 */
function goUpdate()
{
	setReadOnly("budgetAccountDetailDTO.accntNo");
}

/**
 * set Id 
 */
function setSequenceVal(sequenceVal)
{
    budgetAccountDetailForm.elements['budgetAccountDetailDTO.accntId'].value = sequenceVal;
    budgetAccountDetailForm.elements['budgetAccountDetailDTO.accntNo'].value = sequenceVal;
    budgetAccountDetailForm.elements['budgetAccountCommonDTO.accntId'].value = sequenceVal;
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
    
	valAccntNo();
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    budgetAccountDetailForm.elements['budgetAccountDetailDTO.accntId'].value = budgetAccountDetailForm.elements['budgetAccountCommonDTO.accntId'].value;
    if (parent.findGridRow) parent.findGridRow(budgetAccountDetailForm.elements['budgetAccountCommonDTO.accntId'].value);
    getTopPage().afterSaveAll(currentPageId);

    goUpdate();
    setTitle("budgetAccountDetailDTO.accntNo", "budgetAccountDetailDTO.accntDesc");
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/budgetAccountDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="budgetAccountCommonDTO.accntId" />
<html:hidden property="budgetAccountDetailDTO.accntId" /><!-- key -->
<html:hidden property="budgetAccountDetailDTO.accntType" />
	 
	 <div class="article_box">
            <div class="form_box">
            	 <!--예산계정코드  -->
            	 <div class="field">
            	 	<label class="check"><bean:message key="LABEL.accntNo"/></label>
            	 	<div class="input_box">
            	 		<html:text property="budgetAccountDetailDTO.accntNo"  tabindex="10"/> 
            	 	</div>
            	 </div>
	             <!--사용여부  -->
                 <div class="field">
	                <label><bean:message key="LABEL.isUse"/></label>
	                <div class="input_box">
	                    <html:text property="budgetAccountDetailDTO.isUse" tabindex="20"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	             </div>
            	 <!--예산계정명  -->
            	 <div class="field">
            	 	<label class="check"><bean:message key="LABEL.accntDesc"/></label>
            	 	<div class="input_box">
            	 		<html:text property="budgetAccountDetailDTO.accntDesc" tabindex="30"/>
            	 	</div>
            	 </div>
	             <!--예산계정구분  -->
                 <div class="field">
	                <label><bean:message key="LABEL.accntType"/></label>
	                <div class="input_box">
	                    <html:text property="budgetAccountDetailDTO.accntTypeDesc" tabindex="40"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	             </div>
            	 <!--비고  -->
            	 <div class="field_long">
            	 	<label><bean:message key="LABEL.remark"/></label>
            	 	<div class="input_box">
            	 		<html:textarea property="budgetAccountDetailDTO.remark" styleClass="ta100" tabindex="50"/>
            	 	</div>
            	 </div>
            </div> <!-- End of Form_box -->
        </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
