<%--===========================================================================
page 가장 상단에 include하여 사용
ex.) c taglib 이용 하여 include 
     <c:import charEncoding="UTF-8"  url="/common/commonInclude.jsp"></c:import>
author  javaworker
version $Id: commonInclude.jsp,v 1.4 2014/03/17 01:01:46 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@page import="common.util.CommonUtil"%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ page import="dream.login.login.action.LoginAction" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User" %>
<%@ page import="org.apache.commons.beanutils.BeanUtils"%>
<%@ page import="java.lang.reflect.Field"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="common.bean.MwareConfig"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%

	//session.setMaxInactiveInterval(20);
	//userServiceImpl.setSessionListener(session);
	User user = (User)session.getAttribute(session.getId());
	String hostIp = request.getLocalAddr();

	String popupDivId = (String)request.getParameter("popupDivId");
    String parentPopupDivId = (String)request.getParameter("parentPopupDivId");
    //GAIA, ENHANCE, MOBILE
    String pageType = String.valueOf(session.getAttribute("pageType"));
	pageType= pageType=="null"?"index":pageType;

    Hashtable fieldTable = (Hashtable)session.getAttribute("PAGEFIELD");
    //Hashtable fieldTable = MwareConfig.getPageFieldTable();
    String cPId = (String)request.getAttribute("currentPageId");
	String maxLoadCount = MwareConfig.getGridMaxLoadCount();
    

    List fieldList = null;
    String jsonString = "";
    if(fieldTable != null && fieldTable.containsKey(cPId))
    {
        fieldList = (List)fieldTable.get(cPId);
		Gson gson = new Gson();
        
        jsonString = gson.toJson(fieldList);
    }

    Object object = session.getAttribute("MENU");
    List<Map> menuList = null;
    if (object != null) menuList = (List<Map>)object;        

%>
<!-- 페이지 Cache를 하지 않게 하기 위해 구현 -->
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<!-- 페이지 Cache를 하지 않게 하기 위해 구현  -->
<script>
// 현재 page 수정여부

var isUpdating = false;
var DECORATOR_NAME = "";
var loginUser, isHeightCal = false;
var selectdMenuName = "";
var popupDivId = <%=popupDivId%>;  //현재 popup ID
var parentPopupDivId = "<%=parentPopupDivId%>"; //parent popup ID
var maxLoadCount = <%=maxLoadCount%>;
var menuJArray, myVault, PAGE_TYPE;

<%
if (request.getAttribute(com.opensymphony.module.sitemesh.RequestConstants.DECORATOR) != null)
{%>
	DECORATOR_NAME = "<%=request.getAttribute(com.opensymphony.module.sitemesh.RequestConstants.DECORATOR)%>";
	PAGE_TYPE = "<%=pageType%>";
<%}%>
//======================================
// User script 객체 생성
//======================================
<%

if(user != null)
{

    Class userClass = user.getClass();
	Field [] fields = userClass.getDeclaredFields();
	Map desc = BeanUtils.describe(user);

%>
   loginUser = {
	userLang : '<%=user.getLocale().getLanguage()%>',	// script 에서 locale 객체 대신 userLang 사용
<%
 for (int i=0; i<fields.length; i++)
 {
	Object fieldValObj = desc.get(fields[i].getName());
	String fieldValue = fieldValObj==null?"":fieldValObj.toString();
	String _value = CommonUtil.toScript(fieldValue);
	_value = _value=="null"?"":_value;
%>
	<%=fields[i].getName()%> : '<%=_value%>'<%=i==fields.length-1?"":","%>
<%}%>
};

//메뉴 Info 공유 
menuJArray = 
 [
	<%  int mCnt = 0; 
	if(menuList != null)
	for(Map menuMap :menuList) {
	 mCnt++;
	 String _menuId = String.valueOf(menuMap.get("menuId"))=="null"?"":String.valueOf(menuMap.get("menuId"));
	 String _url	= String.valueOf(menuMap.get("url"))=="null"?"":String.valueOf(menuMap.get("url"));
	 String _title	= String.valueOf(menuMap.get("text"))=="null"?"":String.valueOf(menuMap.get("text"));
		if(menuList.size() != mCnt ){
	%>
	  {"id":"<%=_menuId%>","url":"<%=_url%>","title":"<%=_title%>"},
	<%	    }else{        %>
	{"id":"<%=_menuId%>","url":"<%=_url%>","title":"<%=_title%>"}
	<%      }
	} //End of For      
	%>
];

//======================================
// 다국어 지원을 위한 이미지 파일명 세팅
// 다국어 지원이 필요한 이미지에는 필요한 곳에
// imagename${imageLang}.gif 과 같이 세팅한다.
//====================================== 
<%
} //End of user

	if(user != null)
	{
		//로그인 유저의 언어
		String userLanguage = user.getLocale().getLanguage();
		String imageLang = "";
		
		if(!userLanguage.equals("ko"))
		{
		    // 다른 모든 언어는 en 이미지 를 따르게 한다.
			imageLang = "_en";
		}
		
		request.setAttribute("imageLang", imageLang);
	}
