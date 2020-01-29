<%--===========================================================================
검사항목
author  jung7126
version $Id: maPmMstrPointList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrPointListAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrPointDetailAction" %>
<%@ page import="dream.work.pm.std.action.LovStdPointListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 검사항목 -->
<title><bean:message key='LABEL.maPmMstrPointList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var beforePageId = '';
var detailPageAc;
var checkSelAc;

function loadPage() 
{
	initGrid();
	
	detailPageAc = new autoC({"maPmMstrCommonDTO.pointDetailPageId":"param1"});
	detailPageAc.setTable("TACDSYSD");
	detailPageAc.setAcConditionMap({
        "list_type" : "CHECK_TYPE"
       ,"is_use" : "Y"
    });
	detailPageAc.setAcResultMap({
        "maPmMstrCommonDTO.pointDetailCheckTypeId":"cdsysd_no"
    });
	detailPageAc.init();
	
	
	//표준점검항목선택
	checkSelAc = new autoC({"maPmMstrPointDetailDTO.multiDesc":"checkPoint"});
	checkSelAc.setTable("TAEQCTGPMPOINT");
	checkSelAc.setAcConditionMap({ //getwhere에 넣어줄 conditionMap
        "comp_no" : loginUser.compNo
        ,"is_use" : "Y"
    });
    checkSelAc.setAcDConditionMap({
    });
	checkSelAc.setAcResultMap({
        "maPmMstrPointDetailDTO.multiKey":"eqCtgPmPointId"
    });
	checkSelAc.setMultiSelect(true);
	checkSelAc.init();
	
}

function afterDisable()
{
	$('.b_open').show();
	$('.b_excel').show();
	$('.b_setting').show();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   	 maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
        return sortColumn("maPmMstrPointList", this, maPmMstrPointListForm, "PMPOINTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}



/**
 * 생성
 */
function goCreate()
{
	  goClose(beforePageId,this);
	  //점검타입 LOV 
	  detailPageAc.openLov();
	  maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
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
	if (maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.maPmMstrPointListForm;	
	form.strutsAction.value = '<%=MaPmMstrPointListAction.PM_MSTR_POINT_LIST_FIND %>';

	var url = contextPath + "/maPmMstrPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmMstrPointListForm), "PMPOINTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPointId)
{
	maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = _pmPointId;
	findGridList('ReloadRow');
	maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
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
	var form = document.maPmMstrPointListForm;
	 
	form.elements['maPmMstrCommonDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
	goCommonTabPage(form, <%= MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INIT %>, pageId, beforePageId);
	
	beforePageId = pageId;
}

/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
	if(code=="maPmMstrCommonDTO.pointDetailPageId")
	{
		setAfterSelect(maPmMstrPointListForm.elements['maPmMstrCommonDTO.pointDetailPageId'].value);
	}
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	var detailPage  = getValueById(myGrid, rowId,'DETAILPAGE');
	var detailCheckTypeId = getValueById(myGrid, rowId,'CHECKTYPEID');
	
	maPmMstrPointListForm.elements['maPmMstrCommonDTO.pointDetailPageId'].value = detailPage;
	maPmMstrPointListForm.elements['maPmMstrCommonDTO.pointDetailCheckTypeId'].value = detailCheckTypeId;
	
	goTabPage(detailPage);
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    var detailPage  = getValueById(myGrid, selectedId,'DETAILPAGE');
    
    maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value =  getValueById(myGrid, selectedId,'PMPOINTID');  
    maPmMstrPointListForm.elements['strutsAction'].value = '<%=MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPmMstrPointListForm), detailPage); 
} 

  
/**
 * 표준점검항목선택
 */
function goCopy()
{
	checkSelAc.openLov();
}

function setAcLovValue(rtnValueArr, acInputName)
{
	afterSetValue();
}

function afterSetValue(lovType, rtnValue)
{

	maPmMstrPointListForm.strutsAction.value = '<%=MaPmMstrPointListAction.PM_MSTR_STDPART_LOV_INPUT%>';
	
	//goSaveAll();
	var url = contextPath + "/maPmMstrPointList.do";
		
    $.post(url,FormQueryString(maPmMstrPointListForm), function(_data){
    	goSearch();
    });
}

function setAfterSelect(detailPageId)
{
	 var detailPage = detailPageId;
	 beforePageId = detailPage;
	 goCommonTabPage(maPmMstrPointListForm, '', detailPage);
  }


function goCreateAction(pageId)
{
}
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'pmPointId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPmMstrPointListForm.strutsAction.value = '<%=MaPmMstrPointListAction.PM_MSTR_POINT_LIST_DELETE%>';
	var url = contextPath + "/maPmMstrPointList.do";
	
	$.post(url,FormQueryString(maPmMstrPointListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose(beforePageId,this);
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//detail EquipId 가져오기
function getEquipId(){
	   return parent.M$('maPmMstrDetailDTO.equipId').value;
}
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	 maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
	 excelServerAction("maPmMstrPointList", maPmMstrPointListForm );
 } 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPmMstrPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmPointId"/>
<html:hidden property="maPmMstrPointDetailDTO.pmPointId"/>
<html:hidden property="maPmMstrCommonDTO.equipId"/>
<html:hidden property="maPmMstrCommonDTO.pointDetailPageId"/>
<html:hidden property="maPmMstrCommonDTO.pointDetailCheckTypeId"/>
<html:hidden property="maPmMstrPointDetailDTO.multiKey"/>
<html:hidden property="maPmMstrPointDetailDTO.multiDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>