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
<%@ page import="dream.mgr.user.action.MgrUserPlantauthDetailAction"%>
<html:html>
<head>
<!-- 로그인사용자 -->
<title><bean:message key='LABEL.userId' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action

var plantAc;

function loadPage() 
{
	
	// 공장코드
	plantAc = new autoC({"mgrUserPlantauthDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAUSRPLANTAUTH");
    plantAc.setKeyName("mgrUserPlantauthDetailDTO.plant");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "mgrUserPlantauthDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    setTitle("mgrUserPlantauthDetailDTO.plantDesc");
    
    setForUpdate();
    
    // 사용여부
    acSysDesc("mgrUserPlantauthDetailDTO.isAuth","mgrUserPlantauthDetailDTO.isAuth","IS_USE", true);
    
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
    sequenceNextVal('sqausrplantauth_id');
    
    mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.isAuth'].value = "Y";
    mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.isAuth'].value = "Y";
    valSysDir('mgrUserPlantauthDetailDTO.isAuth', 'mgrUserPlantauthDetailDTO.isAuth', 'IS_USE', true);
    
    plantAc.openLov();
}

/**
 * 수정
 */
function goUpdate()
{
	
}

function setSequenceVal(sequenceVal)
{
    mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.usrplantauthId'].value = sequenceVal;
    mgrUserPlantauthDetailForm.elements['maUserCommonDTO.usrPlantauthId'].value = sequenceVal;
}

function valPlant()
{
	isValid = 0;
	
	var actionUrl = contextPath + "/mgrUserPlantauthDetail.do";
    var param =  "&strutsAction=" + '<%= MgrUserPlantauthDetailAction.DETAIL_CHECK %>'
              +  "&" + FormQueryString(mgrUserPlantauthDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterValPlant');
}

var isValid = 0;
function afterValPlant(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(isValid != '0')
    {
		mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.plant'].value = ""; 
		mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.plantDesc'].value = ""; 
		mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.plantDesc'].focus(); 
		
		closeModal();
        alertMessage1("<bean:message key='MESSAGE.MSG0266' />");
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
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
//     if(ckCreate(currentPageId))
		valPlant();
// 	else
//     	goSaveAfterValid();
}

function goSaveAfterValid()
{
    if(ckCreate(currentPageId)) mgrUserPlantauthDetailForm.strutsAction.value = "<%=MgrUserPlantauthDetailAction.USERPLANT_DETAIL_INPUT%>";
    else mgrUserPlantauthDetailForm.strutsAction.value = '<%=MgrUserPlantauthDetailAction.USERPLANT_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/mgrUserPlantauthDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(mgrUserPlantauthDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     mgrUserPlantauthDetailForm.elements['maUserCommonDTO.usrPlantauthId'].value = mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.usrplantauthId'].value;

     if (parent.findGridRow)	parent.findGridRow(mgrUserPlantauthDetailForm.elements['maUserCommonDTO.usrPlantauthId'].value);
     setTitle("mgrUserPlantauthDetailDTO.plantDesc");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
/**
 *  Audit trail
 */
function goAudtrailLink()
{
    var objectId = mgrUserPlantauthDetailForm.elements['mgrUserPlantauthDetailDTO.usrplantauthId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
}


</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/mgrUserPlantauthDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maUserCommonDTO.compNo" />
<html:hidden property="maUserCommonDTO.userId" />
<html:hidden property="maUserCommonDTO.userNo" />
<html:hidden property="maUserCommonDTO.userName" />
<html:hidden property="maUserCommonDTO.usrPlantauthId" />
<html:hidden property="mgrUserPlantauthDetailDTO.userId" />
<html:hidden property="mgrUserPlantauthDetailDTO.usrplantauthId" />
<html:hidden property="mgrUserPlantauthDetailDTO.plant" />

<div class="article_box" id="detailBox">
    <div class="form_box">
	   	<div class="field">
	        <label class="check"><bean:message key="LABEL.plantDesc"/></label> 
            <div class="input_box">
                <html:text property="mgrUserPlantauthDetailDTO.plantDesc" tabindex="10"/>
                <p class="open_spop"><a><span>조회</span></a></p>
            </div>
	    </div>
		<div class="field">
		    <label class="check"><bean:message key="LABEL.isChkauth"/></label>
            <div class="input_box">
                <html:text property="mgrUserPlantauthDetailDTO.isAuth" tabindex="40"/>
                <p class="open_spop"><a><span>조회</span></a></p>
            </div>
		</div>
		
		<!-- 비고 -->
		<div class="field_long">
			<label><bean:message key="LABEL.remark"/></label>
			<div class="input_box">
				<html:textarea property="mgrUserPlantauthDetailDTO.remark" styleClass="ta50" tabindex="250" />
			</div>
		</div>
		
	</div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
