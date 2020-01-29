<%--===========================================================================
사용자메뉴 - 목록
author  kim21017
version $Id: lovMenuList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.menu.action.LovMenuListAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 사용자메뉴 -->
<title><bean:message key='LABEL.UserMenu'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var selectedEqId, selectedEqInd;

function loadPage() 
{
    initGrid();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	myGrid.attachEvent("onCheck",function(rowId, columnId, state){
		setForSave(rowId, columnId, state);
    });
    myGrid.attachEvent("onBeforeSelect",function(rowId, state){
    	var index 		= getIndexById(myGrid, "DESCRIPTION");
	    myGrid.cells(rowId,index).setDisabled(true);
		return true;
	});
    myGrid.attachEvent("onEditCell",function(stage,rId,cInd,nValue,oValue){
//     	 if(myGrid.cells(rId, myGrid.getColIndexById("LVL")).getValue()=='1')
//     	{
//         	return false;
//     	}  
//     	 else
//     	{
//     		 return true;
//     	}    
    }); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch","lovMenuList"); // grid, grid id

}

var delRows= "";
function setForSave(rowId, columnSeq, state)
{
	var menuId = getValueById(myGrid, rowId, "menuId");

	getTopPage().updateArray[currentPageId] = "AUTH";
	
	var paramQ = "&deleteRows=";
	var paramExt = "&deleteRowsExt=";
	
	delRows = delRows + paramQ + menuId + paramExt + state;
}

<%--  function goSave()
{
	lovMenuListForm.strutsAction.value = '<%=LovMenuListAction.USRGRP_AUTH_LIST_DELETE%>';
	var url = contextPath + "/lovMenuList.do";
	$.post(url,FormQueryString(lovMenuListForm)+delRows , function(_data){
    	afterSave();
    });
}  --%>
function afterSave()
{
	getTopPage().afterSaveAll(currentPageId);
	delRows= "";
	//parent.goReload();
	//window.opener.location.href=window.opener.location.href;
	//parent.document.location.reload();
}
function afterSearch()
{
	myGrid.expandAll(); //펼치기
 	//setTimeout("myGrid.collapseAll();//접기", 100);
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/lovMenuList.do";
    lovMenuListForm.elements['strutsAction'].value = '<%=LovMenuListAction.LOV_MENU_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovMenuListForm), "eqLocId");
    /* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(lovMenuListForm), function(_data){
    	myGrid.parse(_data,"js");

    	//var jsonObj = JSON.parse(_data);
    	//console.log(jsonObj.total_count);
    	
     	myGrid.expandAll(); //펼치기
    	setCounter(myGrid,"gridbox"); //TreeGrid는 펼쳐진 Rows만 카운트 함.
     	setTimeout("myGrid.collapseAll();//접기", 200);
    }); */
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqLocId)
{
	lovMenuListForm.elements['lovMenuListDTO.eqLocId'].value = _eqLocId;
	findGridList('ReloadTreeRow');
	lovMenuListForm.elements['lovMenuListDTO.eqLocId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//lovMenuListForm.elements['lovMenuListDTO.eqLocId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('SearchTree');   
}

function goSelect()
{
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	
	//return;
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;
//     if(getValueById(myGrid, selectedId, 'LVL')=='1')return;
	returnArray[0] = getValueById(myGrid, selectedId, 'MENUID');   // ID
    returnArray[1] = getValueById(myGrid, selectedId, 'DESCRIPTION'); // Desc

	var dirType = lovMenuListForm.elements['lovMenuListDTO.codeType'].value;
	
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValue(returnArray, dirType);
// 	self.close();
    closeLayerPopup();
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovMenuList" >
		<html:hidden property="lovMenuListDTO.extCode1" />
		<html:hidden property="lovMenuListDTO.extCode2" />
		<html:hidden property="lovMenuListDTO.codeType" />
		<html:hidden property="strutsAction" />
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap"><a></a></div>
				<div class="stitle_wrap">
					<div class="stitle_icon"><a></a></div>
					<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
				</div>
				<div class="function_box filter">
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
			<div class="article_box">
				<div class="form_box">
					<div class="field">
						<!-- 메뉴 -->
						<label><bean:message key="LABEL.menuName"/></label>
						<div class="input_box">
							<input type='text' name="lovMenuListDTO.menuDesc" />
						</div>
					</div>
				</div>
			</div><!--article_box end-->
		</div> <!--  end section_wrap -->
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap"><a></a></div>
				<div class="stitle_wrap">
					<div class="stitle_icon"><a></a></div>
					<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
				</div>
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
					<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
				</div>
			</div>
		</div> <!--  End of section_wrap -->
	</html:form> 
</body>
</html>