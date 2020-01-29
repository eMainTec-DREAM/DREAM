<%--===========================================================================
메뉴 - 목록
author  kim21017
version $Id: mcDataSelectList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrdata.action.McDataSelectScriptAction" %>
<%@ page import="dream.mgr.usrdata.action.McDataSelectDetailAction" %>
<%@ page import="dream.mgr.usrdata.action.McDataSelectPopupAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메뉴 -->
<title><bean:message key='MENU.mcDataSelectList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

//그리드명
var myGrid,creByAc;

function loadPage() 
{
	setFunction();
	
	setForUpdate();

    //initGrid();
	if(typeof resizeTabFrame == "function") setTimeout("resizeTabFrame()",500);
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

function setFunction()
{
    /* creByAc = new autoC({"mcDataSelectCommonDTO.creByDesc":"y.emp_name"});
    creByAc.setAcDisplay("x.user_no||','||y.emp_name");
    creByAc.setTable("TAUSER x, TAEMP y");
    creByAc.setAcConditionMap({
		"CUSTOM":"x.emp_id=y.emp_id",
	    "x.is_use":"Y"
	   });
    creByAc.setAcResultMap({
    	"mcDataSelectCommonDTO.creBy":"x.user_id"
    });
     creByAc.setAcResultLabel({
    	"mcDataSelectCommonDTO.creBy":"LABEL.userId",
    	"mcDataSelectCommonDTO.creByDesc":"LABEL.creBy"
    }); 
    //creByAc.setCustomLov("lovUser('mcDataSelectCommonDTO.creBy', '', 'mcDataSelectCommonDTO.creByDesc')");
    creByAc.init();  
    
    acSysDesc("mcDataSelectCommonDTO.usrdataTypeDesc","mcDataSelectCommonDTO.usrdataType","USRDATA_TYPE"); */
	   
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mcDataSelectList.do";
    <%-- mcDataSelectListForm.elements['strutsAction'].value = '<%=McDataSelectListAction.DATA_LIST_FIND%>'; --%>
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mcDataSelectListForm), "usrrptId");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = getValueById(myGrid, selectedId,'usrrptId');
	goCommonTabPage(mcDataSelectListForm, <%= McDataSelectDetailAction.DATA_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrrptId)
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = _usrrptId;
	findGridList('ReloadRow');
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('mcDataSelectDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "mcDataSelectDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";
	goCommonTabPage(mcDataSelectListForm, '', "mcDataSelectDetail");
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'usrrptId'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	<%-- mcDataSelectListForm.strutsAction.value = '<%=McDataSelectListAction.DATA_LIST_DELETE%>'; --%>
	var url = contextPath + "/mcDataSelectList.do";
	
	$.post(url,FormQueryString(mcDataSelectListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  

function afterDelete(){
	goClose('mcDataSelectDetail');
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave()
{
	if(checkValidation()) return;
	
	//strutsAction 셋팅.
    mcDataSelectScriptForm.strutsAction.value = '<%=McDataSelectScriptAction.DATA_SCRIPT_UPDATE%>';
    
    var actionUrl = contextPath + "/mcDataSelectScript.do";
    XMLHttpPost(actionUrl, FormQueryString(mcDataSelectScriptForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
}

function goPlay()
{
	//openLayerPopup("mcDataSelectPopup", FormQueryString(mcDataSelectScriptForm));
				
	var url   = contextPath + "/mcDataSelectPopup.do";
	var popWidth = 1010;
	var popHeight = 400;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction="+<%=McDataSelectPopupAction.DATA_SCRIPT_FIND%>
    			+"&mcDataSelectCommonDTO.usrrptId="+mcDataSelectScriptForm.elements['mcDataSelectCommonDTO.usrrptId'].value;
	//post 전송
	openWindowWithPost(url, "SCRIPT_POPUP", param, pos);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mcDataSelectScriptForm.elements['mcDataSelectCommonDTO.usrrptId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mcDataSelectScript.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="mcDataSelectCommonDTO.usrrptId"/><!-- Key -->
<html:hidden property="mcDataSelectCommonDTO.compNo"/>
<html:hidden property="mcDataSelectCommonDTO.title"/>
    
<div class="article_box">
    <div class="form_box">
		<div class="field_long">
			<label><bean:message key="LABEL.script"/></label>
			<div class="input_box">
				<html:textarea property="mcDataSelectCommonDTO.script" styleClass="ta50" tabindex="20" />
			</div>
		</div>
	</div> <!-- End of Form_box -->
</div>
</html:form> 
</body>
</html>