<%--===========================================================================
권한명 - 목록
author ssong 
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.mgr.usrgrp.action.MaUsrGrpListAction" %>
<%@ page import="dream.mgr.usrgrp.action.MaUsrGrpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 권한명 -->
<title><bean:message key='MENU.USRGRP'/></title>
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
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = "";
    	return sortColumn("maUsrGrpList", this, maUsrGrpListForm, "USRGRPID", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maUsrGrpList.do";
    maUsrGrpListForm.elements['strutsAction'].value = '<%=MaUsrGrpListAction.USRGRP_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maUsrGrpListForm), "USRGRPID", "Y");

}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaUsrGrpListAction.USRGRP_LIST_FIND%>');   
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
   	maUsrGrpListForm.elements['maUsrGrpCommonDTO.compNo'].value = getValueById(myGrid, selectedId,'COMPNO');
   	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = getValueById(myGrid, selectedId,'USRGRPID');
   	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpNo'].value = getValueById(myGrid, selectedId,'USRGRPNO');
    
	goCommonTabPage(maUsrGrpListForm, <%= MaUsrGrpDetailAction.USRGRP_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrGrpId)
{
	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = _usrGrpId;
	findGridList('ReloadRow');
	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
     goTabPage('maUsrGrpDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maUsrGrpListForm.elements['maUsrGrpCommonDTO.compNo'].value = getValueById(myGrid, selectedId,'COMPNO');
   	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = getValueById(myGrid, selectedId,'USRGRPID');
   	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpNo'].value = getValueById(myGrid, selectedId,'USRGRPNO');
    maUsrGrpListForm.elements['strutsAction'].value = '<%=MaUsrGrpDetailAction.USRGRP_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maUsrGrpListForm), 'maUsrGrpDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maUsrGrpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = "";
    goCommonTabPage(maUsrGrpListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
//     excelAction(myGrid);
	maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value = "";
	excelServerAction('maUsrGrpList', maUsrGrpListForm);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'USRGRPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maUsrGrpListForm.strutsAction.value = '<%=MaUsrGrpListAction.USRGRP_LIST_DELETE%>';
    var url = contextPath + "/maUsrGrpList.do";

    $.post(url,FormQueryString(maUsrGrpListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maUsrGrpDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUsrGrpListForm.elements['maUsrGrpCommonDTO.usrGrpId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUsrGrpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maUsrGrpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maUsrGrpCommonDTO.usrGrpId"/><!-- Key -->
<html:hidden property="maUsrGrpCommonDTO.usrGrpNo"/>
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
		               <label><bean:message key="LABEL.groupName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maUsrGrpCommonDTO.filterGroupName" tabindex="10"/>
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