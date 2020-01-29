<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.check.action.WorkPmCheckListAction" %>
<%@ page import="dream.work.pm.check.action.WorkPmCheckDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 표준점검항목 -->
<title><bean:message key='MENU.StdPmCheckList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc;

function loadPage() 
{
	// 공장
	if(loginUser.filterPlant != '' && null != loginUser.filterPlant){
		workPmCheckListForm.elements['workPmCheckCommonDTO.filterPlant'].value  = loginUser.filterPlant;
		workPmCheckListForm.elements['workPmCheckCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    // 분류 자동완성
    acSysDesc("workPmCheckCommonDTO.filterCheckPointTypeDesc","workPmCheckCommonDTO.filterCheckPointTypeId","CHECK_POINT_TYPE");
    
    // 공장 자동완성
	plantAc = new autoC({"workPmCheckCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workPmCheckCommonDTO.filterPlant":"plant"
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
    	workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value = "";
        return sortColumn("workPmCheckList", this, workPmCheckListForm, "CHECKPOINTID", ind, direction);
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
    var url = contextPath + "/workPmCheckList.do";
    workPmCheckListForm.elements['strutsAction'].value = '<%=WorkPmCheckListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmCheckListForm), "CHECKPOINTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_checkPointId)
{
    workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value = _checkPointId;
    findGridList('ReloadRow');
    workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value = "";  // 검색시 Tab 이동Key Clear
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
    workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value =  getValueById(myGrid, selectedId,'CHECKPOINTID');  
    
    goCommonTabPage(workPmCheckListForm, <%= WorkPmCheckDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workPmCheckDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value =  getValueById(myGrid, selectedId,'CHECKPOINTID');  
    workPmCheckListForm.elements['strutsAction'].value = '<%=WorkPmCheckDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmCheckListForm), 'workPmCheckDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workPmCheckDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value = "";
    goCommonTabPage(workPmCheckListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'CHECKPOINTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workPmCheckListForm.strutsAction.value = '<%=WorkPmCheckListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmCheckList.do";
    
    $.post(url,FormQueryString(workPmCheckListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workPmCheckDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
    workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value = "";
   excelServerAction("workPmCheckList", workPmCheckListForm);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmCheckListForm.elements['workPmCheckCommonDTO.checkPointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmCheckList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmCheckCommonDTO.checkPointId"/><!-- Key -->

<html:hidden property="workPmCheckCommonDTO.filterCheckPointTypeId"/>
<html:hidden property="workPmCheckCommonDTO.filterPlant"/>

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
                <!-- 점검항목 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkPoint"/></label>
                    <div class="input_box">
                        <html:text property="workPmCheckCommonDTO.filterCheckPoint" tabindex="50"/>
                    </div>
                </div>
                <!-- 분류 -->
                <div class="field">
                    <label><bean:message key="LABEL.category"/></label>
                    <div class="input_box">
                        <html:text property="workPmCheckCommonDTO.filterCheckPointTypeDesc" tabindex="60" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 공장명 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="workPmCheckCommonDTO.filterPlantDesc" tabindex="70" />
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

