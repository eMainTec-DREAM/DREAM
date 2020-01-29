<%--===========================================================================
설비 정기작업 상세
author  kim21017
version $Id: maEqMstrPmWorkDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.list.action.MaEqMstrPmWorkDetailAction"%>
<html>
<head> 
<!--예방작업-->
<title><bean:message key="TAB.maEqMstrPmWorkDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var wrkCalListAc;
var deptAc;
var wkCtrAc;
var empAc;
var plantAc;
var lnWrkListAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maEqMstrPmWorkDetailDTO.pmNo","maEqMstrPmWorkDetailDTO.pmTypeDesc");
	setForUpdate();
	
	// 작업달력
    wrkCalListAc = new autoC({"maEqMstrPmWorkDetailDTO.wrkCalListDesc":"description"});
    wrkCalListAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wrkCalListAc.setTable("TAWRKCALLIST");
    wrkCalListAc.setKeyName("maEqMstrPmWorkDetailDTO.wrkCalListId");
    wrkCalListAc.setAcResultMap({
        "maEqMstrPmWorkDetailDTO.wrkCalListId":"wrkcallist_id"
    });
    wrkCalListAc.init();
    
    // 부서
    deptAc = new autoC({"maEqMstrPmWorkDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maEqMstrPmWorkDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maEqMstrPmWorkDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    //작업그룹
	wkCtrAc = new autoC({"maEqMstrPmWorkDetailDTO.wkCtrDesc":"description"});
	wkCtrAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	wkCtrAc.setTable("TAWKCTR");
	wkCtrAc.setKeyName("maEqMstrPmWorkDetailDTO.wkCtrId");
	wkCtrAc.setAcResultMap({
	    "maEqMstrPmWorkDetailDTO.wkCtrId":"wkctr_id"
	});
	wkCtrAc.init();
	
	//담당자
	empAc = new autoC({"maEqMstrPmWorkDetailDTO.empDesc":"emp_name"});
	empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
	empAc.setTable("TAEMP");
	empAc.setKeyName("maEqMstrPmWorkDetailDTO.empId");
	empAc.setAcResultMap({
        "maEqMstrPmWorkDetailDTO.empId":"emp_id"
    });
	empAc.init();
	
	//공장명
	plantAc = new autoC({"maEqMstrPmWorkDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maEqMstrPmWorkDetailDTO.plantId");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maEqMstrPmWorkDetailDTO.plantId":"plant"
    });
    plantAc.init();
	
	//가동달력
	lnWrkListAc = new autoC({"maEqMstrPmWorkDetailDTO.lnWrkListDesc":"description"});
	lnWrkListAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	lnWrkListAc.setTable("TALNWRKLIST");
	lnWrkListAc.setKeyName("maEqMstrPmWorkDetailDTO.lnWrkListId");
	lnWrkListAc.setAcResultMap({
	    "maEqMstrPmWorkDetailDTO.lnWrkListId":"lnWrkListId"
	});
	lnWrkListAc.init();
	
	acSysDesc("maEqMstrPmWorkDetailDTO.periodTypeDesc","maEqMstrPmWorkDetailDTO.periodTypeId","PERIOD_TYPE",true);
	acSysDesc("maEqMstrPmWorkDetailDTO.isActiveDesc","maEqMstrPmWorkDetailDTO.isActiveId","IS_USE",true);
	
	acSysDesc("maEqMstrPmWorkDetailDTO.scheduleTypeDesc","maEqMstrPmWorkDetailDTO.scheduleTypeId","SCHEDULE_TYPE",true);
	
	//일정생성방법 hidden control
    checkScheduleType($("input[name='maEqMstrPmWorkDetailDTO.scheduleTypeId']").val());
}

/*
 * 일정생성방법 hidden control
 */
function checkScheduleType(scheduleType)
{
	console.log(scheduleType);
	var cycleObj 		= "maEqMstrPmWorkDetailDTO.cycle";
	var periodTypeObj	= "maEqMstrPmWorkDetailDTO.periodTypeDesc";
	var woResBefObj		= "maEqMstrPmWorkDetailDTO.woResBef";
	var wrkcalListObj	= "maEqMstrPmWorkDetailDTO.wrkCalListDesc";
	var usageObj 		= "maEqMstrPmWorkDetailDTO.usage";
	var lnWrkListObj	= "maEqMstrPmWorkDetailDTO.lnWrkListDesc";
	
	if(scheduleType == 'T') 
	{
		///숨김
		hideField(usageObj);
		hideField(lnWrkListObj);
		
		//보임
		showField(cycleObj);
		showField(wrkcalListObj);
		
		maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.lnWrkListDesc'].value = '';
		maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.lnWrkListId'].value = '';
		maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.usage'].value = '';
		
	}
	else if(scheduleType == 'R' || scheduleType == 'U') 
	{
		///숨김
		hideField(cycleObj);
		hideField(wrkcalListObj);
		
		//보임
		showField(usageObj);
		showField(lnWrkListObj);

		maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.woResBef'].value = '';
		maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.cycle'].value = '';
		maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.periodTypeDesc'].value = '';
		maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.periodTypeId'].value = '';
		
	}
}

