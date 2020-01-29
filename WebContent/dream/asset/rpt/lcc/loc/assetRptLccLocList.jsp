<%--===========================================================================
고장TOP(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.loc.action.AssetRptLccLocListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장TOP(위치) -->
<title><bean:message key='MENU.LCCByLoc'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var selectedEqLocId = "";
var selectedCid;
var selectedRowId;

var eqLocAc;
var plantAc,uDeptAc;

function loadPage() 
{
	if(window.name != "LINKED_POPUP")
	{
		assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterStartDate'].value = getYear()+"-01";
		assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
		
		//공장명
	    if(loginUser.filterPlant!='null'){
	    	assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
	
	initGrid();
	
	eqLocAc = new autoC({"assetRptLccLocCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "assetRptLccLocCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.setAcDConditionMap({
    	"plant" : "assetRptLccLocCommonDTO.filterPlantId"
    });
    eqLocAc.init();
    
    uDeptAc = new autoC({"assetRptLccLocCommonDTO.filterUsageDeptDesc":"description"});
    uDeptAc.setTable("TADEPT");
    uDeptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    uDeptAc.setAcResultMap({
        "assetRptLccLocCommonDTO.filterUsageDept":"dept_id"
    });
    uDeptAc.setAcDConditionMap({
    	"plant" : "assetRptLccLocCommonDTO.filterPlantId"
    });
    uDeptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"assetRptLccLocCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptLccLocCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("assetRptLccLocCommonDTO.filterEqLocLevelDesc","assetRptLccLocCommonDTO.filterEqLocLevel","EQLOC_LVL_TYPE", true);
    
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
		selectedEqLocId = rowId;
		selectedCid = columnId;
		goOpen();
	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.startDate'].value = assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterStartDate'].value;
    assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.endDate'].value = assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterEndDate'].value;
    assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.plantId'].value = assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterPlantId'].value;
    assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.plantDesc'].value = assetRptLccLocListForm.elements['assetRptLccLocCommonDTO.filterPlantDesc'].value;
    findGridList('Search');
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.assetRptLccLocListForm;	
	form.strutsAction.value = '<%=AssetRptLccLocListAction.LCC_LOC_LIST_FIND %>';
	
	var url = contextPath + "/assetRptLccLocList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptLccLocListForm), "EQLOCID", "Y");
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
	var form = document.assetRptLccLocListForm;
	
	form.elements['assetRptLccLocDetailDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
	form.elements['assetRptLccLocDetailDTO.eqLocDesc'].value = getValueById(myGrid, selectedId,'EQLOCDESC');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('assetRptLccLocDetailList');
	goTabPage('assetRptLccLocDetailChart');
	goTabPage('assetRptLccLocMaintAmtDetailChart');
	goTabPage('assetRptLccLocWorkTimeDetailChart');
	goTabPage('assetRptLccLocFailCodeDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  excelServerAction("assetRptLccLocList", assetRptLccLocListForm );
  }
 
/**
 * 설비별 보기
 */
function goEqlcc()
{
	var eqLocId = "";
	var eqLocDesc = "";
	var startDate = assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.startDate'].value;
	var endDate = assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.endDate'].value;
	
	if(!(selectedEqLocId == null||selectedEqLocId=='')){
		eqLocId = getValueById(myGrid, selectedEqLocId,'EQLOCID');
		eqLocDesc = getValueById(myGrid, selectedEqLocId,'EQLOCDESC');
	}
	
	var url   = contextPath + "/assetRptLccEquipList.do";
	
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "isDecoratorName=popupPage"+
				"&assetRptLccEquipCommonDTO.strutsAction="+
				"&assetRptLccEquipCommonDTO.filterStartDate="+startDate+
				"&assetRptLccEquipCommonDTO.filterEndDate="+endDate+
				"&assetRptLccEquipCommonDTO.filterEqLocId="+eqLocId+
				"&assetRptLccEquipCommonDTO.filterEqLocDesc="+eqLocDesc; 
	//post 전송
	openWindowWithPost(url, "USE_PT_LIST_POPUP", param, pos);
}

/*
 * 고장이력 보기
 */
function goEqbmLink()
{
	if(typeof selectedRowId == "undefined" || "" == selectedRowId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
		return;
	}
	
	var eqlocId   = getValueById(myGrid, selectedRowId,'EQLOCID');
	var eqlocDesc = getValueById(myGrid, selectedRowId,'EQLOCDESC');
	var fromDate = assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.startDate'].value.replace("-","")+"01";
	var toDate = assetRptLccLocListForm.elements['assetRptLccLocDetailDTO.endDate'].value.replace("-","")+"31";
 	var woStatus = "C";
	
	console.log('eqlocId : ' + eqlocId + ", eqlocDesc : " + eqlocDesc);
	
	goEqbmList(eqlocId, eqlocDesc, '', '', fromDate, toDate, woStatus);
}
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccLocList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccLocCommonDTO.filterEqLocId"/>
<html:hidden property="assetRptLccLocCommonDTO.filterEqLocLevel"/>
<html:hidden property="assetRptLccLocCommonDTO.filterPlantId"/>
<html:hidden property="assetRptLccLocCommonDTO.filterUsageDept"/>

<html:hidden property="assetRptLccLocDetailDTO.eqLocId"/>
<html:hidden property="assetRptLccLocDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptLccLocDetailDTO.startDate"/>
<html:hidden property="assetRptLccLocDetailDTO.endDate"/>
<html:hidden property="assetRptLccLocDetailDTO.chartValue"/>
<html:hidden property="assetRptLccLocDetailDTO.plantId"/>
<html:hidden property="assetRptLccLocDetailDTO.plantDesc"/>

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
							<html:text property="assetRptLccLocCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptLccLocCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 설비위치  -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="assetRptLccLocCommonDTO.filterEqLocDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치Level  -->
				<div class="field">
					<label><bean:message key="LABEL.locLevel"/></label>
					<div class="input_box">
						<html:text property="assetRptLccLocCommonDTO.filterEqLocLevelDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="assetRptLccLocCommonDTO.filterUsageDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="assetRptLccLocCommonDTO.filterPlantDesc"
								tabindex="200" />
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
				<div class="fb_group2">
				</div>
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
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