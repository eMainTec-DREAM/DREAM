<%--===========================================================================
메일수신자설정 - 목록
author  kim21017
version $Id: maMailList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.mail.action.MaMailListAction" %>
<%@ page import="dream.mgr.mail.action.MaMailDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key="MENU.MAILSET"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
var beforePageId = '';
var detailPageAc;
function loadPage() 
{
    initGrid();
    
    detailPageAc = new autoC({"maMailCommonDTO.mailDetailPageId":"param1"});
	detailPageAc.setTable("TACDSYSD");
	detailPageAc.setAcConditionMap({
        "list_type" : "MAIL_SCOPE_TYPE"
       ,"is_use" : "Y"
    });
	detailPageAc.setAcResultMap({
        "maMailCommonDTO.mailScopeTypeId":"cdsysd_no"
    });
	detailPageAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});

    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maMailListForm.elements['maMailCommonDTO.mailListId'].value = "";
    	return sortColumn("maMailList", this, maMailListForm, "MAILLISTID", ind, direction);
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maMailList.do";
    maMailListForm.elements['strutsAction'].value = '<%=MaMailListAction.MAIL_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maMailListForm), "MAILLISTID", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maMailListForm.elements['maMailCommonDTO.mailListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaMailListAction.MAIL_LIST_FIND%>');   
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
	var form = document.maMailListForm;
	
	form.elements['maMailCommonDTO.mailListId'].value = getValueById(myGrid, selectedId,'MAILLISTID');
	
	goCommonTabPage(form, <%= MaMailDetailAction.MAIL_DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_mailListId)
{
	maMailListForm.elements['maMailCommonDTO.mailListId'].value = _mailListId;
	findGridList('ReloadRow');
	maMailListForm.elements['maMailCommonDTO.mailListId'].value = "";
}
function afterAutoCmpt(code)
{
	if(code=="maMailCommonDTO.mailDetailPageId")
	{
		setAfterSelect(maMailListForm.elements['maMailCommonDTO.mailDetailPageId'].value);
	}
}
/**
 * 상세열기
 */
function goOpen(rowId)
{
	var detailPage  = getValueById(myGrid, rowId,'DETAILPAGE');
	var detailMailScopeTypeId = getValueById(myGrid, rowId,'MAILSCOPETYPEID');
	maMailListForm.elements['maMailCommonDTO.mailDetailPageId'].value = detailPage;
	maMailListForm.elements['maMailCommonDTO.mailScopeTypeId'].value = detailMailScopeTypeId;
	
	goTabPage(detailPage);	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;

	var detailPage  = getValueById(myGrid, selectedId,'DETAILPAGE');
    maMailListForm.elements['maMailCommonDTO.mailListId'].value = getValueById(myGrid, selectedId,'MAILLISTID');
    maMailListForm.elements['strutsAction'].value = '<%=MaMailDetailAction.MAIL_DETAIL_INIT%>';
    
    openQuickTabPage(FormQueryString(maMailListForm), detailPage); 
} 

 /**
  * 생성
  */
function goCreate()
{
	goClose(beforePageId,this);
	  //범위타입 LOV 
	  detailPageAc.openLov();
	  maMailListForm.elements['maMailCommonDTO.mailListId'].value = "";
}
function setAfterSelect(detailPageId){
	 var detailPage = detailPageId;
	 beforePageId = detailPage;
	 
	 goCommonTabPage(maMailListForm, '', detailPage);
 }

function goCreateAction(pageId)
{
}

/**
 * Excel Export
 */
function goExcel()
{
	maMailListForm.elements['maMailCommonDTO.mailListId'].value = "";
	excelServerAction("maMailList", maMailListForm ); 
    //excelAction(myGrid);
}

/**
 * 삭제
 */
function goDelete(){
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'MAILLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maMailListForm.strutsAction.value = '<%=MaMailListAction.MAIL_LIST_DELETE%>';
	var url = contextPath + "/maMailList.do";
	
    $.post(url,FormQueryString(maMailListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose(beforePageId,this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maMailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMailCommonDTO.mailListId"/><!-- Key -->
<html:hidden property="maMailCommonDTO.mailDetailPageId"/>
<html:hidden property="maMailCommonDTO.mailScopeTypeId"/>
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
		<div class="article_box" >
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="maMailCommonDTO.filterMailDesc" tabindex="10"/>
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
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>

