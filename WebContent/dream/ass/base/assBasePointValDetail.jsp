<%--===========================================================================
평가기준
author  kim21017
version $Id: assBasePointValDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBasePointValDetailAction"%>
<%-- <%@ page import="dream.ass.base.action.AssBasePointValScriptAction"%> --%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 항목 -->
<title><bean:message key='LABEL.assBase'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("assBasePointValDetailDTO.assEval", "assBasePointValDetailDTO.evalValue");
    //사용여부 자동완성
    acSysDesc("assBasePointValDetailDTO.isUseDesc","assBasePointValDetailDTO.isUseIs","IS_USE", true);
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAASSBASEPVAL_ID');
    assBasePointValDetailForm.elements['assBasePointValDetailDTO.isUseId'].value = 'Y';
    assBasePointValDetailForm.elements['assBasePointValDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('assBasePointValDetailDTO.isUseId', 'assBasePointValDetailDTO.isUseDesc', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    assBasePointValDetailForm.elements['assBasePointValDetailDTO.assBasePointValId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	//goTabPage("assBasePointValScript");
}

function goTabPage(pageId)
{
    <%-- goCommonTabPage(assBasePointValDetailForm, '<%=AssBasePointValScriptAction.DATA_SCRIPT_FIND%>' , pageId); --%>
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
    if(ckCreate(currentPageId)) assBasePointValDetailForm.strutsAction.value = "<%=AssBasePointValDetailAction.DETAIL_INPUT%>";
    else assBasePointValDetailForm.strutsAction.value = "<%=AssBasePointValDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/assBasePointValDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assBasePointValDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assBasePointValDetailForm.elements['assBasePointValDetailDTO.assBasePointValId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("assBasePointValDetailDTO.assEval", "assBasePointValDetailDTO.evalValue");
    
}
/**
 * 저장후생성 버튼 클릭시 저장후.
 */
function afterSavenew()
{
	sequenceNextVal('SQAASSBASEPVAL_ID');
	assBasePointValDetailForm.elements['assBasePointValDetailDTO.assEval'].value = '';
	assBasePointValDetailForm.elements['assBasePointValDetailDTO.evalValue'].value = '';
    assBasePointValDetailForm.elements['assBasePointValDetailDTO.isUseId'].value = 'Y';
    assBasePointValDetailForm.elements['assBasePointValDetailDTO.isUseDesc'].value = 'Y';
	assBasePointValDetailForm.elements['assBasePointValDetailDTO.remark'].value = '';
    valSysDir('assBasePointValDetailDTO.isUseId', 'assBasePointValDetailDTO.isUseDesc', 'IS_USE', true);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assBasePointValDetailForm.elements['assBasePointValDetailDTO.assBasePointValId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assBasePointValDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBasePointListDTO.assBasePointId"/><!-- Key -->
<html:hidden property="assBasePointValListDTO.assBasePointValId"/><!-- Key -->
<html:hidden property="assBasePointValDetailDTO.assBasePointValId"/><!-- Key -->
<html:hidden property="assBasePointValDetailDTO.isUseId"/>
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 항목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.assBase"/></label>
				<div class="input_box">
					<html:text property="assBasePointValDetailDTO.assEval" tabindex="10"/>
				</div>
			</div>
			<!-- 점수 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.point"/></label>
				<div class="input_box">
					<html:text property="assBasePointValDetailDTO.evalValue" tabindex="20" styleClass="num" />
				</div>
			</div>
			<!-- 등급값 FROM -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.assEvalFrom"/></label>
                <div class="input_box">
                    <html:text property="assBasePointValDetailDTO.assEvalFrom" tabindex="30" styleClass="num" />
                </div>
            </div>
            <!-- 등급값 TO -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.assEvalTo"/></label>
                <div class="input_box">
                    <html:text property="assBasePointValDetailDTO.assEvalTo" tabindex="40" styleClass="num" />
                </div>
            </div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="assBasePointValDetailDTO.ordNo" tabindex="50"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="assBasePointValDetailDTO.isUseDesc" tabindex="60" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="assBasePointValDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
