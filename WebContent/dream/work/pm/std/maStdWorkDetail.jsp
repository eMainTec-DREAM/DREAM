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
<%@ page import="dream.work.pm.std.action.MaStdWorkDetailAction"%>
<html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action
<!--//

var checkTypeAc;
var isActiveAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maStdWorkDetailDTO.woDesc");
    
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQASTWRKWORKPRC_ID');

}

/**
 * 수정
 */
function goUpdate()
{
}

function setSequenceVal(sequenceVal)
{	
	maStdWorkDetailForm.elements['maStdWorkDetailDTO.stWrkWorkPrcId'].value = sequenceVal;
     
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
    if(ckCreate(currentPageId)) maStdWorkDetailForm.strutsAction.value = "<%=MaStdWorkDetailAction.STD_DETAIL_INPUT%>";
    else maStdWorkDetailForm.strutsAction.value = '<%=MaStdWorkDetailAction.STD_DETAIL_UPDATE%>'; 
    
    var actionUrl = contextPath + "/maStdWorkDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maStdWorkDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     if (parent.findGridRow)	parent.findGridRow(maStdWorkDetailForm.elements['maStdWorkDetailDTO.stWrkWorkPrcId'].value);
     setTitle("maStdWorkDetailDTO.woDesc");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maStdWorkDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maStdPointCommonDTO.stWrkId"/>
	<html:hidden property="maStdWorkDetailDTO.stWrkWorkPrcId"/>
	<html:hidden property="maStdWorkDetailDTO.stWrkWorkId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">

			<!-- 점검순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_box">
					<html:text property="maStdWorkDetailDTO.stepNum" tabindex="10" styleClass="num"/>
				</div>
			</div>
			<!-- 작업내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.woRemark"/></label>
				<div class="input_box">
					<html:text property="maStdWorkDetailDTO.woDesc" tabindex="40"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maStdWorkDetailDTO.remark" styleClass="ta50"  tabindex="120"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>

