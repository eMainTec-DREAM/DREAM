<%--===========================================================================
연간부품사용일정
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.mayearptsched.action.MaPmYearPtSchedListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 연간부품사용일정 -->
<title><bean:message key="MENU.PMYEARPTSCHED"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc;
var eqTypeAc;

function loadPage() 
{
    initGrid();
    initPartsGrid();
    initDateGrid();
    
    //년도    
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.filterYear'].value = dateToData(getToday()).substr(0, 4);
    
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;

    if(loginUser.eqCtgTypeId!='null'){
    	maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
    	maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
	}
    
    deptAc = new autoC({"maPmYearPtSchedCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maPmYearPtSchedCommonDTO.filterDeptId");
    deptAc.setAcResultMap({
        "maPmYearPtSchedCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    acSysDesc("maPmYearPtSchedCommonDTO.filterEqCtgTypeDesc","maPmYearPtSchedCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");

}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
    	if(columnId <= myGrid.getColIndexById("DEPTDESC") ) return;
    		
    	var month = columnId -1;
   	    if (month < 10)
   	    {
   	        month = "0" + month;
   	    }
    	   
    	var deptId = myGrid.cells(rowId, myGrid.getColIndexById("DEPTID")).getValue();
    	var yyyyMm = maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.filterYear'].value
                        + month;
    
    	findPartsGridList('Search', deptId, yyyyMm);
    	findDateGridList('Search', deptId, yyyyMm);
    });
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

function initPartsGrid()
{
    partsGrid = new dhtmlXGridObject('partsgridbox');
    partsGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    partsGrid.enableSmartRendering(true,500);
    partsGrid.attachEvent("onRowSelect",function(rowId, columnId){
    });
    partsGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"partsgridbox")}); 
    partsGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("maPmYearPtSchedList", this, maPmYearPtSchedListForm, "deptId", ind, direction, "partsgridbox");
	});
    partsGrid.init();
    setHeader(partsGrid, {"GRIDID":"partsgridbox","CALLBACK":""}); // grid, grid id
}

function initDateGrid()
{
    dateGrid = new dhtmlXGridObject('dategridbox');
    dateGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    dateGrid.enableSmartRendering(true,500);
    dateGrid.attachEvent("onRowSelect",function(rowId, columnId){
    });
    dateGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"dategridbox")}); 
    dateGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("maPmYearPtSchedList", this, maPmYearPtSchedListForm, "deptId", ind, direction, "dategridbox");
	});
    dateGrid.init();
    setHeader(dateGrid, {"GRIDID":"dategridbox","CALLBACK":""}); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPmYearPtSchedList.do";

    maPmYearPtSchedListForm.elements['strutsAction'].value = '<%=MaPmYearPtSchedListAction.PMYEAR_LIST_FIND%>';
/*     myGrid.clearAll(); 
    setModal();
    setLoading("gridbox");
    $.post(url,FormQueryString(maPmYearPtSchedListForm), function(_data){
        myGrid.parse(_data,"js");
        myGrid.expandAll(); //펼치기
        setCounter(myGrid,"gridbox"); //TreeGrid는 펼쳐진 Rows만 카운트 함.
        closeModal();
    });
 */     doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmYearPtSchedListForm), "deptId");
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 예방작업현황 그리드에 셋팅한다.
 */
