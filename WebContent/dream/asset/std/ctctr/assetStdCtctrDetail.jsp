<%--===========================================================================
CostCenter - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.ctctr.action.AssetStdCtctrDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- CostCenter -->
<title><bean:message key='LABEL.docCountrNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

var plantAc, deptAc;

function loadPage() 
{
	
    setTitle("assetStdCtctrDetailDTO.ctctrNo", "assetStdCtctrDetailDTO.description");
    //For Save
    setForUpdate();
    
    /**사용여부 */
    acSysDesc("assetStdCtctrDetailDTO.isUse","assetStdCtctrDetailDTO.isUseId","IS_USE",true);
    
    /**공장 */
    plantAc = new autoC({"assetStdCtctrDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("assetStdCtctrDetailDTO.plant");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetStdCtctrDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    /**부서 */
    deptAc = new autoC({"assetStdCtctrDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("assetStdCtctrDetailDTO.deptId");
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    deptAc.setAcDConditionMap({
    	"plant" : "assetStdCtctrDetailDTO.plant"
    });
    deptAc.setAcResultMap({
        "assetStdCtctrDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    /** 사용창고 */
    wareHouseAc = new autoC({"assetStdCtctrDetailDTO.wcodeDesc":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	   });
    wareHouseAc.setAcDConditionMap({
    	"plant" : "assetStdCtctrDetailDTO.plant"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setKeyName("assetStdCtctrDetailDTO.wcodeId");
    wareHouseAc.setAcResultMap({
        "assetStdCtctrDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else 
    {
        goUpdate();
    }
    
}

/**
 * CostCenter코드(Ctctr No) 중복 체크
 */
function valCtctrNo()
{
	var actionUrl = contextPath + "/assetStdCtctrDetail.do";
	var param =  "&strutsAction=" + '<%= AssetStdCtctrDetailAction.CTCTR_DETAIL_CHECK %>'
			  +  "&" + FormQueryString(assetStdCtctrDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidCtctrNo');
}

/**
 * valAssetNo()실행 후 호출
 */
var isValid;
function setValidCtctrNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
        assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.ctctrNo'].value = '';
        assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.ctctrNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
    else
    {
    	if(ckCreate(currentPageId)) assetStdCtctrDetailForm.strutsAction.value = "<%=AssetStdCtctrDetailAction.CTCTR_DETAIL_INPUT%>";
    	else assetStdCtctrDetailForm.strutsAction.value = "<%=AssetStdCtctrDetailAction.CTCTR_DETAIL_UPDATE%>";
    	
    	var actionUrl = contextPath + "/assetStdCtctrDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(assetStdCtctrDetailForm), 'afterSave');
    }
}


/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQACTCTR_ID');
    
    assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.isUse'].value = 'Y';
    assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.isUseId'].value = 'Y';
    
    plantAc.openLov();
}

/**
 * 수정
 */
function goUpdate()
{
    setDisable(document.getElementById("ctctrNoDiv"));
}

/**
 * set Id 
 */
function setSequenceVal(sequenceVal)
{
    assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.ctctrId'].value = sequenceVal;
    assetStdCtctrDetailForm.elements['assetStdCtctrCommonDTO.ctctrId'].value = sequenceVal;
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
    
    valCtctrNo();
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.ctctrId'].value = assetStdCtctrDetailForm.elements['assetStdCtctrCommonDTO.ctctrId'].value;
    if (parent.findGridRow) parent.findGridRow(assetStdCtctrDetailForm.elements['assetStdCtctrCommonDTO.ctctrId'].value);
    getTopPage().afterSaveAll(currentPageId);

    goUpdate();
    
    setTitle("assetStdCtctrDetailDTO.ctctrNo", "assetStdCtctrDetailDTO.description");
    
    logScreenTrace(assetStdCtctrDetailForm.elements['assetStdCtctrCommonDTO.ctctrId'].value, assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.ctctrNo'].value);
    
}


function goTracelog()
{
	var _pageId = currentPageId;
	var _compNo = loginUser.compNo;
	var _objectId = assetStdCtctrDetailForm.elements['assetStdCtctrCommonDTO.ctctrId'].value;
	
	var url   = contextPath + "/appTracelogList.do";
    var popWidth = 1010;
    var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "&strutsAction="+
                "&appTracelogCommonDTO.filterCompNo="+_compNo+
                "&appTracelogCommonDTO.filterObjectId="+_objectId+
                "&appTracelogCommonDTO.filterFileName="+_pageId;
	//post 전송
	openWindowWithPost(url, "TRACELOG_LIST_POPUP", param, pos);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assetStdCtctrDetailForm.elements['assetStdCtctrDetailDTO.ctctrId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assetStdCtctrDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetStdCtctrCommonDTO.ctctrId" />
<html:hidden property="assetStdCtctrDetailDTO.ctctrId" /><!-- key -->
<html:hidden property="assetStdCtctrDetailDTO.plant" />
<html:hidden property="assetStdCtctrDetailDTO.deptId" />
<html:hidden property="assetStdCtctrDetailDTO.isUseId" />
<html:hidden property="assetStdCtctrDetailDTO.wcodeId"/>
	 
	 <div class="article_box">
            <div class="form_box">
            	 <!--CostCenter코드  -->
            	 <div class="field" id="ctctrNoDiv">
            	 	<label class="check"><bean:message key="LABEL.ctctrCode"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdCtctrDetailDTO.ctctrNo"  tabindex="10"/> 
            	 	</div>
            	 </div>
	             <!--사용여부  -->
                 <div class="field">
	                <label><bean:message key="LABEL.isUse"/></label>
	                <div class="input_box">
	                    <html:text property="assetStdCtctrDetailDTO.isUse" tabindex="20"/>
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	             </div>
            	 <!--CostCenter명  -->
            	 <div class="field_long">
            	 	<label class="check"><bean:message key="LABEL.ctctrName"/></label>
            	 	<div class="input_box">
            	 		<html:text property="assetStdCtctrDetailDTO.description" tabindex="30"/>
            	 	</div>
            	 </div>
            	 <!-- 공장명  -->
                 <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
			 	 		<html:text property="assetStdCtctrDetailDTO.plantDesc" tabindex="40" />
			 	 		<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                 </div>
            	 <!-- 부서명  -->
                 <div class="field">
                    <label><bean:message key="LABEL.deptDesc"/></label>
                    <div class="input_box">
			 	 		<html:text property="assetStdCtctrDetailDTO.deptDesc" tabindex="50" />
			 	 		<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                 </div>
            	 <!--비고  -->
            	 <div class="field_long">
            	 	<label><bean:message key="LABEL.remark"/></label>
            	 	<div class="input_box">
            	 		<html:textarea property="assetStdCtctrDetailDTO.remark" styleClass="ta100" tabindex="80"/>
            	 	</div>
            	 </div>
            	<!-- 창고 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.useWareHouse"/></label>
					<div class="input_box">
						<html:text property="assetStdCtctrDetailDTO.wcodeDesc" tabindex="70"/>
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
