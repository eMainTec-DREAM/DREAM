<%--===========================================================================
1개 파일 파일첨부
author  javaworker
version $Id: fileUpload2.jsp,v 1.2 2014/02/06 07:52:41 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.file.action.FileUploadAction" %>
<%@ page import="common.file.dto.FileUploadDTO" %>
<%@ page import="common.util.CommonUtil" %>
<jsp:useBean id="fileUploadForm" class="common.file.form.FileUploadForm" scope="request" />
<%
    FileUploadDTO fileUploadDTO = fileUploadForm.getFileUploadDTO();
	String [][] fileInfo = fileUploadDTO.getFileInfoArray();
%>
<html>
<head>
<!-- 파일관리 -->
<title><bean:message key="fileUpload2.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
<script language="javascript"><!--
//
function loadPage() 
{
	if (fileUploadForm.strutsAction.value == "<%=FileUploadAction.FILE_UPLOAD_SAVE%>")
	{	
	    // opener page의 첨부파일 정보를 표시한다.
	    var objectNo = fileUploadForm.elements['fileUploadDTO.objectNo'].value;
	    var fileNo = fileUploadForm.elements['fileUploadDTO.fileNo'].value;
	    var fileName = fileUploadForm.elements['fileUploadDTO.fileName'].value;
	    
	    opener.setFileNo(objectNo, fileNo, fileName);
	    self.close();
	}

}

/**
 * 저장 버튼을 누른 경우
 */
function goUpload()
{
	//================================================
	// 최대 Byte를 넘었는지 체크한다.
	//if (checkFormMaxByte(fileUploadForm)) return;
	//================================================

	fileUploadForm.strutsAction.value = "<%=FileUploadAction.FILE_UPLOAD_SAVE%>";
	fileUploadForm.action = contextPath + "/fileUpload2.do";
	
	// 파일과 수정된 내용을 전송한다.
	formSubmit(fileUploadForm);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<div class="tab_cont mgt10_1">
    <div class="board_list_04">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" summary="List" >
<html:form action="/fileUpload" enctype="multipart/form-data">
    <tbody>
	   <tr valign="top">
            <td >
				<table id='tableId'><tr><td>
					<input type='file' name='fileList[0]' style='width:500px' />
			 	</td></tr></table>
            </td>
        </tr>
	<!-- 필터사용 변수 시작 -->
	<html:hidden property="strutsAction" /><!-- Action -->
	<html:hidden property="fileUploadDTO.docType" /><!-- 구분-->
	<html:hidden property="fileUploadDTO.objectNo" /><!-- Object No -->
	<html:hidden property="fileUploadDTO.fileNo" /><!-- File No -->
	<html:hidden property="fileUploadDTO.fileName" /><!-- File Name -->
  </tbody>
</html:form>
</table>
</div>
</div>
<table><tr height="5"><td></td></tr></table>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
</html>
