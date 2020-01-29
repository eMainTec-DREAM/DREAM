<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="mobile.dream.work.pm.list.action.WorkPmWorkDetailAction"%>
<head>
<title>Dream Mobile</title>
<meta name="decorator" content="mobileTabPage">

</head>

<body>
<html:form action="/workPmWorkDetail.do">
<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
<html:hidden property="workPmWorkCommonDTO.wkorId"/>
<html:hidden property="workPmWorkDetailDTO.equipId"/>
<html:hidden property="workPmWorkDetailDTO.woTypeId"/>
<html:hidden property="workPmWorkDetailDTO.pmTypeId"/>
<html:hidden property="workPmWorkDetailDTO.empId"/>
<html:hidden property="workPmWorkDetailDTO.woStatusId"/>
<html:hidden property="workPmWorkDetailDTO.deptId"/>


<html:hidden property="workPmWorkDetailDTO.workTime"/>
    	    <div class="form_wrap">
		      
		      
		      <div class="field ty_srch">
		        <div class="btn b_srch"><a><span>Search</span></a></div>
		        <div class="input_tx">
		        	<html:text property="workPmWorkDetailDTO.equipDesc" tabindex="10" />
		        </div>
		        <div class="btn b_barcode"><a><span>Barcode</span></a></div>
		      </div>
		      <div class="field ty_readonly">
		        <div class="input_tx">
		        	<html:text property="workPmWorkDetailDTO.eqLocDesc" tabindex="20" readonly="true"/>
		        </div>
		      </div>
		      <!-- 작업명 -->
		      <div class="field">
		        <label><bean:message key="LABEL.woName"/></label>
		        <div class="input_tx">
		        	<html:text property="workPmWorkDetailDTO.wkOrDesc" tabindex="30"/>
		        </div>
		      </div>
		      <!-- 작업종류 -->
		      <div class="field ty_srch">
		        <label><bean:message key="LABEL.woType"/></label>
		        <div class="btn b_srch"><a><span>Search</span></a></div>
		        <div class="input_tx">
		        	<html:text property="workPmWorkDetailDTO.woTypeDesc" tabindex="40"/>
		        </div>
		      </div>
		      <!-- 작업형태 -->
		      <div class="field ty_srch">
		        <label><bean:message key="LABEL.pmType"/></label>
		        <div class="btn b_srch"><a><span>Search</span></a></div>
		        <div class="input_tx">
		        	<html:text property="workPmWorkDetailDTO.pmTypeDesc" tabindex="50"/>
		        </div>
		      </div>
		      <div class="field ty_time">
		        <label><bean:message key="LABEL.pmDate"/></label>
		        <div class="btn b_date"><a><span>날짜</span></a></div>
		        <div class="input_tx">
		        	<html:text styleClass="datatx" property="workPmWorkDetailDTO.wkorDate" tabindex="60" />
		        </div>
		      </div>
		      <div class="field ty_readonly">
		      	<label><bean:message key="LABEL.manager"/></label>
		        <div class="input_tx">
		        	<html:text property="workPmWorkDetailDTO.empDesc" tabindex="70" readonly="true"/>
		        </div>
		      </div>
		      <!-- 작업내용 -->
		      <div class="field">
				<label><bean:message key="LABEL.woRemark"/></label>
				<div class="input_tx">
					<html:textarea property="workPmWorkDetailDTO.perform" tabindex="80" />
				</div>
			  </div>
		      
		      <div class="field ty_time">
		        <label><bean:message key="LABEL.workTime"/></label>
		        <div class="btn b_date"><a><span>날짜</span></a></div>
		        <div class="input_tx">
		        	<html:text styleClass="datatx" property="workPmWorkDetailDTO.startDate" tabindex="90" />
		        </div>
		        <div class="btn b_time"><a><span>시각</span></a></div>
		        <div class="input_tx">
		        	<html:text styleClass="timetx" property="workPmWorkDetailDTO.startTime" tabindex="100" />
		        </div>
		      </div>
		      <div class="field ty_time">
		        <div class="btn b_date"><a><span>날짜</span></a></div>
		        <div class="input_tx">
		        	<html:text styleClass="datatx" property="workPmWorkDetailDTO.endDate" tabindex="110" />
		        </div>
		        <div class="btn b_time"><a><span>시각</span></a></div>
		        <div class="input_tx">
		        	<html:text styleClass="timetx" property="workPmWorkDetailDTO.endTime" tabindex="120" />
		        </div>
		      </div>
		      
</html:form>	
<script type="text/javascript">
var myList;
function loadPage()
{
	
	
	alert($('[name="strutsAction"]').val());
	if(ckCreate(currentPageId)){
		goInput();
	}
	else 
	{
		goUpdate();
	}

	setForUpdate();
}

function goUpdate()
{
	
}

function setWorkTime(){
	var startDate = workPmWorkDetailForm.elements['workPmWorkDetailDTO.startDate'].value;
 	var startTime = workPmWorkDetailForm.elements['workPmWorkDetailDTO.startTime'].value;
 	var endDate = workPmWorkDetailForm.elements['workPmWorkDetailDTO.endDate'].value;
 	var endTime = workPmWorkDetailForm.elements['workPmWorkDetailDTO.endTime'].value;

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
			workPmWorkDetailForm.elements['workPmWorkDetailDTO.endTime'].value = "";
    		alertMessage1("<bean:message key='MESSAGE.MSG0110' />");
    		workPmWorkDetailForm.elements['workPmWorkDetailDTO.endTime'].focus();
    		return;
		}
		else
		{
			
			var tempTime = Math.floor((eDate.getTime() - sDate.getTime())/60000);
			
			workPmWorkDetailForm.elements['workPmWorkDetailDTO.workTime'].value = setNumberFormat(tempTime);
		}
		
 	}
}

function goInput()
{
	//작업상태 = P - 작업대기
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.woStatusId'].value = "P";
	
	//작업시작일자, 종료일자 넣기.
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.startDate'].value   = getToday();
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.endDate'].value   = getToday();
	//작업일자 넣기
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.wkorDate'].value   = getToday();
	//작업시작시간(1시간전), 종료시간(현재시간) 넣기.
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.startTime'].value   = getMinusTime(false,1);
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.endTime'].value   = getTime(false);

	//부서
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.deptId'].value    = loginUser.getDeptId;
	//workPmWorkDetailForm.elements['workPmWorkDetailDTO.deptDesc'].value  = loginUser.getDeptDesc;
	//사원
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.empId'].value     = loginUser.empId;
	workPmWorkDetailForm.elements['workPmWorkDetailDTO.empDesc'].value   = loginUser.empName;

	setWorkTime();

	sequenceNextVal('SQAWKOR_ID');
}

function goSave(_page)
{
	if(ckCreate(currentPageId)) workPmWorkDetailForm.strutsAction.value = '<%=WorkPmWorkDetailAction.PM_WORK_DETAIL_INPUT%>';
	else workPmWorkDetailForm.strutsAction.value = '<%=WorkPmWorkDetailAction.PM_WORK_DETAIL_UPDATE%>';
	
	setWorkTime()
	
	var actionUrl = contextPath + "/workPmWorkDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmWorkDetailForm), 'afterSave');
}

//작업종류 LOV

</script>
</body>
