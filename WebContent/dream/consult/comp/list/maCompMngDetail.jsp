<%--===========================================================================
회사설정 - 상세
author  kim21017
version $Id: maCompMngDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.list.action.MaCompMngDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="common.bean.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List loginTitleImageList = (List)request.getAttribute("slideLoginTitleFileList");
	List loginSubTitleImageList = (List)request.getAttribute("slideLoginSubTitleFileList");
	List mainTitleImageList = (List)request.getAttribute("slideMainTitleFileList");
%>
<html:html>
<head>
<!-- 회사설정 -->
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var loginTitleImageList = new Array();
var loginSubTitleImageList = new Array();
var mainTitleImageList = new Array();

var change_img_time 	= 5000;	
var transition_speed	= 500;

<%
if(loginTitleImageList != null)
	for(int i= 0; loginTitleImageList.size() > i ; i++)
	{
	    Map loginTitleImageMap = (Map)loginTitleImageList.get(i);
%>
		loginTitleImageList.push('<%=(String)loginTitleImageMap.get("FILE_NAME")%>');
<% 
	}
%>	

<%
if(loginSubTitleImageList != null)
	for(int i= 0; loginSubTitleImageList.size() > i ; i++)
	{
	    Map loginSubTitleImageMap = (Map)loginSubTitleImageList.get(i);
%>
		loginSubTitleImageList.push('<%=(String)loginSubTitleImageMap.get("FILE_NAME")%>');
<% 
	}
%>

<%
if(mainTitleImageList != null)
	for(int i= 0; mainTitleImageList.size() > i ; i++)
	{
	    Map mainTitleImageMap = (Map)mainTitleImageList.get(i);
%>
		mainTitleImageList.push('<%=(String)mainTitleImageMap.get("FILE_NAME")%>');
<% 
	}
%>
function loadPage() 
{

	if(ckCreate(currentPageId)) 
		goInput();
	
	setTitle("maCompMngDetailDTO.compNo","maCompMngDetailDTO.compDesc");
	//For Save
	setForUpdate();
	
	acSysDesc("maCompMngDetailDTO.isUse","maCompMngDetailDTO.isUse","IS_USE", true);
	acSysDesc("maCompMngDetailDTO.initCtPathYn","maCompMngDetailDTO.initCtPathYn","IS_USE", true);
	acSysDesc("maCompMngDetailDTO.ctPathDesc","maCompMngDetailDTO.ctPath","CT_PATH", true);

	setLoginTitleSlideImage();
	setLoginSubTitleSlideImage();
	setMainTitleSlideImage();
	
	//setImageCount();
}

function setLoginTitleSlideImage() {
	
	 //기존에 있던 img 태그를 지운다(사진삭제)
	 $("#loginTitleLogo").children('img').remove();
	
	 //Slide div안에 img태그 및 에러시 나타나는 이미지 설정
	for(var i=0; i<loginTitleImageList.length; i++)
	{
		var loginTitleError = "document.forms[0].loginTitlePhoto"+i+".src='"+contextPath+"/common/images/ma/no_image.png'";
		//$("#loginTitleLogo").append("<img src='.../Temp/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/dream/common/images/client/login_logo/'"+loginTitleImageList[i]+" 'name='LOGINTITLE_"+i+"' onerror="+loginTitleError+">");
		if(_fileDir == "AZURE")
			$('#loginTitleLogo').append("<img src='"+loginTitleImageList[i]+"' name='photo"+i+"' onerror="+loginTitleError+">");
		else
			$('#loginTitleLogo').append("<img src='"+contextPath+"/dream/imgSlide/"+loginTitleImageList[i]+"' name='photo"+i+"' onerror="+loginTitleError+">");
	}
	
	//console.log("loginTitleImageList[i]: " + loginTitleImageList);
	
	var loginTitleSlideShow = $("#loginTitleLogo"),	
		loginTitleListItems = loginTitleSlideShow.children('img'),
		loginTitleListLen	= loginTitleListItems.length,
		loginTitleSlideIndex  = 0,
		loginTitleChangeList = function (e) {
			if(typeof e =="undefined") e = 1;
			$('#loginTitleLogo_prev').hide();
			$('#loginTitleLogo_next').hide();
			loginTitleListItems.eq(loginTitleSlideIndex).fadeOut(transition_speed, function () {
				loginTitleSlideIndex = loginTitleSlideIndex + e;
				if(loginTitleSlideIndex < 0) loginTitleSlideIndex = loginTitleListLen - 1;
				if (loginTitleSlideIndex == loginTitleListLen) {
					loginTitleSlideIndex = 0;
				}
				$('#loginTitleLogo_prev').hide();
				$('#loginTitleLogo_next').hide();
				loginTitleListItems.eq(loginTitleSlideIndex).fadeIn(transition_speed, function(){
					$('#loginTitleLogo_prev').show();
					$('#loginTitleLogo_next').show();
				});
			});
       };

   //첫 번째 사진이 아닌 것들을 숨김처리
   loginTitleListItems.not(':first').hide();

	// 사진 좌우 버튼 클릭 이벤트ㅡ
	$('#loginTitleLogo_prev').on("click",function(e){
		loginTitleChangeList(-1);
	});
	
	$('#loginTitleLogo_next').on("click",function(e){
		loginTitleChangeList(1);
	});
	$('#loginTitleLogo').children('img').on("click",function(e){
		window.open($('#loginTitleLogo').children('img:visible').attr("src"), '_blank'); 
	});
	
}

