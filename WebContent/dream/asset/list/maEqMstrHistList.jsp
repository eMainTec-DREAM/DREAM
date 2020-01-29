<%--===========================================================================
설비변경이력 - 목록
author  kim21017
version $Id: maEqMstrHistList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrHistListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrHistDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 설비변경이력 -->
<title><bean:message key='MENU.EQMSTRHIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch","maEqMstrHistList"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maEqMstrHistList.do";

    maEqMstrHistListForm.elements['strutsAction'].value = '<%=MaEqMstrHistListAction.EQ_MSTR_HIST_LIST_FIND%>';
    
    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maEqMstrHistListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=MaEqMstrHistListAction.EQ_MSTR_HIST_LIST_FIND%>');   
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
	var form = document.maEqMstrHistListForm;
	 
	form.elements['maEqMstrHistListDTO.eqalthistId'].value = getValueById(myGrid, selectedId,'eqalthistId');
	goCommonTabPage(form, <%= MaEqMstrHistDetailAction.EQ_MSTR_HIST_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrHistDetail');	
}
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrHistList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMstrHistListDTO.equipId"/>
<html:hidden property="maEqMstrHistListDTO.itemNo"/>
<html:hidden property="maEqMstrHistListDTO.eqalthistId"/>
	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>
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
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipment"/></label>
					<div class="input_read">
						<html:text property="maEqMstrHistListDTO.equipDesc" readonly="true"/>
					</div>
				</div>
				<!-- 변경일자 -->
				<div class="field">
					<label><bean:message key="LABEL.changeDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maEqMstrHistListDTO.filterChangeFromDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maEqMstrHistListDTO.filterChangeToDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="stitle_box"><bean:message key="LABEL.List"/></div>
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