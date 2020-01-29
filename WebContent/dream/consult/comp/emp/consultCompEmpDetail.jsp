<%--===========================================================================
사원 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.emp.action.ConsultCompEmpDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 사원 -->
<title><bean:message key='LABEL.empNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

var compAc;
var deptAc;
var isUseAc;
var wkCtrDescAc;
var plantTypeAc;

function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    	//goTabPage("orgEmpCertList");
    	//goTabPage("orgEmpTrainList");
    }
    
    setTitle("consultCompEmpDetailDTO.empNo", "consultCompEmpDetailDTO.empName");
    
    setForUpdate();
    
    
    deptAc = new autoC({"consultCompEmpDetailDTO.deptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcDConditionMap({
    	"comp_no":"consultCompEmpDetailDTO.compNo",
    	"plant" : "consultCompEmpDetailDTO.plantId"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("consultCompEmpDetailDTO.deptId");
    deptAc.setAcResultMap({
        "consultCompEmpDetailDTO.deptId":"dept_id"
    });
    deptAc.init();


    acSysDesc("consultCompEmpDetailDTO.isJoinDesc","consultCompEmpDetailDTO.isJoin","IS_USE");
    
    
    plantTypeAc = new autoC({"consultCompEmpDetailDTO.plantDesc":"description"});
	plantTypeAc.setAcDisplay("DESCRIPTION");
	plantTypeAc.setAcDConditionMap({
    	"comp_no":"consultCompEmpDetailDTO.compNo"
  	   });
	plantTypeAc.setTable("TAUSRPLANTAUTH");
	plantTypeAc.setKeyName("consultCompEmpDetailDTO.plantId");
	plantTypeAc.setAcResultMap({
        "consultCompEmpDetailDTO.plantId":"plant"
    });
	plantTypeAc.init();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEMP_ID');
    consultCompEmpDetailForm.elements['consultCompEmpDetailDTO.isJoin'].value = 'Y';
    consultCompEmpDetailForm.elements['consultCompEmpDetailDTO.isJoinDesc'].value = 'Y';
    
    compAc = new autoC({"consultCompEmpDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setCustomLov("lovComp('consultCompEmpDetailDTO.compNo', 'consultCompEmpDetailDTO.compDesc')");
    compAc.setKeyName("consultCompEmpDetailDTO.compNo");
    compAc.setAcResultMap({
        "consultCompEmpDetailDTO.compNo":"comp_no"
    });
    compAc.init();
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정
    setDisable(document.getElementById("compDescDiv"));
    setDisable(document.getElementById("empNoDiv"));
    //setDisable(document.getElementById("deptDiv"));
    //setDisable(document.getElementById("empNameDiv")); 
}

function setSequenceVal(sequenceVal)
{
    consultCompEmpDetailForm.elements['consultCompEmpDetailDTO.empId'].value = sequenceVal;
    consultCompEmpDetailForm.elements['consultCompEmpCommonDTO.empId'].value = sequenceVal;
}

/**
 * usrGrpNo 중복 체크
 */
function valEmpNo()
{
    isValid = 0;
   
    if(consultCompEmpDetailForm.strutsAction.value == '0')
    {
        var actionUrl = contextPath + "/consultCompEmpDetail.do";
        var param =  "&strutsAction=" + '<%= ConsultCompEmpDetailAction.EMP_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(consultCompEmpDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidEmpNo');
    }
}

/**
 * valEmpNo()실행 후 호출
 */
var isValid = 0;
function setValidEmpNo(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        consultCompEmpDetailForm.elements['consultCompEmpDetailDTO.empNo'].value = '';
        consultCompEmpDetailForm.elements['consultCompEmpDetailDTO.empNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
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
    if(ckCreate(currentPageId)) consultCompEmpDetailForm.strutsAction.value = "<%=ConsultCompEmpDetailAction.EMP_DETAIL_INPUT%>";
    else consultCompEmpDetailForm.strutsAction.value = '<%=ConsultCompEmpDetailAction.EMP_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/consultCompEmpDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(consultCompEmpDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     consultCompEmpDetailForm.elements['consultCompEmpCommonDTO.empId'].value = consultCompEmpDetailForm.elements['consultCompEmpDetailDTO.empId'].value;

     //parent.goSearch();
     if (parent.findGridRow)parent.findGridRow(consultCompEmpDetailForm.elements['consultCompEmpCommonDTO.empId'].value);
     
     setTitle("consultCompEmpDetailDTO.empNo", "consultCompEmpDetailDTO.empName");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
 function goTabPage(pageId)
 {
 	var form = document.consultCompEmpDetailForm;

 	goCommonTabPage(form, '' , pageId);
     
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultCompEmpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="consultCompEmpCommonDTO.compNo" />
	<html:hidden property="consultCompEmpCommonDTO.empId" />
	<html:hidden property="consultCompEmpDetailDTO.compNo" />
	<html:hidden property="consultCompEmpDetailDTO.empId" />
	<html:hidden property="consultCompEmpDetailDTO.deptId" />
	<html:hidden property="consultCompEmpDetailDTO.isJoin" />
	<html:hidden property="consultCompEmpDetailDTO.plantId" />
    <div class="article_box">
        <div class="form_box">
        	<div class="field" id="compDescDiv">
				<label class="check"><bean:message key="LABEL.compDesc"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.compDesc" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
            <div class="field" id="empNoDiv">
				<label class="check"><bean:message key="LABEL.empNo"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.empNo" tabindex="20"/>
				</div>
			</div>
			<div class="field" id="empNameDiv">
				<label><bean:message key="LABEL.empName"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.empName" tabindex="30"/>
				</div>
			</div>
			<div class="field" id="deptDiv">
				<label><bean:message key="LABEL.dept"/></label>
				<div class="input_box">
		            <html:text property="consultCompEmpDetailDTO.deptDesc" tabindex="40"/>
		            <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.mphone"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.mphone" tabindex="50"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.grade"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.grade" tabindex="60"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.joinDate"/></label>
			    <div class="input_box">
					<html:text property="consultCompEmpDetailDTO.joinDate" tabindex="70" />
				    <p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.email"/></label>
			    <div class="input_box">
				    <html:text property="consultCompEmpDetailDTO.email" tabindex="80"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.retireDate"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.retireDate" tabindex="90"/>
				    <p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.plantDesc" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.isJoin"/></label>
				<div class="input_box">
					<html:text property="consultCompEmpDetailDTO.isJoinDesc" tabindex="110"/>
				    <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field_long">
			</div>
			<div class="field_long">
			</div>

    </div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>             
</body>
</html:html>
