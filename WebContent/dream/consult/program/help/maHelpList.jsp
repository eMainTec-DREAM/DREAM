<%--===========================================================================
목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.help.action.MaHelpListAction" %>
<%@ page import="dream.consult.program.help.action.MaHelpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- HelpDesk -->
<title><bean:message key='MENU.HELPDESK'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">

<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();
/*     
    // 근무여부 - 기본 Y 선택.
    maHelpListForm.elements['maHelpCommonDTO.filterIsJoin'].value = 'Y';
    maHelpListForm.elements['maHelpCommonDTO.filterIsJoinDesc'].value = 'Y';

    if(loginUser.wkctrId!='null'){
    	maHelpListForm.elements['maHelpCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
    	maHelpListForm.elements['maHelpCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
	} */
	
    /** 진행상태  */
    acSysDesc("maHelpCommonDTO.helpdeskStatusDesc","maHelpCommonDTO.helpdeskStatus","HELPDESK_STATUS");
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maHelpListForm.elements['maHelpCommonDTO.helpdeskId'].value = "";
        return sortColumn("maHelpList", this, maHelpListForm, "helpdeskId", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maHelpList.do";
    maHelpListForm.elements['strutsAction'].value = '<%=MaHelpListAction.HELP_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maHelpListForm), "helpdeskId", "Y");
}

function findGridRow(_helpdeskId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	maHelpListForm.elements['maHelpCommonDTO.helpdeskId'].value = _helpdeskId;
	findGridList('ReloadRow');
	maHelpListForm.elements['maHelpCommonDTO.helpdeskId'].value = "";
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maHelpListForm.elements['maHelpCommonDTO.helpdeskId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaHelpListAction.HELP_LIST_FIND%>');   
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
   	maHelpListForm.elements['maHelpCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   	maHelpListForm.elements['maHelpCommonDTO.helpdeskId'].value  = getValueById(myGrid, selectedId, 'helpdeskId');

	goCommonTabPage(maHelpListForm, <%= MaHelpDetailAction.HELP_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maHelpDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maHelpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maHelpListForm.elements['maHelpCommonDTO.helpdeskId'].value = "";
    goCommonTabPage(maHelpListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'helpdeskId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maHelpListForm.strutsAction.value = '<%=MaHelpListAction.HELP_LIST_DELETE%>';
    var url = contextPath + "/maHelpList.do";
    
    $.post(url,FormQueryString(maHelpListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('maHelpDetail', this);
    // goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maHelpListForm.elements['maHelpCommonDTO.helpdeskId'].value = "";
	excelServerAction("maHelpList", maHelpListForm );
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maHelpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maHelpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maHelpCommonDTO.helpdeskId"/><!-- Key -->
<html:hidden property="maHelpCommonDTO.helpdeskStatus"/>
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
	       <div class="article_box">
	           <div class="form_box">
	               <div class="field">
		               <label><bean:message key="LABEL.appReqDate"/></label>
		               <div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maHelpCommonDTO.reqDateFrom" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maHelpCommonDTO.reqDateTo" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
	               </div>
	               <div class="field">
		               <label><bean:message key="LABEL.proStatus"/></label>
                       <div class="input_box">
						<html:text property="maHelpCommonDTO.helpdeskStatusDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
               	   </div>
               	   <div class="field">
                       <label><bean:message key="LABEL.title"/></label>
                       <div class="input_box">
                       	  <html:text property="maHelpCommonDTO.description" tabindex="50"/>
	                   </div>
                   </div>           
                   <div class="field">
                        <label><bean:message key="LABEL.request"/></label>
                        <div class="input_box">
                           <html:text property="maHelpCommonDTO.request" tabindex="60"/>
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