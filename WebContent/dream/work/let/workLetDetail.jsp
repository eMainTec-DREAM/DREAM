<%--===========================================================================
안전작업 - 상세
author  syyang
version $Id: workLetDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.work.let.action.WorkLetDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- WO # -->
<title><bean:message key='LABEL.woNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;

/** 자동완성 변수  */
var reqByAc;
var reqDeptAc;
var recByAc;
var recDeptAc;
var plantAc;
var woNoAc;

function loadPage() 
{	
	setTitle("workLetDetailDTO.woLetNo","workLetDetailDTO.woLetDesc");
	
	workLetDetailForm.elements['workLetDetailDTO.woLetStatusDesc'].readOnly="true";
	
	setForUpdate();
	
	$("input[name='workLetDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='workLetDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });
	
	//신청담당자
    reqByAc = new autoC({"workLetDetailDTO.reqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_join":"Y"
  	   });
    reqByAc.setTable("TAEMP");
    reqByAc.setKeyName("workLetDetailDTO.reqById");
    reqByAc.setAcResultMap({
        "workLetDetailDTO.reqById":"emp_id"
        ,"workLetDetailDTO.reqDeptId":"dept_id"
        ,"workLetDetailDTO.reqDeptDesc":"deptDesc"
    });
    reqByAc.init();
    
    //신청부서
    reqDeptAc = new autoC({"workLetDetailDTO.reqDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setKeyName("workLetDetailDTO.reqDeptId");
    reqDeptAc.setAcResultMap({
        "workLetDetailDTO.reqDeptId":"dept_id"
    });
    reqDeptAc.init();

	//허가자
    recByAc = new autoC({"workLetDetailDTO.recByDesc":"emp_name"});
    recByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_join":"Y"
  	   });
    recByAc.setTable("TAEMP");
    recByAc.setKeyName("workLetDetailDTO.recById");
    recByAc.setAcResultMap({
        "workLetDetailDTO.recById":"emp_id"
        ,"workLetDetailDTO.recDeptId":"dept_id"
        ,"workLetDetailDTO.recDeptDesc":"deptDesc"
    });
    recByAc.init();
    
    //허가부서
    recDept = new autoC({"workLetDetailDTO.recDeptDesc":"description"});
    recDept.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    recDept.setTable("TADEPT");
    recDept.setKeyName("workLetDetailDTO.recDeptId");
    recDept.setAcResultMap({
        "workLetDetailDTO.recDeptId":"dept_id"
    });
    recDept.init();

    //공장
    plantAc = new autoC({"workLetDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workLetDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    // W/O#
    woNoAc = new autoC({"workLetDetailDTO.woNo":"woNo"});
    woNoAc.setTable("TAWORKORDER");
    woNoAc.setAcResultMap({
        "workLetDetailDTO.wkOrId":"wkorId"
    }); 
    woNoAc.setKeyName("workLetDetailDTO.wkorId"); 
    woNoAc.init();
    
	if(ckCreate(currentPageId)){
		goInput();
	}
	else 
	{
		goUpdate();
	}
	
}

/**
 * Show/Hide Button
 */
function setBtnAsStatus()
{
	if(workLetDetailForm.elements['workLetDetailDTO.woLetStatus'].value=="COM"){
		setDisableAll();
		hideBtn("CONFIRM");
		
		showBtn("REVERSE");
	}
	else{
		setEnableAll();
		showBtn("CONFIRM");
		
		hideBtn("REVERSE");
	}
}

function goUpdate()
{
	setBtnAsStatus();
}

function goInput()
{
	sequenceNextVal('SQAWOLET_ID');
	
	//완료버튼 감추기.
	hideBtn("CONFIRM");
	hideBtn("PRINT");
	
	//작업상태 = WRK - 작석중
	workLetDetailForm.elements['workLetDetailDTO.woLetStatusDesc'].value = "WRK";
	valSysDir('workLetDetailDTO.woLetStatus', 'workLetDetailDTO.woLetStatusDesc', 'WOLET_STATUS', true);

	//작업기간(From, To) 넣기.
	workLetDetailForm.elements['workLetDetailDTO.startDate'].value   = getToday();
	workLetDetailForm.elements['workLetDetailDTO.endDate'].value   = getToday();

	//작업시간 From(1시간전), To(현재시간) 넣기.
	workLetDetailForm.elements['workLetDetailDTO.startTime'].value   = getMinusTime(false,1);
	workLetDetailForm.elements['workLetDetailDTO.endTime'].value   = getTime(false);

    //공장명
    if(loginUser.plant!='null' && loginUser.plant!=''){
    	workLetDetailForm.elements['workLetDetailDTO.plant'].value  = loginUser.plant;
    	workLetDetailForm.elements['workLetDetailDTO.plantDesc'].value  = loginUser.plantDesc;
	}
	setWorkTime();
    
}

function setSequenceVal(sequenceVal)
{
	workLetDetailForm.elements['workLetDetailDTO.woLetNo'].value = sequenceVal;
	workLetDetailForm.elements['workLetDetailDTO.woLetId'].value = sequenceVal;
	workLetDetailForm.elements['workLetCommonDTO.woLetId'].value = sequenceVal;

}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.workLetDetailForm;
	
	goCommonTabPage(form, '' , pageId);
    
}

/**
 * 작업완료
 */
function goConfirm()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0017'/>", function(result){
			if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				//================================
				// 필수 확정항목 체크한다.
				//================================
				if(checkConfirmValidation()) return;
				
				//작업시간이 미래라면 완료 처리 안됨
				var todayStr = getToday().split("-").join("");
				var startDate = workLetDetailForm.elements['workLetDetailDTO.startDate'].value;
				startDate = startDate.split("-").join("");
				var endDate = workLetDetailForm.elements['workLetDetailDTO.endDate'].value;
				endDate = endDate.split("-").join("");
				
				if(todayStr < startDate||todayStr < endDate)
				{
					alertMessage1("<bean:message key='MESSAGE.MSG0034'/>");
					return;
				}
				
				workLetDetailForm.elements['workLetDetailDTO.letDate'].value = getToday(); 
				workLetDetailForm.elements['workLetDetailDTO.updTime'].value = getNowDateTime(true); 
				workLetDetailForm.strutsAction.value = '<%=WorkLetDetailAction.WO_LET_DETAIL_COMPLETE%>';
				var actionUrl = contextPath + "/workLetDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(workLetDetailForm), 'afterConfirm');
			}
		});
	}
}
/**
 * 완료후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
	alertMessage1("<bean:message key='MESSAGE.MSG0015'/>");
	
	//안전작업상태 = COM - 작업완료
	workLetDetailForm.elements['workLetDetailDTO.woLetStatus'].value = "COM";
	valSysDirCode('workLetDetailDTO.woLetStatus', 'workLetDetailDTO.woLetStatusDesc', 'WOLET_STATUS', true);
	
	//확정자명 = 로그인 유저
	workLetDetailForm.elements['workLetDetailDTO.letByDesc'].value = "<%=user.getEmpName()%>";
	if (parent.findGridRow)	parent.findGridRow(workLetDetailForm.elements['workLetDetailDTO.woLetId'].value);
	 
	setBtnAsStatus();
}
 
/**
 * 작업완료 취소
 */
