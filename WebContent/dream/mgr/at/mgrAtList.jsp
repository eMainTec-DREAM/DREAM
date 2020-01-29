<%--===========================================================================
Audit Trail
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.at.action.MgrAtListAction" %>
<%@ page import="dream.mgr.at.action.MgrAtDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- Audit Trail -->
<title><bean:message key='MENU.AUDTRAIL'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var pageAc, empAc, langAc;

var beforePageId = 'mgrAtDetail';
function loadPage() 
{
    initGrid();
    
	// 화면
	pageAc = new autoC({"mgrAtCommonDTO.filterPageDesc":"description"});
    pageAc.setTable("TAPAGE");
    pageAc.setAcConditionMap({
    	"is_use":"Y"
    });
    pageAc.setAcResultMap({
        //"mgrAtCommonDTO.filterPageId":"page_id"
         "mgrAtCommonDTO.filterPageId":"page_id"
    });
    pageAc.init();
    
    // 변경구분
    acSysDesc("mgrAtCommonDTO.filterCudTypeDesc","mgrAtCommonDTO.filterCudTypeId","DATA_CUD_TYPE");
	
	// 변경자
    empAc = new autoC({"mgrAtCommonDTO.filterUpdByDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "mgrAtCommonDTO.filterUpdById":"emp_no"
    });
    empAc.init();
    
	// 항목
    langAc = new autoC({"mgrAtCommonDTO.filterKeyName":"key_name"});
	langAc.setTable("TALANG");
	langAc.setAcResultMap({
	    "mgrAtCommonDTO.filterKeyNo":"key_no"
	    , "mgrAtCommonDTO.filterKeyType":"key_type"
	});
	langAc.init();
	

	if(mgrAtListForm.elements['mgrAtCommonDTO.filterFromDate'].value == "")
	{
		mgrAtListForm.elements['mgrAtCommonDTO.filterFromDate'].value   = getWorkDay(-7);
		mgrAtListForm.elements['mgrAtCommonDTO.filterToDate'].value   = getWorkDay();
	}
	
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrAtListForm.elements['mgrAtCommonDTO.tracelogId'].value = "";
        return sortColumn("mgrAtList", this, mgrAtListForm, "TRACELOGID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); 
	myGrid.init();

	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
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
	var form = document.mgrAtListForm;

	form.elements['mgrAtCommonDTO.tracelogDtlId'].value = getValueById(myGrid, selectedId,'TRACELOGDTLID');
	
	// 변경구분이 조회인 경우, 상세페이지로 이동 안함.
	if (""!=form.elements['mgrAtCommonDTO.tracelogDtlId'].value)
	{
		goCommonTabPage(mgrAtListForm, '<%=MgrAtDetailAction.DETAIL_FIND%>', pageId, beforePageId);
		beforePageId = pageId;
	}
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	goTabPage("mgrAtDetail");	
}

/**
 * Open 버튼 클릭
 */
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	var form = document.mgrAtListForm;

	form.elements['mgrAtCommonDTO.tracelogId'].value = getValueById(myGrid, selectedId,'TRACELOGID');
	form.elements['strutsAction'].value = '<%=MgrAtDetailAction.DETAIL_FIND%>';
 	var pageId = "mgrAtDetail";
 	
 	openQuickTabPage(FormQueryString(form), pageId); 
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrAtList.do";

    mgrAtListForm.elements['strutsAction'].value = '<%=MgrAtListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrAtListForm), "TRACELOGID", "Y");
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
     mgrAtListForm.elements['mgrAtCommonDTO.tracelogId'].value = "";
	 excelServerAction("mgrAtList", mgrAtListForm );  
 }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrAtList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="mgrAtCommonDTO.tracelogId"/>
<html:hidden property="mgrAtCommonDTO.tracelogDtlId"/>
<html:hidden property="mgrAtCommonDTO.objectId"/>
<html:hidden property="mgrAtCommonDTO.fileName"/>

<html:hidden property="mgrAtCommonDTO.filterPageId"/>
<html:hidden property="mgrAtCommonDTO.filterCudTypeId"/>
<html:hidden property="mgrAtCommonDTO.filterUpdById"/>
<html:hidden property="mgrAtCommonDTO.filterKeyType"/>
<html:hidden property="mgrAtCommonDTO.filterKeyNo"/>

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
                <!-- 화면ID -->
                <div class="field">
                    <label><bean:message key="LABEL.fileName"/></label>
                    <div class="input_box">
                        <html:text property="mgrAtCommonDTO.filterPageDesc" tabindex="10"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 변경구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.dataCudType"/></label>
                    <div class="input_box">
                        <html:text property="mgrAtCommonDTO.filterCudTypeDesc" tabindex="20"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 변경일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.changeDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="mgrAtCommonDTO.filterFromDate" tabindex="30" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="mgrAtCommonDTO.filterToDate" tabindex="40" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 변경자 -->
                <div class="field">
                    <label><bean:message key="LABEL.changeBy"/></label>
                    <div class="input_box">
                        <html:text property="mgrAtCommonDTO.filterUpdByDesc" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 항목 -->
                <div class="field">
                    <label><bean:message key="LABEL.prompt"/></label>
                    <div class="input_box">
                        <html:text property="mgrAtCommonDTO.filterKeyName" tabindex="60" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
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
		<div class="article_box">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:210px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>