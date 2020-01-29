<%--===========================================================================
연결메뉴 - 목록
author  kim21017
version $Id: maMyLink.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@page import="dream.pers.mamylink.form.MaMyLinkForm"%>
<%@page import="dream.pers.mamylink.dto.MaMyLinkDTO"%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.mamylink.action.MaMyLinkAction" %>
<%@ page import="dream.pers.mamylink.dto.MaMyLinkDTO" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
String [] menuPath = (String [])request.getAttribute("menuPath");


//String menuDesc = MaMyLinkDTO.this.getMenuDesc().toString();
%>
<html>
<head>
<!-- 연결메뉴 -->
<title><bean:message key='LABEL.LinkedMenu'/></title>
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
    $('#title2').text(maMyLinkForm.elements['maMyLinkDTO.menuDesc'].value);
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
    	 if(myGrid.cells(rId, myGrid.getColIndexById("LVL")).getValue()=='1')
    	{
        	return false;
    	}  
    	 else
    	{
    		 return true;
    	}    
    }); 
	 myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id

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

function goSave()
{
	maMyLinkForm.strutsAction.value = '<%=MaMyLinkAction.USRGRP_AUTH_LIST_DELETE%>';
	var url = contextPath + "/maMyLink.do";
	$.post(url,FormQueryString(maMyLinkForm)+delRows , function(_data){
    	afterSave();
    });
}
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
    var url = contextPath + "/maMyLink.do";
    maMyLinkForm.elements['strutsAction'].value = '<%=MaMyLinkAction.USRGRP_AUTH_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maMyLinkForm), "eqLocId");
    /* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maMyLinkForm), function(_data){
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
	maMyLinkForm.elements['maMyLinkDTO.eqLocId'].value = _eqLocId;
	findGridList('ReloadTreeRow');
	maMyLinkForm.elements['maMyLinkDTO.eqLocId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//maMyLinkForm.elements['maMyLinkDTO.eqLocId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('SearchTree');   
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maMyLink.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMyLinkDTO.compNo"/><!-- Key -->
<html:hidden property="maMyLinkDTO.usrGrpId"/><!-- Key -->
<html:hidden property="maMyLinkDTO.usrGrpNo" />
<html:hidden property="maMyLinkDTO.menuId" />
<html:hidden property="maMyLinkDTO.menuDesc" />
    <div class="section_wrap">
            <div class="sheader_box">
             <div class="stitle_wrap" >
             	<div class="view_icon"></div>
             	<b id='title2'></b> <b><bean:message key='LABEL.LinkedMenu'/></b>     
             </div>     
            <div class="function_box">
            	<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
					<a href="javascript:goSave();" class="b_save"><span><bean:message key="BUTTON.SAVE"/></span></a>
				</div>
				<div class="b_line"></div>
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