<%--===========================================================================
author  ssong
version $Id: bottomInclude.jsp,v 1.3 2013/12/10 07:35:57 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page import="common.message.DataBaseMessageResources"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="common.bean.User"%>
<%
	// 모든 변수는 이 페이지를 포함한 페이지에서 변수이름 충돌을 방지하기 위해서 
	// 앞에 bottom 을 붙인다

	Object bottomMessageObject = request.getAttribute("MESSAGE");
	String bottomMessage = "";
	String menuKey = "";
	if (bottomMessageObject != null)
    {
        bottomMessage = (String)bottomMessageObject;
    }

   	// 현재 페이지의 Menu 경로를 표시한다.
    String [] bottomMenuPath = (String [])request.getAttribute("menuPath");
%>
<script>
var _fncName = "";
var _isFromNotice="";
//======================================
// MESSAGE - Show
//======================================
<%
String pageMessage = (String)request.getAttribute("PAGE_MESSAGE");
if (pageMessage != null && !"".equals(pageMessage))
{
%>
	alertMessage1('<%=pageMessage%>');
<%
}
//==========================
// FUNCTION CALL FROM ACTION
//==========================
String[] actionFunction = (String[])request.getAttribute("ACTION_FUNCTION");
if (actionFunction != null && !"".equals(actionFunction[0]))
{
%>
	_fncName = "<%=actionFunction[0]%>"+"()";
<%
}
//==========================
//Notice창에서 넘어오는 지 확인할 때 쓰는 flag값
//==========================
String[] isFromNotice = (String[])request.getAttribute("IS_FROM_NOTICE");
if (isFromNotice != null && !"".equals(isFromNotice[0]))
{
%>
	_isFromNotice = "<%=isFromNotice[0]%>";
<%
}
String formName = (String)request.getAttribute("FORM_NAME");
if (formName != null && !"".equals(formName))
{%>
var <%=formName%> = document.forms['<%=formName%>'];
<%}%>
//=====================================================
// 현재 페이지 경로를 표시하여준다.
try
{
	var menuLocation1 = "";
	var menuLocation2 = "";
<%
	// index.do인경우 bottomMenuPath 가 null 이다.
	int bottomMenuPathLength = bottomMenuPath==null?0:bottomMenuPath.length;
	if (bottomMenuPathLength>=1 && bottomMenuPath != null && !"".equals(bottomMenuPath[0])) 
    {
	    
	    DataBaseMessageResources dataBaseMessageResources =
            (DataBaseMessageResources)request.getAttribute(Globals.MESSAGES_KEY);
        // Login User Instance
        User user = (User)request.getSession().getAttribute(request.getSession().getId());
        
        if(user != null)
        {
        	menuKey = "MENU."+bottomMenuPath[4];
        	menuKey = dataBaseMessageResources.getMessage(user.getLocale(), menuKey);
        }
        // message key : lang.pageId.keyId   ex) ko.BUTTON.CANCEL 
//         String menuLocation1Name = dataBaseMessageResources.getMessage(user.getLocale(), "MENU." + bottomMenuPath[0]);
//         String menuLocation2Name = dataBaseMessageResources.getMessage(user.getLocale(), "MENU." + bottomMenuPath[1]);
%>
<%--         menuLocation1 = "<%=menuLocation1Name%>"; --%>
<%--         menuLocation2 = "<%=bottomMenuPath[3]%>"; --%>
<%
    }

%>
	if("<%=menuKey%>" != "") $('#pg_title > h1').text("<%=menuKey%>");
	else $('#pg_title > h1').text($('title').eq(0).text());

	
	<%-- $('title').text("<bean:message key="<%=menuKey%>"/>"); --%>
	//document.getElementById('menuLocation0').innerHTML = menuLocation2;	
// 	document.getElementById('menuLocation1').innerHTML = menuLocation1;	
// 	document.getElementById('menuLocation2').innerHTML = menuLocation2;	
}
catch(e){}
//=====================================================

