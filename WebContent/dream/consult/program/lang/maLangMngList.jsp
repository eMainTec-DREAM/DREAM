<%--===========================================================================
다국어 - 목록
author  kim21017
version $Id: maLangMngList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.lang.action.MaLangMngListAction" %>
<%@ page import="dream.consult.program.lang.action.MaLangMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 다국어 -->
<title><bean:message key='MENU.LANG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
    initGrid();
    
    acSysDesc("maLangMngCommonDTO.filterKeyTypeDesc","maLangMngCommonDTO.filterKeyType","KEY_TYPE");
    acSysDesc("maLangMngCommonDTO.filterLangDesc","maLangMngCommonDTO.filterLang","LANG");
}

/**
 * 그리드 초기화
 */
function initGrid()
{	
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.enableSmartRendering(true, 500);
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
    var url = contextPath + "/maLangMngList.do";
    maLangMngListForm.elements['strutsAction'].value = '<%=MaLangMngListAction.LANG_LIST_FIND%>';
    
/*     myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maLangMngListForm), function(_data){
    	myGrid.parse(_data,"js");
    }); */
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maLangMngListForm), "LANGID", "Y");
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maLangMngListForm.elements['maLangMngCommonDTO.langId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

function findGridRow(langId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	maLangMngListForm.elements['maLangMngCommonDTO.langId'].value 	= langId;
	
	findGridList('ReloadRow');
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
	maLangMngListForm.elements['maLangMngCommonDTO.langId'].value 	= getValueById(myGrid, selectedId,'LANGID');
	
	goCommonTabPage(maLangMngListForm, <%= MaLangMngDetailAction.LANG_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen(){
	goTabPage('maLangMngDetail');
}
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maLangMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maLangMngListForm.elements['maLangMngCommonDTO.langId'].value = "";	
	goCommonTabPage(maLangMngListForm, '', pageId);
}

/**
 * 삭제
*/
function goDelete()
{
 	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'LANGID'); //Grid, check box column seq, pk column seq,ext column seq
 	//체크된게 없으면 return
 	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	 
	maLangMngListForm.strutsAction.value = '<%=MaLangMngListAction.LANG_LIST_DELETE%>';
 	var url = contextPath + "/maLangMngList.do";
 	
 	$.post(url,FormQueryString(maLangMngListForm)+delArray , function(_data){
    	afterDelete();
    });
}
 
function afterDelete()
{
	goClose('maLangMngDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goExcel()
{
	excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maLangMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maLangMngCommonDTO.langId"/><!-- Key -->
<html:hidden property="maLangMngCommonDTO.filterLang"/><!-- Key -->
<html:hidden property="maLangMngCommonDTO.filterKeyType"/>
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
			
				<div class="field">
					<label><bean:message key="LABEL.language"/></label>
					<div class="input_box">
						<html:text property="maLangMngCommonDTO.filterLangDesc" tabindex="10" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.keyType"/></label>
					<div class="input_box">
						<html:text property="maLangMngCommonDTO.filterKeyTypeDesc" tabindex="10" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.keyName"/></label>
					<div class="input_box">
						<html:text property="maLangMngCommonDTO.filterKeyName" tabindex="20"/>
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
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>