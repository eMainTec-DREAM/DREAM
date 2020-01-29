<%--===========================================================================
문서분류체계
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.ctg.action.DocCtgListAction" %>
<%@ page import="dream.doc.ctg.action.DocCtgDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 문서분류체계 -->
<title><bean:message key="MENU.DOCCATEG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var docctgAc;

function loadPage() 
{
	initGrid();
	
	docctgAc = new autoC({"docCtgCommonDTO.pDocctgDesc":"description"});
    docctgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	   	,"p_docctg_id":"0"
  	   });
    docctgAc.setAcDConditionMap({
	   });
    docctgAc.setTable("TADOCCTG");
    docctgAc.setAcResultMap({
        "docCtgCommonDTO.pDocctgId":"docctg_id"
    });
    docctgAc.init();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);

    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/docCtgList.do";

    docCtgListForm.elements['strutsAction'].value = '<%=DocCtgListAction.DOCCTG_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(docCtgListForm), "docctgId", "", "pDocctgId"); 
    
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	docCtgListForm.elements['docCtgCommonDTO.docctgId'].value = "";     // 검색시 Tab 이동Key Clear
	findGridList('SearchTree');
}
function findGridRow(_docctgId)
{
	docCtgListForm.elements['docCtgCommonDTO.docctgId'].value 	= _docctgId;
	findGridList('ReloadTreeRow');
	docCtgListForm.elements['docCtgCommonDTO.docctgId'].value = "";     // 검색시 Tab 이동Key Clear
}

function afterSearch()
{
	myGrid.expandAll(); //펼치기
 	setTimeout("myGrid.collapseAll();//접기", 100);
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
    docCtgListForm.elements['docCtgCommonDTO.docctgId'].value = getValueById(myGrid, selectedId, 'docctgId');

    goCommonTabPage(docCtgListForm, <%=DocCtgDetailAction.DOCCTG_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('docCtgDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    docCtgListForm.elements['docCtgCommonDTO.docctgId'].value = getValueById(myGrid, selectedId, 'docctgId');
    docCtgListForm.elements['strutsAction'].value = '<%=DocCtgDetailAction.DOCCTG_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(docCtgListForm), 'docCtgDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "docCtgDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	docCtgListForm.elements['docCtgCommonDTO.docctgId'].value = "";
    goCommonTabPage(docCtgListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'docctgId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    docCtgListForm.strutsAction.value = '<%=DocCtgListAction.DOCCTG_LIST_DELETE%>';
    var url = contextPath + "/docCtgList.do";

    $.post(url,FormQueryString(docCtgListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('docCtgDetail');
//     goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	docCtgListForm.elements['docCtgCommonDTO.docctgId'].value = "";
    excelServerAction("docCtgList", docCtgListForm);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/docCtgList">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="docCtgCommonDTO.docctgId"/>
<html:hidden property="docCtgCommonDTO.pDocctgId"/>
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
							<html:text property="docCtgCommonDTO.description" tabindex="10"/>

						</div>
					</div>
					<!-- 상위분류 -->
					<div class="field">
						<label><bean:message key="LABEL.pcdUsrdDesc"/></label>
						<div class="input_box">
							<html:text property="docCtgCommonDTO.pDocctgDesc" tabindex="20"/>
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