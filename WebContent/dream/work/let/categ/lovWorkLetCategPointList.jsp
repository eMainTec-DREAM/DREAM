<%--===========================================================================
안전작업유형 상세코드 Popup
author  syyang
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
<%@ page import="dream.work.let.categ.action.LovWorkLetCategPointListAction"%>
<html>
<head>
<!-- 안전작업유형 검색 -->
<title><bean:message key="LABEL.woLetCtgType"/></title>
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
	
    // 사용여부
    acSysDesc("workLetCategCommonDTO.isUse","workLetCategCommonDTO.isUse","IS_USE");
    
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("lovWorkLetCategPointList", this, lovWorkLetCategPointListForm, "WOLETCTGPOINTID", ind, direction);
    });
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovWorkLetCategPointList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovWorkLetCategPointListForm;	
	form.strutsAction.value = '<%=LovWorkLetCategPointListAction.LOV_WO_LET_CATEG_POINT_AC_FIND%>';
	var url = contextPath + "/lovWorkLetCategPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "WOLETCTGPOINTID", "Y");
}

function goSelect()
{
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "WOLETCTGPOINTID");
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
	<html:form action="/lovWorkLetCategPointList" >
	
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovWorkLetCategPointListDTO.extCode1" />
		<html:hidden property="lovWorkLetCategPointListDTO.multiSelect" />
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
			</div>
			<!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
                    <!-- 점검항목 -->
					<div class="field">
						<label><bean:message key="LABEL.checkPoint"/></label>
						    <div class="input_box">
							<html:text property="lovWorkLetCategPointListDTO.checkPoint" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 사용여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
    						<html:text property="lovWorkLetCategPointListDTO.isUse" />
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