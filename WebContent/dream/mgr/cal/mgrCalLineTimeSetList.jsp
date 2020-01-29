<%--===========================================================================
가동달력설정 - 목록
author  euna0207
version $Id: mgrCalLineTimeSetList.jsp,v 1.0 2017/01/05 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.cal.action.MgrCalLineTimeSetAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 가동시간설정 -->
<title><bean:message key='MENU.RUNTIMESET'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var compDescAc;

function loadPage() 
{
    initGrid();
    
    compDescAc = new autoC({"mgrCalLineTimeSetDTO.filterCompDesc":"description"});
    compDescAc.setTable("TACOMP");
    compDescAc.setAcResultMap({
        "mgrCalLineTimeSetDTO.compNo":"comp_no"
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
		mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = "";
    	return sortColumn("mgrCalLineTimeSetList", this, mgrCalLineTimeSetForm, "LNWRKLISTID", ind, direction);
	});
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrCalLineTimeSetList.do";
    mgrCalLineTimeSetForm.elements['strutsAction'].value = '<%=MgrCalLineTimeSetAction.LINE_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrCalLineTimeSetForm), "LNWRKLISTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_lnWrkListId)
{
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = _lnWrkListId;
	findGridList('ReloadRow');
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.mgrCalLineTimeSetForm;
	
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value =  getValueById(myGrid, selectedId,'LNWRKLISTID');
    
	goCommonTabPage(form, <%= MgrCalLineTimeSetAction.LINE_DETAIL_INIT %>, pageId);
    
}

/**
 * 상세 열기
 */
 function goOpen(){
	 goTabPage('mgrCalLineTimeSetDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;
    
    mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value =  getValueById(myGrid, selectedId,'LNWRKLISTID');
    mgrCalLineTimeSetForm.elements['strutsAction'].value = '<%=MgrCalLineTimeSetAction.LINE_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(mgrCalLineTimeSetForm), 'mgrCalLineTimeSetDetail');
}

 /**
  * 생성
  */
 function goCreate()
 {
 	createValidationCheck(myGrid, "mgrCalLineTimeSetDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = "";
 	goCommonTabPage(mgrCalLineTimeSetForm, '', pageId);	
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

	mgrCalLineTimeSetForm.strutsAction.value = '<%=MgrCalLineTimeSetAction.LINE_LIST_DELETE%>';
	var url = contextPath + "/mgrCalLineTimeSetList.do";
	
	$.post(url,FormQueryString(mgrCalLineTimeSetForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('mgrCalLineTimeSetDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
 
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = "";
	excelServerAction("mgrCalLineTimeSetList", mgrCalLineTimeSetForm);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrCalLineTimeSetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrCalLineTimeSetDTO.lnWrkListId"/><!-- Key -->
<html:hidden property="mgrCalLineTimeSetDTO.compNo"/><!-- Key -->

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
				<!-- 가동달력명 -->
				<div class="field">
					<label><bean:message key="LABEL.lnWrkListDesc"/></label>
					<div class="input_box">
						<html:text property="mgrCalLineTimeSetDTO.filterLnWrkListDesc" tabindex="20"/>
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