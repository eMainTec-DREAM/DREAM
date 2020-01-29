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
<%@page import="dream.mgr.usrrpt.action.MaUserRptTableDetailAction"%>
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
var taTableAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setFunction();

	if($("input[name='maUserRptTableDetailDTO.stepNum']").val() == "1" || $("input[name='maUserRptTableDetailDTO.mainSubType']").val() == "MAIN")
	{
		//Hide Join Field
		$("input[name='maUserRptTableDetailDTO.joinTypeDesc']").parents('.field').hide()
		.find("label").removeClass("check"); 
		//Hide Tab 
		$(".accordion_wrap").hide();
	}
}

function setFunction()
{
	setTitle("maUserRptTableDetailDTO.tableName");
	setForUpdate();
	
	taTableAc = new autoC({"maUserRptTableDetailDTO.tableName":"table_name"});
	taTableAc.setTable("TATABLE");
	taTableAc.setAcConditionMap({
	 	   "is_use":"Y"
	 	   });
	taTableAc.setAcResultMap({
        "maUserRptTableDetailDTO.tableId":"table_id"
        ,"maUserRptTableDetailDTO.tableDesc":"description"
    });
	taTableAc.setKeyName("maUserRptTableDetailDTO.tableId"); 
	taTableAc.init();
	
	acSysDesc("maUserRptTableDetailDTO.joinTypeDesc","maUserRptTableDetailDTO.joinType","JOIN_TYPE", true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('sqausrrpttab_id');
	
	$(".accordion_wrap").hide();
}

function goTabPage(pageId)
{
	var form = document.maUserRptTableDetailForm;

	goCommonTabPage(form, '' , pageId);  
}

/**
 * 수정
 */
function goUpdate()
{
	setReadOnly("maUserRptTableDetailDTO.tableName");
}

function setSequenceVal(sequenceVal)
{
	maUserRptTableDetailForm.elements['maUserRptTableDetailDTO.usrrpttabId'].value = sequenceVal;
	maUserRptTableDetailForm.elements['maUserRptCommonDTO.usrrpttabId'].value = sequenceVal;
	maUserRptTableDetailForm.elements['maUserRptTableDetailDTO.usrrptlistId'].value = maUserRptTableDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;
}

function goSave()
{	
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maUserRptTableDetailForm.strutsAction.value = '<%=MaUserRptTableDetailAction.USER_TABLE_DETAIL_INPUT%>';
	else maUserRptTableDetailForm.strutsAction.value = '<%= MaUserRptTableDetailAction.USER_TABLE_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maUserRptTableDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maUserRptTableDetailForm), 'afterSave');
	
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maUserRptTableDetailForm.elements['maUserRptTableDetailDTO.usrrpttabId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    //setTitle("maUserRptTableDetailDTO.itemNo", "maUserRptTableDetailDTO.equipDesc");
    
    if($("input[name='maUserRptTableDetailDTO.stepNum']").val() != "1")
	{
    	$(".accordion_wrap").show();
	}
    
    setReadOnly("maUserRptTableDetailDTO.tableName");//maUserRptTableDetailDTO.joinTypeDesc
    //setReadOnly("maUserRptTableDetailDTO.joinTypeDesc");

    //생성인 경우만 sub Column 조회
    if(maUserRptTableDetailForm.strutsAction.value == '<%=MaUserRptTableDetailAction.USER_TABLE_DETAIL_INPUT%>')
    { 
    	getIframeContent().reloadSubList();
    	/* var topPage = getTopPage();
    	topPage.getParentIframe("maUserRptColList").goSearch();
    	topPage.getParentIframe("maUserRptJoinList").goSearch();
    	topPage.getParentIframe("maUserRptParamList").goSearch();
    	topPage.getParentIframe("maUserRptOrdList").goSearch(); */
    }
	
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUserRptTableDetailForm.elements['maUserRptTableDetailDTO.usrrpttabId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maUserRptTableDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptlistId"/>
	<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
	<html:hidden property="maUserRptCommonDTO.stepNum"/>
	<html:hidden property="maUserRptTableDetailDTO.usrrptlistId"/><!-- Key -->
	<html:hidden property="maUserRptTableDetailDTO.usrrpttabId"/>
	<html:hidden property="maUserRptTableDetailDTO.tableId"/>
	<html:hidden property="maUserRptTableDetailDTO.mainSubType"/>
	<html:hidden property="maUserRptTableDetailDTO.joinType"/>
	<html:hidden property="maUserRptTableDetailDTO.stepNum"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <!-- 테이블명 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.tableDesc"/></label>
                <div class="input_box">
                    <html:text property="maUserRptTableDetailDTO.tableName" tabindex="10"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.tableName"/></label>
                <div class="input_read">
                    <html:text property="maUserRptTableDetailDTO.tableDesc" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 조인조건 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.joinType"/></label>
                <div class="input_box">
                    <html:text property="maUserRptTableDetailDTO.joinTypeDesc" tabindex="30"/>
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