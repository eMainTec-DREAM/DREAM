<%--===========================================================================
문서분류체계
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.ctg.action.DocCtgLovAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 문서분류체계 -->
<title><bean:message key="MENU.DOCCATEG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
var conditionFilterMap = {
		"docctg_id":"docCtgLovDTO.docctgId"
	};

/** 자동완성 변수 */
var docctgAc;

function loadPage() 
{
	convertCondition();
	
	initGrid();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);

	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goSelect();
	});
	myGrid.setEditable(false);
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id

}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/docCtgLov.do";

    docCtgLovForm.elements['strutsAction'].value = '<%=DocCtgLovAction.DOCCTG_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(docCtgLovForm), "docctg_Id"); 
    
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
// 	docCtgLovForm.elements['docCtgLovDTO.docctgId'].value = "";     // 검색시 Tab 이동Key Clear
	docCtgLovForm.elements['currentPageId'].value = currentPageId;
	findGridList('SearchTree');
}

function afterSearch()
{
	myGrid.expandAll(); //펼치기
 	//setTimeout("myGrid.collapseAll();//접기", 100);
}

function goSelect()
{
	setAcValue(myGrid);
}

function convertCondition()
{
	var paramObj = JSON.parse(M$('param').value);
	for(var key in paramObj){
		if(typeof conditionFilterMap[key] != "undefined" && typeof M$(conditionFilterMap[key]) == "object")
		{
			M$(conditionFilterMap[key]).value = paramObj[key];
			paramObj[key] = '';
			M$('param').value = JSON.stringify(paramObj);
		}
	}
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/docCtgLov">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

<html:hidden property="docCtgLovDTO.extCode1" />
<html:hidden property="docCtgLovDTO.extCode2" />
<html:hidden property="docCtgLovDTO.codeType" />
<html:hidden property="docCtgLovDTO.multiSelect" />
<html:hidden property="docCtgLovDTO.docctgId"/>
<html:hidden property="docCtgLovDTO.pdocctgId"/>
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
	                <!-- 분류명 -->
					<div class="field">
						<label><bean:message key="LABEL.categDesc"/></label>
						<div class="input_box">
							<html:text property="docCtgLovDTO.description" tabindex="10"/>

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