var bottomMessage = '<%=bottomMessage%>';

if (bottomMessage != null && bottomMessage != '')
{
	alert(bottomMessage);
}

/**
 * Default Page 화면에서 작동
 */
function checkActionCall()
{
	if(_fncName != "") eval(_fncName);
}

function foldSec(obj)
{
	obj.find('.stitle_icon').removeClass('stitle_icon').addClass('stitle_icon_up');

	obj.find('.article_box, .accordion_wrap').hide();
	if(typeof resizeTabFrame == "function") resizeTabFrame();

	obj.find('.function_box').find('a').each(function(index){
		if(!$(this).is('.b_unfold,.b_fold,.b_close')) $(this).hide();
	});
}

function unfoldSec(obj)
{
	obj.find('.stitle_icon_up').removeClass('stitle_icon_up').addClass('stitle_icon');

	obj.find('.article_box, .accordion_wrap').show();
	if(typeof resizeTabFrame == "function") resizeTabFrame();

	obj.find('.function_box').find('a').each(function(index){
		if(!$(this).is('.b_unfold,.b_fold,.b_close,.fHide')) $(this).show();
	});
}


//상세페이지 접기 기능  
$(".sheader_wrap:not('.detail')>a, .stitle_wrap").bind('click',function()
{
	var _p = $(this).parents('.section_wrap');
	if(_p.find('.stitle_icon').length >=1)
	{
		foldSec(_p);
	}
	else if(_p.find('.stitle_icon_up').length >= 1)
	{
		unfoldSec(_p);
	}

	
});  



$('.ac_menu').bind('click',function(e){
	
 	e.stopPropagation();
	/*e.preventDefault(); */
	
	var divObj = $(this).parent().find('div[id^="tabDivTAB"]');
	if(divObj.css("display") == "block")
	{
		$(this).removeClass("current");
		$(this).parent().find("div:not('.sfb_wrap')").hide();
		if(typeof resizeTabFrame == "function") resizeTabFrame();
	}
	else
	{
		//iFrame가 로드 안되었고, goTabPage 펑션이 정의되어야, Iframe 로드
		if(typeof goTabPage == "function" && $(this).parent().find('iframe').contents().find('div').length == 0) 
		{
			var pgId  = divObj.prop("id").replace("tabDivTAB.","");
			goTabPage(pgId);
		}
		/* else if(typeof goTabPage == "function" && $("input[name='strutsAction']").val() == 0) 
		{
			goTabPage(divObj.prop("id").replace("tabDivTAB.",""));
		}  */
		
		/* $(this).parent().find('div').slideDown('slow', function(){
			if(typeof resizeTabFrame == "function") resizeTabFrame();
		}); */
		$(this).parent().find("div:not('.sfb_wrap')").show();

		//Inner Tab Page Resize
		var targetIfmCnt = divObj.find('iframe').get(0).contentWindow;
		if(typeof targetIfmCnt.resizeTabFrame == "function")
		{ 
			targetIfmCnt.resizeTabFrame(); //각 페이지의 goSave function 호출

	    	if(targetIfmCnt.disableAll){
	    		targetIfmCnt.setDisable();
// 	    		$(this).next().find('a').hide();
	    	}
	    	else 
	    	{
	    		targetIfmCnt.setEnable();
	    		//$(this).next().find('a').show();
	    	}

		}

		
		//Tab Page Resize (parents of inner)
		if(typeof resizeTabFrame == "function") resizeTabFrame();
		
		
		$(this).addClass("current");
	}
});

/**
 * 화면 클릭시 Close AC Popup
 */
$(document).on('click',function(e){
	var topPage = getTopPage();
	if(topPage.myPop && topPage.myPop.isVisible())topPage.myPop.hide();

	if($(e.target).parents('.fb_group3').length == 0) {
	     $('.sfb_wrap:visible').hide();
	}
});

