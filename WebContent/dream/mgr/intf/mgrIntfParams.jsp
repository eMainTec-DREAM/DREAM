<%--===========================================================================
인터페이스 파라미터 설정 
author  ghlee
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@page import="common.bean.MwareConfig"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.mgr.intf.action.MgrIntfParamsAction"%>
<html>
<head>
<!--인터페이스 파라미터 -->
<title><bean:message key="BUTTON.INTFEXE"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!-- //

var myGrid;
var proGrid;
function loadPage() 
{
	addTitle("mgrIntfCommonDTO.intfNo");
	addTitle("mgrIntfCommonDTO.intfDesc");
	
	initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrIntfParams.do";
    mgrIntfParamsForm.elements['strutsAction'].value = '<%=MgrIntfParamsAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrIntfParamsForm), "INTFMAPID");
}

function goSearch()
{
	editRow(myGrid);
	
	var url = contextPath + "/mgrIntfParams.do";
	var stAct = "<%=MgrIntfParamsAction.LIST_EXEC %>";
	stAct += "&mgrIntfCommonDTO.intfId="+mgrIntfParamsForm.elements['mgrIntfCommonDTO.intfId'].value;
	stAct += "&mgrIntfCommonDTO.intfNo="+mgrIntfParamsForm.elements['mgrIntfCommonDTO.intfNo'].value;
	proGrid = setGridUpdate(url, stAct, myGrid, function(response){
		if(response.valid == "N") {
			response.invalidIds.forEach(function (item, index, array) {
				myGrid.setCellTextStyle(item,getCoumnIdx(myGrid, "SRCFIELDVALUE"),"border-bottom:2px solid red;");
			});
			alertMessage1('<bean:message key="MESSAGE.MSG0115"/>');
		}
		else if(response.valid == "Y") {
			alertMessage1('<bean:message key="MESSAGE.MSG0116"/>');
			
			frm = getIframeContent();
		}
	},'{}');
	
	setColumnType(myGrid,"SRCFIELDVALUE","ed"); //EDIT
	
	findGridList('Search');
}

function goPlay()
{
	//if no params, make empty row
	if(myGrid.getRowsNum()==0) myGrid.addRow((new Date()).valueOf(),"");
	
	for(var i = 0; myGrid.getRowsNum()>i; i++)
	{
		proGrid.setUpdated(myGrid.getRowId(i), true);
	}
	proGrid.sendData();
}

function goExcel()
{
	excelAction(myGrid);
}

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/mgrIntfParams.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrIntfCommonDTO.intfId" />
<html:hidden property="mgrIntfCommonDTO.intfNo" />
<html:hidden property="mgrIntfCommonDTO.intfDesc" />
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
    	<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.docCountrNo"/></div>
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
    </div> <!--  end section_wrap -->
</html:form> 
</body>
</html>