<%--===========================================================================
자재출고확정
author  
version $Id: partIssWoItemList.jsp $
since   1.0
======================================================t=====================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.wo.action.PartIssWoItemListAction" %>
<%@ page import="dream.part.iss.wo.action.PartIssWoItemDetailAction" %> 
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
<title><bean:message key="MENU.PTISS"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */


function loadPage() 
{
	
	partIssWoItemListForm.elements['partIssWoItemListDTO.ptisslistId'].value=partIssWoItemListForm.elements['maPtIssCommonDTO.ptisslistId'].value;
	partIssWoItemListForm.elements['partIssWoItemListDTO.partNo'].value=partIssWoItemListForm.elements['maPtIssDetailDTO.partNo'].value;
	partIssWoItemListForm.elements['partIssWoItemListDTO.partDesc'].value=partIssWoItemListForm.elements['maPtIssDetailDTO.partDesc'].value;
	partIssWoItemListForm.elements['partIssWoItemListDTO.equipId'].value=partIssWoItemListForm.elements['maPtIssDetailDTO.equipId'].value;
    
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
    var url = contextPath + "/partIssWoItemList.do";

    partIssWoItemListForm.elements['strutsAction'].value = '<%=PartIssWoItemListAction.PTISSWOITEM_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partIssWoItemListForm), "ptisslistSerialId");
    
}

/**
 * 
 */
function goSearch()
{
	
	partIssWoItemListForm.elements['partIssWoItemListDTO.partId'].value = $(parent.document).find('[name="maPtIssDetailDTO.partId"]').val();
    
    findGridList('Search');
    
    goClose('partIssWoItemDetail',this);
}
function findGridRow(ptisslistSerialId)
{  
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
    if (partIssWoItemListForm.elements['partIssWoItemListDTO.partId'].value == '') return;
	partIssWoItemListForm.elements['partIssWoItemListDTO.ptisslistSerialId'].value=ptisslistSerialId;
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
	partIssWoItemListForm.elements['partIssWoItemListDTO.ptisslistSerialId'].value = getValueById(myGrid, selectedId, 'ptisslistSerialId');
    partIssWoItemListForm.elements['partIssWoItemListDTO.ptisslistId'].value = getValueById(myGrid, selectedId, 'ptisslistId');
    partIssWoItemListForm.elements['partIssWoItemListDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
    goCommonTabPage(partIssWoItemListForm, <%=PartIssWoItemDetailAction.PTISS_DETAIL_INIT%>, pageId);
    
}

/**
 * 상세 열기
 */
function goOpen()
{
	
	goTabPage('partIssWoItemDetail');
}

/**
 * 생성
 */
function goCreate()
{
	
	createValidationCheck(myGrid, "partIssWoItemDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	
	goCommonTabPage(partIssWoItemListForm, '', pageId);
	
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck','ptisslistSerialId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    partIssWoItemListForm.strutsAction.value = '<%=PartIssWoItemListAction.PTISSWOITEM_LIST_DELETE%>';
    var url = contextPath + "/partIssWoItemList.do";

    $.post(url,FormQueryString(partIssWoItemListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('partIssWoItemDetail',this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/partIssWoItemList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtIssCommonDTO.ptisslistId"/><!-- Key -->
<html:hidden property="maPtIssDetailDTO.partId"/><!-- Key -->
<html:hidden property="maPtIssDetailDTO.partNo"/><!-- Key -->
<html:hidden property="maPtIssDetailDTO.partDesc"/><!-- Key -->
<html:hidden property="maPtIssDetailDTO.equipId"/><!-- Key -->
<html:hidden property="partIssWoItemListDTO.equipId"/><!-- Key -->
<html:hidden property="partIssWoItemListDTO.partId"/><!-- Key -->
<html:hidden property="partIssWoItemListDTO.ptisslistId"/>
<html:hidden property="partIssWoItemListDTO.ptisslistSerialId"/>
<html:hidden property="partIssWoItemListDTO.partNo"/>
<html:hidden property="partIssWoItemListDTO.partDesc"/>

        
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