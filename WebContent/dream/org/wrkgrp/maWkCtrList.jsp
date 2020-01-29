<%--===========================================================================
작업그룹 - 목록
author  kim21017
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.wrkgrp.action.MaWkCtrListAction" %>
<%@ page import="dream.org.wrkgrp.action.MaWkCtrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업그룹 -->
<title><bean:message key='MENU.WKCTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var selectedInd;
var wkCtrDescAc;
var isUseAc;
function loadPage() 
{
    initGrid();
    // 사용여부 - 기본 선택.
    maWkCtrListForm.elements['maWkCtrCommonDTO.filterIsUse'].value = "Y";
    
    /** 작업그룹  */
    wkCtrDescAc = new autoC({"maWkCtrCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWkCtrCommonDTO.filterWkCtrDesc":"description"
    });
    wkCtrDescAc.init();

    /** 상위작업그룹  */
    wkCtrDescAc = new autoC({"maWkCtrCommonDTO.filterPaWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWkCtrCommonDTO.filterPaWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    /**사용여부 */
    acSysDesc("maWkCtrCommonDTO.filterIsUse","maWkCtrCommonDTO.filterIsUse","IS_USE");
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
        selectedId = rowId;
		selectedInd = columnId;
    });
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWkCtrList.do";
    maWkCtrListForm.elements['strutsAction'].value = '<%=MaWkCtrListAction.WKCTR_LIST_FIND%>';
    setModal();
    $.post(url,FormQueryString(maWkCtrListForm), function(_data){
	    myGrid.clearAll(); 
	    setLoading("gridbox");
        myGrid.parse(_data,"js");
        myGrid.expandAll(); //펼치기
        setCounter(myGrid,"gridbox"); //TreeGrid는 펼쳐진 Rows만 카운트 함.
        closeModal();
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maWkCtrListForm.elements['maWkCtrCommonDTO.wkCtrId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaWkCtrListAction.WKCTR_LIST_FIND%>');   
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
   	maWkCtrListForm.elements['maWkCtrCommonDTO.wkCtrId'].value = getValueById(myGrid, selectedId, 'WKCTRID');
   	maWkCtrListForm.elements['maWkCtrCommonDTO.detailPaWkCtrId'].value = getValueById(myGrid, selectedId, 'WKCTRID');
   	maWkCtrListForm.elements['maWkCtrCommonDTO.detailPaWkCtrDesc'].value = getValueById(myGrid, selectedId, 'WKCTRDESC');

	goCommonTabPage(maWkCtrListForm, <%= MaWkCtrDetailAction.WKCTR_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maWkCtrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maWkCtrListForm.elements['maWkCtrCommonDTO.wkCtrId'].value = getValueById(myGrid, selectedId, 'WKCTRID');
   	maWkCtrListForm.elements['maWkCtrCommonDTO.detailPaWkCtrId'].value = getValueById(myGrid, selectedId, 'WKCTRID');
   	maWkCtrListForm.elements['maWkCtrCommonDTO.detailPaWkCtrDesc'].value = getValueById(myGrid, selectedId, 'WKCTRDESC');
    maWkCtrListForm.elements['strutsAction'].value = '<%=MaWkCtrDetailAction.WKCTR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maWkCtrListForm), 'maWkCtrDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maWkCtrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maWkCtrListForm.elements['maWkCtrCommonDTO.wkCtrId'].value = "";
    goCommonTabPage(maWkCtrListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'WKCTRID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maWkCtrListForm.strutsAction.value = '<%=MaWkCtrListAction.WKCTR_LIST_DELETE%>';
    var url = contextPath + "/maWkCtrList.do";
    
    $.post(url,FormQueryString(maWkCtrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maWkCtrDetail');
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWkCtrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWkCtrCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maWkCtrCommonDTO.wkCtrId"/><!-- Key -->
<html:hidden property="maWkCtrCommonDTO.detailPaWkCtrId"/><!-- Key -->
<html:hidden property="maWkCtrCommonDTO.detailPaWkCtrDesc"/><!-- Key -->
<html:hidden property="maWkCtrCommonDTO.filterPaWkCtrId"/>
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
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
			</div>
		</div><!--sheader_box end-->
	       <div class="article_box">
	           <!--작업그룹NO  -->
	           <div class="form_box">
	               <div class="field">
		               <label><bean:message key="LABEL.wkCtrNo"/></label>
		               <div class="input_box">
		               		<html:text property="maWkCtrCommonDTO.filterWkCtrNo" tabindex="10"/>
		               </div>
	               </div>
	               <!--작업그룹  -->
	               <div class="field">
		               <label><bean:message key="LABEL.wkCtr"/></label>
                       <div class="input_box">
                            <html:text property="maWkCtrCommonDTO.filterWkCtrDesc" tabindex="20"/>
                             <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
               	   </div>
               	   <!--상위작업그룹  -->
               	   <div class="field">
                       <label><bean:message key="LABEL.pWkCtr"/></label>
                       <div class="input_box">
	                        <html:text property="maWkCtrCommonDTO.filterPaWkCtrDesc" tabindex="30"/>
	                        <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
                   </div>        
                   <!-- 사용여부 -->   
                   <div class="field">
                      <label><bean:message key="LABEL.isUse"/></label>
                      <div class="input_box">
                           <html:text property="maWkCtrCommonDTO.filterIsUse" tabindex="40"/>
		                    <p class="open_spop">
		                        <a>
		                            <span>조회</span>
		                        </a>
		                    </p>
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
			</div>
        </div><!--sheader_box end-->
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
    </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>