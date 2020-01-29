<%--===========================================================================
작업결과(교육) 작업자
author  kim21017
version $Id: maWoResultTrEleCraftDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.MaWoResultCraftDetailAction"%>
<html>
<head>
<!-- 교육자-->
<title><bean:message key="TAB.maWoResultTrEleCraftList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var mainMngAc;
function loadPage() 
{
	setTitle("maWoResultCraftDetailDTO.empNo","maWoResultCraftDetailDTO.empDesc");
	setForUpdate();
	
	$("input[name='maWoResultCraftDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='maWoResultCraftDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });
	
	mainMngAc = new autoC({"maWoResultCraftDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
       	,"dept_id": parent.parent.M$('maWoResultMstrDetailDTO.deptId').value
       	,"deptDesc": parent.parent.M$('maWoResultMstrDetailDTO.deptDesc').value
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maWoResultCraftDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "maWoResultCraftDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
	if(ckCreate(currentPageId)) goInput();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOCRAFT_ID');
	
	//날짜,시간값이 비어있으면 작업상세의 값을 넣는다.
	setInitVal('maWoResultCraftDetailDTO.startDate', parent.parent.M$('maWoResultMstrDetailDTO.startDate').value);
	setInitVal('maWoResultCraftDetailDTO.startTime', parent.parent.M$('maWoResultMstrDetailDTO.startTime').value);
	setInitVal('maWoResultCraftDetailDTO.endDate', parent.parent.M$('maWoResultMstrDetailDTO.endDate').value);
	setInitVal('maWoResultCraftDetailDTO.endTime', parent.parent.M$('maWoResultMstrDetailDTO.endTime').value);
	
	setWorkTime();
	
	mainMngAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.woCraftId'].value = sequenceVal;
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	vTime();

	if(isValid!='0') return;
	
	if(M$('maWoResultCraftDetailDTO.workTime').value == '') setWorkTime();
	
	if(ckCreate(currentPageId)) maWoResultCraftDetailForm.strutsAction.value = '<%=MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INPUT%>';
	else maWoResultCraftDetailForm.strutsAction.value = '<%= MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maWoResultCraftDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoResultCraftDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.woCraftId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function vTime(){

	var actionUrl = contextPath + "/maWoResultCraftDetail.do";
	var param =  "&strutsAction=" + '<%= MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_TIME_CHECK %>'
	          +  "&" + FormQueryString(maWoResultCraftDetailForm);
	XMLHttpPostVal(actionUrl, param, 'aftervTime');
}


var isValid = '0';
function aftervTime(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(isValid == '1')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0233'/>");
		maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.startTime'].value = '';
		maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.endTime'].value = '';
		
		return;
		
	} else if(isValid == '2') {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0111'/>");
		maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.startTime'].value = '';
		maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.endTime'].value = '';
		
		return;
    }
	
}

function setWorkTime(){
	var startDate = maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.startDate'].value;
 	var startTime = maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.startTime'].value;
 	var endDate = maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.endDate'].value;
 	var endTime = maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.endTime'].value;
 	
	maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.workTime'].value = setNumberFormat(getMinInterval(maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.startDate'], maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.startTime'], maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.endDate'],maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.endTime']));
}


/* audit Trail */
function goAudtrailLink()
{ 	
	var objectId = maWoResultCraftDetailForm.elements['maWoResultCraftDetailDTO.woCraftId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
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
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoResultCraftDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="maWoResultCraftDetailDTO.woCraftId"/>
	<html:hidden property="maWoResultCraftDetailDTO.empNo"/>
	<html:hidden property="maWoResultCraftDetailDTO.empId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 교육자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.trainUser"/></label>
				<div class="input_box">
					<html:text property="maWoResultCraftDetailDTO.empDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field_blank"></div>
			<!-- 교육시작시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.trainStartTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultCraftDetailDTO.startDate"  tabindex="20" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultCraftDetailDTO.startTime"   tabindex="30"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<div class="field_blank"></div>
			<!-- 교육종료시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.trainEndTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultCraftDetailDTO.endDate" tabindex="40" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultCraftDetailDTO.endTime" tabindex="50" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 교육시간 -->
			<div class="field">
				<label><bean:message key="LABEL.trainTimeMin"/></label>
				<div class="input_box">
					<html:text property="maWoResultCraftDetailDTO.workTime" tabindex="60" styleClass="num"/>
				</div>
			</div>
			
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultCraftDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>