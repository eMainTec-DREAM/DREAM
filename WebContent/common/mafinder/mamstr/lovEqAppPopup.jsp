<%--===========================================================================
설비기안품의서 Popup
author  ssong
version $Id: lovEqAppPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.mafinder.mamstr.action.LovEqAppListAction"%>
<html>
<head>
<!-- 설비기안품의서 -->
<title><bean:message key="LABEL.eqAppList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

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
		goConfirm();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovEqAppList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovEqAppListForm;	
	form.strutsAction.value = '<%=LovEqAppListAction.LOV_EQAPP_FIND%>';
	var url = contextPath + "/lovEqAppList.do";
	myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
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
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

	returnArray[0] = getValueById(myGrid, selectedId,'EQAPPNO'); // no
	returnArray[1] = getValueById(myGrid, selectedId,'EQAPPID'); // ID
    returnArray[2] = getValueById(myGrid, selectedId,'TITLE'); // title
    returnArray[3] = getValueById(myGrid, selectedId,'CONTENTS'); // contents
    returnArray[4] = getValueById(myGrid, selectedId,'RECEIVER'); // receiver
    returnArray[5] = getValueById(myGrid, selectedId,'REQDATE'); // reqDate
    returnArray[6] = getValueById(myGrid, selectedId,'APPDATE'); // appDate
	

	var dirType = lovEqAppListForm.elements['lovEqAppListDTO.codeType'].value;
	
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValue(returnArray, dirType);
// 	self.close();
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
	<html:form action="/lovEqAppList" >
		<html:hidden property="lovEqAppListDTO.extCode1" />
		<html:hidden property="lovEqAppListDTO.extCode2" />
		<html:hidden property="lovEqAppListDTO.codeType" />
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
						<!-- 품의번호 -->
						<label><bean:message key="LABEL.eqAppId"/></label>
						<div class="input_box">
							<input type='text' name="lovEqAppListDTO.eqAppId" />
						</div>
					</div>
					<div class="field">
						<!-- 기안일자 -->
						<label><bean:message key="LABEL.reqDate"/></label>
						<div class="input_box">
							<input type='text' name="lovEqAppListDTO.reqDate" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
					<div class="field">
						<!-- 제목 -->
						<label><bean:message key="LABEL.title"/></label>
						<div class="input_box">
							<input type='text' name="lovEqAppListDTO.title" />
						</div>
					</div>
					<div class="field">
						<!-- 결재일자 -->
						<label><bean:message key="LABEL.appDate"/></label>
						<div class="input_box">
							<input type='text' name="lovEqAppListDTO.appDate" />
							<p class="open_calendar"><span>날짜</span></p>
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