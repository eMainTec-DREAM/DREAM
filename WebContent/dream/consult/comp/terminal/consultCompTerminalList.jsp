<%--===========================================================================
접근터미널 - 목록
author  kim21017
version $Id: consultCompTerminalList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.terminal.action.ConsultCompTerminalListAction" %>
<%@ page import="dream.consult.comp.terminal.action.ConsultCompTerminalDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key="MENU.TERMINAL"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var compAc;

function loadPage() 
{
    initGrid();
    acSysDesc("consultCompTerminalCommonDTO.filterServiceTypeDesc","consultCompTerminalCommonDTO.filterServiceTypeId","SERVICE_TYPE");
    compAc = new autoC({"consultCompTerminalCommonDTO.filterCompNoDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setCustomLov("lovComp('consultCompTerminalCommonDTO.filterCompNo', 'consultCompTerminalCommonDTO.filterCompNoDesc')");
    compAc.setAcResultMap({
        "consultCompTerminalCommonDTO.filterCompNo":"code",
    });
    compAc.init();
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
    	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = "";
        return sortColumn("consultCompTerminalList", this, consultCompTerminalListForm, "ACCESSMNLID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompTerminalList.do";
    consultCompTerminalListForm.elements['strutsAction'].value = '<%=ConsultCompTerminalListAction.TERMINAL_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompTerminalListForm), "ACCESSMNLID", "Y");

}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_compNo, _accessMnlId)
{
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = _accessMnlId;
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.compNo'].value = _compNo;
	findGridList('ReloadRow');
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = "";
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.compNo'].value = "";
}

function goSearch()
{
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = "";	// 검색시 Tab 이동Key Clear
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.compNo'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value =  getValueById(myGrid, selectedId,'ACCESSMNLID');  
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.compNo'].value =  getValueById(myGrid, selectedId,'COMPNO');  
	goCommonTabPage(consultCompTerminalListForm, <%= ConsultCompTerminalDetailAction.TERMINAL_DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('consultCompTerminalDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value =  getValueById(myGrid, selectedId,'ACCESSMNLID');  
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.compNo'].value =  getValueById(myGrid, selectedId,'COMPNO');  
	consultCompTerminalListForm.elements['strutsAction'].value = '<%=ConsultCompTerminalDetailAction.TERMINAL_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(consultCompTerminalListForm), 'consultCompTerminalDetail'); 
} 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "consultCompTerminalDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = "";
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.compNo'].value = "";
	goCommonTabPage(consultCompTerminalListForm, '', pageId);	
}

/**
 * Excel Export
 */
function goExcel()
{
	consultCompTerminalListForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = "";
    excelServerAction("consultCompTerminalList",consultCompTerminalListForm);
}

/**
 * 삭제
 */
 function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'ACCESSMNLID', 'COMPNO'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	consultCompTerminalListForm.strutsAction.value = '<%=ConsultCompTerminalListAction.TERMINAL_DELETE%>';
  	var url = contextPath + "/consultCompTerminalList.do";
  	
  	$.post(url,FormQueryString(consultCompTerminalListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('consultCompTerminalDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompTerminalList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompTerminalCommonDTO.accessMnlId"/><!-- Key -->
<html:hidden property="consultCompTerminalCommonDTO.compNo"/><!-- Key -->
<html:hidden property="consultCompTerminalCommonDTO.filterServiceTypeId"/>
<html:hidden property="consultCompTerminalCommonDTO.filterCompNo"/>
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
		               <label><bean:message key="LABEL.compDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="consultCompTerminalCommonDTO.filterCompNoDesc" tabindex="1"/>
	               	   		<p class="open_spop">
	               	   			<a><span>조회</span></a>
	               	   		</p>
	               	   </div>
               	   </div>
				<div class="field">
					<label><bean:message key="LABEL.terminalDesc"/></label>
					<div class="input_box">
						<html:text property="consultCompTerminalCommonDTO.filterDesc" tabindex="10"/>
					</div>
				</div>
				
				<div class="field">
					<label><bean:message key="LABEL.serviceType"/></label>
					<div class="input_box">
						<html:text property="consultCompTerminalCommonDTO.filterServiceTypeDesc" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
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