<%--===========================================================================
목록
author  euna0207
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.unit.action.AssetRptEqUnitsAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메시지 수신설정 -->
<title><bean:message key='TAB.msgEmp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">

var eqLocAc, empAc, equipAc, plantAc, deptAc, eqCtgAc;
function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	assetRptEqUnitsForm.elements['assetRptEqUnitsDTO.equipId'].value = "";
        return sortColumn("assetRptEqUnits", this, assetRptEqUnitsForm, "EQUIPID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	//설비위치
    eqLocAc = new autoC({"assetRptEqUnitsDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    eqLocAc.setAcDConditionMap({
    	"plant" : "assetRptEqUnitsDTO.filterPlantId"
    });
    eqLocAc.setAcResultMap({
        "assetRptEqUnitsDTO.filterEqLocId":"EQLOC_ID"
    });
    eqLocAc.init();
	
    //공장명
    plantAc = new autoC({"assetRptEqUnitsDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptEqUnitsDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
    //설비종류
    eqCtgAc = new autoC({"assetRptEqUnitsDTO.filterEqCtgDesc":"full_desc"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    eqCtgAc.setAcResultMap({
        "assetRptEqUnitsDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    
    //관리부서
    deptAc = new autoC({"assetRptEqUnitsDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "assetRptEqUnitsDTO.filterDeptId":"dept_id"
        ,"assetRptEqUnitsDTO.filterPlantId":"plant"
        ,"assetRptEqUnitsDTO.filterPlantDesc":"plantDesc"
    });
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    deptAc.setAcDConditionMap({
    	"plant" : "assetRptEqUnitsDTO.filterPlantId"
    });
   
    deptAc.init();
    
	setHeader(myGrid, "gridbox", "setCal"); // grid, grid id, callBack
}

function setCal()
{
	myGrid.setDateFormat("%Y-%m-%d %H:%i:%s","%Y%m%d%H%i%s");
	goSearch();
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	assetRptEqUnitsForm.strutsAction.value = '<%=AssetRptEqUnitsAction.LIST_FIND%>';
	var url = contextPath + "/assetRptEqUnits.do";
		
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptEqUnitsForm), "EQUIPID", "Y");
}


/**
 * Excel Export
 */
function goExcel()
{
	assetRptEqUnitsForm.elements['assetRptEqUnitsDTO.equipId'].value = "";
	excelServerAction("assetRptEqUnits", assetRptEqUnitsForm);  
}
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptEqUnits.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetRptEqUnitsDTO.equipId"/><!-- key -->

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
					<div class="b_line"></div> 
					<div class="fb_group1">
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<!-- 설비번호 -->
					<div class="field">
						<label><bean:message key="LABEL.equipNo"/></label>
						<div class="input_box">
							<html:text property="assetRptEqUnitsDTO.filterItemNo" tabindex="10"/>
						</div>
					</div>
					<!-- 설비명 -->
					<div class="field">
						<label><bean:message key="LABEL.equipName"/></label>
						<div class="input_box">
							<html:text property="assetRptEqUnitsDTO.filterEquipDesc" tabindex="20"/>
						</div>
					</div>
					<!-- 설비위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="assetRptEqUnitsDTO.filterEqLocDesc" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 공장 -->
					<div class="field">
						<label><bean:message key="LABEL.plant"/></label>
						<div class="input_box">
							<html:text property="assetRptEqUnitsDTO.filterPlantDesc" tabindex="40"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 설비종류 -->
					<div class="field">
						<label><bean:message key="LABEL.eqCtgDesc"/></label>
						<div class="input_box">
							<html:text property="assetRptEqUnitsDTO.filterEqCtgDesc" tabindex="50"/>
							<p class="open_spop"><a><span>조회</span></a></p>							
						</div>
					</div>
					<!-- 관리부서 -->
					<div class="field">
						<label><bean:message key="LABEL.mngDept"/></label>
						<div class="input_box">
							<html:text property="assetRptEqUnitsDTO.filterDeptDesc" tabindex="60"/>
							<p class="open_spop"><a><span>조회</span></a></p>							
						</div>
					</div>
					<!-- 부위명 -->
					<div class="field">
						<label><bean:message key="LABEL.ctgAsmbName"/></label>
						<div class="input_box">
							<html:text property="assetRptEqUnitsDTO.filterEqCtgAsmbDesc" tabindex="70"/>
						</div>
					</div>																				
					<!-- 수정일자 -->
					<div class="field">
						<label><bean:message key="LABEL.modifyDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="assetRptEqUnitsDTO.filterUpDate" tabindex="80" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="assetRptEqUnitsDTO.filterEndUpDate" tabindex="90" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
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
					<div class="b_line"></div> 
					<div class="fb_group1">
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