function setLoginSubTitleSlideImage() {

	 $("#loginSubTitleLogo").children('img').remove();
	
	for(var i=0; i<loginSubTitleImageList.length; i++)
	{
		var loginSubTitleError = "document.forms[0].loginSubTitlePhoto"+i+".src='"+contextPath+"/common/images/ma/no_image.png'";
		//$("#loginSubTitleLogo").append("<img src='"+contextPath+"/common/images/client/"+loginSubTitleImageList[i]+"' name='loginSubTitlePhoto"+i+"' onerror="+loginSubTitleError+">");
		if(_fileDir == "AZURE")
			$('#loginSubTitleLogo').append("<img src='"+loginSubTitleImageList[i]+"' name='photo"+i+"' onerror="+loginSubTitleError+">");
		else
			$('#loginSubTitleLogo').append("<img src='"+contextPath+"/dream/imgSlide/"+loginSubTitleImageList[i]+"' name='photo"+i+"' onerror="+loginSubTitleError+">");
	}
	
	var loginSubTitleSlideShow = $("#loginSubTitleLogo"),	
		loginSubTitleListItems = loginSubTitleSlideShow.children('img'),
		loginSubTitleListLen	= loginSubTitleListItems.length,
		loginSubTitleSlideIndex  = 0,
		loginSubTitleChangeList = function (e) {
			if(typeof e =="undefined") e = 1;
			//$('#loginSubTitleLogo_prev').hide();
			//$('#loginSubTitleLogo_next').hide();
			loginSubTitleListItems.eq(loginSubTitleSlideIndex).fadeOut(transition_speed, function () {
				loginSubTitleSlideIndex = loginSubTitleSlideIndex + e;
				if(loginSubTitleSlideIndex < 0) loginSubTitleSlideIndex = loginSubTitleListLen - 1;
				if (loginSubTitleSlideIndex == loginSubTitleListLen) {
					loginSubTitleSlideIndex = 0;
				}
				//$('#loginSubTitleLogo_prev').hide();
				//$('#loginSubTitleLogo_next').hide();
				loginSubTitleListItems.eq(loginSubTitleSlideIndex).fadeIn(transition_speed, function(){
					$('#loginTitleLogo_prev').show();
					$('#loginTitleLogo_next').show();
				});
			});
      };

	loginSubTitleListItems.not(':first').hide();

	$('#loginSubTitleLogo_prev').on("click",function(e){
		loginSubTitleChangeList(-1);
	});
	
	$('#loginSubTitleLogo_next').on("click",function(e){
		loginSubTitleChangeList(1);
	});
	$('#loginSubTitleLogo').children('img').on("click",function(e){
		window.open($('#loginSubTitleLogo').children('img:visible').attr("src"), '_blank'); 
	});
}

