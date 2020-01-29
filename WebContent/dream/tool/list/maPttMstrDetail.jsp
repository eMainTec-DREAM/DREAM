<%--===========================================================================
자재마스터 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.list.action.MaPttMstrDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
	User loginUser = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html:html> 
<head>
<!-- 자재마스터 -->
<title><bean:message key='LABEL.partNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

var partGroupAc;
var slideImage = new Array();
<%
if(list != null)
	for(int i= 0; list.size() > i ; i++)
	{
	    Map map = (Map)list.get(i);
%>
	slideImage.push('<%=(String)map.get("FILE_NAME")%>');
<% 
	}
%>	
function loadPage() 
{	
	$("#isSerial").hide();
	$("input[name='maPttMstrDetailDTO.isSerial']").attr("readonly",true);
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
        //goTabPage("maPtMstrVendorList");
        //goTabPage("maPtMstrEqPartList");
        //goTabPage("maPtMstrUsedDeptList");
    }
    
    setTitle("maPttMstrDetailDTO.partNo", "maPttMstrDetailDTO.description");
    
    setForUpdate();
    
    setSlideImage();
    

	partGroupAc = new autoC({"maPttMstrDetailDTO.partGroupDesc":"description"});
	partGroupAc.setAcDisplay("DESCRIPTION");
	partGroupAc.setAcConditionMap({
	    	"dir_type":"PART_GROUP",
	    	"is_use":"Y",
	    	"comp_no":loginUser.compNo
		   });
	partGroupAc.setTable("TACDUSRD");
	partGroupAc.setKeyName("maPttMstrDetailDTO.partGroup");
	partGroupAc.setAcResultMap({
	    "maPttMstrDetailDTO.partGroup":"cdusrd_no"
	});
	partGroupAc.init();
    
    acSysDesc("maPttMstrDetailDTO.isSerial","maPttMstrDetailDTO.isSerial","IS_USE", true);
    acSysDesc("maPttMstrDetailDTO.isUse","maPttMstrDetailDTO.isUse","IS_USE", true);
}

function loadSlideImages()
{
	var url = contextPath + "/maPttMstrDetail.do";

    var oldSAction = maPttMstrDetailForm.elements['strutsAction'].value;
    maPttMstrDetailForm.elements['strutsAction'].value = '<%=MaPttMstrDetailAction.PTMSTR_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(maPttMstrDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    maPttMstrDetailForm.elements['strutsAction'].value = oldSAction;
}

/**
 * 입력
 */
function goInput()
{
	$("#isSerial").show();
	$("input[name='maPttMstrDetailDTO.isSerial']").attr("readonly",false);
	sequenceNextVal('SQAPART_ID');
    maPttMstrDetailForm.elements['maPttMstrDetailDTO.isUse'].value = 'Y';
    maPttMstrDetailForm.elements['maPttMstrDetailDTO.partCateg'].value = 'TOOL';
    maPttMstrDetailForm.elements['maPttMstrDetailDTO.partCategDesc'].value = '공기구';
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maPttMstrDetailForm.elements['maPttMstrDetailDTO.partNo'].readOnly = true;
    document.getElementById("partNoDiv").className = "input_read"; 
}

function setSequenceVal(sequenceVal)
{
	maPttMstrDetailForm.elements['maPttMstrDetailDTO.partId'].value = sequenceVal;
    maPttMstrDetailForm.elements['maPttMstrCommonDTO.partId'].value = sequenceVal;
	maPttMstrDetailForm.elements['maPttMstrDetailDTO.partNo'].value = sequenceVal;
    
    //goTabPage("maPtMstrVendorList");
    //goTabPage("maPtMstrEqPartList");
    //goTabPage("maPtMstrUsedDeptList");
}

/**
 * partNo 세팅
 */
function setNoVal(nextNo){
	 maPttMstrDetailForm.elements['maPttMstrDetailDTO.partNo'].value = nextNo;
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId)
{
    var form = document.maPttMstrDetailForm;
    
    if(pageId == "maDocLibList" || pageId == "maPttMstrDocLibList")
    {   
    	maPttMstrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maPttMstrDetailForm.elements['maPttMstrCommonDTO.partId'].value;
    	maPttMstrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PTTMSTR";  //PTMSTR
    	maPttMstrDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  // 3등급 기본세팅 
    	maPttMstrDetailForm.elements['maDocLibCommonDTO.description'].value = maPttMstrDetailForm.elements['maPttMstrDetailDTO.description'].value;  
        goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
    }
    else
    goCommonTabPage(form, '' , pageId);
}

/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=MaPttMstrDetailAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" +maPttMstrDetailForm.elements['maPttMstrCommonDTO.partId'].value +
                "&" + "maDocImgCommonDTO.objectType=PTTMSTR"+
                "&" + "maDocImgCommonDTO.objectDesc="+ maPttMstrDetailForm.elements['maPttMstrDetailDTO.description'].value +
                "&" + "maDocImgCommonDTO.regId="+ loginUser.empId+
                "&" + "maDocImgCommonDTO.regDesc="+ loginUser.userName+
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}

/**
 * partNo 중복 체크
 */
