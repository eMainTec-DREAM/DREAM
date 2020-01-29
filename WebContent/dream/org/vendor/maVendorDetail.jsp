<%--===========================================================================
관련업체 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.vendor.action.MaVendorDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 관련업체 -->
<title><bean:message key='LABEL.vendorNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

var isUseAc, vendorTypeAc;

function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maVendorDetailDTO.vendorNo", "maVendorDetailDTO.description");
    
    setForUpdate();
    
    /** 사용여부  */
    acSysDesc("maVendorDetailDTO.isUse","maVendorDetailDTO.isUse","IS_USE",true);
    
    vendorTypeAc = new autoC({"maVendorDetailDTO.vendorTypeDesc":"description"});
	vendorTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"dir_type":"VENDOR_TYPE"
 	   });
	vendorTypeAc.setTable("TACDUSRD");
	vendorTypeAc.setKeyName("maVendorDetailDTO.vendorType")
    vendorTypeAc.setAcResultMap({
        "maVendorDetailDTO.vendorType":"cdusrd_no"
    });
	vendorTypeAc.init();
}

/**
 * 입력
 */
function goInput()
{
	maVendorDetailForm.elements['maVendorDetailDTO.isUse'].value = "Y";
	
    sequenceNextVal('SQAVENDOR_ID');
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maVendorDetailForm.elements['maVendorDetailDTO.vendorNo'].readOnly = true;
    document.getElementById("vendorNoDiv").className = "input_read";
}

function setSequenceVal(sequenceVal)
{
    maVendorDetailForm.elements['maVendorDetailDTO.vendorId'].value = sequenceVal;
    maVendorDetailForm.elements['maVendorCommonDTO.vendorId'].value = sequenceVal;
	maVendorDetailForm.elements['maVendorDetailDTO.vendorNo'].value = sequenceVal;
}


/**
 * vendor no 중복 체크
 */
function valVendorNo()
{
    isValid = 0;
    if(maVendorDetailForm.strutsAction.value == '0')
    {
        var actionUrl = contextPath + "/maVendorDetail.do";
        var param =  "&strutsAction=" + '<%= MaVendorDetailAction.VENDOR_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(maVendorDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidVendorNo');
    }
}

/**
 * valVendorNo()실행 후 호출
 */
var isValid = 0;
function setValidVendorNo(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        maVendorDetailForm.elements['maVendorDetailDTO.vendorNo'].value = '';
        maVendorDetailForm.elements['maVendorDetailDTO.vendorNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
}

/**
 * 저장
 */ 
function goSave()
{
    var msg = '<bean:message key="MESSAGE.MSG041"/>';

    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maVendorDetailForm.strutsAction.value = "<%=MaVendorDetailAction.VENDOR_DETAIL_INPUT%>";
    else maVendorDetailForm.strutsAction.value = '<%=MaVendorDetailAction.VENDOR_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maVendorDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maVendorDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maVendorDetailForm.elements['maVendorCommonDTO.vendorId'].value = maVendorDetailForm.elements['maVendorDetailDTO.vendorId'].value;

     if (parent.findGridRow)	parent.findGridRow(maVendorDetailForm.elements['maVendorCommonDTO.vendorId'].value);
     
     setTitle("maVendorDetailDTO.vendorNo", "maVendorDetailDTO.description");
     
     getTopPage().afterSaveAll(currentPageId);
     
     goUpdate();
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maVendorDetailForm.elements['maVendorDetailDTO.vendorId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
    <html:form action="/maVendorDetail" >
    <html:hidden property="strutsAction"/>
    <html:hidden property="currentPageId"/>
    <html:hidden property="maVendorCommonDTO.compNo" />
    <html:hidden property="maVendorCommonDTO.vendorId" />
    <html:hidden property="maVendorDetailDTO.vendorId" />
     <html:hidden property="maVendorDetailDTO.vendorType" />
    <div class="article_box">
        <div class="form_box">
        <!--업체코드  -->
             <div class="field">
                <label class="check"><bean:message key="LABEL.vendorNo"/></label>
                <div id="vendorNoDiv" class="input_box">
                    <html:text property="maVendorDetailDTO.vendorNo"  
                        onblur="valVendorNo();" tabindex="1"/>
                </div>
             </div>
             <!-- 대표자명 -->
             <div class="field">
                <label><bean:message key="LABEL.repName"/></label>
                <div class="input_box">
                    <html:text property="maVendorDetailDTO.repName" tabindex="10"/>
                </div>
             </div>
             <!-- 업체명 -->
             <div class="field">
                <label class="check"><bean:message key="LABEL.vendorDesc"/></label>
                <div class="input_box">
                    <html:text property="maVendorDetailDTO.description" tabindex="20"/>
                </div>
             </div>
             <!-- 전화번호 -->
             <div class="field">
                <label><bean:message key="LABEL.office"/></label>
                <div class="input_box">
                    <html:text property="maVendorDetailDTO.office" tabindex="30"/>
                </div>
             </div>
             <!-- 담당자명 -->
             <div class="field">
                <label><bean:message key="LABEL.person"/></label>
                <div class="input_box">
                    <html:text property="maVendorDetailDTO.person" tabindex="40"/>
                </div>
             </div>
             <!-- 이메일 -->
             <div class="field">
                <label><bean:message key="LABEL.email"/></label>
                <div class="input_box">
                    <html:text property="maVendorDetailDTO.email" tabindex="50"/>
                </div>
             </div>
             <!--휴대전화  -->
             <div class="field">
                <label><bean:message key="LABEL.mobile"/></label>
                <div class="input_box">
                    <html:text property="maVendorDetailDTO.mobile" tabindex="60"/>
                </div>
             </div>
             <!--설비자재거래처여부  -->
			<div class="field">
				<label><bean:message key="LABEL.isEqPtVendor"/></label>
				<div class="input_box">
					<html:text property="maVendorDetailDTO.isUse" tabindex="70"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!--거래처종류  -->
			<div class="field">
				<label><bean:message key="LABEL.vendorType"/></label>
				<div class="input_box">
					<html:text property="maVendorDetailDTO.vendorTypeDesc" tabindex="70"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!--주소  -->
             <div class="field_long">
                <label><bean:message key="LABEL.address"/></label>
                <div class="input_box">
                    <html:text property="maVendorDetailDTO.address" tabindex="80"/>
                </div>
             </div>
             <!-- 상세설명 -->
             <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="maVendorDetailDTO.remark" tabindex="90"/>
                </div>
             </div>
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>