function setMainTitleSlideImage() {
	
	$("#mainTitleLogo").children('img').remove();
	
	for(var i=0; i<mainTitleImageList.length; i++)
	{
		var mainTitleError = "document.forms[0].mainTitlePhoto"+i+".src='"+contextPath+"/common/images/ma/no_image.png'";
		//$("#mainTitleLogo").append("<img src='"+contextPath+"/common/images/client/"+mainTitleImageList[i]+"' name='mainTitlePhoto"+i+"' onerror="+mainTitleError+">");
		if(_fileDir == "AZURE")
			$('#mainTitleLogo').append("<img src='"+mainTitleImageList[i]+"' name='photo"+i+"' onerror="+mainTitleError+">");
		else
			$('#mainTitleLogo').append("<img src='"+contextPath+"/dream/imgSlide/"+mainTitleImageList[i]+"' name='photo"+i+"' onerror="+mainTitleError+">");
	}
	
	var mainTitleSlideShow = $("#mainTitleLogo"),	
		mainTitleListItems = mainTitleSlideShow.children('img'),
		mainTitleListLen	= mainTitleListItems.length,
		mainTitleSlideIndex  = 0,
		mainTitleChangeList = function (e) {
			if(typeof e =="undefined") e = 1;
			//$('#mainTitleLogo_prev').hide();
			//$('#mainTitleLogo_next').hide();
			mainTitleListItems.eq(mainTitleSlideIndex).fadeOut(transition_speed, function () {
				mainTitleSlideIndex = mainTitleSlideIndex + e;
				if(mainTitleSlideIndex < 0) mainTitleSlideIndex = mainTitleListLen - 1;
				if (mainTitleSlideIndex == mainTitleListLen) {
					mainTitleSlideIndex = 0;
				}
				//$('#mainTitleLogo_prev').hide();
				//$('#mainTitleLogo_next').hide();
				mainTitleListItems.eq(mainTitleSlideIndex).fadeIn(transition_speed, function(){
					$('#mainTitleLogo_prev').show();
					$('#mainTitleLogo_next').show();
				});
			});
      };

      mainTitleListItems.not(':first').hide();

	$('#mainTitleLogo_prev').on("click",function(e){
		mainTitleChangeList(-1);
	});
	
	$('#mainTitleLogo_next').on("click",function(e){
		mainTitleChangeList(1);
	});
	$('#mainTitleLogo').children('img').on("click",function(e){
		window.open($('#mainTitleLogo').children('img:visible').attr("src"), '_blank'); 
	});
}

function goInput()
{
	sequenceNextVal('SQACOMP_ID');
}

function setSequenceVal(sequenceVal)
{	
	maCompMngDetailForm.elements['maCompMngDetailDTO.compId'].value = sequenceVal;
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

	if(ckCreate(currentPageId)) maCompMngDetailForm.strutsAction.value = "<%=MaCompMngDetailAction.COMP_DETAIL_INPUT%>";
	else maCompMngDetailForm.strutsAction.value = "<%=MaCompMngDetailAction.COMP_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maCompMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maCompMngDetailForm), 'afterSave');
}

