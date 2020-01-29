<%--===========================================================================
자격증분류 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.cert.list.action.CertListAction" %>
<%@ page import="dream.cert.list.action.CertDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자격증분류 -->
<title><bean:message key='MENU.CERTLIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

function loadPage() 
{

    
    initGrid();
    
    var certTypeAc = new autoC({"certCommonDTO.filterCertTypeDesc":"description"});
    certTypeAc.setTable("TACDUSRD");
    certTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"CERT_TYPE"
  	   });
    certTypeAc.setAcResultMap({
        "certCommonDTO.filterCertType":"cdusrd_no"
    });
    certTypeAc.init();
    
	//acSysDesc("certCommonDTO.filterCertTypeDesc","certCommonDTO.filterCertType","CERT_TYPE", false);
   
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
		certListForm.elements['certCommonDTO.certListId'].value = "";
    	return sortColumn("certList", this, certListForm, "CERTLISTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/certList.do";

    certListForm.elements['strutsAction'].value = '<%=CertListAction.CERT_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(certListForm), "CERTLISTID", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_certListId)
{
	certListForm.elements['certCommonDTO.certListId'].value = _certListId;
	findGridList('ReloadRow');
	certListForm.elements['certCommonDTO.certListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	certListForm.elements['certCommonDTO.certListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=CertListAction.CERT_LIST_FIND%>');   
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
	var form = document.certListForm;
	 
	form.elements['certCommonDTO.certListId'].value = getValueById(myGrid, selectedId, 'certListId');
	goCommonTabPage(form, <%= CertDetailAction.CERT_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('certDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    certListForm.elements['certCommonDTO.certListId'].value = getValueById(myGrid, selectedId, 'certListId');
    certListForm.elements['strutsAction'].value = '<%=CertDetailAction.CERT_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(certListForm), 'certDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "certDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	certListForm.elements['certCommonDTO.certListId'].value = "";
	goCommonTabPage(certListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'certListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	certListForm.strutsAction.value = '<%=CertListAction.CERT_LIST_DELETE%>';
	var url = contextPath + "/certList.do";
	$.post(url,FormQueryString(certListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('certDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
<html:form action="/certList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="certCommonDTO.certListId"/><!-- Key -->
<html:hidden property="certCommonDTO.filterCertType"/><!-- Key -->
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
					<label><bean:message key="LABEL.certName"/></label>
					<div class="input_box">
						<html:text property="certCommonDTO.filterCertName" tabindex="50"/>
					</div>
				</div>
				<!-- 자격증구분  -->
				<div class="field">
					<label><bean:message key="LABEL.certType"/></label>
					<div class="input_box">
						<html:text property="certCommonDTO.filterCertTypeDesc" tabindex="70"/>
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