<%--===========================================================================
가동시간설정 - 목록
author  kim21017
version $Id: maLineTimeList.jsp,v 1.0 2017/01/05 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.time.action.MaLineTimeListAction" %>
<%@ page import="dream.consult.comp.time.action.MaLineTimeDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 가동시간설정 -->
<title><bean:message key='MENU.RUNTIMESET'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var compDescAc;

function loadPage() 
{
    initGrid();
    
    compDescAc = new autoC({"maLineTimeCommonDTO.filterCompDesc":"description"});
    compDescAc.setTable("TACOMP");
    compDescAc.setAcResultMap({
        "maLineTimeCommonDTO.compNo":"comp_no"
    });
    compDescAc.init();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maLineTimeListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value = "";
		maLineTimeListForm.elements['maLineTimeCommonDTO.compNo'].value = "";
    	return sortColumn("maLineTimeList", this, maLineTimeListForm, "LNWRKLISTID", ind, direction);
	});
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maLineTimeList.do";
    maLineTimeListForm.elements['strutsAction'].value = '<%=MaLineTimeListAction.LINE_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maLineTimeListForm), "LNWRKLISTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_compNo, _lnWrkListId)
{
	maLineTimeListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value = _lnWrkListId;
	maLineTimeListForm.elements['maLineTimeCommonDTO.compNo'].value = _compNo;
	findGridList('ReloadRow');
	maLineTimeListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value = "";
	maLineTimeListForm.elements['maLineTimeCommonDTO.compNo'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maLineTimeListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value = "";	// 검색시 Tab 이동Key Clear
	maLineTimeListForm.elements['maLineTimeCommonDTO.compNo'].value = "";
    findGridList();   
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;
    
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.maLineTimeListForm;
	maLineTimeListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value =  getValueById(myGrid, selectedId,'LNWRKLISTID');
	maLineTimeListForm.elements['maLineTimeCommonDTO.compNo'].value =  getValueById(myGrid, selectedId,'COMPNO');
    
	goCommonTabPage(form, <%= MaLineTimeDetailAction.LINE_DETAIL_INIT %>, pageId);
    
}

/**
 * 상세 열기
 */
 function goOpen(){
	 goTabPage('maLineTimeDetail');
}

 /**
  * 생성
  */
 function goCreate()
 {
 	createValidationCheck(myGrid, "maLineTimeDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 maLineTimeListForm.elements['maLineTimeCommonDTO.compNo'].value = "";
	 maLineTimeListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value = "";
 	goCommonTabPage(maLineTimeListForm, '', pageId);	
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'LNWRKLISTID', 'COMPNO'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maLineTimeListForm.strutsAction.value = '<%=MaLineTimeListAction.LINE_LIST_DELETE%>';
	var url = contextPath + "/maLineTimeList.do";
	
	$.post(url,FormQueryString(maLineTimeListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maLineTimeDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
 
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	maLineTimeListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value = "";
	maLineTimeListForm.elements['maLineTimeCommonDTO.compNo'].value = "";
	excelServerAction("maLineTimeList", maLineTimeListForm);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maLineTimeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maLineTimeCommonDTO.lnWrkListId"/><!-- Key -->
<html:hidden property="maLineTimeCommonDTO.compNo"/><!-- Key -->

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
				<!-- 회사 -->
				<div class="field">
					<label><bean:message key="LABEL.compDesc"/></label>
					<div class="input_box">
						<html:text property="maLineTimeCommonDTO.filterCompDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 가동달력명 -->
				<div class="field">
					<label><bean:message key="LABEL.lnWrkListDesc"/></label>
					<div class="input_box">
						<html:text property="maLineTimeCommonDTO.filterLnWrkListDesc" tabindex="20"/>
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