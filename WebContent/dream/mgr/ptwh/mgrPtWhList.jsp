<%--===========================================================================
부품창고 List
author  sy.yang
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhListAction" %>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부품창고 -->
<title><bean:message key='MENU.PTWAREHOUSE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var plantAc;

function loadPage() 
{
    initGrid();
  	//공장명
    if(loginUser.filterPlant!='null'){
    	mgrPtWhListForm.elements['mgrPtWhCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	mgrPtWhListForm.elements['mgrPtWhCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
    //공장 자동완성
    plantAc = new autoC({"mgrPtWhCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	   });
    plantAc.setAcResultMap({
        "mgrPtWhCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
   
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
		mgrPtWhListForm.elements['mgrPtWhCommonDTO.wcodeId'].value = "";
    	return sortColumn("mgrPtWhList", this, mgrPtWhListForm, "wcodeId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrPtWhList.do";
    mgrPtWhListForm.elements['strutsAction'].value = '<%=MgrPtWhListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrPtWhListForm), "wcodeId","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wcodeId)
{
	mgrPtWhListForm.elements['mgrPtWhCommonDTO.wcodeId'].value = _wcodeId;
	findGridList('ReloadRow')
	mgrPtWhListForm.elements['mgrPtWhCommonDTO.wcodeId'].value = "";
}

function goSearch()
{
	mgrPtWhListForm.elements['mgrPtWhCommonDTO.wcodeId'].value = "";    // 검색시 Tab 이동Key Clear
	findGridList('Search','<%=MgrPtWhListAction.LIST_FIND%>');
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
	mgrPtWhListForm.elements['mgrPtWhCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
	
	goCommonTabPage(mgrPtWhListForm, <%= MgrPtWhDetailAction.DETAIL_INIT %>, pageId);
} 

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrPtWhDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrPtWhListForm.elements['mgrPtWhCommonDTO.wcodeId'].value =  getValueById(myGrid, selectedId,'wcodeId');
    mgrPtWhListForm.elements['strutsAction'].value = '<%=MgrPtWhDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrPtWhListForm), 'mgrPtWhDetail'); 
} 

/**
 * Excel Export
 */
function goExcel()
{
	//excelAction(myGrid);  
	mgrPtWhListForm.elements['mgrPtWhCommonDTO.wcodeId'].value = "";
 	excelServerAction("mgrPtWhList", mgrPtWhListForm );
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrPtWhList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrPtWhCommonDTO.wcodeId"/><!-- Key -->
<html:hidden property="mgrPtWhCommonDTO.filterWcodeId"/>
<html:hidden property="mgrPtWhCommonDTO.filterPlantId"/>
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
				<!-- 창고명 -->
				<div class="field">
					<label><bean:message key="LABEL.wname"/></label>
					<div class="input_box">
						<html:text property="mgrPtWhCommonDTO.filterWName" tabindex="10"/>
					</div>
				</div>
				
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="mgrPtWhCommonDTO.filterPlantDesc" tabindex="80" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
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