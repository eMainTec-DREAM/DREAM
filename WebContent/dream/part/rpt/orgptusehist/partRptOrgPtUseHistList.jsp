<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.orgptusehist.action.PartRptOrgPtUseHistListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별사용분석 -->
<title><bean:message key='MENU.ORGPTUSEHIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc, deptAc;

var selectedCid;

function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//년도 
	partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.filterStartYyyymm'].value = getYear()+"-01"; 
    partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.filterEndYyyymm'].value = getYear()+"-"+(getMonth());
    
    //공장 
    plantAc = new autoC({"partRptOrgPtUseHistCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "partRptOrgPtUseHistCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //조직구분
    acSysDesc("partRptOrgPtUseHistCommonDTO.filterDeptCategDesc","partRptOrgPtUseHistCommonDTO.filterDeptCategId","DEPT_CATEG");
    
    //부서
	deptAc = new autoC({"partRptOrgPtUseHistCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    deptAc.setAcDConditionMap({
    	"dept_categ": "partRptOrgPtUseHistCommonDTO.filterDeptCategId",
    	"plant" : "partRptOrgPtUseHistCommonDTO.filterPlantId"
    });
    deptAc.setAcResultMap({
        "consultCompUserCommonDTO.filterDeptId":"dept_id"
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
    	selectedCid = columnId;
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.deptId'].value = "";
        return sortColumn("partRptOrgPtUseHistList", this, partRptOrgPtUseHistListForm, "PLANTID", ind, direction);
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
    var url = contextPath + "/partRptOrgPtUseHistList.do";
    partRptOrgPtUseHistListForm.elements['strutsAction'].value = '<%=PartRptOrgPtUseHistListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partRptOrgPtUseHistListForm), "PLANTID", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    
    partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistDetailDTO.startYyyymm'].value = partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.filterStartYyyymm'].value;
    partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistDetailDTO.endYyyymm'].value = partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.filterEndYyyymm'].value;
    partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistDetailDTO.deptId'].value = partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.deptId'].value;
    partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistDetailDTO.plantId'].value = partRptOrgPtUseHistListForm.elements['partRptOrgPtUseHistCommonDTO.filterPlantId'].value;
	
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
	var form = document.partRptOrgPtUseHistListForm;
	
	form.elements['partRptOrgPtUseHistDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	form.elements['partRptOrgPtUseHistDetailDTO.plantId'].value = form.elements['partRptOrgPtUseHistCommonDTO.filterPlantId'].value;
	form.elements['partRptOrgPtUseHistDetailDTO.deptDesc'].value = getValueById(myGrid, selectedId,'ORGNAME');
	form.elements['partRptOrgPtUseHistDetailDTO.plantDesc'].value = form.elements['partRptOrgPtUseHistCommonDTO.filterPlantDesc'].value;
	
	var selectedColId = myGrid.getColumnId(selectedCid);
	
	if(selectedColId != "USEAMT" && selectedColId != "USECNT")
	{
		form.elements['partRptOrgPtUseHistDetailDTO.chartValue'].value = "USEAMT";
	}
	else
	{
		form.elements['partRptOrgPtUseHistDetailDTO.chartValue'].value = selectedColId;
	}
	
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('partRptOrgPtUseHistDetailList');
	goTabPage('partRptOrgPtUseHistDetailChart');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("partRptOrgPtUseHistList", partRptOrgPtUseHistListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partRptOrgPtUseHistList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="partRptOrgPtUseHistCommonDTO.deptId"/> <!-- KEY -->
<html:hidden property="partRptOrgPtUseHistCommonDTO.filterPlantId"/>
<html:hidden property="partRptOrgPtUseHistCommonDTO.filterDeptId"/>
<html:hidden property="partRptOrgPtUseHistCommonDTO.filterDeptCategId"/>

<html:hidden property="partRptOrgPtUseHistDetailDTO.deptId"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.deptDesc"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.plantId"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.plantDesc"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.startYyyymm"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.endYyyymm"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.chartValue"/>

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
                            <html:text property="partRptOrgPtUseHistCommonDTO.filterStartYyyymm" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="partRptOrgPtUseHistCommonDTO.filterEndYyyymm" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 조직구분 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.deptCategDesc"/></label>
                    <div class="input_box">
                        <html:text property="partRptOrgPtUseHistCommonDTO.filterDeptCategDesc" tabindex="30" />
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
                        <html:text property="partRptOrgPtUseHistCommonDTO.filterPlantDesc" tabindex="40" />
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
                        <html:text property="partRptOrgPtUseHistCommonDTO.filterDeptDesc" tabindex="50" />
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

