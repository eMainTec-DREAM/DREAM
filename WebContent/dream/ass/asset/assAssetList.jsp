<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.asset.action.AssAssetListAction" %>
<%@ page import="dream.ass.asset.action.AssAssetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비등급 평가 -->
<title><bean:message key='MENU.ASSASSET'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var eqLocAc, empAc, equipAc, plantAc;


function loadPage() 
{
	assAssetListForm.elements['assAssetCommonDTO.eqCtgType'].value = "MC"; 

	//공장명
    if(loginUser.filterPlant!='null'){
    	assAssetListForm.elements['assAssetCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	assAssetListForm.elements['assAssetCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	// 날짜 자동완성 (1달전~오늘)
	assAssetListForm.elements['assAssetCommonDTO.filterAssStartDate'].value = getMinusMonth2(new Date(), -1); 
	assAssetListForm.elements['assAssetCommonDTO.filterAssEndDate'].value   = getToday();
    
	// 상태 자동완성
    acSysDesc("assAssetCommonDTO.filterEqasslistStatusDesc","assAssetCommonDTO.filterEqasslistStatusId","EQASSLIST_STATUS");
    acSysDesc("assAssetCommonDTO.conOperDesc","assAssetCommonDTO.conOper","TAB_CON_OPERATOR");
	// 설비 자동완성 
	equipAc = new autoC({"assAssetCommonDTO.filterEquipDesc":"description"});
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
        "assAssetCommonDTO.filterEquipId":"EQUIP_ID"
    });
	equipAc.setAcDConditionMap({
    	"eqloc_id" : "assAssetCommonDTO.filterEqLocId",
    	"plant" : "assAssetCommonDTO.filterPlantId"
    	
    });
	equipAc.init();
	
	// 설비위치 자동완성
    eqLocAc = new autoC({"assAssetCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    eqLocAc.setAcDConditionMap({
    	"plant" : "assAssetCommonDTO.filterPlantId"
    });
    eqLocAc.setAcResultMap({
        "assAssetCommonDTO.filterEqLocId":"EQLOC_ID"
    });
    eqLocAc.init();
	
    // 평가등급 자동완성
    acSysDesc("assAssetCommonDTO.filterEqGradeDesc","assAssetCommonDTO.filterEqGradeId","EQ_GRADE");
    
    // 담당자 자동완성
    empAc = new autoC({"assAssetCommonDTO.filterEmpName":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    empAc.setAcDConditionMap({
    	"plant" : "assAssetCommonDTO.filterPlantId"
    });
    empAc.setAcResultMap({
        "assAssetCommonDTO.filterEmpId":"emp_id"
    });
    empAc.init();
	
    // 공장명
    plantAc = new autoC({"assAssetCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assAssetCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 평가구분 자동완성
    acSysDesc("assAssetCommonDTO.filterAssTypeDesc","assAssetCommonDTO.filterAssTypeId","EQASSLIST_TYPE");
	
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
    	assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value = "";
        return sortColumn("assAssetList", this, assAssetListForm, "EQASSLISTID", ind, direction);
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
    var url = contextPath + "/assAssetList.do";
    assAssetListForm.elements['strutsAction'].value = '<%=AssAssetListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assAssetListForm), "EQASSLISTID" , "Y");

}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqasslistId)
{
    assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value = _eqasslistId;
    findGridList('ReloadRow');
    assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value = "";  // 검색시 Tab 이동Key Clear
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
    assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value =  getValueById(myGrid, selectedId,'EQASSLISTID');  
    
    goCommonTabPage(assAssetListForm, <%= AssAssetDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assAssetDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value =  getValueById(myGrid, selectedId,'EQASSLISTID');  
    assAssetListForm.elements['strutsAction'].value = '<%=AssAssetDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assAssetListForm), 'assAssetDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assAssetDetail" , "goCreateAction");
    
    // 생성시 설비, 평가등급 기준표 수정 불가
}

function goCreateAction(pageId)
{
    assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value = "";
    goCommonTabPage(assAssetListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{

    var delArray = getDeletRows(myGrid, 'isDelCheck', 'EQASSLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    assAssetListForm.strutsAction.value = '<%=AssAssetListAction.LIST_DELETE%>';
    var url = contextPath + "/assAssetList.do";
    
    $.post(url,FormQueryString(assAssetListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assAssetDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value = "";
    excelServerAction("assAssetList", assAssetListForm );  
}

function goCreplaninvt()
{
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0236'/>", function(result){
		 if(result){
			 
			 var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'EQUIPID'); //Grid, check box column seq, pk column seq
			 
			 if(typeof selArray == "undefined"){
					alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
					return;
				}
			    assAssetListForm.strutsAction.value = '<%=AssAssetListAction.CREATE_INVT_LIST%>';
				var url = contextPath + "/assAssetList.do";
			    $.post(url,FormQueryString(assAssetListForm)+selArray , function(_data){
			        afterCreplaninvt();
			    });
		 }
	 });
}

function afterCreplaninvt()
{
    goClose('assAssetDetail', this);
    alertMessage1('<bean:message key="MESSAGE.MSG0237"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assAssetListForm.elements['assAssetCommonDTO.eqasslistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assAssetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assAssetCommonDTO.eqasslistId"/><!-- Key -->
<html:hidden property="assAssetCommonDTO.filterEqasslistStatusId"/>     <!-- 상태ID -->
<html:hidden property="assAssetCommonDTO.filterEquipId"/>               <!-- 설비ID -->
<html:hidden property="assAssetCommonDTO.filterEqLocId"/>               <!-- 설비위치ID -->
<html:hidden property="assAssetCommonDTO.filterEqGradeId"/>             <!-- 평가등급ID -->
<html:hidden property="assAssetCommonDTO.filterEmpId"/>                 <!-- 담당자ID -->
<html:hidden property="assAssetCommonDTO.filterPlantId"/>
<html:hidden property="assAssetCommonDTO.conOper"/>
<html:hidden property="assAssetCommonDTO.eqCtgType"/>
<html:hidden property="assAssetCommonDTO.filterAssTypeId"/>

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
                <!-- 평가일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.assDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="assAssetCommonDTO.filterAssStartDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="assAssetCommonDTO.filterAssEndDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 상태 -->
                <div class="field">
                    <label><bean:message key="LABEL.status"/></label>
                    <div class="input_box">
                        <html:text property="assAssetCommonDTO.filterEqasslistStatusDesc" tabindex="20"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 설비번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipNo"/></label>
                    <div class="input_box">
                        <html:text property="assAssetCommonDTO.filterEquipNo" tabindex="25"/>
                    </div>
                </div>
                <!-- 설비 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipDesc"/></label>
                    <div class="input_box">
                        <html:text property="assAssetCommonDTO.filterEquipDesc" tabindex="30"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 설비위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.eqLocDesc"/></label>
                    <div class="input_box">
                        <html:text property="assAssetCommonDTO.filterEqLocDesc" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 평가등급 -->
                <div class="field">
                    <label><bean:message key="LABEL.assGrade"/></label>
                    <div class="input_box">
                        <html:text property="assAssetCommonDTO.filterEqGradeDesc" tabindex="50"/>
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
                        <html:text property="assAssetCommonDTO.filterEmpName" tabindex="60" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 평가점수 -->
                <div class="field">
                <label><bean:message key="LABEL.assScore"/></label>
	                <div class="datetime_wrap">
	                    <div class="input_box">
	                        <html:text property="assAssetCommonDTO.assScore" tabindex="70" styleClass="num"/>
	                    </div>
	                    <div class="input_box">
	                        <html:text property="assAssetCommonDTO.conOperDesc" tabindex="80"/>
	                        <p class="open_spop">
	                            <a>
	                            <span>조회</span></a>
	                        </p>
	                    </div>
	                </div>
	            </div>	
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="assAssetCommonDTO.filterPlantDesc" tabindex="90" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 평가구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.assType"/></label>
                    <div class="input_box">
                        <html:text property="assAssetCommonDTO.filterAssTypeDesc" tabindex="100"/>
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