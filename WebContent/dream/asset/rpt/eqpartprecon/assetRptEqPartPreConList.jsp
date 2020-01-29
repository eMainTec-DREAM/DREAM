<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.eqpartprecon.action.AssetRptEqPartPreConListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비부품현황 -->
<title><bean:message key='MENU.EQPARTPRECON'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc, eqLocDescAc, eqCtgTypeAc;

function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	assetRptEqPartPreConListForm.elements['assetRptEqPartPreConCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	assetRptEqPartPreConListForm.elements['assetRptEqPartPreConCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    //공장명 
    plantAc = new autoC({"assetRptEqPartPreConCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "assetRptEqPartPreConCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //설비위치
    eqLocDescAc = new autoC({"assetRptEqPartPreConCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqLocDescAc.setAcResultMap({
        "assetRptEqPartPreConCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "assetRptEqPartPreConCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    //설비종류
    eqCtgTypeAc = new autoC({"assetRptEqPartPreConCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgTypeAc.setAcResultMap({
        "assetRptEqPartPreConCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    // 설비
    equipDescAc = new autoC({"assetRptEqPartPreConCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcDisplay("DESCRIPTION");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "assetRptEqPartPreConCommonDTO.equipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "assetRptEqPartPreConCommonDTO.filterEqLocId",
    	"eqctg_id" : "assetRptEqPartPreConCommonDTO.filterEqCtgId",
    	"dept_id" : "assetRptEqPartPreConCommonDTO.filterDeptId",
    	"plant" : "assetRptEqPartPreConCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
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
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/assetRptEqPartPreConList.do";
    assetRptEqPartPreConListForm.elements['strutsAction'].value = '<%=AssetRptEqPartPreConListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptEqPartPreConListForm), "PLANTID", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    
    findGridList('Search');
    
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
	var form = document.assetRptEqPartPreConListForm;
	
	form.elements['assetRptEqPartPreConDetailDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
	form.elements['assetRptEqPartPreConDetailDTO.equipDesc'].value = getValueById(myGrid, selectedId,'EQUIPDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('assetRptEqPartPreConDetailPartList');
}

function goOpenSub(){
	goTabPage('assetRptEqPartPreConDetailStockList');
}

/**
 * Excel Export
 */
function goExcel()
{
//	if(checkValidation()) return;
	excelAction(myGrid);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptEqPartPreConList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetRptEqPartPreConCommonDTO.equipId"/>
<html:hidden property="assetRptEqPartPreConCommonDTO.filterPlantId"/>
<html:hidden property="assetRptEqPartPreConCommonDTO.filterEqLocId"/>
<html:hidden property="assetRptEqPartPreConCommonDTO.filterEqCtgId"/>
<html:hidden property="assetRptEqPartPreConDetailDTO.equipId"/>
<html:hidden property="assetRptEqPartPreConDetailDTO.equipDesc"/>
<html:hidden property="assetRptEqPartPreConDetailDTO.partId"/>
<html:hidden property="assetRptEqPartPreConDetailDTO.partDesc"/>
<html:hidden property="assetRptEqPartPreConDetailDTO.partGrade"/>
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
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="assetRptEqPartPreConCommonDTO.filterPlantDesc" tabindex="10" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.location"/></label>
                    <div class="input_box">
                        <html:text property="assetRptEqPartPreConCommonDTO.filterEqLocDesc" tabindex="20" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 종류 -->
                <div class="field">
                    <label><bean:message key="LABEL.type"/></label>
                    <div class="input_box">
                        <html:text property="assetRptEqPartPreConCommonDTO.filterEqCtgDesc" tabindex="30"/>
                        <p class="open_spop">
                            <a>
                             <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 설비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipName"/></label>
                    <div class="input_box">
                        <html:text property="assetRptEqPartPreConCommonDTO.filterEquipDesc" tabindex="40"/>
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

