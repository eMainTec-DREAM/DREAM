<%--===========================================================================
설비종류별 부위
author  kim21017
version $Id: maEqCtgSpecDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.categ.list.action.MaEqCtgSpecDetailAction"%>
<html>
<head>
<!-- 부위명-->
<title><bean:message key="TAB.maEqMstrSpecList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var eqCtgAsmbAc;
var isUse;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maEqCtgSpecDetailDTO.categ","maEqCtgSpecDetailDTO.prompt");
	setForUpdate();
	
    // 부위
	eqCtgAsmbAc = new autoC({"maEqCtgSpecDetailDTO.eqCtgAsmbDesc":"description"});
    eqCtgAsmbAc.setAcConditionMap({
		"comp_no":loginUser.compNo
      , "is_use" : "Y"
	});
    eqCtgAsmbAc.setAcDConditionMap({
    	"eqctg_id":"maEqCatalogCommonDTO.eqCtgId"
    });
    eqCtgAsmbAc.setTable("TAEQCTGASMB");
    eqCtgAsmbAc.setKeyName("maEqCtgSpecDetailDTO.eqCtgAsmbId");
    eqCtgAsmbAc.setAcResultMap({
        "maEqCtgSpecDetailDTO.eqCtgAsmbId":"eq_ctg_asmb_id"
    });
    eqCtgAsmbAc.init();

    // 사용여부
    acSysDesc("maEqCtgSpecDetailDTO.isUse","maEqCtgSpecDetailDTO.isUse","IS_USE",true);
    
    // 공통 제원 여부
    acSysDesc("maEqCtgSpecDetailDTO.isEqTypeSpec","maEqCtgSpecDetailDTO.isEqTypeSpec","IS_USE",true);

    
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQCTGSPEC_ID');

	setVal("maEqCtgSpecDetailDTO.isUse","Y");
	setVal("maEqCtgSpecDetailDTO.isEqTypeSpec","N");
}

function setSequenceVal(sequenceVal)
{
	maEqCtgSpecDetailForm.elements['maEqCtgSpecDetailDTO.eqCtgSpecId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maEqCtgSpecDetailForm.strutsAction.value = '<%=MaEqCtgSpecDetailAction.EQ_CTG_SPEC_DETAIL_INPUT%>';
	else maEqCtgSpecDetailForm.strutsAction.value = '<%= MaEqCtgSpecDetailAction.EQ_CTG_SPEC_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqCtgSpecDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqCtgSpecDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
	parent.goSearch();
    getTopPage().afterSaveAll(currentPageId);
}


function goAudtrailLink()
{
	var objectId = maEqCtgSpecDetailForm.elements['maEqCtgSpecDetailDTO.eqCtgSpecId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqCtgSpecDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/>
	<html:hidden property="maEqCtgSpecDetailDTO.eqCtgId"/>
	<html:hidden property="maEqCtgSpecDetailDTO.eqCtgSpecId"/>
	<html:hidden property="maEqCtgSpecDetailDTO.eqCtgAsmbId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
		<!-- 부위,분류,항목,단위,정렬값 -->
			<!-- 작업부위 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workPart"/></label>
				<div class="input_box">
					<html:text property="maEqCtgSpecDetailDTO.eqCtgAsmbDesc" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 분류 -->
			<div class="field">
				<label><bean:message key="LABEL.category"/></label>
				<div class="input_box">
					<html:text property="maEqCtgSpecDetailDTO.categ" tabindex="10"/>
				</div>
			</div>
			<!-- 항목 -->
			<div class="field">
				<label><bean:message key="LABEL.prompt"/></label>
				<div class="input_box">
					<html:text property="maEqCtgSpecDetailDTO.prompt" tabindex="20"/>
				</div>
			</div>
			<!-- 단위 -->
			<div class="field">
				<label><bean:message key="LABEL.uom"/></label>
				<div class="input_box">
					<html:text property="maEqCtgSpecDetailDTO.uom" tabindex="40"/>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqCtgSpecDetailDTO.ordNo" tabindex="50"/>
				</div>
			</div>
			<!-- 공통부위여부 -->
            <div class="field">
                <label><bean:message key="LABEL.isCommCtgSpec"/></label>
                <div class="input_box">
                    <html:text property="maEqCtgSpecDetailDTO.isEqTypeSpec" tabindex="55"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maEqCtgSpecDetailDTO.isUse" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>