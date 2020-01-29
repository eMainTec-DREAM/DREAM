<%--===========================================================================
구성자재
author  kim21017
version $Id: maEqMstrMoldProdDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.asset.list.action.MaEqMstrMoldProdDetailAction"%>
<html>
<head>
<!-- 구성자재-->
<title><bean:message key="TAB.maEqMstrMoldProdList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	setTitle("maEqMstrMoldProdDetailDTO.productNo","maEqMstrMoldProdDetailDTO.productDesc");
	setForUpdate();
	getParentEqId();
}
//상세 설비번호 가져오기
function getParentEqId(){
	maEqMstrMoldProdDetailForm.elements['maEqMstrMoldProdDetailDTO.equipId'].value = parent.getEquipId();
}
function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQPRODUCT_ID');
}

function setSequenceVal(sequenceVal)
{
	maEqMstrMoldProdDetailForm.elements['maEqMstrMoldProdDetailDTO.eqMoldProductId'].value = sequenceVal;
}

function goSave(){
	if(ckCreate(currentPageId)) maEqMstrMoldProdDetailForm.strutsAction.value = '<%=MaEqMstrMoldProdDetailAction.EQ_MSTR_MOLDPROD_DETAIL_INPUT%>';
	else maEqMstrMoldProdDetailForm.strutsAction.value = '<%= MaEqMstrMoldProdDetailAction.EQ_MSTR_MOLDPROD_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrMoldProdDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrMoldProdDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
//     parent.goSearch();
    if (parent.findGridRow)	parent.findGridRow(maEqMstrMoldProdDetailForm.elements['maEqMstrMoldProdDetailDTO.eqMoldProductId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function goAudtrailLink()
{
	var objectId = maEqMstrMoldProdDetailForm.elements['maEqMstrMoldProdDetailDTO.eqMoldProductId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrMoldProdDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrMoldProdDetailDTO.eqMoldProductId"/>
	<html:hidden property="maEqMstrMoldProdDetailDTO.equipId"/>
	<html:hidden property="maEqMstrMoldProdDetailDTO.ordNo"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 품목코드 -->
			<div class="field">
				<label><bean:message key="LABEL.productNo"/></label>  
				<div class="input_box">
					<html:text property="maEqMstrMoldProdDetailDTO.productNo"  tabindex="10" />
				</div>
			</div>
			<!-- 품목명 -->
			<div class="field">
				<label><bean:message key="LABEL.productDesc"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldProdDetailDTO.productDesc"  tabindex="20"  />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqMstrMoldProdDetailDTO.remark" styleClass="ta50" tabindex="30" />
				</div>
			</div>
			
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>