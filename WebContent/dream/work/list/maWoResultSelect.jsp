<%--===========================================================================
작업 형태, 종류 선택팝업 Popup
author  ssong
version $Id: maWoResultSelect.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.work.list.action.MaWoResultSelectAction"%>
<html>
<head>
<!-- 작업종류&작업형태 -->
<title><bean:message key="LABEL.woType"/>&<bean:message key="LABEL.pmType"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//작업유형
var woTypeGrid;
//직압향테
var pmTypeGrid;

var selectedWoTypeId; // 작업종류 선택 id
var selectedPmTypeId; // 작업형태 선태 id

function loadPage() 
{
	initWoTypeGrid();
	initPmTypeGrid();
	
	findWoTypeGridList();
}

/**
 * 그리드(작업유형) 초기화
 */
function initWoTypeGrid()
{
	woTypeGrid = new dhtmlXGridObject('woTypeBox');
    woTypeGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	woTypeGrid.setHeader("<bean:message key='LABEL.seqNo'/>,<bean:message key='LABEL.woType'/>,<bean:message key='LABEL.woType'/>,");
	woTypeGrid.setColumnIds("WOTYPESEQNO,WOTYPE,WOTYPEDESC,PARAM");
	woTypeGrid.setInitWidths("60,150,280,100");
	woTypeGrid.setColAlign("center,left,left,left");
	woTypeGrid.setColTypes("cntr,ro,ro,ro");
	woTypeGrid.setColumnHidden(1,true);
	woTypeGrid.setColumnHidden(3,true);
	woTypeGrid.attachEvent("onXLE",function(grdObj,count){});
	woTypeGrid.attachEvent("onRowSelect",function(id, ind){
		selectedWoTypeId = id;
		maWoResultSelectForm.elements['maWoResultSelectDTO.selectedPmType'].value = getValueById(woTypeGrid, selectedWoTypeId, 'PARAM');
		findPmTypeGridList('<%=MaWoResultSelectAction.WO_SELECT_TYPE_FIND%>');
	});
	
	woTypeGrid.init();
}
/**
 * 그리드 (작업형태)초기화
 */
function initPmTypeGrid()
{
	pmTypeGrid = new dhtmlXGridObject('pmTypeBox');
    pmTypeGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	pmTypeGrid.setHeader("<bean:message key='LABEL.seqNo'/>,<bean:message key='LABEL.pmType'/>,<bean:message key='LABEL.pmType'/>,,<bean:message key='LABEL.remark'/>");
	pmTypeGrid.setColumnIds("PMTYPESEQNO,PMTYPE,PMTYPEDESC,PARAM,REMARK");
	pmTypeGrid.setInitWidths("60,100,100,50,190");
	pmTypeGrid.setColAlign("center,left,left,left,left");
	pmTypeGrid.setColTypes("cntr,ro,ro,ro,ro");
	pmTypeGrid.setColumnHidden(1,true);
	pmTypeGrid.setColumnHidden(3,true);
	pmTypeGrid.attachEvent("onXLE",function(grdObj,count){});
	pmTypeGrid.attachEvent("onRowSelect",function(id, ind){
		selectedPmTypeId = id;
	});
	pmTypeGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	
	pmTypeGrid.init();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findWoTypeGridList()
{
	var form = document.maWoResultSelectForm;	
	form.strutsAction.value = '<%=MaWoResultSelectAction.WO_SELECT_WOTYPE_FIND%>';
	var url = contextPath + "/maWoResultSelect.do";
	woTypeGrid.clearAll();
	setLoading("woTypeBox");
    $.post(url,FormQueryString(form), function(_data){
    	woTypeGrid.parse(_data,"js");
    	selectedWoTypeId = 1;
    	woTypeGrid.selectRow(selectedWoTypeId-1);
    	maWoResultSelectForm.elements['maWoResultSelectDTO.selectedPmType'].value = getValueById(woTypeGrid, selectedWoTypeId, 'PARAM');
    	findPmTypeGridList('<%=MaWoResultSelectAction.WO_SELECT_TYPE_FIND%>');
    });
}

function findPmTypeGridList(struts)
{
	var form = document.maWoResultSelectForm;	
	form.strutsAction.value = struts;
	var url = contextPath + "/maWoResultSelect.do";
	pmTypeGrid.clearAll(); 
	setLoading("pmTypeBox");
    $.post(url,FormQueryString(form), function(_data){
    	pmTypeGrid.parse(_data,"js");
    	selectedPmTypeId = 1;
    	pmTypeGrid.selectRow(selectedPmTypeId-1);
    });
}

function goSelect()
{
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	if(typeof selectedWoTypeId=="undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0040"/>');
		return ;
	}
	if(typeof selectedPmTypeId=="undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0039"/>');
		return ;
	}
		
	
	var returnArray = new Array();

	returnArray[0] = getValueById(woTypeGrid, selectedWoTypeId, 'WOTYPE');
    returnArray[1] = getValueById(pmTypeGrid, selectedPmTypeId, 'PMTYPE');
    returnArray[2] = getValueById(pmTypeGrid, selectedPmTypeId, 'PARAM');

    getIframeContent().setAfterSelect(returnArray);
    closeLayerPopup();
}

/**
 * 검색
 */
function goSearch()
{
	findGridList();
}
function afterSelectClose(){
	//goConfirm();
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/maWoResultSelect" >
		<html:hidden property="strutsAction" />
		<html:hidden property="maWoResultSelectDTO.selectedWoType" />
		<html:hidden property="maWoResultSelectDTO.selectedPmType" />
		<div class="section_wrap">
	    <div class="sheader_box">
	        <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="MESSAGE.MSG0041"/></div>
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
	    <div class="article_box grid_cul" style="border-right:1px solid #eee;">
            <div class="grid_area">
            	<div id="woTypeBox" style="width:100%; height:290px; background-color:white;"></div>
            </div>
	 	</div>
	 	<div class="article_box grid_cul">
            <div class="grid_area">
            	<div id="pmTypeBox" style="width:100%; height:290px; background-color:white;"></div>
            </div>
	 	</div>
	 </div>
	</html:form> 
</body>
</html>