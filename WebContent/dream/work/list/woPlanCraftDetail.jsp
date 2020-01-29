<%--===========================================================================
작업계획목록 - 작업자
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WoPlanCraftDetailAction"%>
<html>
<head>
<!-- 작업자-->
<title><bean:message key="TAB.maWoResultCraftList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var mainMngAc;
function loadPage() 
{
	
	setTitle("woPlanCraftDetailDTO.empNo","woPlanCraftDetailDTO.empDesc");
	setForUpdate();
	
	$("input[name='woPlanCraftDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='woPlanCraftDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });
	
	mainMngAc = new autoC({"woPlanCraftDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("woPlanCraftDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "woPlanCraftDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
	if(ckCreate(currentPageId)) goInput();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOCRAFT_ID');
	//날짜,시간값이 비어있으면 작업상세의 값을 넣는다.
	var dateTimes = parent.getDateTime();
	dateTimes = dateTimes.split(",");
	if(woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startDate'].value=='') 
		woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startDate'].value = dateTimes[0];
	if(woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startTime'].value=='') 
		woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startTime'].value = dateTimes[1];
	if(woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endDate'].value=='') 
		woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endDate'].value = dateTimes[2];
	if(woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endTime'].value=='') 
		woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endTime'].value = dateTimes[3];
	
	setWorkTime();
	
	mainMngAc.openLov();
	
	
}

function setSequenceVal(sequenceVal)
{
	woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.woCraftId'].value = sequenceVal;
}
function workTimeCheck(){
	var detailWorkTime = parent.getWorkTime();
	var craftWorkTime = woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.workTime'].value;
	if($('[name="woPlanCraftDetailDTO.workTime"]').parent().parent().attr('class').indexOf('hidden') > -1){
		return true;
	}
	if(Number(craftWorkTime)>Number(detailWorkTime)){
		return false;
	}else{
		return true;
	}
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	if(checkRequireValue('woPlanCraftDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
	if(ckCreate(currentPageId)){
		//중복 작업자 체크
		validEmp();
		if(isValid!='0') return;
	}
	if(!workTimeCheck()){
		closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0111'/>");
		return;
	}
	if(ckCreate(currentPageId)) woPlanCraftDetailForm.strutsAction.value = '<%=WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INPUT%>';
	else woPlanCraftDetailForm.strutsAction.value = '<%= WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_UPDATE %>';
	
	if(M$('woPlanCraftDetailDTO.workTime').value == '') setWorkTime();
	
	var actionUrl = contextPath + "/woPlanCraftDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(woPlanCraftDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.woCraftId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function validEmp(){
	var actionUrl = contextPath + "/woPlanCraftDetail.do";
	var param =  "&strutsAction=" + '<%= WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_CHECK %>'
	          +  "&" + FormQueryString(woPlanCraftDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidEmp');
}

var isValid;
function afterValidEmp(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0035'/>");
		woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.empId'].value = '';
		woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.empDesc'].value = '';
    }
}

function setWorkTime(){
	var startDate = woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startDate'].value;
 	var startTime = woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startTime'].value;
 	var endDate = woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endDate'].value;
 	var endTime = woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endTime'].value;
 	
	woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.workTime'].value = setNumberFormat(getMinInterval(woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startDate'], woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.startTime'], woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endDate'],woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.endTime']));
			
 }

/* audit Trail */
function goAudtrailLink()
{
	var objectId = woPlanCraftDetailForm.elements['woPlanCraftDetailDTO.woCraftId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/woPlanCraftDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="woPlanCommonDTO.wkOrId"/>
	<html:hidden property="woPlanCraftDetailDTO.woCraftId"/>
	<html:hidden property="woPlanCraftDetailDTO.empNo"/>
	<html:hidden property="woPlanCraftDetailDTO.empId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 작업자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woCraft"/></label>
				<div class="input_box">
					<html:text property="woPlanCraftDetailDTO.empDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 소요시간 -->
			<div class="field">
				<label><bean:message key="LABEL.woTimeMin"/></label>
				<div class="input_box">
					<html:text property="woPlanCraftDetailDTO.workTime" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 작업시작시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woFromTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="woPlanCraftDetailDTO.startDate"  tabindex="30" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="woPlanCraftDetailDTO.startTime"   tabindex="40"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업종료시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woToTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="woPlanCraftDetailDTO.endDate" tabindex="50" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="woPlanCraftDetailDTO.endTime" tabindex="60" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="woPlanCraftDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>