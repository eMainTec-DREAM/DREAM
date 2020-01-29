<%--===========================================================================
설비등급평가 항목별 점수
author  nhkim8548
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.ass.asset.action.AssetRptAssAssetScoreListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비등급평가 항목별 점수 -->
<title><bean:message key='MENU.ASSASSETCATEGORYSCORE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
//그리드명
var myGrid;
// 자동완성 변수
var filterAssCategoryTypeDesc;

function loadPage() 
{
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	assetRptAssAssetScoreListForm.elements['assetRptAssAssetScoreCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	assetRptAssAssetScoreListForm.elements['assetRptAssAssetScoreCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	// 자동완성
	acSysDesc("assetRptAssAssetScoreCommonDTO.filterAssCategoryTypeDesc","assetRptAssAssetScoreCommonDTO.filterAssCategoryTypeId","ASS_POINT_TYPE");
	//공장명
    plantAc = new autoC({"assetRptAssAssetScoreCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    })
    plantAc.setAcResultMap({
        "assetRptAssAssetScoreCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
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
    	return sortColumn("assetRptAssAssetScoreList", this, assetRptAssAssetScoreListForm, "", ind, direction);
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
    var url = contextPath + "/assetRptAssAssetScoreList.do";
    assetRptAssAssetScoreListForm.elements['strutsAction'].value = '<%=AssetRptAssAssetScoreListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptAssAssetScoreListForm), "", "Y");
}

function goSearch()
{
	findGridList('Search');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
    excelServerAction("assetRptAssAssetScoreList", assetRptAssAssetScoreListForm);
}
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptAssAssetScoreList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetRptAssAssetScoreCommonDTO.filterPlantId"/>
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
               <!-- 설비번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipNo"/></label>
                    <div class="input_box">
                        <html:text property="assetRptAssAssetScoreCommonDTO.filterEquipNo" tabindex="30" />
                    </div>
                </div>
                <!-- 설비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipName"/></label>
                    <div class="input_box">
                        <html:text property="assetRptAssAssetScoreCommonDTO.filterEquipDesc" tabindex="30" />
                    </div>
                </div>
                <!-- 평가항목구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.assCategoryType"/></label>
                    <div class="input_box">
                        <html:text property="assetRptAssAssetScoreCommonDTO.filterAssCategoryTypeDesc" tabindex="30" />
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
                        <html:text property="assetRptAssAssetScoreCommonDTO.filterPlantDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 평가항목 -->
				<div class="field">
					<label><bean:message key="LABEL.assCategory"/></label>
					<div class="input_box">
						<html:text property="assetRptAssAssetScoreCommonDTO.filterAssCategoryDesc" tabindex="40"/>
					</div>
				</div>
				 <!-- 평가일자 -->
                <div class="field">
					<label><bean:message key="LABEL.assDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="assetRptAssAssetScoreCommonDTO.filterAssStartDate" tabindex="110" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptAssAssetScoreCommonDTO.filterAssEndDate" tabindex="120" />
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

