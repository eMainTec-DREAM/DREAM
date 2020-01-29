<%--===========================================================================
구매입고 - 상세
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.tool.rec.action.MaPttRecDetailAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 구매입고 : 품번 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var deptAc, mainMngAc,partNameAc,vendorDescAc, wareHouseAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maPttRecDetailDTO.partNo", "maPttRecDetailDTO.partNameSize");
	
	setForUpdate();
	
	setFunction();
}

function setFunction()
{
    partNameAc = new autoC({"maPttRecDetailDTO.partNo":"part_no"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcResultMap({
        "maPttRecDetailDTO.partNameSize":"partNameSize"
        ,"maPttRecDetailDTO.partId":"part_id"
    })
    partNameAc.init();
    
    wareHouseAc = new autoC({"maPttRecDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttRecDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
	deptAc = new autoC({"maPttRecDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttRecDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttRecDetailDTO.inspectorName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPttRecDetailDTO.deptId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttRecDetailDTO.inspector":"emp_id"
    });
    mainMngAc.init();
    
    
    vendorDescAc = new autoC({"maPttRecDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "maPttRecDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
}
	
function goUpdate()
{
    //수정시 readonly설정 
    //maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  
    // 버튼 활성화 
    var prRecListStatus = maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListStatus'].value;

    if(prRecListStatus == "W") // 작성중 
    {
	    $(document).find('.b_rec_complete').show();
	    $(document).find('.b_rec_cancel').hide();
	    $(document).find('.b_save').show();

	    // 입력 Form disable
        setEnable($(".form_box"));
    }
    else if(prRecListStatus == "C") // 입고완료 
    {
        $(document).find('.b_rec_complete').hide();
        $(document).find('.b_rec_cancel').show();
        $(document).find('.b_save').hide();

        // 입력 Form disable
        setDisable($(".form_box"));
    }
    else
   	{
    	$(document).find('.b_save').show();
   	}
}

function goInput()
{ 
	sequenceNextVal('SQAPRRECLIST_ID');
	
	maPttRecDetailForm.elements['maPttRecDetailDTO.recDate'].value = getToday(); 
	maPttRecDetailForm.elements['maPttRecDetailDTO.deptId'].value = loginUser.deptId;
    maPttRecDetailForm.elements['maPttRecDetailDTO.deptDesc'].value = loginUser.deptDesc;
    maPttRecDetailForm.elements['maPttRecDetailDTO.inspector'].value = loginUser.empId;
    maPttRecDetailForm.elements['maPttRecDetailDTO.inspectorName'].value = loginUser.empName;
    maPttRecDetailForm.elements['maPttRecDetailDTO.wcodeId'].value = loginUser.twcodeId;
    maPttRecDetailForm.elements['maPttRecDetailDTO.wname'].value = loginUser.twname;
	//maPttRecDetailForm.elements['maPttRecDetailDTO.recQty'].value = "0"; 
	//maPttRecDetailForm.elements['maPttRecDetailDTO.unitPrice'].value = "0"; 
	//maPttRecDetailForm.elements['maPttRecDetailDTO.totPrice'].value = "0"; 

	// 입고상태 : W=작성중
	maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListStatus'].value = "W"; 
	valSysDirCode('maPttRecDetailDTO.prRecListStatus', 'maPttRecDetailDTO.prRecListStatusDesc', 'PRRECLIST_STATUS', true);

	// 버튼 비활성화 
	$(document).find('.b_rec_complete').hide();
	$(document).find('.b_rec_cancel').hide();
}

function setSequenceVal(sequenceVal)
{
	maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListId'].value = sequenceVal;
	maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListNo'].value = sequenceVal;
	maPttRecDetailForm.elements['maPttRecCommonDTO.prRecListId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
	// 입고완료된 정보일 경우 "저장" 불가.
	if(!ckCreate(currentPageId) 
			&& maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListStatus'].value == 'C') 
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0012' />");
		return;
	}
		
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPttRecDetailForm.strutsAction.value = '<%=MaPttRecDetailAction.PTREC_DETAIL_INPUT%>';
	else maPttRecDetailForm.strutsAction.value = '<%=MaPttRecDetailAction.PTREC_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPttRecDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPttRecDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListId'].value = maPttRecDetailForm.elements['maPttRecCommonDTO.prRecListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("maPttRecDetailDTO.partNo", "maPttRecDetailDTO.partNameSize");
}
 
function sumTotPrice()
{
	 var unitPrice = maPttRecDetailForm.elements['maPttRecDetailDTO.unitPrice'].value;
	 var recQty = maPttRecDetailForm.elements['maPttRecDetailDTO.recQty'].value;
	
	 var result = intToData(recQty) * intToData(unitPrice);
	 maPttRecDetailForm.elements['maPttRecDetailDTO.totPrice'].value = result;
	 setMoneyFormat(maPttRecDetailForm.elements['maPttRecDetailDTO.totPrice'], "3");
}

/**
 * 입고완료 처리 
 */
function goRec_complete()
{
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
        if(result)
        {
		    maPttRecDetailForm.strutsAction.value = '<%=MaPttRecDetailAction.PTREC_DETAIL_STATUS_UPDATE%>';
		    
		    maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListStatus'].value = "C"; // 입고완료
            maPttRecDetailForm.elements['maPttRecDetailDTO.ptRecMode'].value = "C"; // 입고완료
		    
		    var actionUrl = contextPath + "/maPttRecDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPttRecDetailForm), 'afterSaveStatus');
        }
    });
}

/**
 * 입고취소 처리 
 */
function goRec_cancel()
{
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0019'/>", function(result){
        if(result)
        {
		    maPttRecDetailForm.strutsAction.value = '<%=MaPttRecDetailAction.PTREC_DETAIL_STATUS_UPDATE%>';
		    
		    maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListStatus'].value = "W"; // 작성중
		    maPttRecDetailForm.elements['maPttRecDetailDTO.ptRecMode'].value = "R"; // 입고취소
		    
		    var actionUrl = contextPath + "/maPttRecDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPttRecDetailForm), 'afterSaveStatus');
        }
    });
}

/**
 * 입고완료/입고취소(작성중) 처리 후 호출 
 */
function afterSaveStatus()
{
	valSysDirCode('maPttRecDetailDTO.prRecListStatus', 'maPttRecDetailDTO.prRecListStatusDesc', 'PRRECLIST_STATUS', true);
	parent.goSearch();
	goUpdate();
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPttRecDetailForm.elements['maPttRecDetailDTO.prRecListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPttRecDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPttRecCommonDTO.prRecListId" />
	<html:hidden property="maPttRecDetailDTO.prRecListId" />
	<html:hidden property="maPttRecDetailDTO.prRecListStatus" />
	<html:hidden property="maPttRecDetailDTO.vendorId" />
	<html:hidden property="maPttRecDetailDTO.partId" />
	<html:hidden property="maPttRecDetailDTO.deptId" />
	<html:hidden property="maPttRecDetailDTO.wcodeId" />
	<html:hidden property="maPttRecDetailDTO.inspector" />
	<html:hidden property="maPttRecDetailDTO.ptRecMode" /><!-- 입고이력저상시 사용 -->
	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.ptRecListNo"/></label>
				<div id="ptRecListNoDiv" class="input_read">
					<html:text property="maPttRecDetailDTO.prRecListNo" tabindex="10" readonly="true" />
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.ptRecListStatus"/></label>
				<div class="input_read">
					<html:text property="maPttRecDetailDTO.prRecListStatusDesc" tabindex="20" readonly="true" />
				</div>
			</div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.recDate"/></label>
                <div class="input_box">
                    <html:text property="maPttRecDetailDTO.recDate" tabindex="100" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>			
			<div class="field">
				<label><bean:message key="LABEL.recVendor"/></label>
				<div class="input_box">
                    <html:text property="maPttRecDetailDTO.vendorDesc" tabindex="30" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
			</div>
			<div class="field">
                <label class="check"><bean:message key="LABEL.ptNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPttRecDetailDTO.partNo" tabindex="40"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
            <div class="field">
                <label><bean:message key="LABEL.manageDept"/></label>
                <div class="input_box">
                    <html:text property="maPttRecDetailDTO.deptDesc" tabindex="50"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			
            <div class="field_long">
                <label><bean:message key="LABEL.ptNameSize"/></label>
                <div class="input_read">
                    <html:text property="maPttRecDetailDTO.partNameSize" tabindex="60" readonly="true"/>
                </div>
            </div>
            <div class="field">
               <label class="check"><bean:message key="LABEL.wname"/></label>
               <div id="wnameDiv" class="input_box">
                   <html:text property="maPttRecDetailDTO.wname" tabindex="65"/>
                   <p id="wnameSchBtn" class="open_spop">
                       <a>
                           <span>조회</span>
                       </a>    
                   </p>
               </div> 
             </div>             
            <div class="field">
                <label><bean:message key="LABEL.inspector"/></label>
                <div class="input_box">
                    <html:text property="maPttRecDetailDTO.inspectorName" tabindex="68"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>           
             <div class="field">
                <label class="check"><bean:message key="LABEL.recQty"/></label>
                <div class="input_box">
                    <html:text property="maPttRecDetailDTO.recQty" tabindex="70"  
                        onblur="javascript:sumTotPrice();" styleClass="num"/>
                </div>
             </div>            			
            <div class="field">
                <label><bean:message key="LABEL.recUnitPrice"/></label>
                <div class="input_box">
                    <html:text property="maPttRecDetailDTO.unitPrice" tabindex="80" 
                        onblur="javascript:sumTotPrice();" styleClass="num"/>
                </div>
            </div>                     
            <div class="field">
                <label><bean:message key="LABEL.recTotPrice"/></label>
                <div class="input_read">
                    <html:text property="maPttRecDetailDTO.totPrice" tabindex="100" readonly="true" styleClass="num"/>
                </div>
            </div>  
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPttRecDetailDTO.remark" styleClass="ta50" tabindex="190" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>