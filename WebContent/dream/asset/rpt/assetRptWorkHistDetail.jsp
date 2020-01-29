<%--===========================================================================
설비이력(과거) Detail
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.action.AssetRptWorkHistDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 설비이력(과거) -->
<title><bean:message key='MENU.EQHIST' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var equipDescAc;
var deptAc;
var empAc;
var eqAsmbAc;

function loadPage() 
{
	var wkOrId = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.wkOrId'].value;
    if(wkOrId != "undefined" && wkOrId != "") {
        setDisableAll();
        showBtn('wo');
    }
	
    setTitle("assetRptWorkHistDetailDTO.itemNo", "assetRptWorkHistDetailDTO.equipName");
    
    //For Save
    setForUpdate();
    
    //설비 자동완성
    equipDescAc = new autoC({"assetRptWorkHistDetailDTO.equipName":"description"});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "assetRptWorkHistDetailDTO.itemNo":"item_no",
        "assetRptWorkHistDetailDTO.eqlocDesc":"eqLocDesc",
        "assetRptWorkHistDetailDTO.eqType":"eqCtgDesc"
        
    });
    equipDescAc.setKeyName("assetRptWorkHistDetailDTO.itemNo"); 
    equipDescAc.init();
    
    //담당부서 자동완성
    deptAc = new autoC({"assetRptWorkHistDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    deptAc.setAcResultMap({
        "assetRptWorkHistDetailDTO.deptId":"dept_id"
    });
    deptAc.setKeyName("assetRptWorkHistDetailDTO.deptId"); 
    deptAc.init();
    
	//작업종류 AC
    acSysDesc("assetRptWorkHistDetailDTO.woType","assetRptWorkHistDetailDTO.woTypeId","WO_TYPE",true);
    
	$("input[name='assetRptWorkHistDetailDTO.startDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='assetRptWorkHistDetailDTO.startTime']").blur( function(){
		setWorkTime();
    });
	$("input[name='assetRptWorkHistDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='assetRptWorkHistDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEQHISTORY_ID');
    
    setInitVal("assetRptWorkHistDetailDTO.eqHistGenType", "MANUAL");
    
    hideBtn('wo');
    
    equipDescAc.openLov();
    
}
function setSequenceVal(sequenceVal)
{
    assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.eqHistoryId'].value = sequenceVal;
    assetRptWorkHistDetailForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
}

/**
 * 저장
 */ 
