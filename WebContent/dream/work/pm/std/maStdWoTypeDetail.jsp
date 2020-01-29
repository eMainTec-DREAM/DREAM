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
<%@ page import="dream.work.pm.std.action.MaStdWoTypeDetailAction"%>
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
var acSysDescAc;
var isPmTypeLoad = false;   // true면 distroy 후 재로딩

function loadPage() 
{
    setTitle("maStdWoTypeDetailDTO.woTypeDesc","maStdWoTypeDetailDTO.pmTypeDesc");
    
    setForUpdate();

	//작업종류  AC
    acSysDesc("maStdWoTypeDetailDTO.woTypeDesc","maStdWoTypeDetailDTO.woType","WO_TYPE",true);
    
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
    sequenceNextVal('SQASTWRKWORK_ID');
    
    acSysDescAc.openLov();
}

/**
 * 수정
 */
function goUpdate()
{
	acPmSysDescAc = acSysDesc("maStdWoTypeDetailDTO.pmTypeDesc","maStdWoTypeDetailDTO.pmType",maStdWoTypeDetailForm.elements['maStdWoTypeDetailDTO.woType'].value+"_TYPE",true);
}

function setSequenceVal(sequenceVal)
{

	maStdWoTypeDetailForm.elements['maStdWoTypeDetailDTO.stWrkWorkId'].value = sequenceVal;
     
}


function afterAutoCmpt(code)
{
	if(code=="maStdWoTypeDetailDTO.woTypeDesc")
    {
		var listType = maStdWoTypeDetailForm.elements['maStdWoTypeDetailDTO.woType'].value+"_TYPE";

	    // 선택했던 작업형태 초기화
	    maStdWoTypeDetailForm.elements['maStdWoTypeDetailDTO.pmTypeDesc'].value = "";
	    maStdWoTypeDetailForm.elements['maStdWoTypeDetailDTO.pmType'].value = "";
	     
        setPmTypeAc(listType);
        isPmTypeLoad = true;
    }
}


//작업형태 AC
function setPmTypeAc(listType)
{
	if(isPmTypeLoad)
	 {
	     // 작업종류를 재선택한 경우 distroy 후 로드한다.
	     pmTypeAc.destroy();
	     
	     isPmTypeLoad = false;
	 }
		
	 // 작업형태
	 pmTypeAc = new autoC({"maStdWoTypeDetailDTO.pmTypeDesc":"description"});
	 pmTypeAc.setAcConditionMap({
	         "list_type":listType,
	         "is_use":"Y"
	    });
	 pmTypeAc.setTable("TACDSYSD");
	 pmTypeAc.setKeyName("maStdWoTypeDetailDTO.pmType");
	 pmTypeAc.setAcResultMap({
	     "maStdWoTypeDetailDTO.pmType":"cdsysd_no"
	 });
	 pmTypeAc.init();
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
    if(ckCreate(currentPageId)) maStdWoTypeDetailForm.strutsAction.value = "<%=MaStdWoTypeDetailAction.STD_DETAIL_INPUT%>";
    else maStdWoTypeDetailForm.strutsAction.value = '<%=MaStdWoTypeDetailAction.STD_DETAIL_UPDATE%>'; 
    
    var actionUrl = contextPath + "/maStdWoTypeDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maStdWoTypeDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     if (parent.findGridRow)	parent.findGridRow(maStdWoTypeDetailForm.elements['maStdWoTypeDetailDTO.stWrkWorkId'].value);
     setTitle("maStdWoTypeDetailDTO.woTypeDesc","maStdWoTypeDetailDTO.pmTypeDesc");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maStdWoTypeDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maStdPointCommonDTO.stWrkId"/>
	<html:hidden property="maStdWoTypeDetailDTO.stWrkWorkId"/>
	<html:hidden property="maStdWoTypeDetailDTO.woType"/>
	<html:hidden property="maStdWoTypeDetailDTO.pmType"/>

	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">

			<!-- 작업종류-->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woType"/></label>
				<div class="input_box">
					<html:text property="maStdWoTypeDetailDTO.woTypeDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>   
			<!-- 작업형태-->
			<div class="field">
				<label>작업형태</label>
				<div class="input_box">
					<html:text property="maStdWoTypeDetailDTO.pmTypeDesc" tabindex="15" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>   
			<!-- 설명-->
			<div class="field_long">
				<label>설명</label>
				<div class="input_box">
					<html:text property="maStdWoTypeDetailDTO.description" tabindex="18" />
				</div>
			</div>   
			<div class="field" style="display: none">
				<!-- 작업종류-->
				<div class="input_box">
					<html:text property="maStdWoTypeDetailDTO.empty" tabindex="20" />
					<p class="open_spop">
						<a>
						<span>조회</span></a>
					</p>
				</div>
			</div>  
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maStdWoTypeDetailDTO.remark" styleClass="ta50"  tabindex="30"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>

