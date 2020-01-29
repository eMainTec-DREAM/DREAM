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
<title><bean:message key='LABEL.pageAuth'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="innerTabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();

   // setForUpdate();
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
	    		myGrid.cellById(myGrid.getRowId(i),colIdx).cell.wasChanged=true;
	    	}
	    } else {
	    	var colIdx = getCoumnIdx(myGrid, 'AUTHCHECK');
	    	for(var i = 0; myGrid.getRowsNum() > i; i++)
	    	{
	    		setValueById(myGrid, myGrid.getRowId(i), 'AUTHCHECK', "0");
	    		myGrid.cellById(myGrid.getRowId(i),colIdx).cell.wasChanged=true;
	    	}
	    }
	});
}   

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableAutoHeight(true);
    myGrid.attachEvent("onCheck",function(rowId, columnId, state){

    	getTopPage().updateArray["consultCompUsrGrpAuthList"] = "AUTH";
    	
      	var ids = myGrid.getAllSubItems(rowId);
      	if(ids == "") return;

    	ids = ids.split(",");
    	for(var i=0, length= ids.length; i< length; i++) {
    		setValueById(myGrid, ids[i], "AUTHCHECK", state);
    		myGrid.cellById(ids[i],columnId).cell.wasChanged=true;
    	}
    });
    //myGrid.setEditable(false);
    myGrid.attachEvent("onBeforeSorting", function(ind,type,direction){
        return false;
    });
    myGrid.attachEvent("onBeforeSelect",function(rowId, state){
	    myGrid.cells(rowId,getCoumnIdx(myGrid, "pageDesc")).setDisabled(true);
		return true;
	});
    myGrid.enableTreeGridLines();
    myGrid.attachEvent("onXLE",function(grdObj,count){grdObj.expandAll(); setCounter(grdObj,"gridbox");}); myGrid.init();

    setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}

function goSave()
{
	var menuMap = {};
	var rows = [];
	var ids=myGrid.getChangedRows();
	if(ids == "") return;

	ids = ids.split(",");
	
	for(var i=0, length= ids.length; i< length; i++) {
		var rowObj = {};
		rowObj['pageId'] = getValueById(myGrid, ids[i],'PAGEID');
		rowObj['pgPageId'] = getValueById(myGrid, ids[i],'PGPAGEID');
		rowObj['check'] = getValueById(myGrid, ids[i],'AUTHCHECK');
		rows.push(rowObj);
// 		menuMap[getValueById(myGrid, ids[i],'PGPAGEID')] = getValueById(myGrid, ids[i],'AUTHCHECK');
	}

	consultCompUsrGrpAuthListForm.elements['consultCompUsrGrpCommonDTO.jsonArray'].value = JSON.stringify(rows);
	
	consultCompUsrGrpAuthListForm.strutsAction.value = '<%=ConsultCompUsrGrpAuthListAction.USRGRP_AUTHPAGE_LIST_SAVE%>';
	var url = contextPath + "/consultCompUsrGrpPageAuthList.do";
	$.post(url,FormQueryString(consultCompUsrGrpAuthListForm) , function(_data){
    	afterSave();
    	
    	myGrid.clearChangedState();
    });   
}

function afterSave()
{
	getTopPage().afterSaveAll("consultCompUsrGrpAuthList");
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(actionType,sheetAction)
{
    var url = contextPath + "/consultCompUsrGrpPageAuthList.do";
    consultCompUsrGrpAuthListForm.elements['strutsAction'].value = sheetAction;
    
    doGridAction(actionType, myGrid, "gridbox", url, FormQueryString(consultCompUsrGrpAuthListForm), "PAGEID" ,"" , "PPAGEID");
    
    /* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(consultCompUsrGrpAuthListForm), function(_data){
        myGrid.parse(_data,"js");
    }); */
    
    setForUpdate("consultCompUsrGrpAuthList");
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=ConsultCompUsrGrpAuthListAction.USRGRP_AUTHPAGE_LIST_FIND%>');   
}

/**
 * 버튼권한 페이지 호출 
 */
function goBtnauth()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;

	var pageId  = getValueById(myGrid, selectedId,'PAGEID');
	if(pageId == "") return;
	
	consultCompUsrGrpAuthListForm.elements['strutsAction'].value = "";	
	consultCompUsrGrpAuthListForm.elements['consultCompUsrGrpCommonDTO.pageId'].value = pageId;
		
    openLayerPopup("consultCompUsrGrpBtnAuthList", FormQueryString(consultCompUsrGrpAuthListForm)); 
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    excelServerAction("consultCompUsrGrpPageAuthList", consultCompUsrGrpAuthListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompUsrGrpPageAuthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompUsrGrpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="consultCompUsrGrpCommonDTO.usrGrpId"/><!-- Key -->
<html:hidden property="consultCompUsrGrpCommonDTO.usrGrpNo" />
<html:hidden property="consultCompUsrGrpCommonDTO.jsonArray" />
<html:hidden property="consultCompUsrGrpCommonDTO.menuId" />
<html:hidden property="consultCompUsrGrpCommonDTO.pageId" />

    <div class="section_wrap">
    	<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
		
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:500px; background-color:white;"></div>
            </div>
        </div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>