function varPartNo()
{
	isValid = 0;
	if(maPttMstrDetailForm.strutsAction.value == '0')
	{
		var actionUrl = contextPath + "/maPttMstrDetail.do";
		var param =  "&strutsAction=" + '<%= MaPttMstrDetailAction.PTMSTR_DETAIL_CHECK %>'
				  +  "&" + FormQueryString(maPttMstrDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setValidDirType');
	}
}

/**
 * varPartNo()실행 후 호출
 */
var isValid = 0;
function setValidDirType(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        maPttMstrDetailForm.elements['maPttMstrDetailDTO.partNo'].value = '';
        maPttMstrDetailForm.elements['maPttMstrDetailDTO.partNo'].focus();
        
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
    
    //fullDesc set
    maPttMstrDetailForm.elements['maPttMstrDetailDTO.fullDesc'].value = maPttMstrDetailForm.elements['maPttMstrDetailDTO.description'].value
                                                                     + ","
                                                                     + maPttMstrDetailForm.elements['maPttMstrDetailDTO.ptSize'].value
                                                                     + ","
                                                                     + maPttMstrDetailForm.elements['maPttMstrDetailDTO.vendorPtCode'].value;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maPttMstrDetailForm.strutsAction.value = "<%=MaPttMstrDetailAction.PTMSTR_DETAIL_INPUT%>";
    else maPttMstrDetailForm.strutsAction.value = '<%=MaPttMstrDetailAction.PTMSTR_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maPttMstrDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPttMstrDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maPttMstrDetailForm.elements['maPttMstrCommonDTO.partId'].value = maPttMstrDetailForm.elements['maPttMstrDetailDTO.partId'].value;
     if (parent.findGridRow)	parent.findGridRow(maPttMstrDetailForm.elements['maPttMstrCommonDTO.partId'].value);
     getTopPage().afterSaveAll(currentPageId);
     
     goUpdate();
     setTitle("maPttMstrDetailDTO.partNo", "maPttMstrDetailDTO.description");
     
 }
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPttMstrDetailForm.elements['maPttMstrDetailDTO.partId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPttMstrDetail" >
	<html:hidden property="currentPageId"/>
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPttMstrCommonDTO.compNo" />
	<html:hidden property="maPttMstrCommonDTO.partId" />
    <html:hidden property="maPttMstrDetailDTO.partId" />
    <html:hidden property="maPttMstrDetailDTO.plfType" />
    <html:hidden property="maPttMstrDetailDTO.plfTypeDesc" />
    <html:hidden property="maPttMstrDetailDTO.mroType" />
    <html:hidden property="maPttMstrDetailDTO.fullDesc" />
    <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
    <html:hidden property="maDocLibCommonDTO.objectType" />
    <html:hidden property="maDocLibCommonDTO.securGrade" />
    <html:hidden property="maDocLibCommonDTO.description" />
    <html:hidden property="maPttMstrDetailDTO.partGroup" />
    <html:hidden property="maPttMstrDetailDTO.partCateg" />
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
        	 	<div id="partNoDiv" class="input_read">
        	 		<html:text property="maPttMstrDetailDTO.partNo" tabindex="10" readonly="true"/>
        	 	</div>
        	 </div>
        	 <!-- 사진 -->
             <div class="field_img">
				<label><bean:message key="LABEL.photo"/></label>
				<div class="img_box" name="maPttMstrDetailDTO.imgslide">
					<div class="function_box manual"><div class="fb_group1"><a class="b_photo"></a></div> </div>
					<div class="img_prev">
						<a></a>
					</div>
					<div class="img_next">
						<a></a>
					</div>
					
				</div>
             </div>
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.partDesc"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.description" tabindex="20"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptSize"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.ptSize" tabindex="30"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.maker"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.maker" tabindex="40"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.model"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.model" tabindex="50"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.seller"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.seller" tabindex="60"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.lastPrice"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.lastPrice" tabindex="70"  styleClass="num"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.leadTime"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.leadTime" tabindex="90"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptUsage"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.usage" tabindex="100"/>
        	 	</div>
        	 </div>
        	 <div class="field">
                <label><bean:message key="LABEL.isUse"/></label>
                <div class="input_box">
                    <html:text property="maPttMstrDetailDTO.isUse" tabindex="110"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.kind"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.kind" tabindex="130"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.varClass"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.varClass" tabindex="140"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.partGroup"/></label>
        	 	<div class="input_box">
 					<html:text property="maPttMstrDetailDTO.partGroupDesc" tabindex="150"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label><bean:message key="LABEL.vendorPtCode"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPttMstrDetailDTO.vendorPtCode" tabindex="160"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label  class="check"><bean:message key="LABEL.partCateg"/></label>
        	 	<div class="input_read">
					<html:text property="maPttMstrDetailDTO.partCategDesc" tabindex="20"  readonly="true" />
				</div>
        	 </div>
        	 <div class="field">
        	 <label><bean:message key="LABEL.isSerial"/></label>
                <div class="input_box">
                    <html:text property="maPttMstrDetailDTO.isSerial" tabindex="110"/>
                    <p class="open_spop" id="isSerial">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
        	 </div>
        	 
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>