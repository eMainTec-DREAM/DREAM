<%--===========================================================================
설비유형 선택팝업 Popup
author  kim21017
version $Id: maEqCtgTypeSelect.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.asset.list.action.MaEqCtgTypeSelectAction"%>
<html>
<head>
<!-- 설비유형 -->
<title><bean:message key="LABEL.eqCtgType"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//설비유형
var myGrid;

var selectedEqCtgTypeId; // 설비유형 선택 id

function loadPage() 
{
	initGrid();
	
	findGridList();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,<bean:message key='LABEL.code'/>,<bean:message key='LABEL.eqCtgType'/>,");
	myGrid.setColumnIds("SEQNO,EQCTGTYPE,EQCTGTYPEDESC,PARAM1");
	myGrid.setInitWidths("120,100,450,100");
	myGrid.setColAlign("center,left,left,left");
	myGrid.setColTypes("cntr,ro,ro,ro");
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(3,true);
	myGrid.attachEvent("onXLE",function(grdObj,count){});
	myGrid.attachEvent("onRowSelect",function(id, ind){
		selectedEqCtgTypeId = id;
	});
	//myGrid.setDateFormat("%Y-%m-%d");
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	
	myGrid.init();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.maEqCtgTypeSelectForm;	
	form.strutsAction.value = '<%=MaEqCtgTypeSelectAction.EQCTGTYPE_SELECT_FIND%>';
	var url = contextPath + "/maEqCtgTypeSelect.do";
	myGrid.clearAll();
	setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    	selectedEqCtgTypeId = 1;
    	myGrid.selectRow(selectedEqCtgTypeId-1);
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
	if(typeof selectedEqCtgTypeId=="undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG037"/>');
		return ;
	}
	
	var returnArray = new Array();

	returnArray[0] = getValueById(myGrid, selectedEqCtgTypeId, 'EQCTGTYPE');
    returnArray[1] = getValueById(myGrid, selectedEqCtgTypeId, 'PARAM1');

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
// 	goConfirm();
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/maEqCtgTypeSelect" >
		<html:hidden property="strutsAction" />
		<html:hidden property="maEqCtgTypeSelectDTO.selectedEqCtgType" />
		<div class="section_wrap">
	    <div class="sheader_box">
	        <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.eqCtgType"/></div>
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
	    <div class="article_box" style="border-right:1px solid #eee;">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div>
	</html:form> 
</body>
</html>