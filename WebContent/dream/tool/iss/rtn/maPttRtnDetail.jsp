<%--===========================================================================
구매출고 - 상세
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
<%@ page import="dream.tool.iss.rtn.action.MaPttRtnDetailAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 구매출고 : 품번 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maPttRtnDetailDTO.partNo", "maPttRtnDetailDTO.partDesc");
	
	setForUpdate();
	setFunction();
}

function setFunction()
{
	partNameAc = new autoC({"maPttRtnDetailDTO.partNo":"part_no"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setAcDConditionMap({
    	"wcode_id" : "maPttRtnDetailDTO.wcodeId"
    });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcResultMap({
        "maPttRtnDetailDTO.partDesc":"partNameSize"
        ,"maPttRtnDetailDTO.partId":"part_id"
    })
    partNameAc.init();
    
    wareHouseAc = new autoC({"maPttRtnDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttRtnDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
	deptAc = new autoC({"maPttRtnDetailDTO.rtnDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttRtnDetailDTO.rtnDept":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttRtnDetailDTO.recByName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPttRtnDetailDTO.recDept"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttRtnDetailDTO.recBy":"emp_id"
    });
    mainMngAc.init();

    accdeptAc = new autoC({"maPttRtnDetailDTO.recDeptDesc":"description"});
    accdeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    accdeptAc.setTable("TADEPT");
    accdeptAc.setAcResultMap({
        "maPttRtnDetailDTO.recDept":"dept_id"
    });
    accdeptAc.init();
    
}
	
function goUpdate()
{
    //수정시 readonly설정 
    //maPttRtnDetailForm.elements['maPttRtnDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  
    // 버튼 활성화 
    var ptRtnStatus = maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnStatus'].value;

    if(ptRtnStatus == "W") // 작성중 
    {
	    $(document).find('.b_confirmrtn').show();
	    $(document).find('.b_save').show();

	    // 입력 Form disable
        setEnable($(".form_box"));
    }
    else if(ptRtnStatus == "C") // 반납완료 
    {
        $(document).find('.b_confirmrtn').hide();
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
	sequenceNextVal('SQAPTRTNLIST_ID');
	
	maPttRtnDetailForm.elements['maPttRtnDetailDTO.rtnDate'].value = getToday(); 
	maPttRtnDetailForm.elements['maPttRtnDetailDTO.rtnDept'].value = loginUser.deptId;
    maPttRtnDetailForm.elements['maPttRtnDetailDTO.rtnDeptDesc'].value = loginUser.deptDesc;

    //maPttRtnDetailForm.elements['maPttRtnDetailDTO.wcodeId'].value = loginUser.twcodeId;
    //maPttRtnDetailForm.elements['maPttRtnDetailDTO.wname'].value = loginUser.twname;
	//maPttRtnDetailForm.elements['maPttRtnDetailDTO.recQty'].value = "0"; 
	//maPttRtnDetailForm.elements['maPttRtnDetailDTO.unitPrice'].value = "0"; 
	//maPttRtnDetailForm.elements['maPttRtnDetailDTO.totPrice'].value = "0"; 

	// 출고상태 : W=작성중
	maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnStatus'].value = "W"; 
	valSysDirCode('maPttRtnDetailDTO.ptRtnStatus', 'maPttRtnDetailDTO.ptRtnStatusDesc', 'PTRTN_STATUS', true);

	// 버튼 비활성화 
/* 	$(document).find('.b_rec_complete').hide(); */
	$(document).find('.b_confirmrtn').hide();
}

function setSequenceVal(sequenceVal)
{
	maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnListId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
	// 반납완료된 정보일 경우 "저장" 불가.
	if(!ckCreate(currentPageId) 
			&& maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnStatus'].value == 'C') 
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0012' />");
		return;
	}
		
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPttRtnDetailForm.strutsAction.value = '<%=MaPttRtnDetailAction.PTRTN_DETAIL_INPUT%>';
	else maPttRtnDetailForm.strutsAction.value = '<%=MaPttRtnDetailAction.PTRTN_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPttRtnDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPttRtnDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPttRtnDetailForm.elements['maPttRtnCommonDTO.ptRtnListId'].value = maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPttRtnDetailForm.elements['maPttRtnCommonDTO.ptRtnListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("maPttRtnDetailDTO.partNo", "maPttRtnDetailDTO.partDesc");
}
 


/**
 * 반납완료 처리 
 */
function goConfirmrtn()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0091'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;

				 maPttRtnDetailForm.strutsAction.value = '<%=MaPttRtnDetailAction.PTRTN_COMPLETE%>';
				 var actionUrl = contextPath + "/maPttRtnDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maPttRtnDetailForm), 'afterConfirm');
			 }
			});
	 }
}

