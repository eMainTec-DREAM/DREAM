<%--===========================================================================
설비마스터 - 목록
author  kim21017
version $Id: maEqMstrMoldList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrListAction"%>
<%@ page import="dream.asset.list.action.MaEqMstrMoldDetailAction"%>
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
<title><bean:message key="MENU.EQMOLDMSTR"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var selectedEqId;
/** 자동완성 변수 */
var eqLocAc;
var eqCtgTypeAc;
var deptAc;
var mainMngAc;
var subMngAc;
var assetAc;
//생성 타입
var createType;
var wkctrAc;
var eqMoldAtypeAc;
var eqMoldBtypeAc;
var eqMoldCtypeAc;

var isNew = false;

function loadPage() 
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.eqCtgType'].value = "MD";
	if(window.name=="LOCLIST_EQ_POPUP"||window.name=="DEPTLIST_EQ_POPUP"){
	}else{
		//부서
		maEqMstrListForm.elements['maEqMstrCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		maEqMstrListForm.elements['maEqMstrCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	}
	
    initGrid();

    maEqMstrListForm.elements['maEqMstrCommonDTO.filterIsLastVersionId'].value = 'Y';
    maEqMstrListForm.elements['maEqMstrCommonDTO.filterIsLastVersionDesc'].value = 'Y';
	valSysDir('maEqMstrCommonDTO.filterIsLastVersionId', 'maEqMstrCommonDTO.filterIsLastVersionDesc', 'IS_USE', true);
	
    //설비위치 자동완성
    eqLocAc = new autoC({"maEqMstrCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.init();
    
    //설비종류
    eqCtgTypeAc = new autoC({"maEqMstrCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgTypeAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    //설비상태
    acSysDesc("maEqMstrCommonDTO.eqStatusDesc","maEqMstrCommonDTO.eqStatusId","EQ_STATUS");
    //관리부서
    
    deptAc = new autoC({"maEqMstrCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maEqMstrCommonDTO.filterDeptId":"dept_id",
    });
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 

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
    usageDeptAc.init();
  	//--------------------------관리자(정)----------------------------------//
    mainMngAc = new autoC({"maEqMstrCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maEqMstrCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrCommonDTO.filterDeptId"
    });
    mainMngAc.init();
    //--------------------------관리자(부)--------------------------------------//
    subMngAc = new autoC({"maEqMstrCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maEqMstrCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrCommonDTO.filterDeptId"
    });
   
    subMngAc.init();
    
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
	
	//금형구분
	eqMoldAtypeAc = new autoC({"maEqMstrCommonDTO.filterEqMoldAtypeDesc":"description"});
	eqMoldAtypeAc.setTable("TACDUSRD");
	eqMoldAtypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
        ,"dir_type":"EQMOLD_ATYPE"
    });
	eqMoldAtypeAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqMoldAtype":"cdusrd_no"
    });
	eqMoldAtypeAc.init();
	
	//금형구분
	eqMoldBtypeAc = new autoC({"maEqMstrCommonDTO.filterEqMoldBtypeDesc":"description"});
	eqMoldBtypeAc.setTable("TACDUSRD");
	eqMoldBtypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
        ,"dir_type":"EQMOLD_BTYPE"
    });
	eqMoldBtypeAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqMoldBtype":"cdusrd_no"
    });
	eqMoldBtypeAc.init();
	
	//금형구분
	eqMoldCtypeAc = new autoC({"maEqMstrCommonDTO.filterEqMoldCtypeDesc":"description"});
	eqMoldCtypeAc.setTable("TACDUSRD");
	eqMoldCtypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
        ,"dir_type":"EQMOLD_CTYPE"
    });
	eqMoldCtypeAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqMoldCtype":"cdusrd_no"
    });
	eqMoldCtypeAc.init();
	
    acSysDesc("maEqMstrCommonDTO.filterIsLastVersionDesc","maEqMstrCommonDTO.filterIsLastVersionId","IS_USE");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500); 
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedEqId=rowId;
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
	var url = contextPath + "/maEqMstrMoldList.do";

    maEqMstrListForm.elements['strutsAction'].value = '<%=MaEqMstrListAction.EQ_MOLD_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrListForm), "EQUIPID","Y");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
	excelServerAction("maEqMstrMoldList", maEqMstrListForm);
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
	goCommonTabPage(form, <%= MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_INIT %>, pageId);
	
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrMoldDetail');
}
 
 /**
 *
 * 상세열기 버튼
 */
