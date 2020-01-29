<%--===========================================================================
부서별 예산금액
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.budget.plan.list.action.MaPtDeptBgDetailAction"%>
<html>
<head>
<!-- 부서별 예산금액 -->
<title><bean:message key="LABEL.dept"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("maPtDeptBgDetailDTO.deptDesc");
	setForUpdate();

}

function goSave()
{
	if(checkValidation()) return;
	
	var bgtDeptPlanId = maPtDeptBgDetailForm.elements['maPtBudgetCommonDTO.bgtDeptPlanId'].value;
	var bgtPlanId     = maPtDeptBgDetailForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value;
	var compNo        = maPtDeptBgDetailForm.elements['maPtBudgetCommonDTO.compNo'].value;
	
	if(bgtDeptPlanId == "")
	{
		 maPtDeptBgDetailForm.elements['maPtDeptBgDetailDTO.bgtPlanId'].value = bgtPlanId;
	     maPtDeptBgDetailForm.elements['maPtDeptBgDetailDTO.compNo'].value = compNo;
	     
		 maPtDeptBgDetailForm.strutsAction.value = '<%=MaPtDeptBgDetailAction.PTDEPT_BUDGET_DETAIL_INPUT%>';
	}
	else maPtDeptBgDetailForm.strutsAction.value = '<%= MaPtDeptBgDetailAction.PTDEPT_BUDGET_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPtDeptBgDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtDeptBgDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPtDeptBgDetailForm.elements['maPtDeptBgDetailDTO.bgtDeptPlanId'].value, maPtDeptBgDetailForm.elements['maPtDeptBgDetailDTO.deptId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtDeptBgDetailForm.elements['maPtDeptBgDetailDTO.bgtDeptPlanId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPtDeptBgDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtBudgetCommonDTO.bgtDeptPlanId"/>
	<html:hidden property="maPtBudgetCommonDTO.bgtPlanId"/>
	<html:hidden property="maPtBudgetCommonDTO.deptId"/>
	<html:hidden property="maPtBudgetCommonDTO.compNo"/>
	<html:hidden property="maPtDeptBgDetailDTO.bgtDeptPlanId"/>
	<html:hidden property="maPtDeptBgDetailDTO.bgtPlanId"/>
	<html:hidden property="maPtDeptBgDetailDTO.deptId"/>
	<html:hidden property="maPtDeptBgDetailDTO.compNo"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<div class="field">
                <label><bean:message key="LABEL.dept"/></label>
                <div class="input_read">
                    <html:text property="maPtDeptBgDetailDTO.deptDesc" tabindex="10" readonly="true"/>
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.planAmt"/></label>
                <div class="input_box">
                    <html:text property="maPtDeptBgDetailDTO.planAmt" tabindex="20" styleClass="num" />
                </div>
            </div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>