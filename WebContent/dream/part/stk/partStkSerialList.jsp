<%--===========================================================================
자재출고확정
author  
version $Id: partStkSerialList.jsp $
since   1.0
======================================================t=====================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.stk.action.PartStkSerialListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자재출고확정 -->
<title><bean:message key="LABEL.partNo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */


function loadPage() 
{
	
	partStkSerialListForm.elements['partStkSerialListDTO.partId'].value=partStkSerialListForm.elements['maPtStckCommonDTO.partId'].value;

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
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id

    //myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"userQty"),".",",");
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partStkSerialList.do";

    partStkSerialListForm.elements['strutsAction'].value = '<%=PartStkSerialListAction.STKSERIAL_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partStkSerialListForm), "partId");
    
}

function goOpen()
{
    
}

/**
 * 
 */
function goSearch()
{
	
	findGridList('Search');
}
function findGridRow(partId)
{  
	
	partStkSerialListForm.elements['partStkSerialListDTO.partId'].value=partId;
	findGridList('ReloadRow');
	
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/partStkSerialList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtStckCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtStckDetailDTO.partId"/><!-- Key -->
<html:hidden property="partStkSerialListDTO.partId"/><!-- Key -->
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