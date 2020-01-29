<%--===========================================================================
구매신청 item  목록
author  kim21017
version $Id: maPtBuyReqList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.part.pur.buy.action.MaPtBuyReqListAction" %>
<%@ page import="dream.part.pur.buy.action.MaPtBuyReqDetailAction" %>
<%@ page import="dream.part.pur.buy.action.MaPtBuyReqHdrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<title><bean:message key='LABEL.buyReq'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var partAc;
var ptPnAc;
var ctCtrAc, purGroupAc, accntTypeAc, accntAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	partAc = new autoC({"maPtBuyReqDetailDTO.multiDesc":"full_desc"});
	partAc.setTable("TAPARTS");
	partAc.setAcResultMap({
        "maPtBuyReqDetailDTO.multiKey":"part_id"
    });
	partAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
    });
	partAc.setMultiSelect(true);
	partAc.init();
	
    ptPnAc = new autoC({"maPtBuyReqDetailDTO.ptPnListIds":"ptPnListId"});
    ptPnAc.setTable("TAPTPNLIST");
    ptPnAc.setAcResultMap({
        "maPtBuyReqDetailDTO.ptPnListIds":"ptPnListId"
    });
    ptPnAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"plant":parent.M$("maPtBuyReqHdrDetailDTO.plantId").value
    	,"plant_desc":parent.M$("maPtBuyReqHdrDetailDTO.plantDesc").value
  	});
    ptPnAc.setMultiSelect(true);
    ptPnAc.init();
    
    hideBtn("SAVE");
	hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maPtBuyReqDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value = "";
        return sortColumn("maPtBuyReqList", this, maPtBuyReqListForm, "PTPRITEMID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/* Edit 기능 추가 */
/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("CREATE");
	hideBtn("REGBATCH");
	hideBtn("PNSELECT");
	hideBtn("OPEN");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("maPtBuyReqDetail", this);

    //set for update mark.
//     setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    //showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/maPtBuyReqList.do";
	var stAct = "<%=MaPtBuyReqListAction.EDIT_LIST_SAVE %>";

	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave");
}

function goSave()
{
	if(maPtBuyReqListForm.strutsAction.value == '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_INPUT%>'
		|| maPtBuyReqListForm.strutsAction.value == '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_GOT_INPUT%>')
	{
		var url = contextPath + "/maPtBuyReqList.do";
		
	    $.post(url,FormQueryString(maPtBuyReqListForm), function(_data){
	    	afterSave(_data);
	    });
	}
	else
	{
		//Send All Data ONce
		proGrid.sendData();
	}
}

/**
 * 수정모드 취소 
 */
function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
}

/**
 * After Edit
 */
function afterSave(ajaxXmlDoc)
{
	if(maPtBuyReqListForm.strutsAction.value == '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_INPUT%>'
		|| maPtBuyReqListForm.strutsAction.value == '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_GOT_INPUT%>')
	{
		//=====================================
	    if (!checkHttpXml(ajaxXmlDoc)) return;
	    //=====================================
	    getTopPage().afterSaveAll(currentPageId);
	    
	    goSearch();
	}
	else
	{
		afterEditRow(myGrid);
		
		//Control Button
		hideBtn("SAVE");
		//hideBtn("ADD");
		//hideBtn("DEL");
		showBtn("EDIT");
		hideBtn("EDITCNCL");
		
		showBtn("CREATE");
		showBtn("REGBATCH");
		showBtn("PNSELECT");
		showBtn("OPEN");
		showBtn("DELETE");
		showBtn("EXCEL");
		showBtn("SETTING");
		
		//attach Event to open detail page
		//addRowSelEvent();

		//Clear Key Value
		maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value = "";
		
		//Search
		goSearch();
		
		//Clear Update Mark for this page 
		clearUpdate(currentPageId);
		
		//Make Page as Normal
// 	 	setForNormal();
	}
	
}

/**
 * AC Setting, This is called when the column is changed to edit mode.
 */
function setGridAc(_gridObj, _cellObj)
{
	//Column Id별로 event 할당
	
	// 코스트센터
    ctCtrAc = new autoC({"CTCTRDESC":"description"});
    ctCtrAc.setTable("TACTCTR");
    ctCtrAc.setCol(_cellObj);
    ctCtrAc.setGrid(_gridObj);
    ctCtrAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   ,"is_use":"Y"
  	});
    ctCtrAc.setAcResultMap({
        "CTCTRID":"ctctr_id"
    });
    ctCtrAc.setKeyName("CTCTRID");
    ctCtrAc.init();
    
	// 예산계정
    accntAc = new autoC({"ACCNTDESC":"description"});
    accntAc.setTable("TAACCOUNT");
    accntAc.setCol(_cellObj);
    accntAc.setGrid(_gridObj);
    accntAc.setAcConditionMap({
  	   "is_use":"Y"
  	});
    accntAc.setAcDConditionMap({
  	   "accnt_type":"ACCNTTYPE"
  	});
    accntAc.setAcResultMap({
        "ACCNTID":"accnt_id"
    });
    accntAc.setKeyName("ACCNTID");
    accntAc.init();
    
    /** 구매그룹 */
    purGroupAc = new autoC({"PURGROUPDESC":"description"});
    purGroupAc.setTable("TACDUSRD");
    purGroupAc.setCol(_cellObj);
    purGroupAc.setGrid(_gridObj);
    purGroupAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y",
    	"dir_type":"PUR_GROUP"
  	   });
    purGroupAc.setKeyName("PURGROUP");
    purGroupAc.setAcResultMap({
        "PURGROUP":"cdusrd_no"
    });
    purGroupAc.init();
    
    accntTypeAc = acSysDesc("ACCNTTYPEDESC","ACCNTTYPE","ACCNT_TYPE", true,_gridObj,_cellObj);
}

