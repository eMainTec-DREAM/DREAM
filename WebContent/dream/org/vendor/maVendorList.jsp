<%--===========================================================================
관련업체 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.vendor.action.MaVendorListAction" %>
<%@ page import="dream.org.vendor.action.MaVendorDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 관련업체 -->
<title><bean:message key="MENU.VENDOR"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

var vendorAc, vendorTypeAc;
function loadPage() 
{
	initGrid();
	
	/**업체명  */
	vendorAc = new autoC({"maVendorCommonDTO.filterVendorDesc":"description"});
	vendorAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
	vendorAc.setTable("TAVENDOR");
	vendorAc.setAcResultMap({
        "maVendorCommonDTO.filterVendorDesc":"description"
    });
	vendorAc.init();
	
	//--------------------------------거래처 종류 
	vendorTypeAc = new autoC({"maVendorCommonDTO.filterVendorTypeDesc":"description"});
	vendorTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"dir_type":"VENDOR_TYPE"
 	   });
	vendorTypeAc.setTable("TACDUSRD");
	vendorTypeAc.setKeyName("maVendorCommonDTO.filterVendorType")
    vendorTypeAc.setAcResultMap({
        "maVendorCommonDTO.filterVendorType":"cdusrd_no"
    });
	vendorTypeAc.init();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maVendorListForm.elements['maVendorCommonDTO.vendorId'].value = "";
    	return sortColumn("maVendorList", this, maVendorListForm, "VENDORID", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maVendorList.do";
    maVendorListForm.elements['strutsAction'].value = '<%=MaVendorListAction.VENDOR_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maVendorListForm), "VENDORID","Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_vendorId)
{
	maVendorListForm.elements['maVendorCommonDTO.vendorId'].value = _vendorId;
	findGridList('ReloadRow');
	maVendorListForm.elements['maVendorCommonDTO.vendorId'].value = "";
}
/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maVendorListForm.elements['maVendorCommonDTO.vendorId'].value = "";    // 검색시 Tab 이동Key Clear
	findGridList('Search', '<%=MaVendorListAction.VENDOR_LIST_FIND%>');
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
	var form = document.maVendorListForm;
	form.elements['maVendorCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
	form.elements['maVendorCommonDTO.vendorId'].value = getValueById(myGrid, selectedId, 'vendorId');

    goCommonTabPage(form, <%=MaVendorDetailAction.VENDOR_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maVendorDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maVendorListForm.elements['maVendorCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maVendorListForm.elements['maVendorCommonDTO.vendorId'].value = getValueById(myGrid, selectedId, 'vendorId');
    maVendorListForm.elements['strutsAction'].value = '<%=MaVendorDetailAction.VENDOR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maVendorListForm), 'maVendorDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maVendorDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maVendorListForm.elements['maVendorCommonDTO.vendorId'].value = "";
    goCommonTabPage(maVendorListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'vendorId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maVendorListForm.strutsAction.value = '<%=MaVendorListAction.VENDOR_LIST_DELETE%>';
    var url = contextPath + "/maVendorList.do";

    $.post(url,FormQueryString(maVendorListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maVendorDetail', this);
    //  goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maVendorListForm.elements['maVendorCommonDTO.vendorId'].value = "";
	excelServerAction("maVendorList", maVendorListForm );
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maVendorListForm.elements['maVendorCommonDTO.vendorId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maVendorList">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maVendorCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maVendorCommonDTO.vendorId"/><!-- Key -->
<html:hidden property="maVendorCommonDTO.filterVendorType"/>
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
               <div class="form_box">
               <!--업체명 -->
                   <div class="field">
                       <label><bean:message key="LABEL.vendorDesc"/></label>
                       <div class="input_box">
                            <html:text property="maVendorCommonDTO.filterVendorDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a>
                                   <span> 조회</span>
                                </a>
                            </p>
                       </div>
                   </div>
                   <!-- 거래처 종류 -->
                   <div class="field">
                       <label><bean:message key="LABEL.vendorType"/></label>
                       <div class="input_box">
                            <html:text property="maVendorCommonDTO.filterVendorTypeDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a>
                                   <span> 조회</span>
                                </a>
                            </p>
                       </div>
                   </div>
               <!--비고 -->
                   <div class="field">
                       <label><bean:message key="LABEL.remark"/></label>
                       <div class="input_box">
                            <html:text property="maVendorCommonDTO.filterRemark" tabindex="20"/>
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