<%--===========================================================================
설비마스터 - 목록
author  kim21017
version $Id: maEqMstrList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction" %>
<%@ page import="dream.comm.revision.action.CommRevAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String isUseAssetRevision = MwareConfig.getIsUseAssetRevision();
%>
<html>
<head>
<!-- 설비마스터 -->
<title><bean:message key="MENU.EQMSTR"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
var selectedEqId='';
var beforePageId = '';

/** 자동완성 변수 */
var equipDescAc;
var eqLocDescAc;
var eqCtgTypeAc;
var eqStatusAc;
var deptAc;
var isLawEqAc;
var mainMngAc;
var subMngAc;
var eqGradeAc;
var eqCtgTypeAc;
var assetAc;
var usageDeptAc;
var eqGrade;
var wkctrAc;
var plantAc;

//생성 타입
var createType;

var isNew = false;

function loadPage() 
{      
	if(window.name=="LOCLIST_EQ_POPUP"||window.name=="DEPTLIST_EQ_POPUP"){
	}
	else{
		//부서
		maEqMstrListForm.elements['maEqMstrCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		maEqMstrListForm.elements['maEqMstrCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		
		if(loginUser.eqLocId!='null' && loginUser.eqLocId!=''){
			maEqMstrListForm.elements['maEqMstrCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			maEqMstrListForm.elements['maEqMstrCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
		if(loginUser.eqCtgTypeId!='null' && loginUser.eqCtgTypeId!=''){
			maEqMstrListForm.elements['maEqMstrCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
			maEqMstrListForm.elements['maEqMstrCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	  	//공장명
	    if(loginUser.filterPlant!='null'){
	    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
		
	}
	
	
	maEqMstrListForm.elements['maEqMstrCommonDTO.eqStatusDesc'].value = "R"; 
	maEqMstrListForm.elements['maEqMstrCommonDTO.eqStatusId'].value = "R"; 
	valSysDir('maEqMstrCommonDTO.eqStatusId', 'maEqMstrCommonDTO.eqStatusDesc', 'EQ_STATUS', true);
	
    initGrid();

    maEqMstrListForm.elements['maEqMstrCommonDTO.filterIsLastVersionId'].value = 'Y';
    maEqMstrListForm.elements['maEqMstrCommonDTO.filterIsLastVersionDesc'].value = 'Y';
	valSysDir('maEqMstrCommonDTO.filterIsLastVersionId', 'maEqMstrCommonDTO.filterIsLastVersionDesc', 'IS_USE', true);
	
    //----------------------------------------------------------------//
    equipDescAc = new autoC({"maEqMstrCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maEqMstrCommonDTO.filterEqLocId",
    	"eqctg_id" : "maEqMstrCommonDTO.filterEqCtgId",
    	"dept_id" : "maEqMstrCommonDTO.filterDeptId"
    });
    equipDescAc.init();
    //----------------------------------------------------------------//
    eqLocDescAc = new autoC({"maEqMstrCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });
    eqLocDescAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    //----------------------------------------------------------------//
    eqCtgTypeAc = new autoC({"maEqMstrCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgTypeAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.setMultiSelect(false);
	eqCtgTypeAc.init();
    //----------------------------------------------------------------//

    deptAc = new autoC({"maEqMstrCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maEqMstrCommonDTO.filterDeptId":"dept_id",
    });
    deptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	}); 
    deptAc.setAcDConditionMap({
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });

   // deptAc.setCustomLov("lovDept('maEqMstrCommonDTO.filterDeptId', '', 'maEqMstrCommonDTO.filterDeptDesc')");
    deptAc.init();
    //----------------------------------------------------------------//
	// 사용부서
    usageDeptAc = new autoC({"maEqMstrCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "maEqMstrCommonDTO.filterUsageDeptId":"dept_id",
    });
    usageDeptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	}); 
    usageDeptAc.setAcDConditionMap({
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });
    usageDeptAc.init();
    //----------------------------------------------------------------//
    mainMngAc = new autoC({"maEqMstrCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maEqMstrCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrCommonDTO.filterDeptId"
    });
    //mainMngAc.setCustomLov("lovEmp('maEqMstrCommonDTO.filterMainMngId', '', 'maEqMstrCommonDTO.filterMainMngName')");
    mainMngAc.init();
    //----------------------------------------------------------------//
    subMngAc = new autoC({"maEqMstrCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maEqMstrCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrCommonDTO.filterDeptId"
    });
    //subMngAc.setCustomLov("lovEmp('maEqMstrCommonDTO.filterSubMngId', '', 'maEqMstrCommonDTO.filterSubMngName')");
    subMngAc.init();
    //----------------------------------------------------------------//
    
    assetAc = new autoC({"maEqMstrCommonDTO.filterAccAssetDesc":"description"});
    assetAc.setTable("TAASSET");
    assetAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	,"is_use":"Y"
    });
    assetAc.setAcResultMap({
        "maEqMstrCommonDTO.filterAccAssetId":"asset_id"
    });
    assetAc.init();
    //----------------------------------------------------------------//
	//작업그룹
	wkctrAc = new autoC({"maEqMstrCommonDTO.filterWkctrDesc":"description"});
	wkctrAc.setTable("TAWKCTR");
	wkctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	wkctrAc.setAcResultMap({
        "maEqMstrCommonDTO.filterWkctrId":"wkctr_id"
    });
	wkctrAc.init();
	
	//-----------------------------------------------------------------//
	    
      eqGrade = new autoC({"maEqMstrCommonDTO.filterEqGradeDesc":"description"});
    eqGrade.setTable("TACDSYSD");
    eqGrade.setAcConditionMap({
        "list_type":"EQ_GRADE"
    	,"is_use":"Y"
    //'	,"param1":"MC"
    });
    eqGrade.setAcResultMap({
        "maEqMstrCommonDTO.filterEqGradeId":"cdsysd_no"
    });
    eqGrade.init();
    
    //공장명
    plantAc = new autoC({"maEqMstrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maEqMstrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    
    acSysDesc("maEqMstrCommonDTO.eqStatusDesc","maEqMstrCommonDTO.eqStatusId","EQ_STATUS");
    acSysDesc("maEqMstrCommonDTO.filterIsLawEq","maEqMstrCommonDTO.filterIsLawEq","IS_USE");
    //acSysDesc("maEqMstrCommonDTO.filterEqGradeDesc","maEqMstrCommonDTO.filterEqGradeId","EQ_GRADE");
    acSysDesc("maEqMstrCommonDTO.filterEqCtgTypeDesc","maEqMstrCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    acSysDesc("maEqMstrCommonDTO.filterIsLastVersionDesc","maEqMstrCommonDTO.filterIsLastVersionId","IS_USE");
    acSysDesc("maEqMstrCommonDTO.filterPlfTypeDesc","maEqMstrCommonDTO.filterPlfTypeId","PLF_TYPE");
    
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500); 
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedEqId = rowId;
		goOpen(rowId);
	});
	
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
    	return sortColumn("maEqMstrList", this, maEqMstrListForm, "EQUIPID", ind, direction);
	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maEqMstrList.do";

    maEqMstrListForm.elements['strutsAction'].value = '<%=MaEqMstrListAction.EQ_MSTR_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrListForm), "EQUIPID","Y");
}


/*
 * 엑셀 업로드
 */
function goExluploadLink()
{
	var actionUrl = contextPath + "/maEqMachMstrList.do";
    var param =  "&strutsAction=" + '<%= MaEqMstrListAction.GET_DATA %>'
              +  "&" + FormQueryString(maEqMstrListForm);
    XMLHttpPostVal(actionUrl, param, 'afterGoExlupload');
}

var dataArr;
function afterGoExlupload(ajaxXmlDoc)
{
	dataArr = '0';
	var data = parseXmlDoc(ajaxXmlDoc, 'DESC');
	var uploadTypeId = "";
	var uploadType = "";
	var tableName = "";
	
	data = data.toString();

	if(data != '0')
    {
		dataArr = data.split(',');
		
		uploadTypeId = dataArr[0];
		uploadType = dataArr[1];
		tableName = dataArr[2];
		
    }
		goExlupload(uploadTypeId, uploadType, tableName);
}


/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	//excelAction(myGrid);
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
	excelServerAction("maEqMstrList", maEqMstrListForm );  
 }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_equipId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = _equipId;
	findGridList('ReloadRow');
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
}

function afterRowSearch()
{
	if(createType=='REVISION'){
		//선택된 row 상세 페이지 열기
		goOpenAfterRev(myGrid.getSelectedRowId());
	}
	createType = '';
}
function findGridAfterRev(_equipId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = _equipId;
	createType = "REVISION";
	findGridList('ReloadRow');
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
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
	var form = document.maEqMstrListForm;

	if(!isNew)
	{
		form.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
	}
	
	goCommonTabPage(form, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, pageId,beforePageId);

	beforePageId = pageId;
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	var param  = getValueById(myGrid, rowId,'PARAM');
	maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = getValueById(myGrid, rowId,'EQCTGTYPE');
	goTabPage(param);
}
 
 function goOpenAfterRev(regRowId){
	 selectedEqId = regRowId;
	 if(regRowId != 'null' || regRowId != 'undefinded'|| regRowId != ''){
		 goOpen(regRowId);
	 }
 }
 /**
  * 상세열기 버튼
  */
 function goOpenAction()
 {
 	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	var form = document.maEqMstrListForm;
	form.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
 	form.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>';
	var pageId  = getValueById(myGrid, selectedId,'PARAM');
 	openQuickTabPage(FormQueryString(form), pageId); 
 } 
 
  /**
   * 생성
   */
 function goCreate()
 {
//  	createValidationCheck(myGrid, "maEqMstrDetail" , "goCreateAction");
	goClose(beforePageId);
	openSelectType();
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
 }
  
function afterCreate(_equipId, pageId)
{
	findGridRow(_equipId);
	 
	var form = document.maEqMstrListForm;

	form.elements['maEqMstrCommonDTO.equipId'].value = _equipId;
	goCommonTabPage(form, '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>', pageId, beforePageId);
	
	console.log("!!!!!!"+pageId+"   "+beforePageId);
	beforePageId = pageId;
}

 function goCreateAction(pageId)
 {
 }

 function setAfterSelect(returnArray){
		var eqCtgType = returnArray[0];
		var param2  = returnArray[1];
		maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = eqCtgType;
		//제개정 사용여부
		if("<%=isUseAssetRevision%>"=="N"){
			beforePageId = param2;
			goCommonTabPage(maEqMstrListForm, '', param2);
		}else{
			var param = "strutsAction=1001";
			//PM의 경우 PM마스터 추가시 작업종류(woType)와 작업형태(pmType)값이 필요하여 파라미터 전달
			//생성시 선택하는 팝업이 없으면 param에 제정 후 열릴 상세 PageId만 파리미터로 전달하면 됨
			param += "&" + "popupWidth=800";
			param += "&" + "commRevCommonDTO.eqCtgTypeId="+eqCtgType;
			param += "&" + "commRevCommonDTO.revisionObjType="+"ASSET";
			param += "&" + "commRevCommonDTO.param="+param2;
			
			if("TL"==eqCtgType){
				openLayerPopup("commEqToolRevRegislate", param);
			}else{
				openLayerPopup("commRevRegislate", param);
			}
		}
	}
 
 /**
  * 레포트 출력
  */
function goPrint()
 {
 	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'EQUIPID'); //Grid, check box column seq, pk column seq
 	
 	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_QR_INSERT%>';
	var url = contextPath + "/maEqMstrList.do";
	$.post(url,FormQueryString(maEqMstrListForm)+selArray+"&fileName=eqMstrBarcodeEn" , function(_data){
		startReportCall();
    });
 } 
 
 function goListbarcode()
 {
	 maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_QR_LIST_INSERT%>';
		var url = contextPath + "/maEqMstrList.do";
		$.post(url,FormQueryString(maEqMstrListForm)+"&fileName=eqMstrBarcodeEn" , function(_data){
			startReportCall();
	    });
 }

 function startReportCall ()
 {
	 //국내설비 바코드
	 if("<%=user.getCompNo()%>" == "100") reportCall("eqMstrBarcode","eqMstrBarcode", "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'eqMstrBarcodeEn');
	 else if("<%=user.getCompNo()%>" == "140"&&loginUser.plant=='SLP') reportCall('eqMstrBarcode_SLP','eqMstrBarcodeEn', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'eqMstrBarcodeEn');
	 else if("<%=user.getCompNo()%>" == "170") reportCall('eqMstrBarcodeDaewoong','eqMstrBarcodeDaewoong', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'eqMstrBarcodeEn');
	 else if("<%=user.getCompNo()%>" == "190") reportCall('eqMstrBarcodeDkpharm','eqMstrBarcodeDkpharm', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'eqMstrBarcodeEn');
	 else reportCall('eqMstrBarcodeEn','eqMstrBarcodeEn', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'eqMstrBarcodeEn');
 }
 
 /**
   * 삭제
   */
	function goDelete(){
		var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQUIPID'); //Grid, check box column seq, pk column seq
		if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
		
		maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_LIST_DELETE%>';
		var url = contextPath + "/maEqMstrList.do";
		$.post(url,FormQueryString(maEqMstrListForm)+delArray , function(_data){
	    	afterDelete();
	    });
	}
 
	function afterDelete(){
		goClose('maEqMstrDetail');
    	//goSearch();
    	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
    }

 /**
  * 설비유형 선택창 열기
  */
 function openSelectType(){
 	width  = 550;
 	height = 540;
 	sleft = (screen.width - width) / 2;
 	stop = (screen.height - height) / 2;
 	features = "left=" + sleft + ",top=" + stop;
 	
 	var param = "strutsAction=1001";
 	var url =  contextPath + "/maEqCtgTypeSelect.do";
 	
 	openLayerPopup("maEqCtgTypeSelect", param);
 }
 
 /**
 * 설비마스터 복사
 */
 function goCopycreate(){
	 var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'EQUIPID'); //Grid, check box column seq, pk column seq
	 	
 	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	 
 	dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
		if(result){
			setModal('<bean:message key="MESSAGE.MSG0083"/>');
			maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_COPY%>';
			var url = contextPath + "/maEqMstrList.do";
			$.post(url,FormQueryString(maEqMstrListForm)+selArray , function(_data){
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

function setKeyAftercopy(_newId,_pageId)
{
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = _newId;
	goTabPage(_pageId)
	isNew = false;
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrCommonDTO.filterEqLocId"/>
<html:hidden property="maEqMstrCommonDTO.filterEqCtgId"/>
<html:hidden property="maEqMstrCommonDTO.filterPlfTypeId"/>
<html:hidden property="maEqMstrCommonDTO.filterDeptId"/>
<html:hidden property="maEqMstrCommonDTO.filterMainMngId"/>
<html:hidden property="maEqMstrCommonDTO.filterEqGradeId"/>
<html:hidden property="maEqMstrCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maEqMstrCommonDTO.filterSubMngId"/>
<html:hidden property="maEqMstrCommonDTO.filterPlantId"/>
<html:hidden property="maEqMstrCommonDTO.eqStatusId"/>
<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId"/>
<html:hidden property="maEqMstrCommonDTO.filterIsLastVersionId"/>
<html:hidden property="maEqMstrCommonDTO.filterAccAssetId"/>
<html:hidden property="maEqMstrCommonDTO.filterUsageDeptId"/>
<html:hidden property="maEqMstrCommonDTO.eqCtgType"/>
<html:hidden property="maEqMstrCommonDTO.filterWkctrId"/>

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
				<!-- 설비번호 -->
				<div class="field">
					<label><bean:message key="LABEL.equipNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEquipNo" tabindex="5"/>
					</div>
				</div>
				<!-- 설비명 -->
				<div class="field">
					<label><bean:message key="LABEL.equipName"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEquipDesc" tabindex="10"/>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEqLocDesc" tabindex="20"/>
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
						<html:text property="maEqMstrCommonDTO.filterEqCtgDesc" tabindex="30"/>
						<p class="open_spop">
							<a>
							 <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
					<label><bean:message key="LABEL.equipStatus"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.eqStatusDesc" tabindex="35"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
                <div class="field">
                    <label><bean:message key="LABEL.plfType"/></label>
                    <div class="input_box">
                         <html:text property="maEqMstrCommonDTO.filterPlfTypeDesc" tabindex="35"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>  
				<!-- 제형 -->
				<div class="field">
					<label><bean:message key="LABEL.prodShape"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterProdShape" tabindex="35"/>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.mngDept"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterDeptDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 작업그룹  -->
				<div class="field">
					<label><bean:message key="LABEL.wkCtr"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterWkctrDesc" tabindex="52"/>
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
						<html:text property="maEqMstrCommonDTO.filterUsageDeptDesc" tabindex="55"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="maEqMstrCommonDTO.filterMainMngName" tabindex="70"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 제작사 -->
				<div class="field">
					<label><bean:message key="LABEL.maker"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterMaker" tabindex="80"/>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterSubMngName" tabindex="90"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 모델 -->
				<div class="field">
					<label><bean:message key="LABEL.model"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterModelNo" tabindex="100"/>
					</div>
				</div>
				<!-- Old Eq# -->
				<div class="field">
					<label><bean:message key="LABEL.OldEqNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterOldEqNo" tabindex="110"/>
					</div>
				</div>
				<!-- 설비중요도 -->
				<div class="field">
					<label><bean:message key="LABEL.eqGrade"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEqGradeDesc" tabindex="120" />
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
						<html:text property="maEqMstrCommonDTO.filterEqCtgTypeDesc" tabindex="130"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 최신version여부  -->
				<div class="field">
					<label><bean:message key="LABEL.isLastVersion"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterIsLastVersionDesc" tabindex="140"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 문서번호  -->
				<div class="field">
					<label><bean:message key="LABEL.docNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterDocNo" tabindex="150"/>
					</div>
				</div>
				<!-- revision No  -->
				<div class="field">
					<label><bean:message key="LABEL.revNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterRevNo" tabindex="160"/>
					</div>
				</div>
	            <!-- 회계자산  -->
                <div class="field">
                    <label><bean:message key="MENU.ACCASSET"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterAccAssetDesc" tabindex="170"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- Tag No -->
				<div class="field">
					<label><bean:message key="LABEL.tagNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterTagNo" tabindex="241"/>
					</div>
				</div>
				<!-- 생성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.creDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maEqMstrCommonDTO.filterFromCreDate" tabindex="250" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maEqMstrCommonDTO.filterToCreDate" tabindex="260" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maEqMstrCommonDTO.filterPlantDesc" tabindex="280" />
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
			<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
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