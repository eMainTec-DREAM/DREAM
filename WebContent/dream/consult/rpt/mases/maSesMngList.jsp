<%--===========================================================================
Session Management
author  kim21017
version $Id: maSesMngList.jsp,v 1.3 2014/02/19 07:01:59 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>

<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c_rt"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.rpt.mases.action.MaSesMngListAction" %>
<%@ page import="dream.consult.rpt.mases.action.MaSesMngDetailAction" %>
<html>
<head>
<!-- 실시간 접속자 -->
<title><bean:message key="MENU.SESSION"/></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
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
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maSesMngList.do";
    maSesMngListForm.elements['strutsAction'].value = '<%=MaSesMngListAction.SES_LOGIN_FIND%>';
    
    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maSesMngListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maSesMngListForm.elements['maSesMngCommonDTO.sessionId'].value = "";
    findGridList('Search', '<%=MaSesMngListAction.SES_LOGIN_FIND%>');   
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
    maSesMngListForm.elements['maSesMngCommonDTO.sessionId'].value 	= getValueById(myGrid, selectedId,'SESSIONID');
	maSesMngListForm.elements['maSesMngCommonDTO.userNo'].value 	= getValueById(myGrid, selectedId,'USERNO');
	maSesMngListForm.elements['maSesMngCommonDTO.userName'].value 	= getValueById(myGrid, selectedId,'USERNAME');
	maSesMngListForm.elements['maSesMngCommonDTO.loginTime'].value 	= getValueById(myGrid, selectedId,'LOGINTIME');
    
	goCommonTabPage(maSesMngListForm, <%=MaSesMngDetailAction.SES_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기버튼
 */
function goOpen()
{
	goTabPage('maSesMngDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maSesMngListForm.elements['maSesMngCommonDTO.sessionId'].value 	= getValueById(myGrid, selectedId,'SESSIONID');
	maSesMngListForm.elements['maSesMngCommonDTO.userNo'].value 	= getValueById(myGrid, selectedId,'USERNO');
	maSesMngListForm.elements['maSesMngCommonDTO.userName'].value 	= getValueById(myGrid, selectedId,'USERNAME');
	maSesMngListForm.elements['maSesMngCommonDTO.loginTime'].value 	= getValueById(myGrid, selectedId,'LOGINTIME');
    maSesMngListForm.elements['strutsAction'].value = '<%=MaSesMngDetailAction.SES_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maSesMngListForm), 'maSesMngDetail'); 
} 

/**
 * 삭제 버튼
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'SESSIONID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	 
	maSesMngListForm.strutsAction.value = '<%=MaSesMngListAction.SES_LOGOUT%>';
	var url = contextPath + "/maSesMngList.do";
	$.post(url,FormQueryString(maSesMngListForm)+delArray , function(_data){
    	afterDelete();
    });
}

 /**
  * Excel Export
  */
 function goExcel()
 {
     excelAction(myGrid);
 }
 

function afterDelete()
{
	//goSearch();
	 alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maSesMngList.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maSesMngCommonDTO.sessionId"/>
	<html:hidden property="maSesMngCommonDTO.userNo"/>
	<html:hidden property="maSesMngCommonDTO.userName"/>
	<html:hidden property="maSesMngCommonDTO.loginTime"/>
	<input type="hidden" name="sessionId" />
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
				<!-- 사용자명 -->
				<div class="field">
					<label><bean:message key="LABEL.userNo"/></label>
					<div class="input_box">
						<html:text property="maSesMngCommonDTO.filterUserNo" tabindex="10"/>
					</div>
				</div>
			</div>
		</div>
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
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
