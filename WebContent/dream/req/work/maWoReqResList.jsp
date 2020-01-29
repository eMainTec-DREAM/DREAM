<%--===========================================================================
작업요청서- 처리사항 
author  kim21017
version $Id: maWoReqResList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.MaWoReqResListAction" %> 
<%@ page import="dream.req.work.action.MaWoReqResDetailAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 처리사항 -->
<title><bean:message key='TAB.maWoReqResList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var beforePageId= '';
var woAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	woAc = new autoC({"maWoReqCommonDTO.wkorDesc":"description"});
	woAc.setTable("TAWORKORDER");
	woAc.setAcResultMap({
        "maWoReqCommonDTO.wkorId":"wkorId"
    });
	woAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
    	return sortColumn("maWoReqResList", this, maWoReqResListForm, "WOREQRESID", ind, direction);
	});
	myGrid.init();
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
 		var wkorId = getValueById(myGrid, rowId,'WKORID');
 		var pageId = "maWoReqResDetail";

 		if(wkorId != "") pageId = "reqWorkReswoDetail"; 
		
 		goTabPage(pageId);
	});
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value == '') return;
	
	var form = document.maWoReqResListForm;	
	form.strutsAction.value = '<%=MaWoReqResListAction.LIST_FIND %>';
	
	var url = contextPath + "/maWoReqResList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoReqResListForm), "WOREQRESID", "Y");

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
	var form = document.maWoReqResListForm;
	 
	form.elements['maWoReqResListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
	goCommonTabPage(form, <%= MaWoReqResDetailAction.DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
 	maWoReqResListForm.elements['strutsAction'].value = '<%=MaWoReqResDetailAction.DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maWoReqResListForm), 'maWoReqResDetail'); 
}
/**
 * 상세열기
 */
 function goOpen(){
// 	goTabPage('maWoReqResDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
	  excelServerAction("maWoReqResList", maWoReqResListForm );
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoReqResDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
	goCommonTabPage(maWoReqResListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woReqResId,_resStatus)
 {
	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = _woReqResId;
 	findGridList('ReloadRow');
 	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
 	//헤더 상태변경
 	if (parent.changStatus) parent.changStatus(_resStatus);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOREQRESID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoReqResListForm.strutsAction.value = '<%=MaWoReqResListAction.LIST_DELETE%>';
	var url = contextPath + "/maWoReqResList.do";
	
	$.post(url,FormQueryString(maWoReqResListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoReqResDetail',this);
	//goSearch();
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value, maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value);	
	}
	
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/maWoReqResList.do";
	
	$.post(url,FormQueryString(maWoReqResListForm), function(_data){
		afterSave(_data);
	});
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    if (parent.parent.findGridRow)
        parent.parent.findGridRow(maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value);
    
    if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value, maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value);	
	}
    
    goSearch();
}
   
function goSelect()
{
	woAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maWoReqCommonDTO.wkorDesc')
	{
		maWoReqResListForm.strutsAction.value = '<%=MaWoReqResListAction.LIST_WO_LINK %>';
		
		maWoReqResListForm.elements['maWoReqCommonDTO.wkorDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

function goFix()
{
	var param = "strutsAction=1001";
	var url =  contextPath + "/maWoResultSelect.do";
	
	openLayerPopup("maWoResultSelect", param);
}

function setAfterSelect(returnArray){

	var woType = returnArray[0];
	var pmType = returnArray[1];
	var _pageId  = returnArray[2];
	var woGenType = 'WOREQ';
	var woReqId = maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value;
	var woReqResId = maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value;
	var ifm = getIframeContent();
	
	var _param = "strutsAction=0&maWoResultMstrCommonDTO.selectedWoType="+woType+
				 "&maWoResultMstrCommonDTO.selectedPmType="+pmType+
				 "&maWoResultMstrCommonDTO.compNo="+loginUser.compNo+
				 "&maWoResultMstrCommonDTO.woReqId="+maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value +
				 "&maWoResultMstrDetailDTO.woGenType="+woGenType+
				 "&maWoResultMstrDetailDTO.equipDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEquipDesc"]').val()+
				 "&maWoResultMstrDetailDTO.equipId="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEquipId"]').val()+
				 "&maWoResultMstrDetailDTO.eqLocId="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEqLocId"]').val()+
				 "&maWoResultMstrDetailDTO.eqLocDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEqLocDesc"]').val()+
				 "&maWoResultMstrDetailDTO.perform="+$(ifm.document).find('[name="maWoReqDetailDTO.review"]').val()+
				 "&maWoResultMstrDetailDTO.wkOrDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.reqDesc"]').val()+
				 "&maWoResultMstrDetailDTO.deptId="+$(ifm.document).find('[name="maWoReqDetailDTO.recDeptId"]').val()+
				 "&maWoResultMstrDetailDTO.deptDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.recDeptDesc"]').val()+
				 "&maWoResultMstrDetailDTO.empId="+$(ifm.document).find('[name="maWoReqDetailDTO.recEmpId"]').val()+
				 "&maWoResultMstrDetailDTO.empDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.recEmpName"]').val()+
				 "&maWoReqCommonDTO.woReqId="+ woReqId;
	openQuickTabPage(_param, _pageId); 

}

