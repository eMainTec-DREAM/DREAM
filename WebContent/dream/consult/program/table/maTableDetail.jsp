<%--===========================================================================
데이터 테이블 - 상세
author  kim21017
version $Id: maTableDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.table.action.MaTableDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html:html>
<head>
<!-- 코드종류 -->
<title><bean:message key='LABEL.cdType' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maTableColList");
	}
	
	setTitle("maTableDetailDTO.tableNo","maTableDetailDTO.tableDesc");
	
	setForUpdate();
}

function goInput()
{
	sequenceNextVal('SQATABLE_ID');
	
	maTableDetailForm.elements['maTableDetailDTO.creDate'].value   = getToday();
	maTableDetailForm.elements['maTableDetailDTO.creBy'].value     = loginUser.userId;
	maTableDetailForm.elements['maTableDetailDTO.creByDesc'].value = loginUser.userName;
}

function setSequenceVal(sequenceVal)
{
	maTableDetailForm.elements['maTableDetailDTO.tableMId'].value = sequenceVal;
	maTableDetailForm.elements['maTableCommonDTO.tableMId'].value = sequenceVal;
	
	goTabPage("maTableColList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maTableDetailForm;

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
	if(ckCreate(currentPageId)) maTableDetailForm.strutsAction.value = "<%=MaTableDetailAction.LISTTYPE_DETAIL_INPUT%>";
	else maTableDetailForm.strutsAction.value = '<%=MaTableDetailAction.LISTTYPE_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maTableDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maTableDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maTableDetailForm.elements['maTableCommonDTO.tableMId'].value = maTableDetailForm.elements['maTableDetailDTO.tableMId'].value;
     if (parent.findGridRow)	parent.findGridRow(maTableDetailForm.elements['maTableCommonDTO.tableMId'].value);
 	
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maTableDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maTableCommonDTO.tableMId" />
	<html:hidden property="maTableDetailDTO.tableMId" />
	<html:hidden property="maTableDetailDTO.creBy" />
	
	
	<div class="article_box" id="detailBox">
		<div class="form_box">
				<div class="field">
					<label class="check"><bean:message key="LABEL.table"/></label>
					<div class="input_box">
						<html:text property="maTableDetailDTO.tableNo" tabindex="20" />
					</div>
				</div>				
				<div class="field">
					<label class="check"><bean:message key="LABEL.tableDesc"/></label>
					<div class="input_box">
						<html:text property="maTableDetailDTO.tableDesc" tabindex="20" />
					</div>
				</div>
				<!-- 생성자 -->
			    <div class="field">
				<label><bean:message key="LABEL.creBy"/></label>
				<div class="input_read">
					<html:text property="maTableDetailDTO.creByDesc" readonly="true"/>
				</div>
				</div>
				<!-- 생성일자 -->
			    <div class="field">
				<label><bean:message key="LABEL.creDate"/></label>
				<div class="input_read">
					<html:text property="maTableDetailDTO.creDate" readonly="true"/>
				</div>
				</div>
				<!-- 상세설명 -->
			    <div class="field_long">
				<label><bean:message key="LABEL.detailDesc"/></label>
				<div class="input_box">
					<html:textarea property="maTableDetailDTO.detailDesc" styleClass="ta50" tabindex="110" />
				</div>
			</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>