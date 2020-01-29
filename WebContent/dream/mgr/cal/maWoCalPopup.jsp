<%--===========================================================================
비밀번호 변경 - 분류 
author  kim21017
version $Id: maChangePw.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.mgr.cal.action.MaWoCalListAction" %>
<html>
<head>
<!--비밀번호 -->
<title><bean:message key="MENU.SCHEDRESET"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	//alert(maWoCalListForm.elements['maWoCalCommonDTO.selArray'].value);
	maWoCalListForm.elements['maWoCalCommonDTO.popupChangeDate'].value   = getToday();
 	document.getElementById("detailTitle").innerHTML = '<bean:message key="LABEL.schedPopup"/>';
}

function goSave(){


	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0092'/>", function(result){
		 if(result){			
				setModal("<bean:message key='MESSAGE.MSG0093'/>");
			maWoCalListForm.elements['strutsAction'].value = '<%= MaWoCalListAction.WO_CAL_POPUP_SAVE %>';
			
			var actionUrl = contextPath + "/maWoCalList.do";
			XMLHttpPost(actionUrl, FormQueryString(maWoCalListForm), 'afterSave');
		 }
	});
}

function afterSave(ajaxXmlDoc)
{
	var isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid=="false"){
		alertMessage1("<bean:message key='MESSAGE.MSG0002' />");return;
	}
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
	//저장되었습니다. 
    pageMessage = '<bean:message key="MESSAGE.MSG003"/>';
    alertMessage1(pageMessage);
    closeModal();
//  	goClose();
}

function goClose()
{
	closeLayerPopup(this);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoCalList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoCalCommonDTO.selArray"/>
	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="stitle_box" id="detailTitle"></div>
			<div class="function_box">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
					<a href="javascript:goSave();" class="b_save"><span><bean:message key="BUTTON.SCHDRSTCON"/></span></a>
				</div>
				<div class="fb_group1">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
			<!--일자 -->
			<div class="field" style="width: 70%; float: left;">
				<div class="calendar_wrap" align="left">
					<div class="input_box input_carendar">
						<html:text property="maWoCalCommonDTO.popupChangeDate" tabindex="10" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>						
				</div>
				<label class="check" style="float: right; margin-right: 50px;"><bean:message key="LABEL.afterReset"/></label>
			</div>
			<div class="field">

			</div>
			<div class="field">

			</div>
			<div class="field">

			</div>
			<div class="field">

			</div>
			<div class="field">

			</div>
			<div class="field">

			</div>
			<div class="field">

			</div>

			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
</html:form> 
</body>
</html>