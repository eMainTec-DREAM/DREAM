<%--===========================================================================
화면입력항목 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaPgMngFieldListAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngFieldDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면입력항목 -->
<title><bean:message key='MENU.PGFIELD'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var fileNameAc;

function loadPage() 
{
	// 화면ID, 화면명
	fileNameAc = new autoC({"maPgMngCommonDTO.filterPageDesc":"description"});
    fileNameAc.setTable("TAPAGE");
    fileNameAc.setAcResultMap({
        "maPgMngCommonDTO.filterPageDesc":"description"
      , "maPgMngCommonDTO.filterFileName":"file_name"
    });
    fileNameAc.init();  
	
    // 항목옵션
    acSysDesc("maPgMngCommonDTO.filterFieldOptionDesc","maPgMngCommonDTO.filterFieldOptionId","FIELD_OPTION");
    // 시스템 숨김여부
    acSysDesc("maPgMngCommonDTO.filterHiddenYn","maPgMngCommonDTO.filterHiddenYn","IS_USE");
    // 화면Display여부
    acSysDesc("maPgMngCommonDTO.filterDisplayYn","maPgMngCommonDTO.filterDisplayYn","IS_USE");
    // 읽기전용여부
    acSysDesc("maPgMngCommonDTO.filterReadonlyYnDesc","maPgMngCommonDTO.filterReadonlyYn","READONLY_YN");

	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goTabPage("maPgFieldMngDetail");
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = "";
        return sortColumn("maPgFieldMngList", this, maPgMngFieldListForm, "PGFIELDID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	if(checkValidation()) return;
	
	maPgMngFieldListForm.elements['maPgMngCommonDTO.pageId'].value = "";
	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = "";
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	var form = document.maPgMngFieldListForm;	
	form.strutsAction.value = '<%=MaPgMngFieldListAction.PG_FIELD_LIST_FIND %>';
	
	var url = contextPath + "/maPgFieldMngList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPgMngFieldListForm), "PGFIELDID", "Y");

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
	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = getValueById(myGrid, selectedId,'PGFIELDID');
	maPgMngFieldListForm.elements['maPgMngCommonDTO.pageId'].value = getValueById(myGrid, selectedId,'PAGEID');

	goCommonTabPage(maPgMngFieldListForm, <%= MaPgMngFieldDetailAction.PG_FIELD_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgFieldId)
{
	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = _pgFieldId;
	findGridList('ReloadRow');
	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgFieldMngDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPgMngFieldListForm.elements['strutsAction'].value = '<%=MaPgMngFieldDetailAction.PG_FIELD_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPgMngFieldListForm), 'maPgFieldMngDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maPgFieldMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = "";
 	goCommonTabPage(maPgMngFieldListForm, '', pageId);
}
 
/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = "";
    excelServerAction("maPgFieldMngList", maPgMngFieldListForm);
}

  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PGFIELDID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPgMngFieldListForm.strutsAction.value = '<%=MaPgMngFieldListAction.PG_FIELD_LIST_DELETE%>';
	var url = contextPath + "/maPgFieldMngList.do";
	
	$.post(url,FormQueryString(maPgMngFieldListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maPgFieldMngDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSetsysy() {
    
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.CMSG058'/>", function(result){
         if(result){
            var selectedArray = getSelectedRows(myGrid, 'isDelCheck', 'PGFIELDID'); //Grid, check box column seq, pk column seq
            if(typeof selectedArray == "undefined"){
                alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
                return;
            }
            maPgMngFieldListForm.strutsAction.value = '<%=MaPgMngFieldListAction.PG_FIELD_LIST_SYSY%>';
            var url = contextPath + "/maPgFieldMngList.do";
            
            $.post(url,FormQueryString(maPgMngFieldListForm)+selectedArray , function(_data){
                afterSetsys();
            });
         }
    });
    
}

function goSetsysn() {
    
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.CMSG058'/>", function(result){
         if(result){
            var selectedArray = getSelectedRows(myGrid, 'isDelCheck', 'PGFIELDID'); //Grid, check box column seq, pk column seq
            if(typeof selectedArray == "undefined"){
                alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
                return;
            }
            maPgMngFieldListForm.strutsAction.value = '<%=MaPgMngFieldListAction.PG_FIELD_LIST_SYSN%>';
            var url = contextPath + "/maPgFieldMngList.do";
            
            $.post(url,FormQueryString(maPgMngFieldListForm)+selectedArray , function(_data){
                afterSetsys();
            });
         }
    });
    
}

function afterSetsys()
{
    goClose('maPgFieldMngDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgFieldMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="maPgMngCommonDTO.filterFieldOptionId"/>
<html:hidden property="maPgMngFieldListDTO.pgFieldId"/>
<html:hidden property="maPgMngCommonDTO.filterReadonlyYn"/>
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
                <!-- 화면 ID -->
                <div class="field">
                    <label><bean:message key="LABEL.fileName"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterFileName" tabindex="20"/>
                    </div>
                </div>
                <!-- 화면명 -->
                <div class="field">
                    <label><bean:message key="LABEL.pageName"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterPageDesc" tabindex="30"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- Input Property ID -->
                <div class="field">
                    <label><bean:message key="LABEL.fieldId"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.pgFieldId" tabindex="40"/>
                    </div>
                </div>
                <!-- 항목설명 -->
                <div class="field">
                    <label><bean:message key="LABEL.fieldDesc"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterPgFieldDesc" tabindex="50"/>
                    </div>
                </div>
                <!-- 시스템숨김여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.hiddenYn"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterHiddenYn" tabindex="60" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 화면Display여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.displayYn"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterDisplayYn" tabindex="60" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- Label ID -->
                <div class="field">
                    <label><bean:message key="LABEL.labelId"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterKeyNo" tabindex="50"/>
                    </div>
                </div>
                <!-- Label명 -->
                <div class="field">
                    <label><bean:message key="LABEL.labelName"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterKeyName" tabindex="50"/>
                    </div>
                </div>
                <!-- 항목옵션 -->
                <div class="field">
                    <label><bean:message key="LABEL.fieldOption"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterFieldOptionDesc" tabindex="60" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 읽기전용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.readonlyYn"/></label>
                    <div class="input_box">
                        <html:text property="maPgMngCommonDTO.filterReadonlyYnDesc" tabindex="60" />
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