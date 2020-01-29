<%--===========================================================================
접근터미널 - 목록
author  kim21017
version $Id: consultCompTerminalExList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.terminal.action.ConsultCompTerminalExListAction" %>
<%@ page import="dream.consult.comp.terminal.action.ConsultCompTerminalExDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key="MENU.TERMINALEX"/></title>
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
    
    // 회사명
    compAc = new autoC({"consultCompTerminalExCommonDTO.filterCompNoDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setAcResultMap({
        "consultCompTerminalExCommonDTO.filterCompNo":"code",
    });
    compAc.init();
    
    // 서비스구분
    acSysDesc("consultCompTerminalExCommonDTO.filterServiceTypeDesc","consultCompTerminalExCommonDTO.filterServiceTypeId","SERVICE_TYPE");
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
    	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = "";
        return sortColumn("consultCompTerminalExList", this, consultCompTerminalExListForm, "ACCESSMNLEXID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompTerminalExList.do";
    consultCompTerminalExListForm.elements['strutsAction'].value = '<%=ConsultCompTerminalExListAction.TERMINAL_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompTerminalExListForm), "ACCESSMNLEXID", "Y");

}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_compNo, _accessMnlExId)
{
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = _accessMnlExId;
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.compNo'].value = _compNo;
	findGridList('ReloadRow');
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = "";
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.compNo'].value = "";
}

function goSearch()
{
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = "";	// 검색시 Tab 이동Key Clear
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.compNo'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value =  getValueById(myGrid, selectedId,'ACCESSMNLEXID');  
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.compNo'].value =  getValueById(myGrid, selectedId,'COMPNO');  
	goCommonTabPage(consultCompTerminalExListForm, <%= ConsultCompTerminalExDetailAction.TERMINAL_DETAIL_INIT %>, pageId);
}


/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('consultCompTerminalExDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value =  getValueById(myGrid, selectedId,'ACCESSMNLEXID');  
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.compNo'].value =  getValueById(myGrid, selectedId,'COMPNO');  
	consultCompTerminalExListForm.elements['strutsAction'].value = '<%=ConsultCompTerminalExDetailAction.TERMINAL_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultCompTerminalExListForm), 'consultCompTerminalExDetail'); 
} 


/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "consultCompTerminalExDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = "";
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.compNo'].value = "";
	goCommonTabPage(consultCompTerminalExListForm, '', pageId);	
}

/**
 * Excel Export
 */
function goExcel()
{
	consultCompTerminalExListForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = "";
    excelServerAction("consultCompTerminalExList", consultCompTerminalExListForm);
}

/**
 * 삭제
 */
 function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'ACCESSMNLEXID', 'COMPNO'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	consultCompTerminalExListForm.strutsAction.value = '<%=ConsultCompTerminalExListAction.TERMINAL_DELETE%>';
  	var url = contextPath + "/consultCompTerminalExList.do";
  	
  	$.post(url,FormQueryString(consultCompTerminalExListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('consultCompTerminalExDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompTerminalExList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompTerminalExCommonDTO.accessMnlExId"/><!-- Key -->
<html:hidden property="consultCompTerminalExCommonDTO.compNo"/><!-- Key -->
<html:hidden property="consultCompTerminalExCommonDTO.filterServiceTypeId"/>
<html:hidden property="consultCompTerminalExCommonDTO.filterCompNo"/>
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
	               	   		<html:text property="consultCompTerminalExCommonDTO.filterCompNoDesc" tabindex="1"/>
	               	   		<p class="open_spop">
	               	   			<a><span>조회</span></a>
	               	   		</p>
	               	   </div>
               	   </div>
				<div class="field">
					<label><bean:message key="LABEL.terminalDesc"/></label>
					<div class="input_box">
						<html:text property="consultCompTerminalExCommonDTO.filterDesc" tabindex="10"/>
					</div>
				</div>
				
				<div class="field">
					<label><bean:message key="LABEL.serviceType"/></label>
					<div class="input_box">
						<html:text property="consultCompTerminalExCommonDTO.filterServiceTypeDesc" tabindex="20"/>
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