$('.b_leftfold,.b_leftunfold').bind('click',function(){
	var marginLeft = ($('#content').css("margin-left")).replace("px","");
	var topPage = getTopPage();

	if(topPage.myPop && topPage.myPop.isVisible()) topPage.myPop.hide();
		
	if($(this).is('.b_leftfold')) //안접혀 있으면...
	{		
		$('#content,#pg_title,.sheader_box[id*="_title"]').animate({
			marginLeft : "41px"
		});
		
		$('#nav_left').animate({
			width : "40px"
		},  function(){
			$('.gridbox.gridbox_dhx_skyblue').css("width","100%");
			$('.mbox').addClass('mboxfold').removeClass('mbox');
			$('.smb').hide();
		});
		
		$('.m_current').removeClass('m_current').addClass('temp');
		
		$(this).addClass('b_leftunfold').removeClass('b_leftfold');
		
		$('.b_menuopen').hide();
		
	}
	else
	{
		$('#content,#pg_title,.sheader_box[id*="_title"]').animate({
			marginLeft : "246px"
		});
		
		$('#nav_left').animate({
			width : "245px"
		}, function(){
			$('.gridbox.gridbox_dhx_skyblue').css("width","100%");
		});
		
		$(this).addClass('b_leftfold').removeClass('b_leftunfold');
		$('.mboxfold').addClass('mbox').removeClass('mboxfold');
		
		$('.temp').addClass('m_current').removeClass('temp').next().show();
		$('.b_menuopen').show();
	}
});

//메뉴 컨트롤
$('.mb').bind('click',function(e){
	if($(this).parents('li').find('.smb').is(':hidden')) //:visible
	{
		if($('.b_leftfold').length == 0)
		{
			$('.b_leftfold,.b_leftunfold').trigger("click");
		}
		$(this).parents('li').find('.smb').show();
		$(this).parent().addClass('m_current');
	}
	else
	{

		$(this).parents('li').find('.smb').hide();		
		$(this).parent().removeClass('m_current');
	}
});

//이름 클릭하면 내정보로 이동
$('.tmenu_box>b').on("click",function(e){
	var menuId = "";
	for(var i in menuJArray)
    {
        if(menuJArray[i].url.split("?")[0] == "maMyInfo") menuId = menuJArray[i].id;
    }
	goMenuPage("maMyInfo?&menuId="+menuId);
}).css("cursor","pointer");

//사진 
$('.manual>.fb_group1>.b_photo').on("click",function(e){
	goPhoto();
});
//전 사진 
$('.manual>.fb_group1>.b_beforephoto').on("click",function(e){
	goBeforephoto();
});
//후 사진 
$('.manual>.fb_group1>.b_afterphoto').on("click",function(e){
	goAfterphoto();
});
//로그인 타이틀 로고 사진 (loginTitleLogo)
$('.manual>.fb_group1>.b_logintitlelogo').on("click",function(e){
	goLogintitleLogo();
});
//로그인 서브 타이틀 로고 사진 (loginSubTitleLogo)
$('.manual>.fb_group1>.b_loginsubtitlelogo').on("click",function(e){
	goLoginsubtitleLogo();
});
//메인 타이틀 로고 사진 (MainTitleLogo)
$('.manual>.fb_group1>.b_mainTitlelogo').on("click",function(e){
	goMaintitleLogo();
});


