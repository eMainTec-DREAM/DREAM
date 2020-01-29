<%--===========================================================================
Login Page
author  ssong
version $Id: index.jsp,v 1.8 2014/02/25 01:48:48 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt" %>
<%@ page import="dream.login.login.action.LoginAction" %>
<%@ page import="common.bean.MwareConfig"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<% 
String commonContextPath = request.getContextPath();
String initCtPath = MwareConfig.getInitCtPath();
String compNo = MwareConfig.getCompNo();

List<Map> compList = MwareConfig.getCompanies();
List<Map> langList = MwareConfig.getLanguages();
String safetyLength = MwareConfig.getIsPwSafetyLength();
String pwChangeHistCnt = MwareConfig.getPwChangeHistCnt();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script language="javascript">
</script>
<script src="<c:url value="/common/dhtmlxSuite/codebase/dhtmlx.js" />" ></script>

<head>
<!-- 설비관리시스템 -->
<title><bean:message key="MENU.INDEX"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="initCtPath" value="<%=initCtPath %>" />
<link rel="shortcut icon" href="<c:url value="/common/images/${initCtPath}/favicon_dream.ico" />" type="image/x-icon" />
<link rel="icon" href="<c:url value="/common/images/${initCtPath}/favicon_dream.ico" />" type="image/x-icon" />

<link rel="stylesheet" href="<c:url value="/common/css/ma/style.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/common/css/ma/style_${initCtPath}.css" />" type="text/css" />
<!-- 공통 페이지 -->
<script src="<c:url value="/common/js/common.js" />" ></script>
<!--  jQuery -->
<script src="<c:url value="/common/jquery/jquery-1.11.1.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery-ui.min.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery.form.min.js" />" ></script>
<script src="<c:url value="/common/jquery/gibberish-aes.js" />" ></script>

<script language="javascript">
var lovSysDirDefaultAction = '<%=common.finder.valid.action.ListOfValAction.LOV_SYS_DIR_DEFAULT%>';
var contextPath = '<%=commonContextPath%>';
var userIdCookie = location.hostname + "_" +  location.port + "_" + "OTTOGI.userId";
var localeCookie = location.hostname + "_" +  location.port + "_" + "OTTOGI.locale";
var localeDescCookie = location.hostname + "_" +  location.port + "_" + "OTTOGI.localeDesc";
var compNoCookie = location.hostname + "_" +  location.port + "_" + "OTTOGI.compNo";
var compDescCookie = location.hostname + "_" +  location.port + "_" + "OTTOGI.compDesc";

var popupDivId,DECORATOR_NAME,parentPopupDivId;

/**
 * 화면 loading 시 호출된다.
 */
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
	
	//비밀번호를 변경해 주세요
	//alert("<bean:message key='MESSAGE.CMSG101'/>");
	var form = document.loginForm;

	form.elements["loginDTO.userNo"].value = getCookie(userIdCookie);
	var locale = getCookie(localeCookie);
	var localeDesc = getCookie(localeDescCookie);
	var compNo = getCookie(compNoCookie);
	
	if(form.elements["loginDTO.compNo"].value == "" && compNo != "")
	{
		form.elements["loginDTO.compNo"].value = compNo;
	}
	
	if (locale != "")
	{
		form.elements["loginDTO.locale"].value = locale;
		form.elements["loginDTO.localeDesc"].value = localeDesc;
	}
	
	if (form.elements["loginDTO.userNo"].value == "")
	{
		form.elements["loginDTO.userNo"].focus();
	}
	else
	{
		form.elements["loginDTO.passWord"].focus();
	}	

}

