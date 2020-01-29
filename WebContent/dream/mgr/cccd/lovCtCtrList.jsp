<%--===========================================================================
CP Popup
author  ssong
version $Id: lovCtCtrPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.mgr.cccd.action.LovCtCtrListAction"%>
<html>
<head>
<!-- CP -->
<title><bean:message key="LABEL.cp"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;
var plantAc;

var conditionFilterMap = {
	"plant":"lovCtCtrListDTO.plantId"
	,"plantDesc":"lovCtCtrListDTO.plantDesc"
};

function loadPage() 
{
	convertCondition();
	
	//공장명
    if(loginUser.filterPlant!='null' && lovCtCtrListForm.elements['lovCtCtrListDTO.plantId'].value == ''){
       lovCtCtrListForm.elements['lovCtCtrListDTO.plantId'].value  = loginUser.filterPlant;
       lovCtCtrListForm.elements['lovCtCtrListDTO.plantDesc'].value  = loginUser.filterPlantDesc;
    }
	
	plantAc = new autoC({"lovCtCtrListDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovCtCtrListDTO.plantId":"plant"
    });
    plantAc.init();
    
    if(typeof exLoadPage == "function") exLoadPage();
    
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
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovCtCtrList"); // grid, grid id
}

function convertCondition()
{
	var paramObj = JSON.parse(M$('param').value);
	for(var key in paramObj){
		if(typeof conditionFilterMap[key] != "undefined" && typeof M$(conditionFilterMap[key]) == "object")
		{
			M$(conditionFilterMap[key]).value = paramObj[key];
			paramObj[key] = '';
			M$('param').value = JSON.stringify(paramObj);
		}
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovCtCtrListForm;	
	form.strutsAction.value = '<%=LovCtCtrListAction.LOV_CTCTR_AC_FIND%>';
	var url = contextPath + "/lovCtCtrList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "ctctr_id", "Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "ctctr_id");
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
	<html:form action="/lovCtCtrList" >
	
	<html:hidden property="currentPageId"/>
	<html:hidden property="keyCode" /><html:hidden property="chName" />
	<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
	<html:hidden property="codeType" />
	<html:hidden property="param" />
	<html:hidden property="label" />
	<html:hidden property="title" />
	
		<html:hidden property="lovCtCtrListDTO.extCode1" />
		<html:hidden property="lovCtCtrListDTO.extCode2" />
		<html:hidden property="lovCtCtrListDTO.codeType" />
		<html:hidden property="lovCtCtrListDTO.plantId" />
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
						<!-- CP코드 -->
						<label><bean:message key="LABEL.cpCode"/></label>
						<div class="input_box">
							<input type='text' name="lovCtCtrListDTO.ctCtrNo" />
						</div>
					</div>
					<div class="field">
						<!-- CP명 -->
						<label><bean:message key="LABEL.cpDesc"/></label>
						<div class="input_box">
							<input type='text' name="lovCtCtrListDTO.ctCtrDesc" />
						</div>
					</div>
					<div class="field">
						<!-- 공장명 -->
						<label><bean:message key="LABEL.plantDesc"/></label>
						<div class="input_box">
							<input type='text' name="lovCtCtrListDTO.plantDesc" />
							<p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                        </p>
						</div>
					</div>
					<c:set var="filePath" value="enhance/${compName}/mgr/cccd/lovCtCtrList_${compNo}.jsp" />
					<c:if test="${udf:isExist(filePath)}">
						<c:import charEncoding="UTF-8" url="/enhance/${compName}/mgr/cccd/lovCtCtrList_${compNo}.jsp"></c:import>
					</c:if>
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