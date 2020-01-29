<%--===========================================================================
무정지대표라인 - 목록
author  kim21017
version $Id: maNstGrpList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.manst.action.MaNstGrpListAction" %>
<%@ page import="dream.mgr.manst.action.MaNstGrpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 라인 무정지대표라인 -->
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
	/* maNstGrpListForm.elements['maNstGrpCommonDTO.filterYyyy'].value = dateToData(getToday()).substr(0, 4); */
    initGrid();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		/* maNstGrpListForm.elements['maNstGrpCommonDTO.year'].value = getValueById(myGrid, rowId,'YEAR'); // ID */
		goOpen();
	});
/* 	myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1); */
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox");});
	myGrid.attachEvent("onRowDblClicked", function(id,ind){
	});
	 myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id

}

function afterSearch()
{
// 	myGrid.collapseAll();
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maNstGrpList.do";
    maNstGrpListForm.elements['strutsAction'].value = '<%=MaNstGrpListAction.NST_GRP_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maNstGrpListForm), ["POPPLANTNO","POPDEPTNO","POPLINENO"]);
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(popPlantNo,popDeptNo,popLineNo,mainLineNo)
{
	maNstGrpListForm.elements['maNstGrpCommonDTO.popPlantNo'].value = popPlantNo;
	maNstGrpListForm.elements['maNstGrpCommonDTO.popDeptNo'].value = popDeptNo;
	maNstGrpListForm.elements['maNstGrpCommonDTO.popLineNo'].value = popLineNo;
/* 	maNstGrpListForm.elements['maNstGrpCommonDTO.mainLineNo'].value = mainLineNo; */
	findGridList('ReloadRow');
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maNstGrpListForm.elements['maNstGrpCommonDTO.popPlantNo'].value = "";	// 검색시 Tab 이동Key Clear
	maNstGrpListForm.elements['maNstGrpCommonDTO.popDeptNo'].value = "";	// 검색시 Tab 이동Key Clear
	maNstGrpListForm.elements['maNstGrpCommonDTO.popLineNo'].value = "";	// 검색시 Tab 이동Key Clear
	maNstGrpListForm.elements['maNstGrpCommonDTO.mainLineNo'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
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
	var form = document.maNstGrpListForm;
	maNstGrpListForm.elements['maNstGrpCommonDTO.popPlantNo'].value =  getValueById(myGrid, selectedId,'POPPLANTCODE');
	maNstGrpListForm.elements['maNstGrpCommonDTO.popDeptNo'].value =  getValueById(myGrid, selectedId,'POPDEPTCODE');
	maNstGrpListForm.elements['maNstGrpCommonDTO.popLineNo'].value =  getValueById(myGrid, selectedId,'POPLINENO');
	maNstGrpListForm.elements['maNstGrpCommonDTO.mainLineNo'].value = getValueById(myGrid, selectedId,'MAINLINENO');
    
	goCommonTabPage(form, <%= dream.mgr.manst.action.MaNstGrpDetailAction.NST_GRP_DETAIL_INIT %>, pageId);
    
}

/**
 * 상세 열기
 */
 function goOpen(){
	 goTabPage('maNstGrpDetail');
}
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelAction(myGrid);
 }

 /**
  * 생성
  */
 function goCreate()
 {
//  	createValidationCheck(myGrid, "maNstGrpDetail" , "goCreateAction");
 }
 
 function goCreateAction(pageId)
 {
	 maNstGrpListForm.elements['maNstGrpCommonDTO.popPlantNo'].value = "";
	 maNstGrpListForm.elements['maNstGrpCommonDTO.popDeptNo'].value  = "";
	 maNstGrpListForm.elements['maNstGrpCommonDTO.popLineNo'].value  = "";
	 maNstGrpListForm.elements['maNstGrpCommonDTO.mainLineNo'].value = "";
 	goCommonTabPage(maNstGrpListForm, '', "maNstGrpDetail");
 }

/**
 * 삭제
 */
 function goDelete(){
// 	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
// 	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'POPLINEID'); //Grid, check box column seq, pk column seq
// 	//체크된게 없으면 return
// 	 if(typeof delArray == "undefined") return;
	
<%--   	maNstGrpListForm.strutsAction.value = '<%=MaNstGrpListAction.NST_GRP_LIST_DELETE%>'; --%>
//   	var url = contextPath + "/maNstGrpList.do";
  	
//   	$.post(url,FormQueryString(maNstGrpListForm)+delArray , function(_data){
//     	afterDelete();
//     });
  }

// function afterDelete(){
// 	goClose('maNstGrpDetail');
// 	//goSearch();
// 	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
//   }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maNstGrpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maNstGrpCommonDTO.popPlantNo"/><!-- Key1 -->
<html:hidden property="maNstGrpCommonDTO.popDeptNo"/><!-- Key2 -->
<html:hidden property="maNstGrpCommonDTO.popLineNo"/><!-- Key3 -->
<html:hidden property="maNstGrpCommonDTO.mainLineNo"/><!-- Key4 -->
<html:hidden property="maNstGrpCommonDTO.year"/>
	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="maNstGrpCommonDTO.filterPlant" tabindex="10"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maNstGrpCommonDTO.filterDept" tabindex="20"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.line"/></label>
					<div class="input_box">
						<html:text property="maNstGrpCommonDTO.filterLine" tabindex="30"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.mainLine"/></label>
					<div class="input_box">
						<html:text property="maNstGrpCommonDTO.filterMainLine" tabindex="40"/>
					</div>
				</div>
			</div>
   		</div><!--article_box end-->
    </div> <!--  end section_wrap -->
		
	 <div class="section_wrap">
	    <div class="sheader_box">
	        <div class="stitle_box"><bean:message key="LABEL.List"/></div>
	        <div class="function_box list">
	          <div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
	        </div>
	    </div><!--sheader_box end-->
	    <div class="article_box">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:370px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>