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
<%@ page import="dream.mgr.exldata.action.MgrExldataUploadDetailAction"%>
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
<title><bean:message key='TAB.mgrExldataUploadDetail' />
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
	if(""==mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.excelTabDesc'].value 
			|| ""==mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.excelTabId'].value)
	{
		
	}
	
	deleteRows = new Array();
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	$('.stitle_tx').append("<bean:message key='LABEL.selectFile'/>, <bean:message key='LABEL.upload'/>");
	
	mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.manager'].value = loginUser.empName+" / "+loginUser.deptDesc;
	mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.empId'].value = loginUser.empId;
	mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.deptId'].value = loginUser.deptId;
	//For Save
	setForUpdate();
	
	setReadOnly();
	
	loadFileBox();
	
	//Excel Type 자동완성
    excelTypeAc = new autoC({"mgrExldataUploadDetailDTO.excelTabDesc":"description"});
    excelTypeAc.setTable("TAEXCELTAB");
    excelTypeAc.setKeyName("mgrExldataUploadDetailDTO.excelTabId");
    excelTypeAc.setAcConditionMap({
        "excellist_status":"C"
        ,"is_use":"Y"
        ,"is_use_config":"N"
    });
    excelTypeAc.setAcResultMap({
        "mgrExldataUploadDetailDTO.excelTabId":"exceltab_id"
        ,"mgrExldataUploadDetailDTO.tableName":"table_name"
        ,"mgrExldataUploadDetailDTO.uploadPgmName":"upload_pgm_name"
        ,"mgrExldataUploadDetailDTO.sheetName":"sheet_name"
    });
    excelTypeAc.init();
	
}

function loadFileBox()
{
	var oldStrutsAction = mgrExldataUploadDetailForm.strutsAction.value;
	mgrExldataUploadDetailForm.strutsAction.value = '<%=MgrExldataUploadDetailAction.DOCLIB_FILE_SAVE%>';
	var actionUrl = contextPath + "/mgrExldataUploadDetail.do?"+FormQueryString(mgrExldataUploadDetailForm)+"&isUploadPage=true";

	//파일 업로드 객체 생성!
	myVault = new dhtmlXVaultObject({
		container:  "vaultObj",             // html container for vault
	    uploadUrl:  actionUrl,           // html4/html5 upload url
	  //  swfPath:    "../../common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
	    swfPath:    "common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
	    swfUrl:     actionUrl        // flash upload url

	});
	myVault.setSkin('dhx_terrace'); //스킨 변경
	
	mgrExldataUploadDetailForm.strutsAction.value = oldStrutsAction;
	
	//Download 설정 세팅
//     var downUrl = contextPath + "/download.do"; 
// 	myVault.setDownloadURL(downUrl+"?physicalFileName={serverName}&fileType=DOCUMENT&isDownloadPage=true");
	
	//파일삭제 Event 설정 세팅
	myVault.attachEvent("onFileRemove", function(file){
		mgrExldataUploadDetailForm.strutsAction.value = '<%=MgrExldataUploadDetailAction.DOCLIB_FILE_DELETE%>';
		actionUrl = contextPath + "/mgrExldataUploadDetail.do";
		//삭제!!
	    $.post(actionUrl,FormQueryString(mgrExldataUploadDetailForm)+"&deleteRows="+file.name, function(_data){
	    	
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
	
	mgrExldataUploadDetailForm.strutsAction.value = '<%=MgrExldataUploadDetailAction.DOCLIB_DETAIL_UPLOAD%>';

	var actionUrl = contextPath + "/mgrExldataUploadDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrExldataUploadDetailForm)+fileNames, 'afterUpload');
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
	opener.searchAfterUpload(mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.excelTabId'].value
										, mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.excelTabDesc'].value
										, mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.tableName'].value
										, mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.uploadPgmName'].value
										, mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.sheetName'].value);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mgrExldataUploadDetailForm.elements['mgrExldataUploadDetailDTO.excelTabId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrExldataUploadDetail">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="mgrExldataUploadDetailDTO.docId" />
	
	<html:hidden property="mgrExldataUploadDetailDTO.excelTabId" />
	<html:hidden property="mgrExldataUploadDetailDTO.tableName" />
	<html:hidden property="mgrExldataUploadDetailDTO.uploadPgmName" />
	<html:hidden property="mgrExldataUploadDetailDTO.sheetName" />
	<html:hidden property="mgrExldataUploadDetailDTO.empId" />
	<html:hidden property="mgrExldataUploadDetailDTO.deptId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <!-- Excel유형 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.excelType"/></label>
					<div class="input_box">
						<html:text property="mgrExldataUploadDetailDTO.excelTabDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
             	 <!-- 담당자 -->
             	 <div class="field">
             	 	<label><bean:message key="LABEL.manager"/></label>
					<div class="input_read">
						<html:text property="mgrExldataUploadDetailDTO.manager" tabindex="20" readonly="true"/>
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
