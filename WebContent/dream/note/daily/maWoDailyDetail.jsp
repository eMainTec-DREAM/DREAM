<%--===========================================================================
 - 상세
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.note.daily.action.MaWoDailyDetailAction"%>
<%@ page import="dream.note.daily.action.MaWoDailyImageListAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html:html>
<head>
<title><bean:message key='LABEL.woNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var deptAc, plantAc, equipDescAc, wkCtrAc;

function loadPage() 
{
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maWoDailyDetailDTO.woDayListNo", "maWoDailyDetailDTO.title");
	
	setForUpdate();
	if("<%=loginUser.getCompNo()%>" == "140"&&loginUser.plant=='SLP'){
		
	}else if("<%=loginUser.getCompNo()%>" == "150"&&loginUser.plant=='GA'){
		
	}else{
// 		goTabPage("maAppPrcList");
	}
	
	deptAc = new autoC({"maWoDailyDetailDTO.woDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoDailyDetailDTO.plant"
    });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoDailyDetailDTO.woDeptId":"dept_id"
    });
    deptAc.setKeyName("maWoDailyDetailDTO.woDeptId");
    deptAc.init();
    
    plantAc = new autoC({"maWoDailyDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maWoDailyDetailDTO.plant");
    plantAc.setAcResultMap({
        "maWoDailyDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    //설비
    equipDescAc = new autoC({"maWoDailyDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "eq_status":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setKeyName("maWoDailyDetailDTO.equipId");
    equipDescAc.setAcResultMap({
        "maWoDailyDetailDTO.equipId":"equip_id"
    });
    equipDescAc.setKeyName("maWoDailyDetailDTO.equipId"); 
    equipDescAc.init();
    
    //작업그룹
    wkCtrAc = new autoC({"maWoDailyDetailDTO.wkCtrDesc":"description"});
    wkCtrAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setKeyName("maWoDailyDetailDTO.wkCtrId");
    wkCtrAc.setAcResultMap({
        "maWoDailyDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrAc.init();
	
    
    setBtnAsStatus();
}
	
function setBtnAsStatus()
{
	var wodaylistStatus = maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListStatus'].value;
	
	if(wodaylistStatus == "C" || wodaylistStatus == "P" || wodaylistStatus == "R"){
		setDisableAll();
		hideBtn("CONFIRM");
		hideBtn("APPROVAL");
		hideBtn("SAVE");

	}
	else
	{
		setEnableAll();
		showBtn("CONFIRM");
		showBtn("APPROVAL");
		showBtn("SAVE");
	}
}

function goTabPage(pageId)
{
	var form = document.maWoDailyDetailForm;
	
	if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		form.elements['appReqCommonDTO.objectId'].value = form.elements['maWoDailyDetailDTO.woDayListId'].value;
		form.elements['appReqCommonDTO.apprType'].value = "WODAY";
		goCommonTabPage(form, '' , pageId);
	}
	else if(pageId == "maDocLibList" || pageId == "maWoDailyLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['maWoDailyDetailDTO.woDayListId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "WODAY";  
		form.elements['maDocLibCommonDTO.description'].value = 
			form.elements['maWoDailyDetailDTO.title'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maWoDailyImageList") {
		goCommonTabPage(form, '<%=MaWoDailyImageListAction.DATA_FIND%>' , pageId);
	}
	else{
		goCommonTabPage(form, '' , pageId);
	}
}

function goInput()
{
	sequenceNextVal('SQAWODAYLIST_ID');
	
	M$('maWoDailyDetailDTO.updById').value = loginUser.empId;
	M$('maWoDailyDetailDTO.updByDesc').value = loginUser.empName+"/"+loginUser.deptDesc;
	
	M$('maWoDailyDetailDTO.plant').value = loginUser.plant;
	M$('maWoDailyDetailDTO.plantDesc').value = loginUser.plantDesc;
	
	// 작업그룹
	if(!'undefined'==loginUser.wkCtrId  && !'undefined'==loginUser.wkCtrDesc )
	{
		M$('maWoDailyDetailDTO.wkCtrId').value = loginUser.wkCtrId;
		M$('maWoDailyDetailDTO.wkCtrDesc').value = loginUser.wkCtrDesc;
	}
	
	M$('maWoDailyDetailDTO.woDayListStatus').value = "W";
	M$('maWoDailyDetailDTO.woDayListStatusDesc').value = "W";
	valSysDir('maWoDailyDetailDTO.woDayListStatus', 'maWoDailyDetailDTO.woDayListStatusDesc', 'WODAYLIST_STATUS', true);
	
	M$('maWoDailyDetailDTO.woDate').value = getToday();
	
	M$('maWoDailyDetailDTO.woDeptId').value = loginUser.deptId;
	M$('maWoDailyDetailDTO.woDeptDesc').value = loginUser.deptDesc;
	
	
	var baseTime = '<%=MwareConfig.getWorkStartBaseTime()%>';
	if(baseTime.indexOf(":") < 0)
		baseTime = baseTime.substr(0,2)+':'+baseTime.substr(2,2);
	
	M$('maWoDailyDetailDTO.startFdate').value = getMinusDay(1);
	M$('maWoDailyDetailDTO.startFtime').value = baseTime;
	M$('maWoDailyDetailDTO.startEdate').value = getToday();
	M$('maWoDailyDetailDTO.startEtime').value = baseTime;
	
}

function setSequenceVal(sequenceVal)
{
	maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value = sequenceVal;
	maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListNo'].value = sequenceVal;
}

function goUpdate()
{
	//setDisableAll();
	/* if(maWoDailyDetailForm.elements['maWoDailyDetailDTO.pmRepMethodType'].value=='WO'){
		setDisableAll();
	}else{
		setEnableAll();
	} */
		
// 	if (parent.findGridRow) parent.findGridRow(maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value);
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
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maWoDailyDetailForm.strutsAction.value = '<%=MaWoDailyDetailAction.WO_DAILY_DETAIL_INPUT%>';
	else maWoDailyDetailForm.strutsAction.value = '<%=MaWoDailyDetailAction.WO_DAILY_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maWoDailyDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoDailyDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maWoDailyDetailForm.elements['maWoDailyCommonDTO.woDayListId'].value = maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	goTabPage("maWoDailyWoList");
	goTabPage("maWoDailyPmiList");
	goTabPage("maAppPrcList");
	goTabPage("maWoDailyImageList");
	goTabPage("maWoDailyLibList");
	
	setBtnAsStatus();
}

 
 function goApproval()
 {
	 if(checkIsUpdate(document)){
  		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
  	 }else{
  		if(checkValidation()) return;
  		
  		var woDayListId = maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value;
  		 
  		 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
  		 appAction(woDayListId, "WODAY");
  	 }
	
 }
 
 function appAction(objectId, apprType)
 {
	 var param = "strutsAction="+strutsActionApproval + 
		        "&appReqCommonDTO.objectId="+objectId +
	 			"&appReqCommonDTO.apprType=" + apprType;
	  
	 openLayerPopup("appReqDetail",param);
 }
 
 function afterApproval()
 {
	if (parent.findGridRow)	parent.findGridRow(maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value);
	goClose('maWoDailyDetail');
 }

 /**
  * Print 버튼 클릭
  */
 function goPrint()
 {
 	
 	var url   = contextPath + "/maWoDailyDetail.do";
 	var param = "maWoDailyDetailDTO.woDayListId="+ maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value;
 	
 	openPrintView(url, param);
 }
 function goWodailypdf()
 {
	 if("<%=loginUser.getCompNo()%>" == "150"&&loginUser.plant=='GA'){
		 reportCall('gaDailyShiftReport','gaDailyShiftReport', "<%=loginUser.getCompNo()%>", "<%=loginUser.getUserId()%>",maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDate'].value); 
	}else{
		var woDate = M$('maWoDailyDetailDTO.woDate').value==''?"000000":M$('maWoDailyDetailDTO.woDate').value.replace(/-/g,'');
		var startDate = (M$('maWoDailyDetailDTO.startFdate').value==''?"000000":M$('maWoDailyDetailDTO.startFdate').value.replace(/-/g,''))+(M$('maWoDailyDetailDTO.startFtime').value==''?"0000":M$('maWoDailyDetailDTO.startFtime').value.replace(/:/g,''));
		var endDate = (M$('maWoDailyDetailDTO.startEdate').value==''?"000000":M$('maWoDailyDetailDTO.startEdate').value.replace(/-/g,''))+(M$('maWoDailyDetailDTO.startEtime').value==''?"0000":M$('maWoDailyDetailDTO.startEtime').value.replace(/:/g,''));
		reportCall('maWoDailyDetail','maWoDailyDetail', '<%=loginUser.getCompNo()%>'
													  , M$('maWoDailyCommonDTO.woDayListId').value
													  , woDate
													  , startDate
													  , endDate
													  , M$('maWoDailyDetailDTO.woDeptId').value
													  , M$('maWoDailyDetailDTO.plant').value
													  , M$('maWoDailyDetailDTO.wkCtrId').value);
// 		goPrint();
	}
 }
 
 /*
  * 확정
  */
 function goConfirm()
 {
 	if(checkIsUpdate(document)){
 		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
 	 }else{
 		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG045'/>", function(result){
 			 if(result){
 				//================================
 				// 필수 항목 체크한다.
 				//================================
 				if(checkValidation()) return;
 				//================================
 				// 필수 확정항목 체크한다.
 				//================================
 				if(checkConfirmValidation()) return;
 				
 				 maWoDailyDetailForm.strutsAction.value = '<%=MaWoDailyDetailAction.WO_DAILY_DETAIL_COMPLETE%>';
 				 
 				 var actionUrl = contextPath + "/maWoDailyDetail.do";
 					XMLHttpPost(actionUrl, FormQueryString(maWoDailyDetailForm), 'afterConfirm');
 						}
 			});
 	 }
 }

 /**
  * 확정후 호출
  */
 function afterConfirm(ajaxXmlDoc)
 {
 	//=====================================
 	if (!checkHttpXml(ajaxXmlDoc)) return;
 	//=====================================
 		
 	if (parent.findGridRow)	parent.findGridRow(maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value);
 	
 	maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListStatus'].value = "C";
 	valSysDirCode('maWoDailyDetailDTO.woDayListStatus', 'maWoDailyDetailDTO.woDayListStatusDesc', 'WODAYLIST_STATUS', true);
 	getTopPage().afterSaveAll(currentPageId);
 	
 	setBtnAsStatus();
 }
 
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maWoDailyDetailForm.elements['maWoDailyDetailDTO.woDayListId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
 
 function goPrintLink()
 {
	 goPrint();
 }
 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maWoDailyDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>

	<html:hidden property="maWoDailyCommonDTO.woDayListId"/>
	
	<html:hidden property="maWoDailyDetailDTO.woDayListId"/>
	<html:hidden property="maWoDailyDetailDTO.woDayListStatus"/>
	<html:hidden property="maWoDailyDetailDTO.woDeptId"/>
	<html:hidden property="maWoDailyDetailDTO.updById"/>
	<html:hidden property="maWoDailyDetailDTO.plant"/>
	<html:hidden property="maWoDailyDetailDTO.equipId"/>
	<html:hidden property="maWoDailyDetailDTO.wkCtrId"/>
	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
			<!-- 작업# -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workNo"/></label>
				<div class="input_read">
					<html:text property="maWoDailyDetailDTO.woDayListNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_read">
					<html:text property="maWoDailyDetailDTO.woDayListStatusDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 제목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="maWoDailyDetailDTO.title" tabindex="30"/>
				</div>
			</div>
			<!-- 확인일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woDate"/></label>
				<div class="input_box">
					<html:text property="maWoDailyDetailDTO.woDate" tabindex="40"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 작업부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workDept"/></label>
				<div class="input_box">
					<html:text property="maWoDailyDetailDTO.woDeptDesc" tabindex="50"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업시작시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woFromTime"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoDailyDetailDTO.startFdate" tabindex="60"/>
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoDailyDetailDTO.startFtime" tabindex="70"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업종료시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woToTime"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoDailyDetailDTO.startEdate" tabindex="80"/>
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoDailyDetailDTO.startEtime" tabindex="90"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작성자 -->
			<div class="field">
				<label><bean:message key="LABEL.updBy"/></label>
				<div class="input_read">
					<html:text property="maWoDailyDetailDTO.updByDesc" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 공장 -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maWoDailyDetailDTO.plantDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label>설비</label>
				<div class="input_box">
					<html:text property="maWoDailyDetailDTO.equipDesc" tabindex="115"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label>작업그룹</label>
				<div class="input_box">
					<html:text property="maWoDailyDetailDTO.wkCtrDesc" tabindex="117"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maWoDailyDetailDTO.remark" styleClass="ta50" tabindex="120"/>
				</div>
			</div>
						
			<c:set var="filePath" value="enhance/${compName}/note/daily/maWoDailyDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/note/daily/maWoDailyDetail_${compNo}.jsp"></c:import>
			</c:if>
		
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>