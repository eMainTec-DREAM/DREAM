<%--===========================================================================
고장분류 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
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

   
   failureDescAc = new autoC({"maFailureCommonDTO.filterFailureDesc":"key_name"});
   failureDescAc.setAcConditionMap({
        "key_type":"FAILCODE"
            });
   failureDescAc.setTable("TALANG");
    failureDescAc.init();
   
   acSysDesc("maFailureCommonDTO.filterFailTypeDesc","maFailureCommonDTO.filterFailType","FAILURE_TYPE", true);
   
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maFailureListForm.elements['maFailureCommonDTO.failureId'].value = "";
        return sortColumn("maFailureList", this, maFailureListForm, "failureId", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maFailureList.do";

     maFailureListForm.elements['strutsAction'].value = '<%=MaFailureListAction.FAILURE_LIST_FIND%>';
     doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maFailureListForm), "failureId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_failureId)
{
   maFailureListForm.elements['maFailureCommonDTO.failureId'].value = _failureId;
   findGridList('ReloadRow');
   maFailureListForm.elements['maFailureCommonDTO.failureId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
   maFailureListForm.elements['maFailureCommonDTO.failureId'].value = "";    // 검색시 Tab 이동Key Clear
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
   maFailureListForm.elements['maFailureCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   maFailureListForm.elements['maFailureCommonDTO.failureId'].value = getValueById(myGrid, selectedId, 'failureId');

    goCommonTabPage(maFailureListForm, <%=MaFailureDetailAction.FAILURE_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
   goTabPage('maFailureDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maFailureListForm.elements['maFailureCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maFailureListForm.elements['maFailureCommonDTO.failureId'].value = getValueById(myGrid, selectedId, 'failureId');
    maFailureListForm.elements['strutsAction'].value = '<%=MaFailureDetailAction.FAILURE_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maFailureListForm), 'maFailureDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
   createValidationCheck(myGrid, "maFailureDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
   maFailureListForm.elements['maFailureCommonDTO.failureId'].value = "";
    goCommonTabPage(maFailureListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'FAILUREID'); //Grid, check box column seq, pk column seq

    if(typeof delArray == "undefined"){
      alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
      return;
   }
    
    maFailureListForm.strutsAction.value = '<%=MaFailureListAction.FAILURE_LIST_DELETE%>';
    var url = contextPath + "/maFailureList.do";

    $.post(url,FormQueryString(maFailureListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maFailureDetail', this);
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maFailureListForm.elements['maFailureCommonDTO.failureId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maFailureList">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
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
         </div>
           </div><!--sheader_box end-->
           <div class="article_box">
               <div class="form_box">
                   <div class="field">
                   <!--고장분류 -->
                       <label><bean:message key="LABEL.failType"/></label>
                       <div class="input_box">
                            <html:text property="maFailureCommonDTO.filterFailTypeDesc" tabindex="10"/>
                     <p class="open_spop">
                         <a>
                             <span>조회</span>
                         </a>
                     </p>
                  </div>
                   </div>
                   <!--분류명  -->
                   <div class="field">
                       <label><bean:message key="LABEL.failureDesc"/></label>
                       <div class="input_box">
                            <html:text property="maFailureCommonDTO.filterFailureDesc" tabindex="20"/>
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
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
     </div> <!--  End of section_wrap -->

</html:form> 
</body>
</html>