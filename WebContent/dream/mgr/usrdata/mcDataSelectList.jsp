<%--===========================================================================
메뉴 - 목록
author  kim21017
version $Id: mcDataSelectList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrdata.action.McDataSelectListAction" %>
<%@ page import="dream.mgr.usrdata.action.McDataSelectDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메뉴 -->
<title><bean:message key='MENU.mcDataSelectList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

//그리드명
var myGrid,creByAc;

function loadPage() 
{
	setFunction();
	
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
    	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";
    	return sortColumn("mcDataSelectList", this, mcDataSelectListForm, "usrrptId", ind, direction);
	});
    
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

function setFunction()
{
    creByAc = new autoC({"mcDataSelectCommonDTO.creByDesc":"y.emp_name"});
    creByAc.setTable("TAEMP");
    creByAc.setAcConditionMap({
        "comp_no": loginUser.compNo
      , "x.is_use":"Y"
	   });
    creByAc.setAcDConditionMap({
    	"emp_name":"mcDataSelectCommonDTO.creByDesc"
	   });
    creByAc.setAcResultMap({
    	"mcDataSelectCommonDTO.creBy":"emp_id"
    	,"mcDataSelectCommonDTO.creByDesc":"emp_name"
    });
    creByAc.init();  
    
    acSysDesc("mcDataSelectCommonDTO.usrdataTypeDesc","mcDataSelectCommonDTO.usrdataType","USRDATA_TYPE");
	   
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mcDataSelectList.do";
    mcDataSelectListForm.elements['strutsAction'].value = '<%=McDataSelectListAction.DATA_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mcDataSelectListForm), "usrrptId");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";	// 검색시 Tab 이동Key Clear
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
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = getValueById(myGrid, selectedId,'usrrptId');
	goCommonTabPage(mcDataSelectListForm, <%= McDataSelectDetailAction.DATA_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrrptId)
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = _usrrptId;
	findGridList('ReloadRow');
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('mcDataSelectDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = getValueById(myGrid, selectedId,'usrrptId');
    mcDataSelectListForm.elements['strutsAction'].value = '<%=McDataSelectDetailAction.DATA_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mcDataSelectListForm), 'mcDataSelectDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "mcDataSelectDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";
	goCommonTabPage(mcDataSelectListForm, '', "mcDataSelectDetail");
}

/**
 * Excel Export
 */
function goExcel()
{
	mcDataSelectListForm.elements['mcDataSelectCommonDTO.usrrptId'].value = "";
	excelServerAction("mcDataSelectList", mcDataSelectListForm ); 
// 	excelAction(myGrid);
}

/**
 * 삭제
 */
function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'usrrptId'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	mcDataSelectListForm.strutsAction.value = '<%=McDataSelectListAction.DATA_LIST_DELETE%>';
	var url = contextPath + "/mcDataSelectList.do";
	
	$.post(url,FormQueryString(mcDataSelectListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  

function afterDelete(){
	goClose('mcDataSelectDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mcDataSelectList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mcDataSelectCommonDTO.usrrptId"/><!-- Key -->
<html:hidden property="mcDataSelectCommonDTO.creBy"/>
<html:hidden property="mcDataSelectCommonDTO.usrdataType"/>
<html:hidden property="mcDataSelectCommonDTO.usrrptType"/>
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
		               <label><bean:message key="LABEL.title"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="mcDataSelectCommonDTO.title" tabindex="10"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.creBy"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="mcDataSelectCommonDTO.creByDesc" tabindex="20"/>
	               	   		<p class="open_spop"><a><span>조회</span></a></p>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.creDate"/></label>
		               <div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="mcDataSelectCommonDTO.creDateFrom" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="mcDataSelectCommonDTO.creDateTo" tabindex="40" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
	               </div>
				<div class="field">
					<label><bean:message key="LABEL.usrdataType"/></label>
					<div class="input_box">
						<html:text property="mcDataSelectCommonDTO.usrdataTypeDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
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
	    <div class="article_box" >
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>