<%--===========================================================================
자재재고
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.stk.action.MaPttStckListAction" %>
<%@ page import="dream.tool.stk.action.MaPttStckDetailAction" %>
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
<!-- 자재재고 -->
<title><bean:message key="MENU.PTTSTCK"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var partNameAc;
var isUseAc;
var wareHouseAc;

function loadPage() 
{
	var twcodeId = loginUser.twcodeId;
	var twname = loginUser.twname;

	if(twcodeId =="null") twcodeId = "";
	if(twname == "null") twname = "";
	initGrid();
	maPttStckListForm.elements['maPttStckCommonDTO.filterWcodeId'].value = twcodeId;
	maPttStckListForm.elements['maPttStckCommonDTO.filterWname'].value = twname;
    
	//--------------------------------------------------------------------------------//
	partNameAc = new autoC({"maPttStckCommonDTO.filterPartDesc":"description"});
    partNameAc.setAcConditionMap({
 	   "part_categ":"TOOL",
 	   "comp_no":loginUser.compNo
 	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcDConditionMap({
    	"wcode_id" : "maPttStckCommonDTO.filterWcodeId"
    });
    partNameAc.init();

    //--------------------------------------------------------------------------------//
    
    wareHouseAc = new autoC({"maPttStckCommonDTO.filterWname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttStckCommonDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.init();
    //--------------------------------------------------------------------------------//
    
    acSysDesc("maPttStckCommonDTO.filterSaftyYN","maPttStckCommonDTO.filterSaftyYN","IS_USE");
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
    	maPttStckListForm.elements['maPttStckCommonDTO.wcodeId'].value = "";
    	maPttStckListForm.elements['maPttStckCommonDTO.partId'].value = "";
        return sortColumn("maPttStckList", this, maPttStckListForm, ["WCODEID","PARTID"], ind, direction);
    });
    //RowSet에 넘길 array
//     var colArr = new Array();
//     colArr.push("WNAME");
//     colArr.push("PARTNO");
//     colArr.push("PARTNAMESIZE");
//     colArr.push("TOTSAFTYQTY");
//     colArr.push("TOTSTOCKQTY");
//     colArr.push("TOTUSEQTY");
//     colArr.push("UNDERSAFTYQTY");
    
    
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox"); 
//     setRowSpanStd(myGrid,'PARTNO',colArr); 
    }); 
    myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPttStckList.do";

    maPttStckListForm.elements['strutsAction'].value = '<%=MaPttStckListAction.PTSTCK_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttStckListForm), ["WCODEID","PARTID"], "Y");
    
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPttStckListForm.elements['maPttStckCommonDTO.wcodeId'].value = "";     // 검색시 Tab 이동Key Clear
	maPttStckListForm.elements['maPttStckCommonDTO.partId'].value = "";      // 검색시 Tab 이동Key Clear
	findGridList('Search');
}
function findGridRow(wcodeId, partId)
{
	goSearch();
// 	maPttStckListForm.elements['maPttStckCommonDTO.wcodeId'].value 	= wcodeId;
// 	maPttStckListForm.elements['maPttStckCommonDTO.partId'].value 	= partId;
// 	findGridList('ReloadRow');
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
	maPttStckListForm.elements['maPttStckCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPttStckListForm.elements['maPttStckCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
    maPttStckListForm.elements['maPttStckCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
	
    goCommonTabPage(maPttStckListForm, <%=MaPttStckDetailAction.PTSTCK_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPttStckDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPttStckListForm.elements['maPttStckCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPttStckListForm.elements['maPttStckCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
    maPttStckListForm.elements['maPttStckCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
    maPttStckListForm.elements['strutsAction'].value = '<%=MaPttStckDetailAction.PTSTCK_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPttStckListForm), 'maPttStckDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPttStckDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPttStckListForm.elements['maPttStckCommonDTO.wcodeId'].value = "";
	maPttStckListForm.elements['maPttStckCommonDTO.partId'].value = "";
    goCommonTabPage(maPttStckListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'wcodeId', 'partId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPttStckListForm.strutsAction.value = '<%=MaPttStckListAction.PTSTCK_LIST_DELETE%>';
    var url = contextPath + "/maPttStckList.do";

    $.post(url,FormQueryString(maPttStckListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPttStckDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 폐기신청
 */
 var selectArray;
function goCheckdis()
{
	var selArray = getSelectedRows(myGrid, 'ISDELCHECK', 'PARTID','WCODEID');
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0090'/>", function(result){
		 if(result){
			 selectArray = selArray;
			sequenceNextVal('SQAPTDISUSELIST_ID');
		 }
		});
}

function setSequenceVal(sequenceVal)
{
	maPttStckListForm.elements['maPttStckCommonDTO.reqIdx'].value = sequenceVal;
	maPttStckListForm.strutsAction.value = '<%=MaPttStckListAction.PTSTCK_LIST_DISREQ%>';
	var url = contextPath + "/maPttStckList.do"; 
   $.post(url,FormQueryString(maPttStckListForm)+selectArray , function(_data){
   	afterDisreq();
   });
}
/**
 * after구매신청
 */
 function afterDisreq()
 {
 	//구매신청 상세 팝업 띄우기
	var url   = contextPath + "/maPttDisDetail.do";
	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=1001&maPttDisCommonDTO.ptdisuselistId="+ maPttStckListForm.elements['maPttStckCommonDTO.reqIdx'].value;
  
    openWindowWithPost(url, "DISREQHDR", param, pos);
 }
 

/**
 * Excel Export
 */
function goExcel()
{
	maPttStckListForm.elements['maPttStckCommonDTO.wcodeId'].value = "";
	maPttStckListForm.elements['maPttStckCommonDTO.partId'].value = "";
    excelServerAction("maPttStckList", maPttStckListForm );  

}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPttStckList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttStckCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maPttStckCommonDTO.wcodeId"/><!-- Key -->
<html:hidden property="maPttStckCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPttStckCommonDTO.partGrade"/><!-- Key -->
<html:hidden property="maPttStckCommonDTO.filterWcodeId"/>
<html:hidden property="maPttStckCommonDTO.reqIdx"/>
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
                   <div class="field">
                       <label><bean:message key="LABEL.wname"/></label>
                       <div class="input_box">
                            <html:text property="maPttStckCommonDTO.filterWname" tabindex="10" />
		                    <p class="open_spop">
		                        <a>
		                            <span>조회</span>
		                        </a>
		                    </p>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.partNo"/></label>
                       <div class="input_box">
                            <html:text property="maPttStckCommonDTO.filterPartNo" tabindex="20"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.partDesc"/></label>
                       <div class="input_box">
                            <html:text property="maPttStckCommonDTO.filterPartDesc" tabindex="30"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.ptSize"/></label>
                       <div class="input_box">
                            <html:text property="maPttStckCommonDTO.filterPtSize" tabindex="40"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.maker"/></label>
                       <div class="input_box">
                            <html:text property="maPttStckCommonDTO.filterMaker" tabindex="50"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="maPttStckCommonDTO.filterModel" tabindex="60"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.qtyOverCnt"/></label>
                       <div class="input_box">
                            <html:text property="maPttStckCommonDTO.filterQtyCnt" tabindex="60" styleClass="num"/>
                       </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.underSaftyQty"/></label>
                        <div class="input_box">
                           <html:text property="maPttStckCommonDTO.filterSaftyYN" tabindex="70"/>
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