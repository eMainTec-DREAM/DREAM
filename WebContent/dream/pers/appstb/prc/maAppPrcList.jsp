<%--===========================================================================
목록
author  jung7126
version $Id: appPrcList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.appstb.prc.action.AppPrcListAction" %>
<%@ page import="dream.pers.appstb.prc.action.AppPrcDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 결재자 목록 -->
<title><bean:message key='LABEL.appByHist'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="innerTabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
	
	parent.$("#tabFrameTAB\\."+currentPageId).contents().find('.inner_section').css("margin","7px 0px 30px 0px");
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maAppPrcDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	appPrcListForm.elements['appReqCommonDTO.apprusrId'].value = "";
        return sortColumn("maAppPrcList", this, appPrcListForm, "APPRUSRID", ind, direction);
    });

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search', '<%=AppPrcListAction.APP_PRC_FIND %>');   
}

function findGridList(gridAction, strutsAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	//if (appPrcListForm.elements['appReqCommonDTO.apprlistId'].value == '') return;
	
	var form = document.appPrcListForm;	
	form.strutsAction.value = '<%=AppPrcListAction.APP_PRC_FIND %>';

    var url = contextPath + "/appPrcList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(appPrcListForm), "apprusrId","Y");
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
	appPrcListForm.elements['appReqCommonDTO.apprusrId'].value = getValueById(myGrid, selectedId,'apprusrId'); //pggridcol

	goCommonTabPage(appPrcListForm, <%= AppPrcDetailAction.APP_PRC_INIT %>, "maAppPrcDetail");
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maAppPrcDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    appPrcListForm.elements['appReqCommonDTO.apprusrId'].value =  getValueById(myGrid, selectedId,'APPRUSRID');  
    appPrcListForm.elements['strutsAction'].value = '<%=AppPrcDetailAction.APP_PRC_INIT%>';
    openQuickTabPage(FormQueryString(appPrcListForm), 'maAppPrcDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maAppPrcDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	appPrcListForm.elements['appReqCommonDTO.apprusrId'].value = "";
	goCommonTabPage(appPrcListForm, '', pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_keyId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	appPrcListForm.elements['appReqCommonDTO.apprusrId'].value = _keyId;
	findGridList('ReloadRow');
	appPrcListForm.elements['appReqCommonDTO.apprusrId'].value = "";
}

/**
 * Excel Export
 */
function goExcel()
{
   	//excelAction(myGrid);
	appPrcListForm.elements['appReqCommonDTO.apprusrId'].value = "";
	excelServerAction("appPrcList", appPrcListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/appPrcList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="appReqCommonDTO.objectId"/>
<html:hidden property="appReqCommonDTO.apprType"/>
<html:hidden property="appReqCommonDTO.apprusrId"/>
<html:hidden property="appReqCommonDTO.apprlistId"/>
<html:hidden property="appReqCommonDTO.isAppHist" value="Y"/>

<div class="section_wrap">
	    <div class="article_box" id="listBox">
	           <div class="grid_area">
	           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
	           </div>
	 	</div>
 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>