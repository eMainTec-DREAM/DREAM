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
<%@ page import="dream.consult.login.action.ConsultAction" %>
<%@ page import="common.bean.MwareConfig"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
String commonContextPath = request.getContextPath();
String initCtPath = MwareConfig.getInitCtPath();
String compNo = MwareConfig.getCompNo();

List<Map> compList = MwareConfig.getCompanies();
List<Map> langList = MwareConfig.getLanguages();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script language="javascript">
</script>

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
var userIdCookie = location.hostname + "_" +  location.port + "_" + "GAIA.userId";
var localeCookie = location.hostname + "_" +  location.port + "_" + "GAIA.locale";
var localeDescCookie = location.hostname + "_" +  location.port + "_" + "GAIA.localeDesc";
var compNoCookie = location.hostname + "_" +  location.port + "_" + "GAIA.compNo";

var popupDivId,DECORATOR_NAME,parentPopupDivId;

/**
 * 화면 loading 시 호출된다.
 */
function loadPage()
{
	var form = document.consultForm;

	form.elements["consultDTO.userNo"].value = getCookie(userIdCookie);
	var locale = getCookie(localeCookie);
	var localeDesc = getCookie(localeDescCookie);
	
	
	
	if (locale != "")
	{
		form.elements["consultDTO.locale"].value = locale;
		form.elements["consultDTO.localeDesc"].value = localeDesc;
	}
	
	if (form.elements["consultDTO.userNo"].value == "")
	{
		form.elements["consultDTO.userNo"].focus();
	}
	else
	{
		form.elements["consultDTO.passWord"].focus();
	}	

}

function goLogin()
{
	var form = document.consultForm;
	
	var userID = form.elements['consultDTO.userNo'];
	var password = form.elements['consultDTO.passWord'];
	var locale = form.elements['consultDTO.locale'];
	var localeDesc = form.elements["consultDTO.localeDesc"];
	
	userID.value = trim(userID.value);
	userID.value = userID.value.toUpperCase();
	password.value = trim(password.value);
	
	if(userID.value == "")
	{
		// 아이디를 입력바랍니다.
		alert("<bean:message key='MESSAGE.MSG0006'/>");
		userID.focus();
		return;
	}
	
	if(password.value == "")
	{
		// PassWord를 입력바랍니다.
		alert("<bean:message key='MESSAGE.MSG0007'/>");
		password.focus();
		return;
	}

	if(locale.value == "" )
	{
		// 언어를 선택바랍니다.
		alert("<bean:message key='MESSAGE.MSG0045'/>");
		locale.focus();
		return;
	}

	//ID 와 PW 암호화
	//ID 에 새로운 변수를 설정한 이유 : 로그인 실행시 암호화된 값을 보여주지 않기 위함.
	//form.elements['consultDTO.vUserNo'].value = AES_Encode(userID.value);
	//password.value = AES_Encode(password.value);

	//id 저장--변경된 디자인에는 id저장이없어서 주석처리해둠 2015-12-23 김정우
 	goSaveId(form);
 	//$("#login_form").jCryption();

	//form.elements['consultDTO.userNo'].value='';

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
	
	setCookie(userIdCookie, form.elements["consultDTO.userNo"].value, expdate);
	setCookie(localeCookie, form.elements["consultDTO.locale"].value, expdate);
	setCookie(localeDescCookie, form.elements["consultDTO.localeDesc"].value, expdate);
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
	var form = document.consultForm;
	form.elements["consultDTO.locale"].value = lang;
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

</script>
<script src="<c:url value="/common/js/listOfVal.js" />" ></script>
</head>
<body onload="loadPage();" class="login_body">
<html:form action="/consult">
	<input type = "hidden" name="strutsAction" value="<%=ConsultAction.USER_FIND%>"/>
	<html:hidden property="consultDTO.compNo" value = "000"/>
	<html:hidden property="consultDTO.locale"/>
	<html:hidden property="consultDTO.vUserNo"/>
	<html:hidden property="consultDTO.valid"/>
	<div class="login_wrap">
		<div class="config_login_logo">
			<h1>Dream</h1>
		</div>
		<div class="login_box">
			<div class="login_form">
				<p class="login_txt"><bean:message key='LABEL.loginPgMsg02'/></p>
           		<div class="lgf_box">
           			<html:text property="consultDTO.localeDesc" readonly="true"></html:text>
           			<p class="open_spop" style="right:10px; top:10px;">
           				<a href="javascript:lovSysDir2('consultDTO.locale', 'consultDTO.localeDesc','LANG');">
           					<span>조회</span>
           				</a>
           			</p> 
           		</div>
				<div class="lgf_box"><html:text property="consultDTO.userNo" tabindex="10"/></div>
				<div class="lgf_box"><html:password property="consultDTO.passWord" value="" onkeydown="javascript: if (event.keyCode == 13) {goLogin();}" tabindex="20"></html:password></div>
				<div class="login_btn"><input name="" type="button" value="Login" onclick="javascript:goLogin();"></div>
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

	$('[name="consultDTO.localeDesc"]').attr("placeholder", "Language");
	$('[name="consultDTO.userNo"]').attr("placeholder", "User No");
	$('[name="consultDTO.passWord"]').attr("placeholder", "Password");
	
	if(<%=langList.size()%> == 1)
	{
		var langCode = '<%=((Map)langList.get(0)).get("CODE")%>';
		$('[name="consultDTO.localeDesc"]').parents('.lgf_box').hide();
		$('[name="consultDTO.locale"]').val(langCode);
		$('[name="consultDTO.localeDesc"]').val(langCode);
	}
	else if(<%=langList.size()%> == 0)
	{
		alert("<bean:message key='MESSAGE.CMSG0031'/>");
	}
		
	

});	

var consultForm = document.consultForm;
var bottomMessage = '<%=bottomMessage%>';
if (bottomMessage != null && bottomMessage != '')
{
	if (bottomMessage == "LOGINERROR")
	{
		// 이미 로그인을 하셨습니다. 강제로 로그아웃을 하신 다음 다시 로그인 하시겠습니까?
		if (confirm("<bean:message key='MESSAGE.MSG0005'/>"))
		{
			consultForm.strutsAction.value = '<%= ConsultAction.USER_OK_LOGOUT%>';
			consultForm.elements["consultDTO.valid"].value="valid";
			//ID 와 PW 암호화
	//ID 에 새로운 변수를 설정한 이유 : 로그인 실행시 암호화된 값을 보여주지 않기 위함.
	var userID = consultForm.elements['consultDTO.userNo'];
	consultForm.elements['consultDTO.vUserNo'].value = AES_Encode(userID.value);

			consultForm.submit();
		}
		else
		{
			consultForm.elements["consultDTO.userNo"].value = "";
			consultForm.elements["consultDTO.passWord"].value = "";
			consultForm.elements["consultDTO.userNo"].focus();
		}
	}
	else
	{
		alert(bottomMessage);
		consultForm.elements["consultDTO.userNo"].focus();
	}
}
</script>

</body>
</html>