<%--===========================================================================
파일첨부
author  jung7126
version $Id: singlefileUpload.jsp,v 1.1 2013/08/30 09:09:06 javaworker Exp $
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
<title><bean:message key="fileUpload.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
<script language="javascript"><!--
//

function loadPage() 
{
	// 저장 후 재조회
	if (fileUploadForm.strutsAction.value == "<%=FileUploadAction.FILE_UPLOAD_SAVE_FIND%>")
    {
	    // 저장되었습니다.
	    alertMessage1('<bean:message key="MESSAGE.MSG003"/>');
	}
	// 저장 후
	else if (fileUploadForm.strutsAction.value == "<%=FileUploadAction.FILE_UPLOAD_SAVE%>")
	{
	    //=======================================================================
	    // opener page의 첨부파일 정보를 표시한다.
	    var objectNo = fileUploadForm.elements['fileUploadDTO.objectNo'].value;
	    var imageType = fileUploadForm.elements['fileUploadDTO.imageType'].value;
	    opener.showFileAttach(objectNo, imageType);
	    //=======================================================================
	
	    fileUploadForm.strutsAction.value = "<%=FileUploadAction.FILE_UPLOAD_SAVE_FIND%>";
	    fileUploadForm.action = contextPath + "/fileUpload.do";

	    // 파일과 수정된 내용을 전송한다.
	    formSubmit(fileUploadForm);
	}
	
	// 수정가능
	if (fileUploadForm.updating.value == "true")
	{
		// 버튼의 상태를 셋팅한다.
        setPageButton("input");
	}
	// 조회만 가능
	else
	{
		setPageButton("onlyView");
	}
}

/**
 * 저장 버튼을 누른 경우
 */
function goSave()
{	
	//================================================
	// 최대 Byte를 넘었는지 체크한다.
	if (checkFormMaxByte(fileUploadForm)) return;
	//================================================

	fileUploadForm.strutsAction.value = "<%=FileUploadAction.FILE_UPLOAD_SAVE%>";
	fileUploadForm.action = contextPath + "/fileUpload.do";
	
	// 파일과 수정된 내용을 전송한다.
	formSubmit(fileUploadForm);
}

/**
 * 현재 버튼의 상태를 셋팅한다.
 */
function setPageButton(status)
{
	// 첨부가능 상태
	if (status == "input")
	{
        //==========================================
        // 해당 폼의 모든 Object를 enable 상태로 한다.
        enableFormObject(fileUploadForm, true);
        //==========================================
	
		disabledButton("ADDFILE", false);	// 파일추가
		disabledButton("SAVE",   false);		// 저장
	}
	else
	{
        //==========================================
        // 해당 폼의 모든 Object를 readOnly(disable) 상태로 한다.
        enableFormObject(fileUploadForm, false);
        //==========================================
	
		disabledButton("ADDFILE", true);	// 파일추가
		disabledButton("SAVE",   true);			// 저장
	}
}

/**
 * 파일 다운 로드 서블릿을 호출한다.
 */
function fileDownLoad(imageNo)
{
	var param = "?" + "isDownloadPage="   + "true" +
	            "&" + "physicalFileName=" + imageNo;
	            
	bottomForm.action = contextPath + "/download.do" + param;
	
    bottomForm.target = "bottomIframe";
    bottomForm.submit();
    bottomForm.target = "";
}

//======================================================================
//======================================================================
//======================================================================

var sHTML1 = "<table id='tableId";
var sHTML2 =  					"'><tr><td>";
var sHTML3=    "<input type='file' name='fileList[";
var sHTML4=     		                            "]' style='width: 500' /> " +
				 "<input type='button' class='btn_midD' name='' onfocus='this.blur();' value='<bean:message key="BUTTON.CANCEL"/>' onClick=\"javascript:file_cancel('tableId";
var sHTML5 =        "');\" /> " +
			 "</td></tr></table>";

// 새로 첨부되는 테이블 index			 
var tableIndex = 0;

// 첨부되어 있는 파일들
var attachedFileCount = <%=fileInfo==null?0:fileInfo.length%>;

/**
 * 첨부 파일
 */
function goAddfile()
{
	if (tableIndex + attachedFileCount >= 10)
	{
	    // 첨부파일 최대 10개 까지 입니다.
		alertMessage1("<bean:message key="fileUpload.MSG001"/>");
		return;
	}
	
	var fileHTMLObj = M$("fileHTML");
	var sHTML_ALL = sHTML1 + tableIndex + sHTML2 + sHTML3 + tableIndex + sHTML4 + tableIndex + sHTML5;
	
    if (fileHTMLObj.insertAdjacentHTML != null)
    {
       fileHTMLObj.insertAdjacentHTML("BeforeEnd", sHTML_ALL);
    }
    else
    {
        var r = fileHTMLObj.ownerDocument.createRange();
        var df = r.createContextualFragment(sHTML_ALL);
        fileHTMLObj.appendChild(df);
    }
	
	tableIndex++;
}

/**
 * 파일 취소
 */
function file_cancel(tableId)
{
	tableIndex--;

	var fileHTMLObj = M$("fileHTML");
	fileHTMLObj.removeChild(M$(tableId));
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/fileUpload" enctype="multipart/form-data">
	<table width="100%" >
		<tr valign="top">
			<td height="20">
				<mware:button buttonId="ADDFILE" buttonType="midC"/>
			</td>
			<!-- *** 첨부파일은 최대 10건 까지 등록할수 있습니다. *** -->
			<td align="right"><bean:message key="fileUpload.help"/></td>
		</tr>
	</table>
	<table cellspacing="3" border='0' class="table_detail" width="100%" height="300">
	    <tr valign="top" height="15">
	        <td width="450">&nbsp;&nbsp;&nbsp;<b><bean:message key="fileUpload.name"/></b></td><!-- 이름 -->
	        <td width="100" align="center"><b><bean:message key="fileUpload.size"/></b></td><!-- 사이즈 -->
	        <td align="center"><b><bean:message key="fileUpload.del"/></b></td><!-- 삭제 -->
	    </tr>
	    <tr><td height="1" colspan="3" background="<c:url value="/common/images/body/mid_line2.gif" />"></td></tr>
        <tr valign="top">
            <td colspan="3">
                <div id="fileHTML"></div>
            </td>
        </tr>
        <tr><td colspan="3"></td></tr>
	</table>
	<!-- 필터사용 변수 시작 -->
	<html:hidden property="strutsAction" /><!-- Action -->
	<html:hidden property="fileUploadDTO.imageType"   /><!-- 구분-->
	<html:hidden property="fileUploadDTO.objectNo"     /><!-- Object no-->
	<html:hidden property="updating"      /><!-- 파일 입력, 수정 가능여부 -->
</html:form>
<table><tr height="5"><td></td></tr></table>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
</html>
