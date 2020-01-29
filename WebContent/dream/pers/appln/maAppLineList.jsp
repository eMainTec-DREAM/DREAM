<%--===========================================================================
목록
author  kim21017
version $Id: maAppLineList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.appln.action.MaAppLineListAction" %>
<%@ page import="dream.pers.appln.action.MaAppLineDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="MENU.APPLINE"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
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
	
	myGrid.enableSmartRendering(true,20);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value = "";
    	return sortColumn("maAppLineList", this, maAppLineListForm, "apprlineId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, {"gridId":"gridbox"}); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maAppLineList.do";
    maAppLineListForm.elements['strutsAction'].value = '<%=MaAppLineListAction.QNA_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maAppLineListForm), "apprlineId", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaAppLineListAction.QNA_LIST_FIND%>');   
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
	maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value = getValueById(myGrid, selectedId,'apprlineId');
	
	goCommonTabPage(maAppLineListForm, <%= MaAppLineDetailAction.QNA_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_apprlineId)
{
	maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value = _apprlineId;
	findGridList('ReloadRow');
	maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maAppLineDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value = getValueById(myGrid, selectedId,'apprlineId');
    maAppLineListForm.elements['strutsAction'].value = '<%=MaAppLineDetailAction.QNA_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maAppLineListForm), 'maAppLineDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maAppLineDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value = "";
	goCommonTabPage(maAppLineListForm, '', pageId);
}


/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
function goDelete(){
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'apprlineId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maAppLineListForm.strutsAction.value = '<%=MaAppLineListAction.QNA_LIST_DELETE%>';
	var url = contextPath + "/maAppLineList.do";
	
    $.post(url,FormQueryString(maAppLineListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose('maAppLineDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maAppLineListForm.elements['maAppLineCommonDTO.apprlineId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maAppLineList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maAppLineCommonDTO.apprlineId"/>

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
				<!-- 결재선명 -->
				<div class="field">
					<label><bean:message key="LABEL.apprLineTitle"/></label>
					<div class="input_box">
						<html:text property="maAppLineCommonDTO.title" tabindex="10"/>
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