%>

//======================================
// COMMON MESSAGE - START
//======================================
<%
java.util.ArrayList commonArrayMessages = 
	(java.util.ArrayList)request.getSession().getAttribute("COMMON_MESSAGES");
if (commonArrayMessages != null)
{
	int size = commonArrayMessages.size();
	for (int i=0; i<size; i++)
	{
	    String [] tempCommonArray = (String [])commonArrayMessages.get(i);
	    tempCommonArray[0] = tempCommonArray[0].replace('.', '_');
	%>
		var <%=tempCommonArray[0]%> = '<%=tempCommonArray[1].replaceAll("'", "\\\\'")%>';		
	<%
	}
}	
%>

//======================================
// P_MENU_ID, MENU_ID, CurrentPageId
//======================================
<%
String [] menuPath = (String [])request.getAttribute("menuPath");
String pMenuId = "";
String menuId = "";
String menuPageId = "";
if (menuPath != null)
{
    pMenuId = menuPath[0];
    menuId = menuPath[1];
    menuPageId = menuPath[2];
}
%>
var pMenuId = "<%=pMenuId%>";
var menuId  = "<%=menuId%>";
var menuPageId  = "<%=menuPageId%>";
var currentPageId = "<%=request.getAttribute("currentPageId")%>";

//IP address 판별용
var hostIp = "<%=hostIp%>";

//======================================
// loading page strutsAction 
//======================================
var loadStrutsAction = "<%=common.struts.BaseAction.DEFAULT_ACTION%>";
<%
Object currentPageId = request.getAttribute("currentPageId");
if (currentPageId != null)
{
    Object formObj = request.getAttribute(currentPageId.toString()+"Form");
    if (formObj != null)
    {
        common.struts.BaseForm baseForm = (common.struts.BaseForm)formObj;
%>
loadStrutsAction = "<%=baseForm.getStrutsAction()%>";
<%
    }
}
%>
//======================================
// COMMON MESSAGE - END
//======================================
var contextPath = '<%=request.getContextPath()%>';
var serverName	= '<%=request.getServerName()%>';
var serverPort	= '<%=request.getServerPort()%>';

//======================================
// COMMON StrutsAction - END
//======================================
var baseGridExportAction = '<%=common.struts.BaseAction.BASE_GRID_EXPORT%>';

//=======================================================
// listOfVal.js 에서 필요한 strutsAction값 셋팅시
//=======================================================
var lovUsrDirDefaultAction = '<%=common.finder.valid.action.ListOfValAction.LOV_USR_DIR_DEFAULT%>';
var lovSysDirDefaultAction = '<%=common.finder.valid.action.ListOfValAction.LOV_MOBILE_DIR_DEFAULT%>';
var lovTableDefaultAction = '<%=common.finder.valid.action.ListOfValAction.LOV_MOBILE_TABLE_DEFAULT%>';
var lovBaseDefaultAction = '<%=common.struts.BaseAction.BASE_DEFAULT_INIT%>';
var lovFileUploadtAction = '<%=common.file.action.FileUploadAction.FILE_UPLOAD_FIND%>';

