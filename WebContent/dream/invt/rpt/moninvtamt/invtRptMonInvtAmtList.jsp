<%--===========================================================================
author  cjscjs9
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.moninvtamt.action.InvtRptMonInvtAmtListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별사용분석 -->
<title><bean:message key='MENU.MONINVTAMT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc, deptAc, usageDeptAc;

var selectedId;

function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//년도 
	invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtCommonDTO.filterYyyy'].value = getYear(); 
    
    //공장 
    plantAc = new autoC({"invtRptMonInvtAmtCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "invtRptMonInvtAmtCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
 // 사용부서
    usageDeptAc = new autoC({"invtRptMonInvtAmtCommonDTO.filterDeptDesc":"description"});
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "invtRptMonInvtAmtCommonDTO.filterDeptId":"dept_id",
    });
    usageDeptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	}); 
    usageDeptAc.setAcDConditionMap({
    	"plant" : "invtRptMonInvtAmtCommonDTO.filterPlantId"
    });
    usageDeptAc.init();
    
    
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
    	selectedId = rowId;
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("invtRptMonInvtAmtList", this, invtRptMonInvtAmtListForm, "PLANTID", ind, direction);
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
    var url = contextPath + "/invtRptMonInvtAmtList.do";
    invtRptMonInvtAmtListForm.elements['strutsAction'].value = '<%=InvtRptMonInvtAmtListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtRptMonInvtAmtListForm), "PLANTID", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    
    invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtDetailDTO.yyyy'].value = invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtCommonDTO.filterYyyy'].value;
    invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtDetailDTO.plantId'].value = invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtCommonDTO.filterPlantId'].value;
    invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtDetailDTO.deptId'].value = invtRptMonInvtAmtListForm.elements['invtRptMonInvtAmtCommonDTO.filterDeptId'].value;
	
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
	
	var form = document.invtRptMonInvtAmtListForm;
	form.elements['invtRptMonInvtAmtDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	form.elements['invtRptMonInvtAmtDetailDTO.invtListId'].value = getValueById(myGrid, selectedId,'INVTLISTID');
	form.elements['invtRptMonInvtAmtDetailDTO.plantId'].value = form.elements['invtRptMonInvtAmtCommonDTO.filterPlantId'].value;
	form.elements['invtRptMonInvtAmtDetailDTO.deptDesc'].value = getValueById(myGrid, selectedId,'ORGNAME');
	form.elements['invtRptMonInvtAmtDetailDTO.plantDesc'].value = form.elements['invtRptMonInvtAmtCommonDTO.filterPlantDesc'].value;
	
	
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('invtRptMonInvtAmtDetailChart');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("invtRptMonInvtAmtList", invtRptMonInvtAmtListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtRptMonInvtAmtList.do">
<html:hidden property="strutsAction"/>
<!-- KEY -->
<html:hidden property="invtRptMonInvtAmtCommonDTO.filterPlantId"/>
<html:hidden property="invtRptMonInvtAmtCommonDTO.filterDeptId"/>
<html:hidden property="invtRptMonInvtAmtCommonDTO.filterInvtListId"/>

<html:hidden property="invtRptMonInvtAmtDetailDTO.invtListId"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.plantId"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.plantDesc"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.deptId"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.deptDesc"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.yyyy"/>

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
                    <label class="check"><bean:message key="LABEL.year"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="invtRptMonInvtAmtCommonDTO.filterYyyy" tabindex="10" />
                            <p class="open_year_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
                        <html:text property="invtRptMonInvtAmtCommonDTO.filterPlantDesc" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 사용부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.usedDept"/></label>
                    <div class="input_box">
                        <html:text property="invtRptMonInvtAmtCommonDTO.filterDeptDesc" tabindex="40" />
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

