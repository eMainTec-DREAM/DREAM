<%--===========================================================================
 이상점검조치 - 작업요청 목록
author  syyang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabdpoint.action.MaBdPointWoReqListAction" %>
<%@ page import="dream.req.work.action.ReqWorkDetailAction" %>
<%@ page import="dream.req.work.action.MaWoReqTypeSelectAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 작업요청 -->
<title><bean:message key='TAB.WOREQLIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//
var beforePageId = '';
var myGrid;
var woReqAc;

function loadPage()
{
	setForUpdate();
	
	// 기존작업요청
	woReqAc = new autoC({"maBdPointWoReqListDTO.multiDesc":"woReqDesc"});
	woReqAc.setTable("TAWOREQ");
	woReqAc.setAcResultMap({
        "maBdPointWoReqListDTO.multiKey":"woreqid"
    });
	woReqAc.setAcConditionMap({
    	"comp_no" : loginUser.compNo
    });
	woReqAc.setMultiSelect(true);
	woReqAc.init();
	
    initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woNgPointResId'].value = "";
        return sortColumn("maBdPointWoReqList", this, maBdPointWoReqListForm, "WONGPOINTRESID", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maBdPointWoReqListForm.elements['maBdPointCommonDTO.woNgPointId'].value == '') return;

    var url = contextPath + "/maBdPointWoReqList.do";

    maBdPointWoReqListForm.elements['strutsAction'].value = '<%=MaBdPointWoReqListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maBdPointWoReqListForm), "WONGPOINTRESID", "Y");

}

/**
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woNgPointResId)
{
	maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woNgPointResId'].value = _woNgPointResId;
	findGridList('ReloadRow');
	maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woNgPointResId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woNgPointResId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.maBdPointWoReqListForm;

	form.elements['reqWorkCommonDTO.woReqId'].value = getValueById(myGrid, selectedId, 'WOREQID');
 	goCommonTabPage(form, <%= ReqWorkDetailAction.DETAIL_INIT %>, pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
function goOpen(rowId)
{
	var param  = getValueById(myGrid, rowId,'PARAM');
	maBdPointWoReqListForm.elements['reqWorkCommonDTO.selectedWoReqTypeId'].value = getValueById(myGrid, rowId,'WOREQTYPE');
	// 투자요청 만들어지기 전, 기존에 param값 안들어가 있는 데이터들은 수리요청
	if (param == null || param == '') {
		goTabPage('reqWorkDetail');
	} else {
		goTabPage(param);
	}
}
function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maBdPointWoReqListForm.elements['reqWorkCommonDTO.woReqId'].value = getValueById(myGrid, selectedId, 'WOREQID');
    maBdPointWoReqListForm.elements['strutsAction'].value = '<%=ReqWorkDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maBdPointWoReqListForm), 'reqWorkDetail');
} 


/**
 * 생성
 */
function goCreate()
{
	var woReqTypeArr = getSysCode("WOREQ_TYPE");
	
	alert(woReqTypeArr);
	
 	goClose(beforePageId, this);
 	maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woReqId'].value = ""; 
	maBdPointWoReqListForm.elements['reqWorkDetailDTO.reqEquipId'].value = maBdPointWoReqListForm.elements['maBdPointDetailDTO.equipId'].value;
	maBdPointWoReqListForm.elements['reqWorkDetailDTO.reqEquipDesc'].value = maBdPointWoReqListForm.elements['maBdPointDetailDTO.equipDesc'].value;
	
	// WOREQ_TYPE의 is_use값이 Y인게 2 이상일 때만 선택창
	if (woReqTypeArr.length >= 2) {
		openSelectType();
	} else {
		createValidationCheck(myGrid, woReqTypeArr[0].PARAM1 , "goCreateAction");
	}
	
}

/**
 * 작업요청유형 선택창 열기
 */