//report 

var BASE_ACTION_REPORT = "<%=common.struts.BaseAction.BASE_ACTION_REPORT%>";
var BASE_QUICK_LIST = "<%=common.struts.BaseAction.BASE_QUICK_LIST%>";

//=======================================================
//menuCommon.js 에서 필요한 strutsAction값 셋팅시
//=======================================================
var memberPopupAction = '';	

var targetIfmCnt;

/*
 * Get Iframe, not contents
 */
function getPIframe(pIframeName, _contents)
{
	if(typeof _contents == "undefined") _contents = getTopPage().document;
	if(pIframeName == "null")
	{
		targetIfmCnt = $(parent.document);
	}
	else 
	{
		 $(_contents).find('iframe').each(function(index){
		//alert($(this).attr("name")+"    indexOf:"+$(this).attr("name").indexOf("tabFrameTAB."));
			if($(this).attr("name").indexOf("tabFrameTAB.") >= 0)
			{
				var iframeName = $(this).attr("name").replace("tabFrameTAB.","");
				var pIfmName = pIframeName.replace("tabFrame_","");
				//alert($(this).attr("name")+"    "+pIframeName+"    "+"!!!!!  "+iframeName+"   "+ pIfmName+"  "+targetIfmCnt);
				if(iframeName != pIfmName)
				{
					getPIframe(pIframeName, $(this).contents());
				}
				else
				{
					targetIfmCnt = $(this).contents();
					return false;
				}	
			}
			else
			{
				getPIframe(pIframeName, $(this).contents());
			}
		 });
	}
	 return targetIfmCnt;
}

function getParentIframe(pIframeName, _contents)
{
	 if(typeof _contents == "undefined") _contents = getTopPage().document;
		 
	 $(_contents).find('iframe').each(function(index){
	//alert($(this).attr("name")+"    indexOf:"+$(this).attr("name").indexOf("tabFrameTAB."));
		if($(this).attr("name").indexOf("tabFrameTAB.") >= 0)
		{
			var iframeName = $(this).attr("name").replace("tabFrameTAB.","");
			var pIfmName = pIframeName.replace("tabFrame_","");
			//alert($(this).attr("name")+"    "+pIframeName+"    "+"!!!!!  "+iframeName+"   "+ pIfmName+"  "+targetIfmCnt);
			if(iframeName != pIfmName)
			{
				getParentIframe(pIframeName, $(this).contents());
			}
			else
			{
				targetIfmCnt = $(this).get(0).contentWindow;
				return false;
			}	
		}
		else
		{
			getParentIframe(pIframeName, $(this).contents());
		}
	 });
	 
	 return targetIfmCnt;
}


/**
 * Get Iframe Contents 
 */
function getIframeContent()
{
	 var ifrm, _doc;
	 var parentIfmId = parentPopupDivId;

	 if(parentIfmId.indexOf("tabFrame") >= 0 )
     {
		 _doc = getParentIframe(parentIfmId);
		 //ifrm = parent.document.getElementById(parentIfmId);
		 //_doc = ifrm.contentWindow || ifrm.contentDocument;
     }
	 else if(parentIfmId == "" || parentIfmId == "null")
	 {
		 _doc = parent;
	 }
	 else	
     {
 		ifrm = parent.document.getElementById("popupIframe"+parentIfmId);
		 _doc = ifrm.contentWindow || ifrm.contentDocument;
     }

	// Detail Page를 List에서 열지 않고 바로 연경우 parent가 detail page 이며 parent의 iframe 안에 아무것도 없음
    //if (typeof _doc.DECORATOR_NAME == "undefined"){
    //	return parent;
    //}
    //else return _doc;
	
	return _doc;
}

/**
 * 창 닫을때
 */
