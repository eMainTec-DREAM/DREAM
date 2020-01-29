<%--===========================================================================
구성자재
author  kim21017
version $Id: maEqMstrMoldModHistDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.asset.list.action.MaEqMstrMoldModHistDetailAction"%>
<html>
<head>
<!-- 구성자재-->
<title><bean:message key="TAB.maEqMstrMoldModHistList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	setTitle("maEqMstrMoldModHistDetailDTO.modCnt","maEqMstrMoldModHistDetailDTO.capacity");
	setForUpdate();
	getParentEqId();
}
//상세 설비번호 가져오기
function getParentEqId(){
	maEqMstrMoldModHistDetailForm.elements['maEqMstrMoldModHistDetailDTO.equipId'].value = parent.getEquipId();
}
function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQMOLDMODHIST_ID');
}

function setSequenceVal(sequenceVal)
{
	maEqMstrMoldModHistDetailForm.elements['maEqMstrMoldModHistDetailDTO.eqMoldModHistId'].value = sequenceVal;
}

function goSave(){
	if(ckCreate(currentPageId)) maEqMstrMoldModHistDetailForm.strutsAction.value = '<%=MaEqMstrMoldModHistDetailAction.EQ_MSTR_MOLDPROD_DETAIL_INPUT%>';
	else maEqMstrMoldModHistDetailForm.strutsAction.value = '<%= MaEqMstrMoldModHistDetailAction.EQ_MSTR_MOLDPROD_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrMoldModHistDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrMoldModHistDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
//     parent.goSearch();
    if (parent.findGridRow)	parent.findGridRow(maEqMstrMoldModHistDetailForm.elements['maEqMstrMoldModHistDetailDTO.eqMoldModHistId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function goAudtrailLink()
{
	var objectId = maEqMstrMoldModHistDetailForm.elements['maEqMstrMoldModHistDetailDTO.eqMoldModHistId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrMoldModHistDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrMoldModHistDetailDTO.eqMoldModHistId"/>
	<html:hidden property="maEqMstrMoldModHistDetailDTO.equipId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
		     <!-- 차수 -->
			<div class="field">
				<label><bean:message key="LABEL.modCnt"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldModHistDetailDTO.modCnt" tabindex="10" onkeydown="javascript:onlyNumberInput(event);" styleClass="num"/>
				</div>
			</div>
			<!-- Cavity -->
			<div class="field">
				<label><bean:message key="LABEL.cavity"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldModHistDetailDTO.capacity" tabindex="20" />
				</div>
			</div>
			<!-- 일생산량 -->
			<div class="field">
				<label><bean:message key="LABEL.dailyOutPut"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldModHistDetailDTO.outPut" tabindex="30" onkeydown="javascript:onlyNumberInput(event);" styleClass="num"/>
				</div>
			</div>
			<!-- 소유권 -->
			<div class="field">
				<label><bean:message key="LABEL.ownerShip"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldModHistDetailDTO.ownerShip" tabindex="40" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqMstrMoldModHistDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>