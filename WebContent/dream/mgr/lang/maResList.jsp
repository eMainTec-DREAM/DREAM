<%--===========================================================================
언어 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.lang.action.MaResListAction" %>
<%@ page import="dream.mgr.lang.action.MaResDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 언어 -->
<title><bean:message key='MENU.RES'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var langTypeAc;
function loadPage() 
{
    initGrid();
    
    acSysDesc("maResCommonDTO.filterLangDesc","maResCommonDTO.filterLangId","LANG",true);
    acSysDesc("maResCommonDTO.filterKeyTypeDesc","maResCommonDTO.filterKeyTypeId","KEY_TYPE");

    setInitVal('maResCommonDTO.filterLangId',loginUser.langId);
    setInitVal('maResCommonDTO.filterLangDesc',loginUser.langDesc);
    
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
    	maResListForm.elements['maResCommonDTO.langId'].value = "";
        return sortColumn("maResList", this, maResListForm, "LANGID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maResList.do";
    maResListForm.elements['strutsAction'].value = '<%=MaResListAction.RES_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maResListForm), "LANGID", "Y");
}

function findGridRow(_langId)
{
    // 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
    maResListForm.elements['maResCommonDTO.langId'].value = _langId;
    findGridList('ReloadRow');
    maResListForm.elements['maResCommonDTO.langId'].value = "";
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
    
    if(checkValidation()) return;
    
    maResListForm.elements['maResCommonDTO.langId'].value = ""; // 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaResListAction.RES_LIST_FIND%>');   
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
    maResListForm.elements['maResCommonDTO.langId'].value  = getValueById(myGrid, selectedId, 'LANGID');

    goCommonTabPage(maResListForm, <%= MaResDetailAction.RES_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maResDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maResListForm.elements['maResCommonDTO.langId'].value  = getValueById(myGrid, selectedId, 'LANGID');
    maResListForm.elements['strutsAction'].value = '<%=MaResDetailAction.RES_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maResListForm), 'maResDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maResDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maResListForm.elements['maResCommonDTO.langId'].value = "";
    goCommonTabPage(maResListForm, '', pageId);
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
<html:form action="/maResList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maResCommonDTO.langId"/><!-- Key -->
<html:hidden property="maResCommonDTO.filterLangId"/>
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
        <div class="article_box" >
            <div class="form_box">
                   <div class="field">
                       <label><bean:message key="LABEL.keyName"/></label>
                       <div class="input_box">
                            <html:text property="maResCommonDTO.filterKeyName" tabindex="10"/>
                       </div>
                   </div>
                    <div class="field">
                        <label class="check"><bean:message key="LABEL.language"/></label>
                        <div class="input_box">
                            <html:text property="maResCommonDTO.filterLangDesc" tabindex="20" />
                            <p class="open_spop"><a><span>조회</span></a></p>
                        </div>
                    </div>
                    <!-- 등록유형 -->
                   <div class="field">
                        <label class="check"><bean:message key="LABEL.keyType"/></label>
                        <div class="input_box">
                            <html:text property="maResCommonDTO.filterKeyTypeDesc" tabindex="20" />
                            <p class="open_spop"><a><span>조회</span></a></p>
                        </div>
                    </div>    
                    <!-- 등록id -->
                   <div class="field">
                        <label class="check"><bean:message key="LABEL.keyNo"/></label>
                        <div class="input_box">
                            <html:text property="maResCommonDTO.filterKeyNo" tabindex="20" />
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