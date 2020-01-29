<%--===========================================================================
고장분류 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.fail.code.action.FailCodeLovAction" %>
<%@ page import="dream.fail.code.action.MaFailureListAction" %>
<%@ page import="dream.fail.code.action.MaFailureDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 고장분류 -->
<title><bean:message key="MENU.BMCATEG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

var failTypeAc;
var failureDescAc;
function loadPage() 
{
	initGrid();

	failTypeAc = new autoC({"maFailureCommonDTO.filterFailTypeDesc":"description"});
	failTypeAc.setAcConditionMap({
        	"list_type":"FAILURE_TYPE",
        	"is_use":"Y"
  	   });
	failTypeAc.setTable("TACDSYSD");
	failTypeAc.setAcResultMap({
        "maFailureCommonDTO.filterFailType":"cdsysd_no"
    });
	failTypeAc.init();
	
	failureDescAc = new autoC({"maFailureCommonDTO.filterFailureDesc":"description"});
	failureDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "is_use":"Y"
 	   });
	failureDescAc.setTable("TAFAILURE");
	failureDescAc.init();
	
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableMultiselect(chkFilter());
    //myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
    	goSelect();
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); myGrid.init();

    setHeader(myGrid, "gridbox" , "goSearch"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/failCodeLov.do";

    failCodeLovForm.elements['strutsAction'].value = '<%=FailCodeLovAction.FAILURE_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(failCodeLovForm), "failureId");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_failureId)
{
	failCodeLovForm.elements['maFailureCommonDTO.failureId'].value = _failureId;
	findGridList('ReloadRow');
	failCodeLovForm.elements['maFailureCommonDTO.failureId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	failCodeLovForm.elements['maFailureCommonDTO.failureId'].value = "";    // 검색시 Tab 이동Key Clear
	findGridList('Search');
}

function goSelect()
{
	setAcValue(myGrid, "failure_id");
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/failCodeLov">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

<html:hidden property="maFailureCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maFailureCommonDTO.failureId"/><!-- Key -->
<html:hidden property="maFailureCommonDTO.filterFailType"/>
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
           </div><!--sheader_box end-->
           <div class="article_box">
               <div class="form_box">
                  <%--  <div class="field">
                       <label><bean:message key="LABEL.failType"/></label>
                       <div class="input_box">
                            <html:text property="maFailureCommonDTO.filterFailTypeDesc" tabindex="10" 
                            onkeydown="validationKeyDown('maFailureCommonDTO.filterFailTypeDesc', 'maFailureCommonDTO.filterFailType');"/>
							<p class="open_spop">
							    <a href="javascript:lovSysDir('maFailureCommonDTO.filterFailType', 'maFailureCommonDTO.filterFailTypeDesc','FAILURE_TYPE');">
							        <span>조회</span>
							    </a>
							</p>
					   </div>
                   </div> --%>
<%--                    <div class="field">
                       <label><bean:message key="LABEL.failureDesc"/></label>
                       <div class="input_box">
                            <html:text property="maFailureCommonDTO.filterFailureDesc" tabindex="20"/>
                       </div>
                   </div> --%>
                   <div class="field">
                       <label><bean:message key="LABEL.failureDesc"/></label>
                       <div class="input_box">
                            <html:text property="maFailureCommonDTO.filterFailName" tabindex="30"/>
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