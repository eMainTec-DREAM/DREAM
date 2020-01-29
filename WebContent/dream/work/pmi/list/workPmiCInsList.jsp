<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 파트체인지점검목록 -->
<title><bean:message key='MENU.CINSPECTION'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var eqLocAc, empAc, equipAc, deptAc, productAc;

var woType = "PMI";

var beforePageId = 'workPmiCInsDetail';

function loadPage() 
{
	//작업형태 = 파트체인지
	workPmiCInsListForm.elements['workPmiCInsCommonDTO.filterPmTypeId'].value = "CINS";
	
	//생산제품 자동완성
    productAc = new autoC({"workPmiCInsCommonDTO.productDesc":"productDesc"});
    productAc.setAcConditionMap({
        "comp_no":loginUser.compNo
      , "is_use": "Y"
       });
    productAc.setTable("TAPRODUCT");
    productAc.setAcResultMap({
        "workPmiCInsCommonDTO.productId":"productId"
    });
    productAc.init();
	
	// 날짜 자동완성 (1달전~오늘)
	workPmiCInsListForm.elements['workPmiCInsCommonDTO.filterStartDate'].value = getMinusMonth2(new Date(), -1); 
	workPmiCInsListForm.elements['workPmiCInsCommonDTO.filterEndDate'].value   = getToday();
    
	// 상태 자동완성
    acSysDesc("workPmiCInsCommonDTO.filterSchedStatusDesc","workPmiCInsCommonDTO.filterSchedStatusId","PMINSDLIST_STATUS");
	
    // 담당자 자동완성
    empAc = new autoC({"workPmiCInsCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workPmiCInsCommonDTO.filterEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workPmiCInsCommonDTO.filterDeptId"
    });
    empAc.init();
	
	// 설비 자동완성 
    equipAc = new autoC({"workPmiCInsCommonDTO.filterEquipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcResultMap({
        "workPmiCInsCommonDTO.filterEqLocId":"EQLOC_ID"
      , "IS_USE":"Y"
    });
    equipAc.setAcDConditionMap({
    	"dept_id" : "workPmiCInsCommonDTO.filterDeptId"
    });
    equipAc.init();
    
    // 부서 자동완성
    deptAc = new autoC({"workPmiCInsCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workPmiCInsCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    // 관리자(정) 자동완성
    mainMngAc = new autoC({"workPmiCInsCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "workPmiCInsCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.init();
    
    // 관리자(부) 자동완성
    subMngAc = new autoC({"workPmiCInsCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "workPmiCInsCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.init();
    
    
    // 설비유형 자동완성
    acSysDesc("workPmiCInsCommonDTO.filterEqCtgTypeDesc","workPmiCInsCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    // 교대조 AC
    acSysDesc("workPmiCInsCommonDTO.filterShiftDesc","workPmiCInsCommonDTO.filterShiftId","SHIFT_TYPE");
    //법정설비여부 AC
    acSysDesc("workPmiCInsCommonDTO.filterIsLawEq","workPmiCInsCommonDTO.filterIsLawEqId","IS_USE",true);
   
    // 작업상태 AC
    //acSysDesc("workPmiCInsCommonDTO.filterSchedStatusDesc","workPmiCInsCommonDTO.filterSchedStatusId","PMINSDLIST_STATUS");
    
    // 작업그룹 자동완성
    wkCtrDescAc = new autoC({"workPmiCInsCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workPmiCInsCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
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
    	workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = "";
        return sortColumn("workPmiCInsList", this, workPmiCInsListForm, "PMINSDLISTID", ind, direction);
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
    var url = contextPath + "/workPmiCInsList.do";
    workPmiCInsListForm.elements['strutsAction'].value = '<%=WorkPmiCInsListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmiCInsListForm), "PMINSDLISTID" , "Y");
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsDListId)
{
    workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = _pmInsDListId;
    findGridList('ReloadRow');
    workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = "";  // 검색시 Tab 이동Key Clear
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
    workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value =  getValueById(myGrid, selectedId,'PMINSDLISTID');  
    
    goCommonTabPage(workPmiCInsListForm, <%= WorkPmiCInsDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workPmiCInsDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value =  getValueById(myGrid, selectedId,'PMINSDLISTID');  
    workPmiCInsListForm.elements['strutsAction'].value = '<%=WorkPmiCInsDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmiCInsListForm), 'workPmiCInsDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    goCreateAction("workPmiCInsList");
    
}


function goCreateAction(pageId)
{
    workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = "";
    goCommonTabPage(workPmiCInsListForm, '', pageId);
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

    workPmiCInsListForm.strutsAction.value = '<%=WorkPmiCInsListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmiCInsList.do";
    
    $.post(url,FormQueryString(workPmiCInsListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workPmiCInsDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
    workPmiCInsListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = "";
   excelServerAction("workPmiCInsList", workPmiCInsListForm);
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmiCInsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workPmiCInsCommonDTO.pmInsDListId"/>  <!-- Key -->
<html:hidden property="workPmiCInsCommonDTO.filterPmTypeId"/>
<html:hidden property="workPmiCInsCommonDTO.filterIsLawEqId"/>
<html:hidden property="workPmiCInsCommonDTO.filterDeptId"/>
<html:hidden property="workPmiCInsCommonDTO.filterMainMngId"/>
<html:hidden property="workPmiCInsCommonDTO.filterSubMngId"/>
<html:hidden property="workPmiCInsCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workPmiCInsCommonDTO.filterShiftId"/>
<html:hidden property="workPmiCInsCommonDTO.filterSchedStatusId"/>
<html:hidden property="workPmiCInsCommonDTO.filterWkCtrId"/>

<html:hidden property="workPmiCInsCommonDTO.popupWorkDate"/>
<html:hidden property="workPmiCInsCommonDTO.popupWorkPointId"/>
<html:hidden property="workPmiCInsCommonDTO.popupWorkPointNo"/>
<html:hidden property="workPmiCInsCommonDTO.popupPmId"/>
<html:hidden property="workPmiCInsCommonDTO.popupPmNo"/>

<html:hidden property="workPmiCInsCommonDTO.selectedPmType"/>
<html:hidden property="workPmiCInsCommonDTO.selectedWoType"/>

<html:hidden property="workPmiCInsCommonDTO.productId"/>

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
                        <html:text property="workPmiCInsCommonDTO.pmInsDListNo" tabindex="1"/>
                    </div>
                </div>
                <!-- 작업명 -->
                <div class="field">
                    <label><bean:message key="LABEL.woName"/></label>
                    <div class="input_box">
                        <html:text property="workPmiCInsCommonDTO.filterWkOrDesc" tabindex="10"/>
                    </div>
                </div>
                <!-- 작업일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.woDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workPmiCInsCommonDTO.filterStartDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workPmiCInsCommonDTO.filterEndDate" tabindex="30" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 설비 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipDesc"/></label>
                    <div class="input_box">
                        <html:text property="workPmiCInsCommonDTO.filterEquipDesc" tabindex="35" />
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
                        <html:text property="workPmiCInsCommonDTO.filterDeptDesc" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="workPmiCInsCommonDTO.filterEmpDesc" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!--생산제품 -->
				<div class="field">
					<label><bean:message key="LABEL.prodGoods" /></label>
					<div class="input_box">
						<html:text property="workPmiCInsCommonDTO.productDesc" tabindex="140" />
						<p class="open_spop">
							<a><span>조회</span></a>
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

