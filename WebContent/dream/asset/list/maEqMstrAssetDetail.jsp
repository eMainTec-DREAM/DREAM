<%--===========================================================================
관련자산
author  kim21017
version $Id: maEqMstrAssetDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.list.action.MaEqMstrAssetDetailAction"%>
<html>
<head>
<!-- 관련자산-->
<title><bean:message key="TAB.maEqMstrAssetList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var eqAstAc;
var isNew = false;
function loadPage() 
{
	setTitle("maEqMstrAssetDetailDTO.assetNo","maEqMstrAssetDetailDTO.assetDesc");
	setForUpdate();
	
	eqAstAc = new autoC({"maEqMstrAssetDetailDTO.assetNo":"description"});
	eqAstAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	   });
	eqAstAc.setTable("TAASSET");
	eqAstAc.setKeyName("maEqMstrAssetDetailDTO.assetId");
	eqAstAc.setAcResultMap({
        "maEqMstrAssetDetailDTO.assetId":"asset_id"
       	,"maEqMstrAssetDetailDTO.assetDesc":"description"
       	,"maEqMstrAssetDetailDTO.acqDate":"acqDate"
       	,"maEqMstrAssetDetailDTO.buyerAmt":"buyerAmt"
       	,"maEqMstrAssetDetailDTO.depAmt":"depAmt"
       	,"maEqMstrAssetDetailDTO.resAmt":"resAmt"
    });
	eqAstAc.init();
	
	if(ckCreate(currentPageId)) goInput();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQASSET_ID');
	
	eqAstAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	if(isNew) maEqMstrAssetDetailForm.elements['maEqMstrAssetDetailDTO.oldEqAssetId'].value = maEqMstrAssetDetailForm.elements['maEqMstrAssetDetailDTO.eqAssetId'].value;		
		
	maEqMstrAssetDetailForm.elements['maEqMstrAssetDetailDTO.eqAssetId'].value = sequenceVal;
}

function goSave(){
	if(ckCreate(currentPageId)) maEqMstrAssetDetailForm.strutsAction.value = '<%=MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_INPUT%>';
	else maEqMstrAssetDetailForm.strutsAction.value = '<%= MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrAssetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrAssetDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maEqMstrAssetDetailForm.elements['maEqMstrAssetDetailDTO.eqAssetId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

/*
 * 생성
 */
function goCreate()
{
	parent.goCreate();
}

/*
 * 복사
 */
function goCopycreate()
{
	isNew = true;
	sequenceNextVal('SQAEQASSET_ID');
	var form = maEqMstrAssetDetailForm;
	var url = contextPath + "/maEqMstrAssetDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
		dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				form.strutsAction.value = '<%=MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_COPY%>';
			    XMLHttpPostVal(url, FormQueryString(form), 'afterCopycreate');
			}
		});
    }
}
function afterCopycreate()
{
	isNew = false;
	var newEqAssetId = maEqMstrAssetDetailForm.elements['maEqMstrAssetDetailDTO.eqAssetId'].value;
	
	// 상세 닫기
	goClose('maEqMstrAssetDetail');
	
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
	
	if(""== newEqAssetId || "undefined"== typeof newEqAssetId)
	{
		// 전체 복사인 경우
		parent.parent.frames["tabFrameTAB.maEqMstrAssetList"].goSearch();
	}
	else
	{	// Unit 복사인 경우
		if(parent.setKeyAftercopy) parent.setKeyAftercopy(newEqAssetId);
	}
	
}

function goAudtrailLink()
{
	var objectId = maEqMstrAssetDetailForm.elements['maEqMstrAssetDetailDTO.eqAssetId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrAssetDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrCommonDTO.equipId"/>
	<html:hidden property="maEqMstrAssetDetailDTO.eqAssetId"/>
	<html:hidden property="maEqMstrAssetDetailDTO.oldEqAssetId"/>
	<html:hidden property="maEqMstrAssetDetailDTO.assetId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 자산번호 -->
			<div class="field">
				<label><bean:message key="LABEL.assetNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrAssetDetailDTO.assetNo" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 자산명 -->
			<div class="field">
				<label><bean:message key="LABEL.assetName"/></label>
				<div class="input_read">
					<html:text property="maEqMstrAssetDetailDTO.assetDesc" readonly="true"/>
				</div>
			</div>
			<!-- 취득일자 -->
			<div class="field">
				<label><bean:message key="LABEL.acqDate"/></label>
				<div class="input_read">
					<html:text property="maEqMstrAssetDetailDTO.acqDate" readonly="true" />
				</div>
			</div>
			<!-- 취득가액 -->
			<div class="field">
				<label><bean:message key="LABEL.buyerAmt"/></label>
				<div class="input_read">
					<html:text property="maEqMstrAssetDetailDTO.buyerAmt" readonly="true" styleClass="num"/>
				</div>
			</div>
			<!-- 감가상각액 -->
			<div class="field">
				<label><bean:message key="LABEL.depAmt"/></label>
				<div class="input_read">
					<html:text property="maEqMstrAssetDetailDTO.depAmt" readonly="true" styleClass="num"/>
				</div>
			</div>
			<!-- 잔존가액 -->
			<div class="field">
				<label><bean:message key="LABEL.resAmt"/></label>
				<div class="input_read">
					<html:text property="maEqMstrAssetDetailDTO.resAmt" readonly="true" styleClass="num"/>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>