/*  $("input[onblur^='val']").each(function(e){
	var onBlurFnc = $(this).attr("onblur");
	var blParam = onBlurFnc.substring(onBlurFnc.indexOf("(")+1, onBlurFnc.indexOf(")"));
	var param = "";
	if(onBlurFnc.indexOf("valSysDir") > -1)
	{
		var paramArr = blParam.split(",");

		for(var i in paramArr)
		{
			if(paramArr[i].indexOf("\'") != -1)
			{
				if(i == 0) param = paramArr[i];
				else if(i == 1) param = paramArr[i] + "," + param;
				else param = param + "," + paramArr[i];
			}
		}
	}
	else
	{
		var paramArr = blParam.split(",");

		for(var i in paramArr)
		{
			if(paramArr[i].indexOf("\'") != -1)
			{
				if(i == 0) param = paramArr[i];
				else param = param + "," + paramArr[i];
			}
		}
	}

	//$.globalEval( "var newVar = true;" )
	
	$(this).on("keydown",function(e){
		if(e.keyCode != 9)
			$.globalEval( "validationKeyDown("+param+");");
	});
	//onBlurFnc.substring(onBlurFnc.indexOf("(")+1, onBlurFnc.indexOf(")"))
	//console.log( "validationKeyDown("+param+");");
});  */

/**
 * Calendar

var myCalendar = new dhtmlXCalendarObject();
myCalendar.hideTime();
$('.open_calendar').each(function(index){
	$(this).prev().prop("id","cal_"+index);
	$(this).prop("id","calBtn_"+index);
	var inputObj = $(this).prev().get(0);
	
	myCalendar.attachObj({input:"cal_"+index,button:"calBtn_"+index});
	myCalendar.attachObj(inputObj);
	
	$(this).prev().bind({
		"keyup":function(e){
			dateFormat(inputObj, e);
		},
		"keypress":function(e){
			onlyNumberInput(e);
		},
		"blur":function(e){
			validDate(inputObj);
		}
	}).attr("maxlength","9");

});
*/

var _userLang = "ko";
if(loginUser) _userLang = loginUser.userLang;

/**
 * 달력 언어 세팅
 */
dhtmlXCalendarObject.prototype.langData[_userLang] = {
	    dateformat: '%Y-%m-%d',
	    monthesFNames: ["<bean:message key='LABEL.month1'/>","<bean:message key='LABEL.month2'/>","<bean:message key='LABEL.month3'/>","<bean:message key='LABEL.month4'/>","<bean:message key='LABEL.month5'/>","<bean:message key='LABEL.month6'/>",
	                    "<bean:message key='LABEL.month7'/>","<bean:message key='LABEL.month8'/>","<bean:message key='LABEL.month9'/>","<bean:message key='LABEL.month10'/>","<bean:message key='LABEL.month11'/>","<bean:message key='LABEL.month12'/>"],
	    monthesSNames: ["<bean:message key='LABEL.month1'/>","<bean:message key='LABEL.month2'/>","<bean:message key='LABEL.month3'/>","<bean:message key='LABEL.month4'/>","<bean:message key='LABEL.month5'/>","<bean:message key='LABEL.month6'/>",
	                    "<bean:message key='LABEL.month7'/>","<bean:message key='LABEL.month8'/>","<bean:message key='LABEL.month9'/>","<bean:message key='LABEL.month10'/>","<bean:message key='LABEL.month11'/>","<bean:message key='LABEL.month12'/>"],
	    daysFNames: ["<bean:message key='LABEL.sun'/>","<bean:message key='LABEL.mon'/>","<bean:message key='LABEL.tue'/>","<bean:message key='LABEL.wed'/>",
	                    "<bean:message key='LABEL.thu'/>","<bean:message key='LABEL.fri'/>","<bean:message key='LABEL.sat'/>"],
	    daysSNames: ["<bean:message key='LABEL.sun'/>","<bean:message key='LABEL.mon'/>","<bean:message key='LABEL.tue'/>","<bean:message key='LABEL.wed'/>",
	                    "<bean:message key='LABEL.thu'/>","<bean:message key='LABEL.fri'/>","<bean:message key='LABEL.sat'/>"],
	    weekstart: 1,
	    weekname: "w"
};

if(loginUser) dhtmlXCalendarObject.prototype.lang = _userLang;
	    
