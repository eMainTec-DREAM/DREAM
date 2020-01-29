<%--===========================================================================
부품사용분석 List
author  sy.yang
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.ptusehist.action.PartRptPtUseHistListAction" %>
<%@ page import="dream.part.rpt.ptusehist.action.PartRptPtUseHistDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부품사용분석 -->
<title><bean:message key='MENU.PTUSEHIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var deptAc;
var plantAc;
var partAc;

function loadPage() 
{
	if(window.name != "LINKED_POPUP")
	{
		//일자
		partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterStartDate'].value = getYear()+"-01";
		partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	
		//공장명
	    if(loginUser.filterPlant!='null'){
	    	partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
  
    //부서 자동완성
    deptAc = new autoC({"partRptPtUseHistCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    deptAc.setAcDConditionMap({
    	"plant" : "partRptPtUseHistCommonDTO.filterPlantId"
    });
    deptAc.setAcResultMap({
        "partRptPtUseHistCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    //공장 자동완성
    plantAc = new autoC({"partRptPtUseHistCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "partRptPtUseHistCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    //부품 자동완성
    partAc = new autoC({"partRptPtUseHistCommonDTO.filterPartDesc":"full_desc"});
    partAc.setTable("TAPARTS");
    partAc.setCustomLov("lovParts('partRptPtUseHistCommonDTO.filterPartId','', 'partRptPtUseHistCommonDTO.filterPartDesc')");
    partAc.setAcResultMap({
        "partRptPtUseHistCommonDTO.filterPartId":"part_id"
    });
    partAc.init();
    
    initGrid();
}

function initGrid()
{ 
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
    	selectedCid = columnId;
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.partId'].value = "";
        return sortColumn("partRptPtUseHistList", this, partRptPtUseHistListForm, "PARTID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{	
	if(checkValidation()) return;
	
	var form = document.partRptPtUseHistListForm;	
	form.strutsAction.value = '<%=PartRptPtUseHistListAction.LIST_FIND %>';
	
    var url = contextPath + "/partRptPtUseHistList.do";
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partRptPtUseHistListForm), "PARTID", "Y");

}

function goSearch()
{
	findGridList('Search');
	   
	partRptPtUseHistListForm.elements['partRptPtUseHistDetailDTO.startDate'].value = partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterStartDate'].value;
	partRptPtUseHistListForm.elements['partRptPtUseHistDetailDTO.endDate'].value = partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterEndDate'].value;
	partRptPtUseHistListForm.elements['partRptPtUseHistDetailDTO.plantId'].value = partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterPlantId'].value;
	partRptPtUseHistListForm.elements['partRptPtUseHistDetailDTO.deptId'].value = partRptPtUseHistListForm.elements['partRptPtUseHistCommonDTO.filterDeptId'].value;
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
	var form = document.partRptPtUseHistListForm;
	
	form.elements['partRptPtUseHistCommonDTO.partId'].value =  getValueById(myGrid, selectedId,'PARTID');
	form.elements['partRptPtUseHistDetailDTO.partId'].value = getValueById(myGrid, selectedId,'PARTID');
	form.elements['partRptPtUseHistDetailDTO.partNo'].value = getValueById(myGrid, selectedId,'PARTNO');
	form.elements['partRptPtUseHistDetailDTO.partDesc'].value = getValueById(myGrid, selectedId,'PARTDESC');

	goCommonTabPage(form, '',  pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('partRptPtUseHistDetailList');
    goTabPage('partRptPtMstrEqPartList');
}

/**
 * Excel Export
 */
function goExcel()
{
	excelAction(myGrid);
}

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
	<html:form action="/partRptPtUseHistList.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="partRptPtUseHistCommonDTO.partId"/><!-- Key -->
	<html:hidden property="partRptPtUseHistCommonDTO.filterPlantId"/>
	<html:hidden property="partRptPtUseHistCommonDTO.filterDeptId"/>
	<html:hidden property="partRptPtUseHistCommonDTO.filterPartId"/>
	
	<html:hidden property="partRptPtUseHistDetailDTO.startDate" />
	<html:hidden property="partRptPtUseHistDetailDTO.endDate" />
	<html:hidden property="partRptPtUseHistDetailDTO.deptId" />
	<html:hidden property="partRptPtUseHistDetailDTO.plantId" />
	<html:hidden property="partRptPtUseHistDetailDTO.partId" />
	<html:hidden property="partRptPtUseHistDetailDTO.partNo" />
	<html:hidden property="partRptPtUseHistDetailDTO.partDesc" />
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
							<html:text property="partRptPtUseHistCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="partRptPtUseHistCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="partRptPtUseHistCommonDTO.filterDeptDesc" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="partRptPtUseHistCommonDTO.filterPlantDesc" tabindex="40" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 자재 -->
				<div class="field">
					<label><bean:message key="LABEL.parts"/></label>
					<div class="input_box">
						<html:text property="partRptPtUseHistCommonDTO.filterPartDesc" tabindex="50" />
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
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>