function goSave()
{
	setWorkTime();
    //================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) assetRptWorkHistDetailForm.strutsAction.value = "<%=AssetRptWorkHistDetailAction.DETAIL_INPUT%>";
    else assetRptWorkHistDetailForm.strutsAction.value = "<%=AssetRptWorkHistDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/assetRptWorkHistDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assetRptWorkHistDetailForm),'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)
		parent
				.findGridRow(assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.eqHistoryId'].value);

	assetRptWorkHistDetailForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.eqHistoryId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("assetRptWorkHistDetailDTO.itemNo",
			"assetRptWorkHistDetailDTO.equipName");

}

/**
 * 탭페이지
 */
function goTabPage(pageId)
{
    var form = document.assetRptWorkHistDetailForm;
    
    if(pageId == "assetRptWorkHistLibList")
    {   
    	assetRptWorkHistDetailForm.elements['maDocLibCommonDTO.description'].value = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.description'].value; 
    	assetRptWorkHistDetailForm.elements['maDocLibCommonDTO.securGrade'].value = '3'; // 3등급 기본세팅 
    	assetRptWorkHistDetailForm.elements['maDocLibCommonDTO.objectId'].value = assetRptWorkHistDetailForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value;
    	assetRptWorkHistDetailForm.elements['maDocLibCommonDTO.objectType'].value = 'EQHIST';
        
        
        goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
    }
    else
    goCommonTabPage(form, '' , pageId);
}

/**
 * 상세 열기
 */
function goWo(){
	
	var wkOrId = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.wkOrId'].value;
	var param = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.woParam'].value;
    
    if(wkOrId == "undefined" || wkOrId == "") {
        alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
        return;
    }
	
	var url   = contextPath + "/"+param+".do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&maWoResultMstrCommonDTO.wkOrId="+ wkOrId;
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
}
function setWorkTime(){
	var startDate = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.startDate'].value;
 	var startTime = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.startTime'].value;
 	var endDate = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.endDate'].value;
 	var endTime = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.endTime'].value;

 	if(startDate != "" && startTime != "" && endDate != "" && endTime != "")
 	{
		var sDate = new Date();
		sDate.setFullYear(startDate.substring(0,4));
		sDate.setMonth(Number(startDate.substring(5,7)) -1);
		sDate.setDate(startDate.substring(8,10));
		sDate.setHours(startTime.substring(0,2));
		sDate.setMinutes(startTime.substring(3,5));
		var eDate = new Date();
		eDate.setFullYear(endDate.substring(0,4));
		eDate.setMonth(Number(endDate.substring(5,7)) -1);
		eDate.setDate(endDate.substring(8,10));
		eDate.setHours(endTime.substring(0,2));
		eDate.setMinutes(Number(endTime.substring(3,5))+1);
		var caleDate = new Date();
		caleDate.setFullYear(endDate.substring(0,4));
		caleDate.setMonth(Number(endDate.substring(5,7)) -1);
		caleDate.setDate(endDate.substring(8,10));
		caleDate.setHours(endTime.substring(0,2));
		caleDate.setMinutes(Number(endTime.substring(3,5)));
		
		//종료일자가 시작일자보다 큰경우 입력 불가
		if(sDate>caleDate)
		{
			assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.endTime'].value = "";
    		alertMessage1("<bean:message key='MESSAGE.MSG0110' />");
    		assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.endTime'].focus();
    		return;
		}
		else
		{
			
			var tempTime = Math.floor((eDate.getTime() - sDate.getTime())/60000);
			
			assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.workTime'].value = setNumberFormat(tempTime);
		}
		

 	}
 }
 
/**
 * Time 세팅되고 호출되는 Callback
 */
function afterSetTime(_name)
{
	setWorkTime();
}

/**
 * Date 세팅되고 호출되는 Callback
 */
function afterSetDate(_name)
{
	setWorkTime();
}

function goAudtrailLink()
{
	var objectId = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.eqHistoryId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


/**
 * 작업결과 Linked
 */
function goWoLink(){
	
	goWo();
}
/*
 * 설비 Linked
 */
function goMachequipmentLink(){
	
	var equipId = assetRptWorkHistDetailForm.elements['assetRptWorkHistDetailDTO.equipId'].value;
    if(equipId == "undefined" || equipId == "") {
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }
	
	var url   = contextPath + "/maEqMstrDetail.do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&maEqMstrCommonDTO.equipId="+ equipId;
  
    openWindowWithPost(url, "EQ_DETAIL", param, pos);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/assetRptWorkHistDetail">
	<html:hidden property="strutsAction" />
	<html:hidden property="currentPageId"/>
	<html:hidden property="assetRptWorkHistCommonDTO.eqHistoryId" />	<!-- Key -->
	<html:hidden property="assetRptWorkHistDetailDTO.eqHistoryId" />	<!-- Key -->
	<html:hidden property="assetRptWorkHistDetailDTO.itemNo" />
	<html:hidden property="assetRptWorkHistDetailDTO.deptId" />
	<html:hidden property="assetRptWorkHistDetailDTO.empId" />
	<html:hidden property="assetRptWorkHistDetailDTO.woTypeId" />
	<html:hidden property="assetRptWorkHistDetailDTO.wkOrId" />
	<html:hidden property="assetRptWorkHistDetailDTO.woParam" />
	<html:hidden property="assetRptWorkHistDetailDTO.eqHistGenType" />
	<html:hidden property="assetRptWorkHistDetailDTO.equipId" />
		
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
    <html:hidden property="maDocLibCommonDTO.objectType" />
    <html:hidden property="maDocLibCommonDTO.description" />
    <html:hidden property="maDocLibCommonDTO.securGrade" /> 
	
		
		<div class="article_box">
			<div class="form_box">
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box" id="equipNameDiv">
						<html:text property="assetRptWorkHistDetailDTO.equipName" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 부위 -->
				<div class="field">
					<label><bean:message key="LABEL.asmb"/></label>
					<div class="input_box" id="asmbDescDiv">
						<html:text property="assetRptWorkHistDetailDTO.eqAsmbDesc" tabindex="60" />
					</div>
				</div>
				<!-- 설비위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_read" id="eqlocDescDiv">
						<html:text property="assetRptWorkHistDetailDTO.eqlocDesc" tabindex="60" readonly="true"/>
					</div>
				</div>
				<!-- 설비종류 -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_read" id="eqTypeDiv">
						<html:text property="assetRptWorkHistDetailDTO.eqType" tabindex="60" readonly="true"/>
					</div>
				</div>
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.workDate"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.wkorDate" tabindex="80" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 작업종류 -->
				<div class="field">
					<label><bean:message key="LABEL.woTypeDesc"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.woType" tabindex="30" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 작업명(제목) -->
				<div class="field_long">
					<label><bean:message key="LABEL.pmDesc"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.description" tabindex="70" />
					</div>
				</div>
				<!-- 담당부서 -->
				<div class="field">
					<label><bean:message key="LABEL.manageDept"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.deptDesc" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.empName" tabindex="110"/>
					</div>
				</div>
				<!-- 거래처 -->
				<div class="field">
					<label><bean:message key="LABEL.vendor"/></label>
					<div class="input_box">
	                    <html:text property="assetRptWorkHistDetailDTO.vendorName" tabindex="100" />
	                </div>
				</div>
				<!-- 금액 -->
				<div class="field" id="amtDiv">
					<label><bean:message key="LABEL.amt"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.totAmt" tabindex="105" styleClass="num"/>
					</div>
				</div>
				<!-- 작업상세내용 -->
				<div class="field_long">
					<label><bean:message key="LABEL.woPerform"/></label>
					<div class="input_box">
						<html:textarea property="assetRptWorkHistDetailDTO.perform" styleClass="ta50" tabindex="110" />
					</div>
				</div>
				<!-- 고장원인 -->
				<div class="field">
					<label><bean:message key="LABEL.caCd"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.caDesc" tabindex="130"/>
					</div>
				</div>
				<!-- 고장조치 -->
				<div class="field">
					<label><bean:message key="LABEL.reCd"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.reDesc" tabindex="140"/>
					</div>
				</div>
				<!-- 작업시작시간 -->
				<div class="field">
					<label><bean:message key="LABEL.woFromTime"/></label>
					<div class="datetime_wrap">
						<div class="input_box input_carendar">
							<html:text property="assetRptWorkHistDetailDTO.startDate"  tabindex="150" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box">
							<html:text property="assetRptWorkHistDetailDTO.startTime" tabindex="152"/>
							<p class="open_time"><span>시간</span></p>
						</div>
					</div>
				</div>
				<!-- 작업종료시간-->
				<div class="field">
					<label><bean:message key="LABEL.woToTime"/></label>
					<div class="datetime_wrap">
						<div class="input_box input_carendar">
							<html:text property="assetRptWorkHistDetailDTO.endDate" tabindex="165" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box">
							<html:text property="assetRptWorkHistDetailDTO.endTime" tabindex="166" />
							<p class="open_time"><span>시간</span></p>
						</div>
					</div>
				</div>
				<!-- 작업시간(분) -->
				<div class="field">
					<label><bean:message key="LABEL.woTimeMin"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistDetailDTO.workTime" tabindex="177" styleClass="num"/>
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
