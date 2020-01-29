<%--===========================================================================
근무일달력 - 목록
author  kim21017
version $Id: mgrCalCompWkrcalList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.cal.action.MgrCalCompWkrcalListAction" %>
<%@ page import="dream.mgr.cal.action.MgrCalCompWkrcalDetailAction" %>
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
var compAc;

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
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		mgrCalCompWkrcalListForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value = "";
    	return sortColumn("mgrCalCompWkrcalList", this, mgrCalCompWkrcalListForm, "WRKCALLISTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrCalCompWkrcalList.do";
    mgrCalCompWkrcalListForm.elements['strutsAction'].value = '<%=MgrCalCompWkrcalListAction.WRKCAL_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrCalCompWkrcalListForm), "WRKCALLISTID","Y");

}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wrkcalListId)
{
	mgrCalCompWkrcalListForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value = _wrkcalListId;
	findGridList('ReloadRow');
	mgrCalCompWkrcalListForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value = "";
}
/**
 * 회사설정 클릭시 호출
 */
function goSearch()
{
	mgrCalCompWkrcalListForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	mgrCalCompWkrcalListForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value =  getValueById(myGrid, selectedId,'WRKCALLISTID');
	
	goCommonTabPage(mgrCalCompWkrcalListForm, <%= MgrCalCompWkrcalDetailAction.WRKCAL_DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('mgrCalCompWkrcalDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "mgrCalCompWkrcalDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrCalCompWkrcalListForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value = "";
	goCommonTabPage(mgrCalCompWkrcalListForm, '', pageId);	
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
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WRKCALLISTID', 'COMPNO'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	mgrCalCompWkrcalListForm.strutsAction.value = '<%=MgrCalCompWkrcalListAction.WRKCAL_LIST_DELETE%>';
  	var url = contextPath + "/mgrCalCompWkrcalList.do";
  	
  	$.post(url,FormQueryString(mgrCalCompWkrcalListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('mgrCalCompWkrcalDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrCalCompWkrcalList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrCalCompWkrcalCommonDTO.compNo"/><!-- Key -->
<html:hidden property="mgrCalCompWkrcalCommonDTO.wrkcalListId"/><!-- Key -->
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
	       <div class="article_box">
	           <div class="form_box">
               	   <div class="field">
		               <label><bean:message key="LABEL.wrkcalListDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="mgrCalCompWkrcalCommonDTO.filterWrkcalNo" tabindex="10"/>
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
	    <div class="article_box">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>