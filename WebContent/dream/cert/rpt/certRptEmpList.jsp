<%--===========================================================================
자격증분류 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.cert.rpt.emplist.action.CertRptEmpListAction" %>
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
<!-- 자격증보유현황 -->
<title><bean:message key='MENU.CERTEMPLIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//

var myGrid;

function loadPage()
{
    initGrid();
	//acSysDesc("certRptEmpCommonDTO.filterCertTypeDesc","certRptEmpCommonDTO.filterCertType","CERT_TYPE", false);
	
    var certTypeAc = new autoC({"certRptEmpCommonDTO.filterCertTypeDesc":"description"});
    certTypeAc.setTable("TACDUSRD");
    certTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"CERT_TYPE"
  	   });
    certTypeAc.setAcResultMap({
        "certRptEmpCommonDTO.filterCertType":"cdusrd_no"
    });
    certTypeAc.init();
    
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.enableSmartRendering(true,500);
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
    var url = contextPath + "/certRptEmpList.do";

    certRptEmpListForm.elements['strutsAction'].value = '<%=CertRptEmpListAction.CERT_EMP_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(certRptEmpListForm), "CERTLISTID");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', '<%=CertRptEmpListAction.CERT_EMP_LIST_FIND%>');
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
<html:form action="/certRptEmpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="certRptEmpCommonDTO.filterCertType"/><!-- Key -->
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
						<html:text property="certRptEmpCommonDTO.filterCertName" tabindex="10"/>
					</div>
				</div>
				<!-- 자격증구분  -->
				<div class="field">
					<label><bean:message key="LABEL.certType"/></label>
					<div class="input_box">
						<html:text property="certRptEmpCommonDTO.filterCertTypeDesc" tabindex="20"/>
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