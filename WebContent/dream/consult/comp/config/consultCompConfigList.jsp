<%--===========================================================================
시스템환경변수 - 목록
author  kim21017
version $Id: consultCompConfigList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.config.action.ConsultCompConfigListAction" %>
<%@ page import="dream.consult.comp.config.action.ConsultCompConfigDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 시스템 환경변수 -->
<title><bean:message key='MENU.CONFIG'/></title>
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
    
    compAc = new autoC({"consultCompConfigCommonDTO.filterCompDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompConfigCommonDTO.filterCompNo");
    compAc.setAcResultMap({
        "consultCompConfigCommonDTO.filterCompNo":"comp_no",
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
    	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = "";
        return sortColumn("consultCompConfigList", this, consultCompConfigListForm, "COMPCONFIGID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompConfigList.do";
    consultCompConfigListForm.elements['strutsAction'].value = '<%=ConsultCompConfigListAction.CONFIG_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompConfigListForm), "COMPCONFIGID", "Y");

}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = "";	// 검색시 Tab 이동Key Clear
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compNo'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compNo'].value = getValueById(myGrid, selectedId,'COMPNO');
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = getValueById(myGrid, selectedId,'COMPCONFIGID');
    
	goCommonTabPage(consultCompConfigListForm, <%= ConsultCompConfigDetailAction.CONFIG_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_compNo, _compconfigId)
{
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compNo'].value = _compNo;
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = _compconfigId;
	findGridList('ReloadRow');
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compNo'].value = "";
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = "";
}
/**
 * 상세 열기
 */
 function goOpen(){
	goTabPage('consultCompConfigDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     consultCompConfigListForm.elements['consultCompConfigCommonDTO.compNo'].value = getValueById(myGrid, selectedId,'COMPNO');
	 consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = getValueById(myGrid, selectedId,'COMPCONFIGID');  
	 consultCompConfigListForm.elements['strutsAction'].value = '<%=ConsultCompConfigDetailAction.CONFIG_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(consultCompConfigListForm), 'consultCompConfigDetail'); 
 } 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "consultCompConfigDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = "";
	goCommonTabPage(consultCompConfigListForm, '', "consultCompConfigDetail");
}

/**
 * Excel Export
 */
function goExcel()
{
	consultCompConfigListForm.elements['consultCompConfigCommonDTO.compconfigId'].value = "";
    excelServerAction("consultCompConfigList",consultCompConfigListForm);
}

/**
 * 삭제
 */
function goDelete()
{
	//myGrid에 1(2번째)번에 체크가 되어있으면 3번 데이터 가져오기
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'COMPCONFIGID', 'COMPNO'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	
	consultCompConfigListForm.strutsAction.value = '<%=ConsultCompConfigListAction.CONFIG_LIST_DELETE%>';
	var url = contextPath + "/consultCompConfigList.do";
	$.post(url,FormQueryString(consultCompConfigListForm)+delArray , function(_data){
	   	afterDelete();
	});
}

function afterDelete()
{
	goClose('consultCompConfigDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompConfigList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompConfigCommonDTO.compNo"/><!-- Key -->
<html:hidden property="consultCompConfigCommonDTO.filterCompNo"/><!-- Key -->
<html:hidden property="consultCompConfigCommonDTO.compconfigId"/><!-- Key -->
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
                            <html:text property="consultCompConfigCommonDTO.filterCompDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a><span>조회</span></a>
                            </p>
                       </div>
                   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.configDesc"/></label>
		               <div class="input_box">
		               		<html:text property="consultCompConfigCommonDTO.filterDesc" tabindex="10"/>
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