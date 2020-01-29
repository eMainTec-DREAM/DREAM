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
<%@ page import="dream.consult.comp.user.action.ConsultCompUsrGrpListAction" %>
<%@ page import="dream.consult.comp.user.action.ConsultCompUsrGrpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 권한명 -->
<title><bean:message key='MENU.USRGRP'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var compAc;
function loadPage() 
{
    initGrid();
    
    compAc = new autoC({"consultCompUsrGrpCommonDTO.filterCompDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompUsrGrpCommonDTO.filterCompNo");
    compAc.setAcResultMap({
        "consultCompUsrGrpCommonDTO.filterCompNo":"comp_no"
    });
    compAc.init();
    
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
    	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = "";
        return sortColumn("consultCompUsrGrpList", this, consultCompUsrGrpListForm, "USRGRPID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompUsrGrpList.do";
    consultCompUsrGrpListForm.elements['strutsAction'].value = '<%=ConsultCompUsrGrpListAction.USRGRP_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompUsrGrpListForm), "USRGRPID", "Y");

}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=ConsultCompUsrGrpListAction.USRGRP_LIST_FIND%>');   
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
   	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.compNo'].value = getValueById(myGrid, selectedId,'COMPNO');
   	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = getValueById(myGrid, selectedId,'USRGRPID');
   	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpNo'].value = getValueById(myGrid, selectedId,'USRGRPNO');
    
	goCommonTabPage(consultCompUsrGrpListForm, <%= ConsultCompUsrGrpDetailAction.USRGRP_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrGrpId)
{
	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = _usrGrpId;
	findGridList('ReloadRow');
	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
     goTabPage('consultCompUsrGrpDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.compNo'].value = getValueById(myGrid, selectedId,'COMPNO');
   	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = getValueById(myGrid, selectedId,'USRGRPID');
   	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpNo'].value = getValueById(myGrid, selectedId,'USRGRPNO');
    consultCompUsrGrpListForm.elements['strutsAction'].value = '<%=ConsultCompUsrGrpDetailAction.USRGRP_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultCompUsrGrpListForm), 'consultCompUsrGrpDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultCompUsrGrpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = "";
    goCommonTabPage(consultCompUsrGrpListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
//     excelAction(myGrid);
	consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value = "";
	excelServerAction('consultCompUsrGrpList', consultCompUsrGrpListForm);
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
    
    consultCompUsrGrpListForm.strutsAction.value = '<%=ConsultCompUsrGrpListAction.USRGRP_LIST_DELETE%>';
    var url = contextPath + "/consultCompUsrGrpList.do";

    $.post(url,FormQueryString(consultCompUsrGrpListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultCompUsrGrpDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = consultCompUsrGrpListForm.elements['consultCompUsrGrpCommonDTO.usrGrpId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompUsrGrpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="consultCompUsrGrpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="consultCompUsrGrpCommonDTO.usrGrpId"/><!-- Key -->
<html:hidden property="consultCompUsrGrpCommonDTO.usrGrpNo"/>
<html:hidden property="consultCompUsrGrpCommonDTO.filterCompNo"/>
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
		               <label class="check"><bean:message key="LABEL.compDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="consultCompUsrGrpCommonDTO.filterCompDesc" tabindex="10"/>
	               	   		<p class="open_spop">
                                <a><span>조회</span></a>
                            </p>
	               	   </div>
               	   </div>
                   <div class="field">
		               <label><bean:message key="LABEL.usrGrpDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="consultCompUsrGrpCommonDTO.filterGroupName" tabindex="20"/>
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