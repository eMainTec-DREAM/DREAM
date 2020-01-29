<%--===========================================================================
MTBF,MTTR(사용부서)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.usdept.action.AssetRptMtbfmttrUsDeptListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR(사용부서) -->
<title><bean:message key='MENU.MTBFMTTRUSAGEDEPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 <style type="text/css">

.dhx_axis_item_x {
	padding-top:2px;
	-webkit-transform-origin: 0 0;
    -moz-transform-origin: 0 0;
         -o-transform-origin: 0 0;
            transform-origin: 0 0;
    -webkit-transform: rotate(-20deg);
       -moz-transform: rotate(-20deg);
         -o-transform: rotate(-20deg);
            transform: rotate(-20deg);
    margin-top:20px;  
    /* font-size: 15px; */
}

.dhx_canvas_text{
    position: absolute;
    text-align: center;
    overflow: hidden;
    white-space: nowrap;
    z-index: 1000;
    /* font-size: 15px; */
}

</style>
<script language="javascript">
<!--//

var plantAc, uDeptAc, eqGRade;
var selectedRowId;

function loadPage() 
{
	if(window.name != "LINKED_POPUP")
	{
		var startDate = assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterStartDate'].value;
		var endDate = assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterEndDate'].value;
		if((startDate=="" || startDate==null)&&(endDate=="" || endDate==null)) {
			assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterStartDate'].value = getYear()+"-01";
			assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
		}
		
		//공장명
	    if(loginUser.filterPlant!='null'){
	    	assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
	
	initGrid();
	initChart();
    
    uDeptAc = new autoC({"assetRptMtbfmttrUsDeptCommonDTO.filterUsageDeptDesc":"description"});
    uDeptAc.setTable("TADEPT");
    uDeptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    uDeptAc.setAcResultMap({
        "assetRptMtbfmttrUsDeptCommonDTO.filterUsageDept":"dept_id"
    });
    uDeptAc.setAcDConditionMap({
    	"plant" : "assetRptMtbfmttrUsDeptCommonDTO.filterPlantId"
    });
    uDeptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"assetRptMtbfmttrUsDeptCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptMtbfmttrUsDeptCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterSeparation'].value   = "L3";
    assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterSeparationDesc'].value   = "<bean:message key='CODESET.POP_LTYPE.L3'/>";
	valSysDir('assetRptMtbfmttrUsDeptCommonDTO.filterSeparation', 'assetRptMtbfmttrUsDeptCommonDTO.filterSeparationDesc', 'POP_LTYPE', true);
    
	// 구분 AC
	separAc = new autoC({"assetRptMtbfmttrUsDeptCommonDTO.filterSeparationDesc":"description"});
    separAc.setTable("TACDSYSD");
    separAc.setAcConditionMap({
    	"list_type":"POP_LTYPE"
   		,"param1":"dept"
    })
    separAc.setAcResultMap({
        "assetRptMtbfmttrUsDeptCommonDTO.filterSeparation":"cdsysd_no"
    });
    separAc.init();
    
    //-----------------------------------------------설비등급------------------------------------------------
    eqGRade = new autoC({"assetRptMtbfmttrUsDeptCommonDTO.filterEqGradeDesc":"description"});
    eqGRade.setAcDisplay("DESCRIPTION");
    eqGRade.setAcConditionMap({
        	"list_type":"EQ_GRADE"
        	,"param2":"EQUIP"
  	   });
    eqGRade.setTable("TACDSYSD");
    eqGRade.setAcResultMap({
        "assetRptMtbfmttrUsDeptCommonDTO.filterEqGrade":"cdsysd_no",
        "assetRptMtbfmttrUsDeptDetailDTO.filterEqGrade":"cdsysd_no"
    });
    eqGRade.init();
}

var myGrid;
var myMtbfChart;
var myMttrChart;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("assetRptMtbfmttrUsDeptList", this, assetRptMtbfmttrUsDeptListForm, "USAGEDEPTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myMtbfChart =  new dhtmlXChart({
		view:"bar",
		container:"mtbfchartbox",
		value:"#MTBF#",
		color: "#66ccff",
		gradient:"rising",
		label:function(val){
			return setNumberFormat(val.MTBF);
		},
		tooltip:{
			template:function(val){
				return val.USAGEDEPTDESC +" ("+ setNumberFormat(val.MTBF) +")";	
			}
		},
		xAxis:{
			template:"'#USAGEDEPTDESC#"
		},
		yAxis:{}
	});
	
	myMttrChart =  new dhtmlXChart({
		view:"bar",
		container:"mttrchartbox",
		value:"#MTTR#",
		color: "#66ccff",
		gradient:"rising",
		label:function(val){
			return setNumberFormat(val.MTTR);	
		},
		tooltip:{
			template:function(val){
				return val.USAGEDEPTDESC +" ("+ setNumberFormat(val.MTTR) +")";	
			}
		},
		xAxis:{
			template:"'#USAGEDEPTDESC#"
		},
		yAxis:{}
	});

}

