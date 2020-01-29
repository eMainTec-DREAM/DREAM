<%--===========================================================================
권한명 - 목록
author ssong 
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.mgr.usrgrp.action.MaUsrGrpAuthListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 권한명 -->
<title><bean:message key='LABEL.btnAuth'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="innerTabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();
}

dhtmlXGridObject.prototype._in_header_master_checkbox=function(t,i,c){
    t.innerHTML=c[0]+"<input type='checkbox' name='selectAll'/>"+c[1];
	//var self=this;
	$(t).find('input').change(function(){
	    if($(this).is(':checked')) {
	    	var colIdx = getCoumnIdx(myGrid, 'ISDELCHECK');
	    	for(var i = 0; myGrid.getRowsNum() > i; i++)
	    	{
	    		setValueById(myGrid, myGrid.getRowId(i), 'ISDELCHECK', "1");
	    		myGrid.cellById(myGrid.getRowId(i),colIdx).cell.wasChanged=true
	    	}
	    } else {
	    	var colIdx = getCoumnIdx(myGrid, 'ISDELCHECK');
	    	for(var i = 0; myGrid.getRowsNum() > i; i++)
	    	{
	    		setValueById(myGrid, myGrid.getRowId(i), 'ISDELCHECK', "0");
	    		myGrid.cellById(myGrid.getRowId(i),colIdx).cell.wasChanged=true
	    	}
	    }
	});
}  

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onCheck",function(rowId, columnId, state){
    	getTopPage().updateArray["maUsrGrpAuthList"] = "AUTH";
    });
    myGrid.attachEvent("onBeforeSelect",function(rowId, state){
	    myGrid.cells(rowId,getCoumnIdx(myGrid, "description")).setDisabled(true);
		return true;
	});
    myGrid.attachEvent("onBeforeSorting", function(ind,type,direction){
        return false;
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}

function goSave()
{
	var menuMap = {};
	var ids=myGrid.getChangedRows();
	if(ids == "") return;

	ids = ids.split(",");
	for(var i=0, length= ids.length; i< length; i++) {
		menuMap[getValueById(myGrid, ids[i],'pgbtnId')] = getValueById(myGrid, ids[i],'ISDELCHECK');
	}

	maUsrGrpAuthListForm.elements['maUsrGrpCommonDTO.jsonArray'].value = JSON.stringify(menuMap);
	
	maUsrGrpAuthListForm.strutsAction.value = '<%=MaUsrGrpAuthListAction.USRGRP_AUTHPBTN_LIST_SAVE%>';
	var url = contextPath + "/mgrUsrgrpBtnList.do";
	$.post(url,FormQueryString(maUsrGrpAuthListForm) , function(_data){
    	afterSave();
    	
    	myGrid.clearChangedState();
    });  
}

function afterSave()
{
	getTopPage().afterSaveAll("maUsrGrpAuthList");
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(actionType,sheetAction)
{
    var url = contextPath + "/mgrUsrgrpBtnList.do";
    maUsrGrpAuthListForm.elements['strutsAction'].value = sheetAction;
    
    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maUsrGrpAuthListForm), function(_data){
        myGrid.parse(_data,"js");
    });

    setForUpdate("maUsrGrpAuthList");
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=MaUsrGrpAuthListAction.USRGRP_AUTHBTN_LIST_FIND%>');   
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
<html:form action="/mgrUsrgrpBtnList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUsrGrpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maUsrGrpCommonDTO.usrGrpId"/><!-- Key -->
<html:hidden property="maUsrGrpCommonDTO.usrGrpNo" />
<html:hidden property="maUsrGrpCommonDTO.jsonArray" />
<html:hidden property="maUsrGrpCommonDTO.menuId" />
<html:hidden property="maUsrGrpCommonDTO.pageId" />

    <div class="section_wrap">
    	<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
		
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:500px; background-color:white;"></div>
            </div>
        </div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>