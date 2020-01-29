<%--===========================================================================
예산계정 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.budget.account.action.BudgetAccountListAction" %>
<%@ page import="dream.budget.account.action.BudgetAccountDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예산계정 -->
<title><bean:message key='MENU.BUDACCNT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

function loadPage() 
{
    initGrid();
    // 사용여부 - 기본 선택.
    budgetAccountListForm.elements['budgetAccountCommonDTO.filterIsUse'].value = "Y";
    
    /**예산계정구분  */
    acSysDesc("budgetAccountCommonDTO.filterAccntTypeDesc","budgetAccountCommonDTO.filterAccntType","ACCNT_TYPE");
    /**사용여부  */
    acSysDesc("budgetAccountCommonDTO.filterIsUse","budgetAccountCommonDTO.filterIsUse","IS_USE");
    
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("budgetAccountList", this, budgetAccountListForm, "ACCNTID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/budgetAccountList.do";
    budgetAccountListForm.elements['strutsAction'].value = '<%=BudgetAccountListAction.LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(budgetAccountListForm), "ACCNTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(accntId)
{
	budgetAccountListForm.elements['budgetAccountCommonDTO.accntId'].value = accntId;

	findGridList('ReloadRow');
	
	budgetAccountListForm.elements['budgetAccountCommonDTO.accntId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	budgetAccountListForm.elements['budgetAccountCommonDTO.accntId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
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
	budgetAccountListForm.elements['budgetAccountCommonDTO.accntId'].value  = getValueById(myGrid, selectedId, 'ACCNTID');
	
	goCommonTabPage(budgetAccountListForm, <%= BudgetAccountDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('budgetAccountDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    budgetAccountListForm.elements['budgetAccountCommonDTO.accntId'].value  = getValueById(myGrid, selectedId, 'ACCNTID');
    budgetAccountListForm.elements['strutsAction'].value = '<%=BudgetAccountDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(budgetAccountListForm), 'budgetAccountDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "budgetAccountDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	budgetAccountListForm.elements['budgetAccountCommonDTO.accntId'].value = "";
	goCommonTabPage(budgetAccountListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'accntId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    budgetAccountListForm.strutsAction.value = '<%=BudgetAccountListAction.LIST_DELETE%>';
    var url = contextPath + "/budgetAccountList.do";
    
    $.post(url,FormQueryString(budgetAccountListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('budgetAccountDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	budgetAccountListForm.elements['budgetAccountCommonDTO.accntId'].value = "";
    excelServerAction("budgetAccountList", budgetAccountListForm);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/budgetAccountList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="budgetAccountCommonDTO.accntId"/><!-- Key -->
<html:hidden property="budgetAccountCommonDTO.filterAccntType"/>
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
	           	   <!--예산계정명  -->
	               <div class="field">
		               <label><bean:message key="LABEL.accntDesc"/></label>
		               <div class="input_box">
		               		<html:text property="budgetAccountCommonDTO.filterAccntDesc" tabindex="10"/>
		               </div>
	               </div>
	               <!-- 예산계정코드 -->
	               <div class="field">
		               <label><bean:message key="LABEL.accntNo"/></label>
                       <div class="input_box">
                            <html:text property="budgetAccountCommonDTO.filterAccntNo" tabindex="20"/>
                       </div>
               	   </div>    
                   <!--예산계정구분  -->   
                   <div class="field">
                        <label><bean:message key="LABEL.accntType"/></label>
                        <div class="input_box">
                           <html:text property="budgetAccountCommonDTO.filterAccntTypeDesc" tabindex="30"/>
                            <p class="open_spop"><a><span>조회</span></a></p>
                      </div>
                   </div> 
                   <!--사용여부  -->   
                   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="budgetAccountCommonDTO.filterIsUse" tabindex="40"/>
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