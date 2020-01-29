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
<%@page import="dream.mgr.usrrpt.action.MaUserRptParamDetailAction"%>
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
	setTitle("maUserRptParamDetailDTO.tableName");
	setForUpdate();

	acSysDesc("maUserRptParamDetailDTO.whrConOperatorDesc","maUserRptParamDetailDTO.whrConOperator","TAB_CON_OPERATOR", true);
	acSysDesc("maUserRptParamDetailDTO.useYn","maUserRptParamDetailDTO.useYn","IS_USE", true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('sqausrrptwhr_id');
}

function goTabPage(pageId)
{
	var form = document.maUserRptParamDetailForm;

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
	maUserRptParamDetailForm.elements['maUserRptParamDetailDTO.usrrptwhrId'].value = sequenceVal;
	maUserRptParamDetailForm.elements['maUserRptCommonDTO.usrrptwhrId'].value = sequenceVal;
	//maUserRptParamDetailForm.elements['maUserRptParamDetailDTO.usrrptlistId'].value = maUserRptParamDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;

	maUserRptParamDetailForm.strutsAction.value = '<%=MaUserRptParamDetailAction.USER_PARAM_DETAIL_INPUT%>';
	var actionUrl = contextPath + "/maUserRptParamDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maUserRptParamDetailForm), 'afterSave');
}

function goSave()
{	
	if(checkValidation()) return;
	
	maUserRptParamDetailForm.elements['maUserRptParamDetailDTO.usrrptlistId'].value = maUserRptParamDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;
	maUserRptParamDetailForm.elements['maUserRptParamDetailDTO.usrrpttabId'].value = maUserRptParamDetailForm.elements['maUserRptCommonDTO.usrrpttabId'].value;

	var userWhrId = maUserRptParamDetailForm.elements['maUserRptCommonDTO.usrrptwhrId'].value;
	
	if(userWhrId == "") goInput(); 
	else
	{
		maUserRptParamDetailForm.strutsAction.value = '<%= MaUserRptParamDetailAction.USER_PARAM_DETAIL_UPDATE %>';
		var actionUrl = contextPath + "/maUserRptParamDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maUserRptParamDetailForm), 'afterSave');
	}
	
	
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maUserRptParamDetailForm.elements['maUserRptParamDetailDTO.tabcolId'].value, maUserRptParamDetailForm.elements['maUserRptParamDetailDTO.usrrptwhrId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
	
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUserRptParamDetailForm.elements['maUserRptParamDetailDTO.tabcolId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maUserRptParamDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptlistId"/>
	<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptwhrId"/>
	<html:hidden property="maUserRptCommonDTO.stepNum"/>
	<html:hidden property="maUserRptParamDetailDTO.usrrptlistId"/><!-- Key -->
	<html:hidden property="maUserRptParamDetailDTO.usrrpttabId"/>
	<html:hidden property="maUserRptParamDetailDTO.usrrptwhrId"/>
	<html:hidden property="maUserRptParamDetailDTO.tabcolId"/>
	<html:hidden property="maUserRptParamDetailDTO.tableId"/>
	<html:hidden property="maUserRptParamDetailDTO.isDisplay"/>
	<html:hidden property="maUserRptParamDetailDTO.whrConOperator"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 테이블명 -->
            <div class="field">
                <label><bean:message key="LABEL.tableDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptParamDetailDTO.tableName" tabindex="10" readonly="true"/>
                </div>
            </div>
            <!-- 컬럼명 -->
            <div class="field">
                <label><bean:message key="LABEL.columnName"/></label>
                <div class="input_read">
                    <html:text property="maUserRptParamDetailDTO.columnName" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 컬럼설명 -->
            <div class="field_long">
                <label><bean:message key="LABEL.colDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptParamDetailDTO.colDesc" tabindex="30" readonly="true"/>
                </div>
            </div>
            <!-- 레포트표시여부-->
            <div class="field">
                <label><bean:message key="LABEL.isFilter"/></label>
                <div class="input_box">
                    <html:text property="maUserRptParamDetailDTO.useYn" tabindex="40"/>
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
                    <html:text property="maUserRptParamDetailDTO.stepNum" tabindex="50" styleClass="num"/>
                </div>
            </div>
            <!-- 조건연산자 -->
            <div class="field">
                <label><bean:message key="LABEL.tabConOperatorDesc"/></label>
                <div class="input_box">
                    <html:text property="maUserRptParamDetailDTO.whrConOperatorDesc" tabindex="60"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 값 -->
            <div class="field">
                <label><bean:message key="LABEL.conValue"/></label>
                <div class="input_box">
                    <html:text property="maUserRptParamDetailDTO.conValue" tabindex="70"/>
                </div>
            </div>
            
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>