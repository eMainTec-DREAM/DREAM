<%--===========================================================================
사진첨부 - 목록
author  jung7126
version $Id: maDocImgList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.doc.img.action.MaDocImgListAction" %>
<%@ page import="dream.doc.img.action.MaDocImgDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 첨부문서 -->
<title><bean:message key='MENU.DOCIMG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
 
<script language="javascript">
<!--//

//그리드명
var myGrid;

function loadPage() 
{
    maDocImgListForm.elements['maDocImgCommonDTO.deptId'].value    = "<%=user.getDeptId()%>";
    maDocImgListForm.elements['maDocImgCommonDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";	
    initGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setDateFormat("%Y-%m-%d");

	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	var _gridHeight;
	if(DECORATOR_NAME == "popupPage") _gridHeight = "150px";
	//Inner Page
	if(maDocImgListForm.elements['strutsAction'].value == '<%=MaDocImgListAction.BASE_QUICK_SEARCH%>')
	{
		$('.hideFilter').hide();
		
		setHeader(myGrid, "gridbox","goSearch", "", _gridHeight);  // grid, grid id
	}
	else
	{
		setHeader(myGrid, "gridbox","goSearch", "",_gridHeight); // grid, grid id
	}
	
	/* //팝업창일때만.
	if(DECORATOR_NAME == "popupPage")
	{
		$('#gridbox').css("height","150px");
		myGrid.setSizes();
	} */
}

/**
 * hide button manually
 */
function hideButton(buttonId)
{
	if(buttonId == "create" && maDocImgListForm.elements['strutsAction'].value != '<%=MaDocImgListAction.BASE_QUICK_SEARCH%>')
	{
		$('.b_create').hide();
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maDocImgList.do";
    maDocImgListForm.elements['strutsAction'].value = '<%=MaDocImgListAction.DOCIMG_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maDocImgListForm), "IMAGEID");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_imageId)
{
	maDocImgListForm.elements['maDocImgCommonDTO.imageId'].value = _imageId;
	findGridList('ReloadRow');
	maDocImgListForm.elements['maDocImgCommonDTO.imageId'].value = "";
}
/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maDocImgListForm.elements['maDocImgCommonDTO.imageId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	maDocImgListForm.elements['maDocImgCommonDTO.imageId'].value = getValueById(myGrid, selectedId,'imageId');
	goCommonTabPage(maDocImgListForm, '<%=MaDocImgDetailAction.DOCIMG_DETAIL_INIT%>', pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maDocImgDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maDocImgDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maDocImgListForm.elements['maDocImgCommonDTO.imageId'].value = "";
	goCommonTabPage(maDocImgListForm, '', "maDocImgDetail");
}

/**
 * 삭제
 */
function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'isDelCheck', 'imageId'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	 
	maDocImgListForm.strutsAction.value = '<%=MaDocImgListAction.DOCIMG_LIST_DELETE%>';
	var url = contextPath + "/maDocImgList.do";
	
	$.post(url,FormQueryString(maDocImgListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  

function afterDelete(){
	goClose('maDocImgDetail', this);
	//if(typeof goSearch =="function") goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
	
	reloadSlideImg();
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelAction(myGrid);
 }

 function reloadSlideImg()
 {
 	getIframeContent().loadSlideImages();
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maDocImgList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maDocImgCommonDTO.imageId"/><!-- Key -->
<html:hidden property="maDocImgCommonDTO.objectId"/>
<html:hidden property="maDocImgCommonDTO.objectType"/>
<html:hidden property="maDocImgCommonDTO.objectTypeCode"/>
<html:hidden property="maDocImgCommonDTO.deptId"/>
<html:hidden property="maDocImgCommonDTO.description"/>
<html:hidden property="maDocImgCommonDTO.param1"/>
<html:hidden property="maDocImgCommonDTO.compNo"/>
    <!-- searchbox 박스 Line -->
    <div class="section_wrap hideFilter">
    	<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
	       <div class="article_box" style="display:none;">
	           <div class="form_box">
	               <div class="field">
		               <label><bean:message key="LABEL.imageDesc"/></label>
	               	   <div class="input_box">
							<html:text property="maDocImgCommonDTO.description" tabindex="10" /> 
	               	   </div>
               	   </div>
	               <div class="field">
					   <label><bean:message key="LABEL.imgObjType"/></label>
					   <div class="input_box">
							<html:text property="maDocImgCommonDTO.objectTypeDesc" tabindex="20" 
								onkeydown="validationKeyDown('maDocImgCommonDTO.objectTypeDesc', 'maDocImgCommonDTO.objectTypeCode');"/>
							<p class="open_spop"><a href="javascript:lovSysDir('maDocImgCommonDTO.objectTypeCode', 'maDocImgCommonDTO.objectTypeDesc','OBJECT_TYPE');"><span>조회</span></a></p>
					   </div>
				   </div>
				   <div class="field">
					   <label><bean:message key="LABEL.deptId"/></label>
					   <div class="input_box">
							<html:text property="maDocImgCommonDTO.deptDesc" tabindex="30" 
								onkeydown="validationKeyDown('maDocImgCommonDTO.deptDesc', 'maDocImgCommonDTO.deptId');"/>
							<p class="open_spop"><a href="javascript:lovDept('maDocImgCommonDTO.deptId', '', 'maDocImgCommonDTO.deptDesc');"><span>조회</span></a></p>
					   </div>
				   </div>
				   <div class="field">
					   <label><bean:message key="LABEL.regId"/></label>
					   <div class="input_box">
							<html:text property="maDocImgCommonDTO.regDesc" tabindex="40" 
								onkeydown="validationKeyDown('maDocImgCommonDTO.regDesc', 'maDocImgCommonDTO.regId');"/>
							<p class="open_spop"><a href="javascript:lovEmp('maDocImgCommonDTO.regId', '', 'maDocImgCommonDTO.regDesc');"><span>조회</span></a></p>
					   </div>
				   </div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
		
	 <div class="section_wrap">
	    <div class="sheader_box">
	        <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
	    </div><!--sheader_box end-->
	    <div class="article_box" >
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>