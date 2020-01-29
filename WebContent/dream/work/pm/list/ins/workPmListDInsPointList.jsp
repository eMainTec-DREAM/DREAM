<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmListDInsPointListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListDInsPointDetailAction" %>
<%@ page import="dream.work.pm.std.action.LovStdPointListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 점검 -->
<title><bean:message key='LABEL.pm'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
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
	checkSelAc = new autoC({"maPmMstrCommonDTO.multiDesc":"checkPoint"});
	checkSelAc.setTable("TAEQCTGPMPOINT");
	checkSelAc.setAcConditionMap({ //getwhere에 넣어줄 conditionMap
        "comp_no" : loginUser.compNo
        ,"is_use" : "Y"
    });
    checkSelAc.setAcDConditionMap({
    });
	checkSelAc.setAcResultMap({
        "maPmMstrCommonDTO.multiKey":"eqCtgPmPointId"
    });
	checkSelAc.setMultiSelect(true);
	checkSelAc.init();
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goTabPage("workPmListDInsPointDetail");
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
        return sortColumn("workPmListDInsPointList", this, workPmListDInsPointListForm, "PMPOINTID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
	
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 생성
*/

function goCreate()
{
 	createValidationCheck(myGrid, "workPmListDInsPointDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
	goCommonTabPage(workPmListDInsPointListForm, '', pageId);
}


function goSearch()
{
    findGridList('Search');
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.workPmListDInsPointListForm;	
	form.strutsAction.value = '<%=WorkPmListDInsPointListAction.LIST_FIND %>';

	var url = contextPath + "/workPmListDInsPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmListDInsPointListForm), "PMPOINTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPointId)
{
	workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = _pmPointId;

	findGridList('ReloadRow');
    workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
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
    workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value =  getValueById(myGrid, selectedId,'PMPOINTID');  
    
    goCommonTabPage(workPmListDInsPointListForm, <%= WorkPmListDInsPointDetailAction.DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}


/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
	if(code=="maPmMstrCommonDTO.pointDetailPageId")
	{
		setAfterSelect(workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pointDetailPageId'].value);
	}
}

/**
 * 상세 열기
 */
function goOpen(rowId)
{
// 	var detailPage  = getValueById(myGrid, rowId,'DETAILPAGE');
	var detailPage  = "workPmListDInsPointDetail";
	var detailCheckTypeId = getValueById(myGrid, rowId,'CHECKTYPEID');
	
	workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pointDetailPageId'].value = detailPage;
	workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pointDetailCheckTypeId'].value = detailCheckTypeId;
	
	
    goTabPage(detailPage);
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    var detailPage  = getValueById(myGrid, selectedId,'DETAILPAGE');
    
    workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value =  getValueById(myGrid, selectedId,'PMPOINTID');  
    workPmListDInsPointListForm.elements['strutsAction'].value = '<%=WorkPmListDInsPointDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmListDInsPointListForm), detailPage); 
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

	workPmListDInsPointListForm.strutsAction.value =  '<%=WorkPmListDInsPointListAction.LOV_INPUT%>';
	
	//goSaveAll();
	var url = contextPath + "/workPmListDInsPointList.do";
		
    $.post(url,FormQueryString(workPmListDInsPointListForm), function(_data){
    	goSearch();
    });
}

function setAfterSelect(detailPageId)
{
	 var detailPage = detailPageId;
	 beforePageId = detailPage;
	 goCommonTabPage(workPmListDInsPointListForm, '', detailPage);
  }

 
/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMPOINTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workPmListDInsPointListForm.strutsAction.value = '<%=WorkPmListDInsPointListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmListDInsPointList.do";
    
    $.post(url,FormQueryString(workPmListDInsPointListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
	goClose(beforePageId,this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//detail EquipId 가져오기
function getEquipId(){
	   return parent.M$('maPmMstrDetailDTO.equipId').value;
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
    workPmListDInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
   excelServerAction("workPmListDInsPointList", workPmListDInsPointListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmListDInsPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.equipId"/>
<html:hidden property="maPmMstrCommonDTO.pointDetailPageId"/>
<html:hidden property="maPmMstrCommonDTO.pointDetailCheckTypeId"/> 
<html:hidden property="maPmMstrCommonDTO.multiKey"/>
<html:hidden property="maPmMstrCommonDTO.multiDesc"/>
<html:hidden property="maPmMstrCommonDTO.pmPointId"/> 


    <!-- searchbox 박스 Line -->
    
    <div class="article_box" id="listBox">
        <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
        </div>
    </div>
    
</html:form> 
</body>
</html>