function unLoad(event)
{
    if(!event) event = window.event;
	if(event.clientY < 0 && document.readyState == "complete")
	{
		bottomForm.strutsAction.value = "<%=LoginAction.UNLOAD%>";
	
		bottomForm.target = "bottomIframe";
		bottomForm.action = contextPath + "/index.do";
		bottomForm.submit();
	}	
}
var messageHtml;

//alert(jQuery("#bottomMsg").css("visibility", "visible"));
</script>
<!-- common util -->
<script src="<c:url value="/common/menu/js/menuCommon.js" />" ></script>
<script src="<c:url value="/common/js/common.js" />" ></script>
<script src="<c:url value="/common/js/dateUtil.js" />" ></script>
<script src="<c:url value="/common/js/listOfVal.js" />" ></script>
<script src="<c:url value="/common/js/mwareActiveX.js" />" ></script>
<script src="<c:url value="/common/ajax/ajaxAutoComplete.js" />" ></script>
<script src="<c:url value="/common/ajax/ajaxValCommon.js" />" ></script>
<c:import charEncoding="UTF-8" url="/common/ajax/ajaxValidation.jsp"></c:import>


<!--  jQuery -->
<script src="<c:url value="/common/jquery/jquery-1.11.1.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery-ui.min.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery.form.min.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery.scrollbar.js" />" ></script>

<c:import charEncoding="UTF-8" url="/common/calendar/calendar.jsp"></c:import>


