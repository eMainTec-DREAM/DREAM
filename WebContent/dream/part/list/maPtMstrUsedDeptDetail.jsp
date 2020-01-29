<%--===========================================================================
부품사용부서
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.part.list.action.MaPtMstrUsedDeptDetailAction"%>
<html>
<head>
<!-- 부품사용부서 -->
<title><bean:message key="LABEL.equipment"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var deptAc; 
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maPtMstrUsedDeptDetailDTO.deptNo", "maPtMstrUsedDeptDetailDTO.deptDesc");
	
    deptAc = new autoC({"maPtMstrUsedDeptDetailDTO.deptNo":"dept_no"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maPtMstrUsedDeptDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maPtMstrUsedDeptDetailDTO.deptId":"dept_id"
        ,"maPtMstrUsedDeptDetailDTO.deptDesc":"description"
    });
    deptAc.init();
	
	setForUpdate();
}

function goInput()
{
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPTUSEDDEPT_ID');
	maPtMstrUsedDeptDetailForm.elements['maPtMstrUsedDeptDetailDTO.partId'].value = maPtMstrUsedDeptDetailForm.elements['maPtMstrCommonDTO.partId'].value;
}

/**
 * 수정
 */
function goUpdate()
{

}

function setSequenceVal(sequenceVal)
{
	maPtMstrUsedDeptDetailForm.elements['maPtMstrUsedDeptDetailDTO.ptUsedDeptId'].value = sequenceVal;
}

function goSave()
{
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPtMstrUsedDeptDetailForm.strutsAction.value = '<%=MaPtMstrUsedDeptDetailAction.PTMSTR_USEDDEPT_DETAIL_INPUT%>';
	else maPtMstrUsedDeptDetailForm.strutsAction.value = '<%= MaPtMstrUsedDeptDetailAction.PTMSTR_USEDDEPT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPtMstrUsedDeptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtMstrUsedDeptDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPtMstrUsedDeptDetailForm.elements['maPtMstrUsedDeptDetailDTO.ptUsedDeptId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("maPtMstrUsedDeptDetailDTO.deptNo", "maPtMstrUsedDeptDetailDTO.deptDesc");
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPtMstrUsedDeptDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPtMstrCommonDTO.partId"/>
	<html:hidden property="maPtMstrUsedDeptDetailDTO.ptUsedDeptId"/><!-- Key -->
	<html:hidden property="maPtMstrUsedDeptDetailDTO.partId"/>
	<html:hidden property="maPtMstrUsedDeptDetailDTO.deptId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
            <div class="field">
                <label class="check"><bean:message key="LABEL.deptNo"/></label>
                <div class="input_box">
                    <html:text property="maPtMstrUsedDeptDetailDTO.deptNo" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.deptDesc"/></label>
                <div class="input_read">
                    <html:text property="maPtMstrUsedDeptDetailDTO.deptDesc" tabindex="20" readonly="true" />
                </div>
            </div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>