/**
 * Put the delete Mark
 */
function goDel()
{
	//del Row (return Row ID)
	delRow(myGrid);
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value = "";
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (maPtBuyReqListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value == '') return;
	
	var form = document.maPtBuyReqListForm;	
	form.strutsAction.value = '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_FIND%>'; 

	var url = contextPath + "/maPtBuyReqList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtBuyReqListForm), "PTPRITEMID", "Y");

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
	var form = document.maPtBuyReqListForm;
	form.elements['maPtBuyReqListDTO.ptPrItemId'].value = getValueById(myGrid, selectedId,'PTPRITEMID');
    
	goCommonTabPage(form, <%= MaPtBuyReqDetailAction.BUY_ITEM_DETAIL_INIT %>, "maPtBuyReqDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptPrItemId)
{
	maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value = _ptPrItemId;
	findGridList('ReloadRow');
	maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtBuyReqDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value =  getValueById(myGrid, selectedId,'PTPRITEMID');  
    maPtBuyReqListForm.elements['strutsAction'].value = '<%=MaPtBuyReqDetailAction.BUY_ITEM_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtBuyReqListForm), 'maPtBuyReqDetail'); 
} 


/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtBuyReqDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value = "";
	goCommonTabPage(maPtBuyReqListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
	maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value = "";
    excelServerAction("maPtBuyReqList", maPtBuyReqListForm );  
}

/**
 * 삭제
 */
 function goDelete()
 {
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PTPRLISTID', 'PTPRITEMID', 'PTPNLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPtBuyReqListForm.strutsAction.value = '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_DELETE%>';
	
	var url = contextPath + "/maPtBuyReqList.do";
	
    $.post(url,FormQueryString(maPtBuyReqListForm)+delArray , function(_data){
    	afterDelete();
    }); 
 }

function afterDelete(){
	goClose('maPtBuyReqDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	partAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	// 일괄등록
	if(code == 'maPtBuyReqDetailDTO.multiDesc')
	{
		maPtBuyReqListForm.strutsAction.value = '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_INPUT%>';
		
		maPtBuyReqListForm.elements['maPtBuyReqDetailDTO.curr'].value = "WON";
		maPtBuyReqListForm.elements['maPtBuyReqDetailDTO.recQty'].value = "1";
		
		// 요청자
		if(maPtBuyReqListForm.elements['maPtBuyReqDetailDTO.appReqById'].value==""){
			maPtBuyReqListForm.elements['maPtBuyReqDetailDTO.appReqById'].value     = "<%=user.getEmpId()%>";                   
			maPtBuyReqListForm.elements['maPtBuyReqDetailDTO.appReqByDesc'].value   = "<%=user.getEmpName()%>";                 
		}
		
		maPtBuyReqListForm.elements['maPtBuyReqDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		if(typeof exSetAcLovValue == "function") exSetAcLovValue(rtnArr, code);
		
		goSaveAll();
	} 
	// 현장신청부품
	else if(code == 'maPtBuyReqDetailDTO.ptPnListIds'){
	 	maPtBuyReqListForm.strutsAction.value = '<%=MaPtBuyReqListAction.BUY_ITEM_LIST_GOT_INPUT%>';
	 	maPtBuyReqListForm.elements['maPtBuyReqDetailDTO.curr'].value = "WON";
	 	
	 	if(typeof exSetAcLovValue == "function") exSetAcLovValue(rtnArr, code);
		
		goSaveAll();
		
	}
}

/**
 * 현장신청부품 선택
 */
function goPnselect(){
	ptPnAc.openLov();
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtBuyReqListForm.elements['maPtBuyReqListDTO.ptPrItemId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtBuyReqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.ptPrListId"/><!-- Key -->
<html:hidden property="maPtBuyReqListDTO.ptPrItemId"/><!-- Detail Key -->
<html:hidden property="maPtBuyReqListDTO.multiKey"/>
<html:hidden property="maPtBuyReqListDTO.multiDesc"/>
<html:hidden property="maPtBuyReqDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maPtBuyReqDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maPtBuyReqDetailDTO.curr"/>
<html:hidden property="maPtBuyReqDetailDTO.accntType"/>
<html:hidden property="maPtBuyReqDetailDTO.recQty"/>
<html:hidden property="maPtBuyReqDetailDTO.appReqById"/>
<html:hidden property="maPtBuyReqDetailDTO.appReqByDesc"/>
<html:hidden property="maPtBuyReqDetailDTO.partId"/>
<html:hidden property="maPtBuyReqDetailDTO.ptPnListIds"/>
<html:hidden property="maPtBuyReqDetailDTO.partDesc"/>
<html:hidden property="maPtBuyReqDetailDTO.ptSize"/>
<html:hidden property="maPtBuyReqDetailDTO.remark"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
	 	<c:set var="filePath" value="enhance/${compName}/part/pur/buy/maPtBuyReqList_${compNo}.jsp" />
		<c:if test="${udf:isExist(filePath)}">
			<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/pur/buy/maPtBuyReqList_${compNo}.jsp"></c:import>
		</c:if>
 	</div>

</html:form> 
</body>
</html>