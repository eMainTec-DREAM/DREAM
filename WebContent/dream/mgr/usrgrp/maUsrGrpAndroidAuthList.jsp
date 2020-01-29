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
<%@ page import="dream.mgr.usrgrp.action.MaUsrGrpAuthListAction" %>
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
	    myGrid.cells(rowId,getCoumnIdx(myGrid, "description")).setDisabled(true);
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
	
	maUsrGrpAuthListForm.elements['maUsrGrpCommonDTO.jsonArray'].value = JSON.stringify(menuMap);
	
	maUsrGrpAuthListForm.strutsAction.value = '<%=MaUsrGrpAuthListAction.USRGRP_AUTH_LIST_DELETE%>';
	var url = contextPath + "/maUsrGrpAuthList.do";
	$.post(url,FormQueryString(maUsrGrpAuthListForm)+delRows , function(_data){
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
    var url = contextPath + "/maUsrGrpAuthList.do";
    maUsrGrpAuthListForm.elements['maUsrGrpCommonDTO.filterServiceType'].value = "ANDROID";
    maUsrGrpAuthListForm.elements['strutsAction'].value = sheetAction;
    
    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maUsrGrpAuthListForm), function(_data){
    	////console.log(_data);
        myGrid.parse(_data,"js");
    });
    
    myGrid.setAwaitedRowHeight(30);
    
    setForUpdate();
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=MaUsrGrpAuthListAction.USRGRP_AUTH_LIST_FIND%>');   
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    excelServerAction("maUsrGrpAuthList", maUsrGrpAuthListForm);
}

/**
 * Page Authorization Popup
 */
function goPmstd()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;

	var pageId  = getValueById(myGrid, selectedId,'PAGEID');
	if(pageId == "") return;
	
	maUsrGrpAuthListForm.elements['strutsAction'].value = "";	
	maUsrGrpAuthListForm.elements['maUsrGrpCommonDTO.menuId'].value = getValueById(myGrid, selectedId,'KEYNO');
		
    openLayerPopup("mgrUsrgrpPageList", FormQueryString(maUsrGrpAuthListForm));
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUsrGrpAuthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUsrGrpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maUsrGrpCommonDTO.usrGrpId"/><!-- Key -->
<html:hidden property="maUsrGrpCommonDTO.usrGrpNo" />
<html:hidden property="maUsrGrpCommonDTO.jsonArray" />
<html:hidden property="maUsrGrpCommonDTO.menuId" />
<html:hidden property="maUsrGrpCommonDTO.filterServiceType" />

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