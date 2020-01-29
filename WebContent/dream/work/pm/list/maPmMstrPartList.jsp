<%--===========================================================================
사용자재
author  jung7126
version $Id: maPmMstrPartList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrPartListAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrPartDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 사용자재 -->
<title><bean:message key='LABEL.parts'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var partAc;
var stdPartAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	// 부품 일괄등록
	partAc = new autoC({"maPmMstrPartDetailDTO.multiDesc":"full_desc"});
	partAc.setTable("TAPARTS");
	partAc.setAcResultMap({
        "maPmMstrPartDetailDTO.multiKey":"part_id"
    });
	partAc.setAcConditionMap({
	  	   "comp_no":loginUser.compNo
	  	   });
	partAc.setMultiSelect(true);
	partAc.init();
	
	// 표준부품선택
	stdPartAc = new autoC({"maPmMstrPartDetailDTO.multiStdPartDesc":"partNameSize"});
	stdPartAc.setTable("TAEQCTGPART");
	stdPartAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
        , "is_use" : "Y"
    })
    stdPartAc.setAcDConditionMap({
        "pm_id" : "maPmMstrCommonDTO.pmId"
    })
	stdPartAc.setAcResultMap({
        "maPmMstrPartDetailDTO.multiStdPartKey":"partId"
    });
	stdPartAc.setMultiSelect(true);
	stdPartAc.init();
	
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maPmMstrPartDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPmMstrPartListForm.elements['maPmMstrCommonDTO.pmPartId'].value = "";
        return sortColumn("maPmMstrPartList", this, maPmMstrPartListForm, "PMPARTID", ind, direction);
    });
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maPmMstrPartListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.maPmMstrPartListForm;	
	form.strutsAction.value = '<%=MaPmMstrPartListAction.PM_MSTR_PART_LIST_FIND %>';
	
	var url = contextPath + "/maPmMstrPartList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmMstrPartListForm), "PMPARTID","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPartId)
{
	maPmMstrPartListForm.elements['maPmMstrCommonDTO.pmPartId'].value = _pmPartId;
	findGridList('ReloadRow');
	maPmMstrPartListForm.elements['maPmMstrCommonDTO.pmPartId'].value = "";
}


/**
 * Modify List Data using Detail Data when Detail saved. 
 */
// function setDataAction(detailArray, keyVal)
// {
// 	var keyValMap = {};
// 	keyValMap["PARTNO"] 		= "maPmMstrPartDetailDTO.partNo";
// 	keyValMap["PARTDESC"] 		= "maPmMstrPartDetailDTO.partDesc";
// 	keyValMap["USEQTY"] 		= "maPmMstrPartDetailDTO.useQty";
// 	keyValMap["PMID"] 			= "maPmMstrCommonDTO.pmId";
// 	keyValMap["PMPARTID"] 		= "maPmMstrPartDetailDTO.pmPartId";

// 	setValues(myGrid, 'PMPARTID', keyVal, keyValMap, detailArray);
// }

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
   tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.maPmMstrPartListForm;
	 
	form.elements['maPmMstrCommonDTO.pmPartId'].value = getValueById(myGrid, selectedId,'pmPartId');
	goCommonTabPage(form, <%= MaPmMstrPartDetailAction.PM_MSTR_PART_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maPmMstrPartDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPmMstrPartListForm.elements['maPmMstrCommonDTO.pmPartId'].value =  getValueById(myGrid, selectedId,'PMPARTID');  
    maPmMstrPartListForm.elements['strutsAction'].value = '<%=MaPmMstrPartDetailAction.PM_MSTR_PART_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPmMstrPartListForm), 'maPmMstrPartDetail'); 
} 

  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maPmMstrPartDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maPmMstrPartListForm.elements['maPmMstrCommonDTO.pmPartId'].value = "";
	goCommonTabPage(maPmMstrPartListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'pmPartId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPmMstrPartListForm.strutsAction.value = '<%=MaPmMstrPartListAction.PM_MSTR_PART_LIST_DELETE%>';
	var url = contextPath + "/maPmMstrPartList.do";
	
	$.post(url,FormQueryString(maPmMstrPartListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maPmMstrPartDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	maPmMstrPartListForm.elements['maPmMstrCommonDTO.pmPartId'].value = "";
	excelServerAction("maPmMstrPartList", maPmMstrPartListForm);
}

function goSave(){
	var url = contextPath + "/maPmMstrPartList.do";
	
    $.post(url,FormQueryString(maPmMstrPartListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
}

/**
 * 표준부품선택
 */
function goStdpt()
{
 	stdPartAc.openLov();
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
	if(code == 'maPmMstrPartDetailDTO.multiDesc')
	{
		maPmMstrPartListForm.strutsAction.value = '<%=MaPmMstrPartListAction.PM_MSTR_PART_LIST_INPUT%>';
		
		maPmMstrPartListForm.elements['maPmMstrPartDetailDTO.useQty'].value = "1";
				
		goSaveAll();
	}
	else if(code == 'maPmMstrPartDetailDTO.multiStdPartDesc')
	{
		maPmMstrPartListForm.strutsAction.value = '<%=MaPmMstrPartListAction.PM_MSTR_STDPART_LIST_INPUT%>';
		
		maPmMstrPartListForm.elements['maPmMstrPartDetailDTO.useQty'].value = "1";
				
		goSaveAll();
	}
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPmMstrPartList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmPartId"/>
<html:hidden property="maPmMstrPartDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maPmMstrPartDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maPmMstrPartDetailDTO.multiStdPartKey"/><!-- MultiSelect Key -->
<html:hidden property="maPmMstrPartDetailDTO.multiStdPartDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maPmMstrPartDetailDTO.useQty"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>