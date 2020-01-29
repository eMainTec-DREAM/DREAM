<%--===========================================================================
작업그룹 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.wrkgrp.action.MaWkCtrDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 작업그룹 -->
<title><bean:message key='LABEL.wkCtrNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

var wkCtrDescAc;
var isUseAc;
function loadPage() 
{
	//alert(maWkCtrDetailForm.elements['maWkCtrDetailDTO.tWcodeId'].value);
	if(ckCreate(currentPageId)) goInput();
	else 
    {
        goUpdate();
    }
	
    setTitle("maWkCtrDetailDTO.wkCtrNo", "maWkCtrDetailDTO.wkCtrDesc");
    //For Save
    setForUpdate();
    
    /** 작업그룹  */
    wkCtrDescAc = new autoC({"maWkCtrDetailDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setKeyName("maWkCtrDetailDTO.wkCtrDesc");
    wkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_use":"Y"
       });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
    	"maWkCtrDetailDTO.wkCtrDesc":"description"
    });
    wkCtrDescAc.init();
    
    
    /**상위 작업그룹  */
	wkCtrDescAc = new autoC({"maWkCtrDetailDTO.paWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
			"is_use":"Y"
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setKeyName("maWkCtrDetailDTO.paWkCtrId");
    wkCtrDescAc.setAcResultMap({
        "maWkCtrDetailDTO.paWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();

    /** 사용여부  */
    acSysDesc("maWkCtrDetailDTO.isUse","maWkCtrDetailDTO.isUse","IS_USE",true);
}

/**
 * 작업그룹No(WkCtr No) 중복 체크
 */
function valWkCtrNo()
{
	if(maWkCtrDetailForm.strutsAction.value == '0')
	{
		var actionUrl = contextPath + "/maWkCtrDetail.do";
		var param =  "&strutsAction=" + '<%= MaWkCtrDetailAction.WKCTR_DETAIL_CHECK %>'
				  +  "&" + FormQueryString(maWkCtrDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setValidWkCtrNo');
	}
}

/**
 * valWkCtrNo()실행 후 호출
 */
var isValid = 0;
function setValidWkCtrNo(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        maWkCtrDetailForm.elements['maWkCtrDetailDTO.wkCtrNo'].value = '';
        maWkCtrDetailForm.elements['maWkCtrDetailDTO.wkCtrNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAWKCTR_ID');
    
    maWkCtrDetailForm.elements['maWkCtrDetailDTO.isUse'].value = 'Y';
    maWkCtrDetailForm.elements['maWkCtrDetailDTO.paWkCtrId'].value = maWkCtrDetailForm.elements['maWkCtrCommonDTO.detailPaWkCtrId'].value;
    maWkCtrDetailForm.elements['maWkCtrDetailDTO.paWkCtrDesc'].value = maWkCtrDetailForm.elements['maWkCtrCommonDTO.detailPaWkCtrDesc'].value;
}

/**
 * 수정
 */
function goUpdate()
{
    setDisable(document.getElementById("wkCtrNoDiv"));
}

/**
 * set Id 
 */
function setSequenceVal(sequenceVal)
{
    maWkCtrDetailForm.elements['maWkCtrDetailDTO.wkCtrId'].value = sequenceVal;
    maWkCtrDetailForm.elements['maWkCtrDetailDTO.wkCtrNo'].value = sequenceVal;
    maWkCtrDetailForm.elements['maWkCtrCommonDTO.wkCtrId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) maWkCtrDetailForm.strutsAction.value = "<%=MaWkCtrDetailAction.WKCTR_DETAIL_INPUT%>";
    else maWkCtrDetailForm.strutsAction.value = "<%=MaWkCtrDetailAction.WKCTR_DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/maWkCtrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWkCtrDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maWkCtrDetailForm.elements['maWkCtrDetailDTO.wkCtrId'].value = maWkCtrDetailForm.elements['maWkCtrCommonDTO.wkCtrId'].value;
    parent.goSearch();
    getTopPage().afterSaveAll(currentPageId);

    goUpdate();
    setTitle("maWkCtrDetailDTO.wkCtrNo", "maWkCtrDetailDTO.wkCtrDesc");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maWkCtrDetailForm.elements['maWkCtrDetailDTO.wkCtrId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/maWkCtrDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWkCtrCommonDTO.wkCtrId" />
<html:hidden property="maWkCtrCommonDTO.detailPaWkCtrId"/><!-- Key -->
<html:hidden property="maWkCtrCommonDTO.detailPaWkCtrDesc"/><!-- Key -->
<html:hidden property="maWkCtrDetailDTO.wkCtrId" /><!-- key -->
<html:hidden property="maWkCtrDetailDTO.paWkCtrId" />
	 <div class="article_box">
            <div class="form_box">
            <!--작업그룹NO -->
            	 <div class="field" id="wkCtrNoDiv">
            	 	<label class="check"><bean:message key="LABEL.wkCtrNo"/></label>
            	 	<div class="input_box">
            	 		<html:text property="maWkCtrDetailDTO.wkCtrNo" 
            	 		    onblur="valWkCtrNo();" tabindex="10"/> 
            	 	</div>
            	 </div>
            	 <!--상위작업그룹  -->
            	 <div class="field">
            	 	<label><bean:message key="LABEL.pWkCtr"/></label>
                    <div class="input_box">
                        <html:text property="maWkCtrDetailDTO.paWkCtrDesc" tabindex="20"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                     </div>
            	 </div>
            	 <!--작업그룹  -->
            	 <div class="field">
            	 	<label class="check"><bean:message key="LABEL.wkCtr"/></label>
            	 	<div class="input_box">
            	 		<html:text property="maWkCtrDetailDTO.wkCtrDesc" tabindex="30"/>
            	 	</div>
            	 </div>
            	 <!--사용여부  -->
                <div class="field">
	                <label><bean:message key="LABEL.isUse"/></label>
	                <div class="input_box">
	                    <html:text property="maWkCtrDetailDTO.isUse" tabindex="40"/>
	                    <p class="open_spop">
	                        <a href="javascript:lovTable('maWkCtrDetailDTO.isUse', 'maWkCtrDetailDTO.isUse','YN');">
	                            <span>조회</span>
	                        </a>
	                    </p>
	                </div>
	             </div>
	             <!-- 정렬값 -->
            	 <div class="field">
            	 	<label><bean:message key="LABEL.ordNo"/></label>
            	 	<div class="input_box">
            	 		<html:text property="maWkCtrDetailDTO.ordNo" tabindex="50" />
            	 	</div>
            	 </div>
				<!-- 작업상세내용 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="maWkCtrDetailDTO.remark" styleClass="ta50" tabindex="60" />
					</div>
				</div>
            </div> <!-- End of Form_box -->
        </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
