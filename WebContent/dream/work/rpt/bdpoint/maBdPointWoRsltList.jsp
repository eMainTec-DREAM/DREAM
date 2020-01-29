<%--===========================================================================
이상점검조치 - 작업결과 목록
author  syyang
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabdpoint.action.MaBdPointWoRsltListAction" %> 
<%@ page import="dream.req.work.action.MaWoReqResListAction" %> 
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 작업결과 -->
<title><bean:message key='TAB.REQWO'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var beforePageId= '';
var myGrid;
var woAc;

function loadPage() 
{
	setForUpdate();
	
	// 기존작업요청
	woAc = new autoC({"maBdPointWoRsltListDTO.multiDesc":"woDesc"});
	woAc.setTable("TAWORKORDER");
	woAc.setAcResultMap({
        "maBdPointWoRsltListDTO.multiKey":"wkorId"
    });
	woAc.setAcConditionMap({
    	"comp_no" : loginUser.compNo
    });
	woAc.setMultiSelect(true);
	woAc.init();
	
	initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.woNgPointResId'].value = "";
        return sortColumn("maBdPointWoRsltList", this, maBdPointWoRsltListForm, "WONGPOINTRESID", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
	
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.woNgPointResId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maBdPointWoRsltListForm.elements['maBdPointCommonDTO.woNgPointId'].value == '') return;
	
	var form = document.maBdPointWoRsltListForm;	
	form.strutsAction.value = '<%=MaBdPointWoRsltListAction.LIST_FIND %>';
	
	var url = contextPath + "/maBdPointWoRsltList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maBdPointWoRsltListForm), "WONGPOINTRESID", "Y");

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
	var form = document.maBdPointWoRsltListForm;
	 
	form.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	
 	goCommonTabPage(form, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}

function goOpen(rowId)
{
	var selectedId=myGrid.getSelectedRowId();
	var wkorId = getValueById(myGrid, rowId,'WKORID');
	var woType = getValueById(myGrid, rowId,'WOTYPE');
	var pmType = getValueById(myGrid, rowId,'PMTYPE');
	var param = getValueById(myGrid, rowId,'PARAM');
	if(typeof woType != "undefined" && wkorId != "")
	{
		maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.woNgPointId'].value = maBdPointWoRsltListForm.elements['maBdPointCommonDTO.woNgPointId'].value;
		maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.woNgPointResId'].value = getValueById(myGrid, selectedId, 'woNgPointResId');
		maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
		maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;
		goTabPage(param);
	}
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;
 	maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
}

/**
 * 생성
 */
function goCreate()
{
	if(""!=beforePageId)
		goClose(beforePageId, this);
	maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
	maBdPointWoRsltListForm.elements['maWoResultMstrDetailDTO.equipId'].value = maBdPointWoRsltListForm.elements['maBdPointDetailDTO.equipId'].value;
	maBdPointWoRsltListForm.elements['maWoResultMstrDetailDTO.equipDesc'].value = maBdPointWoRsltListForm.elements['maBdPointDetailDTO.equipDesc'].value;
	
	openSelectType();
}

 function goCreateAction(pageId)
 {
	maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.woReqResId'].value = "";
	maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.wkorId'].value = "";
	goCommonTabPage(maBdPointWoRsltListForm, '', pageId);
 }
 

 function setAfterSelect(returnArray)
 {
	var woType = returnArray[0];
	var pmType = returnArray[1];
	var param  = returnArray[2];
	maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	maBdPointWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;
	
	beforePageId = param;
	goCommonTabPage(maBdPointWoRsltListForm, '', param);
}
 
 /**
  * 작업종류& 작업형태 선택창 열기
  */
 function openSelectType(){
 	width  = 850;
 	height = 540;
 	sleft = (screen.width - width) / 2;
 	stop = (screen.height - height) / 2;
 	features = "left=" + sleft + ",top=" + stop;
 	
 	var param = "strutsAction=1001";
 	var url =  contextPath + "/maWoResultSelect.do";
 	
 	openLayerPopup("maWoResultSelect", param);
 }

 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woNgPointResId,_type)
 {
	maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.woNgPointResId'].value = _woNgPointResId;
 	findGridList('ReloadRow');
 	maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.woNgPointResId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WONGPOINTRESID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maBdPointWoRsltListForm.strutsAction.value = '<%=MaBdPointWoRsltListAction.LIST_DELETE%>';
	var url = contextPath + "/maBdPointWoRsltList.do";
	
	$.post(url,FormQueryString(maBdPointWoRsltListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose(beforePageId, this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
   
/**
 * 신규작업 생성버튼
 */
function goWocreate()
{
	goCreate();
}

function afterCreate(keyId)
{
	// 생성한 작업결과를 TAWONGPOINTRES에 넣어준다. 
	// 기존작업선택과 같은 프로세스.
	var _arrayData = [];
	_arrayData.push(keyId);
	
	setLovValue(_arrayData, '');
}

function setLovValue(_arrayData, _type)
{
	var wkorId = _arrayData[0];
	
	maBdPointWoRsltListForm.strutsAction.value = '<%=MaBdPointWoRsltListAction.LIST_WO_INPUT %>';
	maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.wkorId'].value = wkorId;
	
	var url = contextPath + "/maBdPointWoRsltList.do";
	
	$.post(url,FormQueryString(maBdPointWoRsltListForm), function(_data){
		goSearch();
	 	
		<%-- goCommonTabPage(maBdPointWoRsltListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId, beforePageId); --%>
	});
}

/**
 * 기존작업 연결버튼
 */
function goWoselect()
{
	if(""!=beforePageId)
		goClose(beforePageId, this);
	
	woAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	maBdPointWoRsltListForm.strutsAction.value = '<%=MaBdPointWoRsltListAction.LIST_WO_LINK %>';

	goSaveAll();
}

function goSave(){
	var url = contextPath + "/maBdPointWoRsltList.do";
	
    $.post(url,FormQueryString(maBdPointWoRsltListForm), function(_data){
    	afterSave(_data);
    });
}
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    maBdPointWoRsltListForm.elements['maBdPointWoRsltListDTO.woNgPointResId'].value = "";
    
    goSearch();

    if(typeof searchPage("maBdPointDetail").checkStatus == "function" && parent.currentPageId == "maBdPointWoRsltList"){
		searchPage("maBdPointDetail").checkStatus();	
	}
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	maBdPointWoRsltListForm.elements['maBdPointCommonDTO.woNgPointId'].value = "";
	excelServerAction("maBdPointWoRsltList", maBdPointWoRsltListForm);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maBdPointWoRsltListForm.elements['maBdPointCommonDTO.woNgPointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maBdPointWoRsltList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maBdPointCommonDTO.woNgPointId" />	<!-- Key -->
<html:hidden property="maBdPointDetailDTO.equipId" />
<html:hidden property="maBdPointDetailDTO.equipDesc" />
<html:hidden property="maBdPointWoRsltListDTO.woNgPointResId"/>
<html:hidden property="maBdPointWoRsltListDTO.woReqId"/>
<html:hidden property="maBdPointWoRsltListDTO.woReqResId"/>
<html:hidden property="maBdPointWoRsltListDTO.wkorId"/>
<html:hidden property="maBdPointWoRsltListDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maBdPointWoRsltListDTO.multiDesc"/><!-- MultiSelect Desc -->

<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>
<html:hidden property="maWoResultMstrCommonDTO.woNgPointId" />
<html:hidden property="maWoResultMstrCommonDTO.woNgPointResId" />
<html:hidden property="maWoResultMstrDetailDTO.equipId" />
<html:hidden property="maWoResultMstrDetailDTO.equipDesc" />
	
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>