$.datepicker.regional[_userLang] = {
    closeText: 'Close', // 닫기 버튼 텍스트 변경
    currentText: 'Today', // 오늘 텍스트 변경
    monthNames: ["<bean:message key='LABEL.month1'/>","<bean:message key='LABEL.month2'/>","<bean:message key='LABEL.month3'/>","<bean:message key='LABEL.month4'/>","<bean:message key='LABEL.month5'/>","<bean:message key='LABEL.month6'/>",
                 "<bean:message key='LABEL.month7'/>","<bean:message key='LABEL.month8'/>","<bean:message key='LABEL.month9'/>","<bean:message key='LABEL.month10'/>","<bean:message key='LABEL.month11'/>","<bean:message key='LABEL.month12'/>"], // 개월 텍스트 설정
    monthNamesShort: ["<bean:message key='LABEL.month1'/>","<bean:message key='LABEL.month2'/>","<bean:message key='LABEL.month3'/>","<bean:message key='LABEL.month4'/>","<bean:message key='LABEL.month5'/>","<bean:message key='LABEL.month6'/>",
	                    "<bean:message key='LABEL.month7'/>","<bean:message key='LABEL.month8'/>","<bean:message key='LABEL.month9'/>","<bean:message key='LABEL.month10'/>","<bean:message key='LABEL.month11'/>","<bean:message key='LABEL.month12'/>"], // 개월 텍스트 설정
    dayNames: ["<bean:message key='LABEL.sun'/>","<bean:message key='LABEL.mon'/>","<bean:message key='LABEL.tue'/>","<bean:message key='LABEL.wed'/>",
               "<bean:message key='LABEL.thu'/>","<bean:message key='LABEL.fri'/>","<bean:message key='LABEL.sat'/>"], // 요일 텍스트 설정
    dayNamesMin: ["<bean:message key='LABEL.sun'/>","<bean:message key='LABEL.mon'/>","<bean:message key='LABEL.tue'/>","<bean:message key='LABEL.wed'/>",
                  "<bean:message key='LABEL.thu'/>","<bean:message key='LABEL.fri'/>","<bean:message key='LABEL.sat'/>"], // 요일 텍스트 축약 설정    dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
    dateFormat: 'dd/mm/yy' // 날짜 포맷 설정
};

if(loginUser) $.datepicker.setDefaults($.datepicker.regional[_userLang]);

//Calendar Setting
setCalendar();
	 
/* $('.open_calendar').prev().datepicker({
    numberOfMonths: 3,
    showCurrentAtPos: 1,
    dateFormat:"yy-mm-dd",
    //changeMonth: true,
   // changeYear: true,
    onSelect: function(date, obj){
    	if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";
    }
    //showButtonPanel: true
  });
 */

 /**
  * Number Format, Only Number 
  */
