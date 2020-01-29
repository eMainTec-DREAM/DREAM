
<%--===========================================================================
구매입고 item 상세
author  kim21017
version $Id: maPtRecSerialDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@page import="dream.part.rec.action.MaPtRecSerialDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.partDesc"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maPtRecSerialDetailDTO.serialNo");
	setForUpdate();
	goUpdate();


    //alert($(parent.document).find('[name="maPtRecDetailDTO.partId"]').val());
}


function goUpdate()
{
    //수정시 readonly설정 
    //maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  
    // 버튼 활성화 
    var prRecListStatus = maPtRecSerialDetailForm.elements['maPtRecCommonDTO.prRecStatus'].value;

    if(prRecListStatus == "W") // 작성중 
    {
	    $(document).find('.b_save').show();

	    // 입력 Form disable
        setEnable($(".form_box"));
    }
    else if(prRecListStatus == "C") // 입고완료 
    {
        $(document).find('.b_save').hide();

        // 입력 Form disable
        setDisable($(".form_box"));
    }
    else
   	{
    	$(document).find('.b_save').show();
   	}
}


function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPRRECLIST_SERIAL_ID');
	
}

function setSequenceVal(sequenceVal)
{
	maPtRecSerialDetailForm.elements['maPtRecSerialDetailDTO.serialId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
/* 	if(parseInt(maPtRecSerialDetailForm.elements['maPtRecSerialDetailDTO.recQty'].value) <= 0 )
    {
    	alertMessage1("<bean:message key='LABEL.useQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    } */
    if(ckCreate(currentPageId)){
    	  //중복 시리얼 체크
    	validSerial();
    	if(isValid!='0') return;
    }

    
	if(ckCreate(currentPageId)) maPtRecSerialDetailForm.strutsAction.value = '<%=MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_INPUT%>';
	else maPtRecSerialDetailForm.strutsAction.value = '<%= MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPtRecSerialDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtRecSerialDetailForm), 'afterSave');
}

function validSerial(){
	var actionUrl = contextPath + "/maPtRecSerialDetail.do";
	var param =  "&strutsAction=" + '<%= MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_CHECK %>'
	          +  "&" + FormQueryString(maPtRecSerialDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidSerial');
}

var isValid;

function afterValidSerial(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0157'/>");
		maPtRecSerialDetailForm.elements['maPtRecSerialDetailDTO.serialNo'].value = '';
    }
}


function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPtRecSerialDetailForm.elements['maPtRecSerialDetailDTO.serialId'].value);

    setTitle("maPtRecSerialDetailDTO.serialNo");
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.maPtRecSerialDetailForm;

	goCommonTabPage(form, '' , pageId);
}

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtRecSerialDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtRecCommonDTO.prRecListId"/>
<html:hidden property="maPtRecSerialDetailDTO.serialId"/>
<html:hidden property="maPtRecSerialDetailDTO.partId"/>
<html:hidden property="maPtRecCommonDTO.prRecStatus"/><!-- Key -->
<html:hidden property="maPtRecCommonDTO.partId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.Serial"/></label>
					<div class="input_box">
						<html:text property="maPtRecSerialDetailDTO.serialNo" tabindex="20"/>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="maPtRecSerialDetailDTO.remark" styleClass="ta350" tabindex="70" />
					</div>
				</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>