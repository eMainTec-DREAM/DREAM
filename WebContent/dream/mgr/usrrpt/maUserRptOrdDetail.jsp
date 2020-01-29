<%--===========================================================================

author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.mgr.usrrpt.action.MaUserRptOrdDetailAction"%>
<html>
<head>
<!--  -->
<title><bean:message key="LABEL.equipment"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
/*자동완성 */
var taTable;
function loadPage() 
{
	//if(ckCreate(currentPageId)) goInput();
	//else goUpdate();

	setFunction();
}

function setFunction()
{
	setTitle("maUserRptOrdDetailDTO.tableName");
	setForUpdate();

	acSysDesc("maUserRptOrdDetailDTO.sortTypeDesc","maUserRptOrdDetailDTO.sortType","SORT_TYPE", true);
	acSysDesc("maUserRptOrdDetailDTO.useYn","maUserRptOrdDetailDTO.useYn","IS_USE", true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('sqausrrptord_id');
}

function goTabPage(pageId)
{
	var form = document.maUserRptOrdDetailForm;

	goCommonTabPage(form, '' , pageId);  
}

/**
 * 수정
 */
function goUpdate()
{

}

function setSequenceVal(sequenceVal)
{
	maUserRptOrdDetailForm.elements['maUserRptOrdDetailDTO.usrrptordId'].value = sequenceVal;
	maUserRptOrdDetailForm.elements['maUserRptCommonDTO.usrrptordId'].value = sequenceVal;
	//maUserRptOrdDetailForm.elements['maUserRptOrdDetailDTO.usrrptlistId'].value = maUserRptOrdDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;

	maUserRptOrdDetailForm.strutsAction.value = '<%=MaUserRptOrdDetailAction.USER_ORD_DETAIL_INPUT%>';
	var actionUrl = contextPath + "/maUserRptOrdDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maUserRptOrdDetailForm), 'afterSave');
}

function goSave()
{	
	if(checkValidation()) return;
	
	maUserRptOrdDetailForm.elements['maUserRptOrdDetailDTO.usrrptlistId'].value = maUserRptOrdDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;
	maUserRptOrdDetailForm.elements['maUserRptOrdDetailDTO.usrrpttabId'].value = maUserRptOrdDetailForm.elements['maUserRptCommonDTO.usrrpttabId'].value;

	var userColId = maUserRptOrdDetailForm.elements['maUserRptCommonDTO.usrrptordId'].value;
	
	if(userColId == "") goInput(); 
	else
	{
		maUserRptOrdDetailForm.strutsAction.value = '<%= MaUserRptOrdDetailAction.USER_ORD_DETAIL_UPDATE %>';
		var actionUrl = contextPath + "/maUserRptOrdDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maUserRptOrdDetailForm), 'afterSave');
	}
	
	
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maUserRptOrdDetailForm.elements['maUserRptOrdDetailDTO.tabcolId'].value, maUserRptOrdDetailForm.elements['maUserRptOrdDetailDTO.usrrptordId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
	
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUserRptOrdDetailForm.elements['maUserRptOrdDetailDTO.tabcolId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maUserRptOrdDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptlistId"/>
	<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptordId"/>
	<html:hidden property="maUserRptCommonDTO.stepNum"/>
	<html:hidden property="maUserRptOrdDetailDTO.usrrptlistId"/><!-- Key -->
	<html:hidden property="maUserRptOrdDetailDTO.usrrpttabId"/>
	<html:hidden property="maUserRptOrdDetailDTO.usrrptordId"/>
	<html:hidden property="maUserRptOrdDetailDTO.tabcolId"/>
	<html:hidden property="maUserRptOrdDetailDTO.tableId"/>
	<html:hidden property="maUserRptOrdDetailDTO.colAlign"/>
	<html:hidden property="maUserRptOrdDetailDTO.isDisplay"/>
	<html:hidden property="maUserRptOrdDetailDTO.sortType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 테이블명 -->
            <div class="field">
                <label><bean:message key="LABEL.tableDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptOrdDetailDTO.tableName" tabindex="10" readonly="true"/>
                </div>
            </div>
            <!-- 컬럼명 -->
            <div class="field">
                <label><bean:message key="LABEL.columnName"/></label>
                <div class="input_read">
                    <html:text property="maUserRptOrdDetailDTO.columnName" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 컬럼설명 -->
            <div class="field_long">
                <label><bean:message key="LABEL.colDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptOrdDetailDTO.colDesc" tabindex="30" readonly="true"/>
                </div>
            </div>
            <!-- 레포트표시여부-->
            <div class="field">
                <label><bean:message key="LABEL.isDisplay"/></label>
                <div class="input_box">
                    <html:text property="maUserRptOrdDetailDTO.useYn" tabindex="40"/>
                	<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 순서 -->
            <div class="field">
                <label><bean:message key="LABEL.seqNo"/></label>
                <div class="input_box">
                    <html:text property="maUserRptOrdDetailDTO.stepNum" tabindex="50" styleClass="num"/>
                </div>
            </div>
            <!-- Order Type -->
            <div class="field">
                <label><bean:message key="LABEL.sortType"/></label>
                <div class="input_box">
                    <html:text property="maUserRptOrdDetailDTO.sortTypeDesc" tabindex="90"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>