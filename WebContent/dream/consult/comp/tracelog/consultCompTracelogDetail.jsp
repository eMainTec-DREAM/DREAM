<%--===========================================================================
Screen Trace Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.tracelog.action.ConsultCompTracelogDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- Screen Trace -->
<title><bean:message key='MENU.SCREENTRACE' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var compAc, pageAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("consultCompTracelogDetailDTO.pageDesc", "consultCompTracelogDetailDTO.objectId");
    //For Save
    setForUpdate();
    
    compAc = new autoC({"consultCompTracelogDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompTracelogDetailDTO.compNo");
    compAc.setAcResultMap({
        "consultCompTracelogDetailDTO.compNo":"comp_no"
    });
    compAc.init();
    
    pageAc = new autoC({"consultCompTracelogDetailDTO.pageDesc":"description"});
    pageAc.setTable("TAPAGE");
    pageAc.setKeyName("consultCompTracelogDetailDTO.pageId");
    pageAc.setAcResultMap({
    	"consultCompTracelogDetailDTO.pageId":"page_id",
    	"consultCompTracelogDetailDTO.fileName":"file_name"
    });
    pageAc.init();  
    
    initContents();
    
    setDisableAll();
//     setDisableAll(document.getElementById("disableFrame"));
}

function initContents() {
	//여기를 수정
	var $TraceLogContents = $( "#traceLog" );
	$TraceLogContents.append( consultCompTracelogDetailForm.elements['consultCompTracelogDetailDTO.contents'].value );
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
    consultCompTracelogDetailForm.elements['consultCompTracelogDetailDTO.scrnTraceLogId'].value = sequenceVal;
    consultCompTracelogDetailForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) consultCompTracelogDetailForm.strutsAction.value = "<%=ConsultCompTracelogDetailAction.DETAIL_INPUT%>";
    else consultCompTracelogDetailForm.strutsAction.value = "<%=ConsultCompTracelogDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/consultCompTracelogDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompTracelogDetailForm), 'afterSave');

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
		parent.findGridRow(consultCompTracelogDetailForm.elements['consultCompTracelogDetailDTO.scrnTraceLogId'].value);

	consultCompTracelogDetailForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = consultCompTracelogDetailForm.elements['consultCompTracelogDetailDTO.scrnTraceLogId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("consultCompTracelogDetailDTO.pageDesc", "consultCompTracelogDetailDTO.objectId");
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/consultCompTracelogDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="consultCompTracelogCommonDTO.scrnTraceLogId" />
		<!-- Key -->
		<html:hidden property="consultCompTracelogDetailDTO.scrnTraceLogId" />
		<!-- Key -->
		<html:hidden property="consultCompTracelogDetailDTO.compNo" />
		<html:hidden property="consultCompTracelogDetailDTO.pageId" />
		<html:hidden property="consultCompTracelogDetailDTO.contents" />

		<div class="article_box">
			<div class="form_box">
				<!-- 회사 -->
				<div class="field">
                    <label><bean:message key="LABEL.compDesc"/></label>
                    <div class="input_box">
                         <html:text property="consultCompTracelogDetailDTO.compDesc" tabindex="10"/>
                         <p class="open_spop">
                             <a><span>조회</span></a>
                         </p>
                    </div>
                </div>
                <!-- 화면 Id -->
				<div class="field">
					<label><bean:message key="LABEL.fileName"/></label>
					<div class="input_box">
						<html:text property="consultCompTracelogDetailDTO.fileName" tabindex="40"/>
					</div>
				</div>
				<!-- 화면명 -->
				<div class="field">
		              <label><bean:message key="LABEL.pageDesc"/></label>
		              <div class="input_box">
		              		<html:text property="consultCompTracelogDetailDTO.pageDesc" tabindex="20"/>
		              		<p class="open_spop">
		                       <a>
		                           <span>조회</span>
		                       </a>
		                    </p>
		              </div>
	             </div>
				<!-- Object Id -->
				<div class="field">
					<label><bean:message key="LABEL.objectId"/></label>
					<div class="input_box">
						<html:text property="consultCompTracelogDetailDTO.objectId" tabindex="40"/>
					</div>
				</div>
				<!-- 수정일자 -->
				<div class="field">
					<label><bean:message key="LABEL.updDate" /></label>
					<div class="input_box">
						<html:text property="consultCompTracelogDetailDTO.updDate" tabindex="30" />
					</div>
				</div>
				<!-- 수정자 -->
				<div class="field">
					<label><bean:message key="LABEL.updBy" /></label>
					<div class="input_box">
						<html:text property="consultCompTracelogDetailDTO.updBy" tabindex="30" />
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
