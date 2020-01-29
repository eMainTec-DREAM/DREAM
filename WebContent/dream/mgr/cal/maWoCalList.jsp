<%--===========================================================================
Working Calendar- 목록
author kim21017
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.cal.action.MaWoCalListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- Working Calendar -->
<title><bean:message key="MENU.WORKCAL"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var plantTypeAc;
var wrkcalAc;

function loadPage() 
{
	today = new Date();
	var year  = today.getFullYear();
	var month = today.getMonth()+1;
	
	var lastDate;
	lastDate = new Date(today.getYear(), today.getMonth()+1,0);
	
	initGrid();
	if(month<10) month='0'+month;
	maWoCalListForm.elements['maWoCalCommonDTO.filterStartDate'].value = year+"-"+month+"-01";
	maWoCalListForm.elements['maWoCalCommonDTO.filterEndDate'].value   = year+"-"+month+"-"+lastDate.getDate();
	
	wrkcalAc = new autoC({"maWoCalCommonDTO.filterWrkcalListDesc":"description"});
	wrkcalAc.setTable("TAWRKCALLIST");
	wrkcalAc.setAcConditionMap({
		"comp_no":loginUser.compNo
      , "is_use" : "Y"
    });
	wrkcalAc.setKeyName("maWoCalCommonDTO.filterWrkcalListId");
	wrkcalAc.setAcResultMap({
	    "maWoCalCommonDTO.filterWrkcalListId":"wrkcalList_Id"
	});
	wrkcalAc.init();
	
	acSysDesc("maWoCalCommonDTO.filterIsJoin","maWoCalCommonDTO.filterIsJoin","IS_USE");

}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
    	
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ 
    	setCounter(grdObj,"gridbox");
    	//setRowSpan(myGrid,'YYYY'); 
    	//setRowSpan(myGrid,'MM'); 
    }); 
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maWoCalListForm.elements['maWoCalCommonDTO.wrkCalendarId'].value = "";
    	return sortColumn("maWoCalList", this, maWoCalListForm, "WRKCALENDARID", ind, direction);
	});
    myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWoCalList.do";

    maWoCalListForm.elements['strutsAction'].value = '<%=MaWoCalListAction.WO_CAL_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoCalListForm), "WRKCALENDARID","Y");
    /* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maWoCalListForm), function(_data){
    	myGrid.parse(_data,"js");
    	//isWork 값이 N 이면 text를 빨간색으로 변경
    	for(var i=1; i<=myGrid.getRowsNum(); i++){
    		if(getValueById(myGrid,i,"ISWORK")=='N'){
    			myGrid.setRowTextStyle(i, "color: red;");
    		}
    	}
    }); */
}


function afterSearch(_gridId, _data)
{
	for(var i=1; i<=myGrid.getRowsNum(); i++)
	{
		var rowId = myGrid.getRowId(i);
		if(typeof rowId == "undefined")continue;
		
		if(getValueById(myGrid,rowId,"ISWORK")=='N') myGrid.setRowTextStyle(rowId, "color: red;");
	}
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	if(checkValidation()) return;

	maWoCalListForm.elements['maWoCalCommonDTO.wrkCalendarId'].value = "";    // 검색시 Tab 이동Key Clear
	findGridList('Search');
}

 /**
 * 선택항목 근무로 지정 
 */
function goDayon()
{
	var selArray = getSelectedRows(myGrid, 'ISDELCHECK', 'WRKCALENDARID', 'ISWORK');
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0062'/>", function(result){
		if(result){
		 selectArray = selArray;
		 maWoCalListForm.strutsAction.value = '<%=MaWoCalListAction.WO_CAL_LIST_DAYON%>';
			var url = contextPath + "/maWoCalList.do"; 
		   $.post(url,FormQueryString(maWoCalListForm)+selectArray , function(_data){
			   afterDayonoff();
		   });
	 }
	}); 
}
/**
 * 선택항목 휴무로 지정 
 */
function goDayoff()
{
	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'WRKCALENDARID');
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0063'/>", function(result){
		 if(result){
			 selectArray = selArray;
			 maWoCalListForm.strutsAction.value = '<%=MaWoCalListAction.WO_CAL_LIST_DAYOFF%>';
				var url = contextPath + "/maWoCalList.do"; 
			   $.post(url,FormQueryString(maWoCalListForm)+selectArray , function(_data){
				   afterDayonoff();
			   });
		 }
		});
}

function afterDayonoff()
{
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG0030"/>');
}
 
/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    maWoCalListForm.elements['maWoCalCommonDTO.wrkCalendarId'].value = "";
	excelServerAction("maWoCalList", maWoCalListForm );  
}


function goSchedreset()
{
/* 	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'WRKCALENDARID');
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	var selectArray = selArray.substring(1,selArray.length).split("&");
	var selectStr;
	for(var i=0; i<selectArray.length;i++){
		//alert(selectArray[i]);
		if(i==0)
		{
			selectStr=selectArray[i].split("=")[1];
		}
		else
		{
			selectStr= selectStr+","+ selectArray[i].split("=")[1];
		}

		//selectStr +=","+selectArray[i].split("=")[1];
	}
	
	//alert(selectStr);
	var param = "maWoCalCommonDTO.selArray="+selectStr; */
	
openLayerPopup("maWoCalPopup");
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maWoCalList">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoCalCommonDTO.wrkCalendarId"/><!-- Key -->
<html:hidden property="maWoCalCommonDTO.filterWrkcalListId"/><!-- Key -->
<html:hidden property="maWoCalCommonDTO.selArray"/>
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
			<!--일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workDate"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoCalCommonDTO.filterStartDate" tabindex="10" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="maWoCalCommonDTO.filterEndDate" tabindex="20" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			
			<!--근무달력 -->
			<div class="field">
				<label><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="maWoCalCommonDTO.filterWrkcalListDesc" tabindex="40" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!--근무여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isWork"/></label>
				<div class="input_box">
					<html:text property="maWoCalCommonDTO.filterIsJoin" tabindex="50" />
					<p class="open_spop">
						<a><span>조회</span></a>
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
			<div class="fb_group2"></div>
		</div>
	</div><!--sheader_box end-->
	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
		</div>
	</div>
</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>