$(window).resize(function(){
	myMtbfChart.resize();
	myMttrChart.resize();
});

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
    assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptDetailDTO.plantId'].value = assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterPlantId'].value;
    assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptDetailDTO.plantDesc'].value = assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterPlantDesc'].value;
    assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptDetailDTO.startDate'].value = assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterStartDate'].value;
    assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptDetailDTO.endDate'].value = assetRptMtbfmttrUsDeptListForm.elements['assetRptMtbfmttrUsDeptCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.assetRptMtbfmttrUsDeptListForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrUsDeptListAction.MTTRMTBF_EQUIP_LIST_FIND %>';
	
	var url = contextPath + "/assetRptMtbfmttrUsDeptList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptMtbfmttrUsDeptListForm), "USAGEDEPTID", "Y");
	
	$.post(url,FormQueryString(assetRptMtbfmttrUsDeptListForm), function(_data){
		myMtbfChart.clearAll();
		myMttrChart.clearAll();
		myMtbfChart.parse(_data,"json");
		myMttrChart.parse(_data,"json");
    });
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
	var form = document.assetRptMtbfmttrUsDeptListForm;
	
	form.elements['assetRptMtbfmttrUsDeptDetailDTO.usageDeptId'].value = getValueById(myGrid, selectedId,'USAGEDEPTID');
	form.elements['assetRptMtbfmttrUsDeptDetailDTO.usageDeptDesc'].value = getValueById(myGrid, selectedId,'USAGEDEPTDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('assetRptMtbfmttrUsDeptDetailList');
	goTabPage('assetRptMtbfmttrUsDeptDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  	excelServerAction("assetRptMtbfmttrUsDeptList", assetRptMtbfmttrUsDeptListForm);
  }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptMtbfmttrUsDeptList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterPlantId"/>
<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterUsageDeptId"/>
<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterSeparation"/>
<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterEqGrade"/>

<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.plantId"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.plantDesc"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.usageDeptId"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.usageDeptDesc"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.endDate"/>

<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.filterEqGrade"/>

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
				<!-- 월 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.month"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="assetRptMtbfmttrUsDeptCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptMtbfmttrUsDeptCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="assetRptMtbfmttrUsDeptCommonDTO.filterUsageDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="assetRptMtbfmttrUsDeptCommonDTO.filterPlantDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a>
                        </p>
                    </div>
                </div>
                <!-- 구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.separation"/></label>
                    <div class="input_box">
                        <html:text property="assetRptMtbfmttrUsDeptCommonDTO.filterSeparationDesc" tabindex="30" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 설비등급   -->
                <div class="field">
                    <label><bean:message key="LABEL.eqGrade"/></label>
                    <div class="input_box">
							<html:text property="assetRptMtbfmttrUsDeptCommonDTO.filterEqGradeDesc" tabindex="60" />
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
				<div class="stitle_tx"><bean:message key="LABEL.mtbfMttr"/></div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="ch_title"><bean:message key='LABEL.mtbfHour'/></div>
			<div class="grid_area">
				<div id="mtbfchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div> 
		<div class="article_box">
			<div class="ch_title"><bean:message key='LABEL.mttrMin'/></div>
			<div class="grid_area">
				<div id="mttrchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.mtbfMttr"/></div>
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
</html:form> 
</body>
</html>