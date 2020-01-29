<%--===========================================================================
표준항목선택 Popup
author  ssong
version $Id: lovVendorPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.work.pm.std.action.LovStdPointListAction"%>
<html>
<head>
<!-- 표준항목선택 -->
<title><bean:message key="LABEL.stdPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<script language="javascript">
<!--//

//그리드명
var myGrid;

function loadPage() 
{
	initGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		//goConfirm();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox", "goSearch", "lovStdPointList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovStdPointListForm;	
	form.strutsAction.value = '<%=LovStdPointListAction.LOV_STDPOINT_FIND%>';
	var url = contextPath + "/lovStdPointList.do";
	myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'stWrkPointId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	lovStdPointListForm.strutsAction.value = '<%=LovStdPointListAction.LOV_STDPOINT_INPUT %>';
	var url = contextPath + "/lovStdPointList.do";
	$.post(url,FormQueryString(lovStdPointListForm)+delArray , function(_data){
		getIframeContent().goSearch();
		closeLayerPopup();
    });
	
	/* var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

	returnArray[0] = getValueById(myGrid, selectedId, 'vendorId'); // ID
    returnArray[1] = getValueById(myGrid, selectedId, 'vendorNo'); // NO
    returnArray[2] = getValueById(myGrid, selectedId, 'vendorDesc'); // Desc
    returnArray[3] = getValueById(myGrid, selectedId, 'addrNrepName'); // addrNrepName
    returnArray[4] = getValueById(myGrid, selectedId, 'person'); //person
    returnArray[5] = getValueById(myGrid, selectedId, 'office'); //office;
    returnArray[6] = getValueById(myGrid, selectedId, 'mobile'); //mobile;
    returnArray[7] = getValueById(myGrid, selectedId, 'email'); //email;
	

	var dirType = lovStdPointListForm.elements['lovStdPointListDTO.codeType'].value;
	
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValue(returnArray, dirType); */
// 	self.close();
//    closeLayerPopup();
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
	<html:form action="/lovStdPointList" >
		<html:hidden property="lovStdPointListDTO.pmId" />
		<html:hidden property="strutsAction" />
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
						<!-- 점검항목 -->
						<label><bean:message key="LABEL.checkPoint"/></label>
						<div class="input_box">
							<html:text property="lovStdPointListDTO.pointDesc" />
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
					<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
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
					<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
				</div>
			</div>
		</div> <!--  End of section_wrap -->
	</html:form> 
</body>
</html>