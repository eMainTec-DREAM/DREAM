<%--===========================================================================
권한명 - 목록
author ssong 
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.consult.comp.user.action.ConsultCompUsrGrpAuthListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 권한명 -->
<title><bean:message key='MENU.USRGRP'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="innerTabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();
    
    setForUpdate();
}

dhtmlXGridObject.prototype._in_header_master_checkbox=function(t,i,c){
    t.innerHTML=c[0]+"<input type='checkbox' name='selectAll'/>"+c[1];
	//var self=this;
	$(t).find('input').change(function(){
	    if($(this).is(':checked')) {
	    	var colIdx = getCoumnIdx(myGrid, 'AUTHCHECK');
	    	for(var i = 0; myGrid.getRowsNum() > i; i++)
	    	{
	    		setValueById(myGrid, myGrid.getRowId(i), 'AUTHCHECK', "1");
	    		myGrid.cellById(myGrid.getRowId(i),colIdx).cell.wasChanged=true
	    	}
	    } else {
	    	var colIdx = getCoumnIdx(myGrid, 'AUTHCHECK');
	    	for(var i = 0; myGrid.getRowsNum() > i; i++)
	    	{
	    		setValueById(myGrid, myGrid.getRowId(i), 'AUTHCHECK', "0");
	    		myGrid.cellById(myGrid.getRowId(i),colIdx).cell.wasChanged=true
	    	}
	    }
	});
}   

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,600);
    myGrid.attachEvent("onCheck",function(rowId, columnId, state){
      	setForSave(rowId, columnId, state);
      	
      	var ids = myGrid.getAllSubItems(rowId);
      	if(ids == "") return;

    	ids = ids.split(",");
    	for(var i=0, length= ids.length; i< length; i++) {
    		setValueById(myGrid, ids[i], "AUTH", state);
    		myGrid.cellById(ids[i],columnId).cell.wasChanged=true;
    	}
    });
    myGrid.attachEvent("onBeforeSorting", function(ind,type,direction){
        return false;
    });
    //myGrid.setEditable(false);
    myGrid.attachEvent("onBeforeSelect",function(rowId, state){
	    myGrid.cells(rowId,getCoumnIdx(myGrid, "menuDesc")).setDisabled(true);
		return true;
	});
    myGrid.enableTreeGridLines();
    myGrid.attachEvent("onXLE",function(grdObj,count){ grdObj.expandAll();}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

var delRows= "";
function setForSave(rowId, columnSeq, state)
{
	var keyNo = getValueById(myGrid, rowId, "keyNo");
	var pageId = getValueById(myGrid, rowId, "pageId");
	var keyType = getValueById(myGrid, rowId, "type");
	//alert(keyNo+"   "+pageId+"   "+keyType);
	if(pageId == "")pageId = "NOPAGE";
	
	getTopPage().updateArray[currentPageId] = "AUTH";
	
	var paramQ = "&deleteRows=";
	var paramExt = "&deleteRowsExt=";
	var paramExt1 = "&deleteRowsExt1=";
	
	delRows = delRows + paramQ + keyNo + "^|^" + pageId + paramExt + keyType + paramExt1 + state;
}

function goSave()
{
	var menuMap = {};
	var ids=myGrid.getChangedRows();
	if(ids == "") return;

	ids = ids.split(",");
	for(var i=0, length= ids.length; i< length; i++) {
		menuMap[getValueById(myGrid, ids[i],'KEYNO')] = getValueById(myGrid, ids[i],'AUTHCHECK');
	}
	
	consultCompUsrGrpAuthListForm.elements['consultCompUsrGrpCommonDTO.jsonArray'].value = JSON.stringify(menuMap);
	
	consultCompUsrGrpAuthListForm.strutsAction.value = '<%=ConsultCompUsrGrpAuthListAction.USRGRP_AUTH_LIST_DELETE%>';
	var url = contextPath + "/consultCompUsrGrpBeeAuthList.do";
	$.post(url,FormQueryString(consultCompUsrGrpAuthListForm)+delRows , function(_data){
    	afterSave();
    	
    	myGrid.clearChangedState();
    });  
}

function afterSave()
{
	getTopPage().afterSaveAll(currentPageId);
	delRows= "";
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(searchType,sheetAction)
{
    var url = contextPath + "/consultCompUsrGrpBeeAuthList.do";
    consultCompUsrGrpAuthListForm.elements['consultCompUsrGrpCommonDTO.filterServiceType'].value = "ANDROID";
    consultCompUsrGrpAuthListForm.elements['strutsAction'].value = sheetAction;
    
   // doGridAction(searchType, myGrid, "gridbox", url, FormQueryString(consultCompUsrGrpAuthListForm), "EQUIPID","Y");
    
   doGridAction(searchType, myGrid, "gridbox", url, FormQueryString(consultCompUsrGrpAuthListForm), "CODE" ,"" , "PCODE");
   
    /* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(consultCompUsrGrpAuthListForm), function(_data){
    	////console.log(_data);
        myGrid.parse(_data,"js");
    });
   
    myGrid.setAwaitedRowHeight(30); */
    myGrid.setAwaitedRowHeight(30);
    setForUpdate();
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=ConsultCompUsrGrpAuthListAction.USRGRP_AUTH_LIST_FIND%>');   
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    excelServerAction("consultCompUsrGrpBeeAuthList", consultCompUsrGrpAuthListForm);
}

/**
 * Page Authorization Popup
 */
function goPageauth()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;

	var pageId  = getValueById(myGrid, selectedId,'PAGEID');
	if(pageId == "") return;
	
	consultCompUsrGrpAuthListForm.elements['strutsAction'].value = "";	
	consultCompUsrGrpAuthListForm.elements['consultCompUsrGrpCommonDTO.menuId'].value = getValueById(myGrid, selectedId,'KEYNO');
		
    openLayerPopup("consultCompUsrGrpPageAuthList", FormQueryString(consultCompUsrGrpAuthListForm));
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompUsrGrpBeeAuthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompUsrGrpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="consultCompUsrGrpCommonDTO.usrGrpId"/><!-- Key -->
<html:hidden property="consultCompUsrGrpCommonDTO.usrGrpNo" />
<html:hidden property="consultCompUsrGrpCommonDTO.jsonArray" />
<html:hidden property="consultCompUsrGrpCommonDTO.menuId" />
<html:hidden property="consultCompUsrGrpCommonDTO.filterServiceType" />

    <div class="section_wrap">
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:500px; background-color:white;"></div>
            </div>
        </div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>