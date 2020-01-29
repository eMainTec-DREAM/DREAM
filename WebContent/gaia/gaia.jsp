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
<%
String commonContextPath = request.getContextPath();
String initCtPath = MwareConfig.getInitCtPath();
String compNo = MwareConfig.getCompNo();
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

var popupDivId,DECORATOR_NAME,parentPopupDivId;

/**
 * 화면 loading 시 호출된다.
 */
function loadPage()
{
	var form = document.gaiaForm;

	form.elements["gaiaDTO.userNo"].value = getCookie(userIdCookie);
	
	if (form.elements["gaiaDTO.userNo"].value == "")
	{
		form.elements["gaiaDTO.userNo"].focus();
	}
	else
	{
		form.elements["gaiaDTO.passWord"].focus();
	}	

}

function goLogin()
{
	var form = document.gaiaForm;
	
	var userID = form.elements['gaiaDTO.userNo'];
	var password = form.elements['gaiaDTO.passWord'];
	var locale = form.elements['gaiaDTO.locale'];
	
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

	

	//ID 와 PW 암호화
	//ID 에 새로운 변수를 설정한 이유 : 로그인 실행시 암호화된 값을 보여주지 않기 위함.
	//form.elements['gaiaDTO.vUserNo'].value = AES_Encode(userID.value);
	//password.value = AES_Encode(password.value);

	//id 저장--변경된 디자인에는 id저장이없어서 주석처리해둠 2015-12-23 김정우
 	goSaveId(form);
 	//$("#login_form").jCryption();

	//form.elements['gaiaDTO.userNo'].value='';

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
	
	setCookie(userIdCookie, form.elements["gaiaDTO.userNo"].value, expdate);
	setCookie(localeCookie, form.elements["gaiaDTO.locale"].value, expdate);
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
	var form = document.gaiaForm;
	form.elements["gaiaDTO.locale"].value = lang;
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
<html:form action="/gaia">
	<input type = "hidden" name="strutsAction" value="<%=LoginAction.USER_FIND%>"/>
	<html:hidden property="gaiaDTO.locale" value="ko"/>
	<html:hidden property="gaiaDTO.compNo" value="<%=compNo %>"/>
	<html:hidden property="gaiaDTO.vUserNo"/>
	<html:hidden property="gaiaDTO.valid"/>
	<div class="login_wrap">
		<div class="login_logo">
			<h1>Mware</h1>
			<p><bean:message key='LABEL.loginPgMsg01'/></p>
		</div>
		<div class="login_box">
			<div class="login_form">
				<p class="login_txt"><bean:message key='LABEL.loginPgMsg02'/></p>
				
				<div class="lgf_box"><html:text property="gaiaDTO.userNo" tabindex="10"/></div>
				<div class="lgf_box"><html:password property="gaiaDTO.passWord" value="" onkeydown="javascript: if (event.keyCode == 13) {goLogin();}" tabindex="20"></html:password></div>
				<div class="login_btn"><input name="" type="button" value="Login" onclick="javascript:goLogin();"></div>
			</div>
		</div>
		<div class="login_footer">
			<div class="foot_txt"><bean:message key='LABEL.loginPgMsg03'/></div>
		</div>
	</div>
</html:form> 
<form name="bottomForm" method="post" action="" style="border:0;" >
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
var gaiaForm = document.gaiaForm;
var bottomMessage = '<%=bottomMessage%>';
if (bottomMessage != null && bottomMessage != '')
{
	if (bottomMessage == "LOGINERROR")
	{
		// 이미 로그인을 하셨습니다. 강제로 로그아웃을 하신 다음 다시 로그인 하시겠습니까?
		if (confirm("<bean:message key='MESSAGE.MSG0005'/>"))
		{
			gaiaForm.strutsAction.value = '<%= LoginAction.USER_OK_LOGOUT%>';
			gaiaForm.elements["gaiaDTO.valid"].value="valid";
			//ID 와 PW 암호화
	//ID 에 새로운 변수를 설정한 이유 : 로그인 실행시 암호화된 값을 보여주지 않기 위함.
	var userID = gaiaForm.elements['gaiaDTO.userNo'];
	gaiaForm.elements['gaiaDTO.vUserNo'].value = AES_Encode(userID.value);

			gaiaForm.submit();
		}
		else
		{
			gaiaForm.elements["gaiaDTO.userNo"].value = "";
			gaiaForm.elements["gaiaDTO.passWord"].value = "";
			gaiaForm.elements["gaiaDTO.userNo"].focus();
		}
	}
	else
	{
		alert(bottomMessage);
		gaiaForm.elements["gaiaDTO.userNo"].focus();
	}
}
</script>

</body>
</html>