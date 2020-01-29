<%--===========================================================================
List Of Value Popup
code, description 형태로 조회되는 T4LISTS, T4DIR_DTL 을 제외한 테이블은 
이곳에서 사용한다.
author  javaworker
version $Id: lovTablePopup.jsp,v 1.1 2013/08/30 09:12:01 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.finder.valid.action.ListOfValAction"%>
<html>
<head>
<!-- SEARCH CODE -->
<title><bean:message key="MENU.SEARCHCODE"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//
//var iViewSize = 15;
//var iPageSize = sheetPageSize;

//그리드명
var myGrid;

function loadPage() 
{
	if(listOfValForm.elements['listOfValDTO.codeType'].value=="YN"
		||listOfValForm.elements['listOfValDTO.codeType'].value=="LEVEL"){
		document.getElementById("filterSection").style.display= "none";
	}
	initGrid();

}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.enableMultiselect(chkFilter());
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "listOfVal"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	if(listOfValForm.elements['listOfValDTO.codeType'].value=="YN"
		||listOfValForm.elements['listOfValDTO.codeType'].value=="LEVEL"){
		myGrid.setColumnHidden(1,true); 
	}
	var form = document.listOfValForm;	
	
	form.strutsAction.value = '<%=ListOfValAction.LOV_TABLE_FIND%>';
	
	var url = contextPath + "/listOfVal.do";
	myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    });
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

    var spId = selectedId.split(",");
    if(spId.length != 1)
    {
    	var rtnOne="", rtnTwo=""; 
    	var cnt = 1;
    	for(var i in spId)
    	{
    		//rtnOne = rtnOne + getValueById(myGrid, spId[i], 1);
    		//if(spId.length != cnt) rtnOne = rtnOne + "+";
    		
    		rtnTwo = rtnTwo + getValueById(myGrid, spId[i], 2);
    		if(spId.length != cnt) rtnTwo = rtnTwo + "+";
    		
    		cnt ++;
    	}
    }
    else
    {
    	rtnOne = getValueById(myGrid, selectedId,0);
    	rtnTwo = getValueById(myGrid, selectedId,2);
    }

	returnArray[0] = rtnOne;
	returnArray[1] = rtnTwo;
	
	var dirType = listOfValForm.elements['listOfValDTO.codeType'].value;
	
	
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValue(returnArray, dirType);
// 	self.close();
    closeLayerPopup();
}

/**
 * 검색
 */
function goSearch()
{
	findGridList();
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/listOfVal" >
		<html:hidden property="listOfValDTO.codeType" />
		<html:hidden property="listOfValDTO.extCode1" />
<%-- 		<html:hidden property="listOfValDTO.code" /> --%>
		<html:hidden property="listOfValDTO.codeKind" />
		<html:hidden property="strutsAction" />
		<div class="section_wrap" id="filterSection">
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<div class="field">
						<!-- desc -->
						<label><bean:message key="LABEL.code"/></label>
						<div class="input_box">
							<html:text property="listOfValDTO.code" />
						</div>
					</div>
					<div class="field">
						<!-- desc -->
						<label><bean:message key="LABEL.desc"/></label>
						<div class="input_box">
							<html:text property="listOfValDTO.description" />
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
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