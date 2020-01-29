<%--===========================================================================
자재출고확정 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.wo.action.MaPtIssDetailAction"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
%>
<html:html> 
<head>
<!-- 자재출고확정 -->
<title><bean:message key='LABEL.partNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!--//
/** 자동완성 변수 */
var partNoAc;
var wareHouseAc;
var plantAc;
var recByAc;
var ptissAc;

function loadPage() 
{ 
	chkStatus();
	
    setForUpdate();

    partNoAc = new autoC({"maPtIssDetailDTO.partNo":"part_no"});
    partNoAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "part_categ":"SPPT"
 	   });
    partNoAc.setAcDConditionMap({
    	"wcode_id" : "maPtIssDetailDTO.wcodeId"
    });
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
        "maPtIssDetailDTO.partId":"part_id"
        ,"maPtIssDetailDTO.partDesc":"description"
        ,"maPtIssDetailDTO.ptSize":"pt_size"
        ,"maPtIssDetailDTO.model":"model"
        ,"maPtIssDetailDTO.isSerial":"is_serial_part"
        ,"maPtIssDetailDTO.stockQty":"stockQty"
        ,"maPtIssDetailDTO.maker":"maker"
    });
    partNoAc.setKeyName("maPtIssDetailDTO.partId");
    partNoAc.init();
   
    acSysDesc("maPtIssDetailDTO.partGradeDesc","maPtIssDetailDTO.partGrade","PART_GRADE",true);
    
    wareHouseAc = new autoC({"maPtIssDetailDTO.wname":"wname"});
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART",
    	"is_use":"Y"
  	   });
    wareHouseAc.setKeyName("maPtIssDetailDTO.wcodeId");
    wareHouseAc.setAcResultMap({
        "maPtIssDetailDTO.wcodeId":"wcode_id"
       	,"maPtIssDetailDTO.stockQty":""
    });
    wareHouseAc.setAcDConditionMap({
    	"plant" : "maPtIssDetailDTO.plantId"
    	,"plantDesc":"maPtIssDetailDTO.plantDesc"
    });
    wareHouseAc.init();
    
    /** 공장 */
    plantAc = new autoC({"maPtIssDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maPtIssDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maPtIssDetailDTO.plantId":"plant"
    });
    plantAc.init();

    /**출고구분 */
  	ptissAc = new autoC({"maPtIssDetailDTO.ptissTypeDesc":"description"});
  	ptissAc.setTable("TACDSYSD");
  	ptissAc.setAcConditionMap({
    	"list_type" : "PTISS_TYPE"
      , "is_use"    : "Y"	
      , "param2"    : "GITYPE"
  	});
  	ptissAc.setKeyName("maPtIssDetailDTO.ptissTypeId");
  	ptissAc.setAcResultMap({
        "maPtIssDetailDTO.ptissTypeId":"cdsysd_no"
    });
  	ptissAc.init();
    
  	
  	/**출고자 */
  	recByAc = new autoC({"maPtIssDetailDTO.issueByDesc":"emp_name"});
  	recByAc.setAcConditionMap({
    	"is_join":"Y"
  	});
  	recByAc.setTable("TAEMP");
  	recByAc.setKeyName("maPtIssDetailDTO.issueById");
  	recByAc.setAcResultMap({
        "maPtIssDetailDTO.issueById":"emp_id"
    });
  	recByAc.init();
  	
  	/**수령자 */
  	recByAc = new autoC({"maPtIssDetailDTO.recByDesc":"emp_name"});
  	recByAc.setAcConditionMap({
    	"is_join":"Y"
  	});
  	recByAc.setTable("TAEMP");
  	recByAc.setKeyName("maPtIssDetailDTO.recById");
  	recByAc.setAcResultMap({
        "maPtIssDetailDTO.recById":"emp_id"
    });
  	recByAc.init();
    
    checkSerial();
    
    if(ckCreate(currentPageId)) goInput();
    else{
    	goTabPage("partIssWoItemList");
    	setPtIssTitle();
    	goUpdate();
    }
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId)
{
    var form = document.maPtIssDetailForm;
    goCommonTabPage(form, '' , pageId);
}

/**
 * Check Status 
 */
function chkStatus()
{
	var ptissStatus = maPtIssDetailForm.elements['maPtIssDetailDTO.ptissStatus'].value;
	
	 //완료!
	if(ptissStatus=="C")
	{
		setDisableAll();
	    //출고취소버튼 살리기
		setTimeout("$('.b_canceliss').show();",200);
	}
	else{
		setEnableAll();
		//출고취소버튼 죽임
		$('.b_canceliss').hide();
	}
}

