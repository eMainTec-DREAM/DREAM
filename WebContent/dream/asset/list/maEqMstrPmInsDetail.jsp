<%--===========================================================================
설비 정기점검 상세
author  kim21017
version $Id: maEqMstrPmInsDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.list.action.MaEqMstrPmInsDetailAction"%>
<html>
<head>
<!--예방점검-->
<title><bean:message key="TAB.maEqMstrPmInsDetail"/></title>
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
	maEqMstrPmInsDetailForm.elements['maPmMstrCommonDTO.pmId'].value
		= maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmId'].value;
	
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maEqMstrPmInsDetailDTO.pmNo","maEqMstrPmInsDetailDTO.pmTypeDesc");
	setForUpdate();
	
	// 작업달력
    wrkCalListAc = new autoC({"maEqMstrPmInsDetailDTO.wrkCalListDesc":"description"});
    wrkCalListAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wrkCalListAc.setTable("TAWRKCALLIST");
    wrkCalListAc.setKeyName("maEqMstrPmInsDetailDTO.wrkCalListId");
    wrkCalListAc.setAcResultMap({
        "maEqMstrPmInsDetailDTO.wrkCalListId":"wrkcallist_id"
    });
    wrkCalListAc.init();
    
    // 부서
    deptAc = new autoC({"maEqMstrPmInsDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maEqMstrPmInsDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maEqMstrPmInsDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    //작업그룹
	wkCtrAc = new autoC({"maEqMstrPmInsDetailDTO.wkCtrDesc":"description"});
	wkCtrAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	wkCtrAc.setTable("TAWKCTR");
	wkCtrAc.setKeyName("maEqMstrPmInsDetailDTO.wkCtrId");
	wkCtrAc.setAcResultMap({
	    "maEqMstrPmInsDetailDTO.wkCtrId":"wkctr_id"
	});
	wkCtrAc.init();
	
	//담당자
	empAc = new autoC({"maEqMstrPmInsDetailDTO.empDesc":"emp_name"});
	empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
	empAc.setTable("TAEMP");
	empAc.setKeyName("maEqMstrPmInsDetailDTO.empId");
	empAc.setAcResultMap({
        "maEqMstrPmInsDetailDTO.empId":"emp_id"
    });
	empAc.init();

	//공장명
	plantAc = new autoC({"maEqMstrPmInsDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maEqMstrPmInsDetailDTO.plantId");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maEqMstrPmInsDetailDTO.plantId":"plant"
    });
    plantAc.init();
	
	//가동달력
	lnWrkListAc = new autoC({"maEqMstrPmInsDetailDTO.lnWrkListDesc":"description"});
	lnWrkListAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	lnWrkListAc.setTable("TALNWRKLIST");
	lnWrkListAc.setKeyName("maEqMstrPmInsDetailDTO.lnWrkListId");
	lnWrkListAc.setAcResultMap({
	    "maEqMstrPmInsDetailDTO.lnWrkListId":"lnWrkListId"
	});
	lnWrkListAc.init();
	
	acSysDesc("maEqMstrPmInsDetailDTO.periodTypeDesc","maEqMstrPmInsDetailDTO.periodTypeId","PERIOD_TYPE",true);
	acSysDesc("maEqMstrPmInsDetailDTO.isActiveDesc","maEqMstrPmInsDetailDTO.isActiveId","IS_USE",true);

	acSysDesc("maEqMstrPmInsDetailDTO.scheduleTypeDesc","maEqMstrPmInsDetailDTO.scheduleTypeId","SCHEDULE_TYPE",true);
	
	//일정생성방법 hidden control
    checkScheduleType($("input[name='maEqMstrPmInsDetailDTO.scheduleTypeId']").val());
}

/*
 * 일정생성방법 hidden control
 */
function checkScheduleType(scheduleType)
{
	var cycleObj 		= "maEqMstrPmInsDetailDTO.cycle";
	var periodTypeObj	= "maEqMstrPmInsDetailDTO.periodTypeDesc";
	var woResBefObj		= "maEqMstrPmInsDetailDTO.woResBef";
	var wrkcalListObj	= "maEqMstrPmInsDetailDTO.wrkCalListDesc";
	var usageObj 		= "maEqMstrPmInsDetailDTO.usage";
	var lnWrkListObj	= "maEqMstrPmInsDetailDTO.lnWrkListDesc";
	
	if(scheduleType == 'T') 
	{
		//보임
		showField(cycleObj);
		showField(wrkcalListObj);
		
		///숨김
		hideField(usageObj);
		hideField(lnWrkListObj);
		
		maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.lnWrkListDesc'].value = '';
		maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.lnWrkListId'].value = '';
		maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.usage'].value = '';
		
	}
	else if(scheduleType == 'R' || scheduleType == 'U') 
	{
		//보임
		showField(usageObj);
		showField(lnWrkListObj);
		
		///숨김
		hideField(cycleObj);
		hideField(wrkcalListObj);

		maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.woResBef'].value = '';
		maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.cycle'].value = '';
		maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.periodTypeDesc'].value = '';
		maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.periodTypeId'].value = '';
		
	}
}

function afterAutoCmpt(code, rtnJsonArry)
{
	if(code == "maEqMstrPmInsDetailDTO.scheduleTypeDesc") {
		checkScheduleType(rtnJsonArry[0].CDSYSD_NO);
	}
}

