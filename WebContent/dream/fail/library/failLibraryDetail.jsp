<%--===========================================================================
고장Library - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.fail.code.action.MaFailureDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 고장Library -->
<title><bean:message key='LABEL.failureNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

var eqCtgTypeAc, failurePt, failureCa, failureMo, failurePm, failureRe;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }

    setTitle("failLibraryDetailDTO.eqctgNo", "failLibraryDetailDTO.eqctgDesc");
    
    setForUpdate();

    /**설비종류  */
    eqCtgTypeAc = new autoC({"failLibraryDetailDTO.eqctgDesc":"description"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    eqCtgTypeAc.setAcResultMap({
        "failLibraryDetailDTO.eqctgId":"eqctg_id",
        "failLibraryDetailDTO.eqctgNo":"eqctg_no"
    });
	eqCtgTypeAc.setKeyName("failLibraryDetailDTO.eqctgId");
	eqCtgTypeAc.init();
    
    /** 고장부위  */
    failurePt = new autoC({"failLibraryDetailDTO.failurePtDesc":"failureDesc"});
    failurePt.setTable("TAFAILURE");
    failurePt.setAcResultMap({
        "failLibraryDetailDTO.failurePtId":"failure_id",
        "failLibraryDetailDTO.failurePtNo":"failure_no"
    });
    failurePt.setAcConditionMap({
  	   "fail_type":"PT"
  	   });
    failurePt.setKeyName("failLibraryDetailDTO.failurePtId");
    failurePt.init();
    
    
    /** 고장 원인  */
    failureCa = new autoC({"failLibraryDetailDTO.failureCaDesc":"failureDesc"});
    failureCa.setTable("TAFAILURE");
    failureCa.setAcResultMap({
        "failLibraryDetailDTO.failureCaId":"failure_id",
        "failLibraryDetailDTO.failureCaNo":"failure_no",
    });
    failureCa.setAcConditionMap({
  	   "fail_type":"CA"
  	   });
    failureCa.setKeyName("failLibraryDetailDTO.failureCaId");
    failureCa.init();
    
    /**고장현상  */
    failureMo = new autoC({"failLibraryDetailDTO.failureMoDesc":"failureDesc"});
    failureMo.setTable("TAFAILURE");
    failureMo.setAcResultMap({
        "failLibraryDetailDTO.failureMoId":"failure_id"
        ,"failLibraryDetailDTO.failureMoNo":"failure_no"
    });
    failureMo.setAcConditionMap({
  	   "fail_type":"MO"
  	   });
    failureMo.setKeyName("failLibraryDetailDTO.failureMoId");
    failureMo.init();
    
    /** 고장조치  */
    failureRe = new autoC({"failLibraryDetailDTO.failureReDesc":"failureDesc"});
    failureRe.setTable("TAFAILURE");
    failureRe.setAcResultMap({
        "failLibraryDetailDTO.failureReId":"failure_id"
        ,"failLibraryDetailDTO.failureReNo":"failure_no"
    });
    failureRe.setAcConditionMap({
  	   "fail_type":"RE"
  	   });
    failureRe.setKeyName("failLibraryDetailDTO.failureReId");
    failureRe.init();
    
    /** 예방업무  */
    failurPm = new autoC({"failLibraryDetailDTO.failurePmDesc":"failureDesc"});
    failurPm.setTable("TAFAILURE");
    failurPm.setAcResultMap({
        "failLibraryDetailDTO.failurePmId":"failure_id"
        ,"failLibraryDetailDTO.failurePmNo":"failure_no"
    });
    failurPm.setAcConditionMap({
  	   "fail_type":"PM"
  	   });
    failurPm.setKeyName("failLibraryDetailDTO.failurePmId");
    failurPm.init();
    
    
    acSysDesc("failLibraryDetailDTO.checkYn","failLibraryDetailDTO.checkYn","IS_USE", true);
    /** 사용여부  */
    acSysDesc("failLibraryDetailDTO.isUse","failLibraryDetailDTO.isUse","IS_USE", true);
}

function afterSetValue(type, _name)
{
	if(_name == "failLibraryDetailDTO.eqctgId")
	{
		eqCtgTypeAc.exec();
	}
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAFAILSETLIST_ID');
    
    failLibraryDetailForm.elements['failLibraryDetailDTO.isUse'].value = "N";
}

/**
 * 수정
 */
function goUpdate()
{

}

function setSequenceVal(sequenceVal)
{
	failLibraryDetailForm.elements['failLibraryDetailDTO.failsetlistId'].value = sequenceVal;
    failLibraryDetailForm.elements['failLibraryCommonDTO.failsetlistId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) failLibraryDetailForm.strutsAction.value = "<%=MaFailureDetailAction.FAILURE_DETAIL_INPUT%>";
    else failLibraryDetailForm.strutsAction.value = '<%=MaFailureDetailAction.FAILURE_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/failLibraryDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(failLibraryDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     failLibraryDetailForm.elements['failLibraryCommonDTO.failsetlistId'].value = failLibraryDetailForm.elements['failLibraryDetailDTO.failsetlistId'].value;

     if (parent.findGridRow) parent.findGridRow(failLibraryDetailForm.elements['failLibraryCommonDTO.failsetlistId'].value);
     getTopPage().afterSaveAll(currentPageId);
     
     setTitle("failLibraryDetailDTO.eqctgNo", "failLibraryDetailDTO.description");
     goUpdate();
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = failLibraryDetailForm.elements['failLibraryDetailDTO.failsetlistId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/failLibraryDetail" >
    <html:hidden property="strutsAction"/>
    <html:hidden property="currentPageId"/>
    <html:hidden property="failLibraryCommonDTO.compNo"/><!-- Key -->
	<html:hidden property="failLibraryCommonDTO.failsetlistId"/><!-- Key -->
	<html:hidden property="failLibraryDetailDTO.failsetlistId"/>
	<html:hidden property="failLibraryDetailDTO.eqctgId"/>
	<html:hidden property="failLibraryDetailDTO.eqctgNo"/>
	<html:hidden property="failLibraryDetailDTO.failurePtId"/> <!-- 부위 -->
	<html:hidden property="failLibraryDetailDTO.failurePtNo"/>
	<html:hidden property="failLibraryDetailDTO.failureCaId"/>
	<html:hidden property="failLibraryDetailDTO.failureCaNo"/>
	<html:hidden property="failLibraryDetailDTO.failureMoId"/>
	<html:hidden property="failLibraryDetailDTO.failureMoNo"/>
	<html:hidden property="failLibraryDetailDTO.failurePmId"/>
	<html:hidden property="failLibraryDetailDTO.failurePmNo"/>
	<html:hidden property="failLibraryDetailDTO.failureReId"/>
	<html:hidden property="failLibraryDetailDTO.failureReNo"/>
    <html:hidden property="failLibraryDetailDTO.creDate"/>
    <div class="article_box" id="detailBox">
        <div class="form_box">
        
            <!-- 설비종류 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.type"/></label>
				<div class="input_box">
					<html:text property="failLibraryDetailDTO.eqctgDesc" tabindex="10"/>
					<p class="open_spop">
						<a>
						 <span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="failLibraryDetailDTO.failurePtDesc" tabindex="20"/>
					<p class="open_spop">
						<a>
						 <span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 원인 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.reason"/></label>
				<div class="input_box">
					<html:text property="failLibraryDetailDTO.failureCaDesc" tabindex="30"/>
					<p class="open_spop">
						<a>
						 <span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 현상 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.FAILUREMONO"/></label>
				<div class="input_box">
					<html:text property="failLibraryDetailDTO.failureMoDesc" tabindex="40"/>
					<p class="open_spop">
						<a>
						 <span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 조치 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.FAILURERENO"/></label>
				<div class="input_box">
					<html:text property="failLibraryDetailDTO.failureReDesc" tabindex="50"/>
					<p class="open_spop">
						<a>
						 <span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 예방업무 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.FAILUREPMNO"/></label>
				<div class="input_box">
					<html:text property="failLibraryDetailDTO.failurePmDesc" tabindex="60"/>
					<p class="open_spop">
						<a>
						 <span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="failLibraryDetailDTO.isUse" tabindex="90"/>
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