function goReverse()
{
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0231'/>", function(result){
		 if(result){
		    var url = contextPath + "/workLetDetail.do";
		    workLetDetailForm.strutsAction.value = '<%=WorkLetDetailAction.WO_LET_DETAIL_COMPLETE_CANCEL%>';
		    XMLHttpPost(url, FormQueryString(workLetDetailForm), 'afterReverse');
		 }
	});
}
/**
 * 완료 취소후 호출
 */
function afterReverse(ajaxXmlDoc)
{
 	alertMessage1("<bean:message key='MESSAGE.CMSG034'/>");
 	
	if (parent.findGridRow)	parent.findGridRow(workLetDetailForm.elements['workLetDetailDTO.woLetId'].value);
	if (parent.goTabPage)	parent.goTabPage(currentPageId);
	
	setUpdateStatus(parseXmlDoc(ajaxXmlDoc, 'DESC'));
	
	setBtnAsStatus();
}

/**
 * 저장
 */ 
function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	var nowDateTime = getNowDateTime(true);
 	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) {
		workLetDetailForm.elements['workLetDetailDTO.creTime'].value = nowDateTime; 
		workLetDetailForm.elements['workLetDetailDTO.updTime'].value = nowDateTime; 

		workLetDetailForm.strutsAction.value = '<%=WorkLetDetailAction.WO_LET_DETAIL_INPUT%>';
	}
	else {
		workLetDetailForm.elements['workLetDetailDTO.updTime'].value = nowDateTime; 
		
		workLetDetailForm.strutsAction.value = '<%=WorkLetDetailAction.WO_LET_DETAIL_UPDATE%>';
	}
	
	if(M$('workLetDetailDTO.workTime').value == '') setWorkTime();
	
	
	var actionUrl = contextPath + "/workLetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workLetDetailForm), 'afterSave');
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

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	workLetDetailForm.elements['workLetDetailDTO.woLetId'].value = workLetDetailForm.elements['workLetCommonDTO.woLetId'].value;
	
	if (parent.findGridRow)	parent.findGridRow(workLetDetailForm.elements['workLetDetailDTO.woLetId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
	
	setBtnAsStatus();
}
 
function setWorkTime()
{
	var startDate = workLetDetailForm.elements['workLetDetailDTO.startDate'].value;
 	var startTime = workLetDetailForm.elements['workLetDetailDTO.startTime'].value;
 	var endDate = workLetDetailForm.elements['workLetDetailDTO.endDate'].value;
 	var endTime = workLetDetailForm.elements['workLetDetailDTO.endTime'].value;

 	workLetDetailForm.elements['workLetDetailDTO.workTime'].value = setNumberFormat(getMinInterval(workLetDetailForm.elements['workLetDetailDTO.startDate'], workLetDetailForm.elements['workLetDetailDTO.startTime'], workLetDetailForm.elements['workLetDetailDTO.endDate'],workLetDetailForm.elements['workLetDetailDTO.endTime']));
 }
 

function setUpdateStatus(_resStatus)
{
	workLetDetailForm.elements['workLetDetailDTO.woLetStatus'].value   = _resStatus;
	workLetDetailForm.elements['workLetDetailDTO.woLetStatusDesc'].value = _resStatus;
	valSysDir('workLetDetailDTO.woLetStatus', 'workLetDetailDTO.woLetStatusDesc', 'WOLET_STATUS', true);
	
	if (parent.findGridRow)	parent.findGridRow(workLetDetailForm.elements['workLetDetailDTO.woLetId'].value);
}

/**
 * Print 버튼 클릭
 */
function goPrint()
{

}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = workLetDetailForm.elements['workLetDetailDTO.woLetId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

  
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workLetDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="workLetCommonDTO.woLetId" />
	<html:hidden property="workLetDetailDTO.woLetId" />
	<html:hidden property="workLetDetailDTO.woLetStatus" />
 	<html:hidden property="workLetDetailDTO.recById" />
	<html:hidden property="workLetDetailDTO.recDeptId" />
	<html:hidden property="workLetDetailDTO.reqById" />
	<html:hidden property="workLetDetailDTO.reqDeptId" />
	<html:hidden property="workLetDetailDTO.plant" />
	<html:hidden property="workLetDetailDTO.workTime" />
	<html:hidden property="workLetDetailDTO.wkOrId" />
	<html:hidden property="workLetDetailDTO.letDate" />
	<html:hidden property="workLetDetailDTO.letBy" />
 	
	<div class="article_box">
		<div class="form_box">
			<!-- 안전작업 No -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woLetNo"/></label>
				<div class="input_read" id="woNoDiv">
					<html:text property="workLetDetailDTO.woLetNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_read" id="woStatusDescDiv">
					<html:text property="workLetDetailDTO.woLetStatusDesc" tabindex="20" />
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woLetName"/></label>
				<div class="input_box">
					<html:text property="workLetDetailDTO.woLetDesc" tabindex="30" />
				</div>
			</div>
			<!-- 설비/기기 -->
			<div class="field">
				<label><bean:message key="LABEL.itemDesc"/></label>
				<div class="input_box">
                    <html:text property="workLetDetailDTO.itemDesc" tabindex="40" />
                </div>
			</div>
			<!-- 작업장소 -->
			<div class="field">
				<label><bean:message key="LABEL.woLocation"/></label>
				<div class="input_box">
                    <html:text property="workLetDetailDTO.place" tabindex="50" />
                </div>
			</div>
			<!-- 작업기간 From -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woPeriodFrom"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workLetDetailDTO.startDate"  tabindex="60" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workLetDetailDTO.startTime"   tabindex="70"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업기간 To -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woPeriodTo"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workLetDetailDTO.endDate" tabindex="80" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workLetDetailDTO.endTime" tabindex="90" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 신청담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.reqBy"/></label>
				<div class="input_box">
					<html:text property="workLetDetailDTO.reqByDesc" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 신청당부서 -->
			<div class="field">
				<label><bean:message key="LABEL.reqDept"/></label>
				<div class="input_box">
					<html:text property="workLetDetailDTO.reqDeptDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 허가자 -->
			<div class="field">
				<label><bean:message key="LABEL.letBy"/></label>
				<div class="input_box">
					<html:text property="workLetDetailDTO.recByDesc" tabindex="120"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 허가부서 -->
			<div class="field">
				<label><bean:message key="LABEL.letDept"/></label>
				<div class="input_box">
					<html:text property="workLetDetailDTO.recDeptDesc" tabindex="130"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공장 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="workLetDetailDTO.plantDesc" tabindex="140"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 안전작업 No -->
			<div class="field">
				<label><bean:message key="LABEL.woNo"/></label>
				<div class="input_box">
					<html:text property="workLetDetailDTO.woNo" tabindex="150"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workLetDetailDTO.remark" styleClass="ta50" tabindex="200" />
				</div>
			</div>
			
			<!-- 확정자 -->
			<div class="field">
				<label><bean:message key="LABEL.closeBy"/></label>
				<div class="input_read" >
					<html:text property="workLetDetailDTO.letByDesc" tabindex="185" readonly="true" />
				</div>
			</div>
			<!-- 생성시간 -->
			<div class="field">
				<label><bean:message key="LABEL.createDate"/></label>
				<div class="input_read" >
					<html:text property="workLetDetailDTO.creTime" tabindex="187" readonly="true"/>
				</div>
			</div>
			<!-- 수정시간 -->
			<div class="field">
				<label><bean:message key="LABEL.updateDate"/></label>
				<div class="input_read" >
					<html:text property="workLetDetailDTO.updTime" tabindex="188" readonly="true"/>
				</div>
			</div>
			
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>