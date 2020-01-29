<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: rcmTaskMapDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.rcm.taskmap.action.RcmTaskMapDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key="LABEL.qnaItem"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("rcmTaskMapItemList");
	}
	
	setTitle("rcmTaskMapDetailDTO.mapNo","rcmTaskMapDetailDTO.mapName");
	
	setForUpdate();
	
}

function goInput()
{
	rcmTaskMapDetailForm.elements['rcmTaskMapDetailDTO.isUse'].value = 'Y';
	rcmTaskMapDetailForm.elements['rcmTaskMapDetailDTO.regDate'].value = getToday(); 
	
	sequenceNextVal('SQAPMTASKMAP_ID');
}

function setSequenceVal(sequenceVal)
{
	rcmTaskMapDetailForm.elements['rcmTaskMapDetailDTO.pmTaskMapListId'].value = sequenceVal;
	rcmTaskMapDetailForm.elements['rcmTaskMapCommonDTO.pmTaskMapListId'].value = sequenceVal;
	rcmTaskMapDetailForm.elements['rcmTaskMapDetailDTO.mapNo'].value = sequenceVal;
	
	goTabPage("rcmTaskMapItemList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId) 
{
	var form = document.rcmTaskMapDetailForm;

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
	if(ckCreate(currentPageId)) rcmTaskMapDetailForm.strutsAction.value = "<%=RcmTaskMapDetailAction.QNA_DETAIL_INPUT%>";
	else rcmTaskMapDetailForm.strutsAction.value = '<%=RcmTaskMapDetailAction.QNA_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/rcmTaskMapDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmTaskMapDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     if (parent.findGridRow)	parent.findGridRow(rcmTaskMapDetailForm.elements['rcmTaskMapDetailDTO.pmTaskMapListId'].value);
     rcmTaskMapDetailForm.elements['rcmTaskMapCommonDTO.pmTaskMapListId'].value = rcmTaskMapDetailForm.elements['rcmTaskMapDetailDTO.pmTaskMapListId'].value;
 	 
     setTitle("rcmTaskMapDetailDTO.mapNo","rcmTaskMapDetailDTO.mapName");
     
     getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = rcmTaskMapDetailForm.elements['rcmTaskMapDetailDTO.pmTaskMapListId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/rcmTaskMapDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmTaskMapCommonDTO.pmTaskMapListId" />
	<html:hidden property="rcmTaskMapDetailDTO.pmTaskMapListId" />
	<div class="article_box">
		<div class="form_box">
             <div class="field">
                <label ><bean:message key="LABEL.taskNo"/></label>
                <div class="input_read">
                    <html:text property="rcmTaskMapDetailDTO.mapNo" readonly="true"/>
                </div>
             </div>  
              <div class="field_long">
                <label><bean:message key="LABEL.taskName"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapDetailDTO.mapName" tabindex="60"/>
                </div>
             </div> 
             <!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="rcmTaskMapDetailDTO.isUse" tabindex="160"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 등록일 -->
			<div class="field">
                <label class="check"><bean:message key="LABEL.regDate"/></label>
                <div class="input_box">
                    <html:text property="rcmTaskMapDetailDTO.regDate" tabindex="100" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>	
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmTaskMapDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>