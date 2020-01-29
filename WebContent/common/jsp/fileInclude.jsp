<%--===========================================================================
author  javaworker
version $Id: fileInclude.jsp,v 1.1 2013/08/30 09:09:08 javaworker Exp $
since   1.0
현재 사용하지 않는 페이지
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<script language="javascript">

/**
 * 파일 취소
 */
function file_cancel(tableId)
{
	var testObj = M$("fileHTML");
	testObj.removeChild(M$(tableId));
}

var sHTML1 = "<table id='tableId";
var sHTML2 =  					"'><tr><td>" +
				"<input type='file' name='fileList[";
var sHTML3=     		                            "]' style='width: 500' /> " +
				 "<input type='button' class='inputB_c2' name='' onfocus='this.blur();' value='최소' onClick=\"javascript:file_cancel('tableId";
var sHTML4 =        "');\" /> " +
			 "</td></tr></table>";
var tableIndex = 0;

/**
 * 첨부 파일
 */
function fileAdd(_fileHtmlId)
{
	var fileHTMLObj = M$(_fileHtmlId);
	var sHTML_ALL = sHTML1 + tableIndex + sHTML2 + tableIndex + sHTML3 + tableIndex + sHTML4;
	
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

function fileDownload()
{
//	isDownloadPage.value = "true";

//	bottomForm.method = "";
	var param = "?isDownloadPage=true";
	bottomForm.action = contextPath + "/download.do"+param;
	
	bottomForm.submit();
}
</script>
<table>
	<tr>
		<td>
			<input type="button" name="" class='inputB_c4' onfocus='this.blur();' value="파일 첨부" onClick="fileAdd('fileHTML')"/>
			<input type="button" name="" class='inputB_c6' onfocus='this.blur();' value="파일 다운 테스트" onClick="fileDownload();"/>
		</td>
	</tr>
	<tr>
		<td>
			<div id="fileHTML">
			</div>	
		</td>
	</tr>
</table>