<%--===========================================================================
설비구성부품
author  ghlee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.eqParts.action.AssetRptEqPartsAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비구성부품 -->
<title><bean:message key='PAGE.assetRptEqParts'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var eqCtgAc;
var plantAc;

function loadPage() 
{
	//설비종류
    eqCtgAc = new autoC({"assetRptEqPartsDTO.eqCtgDesc":"description"});
    eqCtgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcResultMap({
        "assetRptEqPartsDTO.eqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    
    //공장 
    plantAc = new autoC({"assetRptEqPartsDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "assetRptEqPartsDTO.plant":"plant"
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
        return sortColumn("assetRptEqParts", this, assetRptEqPartsForm, "PLANTID", ind, direction);
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
    var url = contextPath + "/assetRptEqParts.do";
    assetRptEqPartsForm.elements['strutsAction'].value = '<%=AssetRptEqPartsAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptEqPartsForm), "EQUIPID", "Y");
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
    excelServerAction("assetRptEqParts", assetRptEqPartsForm);
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptEqParts.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetRptEqPartsDTO.eqCtgId"/>
<html:hidden property="assetRptEqPartsDTO.plant"/>

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
                        <html:text property="assetRptEqPartsDTO.itemNo" tabindex="10" />
                    </div>
                </div>
                <!-- 설비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipName"/></label>
                    <div class="input_box">
                        <html:text property="assetRptEqPartsDTO.equipDesc" tabindex="20" />
                    </div>
                </div>
                <!-- 부품번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.partNo"/></label>
                    <div class="input_box">
                        <html:text property="assetRptEqPartsDTO.partNo" tabindex="30" />
                    </div>
                </div>
                <!-- 부품명 -->
                <div class="field">
                    <label><bean:message key="LABEL.partDesc"/></label>
                    <div class="input_box">
                        <html:text property="assetRptEqPartsDTO.partDesc" tabindex="40" />
                    </div>
                </div>
                <!-- 설비종류 -->
                <div class="field">
                    <label><bean:message key="LABEL.eqCtg"/></label>
                    <div class="input_box">
                        <html:text property="assetRptEqPartsDTO.eqCtgDesc" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="assetRptEqPartsDTO.plantDesc" tabindex="60"/>
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

