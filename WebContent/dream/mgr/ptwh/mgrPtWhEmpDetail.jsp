<%--===========================================================================
부품창고 담당자 Detail
author  sy.yang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhEmpDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 부품창고 담당자 -->
<title><bean:message key='LABEL.manager' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var EmpAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
    setTitle("mgrPtWhEmpDetailDTO.empNo", "mgrPtWhEmpDetailDTO.empDesc");
   
    //For Save
    setForUpdate();
    
    EmpAc = new autoC({"mgrPtWhEmpDetailDTO.empDesc":"emp_name"});
    EmpAc.setAcDisplay("EMP_NAME");
    EmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    EmpAc.setTable("TAEMP");
    EmpAc.setKeyName("mgrPtWhEmpDetailDTO.empId");
    EmpAc.setAcResultMap({
        "mgrPtWhEmpDetailDTO.empId":"emp_id"
        ,"mgrPtWhEmpDetailDTO.deptId":"deptId"
        ,"mgrPtWhEmpDetailDTO.deptDesc":"deptDesc"
    });
    EmpAc.init();    
}

/**
 * 입력
 */
function goInput()
{
	// 시퀀스를 조회한다.
    sequenceNextVal('SQAWHUSER_ID');    
    mgrPtWhEmpDetailForm.elements['mgrPtWhEmpDetailDTO.wcodeId'].value = mgrPtWhEmpDetailForm.elements['mgrPtWhCommonDTO.wcodeId'].value;
}

function setSequenceVal(sequenceVal)
{
    mgrPtWhEmpDetailForm.elements['mgrPtWhEmpDetailDTO.whUserId'].value = sequenceVal;
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

    // 생성, 수정 시 중복 담당자 체크
	validEmp();
	if(isValid!='0') return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) mgrPtWhEmpDetailForm.strutsAction.value = "<%=MgrPtWhEmpDetailAction.DETAIL_INPUT%>";
    else mgrPtWhEmpDetailForm.strutsAction.value = "<%=MgrPtWhEmpDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrPtWhEmpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrPtWhEmpDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) 
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(mgrPtWhEmpDetailForm.elements['mgrPtWhEmpDetailDTO.whUserId'].value);

	mgrPtWhEmpDetailForm.elements['mgrPtWhEmpListDTO.whUserId'].value = mgrPtWhEmpDetailForm.elements['mgrPtWhEmpDetailDTO.whUserId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("mgrPtWhEmpDetailDTO.empNo","mgrPtWhEmpDetailDTO.empDesc");

}

/**
 * 중복 담당자 체크
 */
function validEmp(){
	var actionUrl = contextPath + "/mgrPtWhEmpDetail.do";
	var param =  "&strutsAction=" + '<%= MgrPtWhEmpDetailAction.DETAIL_CHECK %>'
	          +  "&" + FormQueryString(mgrPtWhEmpDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidEmpNo');
}
/**
 * valEmpNo()실행 후 호출
 */
var isValid;
function setValidEmpNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
    	mgrPtWhEmpDetailForm.elements['mgrPtWhEmpDetailDTO.empNo'].value = '';
    	mgrPtWhEmpDetailForm.elements['mgrPtWhEmpDetailDTO.empNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mgrPtWhEmpDetailForm.elements['mgrPtWhEmpDetailDTO.whUserId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrPtWhEmpDetail.do">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="mgrPtWhCommonDTO.wcodeId" />
		<html:hidden property="mgrPtWhEmpListDTO.wcodeId" />
		<html:hidden property="mgrPtWhEmpListDTO.whUserId" />
		
		<html:hidden property="mgrPtWhEmpDetailDTO.wcodeId" />
		<html:hidden property="mgrPtWhEmpDetailDTO.whUserId" />
		<html:hidden property="mgrPtWhEmpDetailDTO.empId" />
		<html:hidden property="mgrPtWhEmpDetailDTO.empNo" />

		<div class="article_box">
			<div class="form_box">
				<!-- 사원번호 -->
	            <div class="field">
					<label class="check"><bean:message key="LABEL.emp"/></label>
					<div class="input_box">
						<html:text property="mgrPtWhEmpDetailDTO.empDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!--부서  -->
				<div class="field" id="deptDiv">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_read">
			            <html:text property="mgrPtWhEmpDetailDTO.deptDesc" tabindex="20" readonly="true"/>			           
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="mgrPtWhEmpDetailDTO.remark"
							tabindex="60" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
