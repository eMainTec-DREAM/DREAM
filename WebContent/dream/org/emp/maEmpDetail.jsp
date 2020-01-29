<%--===========================================================================
사원 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.emp.action.MaEmpDetailAction"%>
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

var deptAc;
var isUseAc;
var wkCtrDescAc;
var plantTypeAc;
var vendorAc;

function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maEmpDetailDTO.empNo", "maEmpDetailDTO.empName");
    
    setForUpdate();
    
    deptAc = new autoC({"maEmpDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setAcDConditionMap({
    	"plant" : "maEmpDetailDTO.plantId"
    	,"plantDesc" : "maEmpDetailDTO.plantDesc"
    });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maEmpDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maEmpDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    wkCtrDescAc = new autoC({"maEmpDetailDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
			"is_use":"Y"
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setKeyName("maEmpDetailDTO.wkCtrId");
    wkCtrDescAc.setAcResultMap({
        "maEmpDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();

    
    acSysDesc("maEmpDetailDTO.isJoinDesc","maEmpDetailDTO.isJoin","IS_USE", true);
    acSysDesc("maEmpDetailDTO.isMailRecDesc","maEmpDetailDTO.isMailRec","IS_USE", true);
    // 직영 여부
    acSysDesc("maEmpDetailDTO.isDirectDesc","maEmpDetailDTO.isDirect","IS_USE", true);

    plantTypeAc = new autoC({"maEmpDetailDTO.plantDesc":"description"});
	plantTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantTypeAc.setTable("TAPLANT");
	plantTypeAc.setKeyName("maEmpDetailDTO.plantId");
	plantTypeAc.setAcResultMap({
        "maEmpDetailDTO.plantId":"plant"
    });
	plantTypeAc.init();
	/** 거래처  */
	vendorAc = new autoC({"maEmpDetailDTO.vendorDesc":"description"});
	vendorAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
	vendorAc.setTable("TAVENDOR");
	vendorAc.setKeyName("maEmpDetailDTO.vendorId");
	vendorAc.setAcResultMap({
        "maEmpDetailDTO.vendorId":"vendor_id"
    });
	vendorAc.init();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEMP_ID');
    maEmpDetailForm.elements['maEmpDetailDTO.isJoin'].value = 'Y';
    maEmpDetailForm.elements['maEmpDetailDTO.isJoinDesc'].value = 'Y';
    
  	//공장명
    if(loginUser.plant!='null'){
    	maEmpDetailForm.elements['maEmpDetailDTO.plantId'].value  = loginUser.plant;
    	maEmpDetailForm.elements['maEmpDetailDTO.plantDesc'].value  = loginUser.plantDesc;
	}
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정    
    setDisable(document.getElementById("empNoDiv"));
    //setDisable(document.getElementById("deptDiv"));
    //setDisable(document.getElementById("empNameDiv")); 
}

function setSequenceVal(sequenceVal)
{
    maEmpDetailForm.elements['maEmpDetailDTO.empId'].value = sequenceVal;
    maEmpDetailForm.elements['maEmpDetailDTO.empNo'].value = sequenceVal;
    maEmpDetailForm.elements['maEmpCommonDTO.empId'].value = sequenceVal;
}

/**
 * empNo 중복 체크
 */
function valEmpNo()
{
	var actionUrl = contextPath + "/maEmpDetail.do";
	var param =  "&strutsAction=" + '<%= MaEmpDetailAction.EMP_DETAIL_CHECK %>'
    		  +  "&empId=" + maEmpDetailForm.elements['maEmpDetailDTO.empId'].value
    		  +  "&empNo=" + maEmpDetailForm.elements['maEmpDetailDTO.empNo'].value ;
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
        maEmpDetailForm.elements['maEmpDetailDTO.empNo'].value = '';
        maEmpDetailForm.elements['maEmpDetailDTO.empNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
    else
    {
    	goSaveAfterValid();
    }
}

/**
 * 저장
 */ 
function goSave()
{
	var empId = maEmpDetailForm.elements['maEmpDetailDTO.empId'].value;
	var empNo = maEmpDetailForm.elements['maEmpDetailDTO.empNo'].value;
	
	if(!empId&&!empNo)
	{
		alertMessage1("<bean:message key='MESSAGE.CMSG039' />");
		return;	
	} 
	
    valEmpNo();
}

function goSaveAfterValid()
{
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
  	
    //strutsAction 셋팅.
	if(ckCreate(currentPageId)) maEmpDetailForm.strutsAction.value = "<%=MaEmpDetailAction.EMP_DETAIL_INPUT%>";
    else maEmpDetailForm.strutsAction.value = '<%=MaEmpDetailAction.EMP_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maEmpDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maEmpDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maEmpDetailForm.elements['maEmpCommonDTO.empId'].value = maEmpDetailForm.elements['maEmpDetailDTO.empId'].value;

     //parent.goSearch();
     if (parent.findGridRow)parent.findGridRow(maEmpDetailForm.elements['maEmpCommonDTO.empId'].value);
     
     setTitle("maEmpDetailDTO.empNo", "maEmpDetailDTO.empName");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
 function goTabPage(pageId)
 {
 	var form = document.maEmpDetailForm;

 	goCommonTabPage(form, '' , pageId);
     
 }
 
 
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maEmpDetailForm.elements['maEmpDetailDTO.empId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maEmpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEmpCommonDTO.compNo" />
	<html:hidden property="maEmpCommonDTO.empId" />
	<html:hidden property="maEmpDetailDTO.empId" />
	<html:hidden property="maEmpDetailDTO.deptId" />
	<html:hidden property="maEmpDetailDTO.isJoin" />
	<html:hidden property="maEmpDetailDTO.isDirect" />
	<html:hidden property="maEmpDetailDTO.isMailRec" />
	<html:hidden property="maEmpDetailDTO.plantId" />
	<html:hidden property="maEmpDetailDTO.wkCtrId" />
	<html:hidden property="maEmpDetailDTO.vendorId" />
    <div class="article_box">
        <div class="form_box">
        <!-- 사원번호 -->
            <div class="field" id="empNoDiv">
				<label class="check"><bean:message key="LABEL.empNo"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.empNo" tabindex="1"/>
				</div>
			</div>
			<!--부서  -->
			<div class="field" id="deptDiv">
				<label class="check"><bean:message key="LABEL.dept"/></label>
				<div class="input_box">
		            <html:text property="maEmpDetailDTO.deptDesc" tabindex="20"/>
		            <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.wkCtrDesc" tabindex="25"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!--사원명  -->
			<div class="field" id="empNameDiv">
				<label class="check"><bean:message key="LABEL.empName"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.empName" tabindex="30"/>
				</div>
			</div>
			<!--직급  -->
			<div class="field">
				<label><bean:message key="LABEL.grade"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.grade" tabindex="40"/>
				</div>
			</div>
			<!--모바일  -->
			<div class="field">
				<label><bean:message key="LABEL.mphone"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.mphone" tabindex="50"/>
				</div>
			</div>
			<!--이메일  -->
			<div class="field">
				<label><bean:message key="LABEL.email"/></label>
			    <div class="input_box">
				    <html:text property="maEmpDetailDTO.email" tabindex="60"/>
				</div>
			</div>
			<!-- 입사일 -->
			<div class="field">
				<label><bean:message key="LABEL.joinDate"/></label>
			    <div class="input_box">
					<html:text property="maEmpDetailDTO.joinDate" tabindex="70" />
				         <p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!--퇴사일  -->
			<div class="field">
				<label><bean:message key="LABEL.retireDate"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.retireDate" tabindex="90"/>
				         <p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 근무여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isJoin"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.isJoinDesc" tabindex="100"/>
				    <p class="open_spop">
				        <a>
				            <span>조회</span>
				        </a>
				    </p>
				</div>
			</div>
			<!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.plantDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 메일수신여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isMailRec"/></label>
				<div class="input_box">
					<html:text property="maEmpDetailDTO.isMailRecDesc" tabindex="120"/>
				    <p class="open_spop">
				        <a>
				            <span>조회</span>
				        </a>
				    </p>
				</div>
			</div>
		<!-- 직영여부 -->
	    <div class="field">
			<label><bean:message key="LABEL.isDirect"/></label>
            <div class="input_box">
                <html:text property="maEmpDetailDTO.isDirectDesc" tabindex="170"/>
                <p class="open_spop">
                    <a>
                        <span>조회</span>
                    </a>
                </p>
            </div>
        </div>
		<!-- 거래처  -->
		<div class="field">
			<label><bean:message key="LABEL.vendor"/></label>
			<div class="input_box">
	            <html:text property="maEmpDetailDTO.vendorDesc" tabindex="180"/>
	            <p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>

    </div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>             
</body>
</html:html>
