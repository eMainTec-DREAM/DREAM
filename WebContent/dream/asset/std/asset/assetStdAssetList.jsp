<%--===========================================================================
회계자산 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.asset.action.AssetStdAssetListAction" %>
<%@ page import="dream.asset.std.asset.action.AssetStdAssetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 회계자산 -->
<title><bean:message key='MENU.ACCASSET'/></title>
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
    assetStdAssetListForm.elements['assetStdAssetCommonDTO.filterIsUse'].value = "Y";
    
    /**사용여부  */
    acSysDesc("assetStdAssetCommonDTO.filterIsUse","assetStdAssetCommonDTO.filterIsUse","IS_USE");
    
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
    	assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value = "";
   		return sortColumn("assetStdAssetList", this, assetStdAssetListForm, "ASSETID", ind, direction);
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
    var url = contextPath + "/assetStdAssetList.do";
    assetStdAssetListForm.elements['strutsAction'].value = '<%=AssetStdAssetListAction.ASSET_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetStdAssetListForm), "ASSETID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(assetId)
{
	assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value = assetId;

	findGridList('ReloadRow');
	
	assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value = "";	// 검색시 Tab 이동Key Clear
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
	assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value  = getValueById(myGrid, selectedId, 'ASSETID');
	
	goCommonTabPage(assetStdAssetListForm, <%= AssetStdAssetDetailAction.ASSET_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetStdAssetDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value  = getValueById(myGrid, selectedId, 'ASSETID');
    assetStdAssetListForm.elements['strutsAction'].value = '<%=AssetStdAssetDetailAction.ASSET_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assetStdAssetListForm), 'assetStdAssetDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetStdAssetDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value = "";
	goCommonTabPage(assetStdAssetListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'assetId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assetStdAssetListForm.strutsAction.value = '<%=AssetStdAssetListAction.ASSET_LIST_DELETE%>';
    var url = contextPath + "/assetStdAssetList.do";
    
    $.post(url,FormQueryString(assetStdAssetListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetStdAssetDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value = "";
    excelServerAction("assetStdAssetList", assetStdAssetListForm);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assetStdAssetListForm.elements['assetStdAssetCommonDTO.assetId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetStdAssetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetStdAssetCommonDTO.assetId"/><!-- Key -->
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
	           	   <!--회계자산명  -->
	               <div class="field">
		               <label><bean:message key="LABEL.accAssetName"/></label>
		               <div class="input_box">
		               		<html:text property="assetStdAssetCommonDTO.filterDescription" tabindex="10"/>
		               </div>
	               </div>
	               <!-- 회계자산코드 -->
	               <div class="field">
		               <label><bean:message key="LABEL.accAssetCode"/></label>
                       <div class="input_box">
                            <html:text property="assetStdAssetCommonDTO.filterAssetNo" tabindex="20"/>
                       </div>
               	   </div>    
                   <!--사용여부  -->   
                   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="assetStdAssetCommonDTO.filterIsUse" tabindex="30"/>
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