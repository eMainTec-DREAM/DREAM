<%--===========================================================================
보전부서 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.dept.action.ConsultCompDeptDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 부서 -->
<title><bean:message key='LABEL.deptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

var deptAc;
var compAc;

function loadPage() 
{
    if(ckCreate(currentPageId))
    {
		goInput();
	}else 
    {
	  goUpdate();  
    }

    //부서 자동완성
    deptAc = new autoC({"consultCompDeptDetailDTO.pdeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcDConditionMap({
 	   "comp_no":"consultCompDeptDetailDTO.compNo"
 	   });
    deptAc.setKeyName("consultCompDeptDetailDTO.pdeptId");
    deptAc.setAcResultMap({
            "consultCompDeptDetailDTO.pdeptId":"dept_id"
        });
    deptAc.init();
    
    
	
    setTitle("consultCompDeptDetailDTO.description");
    //For Save
    setForUpdate();
	
	acSysDesc("consultCompDeptDetailDTO.isUseDesc","consultCompDeptDetailDTO.isUse","IS_USE",true);
	
	acSysDesc("consultCompDeptDetailDTO.isMaintDesc","consultCompDeptDetailDTO.isMaint","IS_USE",true);
    
}

/**
 * 부서코드(Dept No) 중복 체크
 */
function valDeptNo()
{
	if(consultCompDeptDetailForm.strutsAction.value == '0')
	{
		var actionUrl = contextPath + "/consultCompDeptDetail.do";
		var param =  "&strutsAction=" + '<%= ConsultCompDeptDetailAction.DEPT_DETAIL_CHECK %>'
				  +  "&" + FormQueryString(consultCompDeptDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setValidDeptNo');
	}
}

/**
 * valDeptNo()실행 후 호출
 */
var isValid = 0;
function setValidDeptNo(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        consultCompDeptDetailForm.elements['consultCompDeptDetailDTO.deptNo'].value = '';
        consultCompDeptDetailForm.elements['consultCompDeptDetailDTO.deptNo'].focus();
         // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQADEPT_ID');
    consultCompDeptDetailForm.elements['consultCompDeptDetailDTO.isUse'].value = 'Y';
    consultCompDeptDetailForm.elements['consultCompDeptDetailDTO.isUseDesc'].value = 'Y';
    
    compAc = new autoC({"consultCompDeptDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompDeptDetailDTO.compNo");
    compAc.setCustomLov("lovComp('consultCompDeptDetailDTO.compNo', 'consultCompDeptDetailDTO.compDesc')");
    //DB에서 리턴받을 키 값
    compAc.setAcResultMap({
            "consultCompDeptDetailDTO.compNo":"comp_no",
    });
    compAc.init();
    
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 부서코드, 부서명, 상위부서는  readonly설정 
    setDisable(document.getElementById("compDescDiv"));
    setDisable(document.getElementById("deptNoDiv"));
    
    if(compAc) compAc.destroy();
}

/**
 * set Id 
 */
function setSequenceVal(sequenceVal)
{
    consultCompDeptDetailForm.elements['consultCompDeptDetailDTO.deptId'].value = sequenceVal;
    consultCompDeptDetailForm.elements['consultCompDeptDetailDTO.deptNo'].value = sequenceVal;
    consultCompDeptDetailForm.elements['consultCompDeptCommonDTO.deptId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) consultCompDeptDetailForm.strutsAction.value = "<%=ConsultCompDeptDetailAction.DEPT_DETAIL_INPUT%>";
    else consultCompDeptDetailForm.strutsAction.value = "<%=ConsultCompDeptDetailAction.DEPT_DETAIL_UPDATE%>";
    
    var actionUrl = contextPath + "/consultCompDeptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompDeptDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow) parent.findGridRow(consultCompDeptDetailForm.elements['consultCompDeptDetailDTO.deptId'].value);

    getTopPage().afterSaveAll(currentPageId);
    setTitle("consultCompDeptDetailDTO.description");
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/consultCompDeptDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompDeptCommonDTO.deptId" />
<html:hidden property="consultCompDeptCommonDTO.filterCompNo" />
<html:hidden property="consultCompDeptDetailDTO.compNo" /><!-- key -->
<html:hidden property="consultCompDeptDetailDTO.deptId" /><!-- key -->
<html:hidden property="consultCompDeptDetailDTO.pdeptId" />
<html:hidden property="consultCompDeptDetailDTO.isUse" />
<html:hidden property="consultCompDeptDetailDTO.isMaint" />
	 <div class="article_box">
            <div class="form_box">
                <div class="field" id="compDescDiv">
                       <label class="check"><bean:message key="LABEL.compDesc"/></label>
                       <!--label class 에 check를 넣으면 회사명* << * 이 붙음 . -->
                       <div class="input_box">
                            <html:text property="consultCompDeptDetailDTO.compDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a><span>조회</span></a>
                            </p>
                       </div>
                </div>
                <div class="field" id="deptNoDiv">
            	 	<label class="check"><bean:message key="LABEL.deptNo"/></label>
            	 	<div class="input_box">
            	 		<html:text property="consultCompDeptDetailDTO.deptNo" 
            	 		    onblur="valDeptNo();" tabindex="20"/> 
            	 	</div>
            	 </div>
            	 <div class="field">
                    <label class="check"><bean:message key="LABEL.deptDesc"/></label>
                    <div class="input_box">
                        <html:text property="consultCompDeptDetailDTO.description" tabindex="30"/>
                    </div>
                 </div>
            	 <div class="field" id="pdeptDescDiv">
            	 	<label><bean:message key="LABEL.pdeptDesc"/></label>
                    <div class="input_box">
                        <html:text property="consultCompDeptDetailDTO.pdeptDesc" tabindex="40"/>
                        <p class="open_spop">
                        <a>
                        <span>조회</span></a></p>
                     </div>                    
            	 </div>
            	 <div class="field">
            	 	<label><bean:message key="LABEL.ordBy"/></label>
            	 	<div class="input_box">
            	 		<html:text property="consultCompDeptDetailDTO.ordNo" tabindex="50" styleClass="num" />
            	 	</div>
            	 </div>
                 <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="consultCompDeptDetailDTO.isUseDesc" tabindex="60" />
                       <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                 </div>
                 <div class="field">
                     <label><bean:message key="LABEL.maintDept"/></label>
                     <div class="input_box">
                        <html:text property="consultCompDeptDetailDTO.isMaintDesc" tabindex="70"/>
                         <p class="open_spop">
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