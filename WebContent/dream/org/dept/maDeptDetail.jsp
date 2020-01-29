<%--===========================================================================
보전부서 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.dept.action.MaDeptDetailAction"%>
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

<script language="javascript">// 저장 시 수행되는 action
<!-- //


var deptDescAc;
var deptCategAc;
var isUseAc;
var wareHouseAc;
var plantTypeAc;
var toolHouseAc;
var fromWcodeAc;
var toWcodeAc;

function loadPage() 
{
	
	if(ckCreate(currentPageId)) goInput();
	else 
    {
        goUpdate();
    }
	
    setTitle("maDeptDetailDTO.deptNo", "maDeptDetailDTO.description");
    //For Save
    setForUpdate();
    
    
    /** 상위부서  */
    deptDescAc = new autoC({"maDeptDetailDTO.pdeptDesc":"description"});
    deptDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptDescAc.setAcDConditionMap({
    	"plant" : "maDeptDetailDTO.plantId"
    });
    deptDescAc.setTable("TADEPT");
    deptDescAc.setKeyName("maDeptDetailDTO.pdeptId");
    deptDescAc.setAcResultMap({
    	"maDeptDetailDTO.pdeptId":"dept_id"
    	
    });
    deptDescAc.init();
    
   /**부서구분 */
    acSysDesc("maDeptDetailDTO.deptCategDesc","maDeptDetailDTO.deptCateg","DEPT_CATEG",true);
    
    /**사용여부 */
    acSysDesc("maDeptDetailDTO.isUseDesc","maDeptDetailDTO.isUse","IS_USE",true);
    
    /**보전부서여부 */
    acSysDesc("maDeptDetailDTO.isMaintDesc","maDeptDetailDTO.isMaint","IS_USE",true);
    
    /**모니터링여부 */
    acSysDesc("maDeptDetailDTO.isMonitoringDesc","maDeptDetailDTO.isMonitoring","IS_USE",true);
    
    /** 사용창고 */
    wareHouseAc = new autoC({"maDeptDetailDTO.wcodeDesc":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	});
    wareHouseAc.setAcDConditionMap({
    	"plant":"maDeptDetailDTO.plantId"
    });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setKeyName("maDeptDetailDTO.wcodeId");
    wareHouseAc.setAcResultMap({
        "maDeptDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
     /** 공장 */
    plantTypeAc = new autoC({"maDeptDetailDTO.plantDesc":"description"});
	plantTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantTypeAc.setTable("TAPLANT");
	plantTypeAc.setKeyName("maDeptDetailDTO.plantId");
	plantTypeAc.setAcResultMap({
        "maDeptDetailDTO.plantId":"plant"
    });
	plantTypeAc.init();
	
	/** 공기구창고 */
	toolHouseAc = new autoC({"maDeptDetailDTO.twcodeDesc":"wname"});
	toolHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	});
	toolHouseAc.setAcDConditionMap({
    	"plant":"maDeptDetailDTO.plantId"
    });
	toolHouseAc.setTable("TAWAREHOUSE");
	toolHouseAc.setKeyName("maDeptDetailDTO.twcodeId");
	toolHouseAc.setAcResultMap({
        "maDeptDetailDTO.twcodeId":"wcode_id"
    });
	toolHouseAc.init();
	
    /** From 부품이동창고 */
    fromWcodeAc = new autoC({"maDeptDetailDTO.fromWcodeDesc":"wname"});
    fromWcodeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART",
    	"wh_type":"LEGACY"
  	});
    fromWcodeAc.setAcDConditionMap({
    	"plant":"maDeptDetailDTO.plantId"
    });
    fromWcodeAc.setTable("TAWAREHOUSE");
    fromWcodeAc.setKeyName("maDeptDetailDTO.fromWcodeId");
    fromWcodeAc.setAcResultMap({
        "maDeptDetailDTO.fromWcodeId":"wcode_id"
    });
    fromWcodeAc.init();
    
    /** To 부품이동창고 */
    toWcodeAc = new autoC({"maDeptDetailDTO.toWcodeDesc":"wname"});
    toWcodeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART",
    	"wh_type":"DREAM"
  	});
    toWcodeAc.setAcDConditionMap({
    	"plant":"maDeptDetailDTO.plantId"
    });
    toWcodeAc.setTable("TAWAREHOUSE");
    toWcodeAc.setKeyName("maDeptDetailDTO.toWcodeId");
    toWcodeAc.setAcResultMap({
        "maDeptDetailDTO.toWcodeId":"wcode_id"
    });
    toWcodeAc.init();

    setInitVal("maDeptDetailDTO.isMaintDesc", "N");
    setInitVal("maDeptDetailDTO.isMaint", "N");
}

