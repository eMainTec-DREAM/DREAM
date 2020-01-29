<%--===========================================================================
자재출고확정
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.wo.action.PartIssWoItemDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자재출고확정 -->
<title><bean:message key="LABEL.serialNo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//serialCheck 유무
var isValid;
var serialAc;

function loadPage() 
{
	
	
    if(ckCreate(currentPageId)) goInput();
    
    setTitle("partIssWoItemListDTO.partNo","partIssWoItemListDTO.partDesc");
    setForUpdate();
    goUpdate();
    
    serialAc=new autoC({"partIssWoItemDetailDTO.serialNo":"serialNo"});
    serialAc.setAcConditionMap({
    	"comp_no": loginUser.compNo
       ,"serial_no":  partIssWoItemDetailForm.elements['partIssWoItemDetailDTO.serialNo'].value
       ,"part_id":  partIssWoItemDetailForm.elements['partIssWoItemListDTO.partId'].value
       ,"eq_status": "S"
    });
    serialAc.setTable("TAPARTEQUIP");
    serialAc.setAcDConditionMap({
    	"serial_no" : "partIssWoItemDetailDTO.serialNo"
    });
    serialAc.setAcResultMap({
        "partIssWoItemDetailDTO.serialNo" :"serialNo"
        ,"partIssWoItemDetailDTO.partId":"partId"
        ,"partIssWoItemDetailDTO.equipId":"equipId"
    }); 
    serialAc.setKeyName("partIssWoItemDetailDTO.serialNo"); 
    serialAc.init();
    
}
 
 
 
/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAPTISSLIST_ID');
}

function setSequenceVal(sequenceVal)
{
    partIssWoItemDetailForm.elements['partIssWoItemDetailDTO.ptisslistSerialId'].value = sequenceVal;
    partIssWoItemDetailForm.elements['partIssWoItemListDTO.ptisslistSerialId'].value = sequenceVal;
}

/**
 * 수정
 */
function goUpdate()
{
    
}

function goTabPage(pageId)
{
    
    var form = document.partIssWoItemDetailForm;
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
    var struts=partIssWoItemDetailForm.strutsAction.value;
    
    if(ckCreate(currentPageId)){
        //중복 작업자 체크
      goValidSerial();
      if(isValid!='') return;
    }
    
    
    
    partIssWoItemDetailForm.strutsAction.value=struts;
    if(ckCreate(currentPageId))partIssWoItemDetailForm.strutsAction.value = "<%=PartIssWoItemDetailAction.PTISS_DETAIL_INPUT%>";
    else partIssWoItemDetailForm.strutsAction.value = '<%=PartIssWoItemDetailAction.PTISS_DETAIL_UPDATE%>';
    var actionUrl = contextPath + "/partIssWoItemDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(partIssWoItemDetailForm), 'afterSave');
    
    
}

/**
 * serialNo 중복 확인.
 */
function goValidSerial(){
	
   partIssWoItemDetailForm.elements['partIssWoItemDetailDTO.serialNo'].value;
   partIssWoItemDetailForm.strutsAction.value = '<%=PartIssWoItemDetailAction.PTISS_SERIAL_CHECK%>';
   var url = contextPath + "/partIssWoItemDetail.do";
   var param =  "&strutsAction=" + '<%= PartIssWoItemDetailAction.PTISS_SERIAL_CHECK %>'
                  +  "&" + FormQueryString(partIssWoItemDetailForm);
   XMLHttpPostVal(url, param, 'afterValidSerial');
    
}
function afterValidSerial(ajaxXmlDoc)
{
	isValid = '';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    if(isValid != '')
    {
        closeModal();
        alertMessage1("<bean:message key='MESSAGE.MSG0157'/>");
        partIssWoItemDetailForm.elements['partIssWoItemDetailDTO.serialNo'].value="";
    }
}


/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	
   //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    
    if (parent.findGridRow) parent.findGridRow(partIssWoItemDetailForm.elements['partIssWoItemDetailDTO.ptisslistSerialId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setTitle("partIssWoItemListDTO.partNo","partIssWoItemListDTO.partDesc");
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/partIssWoItemDetail">
<html:hidden property="strutsAction"/>
<html:hidden property="partIssWoItemListDTO.ptisslistId"/>
<html:hidden property="partIssWoItemListDTO.equipId"/>
<html:hidden property="partIssWoItemListDTO.partId"/><!-- Key -->
<html:hidden property="partIssWoItemListDTO.partNo"/><!-- Key -->
<html:hidden property="partIssWoItemListDTO.partDesc"/><!-- Key -->
<html:hidden property="partIssWoItemListDTO.ptisslistSerialId"/><!-- Key -->
<html:hidden property="partIssWoItemDetailDTO.partId"/><!-- Key -->
<html:hidden property="partIssWoItemDetailDTO.equipId"/>
<html:hidden property="partIssWoItemDetailDTO.ptisslistSerialId"/>

<div class="article_box" id="detailBox">
        <div class="form_box">
              <!-- 시리얼 번호 -->
           <div class="field">
             <label class="check"><bean:message key="LABEL.serialNo"/></label>
                <div class="input_box">
                  <html:text property="partIssWoItemDetailDTO.serialNo" tabindex="10" />
                      <p class="open_spop">
                          <a>
                              <span>조회</span>
                          </a>
                      </p>
                </div>
          </div>
          
         <!-- 비고 -->
          <div class="field_long">
              <label><bean:message key="LABEL.remark"/></label>
              <div class="input_box">
                  <html:text property="partIssWoItemDetailDTO.remark" styleClass="ta50" tabindex="20"/>
              </div>
          </div> 

        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->

</html:form> 
</body>
</html>