function setPtIssTitle()
{
    setTitle("maPtIssDetailDTO.partNo");
}

/*
 * serial Check
 */
function checkSerial()
{
    var isSerialPart = maPtIssDetailForm.elements['maPtIssDetailDTO.isSerial'].value;
    
    if(isSerialPart == "Y") 
    {
        $('#partIssWoItemList_tabList').show();
        var ifm = getParentIframe("partIssWoItemList");
        if(typeof ifm.goSearch == "function"){
        	ifm.goSearch();
        }
        //보이게 해주세요.
        resizeTabFrame();
    }
    else
    {
        //안 보이게 해주세요.
        $('#partIssWoItemList_tabList').hide();
        resizeTabFrame();
    }
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAPTISSLIST_ID');
	setInitVal('maPtIssDetailDTO.partGrade', '<%=partGrade%>');
	setInitVal('maPtIssDetailDTO.partGradeDesc', '<%=partGrade%>');
	
	valSysDirCode('maPtIssDetailDTO.partGrade', 'maPtIssDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	//부서창고로 기본 세팅
	setInitVal('maPtIssDetailDTO.wcodeId', loginUser.wcodeId);
	setInitVal('maPtIssDetailDTO.wname', loginUser.wname);
	
	setInitVal('maPtIssDetailDTO.plantId', loginUser.plant);
	setInitVal('maPtIssDetailDTO.plantDesc', loginUser.plantDesc);
	
	setInitVal('maPtIssDetailDTO.issueDeptId', loginUser.deptId);
	setInitVal('maPtIssDetailDTO.issueDeptDesc', loginUser.deptDesc);
	
	setInitVal('maPtIssDetailDTO.issueById', loginUser.empId);
	setInitVal('maPtIssDetailDTO.issueByDesc', loginUser.empName);
	
	setInitVal('maPtIssDetailDTO.issueDate', getToday());
	setInitVal('maPtIssDetailDTO.issueQty', 1)
	
    partNoAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	maPtIssDetailForm.elements['maPtIssDetailDTO.ptisslistId'].value = sequenceVal;
	maPtIssDetailForm.elements['maPtIssDetailDTO.ptisslistNo'].value = sequenceVal;
	maPtIssDetailForm.elements['maPtIssCommonDTO.ptisslistId'].value = sequenceVal;
}

function afterAutoCmpt(code)
{
	//자재 lov 를 선택 했을때만 실행
	if(code=='maPtIssDetailDTO.partNo')
    { 
		checkSerial();
	}
	if(code=='maPtIssDetailDTO.wname' || code=='maPtIssDetailDTO.partNo' || code=='maPtIssDetailDTO.partGradeDesc')
    { 
		if(maPtIssDetailForm.elements['maPtIssDetailDTO.wcodeId'].value != "" &&
		   maPtIssDetailForm.elements['maPtIssDetailDTO.partId'].value != "" &&
		   maPtIssDetailForm.elements['maPtIssDetailDTO.partGrade'].value != "") 
		{
			findStockQty();
		}
	}
}

function findStockQty()
{
	var actionUrl = contextPath + "/maPtIssDetail.do";
	
	params = 'strutsAction='+'<%=MaPtIssDetailAction.PTISS_FIND_STOCK_QTY%>'
		   + '&maPtIssDetailDTO.wcodeId='+maPtIssDetailForm.elements['maPtIssDetailDTO.wcodeId'].value
		   + '&maPtIssDetailDTO.partId='+maPtIssDetailForm.elements['maPtIssDetailDTO.partId'].value
		   + '&maPtIssDetailDTO.partGrade='+maPtIssDetailForm.elements['maPtIssDetailDTO.partGrade'].value;
	
	XMLHttpPost(actionUrl, params, 'afterFindStockQty');
}

function afterFindStockQty(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	maPtIssDetailForm.elements['maPtIssDetailDTO.stockQty'].value = rtnValue[0];
}

/**
 * 수정
 */
function goUpdate()
{
	setForUpdate();
	
	setReadOnly('maPtIssDetailDTO.partNo');
	setReadOnly('maPtIssDetailDTO.partGradeDesc');
	setReadOnly('maPtIssDetailDTO.wname');
}

 
/**
 * 저장
 */ 
function goSave()
{
    var issueQty = maPtIssDetailForm.elements['maPtIssDetailDTO.issueQty'].value;
    if(issueQty == "" || parseFloat(issueQty) == 0 )
    {
    	alertMessage1("<bean:message key='LABEL.useQty'/>"+"<bean:message key='MESSAGE.MSG0246' />");
    	maPtIssDetailForm.elements['maPtIssDetailDTO.issueQty'].value = "";
    	maPtIssDetailForm.elements['maPtIssDetailDTO.issueQty'].focus();

    	closeModal();
    	return;
    }
    
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maPtIssDetailForm.strutsAction.value = "<%=MaPtIssDetailAction.PTISS_DETAIL_INPUT%>";
    else maPtIssDetailForm.strutsAction.value = '<%=MaPtIssDetailAction.PTISS_DETAIL_UPDATE%>';
 
    var actionUrl = contextPath + "/maPtIssDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPtIssDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
   //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    if (parent.findGridRow)	parent.findGridRow(maPtIssDetailForm.elements['maPtIssCommonDTO.ptisslistId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setPtIssTitle();
    
    chkStatus();
}

/**
 * 출고처리
 */
function goConfirmiss()
{
	var stockQty = maPtIssDetailForm.elements['maPtIssDetailDTO.stockQty'].value;
	var partGrade = maPtIssDetailForm.elements['maPtIssDetailDTO.partGrade'].value;
	
    goAfterConfirmiss();
}

function goAfterConfirmiss()
{
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
    }else{
        getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0053'/>", function(result){
            if(result){
               //================================
               // 필수 항목 체크한다.
               //================================
               if(checkValidation()) return;

                maPtIssDetailForm.strutsAction.value = '<%=MaPtIssDetailAction.PTISS_ISSUE%>';
                var actionUrl = contextPath + "/maPtIssDetail.do";
                   XMLHttpPost(actionUrl, FormQueryString(maPtIssDetailForm), 'afterConfirm');
            }
           });
    }
}

