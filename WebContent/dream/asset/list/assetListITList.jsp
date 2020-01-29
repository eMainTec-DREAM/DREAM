<%--===========================================================================
IT장비목록 List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction"%>
<%@ page import="dream.comm.revision.action.CommRevAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.MwareConfig"%>
<%
	String isUseAssetRevision = MwareConfig.getIsUseAssetRevision();
%>
<html>
<head>
<!-- IT장비목록 -->
<title><bean:message key='MENU.EQIT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var selectedEqId='';

var deptAc, mainMngAc, userAc, plantAc;
var usageDeptAc;

//생성 타입
var createType;

var isNew = false;

function loadPage() 
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.eqCtgType'].value = "IT";
  	//공장명
    if(loginUser.filterPlant!='null'){
    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
  	
    initGrid();
    
    //설비상태 
    acSysDesc("maEqMstrCommonDTO.eqStatusDesc","maEqMstrCommonDTO.eqStatusId","EQ_STATUS");
    
    //관리부서
    deptAc = new autoC({"maEqMstrCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcDConditionMap({
    	"comp_no": loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "maEqMstrCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });
    deptAc.init();
    
	// 사용부서
    usageDeptAc = new autoC({"maEqMstrCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "maEqMstrCommonDTO.filterUsageDeptId":"dept_id",
    });
    usageDeptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	}); 
    usageDeptAc.init();
    
    //관리자(정)
    mainMngAc = new autoC({"maEqMstrCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no": loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maEqMstrCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrCommonDTO.filterDeptId",
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    //사용자
    userAc = new autoC({"maEqMstrCommonDTO.filterUserName":"emp_name"});
    userAc.setAcConditionMap({
    	"comp_no": loginUser.compNo
  	});
    userAc.setTable("TAEMP");
    userAc.setAcResultMap({
        "maEqMstrCommonDTO.filterUserId":"emp_id"
    });
    userAc.setAcDConditionMap({
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });
    userAc.init();
    
    //최신Version여부
    acSysDesc("maEqMstrCommonDTO.filterIsLastVersionDesc","maEqMstrCommonDTO.filterIsLastVersionId","IS_USE");
    
    //공장 
    plantAc = new autoC({"maEqMstrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcDConditionMap({
    	"comp_no": loginUser.compNo
	});
    plantAc.setAcResultMap({
        "maEqMstrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();

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
    	return sortColumn("maEqMstrList", this, maEqMstrListForm, "ITEQID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/assetListITList.do";
    maEqMstrListForm.elements['strutsAction'].value = '<%=MaEqMstrListAction.EQ_IT_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrListForm), "ITEQID","Y");

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

function goOpenAfterRev(regRowId){
	 selectedEqId = regRowId;
	 if(regRowId != 'null' || regRowId != 'undefinded'|| regRowId != ''){
		 goOpen(regRowId);
	 }
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
	if(!isNew){
		maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value =  getValueById(myGrid, selectedId,'ITEQID');
	}
    maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value='IT'
	goCommonTabPage(maEqMstrListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, pageId);
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_equipId)
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = _equipId;
	findGridList('ReloadRow');
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
}

function goSearch()
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";	// 검색시 Tab 이동Key Clear
	findGridList('Search');
}


/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetListITDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'ITEQID');
	maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = 'IT';
	maEqMstrListForm.elements[''].value = '<%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(maEqMstrListForm), 'assetListITDetail'); 
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetListITDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
	maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = "IT";
	
	//제개정 사용여부
	if("<%=isUseAssetRevision%>"=="N"){
		goCommonTabPage(maEqMstrListForm, '', pageId);
	}else{
		//제정화면 팝업
		var param = "strutsAction=1001";
		param += "&" + "popupWidth=800";
		param += "&" + "commRevCommonDTO.eqCtgTypeId="+maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value;
		param += "&" + "commRevCommonDTO.revisionObjType="+"ASSET";
		param += "&" + "commRevCommonDTO.param="+pageId;
		
		openLayerPopup("commRevRegislate", param);
	}
}


function afterCreate(_equipId, pageId)
{
	findGridRow(_equipId);
	 
	var form = document.maEqMstrListForm;

	form.elements['maEqMstrCommonDTO.equipId'].value = _equipId;
	goCommonTabPage(form, '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>', pageId);
}
 

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ITEqID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_LIST_DELETE%>';
    var url = contextPath + "/assetListITList.do";
    
    $.post(url,FormQueryString(maEqMstrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetListITDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
	excelServerAction("maEqMstrList", maEqMstrListForm );
// 	excelAction(myGrid);
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


//::::::::::::::::::::::::::::::::: SVN Branch 테스트 ::::::::::::::::::::::::::::::::::::::::::
	
	
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetListITList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrCommonDTO.eqStatusId"/>
<html:hidden property="maEqMstrCommonDTO.filterDeptId"/>
<html:hidden property="maEqMstrCommonDTO.filterMainMngId"/>
<html:hidden property="maEqMstrCommonDTO.filterUserId"/>
<html:hidden property="maEqMstrCommonDTO.filterIsLastVersionId"/>
<html:hidden property="maEqMstrCommonDTO.filterPlantId"/>
<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId"/>
<html:hidden property="maEqMstrCommonDTO.eqCtgType"/>
<html:hidden property="maEqMstrCommonDTO.filterUsageDeptId"/>
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
				<!-- 장비번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.ITEqNo"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterEquipNo" tabindex="5"/>
                    </div>
                </div>
                <!-- 설비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.ITEqName"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterEquipDesc" tabindex="10"/>
                    </div>
                </div>
                <!-- 상태 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipStatus"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.eqStatusDesc" tabindex="20"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 관리부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.manageDept"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterDeptDesc" tabindex="30"/>
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
                <!-- 모델 -->
                <div class="field">
                    <label><bean:message key="LABEL.model"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterModelNo" tabindex="40"/>
                    </div>
                </div>
                <!-- 관리자(정) -->
                <div class="field">
                    <label><bean:message key="LABEL.mainManager"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterMainMngName" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 사용자 -->
                <div class="field">
                    <label><bean:message key="LABEL.user"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterUserName" tabindex="60"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- Serial 번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.serialNo"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterSerialNo" tabindex="70"/>
                    </div>
                </div>
                <!-- 구입일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.buyDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="maEqMstrCommonDTO.filterStartBuyDate" tabindex="80" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="maEqMstrCommonDTO.filterEndBuyDate" tabindex="90" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 사양 -->
                <div class="field">
                    <label><bean:message key="LABEL.Specification"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterSpecification" tabindex="100"/>
                    </div>
                </div>
                <!-- 최신version여부  -->
				<div class="field">
					<label><bean:message key="LABEL.isLastVersion"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterIsLastVersionDesc" tabindex="110"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- revision No  -->
				<div class="field">
					<label><bean:message key="LABEL.revNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterRevNo" tabindex="120"/>
					</div>
				</div>
				<!-- Tag No -->
				<div class="field">
					<label><bean:message key="LABEL.tagNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterTagNo" tabindex="241"/>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maEqMstrCommonDTO.filterPlantDesc" tabindex="130" />
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