<style type="text/css">
<%
String fontSize = null;
if(user != null)
{
    fontSize = user.getScrnFontSize();
}
	if(!"null".equals(fontSize))
	{
	    if("LARGE".equals(fontSize))
	    {    
%>
body,input,textarea,select,button,table{font-size:14px;}
.stitle_box {position:absolute; left:11px; top:7px; height:20px; background:url(./common/im--ages/ma/i_article.png) no-repeat 0 1px; padding-left:20px; font-size:1em; font-weight:bold; color:#000;}
.field {height:25px;}
.field_img {height:181px; }
.img_box {height:180px; }
.mbox a.mb { padding:6px 30px 15px 27px;}
#pg_title h1 { padding:15px 10px 9px 45px;}
<%	
	    }
	    else if("BIG".equals(fontSize))
	    {
%>
body,input,textarea,select,button,table{font-size:16px;}
.stitle_box {position:absolute; left:11px; top:6px; height:22px; background:url(./common/images/ma/i_article.png) no-repeat 0 2px; padding-left:20px; font-size:1em; font-weight:bold; color:#000; }
.field {height:27px;}
.field_img {height:192px; }
.img_box {height:192px; }
.mbox a.mb { padding:5px 30px 18px 27px;}
#pg_title h1 { padding:14px 10px 8px 45px;}
<%
	    }
	}
%>
</style>

<!-- Dhtmlx Grid -->
<script src="<c:url value="/common/dhtmlxSuite/codebase/dhtmlx.js" />" ></script>
<script src="<c:url value="/common/dhtmlxSuite/codebase/dhtmlxvault.js" />" ></script>
<script src="<c:url value="/common/dhtmlxSuite/codebase/swfobject.js" />" ></script>
<link rel="stylesheet" href="<c:url value="/common/dhtmlxSuite/codebase/dhtmlx_ma.css" />" type="text/css">
<link rel="stylesheet" href="<c:url value="/common/dhtmlxSuite/codebase/dhtmlxvault.css" />" type="text/css">
<link rel="stylesheet" href="<c:url value="/common/dhtmlxSuite/skins/terrace/dhtmlxvault.css" />" type="text/css">

<script type="text/javascript">
<!--

if(!jQuery.browser){

	jQuery.browser = {};
	jQuery.browser.mozilla = false;
	jQuery.browser.webkit = false;
	jQuery.browser.opera = false;
	jQuery.browser.safari = false;
	jQuery.browser.chrome = false;
	jQuery.browser.msie = false;
	jQuery.browser.android = false;
	jQuery.browser.blackberry = false;
	jQuery.browser.ios = false;
	jQuery.browser.operaMobile = false;
	jQuery.browser.windowsMobile = false;
	jQuery.browser.mobile = false;

	var nAgt = navigator.userAgent;
	jQuery.browser.ua = nAgt;

	jQuery.browser.name  = navigator.appName;
	jQuery.browser.fullVersion  = ''+parseFloat(navigator.appVersion);
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);
	var nameOffset,verOffset,ix;

// In Opera, the true version is after "Opera" or after "Version"
	if ((verOffset=nAgt.indexOf("Opera"))!=-1) {
		jQuery.browser.opera = true;
		jQuery.browser.name = "Opera";
		jQuery.browser.fullVersion = nAgt.substring(verOffset+6);
		if ((verOffset=nAgt.indexOf("Version"))!=-1)
			jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
	}

// In MSIE < 11, the true version is after "MSIE" in userAgent
	else if ( (verOffset=nAgt.indexOf("MSIE"))!=-1) {
		jQuery.browser.msie = true;
		jQuery.browser.name = "Microsoft Internet Explorer";
		jQuery.browser.fullVersion = nAgt.substring(verOffset+5);
	}

// In TRIDENT (IE11) => 11, the true version is after "rv:" in userAgent
	else if (nAgt.indexOf("Trident")!=-1 ) {
		jQuery.browser.msie = true;
		jQuery.browser.name = "Microsoft Internet Explorer";
		var start = nAgt.indexOf("rv:")+3;
		var end = start+4;
		jQuery.browser.fullVersion = nAgt.substring(start,end);
	}

// In Chrome, the true version is after "Chrome"
	else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {
		jQuery.browser.webkit = true;
		jQuery.browser.chrome = true;
		jQuery.browser.name = "Chrome";
		jQuery.browser.fullVersion = nAgt.substring(verOffset+7);
	}
// In Safari, the true version is after "Safari" or after "Version"
	else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {
		jQuery.browser.webkit = true;
		jQuery.browser.safari = true;
		jQuery.browser.name = "Safari";
		jQuery.browser.fullVersion = nAgt.substring(verOffset+7);
		if ((verOffset=nAgt.indexOf("Version"))!=-1)
			jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
	}
// In Safari, the true version is after "Safari" or after "Version"
	else if ((verOffset=nAgt.indexOf("AppleWebkit"))!=-1) {
		jQuery.browser.webkit = true;
		jQuery.browser.name = "Safari";
		jQuery.browser.fullVersion = nAgt.substring(verOffset+7);
		if ((verOffset=nAgt.indexOf("Version"))!=-1)
			jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
	}
// In Firefox, the true version is after "Firefox"
	else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {
		jQuery.browser.mozilla = true;
		jQuery.browser.name = "Firefox";
		jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
	}
// In most other browsers, "name/version" is at the end of userAgent
	else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) < (verOffset=nAgt.lastIndexOf('/')) ){
		jQuery.browser.name = nAgt.substring(nameOffset,verOffset);
		jQuery.browser.fullVersion = nAgt.substring(verOffset+1);
		if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) {
			jQuery.browser.name = navigator.appName;
		}
	}

	/*Check all mobile environments*/
	jQuery.browser.android = (/Android/i).test(nAgt);
	jQuery.browser.blackberry = (/BlackBerry/i).test(nAgt);
	jQuery.browser.ios = (/iPhone|iPad|iPod/i).test(nAgt);
	jQuery.browser.operaMobile = (/Opera Mini/i).test(nAgt);
	jQuery.browser.windowsMobile = (/IEMobile/i).test(nAgt);
	jQuery.browser.mobile = jQuery.browser.android || jQuery.browser.blackberry || jQuery.browser.ios || jQuery.browser.windowsMobile || jQuery.browser.operaMobile;


// trim the fullVersion string at semicolon/space if present
	if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1)
		jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);
	if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1)
		jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);

	jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10);
	if (isNaN(jQuery.browser.majorVersion)) {
		jQuery.browser.fullVersion  = ''+parseFloat(navigator.appVersion);
		jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);
	}
	jQuery.browser.version = jQuery.browser.majorVersion;
}

