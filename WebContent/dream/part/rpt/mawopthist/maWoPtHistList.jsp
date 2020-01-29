<%--===========================================================================
자재사용이력
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.mawopthist.action.MaWoPtHistListAction" %>
<%@ page import="dream.ass.asset.action.AssAssetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 자재사용이력 -->
<title><bean:message key="MENU.PTUSELIST"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc;
var equipDescAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var subMngAc;
var isLawEqAc;
var eqTypeDescAc;
var plantAc;
function loadPage() 
{
	initGrid();
	if(window.name=="USE_PT_LIST_POPUP" || window.name == "LINKED_POPUP"){
	}else{
		maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterStartDate'].value  = getMinusMonth2(new Date(), -1);
	    maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterEndDate'].value    = getToday();    
	    maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
	    maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
		if(loginUser.eqLocId!='null'){
			maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
		if(loginUser.eqCtgTypeId!='null'){
			maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
			maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	    if(loginUser.filterPlant!='null'){
	    	maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	maWoPtHistListForm.elements['maWoPtHistCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
	
	deptAc = new autoC({"maWoPtHistCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoPtHistCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    equipDescAc = new autoC({"maWoPtHistCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoPtHistCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoPtHistCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoPtHistCommonDTO.filterDeptId",
    	"plant" : "maWoPtHistCommonDTO.filterPlantId"
    });
    equipDescAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.init();
    
    partAc = new autoC({"maWoPtHistCommonDTO.filterPartDesc":"description"});
    partAc.setAcConditionMap({
 	   "part_categ":"SPPT",
 	   "comp_no":loginUser.compNo
 	   });
    partAc.setTable("TAPARTS");
    partAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterPartId":"part_id"
        ,"maWoPtHistCommonDTO.filterPartNo":"part_no"
    });
    partAc.init();
    
    assetAc = new autoC({"maWoPtHistCommonDTO.filterAssetDesc":"description"});
    assetAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	   });
    assetAc.setTable("TAASSET");
    assetAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterAssetId":"asset_id"
    });
    assetAc.init();
    
    eqLocDescAc = new autoC({"maWoPtHistCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoPtHistCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoPtHistCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    mainMngAc = new autoC({"maWoPtHistCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoPtHistCommonDTO.filterDeptId",
    	"plant" : "maWoPtHistCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maWoPtHistCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maWoPtHistCommonDTO.filterDeptId",
    	"plant" : "maWoPtHistCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maWoPtHistCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoPtHistCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("maWoPtHistCommonDTO.filterIsLawEq","maWoPtHistCommonDTO.filterIsLawEq","IS_USE");
    acSysDesc("maWoPtHistCommonDTO.filterEqCtgTypeDesc","maWoPtHistCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    

	/* $("input[name='maWoPtHistCommonDTO.filterPartNo']").on({
		"keyup":function(e){
			valPartNo('maWoPtHistCommonDTO.filterPartId','maWoPtHistCommonDTO.filterPartNo','maWoPtHistCommonDTO.filterPartDesc', true);
		}
	}); */
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
    });
  	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("maWoPtHistList", this, maWoPtHistListForm, "PARTID", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWoPtHistList.do";

    maWoPtHistListForm.elements['strutsAction'].value = '<%=MaWoPtHistListAction.WOPTHIST_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoPtHistListForm), "PARTID", "Y");
    
    
/*     myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maWoPtHistListForm), function(_data){
        myGrid.parse(_data,"js");
    }); */
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	findGridList('Search');
}

/**
 * Excel Export
 */
function goExcel()
{
//     excelAction(myGrid);
    excelServerAction('maWoPtHistList', maWoPtHistListForm);
}
/**
 * 구매신청
 */
 var selectArray;
function goBuyreq()
{
	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'PARTID');
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
	maWoPtHistListForm.elements['maWoPtHistCommonDTO.reqIdx'].value = sequenceVal;
	maWoPtHistListForm.strutsAction.value = '<%=MaWoPtHistListAction.WOPTHIST_LIST_BUYREQ%>';
	var url = contextPath + "/maWoPtHistList.do"; 
   $.post(url,FormQueryString(maWoPtHistListForm)+selectArray , function(_data){
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
    
    var param = "strutsAction="+ '<%=AssAssetDetailAction.DETAIL_INIT%>'
    		  +"&maPtBuyReqHdrCommonDTO.ptPrListId="+ maWoPtHistListForm.elements['maWoPtHistCommonDTO.reqIdx'].value;
  
    openWindowWithPost(url, "BUYREQHDR", param, pos);
 }
 
function goCart()
{
	var selectArray = getSelectedRows(myGrid, 'ISDELCHECK', 'WOPARTID');
	if(typeof selectArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0210'/>", function(result){
		 if(result){
			 setModal('<bean:message key="MESSAGE.MSG0211"/>');
			 maWoPtHistListForm.strutsAction.value = '<%=MaWoPtHistListAction.WOPTHIST_LIST_CART%>';
			var url = contextPath + "/maWoPtHistList.do"; 
		   $.post(url,FormQueryString(maWoPtHistListForm)+selectArray , function(_data){
		   	afterCart();
		   });
		 }
		});
}

function afterCart()
{
	closeModal();
	alertMessage1('<bean:message key="MESSAGE.MSG034"/>');
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maWoPtHistList">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoPtHistCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maWoPtHistCommonDTO.filterDeptId"/>
<html:hidden property="maWoPtHistCommonDTO.filterEquipId"/>
<html:hidden property="maWoPtHistCommonDTO.filterPartId"/>
<html:hidden property="maWoPtHistCommonDTO.filterPartNo"/>
<html:hidden property="maWoPtHistCommonDTO.filterEqLocId"/>
<html:hidden property="maWoPtHistCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoPtHistCommonDTO.filterAssetId"/>
<html:hidden property="maWoPtHistCommonDTO.filterMainMngId"/>
<html:hidden property="maWoPtHistCommonDTO.filterSubMngId"/>
<html:hidden property="maWoPtHistCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maWoPtHistCommonDTO.filterPlantId"/>
<html:hidden property="maWoPtHistCommonDTO.reqIdx"/>
	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
				<div class="sheader_wrap"><a></a></div>
				<div class="stitle_wrap">
					<div class="stitle_icon"><a></a></div>
					<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
				</div>
				<div class="function_box filter not_fold">
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
					    <label><bean:message key="LABEL.date"/></label>
						<div class="calendar_wrap">
						    <div class="input_box input_carendar">
						        <html:text property="maWoPtHistCommonDTO.filterStartDate" tabindex="10" />
						    <p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
							    <html:text property="maWoPtHistCommonDTO.filterEndDate" tabindex="20" />
							            <p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 관리부서 -->
					<div class="field">
                        <label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
						    <html:text property="maWoPtHistCommonDTO.filterDeptDesc" tabindex="30"/>
						    <p class="open_spop"><a><span>조회</span></a></p>
						</div>
                    </div>   
					<div class="field">
					    <label><bean:message key="LABEL.equipDesc"/></label>
					    <div class="input_box">
					        <html:text property="maWoPtHistCommonDTO.filterEquipDesc" tabindex="40"/>
					        <p class="open_spop">
					            <a>
					                <span>조회</span>
					            </a>
					        </p>
					    </div>
					</div>                        
					<!-- 부품코드 -->
					<div class="field">
					    <label><bean:message key="LABEL.parts"/></label>
					    <div class="input_box">
					        <html:text property="maWoPtHistCommonDTO.filterPartDesc" tabindex="40"/>
					        <p class="open_spop">
					            <a>
					                <span>조회</span>
					            </a>
					        </p>
					    </div>
					</div> 
                    <!-- 자산번호 -->
                    <div class="field">
                        <label><bean:message key="LABEL.asset"/></label>
                        <div class="input_box">
                            <html:text property="maWoPtHistCommonDTO.filterAssetDesc" tabindex="60"/>
                                        
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p> 
                        </div>
                    </div>
                    <!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="maWoPtHistCommonDTO.filterEqLocDesc" tabindex="70"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 종류 -->
					<div class="field">
						<label><bean:message key="LABEL.type"/></label>
						<div class="input_box">
							<html:text property="maWoPtHistCommonDTO.filterEqCtgDesc" tabindex="80" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 관리자(정) -->
					<div class="field">
						<label><bean:message key="LABEL.mainManager"/></label>
						<div class="input_box">
							<html:text property="maWoPtHistCommonDTO.filterMainMngName" tabindex="100"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(부) -->
					<div class="field">
						<label><bean:message key="LABEL.subManager"/></label>
						<div class="input_box">
							<html:text property="maWoPtHistCommonDTO.filterSubMngName" tabindex="110"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 법정설비여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isLawEq"/></label>
						<div class="input_box">
							<html:text property="maWoPtHistCommonDTO.filterIsLawEq" tabindex="90" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 설비유형  -->
					<div class="field">
						<label><bean:message key="LABEL.eqCtgType"/></label>
						<div class="input_box">
							<html:text property="maWoPtHistCommonDTO.filterEqCtgTypeDesc" tabindex="100" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 공장명  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantDesc"/></label>
	                    <div class="input_box">
							<html:text property="maWoPtHistCommonDTO.filterPlantDesc" />
							<p class="open_spop"><a><span>조회</span></a></p>
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