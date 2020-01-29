<%--===========================================================================
월별단가
author  youngjoo38
version $Id: workPmCheckMonthlyUnitPriceDetail.jsp,v 1.1 2017/08/28 12:37:40 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.check.action.WorkPmCheckMonthlyUnitPriceDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 월별단가 -->
<title><bean:message key='LABEL.prompt'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
//var assEvalDescAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    workPmCheckMonthlyUnitPriceDetailForm.elements['workPmCheckMonthlyUnitPriceDetailDTO.checkPointId'].value 
    = workPmCheckMonthlyUnitPriceDetailForm.elements['workPmCheckCommonDTO.checkPointId'].value;
    
    workPmCheckMonthlyUnitPriceDetailForm.elements['workPmCheckMonthlyUnitPriceDetailDTO.stdChkPointPriceId'].value 
    = workPmCheckMonthlyUnitPriceDetailForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value;
    
    setTitle("workPmCheckMonthlyUnitPriceDetailDTO.yyyymm");
    
    
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQASTDCHKPOINTPRICE_ID');
    
    $('.open_mon_calendar').trigger('click');
}
function setSequenceVal(sequenceVal)
{
    workPmCheckMonthlyUnitPriceDetailForm.elements['workPmCheckMonthlyUnitPriceDetailDTO.stdChkPointPriceId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
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
    if(ckCreate(currentPageId)) workPmCheckMonthlyUnitPriceDetailForm.strutsAction.value = "<%=WorkPmCheckMonthlyUnitPriceDetailAction.DETAIL_INPUT%>";
    else workPmCheckMonthlyUnitPriceDetailForm.strutsAction.value = "<%=WorkPmCheckMonthlyUnitPriceDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/workPmCheckMonthlyUnitPriceDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmCheckMonthlyUnitPriceDetailForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workPmCheckMonthlyUnitPriceDetailForm.elements['workPmCheckMonthlyUnitPriceDetailDTO.stdChkPointPriceId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workPmCheckMonthlyUnitPriceDetailDTO.yyyymm");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmCheckMonthlyUnitPriceDetailForm.elements['workPmCheckMonthlyUnitPriceDetailDTO.stdChkPointPriceId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/workPmCheckMonthlyUnitPriceDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmCheckCommonDTO.checkPointId"/><!-- Key -->
<html:hidden property="workPmCheckMonthlyUnitPriceListDTO.checkPointId"/>
<html:hidden property="workPmCheckMonthlyUnitPriceDetailDTO.checkPointId"/>
<html:hidden property="workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId"/>
<html:hidden property="workPmCheckMonthlyUnitPriceDetailDTO.stdChkPointPriceId"/><!-- Key -->
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 년월 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.yyyymm"/></label>
				<div class="input_box">
					<html:text property="workPmCheckMonthlyUnitPriceDetailDTO.yyyymm" tabindex="10"/>
                    <p class="open_mon_calendar">
                        <span>날짜</span>
                    </p>
				</div>
			</div>
			
			<div class="field"></div>
			
			<!-- 단가 -->
			<div class="field" >
				<label class="check"><bean:message key="LABEL.unitPrice"/></label>
				<div class="input_box">
					<html:text property="workPmCheckMonthlyUnitPriceDetailDTO.unitPrice" tabindex="20" styleClass="num dataintComma"/>
				</div>
			</div>

			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workPmCheckMonthlyUnitPriceDetailDTO.remark" styleClass="ta50" tabindex="30"/>
				</div>
			</div>
			
            <div class="field_long"></div>
            <div class="field_long"></div>
            <div class="field_long"></div>
			
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
