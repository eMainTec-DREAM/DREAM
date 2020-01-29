<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmiDInsListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiDInsDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 일상점검목록 -->
<title><bean:message key='MENU.TINSPECTION'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var eqLocAc, empAc, equipAc, deptAc, plantAc;

var beforePageId = 'workPmiDInsDetail';

function loadPage() 
{
	workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterPmTypeId'].value = "DINS"; 
	workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterPmTypeDesc'].value = "DINS"; 
	
    valSysDir('workPmiDInsCommonDTO.filterPmTypeId', 'workPmiDInsCommonDTO.filterPmTypeDesc', 'PMI_TYPE', true);

	
	// 날짜 자동완성 (1달전~오늘)
	if(workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterStartDate'].value == "")
	{
		workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterStartDate'].value = getMinusDay(7); 
		workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterEndDate'].value   = getToday();
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
	//작업그룹
    if(loginUser.filterWkCtrId!='null')
    {
    	workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterWkCtrId'].value     = loginUser.filterWkCtrId;
    	workPmiDInsListForm.elements['workPmiDInsCommonDTO.filterWkCtrDesc'].value   = loginUser.filterWkCtrDesc;
    }
	
	// 상태 자동완성
    acSysDesc("workPmiDInsCommonDTO.filterSchedStatusDesc","workPmiDInsCommonDTO.filterSchedStatusId","PMINSDLIST_STATUS");
	
    // 담당자 자동완성
    empAc = new autoC({"workPmiDInsCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workPmiDInsCommonDTO.filterEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workPmiDInsCommonDTO.filterDeptId",
    	"plant" : "workPmiDInsCommonDTO.filterPlantId"
    });
    empAc.init();
	
	// 작업형태 자동완성
	acSysDesc("workPmiDInsCommonDTO.filterPmTypeDesc","workPmiDInsCommonDTO.filterPmTypeId",'PMI_TYPE');
	
	// 설비 자동완성 
    equipAc = new autoC({"workPmiDInsCommonDTO.filterEquipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcResultMap({
        "workPmiDInsCommonDTO.filterEqLocId":"EQLOC_ID"
      , "IS_USE":"Y"
    });
    equipAc.setAcDConditionMap({
    	"dept_id" : "workPmiDInsCommonDTO.filterDeptId",
    	"plant" : "workPmiDInsCommonDTO.filterPlantId"
    });
    equipAc.init();
    
    // 부서 자동완성
    deptAc = new autoC({"workPmiDInsCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workPmiDInsCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workPmiDInsCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    // 관리자(정) 자동완성
    mainMngAc = new autoC({"workPmiDInsCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "workPmiDInsCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "workPmiDInsCommonDTO.filterDeptId",
    	"plant" : "workPmiDInsCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    // 관리자(부) 자동완성
    subMngAc = new autoC({"workPmiDInsCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "workPmiDInsCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "workPmiDInsCommonDTO.filterDeptId",
    	"plant" : "workPmiDInsCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
    
    // 설비유형 자동완성
    acSysDesc("workPmiDInsCommonDTO.filterEqCtgTypeDesc","workPmiDInsCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    // 교대조 AC
    acSysDesc("workPmiDInsCommonDTO.filterShiftDesc","workPmiDInsCommonDTO.filterShiftId","SHIFT_TYPE");
    //법정설비여부 AC
    acSysDesc("workPmiDInsCommonDTO.filterIsLawEq","workPmiDInsCommonDTO.filterIsLawEqId","IS_USE",true);
   
    // 작업상태 AC
    //acSysDesc("workPmiDInsCommonDTO.filterSchedStatusDesc","workPmiDInsCommonDTO.filterSchedStatusId","PMINSDLIST_STATUS");
    
    // 작업그룹 자동완성
    wkCtrDescAc = new autoC({"workPmiDInsCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workPmiDInsCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    plantAc = new autoC({"workPmiDInsCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workPmiDInsCommonDTO.filterPlantId":"plant"
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
/*     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = "";
        return sortColumn("workPmiDInsList", this, workPmiDInsListForm, "PMINSDLISTID", ind, direction);
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
    var url = contextPath + "/workPmiDInsList.do";
    workPmiDInsListForm.elements['strutsAction'].value = '<%=WorkPmiDInsListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmiDInsListForm), "PMINSDLISTID" , "Y");
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsDListId)
{
    workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = _pmInsDListId;
    findGridList('ReloadRow');
    workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = "";  // 검색시 Tab 이동Key Clear
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
    workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value =  getValueById(myGrid, selectedId,'PMINSDLISTID');  
    
    goCommonTabPage(workPmiDInsListForm, <%= WorkPmiDInsDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workPmiDInsDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value =  getValueById(myGrid, selectedId,'PMINSDLISTID');  
    workPmiDInsListForm.elements['strutsAction'].value = '<%=WorkPmiDInsDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmiDInsListForm), 'workPmiDInsDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    var param = "strutsAction=0";
    openLayerPopup("workPmDInsSelect", param);
}

function execValidation(workDate, workPointId, workPointNo, pmId, pmNo)
{
    var form = document.workPmiDInsListForm;
    
    form.elements['workPmiDInsCommonDTO.popupWorkDate'].value = workDate;
    form.elements['workPmiDInsCommonDTO.popupWorkPointId'].value = workPointId;
    
    form.elements['workPmiDInsCommonDTO.popupWorkPointNo'].value = workPointNo;
    form.elements['workPmiDInsCommonDTO.popupPmId'].value = pmId;
    form.elements['workPmiDInsCommonDTO.popupPmNo'].value = pmNo;
     
    form.elements['workPmiDInsCommonDTO.pmInsDListId'].value = "";
    goCommonTabPage(form, '', "workPmiDInsDetail");
}

function goCreateAction(pageId)
{
    workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = "";
    goCommonTabPage(workPmiDInsListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMINSDLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workPmiDInsListForm.strutsAction.value = '<%=WorkPmiDInsListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmiDInsList.do";
    
    $.post(url,FormQueryString(workPmiDInsListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workPmiDInsDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
    workPmiDInsListForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = "";
   excelServerAction("workPmiDInsList", workPmiDInsListForm);
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
<html:form action="/workPmiDInsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workPmiDInsCommonDTO.pmInsDListId"/>  <!-- Key -->
<html:hidden property="workPmiDInsCommonDTO.filterPmTypeId"/>
<html:hidden property="workPmiDInsCommonDTO.filterPmTypeDesc"/>
<html:hidden property="workPmiDInsCommonDTO.filterIsLawEqId"/>
<html:hidden property="workPmiDInsCommonDTO.filterDeptId"/>
<html:hidden property="workPmiDInsCommonDTO.filterMainMngId"/>
<html:hidden property="workPmiDInsCommonDTO.filterSubMngId"/>
<html:hidden property="workPmiDInsCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workPmiDInsCommonDTO.filterShiftId"/>
<html:hidden property="workPmiDInsCommonDTO.filterSchedStatusId"/>
<html:hidden property="workPmiDInsCommonDTO.filterWkCtrId"/>
<html:hidden property="workPmiDInsCommonDTO.filterEqLocId"/>
<html:hidden property="workPmiDInsCommonDTO.filterEmpId"/>
<html:hidden property="workPmiDInsCommonDTO.filterPlantId"/>
<html:hidden property="workPmiDInsCommonDTO.notPmschedStatusId"/>

<html:hidden property="workPmiDInsCommonDTO.popupWorkDate"/>
<html:hidden property="workPmiDInsCommonDTO.popupWorkPointId"/>
<html:hidden property="workPmiDInsCommonDTO.popupWorkPointNo"/>
<html:hidden property="workPmiDInsCommonDTO.popupPmId"/>
<html:hidden property="workPmiDInsCommonDTO.popupPmNo"/>

<html:hidden property="workPmiDInsCommonDTO.selectedPmType"/>
<html:hidden property="workPmiDInsCommonDTO.selectedWoType"/>

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
                <!-- 결과# -->
                <div class="field">
                    <label><bean:message key="LABEL.resultNo"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.pmInsDListNo" tabindex="1"/>
                    </div>
                </div>
                <!-- 작업명 -->
                <div class="field">
                    <label><bean:message key="LABEL.woName"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterWkOrDesc" tabindex="10"/>
                    </div>
                </div>
                <!-- 작업일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.woDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workPmiDInsCommonDTO.filterStartDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workPmiDInsCommonDTO.filterEndDate" tabindex="30" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 설비 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipDesc"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterEquipDesc" tabindex="35" />
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
                        <html:text property="workPmiDInsCommonDTO.filterDeptDesc" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterEmpDesc" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 법정설비여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isLawEq"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterIsLawEq" tabindex="90" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 관리자(정) -->
                <div class="field">
                    <label><bean:message key="LABEL.mainManager"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterMainMngName" tabindex="100" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 관리자(부) -->
                <div class="field">
                    <label><bean:message key="LABEL.subManager"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterSubMngName" tabindex="110" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <%-- 
                <!-- 작업형태  -->
                <div class="field">
                    <label><bean:message key="LABEL.pmType"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterPmTypeDesc" tabindex="130" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                 --%>
                <!-- 작업상태  -->
                <div class="field">
                    <label><bean:message key="LABEL.woStatus"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterSchedStatusDesc" tabindex="140" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 예방작업번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.pmNo"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterPmNo" tabindex="160"/>
                    </div>
                </div>     
                <!-- 작업그룹 -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterWkCtrDesc" tabindex="170" />
                         <p class="open_spop">
                             <a>
                                 <span>조회</span>
                             </a>
                         </p>
                   </div>
                </div>
                <!-- 설비유형  -->
                <div class="field">
                    <label><bean:message key="LABEL.eqCtgType"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterEqCtgTypeDesc" tabindex="180" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- shift  -->
                <div class="field">
                    <label><bean:message key="LABEL.shiftType"/></label>
                    <div class="input_box">
                        <html:text property="workPmiDInsCommonDTO.filterShiftDesc" tabindex="190" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="workPmiDInsCommonDTO.filterPlantDesc" tabindex="160" />
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

