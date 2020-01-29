<%--===========================================================================
파일첨부
author  javaworker
version $Id: fileUpload.jsp,v 1.3 2014/02/06 06:55:53 javaworker Exp $
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
	    var docType = fileUploadForm.elements['fileUploadDTO.docType'].value;
	    var fileAttachTagId = fileUploadForm.elements['fileUploadDTO.fileAttachTagId'].value;
	    
	    // 그리드에 row 단위로 첨부가 있는경우 처리
	    if ("GRID" == fileAttachTagId) opener.showGridAttach(objectNo, docType);
	    else opener.showFileAttach(objectNo, docType, fileAttachTagId);
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
 * Upload
 */
function goUpload()
{	
	//================================================
	// 최대 Byte를 넘었는지 체크한다.
	if (checkFormMaxByte(fileUploadForm)) return;
	//================================================

	var delFileNoObj = document.getElementsByName('fileUploadDTO.fileNoArray');
	var delChecked = false;
	for (var i=0; i<delFileNoObj.length; i++)
	{
		if (delFileNoObj[i].checked)
		{
			delChecked = true;
			break;
		}
	}

	//첨부한 파일이 하나도 없는경우 메세지 처리	
	if(rowCount=='0' && !delChecked)
	{
		//추가한 파일이 없습니다.
	    alertMessage1('<bean:message key="MESSAGE.MSG036"/>');
		return;
	}

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
		//disabledButton("SAVE",   false);		// 저장
	}
	else
	{
        //==========================================
        // 해당 폼의 모든 Object를 readOnly(disable) 상태로 한다.
        enableFormObject(fileUploadForm, false);
        //==========================================
	
		disabledButton("ADDFILE", true);	// 파일추가
		//disabledButton("SAVE",   true);			// 저장
	}
}

// 첨부되는 Row Count			 
var rowCount = 0;

// 첨부되어 있는 파일들
var attachedFileCount = <%=fileInfo==null?0:fileInfo.length%>;

/**
 * 첨부 파일
 */
function goAddfile()
{
	if (rowCount + attachedFileCount >= 10)
	{
	    // 첨부파일 최대 10개 까지 입니다.
		alertMessage1("<bean:message key="fileUpload.MSG001"/>");
		return;
	}
	
	var fileHTMLObj = M$("fileHTML");
    var row = fileHTMLObj.insertRow(fileHTMLObj.rows.length);
    row.id = "new_file_row["+rowCount+"]";
    var cell0 = row.insertCell(0);
    cell0.innerHTML = "<input type='file' name='fileList["+rowCount+"]' id='fileList["+rowCount+"]' style='width:500px' />"+
    				  "<input type='button' class='btn_midD' onfocus='this.blur();' value='<bean:message key="BUTTON.CANCEL"/>' onClick='javascript:fileCancel("+rowCount+");' />";
    rowCount++;
    
    //========================================
    // IE8에서 message bar가 밀리지 않아서 보정
    // 대체 messageBar는 뭘까... 2013.12.02 bluemoon
    //M$('messageBar').display = 'none';
    //M$('messageBar').display = 'block';
    //========================================
}

/**
 * 파일 취소
 */
function fileCancel(_rowInex)
{
	M$("new_file_row["+_rowInex+"]").style.display = "none";
	
	// Get a reference to the uploaded file control.
    var control = document.getElementById("fileList["+_rowInex+"]");
	M$("new_file_row["+_rowInex+"]").removeChild(control.parentNode);
	
    //========================================
    // IE8에서 message bar가 밀리지 않아서 보정
    // 대체 messageBar는 뭘까... 2013.12.02 bluemoon
    //M$('messageBar').display = 'none';
    //M$('messageBar').display = 'block';
    //========================================
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
  <div>
      <table width="100%">
        <tr>
			<td height="20" width="200">
				<mware:button buttonId="ADDFILE" buttonType="midC"/>
			</td>
			<!-- *** 첨부파일은 최대 10건 까지 등록할수 있습니다. *** -->
			<td align="right"><bean:message key="fileUpload.help"/></td>
        </tr>
      </table>
  </div>
  <div class="board_detail">
	<html:form action="/fileUpload" enctype="multipart/form-data">
    <table cellspacing="0" cellpadding="0" border="0" summary="List" class="board_detail_list">
    <tbody>
	    <tr valign="top" height="15">
	        <th width="450" class="td_title_d" style="text-align: center;">&nbsp;&nbsp;&nbsp;<b><bean:message key="fileUpload.name"/></b></th><!-- 이름 -->
	        <th width="100" class="td_title_d" style="text-align: center;"><b><bean:message key="fileUpload.size"/></b></th><!-- 사이즈 -->
	        <th class="td_title_d"><b><bean:message key="fileUpload.del"/></b></th><!-- 삭제 -->
	    </tr>
<%
	int i=0;
	for (i=0; fileInfo != null && i<fileInfo.length; i++)
	{
%>
		<tr>
			<td class="td_input_d" width="500">-&nbsp;
				<a href="javascript:fileDownLoad('<%=fileInfo[i][2]%>');" >
					<font color="#666666">
						<%=fileInfo[i][0]%> 
					</font>
				</a>
			</td>
			<td class="td_input_d" width="100">
				<font color="#666666">
					<%=CommonUtil.convertMoney(fileInfo[i][1]) + " Byte"%> 
				</font>
			</td>
			<td class="td_input_d" width="50">
				<input type="checkbox" name="fileUploadDTO.fileNoArray" value="<%=fileInfo[i][2]%>" onfocus="this.blur();" class="input_noborder" />
			</td>
		</tr>
<%		
	}
%>
        <tr valign="top">
            <td colspan="3">
                <table id="fileHTML"></table>
				<!-- 필터사용 변수 시작 -->
				<html:hidden property="strutsAction" /><!-- Action -->
				<html:hidden property="fileUploadDTO.docType"   /><!-- 구분-->
				<html:hidden property="fileUploadDTO.objectNo"     /><!-- Object no-->
				<html:hidden property="fileUploadDTO.fileAttachTagId" /><!-- Object no-->
				<html:hidden property="updating" value="true" /><!-- 파일 입력, 수정 가능여부 -->
            </td>
        </tr>
  	  </tbody>
	</table>
	</html:form>
  </div>
<table><tr height="5"><td></td></tr></table>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
</html>
