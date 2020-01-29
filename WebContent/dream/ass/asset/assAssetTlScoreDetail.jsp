<%--===========================================================================
평가점수
author  youngjoo38
version $Id: assAssetScoreDetail.jsp,v 1.1 2017/08/28 12:37:40 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.asset.action.AssAssetScoreDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 평가점수 -->
<title><bean:message key='LABEL.prompt'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var assEvalDescAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setDisable(document.getElementsByName("disableDiv"));
	
    setTitle("assAssetScoreDetailDTO.assBasePointDesc");
    
    
    // 선택 자동완성
    assEvalDescAc = new autoC({"assAssetScoreDetailDTO.assEvalDesc":"ass_eval"});
    assEvalDescAc.setTable("TAASSBASEPVAL");
    assEvalDescAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
        , "is_use" : "Y"
    });
    assEvalDescAc.setAcDConditionMap({
        "assbaselist_id" : "assAssetScoreDetailDTO.assBaseListId"
        , "assbasepoint_id" : "assAssetScoreDetailDTO.assBasePointId"
    });
    
    assEvalDescAc.setAcResultMap({
        "assAssetScoreDetailDTO.assEvalDesc":"assEval" /* 선택항목 */
        , "assAssetScoreDetailDTO.eqAssPValDesc":"evalValue" /* 점수 */
        , "assAssetScoreDetailDTO.assBasePValId":"assBasePvalId" /* assBasePvalId */
    });
    assEvalDescAc.setKeyName("assAssetScoreDetailDTO.assEvalDesc");
    assEvalDescAc.init();
    
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEQASSPVAL_ID');
}
function setSequenceVal(sequenceVal)
{
    assAssetScoreDetailForm.elements['assAssetScoreDetailDTO.eqassPValId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) assAssetScoreDetailForm.strutsAction.value = "<%=AssAssetScoreDetailAction.DETAIL_INPUT%>";
    else assAssetScoreDetailForm.strutsAction.value = "<%=AssAssetScoreDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/assAssetTlScoreDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assAssetScoreDetailForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assAssetScoreDetailForm.elements['assAssetScoreDetailDTO.eqAssPValId'].value);
    parent.parent.gradeUpdate();
    parent.parent.scoreUpdate();
    if (parent.parent.parent.findGridRow)	parent.parent.parent.findGridRow(assAssetScoreDetailForm.elements['assAssetCommonDTO.eqasslistId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("assAssetScoreDetailDTO.assBasePointDesc");
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assAssetTlScoreDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="assAssetCommonDTO.eqasslistId"/><!-- Key -->
<html:hidden property="assAssetScoreDetailDTO.eqAssPValId"/><!-- Key -->
<html:hidden property="assAssetScoreDetailDTO.assPointTypeId"/> <!-- 항목구분ID -->
<html:hidden property="assAssetScoreDetailDTO.assBasePointId"/> <!-- 항목ID -->
<html:hidden property="assAssetScoreDetailDTO.assEvalId"/> <!-- 선택ID -->
<html:hidden property="assAssetScoreDetailDTO.assBaseListId"/> <!-- 평가기준LIST ID -->
<html:hidden property="assAssetScoreDetailDTO.assBasePValId"/> <!-- 평가항목별 평가기준 ID -->
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 구분 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.separation"/></label>
				<div class="input_box">
					<html:text property="assAssetScoreDetailDTO.assPointTypeDesc" tabindex="10"/>
				</div>
			</div>
			<!-- 항목 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.prompt"/></label>
				<div class="input_box">
					<html:text property="assAssetScoreDetailDTO.assBasePointDesc" tabindex="20"/>
				</div>
			</div>
			<!-- 선택 -->
			<div class="field">
				<label><bean:message key="LABEL.select"/></label>
				<div class="input_box">
					<html:text property="assAssetScoreDetailDTO.assEvalDesc" tabindex="30"/>
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
				
			</div>
			<!-- 평가점수 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.assScore"/></label>
				<div class="input_box">
					<html:text property="assAssetScoreDetailDTO.eqAssPValDesc" tabindex="40" styleClass="num" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="assAssetScoreDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