function goLogin()
{
	var form = document.loginForm;
	
	var userID = form.elements['loginDTO.userNo'];
	var password = form.elements['loginDTO.passWord'];
	var otp = form.elements['loginDTO.otp'];
	var newPassword = form.elements['loginDTO.newPassWord'];
	var validPassword = form.elements['loginDTO.validPassWord'];
	var locale = form.elements['loginDTO.locale'];
	var localeDesc = form.elements["loginDTO.localeDesc"];
	var compNo = form.elements["loginDTO.compNo"];
	var compDesc = form.elements["loginDTO.compDesc"];

	userID.value = trim(userID.value);
	userID.value = userID.value.toUpperCase();
	
	form.elements["strutsAction"].value = '<%=LoginAction.PW_CHANGE%>';
	
	if(userID.value == "")
	{
		// 아이디를 입력바랍니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0006'/>");
		userID.focus();
		return;
	}
	
	if(password.value == "")
	{
		// PassWord를 입력바랍니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0007'/>");
		password.focus();
		return;
	}
	
	if('<%=MwareConfig.getIsUseOtpLogin()%>' == "Y" && otp.value == "")
	{
		// otp를 입력바랍니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0241'/>");
		otp.focus();
		return;
	}

	if(locale.value == "")
	{
		// 언어를 선택바랍니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0045'/>");
		locale.focus();
		return;
	}

	if(compNo.value == "")
	{
		// 회사를 선택바랍니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0223'/>");	// 메시지 추가
		compDesc.focus();
		return;
	}
	// 비밀번호 입력길이제한
	if(password.value.length > 30) 
	{
		// 계정정보가 맞지 않습니다.
		alertMessage3("<bean:message key='MESSAGE.MSG1002'/>");	// 메시지 추가
		password.focus();
		return;
	}
	    
	//Safety Length 가 Y 인경우에만 해당 validation 체크한다.
	if("<%=safetyLength%>"=='Y')
	{

		if(!newPassword.value.match(/^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/)) {
	    	//비밀번호는 숫자와 영문 특수문자를 혼합해야 합니다.
	    	alertMessage1("<bean:message key='MESSAGE.MSG0082' />");
	    	//alertMessage1("<bean:message key='MESSAGE.MSG0082' />");
	    	newPassword.focus();
	    	return;
		}
	
	}

	//ID 와 PW 암호화
	//ID 에 새로운 변수를 설정한 이유 : 로그인 실행시 암호화된 값을 보여주지 않기 위함.
	//form.elements['loginDTO.vUserNo'].value = AES_Encode(userID.value);
	//password.value = AES_Encode(password.value);

	//id 저장--변경된 디자인에는 id저장이없어서 주석처리해둠 2015-12-23 김정우
 	goSaveId(form);
 	//$("#login_form").jCryption();

	//form.elements['loginDTO.userNo'].value='';
	var date = new Date();
	form.elements["loginDTO.localTime"].value = date.getTime()-date.getTimezoneOffset()*60000;
	
	form.submit();
}

function goSaveId(form)
{
	var expdate = new Date();

//	if(form.saveID.checked)
//	{
		expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
//	}
//	else
//	{
//		expdate.setTime(expdate.getTime() - 1);
//	}
	
	setCookie(userIdCookie, form.elements["loginDTO.userNo"].value, expdate);
	setCookie(localeCookie, form.elements["loginDTO.locale"].value, expdate);
	setCookie(localeDescCookie, form.elements["loginDTO.localeDesc"].value, expdate);
	setCookie(compNoCookie, form.elements["loginDTO.compNo"].value, expdate);
	setCookie(compDescCookie, form.elements["loginDTO.compDesc"].value, expdate);
}

function setCookie (name, value, expires) 
{
	document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) 
{
	var search = Name + "=";

	if (document.cookie.length > 0) 
	{ 
		// 쿠키가 설정되어 있다면
	  	offset = document.cookie.indexOf(search);

	  	if (offset != -1) 
	  	{ 
	  		// 쿠키가 존재하면
	    	offset += search.length;
	    	// set index of beginning of value
	    	end = document.cookie.indexOf(";", offset);
	    	// 쿠키 값의 마지막 위치 인덱스 번호 설정
	    	if (end == -1)
	      		end = document.cookie.length;
	    	return unescape(document.cookie.substring(offset, end));
	  	}
	}
	
	return "";
}

/**
 * 마우스 다운시 버튼의 누른 효과를 준다.
 */
var mouseDownObj;
function onLoginMouseDown(buttonObj)
{
    if (isIE) buttonObj.style.filter = "alpha(opacity=60)";
    else buttonObj.style.opacity = 0.6;
    
    mouseDownObj = buttonObj;
}
/**
 * 마우스 다운시 버튼의 누른 효과를 없애준다.
 */
function onLoginMouseUp()
{
    if (mouseDownObj)
    {
	    if (isIE) mouseDownObj.style.filter = "";
	    else mouseDownObj.style.opacity = 1;
	}
}

/**
 * 언어변경시
 */
function changeLang(){
	var lang = document.getElementById("langOptions").options[document.getElementById("langOptions").selectedIndex].value;
	var form = document.loginForm;
	form.elements["loginDTO.locale"].value = lang;
}

function lovSysDir2(_codeId, _descId, _codeType)
{
	var pValId = new Array();
    pValId[0] = _codeId;
    pValId[1] = _descId;
    
    lovValueId      = pValId;
    if(typeof extCode1 == "undefined") extCode1 = "";
    var param = "strutsAction=" + "2005" +
                "&" + "listOfValDTO.codeType=" + _codeType+
                "&" + "popupWidth="+300;

    openLayerPopup("listOfVal", param);
}


/**
 * 취소버튼 클릭시 index로 이동
 */
function goCancel()
{
	var form = document.loginForm;
	
	form.elements["strutsAction"].value = '<%=LoginAction.CANCEL%>';

	form.submit();
}

