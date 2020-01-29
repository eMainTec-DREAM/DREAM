<%--===========================================================================
부품Image Link
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.PartListImgLinkUrlAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.MwareConfig"%>
<html:html>
<head>
<title><bean:message key='PAGE.partListImgLinkUrlDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
    setTitle("partListImgLinkUrlDTO.ptImgUrlId");
   
    //For Save
    setForUpdate();
    
	if(ckCreate(currentPageId)) goInput();
}

/**
 * 입력
 */
function goInput()
{
	// 시퀀스를 조회한다.
    sequenceNextVal('SQAPTIMGURL_ID');
}

function setSequenceVal(sequenceVal)
{
    partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) partListImgLinkUrlForm.strutsAction.value = "<%=PartListImgLinkUrlAction.DETAIL_INPUT%>";
    else partListImgLinkUrlForm.strutsAction.value = "<%=PartListImgLinkUrlAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/partListImgLinkUrlDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(partListImgLinkUrlForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) 
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("partListImgLinkUrlDTO.ptImgUrlId");
}

/**
 * 이미지 수신
 */
function goGetimage() 
{
	if(checkIsUpdate(document)){
		alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0295'/>", function(result){
			if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				
				partListImgLinkUrlForm.strutsAction.value = "<%=PartListImgLinkUrlAction.GET_IMAGE%>";
				
				var actionUrl = contextPath + "/partListImgLinkUrlDetail.do";
				
				$.post(actionUrl, FormQueryString(partListImgLinkUrlForm)).done(function(_data){
					afterGetimage(JSON.parse(_data));
				});
			}
		});
	}
}

/**
 * 이미지 수신후 호출
 */
function afterGetimage(_dataObj)
{
	if (parent.findGridRow)parent.findGridRow(partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value);
	
	if(_dataObj.state != 'ERROR'){
		if(searchPage("maPtMstrDetail").loadSlideImages) searchPage("maPtMstrDetail").loadSlideImages();
	}
	
	alertMessage1(_dataObj.info);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partListImgLinkUrlDetail.do">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="partListImgLinkUrlDTO.ptImgUrlId" />
		<html:hidden property="partListImgLinkUrlDTO.partId" />
		<html:hidden property="partListImgLinkUrlDTO.isReceived" />
		<html:hidden property="partListImgLinkUrlDTO.imageReceiveStatus" />

		<div class="article_box">
			<div class="form_box">
				<!-- Imgae Link(URL) -->
	            <div class="field_long">
					<label><bean:message key="LABEL.imageLinkURL"/></label>
					<div class="input_box">
						<html:text property="partListImgLinkUrlDTO.imgUrl" tabindex="10" />
					</div>
				</div>
				<!-- Image 수신여부 -->
	            <div class="field">
					<label><bean:message key="LABEL.isImageReceived"/></label>
					<div class="input_box">
						<html:text property="partListImgLinkUrlDTO.isReceivedDesc" tabindex="20" readonly="true" />
					</div>
				</div>
				<!-- Link정보 수정시간 -->
	            <div class="field">
					<label><bean:message key="LABEL.linkedUpdateTime"/></label>
					<div class="input_box">
						<html:text property="partListImgLinkUrlDTO.updDate" tabindex="30" readonly="true" />
					</div>
				</div>
				<!-- Imgae 수신진행상태 -->
	            <div class="field">
					<label><bean:message key="LABEL.imageReceiveStatus"/></label>
					<div class="input_box">
						<html:text property="partListImgLinkUrlDTO.imageReceiveStatusDesc" tabindex="40" readonly="true" />
					</div>
				</div>
				<!-- Image 수신시간 -->
	            <div class="field">
					<label><bean:message key="LABEL.imageReceivedTime"/></label>
					<div class="input_box">
						<html:text property="partListImgLinkUrlDTO.receivedTime" tabindex="50" readonly="true" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