/**
 * 부서코드(Dept No) 중복 체크
 */
function valDeptNo()
{
	if(maDeptDetailForm.strutsAction.value == '0')
	{
		var actionUrl = contextPath + "/maDeptDetail.do";
		var param =  "&strutsAction=" + '<%= MaDeptDetailAction.DEPT_DETAIL_CHECK %>'
				  +  "&" + FormQueryString(maDeptDetailForm);
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
        maDeptDetailForm.elements['maDeptDetailDTO.deptNo'].value = '';
        maDeptDetailForm.elements['maDeptDetailDTO.deptNo'].focus();
        
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
    
    maDeptDetailForm.elements['maDeptDetailDTO.isUse'].value = 'Y';
    maDeptDetailForm.elements['maDeptDetailDTO.isUseDesc'].value = 'Y';
    
  	//부서창고로 기본 세팅
    maDeptDetailForm.elements['maDeptDetailDTO.wcodeId'].value = loginUser.wcodeId;
    maDeptDetailForm.elements['maDeptDetailDTO.wcodeDesc'].value = loginUser.wname;

    maDeptDetailForm.elements['maDeptDetailDTO.pdeptId'].value = maDeptDetailForm.elements['maDeptCommonDTO.detailPdeptId'].value;
    maDeptDetailForm.elements['maDeptDetailDTO.pdeptDesc'].value = maDeptDetailForm.elements['maDeptCommonDTO.detailPdeptDesc'].value;
  	
    //공장 기본 세팅
    maDeptDetailForm.elements['maDeptDetailDTO.plantId'].value = loginUser.plant;
    maDeptDetailForm.elements['maDeptDetailDTO.plantDesc'].value = loginUser.plantDesc;
}

/**
 * 수정
 */
function goUpdate()
{
    //수정시 부서코드, 부서명, 상위부서는  readonly설정 
    setDisable(document.getElementById("deptNoDiv"));
    //setDisable(document.getElementById("deptDescDiv"));
    //setDisable(document.getElementById("pdeptDescDiv"));
}

/**
 * set Id 
 */
function setSequenceVal(sequenceVal)
{
    maDeptDetailForm.elements['maDeptDetailDTO.deptId'].value = sequenceVal;
    maDeptDetailForm.elements['maDeptDetailDTO.deptNo'].value = sequenceVal;
    maDeptDetailForm.elements['maDeptCommonDTO.deptId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) maDeptDetailForm.strutsAction.value = "<%=MaDeptDetailAction.DEPT_DETAIL_INPUT%>";
    else maDeptDetailForm.strutsAction.value = "<%=MaDeptDetailAction.DEPT_DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/maDeptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maDeptDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maDeptDetailForm.elements['maDeptDetailDTO.deptId'].value = maDeptDetailForm.elements['maDeptCommonDTO.deptId'].value;
    parent.goSearch();
    getTopPage().afterSaveAll(currentPageId);

    goUpdate();
    setTitle("maDeptDetailDTO.deptNo", "maDeptDetailDTO.description");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maDeptDetailForm.elements['maDeptDetailDTO.deptId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maDeptDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maDeptCommonDTO.deptId" />
<html:hidden property="maDeptCommonDTO.detailPdeptId"/>
<html:hidden property="maDeptCommonDTO.detailPdeptDesc"/>
<html:hidden property="maDeptDetailDTO.compNo" /><!-- key -->
<html:hidden property="maDeptDetailDTO.deptId" /><!-- key -->
<html:hidden property="maDeptDetailDTO.pdeptId" />
<html:hidden property="maDeptDetailDTO.deptCateg" />
<html:hidden property="maDeptDetailDTO.isUse" />
<html:hidden property="maDeptDetailDTO.wcodeId"/>
<html:hidden property="maDeptDetailDTO.plantId"/>
<html:hidden property="maDeptDetailDTO.twcodeId"/>
<html:hidden property="maDeptDetailDTO.isMaint" />
<html:hidden property="maDeptDetailDTO.isMonitoring" />
<html:hidden property="maDeptDetailDTO.fromWcodeId" />
<html:hidden property="maDeptDetailDTO.toWcodeId" />
	 
	 <div class="article_box">
            <div class="form_box">
            <!--부서코드  -->
            	 <div class="field" id="deptNoDiv">
            	 	<label class="check"><bean:message key="LABEL.deptNo"/></label>
            	 	<div class="input_box">
            	 		<html:text property="maDeptDetailDTO.deptNo" 
            	 		    onblur="valDeptNo();" tabindex="1"/> 
            	 	</div>
            	 </div>
            	 <!--상위부서  -->
            	 <div class="field" id="pdeptDescDiv">
            	 	<label><bean:message key="LABEL.pdeptDesc"/></label>
                    <div class="input_box">
                        <html:text property="maDeptDetailDTO.pdeptDesc" tabindex="20"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                     </div>                    
            	 </div>
            	 <!--부서명  -->
            	 <div class="field">
            	 	<label class="check"><bean:message key="LABEL.deptDesc"/></label>
            	 	<div class="input_box">
            	 		<html:text property="maDeptDetailDTO.description" tabindex="30"/>
            	 	</div>
            	 </div>
            	 <!-- 부서구분 -->
                <div class="field">
	                <label><bean:message key="LABEL.deptCateg"/></label>
	                <div class="input_box">
                    <html:text property="maDeptDetailDTO.deptCategDesc" tabindex="40"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
	             </div>
	             
	             <!--사용여부  -->
                <div class="field">
	                <label><bean:message key="LABEL.isUse"/></label>
	                <div class="input_box">
	                    <html:text property="maDeptDetailDTO.isUseDesc" tabindex="50"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
	                </div>
	             </div>
	             <!--정렬값  -->
            	 <div class="field">
            	 	<label><bean:message key="LABEL.ordNo"/></label>
            	 	<div class="input_box">
            	 		<html:text property="maDeptDetailDTO.ordNo" tabindex="60" styleClass="num" />
            	 	</div>
            	 </div>
            	 <!-- 창고 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.useWareHouse"/></label>
					<div class="input_box">
						<html:text property="maDeptDetailDTO.wcodeDesc" tabindex="70"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공장(Plant) -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="maDeptDetailDTO.plantDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- From 부품이동창고 -->
				<div class="field">
					<label><bean:message key="LABEL.fromWcodeDesc"/></label>
					<div class="input_box">
						<html:text property="maDeptDetailDTO.fromWcodeDesc" tabindex="85"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- To 부품이동창고 -->
				<div class="field">
					<label><bean:message key="LABEL.toWcodeDesc"/></label>
					<div class="input_box">
						<html:text property="maDeptDetailDTO.toWcodeDesc" tabindex="90"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 공기구창고 -->
				<div class="field">
					<label><bean:message key="LABEL.useTWareHouse"/></label>
					<div class="input_box">
						<html:text property="maDeptDetailDTO.twcodeDesc" tabindex="95"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 보전부서여부 -->
				<div class="field">
                     <label class="check"><bean:message key="LABEL.maintDept"/></label>
                     <div class="input_box">
                        <html:text property="maDeptDetailDTO.isMaintDesc" tabindex="98"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                  	 </div>
                </div> 
                <!-- 모니터링여부 -->
				<div class="field">
                     <label><bean:message key="LABEL.isMonitor"/></label>
                     <div class="input_box">
                        <html:text property="maDeptDetailDTO.isMonitoringDesc" tabindex="100"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                  	 </div>
                </div>                
				<!-- 설비보유갯수 -->
				<div class="field">
					<label><bean:message key="LABEL.EQCNT"/></label>
					<div class="input_read">
						<html:text property="maDeptDetailDTO.eqCnt" tabindex="110" readonly="true"/>
					</div>
				</div>
            </div> <!-- End of Form_box -->
        </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
