<%--===========================================================================
일반자료실
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.data.action.MaDocCntrCdListAction" %>
<%@ page import="dream.doc.data.action.MaDocCntrCdDetailAction" %>
<%@ page import="common.bean.User"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
    User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>

<html>
<head>
<!-- 일반자료실 -->
<title><bean:message key="MENU.ETCDOC"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var eqCtgTypeAc;
var deptAc;
var mainMngAc;
var plantAc;
function loadPage() 
{
	//document.title = "test";

	//maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.filterFromDate'].value  = getMinusMonth2(new Date(), -6);
	//maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.filterToDate'].value    = getToday();
	
	//공장명
//     if(loginUser.filterPlant!='null'){
//     	maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
//     	maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
//     }
	    
    initGrid();
    
    eqCtgTypeAc = new autoC({"maDocCntrCdCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maDocCntrCdCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    deptAc = new autoC({"maDocCntrCdCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maDocCntrCdCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maDocCntrCdCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maDocCntrCdCommonDTO.filterUserName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maDocCntrCdCommonDTO.filterUserId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maDocCntrCdCommonDTO.filterDeptId",
    	"plant" : "maDocCntrCdCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    plantAc = new autoC({"maDocCntrCdCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maDocCntrCdCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
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
    	maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = "";
        return sortColumn("maDocCntrCdList", this, maDocCntrCdListForm, "DOCCNTRID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maDocCntrCdList.do";

    maDocCntrCdListForm.elements['strutsAction'].value = '<%=MaDocCntrCdListAction.DOCCNTR_CD_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maDocCntrCdListForm), "DOCCNTRID", "Y");

}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
    maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = "";    // 검색시 Tab 이동Key Clear
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
    maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = getValueById(myGrid, selectedId, 'docCntrId');

    goCommonTabPage(maDocCntrCdListForm, <%=MaDocCntrCdDetailAction.DOCCNTR_CD_DETAIL_INIT%>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_docCntrId)
{
	maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = _docCntrId;
	findGridList('ReloadRow');
	maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maDocCntrCdDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = getValueById(myGrid, selectedId, 'docCntrId');
    maDocCntrCdListForm.elements['strutsAction'].value = '<%=MaDocCntrCdDetailAction.DOCCNTR_CD_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maDocCntrCdListForm), 'maDocCntrCdDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maDocCntrCdDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = "";
    goCommonTabPage(maDocCntrCdListForm, '', pageId);
}
 
/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'docCntrId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maDocCntrCdListForm.strutsAction.value = '<%=MaDocCntrCdListAction.DOCCNTR_CD_LIST_DELETE%>';
    var url = contextPath + "/maDocCntrCdList.do";

    $.post(url,FormQueryString(maDocCntrCdListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maDocCntrCdDetail');
   // goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maDocCntrCdListForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = "";
    excelServerAction("maDocCntrCdDocLibList", maDocCntrCdListForm);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maDocCntrCdList">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maDocCntrCdCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maDocCntrCdCommonDTO.docCntrId"/><!-- Key -->
<html:hidden property="maDocCntrCdCommonDTO.filterEqCtgId"/>
<html:hidden property="maDocCntrCdCommonDTO.filterDeptId"/>
<html:hidden property="maDocCntrCdCommonDTO.filterUserId"/>
<html:hidden property="maDocCntrCdCommonDTO.filterPlantId"/>
<!-- 자료실 분류 -->
<html:hidden property="maDocCntrCdCommonDTO.docCntrType"/>
<html:hidden property="maDocCntrCdCommonDTO.objectType"/>
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
                       <label><bean:message key="LABEL.docCntrDesc"/></label>
                       <div class="input_box">
                            <html:text property="maDocCntrCdCommonDTO.filterDescription" tabindex="10"/>
                       </div>
                   </div>
                <div class="field">
                    <label><bean:message key="LABEL.regDate"/></label>
                    <div class="calendar_wrap">
	                    <div class="input_box input_carendar">
	                        <html:text property="maDocCntrCdCommonDTO.filterFromDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maDocCntrCdCommonDTO.filterToDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                 </div>
                </div> 
                <div class="field">
                    <label><bean:message key="LABEL.docCntrNo"/></label>
                    <div class="input_box">
                         <html:text property="maDocCntrCdCommonDTO.filterDocCntrNo" tabindex="40"/>
                    </div>
                </div>  
                <div class="field">
                    <label><bean:message key="LABEL.eqCtg2"/></label>
                    <div class="input_box">
                        <html:text property="maDocCntrCdCommonDTO.filterEqCtgDesc" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>                
                <div class="field">
                    <label><bean:message key="LABEL.manageDept"/></label>
                    <div class="input_box">
                        <html:text property="maDocCntrCdCommonDTO.filterDeptDesc" tabindex="60"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <div class="field">
                    <label><bean:message key="LABEL.regId"/></label>
                    <div class="input_box">
                        <html:text property="maDocCntrCdCommonDTO.filterUserName" tabindex="70"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>            
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="maDocCntrCdCommonDTO.filterPlantDesc" tabindex="80" />
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