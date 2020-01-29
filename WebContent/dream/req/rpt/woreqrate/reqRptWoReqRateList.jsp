<%--===========================================================================
요청접수율(처리자)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.rpt.woreqrate.action.ReqRptWoReqRateListAction" %>
<%@ page import="dream.req.rpt.woreqrate.action.ReqRptWoReqRateDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 요청접수율(처리자) -->
<title><bean:message key='MENU.WOREQRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var deptAc;
var plantAc;
var selectedGridId;

function loadPage() 
{
	reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterStartDate'].value = getMinusMonth2(new Date(), -1);
	reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterEndDate'].value   = getToday();
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
 	// 요청일기준 - 기본 선택.
    reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterIsReqDate'].value = "Y";
    reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterIsReqDateDesc'].value = "Y";
    valSysDirCode('reqRptWoReqRateCommonDTO.filterIsReqDate', 'reqRptWoReqRateCommonDTO.filterIsReqDateDesc', 'IS_USE','', true);
	
	initGrid();
	initChart();
	
	deptAc = new autoC({"reqRptWoReqRateCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "reqRptWoReqRateCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "reqRptWoReqRateCommonDTO.filterPlantId"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"reqRptWoReqRateCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "reqRptWoReqRateCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("reqRptWoReqRateCommonDTO.filterIsReqDateDesc","reqRptWoReqRateCommonDTO.filterIsReqDate","IS_USE",true);
}

var myGrid;
var myChart;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedGridId = rowId;
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterDeptId'].value = "";
    	return sortColumn("reqRptWoReqRateList", this, reqRptWoReqRateListForm, "DEPTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myChart =  new dhtmlXChart({
		view:"stackedBar",
		container:"chartbox",
		value:"#COMPLETECNT#",
		color: "#ffbf1e",
		gradient:"falling",
		tooltip:{
			template:"#COMPLETERATE#"+"%"
		},
		xAxis:{
			template:"'#PARTDESC#",
			step:1
		},
		yAxis:{},
		legend:{
			values:[{text:"<bean:message key='LABEL.workReqCnt'/>",color:"#dddddd"},{text:"<bean:message key='LABEL.completeCnt'/>",color:"#ffbf1e"}],
			valign:"middle",
			align:"right",
			width:90,
			layout:"y"
		}
	});
	myChart.addSeries({
		value:"#INCOMPLETECNT#",
		color:"#dddddd"
	});
}

$(window).resize(function(){
	myChart.resize();
});

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');
    reqRptWoReqRateListForm.elements['reqRptWoReqRateDetailDTO.startDate'].value = reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterStartDate'].value;
    reqRptWoReqRateListForm.elements['reqRptWoReqRateDetailDTO.endDate'].value = reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterEndDate'].value;
    reqRptWoReqRateListForm.elements['reqRptWoReqRateDetailDTO.isReqDate'].value = reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterIsReqDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.reqRptWoReqRateListForm;	
	form.strutsAction.value = '<%=ReqRptWoReqRateListAction.LIST_FIND %>';
	
	var url = contextPath + "/reqRptWoReqRateList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqRptWoReqRateListForm), "DEPTID", "Y");
	
	$.post(url,FormQueryString(reqRptWoReqRateListForm), function(_data){
		myChart.clearAll();
    	myChart.parse(_data,"json");
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
	var form = document.reqRptWoReqRateListForm;
	
	form.elements['reqRptWoReqRateDetailDTO.deptDesc'].value = getValueById(myGrid, selectedId,'PARTDESC');
	form.elements['reqRptWoReqRateDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('reqRptWoReqRateDetailList');
	goTabPage('reqRptWoReqRateDetailChart');
}

/**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  if(checkValidation()) return;
  	excelServerAction("reqRptWoReqRateList", reqRptWoReqRateListForm);
  }
 
  /**
  * 접수리스트 보기
  */
  function goWorkreslist(){
	if(typeof selectedGridId == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    var url   = contextPath + "/maWoReqList.do";
 
    var popWidth = 1010;
    var popHeight = 640;
 
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);
 
    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "isDecoratorName=popupPage"+
    				"&strutsAction=&maWoReqCommonDTO.filterReqStartDate="+reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterStartDate'].value.replace(/-/gi,"")+
    				"&maWoReqCommonDTO.filterReqEndDate="+reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterEndDate'].value.replace(/-/gi,"")+
    				"&maWoReqCommonDTO.filterRecDeptId="+getValueById(myGrid, selectedGridId,'DEPTID')+
    				"&maWoReqCommonDTO.filterRecDeptDesc="+getValueById(myGrid, selectedGridId,'PARTDESC')+
    				"&maWoReqCommonDTO.filterPlantId="+reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterPlantId'].value+
    				"&maWoReqCommonDTO.filterPlantDesc="+reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterPlantDesc'].value;
    openWindowWithPost(url, "WORESLIST_POPUP", param, pos);
    
  }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqRptWoReqRateList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="reqRptWoReqRateCommonDTO.filterDeptId"/>
<html:hidden property="reqRptWoReqRateCommonDTO.filterPlantId"/>
<html:hidden property="reqRptWoReqRateCommonDTO.filterIsReqDate"/>
<html:hidden property="reqRptWoReqRateDetailDTO.deptId"/>
<html:hidden property="reqRptWoReqRateDetailDTO.deptDesc"/>
<html:hidden property="reqRptWoReqRateDetailDTO.startDate"/>
<html:hidden property="reqRptWoReqRateDetailDTO.endDate"/>
<html:hidden property="reqRptWoReqRateDetailDTO.isReqDate"/>

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
							<html:text property="reqRptWoReqRateCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="reqRptWoReqRateCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 요청일기준  -->
                <div class="field">
                    <label><bean:message key="LABEL.isReqDate"/></label>
                    <div class="input_box">
						<html:text property="reqRptWoReqRateCommonDTO.filterIsReqDateDesc" tabindex="30" />
						<p class="open_spop">
                           <a>
                               <span>조회</span>
                           </a>
                        </p>
                    </div>
                </div>
				<!-- 부서  -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="reqRptWoReqRateCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="reqRptWoReqRateCommonDTO.filterPlantDesc" tabindex="50" />
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
                <div class="stitle_tx"><bean:message key="LABEL.comWoReqRateGraphPart"/></div>
            </div>
            <div class="function_box">
                <div class="fb_group3">
                    <div class="sfb_wrap" style="display:none;">
                    </div>
                </div>

                <div class="fb_group2">
                </div>
            </div>
        </div>
        <div class="article_box">
            <div class="grid_area">
                <div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
            </div>
        </div>
    </div> <!--  end section_wrap -->	
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key='LABEL.comWoReqRateGraphMan'/></div>
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