$( window ).resize(function() {
	if(myPop) myPop.hide();
});


/**
 * DB Data를 기준으로 항목 생성 
 */
function makeField(lang, fieldName, displayYn, fieldOption, ordNo ,readOnlyYn )
{
	fieldOption = fieldOption=="null"?"":fieldOption;

	var fieldObj = $('<div>').addClass("field").addClass(fieldOption).append(
		$('<label>').text(lang)
	).append(
				$('<div>').addClass(readOnlyYn=="Y"?"input_read":"input_box").append(
				$('<input>').prop({
					"name":fieldName,
					"type":"text",
					"tabindex" : ordNo,
					"readonly" : readOnlyYn=="Y"?true:false
				}))
			).addClass(displayYn=="N"?"hidden":"");
	
	$('.form_box').prepend(fieldObj);
}
	
/** 화면 컬럼 조정 */
jQuery(function($){
	<%
	if(fieldTable != null && fieldTable.containsKey(cPId))
    {
		%>
		$('.field_long_blank,.field_blank,.field_divide').remove();
		<%
        fieldList = (List)fieldTable.get(cPId);
        for(Object obj : fieldList)
        {
            Map map = (Map)obj; 
            String langKey 		= String.valueOf(map.get("langKey"));
            String fieldId 		= String.valueOf(map.get("fieldId"));
            String displayYn 	= String.valueOf(map.get("displayYn"));
            String fieldOption	= String.valueOf(map.get("fieldOption"));
            String ordNo		= String.valueOf(map.get("ordNo"));
            String readonlyYn	= String.valueOf(map.get("readonlyYn"));
            String checkYn		= String.valueOf(map.get("checkYn"));
            %>
            
            var inputObj = $('[name="<%=fieldId%>"]');
	        var lang='<bean:message key="<%=langKey%>"/>';
            if(inputObj.size() >= 1)
            {
				var fieldObj = inputObj.parents('.field,.field_long,.field_img');
				var labelObj = fieldObj.find('label');
				var divObj   = fieldObj.find('div[class^="input_"]');
				//var divObj   = inputObj.parents('div[class^="input_"]');
				inputObj.prop("tabindex","<%=ordNo%>");
				
	            if("<%=langKey%>" != ".") labelObj.text(lang);
	            
	            if("<%=checkYn%>" == "Y") labelObj.addClass('check');
	            else labelObj.removeClass('check');
	            
	            if("<%=displayYn%>" == "N") fieldObj.addClass('hidden');
				else fieldObj.removeClass('hidden');
	            
				if("<%=readonlyYn%>" == "Y")
				{
					divObj.addClass('input_read').removeClass('input_box');
					if(chkFilter()) //Filter에서는 살려준다.
					{
						fieldObj.find('input,textarea,.img_box').prop("readonly","true");
					}
					else 
					{
						fieldObj.find('input,textarea,.img_box').prop("readonly","true").datepicker("destroy");
						fieldObj.find('p,a').hide();
					}
					
				}
				else if("<%=readonlyYn%>" == "N")
				{ 
					divObj.addClass('input_box').removeClass('input_read');
					fieldObj.find('input,textarea,.img_box').prop("readonly","");
					fieldObj.find('p,a').show();
				}
					
	            if("<%=displayYn%>" == "Y" && !("<%=fieldOption%>" == "" || "<%=fieldOption%>" == "null")) $('.form_box').prepend($("<DIV></DIV>").addClass("<%=fieldOption%>"));
				
				if("<%=ordNo%>" != "null")$('.form_box').prepend(fieldObj);
            }
            else
            {
            	makeField(lang,'<%=fieldId%>','<%=displayYn%>','<%=fieldOption%>','<%=ordNo%>','<%=readonlyYn%>' );
            }
            <%
        }
        %>
        <%
    }
	%>
});	
//-->
</script>