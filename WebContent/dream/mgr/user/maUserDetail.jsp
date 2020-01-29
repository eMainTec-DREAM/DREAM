<%--===========================================================================
로그인사용자 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@page import="dream.mgr.user.action.MaUserPwAction"%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.user.action.MaUserDetailAction"%>
<html:html>
<head>
<!-- 로그인사용자 -->
<title><bean:message key='LABEL.userId' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action
<!--//
var mainMngAc;
var usrGrpAc;
var initMenuAc;
var beeInitMenuAc;
var isUseAc;
var isMonitorAc;
var shiftTypeAc;
var eqCtgTypeAc;
var eqLocDescAc;
var deptAc;
var wareHouseAc;
var filterWkCtrAc;
var empAc;
var usedDeptAc;
var vendorAc;
var filterVendorAc;

function loadPage() 
{
    setTitle("maUserDetailDTO.userNo", "maUserDetailDTO.userName");
    
    setForUpdate();
    
    mainMngAc = new autoC({"maUserDetailDTO.empNo":"emp_no"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maUserDetailDTO.empId":"emp_id",
        "maUserDetailDTO.userName":"emp_name",
        "maUserDetailDTO.mphone":"m_phone",
        "maUserDetailDTO.email":"e_mail",
        "maUserDetailDTO.userNo":"emp_no",
        "maUserDetailDTO.empNo":"emp_no"
    });
    mainMngAc.setKeyName("maUserDetailDTO.empId");
    mainMngAc.init();
    
    usrGrpAc = new autoC({"maUserDetailDTO.usrGrpName":"group_name"});
    usrGrpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_admin_group":"N"
  	   });
    usrGrpAc.setTable("TAUSRGRP");
    usrGrpAc.setKeyName("maUserDetailDTO.usrGrpId");
    usrGrpAc.setAcResultMap({
        "maUserDetailDTO.usrGrpId":"usrgrp_id"
    });
    usrGrpAc.init();
    
    initMenuAc = new autoC({"maUserDetailDTO.initMenuDesc":"description"});
    initMenuAc.setAcConditionMap({
 //   	"comp_no":loginUser.compNo
    	"service_type":"WEB"
  	   });
    initMenuAc.setTable("TAPMENU");
    initMenuAc.setKeyName("maUserDetailDTO.initMenuId");
    initMenuAc.setAcResultMap({
        "maUserDetailDTO.initMenuId":"menu_id"
    });
    initMenuAc.init();
    
  	//BEE 초기화면
    beeInitMenuAc = new autoC({"maUserDetailDTO.beeInitMenuDesc":"description"});
    beeInitMenuAc.setAcConditionMap({
 //   	"comp_no":loginUser.compNo
    	"service_type":"ANDROID"
  	   });
    beeInitMenuAc.setTable("TAPMENU");
    beeInitMenuAc.setKeyName("maUserDetailDTO.beeInitMenuId");
    beeInitMenuAc.setAcResultMap({
        "maUserDetailDTO.beeInitMenuId":"menu_id"
    });
    beeInitMenuAc.init();

    // 사용여부
    acSysDesc("maUserDetailDTO.isUseDesc","maUserDetailDTO.isUse","IS_USE");
    
    // 잠김여부
    acSysDesc("maUserDetailDTO.isLockedDesc","maUserDetailDTO.isLocked","IS_USE");

    // 교대조
    acSysDesc("maUserDetailDTO.shiftTypeDesc","maUserDetailDTO.shiftTypeId","SHIFT_TYPE", true);
     
    // 관리설비유형
    acSysDesc("maUserDetailDTO.eqCtgTypeDesc","maUserDetailDTO.eqCtgTypeId","EQCTG_TYPE", true);

    // 모니터링 대상 여부
    acSysDesc("maUserDetailDTO.isMonitor","maUserDetailDTO.isMonitor","IS_USE");

    // 메뉴표시
    acSysDesc("maUserDetailDTO.menuDisplayDesc","maUserDetailDTO.menuDisplayId","MENU_DISPLAY", true);
    
	// 문서권한
    acSysDesc("maUserDetailDTO.securGradeDesc","maUserDetailDTO.securGradeId","SECUR_GRADE", true);
    // 직영 여부
    acSysDesc("maUserDetailDTO.isDirectDesc","maUserDetailDTO.isDirect","IS_USE", true);

  	//설비위치
	eqLocDescAc = new autoC({"maUserDetailDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setKeyName("maUserDetailDTO.eqLocId");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maUserDetailDTO.eqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    //부서
    deptAc = new autoC({"maUserDetailDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maUserDetailDTO.filterDeptId");
    deptAc.setAcResultMap({
        "maUserDetailDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    //창고
    wareHouseAc = new autoC({"maUserDetailDTO.filterWcodeDesc":"wname"});
    wareHouseAc.setAcDConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maUserDetailDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.init();
    //작업그룹
    filterWkCtrAc = new autoC({"maUserDetailDTO.filterWkCtrDesc":"description"});
    filterWkCtrAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
		"is_use":"Y"
  	   });
    filterWkCtrAc.setTable("TAWKCTR");
    filterWkCtrAc.setAcResultMap({
        "maUserDetailDTO.filterWkCtrId":"wkctr_id"
    });
    filterWkCtrAc.setKeyName("maUserDetailDTO.filterWkCtrId");
    filterWkCtrAc.init();
    //담당자
    empAc = new autoC({"maUserDetailDTO.filterEmpDesc":"emp_name"});
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    empAc.setTable("TAEMP");
    empAc.setKeyName("maUserDetailDTO.filterEmpId");
    empAc.setAcResultMap({
        "maUserDetailDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "maUserDetailDTO.filterDeptId"
    });
    empAc.init();    
    
    /* 자주보는 사용부서 */
    usedDeptAc = new autoC({"maUserDetailDTO.filterUsageDeptDesc":"description"});
    usedDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usedDeptAc.setTable("TADEPT");
    usedDeptAc.setKeyName("maUserDetailDTO.filterUsageDeptId");
    usedDeptAc.setAcResultMap({
        "maUserDetailDTO.filterUsageDeptId":"dept_id"
    });
    usedDeptAc.init();
    
	/** 거래처  */
	vendorAc = new autoC({"maUserDetailDTO.vendorDesc":"description"});
	vendorAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
	vendorAc.setTable("TAVENDOR");
	vendorAc.setKeyName("maUserDetailDTO.vendorId");
	vendorAc.setAcResultMap({
        "maUserDetailDTO.vendorId":"vendor_id"
    });
	vendorAc.init();
	
	/** 자주보는 거래처  */
	filterVendorAc = new autoC({"maUserDetailDTO.filterVendorDesc":"description"});
	filterVendorAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
	filterVendorAc.setTable("TAVENDOR");
	filterVendorAc.setKeyName("maUserDetailDTO.filterVendorId");
	filterVendorAc.setAcResultMap({
        "maUserDetailDTO.filterVendorId":"vendor_id"
    });
	filterVendorAc.init();
    
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAUSER_ID');
    maUserDetailForm.elements['maUserDetailDTO.isUse'].value = 'Y';
    maUserDetailForm.elements['maUserDetailDTO.isUseDesc'].value = 'Y';
    maUserDetailForm.elements['maUserDetailDTO.isLocked'].value = 'N';
    maUserDetailForm.elements['maUserDetailDTO.isLockedDesc'].value = 'N';
//    maUserDetailForm.elements['maUserDetailDTO.menuDisplayId'].value = 'EXPAND';
//    maUserDetailForm.elements['maUserDetailDTO.menuDisplayDesc'].value = '펼치기';

    mainMngAc.openLov();
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
//     maUserDetailForm.elements['maUserDetailDTO.userNo'].readOnly = false;
}

function setSequenceVal(sequenceVal)
{
    maUserDetailForm.elements['maUserDetailDTO.userId'].value = sequenceVal;
    maUserDetailForm.elements['maUserDetailDTO.userNo'].value = sequenceVal;
    maUserDetailForm.elements['maUserCommonDTO.userId'].value = sequenceVal;
    

	//교대조 - 주간
// 	maUserDetaiLFORM.ELEMENTS['MAUSERDETAILDTO.SHIFTTYPEDESC'].VALUE = "DAY";
// 	VALSYSDIR('maUserDetailDTO.shiftTypeId', 'maUserDetailDTO.shiftTypeDesc', 'SHIFT_TYPE', true);
}

/**
 * userNo 중복 체크
 */
function valUserNo()
{
    var actionUrl = contextPath + "/maUserDetail.do";
    var param =  "&strutsAction=" + '<%= MaUserDetailAction.USER_DETAIL_CHECK %>'
              +  "&" + FormQueryString(maUserDetailForm);
    XMLHttpPostVal(actionUrl, param, 'setValidUserNo');
}

/**
 * valUserNo()실행 후 호출
 */
var isValid;
function setValidUserNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
        maUserDetailForm.elements['maUserDetailDTO.userNo'].value = '';
        maUserDetailForm.elements['maUserDetailDTO.userNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }else{
    	//strutsAction 셋팅.
        if(ckCreate(currentPageId)) maUserDetailForm.strutsAction.value = "<%=MaUserDetailAction.USER_DETAIL_INPUT%>";
        else maUserDetailForm.strutsAction.value = '<%=MaUserDetailAction.USER_DETAIL_UPDATE%>';
        
        var actionUrl = contextPath + "/maUserDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(maUserDetailForm), 'afterSave');
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
    valUserNo();
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maUserDetailForm.elements['maUserCommonDTO.userId'].value = maUserDetailForm.elements['maUserDetailDTO.userId'].value;

     if (parent.findGridRow)	parent.findGridRow(maUserDetailForm.elements['maUserCommonDTO.userId'].value);
     setTitle("maUserDetailDTO.userNo", "maUserDetailDTO.userName");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
     
     //parent.goOpen();
 }

 /**
  * 비밀번호변경
  */
 function goChpw()
 {
    var userId      = maUserDetailForm.elements['maUserCommonDTO.userId'].value;
    var userNo      = maUserDetailForm.elements['maUserCommonDTO.userNo'].value;
    var userName    = maUserDetailForm.elements['maUserCommonDTO.userName'].value;
    chgPwLayer(userId, userNo, userName);
}
/**
 * 비밀번호 변경 팝업
 */
function chgPwLayer(userId,userNo,userName)
{
    var url = contextPath + "/maUserPw.do";

    var popWidth = 910;
    var popHeight = 680;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=no, status=no";
    var strutsVal= "<%=MaUserPwAction.USER_PW_INIT%>";
    var param = "strutsAction="+strutsVal+"&maUserCommonDTO.userId="+userId+"&maUserPwDTO.userId="+userId+"&maUserPwDTO.userName="+userName+"&maUserPwDTO.userNo="+userNo;
    openLayerPopup("maUserPw", param);
}
/**
 *  Audit trail
 */
function goAudtrailLink()
{
    var objectId = maUserDetailForm.elements['maUserDetailDTO.userId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
}

function goSenduserinfomail()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
		 return;
	}
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MGS0237'/>", function(result){
		if(result)
		{
			try{
				maUserDetailForm.strutsAction.value = '<%=MaUserDetailAction.USER_DETAIL_SEND_MAIL%>';
				var actionUrl = contextPath + "/maUserDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(maUserDetailForm), 'afterSenduserinfomail');
		    }catch(e){}
		}
		
	});
}

