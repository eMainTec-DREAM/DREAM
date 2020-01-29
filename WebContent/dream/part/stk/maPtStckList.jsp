<%--===========================================================================
자재재고
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.stk.action.MaPtStckListAction" %>
<%@ page import="dream.part.stk.action.MaPtStckDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
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
<title><bean:message key="MENU.PTSTCK"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var partNameAc;
var wareHouseAc;
var partGroupAc;
var equipAc;

function loadPage() 
{
	if(_isFromNotice != "Y")
	{
		var wcodeId = loginUser.filterWcodeId;
		var wname = loginUser.filterWcodeDesc;
		
		if(wcodeId =="null") wcodeId = "";
		if(wname == "null") wname = "";
		
		//공장명
	    if(loginUser.filterPlant!='null'){
	    	maPtStckListForm.elements['maPtStckCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	maPtStckListForm.elements['maPtStckCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	    }
		
	    maPtStckListForm.elements['maPtStckCommonDTO.filterWcodeId'].value = wcodeId;
		maPtStckListForm.elements['maPtStckCommonDTO.filterWname'].value = wname;
		
		maPtStckListForm.elements['maPtStckCommonDTO.filterQtyCnt'].value = "0.1";
	}
	
	initGrid();
	
	partNameAc = new autoC({"maPtStckCommonDTO.filterPartDesc":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcDConditionMap({
    	"wcode_id" : "maPtStckCommonDTO.filterWcodeId"
    });
    partNameAc.init();
    
    wareHouseAc = new autoC({"maPtStckCommonDTO.filterWname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART",
    	"is_use":"Y"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPtStckCommonDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.setCustomLov("lovWh('maPtStckCommonDTO.filterWcodeId', 'maPtStckCommonDTO.filterWname')");
    wareHouseAc.init();


	partGroupAc = new autoC({"maPtStckCommonDTO.filterPartGroupDesc":"description"});
	partGroupAc.setAcDisplay("DESCRIPTION");
	partGroupAc.setAcConditionMap({
	    	"dir_type":"PART_GROUP",
	    	"is_use":"Y",
	    	"comp_no":loginUser.compNo
		   });
	partGroupAc.setTable("TACDUSRD");
	partGroupAc.setAcResultMap({
	    "maPtStckCommonDTO.filterPartGroup":"cdusrd_no"
	});
	partGroupAc.init();
	
	equipAc = new autoC({"maPtStckCommonDTO.filterEquipDesc":"description"});
	equipAc.setAcDisplay("DESCRIPTION");
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
	    "maPtStckCommonDTO.filterEquipId":"equip_id"
	});
	equipAc.init();
	
	plantAc = new autoC({"maPtStckCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPtStckCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
	
    acSysDesc("maPtStckCommonDTO.filterPtAbcClassDesc","maPtStckCommonDTO.filterPtAbcClass","PT_ABC_CLASS");
    acSysDesc("maPtStckCommonDTO.filterSaftyYN","maPtStckCommonDTO.filterSaftyYN","IS_USE");
    
    if(typeof exLoadPage == "function") exLoadPage();
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
    	maPtStckListForm.elements['maPtStckCommonDTO.partId'].value = "";
    	return sortColumn("maPtStckList", this, maPtStckListForm, "PARTID", ind, direction);
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
    
    //myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    //	maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value 	= "";
    // 	maPtStckListForm.elements['maPtStckCommonDTO.partId'].value 	= "";
    //	return sortColumn("maPtStckList", this, maPtStckListForm, ["WCODEID","PARTID"], ind, direction);
	//});
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
    var url = contextPath + "/maPtStckList.do";

    maPtStckListForm.elements['strutsAction'].value = '<%=MaPtStckListAction.PTSTCK_LIST_FIND%>';

    myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"ASTOCKQTY"),".",",");
    myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"bstockqty"),".",",");
    myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"totstockqty"),".",",");
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtStckListForm), ["WCODEID","PARTID"], "Y");
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value = "";     // 검색시 Tab 이동Key Clear
	maPtStckListForm.elements['maPtStckCommonDTO.partId'].value = "";      // 검색시 Tab 이동Key Clear
	findGridList('Search');
}
function findGridRow(wcodeId, partId)
{
 	maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value 	= wcodeId;
 	maPtStckListForm.elements['maPtStckCommonDTO.partId'].value 	= partId;
 	findGridList('ReloadRow');
 	
 	maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value 	= "";
 	maPtStckListForm.elements['maPtStckCommonDTO.partId'].value 	= "";
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
	maPtStckListForm.elements['maPtStckCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
    maPtStckListForm.elements['maPtStckCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
	
    goCommonTabPage(maPtStckListForm, <%=MaPtStckDetailAction.PTSTCK_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPtStckDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtStckListForm.elements['maPtStckCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
    maPtStckListForm.elements['maPtStckCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
    maPtStckListForm.elements['strutsAction'].value = '<%=MaPtStckDetailAction.PTSTCK_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtStckListForm), 'maPtStckDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtStckDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value = "";
	maPtStckListForm.elements['maPtStckCommonDTO.partId'].value = "";
    goCommonTabPage(maPtStckListForm, '', pageId);
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
    
    maPtStckListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_LIST_DELETE%>';
    var url = contextPath + "/maPtStckList.do";

    $.post(url,FormQueryString(maPtStckListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPtStckDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 구매신청
 */
 var selectArray;
function goBuyreq()
{
	var selArray = getSelectedRows(myGrid, 'ISDELCHECK', 'PARTID', 'UNDERSAFTYQTY');
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0052'/>", function(result){
		 if(result){
			 selectArray = selArray;
			sequenceNextVal('SQAPTPRLIST_ID');
		 }
		});
}

function setSequenceVal(sequenceVal)
{
	maPtStckListForm.elements['maPtStckCommonDTO.reqIdx'].value = sequenceVal;
	maPtStckListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_LIST_BUYREQ%>';
	var url = contextPath + "/maPtStckList.do"; 
   $.post(url,FormQueryString(maPtStckListForm)+selectArray , function(_data){
   	afterBuyreq();
   });
}
/**
 * after구매신청
 */
 function afterBuyreq()
 {
 	//구매신청 상세 팝업 띄우기
	var url   = contextPath + "/maPtBuyReqHdrDetail.do";
	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction="+'<%=MaPtStckDetailAction.PTSTCK_DETAIL_INIT%>'
    		  +"&maPtBuyReqHdrCommonDTO.ptPrListId="+ maPtStckListForm.elements['maPtStckCommonDTO.reqIdx'].value;
  
    openWindowWithPost(url, "BUYREQHDR", param, pos);
 }
 
 /**
  * 레포트 출력
  */
function goPrint()
 {
	var selArray = getSelectedRows(myGrid, 'ISDELCHECK', 'PARTID','WCODEID'); //Grid, check box column seq, pk column seq
 	
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtStckListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_QR_INSERT%>';
	var url = contextPath + "/maPtStckList.do";
	$.post(url,FormQueryString(maPtStckListForm)+selArray , function(_data){
		startReportCall();
    });
 } 
function goListbarcode()
 {
	
	maPtStckListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_QR_LIST_INSERT%>'; 
	var url = contextPath + "/maPtStckList.do";
	$.post(url,FormQueryString(maPtStckListForm) , function(_data){
		startReportCall();
    });
 } 

 function startReportCall ()
 {
	 if("<%=user.getCompNo()%>" == "140"&&loginUser.plant=='SLP'){
		 reportCall('ptBarcode_SLP','ptBarcode', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>");
	 }
	 else if("<%=user.getCompNo()%>" == "130"||loginUser.plant=='MTL'){
		 reportCall('ptBarcode_MTL','ptBarcode_MTL', "<%=user.getCompNo()%>", "<%=user.getUserId()%>");
	 }
	 else if("<%=user.getCompNo()%>" == "150"||loginUser.plant=='GA'){
		 reportCall('ptBarcode_GA','ptBarcode_GA', "<%=user.getCompNo()%>", "<%=user.getUserId()%>");
	 }else{
		 reportCall('ptBarcode','ptBarcode', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>");
	 }
 }
 
/**
 * Excel Export
 */
function goExcel()
{
	maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value 	= "";
 	maPtStckListForm.elements['maPtStckCommonDTO.partId'].value 	= "";
    excelServerAction("maPtStckList", maPtStckListForm);
    //excelAction(myGrid);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtStckListForm.elements['maPtStckCommonDTO.wcodeId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtStckList">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtStckCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.wcodeId"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.partGrade"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.filterWcodeId"/>
<html:hidden property="maPtStckCommonDTO.filterPtAbcClass"/>
<html:hidden property="maPtStckCommonDTO.filterPartGroup"/>
<html:hidden property="maPtStckCommonDTO.filterEquipId"/>
<html:hidden property="maPtStckCommonDTO.filterPlantId"/>
<html:hidden property="maPtStckCommonDTO.reqIdx"/>
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
                            <html:text property="maPtStckCommonDTO.filterWname" tabindex="10"/>
		                    <p class="open_spop">
		                        <a>
		                            <span>조회</span>
		                        </a>
		                    </p>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.partNameSize"/></label>
                       <div class="input_box">
                            <html:text property="maPtStckCommonDTO.filterPartDesc" tabindex="20"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.partNo"/></label>
                       <div class="input_box">
                            <html:text property="maPtStckCommonDTO.filterPartNo" tabindex="30"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.maker"/></label>
                       <div class="input_box">
                            <html:text property="maPtStckCommonDTO.filterMaker" tabindex="50"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="maPtStckCommonDTO.filterModel" tabindex="60"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.qtyOverCnt"/></label>
                       <div class="input_box">
                            <html:text property="maPtStckCommonDTO.filterQtyCnt" tabindex="60" styleClass="num"/>
                       </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.underSaftyQty"/></label>
                        <div class="input_box">
                           <html:text property="maPtStckCommonDTO.filterSaftyYN" tabindex="70"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div> 
                   <div class="field">
                        <label><bean:message key="LABEL.partGroup"/></label>
                        <div class="input_box">
                           <html:text property="maPtStckCommonDTO.filterPartGroupDesc" tabindex="75"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.vendorPtCode"/></label>
                       <div class="input_box">
                            <html:text property="maPtStckCommonDTO.filterVendorPtCode" tabindex="80"/>
                       </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.ptAbcClass"/></label>
                        <div class="input_box">
                           <html:text property="maPtStckCommonDTO.filterPtAbcClassDesc" tabindex="85"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <!-- 공장명  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantDesc"/></label>
	                    <div class="input_box">
								<html:text property="maPtStckCommonDTO.filterPlantDesc" tabindex="90" />
								<p class="open_spop">
	                            <a>
	                                <span>조회</span>
	                            </a>
	                        </p>
	                    </div>
	                </div>
                   <!-- 설비 -->
	                <div class="field">
	                     <label><bean:message key="LABEL.equipName"/></label>
	                     <div class="input_box">
	                        <html:text property="maPtStckCommonDTO.filterEquipDesc" tabindex="100"/>
	                         <p class="open_spop"><a><span>조회</span></a></p>
	                   </div>
	                </div>
	                <c:set var="filePath" value="enhance/${compName}/part/stk/maPtStckList_${compNo}.jsp" />
					<c:if test="${udf:isExist(filePath)}">
						<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/stk/maPtStckList_${compNo}.jsp"></c:import>
					</c:if>
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