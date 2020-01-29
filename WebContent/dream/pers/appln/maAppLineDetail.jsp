<%--===========================================================================
상세
author  kim21017
version $Id: maAppLineDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.pers.appln.action.MaAppLineDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key="LABEL.qnaAns"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maAppLineUsrList");
	}
	
	setTitle("maAppLineDetailDTO.title");
	
	setForUpdate();
}

function goInput()
{
	sequenceNextVal('SQAAPPRLINE_ID');
	<%-- maAppLineDetailForm.elements['maAppLineDetailDTO.regDate'].value = getToday();

	maAppLineDetailForm.elements['maAppLineDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maAppLineDetailForm.elements['maAppLineDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	maAppLineDetailForm.elements['maAppLineDetailDTO.userId'].value     = "<%=user.getUserId()%>";
	maAppLineDetailForm.elements['maAppLineDetailDTO.userDesc'].value   = "<%=user.getUserName()%>"; --%>
}

function setSequenceVal(sequenceVal)
{
	maAppLineDetailForm.elements['maAppLineDetailDTO.apprlineId'].value = sequenceVal;
	maAppLineDetailForm.elements['maAppLineCommonDTO.apprlineId'].value = sequenceVal;
/* 	maAppLineDetailForm.elements['maAppLineDetailDTO.questionNo'].value = sequenceVal; */
	
	goTabPage("maAppLineUsrList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maAppLineDetailForm;

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
	if(ckCreate(currentPageId)) maAppLineDetailForm.strutsAction.value = "<%=MaAppLineDetailAction.QNA_DETAIL_INPUT%>";
	else maAppLineDetailForm.strutsAction.value = '<%=MaAppLineDetailAction.QNA_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maAppLineDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maAppLineDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maAppLineDetailForm.elements['maAppLineCommonDTO.apprlineId'].value = maAppLineDetailForm.elements['maAppLineDetailDTO.apprlineId'].value;
     if (parent.findGridRow)	parent.findGridRow(maAppLineDetailForm.elements['maAppLineCommonDTO.apprlineId'].value);

 	setTitle("maAppLineDetailDTO.title");
     
     getTopPage().afterSaveAll(currentPageId);
 }
 /**
 *신청완료
 */
 function goConfirm(){
	alert('goConfirm');
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maAppLineDetailForm.elements['maAppLineDetailDTO.apprlineId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maAppLineDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maAppLineCommonDTO.apprlineId" />
	<html:hidden property="maAppLineDetailDTO.apprlineId" />

	<div class="article_box">
		<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.apprLineTitle"/></label>
					<div class="input_box">
						<html:text property="maAppLineDetailDTO.title" tabindex="10"/>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.failureRemark"/></label>
					<div class="input_box">
						<html:textarea property="maAppLineDetailDTO.remark" styleClass="ta50"  tabindex="20"/>
					</div>
				</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>