/**
 * W/O 열기
 */
function goWo()
{ 
	var selectedId=myGrid.getSelectedRowId();
	
    if(selectedId == "undefined" || selectedId == "") return;
    
    var wkOrId = getValueById(myGrid, selectedId, "WKORID");
    var pmType = getValueById(myGrid, selectedId, "PMTYPE");
    var woType = getValueById(myGrid, selectedId, "WOTYPE");
    var woparam = getValueById(myGrid, selectedId, "WOPARAM");
    var woReqId = maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value;
    var woReqResId = maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value;
    
    if(wkOrId == "undefined" || wkOrId == ""
	    || pmType == "undefined" || pmType == ""
	   	|| woparam == "undefined" || woparam == "")
    {
    	alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
    	return;
    }
    
    pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
    
    var url   = contextPath + "/"+woparam+".do";

    var popWidth = 1010;
    var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&maWoResultMstrCommonDTO.wkOrId="+ wkOrId+"&maWoResultMstrCommonDTO.selectedWoType="+ woType +"&maWoReqCommonDTO.woReqId="+ woReqId
    			+ "&maWoResultMstrCommonDTO.woReqResId="+ woReqResId;
    
    openWindowWithPost(url, "WO_DETAIL", param, pos);
    //alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
}

function goCrewoplan()
{
	var ifm = getIframeContent();
	
	var _param = "strutsAction=0"+
				 "&woPlanCommonDTO.compNo="+loginUser.compNo+
				 "&woPlanCommonDTO.woReqId="+maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value +
				 "&woPlanDetailDTO.equipDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEquipDesc"]').val()+
				 "&woPlanDetailDTO.equipId="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEquipId"]').val()+
				 "&woPlanDetailDTO.eqLocId="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEqLocId"]').val()+
				 "&woPlanDetailDTO.eqLocDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.reqEqLocDesc"]').val()+
				 "&woPlanDetailDTO.perform="+$(ifm.document).find('[name="maWoReqDetailDTO.review"]').val()+
				 "&woPlanDetailDTO.wkOrDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.reqDesc"]').val()+
				 "&woPlanDetailDTO.deptId="+$(ifm.document).find('[name="maWoReqDetailDTO.recDeptId"]').val()+
				 "&woPlanDetailDTO.deptDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.recDeptDesc"]').val()+
				 "&woPlanDetailDTO.empId="+$(ifm.document).find('[name="maWoReqDetailDTO.recEmpId"]').val()+
				 "&woPlanDetailDTO.empDesc="+$(ifm.document).find('[name="maWoReqDetailDTO.recEmpName"]').val();
	openQuickTabPage(_param, "woPlanDetail"); 
}

function goWoplan()
{
	var selectedId=myGrid.getSelectedRowId();
	
    if(selectedId == "undefined" || selectedId == "") return;
    
    var wkOrId = getValueById(myGrid, selectedId, "WKORID");
    
    if(wkOrId == "undefined" || wkOrId == "")
    {
    	alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
    	return;
    }
    
    var url   = contextPath + "/woPlanDetail.do";

    var popWidth = 1010;
    var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=1001&woPlanCommonDTO.wkOrId="+ wkOrId;
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoReqResList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoReqCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="maWoReqCommonDTO.wkorId"/>
<html:hidden property="maWoReqCommonDTO.wkorDesc"/>
<html:hidden property="maWoReqResListDTO.woReqResId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>