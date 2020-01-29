<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListCinsPlanMstrListAction" %>
<%@ page import="dream.work.list.action.WorkListCinsPlanMstrDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 파트체인지 점검계획 -->
<title><bean:message key='MENU.partChangeInsPlanList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var eqLocAc, empAc, equipAc, deptAc;

var woType = "PMI";

var beforePageId = 'workListCinsPlanMstrDetail';

function loadPage() 
{
	// 날짜 팝업 (1달전~오늘)
	workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.filterStartPlanDate'].value = getMinusMonth2(new Date(), -1); 
	workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.filterEndPlanDate'].value   = getToday();
    
    // 생산제품 팝업
    wkCtrDescAc = new autoC({"workListCinsPlanMstrCommonDTO.filterProductDesc":"productDesc"});
    wkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    wkCtrDescAc.setTable("TAPMEQUIP");
    wkCtrDescAc.setAcResultMap({
        "workListCinsPlanMstrCommonDTO.filterProductId":"productId"
    });
    wkCtrDescAc.init();
    
	initGrid();
	
	
	// 설비 팝업 
    equipAc = new autoC({"workListCinsPlanMstrCommonDTO.filterEquipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcResultMap({
        "workListCinsPlanMstrCommonDTO.filterEquipId":"EQUIP_ID"
    });
    equipAc.setAcDConditionMap({
    	"eqloc_id" : "workListCinsPlanMstrCommonDTO.filterEqLocId",
    	"dept_id" : "workListCinsPlanMstrCommonDTO.filterDeptId"
    });
    equipAc.init();
    
    // 설비위치 팝업
    eqLocAc = new autoC({"workListCinsPlanMstrCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    eqLocAc.setAcResultMap({
        "workListCinsPlanMstrCommonDTO.filterEqLocId":"EQLOC_ID"
    });
    eqLocAc.init();
	
    // 부서 팝업
    deptAc = new autoC({"workListCinsPlanMstrCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workListCinsPlanMstrCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    // 담당자 팝업
    empAc = new autoC({"workListCinsPlanMstrCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workListCinsPlanMstrCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workListCinsPlanMstrCommonDTO.filterDeptId"
    });
    empAc.init();
	
	// 점검일정 발행여부
    acSysDesc("workListCinsPlanMstrCommonDTO.filterIsActive","workListCinsPlanMstrCommonDTO.filterIsActive","IS_USE");
	
	// 점검완료여부
    acSysDesc("workListCinsPlanMstrCommonDTO.filterIsComplete","workListCinsPlanMstrCommonDTO.filterIsComplete","IS_USE");
	
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
/*     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = "";
        return sortColumn("workListCinsPlanMstrList", this, workListCinsPlanMstrListForm, "PMINSDSCHEDID", ind, direction);
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
    var url = contextPath + "/workListCinsPlanMstrList.do";
    workListCinsPlanMstrListForm.elements['strutsAction'].value = '<%=WorkListCinsPlanMstrListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListCinsPlanMstrListForm), "PMINSDSCHEDID" , "Y");
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsDSchedId)
{
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = _pmInsDSchedId;
    findGridList('ReloadRow');
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = "";  // 검색시 Tab 이동Key Clear
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
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value =  getValueById(myGrid, selectedId,'PMINSDSCHEDID');  
    
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmId'].value = getValueById(myGrid, selectedId, 'PMID');
    
    goCommonTabPage(workListCinsPlanMstrListForm, <%= WorkListCinsPlanMstrDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workListCinsPlanMstrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
	workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value =  getValueById(myGrid, selectedId,'PMINSDSCHEDID');  
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmId'].value = getValueById(myGrid, selectedId, 'PMID');
    workListCinsPlanMstrListForm.elements['strutsAction'].value = '<%=WorkListCinsPlanMstrDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workListCinsPlanMstrListForm), 'workListCinsPlanMstrDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    // 생성 팝업 열기
    <%-- var param = "strutsAction="+'<%=WorkListCinsPlanMstrListAction.POPUP_SELECT%>'; --%>
    /* var param = "strutsAction=1003"; */
    //openLayerPopup("workListCinsPlanMstrList", param);
    
    createValidationCheck(myGrid, "workListCinsPlanMstrDetail" , "goCreateAction");
}
 function goCreateAction(pageId)
{
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = "";
    goCommonTabPage(workListCinsPlanMstrListForm, '', pageId);
} 

 /**
  * 작업종류& 작업형태 선택창 열기
  */
<%--  function openSelectType(){
     var param = "strutsAction=1001";
     param += "&"+"maPmMstrSelectDTO.selectedPmType="+woType+"_TYPE";
     var url =  contextPath + "/workPmListSelectPm.do";
     openLayerPopup("workPmListSelectPm", param);
 }
 
 function setAfterSelect(returnArray){

	    var pmType = returnArray[0];
	    var param = returnArray[1];
	    
	    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.selectedPmType'].value = pmType;
	    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.selectedWoType'].value = woType;

	    beforePageId = param;
	    
//	    goCommonTabPage(workListCinsPlanMstrListForm, '', pageId);
//	    goCommonTabPage(workListCinsPlanMstrListForm, '', param);
	}
 
 function execValidation(workDate, workPointId, workPointNo, pmId, pmNo, productId, productDesc, pmEquipId)
{
    var form = document.workListCinsPlanMstrListForm;
    
    form.elements['workListCinsPlanMstrCommonDTO.popupWorkDate'].value = workDate;
    form.elements['workListCinsPlanMstrCommonDTO.popupWorkPointId'].value = workPointId;
    
    form.elements['workListCinsPlanMstrCommonDTO.popupWorkPointNo'].value = workPointNo;
    form.elements['workListCinsPlanMstrCommonDTO.popupPmId'].value = pmId;
    form.elements['workListCinsPlanMstrCommonDTO.popupPmNo'].value = pmNo;
    
    form.elements['workListCinsPlanMstrCommonDTO.productId'].value = productId;
    form.elements['workListCinsPlanMstrCommonDTO.productDesc'].value = productDesc;
    form.elements['workListCinsPlanMstrCommonDTO.pmEquipId'].value = pmEquipId;
     
    form.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = "";
    goCommonTabPage(form, <%= WorkListCinsPlanMstrDetailAction.DETAIL_INIT%>, "workListCinsPlanMstrDetail");
}
 --%>

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMINSDSCHEDID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workListCinsPlanMstrListForm.strutsAction.value = '<%=WorkListCinsPlanMstrListAction.LIST_DELETE%>';
    var url = contextPath + "/workListCinsPlanMstrList.do";
    
    $.post(url,FormQueryString(workListCinsPlanMstrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workListCinsPlanMstrDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
    workListCinsPlanMstrListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = "";
   excelServerAction("workListCinsPlanMstrList", workListCinsPlanMstrListForm);
}

/**
 * Print 버튼 클릭
 */
function goPrint()
{
   var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'WKORID'); //Grid, check box column seq, pk column seq
   if(typeof selArray == "undefined"){
       alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
       return;
   }
   var selectArray = selArray.substring(1,selArray.length).split("&");
   var selectStr;
   for(var i=0; i<selectArray.length;i++){
       selectStr +=","+selectArray[i].split("=")[1];
   }
   
   var url   = contextPath + "/workListPmiList.do";
   openPrintView(url, "maWoResultMstrCommonDTO.selectedWkorId="+selectStr);
}

function goWopdf(){
   goPrint();
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListCinsPlanMstrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.pmInsDSchedId"/>  <!-- Key -->
<html:hidden property="workListCinsPlanMstrCommonDTO.filterProductId"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.filterDeptId"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.filterEquipId"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.filterEmpId"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.filterEqLocId"/>

<html:hidden property="workListCinsPlanMstrCommonDTO.selectedPmType"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.selectedWoType"/>

<html:hidden property="workListCinsPlanMstrCommonDTO.pmId"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.pmEquipId"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.productId"/>
<html:hidden property="workListCinsPlanMstrCommonDTO.productDesc"/>
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
                <!-- 계획일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.planDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workListCinsPlanMstrCommonDTO.filterStartPlanDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workListCinsPlanMstrCommonDTO.filterEndPlanDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 생산제품 -->
                <div class="field">
                    <label><bean:message key="LABEL.prodGoods"/></label>
                        <div class="input_box">
                        <html:text property="workListCinsPlanMstrCommonDTO.filterProductDesc" tabindex="30" />
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
                        <html:text property="workListCinsPlanMstrCommonDTO.filterDeptDesc" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 설비 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipDesc"/></label>
                        <div class="input_box">
                        <html:text property="workListCinsPlanMstrCommonDTO.filterEquipDesc" tabindex="50" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.eqLocDesc"/></label>
                        <div class="input_box">
                        <html:text property="workListCinsPlanMstrCommonDTO.filterEqLocDesc" tabindex="60" />
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
                        <html:text property="workListCinsPlanMstrCommonDTO.filterEmpDesc" tabindex="70" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 점검일정발행여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isInsPlanActive"/></label>
                        <div class="input_box">
                        <html:text property="workListCinsPlanMstrCommonDTO.filterIsActive" tabindex="80" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 점검완료여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isInsComplete"/></label>
                        <div class="input_box">
                        <html:text property="workListCinsPlanMstrCommonDTO.filterIsComplete" tabindex="90" />
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