function setNumberFmt()
{
	 var preText="";
	 $('.num').on({
	 	"keydown" : function(e){
	 		
	 		if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl/cmd+A
 	            (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
 	             // Allow: Ctrl/cmd+C1
 	            (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
 	             // Allow: Ctrl/cmd+X
 	            (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
 	        	// Allow: Ctrl/cmd+V
 	            (e.keyCode == 86 && (e.ctrlKey === true || e.metaKey === true)) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
	 	        }
	 		
	 		// Ensure that it is a number and stop the keypress
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	            e.preventDefault();
	        }
	 	
	 	},
	 	"focus":function(e){
	 		preText = $(this).val();
	 	},
	 	"keyup":function(e){
	 		if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl/cmd+A
 	            (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
 	             // Allow: Ctrl/cmd+C
 	            (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
 	             // Allow: Ctrl/cmd+X
 	            (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
 	        	// Allow: Ctrl/cmd+V
 	            (e.keyCode == 86 && (e.ctrlKey === true || e.metaKey === true)) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
	 	        }
	 		
	    	var v = $(this).val().split(",").join("");
	 		$(this).val(v.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, ''));
	 		
	 		setMoneyFormat($(this)[0], 3);
	 	},
	 	"blur":function(e)
	 	{
	 		var v = $(this).val().split(",").join("");
	 		$(this).val(v.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, ''));
	 		
	 		setMoneyFormat($(this)[0], 3);
	 	}
	 }).css("text-align","right");

	 $('.ty_num').on({
	 	"keydown" : function(e){

	 			if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190, 189, 109]) !== -1 ||
	 	             // Allow: Ctrl/cmd+A
	 	            (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	             // Allow: Ctrl/cmd+C
	 	            (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	             // Allow: Ctrl/cmd+X
	 	            (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	        	// Allow: Ctrl/cmd+V
	 	            (e.keyCode == 86 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	             // Allow: home, end, left, right
	 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
	 	                 // let it happen, don't do anything
	 	                 return;
		 	        }
	 			
		 		// Ensure that it is a number and stop the keypress
		        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
		            e.preventDefault();
		        }

		 	},
		 	"focus":function(e){
		 		preText = $(this).val();
		 	},
		 	"keyup":function(e){
		 		console.log(e.keyCode);
		 		if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190, 189, 109]) !== -1 ||
	 	             // Allow: Ctrl/cmd+A
	 	            (e.keyCode == 65 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	             // Allow: Ctrl/cmd+C
	 	            (e.keyCode == 67 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	             // Allow: Ctrl/cmd+X
	 	            (e.keyCode == 88 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	        	// Allow: Ctrl/cmd+V
	 	            (e.keyCode == 86 && (e.ctrlKey === true || e.metaKey === true)) ||
	 	             // Allow: home, end, left, right
	 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
	 	                 // let it happen, don't do anything
	 	                 return;
		 	        }
		 		
		    	var v = $(this).val().split(",").join("");
		 		$(this).val(v.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, ''));
		 		
		 		setMoneyFormat($(this)[0], 3);
		 	},
		 	"blur":function(e)
		 	{
		 		var v = $(this).val().split(",").join("");
		 		$(this).val(v.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, ''));
		 		
		 		setMoneyFormat($(this)[0], 3);
		 	}
		    
	 }).css("text-align","right");
}

setNumberFmt();

/*
 * 시간형식 (콜론만 추가)
 */
$('.time').on({
	"keyup" : function(e){
		//timeFormat($(this)[0], e);
		var txt = "";
		var numVal = $(this).val().split(":").join("");

		for(var i = numVal.length; i > 0  ; i--)
		{
			var startIdx = numVal.length + 1 - i;
			if(startIdx >= 6) txt = numVal.substr(i-1,1) + txt ;
			else if(startIdx%2 == 0 && startIdx > 1 && i!=1) txt = ":"+numVal.substr(i-1,1) + txt ;
			else txt = numVal.substr(i-1,1) + txt;
		}
		$(this).val(txt);
		
		if(!$.isNumeric(numVal)) 
		{
			if($(this).val() != "") $(this).val(preText);
			return;
		}
		else preText = $(this).val(); 
		
	},
	"focus":function(e){
		preText = $(this).val();
	}
}).css("text-align","right");

/*
 * 시간형식 세팅
 */
$('.time_format').on({
	"keyup" : function(e){
		//timeFormat($(this)[0], e);
		var txt = "";
		var numVal = $(this).val().split(":").join("");
		for(var i = numVal.length; i > 0  ; i--)
		{
			var startIdx = numVal.length + 1 - i;
			
			if(startIdx%2 == 0 && startIdx > 1 && i!=1) txt = ":"+numVal.substr(i-1,1) + txt ;
			else txt = numVal.substr(i-1,1) + txt;

		}
		$(this).val(txt);
		
		if(!$.isNumeric(numVal) || numVal.length > 6) 
		{
			if($(this).val() != "") $(this).val(preText);
			return;
		}
		else preText = $(this).val(); 
	
		if(numVal.length%2 == 0)
		{
			var resultVal = txt.split(":");
			for(var j = 0; resultVal.length > j ; j++)
			{
				if(j == 0 && resultVal[j] > 23)
				{ 
					alertMessage1(COMMON_CMSG011);
					$(this).val("");
					return;
				}
				else if(j == 1 && resultVal[j] > 59) 
				{ 
					alertMessage1(COMMON_CMSG011);
					$(this).val("");
					return;
				}
				else if(j == 2 && resultVal[j] > 59) 
				{ 
					alertMessage1(COMMON_CMSG011);
					$(this).val("");
					return;
				}
			} 
		}
		
	},
	"focus":function(e){
		preText = $(this).val();
	}
}).css("text-align","right");

