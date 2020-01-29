<%--===========================================================================
검교정상세 측정값목록
author  kim21017
version $Id: workListGnlCavalList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListGnlCavalListAction" %>
<%@ page import="dream.work.list.action.WorkListGnlCavalDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 측정값 -->
<title><bean:message key='TAB.workListGnlCavalList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
	
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
	
	myGrid.setHeader("<bean:message key='LABEL.ISDELCHECK'/>,<bean:message key='LABEL.seqNo'/>,<bean:message key='LABEL.separation'/>,<bean:message key='LABEL.calibPoint'/>,<bean:message key='LABEL.allowValue'/>,<bean:message key='LABEL.asFound'/>,#cspan,#cspan,<bean:message key='LABEL.asLeft'/>,#cspan,#cspan,ID,ID");
	myGrid.attachHeader(["#rspan","#rspan","#rspan","#rspan","#rspan","<bean:message key='LABEL.asfStdValue'/>","<bean:message key='LABEL.asfCalValue'/>","<bean:message key='LABEL.asfDiffValue'/>","<bean:message key='LABEL.aslStdValue'/>","<bean:message key='LABEL.aslCalValue'/>","<bean:message key='LABEL.aslDiffValue'/>","#rspan","#rspan"]);
 	myGrid.setColumnIds("ISDELCHECK,SEQNO,CLIBPOINTTYPEDESC,CALIBPOINT,ALLOWVALUE,ASFSTDVALUE,ASFCALVALUE,ASFDIFFVALUE,ASLSTDVALUE,ASLCALVALUE,ASLDIFFVALUE,WOCALIBGNLVALUEID,CALIBPOINTTYPE");
	myGrid.setInitWidths("60,60,120,80,80,80,80,80,80,80,80,80,80");
	myGrid.setColAlign("center,center,left,center,center,center,center,center,center,center,center,center,left");
	myGrid.setColTypes("ch,cntr,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
	myGrid.attachEvent("onEditCell",function(stage,id,index,new_value,old_value){
		if(index == 2) return true;		// 회차
		if(index == 3) return true;		// 교청Point
		if(index == 4) return true;		// 허용오차
		if(index == 7) return true;		// AF편차
		if(index == 10) return true;	// AL편차
		if(index == 11) return true;	// AL편차
		if(index == 5){//AF 표준값 변경
			if(new_value=='') myGrid.cells(id, 7).setValue(''); //값이 없으면 편차 지우기.
			else{//값이 있다면 편차 구하기.
				var asfStdValue   = parseFloat(new_value);
				var asfCalValue   = parseFloat(myGrid.cells(id, 6).getValue());
				
				myGrid.cells(id, 7).setValue(getDiffValue(asfCalValue,asfStdValue));
			}
			
			return true;
		}
		if(index == 6){//AF 측정값에 따라 편차값 변경
			if(new_value=='') myGrid.cells(id, 7).setValue('');//값이 없으면 편차 지우기.
			else{//값이 있다면 편차 구하기.
				var asfStdValue   = parseFloat(myGrid.cells(id, 5).getValue());
				var asfCalValue   = parseFloat(new_value);
				myGrid.cells(id, 7).setValue(getDiffValue(asfCalValue,asfStdValue));
			}
			
			return true;
		}
		if(index == 8){//AL 표준값 변경
			if(new_value=='') myGrid.cells(id, 10).setValue('');
			else{//값이 있다면 편차 구하기.
				var aslStdValue   = parseFloat(new_value);
				var aslCalValue   = parseFloat(myGrid.cells(id, 9).getValue());
				
				myGrid.cells(id, 10).setValue(getDiffValue(aslCalValue,aslStdValue));
			}
			
			return true;
		}
		if(index == 9){//AL 측정값에 따라 편차값 변경
			if(new_value=='') myGrid.cells(id, 10).setValue('');//값이 없으면 편차 지우기.
			else{//값이 있다면 편차 구하기.
				var aslStdValue = parseFloat(myGrid.cells(id, 8).getValue());
				var aslCalValue   = parseFloat(new_value);
				myGrid.cells(id, 10).setValue(getDiffValue(aslCalValue,aslStdValue));
			}
			
			return true;
		}
		
	});
	
	myGrid.enableAutoHeight(true,100);
	myGrid.setColumnHidden(11,true);
	//myGrid.setColumnHidden(12,true);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("workListGnlCavalDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = "";
        return sortColumn("workListGnlCavalList", this, workListGnlCavalListForm, "WOCALIBGNLVALUEID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(myGrid,"gridbox", true)}); myGrid.init();

	myGrid.enableAutoHeight(true);
	isHeaderLoaded[currentPageId+".gridbox"] = "Y";
	setCounter(myGrid,"gridbox", true);
	
	goSearch();
}

/**
 * 편차 구하기. 둘 중 소수점 큰 것에 맞춰서 편차구함.
 */
function getDiffValue (val1, val2){
	var dpLength1 = 0;
	var dpLength2 = 0;
	
	if((val1+'').indexOf('.')>0){
		dpLength1 = (val1+'').length - (val1+'').indexOf('.')-1;
	}else{
		dpLength1 = 0;
	}
	
	if((val2+'').indexOf('.')>0){
		dpLength2 = (val2+'').length - (val2+'').indexOf('.')-1;
	}else{
		dpLength2 = 0;
	}
	var fixedLength = Math.max(dpLength1,dpLength2);
	
	var resultVal = (val1 - val2).toFixed(fixedLength);
	if(resultVal=='NaN') resultVal = '';
	
	return resultVal;
}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = "";
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.workListGnlCavalListForm;	
	form.strutsAction.value = '<%=WorkListGnlCavalListAction.WORK_LIST_GNL_CAVAL_LIST_FIND %>';
	
	var url = contextPath + "/workListGnlCavalList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListGnlCavalListForm), "WOCALIBGNLVALUEID", "Y");

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
	var form = document.workListGnlCavalListForm;

	form.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = getValueById(myGrid, selectedId,'WOCALIBGNLVALUEID');
	goCommonTabPage(form, <%= WorkListGnlCavalDetailAction.WORK_LIST_GNL_CAVAL_DETAIL_INIT %>, pageId);
}
/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workListGnlCavalDetail');
}

 function goOpenAction()
 {
        var selectedId=myGrid.getSelectedRowId();

        if(selectedId == null) return;
        workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = getValueById(myGrid, selectedId,'WOCALIBGNLVALUEID');

        workListGnlCavalListForm.elements['strutsAction'].value = '<%=WorkListGnlCavalDetailAction.WORK_LIST_GNL_CAVAL_DETAIL_INIT%>';

        openQuickTabPage(FormQueryString(workListGnlCavalListForm), "workListGnlCavalDetail"); 

 }
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = "";
  	excelServerAction("workListGnlCavalList",workListGnlCavalListForm);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workListGnlCavalDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = "";
	goCommonTabPage(workListGnlCavalListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_wocalibgnlvalueId)
 {
	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = _wocalibgnlvalueId;
 	findGridList('ReloadRow');
 	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOCALIBGNLVALUEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workListGnlCavalListForm.strutsAction.value = '<%=WorkListGnlCavalListAction.WORK_LIST_GNL_CAVAL_LIST_DELETE%>';
	var url = contextPath + "/workListGnlCavalList.do";
	
	$.post(url,FormQueryString(workListGnlCavalListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workListGnlCavalDetail',this);
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goCopycalibstdvalue(){
	
	$('[name="maWoResultMstrCommonDTO.equipId"]').val(parent.$('[name="maWoResultMstrDetailDTO.equipId"]').val());
	//선택된 설비값 체크
	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.pmCalibStdTpId'].value = 
		parent.maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmCalibStdTpId'].value;
	
	var pmcType = workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value;
	if(workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.pmCalibStdTpId'].value=='' && pmcType == ""){
		alertMessage1('<bean:message key="MESSAGE.MSG0175"/>');
		return;
	}

	//표준값 복사
	workListGnlCavalListForm.strutsAction.value = '<%=WorkListGnlCavalListAction.WORK_LIST_GNL_CAVAL_LIST_COPY%>';
	var actionUrl = contextPath + "/workListGnlCavalList.do";
	XMLHttpPost(actionUrl, FormQueryString(workListGnlCavalListForm), 'afterCopycalibstdvalue');
	
}
function afterCopycalibstdvalue(){
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
	goSearch();
}
function goRegbatchcalibval(){
	//측정값 일괄등록
	var param = "popupWidth=980&maWoResultMstrCommonDTO.wkOrId="+workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value; 
	openLayerPopup("workListGnlCavalPopup", param);
}


/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("OPEN");
	hideBtn("SETTING");
	hideBtn("COPYCALIBSTDVALUE");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("workListGnlCavalDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/workListGnlCavalList.do";
	var stAct = "<%=WorkListGnlCavalListAction.WORK_LIST_GNL_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"CLIBPOINTTYPEDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"CALIBPOINT","ed"); //EDIT,NUMBER
	setColumnType(myGrid,"ALLOWVALUE","ed"); //EDIT,NUMBER
	setColumnType(myGrid,"ASFSTDVALUE","ed"); //EDIT,NUMBER
	setColumnType(myGrid,"ASFCALVALUE","ed"); //EDIT,NUMBER
	setColumnType(myGrid,"ASLSTDVALUE","ed"); //EDIT,NUMBER
	setColumnType(myGrid,"ASLCALVALUE","ed"); //EDIT,NUMBER
}

/**
 * After Edit
 */
function afterSave()
{
	afterEditRow(myGrid);
	
	//Control Button
	hideBtn("SAVE");
	hideBtn("ADD");
	hideBtn("DEL");
	showBtn("EDIT");
	hideBtn("EDITCNCL");

	showBtn("CREATE");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("OPEN");
	showBtn("SETTING");
	showBtn("COPYCALIBSTDVALUE");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
	workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value = "";
	
	//Search
	goSearch();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	//Make Page as Normal
	setForNormal();
}

/**
 * AC Setting, This is called when the column is changed to edit mode.
 */
function setGridAc(_gridObj, _cellObj)
{
	//Column Id별로 event 할당
    acSysDesc("CLIBPOINTTYPEDESC","CALIBPOINTTYPE","CALIB_POINT_TYPE",true,_gridObj,_cellObj);
}

/**
 * Add new row
 */
function goAdd()
{
	//Add Row (return Row ID)
    rId = addRow(myGrid);
    
    sequenceNextVal('SQAWOCALIBGNLVALUE_ID');
}

/**
 * Put the delete Mark
 */
function goDel()
{
	//del Row (return Row ID)
	delRow(myGrid);
}


function goSave()
{
	//Send All Data ONce
	proGrid.sendData();
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
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workListGnlCavalListForm.elements['maWoResultMstrCommonDTO.wocalibgnlvalueId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListGnlCavalList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.pmCalibStdTpId"/>
<html:hidden property="maWoResultMstrCommonDTO.wocalibgnlvalueId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType" />
<html:hidden property="maWoResultMstrCommonDTO.equipId" />
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:230px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>