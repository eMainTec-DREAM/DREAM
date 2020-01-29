<%--===========================================================================
레포트변환
author  kim21017
version $Id: maReport.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.foreport.action.MaReportAction"%>
<html:html>
<head>
<!-- 레포트 -->
<title><bean:message key='MENU.REPORTMNG' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">

<script type="text/javascript">// 저장 시 수행되는 action

var resourceName = "";

function loadPage() 
{
}

/**
 * Resource 로딩후 조회된다.
 */
function resourceLoading(ajaxXmlDoc)
{
	//===============================================================
	var valMessage = '시스템 관리자에게 문의 바랍니다.';	// 시스템 관리자에게 문의 바랍니다.
	if (!valCheckHttpXml(ajaxXmlDoc, valMessage)) return;
	//===============================================================
	
	alert(resourceName + '을 다시 로딩하였습니다.'); // 을 다시 로딩하였습니다.
}

function goReport()
{
	resourceName = "XSL-FO";
	
	//현재 설정된 엑셀파일 경로 및 파일명
	var tempExcel = maReportForm.elements['tempExcel'].value;
	if(tempExcel == null || tempExcel == "")
	{
		//파일을 입력하시오.
		alert('파일을 입력하시오.');
		return;
	}
	
	var actionUrl = contextPath + "/maReport.do";
	var param = "strutsAction=" + "<%=MaReportAction.MA_REPORT_XSLFO%>"
					+ "&maReportCommonDTO.filePath=" + getRealPath(M$("tempExcel"));
	
	XMLHttpPostVal(actionUrl, param, 'resourceLoading');	
}
function goPdf()
{
 	width  = 800;
	height = 1000;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	var url = contextPath + "/maReport.do" +
						"?strutsAction=" + BASE_ACTION_REPORT +
						"&" + "maReportCommonDTO.filePath=" + getRealPath(M$("tempExcel"));
	window.open(url, "REPORT", features+"scrollbars=yes,status=no,toolbar=no,resizable=yes,location=no,menu=no,width="+width+",height="+height+"");
}
	

function getRealPath(obj)
{
	return obj.value.replace("c:\\fakepath\\",""); 
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<div class="section_wrap">
	<html:form action="/maReport.do" enctype="multipart/form-data">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maReportCommonDTO.filePath" />
		<div class="sheader_box">
			<div id="detailTitle" class="stitle_box"><bean:message key='LABEL.reportChange' /></div>
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
		<div class="article_box">
			<div class="form_box">
				<table class="data_table">
					<caption></caption>
					<colgroup>
						<col width="20%" />
						<col width="*" />
						<col width="15%" />
					</colgroup>
					<tbody>
					<tr>
						<th><span>Excel File</span></th>
						<td>
							<input type="file" id="tempExcel" name="tempExcel" style='width:500' tabindex="20"/>
						</td>
					</tr>
					</tbody>
				</table>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</div>
</body>
</html:html>