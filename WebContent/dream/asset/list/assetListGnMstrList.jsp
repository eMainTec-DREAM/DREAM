<%--===========================================================================
일반자산목록 List
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction"%>
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
<!-- 일반자산목록 -->
<title><bean:message key='MENU.GNMSTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var selectedEqId='';

/** 자동완성 변수 */
var eqCtgTypeAc;
var manageDeptAc;
var useDept;
var eqLocDescAc;
var plantAc;

//생성 타입
var createType;

var isNew = false;

function loadPage() 
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.eqCtgType'].value = "GN";
	
 	 //공장명
    if(loginUser.filterPlant!='null'){
    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
  	
  	//위치
    if(loginUser.eqLocId!='null'){
        maEqMstrListForm.elements['maEqMstrCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
        maEqMstrListForm.elements['maEqMstrCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
    }
  	//관리부서
    if(loginUser.filterDeptId!='null'){
    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
    	maEqMstrListForm.elements['maEqMstrCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	}
  	
    initGrid();
    
    // 장비종류
    eqCtgTypeAc = new autoC({"maEqMstrCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    eqCtgTypeAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    //장비상태 
    acSysDesc("maEqMstrCommonDTO.eqStatusDesc","maEqMstrCommonDTO.eqStatusId","EQ_STATUS");
    
    //관리부서
    manageDeptAc = new autoC({"maEqMstrCommonDTO.filterDeptDesc":"description"});
    manageDeptAc.setTable("TADEPT");
    manageDeptAc.setAcDConditionMap({
    	"comp_no": loginUser.compNo
  	   });
    manageDeptAc.setAcResultMap({
        "maEqMstrCommonDTO.filterDeptId":"dept_id"
    });
    manageDeptAc.setAcDConditionMap({
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });
    manageDeptAc.init();
    
    //사용부서
    useDept = new autoC({"maEqMstrCommonDTO.filterUsageDeptDesc":"description"});
    useDept.setTable("TADEPT");
    useDept.setAcDConditionMap({
    	"comp_no": loginUser.compNo
  	   });
    useDept.setAcResultMap({
        "maEqMstrCommonDTO.filterUsageDeptId":"dept_id"
    });
    useDept.setAcDConditionMap({
    	"plant" : "maEqMstrCommonDTO.filterPlantId"
    });
    useDept.init();
    
    eqLocDescAc = new autoC({"maEqMstrCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqLocDescAc.setAcResultMap({
        "maEqMstrCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
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
    
    //최신Version여부
    acSysDesc("maEqMstrCommonDTO.filterIsLastVersionDesc","maEqMstrCommonDTO.filterIsLastVersionId","IS_USE");

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
    var url = contextPath + "/assetListGnMstrList.do";
    maEqMstrListForm.elements['strutsAction'].value = '<%=MaEqMstrListAction.EQ_GN_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrListForm), "EQUIPID","Y");

}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
	excelServerAction("maEqMachMstrList", maEqMstrListForm ); 

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";  // 검색시 Tab 이동Key Clear
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

function goTabPageAction(pageId,selectedId)
{
    if(!isNew){
    	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value =getValueById(myGrid, selectedId,'EQUIPID');
    }
    maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value='GN';
    goCommonTabPage(maEqMstrListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetListGnMstrDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
	maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = "GN";
	maEqMstrListForm.elements[''].value = '<%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(maEqMstrListForm), 'assetListGnMstrDetail'); 
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetListGnMstrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value = "";
	maEqMstrListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = "GN";
	
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
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'EQUIPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_LIST_DELETE%>';
    var url = contextPath + "/assetListGnMstrList.do";
    
    $.post(url,FormQueryString(maEqMstrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetListGnMstrDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
   var url = contextPath + "/assetListGnMstrList.do";
   $.post(url,FormQueryString(maEqMstrListForm)+selArray+"&fileName=eqMstrBarcodeEn" , function(_data){
       startReportCall();
   });
} 

function goListbarcode()
{
    maEqMstrListForm.strutsAction.value = '<%=MaEqMstrListAction.EQ_MSTR_QR_LIST_INSERT%>';
       var url = contextPath + "/assetListGnMstrList.do";
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
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetListGnMstrList.do">
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
<html:hidden property="maEqMstrCommonDTO.filterEqLocId"/>
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
			    <!-- 장비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.eqDesc"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterEquipDesc" tabindex="10"/>
                    </div>
                </div>
				<!-- 장비번호 -->
                <div class="field">
                    <label><bean:message key="LABEL.eqNo"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterEquipNo" tabindex="20"/>
                    </div>
                </div>
				<!-- 장비종류 -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtgName"/></label>
					<div class="input_box">
						<html:text property="maEqMstrCommonDTO.filterEqCtgDesc" tabindex="30"/>
						<p class="open_spop">
							<a>
							 <span>조회</span>
							</a>
						</p>
					</div>
				</div>
                <!-- 장비상태 -->
                <div class="field">
                    <label><bean:message key="LABEL.eqStatus"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.eqStatusDesc" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
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
                <!-- 사용부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.usedDept"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterUsageDeptDesc" tabindex="60"/>
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
						<html:text property="maEqMstrCommonDTO.filterMaker" tabindex="70"/>
					</div>
				</div>
                <!-- 모델 -->
                <div class="field">
                    <label><bean:message key="LABEL.model"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterModelNo" tabindex="80"/>
                    </div>
                </div>
                <!-- 위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.eqLocName"/></label>
                    <div class="input_box">
                        <html:text property="maEqMstrCommonDTO.filterEqLocDesc" tabindex="90"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maEqMstrCommonDTO.filterPlantDesc" tabindex="100" />
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