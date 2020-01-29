<%--===========================================================================
설비별 고장강도율
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.bdintensity.action.AssetRptBdIntensityListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비별 고장강도,도수율 -->
<title><bean:message key='MENU.BDDURFREQRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc;
var deptAc;
var eqCtgTypeAc;
var eqGRade;

function loadPage() 
{
	// 공장코드
	plantAc = new autoC({"assetRptBdIntensityCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptBdIntensityCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
   //-----------------------------------------------설비등급------------------------------------------------
    eqGRade = new autoC({"assetRptBdIntensityCommonDTO.filterEqGradeDesc":"description"});
    eqGRade.setAcConditionMap({
        	"list_type":"EQ_GRADE"
        	,"param2":"EQUIP"
  	   });
    eqGRade.setTable("TACDSYSD");
    eqGRade.setAcResultMap({
        "assetRptBdIntensityCommonDTO.filterEqGrade":"cdsysd_no"
    });
    eqGRade.init();
    
 	//부서
    deptAc = new autoC({"assetRptBdIntensityCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "assetRptBdIntensityCommonDTO.filterDeptId":"dept_id"
        , "assetRptBdIntensityCommonDTO.filterPlantId":"plant"
        , "assetRptBdIntensityCommonDTO.filterPlantDesc":"plantDesc"
    });
    deptAc.init();
    
    
	//월
	assetRptBdIntensityListForm.elements['assetRptBdIntensityCommonDTO.filterStartDate'].value = getMinusMonth(-6);
	assetRptBdIntensityListForm.elements['assetRptBdIntensityCommonDTO.filterEndDate'].value = getMinusMonth(0);
	//공장
	assetRptBdIntensityListForm.elements['assetRptBdIntensityCommonDTO.filterPlantId'].value = loginUser.filterPlant;
	assetRptBdIntensityListForm.elements['assetRptBdIntensityCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	
	if(typeof afterloadPage == "function")
		afterloadPage();
// 	else
// 	{
// 		//구분
// 		assetRptBdIntensityListForm.elements['assetRptBdIntensityCommonDTO.filterSeparation'].value   = "L5";
// 		assetRptBdIntensityListForm.elements['assetRptBdIntensityCommonDTO.filterSeparationDesc'].value   = "<bean:message key='CODESET.DW_EMS_LTYPE.L5'/>";
// 		valSysDir('assetRptBdIntensityCommonDTO.filterSeparation', 'assetRptBdIntensityCommonDTO.filterSeparationDesc', 'DW_EMS_LTYPE', true);
		
// 		//구분
// 		acSysDesc("assetRptBdIntensityCommonDTO.filterSeparationDesc","assetRptBdIntensityCommonDTO.filterSeparation","DW_EMS_LTYPE",true);
// 	}
	
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
        return sortColumn("assetRptBdIntensityList", this, assetRptBdIntensityListForm, "EQUIPID", ind, direction);
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
    var url = contextPath + "/assetRptBdIntensityList.do";
    assetRptBdIntensityListForm.elements['strutsAction'].value = '<%=AssetRptBdIntensityListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptBdIntensityListForm), "EQUIPID", "Y");
}

function goSearch()
{
	for(var i = 0; myGrid.getColumnsNum() > i; i++)
	{
		if(myGrid.getColType(i) == "ron" ) 
		{
			myGrid.setNumberFormat("0,000.00",i,".",",");
		}
	}
	
	if(checkRequireValue("assetRptBdIntensityCommonDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
    if(checkRequireValue("assetRptBdIntensityCommonDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	
    if(checkValidation()) return;
    
    findGridList('Search');
    
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("assetRptBdIntensityList", assetRptBdIntensityListForm);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('assetRptBdIntensityFreqRateDetailChart');
 	goTabPage('assetRptBdIntensityDuraRateDetailChart');
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
 	var form = document.assetRptBdIntensityListForm;
 	
 	form.elements['assetRptBdIntensityDetailListDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
 	form.elements['assetRptBdIntensityDetailListDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
 	form.elements['assetRptBdIntensityDetailListDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
 	form.elements['assetRptBdIntensityDetailListDTO.eqLocDesc'].value = getValueById(myGrid, selectedId,'EQLOCDESC');
 	form.elements['assetRptBdIntensityDetailListDTO.usageDeptId'].value = getValueById(myGrid, selectedId,'USAGEDEPTID');
 	form.elements['assetRptBdIntensityDetailListDTO.usageDeptDesc'].value = getValueById(myGrid, selectedId,'USAGEDEPTDESC');
 	form.elements['assetRptBdIntensityDetailListDTO.eqCtgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
 	form.elements['assetRptBdIntensityDetailListDTO.eqCtgDesc'].value = getValueById(myGrid, selectedId,'EQCTGDESC');
 	form.elements['assetRptBdIntensityDetailListDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
 	form.elements['assetRptBdIntensityDetailListDTO.equipDesc'].value = getValueById(myGrid, selectedId,'EQUIPDESC');
 	
 	goCommonTabPage(form, '' , pageId);
 }

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptBdIntensityList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetRptBdIntensityCommonDTO.filterSeparation"/>
<html:hidden property="assetRptBdIntensityCommonDTO.filterEqGrade"/>
<html:hidden property="assetRptBdIntensityCommonDTO.filterDeptId"/>

<html:hidden property="assetRptBdIntensityDetailListDTO.plantId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqLocId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqLocDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.usageDeptId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.usageDeptDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqCtgId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqCtgDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.equipId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.equipDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.plantDesc"/>

<html:hidden property="assetRptBdIntensityCommonDTO.filterPlantId"/>
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
                            <html:text property="assetRptBdIntensityCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="assetRptBdIntensityCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 구분 -->
                <div class="field" id="separationDiv">
                    <label><bean:message key="LABEL.separation"/></label>
                    <div class="input_box">
                        <html:text property="assetRptBdIntensityCommonDTO.filterSeparationDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="assetRptBdIntensityCommonDTO.filterPlantDesc"
								tabindex="60" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 설비등급  -->
				<div class="field">
					<label><bean:message key="LABEL.eqGrade"/></label>
					<div class="input_box">
						<html:text property="assetRptBdIntensityCommonDTO.filterEqGradeDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="assetRptBdIntensityCommonDTO.filterDeptDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비번호 -->
				<div class="field">
					<label><bean:message key="LABEL.equipNo"/></label>
					<div class="input_box">
						<html:text property="assetRptBdIntensityCommonDTO.filterEquipNo" tabindex="55"/>
					</div>
				</div>				
		<c:set var="filePath" value="enhance/${compName}/asset/rpt/bdintensity/assetRptBdIntensityList_${compNo}.jsp" />
		<c:if test="${udf:isExist(filePath)}">
			<c:import charEncoding="UTF-8" url="/enhance/${compName}/asset/rpt/bdintensity/assetRptBdIntensityList_${compNo}.jsp"></c:import>
		</c:if>
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