function afterConfirm(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	//0:성공여부 (S :성공), 1:메시지
	if("S" == rtnValue[0])
	{
		//출고 처리했습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0066'/>");
		
		//완료!
		maPtIssDetailForm.elements['maPtIssDetailDTO.ptissStatus'].value = "C";
		valSysDirCode('maPtIssDetailDTO.ptissStatus', 'maPtIssDetailDTO.ptissStatusDesc', 'PTISS_STATUS', true);
		chkStatus();
		
		maPtIssDetailForm.elements['maPtIssDetailDTO.budat'].value = rtnValue[3];
		
		if (parent.findGridRow)	parent.findGridRow(maPtIssDetailForm.elements['maPtIssCommonDTO.ptisslistId'].value);
		
		goClose('partIssWoItemDetail',this);
	}
	else
	{
		alertMessage1(rtnValue[1]);
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

				 maPtIssDetailForm.strutsAction.value = '<%=MaPtIssDetailAction.PTISS_CANCEL_ISSUE%>';
				 var actionUrl = contextPath + "/maPtIssDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maPtIssDetailForm), 'afterCancel');
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
		maPtIssDetailForm.elements['maPtIssDetailDTO.ptissStatus'].value = "W";
		valSysDirCode('maPtIssDetailDTO.ptissStatus', 'maPtIssDetailDTO.ptissStatusDesc', 'PTISS_STATUS', true);
		chkStatus();
		
		if (parent.findGridRow)	parent.findGridRow(maPtIssDetailForm.elements['maPtIssCommonDTO.ptisslistId'].value);
		goUpdate();
	}
	else
	{
		//출고에 문제가 있습니다. 사이트 관리자에게 문의하세요.
		alertMessage1("<bean:message key='MESSAGE.MSG0065'/>");
	}
	
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtIssDetailForm.elements['maPtIssDetailDTO.ptisslistId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtIssDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtIssCommonDTO.compNo" />
	<html:hidden property="maPtIssCommonDTO.ptisslistId" />
	
	<html:hidden property="maPtIssDetailDTO.wcodeId" />
    <html:hidden property="maPtIssDetailDTO.partId" />
    <html:hidden property="maPtIssDetailDTO.partGrade" />
    <html:hidden property="maPtIssDetailDTO.ptisslistId" />
    <html:hidden property="maPtIssDetailDTO.wkorId" />
    <html:hidden property="maPtIssDetailDTO.wopartId" />
    <html:hidden property="maPtIssDetailDTO.ptissTypeId" />
    <html:hidden property="maPtIssDetailDTO.ptissStatus" />
    <html:hidden property="maPtIssDetailDTO.outWcode" />
    <html:hidden property="maPtIssDetailDTO.outPlant" />
    <html:hidden property="maPtIssDetailDTO.whType" />
    <html:hidden property="maPtIssDetailDTO.uom" />
    <html:hidden property="maPtIssDetailDTO.budat" />
    <html:hidden property="maPtIssDetailDTO.isSerial" />
    <html:hidden property="maPtIssDetailDTO.plantId" />
    <html:hidden property="maPtIssDetailDTO.issueDeptId" />
    <html:hidden property="maPtIssDetailDTO.issueById" />
    <html:hidden property="maPtIssDetailDTO.recById" />

    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 <!-- 출고번호 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptIssListId"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.ptisslistNo" tabindex="5" readonly="true"/>
        	 	</div>
        	 </div>
        	 <!-- 출고상태 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.issStatus"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.ptissStatusDesc" tabindex="20" readonly="true"/>
        	 	</div>
        	 </div>
        	 <!-- 부품번호 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
                <div class="input_box">
                    <html:text property="maPtIssDetailDTO.partNo" tabindex="80" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
        	 </div>
        	 <!-- 부품명 -->
			 <div class="field">
				<label><bean:message key="LABEL.partDesc"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.partDesc" readonly="true" tabindex="90"/>
        	 	</div>
			 </div>
        	 <!-- 규격 -->
			 <div class="field">
				<label><bean:message key="LABEL.ptSize"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.ptSize" readonly="true" tabindex="93"/>
        	 	</div>
			 </div>
			<!-- 제작사 -->
			 <div class="field">
				<label><bean:message key="LABEL.maker"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.maker" readonly="true" tabindex="93"/>
        	 	</div>
			 </div>			 
        	 <!-- 모델 -->
			 <div class="field">
				<label><bean:message key="LABEL.model"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.model" readonly="true" tabindex="96"/>
        	 	</div>
			 </div>
        	 <!-- 자재등급 -->
			 <div class="field">
				<label class="check"><bean:message key="LABEL.partGrade"/></label>
				<div class="input_box">
					<html:text property="maPtIssDetailDTO.partGradeDesc" tabindex="100" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			 </div>
        	 <!-- 창고 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.wname"/></label>
        	 	<div id="wnameDiv" class="input_box">
        	 		<html:text property="maPtIssDetailDTO.wname" tabindex="110"/>
                    <p id="wnameSchBtn" class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>    
                    </p>
        	 	</div>
        	 </div>
			 <!-- 출고수량 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.issQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtIssDetailDTO.issueQty" tabindex="120" styleClass="ty_num"/>
        	 	</div>
        	 </div>
        	 <!-- 현재고수량 -->
        	 <div class="field" id="astockQtyDiv">
        	 	<label><bean:message key="LABEL.stockQty"/></label>
        	 	<div class="input_read" id="astockQtyBoxDiv">
        	 		<html:text property="maPtIssDetailDTO.stockQty" tabindex="60" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <!-- 출고일자 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.issDate"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.issueDate" readonly="true" tabindex="130"/>
        	 	</div>
        	 </div>
        	 <!-- 공장(Plant) -->
			 <div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maPtIssDetailDTO.plantDesc" tabindex="140"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			 </div>		
			 <!-- 출고부서 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.issDept"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.issueDeptDesc" readonly="true" tabindex="150"/>
        	 	</div>
        	 </div>
        	 <!-- 출고자 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.issueBy"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssDetailDTO.issueByDesc" readonly="true" tabindex="160"/>
					<p class="open_spop"><a><span>조회</span></a></p>
        	 	</div>
        	 </div>	
        	 <!-- 수령자 -->
			 <div class="field">
				<label><bean:message key="LABEL.recBy"/></label>
				<div class="input_box">
					<html:text property="maPtIssDetailDTO.recByDesc" tabindex="170"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			 </div>
			 <!-- 출고구분 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptIssMode"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtIssDetailDTO.ptissTypeDesc" tabindex="180"/>
        	 		<p class="open_spop"><a><span>조회</span></a></p>
        	 	</div>
        	 </div>		
        	 <!-- 비고 -->
        	 <div class="field_long">
        	 	<label><bean:message key="LABEL.remark"/></label>
        	 	<div class="input_box">
        	 		<html:textarea property="maPtIssDetailDTO.remark" styleClass="ta50" tabindex="210"/>
        	 	</div>
        	 </div>
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>