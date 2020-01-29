<%--===========================================================================
자재출고WO
author  ghlee
version $Id:$
since   1.0
======================================================t=====================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.wo.action.PartIssWoPartListAction" %>
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
<!-- 자재출고WO -->
<title><bean:message key="LABEL.work"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */
var woPtAc, woAc;

function loadPage() 
{
	showBtn("LINKWOPART");
	showBtn("ADDWOPART");
	showBtn("DELETE");
	
	partIssWoPartListForm.elements['partIssWoPartListDTO.ptIssListId'].value=partIssWoPartListForm.elements['maPtIssDetailDTO.ptisslistId'].value;
    
	woPtAc = new autoC({"partIssWoPartListDTO.multiWoPartDesc":"partNo"});
	woPtAc.setAcConditionMap({
		"wcode_id":parent.M$('maPtIssDetailDTO.wcodeId').value,
	 	"part_id":parent.M$('maPtIssDetailDTO.partId').value,
	 	"part_grade":parent.M$('maPtIssDetailDTO.partGrade').value,
 	    "ptisslist_id":"NULL"
 	});
	woPtAc.setTable("TAWOPARTS");
	woPtAc.setAcResultMap({
        "partIssWoPartListDTO.multiWoPartId":"woPartId"
    });
	woPtAc.setMultiSelect(true);
	woPtAc.init();
	
	woAc = new autoC({"partIssWoPartListDTO.multiWkOrDesc":"woDesc"});
	woAc.setAcConditionMap({
 	   "wo_status":"PRW+PRWDA+P+PRP",
 	   "comp_no":loginUser.compNo
 	});
	woAc.setTable("TAWORKORDER");
	woAc.setAcResultMap({
        "partIssWoPartListDTO.multiWkOrId":"wkorId"
    });
	woAc.setMultiSelect(true);
	woAc.init();
    
	initGrid();	
	
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("partIssWoPartList", this, partIssWoPartListForm, "WOPARTID", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partIssWoPartList.do";

    partIssWoPartListForm.elements['strutsAction'].value = '<%=PartIssWoPartListAction.WOPART_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partIssWoPartListForm), "WOPARTID", "Y");
    
}

/**
 * 
 */
function goSearch()
{
    findGridList('Search');
}

function goSave()
{
	if(partIssWoPartListForm.strutsAction.value == '<%=PartIssWoPartListAction.WOPART_LIST_LINK%>' ||
			partIssWoPartListForm.strutsAction.value == '<%=PartIssWoPartListAction.WOPART_LIST_ADD%>')
	{
		var url = contextPath + "/partIssWoPartList.do";
	    
	    $.post(url,FormQueryString(partIssWoPartListForm), function(_data){
	    	afterSave(_data);
	    });
	}
}
function afterSave(ajaxXmlDoc)
{
	if(partIssWoPartListForm.strutsAction.value == '<%=PartIssWoPartListAction.WOPART_LIST_LINK%>' ||
			partIssWoPartListForm.strutsAction.value == '<%=PartIssWoPartListAction.WOPART_LIST_ADD%>')
	{
		//=====================================
	    if (!checkHttpXml(ajaxXmlDoc)) return;
	    //=====================================
	    getTopPage().afterSaveAll(currentPageId);
	    
	    setForNormal();
	    
	    goSearch();
	}
} 

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck','WOPARTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    partIssWoPartListForm.strutsAction.value = '<%=PartIssWoPartListAction.WOPART_LIST_DELETE%>';
    var url = contextPath + "/partIssWoPartList.do";
	
    $.post(url,FormQueryString(partIssWoPartListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goLinkwopart()
{
	setForUpdate();
	
	woPtAc.openLov();
}

function goAddwopart()
{
	setForUpdate();
	
	woAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'partIssWoPartListDTO.multiWoPartDesc') {
		partIssWoPartListForm.strutsAction.value = '<%=PartIssWoPartListAction.WOPART_LIST_LINK%>';
		
		goSaveAll();
	}
	if(code == 'partIssWoPartListDTO.multiWkOrDesc') {
		partIssWoPartListForm.strutsAction.value = '<%=PartIssWoPartListAction.WOPART_LIST_ADD%>';
		
		goSaveAll();
	}
}

function goExcel()
{
	partIssWoPartListForm.elements['partIssWoPartListDTO.woPartId'].value = "";
    excelServerAction("partIssWoPartList", partIssWoPartListForm );  
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/partIssWoPartList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtIssDetailDTO.ptisslistId"/><!-- Key -->
<html:hidden property="partIssWoPartListDTO.woPartId"/><!-- Key -->
<html:hidden property="partIssWoPartListDTO.ptIssListId"/>
<html:hidden property="partIssWoPartListDTO.multiWkOrId"/>
<html:hidden property="partIssWoPartListDTO.multiWkOrDesc"/>
<html:hidden property="partIssWoPartListDTO.multiWoPartId"/>
<html:hidden property="partIssWoPartListDTO.multiWoPartDesc"/>

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