function afterConfirm(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	//0:성공여부 (S :성공), 1: ERP 문서번호 , 2:출고년도, 3:전기일 
	if("S" == rtnValue[0])
	{

		//반납 처리했습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0084'/>");
		
		//완료!
		maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnStatus'].value = "C";
		valSysDirCode('maPttRtnDetailDTO.ptRtnStatus', 'maPttRtnDetailDTO.ptRtnStatusDesc', 'PTRTN_STATUS', true);

		
		if (parent.findGridRow)	parent.findGridRow(maPttRtnDetailForm.elements['maPttRtnCommonDTO.ptRtnListId'].value);
		$(document).find('.b_confirmrtn').hide();
        $(document).find('.b_save').hide();

        // 입력 Form disable
        setDisable($(".form_box"));
	}
	else
	{
		//반납에 문제가 있습니다. 사이트 관리자에게 문의하세요.
		alertMessage1("<bean:message key='MESSAGE.MSG0065'/>");
	}
}

/**
 * 출고취소
 */
function goCanceliss()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0054'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;

				 maPttRtnDetailForm.strutsAction.value = '<%=MaPttRtnDetailAction.PTRTN_CANCEL_ISSUE%>';
				 var actionUrl = contextPath + "/maPttRtnDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maPttRtnDetailForm), 'afterCancel');
			 }
			});
	 }
}

function afterCancel(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	//0:성공여부 (S :성공), 1: ERP 문서번호 , 2:출고년도
	if("S" == rtnValue[0])
	{
		//출고 취소했습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0064'/>");

		//취소!
		maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnStatus'].value = "X";
		valSysDirCode('maPttRtnDetailDTO.ptRtnStatus', 'maPttRtnDetailDTO.ptRtnStatusDesc', 'PTRTN_STATUS', true);

		
		if (parent.findGridRow)	parent.findGridRow(maPttRtnDetailForm.elements['maPttRtnCommonDTO.ptRtnListId'].value);
	}
	else
	{
		//출고에 문제가 있습니다. 사이트 관리자에게 문의하세요.
		alertMessage1("<bean:message key='MESSAGE.MSG0065'/>");
	}
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPttRtnDetailForm.elements['maPttRtnDetailDTO.ptRtnListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPttRtnDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPttRtnCommonDTO.ptRtnListId" />
	<html:hidden property="maPttRtnDetailDTO.ptRtnStatus" />
	<html:hidden property="maPttRtnDetailDTO.partId" />
	<html:hidden property="maPttRtnDetailDTO.rtnDept" />
	<html:hidden property="maPttRtnDetailDTO.recDept" />
	<html:hidden property="maPttRtnDetailDTO.wcodeId" />
	<html:hidden property="maPttRtnDetailDTO.recBy" />

	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.ptRtnListId"/></label>
				<div id="ptRecListNoDiv" class="input_read">
					<html:text property="maPttRtnDetailDTO.ptRtnListId" tabindex="10" readonly="true"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.ptRtnStatus"/></label>
				<div class="input_read">
					<html:text property="maPttRtnDetailDTO.ptRtnStatusDesc" tabindex="20" readonly="true" />
				</div>
			</div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.rtnDate"/></label>
                <div class="input_box">
                    <html:text property="maPttRtnDetailDTO.rtnDate" tabindex="100" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>			
            <div class="field">
               <label class="check"><bean:message key="LABEL.wname"/></label>
               <div id="wnameDiv" class="input_box">
                   <html:text property="maPttRtnDetailDTO.wname" tabindex="65"/>
                   <p id="wnameSchBtn" class="open_spop">
                       <a>
                           <span>조회</span>
                       </a>    
                   </p>
               </div> 
             </div>   
			<div class="field">
                <label class="check"><bean:message key="LABEL.ptNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPttRtnDetailDTO.partNo" tabindex="40"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
            <div class="field">
                <label><bean:message key="LABEL.rtnDept"/></label>
                <div class="input_box">
                    <html:text property="maPttRtnDetailDTO.rtnDeptDesc" tabindex="50"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			
            <div class="field_long">
                <label><bean:message key="LABEL.ptNameSize"/></label>
                <div class="input_read">
                    <html:text property="maPttRtnDetailDTO.partDesc" tabindex="60" readonly="true"/>
                </div>
            </div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.rtnQty"/></label>
                <div class="input_box">
                    <html:text property="maPttRtnDetailDTO.rtnQty" tabindex="70"  
                         styleClass="num"/>
                </div>
             </div>  
            <div class="field">
                <label class="check"><bean:message key="LABEL.rentBy"/></label>
                <div class="input_box">
                    <html:text property="maPttRtnDetailDTO.recByName" tabindex="68"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>     
            <div class="field">
                <label class="check"><bean:message key="LABEL.rentDept"/></label>
                <div class="input_box">
                    <html:text property="maPttRtnDetailDTO.recDeptDesc" tabindex="50"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            </div>  
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPttRtnDetailDTO.remark" styleClass="ta50" tabindex="190" />
		     </div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>