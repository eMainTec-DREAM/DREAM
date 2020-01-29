<%--===========================================================================
목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.wrkimp.action.MaWrkimpListAction" %>
<%@ page import="dream.consult.program.wrkimp.action.MaWrkimpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- WrkimpDesk -->
<title><bean:message key='MENU.WRKIMP'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">

<script language="javascript">
<!--//

var myGrid;
var empDescAc;
function loadPage() 
{
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
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maWrkimpListForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = "";
        return sortColumn("maWrkImpList", this, maWrkimpListForm, "GOWRKIMPID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
    
    acSysDesc("maWrkimpCommonDTO.gowrkimpStatusDesc","maWrkimpCommonDTO.gowrkimpStatus","GOWRKIMP_STATUS");
    
    acSysDesc("maWrkimpCommonDTO.wrkImpCreTypeDesc","maWrkimpCommonDTO.wrkImpCreType","WRKIMP_CRE_TYPE");
    
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWrkImpList.do";
    maWrkimpListForm.elements['strutsAction'].value = '<%=MaWrkimpListAction.WRKIMP_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWrkimpListForm), "gowrkimpId", "Y");
}

function findGridRow(_gowrkimpId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	maWrkimpListForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = _gowrkimpId;
	findGridList('ReloadRow');
	maWrkimpListForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = "";
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maWrkimpListForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaWrkimpListAction.WRKIMP_LIST_FIND%>');   
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
   	maWrkimpListForm.elements['maWrkimpCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   	maWrkimpListForm.elements['maWrkimpCommonDTO.gowrkimpId'].value  = getValueById(myGrid, selectedId, 'gowrkimpId');

	goCommonTabPage(maWrkimpListForm, <%= MaWrkimpDetailAction.WRKIMP_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maWrkImpDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;

    maCompMngListForm.elements['strutsAction'].value = '<%=MaWrkimpDetailAction.WRKIMP_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(maCompMngListForm), 'maWrkImpDetail'); 
}    

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maWrkImpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maWrkimpListForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = "";
    goCommonTabPage(maWrkimpListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'gowrkimpId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maWrkimpListForm.strutsAction.value = '<%=MaWrkimpListAction.WRKIMP_LIST_DELETE%>';
    var url = contextPath + "/maWrkImpList.do";
    
    $.post(url,FormQueryString(maWrkimpListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('maWrkImpDetail', this);
    // goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maWrkimpListForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = "";
    excelServerAction("maWrkImpList", maWrkimpListForm);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWrkImpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWrkimpCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maWrkimpCommonDTO.gowrkimpId"/>
<html:hidden property="maWrkimpCommonDTO.gowrkimpStatus"/>
<html:hidden property="maWrkimpCommonDTO.wrkImpCreType"/>
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
	           		<!-- 작성일자 -->
	               <div class="field">
		               <label><bean:message key="LABEL.updDate"/></label>
		               <div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWrkimpCommonDTO.workSDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWrkimpCommonDTO.workEDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
	               </div>
	               <div class="field">
	               		<!-- 진행상태 -->
		               <label><bean:message key="LABEL.proStatus"/></label>
                       <div class="input_box">
						<html:text property="maWrkimpCommonDTO.gowrkimpStatusDesc" tabindex="40" 
									onkeydown="validationKeyDown('maWrkimpCommonDTO.gowrkimpStatusDesc', 'maWrkimpCommonDTO.gowrkimpStatus');"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
               	   </div>
               	   <div class="field">
                       <label><bean:message key="LABEL.title"/></label>
                       <div class="input_box">
                       	  <html:text property="maWrkimpCommonDTO.description" tabindex="50"/>
	                   </div>
                   </div>        
                   <!-- 작성자 -->
					<div class="field">
						<label><bean:message key="LABEL.writeBy"/></label>
						<div class="input_box">
						    <html:text property="maWrkimpCommonDTO.writeByName" tabindex="60"/>
						</div>
					</div>
					
					<!-- 검토자 -->
                    <div class="field">
                        <label><bean:message key="LABEL.viewBy"/></label>
                        <div class="input_box">
                            <html:text property="maWrkimpCommonDTO.viewByName" tabindex="70"/>
                        </div>
                    </div>
                    
                    <!-- 작업자 -->
                    <div class="field">
                        <label><bean:message key="LABEL.woCraft"/></label>
                        <div class="input_box">
                            <html:text property="maWrkimpCommonDTO.workByName" tabindex="80"/>
                        </div>
                    </div>
					
					<!-- 요청Site -->
                    <div class="field">
                        <label><bean:message key="LABEL.wrkimpSite"/></label>
                        <div class="input_box">
                            <html:text property="maWrkimpCommonDTO.wrkimpSite" tabindex="90"/>
                        </div>
                    </div>
                    
                    <div class="field">
                        <!-- 진행상태 -->
                       <label><bean:message key="LABEL.wrkimpCreTypeDesc"/></label>
                       <div class="input_box">
                        <html:text property="maWrkimpCommonDTO.wrkImpCreTypeDesc" tabindex="100" 
                                    onkeydown="validationKeyDown('maWrkimpCommonDTO.wrkImpCreTypeDesc', 'maWrkimpCommonDTO.wrkImpCreType');"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
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