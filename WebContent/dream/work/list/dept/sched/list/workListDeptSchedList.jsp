<%--===========================================================================
업체별 작업스케줄
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.dept.sched.list.action.WorkListDeptSchedListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 업체별 작업스케줄 -->
<title><bean:message key='PAGE.workListDeptSchedList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var plantAc;
var eqCtgTypeAc;
function loadPage() 
{
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.filterStartDate'].value = getYear()+'-'+getMonth()+'-01';
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.filterEndDate'].value = plusLastDayOfMonth(getYear()+'-'+getMonth());
	
    plantAc = new autoC({"workListDeptSchedCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workListDeptSchedCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    eqCtgTypeAc = new autoC({"workListDeptSchedCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    eqCtgTypeAc.setAcResultMap({
        "workListDeptSchedCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
	goSearch();
}

var myGrid;
function initGrid()
{
	// 기간 동적으로 grid 생성
	var startDate = workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.filterStartDate'].value;
	var endDate = workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.filterEndDate'].value;
	var dateStr = "";
	var widthStr = "";
	var colAliStr = "";
	var colTyStr = "";
	for (var i = 0; i < getDayInterval(dateToData(startDate),dateToData(endDate))+1; i++) {
		var date = getMinusDay2(startDate,-i);
		dateStr += ","+date.substr(5,2)+"/"+date.substr(8,2);
		widthStr += ",45";
		colAliStr += ",left";
		colTyStr += ",ro";
	}
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	// 상세리스트에도 검색했을 당시에 그 조건 그대로 걸리도록하기위해서 hidden값으로 검색조건 받아옴
	myGrid.setColumnIds("SEQNO,DEPTID,DEPTDESC,STARTDATE,ENDDATE,EQCTGID,EQCTGDESC,PLANTID,PLANTDESC" + dateStr);
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,DEPTID,<bean:message key='LABEL.deptDesc'/>,STARTDATE,ENDDATE,EQCTGID,EQCTGDESC,PLANTID,PLANTDESC" + dateStr);
	myGrid.setColumnHidden(1,true);
 	myGrid.setColumnHidden(3,true);
	myGrid.setColumnHidden(4,true); 
	myGrid.setColumnHidden(5,true); 
	myGrid.setColumnHidden(6,true); 
	myGrid.setColumnHidden(7,true); 
	myGrid.setColumnHidden(8,true); 
	myGrid.setInitWidths("50,100,200,100,100,100,100,100,100"+widthStr);
	myGrid.setColAlign("center,left,left,left,left,left,left,left,left"+colAliStr);
	myGrid.setColTypes("cntr,ro,ro,ro,ro,ro,ro,ro,ro"+colTyStr);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("workListDeptSchedList", this, workListDeptSchedListForm, "DEPTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});

	myGrid.init();
	
	isHeaderLoaded[currentPageId+".gridbox"] = "Y";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	if(checkRequireValue("workListDeptSchedCommonDTO.filterStartDate","<bean:message key='LABEL.period'/>")) return;
    if(checkRequireValue("workListDeptSchedCommonDTO.filterEndDate","<bean:message key='LABEL.period'/>")) return;
	if(checkValidation()) return;
	
	var startDate = workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.filterStartDate'].value;
	var endDate = workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.filterEndDate'].value;
	
	if (getDayInterval(dateToData(startDate),dateToData(endDate))+1 >31) {
		alertMessage1('<bean:message key="MESSAGE.MSG0014"/>');
		return;
	}
	
    initGrid();
    findGridList('Search');
}

function afterSearch(gridId, e)
{
	// 0이상인 column만 색칠
	for(var i = 0; myGrid.getRowsNum() > i; i++)
	{ 
		var rowId = myGrid.getRowId(i);
		if(typeof rowId == "undefined")continue;
		
		myGrid.forEachCell(rowId,function(cellObj,ind){
			var cnt = parseInt(myGrid.cells(rowId,ind).getValue());
			if (cnt > 0) myGrid.setCellTextStyle(rowId,ind,"background-color:purple;");
			if (ind > 8) myGrid.cells(rowId,ind).setValue('');
		});
		
	}
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workListDeptSchedListForm;	
	form.strutsAction.value = '<%=WorkListDeptSchedListAction.LIST_FIND %>';
	
	var url = contextPath + "/workListDeptSchedList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListDeptSchedListForm), "DEPTID", "Y");
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
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.deptDesc'].value = getValueById(myGrid, selectedId,'DEPTDESC');
	// 상세리스트에도 검색했을 당시에 그 조건 그대로 걸리도록
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.startDate'].value = getValueById(myGrid, selectedId,'STARTDATE');
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.endDate'].value = getValueById(myGrid, selectedId,'ENDDATE');
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.eqCtgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.eqCtgDesc'].value = getValueById(myGrid, selectedId,'EQCTGDESC');
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
	workListDeptSchedListForm.elements['workListDeptSchedCommonDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
	
	goCommonTabPage(workListDeptSchedListForm, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workListDeptSchedListDeptList');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	if(checkValidation()) return;
	excelServerAction("workListDeptSchedList", workListDeptSchedListForm);
  }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListDeptSchedList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workListDeptSchedCommonDTO.filterEqCtgId"/>
<html:hidden property="workListDeptSchedCommonDTO.filterPlantId"/>
<html:hidden property="workListDeptSchedCommonDTO.deptId"/>
<html:hidden property="workListDeptSchedCommonDTO.deptDesc"/>
<html:hidden property="workListDeptSchedCommonDTO.startDate"/>
<html:hidden property="workListDeptSchedCommonDTO.endDate"/>
<html:hidden property="workListDeptSchedCommonDTO.eqCtgId"/>
<html:hidden property="workListDeptSchedCommonDTO.eqCtgDesc"/>
<html:hidden property="workListDeptSchedCommonDTO.plantId"/>
<html:hidden property="workListDeptSchedCommonDTO.plantDesc"/>

	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<!-- 기간 -->
				<div class="field">
					<label class="check">기간</label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workListDeptSchedCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workListDeptSchedCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 설비종류  -->
				<div class="field">
					<label>설비종류</label>
					<div class="input_box">
						<html:text property="workListDeptSchedCommonDTO.filterEqCtgDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
				<div class="field">
					<label>공장명</label>
					<div class="input_box">
						<html:text property="workListDeptSchedCommonDTO.filterPlantDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="TAB.pmRatio"/></div>
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