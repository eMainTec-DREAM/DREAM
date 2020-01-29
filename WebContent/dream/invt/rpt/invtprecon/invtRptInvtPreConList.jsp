<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.invtprecon.action.InvtRptInvtPreConListAction" %>
<%@ page import="dream.invt.rpt.invtprecon.action.InvtRptInvtPreConDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 투자현황 -->
<title><bean:message key='MENU.INVTPRECON'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var deptAc;
var plantAc;

function loadPage() 
{
	//년도 AC
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.filterStartYear'].value = dateToData(getToday()).substr(0, 4);
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.filterEndYear'].value = dateToData(getToday()).substr(0, 4);

    //공장
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.filterPlantId'].value = loginUser.filterPlant;
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
    
    //부서 AC 
    deptAc = new autoC({"invtRptInvtPreConCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "invtRptInvtPreConCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
	acSysDesc("invtRptInvtPreConCommonDTO.filterInvtCategDesc","invtRptInvtPreConCommonDTO.filterInvtCateg","INVT_CATEG");
	
	plantAc = new autoC({"invtRptInvtPreConCommonDTO.filterPlantDesc":"description"});
	plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantAc.setTable("TAPLANT");
	plantAc.setAcResultMap({
        "invtRptInvtPreConCommonDTO.filterPlantId":"plant"
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
        goOpen();
    });
     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.deptId'].value = "";
    	invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.year'].value = "";
        return sortColumn("invtRptInvtPreConList", this, invtRptInvtPreConListForm, "DEPTID", ind, direction);
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
    var url = contextPath + "/invtRptInvtPreConList.do";
    invtRptInvtPreConListForm.elements['strutsAction'].value = '<%=InvtRptInvtPreConListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtRptInvtPreConListForm), "DEPTID", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.deptId'].value = "";  // 검색시 Tab 이동Key Clear
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.year'].value = "";
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
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.deptId'].value =  getValueById(myGrid, selectedId,'DEPTID');  
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.year'].value =  getValueById(myGrid, selectedId,'YEAR');  
    
    goCommonTabPage(invtRptInvtPreConListForm, <%= InvtRptInvtPreConDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('invtRptInvtPreConDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.deptId'].value =  getValueById(myGrid, selectedId,'DEPTID');  
    invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.year'].value =  getValueById(myGrid, selectedId,'YEAR');  
    invtRptInvtPreConListForm.elements['strutsAction'].value = '<%=InvtRptInvtPreConDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(invtRptInvtPreConListForm), 'invtRptInvtPreConDetail'); 
} 


/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.deptId'].value = "";
	invtRptInvtPreConListForm.elements['invtRptInvtPreConCommonDTO.year'].value = "";
   excelServerAction("invtRptInvtPreConList", invtRptInvtPreConListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtRptInvtPreConList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="invtRptInvtPreConCommonDTO.deptId"/><!-- Key -->
<html:hidden property="invtRptInvtPreConCommonDTO.year"/><!-- Key -->
<html:hidden property="invtRptInvtPreConCommonDTO.filterDeptId"/>
<html:hidden property="invtRptInvtPreConCommonDTO.filterInvtCateg"/>
<html:hidden property="invtRptInvtPreConCommonDTO.filterPlantId" />


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
                <!-- 일자 -->
                 <div class="field">
                    <label class="check"><bean:message key="LABEL.date"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="invtRptInvtPreConCommonDTO.filterStartYear" tabindex="10" />
                            <p class="open_year_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="invtRptInvtPreConCommonDTO.filterEndYear" tabindex="20" />
                            <p class="open_year_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.dept"/></label>
                    <div class="input_box">
                        <html:text property="invtRptInvtPreConCommonDTO.filterDeptDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 투자구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.invtCategDesc"/></label>
                    <div class="input_box">
                        <html:text property="invtRptInvtPreConCommonDTO.filterInvtCategDesc" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 공장(Plant) -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="invtRptInvtPreConCommonDTO.filterPlantDesc" tabindex="110"/>
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

