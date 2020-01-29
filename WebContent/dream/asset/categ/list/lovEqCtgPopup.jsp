<%--===========================================================================
설비종류 Popup
author  ssong
version $Id: lovEqCtgPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.asset.categ.list.action.LovEqCtgListAction"%>
<html>
<head>
<!-- 설비종류 -->
<title><bean:message key="MENU.EQCTG"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

function loadPage() 
{
	initGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovEqCtgList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList()
{
	var form = document.lovEqCtgListForm;	
	form.strutsAction.value = '<%=LovEqCtgListAction.LOV_EQCTG_FIND%>';
	var url = contextPath + "/lovEqCtgList.do";
    $.post(url,FormQueryString(form), function(_data){
		myGrid.clearAll(); setLoading("gridbox");
    	myGrid.parse(_data,"js");
//     	myGrid.expandAll(); //펼치기
    	setCounter(myGrid,"gridbox", "goSearch"); //TreeGrid는 펼쳐진 Rows만 카운트 함.
    });
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;
    //설비마스터에서 종류선택시 마지막 레벨만 선택가능합니다.
    if(lovEqCtgListForm.elements['lovEqCtgListDTO.extCode2'].value=="IS_LOWEST_LVL"&&
    		getValueById(myGrid, selectedId, 'ISLOWESTLVL')!="Y"){
    	alertMessage1("<bean:message key='MESSAGE.MSG0061'/>");
    	return;
    }
	returnArray[0] = getValueById(myGrid, selectedId,'EQCTGID'); // ID
    returnArray[1] = getValueById(myGrid, selectedId,'FULLDESC').replace(/&gt;/gi, ">"); //FULL_DESC

	var dirType = lovEqCtgListForm.elements['lovEqCtgListDTO.codeType'].value;
	
    getIframeContent().setLovValue(returnArray, dirType);
    closeLayerPopup();
}

/**
 * 검색
 */
function goSearch()
{
	findGridList();
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovEqCtgList" >
		<html:hidden property="lovEqCtgListDTO.extCode1" />
		<html:hidden property="lovEqCtgListDTO.extCode2" />
		<html:hidden property="lovEqCtgListDTO.codeType" />
		<html:hidden property="strutsAction" />
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<!-- 위치명 -->
					<div class="field">
						<label><bean:message key="LABEL.ctgName"/></label>
						<div class="input_box">
							<input type='text' name="lovEqCtgListDTO.fullDesc" tabindex="10"/>
						</div>
					</div>
					<!-- 레벨 -->
					<div class="field">
						<label><bean:message key="LABEL.level"/></label>
						<div class="input_box">
							<html:text property="lovEqCtgListDTO.lvl" tabindex="20"
								onblur="valLvl('lovEqCtgListDTO.lvl', true);"/>
							<p class="open_spop">
								<a href="javascript:lovTable('lovEqCtgListDTO.lvl', 'lovEqCtgListDTO.lvl','LEVEL');">
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
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