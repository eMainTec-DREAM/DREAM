<%--===========================================================================
예산계정 LOV
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.budget.account.action.LovAccountListAction"%>
<html>
<head>
<!-- 예산계정 -->
<title><bean:message key="MENU.BUDACCNT"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

function loadPage() 
{
	initGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableMultiselect(chkFilter());
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("lovAccountList", this, lovAccountListForm, "ACCNT_ID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovAccountList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovAccountListForm;	
	form.strutsAction.value = '<%=LovAccountListAction.LOV_AC_FIND%>';
	var url = contextPath + "/lovAccountList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovAccountListForm),"ACCNT_ID", "Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "ACCNT_ID");
}

/**
 * 검색
 */
function goSearch()
{
	findGridList('Search');
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovAccountList" >
	<html:hidden property="currentPageId"/>
    <html:hidden property="keyCode" /><html:hidden property="chName" />
    <html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
    <html:hidden property="codeType" />
    <html:hidden property="param" />
    <html:hidden property="label" />
    <html:hidden property="title" />
		
	<html:hidden property="strutsAction" />
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
						<!-- 계정번호 -->
						<label><bean:message key="LABEL.accntNo"/></label>
						<div class="input_box">
							<input type='text' name="lovAccountListDTO.accntNo" />
						</div>
					</div>
					<div class="field">
						<!-- 계정명 -->
						<label><bean:message key="LABEL.accntDesc"/></label>
						<div class="input_box">
							<input type='text' name="lovAccountListDTO.description" />
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