<%--===========================================================================
부품창고
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.warehouse.action.MaPtWhListAction" %>
<%@ page import="dream.consult.comp.warehouse.action.MaPtWhDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 부품창고 -->
<title><bean:message key="MENU.PTSTOCK"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var wareHouseAc;

function loadPage() 
{
	initGrid();
	
	wareHouseAc = new autoC({"maPtWhCommonDTO.filterWname":"wname"});
    wareHouseAc.setAcDConditionMap({
    	"comp_no":"maPtWhCommonDTO.filterCompNo",
    	"wh_categ":"PART"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPtWhCommonDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
    compAc = new autoC({"maPtWhCommonDTO.filterCompDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("maPtWhCommonDTO.filterCompNo");
    compAc.setAcResultMap({
        "maPtWhCommonDTO.filterCompNo":"comp_no",
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
    	maPtWhListForm.elements['maPtWhCommonDTO.wcodeId'].value = "";
        return sortColumn("maPtWhList", this, maPtWhListForm, "WCODEID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtWhList.do";

    maPtWhListForm.elements['strutsAction'].value = '<%=MaPtWhListAction.PTWH_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtWhListForm), "WCODEID", "Y");
    
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPtWhListForm.elements['maPtWhCommonDTO.compNo'].value = "";     // 검색시 Tab 이동Key Clear
	maPtWhListForm.elements['maPtWhCommonDTO.wcodeId'].value = "";     // 검색시 Tab 이동Key Clear
	findGridList('Search');
}


function findGridRow(compNo, wcodeId)
{
	maPtWhListForm.elements['maPtWhCommonDTO.compNo'].value 	= compNo;
	maPtWhListForm.elements['maPtWhCommonDTO.wcodeId'].value 	= wcodeId;
	findGridList('ReloadRow');
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
    maPtWhListForm.elements['maPtWhCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'COMPNO');
    maPtWhListForm.elements['maPtWhCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'WCODEID');

    goCommonTabPage(maPtWhListForm, <%=MaPtWhDetailAction.PTWH_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPtWhDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtWhListForm.elements['maPtWhCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'COMPNO');
    maPtWhListForm.elements['maPtWhCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'WCODEID');
    maPtWhListForm.elements['strutsAction'].value = '<%=MaPtWhDetailAction.PTWH_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtWhListForm), 'maPtWhDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtWhDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtWhListForm.elements['maPtWhCommonDTO.wcodeId'].value = "";
    goCommonTabPage(maPtWhListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'WCODEID', 'COMPNO'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtWhListForm.strutsAction.value = '<%=MaPtWhListAction.PTWH_LIST_DELETE%>';
    var url = contextPath + "/maPtWhList.do";

    $.post(url,FormQueryString(maPtWhListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPtWhDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtWhListForm.elements['maPtWhCommonDTO.wcodeId'].value = "";
    excelServerAction("maPtWhList",maPtWhListForm);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtWhList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtWhCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maPtWhCommonDTO.filterCompNo"/><!-- Key -->
<html:hidden property="maPtWhCommonDTO.wcodeId"/><!-- Key -->
<html:hidden property="maPtWhCommonDTO.filterWcodeId"/>
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
                       <label><bean:message key="LABEL.compDesc"/></label>
                       <div class="input_box">
                            <html:text property="maPtWhCommonDTO.filterCompDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a><span>조회</span></a>
                            </p>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.wname"/></label>
                       <div class="input_box">
                            <html:text property="maPtWhCommonDTO.filterWname" tabindex="10"/>
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
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
     </div> <!--  End of section_wrap -->

</html:form> 
</body>
</html>