function goOpenAction()
{
   var selectedId=myGrid.getSelectedRowId();
   if(selectedId == null) return;
   
   var form = document.maEqMstrListForm;
	 
	form.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
   form.elements['strutsAction'].value = '<%=MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_INIT%>';
   openQuickTabPage(FormQueryString(form), 'maEqMstrMoldDetail'); 
}  
 
  /**
   * 생성
   */
 function goCreate()
 {
 	createValidationCheck(myGrid, "maEqMstrMoldDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
	maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = 'MD';
	
	//제개정 사용여부
	if("<%=isUseAssetRevision%>"=="N"){
		goCommonTabPage(maEqMstrListForm, '', pageId);
	}else{
		var param = "strutsAction=&commRevCommonDTO.eqCtgTypeId="+maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value;
		param += "&" + "commRevCommonDTO.revisionObjType="+"ASSET";
	 	openLayerPopup("commRevRegislate", param);
	}
 }

 function afterCreate(_equipId, pageId)
 {
 	findGridRow(_equipId);
 	 
 	var form = document.maEqMstrListForm;

 	form.elements['maEqMstrCommonDTO.equipId'].value = _equipId;
 	goCommonTabPage(form, '<%=MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_INIT%>', pageId);
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
    var url = contextPath + "/maEqMstrMoldList.do";
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
	 else if("<%=user.getCompNo()%>" == "190") reportCall('eqMstrBarcodeDkpharm','eqMstrBarcodeDkpharm', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'eqMstrBarcodeEn');
	 else reportCall('eqMstrBarcodeEn','eqMstrBarcodeEn', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'eqMstrBarcodeEn');
}
 
/**
 * 삭제
*/
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQUIPID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;
	
	maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrMoldList.do";
	$.post(url,FormQueryString(maEqMstrListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maEqMstrMoldDetail');
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
	    
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrMoldList.do">

<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrCommonDTO.filterEqLocId"/>
<html:hidden property="maEqMstrCommonDTO.filterEqCtgId"/>
<html:hidden property="maEqMstrCommonDTO.filterPlfTypeId"/>
<html:hidden property="maEqMstrCommonDTO.filterDeptId"/>
<html:hidden property="maEqMstrCommonDTO.filterMainMngId"/>
<html:hidden property="maEqMstrCommonDTO.filterSubMngId"/>
<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId"/>
<html:hidden property="maEqMstrCommonDTO.eqStatusId"/>
<html:hidden property="maEqMstrCommonDTO.filterIsLastVersionId"/>
<html:hidden property="maEqMstrCommonDTO.eqCtgType"/>
<html:hidden property="maEqMstrCommonDTO.filterAccAssetId"/>
<html:hidden property="maEqMstrCommonDTO.filterUsageDeptId"/>
<html:hidden property="maEqMstrCommonDTO.filterWkctrId" /> <!-- 작업그룹 ID -->
<html:hidden property="maEqMstrCommonDTO.filterEqMoldAtype" />
<html:hidden property="maEqMstrCommonDTO.filterEqMoldBtype" />
<html:hidden property="maEqMstrCommonDTO.filterEqMoldCtype" />

<html:hidden property="strutsAction"/>
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
						<p class="open_spop"><a><span>조회</span></a></p>
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
				<!-- 관리자(정) -->
				<div class="field">
					<label><bean:message key="LABEL.mainManager"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterMainMngName" tabindex="70"/>
						<p class="open_spop"><a><span>조회</span></a></p>
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
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 모델 -->
				<div class="field">
					<label><bean:message key="LABEL.model"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterModelNo" tabindex="100"/>
					</div>
				</div>
				<!-- 기존금형번호 -->
				<div class="field">
					<label><bean:message key="LABEL.oldMoldNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterOldEqNo" tabindex="110"/>
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
				<!-- 작업그룹  -->
				<div class="field">
					<label><bean:message key="LABEL.wkCtr"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterWkctrDesc" tabindex="130"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div> 			
				<!-- 금형구분 -->
				<div class="field">
					<label><bean:message key="LABEL.eqMoldAtype"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEqMoldAtypeDesc" tabindex="180"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>	
				<!-- 금형구분 -->
				<div class="field">
					<label><bean:message key="LABEL.eqMoldBtype"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEqMoldBtypeDesc" tabindex="190"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>	
				<!-- 금형구분 -->
				<div class="field">
					<label><bean:message key="LABEL.eqMoldCtype"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEqMoldCtypeDesc" tabindex="200"/>
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