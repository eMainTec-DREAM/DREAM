<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<%@ page import="dream.consult.comp.user.action.ConsultCompUserDetailAction" %>
<%@ page import="dream.mgr.user.action.MaUserPwAction" %>

<html:html>
<head>
<!-- 사용자 디테일 -->
<title><bean:message key='MENU.USER' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var compAc;
var empAc;

var deptAc;
var eqLocAc;
var eqCtgAc;

var usrGrpAc;
var initMenuAc;
var wkCtrAc;
var menuDisplayAc;

function loadPage() 
{	
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    setTitle("consultCompUserDetailDTO.userNo", "consultCompUserDetailDTO.userName");
    //For Save
    setForUpdate();
    //사용여부 자동완성
    acSysDesc("consultCompUserDetailDTO.isUseDesc","consultCompUserDetailDTO.isUseId","IS_USE", true);
    
    //회사명 자동완성 (pop up)
    compAc = new autoC({"consultCompUserDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompUserDetailDTO.compNo");
    compAc.setAcResultMap({
        "consultCompUserDetailDTO.compNo":"comp_no"
    });
    compAc.init();
    
    //사원 자동완성
    empAc = new autoC({"consultCompUserDetailDTO.empName":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setKeyName("consultCompUserDetailDTO.empId");
    empAc.setAcDConditionMap({
        "comp_no":"consultCompUserDetailDTO.compNo",
    	"dept_id" : "consultCompUserDetailDTO.deptId"
    });
    empAc.setAcResultMap({
        "consultCompUserDetailDTO.empId":"emp_id"
    	,"consultCompUserDetailDTO.email":"e_mail"
        ,"consultCompUserDetailDTO.phone":"m_phone"
        ,"consultCompUserDetailDTO.userName":"emp_name"
        ,"consultCompUserDetailDTO.compNo":"comp_no"
        ,"consultCompUserDetailDTO.compDesc":"compDesc"
    });
    empAc.init();
    
    //권한 자동완성 (pop up)
    usrGrpAc = new autoC({"consultCompUserDetailDTO.usrGrpDesc":"group_name"});
    usrGrpAc.setTable("TAUSRGRP");
    usrGrpAc.setKeyName("consultCompUserDetailDTO.usrGrpId");
    usrGrpAc.setAcDConditionMap({
        "comp_no":"consultCompUserDetailDTO.compNo"
    });
    usrGrpAc.setAcResultMap({
        "consultCompUserDetailDTO.usrGrpId":"usrgrp_id"
    });
    usrGrpAc.init();
    
    //초기화면 (pop up)
    initMenuAc = new autoC({"consultCompUserDetailDTO.initMenuDesc":"description"});
    initMenuAc.setTable("TACMENU");
    initMenuAc.setKeyName("consultCompUserDetailDTO.initMenuId");
    initMenuAc.setAcResultMap({
        "consultCompUserDetailDTO.initMenuId":"MENU_ID"
    });
    initMenuAc.init();
    
    //교대조 (pop up)
    acSysDesc("consultCompUserDetailDTO.shiftTypeDesc","consultCompUserDetailDTO.shiftTypeId","SHIFT_TYPE", true);
    
    //설비위치 자동완성
    eqLocAc = new autoC({"consultCompUserDetailDTO.eqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setKeyName("consultCompUserDetailDTO.eqLocId");
    eqLocAc.setAcDConditionMap({
        "comp_no":"consultCompUserDetailDTO.compNo"
    });
    eqLocAc.setAcResultMap({
        "consultCompUserDetailDTO.eqLocId":"EQLOC_ID"
    });
    eqLocAc.init();
    
    //알람 여부
    acSysDesc("consultCompUserDetailDTO.alarmViewYn","consultCompUserDetailDTO.alarmViewYnId","IS_USE", true);
    
    //작업그룹
    wkCtrAc = new autoC({"consultCompUserDetailDTO.wkctrDesc":"description"});
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setKeyName("consultCompUserDetailDTO.wkctrId");
    wkCtrAc.setAcDConditionMap({
        "comp_no":"consultCompUserDetailDTO.compNo"
        ,"is_use":"Y"
    });
    wkCtrAc.setAcResultMap({
        "consultCompUserDetailDTO.wkctrId":"wkctr_id"
    });
    wkCtrAc.init();
    
    //설비종류 자동완성
    acSysDesc("consultCompUserDetailDTO.eqCtgTypeDesc","consultCompUserDetailDTO.eqCtgTypeId","EQCTG_TYPE", true);
    
    //부서 자동완성
    deptAc = new autoC({"consultCompUserDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("consultCompUserDetailDTO.deptId");
    deptAc.setAcDConditionMap({
        "comp_no":"consultCompUserDetailDTO.compNo"
    });
    deptAc.setAcResultMap({
        "consultCompUserDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    //메뉴표시 (pop up)
    acSysDesc("consultCompUserDetailDTO.menuDisplayDesc","consultCompUserDetailDTO.menuDisplayId","MENU_DISPLAY", true);
    
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAUSER_ID');
    
    consultCompUserDetailForm.elements['consultCompUserDetailDTO.regDate'].value = getToday().replace(/-/g, "");
    
    consultCompUserDetailForm.elements['consultCompUserDetailDTO.isUseId'].value = 'Y';
    consultCompUserDetailForm.elements['consultCompUserDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('consultCompUserDetailDTO.isUseId', 'consultCompUserDetailDTO.isUseDesc', 'IS_USE', true);
}

function setSequenceVal(sequenceVal)
{
    consultCompUserDetailForm.elements['consultCompUserDetailDTO.userId'].value = sequenceVal;
    consultCompUserDetailForm.elements['consultCompUserDetailDTO.userNo'].value = sequenceVal;
    consultCompUserDetailForm.elements['consultCompUserCommonDTO.userId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
    //수정시 readonly설정 
    setDisable(document.getElementById("compDescDiv"));
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
    valUserNo();

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    if (parent.findGridRow)
        parent.findGridRow(consultCompUserDetailForm.elements['consultCompUserDetailDTO.userId'].value);
	    consultCompUserDetailForm.elements['consultCompUserCommonDTO.userId'].value = consultCompUserDetailForm.elements['consultCompUserDetailDTO.userId'].value;
		getTopPage().afterSaveAll(currentPageId);
		setTitle("consultCompUserDetailDTO.userNo","consultCompUserDetailDTO.userName");
}
    
/**
 * 비밀번호변경
 */
function goChpw(){
	
    var userId      = consultCompUserDetailForm.elements['consultCompUserCommonDTO.userId'].value;
    var userNo      = consultCompUserDetailForm.elements['consultCompUserDetailDTO.userNo'].value;
    var userName    = consultCompUserDetailForm.elements['consultCompUserDetailDTO.userName'].value;
    var compNo    = consultCompUserDetailForm.elements['consultCompUserDetailDTO.compNo'].value;
    
    chgPwLayer(userId, userNo, userName, compNo);
	
}

/**
 * 비밀번호 변경 팝업
 */
function chgPwLayer(userId,userNo,userName,compNo)
{
    var url = contextPath + "/maUserPw.do";

    var strutsVal= "<%=MaUserPwAction.USER_PW_INIT%>";
    var param = "strutsAction="+strutsVal+"&maUserCommonDTO.compNo="+compNo+"&maUserCommonDTO.userId="+userId+"&maUserPwDTO.userId="+userId+"&maUserPwDTO.userName="+userName+"&maUserPwDTO.userNo="+userNo;
    openLayerPopup("maUserPw", param);
}

/** 
 * UserNo 중복체크 
 */
function valUserNo() 
{
	var actionUrl = contextPath + "/consultCompUserDetail.do";
	var param =  "&strutsAction=" + '<%= ConsultCompUserDetailAction.DETAIL_CHECK %>'
			  +  "&" + FormQueryString(consultCompUserDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidUserNo');
	
}

var isValid = 0;
function setValidUserNo(ajaxXmlDoc) 
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    if(isValid != '0')
    {
    	closeModal();
    	consultCompUserDetailForm.elements['consultCompUserDetailDTO.userNo'].value = '';
    	consultCompUserDetailForm.elements['consultCompUserDetailDTO.userNo'].focus();
        
        alertMessage1("<bean:message key='MESSAGE.CMSG015' />");
    } else {
        if(ckCreate(currentPageId)) consultCompUserDetailForm.strutsAction.value = "<%=ConsultCompUserDetailAction.DETAIL_INPUT%>";
        else consultCompUserDetailForm.strutsAction.value = "<%=ConsultCompUserDetailAction.DETAIL_UPDATE%>";
        
        var actionUrl = contextPath + "/consultCompUserDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(consultCompUserDetailForm),'afterSave');
    }
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/consultCompUserDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="consultCompUserCommonDTO.userId" />          <!-- Key -->
        <html:hidden property="consultCompUserDetailDTO.userId" />          <!-- Key -->
        <html:hidden property="consultCompUserDetailDTO.compNo" />          <!-- 회사코드 -->
        <html:hidden property="consultCompUserDetailDTO.empId" />           <!-- 사원 ID -->
        <html:hidden property="consultCompUserDetailDTO.usrGrpId" />          <!-- 권한 ID -->
        <html:hidden property="consultCompUserDetailDTO.initMenuId" />      <!-- 초기화면 ID -->
        <html:hidden property="consultCompUserDetailDTO.shiftTypeId" />     <!-- 교대조 ID -->
        <html:hidden property="consultCompUserDetailDTO.isUseId" />         <!-- 사용여부 ID -->
        <html:hidden property="consultCompUserDetailDTO.eqLocId" />         <!-- 설비위치ID -->
        <html:hidden property="consultCompUserDetailDTO.alarmViewYnId" />   <!-- 알람여부ID -->
        <html:hidden property="consultCompUserDetailDTO.wkctrId" />         <!-- 작업그룹ID -->
        <html:hidden property="consultCompUserDetailDTO.eqCtgTypeId" />     <!-- 관리설비유형ID -->
        <html:hidden property="consultCompUserDetailDTO.deptId" />          <!-- 자주보는 부서 ID -->
        <html:hidden property="consultCompUserDetailDTO.menuDisplayId" />   <!-- 메뉴표시 ID -->
        <html:hidden property="consultCompUserDetailDTO.regDate" />         <!-- 등록일 -->
        
        <div class="article_box">
            <div class="form_box">
                <!-- 회사 -->
                <div class="field"  id="compDescDiv">
                    <label class="check"><bean:message key="LABEL.compDesc"/></label>
                    <!--label class 에 check를 넣으면 * 이 추가된다. -->
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.compDesc" tabindex="10"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 로그인 계정 ID -->
                <div class="field">
                    <label><bean:message key="LABEL.userId"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.userNo" tabindex="20"/>
                    </div>
                </div>
                <!-- 로그인 계정명 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.userName1"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.userName" tabindex="30"/>
                    </div>
                </div>
                <!-- 사원명 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.empName"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.empName" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 권한 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.userGroup"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.usrGrpDesc" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 연락처 -->
                <div class="field">
                    <label><bean:message key="LABEL.mphone"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.phone" tabindex="60"/>
                    </div>
                </div>
                <!-- 초기화면 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.menuId"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.initMenuDesc" tabindex="70"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 이메일 -->
                <div class="field">
                    <label><bean:message key="LABEL.email"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.email" tabindex="80"/>
                    </div>
                </div>
                <!-- 교대조 -->
                <div class="field">
                    <label><bean:message key="LABEL.shiftType"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.shiftTypeDesc" tabindex="90"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.isUseDesc" tabindex="100" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 설비위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.location" /></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.eqLocDesc" tabindex="110" />
                        <p class="open_spop">
                            <a> <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 알람여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.alarmViewYn" /></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.alarmViewYn" tabindex="120" />
                        <p class="open_spop">
                            <a> <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 작업그룹 -->
                <div class="field">
                    <label><bean:message key="LABEL.wkCtr" /></label> <!-- ★ 여기 Desc로 바꿔도 가능 -->
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.wkctrDesc" tabindex="130" />
                        <p class="open_spop">
                            <a> <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 관리설비유형 -->
                <div class="field">
                    <label><bean:message key="LABEL.mngEqCtgType" /></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.eqCtgTypeDesc" tabindex="140" />
                        <p class="open_spop">
                            <a> <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 자주보는부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.favoritesDept" /></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.deptDesc" tabindex="150" />
                        <p class="open_spop">
                            <a> <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 메뉴표시 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.menuDisplay" /></label>
                    <div class="input_box">
                        <html:text property="consultCompUserDetailDTO.menuDisplayDesc" tabindex="160" />
                        <p class="open_spop">
                            <a> <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