function afterAutoCmpt(code, rtnJsonArry)
{
	if(code == "maEqMstrPmWorkDetailDTO.scheduleTypeDesc") {
		checkScheduleType(rtnJsonArry[0].CDSYSD_NO);
	}
}


function goInput(){
	// 시퀀스를 조회한다.
	
	sequenceNextVal('SQAPM_ID');
	
	//wo_type = PMW 초기화
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.woTypeId'].value = "PMW";
	
	//list에서 goCreate할 때 선택된 pm_type 세팅
	var selectedPmType = maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkListDTO.selectedPmType'].value;
	//작업형태 
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.pmTypeDesc'].value = selectedPmType;
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.pmTypeId'].value = selectedPmType;
	valSysDir('maEqMstrPmWorkDetailDTO.pmTypeId', 'maEqMstrPmWorkDetailDTO.pmTypeDesc', 'PMW_TYPE', true);
	
	//시행여부 세팅
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.isActiveId'].value = 'Y';
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.isActiveDesc'].value = 'Y';
	
	//부서정보 세팅
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.deptId'].value = loginUser.deptId;
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.deptDesc'].value = loginUser.deptDesc;
	
	//가동량/시간 = T - 시간
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.scheduleTypeDesc'].value = "T"; 
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.scheduleTypeId'].value = "T"; 
	valSysDir('maEqMstrPmWorkDetailDTO.scheduleTypeId', 'maEqMstrPmWorkDetailDTO.scheduleTypeDesc', 'SCHEDULE_TYPE', true);
	
}

function setSequenceVal(sequenceVal)
{
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.pmId'].value = sequenceVal;
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.pmNo'].value = sequenceVal;
	sequenceNextVal2('SQAPMEQUIP_ID');
}

function setSequenceVal2(sequenceVal)
{
	
	maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.pmEquipId'].value = sequenceVal;
}
function goSave(){
	if(checkValidation()) return;
	
	pmSaveAction();
}

function pmSaveAction(){
	if(ckCreate(currentPageId)) maEqMstrPmWorkDetailForm.strutsAction.value = '<%=MaEqMstrPmWorkDetailAction.EQ_MSTR_PMWORK_DETAIL_INPUT%>';
	else maEqMstrPmWorkDetailForm.strutsAction.value = '<%= MaEqMstrPmWorkDetailAction.EQ_MSTR_PMWORK_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrPmWorkDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrPmWorkDetailForm), 'afterSave');
	
}
function goTabPage(pageId)
{
	var form = document.maEqMstrPmWorkDetailForm;
	goCommonTabPage(form, '' , pageId);
}
function afterSave(data)
{
	//=====================================
    if (!checkHttpXml(data)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.pmId'].value
    		,maEqMstrPmWorkDetailForm.elements['maEqMstrPmWorkDetailDTO.pmEquipId'].value);
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrPmWorkDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maEqMstrCommonDTO.equipId"/>
	<html:hidden property="maEqMstrPmWorkListDTO.selectedPmType"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.pmId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.pmEquipId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.woTypeId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.pmTypeId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.periodTypeId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.isActiveId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.deptId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.wkCtrId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.empId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.wrkCalListId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.oldCycle"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.oldPeriodTypeId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.oldInitWrkDate"/>
	
	<html:hidden property="maEqMstrPmWorkDetailDTO.plantId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.scheduleTypeId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.lnWrkListId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			
			<!-- 예방작업번호 -->
			<div class="field">
				<label><bean:message key="LABEL.pmNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPmWorkDetailDTO.pmNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 작업형태 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmType"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPmWorkDetailDTO.pmTypeDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.description" tabindex="30"/>
				</div>
			</div>
			<!-- 일정생성방법 -->
			<div class="field">
				<label>일정생성방법</label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.scheduleTypeDesc" tabindex="35"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공장명 -->
			<div class="field">
				<label>공장명</label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.plantDesc" tabindex="38"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 최초작업시작일 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.initWrkDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.initWrkDate" tabindex="40" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 주기 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.cycleDesc"/></label>
				<div class="datetime_wrap">
					<div class="input_box">
						<html:text property="maEqMstrPmWorkDetailDTO.cycle" tabindex="50" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maEqMstrPmWorkDetailDTO.periodTypeDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			</div>
			<!--몇일전작업생성 -->
			<div class="field">
				<label>몇일전작업생성</label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.woResBef" tabindex="65" styleClass="num"/>
				</div>
			</div>
			<!--가동시간 -->
			<div class="field">
				<label>가동시간</label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.usage" tabindex="68" styleClass="num"/>
				</div>
			</div>
			<!--가동달력 -->
			<div class="field">
				<label>가동달력</label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.lnWrkListDesc" tabindex="69" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!--근무달력 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.wrkCalListDesc" tabindex="70" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.deptDesc"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.deptDesc" tabindex="80" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.wkCtrDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.empDesc" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!--예상작업시간(분) -->
			<div class="field">
				<label>예상작업시간(분)</label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.predWoTimeMin" tabindex="105" styleClass="num"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkDetailDTO.isActiveDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqMstrPmWorkDetailDTO.remark" styleClass="ta50" tabindex="120" />
				</div>
			</div>
			
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>