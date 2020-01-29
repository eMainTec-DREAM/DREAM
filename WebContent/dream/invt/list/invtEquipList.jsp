<%--===========================================================================
응답 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.list.action.InvtEquipListAction" %>
<%@ page import="dream.invt.list.action.InvtDetailAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction" %>
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
<title><bean:message key='LABEL.questionPoint'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;
var evId, proGrid, rId; //Event Id, Process Obj, Row Id
var selectedEqId;
var equipAc;

function loadPage() 
{
	setForUpdate();
	
	// 설비 자동완성 
	equipAc = new autoC({"invtCommonDTO.equipDesc":"description"});
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
        "invtCommonDTO.equipId":"EQUIP_ID"
    });
	equipAc.setAcConditionMap({
    	"comp_no" : loginUser.compNo
    });
	equipAc.setMultiSelect(true);
	equipAc.init();
	
	initGrid();
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
		invtEquipListForm.elements['invtCommonDTO.invtEquipId'].value = "";
        return sortColumn("invtEquipList", this, invtEquipListForm, "INVTEQUIPID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox", true)});
	
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (invtEquipListForm.elements['invtCommonDTO.invtlistId'].value == '') return;
	
	var form = document.invtEquipListForm;	
	form.strutsAction.value = '<%=InvtEquipListAction.LIST_FIND%>'; 

	var url = contextPath + "/invtEquipList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtEquipListForm), "invtEquipId","Y");

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
	var form = document.invtEquipListForm;
	form.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
	goCommonTabPage(form, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_invtEquipId)
{
	invtEquipListForm.elements['invtCommonDTO.equipId'].value = _invtEquipId;
	findGridList('ReloadRow');
	invtEquipListForm.elements['invtCommonDTO.equipId'].value = "";
}
/**
 * 상세열기
 */
function goOpen(rowId)
{
	var param  = getValueById(myGrid, rowId,'PARAM');
	invtEquipListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = getValueById(myGrid, rowId,'EQCTGTYPE');
	goTabPage(param);
}

function goOpenAction()
{
    var selectedId = myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
	var form = document.invtEquipListForm;    
    form.elements['maEqMstrCommonDTO.equipId'].value =  getValueById(myGrid, selectedId,'EQUIPID');  
    form.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>';
    var pageId  = getValueById(myGrid, selectedId,'PARAM');
 	openQuickTabPage(FormQueryString(form), pageId);  
} 

/**
 * 신규설비 생성
 */
function goAddnewequip()
{
	// 열려있는 상세페이지가 있는경우 닫아준다.
    var selectedId=myGrid.getSelectedRowId();
	if(selectedId != null) goClose(getValueById(myGrid, selectedId,'PARAM'), this);
	
	openSelectType();	
	sequenceNextVal('sqainvtequip_id');
}

//afterAddnewequip
function afterCreate(_equipId, pageId)
{
	// TAINVTEQUIP에 넣어주기.
	var form = document.invtEquipListForm;
	var actionUrl = contextPath + "/invtEquipList.do";
	form.elements['invtCommonDTO.equipId'].value = _equipId;
		
	form.strutsAction.value = "<%=InvtEquipListAction.NEWEQ_LIST_INPUT%>";
	XMLHttpPost(actionUrl, FormQueryString(form),'afterSave');
}


function setSequenceVal(sequenceVal)
{
	invtEquipListForm.elements['invtCommonDTO.invtEquipId'].value = sequenceVal;
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	
	var form = document.invtEquipListForm;
	
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    findGridRow(form.elements['invtCommonDTO.equipId'].value);

    getTopPage().afterSaveAll(currentPageId);

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

function setAfterSelect(returnArray){
	var eqCtgType = returnArray[0];
	var param2  = returnArray[1];
	invtEquipListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = eqCtgType;
	//제개정 사용여부
	if("<%=isUseAssetRevision%>"=="N"){
		beforePageId = param2;
		goCommonTabPage(invtEquipListForm, '', param2);
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
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
	invtEquipListForm.elements['invtCommonDTO.invtEquipId'].value = "";
	excelServerAction("invtEquipList", invtEquipListForm);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','invtEquipId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	invtEquipListForm.strutsAction.value = '<%=InvtEquipListAction.LIST_DELETE%>';
	var url = contextPath + "/invtEquipList.do";
	
    $.post(url,FormQueryString(invtEquipListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose();
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/invtEquipList.do";
	
    $.post(url,FormQueryString(invtEquipListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    invtEquipListForm.elements['invtCommonDTO.equipId'].value = "";
    goSearch();
}

/*
 * 기존설비 추가하기
 */
function goAddequip()
{
	equipAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	invtEquipListForm.strutsAction.value = '<%=InvtEquipListAction.EQ_LIST_INPUT%>';
	
	goSaveAll();
}

//설비 sub List는 Disable에서 제외 
function afterDisable()
{
	setEnableAll();
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = invtEquipListForm.elements['invtCommonDTO.equipId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtEquipList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtCommonDTO.invtlistId"/><!-- Key -->
<html:hidden property="invtCommonDTO.invtEquipId"/>
<html:hidden property="invtCommonDTO.equipId"/>
<html:hidden property="invtCommonDTO.equipDesc"/>
<html:hidden property="invtCommonDTO.description"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/>
<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>