<%--===========================================================================
휴무요일 설정 - 목록
author  kim21017
version $Id: mgrCalCompWkrcalDowsetList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.cal.action.MgrCalCompWkrcalDowsetListAction" %>
<%@ page import="dream.mgr.cal.action.MgrCalCompWkrcalDowsetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key="MENU.COMPSET"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

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
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrCalCompWkrcalDowsetList.do";
    mgrCalCompWkrcalDowsetListForm.elements['strutsAction'].value = '<%=MgrCalCompWkrcalDowsetListAction.WRKCAL_DOWSET_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrCalCompWkrcalDowsetListForm), "WRKCALDOWSETID");

}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wrkcalDowsetId)
{
	mgrCalCompWkrcalDowsetListForm.elements['mgrCalCompWkrcalDowsetListDTO.wrkcalDowsetId'].value = _wrkcalDowsetId;
	findGridList('ReloadRow');
	mgrCalCompWkrcalDowsetListForm.elements['mgrCalCompWkrcalDowsetListDTO.wrkcalDowsetId'].value = "";
}

function goSearch()
{
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
	mgrCalCompWkrcalDowsetListForm.elements['mgrCalCompWkrcalDowsetListDTO.wrkcalDowsetId'].value =  getValueById(myGrid, selectedId,'WRKCALDOWSETID');
	
	goCommonTabPage(mgrCalCompWkrcalDowsetListForm, <%=MgrCalCompWkrcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('mgrCalCompWkrcalDowsetDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "mgrCalCompWkrcalDowsetDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrCalCompWkrcalDowsetListForm.elements['mgrCalCompWkrcalDowsetListDTO.wrkcalDowsetId'].value = "";
	goCommonTabPage(mgrCalCompWkrcalDowsetListForm, '', pageId);
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
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WRKCALDOWSETID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	mgrCalCompWkrcalDowsetListForm.strutsAction.value = '<%=MgrCalCompWkrcalDowsetListAction.WRKCAL_DOWSET_LIST_DELETE%>';
  	var url = contextPath + "/mgrCalCompWkrcalDowsetList.do";
  	
  	$.post(url,FormQueryString(mgrCalCompWkrcalDowsetListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('mgrCalCompWkrcalDowsetDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrCalCompWkrcalDowsetList">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrCalCompWkrcalCommonDTO.wrkcalListId"/><!-- Key -->
<html:hidden property="mgrCalCompWkrcalDowsetListDTO.wrkcalDowsetId"/><!-- Key -->
		
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
	    <div class="article_box">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>