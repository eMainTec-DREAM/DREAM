<%--===========================================================================
설비부위 Popup
author  hyosung
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.asset.list.action.LovEqAsmbListAction"%>
<html>
<head>
<!-- 설비부위 -->
<title><bean:message key="LABEL.eqAsmb"/></title>
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
	
	setInitVal("lovEqAsmbListDTO.isUse", "Y");
	
	// 사용여부
	acSysDesc("lovEqAsmbListDTO.isUse", "lovEqAsmbListDTO.isUse", "IS_USE", true);
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.enableTreeGridLines();
	
	if(lovEqAsmbListForm.elements['lovEqAsmbListDTO.multiSelect'].value == "Y") myGrid.enableMultiselect(true);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}



/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	lovEqAsmbListForm.elements['strutsAction'].value = '<%=LovEqAsmbListAction.LOV_EQASMB_AC_FIND%>';
    var url = contextPath + "/eqAsmbValLov.do";
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovEqAsmbListForm),"EQASMB_ID");
 
}

function goSelect(){
    goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "EQASMB_ID");
}

/**
 * 검색
 */
function goSearch()
{
	//findGridList('Search');
	findGridList('SearchTree');
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
	<html:form action="/eqAsmbValLov" >

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

<html:hidden property="lovEqAsmbListDTO.extCode1" />
<html:hidden property="lovEqAsmbListDTO.extCode2" />
<html:hidden property="lovEqAsmbListDTO.codeType" />
<html:hidden property="lovEqAsmbListDTO.multiSelect" />
<html:hidden property="lovEqAsmbListDTO.equipId" />

<html:hidden property="strutsAction" />
<div class="section_wrap">
            <div class="sheader_box">
                <div class="sheader_wrap">
                    <a></a>
                </div>
                <div class="stitle_wrap">
                    <div class="stitle_icon">
                        <a></a>
                    </div>
                    <div class="stitle_tx">
                        <bean:message key="LABEL.Filter" />
                    </div>
                </div>
                <div class="function_box filter">
                    <div class="fb_group3">
                        <div class="sfb_wrap" style="display: none;"></div>
                    </div>
                    <div class="fb_group2"></div>
                </div>
            </div>
            <!--sheader_box end-->
            <div class="article_box">
                <div class="form_box">
                    <div class="field">
                        <!-- 부위명 -->
                        <label><bean:message key="LABEL.ctgAsmbName" /></label>
                        <div class="input_box">
                            <input type='text' name="lovEqAsmbListDTO.eqAsmbDesc" tabindex="110"/>
                        </div>
                        <!-- 사용여부 -->
						<div class="field">
							<label><bean:message key="LABEL.isUse"/></label>
							<div class="input_box">
								<html:text property="lovEqAsmbListDTO.isUse" tabindex="120"/>
								<p class="open_spop"><a><span>조회</span></a></p>
							</div>
						</div>
                    </div>
                </div>
            </div>
            <!--article_box end-->
        </div>
        <!--  end section_wrap -->
        <div class="section_wrap">
            <div class="sheader_box">
                <div class="sheader_wrap">
                    <a></a>
                </div>
                <div class="stitle_wrap">
                    <div class="stitle_icon">
                        <a></a>
                    </div>
                    <div class="stitle_tx">
                        <bean:message key="LABEL.List" />
                    </div>
                </div>
                <div class="function_box list">
                    <div class="fb_group3">
                        <div class="sfb_wrap" style="display: none;"></div>
                    </div>

                    <div class="fb_group2"></div>
                </div>
            </div>
            <!--sheader_box end-->
            <div class="article_box" id="listBox">
                <div class="grid_area">
                    <div id="gridbox"
                        style="width: 100%; height: 270px; background-color: white;"></div>
                </div>
            </div>
        </div>
        <!--  End of section_wrap -->
    </html:form>
</body>
</html>