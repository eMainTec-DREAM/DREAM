<%--===========================================================================
설비종류별 부위
author  kim21017
version $Id: maEqCtgAsmbDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.categ.list.action.MaEqCtgAsmbDetailAction"%>
<html>
<head>
<!-- 부위명-->
<title><bean:message key="LABEL.ctgAsmbName"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var pEqAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maEqCtgAsmbDetailDTO.eqCtgAsmbDesc");
	setForUpdate();
	
	// 사용여부
	acSysDesc("maEqCtgAsmbDetailDTO.isUse","maEqCtgAsmbDetailDTO.isUse","IS_USE",true);
	
	// 공통부위여부
	acSysDesc("maEqCtgAsmbDetailDTO.isEqTypeAsmb","maEqCtgAsmbDetailDTO.isEqTypeAsmb","IS_USE",true);
	
	// 상위부위명 자동완성
    pEqAc = new autoC({"maEqCtgAsmbDetailDTO.peqCtgAsmbDesc":"description"});
    pEqAc.setTable("TAEQCTGASMB");
    pEqAc.setAcConditionMap({
       "comp_no": loginUser.compNo
     , "is_use" : "Y"
    });
    pEqAc.setAcDConditionMap({
       "eqctg_id": "maEqCatalogCommonDTO.eqCtgId"
    });
    pEqAc.setKeyName("maEqCtgAsmbDetailDTO.peqCtgAsmbId");
    pEqAc.setAcResultMap({
    	"maEqCtgAsmbDetailDTO.peqCtgAsmbId":"eq_ctg_asmb_id"
    });
    pEqAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQ_CTG_ASMB_ID');
	maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.isUse'].value = 'Y';
	maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.isEqTypeAsmb'].value = 'N';
	maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.peqCtgAsmbId'].value = maEqCtgAsmbDetailForm.elements['maEqCtgAsmbListDTO.detailPasmbId'].value;
	maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.peqCtgAsmbDesc'].value = maEqCtgAsmbDetailForm.elements['maEqCtgAsmbListDTO.detailPasmbDesc'].value;
	maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.eqCtgId'].value = maEqCtgAsmbDetailForm.elements['maEqCatalogCommonDTO.eqCtgId'].value;
}

function setSequenceVal(sequenceVal)
{
	maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.eqCtgAsmbId'].value = sequenceVal;
	maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.eqCtgAsmbNo'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maEqCtgAsmbDetailForm.strutsAction.value = '<%=MaEqCtgAsmbDetailAction.EQ_CTG_ASMB_DETAIL_INPUT%>';
	else maEqCtgAsmbDetailForm.strutsAction.value = '<%= MaEqCtgAsmbDetailAction.EQ_CTG_ASMB_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqCtgAsmbDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqCtgAsmbDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    
    if (parent.findGridRow)	parent.findGridRow(maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.eqCtgAsmbId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

function goAudtrailLink()
{
	var objectId = maEqCtgAsmbDetailForm.elements['maEqCtgAsmbDetailDTO.eqCtgAsmbId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqCtgAsmbDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/>
	<html:hidden property="maEqCtgAsmbDetailDTO.eqCtgId"/>
	<html:hidden property="maEqCtgAsmbDetailDTO.eqCtgAsmbId"/>
	<html:hidden property="maEqCtgAsmbDetailDTO.peqCtgAsmbId"/>
	<html:hidden property="maEqCtgAsmbDetailDTO.fullDesc"/>
	<html:hidden property="maEqCtgAsmbListDTO.detailPasmbId"/>
	<html:hidden property="maEqCtgAsmbListDTO.detailPasmbDesc"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부위명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.ctgAsmbName"/></label>
				<div class="input_box">
					<html:text property="maEqCtgAsmbDetailDTO.eqCtgAsmbDesc" tabindex="10"/>
				</div>
			</div>
			<!-- 부위코드 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.asmbNo"/></label>
				<div class="input_read">
					<html:text property="maEqCtgAsmbDetailDTO.eqCtgAsmbNo" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 상위부위명 -->
			<div class="field">
				<label><bean:message key="LABEL.pEqCtgAsmb"/></label>
				<div class="input_box">
					<html:text property="maEqCtgAsmbDetailDTO.peqCtgAsmbDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqCtgAsmbDetailDTO.ordNo" tabindex="40"/>
				</div>
			</div>
            <!-- 공통부위여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isCommCtgAsmb"/></label>
				<div class="input_box">
					<html:text property="maEqCtgAsmbDetailDTO.isEqTypeAsmb" tabindex="50"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
            <!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maEqCtgAsmbDetailDTO.isUse" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부위설명 -->
			<div class="field_long">
				<label><bean:message key="LABEL.asmbRemark"/></label>
				<div class="input_box">
					<html:textarea property="maEqCtgAsmbDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>