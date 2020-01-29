<%--===========================================================================
목록
author  euna0207
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.info.action.PersPrivFilterValueAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메시지 수신설정 -->
<title><bean:message key='TAB.msgEmp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">


function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goSelect();    
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	persPrivFilterValueForm.elements['persPrivFilterValueDTO.usrFilterValueId'].value = "";
        return sortColumn("persPrivFilterValueList", this, persPrivFilterValueForm, "USRFILTERVALUEID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	persPrivFilterValueForm.strutsAction.value = '<%=PersPrivFilterValueAction.LIST_FIND%>';
	var url = contextPath + "/persPrivFilterValueList.do";
		
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(persPrivFilterValueForm), "USRFILTERVALUEID", "Y");
}


/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('persPrivFilterValue');
}

function goOpenAction()
{
  
} 
 
  /**
   * 삭제
   */
function goDelete()
{
 	var delArray = getDeletRows(myGrid, 'isDelCheck', 'USRFILTERVALUEID');
 	    
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    persPrivFilterValueForm.strutsAction.value = '<%=PersPrivFilterValueAction.LIST_DELETE%>';
	var url = contextPath + "/persPrivFilterValueList.do";
	
    $.post(url,FormQueryString(persPrivFilterValueForm)+delArray , function(_data){
        afterDelete();
    });
}
  
function afterDelete()
{
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSelect() 
{
    var selectedId = myGrid.getSelectedRowId();
	var ifm = getIframeContent();
    if(typeof ifm.setFormValue == "function") 
    	ifm.setFormValue(JSON.parse(getValueById(myGrid, selectedId, 'SETVALUE')));
    closeLayerPopup();
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/persPrivFilterValueList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="persPrivFilterValueDTO.usrFilterValueId"/><!-- key -->
<html:hidden property="persPrivFilterValueDTO.fileName"/>
<html:hidden property="persPrivFilterValueDTO.userName"/>
<html:hidden property="persPrivFilterValueDTO.userNo"/>
<html:hidden property="persPrivFilterValueDTO.setValue"/>

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
				<!-- 생성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.creDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="persPrivFilterValueDTO.filterCreDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="persPrivFilterValueDTO.filterCreEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="persPrivFilterValueDTO.filterTitle" tabindex="40"/>
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