function goInput(){
	// 시퀀스를 조회한다.
	
	sequenceNextVal('SQAPM_ID');
	
	//wo_type = PMI 초기화
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.woTypeId'].value = "PMI";
	
	//list에서 goCreate할 때 선택된 pm_type 세팅
	var selectedPmType = maEqMstrPmInsDetailForm.elements['maEqMstrPmInsListDTO.selectedPmType'].value;
	//작업형태 
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmTypeDesc'].value = selectedPmType;
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmTypeId'].value = selectedPmType;
	maEqMstrPmInsDetailForm.elements['maPmMstrDetailDTO.pmType'].value = selectedPmType;
	valSysDir('maEqMstrPmInsDetailDTO.pmTypeId', 'maEqMstrPmInsDetailDTO.pmTypeDesc', 'PMI_TYPE', true);
	
	//시행여부 세팅
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.isActiveId'].value = 'Y';
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.isActiveDesc'].value = 'Y';
	
	//부서정보 세팅
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.deptId'].value = loginUser.deptId;
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.deptDesc'].value = loginUser.deptDesc;

	//가동량/시간 = T - 시간
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.scheduleTypeDesc'].value = "T"; 
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.scheduleTypeId'].value = "T"; 
	valSysDir('maEqMstrPmInsDetailDTO.scheduleTypeId', 'maEqMstrPmInsDetailDTO.scheduleTypeDesc', 'SCHEDULE_TYPE', true);
	
}

function setSequenceVal(sequenceVal)
{
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmId'].value = sequenceVal;
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmNo'].value = sequenceVal;
	sequenceNextVal2('SQAPMEQUIP_ID');
}

function setSequenceVal2(sequenceVal)
{
	
	maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmEquipId'].value = sequenceVal;
}
function goSave(){
	if(checkValidation()) return;
	
	pmSaveAction();
}

function pmSaveAction(){
	if(ckCreate(currentPageId)) maEqMstrPmInsDetailForm.strutsAction.value = '<%=MaEqMstrPmInsDetailAction.EQ_MSTR_PMINS_DETAIL_INPUT%>';
	else maEqMstrPmInsDetailForm.strutsAction.value = '<%= MaEqMstrPmInsDetailAction.EQ_MSTR_PMINS_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrPmInsDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrPmInsDetailForm), 'afterSave');
	
}
function goTabPage(pageId)
{
	var form = document.maEqMstrPmInsDetailForm;
	
	maEqMstrPmInsDetailForm.elements['maPmMstrDetailDTO.pmType'].value = maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmTypeId'].value;
	
	goCommonTabPage(form, '' , pageId);
}
function afterSave(data)
{
	//=====================================
    if (!checkHttpXml(data)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmId'].value
    		,maEqMstrPmInsDetailForm.elements['maEqMstrPmInsDetailDTO.pmEquipId'].value);
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrPmInsDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maEqMstrCommonDTO.equipId"/>
	<html:hidden property="maEqMstrPmInsListDTO.selectedPmType"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.pmId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.pmEquipId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.woTypeId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.pmTypeId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.periodTypeId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.isActiveId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.deptId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.wkCtrId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.empId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.wrkCalListId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.oldCycle"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.oldPeriodTypeId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.oldInitWrkDate"/>
	
	<html:hidden property="maEqMstrPmInsDetailDTO.plantId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.scheduleTypeId"/>
	<html:hidden property="maEqMstrPmInsDetailDTO.lnWrkListId"/>
	
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="maPmMstrDetailDTO.pmType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			
			<!-- 예방작업번호 -->
			<div class="field">
				<label><bean:message key="LABEL.pmNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPmInsDetailDTO.pmNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 작업형태 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmType"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPmInsDetailDTO.pmTypeDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.description" tabindex="30"/>
				</div>
			</div>
			<!-- 일정생성방법 -->
			<div class="field">
				<label>일정생성방법</label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.scheduleTypeDesc" tabindex="35"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공장명 -->
			<div class="field">
				<label>공장명</label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.plantDesc" tabindex="38"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 최초작업시작일 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.initWrkDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.initWrkDate" tabindex="40" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 주기 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.cycleDesc"/></label>
				<div class="datetime_wrap">
					<div class="input_box">
						<html:text property="maEqMstrPmInsDetailDTO.cycle" tabindex="50" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maEqMstrPmInsDetailDTO.periodTypeDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			</div>
			<!--몇일전작업생성 -->
			<div class="field">
				<label>몇일전작업생성</label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.woResBef" tabindex="65" styleClass="num"/>
				</div>
			</div>
			<!--가동시간 -->
			<div class="field">
				<label>가동시간</label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.usage" tabindex="68" styleClass="num"/>
				</div>
			</div>
			<!--가동달력 -->
			<div class="field">
				<label>가동달력</label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.lnWrkListDesc" tabindex="69" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!--근무달력 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.wrkCalListDesc" tabindex="70" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.deptDesc"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.deptDesc" tabindex="80" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.wkCtrDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.empDesc" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!--예상작업시간(분) -->
			<div class="field">
				<label>예상작업시간(분)</label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.predWoTimeMin" tabindex="105" styleClass="num"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmInsDetailDTO.isActiveDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqMstrPmInsDetailDTO.remark" styleClass="ta50" tabindex="120" />
				</div>
			</div>
			
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>