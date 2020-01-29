<%--===========================================================================
메뉴 - 목록
author  kim21017
version $Id: maMenuMngList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.menu.action.MaMenuMngListAction" %>
<%@ page import="dream.consult.program.menu.action.MaMenuMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메뉴 -->
<title><bean:message key='MENU.MENU'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

//그리드명
var myGrid,menuDesc;
var pmenuAc;

function loadPage() 
{
	setFunction();
    initGrid();
}

function setFunction()
{
	pmenuAc = new autoC({"maMenuMngCommonDTO.filterPMenuDesc":"description"});
    pmenuAc.setTable("TACMENU");
    pmenuAc.setAcResultMap({
    	"maMenuMngCommonDTO.filterPMenuId":"menu_id"
    });
    pmenuAc.setAcConditionMap({
    	"auth":"N"
  	   });
    pmenuAc.setKeyName("maMenuMngCommonDTO.filterPMenuId");
    pmenuAc.init();  
    /**서비스구분  */
    acSysDesc("maMenuMngCommonDTO.filterServiceTypeDesc","maWoReqCommonDTO.filterServiceTypeId","SERVICE_TYPE");
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		menuDesc=getValueById(myGrid, rowId,'MENUDESC');
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
    var url = contextPath + "/maMenuMngList.do";
    maMenuMngListForm.elements['strutsAction'].value = '<%=MaMenuMngListAction.MENU_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maMenuMngListForm), "MENUID");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maMenuMngListForm.elements['maMenuMngCommonDTO.menuId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('SearchTree');   
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
	maMenuMngListForm.elements['maMenuMngCommonDTO.menuId'].value = getValueById(myGrid, selectedId,'MENUID');
	goCommonTabPage(maMenuMngListForm, <%= MaMenuMngDetailAction.MENU_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_menuId)
{
	maMenuMngListForm.elements['maMenuMngCommonDTO.menuId'].value = _menuId;
	findGridList('ReloadTreeRow');
	maMenuMngListForm.elements['maMenuMngCommonDTO.menuId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maMenuMngDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maMenuMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maMenuMngListForm.elements['maMenuMngCommonDTO.menuId'].value = "";
	goCommonTabPage(maMenuMngListForm, '', "maMenuMngDetail");
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
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'MENUID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	maMenuMngListForm.strutsAction.value = '<%=MaMenuMngListAction.MENU_LIST_DELETE%>';
	var url = contextPath + "/maMenuMngList.do";
	
	$.post(url,FormQueryString(maMenuMngListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  

function afterDelete(){
	goClose('maMenuMngDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/*
 * 화면보기
 */
function goPage()
{
    var id = "";
    
    id = maMenuMngListForm.elements['maMenuMngCommonDTO.menuId'].value;
    if(id == "undefined" || id == "") {
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }
    if(selectedId == "undefined" || selectedId == "") return;
    
    var menuId = maMenuMngListForm.elements['maMenuMngCommonDTO.menuId'].value;

    var woparam = "maPgMngList";
    
    var url   = contextPath + "/"+woparam+".do";

    var popWidth = 1010;
    var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "isDecoratorName=popupPage"
        +"&maPgMngCommonDTO.strutsAction="
        +"&maPgMngCommonDTO.filterMenuId="+ menuId
        +"&maPgMngCommonDTO.filterMenuDesc="+menuDesc
        +"&ACTION_FUNCTION=goSearch";
  
    openWindowWithPost(url, "PAGELIST_POPUP", param, pos);
    
}
	
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maMenuMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMenuMngCommonDTO.menuId"/><!-- Key -->
<html:hidden property="maMenuMngCommonDTO.filterPMenuId"/>
<html:hidden property="maMenuMngCommonDTO.filterMenuId"/>
<html:hidden property="maMenuMngCommonDTO.filterServiceTypeId"/>
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
				   <!-- 화면 ID -->
	               <div class="field">
		               <label><bean:message key="LABEL.fileName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maMenuMngCommonDTO.filterFileName" tabindex="5"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.menuNo"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maMenuMngCommonDTO.filterMenuNo" tabindex="10"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.pageName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maMenuMngCommonDTO.filterPageDesc" tabindex="20"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.menuName"/></label>
		               <div class="input_box">
		               		<html:text property="maMenuMngCommonDTO.filterMenuDesc" tabindex="30"/>
		               </div>
	               </div>
				<div class="field">
					<label><bean:message key="LABEL.pMenu"/></label>
					<div class="input_box">
						<html:text property="maMenuMngCommonDTO.filterPMenuDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.serviceType"/></label>
					<div class="input_box">
						<html:text property="maMenuMngCommonDTO.filterServiceTypeDesc" tabindex="50" />
						<p class="open_spop"><a><span>조회</span></a></p>
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