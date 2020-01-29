<%--===========================================================================
평가항목
author  kim21017
version $Id: assBasePointDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBasePointDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 항목 -->
<title><bean:message key='LABEL.prompt'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var acSysDescAC;
function loadPage() 
{
    setTitle("assBasePointDetailDTO.assPointTypeDesc", "assBasePointDetailDTO.assPoint");
    //구분 자동완성
    acSysDescAC = acSysDesc("assBasePointDetailDTO.assPointTypeDesc","assBasePointDetailDTO.assPointTypeId","ASS_POINT_TYPE", true);
    //사용여부 자동완성
    acSysDesc("assBasePointDetailDTO.isUseDesc","assBasePointDetailDTO.isUseId","IS_USE", true);
    //재평가기준 자동완성
    acSysDesc("assBasePointDetailDTO.reAssBaseDesc","assBasePointDetailDTO.reAssBaseId","ASS_POINT_VALUE_TYPE", true);
    //For Save
    setForUpdate();
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAASSBASEPOINT_ID');
    assBasePointDetailForm.elements['assBasePointDetailDTO.isUseId'].value = 'Y';
    assBasePointDetailForm.elements['assBasePointDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('assBasePointDetailDTO.isUseId', 'assBasePointDetailDTO.isUseDesc', 'IS_USE', true);
    
    acSysDescAC.openLov();
}
function setSequenceVal(sequenceVal)
{
    assBasePointDetailForm.elements['assBasePointDetailDTO.assBasePointId'].value = sequenceVal;
    assBasePointDetailForm.elements['assBasePointListDTO.assBasePointId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	assBasePointDetailForm.elements['assBasePointListDTO.assBasePointId'].value = 
		assBasePointDetailForm.elements['assBasePointDetailDTO.assBasePointId'].value;
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
    if(ckCreate(currentPageId)) assBasePointDetailForm.strutsAction.value = "<%=AssBasePointDetailAction.DETAIL_INPUT%>";
    else assBasePointDetailForm.strutsAction.value = "<%=AssBasePointDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/assBasePointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assBasePointDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assBasePointDetailForm.elements['assBasePointDetailDTO.assBasePointId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("assBasePointDetailDTO.assPointTypeDesc", "assBasePointDetailDTO.assPoint");
    
}
function goTabPage(pageId) 
{
	 var form = document.assBasePointDetailForm;
	 goCommonTabPage(form, '' , pageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assBasePointDetailForm.elements['assBasePointDetailDTO.assBasePointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assBasePointDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBasePointListDTO.assBasePointId"/><!-- Key -->
<html:hidden property="assBasePointDetailDTO.reAssBaseId"/><!-- Key -->
<html:hidden property="assBasePointDetailDTO.assBasePointId"/><!-- Key -->
<html:hidden property="assBasePointDetailDTO.assPointTypeId"/>
<html:hidden property="assBasePointDetailDTO.isUseId"/>
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 구분 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.separation"/></label>
				<div class="input_box">
					<html:text property="assBasePointDetailDTO.assPointTypeDesc" tabindex="10"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="assBasePointDetailDTO.isUseDesc" tabindex="20" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 항목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.prompt"/></label>
				<div class="input_box">
					<html:text property="assBasePointDetailDTO.assPoint" tabindex="30"/>
				</div>
			</div>
			<!-- 정렬값 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="assBasePointDetailDTO.ordNo" tabindex="40"/>
				</div>
			</div>
			<!-- 재평가기준 -->
			<div class="field">
				<label><bean:message key="LABEL.reAssBase"/></label>
				<div class="input_box">
					<html:text property="assBasePointDetailDTO.reAssBaseDesc" tabindex="50" />
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
					<html:textarea property="assBasePointDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			<!-- 재평가 Script -->
			<div class="field_long">
				<label><bean:message key="LABEL.reAssScript"/></label>
				<div class="input_box">
					<html:textarea property="assBasePointDetailDTO.reAssScript" styleClass="ta50" tabindex="60" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
