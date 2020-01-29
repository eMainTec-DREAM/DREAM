<%--===========================================================================
CostCenter - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.ctctr.action.AssetStdCtctrListAction" %>
<%@ page import="dream.asset.std.ctctr.action.AssetStdCtctrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- CostCenter -->
<title><bean:message key='MENU.CTCTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var plantAc;

function loadPage() 
{
    // 사용여부 - 기본 선택.
    assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.filterIsUse'].value = "Y";
    
    /**사용여부  */
    acSysDesc("assetStdCtctrCommonDTO.filterIsUse","assetStdCtctrCommonDTO.filterIsUse","IS_USE");
    
  	//공장명
    if(loginUser.filterPlant!='null'){
       assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
       assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
    
    plantAc = new autoC({"assetStdCtctrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetStdCtctrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    if(typeof exLoadPage == "function") exLoadPage();
    
    initGrid();
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
   		assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value = "";
   		return sortColumn("assetStdCtctrList", this, assetStdCtctrListForm, "CTCTRID", ind, direction);
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
    var url = contextPath + "/assetStdCtctrList.do";
    assetStdCtctrListForm.elements['strutsAction'].value = '<%=AssetStdCtctrListAction.CTCTR_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetStdCtctrListForm), "CTCTRID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(ctctrId)
{
	assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value = ctctrId;

	findGridList('ReloadRow');
	
	assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value = "";	// 검색시 Tab 이동Key Clear
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
	assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value  = getValueById(myGrid, selectedId, 'CTCTRID');
	
	goCommonTabPage(assetStdCtctrListForm, <%= AssetStdCtctrDetailAction.CTCTR_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetStdCtctrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value  = getValueById(myGrid, selectedId, 'CTCTRID');
    assetStdCtctrListForm.elements['strutsAction'].value = '<%=AssetStdCtctrDetailAction.CTCTR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assetStdCtctrListForm), 'assetStdCtctrDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetStdCtctrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value = "";
	goCommonTabPage(assetStdCtctrListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ctctrId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assetStdCtctrListForm.strutsAction.value = '<%=AssetStdCtctrListAction.CTCTR_LIST_DELETE%>';
    var url = contextPath + "/assetStdCtctrList.do";
    
    $.post(url,FormQueryString(assetStdCtctrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetStdCtctrDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assetStdCtctrListForm.elements['assetStdCtctrCommonDTO.ctctrId'].value = "";
   excelServerAction("assetStdCtctrList", assetStdCtctrListForm);
// 	excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetStdCtctrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetStdCtctrCommonDTO.ctctrId"/><!-- Key -->
<html:hidden property="assetStdCtctrCommonDTO.filterPlantId"/>
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
	           	   <!--CostCenter명  -->
	               <div class="field">
		               <label><bean:message key="LABEL.ctctrName"/></label>
		               <div class="input_box">
		               		<html:text property="assetStdCtctrCommonDTO.filterDescription" tabindex="10"/>
		               </div>
	               </div>
	               <!-- CostCenter코드 -->
	               <div class="field">
		               <label><bean:message key="LABEL.ctctrCode"/></label>
                       <div class="input_box">
                            <html:text property="assetStdCtctrCommonDTO.filterCtctrNo" tabindex="20"/>
                       </div>
               	   </div>    
                   <!--사용여부  -->   
                   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="assetStdCtctrCommonDTO.filterIsUse" tabindex="30"/>
                            <p class="open_spop"><a><span>조회</span></a></p>
                      </div>
                   </div> 
                   <!-- 공장명  -->
	               <div class="field">
	                  <label><bean:message key="LABEL.plantDesc"/></label>
	                  <div class="input_box">
							<html:text property="assetStdCtctrCommonDTO.filterPlantDesc" tabindex="40" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
	                        </p>
	                   </div>
	               </div>
	               <c:set var="filePath" value="enhance/${compName}/asset/std/ctctr/assetStdCtctrList_${compNo}.jsp" />
					<c:if test="${udf:isExist(filePath)}">
						<c:import charEncoding="UTF-8" url="/enhance/${compName}/asset/std/ctctr/assetStdCtctrList_${compNo}.jsp"></c:import>
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