/**
 * Make input element with onblur function not to trigger with id value, only trigger with blank id value.
 */
$('input[onblur]').each(function(idx){
	var onBlurTxt = $(this).attr("onblur");
	if(onBlurTxt.indexOf(",") > 0)
	{
		//YN Validation은 예외
		if(onBlurTxt.indexOf("valYn") >= 0) return true;
			
		$(this).attr("onblur",""); //clear inline onblur function 
		
		var idName = onBlurTxt.substring(onBlurTxt.indexOf("(")+2, onBlurTxt.indexOf(",")-1);
		var oldValue;
		$(this).on({
			"focus" : function(e){
				oldValue = $(this).val(); //save the desc value when the input element is selected.
			},
			"keyup": function(e){
				if($(this).val() != oldValue)
				{
					//Make id values blank if the desc value has changed.
					$('input[name="'+idName+'"]').val("");
				}
			},
			"blur": function(e){
				//trigger blur functino only if id value is blank.
				if($('input[name="'+idName+'"]').val() == "") $.globalEval(onBlurTxt);
			}		
		});
	}
});

</script>
<style>
.mware-waiting  {
    position: absolute;
    left:40%;
    top:40%;
    display:none;
    border:0px;
}
</style>
<TABLE cellpadding='0' cellspacing="0" border="0" height="0"> 
<tr><td>
<form name="bottomForm" method="post" action="" style="border:0;" >
	<input type="hidden" name="strutsAction"/>
	<input type="hidden" name="isDecoratorName"/>
	<!-- 현재 Menu의 펼쳐진 위치 표시 -->
	<input type="hidden" name="menuScrollTop"/>
	<!-- 
	=====================================================================================
		Quick Menu에서 각 상세로 넘어갈때 사용함 
	=====================================================================================	
	-->
	<!-- 작업요청시 사용 -->
	<input type="hidden" name="woReqCommonDTO.reqNo"/>
	<!-- 작업계획시 사용 -->
	<input type="hidden" name="woPlanCommonDTO.woNo"/>
	<!-- 작업결과입력시 사용 -->
	<input type="hidden" name="woResultCommonDTO.woNo"/>
	<!-- 자재마스터 -->
	<!-- 자재마스터 detail page로 넘어오는 partNo를 전송 -->
	<input type="hidden" name="ptMstrListDTO.partNo"/>
	<!-- 예방점검 detail page로 넘어오는 checkListNo 를 전송 -->
	<input type="hidden" name="pmiMstrCommonDTO.checkListNo"/>
	<!-- 예방정비 detail page로 넘어오는pmNo를 전송 -->
	<input type="hidden" name="pmMstrCommonDTO.pmNo"/>
	<!-- 설비마스터 detail page로 넘어오는 equipNo를 전송 -->
	<input type="hidden" name="eqMstrCommonDTO.equipNo"/>
	<!-- 위치관리 page로 넘어오는 locCode 를 전송 -->
	<input type="hidden" name="eqLocMngDTO.code"/>
	<!-- 자료실 분류 -->
	<input type="hidden" name="maDocCntrCdCommonDTO.docCntrType"/>
	<!-- 동종기계 자료실 분류 -->
	<input type="hidden" name="maDocCntrEcCommonDTO.docCntrType"/>
	<!-- 설비작업현황 - 전체작업현황 --->
	<input type="hidden" name="maWoResultMstrCommonDTO.filterStartDate"/>
	<!-- 설비작업현황 - 전체작업현황 --->
	<input type="hidden" name="maWoResultMstrCommonDTO.filterEndDate"/>
	<!-- 설비작업현황 - 전체작업현황 --->
	<input type="hidden" name="maWoResultMstrCommonDTO.filterPmTypeId"/>
	<!-- 설비작업현황 - 전체작업현황 --->
	<input type="hidden" name="maWoResultMstrCommonDTO.filterWoStatus"/>
	<!-- 설비작업현황 - 전체작업현황 --->
	<input type="hidden" name="maWoResultMstrCommonDTO.filterDeptId"/>
	<!-- 설비작업현황 - 전체작업현황 --->
	<input type="hidden" name="maWoResultMstrCommonDTO.filterDeptDesc"/>
	<!-- 설비작업현황 - 전체작업현황 --->
	<input type="hidden" name="maWoResultMstrCommonDTO.filterWoTypeId"/>
	<!-- 설비작업현황 - 부품입고-구매 --->
	<input type="hidden" name="maPtRecCommonDTO.filterRecStartDate"/>
	<!-- 설비작업현황 - 부품입고-구매 --->
	<input type="hidden" name="maPtRecCommonDTO.filterRecEndDate"/>
	<!-- 설비작업현황 - 부품입고-구매 --->
	<input type="hidden" name="maPtRecCommonDTO.prRecStatus"/>
	<!-- 설비작업현황 - 부품입고-구매 --->
	<input type="hidden" name="maPtRecCommonDTO.filterDeptId"/>
	<!-- 설비작업현황 - 부품입고-구매 --->
	<input type="hidden" name="maPtRecCommonDTO.filterDeptDesc"/>
	<!-- 설비작업현황 - 부품입고-수리 --->
	<input type="hidden" name="maPtRepCommonDTO.filterStartDate"/>
	<!-- 설비작업현황 - 부품입고-수리 --->
	<input type="hidden" name="maPtRepCommonDTO.filterEndDate"/>
	<!-- 설비작업현황 - 부품입고-수리 --->
	<input type="hidden" name="maPtRepCommonDTO.ptRepStatus"/>
	<!-- 설비작업현황 - 부품입고-수리 --->
	<input type="hidden" name="maPtRepCommonDTO.filterDeptId"/>
	<!-- 설비작업현황 - 부품입고-수리 --->
	<input type="hidden" name="maPtRepCommonDTO.filterDeptDesc"/>