function openSelectType(){

	width  = 550;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=" + "<%=MaWoReqTypeSelectAction.WOREQTYPE_SELECT_DEFAULT%>";

	openLayerPopup("maWoReqTypeSelect", param);
}
function setAfterSelect(returnArray){
	var woReqType = returnArray[0];
	var param2  = returnArray[1];
	maBdPointWoReqListForm.elements['reqWorkCommonDTO.selectedWoReqTypeId'].value = woReqType;
	beforePageId = param2;
	goCommonTabPage(maBdPointWoReqListForm, '', param2);
}

function goCreateAction(pageId)
{
	maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woReqId'].value = "";
	goCommonTabPage(maBdPointWoReqListForm, '', pageId);
}

function goWoreqcreate()
{
	goCreate();
}

function afterCreate(keyId)
{
	// 생성한 작업계획을 TAWONGPOINTRES에 넣어준다. 
	// 기존계획선택과 같은 프로세스.
	var _arrayData = [];
	_arrayData.push(keyId);
	
	setLovValue(_arrayData, '');
}

function setLovValue(_arrayData, _type)
{
	var woReqId = _arrayData;

	maBdPointWoReqListForm.strutsAction.value = '<%=MaBdPointWoReqListAction.LIST_WOREQ_INPUT %>';
	maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woReqId'].value = woReqId;
	
	var url = contextPath + "/maBdPointWoReqList.do";
	
	$.post(url,FormQueryString(maBdPointWoReqListForm), function(_data){
		goSearch();	
	});
}


function goWoreqselect()
{
	if(""!=beforePageId)
		goClose(beforePageId, this);
	
	woReqAc.openLov();
}
function setAcLovValue(rtnArr, code)
{
	maBdPointWoReqListForm.strutsAction.value = '<%=MaBdPointWoReqListAction.LIST_WOREQ_LINK%>';

	goSaveAll();
}

function goSave(){
	var url = contextPath + "/maBdPointWoReqList.do";
	
    $.post(url,FormQueryString(maBdPointWoReqListForm), function(_data){
    	afterSave(_data);
    });
}
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woNgPointResId'].value = "";
  
    if(parent.parent.findGridRow){
    	parent.parent.findGridRow(maBdPointWoReqListForm.elements['maBdPointCommonDTO.woNgPointId'].value);
    }
    if(parent.parent.goTabPage){
    	parent.parent.goTabPage(parent.currentPageId);
    }
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

	maBdPointWoReqListForm.strutsAction.value = '<%=MaBdPointWoReqListAction.LIST_DELETE%>';
	var url = contextPath + "/maBdPointWoReqList.do";
	$.post(url,FormQueryString(maBdPointWoReqListForm)+delArray , function(_data){
    	afterDelete();
    });
}
function afterDelete(){
	goClose(beforePageId, this);
 	//goSearch();
 	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }

/**
 * Excel Export
 */
function goExcel()
{
	maBdPointWoReqListForm.elements['maBdPointWoReqListDTO.woNgPointResId'].value = "";
    excelServerAction(currentPageId, maBdPointWoReqListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maBdPointWoReqList.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maBdPointCommonDTO.woNgPointId" />	<!-- Key -->
	<html:hidden property="maBdPointDetailDTO.equipId" />
	<html:hidden property="maBdPointDetailDTO.equipDesc" />
	<html:hidden property="maBdPointWoReqListDTO.woNgPointResId"/>
	<html:hidden property="maBdPointWoReqListDTO.woReqId"/>
	<html:hidden property="maBdPointWoReqListDTO.woReqDesc"/>
	<html:hidden property="maBdPointWoReqListDTO.multiKey"/><!-- MultiSelect Key -->
	<html:hidden property="maBdPointWoReqListDTO.multiDesc"/><!-- MultiSelect Desc -->

	<html:hidden property="reqWorkCommonDTO.selectedWoReqTypeId"/>
	<html:hidden property="reqWorkCommonDTO.woReqId"/>
	<html:hidden property="reqWorkDetailDTO.reqEquipId"/>
	<html:hidden property="reqWorkDetailDTO.reqEquipDesc"/>
	<!-- searchbox 박스 Line -->
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