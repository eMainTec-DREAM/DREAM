<%--===========================================================================
배치작업내역
author  kim21017
version $Id: maBatchMngDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.scheduler.list.action.MaBatchMngDetailAction"%>
<html:html>
<head>
<!-- 프로그램명 -->
<title><bean:message key='LABEL.programName' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action

function loadPage() 
{
	// 수동실행가능작업 여부
	// href="javascript:lovTable('maBatchMngDetailDTO.isExec', 'maBatchMngDetailDTO.isExec','YN');"
    acSysDesc("maBatchMngDetailDTO.isExec","maBatchMngDetailDTO.isExec","IS_USE", true);
	
	
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maBatchMngDetailDTO.batchPgm","maBatchMngDetailDTO.batPgmDesc");
	//For Save
	setForUpdate();
}
function goInput()
{
	sequenceNextVal('SQABATPGM_ID');
	
	maBatchMngDetailForm.elements['maBatchMngDetailDTO.isExec'].value = 'N';
}

function setSequenceVal(sequenceVal)
{
	maBatchMngDetailForm.elements['maBatchMngDetailDTO.batPgmId'].value = sequenceVal;
	maBatchMngDetailForm.elements['maBatchMngCommonDTO.batPgmId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) maBatchMngDetailForm.strutsAction.value = "<%=MaBatchMngDetailAction.BATCH_DETAIL_INPUT%>";
	else maBatchMngDetailForm.strutsAction.value = "<%=MaBatchMngDetailAction.BATCH_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maBatchMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maBatchMngDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maBatchMngDetailForm.elements['maBatchMngDetailDTO.batPgmId'].value = maBatchMngDetailForm.elements['maBatchMngCommonDTO.batPgmId'].value;
    if (parent.findGridRow)	parent.findGridRow(maBatchMngDetailForm.elements['maBatchMngDetailDTO.batPgmId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maBatchMngDetailForm.elements['maBatchMngDetailDTO.batPgmId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maBatchMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maBatchMngCommonDTO.batPgmId" />
	<html:hidden property="maBatchMngDetailDTO.batPgmId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.id"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maBatchMngDetailDTO.batPgmNo" tabindex="10"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.programName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maBatchMngDetailDTO.batchPgm" tabindex="20" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.lastExecTime"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maBatchMngDetailDTO.lastExeTime" readonly = "true" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.isExec"/></label>
					<div class="input_box">
						<html:text property="maBatchMngDetailDTO.isExec" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
             	 	<label><bean:message key="LABEL.execCycle"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maBatchMngDetailDTO.execRemark" tabindex="40" />
             		</div>
             	</div>
				<div class="field">
             	 	<label><bean:message key="LABEL.lastExecBy"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maBatchMngDetailDTO.exeBy" readonly="true" />
             		</div>
             	</div>
				<div class="field_long">
             	 	<label><bean:message key="LABEL.failureRemark"/></label>
             	 	<div class="input_box">
             	 		<html:textarea property="maBatchMngDetailDTO.batPgmDesc" tabindex="50" styleClass="ta50"/>
             		</div>
             	</div>
         </div>
         </div>
	</html:form>
</body>
</html:html>
