<%--===========================================================================
Ref테이블 팝업  Popup
author  kim21017
version $Id: lovRefColumnPopup.jsp,v 1.1 2016/02/18 09:12:01 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.consult.program.table.action.LovRefColumnListAction"%>
<html>
<head>
<!-- Ref테이블 -->
<title><bean:message key="LABEL.refTable"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;
var columnGrid;
function loadPage() 
{
	//alert(lovRefColumnListForm.elements['lovRefColumnListDTO.extCode1'].value);
	initGrid();
	initColumnGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setImageSize(1,1);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	myGrid.attachEvent("onRowSelect", function(id,ind){
		//alert(myGrid.cells(id, myGrid.getColIndexById("TABLEID")).getValue());
// 		//수치면서 실행률이 아닌 값을 클릭 했을 때.
/* 		if(ind>5){
			if(!isChartLoading){
				isChartLoading = true;
				drawChart(id,ind);
			}
		} */
		findColGridList(myGrid.cells(id, myGrid.getColIndexById("TABLEID")).getValue());
	});
	setHeader(myGrid, "gridbox", "goSearch", "lovRefColumnList"); // grid, grid id
}

/**
 * 그리드 초기화
 */
function initColumnGrid(tableId)
{
	columnGrid = new dhtmlXGridObject('columngridbox');
	columnGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	columnGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	columnGrid.setImageSize(1,1);
	columnGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"columngridbox")}); columnGrid.init();
	setHeader(columnGrid, "columngridbox", "goSearch", "lovRefColumnList"); // grid, grid id
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovRefColumnListForm;	
	form.strutsAction.value = '<%=LovRefColumnListAction.LOV_WKCTR_FIND%>';
	var url = contextPath + "/lovRefColumnList.do"; 
	myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    });
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findColGridList(tableId)
{
	var form = document.lovRefColumnListForm;	
	form.strutsAction.value = '<%=LovRefColumnListAction.LOV_COL_FIND%>';
	form.elements['lovRefColumnListDTO.selectedTableId'].value= tableId;
	var url = contextPath + "/lovRefColumnList.do"; 
	columnGrid.clearAll(); setLoading("columngridbox");
    $.post(url,FormQueryString(form), function(_data){
    	columnGrid.parse(_data,"js");
    });
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	var returnArray = new Array();
	var selectedId=columnGrid.getSelectedRowId();
    if(selectedId==null) return;
    returnArray[0] = getValueById(columnGrid, selectedId,3); // Ref Table Name
	returnArray[1] = getValueById(columnGrid, selectedId,0); // Ref Column Name
    returnArray[2] = getValueById(columnGrid, selectedId,2); // Table Col Id
    returnArray[3] = getValueById(columnGrid, selectedId,1); // Column Description
    returnArray[4] = getValueById(columnGrid, selectedId,4); // Table Description
    returnArray[5] = getValueById(columnGrid, selectedId,5); // Table Id
    returnArray[6] = 'lovRefColumnPopup'; // Table Id

    var dirType = lovRefColumnListForm.elements['lovRefColumnListDTO.codeType'].value;
	
    getIframeContent().setLovValue(returnArray, dirType);
    closeLayerPopup();
}

/**
 * 검색
 */
function goSearch()
{
	findGridList();
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovRefColumnList" >
		<html:hidden property="lovRefColumnListDTO.extCode1" />
		<html:hidden property="lovRefColumnListDTO.extCode2" />
		<html:hidden property="lovRefColumnListDTO.codeType" />
		<html:hidden property="lovRefColumnListDTO.selectedTableId" />
		<html:hidden property="strutsAction"/>
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<div class="field">
						<!-- 작업그룹No -->
						<label><bean:message key="LABEL.table"/></label>
						<div class="input_box">
							<input type='text' name="lovRefColumnListDTO.table" />
						</div>
					</div>
					<div class="field">
						<!-- 테이블명 -->
						<label><bean:message key="LABEL.tableDesc"/></label>
						<div class="input_box">
							<input type='text' name="lovRefColumnListDTO.tableDesc" />
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
					<div class="stitle_tx"><bean:message key="LABEL.table"/></div>
				</div>
				<div class="function_box list">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display:none;">
						</div>
					</div>

					<div class="fb_group2">
					</div>
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box" id="listBox">
				<div class="grid_area">
					<div id="gridbox" style="width:100%; height:100px; background-color:white;"></div>
				</div>
			</div>
		</div> <!--  End of section_wrap -->
			<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" ><bean:message key="LABEL.column"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
					<a href="javascript:goConfirm();" class="b_confirm"><span><bean:message key="BUTTON.SELECT"/></span></a>
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="columngridbox" style="width:100%; height:100px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
	</html:form> 
</body>
</html>