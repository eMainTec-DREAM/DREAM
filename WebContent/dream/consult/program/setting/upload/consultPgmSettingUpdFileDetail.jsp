<%--===========================================================================
엑셀업로드 - 상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.setting.upload.action.ConsultPgmSettingUpdFileAction"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="common.bean.User" %>
<%@page import="common.bean.MwareConfig"%>
<%
	User user = (User)session.getAttribute(session.getId());
	String userSecGrade = user.getSecurGrade();
%>
<!DOCTYPE html>
<html:html>
<head>
<title><bean:message key='PAGE.consultPgmSettingUpdFileDetail' />
</title>
<meta name="decorator" content="tabPage">
<script language="javascript">// 저장 시 수행되는 action
var deleteRows;
var myVault;

var excelTypeAc;

$( window ).unload(function() {
	myVault.clear();
});

function loadPage() 
{
	//Excel 유형이 선택되어 있지 않으면 첨부파일을 선택할 수 없음.
	if(""==consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.excelTabDesc'].value 
			|| ""==consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.excelTabId'].value)
	{
		
	}
	
	deleteRows = new Array();
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	$('.stitle_tx').append("<bean:message key='LABEL.selectFile'/>, <bean:message key='LABEL.upload'/>");
	
	consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.manager'].value = loginUser.userName;
	//For Save
	setForUpdate();
	
	setReadOnly();
	
	loadFileBox();
	
	//Excel Type 자동완성
    excelTypeAc = new autoC({"consultPgmSettingUpdFileDTO.excelTabDesc":"description"});
    excelTypeAc.setTable("TAEXCELTAB");
    excelTypeAc.setKeyName("consultPgmSettingUpdFileDTO.excelTabId");
    excelTypeAc.setAcConditionMap({
        "excellist_status":"C"
        ,"is_use":"Y"
        ,"is_use_config":"Y"
    });
    excelTypeAc.setAcResultMap({
        "consultPgmSettingUpdFileDTO.excelTabId":"exceltab_id"
        ,"consultPgmSettingUpdFileDTO.tableName":"table_name"
        ,"consultPgmSettingUpdFileDTO.uploadPgmName":"upload_pgm_name"
        ,"consultPgmSettingUpdFileDTO.sheetName":"sheetName"
    });
    excelTypeAc.init();
	
}

function loadFileBox()
{
	var oldStrutsAction = consultPgmSettingUpdFileForm.strutsAction.value;
	consultPgmSettingUpdFileForm.strutsAction.value = '<%=ConsultPgmSettingUpdFileAction.DOCLIB_FILE_SAVE%>';
	var actionUrl = contextPath + "/consultPgmSettingUpdFileDetail.do?"+FormQueryString(consultPgmSettingUpdFileForm)+"&isUploadPage=true";

	//파일 업로드 객체 생성!
	myVault = new dhtmlXVaultObject({
		container:  "vaultObj",             // html container for vault
	    uploadUrl:  actionUrl,           // html4/html5 upload url
	  //  swfPath:    "../../common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
	    swfPath:    "common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
	    swfUrl:     actionUrl        // flash upload url

	});
	myVault.setSkin('dhx_terrace'); //스킨 변경
	
	consultPgmSettingUpdFileForm.strutsAction.value = oldStrutsAction;
	
	//Download 설정 세팅
//     var downUrl = contextPath + "/download.do"; 
// 	myVault.setDownloadURL(downUrl+"?physicalFileName={serverName}&fileType=DOCUMENT&isDownloadPage=true");
	
	//파일삭제 Event 설정 세팅
	myVault.attachEvent("onFileRemove", function(file){
		consultPgmSettingUpdFileForm.strutsAction.value = '<%=ConsultPgmSettingUpdFileAction.DOCLIB_FILE_DELETE%>';
		actionUrl = contextPath + "/consultPgmSettingUpdFileDetail.do";
		//삭제!!
	    $.post(actionUrl,FormQueryString(consultPgmSettingUpdFileForm)+"&deleteRows="+file.name, function(_data){
	    	
	    });
	});

	//자동 업로드 완료 Event 설정 세팅
	myVault.attachEvent("onUploadComplete", function(files){
		getTopPage().updateArray[currentPageId] = "FILE";  //업로드 셋
	});
	
	myVault.attachEvent("onBeforeFileAdd", function(file){
		var ext = this.getFileExtension(file.name);
		var excelFileType = "xls,xlsx";
		var fileSize  	 = "<%=MwareConfig.getMaxFileSize()%>";	
		var fileType  	 = "<%=MwareConfig.getFileType()%>";		
		
		var rtnValue = true;

		if((fileType.toUpperCase()).indexOf(ext.toUpperCase()) == -1) rtnValue =  true;
		else
		{
			//허용되지 않는 파일입니다.
			alertMessage1('<bean:message key="MESSAGE.MSG049"/>');
			rtnValue = false;
		}
		
		if((excelFileType.toUpperCase()).indexOf(ext.toUpperCase()) != -1) rtnValue =  true;
		else
		{
			//엑셀 파일만 업로드 가능합니다.
			alertMessage1('<bean:message key="MESSAGE.MSG049"/>');//메세지 추가해야함.
			rtnValue = false;
		}

		if(file.size > fileSize*1000000)
		{
			//xxMB 이하의 파일만 업로드 가능합니다.
			alertMessage1(fileSize+'<bean:message key="MESSAGE.MSG048"/>');
			rtnValue = false;
		} 
		
		return rtnValue;
	});
}

/**
 * 입력
 */
function goInput()
{
	
}

function goUpdate()
{
	
}

function goUpload()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	var fileData = myVault.getData();
	var fileNames = "";
	
	if(fileData.length<=0) {
		alertMessage1(COMMON_CMSG013);
		return;
	}
	
	for(ind in fileData) {
		fileNames += "&fileNames="+fileData[ind].name;
	}
	
	consultPgmSettingUpdFileForm.strutsAction.value = '<%=ConsultPgmSettingUpdFileAction.DOCLIB_DETAIL_UPLOAD%>';

	var actionUrl = contextPath + "/consultPgmSettingUpdFileDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmSettingUpdFileForm)+fileNames, 'afterUpload');
	setModal('<bean:message key="MESSAGE.MSG0149"/>');
}

