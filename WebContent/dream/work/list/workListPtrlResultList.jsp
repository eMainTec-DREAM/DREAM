<%--===========================================================================
순회점검 작업결과 목록
author  youngjoo38
version $Id: workListPtrlResultList.jsp,v 1.1 2017/09/15 10:22:27 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListPtrlResultListAction" %>
<%@ page import="dream.work.cal.pmptrlmonth.action.WorkCalPmPtrlMonthListAction" %>
<%@ page import="dream.work.list.action.WorkListPtrlResultMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 순회일정 -->
<title><bean:message key='MENU.PTRLCAL'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var deptAc, managerAc, inspectorAc, wkCtrAc;

function loadPage() 
{
	initGrid();
	
	// 날짜 자동완성 (1달전~오늘)
    workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.filterPtrlStartDate'].value = getMinusMonth2(new Date(), -1); 
    workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.filterPtrlEndDate'].value   = getToday();
    
    //작업그룹
    if(loginUser.filterWkCtrId!='null'){
    	workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.filterPtrlWorkGrpId'].value = loginUser.filterWkCtrId;
    	workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.filterPtrlWorkGrpDesc'].value = loginUser.filterWkCtrDesc;
	}
	
	//부서 자동완성 (pop up)
    deptAc = new autoC({"workListPtrlResultCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workListPtrlResultCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    //담당자 자동완성
    managerAc = new autoC({"workListPtrlResultCommonDTO.filterManagerDesc":"emp_name"});
    managerAc.setTable("TAEMP");
    managerAc.setAcResultMap({
        "workListPtrlResultCommonDTO.filterManagerId":"emp_id"
    });
    managerAc.setAcDConditionMap({
    	"dept_id" : "workListPtrlResultCommonDTO.filterDeptId"
    });
    managerAc.init();
	
    //점검자 자동완성
    inspectorAc = new autoC({"workListPtrlResultCommonDTO.filterPtrlInspectorDesc":"emp_name"});
    inspectorAc.setTable("TAEMP");
    inspectorAc.setAcResultMap({
        "workListPtrlResultCommonDTO.filterPtrlInspectorId":"emp_id"
    });
    inspectorAc.setAcDConditionMap({
    	"dept_id" : "workListPtrlResultCommonDTO.filterDeptId"
    });
    inspectorAc.init();
    
    //작업그룹
    wkCtrAc = new autoC({"workListPtrlResultCommonDTO.filterPtrlWorkGrpDesc":"description"});
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setAcResultMap({
        "workListPtrlResultCommonDTO.filterPtrlWorkGrpId":"wkctr_id"
    });
    wkCtrAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
/*     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = "";
        return sortColumn("workListPtrlResultList", this, workListPtrlResultListForm, "PMPTRLRSLTLISTID", ind, direction);
    }); */
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workListPtrlResultList.do";
    workListPtrlResultListForm.elements['strutsAction'].value = '<%=WorkListPtrlResultListAction.WORK_LIST_PTRL_RESULT_LIST_FIND %>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListPtrlResultListForm), "PMPTRLRSLTLISTID");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPtrlRsltListId)
{
   workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = _pmPtrlRsltListId;
   findGridList('ReloadRow');
   workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	if(checkValidation()) return;
	workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = "";  // 검색시 Tab 이동Key Clear
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
	workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = getValueById(myGrid, selectedId,'PMPTRLRSLTLISTID');
    goCommonTabPage(workListPtrlResultListForm, '<%=WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_INIT%>', pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workListPtrlResultMstrDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = getValueById(myGrid, selectedId,'PMPTRLRSLTLISTID');
     workListPtrlResultListForm.elements['strutsAction'].value = '<%=WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(workListPtrlResultListForm), 'workListPtrlResultMstrDetail'); 
 } 
 
 /**
  * 삭제
  */
function goDelete(){

	 var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
     for(var i = 0 ;i < chkedRowsId.length; i++)
    {
        if(getValueById(myGrid, chkedRowsId[i], "PROSTATUSID") == "C")
        {
            alertMessage1('<bean:message key="LABEL.delCompWork"/>'); //delCompWork
            // 완료된 작업은 삭제할 수 없습니다.
            return;
        }
        // ↑ 완료상태의 순회점검은 삭제할 수 없다.
        /* else if(getValueById(myGrid, chkedRowsId[i], "WOGENTYPE") == "WOPOINT")
        {
            alertMessage1('<bean:message key="MESSAGE.MSG0099"/>');
            return;
        } */
        // ↑ 이상상태의 순회점검은 삭제할 수 없다.
    }
    
	 
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMPTRLRSLTLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workListPtrlResultListForm.strutsAction.value = '<%=WorkListPtrlResultListAction.WORK_LIST_PTRL_RESULT_LIST_DELETE%>';
	var url = contextPath + "/workListPtrlResultList.do";
	
	$.post(url,FormQueryString(workListPtrlResultListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workListPtrlResultMstrDetail',this);
    goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG050"/>');
    // 완료상태의 순회일정을 제외 후 삭제하였습니다.
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	if(checkValidation()) return;
	workListPtrlResultListForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = "";
   excelServerAction("workListPtrlResultList", workListPtrlResultListForm);
 } 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListPtrlResultList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workListPtrlResultCommonDTO.pmPtrlRsltListId"/><!-- Key -->

<html:hidden property="workListPtrlResultCommonDTO.filterPtrlWorkId"/>          <!-- 순회업무 ID -->
<html:hidden property="workListPtrlResultCommonDTO.filterDeptId"/>              <!-- 부서 ID -->
<html:hidden property="workListPtrlResultCommonDTO.filterPtrlWorkGrpId"/>       <!-- 작업그룹 ID -->
<html:hidden property="workListPtrlResultCommonDTO.filterManagerId"/>           <!-- 담당자 ID -->
<html:hidden property="workListPtrlResultCommonDTO.filterPtrlInspectorId"/>     <!-- 점검자 ID -->

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
                <!-- 예정일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.schedDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workListPtrlResultCommonDTO.filterPtrlStartDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workListPtrlResultCommonDTO.filterPtrlEndDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 순회업무명 -->
                <div class="field">
                    <label><bean:message key="LABEL.ptrlWorkTitle"/></label>
                    <div class="input_box">
                        <html:text property="workListPtrlResultCommonDTO.filterPtrlWorkTitle" tabindex="30"/>
                    </div>
                </div>
                <!-- 부서 -->
                <div class="field">
                    <label><bean:message key="MENU.DEPT"/></label>
                    <div class="input_box">
                        <html:text property="workListPtrlResultCommonDTO.filterDeptDesc" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="workListPtrlResultCommonDTO.filterManagerDesc" tabindex="50" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 작업그룹 -->
                <div class="field">
                    <label><bean:message key="LABEL.wkCtr"/></label>
                    <div class="input_box">
                        <html:text property="workListPtrlResultCommonDTO.filterPtrlWorkGrpDesc" tabindex="60"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 순회업무# -->
                <div class="field">
                    <label><bean:message key="LABEL.ptrlWorkNo"/></label>
                    <div class="input_box">
                        <html:text property="workListPtrlResultCommonDTO.filterPtrlWorkNo" tabindex="70" />
                    </div>
                </div>
                <!-- 점검완료일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.ptrlCompleteDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workListPtrlResultCommonDTO.filterPtrlComStartDate" tabindex="80" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workListPtrlResultCommonDTO.filterPtrlComEndDate" tabindex="90" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 점검자 -->
                <div class="field">
                    <label><bean:message key="LABEL.ptrlInspector"/></label>
                    <div class="input_box">
                        <html:text property="workListPtrlResultCommonDTO.filterPtrlInspectorDesc" tabindex="100" />
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