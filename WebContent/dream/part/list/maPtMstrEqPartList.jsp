<%--===========================================================================
자재마스터 사용설비
author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.MaPtMstrEqPartListAction" %>
<%@ page import="dream.part.list.action.MaPtMstrEqPartDetailAction" %>
<%@ page import="dream.part.list.action.MaPtMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 자재마스터 사용설비 -->
<title><bean:message key='TAB.maPtMstrEqPartList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var equipDescAc;
function loadPage() 
{
    initGrid();
    
    equipDescAc = new autoC({"maPtMstrEqPartDetailDTO.multiDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "eq_status":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maPtMstrEqPartDetailDTO.multiKey":"equip_id"
    });
    equipDescAc.setMultiSelect(true);
    equipDescAc.init();
}

var myGrid;
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goTabPage("maPtMstrEqPartDetail");
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.eqPartId'].value = "";
        return sortColumn("maPtMstrEqPartList", this, maPtMstrEqPartListForm, "EQPARTID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

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
    //페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
    if (maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.partId'].value == '') return;
    
    var form = document.maPtMstrEqPartListForm; 
    form.strutsAction.value = '<%=MaPtMstrEqPartListAction.PTMSTR_EQPART_LIST_FIND %>';
    
    var url = contextPath + "/maPtMstrEqPartList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtMstrEqPartListForm), "EQPARTID","Y");

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
    var form = document.maPtMstrEqPartListForm;
    form.elements['maPtMstrCommonDTO.eqPartId'].value = getValueById(myGrid, selectedId, 'eqPartId');
    goCommonTabPage(form, <%= MaPtMstrEqPartDetailAction.PTMSTR_EQPART_DETAIL_INIT %>, pageId);
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqPartId)
{
	maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.eqPartId'].value = _eqPartId;
	findGridList('ReloadRow');
	maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.eqPartId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
    goTabPage('maPtMstrEqPartDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.eqPartId'].value = getValueById(myGrid, selectedId, 'eqPartId');
    maPtMstrEqPartListForm.elements['strutsAction'].value = '<%=MaPtMstrEqPartDetailAction.PTMSTR_EQPART_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(maPtMstrEqPartListForm), 'maPtMstrEqPartDetail'); 
} 

/**
 * 엑셀 다운.
 */
function goExcel()
{
	maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.eqPartId'].value = "";
	excelServerAction("maPtMstrEqPartList", maPtMstrEqPartListForm );  
}
 
  /**
   * 생성
   */
function goCreate()
{
    createValidationCheck(myGrid, "maPtMstrEqPartDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.eqPartId'].value = "";
    goCommonTabPage(maPtMstrEqPartListForm, '', pageId);
}
 
/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'eqPartId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtMstrEqPartListForm.strutsAction.value = '<%=MaPtMstrEqPartListAction.PTMSTR_EQPART_LIST_DELETE%>';
    var url = contextPath + "/maPtMstrEqPartList.do";
    
    $.post(url,FormQueryString(maPtMstrEqPartListForm)+delArray , function(_data){
        afterDelete();
    });
}
 
function afterDelete()
{
    goClose('maPtMstrEqPartDetail',this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/maPtMstrEqPartList.do";
	
    $.post(url,FormQueryString(maPtMstrEqPartListForm), function(_data){
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
 * 일괄등록
 */
function goRegbatch()
{
	setForUpdate();
	
	equipDescAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maPtMstrEqPartDetailDTO.multiDesc')
	{
		maPtMstrEqPartListForm.strutsAction.value = '<%=MaPtMstrEqPartListAction.PTMSTR_EQPART_LIST_INPUT%>';
		
		maPtMstrEqPartListForm.elements['maPtMstrEqPartDetailDTO.partId'].value = maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.partId'].value;
		
		goSaveAll();
	}
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.eqPartId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtMstrEqPartList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtMstrCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtMstrCommonDTO.eqPartId"/>
<html:hidden property="maPtMstrEqPartDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maPtMstrEqPartDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maPtMstrEqPartDetailDTO.partId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
    </div>

</html:form> 
</body>
</html>