<%--===========================================================================
사용달력설정 - 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usage.cal.action.MgrUsageCalSetAction" %>
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
    
    compDescAc = new autoC({"mgrUsageCalSetDTO.filterCompDesc":"description"});
    compDescAc.setTable("TACOMP");
    compDescAc.setAcResultMap({
        "mgrUsageCalSetDTO.compNo":"comp_no"
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
		mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = "";
    	return sortColumn("mgrUsageCalSetList", this, mgrUsageCalSetForm, "LNWRKLISTID", ind, direction);
	});
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrUsageCalSetList.do";
    mgrUsageCalSetForm.elements['strutsAction'].value = '<%=MgrUsageCalSetAction.LINE_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUsageCalSetForm), "LNWRKLISTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_lnWrkListId)
{
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = _lnWrkListId;
	findGridList('ReloadRow');
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.mgrUsageCalSetForm;
	
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value =  getValueById(myGrid, selectedId,'LNWRKLISTID');
    
	goCommonTabPage(form, <%= MgrUsageCalSetAction.LINE_DETAIL_INIT %>, pageId);
    
}

/**
 * 상세 열기
 */
 function goOpen(){
	 goTabPage('mgrUsageCalSetDetail');
}

 function goOpenAction()
 {
 	var selectedId=myGrid.getSelectedRowId();
     
     if(selectedId == null) return;
     
     mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value =  getValueById(myGrid, selectedId,'LNWRKLISTID');
     mgrUsageCalSetForm.elements['strutsAction'].value = '<%=MgrUsageCalSetAction.LINE_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(mgrUsageCalSetForm), 'mgrUsageCalSetDetail'); 
 } 

 /**
  * 생성
  */
 function goCreate()
 {
 	createValidationCheck(myGrid, "mgrUsageCalSetDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = "";
 	goCommonTabPage(mgrUsageCalSetForm, '', pageId);	
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

	mgrUsageCalSetForm.strutsAction.value = '<%=MgrUsageCalSetAction.LINE_LIST_DELETE%>';
	var url = contextPath + "/mgrUsageCalSetList.do";
	
	$.post(url,FormQueryString(mgrUsageCalSetForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('mgrUsageCalSetDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
 
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	mgrUsageCalSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = "";
	excelServerAction("mgrUsageCalSetList", mgrUsageCalSetForm);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrUsageCalSetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrUsageCalSetDTO.lnWrkListId"/><!-- Key -->
<html:hidden property="mgrUsageCalSetDTO.compNo"/><!-- Key -->

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
				<!-- 사용달력명 -->
				<div class="field">
					<label><bean:message key="LABEL.lnWrkListDesc"/></label>
					<div class="input_box">
						<html:text property="mgrUsageCalSetDTO.filterLnWrkListDesc" tabindex="20"/>
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