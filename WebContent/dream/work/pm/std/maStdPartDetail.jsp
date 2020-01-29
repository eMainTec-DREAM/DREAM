<%--===========================================================================
표준항목 - 상세
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
<%@ page import="dream.work.pm.std.action.MaStdPartDetailAction"%>
<html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action
<!--//

var partNoAc;
function loadPage() 
{
    setTitle("maStdPartDetailDTO.partNo", "maStdPartDetailDTO.partDesc");
    
    setForUpdate();
    
    partNoAc = new autoC({"maStdPartDetailDTO.partNo":"part_no"});
    partNoAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "part_categ":"SPPT"
 	   });
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
        "maStdPartDetailDTO.partId":"part_id"
        ,"maStdPartDetailDTO.partDesc":"full_desc"
    });
    partNoAc.setKeyName("maStdPartDetailDTO.partId");
    partNoAc.init();
    
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
    sequenceNextVal('SQASTWRK_PART_ID');
    
    partNoAc.openLov();

}

/**
 * 수정
 */
function goUpdate()
{
}

function setSequenceVal(sequenceVal)
{

	maStdPartDetailForm.elements['maStdPartDetailDTO.stWrkPartId'].value = sequenceVal;
    
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
    if(ckCreate(currentPageId)) maStdPartDetailForm.strutsAction.value = "<%=MaStdPartDetailAction.STD_DETAIL_INPUT%>";
    else maStdPartDetailForm.strutsAction.value = '<%=MaStdPartDetailAction.STD_DETAIL_UPDATE%>'; 
    
    var actionUrl = contextPath + "/maStdPartDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maStdPartDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     if (parent.findGridRow)	parent.findGridRow(maStdPartDetailForm.elements['maStdPartDetailDTO.stWrkPartId'].value);
     setTitle("maStdPartDetailDTO.partNo", "maStdPartDetailDTO.partDesc");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maStdPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maStdPointCommonDTO.stWrkId"/>
	<html:hidden property="maStdPartDetailDTO.stWrkPartId"/>
	<html:hidden property="maStdPartDetailDTO.partId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부품번호 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.partNo"/></label>
                <div class="input_box">
                    <html:text property="maStdPartDetailDTO.partNo" tabindex="80" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
        	 </div>
        	  <!-- 부품명 -->
			 <div class="field_long">
				<label><bean:message key="LABEL.partNameSize"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maStdPartDetailDTO.partDesc"  tabindex="90" readonly = "true"/>
        	 	</div>
			 </div>
			<!-- 사용수량 -->
        	 <div class="field">
        	 	<label>사용수량</label>
                <div class="input_box">
                    <html:text property="maStdPartDetailDTO.useQty" tabindex="80" styleClass="num"/>
                </div>
        	 </div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maStdPartDetailDTO.remark" styleClass="ta50" tabindex="120"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>