</script>
<script src="<c:url value="/common/js/listOfVal.js" />" ></script>
<link rel="stylesheet" href="<c:url value="/common/dhtmlxSuite/codebase/dhtmlx_ma.css" />" type="text/css">
</head>
<body onload="loadPage();" class="login_body">
<html:form action="/index">
	<html:hidden property="strutsAction"/>
	<html:hidden property="loginDTO.vUserNo"/>
	<html:hidden property="loginDTO.valid"/>
	<html:hidden property="loginDTO.compNo"/>
	<html:hidden property="loginDTO.compDesc"/>
	<html:hidden property="loginDTO.locale"/>
	<html:hidden property="loginDTO.localeDesc"/>
	<html:hidden property="loginDTO.localTime"/>
	<div class="login_wrap" style="margin-top:150px; width:400px;">
		<div class="login_box" >
			<div class="login_form" style="width:320px;">
				<p class="login_pwcimg"></p>
				<p class="login_txt" style="margin:15px 0; font-size:18px; color:#333; font-weight:bold; text-align:center;"><bean:message key="MESSAGE.CMSG101"/></p>
				<ul style="margin:10px 0 30px 15px; list-style:disc; color:#777; font-size:12px;">
			 		<li id = "pwChangeHistCnt" style="margin:4px 0;"><label><bean:message key="MESSAGE.MSG0251"/><%=pwChangeHistCnt%><bean:message key="MESSAGE.MSG0252"/></label></li>
			 		<li id = "pwChangePolicy" style="margin:4px 0;"><label><label><bean:message key="MESSAGE.MSG220"/></label></li>
			 	</ul>
				<div class="lgf_box" style="background-color:#f7f7f7; border:1px solid #ebebeb;"><html:text property="loginDTO.userNo" tabindex="30" readonly="true"/></div>
				<div class="lgf_box"><html:password property="loginDTO.passWord" value=""  tabindex="40"></html:password></div>
				<div class="lgf_box"><html:password property="loginDTO.newPassWord" value=""  tabindex="40"></html:password></div>
				<div class="lgf_box"><html:password property="loginDTO.validPassWord" value="" onkeydown="javascript: if (event.keyCode == 13) {goLogin();}" tabindex="50"></html:password></div>
				<div class="lgf_box"><html:password property="loginDTO.otp" value="" onkeydown="javascript: if (event.keyCode == 13) {goLogin();}" tabindex="50"></html:password></div>
				<div style="position:relative; height:40px;">
					<div class="login_btn" style="width:48%; position:absolute; left:0; margin-top:0;"><input name="" type="button" value="변경하기" onclick="javascript:goLogin();"></div>
					<div class="login_btn" style="width:48%; position:absolute; right:0; margin-top:0;"><input name="" type="button" value="취소" onclick="javascript:goCancel();" style="background-color:#999;"></div>
				</div>
			</div>
		</div>
		<div class="login_footer">
			<div class="foot_txt"><bean:message key='LABEL.loginPgMsg03'/></div>
		</div>
	</div>
</html:form> 
<form name="bottomForm" method="post" action="" style="border:0;" >
<c:import charEncoding="UTF-8" url="/common/ajax/ajaxValidation.jsp"></c:import>
</form>
<%
// 모든 변수는 이 페이지를 포함한 페이지에서 변수이름 충돌을 방지하기 위해서 
// 앞에 bottom 을 붙인다.

Object bottomMessageObject = request.getAttribute("MESSAGE");
String bottomMessage = "";
if (bottomMessageObject != null)
{
    bottomMessage = (String)bottomMessageObject;
}
%>
<script>

jQuery(function($){

	$('[name="loginDTO.compNo"]').attr("placeholder", "Company Number");
	$('[name="loginDTO.localeDesc"]').attr("placeholder", "Language");
	$('[name="loginDTO.userNo"]').attr("placeholder", "User No");
	$('[name="loginDTO.passWord"]').attr("placeholder", "Current Password");
	$('[name="loginDTO.newPassWord"]').attr("placeholder", "New Password");
	$('[name="loginDTO.validPassWord"]').attr("placeholder", "Valid Password");
	$('[name="loginDTO.otp"]').attr("placeholder", "OTP");
	
	if(<%=langList.size()%> == 1)
	{
		var langCode = '<%=((Map)langList.get(0)).get("CODE")%>';
		$('[name="loginDTO.localeDesc"]').parents('.lgf_box').hide();
		$('[name="loginDTO.locale"]').val(langCode);
	}
	else if(<%=langList.size()%> == 0)
	{
		alertMessage1("<bean:message key='MESSAGE.CMSG0031'/>");
	}
		
	if(<%=compList.size()%> == 1)
	{
		var compCode = '<%=((Map)compList.get(0)).get("CODE")%>';
		$('[name="loginDTO.compNo"]').val(compCode).parents('.lgf_box').hide();
	}
	else if(<%=compList.size()%> == 0)
	{
		alertMessage1("<bean:message key='MESSAGE.CMSG0031'/>");
	}
	
	if('<%=MwareConfig.getIsUseOtpLogin()%>' != "Y")
	{
		$('[name="loginDTO.otp"]').parents('.lgf_box').hide();
	}

});	

var loginForm = document.loginForm;
var bottomMessage = '<%=bottomMessage%>';
if (bottomMessage != null && bottomMessage != '')
{
	alertMessage3(bottomMessage);
	loginForm.elements["loginDTO.userNo"].focus();
}
</script>
<c:import charEncoding="UTF-8" url="/common/jsp/licensePage.jsp"></c:import>

</body>
</html>