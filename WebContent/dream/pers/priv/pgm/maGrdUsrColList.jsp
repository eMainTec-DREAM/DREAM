<%--===========================================================================
목록
author  jung7126
version $Id: maGrdUsrColList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.pgm.action.MaGrdUsrColListAction" %>
<%@ page import="dream.pers.priv.pgm.action.MaGrdUsrColDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 칼럼 목록 -->
<title><bean:message key='LABEL.maGrdUsrColList'/></title>
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
		goTabPage("maGrdUsrColDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		persPrivUsrFieldListForm.elements['persPrivUsrFieldCommonDTO.pgFieldId'].value = "";
    	return sortColumn("persPrivUsrFieldList", this, persPrivUsrFieldListForm, "PGFIELDID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=MaGrdUsrColListAction.GRD_USR_COL_LIST_FIND %>');   
}

function findGridList(sheetAction, strutsAction)
{
	var form = document.maGrdUsrColListForm;	
	form.strutsAction.value = strutsAction;

	var url = contextPath + "/maGrdUsrColList.do";

	myGrid.clearAll(); setLoading("gridbox");
//     $.post(url,FormQueryString(form), function(_data){
//     	myGrid.parse(_data,"js");
//     });
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maGrdUsrColListForm), "pggridColId", "Y");
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
	maGrdUsrColListForm.elements['maGrdUsrCommonDTO.usrPgGridColId'].value = getValueById(myGrid, selectedId,'usrPgGridColId');
	maGrdUsrColListForm.elements['maGrdUsrCommonDTO.pgGridColId'].value = getValueById(myGrid, selectedId,'pggridColId'); //pggridcol
	
	goCommonTabPage(maGrdUsrColListForm, <%= MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_INIT %>, "maGrdUsrColDetail");
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maGrdUsrColDetail');
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maGrdUsrColList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maGrdUsrCommonDTO.pageId"/><!--  -->
<html:hidden property="maGrdUsrCommonDTO.pgGridId"/><!--  -->
<html:hidden property="maGrdUsrCommonDTO.gridObjId"/>

<html:hidden property="maGrdUsrCommonDTO.usrPgGridColId"/>
<html:hidden property="maGrdUsrCommonDTO.pgGridColId"/>

<html:hidden property="maGrdUsrDetailDTO.pgGridId" />
<html:hidden property="maGrdUsrDetailDTO.usrPgGridId" /> <!-- User Page Grid ID -->
<html:hidden property="maGrdUsrDetailDTO.gridObjId" />
    <!-- searchbox 박스 Line -->

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