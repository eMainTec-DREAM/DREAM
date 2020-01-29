<%--===========================================================================
Excel Data Upload
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.setting.upload.action.ConsultPgmSettingUpdAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- Excel Data Upload -->
<title><bean:message key='PAGE.consultPgmSettingUpdDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTextField();
	
    setTitle("consultPgmSettingUpdDTO.skeyId");
    
}

/**
 * 입력
 */
function goInput()
{
//     sequenceNextVal('SQASKEY_ID');
}
function setSequenceVal(sequenceVal)
{
	
}
/**
 * 수정
 */
function goUpdate()
{
	
}

function setTextField() {
	
	var url = contextPath + "/consultPgmSettingUpdDetail.do";
	consultPgmSettingUpdForm.elements['strutsAction'].value = '<%= ConsultPgmSettingUpdAction.DETAIL_SET_FIELD %>';
	
	$.post(url,FormQueryString(consultPgmSettingUpdForm), function(_data){
		var jsonObj = JSON.parse(_data).data;
		for(ind in jsonObj) {
			var box, readonly;
			if(jsonObj[ind].ISEDITABLE=="Y") {
				box = "box";
				readonly = "";
			}
			else {
				box = "read";
				readonly = "readonly";
			}
			if(jsonObj[ind].ISSYSTEM == "Y" && jsonObj[ind].ISSYSTEMDISPLAY == "N") {
				var hiddenField = "<input type='hidden' name='consultPgmSettingUpdDTO.paramMap."+jsonObj[ind].TABLECOLNAME.toUpperCase()+"' />";
				$("form").append(hiddenField);
			}
			else {
				if(jsonObj[ind].TABLECOLNAME.toUpperCase() == "MESSAGE") {
					var textField = "<div class='field_long'>"+
										 "	<label>"+jsonObj[ind].LABEL+"</label>"+
										 "	<div class='input_"+box+"'>"+
										 "		<textarea name='consultPgmSettingUpdDTO.paramMap."+jsonObj[ind].TABLECOLNAME.toUpperCase()+"' class='ta50' "+readonly+" ></textarea>"+
										 "	</div>"+
										 "</div>";
					$(".form_box").append(textField);
				}
				else {
					var textField = "<div class='field'>"+
										 "	<label>"+jsonObj[ind].LABEL+"</label>"+
										 "	<div class='input_"+box+"'>"+
										 "		<input type='text' name='consultPgmSettingUpdDTO.paramMap."+jsonObj[ind].TABLECOLNAME.toUpperCase()+"' "+readonly+" />"+
										 "	</div>"+
										 "</div>";
					$(".form_box").append(textField);
				}
			}
		}
		//For Save
	    setForUpdate();
	    parent.$("#tabFrameTAB\\.consultPgmSettingUpdDetail").trigger('setHeight');
		goSearch();
	});
}

function goSearch()
{
	var url = contextPath + "/consultPgmSettingUpdDetail.do";
	consultPgmSettingUpdForm.elements['strutsAction'].value = '<%= ConsultPgmSettingUpdAction.DETAIL_INIT %>';
	
	$.post(url,FormQueryString(consultPgmSettingUpdForm), function(_data){
		var jsonObj = JSON.parse(_data).data[0];
		
		for(key in jsonObj) {
			if(typeof consultPgmSettingUpdForm.elements["consultPgmSettingUpdDTO.paramMap."+key] != 'undefined')
				consultPgmSettingUpdForm.elements["consultPgmSettingUpdDTO.paramMap."+key].value = jsonObj[key];
		}
	});
}

/**
 * 저장
 */ 
function goSave()
{
    //================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) consultPgmSettingUpdForm.strutsAction.value = "<%=ConsultPgmSettingUpdAction.DETAIL_INPUT%>";
    else consultPgmSettingUpdForm.strutsAction.value = "<%=ConsultPgmSettingUpdAction.DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/consultPgmSettingUpdDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmSettingUpdForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)
		parent.findGridRow(consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value);
	
	getTopPage().afterSaveAll(currentPageId);

}

function goUpload()
{
	consultPgmSettingUpdForm.strutsAction.value = '<%=ConsultPgmSettingUpdAction.DETAIL_UPLOAD%>';

	var actionUrl = contextPath + "/consultPgmSettingUpdDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmSettingUpdForm), 'afterUpload');
	setModal('<bean:message key="MESSAGE.MSG0149"/>');
}

function afterUpload(ajaxXmlDoc) {
	closeModal();
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)
		parent.findGridRow(consultPgmSettingUpdForm.elements['consultPgmSettingUpdDTO.skeyId'].value);
	
	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/consultPgmSettingUpdDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="consultPgmSettingUpdDTO.skeyId" /><!-- Key -->
		<html:hidden property="consultPgmSettingUpdDTO.excelTabId"/>
		<html:hidden property="consultPgmSettingUpdDTO.tableName" />
		<html:hidden property="consultPgmSettingUpdDTO.uploadPgmName" />

		<div class="article_box">
			<div class="form_box">
				
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
