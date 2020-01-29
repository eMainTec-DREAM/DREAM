<%--===========================================================================
작업계획목록 - 안전작업
author  syyang
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WoPlanWoLetDetailAction"%>
<html>
<head>
<!-- 안전작업-->
<title><bean:message key="TAB.woPlanWoLetList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var woLetCtgAc;

function loadPage() 
{
	setTitle("woPlanWoLetDetailDTO.woLetCtgTypeDesc", "woPlanWoLetDetailDTO.woLetCtgNo");
	
	setForUpdate();
	
	woLetCtgAc = new autoC({"woPlanWoLetDetailDTO.woLetCtgDesc":"description"});
	woLetCtgAc.setTable("TAWOLETCTG");
	woLetCtgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	woLetCtgAc.setKeyName("woPlanWoLetDetailDTO.woLetCtgId");
	woLetCtgAc.setAcResultMap({
        "woPlanWoLetDetailDTO.woLetCtgId":"woLetCtgId"
        ,"woPlanWoLetDetailDTO.woLetCtgDesc":"description"
        ,"woPlanWoLetDetailDTO.woLetCtgType":"woLetCtgType"
        ,"woPlanWoLetDetailDTO.woLetCtgTypeDesc":"woLetCtgTypeDesc"
    });
	woLetCtgAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else {
		goUpdate();
	}
}

function goInput()
{
	// 시퀀스를 조회한다. 시퀀스 생성 안되어 있음
 	sequenceNextVal('SQAWOWOLETLIST_ID');
	
	woLetCtgAc.openLov();
	
}

function setSequenceVal(sequenceVal)
{
	woPlanWoLetDetailForm.elements['woPlanWoLetDetailDTO.woWoLetListId'].value = sequenceVal;
}

function goUpdate()
{
	
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	//중복 안전작업 체크
	validWoLet();
	if(isValid!='0') return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) woPlanWoLetDetailForm.strutsAction.value = "<%=WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_INPUT%>";
    else woPlanWoLetDetailForm.strutsAction.value = "<%=WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/woPlanWoLetDetail.do";
	
	XMLHttpPost(actionUrl, FormQueryString(woPlanWoLetDetailForm), 'afterSave');
		
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(woPlanWoLetDetailForm.elements['woPlanWoLetDetailDTO.woWoLetListId'].value);
    getTopPage().afterSaveAll(currentPageId);

	setTitle("woPlanWoLetDetailDTO.woLetCtgTypeDesc", "woPlanWoLetDetailDTO.woLetCtgNo");
}

function validWoLet(){
	var actionUrl = contextPath + "/woPlanWoLetDetail.do";
	var param =  "&strutsAction=" + '<%= WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_CHECK %>'
	          +  "&" + FormQueryString(woPlanWoLetDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidWoLet');
}

var isValid;
function afterValidWoLet(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(isValid != '0')
    {
    	closeModal();
		
    	alertMessage1("<bean:message key='LABEL.woLet'/>이 <bean:message key='MESSAGE.MSG0009'/>");
		
		woPlanWoLetDetailForm.elements['woPlanWoLetDetailDTO.woLetCtgId'].value = '';
		woPlanWoLetDetailForm.elements['woPlanWoLetDetailDTO.woLetCtgDesc'].value = '';
		woPlanWoLetDetailForm.elements['woPlanWoLetDetailDTO.woLetCtgType'].value = '';
    }
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = woPlanWoLetDetailForm.elements['woPlanWoLetDetailDTO.woWoLetListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/woPlanWoLetDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="woPlanCommonDTO.wkOrId"/>
	<html:hidden property="woPlanWoLetDetailDTO.woWoLetListId"/>
	<html:hidden property="woPlanWoLetDetailDTO.woLetCtgId"/>
	<html:hidden property="woPlanWoLetDetailDTO.woLetCtgType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 안전작업서 명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woLetCtgDesc"/></label>
				<div class="input_box">
					<html:text property="woPlanWoLetDetailDTO.woLetCtgDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 안전작업유형 -->
			<div class="field">
				<label><bean:message key="LABEL.woLetCtgType"/></label>
				<div class="input_read">
					<html:text property="woPlanWoLetDetailDTO.woLetCtgTypeDesc" tabindex="20"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="woPlanWoLetDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>