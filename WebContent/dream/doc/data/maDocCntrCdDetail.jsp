<%--===========================================================================
일반자료실 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.data.action.MaDocCntrCdDetailAction"%>
<%@ page import="dream.doc.data.dto.MaDocCntrCdCommonDTO"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="common.bean.User"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html> 
<head>
<!-- 일반자료실 -->
<title><bean:message key='LABEL.docCntrNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//
    
/** 자동완성 변수 */
var docCntrCategTypeAc;
var eqCtgTypeAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
        goUpdate();
        //goTabPage("maDocLibList");
    }
    
    setTitle("maDocCntrCdDetailDTO.docCntrNo", "maDocCntrCdDetailDTO.description");
    
    setForUpdate();
    
    docCntrCategTypeAc = new autoC({"maDocCntrCdDetailDTO.docCntrCategDesc":"description"});
    docCntrCategTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"dir_type":"DOCCNTR_CATEG"
 	   });
    docCntrCategTypeAc.setTable("TACDUSRD");
    docCntrCategTypeAc.setKeyName("maDocCntrCdDetailDTO.docCntrCateg")
    docCntrCategTypeAc.setAcResultMap({
        "maDocCntrCdDetailDTO.docCntrCateg":"cdusrd_no"
    });
    docCntrCategTypeAc.init();
    
    eqCtgTypeAc = new autoC({"maDocCntrCdDetailDTO.eqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setKeyName("maDocCntrCdDetailDTO.eqCtgId")
    eqCtgTypeAc.setAcResultMap({
        "maDocCntrCdDetailDTO.eqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQADOCCNTR_ID');
    
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.deptId'].value = loginUser.deptId;
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.deptDesc'].value = loginUser.deptDesc;
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.regDate'].value = getToday();
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.userId'].value = loginUser.empId;
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.userName'].value = loginUser.empName;
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.docCntrNo'].readOnly = true;
    //document.getElementById("docCntrNoDiv").className = "input_read"; 
}

function setSequenceVal(sequenceVal)
{
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.docCntrId'].value = sequenceVal;
    maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.docCntrNo'].value = sequenceVal;
    maDocCntrCdDetailForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = sequenceVal;
    
    //goTabPage("maDocLibList");
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId)
{
    var form = document.maDocCntrCdDetailForm;
    
    if(pageId == "maDocCntrCdDocLibList")
    {   
        maDocCntrCdDetailForm.elements['maDocLibCommonDTO.description'].value = maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.description'].value; 
        maDocCntrCdDetailForm.elements['maDocLibCommonDTO.securGrade'].value = '3'; // 3등급 기본세팅 
        maDocCntrCdDetailForm.elements['maDocLibCommonDTO.objectId'].value = maDocCntrCdDetailForm.elements['maDocCntrCdCommonDTO.docCntrId'].value;
        maDocCntrCdDetailForm.elements['maDocLibCommonDTO.objectType'].value = maDocCntrCdDetailForm.elements['maDocCntrCdCommonDTO.objectType'].value;  //DOCCNTR_CD
        
        if(maDocCntrCdDetailForm.elements['maDocLibCommonDTO.objectType'].value == "")maDocCntrCdDetailForm.elements['maDocLibCommonDTO.objectType'].value = "DOCCNTR_CD";
        
        goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
    }
    else
    goCommonTabPage(form, '' , pageId);
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
    if(ckCreate(currentPageId)) maDocCntrCdDetailForm.strutsAction.value = "<%=MaDocCntrCdDetailAction.DOCCNTR_CD_DETAIL_INPUT%>";
    else maDocCntrCdDetailForm.strutsAction.value = '<%=MaDocCntrCdDetailAction.DOCCNTR_CD_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maDocCntrCdDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maDocCntrCdDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maDocCntrCdDetailForm.elements['maDocCntrCdCommonDTO.docCntrId'].value = maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.docCntrId'].value;
     if (parent.findGridRow)	parent.findGridRow(maDocCntrCdDetailForm.elements['maDocCntrCdCommonDTO.docCntrId'].value);
     getTopPage().afterSaveAll(currentPageId);
     goUpdate();
     setTitle("maDocCntrCdDetailDTO.docCntrNo", "maDocCntrCdDetailDTO.description");
     
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maDocCntrCdDetailForm.elements['maDocCntrCdDetailDTO.docCntrId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/maDocCntrCdDetail" >
    <html:hidden property="strutsAction"/>
    <html:hidden property="currentPageId"/>
    <html:hidden property="maDocCntrCdCommonDTO.compNo" />
    <html:hidden property="maDocCntrCdCommonDTO.docCntrId" />
	<html:hidden property="maDocCntrCdCommonDTO.docCntrType"/>
	<html:hidden property="maDocCntrCdCommonDTO.objectType"/>

    <html:hidden property="maDocCntrCdDetailDTO.docCntrId" />
    <html:hidden property="maDocCntrCdDetailDTO.docCntrType" />
    <html:hidden property="maDocCntrCdDetailDTO.eqCtgId" />
    <html:hidden property="maDocCntrCdDetailDTO.docCntrCateg" />
    <html:hidden property="maDocCntrCdDetailDTO.deptId" />
    <html:hidden property="maDocCntrCdDetailDTO.userId" />
    
    <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
    <html:hidden property="maDocLibCommonDTO.objectType" />
    <html:hidden property="maDocLibCommonDTO.description" />
    <html:hidden property="maDocLibCommonDTO.securGrade" /> 
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
             <div class="field_long">
                <label class="check"><bean:message key="LABEL.docCntrDesc"/></label>
                <div class="input_box">
                    <html:text property="maDocCntrCdDetailDTO.description" tabindex="10"/>
                </div>
             </div>
            <div class="field">
                <label><bean:message key="LABEL.docCntrCateg"/></label>
                <div class="input_box">
                    <html:text property="maDocCntrCdDetailDTO.docCntrCategDesc" tabindex="30"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
             </div>
             <div class="field">
                <label><bean:message key="LABEL.eqCtg2"/></label>
                <div class="input_box">
                    <html:text property="maDocCntrCdDetailDTO.eqCtgDesc" tabindex="20"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
             <div class="field">
                <label><bean:message key="LABEL.dept"/></label>
                <div class="input_read">
                    <html:text property="maDocCntrCdDetailDTO.deptDesc" readonly="true" tabindex="40" />
                </div>
            </div> 
            <div class="field">
                <label><bean:message key="LABEL.docCntrNo"/></label>
                <div class="input_read">
                    <html:text property="maDocCntrCdDetailDTO.docCntrNo" readonly="true" tabindex="50"/>
                </div>
             </div>
            <div class="field">
                <label><bean:message key="LABEL.docCntrRegDate"/></label>
                <div class="input_read">
                    <html:text property="maDocCntrCdDetailDTO.regDate" readonly="true" tabindex="70" />
                </div>
            </div>       
            <div class="field">
                <label><bean:message key="LABEL.regId"/></label>
                <div class="input_read">
                    <html:text property="maDocCntrCdDetailDTO.userName" readonly="true" tabindex="60" />
                </div>
            </div>  
            <!-- 내용 -->
            <div class="field_long">
                <label><bean:message key="LABEL.docCntrRemark"/></label>
                <div class="input_box">
                    <html:textarea property="maDocCntrCdDetailDTO.remark" styleClass="ta150" tabindex="70" />
                </div>
            </div>

        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>