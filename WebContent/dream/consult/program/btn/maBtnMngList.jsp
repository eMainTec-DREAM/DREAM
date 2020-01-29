<%--===========================================================================
버튼 - 목록
author  kim21017
version $Id: maBtnMngList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.btn.action.MaBtnMngListAction" %>
<%@ page import="dream.consult.program.btn.action.MaBtnMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 버튼 -->
<title><bean:message key='MENU.BUTTON'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
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
		//alert(myGrid.getStateOfView()+    "    "+rowId);
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maBtnMngList.do";
    maBtnMngListForm.elements['strutsAction'].value = '<%=MaBtnMngListAction.BTN_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maBtnMngListForm), "BUTTONID");

}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_buttonId)
{
	maBtnMngListForm.elements['maBtnMngCommonDTO.buttonId'].value = _buttonId;
	findGridList('ReloadRow');
	maBtnMngListForm.elements['maBtnMngCommonDTO.buttonId'].value = "";
}
/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maBtnMngListForm.elements['maBtnMngCommonDTO.buttonId'].value = "";	// 검색시 Tab 이동Key Clear
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
	maBtnMngListForm.elements['maBtnMngCommonDTO.buttonId'].value =  getValueById(myGrid, selectedId,'BUTTONID'); 
	goCommonTabPage(maBtnMngListForm, <%= MaBtnMngDetailAction.BTN_DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maBtnMngDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maBtnMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maBtnMngListForm.elements['maBtnMngCommonDTO.buttonId'].value = "";
	goCommonTabPage(maBtnMngListForm, '', pageId);	
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
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'BUTTONID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	maBtnMngListForm.strutsAction.value = '<%=MaBtnMngListAction.BTN_LIST_DELETE%>';
  	var url = contextPath + "/maBtnMngList.do";
  	
  	$.post(url,FormQueryString(maBtnMngListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('maBtnMngDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maBtnMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maBtnMngCommonDTO.buttonId"/><!-- Key -->
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
		               <label><bean:message key="LABEL.buttonName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maBtnMngCommonDTO.filterButtonNo" tabindex="10"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.buttonDesc"/></label>
		               <div class="input_box">
		               		<html:text property="maBtnMngCommonDTO.filterButtonDesc" tabindex="20"/>
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