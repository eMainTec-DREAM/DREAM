<%--===========================================================================
로그인사용자 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.consult.user.action.McUserPwAction"%>
<%@ page import="dream.consult.consult.user.action.McUserDetailAction"%>
<html:html>
<head>
<!-- 로그인사용자 -->
<title><bean:message key='LABEL.userId' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action
<!--//

var pgIdVal;
function loadPage() 
{
    setFunction();
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("mcUserDetailDTO.userNo", "mcUserDetailDTO.userName");
    setForUpdate();
    
}

function setFunction()
{
    acSysDesc("mcUserDetailDTO.isUseDesc","mcUserDetailDTO.isUse","IS_USE");
    
	/*============= Valid Dup Start ==============*/
	pgIdVal = new valCheck({"mcUserDetailDTO.userNo":"ehuser_no"});
	pgIdVal.setTable("TAEHUSER");
	pgIdVal.init();
	/*============= Valid Dup End ==============*/
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEHUSER_ID');
    mcUserDetailForm.elements['mcUserDetailDTO.isUse'].value = 'Y';
    mcUserDetailForm.elements['mcUserDetailDTO.isUseDesc'].value = 'Y';
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    //mcUserDetailForm.elements['mcUserDetailDTO.userNo'].readOnly = true;
    //document.getElementById("usrNoDiv").className = "input_read";
    
    $('input[name="mcUserDetailDTO.userNo"]').prop("readonly","true")
    .parents('.input_box')
    .removeClass('input_box')
    .addClass('input_read');
    
    pgIdVal.destroy();
}

function setSequenceVal(sequenceVal)
{
    mcUserDetailForm.elements['mcUserDetailDTO.userId'].value = sequenceVal;
    mcUserDetailForm.elements['mcUserDetailDTO.userNo'].value = sequenceVal;
    mcUserDetailForm.elements['mcUserCommonDTO.userId'].value = sequenceVal;
}

/**
 * usrGrpNo 중복 체크
 */
function valUserNo()
{
    isValid = 0;
   
    if(mcUserDetailForm.strutsAction.value == '0')
    {
        var actionUrl = contextPath + "/mcUserDetail.do";
        var param =  "&strutsAction=" + '<%= McUserDetailAction.USER_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(mcUserDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidUserNo');
    }
}

/**
 * valUserNo()실행 후 호출
 */
var isValid = 0;
function setValidUserNo(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        mcUserDetailForm.elements['mcUserDetailDTO.userNo'].value = '';
        mcUserDetailForm.elements['mcUserDetailDTO.userNo'].focus();
        
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
    if(ckCreate(currentPageId)) mcUserDetailForm.strutsAction.value = "<%=McUserDetailAction.USER_DETAIL_INPUT%>";
    else mcUserDetailForm.strutsAction.value = '<%=McUserDetailAction.USER_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/mcUserDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(mcUserDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     mcUserDetailForm.elements['mcUserCommonDTO.userId'].value = mcUserDetailForm.elements['mcUserDetailDTO.userId'].value;

     if (parent.findGridRow)	parent.findGridRow(mcUserDetailForm.elements['mcUserCommonDTO.userId'].value);
     setTitle("mcUserDetailDTO.userNo", "mcUserDetailDTO.userName");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
 /**
  * 비밀번호변경
  */
 function goChpw()
 {
    var userId      = mcUserDetailForm.elements['mcUserCommonDTO.userId'].value;
    var userNo      = mcUserDetailForm.elements['mcUserCommonDTO.userNo'].value;
    var userName    = mcUserDetailForm.elements['mcUserCommonDTO.userName'].value;
    chgPwLayer(userId, userNo, userName);
}

/**
 * 비밀번호 변경 팝업
 */
function chgPwLayer(userId,userNo,userName)
{
    var url = contextPath + "/mcUserPw.do";

    var strutsVal= "<%=McUserPwAction.USER_PW_INIT%>";
    var param = "strutsAction="+strutsVal+"&mcUserCommonDTO.userId="+userId+"&mcUserPwDTO.userId="+userId+"&mcUserPwDTO.userName="+userName+"&mcUserPwDTO.userNo="+userNo;
    openLayerPopup("mcUserPw", param);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/mcUserDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="mcUserCommonDTO.userId" />
<html:hidden property="mcUserCommonDTO.userNo" />
<html:hidden property="mcUserCommonDTO.userName" />
<html:hidden property="mcUserDetailDTO.userId" />
<html:hidden property="mcUserDetailDTO.isUse" />
<div class="article_box" id="detailBox">
    <div class="form_box">
        <div class="field">
	 	    <label class="check"><bean:message key="LABEL.userId"/></label>
			<div class="input_box">
				<html:text property="mcUserDetailDTO.userNo" tabindex="1"/>
			</div>
	   	</div>
		<div class="field"> 
			<label class="check"><bean:message key="LABEL.userName"/></label>
			<div class="input_box">
				<html:text property="mcUserDetailDTO.userName" tabindex="20"/>
			</div>
		</div>
		<div class="field">
			<label><bean:message key="LABEL.isUse"/></label>
            <div class="input_box">
                <html:text property="mcUserDetailDTO.isUseDesc" tabindex="70"/>
                <p class="open_spop">
                    <a>
                        <span>조회</span>
                    </a>
                </p>
            </div>
        </div>
		</div>
	</div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
