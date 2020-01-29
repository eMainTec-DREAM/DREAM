<%--===========================================================================
메시지타입 상세코드 Popup
author  youngjoo38
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
<%@ page import="dream.pers.priv.info.action.LovMsgCompListAction"%>
<html>
<head>
<!-- 메시지타입 검색 -->
<title><bean:message key="LABEL.msgObjTypeDesc"/></title>
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
	
	// 메시지전송유형
    msgCategAc = new autoC({"lovMsgCompListDTO.filterMsgObjTypeDesc":"msgObjTypeDesc"});
    msgCategAc.setTable("TAMESSAGECATEG");
    msgCategAc.setAcConditionMap({
    	"is_use":"Y"
    });
    msgCategAc.setAcResultMap({
    	"lovMsgCompListDTO.filterMsgObjType":"msgObjType"
    });
    msgCategAc.init();
    
    // 사용여부
    acSysDesc("lovMsgCompListDTO.filterIsUseDesc","lovMsgCompListDTO.filterIsUse","IS_USE");
    // 메일 사용여부
    acSysDesc("lovMsgCompListDTO.filterIsMailUseDesc","lovMsgCompListDTO.filterIsMailUse","IS_USE");
    // SMS 사용여부
    acSysDesc("lovMsgCompListDTO.filterIsSmsUseDesc","lovMsgCompListDTO.filterIsSmsUse","IS_USE");
    
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
        return sortColumn("lovMsgCompList", this, lovMsgCompListForm, "MSGCOMPSETID", ind, direction);
    });
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovMsgCompList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovMsgCompListForm;	
	form.strutsAction.value = '<%=LovMsgCompListAction.LOV_AC_FIND%>';
	var url = contextPath + "/lovMsgCompList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "MSGCOMPSETID", "Y");
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
	setAcValue(myGrid, "MSGCOMPSETID");
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
	<html:form action="/lovMsgCompList" >
	
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovMsgCompListDTO.extCode1" />
		<html:hidden property="lovMsgCompListDTO.multiSelect" />
		<html:hidden property="lovMsgCompListDTO.filterMsgObjType"/> <!-- 메시지타입 -->
		<html:hidden property="lovMsgCompListDTO.filterIsUse"/> 
		<html:hidden property="lovMsgCompListDTO.filterIsSmsUse"/> 
		<html:hidden property="lovMsgCompListDTO.filterIsMailUse"/> 
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
                    <!-- 메시지전송 유형 -->
					<div class="field">
						<label><bean:message key="LABEL.msgObjType"/></label>
						    <div class="input_box">
							<html:text property="lovMsgCompListDTO.filterMsgObjTypeDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 사용여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
    						<html:text property="lovMsgCompListDTO.filterIsUseDesc" />
    						<p class="open_spop"><a><span>조회</span></a></p>
   						</div>
					</div>
					<!-- 메일 사용여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isMailUse"/></label>
                        <div class="input_box">
    						<html:text property="lovMsgCompListDTO.filterIsMailUseDesc" />
    						<p class="open_spop"><a><span>조회</span></a></p>
   						</div>
					</div>
					<!-- SMS 사용여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isSmsUse"/></label>
                        <div class="input_box">
    						<html:text property="lovMsgCompListDTO.filterIsSmsUseDesc" />
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