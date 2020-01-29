<%--===========================================================================
비밀번호 변경 - 분류 
author  kim21017
version $Id: maChangePw.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@page import="common.bean.MwareConfig"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.info.action.MaChangePwAction"%>
<jsp:useBean id="maChangePwForm" class="dream.pers.priv.info.form.MaChangePwForm" scope="request" />
<%
	String safetyLength = MwareConfig.getIsPwSafetyLength();
	String pwChangeHistCnt = MwareConfig.getPwChangeHistCnt();
%>
<html>
<head>
<!--비밀번호 -->
<title><bean:message key="MENU.CHANGEPASSWORD"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	// 비밀번호 변경정책 보여주기
	if("<%=safetyLength%>"=='Y')
		$('#pwChangePolicy').show();
	else
		$('#pwChangePolicy').hide();
	
	if("<%=pwChangeHistCnt%>"!='0')
		$('#pwChangeHistCnt').show();
	else
		$('#pwChangeHistCnt').hide();
	
	document.getElementById("detailTitle").innerHTML = maChangePwForm.elements['maChangePwDTO.userNo'].value+"/"
													+maChangePwForm.elements['maChangePwDTO.userName'].value;
}

function goSave(){
	var oldPw = trim(maChangePwForm.elements['maChangePwDTO.oldPw'].value);
	var newPw = trim(maChangePwForm.elements['maChangePwDTO.newPw'].value);
	var confirmPw = trim(maChangePwForm.elements['maChangePwDTO.confirmPw'].value);

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
		
	// 기존 비밀번호와 새 비밀번호가 같음
	if(oldPw==newPw)
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0173' />");return;
	}
	
	// 새 비밀번호와 확인용 새 비밀번호와 다름.
	if (newPw != confirmPw){
		alertMessage1("<bean:message key='MESSAGE.MSG0001' />");return;
	}
	
	if(maChangePwForm.elements['maChangePwDTO.newPw'].value.length < 8 | 
    		maChangePwForm.elements['maChangePwDTO.newPw'].value.length > 12)
	{	
    	//비밀번호는 8~12자만 사용 가능합니다.
    	alertMessage1("<bean:message key='MESSAGE.MSG0081' />"); return;
	}
    
	//Safety Length 가 Y 인경우에만 해당 validation 체크한다.
	if("<%=safetyLength%>"=='Y')
	{

		if(!maChangePwForm.elements['maChangePwDTO.newPw'].value.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)) {
	    	//비밀번호는 숫자와 영문 특수문자를 혼합해야 합니다.
	    	alertMessage1("<bean:message key='MESSAGE.MSG0082' />"); return;
		}
	
	}
	
	maChangePwForm.elements['strutsAction'].value = '<%= MaChangePwAction.CHG_PW_UPDATE %>';
	
	var actionUrl = contextPath + "/maChangePw.do";
	XMLHttpPost(actionUrl, FormQueryString(maChangePwForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	var rtnMsg = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(rtnMsg != "")
	{
		alertMessage1(rtnMsg);
		return;
	}

	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
	//저장되었습니다. 
    pageMessage = '<bean:message key="MESSAGE.MSG003"/>';
    alertMessage1(pageMessage);
  	goClose();
}

function goClose()
{
	closeLayerPopup(this);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maChangePw.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maChangePwDTO.oldPwTemp"/>
<html:hidden property="maChangePwDTO.userId"/>
<html:hidden property="maChangePwDTO.userNo"/>
<html:hidden property="maChangePwDTO.userName"/>
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
					<a href="javascript:goSave();" class="b_save"><span><bean:message key="BUTTON.SAVE"/></span></a>
				</div>

				<div class="fb_group1">
					
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<div class="field_long">
					<!-- 기존비밀번호 -->
					<label class="check"><bean:message key="LABEL.oldPassword"/></label>
					<div class="input_box">
						<html:password property="maChangePwDTO.oldPw" tabindex="10"/>
					</div>
				</div>
				<div class="field_long">
					<!-- 신규비밀번호 -->
					<label class="check"><bean:message key="LABEL.newPassword"/></label>
					<div class="input_box">
						<html:password property="maChangePwDTO.newPw" tabindex="20"/>
					</div>
				</div>
				<div class="field_long">
					<!-- 확인비밀번호 -->
					<label class="check"><bean:message key="LABEL.confirmPassword"/></label>
					<div class="input_box" >
						<html:password property="maChangePwDTO.confirmPw" tabindex="30"/>
					</div>
				</div>
			</div>
			<ul style="margin:5px 0 20px 135px; list-style:disc; color:#777; font-size:12px;">
			 		<li id = "pwChangeHistCnt" style="margin:4px 0;"><label><bean:message key="MESSAGE.MSG0251"/><%=pwChangeHistCnt%><bean:message key="MESSAGE.MSG0252"/></label></li>
			 		<li id = "pwChangePolicy" style="margin:4px 0;"><label><label><bean:message key="MESSAGE.MSG220"/></label></li>
			</ul>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
</html:form> 
</body>
</html>