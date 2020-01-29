<%--===========================================================================
요청접수율(처리자) 상세
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.rpt.woreqrate.action.ReqRptWoReqRateDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 요청접수율(처리자) 상세 -->
<title><bean:message key='MENU.WOREQRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var selectedGridId;

function loadPage() 
{
	setTitle("reqRptWoReqRateDetailDTO.deptDesc");
    
	initGrid();
}

var myGrid;

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500)
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedGridId = rowId;
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("reqRptWoReqRateDetailList", this, reqRptWoReqRateDetailForm, "REQID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}

function goSearch()
{
	findGridList('Search');   
}

function findGridList(sheetAction)
{
	
	var form = document.reqRptWoReqRateDetailForm;	
	form.strutsAction.value = '<%=ReqRptWoReqRateDetailAction.DETAIL_FIND %>';

    var url = contextPath + "/reqRptWoReqRateDetailList.do";
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqRptWoReqRateDetailForm));
}

/**
* 접수리스트 보기
*/
function goWorkreslist(){
	if(typeof selectedGridId == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
  var url   = contextPath + "/maWoReqList.do";

  var popWidth = 1010;
  var popHeight = 640;

  // pop up이 중앙에 위치하게 한다.
  var TopPosition  = (screen.height/2 - popHeight/2);
  var LeftPosition = (screen.width/2 - popWidth/2);

  var pos = "width=" + popWidth + ",height=" + popHeight + "" +
            ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
  pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
  
  var param = "isDecoratorName=popupPage"+
  				"&strutsAction=&maWoReqCommonDTO.filterReqStartDate="+parent.reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterStartDate'].value.replace(/-/gi,"")+
  				"&maWoReqCommonDTO.filterReqEndDate="+parent.reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterEndDate'].value.replace(/-/gi,"")+
  				"&maWoReqCommonDTO.filterRecEmpId="+getValueById(myGrid, selectedGridId,'REQID')+
  				"&maWoReqCommonDTO.filterRecEmpDesc="+getValueById(myGrid, selectedGridId,'REQBY')+
  				"&maWoReqCommonDTO.filterPlantId="+parent.reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterPlantId'].value+
  				"&maWoReqCommonDTO.filterPlantDesc="+parent.reqRptWoReqRateListForm.elements['reqRptWoReqRateCommonDTO.filterPlantDesc'].value;
  openWindowWithPost(url, "WORESLIST_POPUP", param, pos);
  
}
/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelAction(myGrid);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqRptWoReqRateDetailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="reqRptWoReqRateDetailDTO.deptId"/>
<html:hidden property="reqRptWoReqRateDetailDTO.deptDesc"/>
<html:hidden property="reqRptWoReqRateDetailDTO.startDate"/>
<html:hidden property="reqRptWoReqRateDetailDTO.endDate"/>
<html:hidden property="reqRptWoReqRateDetailDTO.isReqDate"/>

	    <div class="article_box" id="listBox">
	        <div class="grid_area">
	            <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
	        </div>
	    </div>


</html:form> 
</body>
</html>