/**
 * Temp 비밀번호 생성 후 email 발송 
 */
function goResetpw()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
		 return;
	}
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MGS0237'/>", function(result){
		if(result)
		{
			try{
				maUserDetailForm.strutsAction.value = '<%=MaUserDetailAction.USER_DETAIL_SEND_PW_MAIL%>';
				var actionUrl = contextPath + "/maUserDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(maUserDetailForm), 'afterSenduserinfomail');
		    }catch(e){}
		}
		
	});
}

function afterSenduserinfomail(ajaxXmlDoc)
{
	if (!checkHttpXml(ajaxXmlDoc)) return;
    // result = 0:정상, -1:메일서비스 미등록, -2:전송대상이없음
	var result = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
	switch (result.toString()) {
	  case "-1"    : alertMessage1('<bean:message key="MESSAGE.MSG209"/>');
	               break;
	  case "-2"   : alertMessage1('<bean:message key="MESSAGE.MSG210"/>');
	               break;
	  default    : alertMessage1('<bean:message key="MESSAGE.CMSG014"/>');
	               break;
	}
}

function goTabPage(pageId)
{
    var form = document.maUserDetailForm;

    goCommonTabPage(form, '' , pageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maUserDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maUserCommonDTO.compNo" />
<html:hidden property="maUserCommonDTO.userId" />
<html:hidden property="maUserCommonDTO.userNo" />
<html:hidden property="maUserCommonDTO.userName" />
<html:hidden property="maUserDetailDTO.userId" />
<html:hidden property="maUserDetailDTO.empId" />
<html:hidden property="maUserDetailDTO.vendorId" />
<html:hidden property="maUserDetailDTO.initMenuId" />
<html:hidden property="maUserDetailDTO.usrGrpId" />
<html:hidden property="maUserDetailDTO.isUse" />
<html:hidden property="maUserDetailDTO.isLocked" />
<html:hidden property="maUserDetailDTO.isDirect" />
<html:hidden property="maUserDetailDTO.alarmViewYn" />
<html:hidden property="maUserDetailDTO.shiftTypeId" />
<html:hidden property="maUserDetailDTO.eqCtgTypeId" />
<html:hidden property="maUserDetailDTO.menuDisplayId" />
<html:hidden property="maUserDetailDTO.eqLocId" />
<html:hidden property="maUserDetailDTO.filterWcodeId" />
<html:hidden property="maUserDetailDTO.filterDeptId" />
<html:hidden property="maUserDetailDTO.filterEmpId" />
<html:hidden property="maUserDetailDTO.filterVendorId" />
<html:hidden property="maUserDetailDTO.filterWkCtrId" />
<html:hidden property="maUserDetailDTO.securGradeId" />
<html:hidden property="maUserDetailDTO.filterUsageDeptId" />
<html:hidden property="maUserDetailDTO.beeInitMenuId" />

<div class="article_box" id="detailBox">
    <div class="form_box">
        <div class="field">
	 	    <label class="check"><bean:message key="LABEL.userId"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.userNo" tabindex="1"/>
			</div>
	   	</div>
	   	<div class="field">
	        <label class="check"><bean:message key="LABEL.empNo"/></label> 
            <div class="input_box">
                <html:text property="maUserDetailDTO.empNo" tabindex="10"/>
                <p class="open_spop"><a><span>조회</span></a></p>
            </div>
	    </div>
		<div class="field"> 
			<label class="check"><bean:message key="LABEL.userName"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.userName" tabindex="20"/>
			</div>
		</div>
		<div class="field">
			<label><bean:message key="LABEL.mphone"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.mphone" tabindex="30"/>
			</div>
		</div>
		<div class="field">
		    <label class="check"><bean:message key="LABEL.userGroup"/></label>
            <div class="input_box">
                <html:text property="maUserDetailDTO.usrGrpName" tabindex="40"/>
                <p class="open_spop"><a><span>조회</span></a></p>
            </div>
		</div>
		<div class="field">
			<label><bean:message key="LABEL.email"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.email" tabindex="50"/>
			</div>
		</div>
		<div class="field">
			<label class="check"><bean:message key="LABEL.menuId"/></label>
            <div class="input_box">
                <html:text property="maUserDetailDTO.initMenuDesc" tabindex="60"/>
                <p class="open_spop"><a><span>조회</span></a></p>
            </div>
		</div>
		<!-- Bee초기화면 -->
		<div class="field">
			<label><bean:message key="LABEL.beeInitPage"/></label>
            <div class="input_box">
                <html:text property="maUserDetailDTO.beeInitMenuDesc" tabindex="65"/>
                <p class="open_spop"><a><span>조회</span></a></p>
            </div>
		</div>
		<div class="field">
			<label><bean:message key="LABEL.isUse"/></label>
            <div class="input_box">
                <html:text property="maUserDetailDTO.isUseDesc" tabindex="70"/>
                <p class="open_spop">
                    <a>
                        <span>조회</span>
                    </a>
                </p>
            </div>
        </div>
		<div class="field">
			<label><bean:message key="LABEL.isLocked"/></label>
            <div class="input_box">
                <html:text property="maUserDetailDTO.isLockedDesc" tabindex="75"/>
                <p class="open_spop">
                    <a>
                        <span>조회</span>
                    </a>
                </p>
            </div>
        </div>
		<div class="field">
			<label><bean:message key="LABEL.regDate"/></label>
			<div class="input_read">
			    <html:text property="maUserDetailDTO.regDate" tabindex="80" readonly="true"/>
			</div>
        </div>
		<div class="field">
		   <label><bean:message key="LABEL.loginDate"/></label>
		   <div class="input_read">
		       <html:text property="maUserDetailDTO.loginDate" tabindex="90" readonly="true"/>
		   </div>
	    </div>
	    <div class="field">
			<label><bean:message key="LABEL.isMonitor"/></label>
            <div class="input_box">
                <html:text property="maUserDetailDTO.isMonitor" tabindex="90"/>
                <p class="open_spop">
                    <a>
                        <span>조회</span>
                    </a>
                </p>
            </div>
        </div>
		<!-- 교대조. -->
		<div class="field">
			<label><bean:message key="LABEL.shiftType"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.shiftTypeDesc" tabindex="100" />
				<p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 관리 설비유형  -->
		<div class="field">
			<label><bean:message key="LABEL.mngEqCtgType"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.eqCtgTypeDesc" tabindex="110" />
				<p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 메뉴표시  -->
		<div class="field">
			<label><bean:message key="LABEL.menuDisplay"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.menuDisplayDesc" tabindex="120" />
				<p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		
		<!-- 설비위치 -->
		<div class="field">
			<label><bean:message key="LABEL.location"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.eqLocDesc" tabindex="50"/>
				<p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 부서 -->
		<div class="field">
			<label><bean:message key="LABEL.favoritesDept"/></label>
			<div class="input_box">
	            <html:text property="maUserDetailDTO.filterDeptDesc" tabindex="120"/>
	            <p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 창고 -->
		<div class="field">
			<label><bean:message key="LABEL.useWareHouse"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.filterWcodeDesc" tabindex="60" />
				<p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!--작업그룹 명  -->
		<div class="field">
			<label><bean:message key="LABEL.filterWkCtr"/></label>
			<div class="input_box">
	            <html:text property="maUserDetailDTO.filterWkCtrDesc" tabindex="140"/>
	            <p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 사용자  -->
		<div class="field">
			<label><bean:message key="LABEL.favoritesEmp"/></label>
			<div class="input_box">
	            <html:text property="maUserDetailDTO.filterEmpDesc" tabindex="130"/>
	            <p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 문서권한  -->
		<div class="field">
			<label><bean:message key="LABEL.secureGrade"/></label>
			<div class="input_box">
				<html:text property="maUserDetailDTO.securGradeDesc" tabindex="150" />
				<p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- OTP key  -->
		<div class="field">
			<label><bean:message key="LABEL.otpKey"/></label>
			<div class="input_read">
				<html:text property="maUserDetailDTO.otpKey" tabindex="160" readonly="true" />
			</div>
		</div>
		
		<!-- 자주보는 사용부서 -->
		<div class="field">
			<label><bean:message key="LABEL.favoritesUsedDept"/></label>
			<div class="input_box">
	            <html:text property="maUserDetailDTO.filterUsageDeptDesc" tabindex="125"/>
	            <p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 직영여부 -->
	    <div class="field">
			<label><bean:message key="LABEL.isDirect"/></label>
            <div class="input_box">
                <html:text property="maUserDetailDTO.isDirectDesc" tabindex="170"/>
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
	            <html:text property="maUserDetailDTO.vendorDesc" tabindex="180"/>
	            <p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
        <!-- 자주보는 사용부서 -->
		<div class="field">
			<label><bean:message key="LABEL.favoritesVendor"/></label>
			<div class="input_box">
	            <html:text property="maUserDetailDTO.filterVendorDesc" tabindex="190"/>
	            <p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		
	</div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
