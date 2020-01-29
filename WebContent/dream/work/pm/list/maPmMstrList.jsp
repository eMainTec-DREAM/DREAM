<%--===========================================================================
예방작업기준 - 목록
author  jung7126
version $Id: maPmMstrList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrListAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrDetailAction" %>
<%@ page import="dream.comm.revision.action.CommRevAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String isUsePmRevision = MwareConfig.getIsUsePmRevision();
%>
<html>
<head>
<!-- 예방작업기준 -->
<title><bean:message key='MENU.PMMSTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */
var titleAc;
var equipDescAc;
var deptAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var mgrDescAc;
var wkCtrDescAc;
var pmTypeAc;
var subMngAc;
var woTypeAc;

var beforePageId = 'maPmMstrDetail';
function loadPage() 
{
	if(window.name=="PM_LIST"){
	}else{
		//부서
		maPmMstrListForm.elements['maPmMstrCommonDTO.deptId'].value    = loginUser.filterDeptId;
		maPmMstrListForm.elements['maPmMstrCommonDTO.deptDesc'].value  = loginUser.filterDeptDesc;
		
		if(loginUser.eqLocId!='null'){
			maPmMstrListForm.elements['maPmMstrCommonDTO.eqLocId'].value = loginUser.eqLocId;
			maPmMstrListForm.elements['maPmMstrCommonDTO.eqLocDesc'].value = loginUser.eqLocDesc;
		}
/*         if(loginUser.wkctrId!='null'){
        	maPmMstrListForm.elements['maPmMstrCommonDTO.wkCtrId'].value = loginUser.wkctrId;
        	maPmMstrListForm.elements['maPmMstrCommonDTO.wkCtrDesc'].value = loginUser.wkctrDesc;
		} */
        if(loginUser.filterWkCtrId!='null'){
        	maPmMstrListForm.elements['maPmMstrCommonDTO.wkCtrId'].value = loginUser.filterWkCtrId;
        	maPmMstrListForm.elements['maPmMstrCommonDTO.wkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
        if(loginUser.eqCtgTypeId!='null'){
        	maPmMstrListForm.elements['maPmMstrCommonDTO.eqCtgTypeId'].value = loginUser.eqCtgTypeId;
        	maPmMstrListForm.elements['maPmMstrCommonDTO.eqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	}
	
	maPmMstrListForm.elements['maPmMstrCommonDTO.isLastVersion'].value = "Y";
	maPmMstrListForm.elements['maPmMstrCommonDTO.isLastVersionDesc'].value = "Y";
	valSysDir('maPmMstrCommonDTO.isLastVersion', 'maPmMstrCommonDTO.isLastVersionDesc', 'IS_USE', false);
	
    initGrid();
    
    titleAc = new autoC({"maPmMstrCommonDTO.pmDesc":"description"});
    titleAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    titleAc.setTable("TAPMLST");
    titleAc.init();
    
    
    equipDescAc = new autoC({"maPmMstrCommonDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maPmMstrCommonDTO.equipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maPmMstrCommonDTO.eqLocId",
    	"eqctg_id" : "maPmMstrCommonDTO.eqCtgId",
    	"dept_id" : "maPmMstrCommonDTO.deptId"
    });
    equipDescAc.init();
    
    deptAc = new autoC({"maPmMstrCommonDTO.deptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPmMstrCommonDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    eqLocDescAc = new autoC({"maPmMstrCommonDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maPmMstrCommonDTO.eqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maPmMstrCommonDTO.eqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maPmMstrCommonDTO.eqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    mainMngAc = new autoC({"maPmMstrCommonDTO.mainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPmMstrCommonDTO.mainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPmMstrCommonDTO.deptId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maPmMstrCommonDTO.subMngName":"emp_name"});
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maPmMstrCommonDTO.subMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maPmMstrCommonDTO.deptId"
    });
    subMngAc.init();
    
    
    mgrDescAc = new autoC({"maPmMstrCommonDTO.empName":"emp_name"});
    mgrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mgrDescAc.setTable("TAEMP");
    mgrDescAc.setAcResultMap({
        "maPmMstrCommonDTO.empId":"emp_id"
    });
    mgrDescAc.setAcDConditionMap({
    	"dept_id" : "maPmMstrCommonDTO.deptId"
    });
    mgrDescAc.init();
    
    wkCtrDescAc = new autoC({"maPmMstrCommonDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maPmMstrCommonDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    woTypeAc = new autoC({"maPmMstrCommonDTO.woTypeDesc":"description"});
    woTypeAc.setAcConditionMap({
    	"param2":"PM"
    	,"list_type":"WO_TYPE"
    	,"is_use":"Y"
  	   });
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcResultMap({
        "maPmMstrCommonDTO.woType":"cdsysd_no"
    });
    woTypeAc.init();

    acSysDesc("maPmMstrCommonDTO.eqCtgTypeDesc","maPmMstrCommonDTO.eqCtgTypeId","EQCTG_TYPE");
    acSysDesc("maPmMstrCommonDTO.periodTypeDesc","maPmMstrCommonDTO.periodType","PERIOD_TYPE");
    acSysDesc("maPmMstrCommonDTO.isLawEq","maPmMstrCommonDTO.isLawEq","IS_USE",true);
    
    acSysDesc("maPmMstrCommonDTO.isLastVersionDesc","maPmMstrCommonDTO.isLastVersion","IS_USE",true);
    acSysDesc("maPmMstrCommonDTO.revisionStatusDesc","maPmMstrCommonDTO.revisionStatus","REVISION_STATUS",true);
    acSysDesc("maPmMstrCommonDTO.plfTypeDesc","maPmMstrCommonDTO.plfType","PLF_TYPE");
}

/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
		if(code=="maPmMstrCommonDTO.woTypeDesc")
		{
			if(pmTypeAc) pmTypeAc.destroy();
			
		 	var listType = maPmMstrListForm.elements['maPmMstrCommonDTO.woType'].value+"_TYPE";

		 	pmTypeAc = acSysDesc("maPmMstrCommonDTO.pmTypeDesc","maPmMstrCommonDTO.pmType",listType);

		}
}


/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
		if(pmTypeAc)pmTypeAc.destroy();

	 	var listType = maPmMstrListForm.elements['maPmMstrCommonDTO.woType'].value+"_TYPE";

	 	pmTypeAc = acSysDesc("maPmMstrCommonDTO.pmTypeDesc","maPmMstrCommonDTO.pmType",listType);
	}
}


function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
        return sortColumn("maPmMstrList", this, maPmMstrListForm, "PMID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); myGrid.init();

	
	if(window.name=="PM_LIST") setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
	else setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPmMstrList.do";

    maPmMstrListForm.elements['strutsAction'].value = '<%=MaPmMstrListAction.PM_MSTR_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmMstrListForm), "PMID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmId)
{
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = _pmId;
	findGridList('ReloadRow');
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}


/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
   //goClose(beforePageId);
   tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.maPmMstrListForm;

	form.elements['maPmMstrCommonDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	goCommonTabPage(maPmMstrListForm, '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>', pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	var pmType = getValueById(myGrid, rowId,'PMTYPE');
	var param = getValueById(myGrid, rowId,'PARAM');
	maPmMstrListForm.elements['maPmMstrCommonDTO.selectedPmType'].value = pmType;
	goTabPage(param);	
}

/**
 * Open 버튼 클릭
 */
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	var pageId = getValueById(myGrid, selectedId,'PARAM');
 	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
 	maPmMstrListForm.elements['strutsAction'].value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maPmMstrListForm), pageId); 
}
 
  /**
   * 생성
   */
 function goCreate()
 {
//  	createValidationCheck(myGrid, "maPmMstrDetail" , "goCreateAction");
	 goClose(beforePageId);
	 openSelectType();
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
 }

function afterCreate(_pmId, pageId)
{
	findGridRow(_pmId);
	 
	var form = document.maPmMstrListForm;

	form.elements['maPmMstrCommonDTO.pmId'].value = _pmId;
	goCommonTabPage(maPmMstrListForm, '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>', pageId, beforePageId);
}
  
 function goCreateAction(pageId)
 {
 }

/**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'pmId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPmMstrListForm.strutsAction.value = '<%=MaPmMstrListAction.PM_MSTR_LIST_DELETE%>';
	var url = contextPath + "/maPmMstrList.do";
	$.post(url,FormQueryString(maPmMstrListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	
	//goClose('maPm'+maPmMstrListForm.elements['maPmMstrCommonDTO.selectedPmType'].value+'MstrDetail');
    goCloseAll();
	//	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goExcel()
{
	maPmMstrListForm.elements['maPmMstrCommonDTO.pmId'].value = "";
	excelServerAction("maPmMstrList",maPmMstrListForm);
}

/* function setAfterSelect(returnArray){
	var pmType = returnArray[0];
	var param = returnArray[1];
	maPmMstrListForm.elements['maPmMstrCommonDTO.selectedPmType'].value = pmType;
	beforePageId = param;
	goCommonTabPage(maPmMstrListForm, '', param);
} */

/**
 * 작업형태 선택창 열기
 */
function openSelectType(){
	var param = "strutsAction=1001";
	var url =  contextPath + "/maPmMstrSelect.do";
	
	openLayerPopup("maPmMstrSelect", param);
}

function setAfterSelect(returnArray){
	var woType = returnArray[0];
	var pmType = returnArray[1];
	var param2 = returnArray[2];
	maPmMstrListForm.elements['maPmMstrCommonDTO.selectedWoType'].value = woType;
	maPmMstrListForm.elements['maPmMstrCommonDTO.selectedPmType'].value = pmType;
// 	maPmMstrListForm.elements['maPmMstrCommonDTO.param'].value = param2;

	beforePageId = param2;
	
	//제개정 사용여부
	if("<%=isUsePmRevision%>"=="N"){
		goCommonTabPage(maPmMstrListForm, '', param2);
	}else{

		var param = "strutsAction=1001";
		//PM의 경우 PM마스터 추가시 작업종류(woType)와 작업형태(pmType)값이 필요하여 파라미터 전달
		//생성시 선택하는 팝업이 없으면 param에 제정 후 열릴 상세 PageId만 파리미터로 전달하면 됨
		param += "&" + "popupWidth=800";
		param += "&" + "commRevCommonDTO.selectedWoType="+woType;
		param += "&" + "commRevCommonDTO.selectedPmType="+pmType;
		param += "&" + "commRevCommonDTO.revisionObjType="+"PMSTD";
		param += "&" + "commRevCommonDTO.param="+param2;
		
		openLayerPopup("commRevRegislate", param);
	}
	
}
/**
 * 점검마스터 복사
 */
 function goCopycreate(){
	 var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'PMID'); //Grid, check box column seq, pk column seq
	 	
 	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	 
 	dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
		if(result){
			setModal('<bean:message key="MESSAGE.MSG0083"/>');
			maPmMstrListForm.strutsAction.value = '<%=MaPmMstrListAction.PM_MSTR_LIST_COPY%>';
			var url = contextPath + "/maPmMstrList.do"; 
			$.post(url,FormQueryString(maPmMstrListForm)+selArray , function(_data){
				var jsonObj = JSON.parse(_data);  
		    	for(var i = 0; jsonObj.data.length >i ; i++)
		    	{
		    		findGridRow(jsonObj.data[i].ID);
		    	}
		    	closeModal();
		    	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
		    });
		}
	});
 }
 

 /**
  * 레포트 출력
  */