function afterUpload(ajaxXmlDoc) {
	closeModal();
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	
	myVault.clear();
	self.close();
	opener.searchAfterUpload(consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.excelTabId'].value
										, consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.excelTabDesc'].value
										, consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.tableName'].value
										, consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.uploadPgmName'].value
										, consultPgmSettingUpdFileForm.elements['consultPgmSettingUpdFileDTO.sheetName'].value);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultPgmSettingUpdFileDetail">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="consultPgmSettingUpdFileDTO.docId" />
	
	<html:hidden property="consultPgmSettingUpdFileDTO.excelTabId" />
	<html:hidden property="consultPgmSettingUpdFileDTO.tableName" />
	<html:hidden property="consultPgmSettingUpdFileDTO.uploadPgmName" />
	<html:hidden property="consultPgmSettingUpdFileDTO.sheetName" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <!-- Excel유형 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.excelType"/></label>
					<div class="input_box">
						<html:text property="consultPgmSettingUpdFileDTO.excelTabDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
             	 <!-- 담당자 -->
             	 <div class="field">
             	 	<label><bean:message key="LABEL.manager"/></label>
					<div class="input_read">
						<html:text property="consultPgmSettingUpdFileDTO.manager" tabindex="20" readonly="true"/>
					</div>
				</div>
				  <div class="field_long except" name="addFileDiv">
					<label><bean:message key="LABEL.file"/></label>
					<div class="input_box">
							<div id="vaultObj" style="width:95%; height:250px;"></div>
					</div>
				 </div>
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
      
	</html:form>
</body>
</html:html>
