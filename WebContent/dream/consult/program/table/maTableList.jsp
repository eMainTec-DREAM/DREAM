<%--===========================================================================
데이터 테이블 - 목록
author  kim21017
version $Id: maTableList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.table.action.MaTableListAction" %>
<%@ page import="dream.consult.program.table.action.MaTableDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 데이터 테이블-->
<title><bean:message key="MENU.maTableList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();
    
   // dhtmlx.message({
    //	type: "confirm",
	//	text: "<div class='alert_message img_alert'><p>작업중입니다.</p></div>",
	//	expire: -1
	//});
    
    //$('.dhtmlx_popup_button').each(function(e){
    //	if($(this).prop("result") =="true") $(this).addClass('bty1');
    //	else $(this).addClass('bty2');
    //});

}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,20);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maTableListForm.elements['maTableCommonDTO.tableMId'].value = "";
        return sortColumn("maTableList", this, maTableListForm, "TABLEMID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maTableList.do";
    maTableListForm.elements['strutsAction'].value = '<%=MaTableListAction.LISTTYPE_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maTableListForm), "TABLEID", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maTableListForm.elements['maTableCommonDTO.tableMId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaTableListAction.LISTTYPE_LIST_FIND%>');   
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
	maTableListForm.elements['maTableCommonDTO.tableMId'].value = getValueById(myGrid, selectedId,'TABLEID');
	
	goCommonTabPage(maTableListForm, <%= MaTableDetailAction.LISTTYPE_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_tableMId)
{
	maTableListForm.elements['maTableCommonDTO.tableMId'].value = _tableMId;
	findGridList('ReloadRow');
	maTableListForm.elements['maTableCommonDTO.tableMId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maTableDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maTableListForm.elements['maTableCommonDTO.tableMId'].value = getValueById(myGrid, selectedId,'TABLEID');  
    maTableListForm.elements['strutsAction'].value = '<%=MaTableDetailAction.LISTTYPE_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(maTableListForm), 'maTableDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maTableDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maTableListForm.elements['maTableCommonDTO.tableMId'].value = "";
	goCommonTabPage(maTableListForm, '', pageId);
}


/**
 * Excel Export
 */
function goExcel()
{
	maTableListForm.elements['maTableCommonDTO.tableMId'].value = "";
    excelServerAction("maTableList",maTableListForm);
}

/**
 * 삭제
 */
function goDelete(){
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'TABLEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maTableListForm.strutsAction.value = '<%=MaTableListAction.LISTTYPE_LIST_DELETE%>';
	var url = contextPath + "/maTableList.do";
	
    $.post(url,FormQueryString(maTableListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose('maTableDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maTableList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maTableCommonDTO.tableMId"/><!-- Key -->
<html:hidden property="maTableCommonDTO.paramListTypeCateg"/>
	<!-- searchbox 박스 Line -->
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
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" >
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.table"/></label>
					<div class="input_box">
						<html:text property="maTableCommonDTO.filterTable" tabindex="10"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.tableDesc"/></label>
					<div class="input_box">
						<html:text property="maTableCommonDTO.filterTableDesc" tabindex="20"/>
					</div>
				</div>
				<!-- 작성일자 -->
	               <div class="field">
		               <label><bean:message key="LABEL.creDate"/></label>
		               <div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maTableCommonDTO.creSdate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maTableCommonDTO.creEdate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
	               </div>
				<div class="field">
					<label><bean:message key="LABEL.detailDesc"/></label>
					<div class="input_box">
						<html:text property="maTableCommonDTO.filterDescription" tabindex="20"/>
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
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>

