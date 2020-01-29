<%--===========================================================================
생산품목 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.product.action.AssetStdProductListAction" %>
<%@ page import="dream.asset.std.product.action.AssetStdProductDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 생산품목 -->
<title><bean:message key='MENU.PRODUCT'/></title>
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
    assetStdProductListForm.elements['assetStdProductCommonDTO.filterIsUse'].value = "Y";
    
    /**사용여부  */
    acSysDesc("assetStdProductCommonDTO.filterIsUse","assetStdProductCommonDTO.filterIsUse","IS_USE");
    
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        selectedId = rowId;
        goOpen();
    });
    
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value = "";
   		return sortColumn("assetStdProductList", this, assetStdProductListForm, "PRODUCTID", ind, direction);
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
    var url = contextPath + "/assetStdProductList.do";
    assetStdProductListForm.elements['strutsAction'].value = '<%=AssetStdProductListAction.PRODUCT_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetStdProductListForm), "PRODUCTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(productId)
{
	assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value = productId;

	findGridList('ReloadRow');
	
	assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value = "";	// 검색시 Tab 이동Key Clear
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
	assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value  = getValueById(myGrid, selectedId, 'PRODUCTID');
	
	goCommonTabPage(assetStdProductListForm, <%= AssetStdProductDetailAction.PRODUCT_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetStdProductDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value  = getValueById(myGrid, selectedId, 'PRODUCTID');
    assetStdProductListForm.elements['strutsAction'].value = '<%=AssetStdProductDetailAction.PRODUCT_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assetStdProductListForm), 'assetStdProductDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetStdProductDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value = "";
	goCommonTabPage(assetStdProductListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'productId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assetStdProductListForm.strutsAction.value = '<%=AssetStdProductListAction.PRODUCT_LIST_DELETE%>';
    var url = contextPath + "/assetStdProductList.do";
    
    $.post(url,FormQueryString(assetStdProductListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetStdProductDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assetStdProductListForm.elements['assetStdProductCommonDTO.productId'].value = "";
    excelServerAction("assetStdProductList", assetStdProductListForm);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetStdProductList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetStdProductCommonDTO.productId"/><!-- Key -->
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
	           	   <!--생산품목명  -->
	               <div class="field">
		               <label><bean:message key="LABEL.productName"/></label>
		               <div class="input_box">
		               		<html:text property="assetStdProductCommonDTO.filterDescription" tabindex="10"/>
		               </div>
	               </div>
	               <!-- 생산품목코드 -->
	               <div class="field">
		               <label><bean:message key="LABEL.productCode"/></label>
                       <div class="input_box">
                            <html:text property="assetStdProductCommonDTO.filterProductNo" tabindex="20"/>
                       </div>
               	   </div>    
                   <!--사용여부  -->   
                   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="assetStdProductCommonDTO.filterIsUse" tabindex="30"/>
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