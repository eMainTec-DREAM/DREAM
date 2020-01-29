<%--===========================================================================
사용자 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.consult.consult.user.action.McUserListAction" %>
<%@ page import="dream.consult.consult.user.action.McUserDetailAction" %>
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
<!-- 사용자 -->
<title><bean:message key='MENU.mcUserList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

function loadPage() 
{
	initGrid();
	
}
//onRowSelect  onRowSelect

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
    var url = contextPath + "/mcUserList.do";
    mcUserListForm.elements['strutsAction'].value = '<%=McUserListAction.USER_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mcUserListForm), "USERID");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	mcUserListForm.elements['mcUserCommonDTO.userId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=McUserListAction.USER_LIST_FIND%>');   
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
   	mcUserListForm.elements['mcUserCommonDTO.userId'].value = getValueById(myGrid, selectedId, 'userId');

    goCommonTabPage(mcUserListForm, <%=McUserDetailAction.USER_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_userId)
{
	mcUserListForm.elements['mcUserCommonDTO.userId'].value = _userId;
	findGridList('ReloadRow');
	mcUserListForm.elements['mcUserCommonDTO.userId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mcUserDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mcUserDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    mcUserListForm.elements['mcUserCommonDTO.userId'].value = "";
    goCommonTabPage(mcUserListForm, '', pageId);
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
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'userId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mcUserListForm.strutsAction.value = '<%=McUserListAction.USER_LIST_DELETE%>';
    var url = contextPath + "/mcUserList.do";
    
    $.post(url,FormQueryString(mcUserListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('mcUserDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mcUserList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mcUserCommonDTO.compNo"/><!-- Key -->
<html:hidden property="mcUserCommonDTO.userId"/><!-- Key -->
<html:hidden property="mcUserCommonDTO.filterUsrGrpId"/>
<html:hidden property="mcUserCommonDTO.filterDeptId"/>
<html:hidden property="mcUserCommonDTO.filterEqCtgTypeId"/>
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
		               <label><bean:message key="LABEL.userName1"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="mcUserCommonDTO.filterUserName" tabindex="20"/>
	               	   </div>
               	   </div>
	               
                   <div class="field">
                       <label><bean:message key="LABEL.userId"/></label>
                       <div class="input_box">
                            <html:text property="mcUserCommonDTO.filterUserNo" tabindex="40"/>
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
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>