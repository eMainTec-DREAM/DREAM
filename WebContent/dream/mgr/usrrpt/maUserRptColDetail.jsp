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
<%@page import="dream.mgr.usrrpt.action.MaUserRptColDetailAction"%>
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
	setTitle("maUserRptColDetailDTO.tableName");
	setForUpdate();
	
	/* taTable = new autoC({"maUserRptColDetailDTO.tableName":"table_name"});
	taTable.setAcDisplay("DESCRIPTION");
	taTable.setAcConditionMap({
 	   "is_use":"Y"
 	   });
	taTable.setTable("TACOL");
	taTable.setAcResultMap({
        "maUserRptColDetailDTO.tableId":"table_id"
        ,"maUserRptColDetailDTO.tableDesc":"description"
    });
	taTable.setAcResultLabel({
	    "maUserRptColDetailDTO.tableName":"LABEL.tableDesc"
	    ,"maUserRptColDetailDTO.tableDesc":"LABEL.tableName"
	}); 
	taTable.setKeyName("maUserRptColDetailDTO.tableId"); 
	taTable.init(); */
	
	acSysDesc("maUserRptColDetailDTO.colAlignDesc","maUserRptColDetailDTO.colAlign","ALIGN_TYPE", true);
	acSysDesc("maUserRptColDetailDTO.useYn","maUserRptColDetailDTO.useYn","IS_USE", true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('sqausrrptcol_id');
}

function goTabPage(pageId)
{
	var form = document.maUserRptColDetailForm;

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
	maUserRptColDetailForm.elements['maUserRptColDetailDTO.usrrptcolId'].value = sequenceVal;
	maUserRptColDetailForm.elements['maUserRptCommonDTO.usrrptcolId'].value = sequenceVal;
	//maUserRptColDetailForm.elements['maUserRptColDetailDTO.usrrptlistId'].value = maUserRptColDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;

	maUserRptColDetailForm.strutsAction.value = '<%=MaUserRptColDetailAction.USER_COL_DETAIL_INPUT%>';
	var actionUrl = contextPath + "/maUserRptColDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maUserRptColDetailForm), 'afterSave');
}

function goSave()
{	
	if(checkValidation()) return;
	
	maUserRptColDetailForm.elements['maUserRptColDetailDTO.usrrptlistId'].value = maUserRptColDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;
	maUserRptColDetailForm.elements['maUserRptColDetailDTO.usrrpttabId'].value = maUserRptColDetailForm.elements['maUserRptCommonDTO.usrrpttabId'].value;

	var userColId = maUserRptColDetailForm.elements['maUserRptCommonDTO.usrrptcolId'].value;
	
	if(userColId == "") goInput(); 
	else
	{
		maUserRptColDetailForm.strutsAction.value = '<%= MaUserRptColDetailAction.USER_COL_DETAIL_UPDATE %>';
		var actionUrl = contextPath + "/maUserRptColDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maUserRptColDetailForm), 'afterSave');
	}
	
	
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maUserRptColDetailForm.elements['maUserRptColDetailDTO.tabcolId'].value, maUserRptColDetailForm.elements['maUserRptColDetailDTO.usrrptcolId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
	
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUserRptColDetailForm.elements['maUserRptColDetailDTO.tabcolId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maUserRptColDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptlistId"/>
	<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptcolId"/>
	<html:hidden property="maUserRptCommonDTO.stepNum"/>
	<html:hidden property="maUserRptColDetailDTO.usrrptlistId"/><!-- Key -->
	<html:hidden property="maUserRptColDetailDTO.usrrpttabId"/>
	<html:hidden property="maUserRptColDetailDTO.usrrptcolId"/>
	<html:hidden property="maUserRptColDetailDTO.tabcolId"/>
	<html:hidden property="maUserRptColDetailDTO.tableId"/>
	<html:hidden property="maUserRptColDetailDTO.colAlign"/>
	<html:hidden property="maUserRptColDetailDTO.isDisplay"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 테이블명 -->
            <div class="field">
                <label><bean:message key="LABEL.tableDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptColDetailDTO.tableName" tabindex="10" readonly="true"/>
                </div>
            </div>
            <!-- 컬럼명 -->
            <div class="field">
                <label><bean:message key="LABEL.columnName"/></label>
                <div class="input_read">
                    <html:text property="maUserRptColDetailDTO.columnName" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 컬럼설명 -->
            <div class="field_long">
                <label><bean:message key="LABEL.colDesc"/></label>
                <div class="input_read">
                    <html:text property="maUserRptColDetailDTO.colDesc" tabindex="30" readonly="true"/>
                </div>
            </div>
            <!-- 레포트표시여부-->
            <div class="field">
                <label><bean:message key="LABEL.isDisplay"/></label>
                <div class="input_box">
                    <html:text property="maUserRptColDetailDTO.useYn" tabindex="40"/>
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
                    <html:text property="maUserRptColDetailDTO.stepNum" tabindex="50" styleClass="num"/>
                </div>
            </div>
            <!-- 레포트타이틀 -->
            <div class="field">
                <label><bean:message key="LABEL.title"/></label>
                <div class="input_box">
                    <html:text property="maUserRptColDetailDTO.colAlias" tabindex="60"/>
                </div>
            </div>
            <!-- Font Size -->
            <div class="field">
                <label><bean:message key="LABEL.colSize"/></label>
                <div class="input_box">
                    <html:text property="maUserRptColDetailDTO.colSize" tabindex="70" styleClass="num"/>
                </div>
            </div>
            <!-- Width(px) -->
            <div class="field">
                <label><bean:message key="LABEL.colWidth"/></label>
                <div class="input_box">
                    <html:text property="maUserRptColDetailDTO.colWidth" tabindex="80" styleClass="num"/>
                </div>
            </div>
            <!-- align -->
            <div class="field">
                <label><bean:message key="LABEL.colAlign"/></label>
                <div class="input_box">
                    <html:text property="maUserRptColDetailDTO.colAlignDesc" tabindex="90"/>
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