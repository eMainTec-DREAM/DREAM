<%--===========================================================================
비밀번호 설정 
author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@page import="common.bean.MwareConfig"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.mgr.user.action.MaUserPwAction"%>
<jsp:useBean id="maUserPwForm" class="dream.mgr.user.form.MaUserPwForm" scope="request" />
<%
	String safetyLength = MwareConfig.getIsPwSafetyLength();
	String pwChangeHistCnt = MwareConfig.getPwChangeHistCnt();
%>
<html>
<head>
<!--비밀번호 -->
<title><bean:message key="MENU.USERPW"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!-- //

function loadPage() 
{
	// 비밀번호 변경정책 보여주기
	if("<%=safetyLength%>"=='Y')
		$('#pwChangePolicy').show();
	else
		$('#pwChangePolicy').hide();
	
	if("<%=pwChangeHistCnt%>"!='0')
		$('#pwChangeHistCnt').show();
	else
		$('#pwChangeHistCnt').hide();
	
    //setTitle("maUserPwDTO.userNo", "maUserPwDTO.userName");
    setDetailTitle("<bean:message key='LABEL.userNo'/>"
    		,maUserPwForm.elements['maUserPwDTO.userNo'].value
            ,maUserPwForm.elements['maUserPwDTO.userName'].value
            ," : "," / "); //detail 상단에 page  id/desc 세팅
    
            
    maUserPwForm.elements['maUserPwDTO.password'].value     = '';
    maUserPwForm.elements['maUserPwDTO.confirmPw'].value    = '';
    maUserPwForm.elements['maUserPwDTO.userId'].value = maUserPwForm.elements['maUserCommonDTO.userId'].value;
    maUserPwForm.elements['maUserPwDTO.compNo'].value = maUserPwForm.elements['maUserCommonDTO.compNo'].value;
    
    maUserPwForm.elements['strutsAction'].value = '<%= MaUserPwAction.USER_PW_UPDATE %>';
}

function goSave()
{

    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    

  //Safety Length 가 Y 인경우에만 해당 validation 체크한다.
	if("<%=safetyLength%>"=='Y')
	{
	    if(maUserPwForm.elements['maUserPwDTO.password'].value.length < 8 | 
	    		maUserPwForm.elements['maUserPwDTO.password'].value.length > 12)
		{	
	    	//비밀번호는 8~12자만 사용 가능합니다.
	    	alertMessage1("<bean:message key='MESSAGE.MSG0081' />"); return;
		}
	    
	
	    if(!maUserPwForm.elements['maUserPwDTO.password'].value.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)) {
	        	//비밀번호는 숫자와 영문 특수문자를 혼합해야 합니다.
	        	alertMessage1("<bean:message key='MESSAGE.MSG0082' />"); return;
	    }
	
	    // 비밀번호와 확인용 확인 비밀번호와 다름.
	    if (maUserPwForm.elements['maUserPwDTO.password'].value != maUserPwForm.elements['maUserPwDTO.confirmPw'].value)
	    {
	    	alertMessage1("<bean:message key='MESSAGE.MSG0010' />"); return;
	    }
	}
    

    var msg = '<bean:message key="MESSAGE.MSG041"/>';
    getTopPage().dhtmlx.confirm(msg, function(result){
		if(result){
			var actionUrl = contextPath + "/maUserPw.do";
		    XMLHttpPost(actionUrl, FormQueryString(maUserPwForm), 'afterSave');
		}
    });
}

function afterSave(ajaxXmlDoc)
{
    var isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != "")
    {
    	alertMessage1(isValid);
    	return;
    }

    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    var strutsAction = maUserPwForm.strutsAction.value;
    // 수정 후
    if (strutsAction == "<%=MaUserPwAction.USER_PW_UPDATE%>")
    {
        //저장되었습니다. 
        alertMessage1('<bean:message key="MESSAGE.MSG003"/>');
        
        //저장후 Close
        goCloseLayer();
    }
}

function goClose()
{
    closeLayerPopup(this);
}

/**
 * detail 상단 ID/DESC
 */
 function setDetailTitle(title,desc1,desc2,and1, and2){
    var detailTitle = document.getElementById('detailTitle');
    detailTitle.innerHTML = title+and1+desc1+and2+desc2;
}

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maUserPw.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUserCommonDTO.compNo"/>
<html:hidden property="maUserCommonDTO.userId"/>
<html:hidden property="maUserPwDTO.compNo"/>
<html:hidden property="maUserPwDTO.userId"/>
<html:hidden property="maUserPwDTO.userNo"/>
<html:hidden property="maUserPwDTO.userName"/>
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
        <div class="sheader_box">
            <div class="stitle_box" id="detailTitle"></div>
            <div class="function_box">
            	<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
					<a href="javascript:goSave();" class="b_save"><span><bean:message key="BUTTON.SAVE"/></span></a>
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
            </div>
        </div><!--sheader_box end-->
        <div class="article_box">
            <div class="form_box">
            <div class="field">
                    <!-- 신규비밀번호 -->
                    <label class="check"><bean:message key="LABEL.password"/></label>
                    <div class="input_box">
                        <html:password property="maUserPwDTO.password" tabindex="20"/>
                    </div>
                </div>
                <div class="field">
                    <!-- 확인비밀번호 -->
                    <label class="check"><bean:message key="LABEL.confirmPw"/></label>
                    <div class="input_box" >
                        <html:password property="maUserPwDTO.confirmPw" tabindex="30"/>
                    </div>
                </div>
            </div>
            <ul style="margin:5px 0 20px 135px; list-style:disc; color:#777; font-size:12px;">
			 		<li id = "pwChangeHistCnt" style="margin:4px 0;"><label><bean:message key="MESSAGE.MSG0251"/><%=pwChangeHistCnt%><bean:message key="MESSAGE.MSG0252"/></label></li>
			 		<li id = "pwChangePolicy" style="margin:4px 0;"><label><label><bean:message key="MESSAGE.MSG220"/></label></li>
			</ul>
        </div><!--article_box end-->
    </div> <!--  end section_wrap -->
</html:form> 
</body>
</html>