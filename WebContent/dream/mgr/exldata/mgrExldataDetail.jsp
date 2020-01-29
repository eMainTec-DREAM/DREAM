<%--===========================================================================
Excel Data Upload
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page
	import="dream.mgr.exldata.action.MgrExldataDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- Excel Data Upload -->
<title><bean:message key='LABEL.docCountrNo' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	mgrExldataDetailForm.elements['mgrExldataDetailDTO.tableName'].value = mgrExldataDetailForm.elements['mgrExldataCommonDTO.tableName'].value;
    mgrExldataDetailForm.elements['mgrExldataDetailDTO.excelTabId'].value = mgrExldataDetailForm.elements['mgrExldataCommonDTO.excelTabId'].value;
    mgrExldataDetailForm.elements['mgrExldataDetailDTO.skeyId'].value = mgrExldataDetailForm.elements['mgrExldataCommonDTO.skeyId'].value;
	
	setTextField();
	
    setTitle("mgrExldataCommonDTO.skeyId");
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
	
	var url = contextPath + "/mgrExldataDetail.do";
	mgrExldataDetailForm.elements['strutsAction'].value = '<%= MgrExldataDetailAction.DETAIL_SET_FIELD %>';
	
	$.post(url,FormQueryString(mgrExldataDetailForm), function(_data){
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
				var hiddenField = "<input type='hidden' name='mgrExldataDetailDTO.paramMap."+jsonObj[ind].TABLECOLNAME.toUpperCase()+"' />";
				$("form").append(hiddenField);
			}
			else {
				if(jsonObj[ind].TABLECOLNAME.toUpperCase() == "MESSAGE") {
					var textField = "<div class='field_long'>"+
										 "	<label>"+jsonObj[ind].LABEL+"</label>"+
										 "	<div class='input_"+box+"'>"+
										 "		<textarea name='mgrExldataDetailDTO.paramMap."+jsonObj[ind].TABLECOLNAME.toUpperCase()+"' class='ta50' "+readonly+" ></textarea>"+
										 "	</div>"+
										 "</div>";
					$(".form_box").append(textField);
				}
				else {
					var textField = "<div class='field'>"+
										 "	<label>"+jsonObj[ind].LABEL+"</label>"+
										 "	<div class='input_"+box+"'>"+
										 "		<input type='text' name='mgrExldataDetailDTO.paramMap."+jsonObj[ind].TABLECOLNAME.toUpperCase()+"' "+readonly+" />"+
										 "	</div>"+
										 "</div>";
					$(".form_box").append(textField);
				}
			}
		}
		//For Save
	    setForUpdate();
	    parent.$("#tabFrameTAB\\.mgrExldataDetail").trigger('setHeight');
		goSearch();
	});
}

function goSearch()
{
	var url = contextPath + "/mgrExldataDetail.do";
	mgrExldataDetailForm.elements['strutsAction'].value = '<%= MgrExldataDetailAction.DETAIL_INIT %>';

	$.post(url,FormQueryString(mgrExldataDetailForm), function(_data){
		var jsonObj = JSON.parse(_data).data[0];
		
		for(key in jsonObj) {
			if(typeof mgrExldataDetailForm.elements["mgrExldataDetailDTO.paramMap."+key] != 'undefined')
				mgrExldataDetailForm.elements["mgrExldataDetailDTO.paramMap."+key].value = jsonObj[key];
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
    if(ckCreate(currentPageId)) mgrExldataDetailForm.strutsAction.value = "<%=MgrExldataDetailAction.DETAIL_INPUT%>";
    else mgrExldataDetailForm.strutsAction.value = "<%=MgrExldataDetailAction.DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/mgrExldataDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrExldataDetailForm), 'afterSave');
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
		parent.findGridRow(mgrExldataDetailForm.elements['mgrExldataCommonDTO.skeyId'].value);
	
	mgrExldataDetailForm.elements['mgrExldataCommonDTO.skeyId'].value = mgrExldataDetailForm.elements['mgrExldataDetailDTO.skeyId'].value;
	getTopPage().afterSaveAll(currentPageId);

}

function goUpload()
{
	mgrExldataDetailForm.elements['mgrExldataDetailDTO.skeyId'].value = mgrExldataDetailForm.elements['mgrExldataCommonDTO.skeyId'].value;
	mgrExldataDetailForm.elements['mgrExldataDetailDTO.uploadPgmName'].value = mgrExldataDetailForm.elements['mgrExldataCommonDTO.filterUploadPgmName'].value;
	
	mgrExldataDetailForm.strutsAction.value = '<%=MgrExldataDetailAction.DETAIL_UPLOAD%>';

	var actionUrl = contextPath + "/mgrExldataDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrExldataDetailForm), 'afterUpload');
	setModal('<bean:message key="MESSAGE.MSG0149"/>');
}

function afterUpload(ajaxXmlDoc) {
	closeModal();
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)
		parent.findGridRow(mgrExldataDetailForm.elements['mgrExldataCommonDTO.skeyId'].value);
	
	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mgrExldataDetailForm.elements['mgrExldataDetailDTO.skeyId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrExldataDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="mgrExldataCommonDTO.skeyId" />
		<html:hidden property="mgrExldataCommonDTO.filterExcelTabId"/>
		<html:hidden property="mgrExldataCommonDTO.filterTableName"/>
		<html:hidden property="mgrExldataCommonDTO.filterUploadPgmName"/>
		<html:hidden property="mgrExldataCommonDTO.excelTabId"/>
		<html:hidden property="mgrExldataCommonDTO.tableName"/>
		<html:hidden property="mgrExldataCommonDTO.uploadPgmName"/>
		<!-- Key -->
		<html:hidden property="mgrExldataDetailDTO.skeyId" />
		<html:hidden property="mgrExldataDetailDTO.excelTabId"/>
		<html:hidden property="mgrExldataDetailDTO.tableName" />
		<html:hidden property="mgrExldataDetailDTO.uploadPgmName" />
		<!-- Key -->

		<div class="article_box">
			<div class="form_box">
				
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