function goPrint()
 {
 	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'PMID'); //Grid, check box column seq, pk column seq
 	
 	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPmMstrListForm.strutsAction.value = '<%=MaPmMstrListAction.PM_MSTR_QR_INSERT%>';
	var url = contextPath + "/maPmMstrList.do";
	$.post(url,FormQueryString(maPmMstrListForm)+selArray+"&fileName=pmMstrBarcode" , function(_data){
		startReportCall();
    });
 } 
function goListbarcode()
{
	
	maPmMstrListForm.strutsAction.value = '<%=MaPmMstrListAction.PM_MSTR_QR_LIST_INSERT%>'; 
	var url = contextPath + "/maPmMstrList.do";
	$.post(url,FormQueryString(maPmMstrListForm)+"&fileName=pmMstrBarcode" , function(_data){
		startReportCall();
   });
} 

 function startReportCall ()
 {
	 reportCall('pmMstrBarcode','pmMstrBarcode', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'pmMstrBarcode');
 }
 
/**
 * Set Data in List
 */
// function setDataAction(detailArray, keyVal)
// {
// 	var keyValMap = {};
// 	keyValMap["pmno"] 			= "maPmMstrDetailDTO.pmNo";
// 	keyValMap["pmId"] 			= "maPmMstrDetailDTO.pmId";
// 	keyValMap["description"] 	= "maPmMstrDetailDTO.description";
// 	keyValMap["equipdesc"] 		= "maPmMstrDetailDTO.equipDesc";
// 	keyValMap["deptDesc"] 		= "maPmMstrDetailDTO.deptDesc";
// 	keyValMap["pmTypeDesc"] 	= "maPmMstrDetailDTO.pmTypeDesc";
	
