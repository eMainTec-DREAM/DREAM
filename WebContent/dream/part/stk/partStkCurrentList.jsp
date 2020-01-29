<%--===========================================================================
자재재고
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.stk.action.PartStkCurrentAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자재재고 -->
<title><bean:message key="MENU.PTSTCK"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
 
/** 자동완성 변수 */
function loadPage() 
{
	if(loginUser != null)
	{
		setInitVal("partStkCurrentDTO.compNo",loginUser.compNo);
	}  
	
	var _compNo = partStkCurrentForm.elements['partStkCurrentDTO.compNo'].value;
	if(_compNo == "" && loginUser != null) _compNo = loginUser.compNo;
	
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
    	partStkCurrentForm.elements['partStkCurrentDTO.wcodeId'].value 	= "";
     	partStkCurrentForm.elements['partStkCurrentDTO.partId'].value 	= "";
    	return sortColumn("partStkCurrentList", this, partStkCurrentForm, ["WCODEID","PARTID"], ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox"); 
    }); 
    myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partStkCurrentList.do";

    partStkCurrentForm.elements['strutsAction'].value = '<%=PartStkCurrentAction.PTSTCK_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partStkCurrentForm), ["WCODEID","PARTID"], "Y");
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	partStkCurrentForm.elements['partStkCurrentDTO.wcodeId'].value = "";     // 검색시 Tab 이동Key Clear
	partStkCurrentForm.elements['partStkCurrentDTO.partId'].value = "";      // 검색시 Tab 이동Key Clear
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
	partStkCurrentForm.elements['partStkCurrentDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    partStkCurrentForm.elements['partStkCurrentDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
    partStkCurrentForm.elements['partStkCurrentDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');

    goCommonTabPage(partStkCurrentForm, <%=PartStkCurrentAction.PTSTCK_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('partStkCurrentDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partStkCurrentForm.elements['partStkCurrentDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    partStkCurrentForm.elements['partStkCurrentDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
    partStkCurrentForm.elements['partStkCurrentDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
    
    partStkCurrentForm.elements['strutsAction'].value = '<%=PartStkCurrentAction.PTSTCK_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partStkCurrentForm), 'partStkCurrentDetail'); 
} 


/**
 * Excel Export
 */
function goExcel()
{
	partStkCurrentForm.elements['partStkCurrentDTO.wcodeId'].value 	= "";
 	partStkCurrentForm.elements['partStkCurrentDTO.partId'].value 	= "";
    excelServerAction("partStkCurrentList", partStkCurrentForm);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = partStkCurrentForm.elements['partStkCurrentDTO.wcodeId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/partStkCurrentList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="partStkCurrentDTO.compNo"/><!-- Key -->
<html:hidden property="partStkCurrentDTO.wcodeId"/><!-- Key -->
<html:hidden property="partStkCurrentDTO.partId"/><!-- Key -->
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
               <!-- 창고코드 -->
                   <div class="field">
                       <label class="check"><bean:message key="LABEL.wcode"/></label>
                       <div class="input_box">  
                            <html:text property="partStkCurrentDTO.filterWcode" tabindex="10"/>
                       </div>
                   </div>
                   <!-- 부품명/규격 -->
                   <div class="field">
                       <label><bean:message key="LABEL.partNameSize"/></label>
                       <div class="input_box">
                            <html:text property="partStkCurrentDTO.filterPartDesc" tabindex="20"/>
                       </div>
                   </div>
                   <!-- 부품번호 -->
                   <div class="field">
                       <label><bean:message key="LABEL.partNo"/></label>
                       <div class="input_box">
                            <html:text property="partStkCurrentDTO.filterPartNo" tabindex="30"/>
                       </div>
                   </div>
                   <!-- 제작사 -->
                   <div class="field">
                       <label><bean:message key="LABEL.maker"/></label>
                       <div class="input_box">
                            <html:text property="partStkCurrentDTO.filterMaker" tabindex="40"/>
                       </div>
                   </div>
                   <!-- 모델 -->
                   <div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="partStkCurrentDTO.filterModel" tabindex="60"/>
                       </div>
                   </div>
                   <!-- 부품그룹 -->
                   <div class="field">
                       <label><bean:message key="LABEL.partGroup"/></label>
                       <div class="input_box">
                            <html:text property="partStkCurrentDTO.filterPartGroupDesc" tabindex="70"/>
                       </div>
                   </div>
                   <!-- 업체품번 -->
                   <div class="field">
                       <label><bean:message key="LABEL.vendorPtCode"/></label>
                       <div class="input_box">
                            <html:text property="partStkCurrentDTO.filterVendorPtCode" tabindex="80"/>
                       </div>
                   </div>
	                <!-- A보관위치 -->
	                <div class="field">
	                     <label><bean:message key="LABEL.aBinNo"/></label>
	                     <div class="input_box">
	                        <html:text property="partStkCurrentDTO.filterBinNo" tabindex="90"/>
	                   </div>
	                </div>	                   
                   <!-- 공장코드 -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantNo"/></label>
	                    <div class="input_box">
								<html:text property="partStkCurrentDTO.filterPlant" tabindex="100" />
	                    </div>
	                </div>
                   <!-- 회사코드 -->
                   <div class="field">
                        <label class="check"><bean:message key="LABEL.compNo"/></label>  
                        <div class="input_box">
                           <html:text property="partStkCurrentDTO.filterCompNo" tabindex="110"/>
                      </div>
                   </div>
	                <c:set var="filePath" value="enhance/${compName}/part/stk/partStkCurrentList_${compNo}.jsp" />
					<c:if test="${udf:isExist(filePath)}">
						<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/stk/partStkCurrentList_${compNo}.jsp"></c:import>
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