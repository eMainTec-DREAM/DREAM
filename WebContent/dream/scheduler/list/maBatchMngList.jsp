<%--===========================================================================
배치작업내역
author  kim21017
version $Id: maBatchMngList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.scheduler.list.action.MaBatchMngListAction" %>
<%@ page import="dream.scheduler.list.action.MaBatchMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 배치작업내역 -->
<title><bean:message key='MENU.BATCHMNG'/></title>
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
    var url = contextPath + "/maBatchMngList.do";
    maBatchMngListForm.elements['strutsAction'].value = '<%=MaBatchMngListAction.BATCH_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maBatchMngListForm), "BATPGMID", "Y");

}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_batPgmId)
{
	maBatchMngListForm.elements['maBatchMngCommonDTO.batPgmId'].value = _batPgmId;
	findGridList('ReloadRow');
	maBatchMngListForm.elements['maBatchMngCommonDTO.batPgmId'].value = "";
}
/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maBatchMngListForm.elements['maBatchMngCommonDTO.batPgmId'].value = "";	// 검색시 Tab 이동Key Clear
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
	maBatchMngListForm.elements['maBatchMngCommonDTO.batPgmId'].value =  getValueById(myGrid, selectedId,'BATPGMID'); 
	goCommonTabPage(maBatchMngListForm, <%= MaBatchMngDetailAction.BATCH_DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maBatchMngDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maBatchMngListForm.elements['maBatchMngCommonDTO.batPgmId'].value =  getValueById(myGrid, selectedId,'BATPGMID'); 
    maBatchMngListForm.elements['strutsAction'].value = '<%=MaBatchMngDetailAction.BATCH_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maBatchMngListForm), 'maBatchMngDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maBatchMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maBatchMngListForm.elements['maBatchMngCommonDTO.batPgmId'].value = "";
	goCommonTabPage(maBatchMngListForm, '', pageId);	
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
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'BATPGMID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	maBatchMngListForm.strutsAction.value = '<%=MaBatchMngListAction.BATCH_LIST_DELETE%>';
  	var url = contextPath + "/maBatchMngList.do";
  	
  	$.post(url,FormQueryString(maBatchMngListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('maBatchMngDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }
function goExec(){
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0029'/>", function(result){
		 if(result){
			 setModal(COMMON_CMSG003);
			 var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'BATPGMID'); //Grid, check box column seq, pk column seq
			 if(typeof selArray == "undefined"){
					alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
					return;
				}
				
				maBatchMngListForm.strutsAction.value = '<%=MaBatchMngListAction.BATCH_LIST_EXEC%>';
				var url = contextPath + "/maBatchMngList.do";
				$.post(url,FormQueryString(maBatchMngListForm)+selArray , function(_data){
			    	afterExec();
			    });
		 }
	});
}
function afterExec(){
	closeModal();
	goClose('maBatchMngDetail');
	goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG0030"/>');
}
/**
 * 프로시져 실행 
 */
function goPlay()
{
	goExec();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maBatchMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maBatchMngCommonDTO.batPgmId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
    	<div class="sheader_box">
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
		               <label><bean:message key="LABEL.failureRemark"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maBatchMngCommonDTO.filterDesc" tabindex="10"/>
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
	    </div><!--sheader_box end grid_cul-->
	    <div class="article_box" style="border-right:1px solid #eee;">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>