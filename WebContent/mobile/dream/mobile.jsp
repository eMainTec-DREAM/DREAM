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
<%@ page import="mobile.dream.login.action.MobileLoginAction" %>
<%@ page import="common.bean.MwareConfig"%>
<%
String compNo = MwareConfig.getCompNo();
%>
<!doctype html>
<html lang="ko">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<!-- 설비관리시스템 -->
<title><bean:message key="MENU.INDEX"/></title>
<link rel="stylesheet" href="<c:url value="/mobile/dream/css/style.css" />" type="text/css" />

<!-- 공통 페이지 -->
<script src="<c:url value="/common/js/common.js" />" ></script>
<!--  jQuery -->
<script src="<c:url value="/common/jquery/jquery-1.11.1.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery-ui.min.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery.form.min.js" />" ></script>
<script src="<c:url value="/common/jquery/gibberish-aes.js" />" ></script>

<script language="javascript">
var userIdCookie = location.hostname + "_" +  location.port + "_" + "DREAM.userId";
var localeCookie = location.hostname + "_" +  location.port + "_" + "DREAM.locale";
var localeDescCookie = location.hostname + "_" +  location.port + "_" + "DREAM.localeDesc";

var popupDivId,DECORATOR_NAME,parentPopupDivId;

/**
 * 화면 loading 시 호출된다.
 */
function loadPage()
{
 	var form = document.mobileLoginForm;

	form.elements["mobileLoginDTO.userNo"].value = getCookie(userIdCookie);
	/* var locale = getCookie(localeCookie);
	var localeDesc = getCookie(localeDescCookie);
	
	if (locale != "")
	{
		form.elements["mobileLoginDTO.locale"].value = locale;
		form.elements["mobileLoginDTO.localeDesc"].value = localeDesc;
	} */
	
	if (form.elements["mobileLoginDTO.userNo"].value == "")
	{
		form.elements["mobileLoginDTO.userNo"].focus();
	}
	else
	{
		form.elements["mobileLoginDTO.passWord"].focus();
	}	 
	
	$('button').on("click",function(e){
		goLogin();
	});

}

function goLogin()
{
	var form = document.mobileLoginForm;
	
	var userID = form.elements['mobileLoginDTO.userNo'];
	var password = form.elements['mobileLoginDTO.passWord'];
	var locale = form.elements['mobileLoginDTO.locale'];
	var localeDesc = form.elements["mobileLoginDTO.localeDesc"];
	
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

	/* if(locale.value == "" || localeDesc.value == "")
	{
		// 언어를 선택바랍니다.
		alert("<bean:message key='MESSAGE.MSG0045'/>");
		locale.focus();
		return;
	} */

	//ID 와 PW 암호화
	//ID 에 새로운 변수를 설정한 이유 : 로그인 실행시 암호화된 값을 보여주지 않기 위함.
	//form.elements['mobileLoginDTO.vUserNo'].value = AES_Encode(userID.value);
	//password.value = AES_Encode(password.value);

	//id 저장--변경된 디자인에는 id저장이없어서 주석처리해둠 2015-12-23 김정우
 	goSaveId(form);
 	//$("#login_form").jCryption();

	//form.elements['mobileLoginDTO.userNo'].value='';

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
	
	setCookie(userIdCookie, form.elements["mobileLoginDTO.userNo"].value, expdate);
	setCookie(localeCookie, form.elements["mobileLoginDTO.locale"].value, expdate);
	//setCookie(localeDescCookie, form.elements["mobileLoginDTO.localeDesc"].value, expdate);
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
	var form = document.mobileLoginForm;
	form.elements["mobileLoginDTO.locale"].value = lang;
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
<html:form action="/mobile">
	<input type = "hidden" name="strutsAction" value="<%=MobileLoginAction.MOBILE_LOGIN%>"/>
	<html:hidden property="mobileLoginDTO.locale"/>
	<html:hidden property="mobileLoginDTO.compNo" value="<%=compNo %>"/>
	<html:hidden property="mobileLoginDTO.vUserNo"/>
	<html:hidden property="mobileLoginDTO.valid"/>
	<div class="login_wrap">
	<h1><span>Dream Mobile</span></h1>
		<div class="login_box">
	    	<div class="lf_box">
	    		<label>User ID</label>
	    		<html:text property="mobileLoginDTO.userNo" tabindex="10"/>
	    	</div>
	        <div class="lf_box">
	        	<label>Password</label>
	        	<html:password property="mobileLoginDTO.passWord" value="" onkeydown="javascript: if (event.keyCode == 13) {goLogin();}" tabindex="20" />
	        </div>
	        <div class="lb_box">
	        	<button type="button">로그인</button>
	        </div>
	    </div>
	    <div class="ect_box">
	    	<div class="remem"><input type="checkbox" value=""> Remember Me</div>
	        <div class="goset"><a href="#">처음이신가요?</a></div>
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
var mobileLoginForm = document.mobileLoginForm;
var bottomMessage = '<%=bottomMessage%>';
if (bottomMessage != null && bottomMessage != '')
{
	if (bottomMessage == "LOGINERROR")
	{
		// 이미 로그인을 하셨습니다. 강제로 로그아웃을 하신 다음 다시 로그인 하시겠습니까?
		if (confirm("<bean:message key='MESSAGE.MSG0005'/>"))
		{
			mobileLoginForm.strutsAction.value = '<%= MobileLoginAction.USER_OK_LOGOUT%>';
			mobileLoginForm.elements["mobileLoginDTO.valid"].value="valid";
			//ID 와 PW 암호화
	//ID 에 새로운 변수를 설정한 이유 : 로그인 실행시 암호화된 값을 보여주지 않기 위함.
	var userID = mobileLoginForm.elements['mobileLoginDTO.userNo'];
	mobileLoginForm.elements['mobileLoginDTO.vUserNo'].value = AES_Encode(userID.value);

			mobileLoginForm.submit();
		}
		else
		{
			mobileLoginForm.elements["mobileLoginDTO.userNo"].value = "";
			mobileLoginForm.elements["mobileLoginDTO.passWord"].value = "";
			mobileLoginForm.elements["mobileLoginDTO.userNo"].focus();
		}
	}
	else
	{
		alert(bottomMessage);
		mobileLoginForm.elements["mobileLoginDTO.userNo"].focus();
	}
}
</script>

</body>
</html>