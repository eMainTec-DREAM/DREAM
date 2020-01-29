<%--===========================================================================
수선유지비 집행현황
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.maintcost.action.AssetRptMaintCostListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 수선유지비 집행현황 -->
<title><bean:message key='MENU.MAINTCOST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc;
var deptAc;

function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	assetRptMaintCostListForm.elements['assetRptMaintCostCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	assetRptMaintCostListForm.elements['assetRptMaintCostCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//월
	assetRptMaintCostListForm.elements['assetRptMaintCostCommonDTO.filterStartDate'].value = getMinusMonth(-6);
	assetRptMaintCostListForm.elements['assetRptMaintCostCommonDTO.filterEndDate'].value = getMinusMonth(0);
	
    //공장 
    plantAc = new autoC({"assetRptMaintCostCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "assetRptMaintCostCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //부서
    deptAc = new autoC({"assetRptMaintCostCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "assetRptMaintCostCommonDTO.filterDeptId":"dept_id"
        , "assetRptMaintCostCommonDTO.filterPlantId":"plant"
        , "assetRptMaintCostCommonDTO.filterPlantDesc":"plantDesc"
    });
    deptAc.init();
    
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
    	
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("assetRptMaintCostList", this, assetRptMaintCostListForm, "PLANTID", ind, direction);
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
    var url = contextPath + "/assetRptMaintCostList.do";
    assetRptMaintCostListForm.elements['strutsAction'].value = '<%=AssetRptMaintCostListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptMaintCostListForm), "PLANTID", "Y");
}

function goSearch()
{
	if(checkRequireValue("assetRptMaintCostCommonDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
    if(checkRequireValue("assetRptMaintCostCommonDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	
    if(checkValidation()) return;
    
    findGridList('Search');
    
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("assetRptMaintCostList", assetRptMaintCostListForm);
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptMaintCostList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetRptMaintCostCommonDTO.filterPlantId"/>
<html:hidden property="assetRptMaintCostCommonDTO.filterDeptId"/>

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
                            <html:text property="assetRptMaintCostCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="assetRptMaintCostCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="assetRptMaintCostCommonDTO.filterPlantDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="assetRptMaintCostCommonDTO.filterDeptDesc" tabindex="40"/>
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