function goReload()
{
	setModal('<bean:message key="LABEL.LOADING"/>');
	var url = contextPath + "/maCompMngDetail.do";

	maCompMngDetailForm.elements['strutsAction'].value = '<%=MaCompMngDetailAction.COMP_CONFIG_RELOAD%>';

   $.post(url,FormQueryString(maCompMngDetailForm), function(_data){
       closeModal();
       alertMessage1('<bean:message key="LABEL.LOADED"/>');
   }); 
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maCompMngDetailForm.elements['maCompMngCommonDTO.compId'].value = maCompMngDetailForm.elements['maCompMngDetailDTO.compId'].value
    if (parent.findGridRow)	parent.findGridRow(maCompMngDetailForm.elements['maCompMngCommonDTO.compId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

/**
 * 전사원 비밀번호 리셋
 */
function goEmppwreset()
{
	setModal('<bean:message key="LABEL.PWRESET"/>');
	var url = contextPath + "/maCompMngDetail.do";

	maCompMngDetailForm.elements['strutsAction'].value = '<%=MaCompMngDetailAction.COMP_PWRESET_RELOAD%>';

   $.post(url,FormQueryString(maCompMngDetailForm), function(_data){
       closeModal();
       alertMessage1('<bean:message key="LABEL.PWRESETDONE"/>');
   }); 
}


function loadSlideImages()
{
	var url = contextPath + "/maCompMngDetail.do";
	
    var oldSAction = maCompMngDetailForm.elements['strutsAction'].value;
    maCompMngDetailForm.elements['strutsAction'].value = '<%=MaCompMngDetailAction.COMP_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(maCompMngDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);

    	if(maCompMngDetailForm.elements['maCompMngDetailDTO.subImgType'].value=="LOGINTITLE"){
			
    		loginTitleImageList = new Array();
			
	    	for(var i = 0; i<jsonObj.length ; i++)
	    	{
	    		loginTitleImageList.push(jsonObj[i].FILE_NAME);
	    	}
			setLoginTitleSlideImage();
			
		} else if(maCompMngDetailForm.elements['maCompMngDetailDTO.subImgType'].value=="LOGINSUBTITLE"){
			
			loginSubTitleImageList = new Array();
			
	    	for(var i = 0; i<jsonObj.length ; i++)
	    	{
	    		loginSubTitleImageList.push(jsonObj[i].FILE_NAME);
	    	}
			setLoginSubTitleSlideImage();
			
		} else if(maCompMngDetailForm.elements['maCompMngDetailDTO.subImgType'].value=="MAINTITLE"){
			
			mainTitleImageList = new Array();
	    	for(var i = 0; i<jsonObj.length ; i++)
	    	{
	    		mainTitleImageList.push(jsonObj[i].FILE_NAME);
	    	}
	    	setMainTitleSlideImage();
		}
    });
    
    maCompMngDetailForm.elements['strutsAction'].value = oldSAction;

}

function goLogintitleLogo() {
	maCompMngDetailForm.elements['maCompMngDetailDTO.subImgType'].value = "LOGINTITLE";
	goPhoto();
}

function goLoginsubtitleLogo() {
	maCompMngDetailForm.elements['maCompMngDetailDTO.subImgType'].value = "LOGINSUBTITLE";
	goPhoto();	
}

function goMaintitleLogo() {
	maCompMngDetailForm.elements['maCompMngDetailDTO.subImgType'].value = "MAINTITLE";
	goPhoto();	
}

function goPhoto() {

	var url =  contextPath + "/maDocImgPopList.do";
	
    var param = "strutsAction=" + '<%=MaCompMngDetailAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" + maCompMngDetailForm.elements['maCompMngDetailDTO.compId'].value +
                "&" + "maDocImgCommonDTO.objectType=LOGO"+
                "&" + "maDocImgCommonDTO.objectDesc="+ maCompMngDetailForm.elements['maCompMngDetailDTO.compDesc'].value +
                "&" + "maDocImgCommonDTO.subImgType="+maCompMngDetailForm.elements['maCompMngDetailDTO.subImgType'].value+
                "&" + "maDocImgCommonDTO.compNo=" + maCompMngDetailForm.elements['maCompMngDetailDTO.compNo'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);		

}




//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maCompMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maCompMngCommonDTO.compNo" />
	<html:hidden property="maCompMngCommonDTO.compId" />
	<html:hidden property="maCompMngDetailDTO.compId" />
	<html:hidden property="maCompMngDetailDTO.ctPath" />
	<html:hidden property="maCompMngDetailDTO.subImgType" />
    <html:hidden property="maDocImgCommonDTO.objectId" /> <!-- 첨부문서 Key -->
    <html:hidden property="maDocImgCommonDTO.objectType" />
    <html:hidden property="maDocImgCommonDTO.description" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.compNo"/></label>
             	 	<div id="compNoDiv" class="input_box">
             	 		<html:text property="maCompMngDetailDTO.compNo" tabindex="10"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.compDesc"/></label>
             	 	<div id="compNoDescDiv" class="input_box">
             	 		<html:text property="maCompMngDetailDTO.compDesc" tabindex="20" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.ctPath"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maCompMngDetailDTO.ctPathDesc" tabindex="30" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
             	 	</div>
             	 </div>
             	 <!-- 로그인 화면 스킨색 적용여부 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.initCtPathYn"/></label>
					<div class="input_box">
						<html:text property="maCompMngDetailDTO.initCtPathYn" tabindex="40"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="maCompMngDetailDTO.isUse" tabindex="50"/>
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                </div>
                <div class="field_blank"></div>
				<!-- Login sub title logo -->
				<div class="field_img">
					<label><bean:message key="LABEL.loginTitleLogo"/></label>
					<div class="img_box" name="maCompMngDetailDTO.loginSubTitleLogo" id="loginSubTitleLogo">
						<div class="function_box manual"><div class="fb_group1"><a class="b_loginsubtitlelogo"></a></div> </div>
					</div>
				</div>
				
				<!-- Login title logo -->
				<div class="field_img">
					<label><bean:message key="LABEL.loginSubTitleLogo"/></label>
					<div class="img_box" name="maCompMngDetailDTO.loginTitleLogo" id="loginTitleLogo">
						<div class="function_box manual"><div class="fb_group1"><a class="b_logintitlelogo"></a></div> </div>
					</div>
				</div>
				
				<!-- Main title logo -->
				<div class="field_img">
					<label><bean:message key="LABEL.mainTitleLogo"/></label>
					<div class="img_box" name="maCompMngDetailDTO.mainTitleLogo" id="mainTitleLogo">
						<div class="function_box manual"><div class="fb_group1"><a class="b_mainTitlelogo"></a></div> </div>
					</div>
				</div>
         </div> <!-- End of Article_box -->
         </div>
	</html:form>
</body>
</html:html>