function findPartsGridList(sheetAction, _deptId, _yyyyMm)
{
    var url = contextPath + "/maPmYearPtSchedList.do";
    maPmYearPtSchedListForm.elements['strutsAction'].value = '<%=MaPmYearPtSchedListAction.PMPARTS_LIST_FIND%>';

    partsGrid.clearAll();
    setLoading("partsgridbox");
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.deptId'].value  = _deptId;
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.yearMon'].value = _yyyyMm;
    
    setTitle("partsExpTitle");
    
    doGridAction(sheetAction, partsGrid, "partsgridbox", url, FormQueryString(maPmYearPtSchedListForm), "deptId" , "Y");
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 부품입고 그리드에 셋팅한다.
 */
function findDateGridList(sheetAction, _deptId, _yyyyMm)
{
    var url = contextPath + "/maPmYearPtSchedList.do";
    maPmYearPtSchedListForm.elements['strutsAction'].value = '<%=MaPmYearPtSchedListAction.PMDATE_LIST_FIND%>';

    dateGrid.clearAll();
    
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.deptId'].value  = _deptId;
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.yearMon'].value = _yyyyMm;
    
    setTitle("dateExpTitle");
    
    doGridAction(sheetAction, dateGrid, "dategridbox", url, FormQueryString(maPmYearPtSchedListForm), "deptId", "Y");
}

function setTitle(_titleId)
{
	var listLabel = "";
	var yearMon = maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.yearMon'].value;
	yearMon =  yearMon.substr(0, 4)+"-"+yearMon.substr(4)+"월";
	
	if(_titleId == "partsExpTitle")
	{
		listLabel = "<bean:message key='LABEL.partsExpList'/>";
	}
	else if(_titleId == "dateExpTitle")
	{
		listLabel = "<bean:message key='LABEL.dateExpList'/>";
	}
    document.getElementById(_titleId).innerText =listLabel+" ("+yearMon+")";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
    maPmYearPtSchedListForm.elements['maPmYearPtSchedCommonDTO.deptId'].value = "";    // 검색시 Tab 이동Key Clear
    findGridList('SearchTree');
}

/**
 * 상세 열기
 */
function goOpen()
{
    //goTabPage('maPmYearPtYearListDetail');
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}
/**
 * Excel Export
 */
function goDateExcel()
{
    excelAction(dateGrid);
}
/**
 * Excel Export
 */
function goPartsExcel()
{
    excelAction(partsGrid);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPmYearPtSchedList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmYearPtSchedCommonDTO.deptId"/><!-- Key -->
<html:hidden property="maPmYearPtSchedCommonDTO.yearMon"/><!-- Key -->
<html:hidden property="maPmYearPtSchedCommonDTO.filterDeptId"/>
<html:hidden property="maPmYearPtSchedCommonDTO.filterEqCtgTypeId"/>
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
                    <label><bean:message key="LABEL.year"/></label>
                    <div class="input_read">
                        <html:text property="maPmYearPtSchedCommonDTO.filterYear" tabindex="10" readonly="true"/>
                        <p class="open_year_calendar"><span>날짜</span></p>
                    </div>
                </div>          
                <!-- 담당부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.dept"/></label>
                    <div class="input_box">
                        <html:text property="maPmYearPtSchedCommonDTO.filterDeptDesc" tabindex="20"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 설비유형  -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtgType"/></label>
					<div class="input_box">
						<html:text property="maPmYearPtSchedCommonDTO.filterEqCtgTypeDesc" tabindex="30" />
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
				<div class="stitle_tx"><bean:message key="LABEL.yearPartsUsedList"/></div>
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
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
     </div> <!--  End of section_wrap -->
     
    <div class="section_wrap">
        <div class="sheader_box">
        	<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" id="partsExpTitle"><bean:message key="LABEL.partsExpList"/></div>
			</div>
			<div class="function_box">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
					<a href="javascript:goPartsExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
				</div>
			</div>
        </div><!--sheader_box end-->
        <div class="article_box">
            <div class="grid_area">
                <div id="partsgridbox" style="width:100%; height:213px; background-color:white;"></div>
            </div>
        </div>
    </div> <!--  End of section_wrap -->
    <div class="section_wrap">
        <div class="sheader_box">
        	<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" id="dateExpTitle"><bean:message key="LABEL.dateExpList"/></div>
			</div>
			<div class="function_box">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
					<a href="javascript:goDateExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
				</div>
			</div>
        </div><!--sheader_box end-->
        <div class="article_box">
            <div class="grid_area">
                <div id="dategridbox" style="width:100%; height:213px; background-color:white;"></div>
            </div>
        </div>
    </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>