// 	setValues(myGrid, "pmId", keyVal, keyValMap, detailArray); 	
// }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPmMstrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmType"/>
<html:hidden property="maPmMstrCommonDTO.woType"/>
<html:hidden property="maPmMstrCommonDTO.equipId"/>
<html:hidden property="maPmMstrCommonDTO.deptId"/>
<html:hidden property="maPmMstrCommonDTO.eqLocId"/>
<html:hidden property="maPmMstrCommonDTO.eqCtgId"/>
<html:hidden property="maPmMstrCommonDTO.eqCtgTypeId"/>
<html:hidden property="maPmMstrCommonDTO.wkCtrId"/>
<html:hidden property="maPmMstrCommonDTO.plfType"/>
<html:hidden property="maPmMstrCommonDTO.mainMngId"/>
<html:hidden property="maPmMstrCommonDTO.subMngId"/>
<html:hidden property="maPmMstrCommonDTO.selectedPmType"/>
<html:hidden property="maPmMstrCommonDTO.selectedWoType"/>
<html:hidden property="maPmMstrCommonDTO.periodType"/>
<html:hidden property="maPmMstrCommonDTO.empId"/>
<html:hidden property="maPmMstrCommonDTO.param"/>
<html:hidden property="maPmMstrCommonDTO.revisionStatus"/>
<html:hidden property="maPmMstrCommonDTO.isLastVersion"/>

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
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.pmNo" tabindex="10"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmDesc"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.pmDesc" tabindex="20"/>
					</div>
				</div>
				<!-- 작업종류  -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.woTypeDesc" tabindex="25" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmTypeDesc"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.pmTypeDesc" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.equipDesc" tabindex="40"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.deptDesc"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.deptDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 내/외자 -->
                <div class="field">
                    <label><bean:message key="LABEL.plfType"/></label>
                    <div class="input_box">
                         <html:text property="maPmMstrCommonDTO.plfTypeDesc" tabindex="55"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.eqLocDesc" tabindex="60"/>
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
						<html:text property="maPmMstrCommonDTO.eqCtgDesc" tabindex="65"/>
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
						<html:text property="maPmMstrCommonDTO.mainMngName" tabindex="70"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.subMngName" tabindex="80"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.isLawEq" tabindex="90" />
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
						<html:text property="maPmMstrCommonDTO.empName" tabindex="100"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 주기 -->
				<div class="field">
					<label><bean:message key="LABEL.cycleDesc"/></label>
					<div class="input_box">
							<html:text property="maPmMstrCommonDTO.cycle" tabindex="120" styleClass="num"/>
					</div>
				</div>
				<!-- 주기구불 -->
				<div class="field">
					<label><bean:message key="LABEL.periodTypeDesc"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.periodTypeDesc" tabindex="130"/>
							<p class="open_spop">
								<a>
								<span>조회</span></a>
							</p>
					</div>
				</div>
				<!-- 작업그룹 -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maPmMstrCommonDTO.wkCtrDesc" tabindex="140"/>
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
						<html:text property="maPmMstrCommonDTO.eqCtgTypeDesc" tabindex="150"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 최신 Version 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.isLastVersion"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.isLastVersionDesc" tabindex="160"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!--  Revision 상태  -->
				<div class="field">
					<label><bean:message key="LABEL.revisionStatus"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.revisionStatusDesc" tabindex="170"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- revision 번호  -->
				<div class="field">
					<label><bean:message key="LABEL.revNo"/></label>
					<div class="input_box">
						<html:text property="maPmMstrCommonDTO.revNo" tabindex="180"/>
					</div>
				</div>
				<!-- 생성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.creDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maPmMstrCommonDTO.filterFromCreDate" tabindex="190" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maPmMstrCommonDTO.filterToCreDate" tabindex="200" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
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
