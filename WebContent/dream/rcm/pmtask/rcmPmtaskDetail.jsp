<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: rcmPmtaskDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
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
		goUpdate();
	}
	
	setTitle("rcmPmtaskDetailDTO.failDesc");
	
	setForUpdate();

	acSysDesc("rcmPmtaskDetailDTO.rcmTaskTypeDesc","rcmPmtaskDetailDTO.rcmTaskType","RCM_TASK_TYPE", true);
	acSysDesc("rcmPmtaskDetailDTO.periodTypeDesc","rcmPmtaskDetailDTO.periodType","PERIOD_TYPE", true);
}

function goUpdate()
{
	
}

function goInput()
{
	sequenceNextVal('SQARCMPMTASK_ID');
}

function setSequenceVal(sequenceVal)
{
	rcmPmtaskDetailForm.elements['rcmPmtaskDetailDTO.rcmpmtaskId'].value = sequenceVal;
	rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmpmtaskId'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.rcmPmtaskDetailForm;

	if(pageId == "maDocLibList" || pageId == "rcmPmtaskDocLibList")
	{	
		rcmPmtaskDetailForm.elements['maDocLibCommonDTO.objectId'].value = rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmfmeaId'].value;
		rcmPmtaskDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PMTASK";
		rcmPmtaskDetailForm.elements['maDocLibCommonDTO.description'].value = rcmPmtaskDetailForm.elements['rcmPmtaskDetailDTO.failDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "rcmPmtaskCrityList")
	{
		rcmPmtaskDetailForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value = rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmfmeaId'].value;
		goCommonTabPage(form, '' , pageId);
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
	
	if(ckCreate(currentPageId)) rcmPmtaskDetailForm.strutsAction.value = '<%=RcmPmtaskDetailAction.PMTASK_DETAIL_INPUT%>';
	else rcmPmtaskDetailForm.strutsAction.value = '<%=RcmPmtaskDetailAction.PMTASK_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/rcmPmtaskDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmPmtaskDetailForm), 'afterSave');
}


 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================

    var rcmlistId = rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmlistId'].value;
    var rcmfuncId = rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmfuncId'].value;
    var rcmffailId =  rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmffailId'].value;
    var rcmeqid = rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmeqId'].value;
    var rcmeqasmbId = rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmeqasmbId'].value;
    var rcmfmeaId = rcmPmtaskDetailForm.elements['rcmPmtaskCommonDTO.rcmfmeaId'].value;
	
   	 if (parent.findGridRow)	parent.findGridRow([rcmlistId,rcmfuncId,rcmffailId,rcmeqid,rcmeqasmbId,rcmfmeaId]);

     getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = rcmPmtaskDetailForm.elements['rcmPmtaskDetailDTO.rcmpmtaskId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/rcmPmtaskDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmFmeaCommonDTO.rcmfmeaId"/>
	
	<html:hidden property="rcmPmtaskCommonDTO.rcmlistId"/>
	<html:hidden property="rcmPmtaskCommonDTO.rcmfuncId"/>
	<html:hidden property="rcmPmtaskCommonDTO.rcmffailId"/>
	<html:hidden property="rcmPmtaskCommonDTO.rcmeqId"/>
	<html:hidden property="rcmPmtaskCommonDTO.rcmeqasmbId"/>
	<html:hidden property="rcmPmtaskCommonDTO.rcmfmeaId"/>
	<html:hidden property="rcmPmtaskCommonDTO.rcmpmtaskId"/>

	<html:hidden property="rcmPmtaskDetailDTO.rcmlistId"/>
	<html:hidden property="rcmPmtaskDetailDTO.rcmfuncId"/>
	<html:hidden property="rcmPmtaskDetailDTO.rcmffailId"/>
	<html:hidden property="rcmPmtaskDetailDTO.rcmeqId"/>
	<html:hidden property="rcmPmtaskDetailDTO.rcmeqasmbId"/>
	<html:hidden property="rcmPmtaskDetailDTO.rcmfmeaId"/>
	<html:hidden property="rcmPmtaskDetailDTO.rcmpmtaskId"/>
	
	<html:hidden property="rcmPmtaskDetailDTO.mofcd" />
	<html:hidden property="rcmPmtaskDetailDTO.mofailId" />
	<html:hidden property="rcmPmtaskDetailDTO.cafcd" />
	<html:hidden property="rcmPmtaskDetailDTO.cafailId" />
	<html:hidden property="rcmPmtaskDetailDTO.effcd" />
	<html:hidden property="rcmPmtaskDetailDTO.effailId" />
	
	<html:hidden property="rcmPmtaskDetailDTO.periodType" />
	<html:hidden property="rcmPmtaskDetailDTO.rcmTaskType" />
	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
				<!-- 설비 -->
				<div class="field_long">
					<label><bean:message key="LABEL.equipName"/></label>
					<div class="input_read">
						<html:text property="rcmPmtaskDetailDTO.equipDesc" tabindex="10" readonly="true"/>
					</div>
				</div>
				<!-- 기능 -->
				<div class="field_long">
					<label><bean:message key="LABEL.funcName"/></label>
					<div class="input_read">
						<html:text property="rcmPmtaskDetailDTO.funcDesc" tabindex="20"  readonly="true"/>
					</div>
				</div>
				<!-- 기능고장 -->
				<div class="field_long">
					<label><bean:message key="LABEL.rcmFfailName"/></label>
					<div class="input_read">
						<html:text property="rcmPmtaskDetailDTO.failDesc" tabindex="30"  readonly="true"/>
					</div>
				</div>
				<!-- 부위 -->
				<div class="field_long">
					<label><bean:message key="LABEL.asmb"/></label>
					<div class="input_read">
						<html:text property="rcmPmtaskDetailDTO.asmbDesc" tabindex="40"  readonly="true"/>
					</div>
				</div>
				<!-- 고장모드 -->
				<div class="field">
					<label><bean:message key="LABEL.mofailDesc"/></label>
					<div class="input_read">
						<html:text property="rcmPmtaskDetailDTO.mofailDesc" tabindex="50" readonly="true"/>
					</div>
				</div>
				<!-- 고장원인 -->
				<div class="field">
					<label><bean:message key="LABEL.caCd"/></label>
					<div class="input_read">
						<html:text property="rcmPmtaskDetailDTO.cafailDesc" tabindex="50" readonly="true"/>
					</div>
				</div>
				<!-- 고장영향 -->
				<div class="field">
					<label><bean:message key="LABEL.effailDesc"/></label>
					<div class="input_read">
						<html:text property="rcmPmtaskDetailDTO.effailDesc" tabindex="50" readonly="true"/>
					</div>
				</div>
				<!-- PM작업 -->
				<div class="field_long">
					<label><bean:message key="LABEL.pmDesc"/></label>
					<div class="input_box">
						<html:text property="rcmPmtaskDetailDTO.pmDesc" tabindex="60"/>
					</div>
				</div>
				<!-- 고장영향 -->
				<div class="field">
					<label><bean:message key="LABEL.pmtaskType"/></label>
					<div class="input_box">
						<html:text property="rcmPmtaskDetailDTO.rcmTaskTypeDesc" tabindex="70" />
						<p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
					</div>
				</div>
				<div class="field">
	                <label><bean:message key="LABEL.cycleDesc"/></label>
	                <div class="datetime_wrap">
	                    <div class="input_box">
	                        <html:text property="rcmPmtaskDetailDTO.cycle" tabindex="90" styleClass="num"/>
	                    </div>
	                    <div class="input_box">
	                        <html:text property="rcmPmtaskDetailDTO.periodTypeDesc" tabindex="100" />
	                        <p class="open_spop">
	                            <a>
	                            <span>조회</span></a>
	                        </p>
	                    </div>
	                </div>
	            </div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="rcmPmtaskDetailDTO.remark" styleClass="ta50" tabindex="250" />
					</div>
				</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>