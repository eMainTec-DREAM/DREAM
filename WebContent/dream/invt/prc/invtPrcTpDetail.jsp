<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: invtPrcTpDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.invt.prc.action.InvtPrcTpDetailAction"%>
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

var rcmNameAc;
function loadPage() 
{
	if(ckCreate(currentPageId))
	{
	
		goInput();
	}		
	else 
	{
		goTabPage("invtPrcTpItemList");
	}
	
	setTitle("invtPrcTpDetailDTO.invtNo","invtPrcTpDetailDTO.invtDesc");
	
	acSysDesc("invtPrcTpDetailDTO.isUse","invtPrcTpDetailDTO.isUse","IS_USE");
	
	setForUpdate();

}

function goInput()
{
	sequenceNextVal('SQARCMFUNC_ID');
	
	invtPrcTpDetailForm.elements['invtPrcTpDetailDTO.regDate'].value = getToday();

}

function setSequenceVal(sequenceVal)
{
	invtPrcTpDetailForm.elements['invtPrcTpDetailDTO.invtPrcTpId'].value = sequenceVal;
	invtPrcTpDetailForm.elements['invtPrcTpDetailDTO.invtNo'].value = sequenceVal;
	invtPrcTpDetailForm.elements['invtPrcTpCommonDTO.invtPrcTpId'].value = sequenceVal;
	
	goTabPage("invtPrcTpItemList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId) 
{
	var form = document.invtPrcTpDetailForm;

	if(pageId == "maDocLibList" || pageId == "invtPrcTpItemDocLibList")
	{	
		invtPrcTpDetailForm.elements['maDocLibCommonDTO.objectId'].value = invtPrcTpDetailForm.elements['invtPrcTpCommonDTO.invtPrcTpId'].value;
		invtPrcTpDetailForm.elements['maDocLibCommonDTO.objectType'].value = "INVTPRC";
		invtPrcTpDetailForm.elements['maDocLibCommonDTO.description'].value = invtPrcTpDetailForm.elements['invtPrcTpDetailDTO.invtDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
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
	if(ckCreate(currentPageId)) invtPrcTpDetailForm.strutsAction.value = "<%=InvtPrcTpDetailAction.INVTPRC_DETAIL_INPUT%>";
	else invtPrcTpDetailForm.strutsAction.value = '<%=InvtPrcTpDetailAction.INVTPRC_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/invtPrcTpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(invtPrcTpDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     invtPrcTpDetailForm.elements['invtPrcTpCommonDTO.invtPrcTpId'].value = invtPrcTpDetailForm.elements['invtPrcTpDetailDTO.invtPrcTpId'].value;
     if (parent.findGridRow)	parent.findGridRow(invtPrcTpDetailForm.elements['invtPrcTpCommonDTO.invtPrcTpId'].value);

 	setTitle("invtPrcTpDetailDTO.invtNo","invtPrcTpDetailDTO.invtDesc");
     
     getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = invtPrcTpDetailForm.elements['invtPrcTpDetailDTO.invtPrcTpId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/invtPrcTpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="invtPrcTpCommonDTO.invtPrcTpId" />
	<html:hidden property="invtPrcTpDetailDTO.invtPrcTpId" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
			<!-- System 분석No  -->
            <div class="field">
				<label><bean:message key="LABEL.invtNo"/></label>
				<div class="input_read">
					<html:text property="invtPrcTpDetailDTO.invtNo" readonly="true"/>
				</div>
			</div>
			<div class="field_long">
                <label class="check"><bean:message key="LABEL.invtDesc"/></label>
                <div class="input_box" id="rcmDescDiv">
                    <html:text property="invtPrcTpDetailDTO.invtDesc" tabindex="30"/>
                </div>
             </div>
              <!-- 고장가능성여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="invtPrcTpDetailDTO.isUse" tabindex="160"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 등록일 -->
			<div class="field">
				<label><bean:message key="LABEL.regDate"/></label>
				<div class="calendar">
					<div class="input_box input_carendar">
						<html:text property="invtPrcTpDetailDTO.regDate" tabindex="20" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="invtPrcTpDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>