</form>
</td></tr>
</TABLE>
<!-- refresh 되지 않게 submit이 필요한 경우 이하 를 사용한다. -->
<div style="visibility: hidden;">
<iframe name="bottomIframe" src="" width='0' , height='0'></iframe>
</div>
<!-- Calendar -->
<Div id='calDiv' style='POSITION:absolute;vertical-align:top;margin-left:0px;z-index:110;display:none;'> 
  <iframe name="calIframe" id="calIframe" src="" frameborder="0" height="210" width="426" scrolling="no" ></iframe> 
</DIV>
<!-- No Data Display!!! -->
 <div class='dhtmlx_modal_box dhtmlx-alert' id="xId1_0" style="position:absolute;display:none;top:0">
  <div class="dhtmlx_popup_text"  style="" >
  	<span id="noDataMsg"></span>
  </div>
 </div>
<!-- wait message 표시 -->
<div  id="mware_waiting" class="mware-waiting">
  <table width='255' height='84' border='0' cellpadding='0' cellspacing='0'>
    <tr>
      <td background='./common/grid/skin/mbox.jpg'>
        <table width='184' border='0' cellspacing='0' cellpadding='0'>
          <tr>
            <td width='16'></td>
            <td width='32'></td>
            <td width='120'>
              <table width='120' border='0' cellspacing='0' cellpadding='0'>
                <tr>
                  <td width='105'><strong><font color='65A5E3' size='3'></font></strong></td>
                  <td width='15'><strong></strong></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>