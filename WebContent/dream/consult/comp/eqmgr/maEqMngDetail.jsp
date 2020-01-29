<%--===========================================================================
설비담당자변경 - 상세
author  jung7126
version $Id: maEqMngDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.eqmgr.action.MaEqMngDetailAction"%>
<html:html>
<head>
<!-- 담당자 일괄변경-->
<title><bean:message key='LABEL.maEqMngDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
/** 자동완성 변수  */
var eqLocDescAc, deptAc, mainMngAc, subMngAc, newMainMngAc, newSubMngAc;

function loadPage() 
{
	//setForUpdate();

	// 관리부서
    deptAc = new autoC({"maEqMngDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maEqMngDetailDTO.deptId":"dept_id"
    });
    deptAc.setKeyName("maEqMngDetailDTO.deptId");
    deptAc.init();
    
    // 관리자(정)
    mainMngAc = new autoC({"maEqMngDetailDTO.mainMngName":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maEqMngDetailDTO.mainMngId":"emp_id"
    });
    mainMngAc.setKeyName("maEqMngDetailDTO.mainMngId");
    mainMngAc.init();
    
    // 관리자(부)
    subMngAc = new autoC({"maEqMngDetailDTO.subMngName":"emp_name"});
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maEqMngDetailDTO.subMngId":"emp_id"
    });
    subMngAc.setKeyName("maEqMngDetailDTO.subMngId");
    subMngAc.init();
    
    // 신규 관리자(정)
    newMainMngAc = new autoC({"maEqMngDetailDTO.newMainMngName":"emp_name"});
    newMainMngAc.setTable("TAEMP");
    newMainMngAc.setAcResultMap({
        "maEqMngDetailDTO.newMainMngId":"emp_id"
    });
    newMainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMngDetailDTO.deptId"
    });
    newMainMngAc.setKeyName("maEqMngDetailDTO.newMainMngId");
    newMainMngAc.init();
    
    // 신규 관리자(부)
    newSubMngAc = new autoC({"maEqMngDetailDTO.newSubMngName":"emp_name"});
    newSubMngAc.setTable("TAEMP");
    newSubMngAc.setAcResultMap({
        "maEqMngDetailDTO.newSubMngId":"emp_id"
    });
    newSubMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMngDetailDTO.deptId"
    });
    newSubMngAc.setKeyName("maEqMngDetailDTO.newSubMngId");
    newSubMngAc.init();
    
    
    eqLocDescAc = new autoC({"maEqMngDetailDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setKeyName("maEqMngDetailDTO.eqLocId");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maEqMngDetailDTO.eqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
}


function goConfirm()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	var mainMngId = maEqMngDetailForm.elements['maEqMngDetailDTO.mainMngId'];
	var subMngId  = maEqMngDetailForm.elements['maEqMngDetailDTO.subMngId'];
	var newMainId = maEqMngDetailForm.elements['maEqMngDetailDTO.newMainMngId'];
	var newSubId  = maEqMngDetailForm.elements['maEqMngDetailDTO.newSubMngId'];

	if(newMainId.value != "")
	{
		if(mainMngId.value == "")
		{
			alertMessage1('<bean:message key="LABEL.curMainManager"/>' + " " + COMMON_CMSG047);
			mainMngId.focus();
			return;
		}
	}
	else if(newSubId.value != "")
	{
		if(subMngId.value == "")
		{
			alertMessage1('<bean:message key="LABEL.curSubManager"/>' + " " + COMMON_CMSG047);
			subMngId.focus();
			return;
		}
	}
	else if(newMainId.value == "" && newSubId.value == "")
	{
		alertMessage1('<bean:message key="LABEL.noChange"/>');
		return;
	}
	
	//변경하시겠습니까?
	getTopPage().dhtmlx.confirm(COMMON_CMSG058, function(result){
		
		if(result)
		{
			goConfirmAction();
		}
		
	});
}
/**
 * 저장
 */ 
function goConfirmAction()
{	
	//strutsAction 셋팅.
	maEqMngDetailForm.strutsAction.value = '<%=MaEqMngDetailAction.EQ_MNG_UPDATE%>';
	
	var actionUrl = contextPath + "/maEqMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMngDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================

	alertMessage1(COMMON_CMSG032);
	//parent.goSearch();
	//if (parent.findGridRow)	parent.findGridRow(maEqMngDetailForm.elements['maEqMngDetailDTO.equipId'].value);
	
	//getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maEqMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maEqMngDetailDTO.deptId" />
	<html:hidden property="maEqMngDetailDTO.eqLocId" />
	<html:hidden property="maEqMngDetailDTO.mainMngId" />
	<html:hidden property="maEqMngDetailDTO.subMngId" />
	<html:hidden property="maEqMngDetailDTO.newMainMngId" />
	<html:hidden property="maEqMngDetailDTO.newSubMngId" />
	<div class="article_box">
		<div class="form_box">
			
			<!-- 관리부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.mngDept"/></label>
				<div class="input_box">
					<html:text property="maEqMngDetailDTO.deptDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_box">
					<html:text property="maEqMngDetailDTO.eqLocDesc" tabindex="20" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 관리자(정) -->
			<div class="field">
				<label><bean:message key="LABEL.curMainManager"/></label>
				<div class="input_box">
					<html:text property="maEqMngDetailDTO.mainMngName" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 신규 관리자(정) -->
			<div class="field">
				<label><bean:message key="LABEL.newMainManager"/></label>
				<div class="input_box">
					<html:text property="maEqMngDetailDTO.newMainMngName" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 관리자(부) -->
			<div class="field">
				<label><bean:message key="LABEL.curSubManager"/></label>
				<div class="input_box">
					<html:text property="maEqMngDetailDTO.subMngName" tabindex="50"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 신규 관리자(부) -->
			<div class="field">
				<label><bean:message key="LABEL.newSubManager"/></label>
				<div class="input_box">
					<html:text property="maEqMngDetailDTO.newSubMngName" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>