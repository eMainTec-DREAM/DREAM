<%--===========================================================================
재고검색 AC LOV Popup
author  ghlee
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
<%@ page import="dream.part.stk.action.LovPtStckListAction"%>
<html>
<head>
<!-- 재고검색 -->
<title><bean:message key="LABEL.partSearch"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var wareHouseAc;
var partNameAc;
var partAc;

var conditionFilterMap = {
	"wcode_id":"lovPtStckListDTO.filterWId"
	,"wname":"lovPtStckListDTO.filterWDesc"
	,"part_id":"lovPtStckListDTO.filterPartId"
	,"part_no":"lovPtStckListDTO.filterPartNo"
	,"model":"lovPtStckListDTO.filterModel"
	,"stock_qty":"lovPtStckListDTO.filterQtyCnt"
};

function loadPage() 
{
	convertCondition();
	
	initGrid();
	
    // 창고 자동완성
    wareHouseAc = new autoC({"lovPtStckListDTO.filterWDesc":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_use":"Y"
    });
    wareHouseAc.setAcDConditionMap({
    });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "lovPtStckListDTO.filterWId":"wcode_id"
    });
    wareHouseAc.init();
    
    partNameAc = new autoC({"lovPtStckListDTO.filterPartDesc":"full_desc"});
	partNameAc.setAcConditionMap({
	    "part_categ":"SPPT",
	    "comp_no":loginUser.compNo
	});
	partNameAc.setAcDConditionMap({
	    "wcode_id":"lovPtStckListDTO.filterWId"
	});
	partNameAc.setTable("TAPARTS");
	partNameAc.init();
	
	partAc = new autoC({"lovPtStckListDTO.filterPartNo":"part_no"});
	partAc.setAcConditionMap({
	    "part_categ":"SPPT",
	    "comp_no":loginUser.compNo
	});
	partAc.setAcDConditionMap({
	    "wcode_id":"lovPtStckListDTO.filterWId",
	    "wcode_desc":"lovPtStckListDTO.filterWDesc"
	});
	partAc.setTable("TAPARTS");
	partAc.setAcResultMap({
        "lovPtStckListDTO.filterPartId":"part_id"
    });
	partAc.init();
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
	
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("lovPtStckAcList", this, lovPtStckListForm, "PART_ID", ind, direction);
    });
    
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovPtStckAcList"); // grid, grid id
}

function convertCondition()
{
	var paramObj = JSON.parse(M$('param').value);
	for(var key in paramObj){
		if(typeof conditionFilterMap[key] != "undefined" && typeof M$(conditionFilterMap[key]) == "object")
		{
			M$(conditionFilterMap[key]).value = paramObj[key];
			paramObj[key] = '';
			M$('param').value = JSON.stringify(paramObj);
		}
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	lovPtStckListForm.elements['strutsAction'].value = '<%=LovPtStckListAction.LOV_AC_FIND%>';
	var url = contextPath + "/lovPtStckAcList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovPtStckListForm), "PART_ID","Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인d
 */
function goConfirm()
{	
	setAcValue(myGrid, "PART_ID");
}

/**
 * 검색
 */
function goSearch()
{
	findGridList('Search');
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
	<html:form action="/lovPtStckAcList" >
		<html:hidden property="currentPageId"/>
		<html:hidden property="keyCode" /><html:hidden property="chName" />
		<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
		<html:hidden property="codeType" />
		<html:hidden property="param" />
		<html:hidden property="label" />
		<html:hidden property="title" />
		<html:hidden property="lovPtStckListDTO.filterWId" />
		<html:hidden property="lovPtStckListDTO.filterPartId" />
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
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<!-- 창고명 -->
                    <div class="field">
						<label><bean:message key="LABEL.wname"/></label>
						<div class="input_box">
							<input type='text' name="lovPtStckListDTO.filterWDesc" tabindex="10"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<div class="field">
						<!-- 품명/규격 -->
						<label><bean:message key="LABEL.ptNameSize"/></label>
						<div class="input_box">
							<input type='text' name="lovPtStckListDTO.filterPartDesc" tabindex="20"/>
						</div>
					</div>
					<div class="field">
						<!-- 부품번호 -->
						<label><bean:message key="LABEL.partNo"/></label>
						<div class="input_box">
							<input type='text' name="lovPtStckListDTO.filterPartNo" tabindex="30"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 업체품번 -->
					<div class="field">
                       <label><bean:message key="LABEL.vendorPtCode"/></label>
                       <div class="input_box">
                            <html:text property="lovPtStckListDTO.filterVendorPtCode" tabindex="40"/>
                       </div>
                   </div>
				   <!-- 모델 -->
                   <div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="lovPtStckListDTO.filterModel" tabindex="50"/>
                       </div>
                   </div>
				   <!-- 총재고 이상수량 -->
                   <div class="field">
                       <label><bean:message key="LABEL.qtyOverCnt"/></label>
                       <div class="input_box">
                            <html:text property="lovPtStckListDTO.filterQtyCnt" tabindex="60" styleClass="num"/>
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