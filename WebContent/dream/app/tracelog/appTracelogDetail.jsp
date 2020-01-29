<%--===========================================================================
TraceLog Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.app.tracelog.action.AppTracelogDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- TraceLog -->
<title><bean:message key='MENU.SCREENTRACE' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("appTracelogDetailDTO.pageDesc", "appTracelogDetailDTO.objectId");
    //For Save
    setForUpdate();
    
    initContents();
    
    setDisableAll(document.getElementById("disableFrame"));
}

function initContents() {
	//여기를 수정
	var $TraceLogContents = $( "#traceLog" );
	$TraceLogContents.append( appTracelogDetailForm.elements['appTracelogDetailDTO.contents'].value );
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQASCRNTRACELOG_ID');
}
function setSequenceVal(sequenceVal)
{
    appTracelogDetailForm.elements['appTracelogDetailDTO.scrnTraceLogId'].value = sequenceVal;
    appTracelogDetailForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) appTracelogDetailForm.strutsAction.value = "<%=AppTracelogDetailAction.DETAIL_INPUT%>";
    else appTracelogDetailForm.strutsAction.value = "<%=AppTracelogDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/appTracelogDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(appTracelogDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)
		parent.findGridRow(appTracelogDetailForm.elements['appTracelogDetailDTO.scrnTraceLogId'].value);

	appTracelogDetailForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = appTracelogDetailForm.elements['appTracelogDetailDTO.scrnTraceLogId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("appTracelogDetailDTO.pageDesc", "appTracelogDetailDTO.objectId");
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/appTracelogDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="appTracelogCommonDTO.scrnTraceLogId" />
		<!-- Key -->
		<html:hidden property="appTracelogDetailDTO.scrnTraceLogId" />
		<!-- Key -->
		<html:hidden property="appTracelogDetailDTO.fileName" />
		<html:hidden property="appTracelogDetailDTO.compNo" />
		<html:hidden property="appTracelogDetailDTO.objectId" />
		<html:hidden property="appTracelogDetailDTO.contents" />
		<html:hidden property="appTracelogDetailDTO.pageDesc" />

		<div class="article_box">
			<div class="form_box">
				<!-- 수정일자 -->
				<div class="field">
					<label><bean:message key="LABEL.modifyDate" /></label>
					<div class="input_read">
						<html:text property="appTracelogDetailDTO.updDate" tabindex="30" readonly="true" />
					</div>
				</div>
				<!-- 수정자 -->
				<div class="field">
					<label><bean:message key="LABEL.modifyBy" /></label>
					<div class="input_read">
						<html:text property="appTracelogDetailDTO.updBy" tabindex="30" readonly="true" />
					</div>
				</div>
				<!-- Trace 내용 -->
				<div class="field_long" id="disableFrame">
					<label><bean:message key="LABEL.traceLog" /></label>
					<div class="input_box trace_box">
					    <div  id="traceLog"  class="article_box">
					    </div>
					</div> 
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
