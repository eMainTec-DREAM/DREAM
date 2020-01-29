<%--===========================================================================
일일작업승인 사진
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.note.daily.action.MaWoDailyImageListAction" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	
	List afterImageList  = (List)request.getAttribute("slideAfterFileList");
%>
<html>
<head>
<!-- 작업사진 -->
<title><bean:message key='TAB.maWoDailyImageList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var afterSlideImageList = new Array();

var change_img_time 	= 5000;	
var transition_speed	= 500;

<% 
if(afterImageList != null){
	for(int i=0; i<afterImageList.size(); i++)
	{
	    Map afterImageMap = (Map)afterImageList.get(i);
%>
	afterSlideImageList.push('<%=(String)afterImageMap.get("FILE_NAME")%>');
<%
	}
}
%>

function loadPage() 
{
	setAfterSlideImage();
	
	setImageCount();
}

//작업사진 탭 변경 
function setImageCount(){

	var url = contextPath + "/maWoDailyImageList.do";
	
	maWoDailyImageListForm.elements['strutsAction'].value = '<%=MaWoDailyImageListAction.IMAGE_COUNT%>';
	
	$.post(url, FormQueryString(maWoDailyImageListForm), function(_data){
		afterSetImageCount(_data);
	});
}

function afterSetImageCount(data){
	var cnt = parseXmlDoc(data, 'ARR0');
	var totalCnt = cnt[0];
	
	var titleObj = parent.$('#'+currentPageId+'_tabList > .ac_menu');
	
	var titleHtml = titleObj.html();
	if(titleHtml && titleHtml.indexOf(" (") != "-1") titleHtml = titleHtml.substring(0,titleHtml.indexOf(" ("));
	titleObj.html(titleHtml+" ("+totalCnt+") ");
}

/**
 * 이미지를 불러와서 배열에 넣는다.
 */
function loadSlideImages()
{
	var url = contextPath + "/maWoDailyImageList.do";

    var oldSAction = maWoDailyImageListForm.elements['strutsAction'].value;
    maWoDailyImageListForm.elements['strutsAction'].value = '<%=MaWoDailyImageListAction.IMAGE_FIND%>'
	
    //이미지 저장 혹은 후에 다시 이미지를 불러와서 로드
    $.post(url,FormQueryString(maWoDailyImageListForm), function(_data){
    	
    	var jsonObj = JSON.parse(_data);
    	
		if(maWoDailyImageListForm.elements['maWoDailyImageListDTO.subImgType'].value=="APPRSIGN"){
			
			afterSlideImageList = new Array();
			
	    	for(var i = 0; i<jsonObj.length ; i++)
	    	{
	    		afterSlideImageList.push(jsonObj[i].FILE_NAME);
	    	}
			setAfterSlideImage();
		}
    });
    
    maWoDailyImageListForm.elements['strutsAction'].value = oldSAction;
    
    
    setImageCount();
}

function setAfterSlideImage()
{
	 //기존에 있던 img 태그를 지운다(사진삭제)
	 $("#afterImageSlide").children('img').remove();
	
	 //Slide div안에 img태그 및 에러시 나타나는 이미지 설정
	for(var i=0; i<afterSlideImageList.length ; i++)
	{
		var afterError = "document.forms[0].afterPhoto"+i+".src='"+contextPath+"/common/images/ma/no_image.png'";
		if(_fileDir == "AZURE")
			$('#afterImageSlide').append("<img src='"+afterSlideImageList[i]+"' name='photo"+i+"' onerror="+afterError+">");
		else
			$("#afterImageSlide").append("<img src='"+contextPath+"/dream/imgSlide/"+afterSlideImageList[i]+"' name='afterPhoto"+i+"' onerror="+afterError+">");
	}
	
	var afterSlideShow   = $("#afterImageSlide"),
	    afterListItems   = afterSlideShow.children('img'),
	    afterListLen	 = afterListItems.length,
        afterSlideIndex  = 0,
		afterChangeList = function (e) {
			if(typeof e =="undefined") e = 1;
			$('#after_imgslide_prev').hide();
			$('#after_imgslide_next').hide();
			afterListItems.eq(afterSlideIndex).fadeOut(transition_speed, function () {
				afterSlideIndex = afterSlideIndex + e;
				if(afterSlideIndex < 0) afterSlideIndex = afterListLen - 1;
				if (afterSlideIndex == afterListLen) {
					afterSlideIndex = 0;
				}
				$('#after_imgslide_prev').hide();
				$('#after_imgslide_next').hide();
				afterListItems.eq(afterSlideIndex).fadeIn(transition_speed, function(){
					$('#after_imgslide_prev').show();
					$('#after_imgslide_next').show();
				});
			});
        };

    //첫 번째 사진이 아닌 것들을 숨김처리
    afterListItems.not(':first').hide();

	// 사진 좌우 버튼 클릭 이벤트ㅡ
	$('#after_imgslide_prev').on("click",function(e){
		afterChangeList(-1);
	});
	
	$('#after_imgslide_next').on("click",function(e){
		afterChangeList(1);
	});
	$('#afterImageSlide').children('img').on("click",function(e){
		window.open($('#afterImageSlide').children('img:visible').attr("src"), '_blank'); 
	});
}

function goAfterphoto()
{
	maWoDailyImageListForm.elements['maWoDailyImageListDTO.subImgType'].value = "APPRSIGN";
	goPhotoPage();
}
/**
 * 사진 등록하는 팝업 띄우기. subImgType에 파라미터 넘김.
 */
function goPhotoPage(){

	var workDesc = parent.maWoDailyDetailForm.elements['maWoDailyDetailDTO.title'].value;
	if(workDesc == "" || workDesc == "undefined"){
		workDesc = maWoDailyImageListForm.elements['maWoDailyImageListDTO.subImgType'].value+"_PHOTO";
	}
	
	var url =  contextPath + "/maDocImgPopList.do";
    var param = "strutsAction=" + '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" + maWoDailyImageListForm.elements['maWoDailyCommonDTO.woDayListId'].value +
                "&" + "maDocImgCommonDTO.objectType=WODAY"+
                "&" + "maDocImgCommonDTO.objectDesc="+workDesc+
                "&" + "maDocImgCommonDTO.subImgType="+maWoDailyImageListForm.elements['maWoDailyImageListDTO.subImgType'].value+
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDailyImageList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDailyCommonDTO.woDayListId"/><!-- Key -->
<html:hidden property="maWoDailyImageListDTO.subImgType"/>

			<!--후 사진 -->
			<div class="field_img_double" style="height:250px; width:48%; margin-left:10px;">
				<div class="img_box" name="maWoDailyImageListDTO.afterImgslide" id="afterImageSlide" style="float:left; width:75%; height:220px;" >
					<div class="function_box manual"><div class="fb_group1"><a class="b_afterphoto"></a></div> </div>
					<div class="img_prev" id="after_imgslide_prev" style="height:30px; top:190px;">
						<a></a>
					</div>
					<div class="img_next" id="after_imgslide_next" style="height:30px; top:190px;">
						<a></a>
					</div>
				</div>
				<div style="float:left; width:75%; text-align:center; padding:5px 0">[<bean:message key="TAB.maWoDailyImageList"/>]</div>
			</div>
</html:form> 
</body>
</html>