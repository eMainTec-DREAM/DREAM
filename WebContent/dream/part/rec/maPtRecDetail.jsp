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
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.part.rec.action.MaPtRecDetailAction"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId());
	String partGrade = MwareConfig.getPartGrade(); 
%>
<html:html>
<head>
<!-- 구매입고 : 품번 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var partNoAc;
var vendorDescAc;
var deptAc;
var mainMngAc;
var wareHouseAc;
var plantAc;
function loadPage() 
{
	setTitle("maPtRecDetailDTO.partNo", "maPtRecDetailDTO.partDesc");
	
	setForUpdate();
	
	partNoAc = new autoC({"maPtRecDetailDTO.partNo":"full_desc"});
	partNoAc.setTable("TAPARTS");
	partNoAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
	partNoAc.setAcResultMap({
		"maPtRecDetailDTO.partNo":"part_no",
	    "maPtRecDetailDTO.partId":"part_id",
	    "maPtRecDetailDTO.partDesc":"description",
	    "maPtRecDetailDTO.partSize":"pt_size",
	    "maPtRecDetailDTO.model":"model",
	    "maPtRecDetailDTO.isSerial":"is_serial_part",
		"maPtRecDetailDTO.unitPrice":"last_price",
		"maPtRecDetailDTO.currencyId":"currency",
		"maPtRecDetailDTO.currencyDesc":"currencyDesc"
	});
	partNoAc.setKeyName("maPtRecDetailDTO.partId"); 
	partNoAc.init();
	
	
	partNameAc = new autoC({"maPtRecDetailDTO.partDesc":"description"});
	partNameAc.setTable("TAPARTSDESC");
	partNameAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });

	partNameAc.init();
	
	partSizeAc = new autoC({"maPtRecDetailDTO.partSize":"pt_size"});
	partSizeAc.setTable("TAPARTSPTSIZE");
	partSizeAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
	partSizeAc.setAcDConditionMap({
    	"description" : "maPtRecDetailDTO.partDesc"
  	   });
	

	partSizeAc.init();
	
	
	vendorDescAc = new autoC({"maPtRecDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("maPtRecDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "maPtRecDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    deptAc = new autoC({"maPtRecDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant":"maPtRecDetailDTO.plantId"
  	});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maPtRecDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maPtRecDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPtRecDetailDTO.inspectorName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPtRecDetailDTO.deptId"
    	,"plant"  : "maPtRecDetailDTO.plantId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maPtRecDetailDTO.inspector");
    mainMngAc.setAcResultMap({
        "maPtRecDetailDTO.inspector":"emp_id"
    });
    mainMngAc.init();
    
    wareHouseAc = new autoC({"maPtRecDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	});
    wareHouseAc.setAcDConditionMap({
    	"plant":"maPtRecDetailDTO.plantId"
    });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setKeyName("maPtRecDetailDTO.wcodeId");
    wareHouseAc.setAcResultMap({
        "maPtRecDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();

    /** 공장 */
    plantAc = new autoC({"maPtRecDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maPtRecDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maPtRecDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    //품번생성여부
    acSysDesc("maPtRecDetailDTO.isMakePartNo","maPtRecDetailDTO.isMakePartNoId","IS_USE");
  
    //자재등급
    acSysDesc("maPtRecDetailDTO.partGradeDesc","maPtRecDetailDTO.partGrade","PART_GRADE",true);
    
    // 화폐단위
    acSysDesc("maPtRecDetailDTO.currencyDesc","maPtRecDetailDTO.currencyId","CURRENCY",true);
	
  
    //$("#maPtRecSerialList_tabList").css("display","none");
    
	/* $("input[name='maPtRecDetailDTO.partNo']").on({
		"keyup":function(e){
			valPartNo('maPtRecDetailDTO.partId','maPtRecDetailDTO.partNo','maPtRecDetailDTO.partNameSize', true);
		}
	}); */
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
		goTabPage("maPtRecSerialList");
	}
	
	if(typeof enhanceLoadPage == "function") enhanceLoadPage();
}
	
function afterAutoCmpt(code)
{	
	//자재 lov 를 선택 했을때만 실행
	if(code=='maPtRecDetailDTO.partNameSize'||code=='maPtRecDetailDTO.partNo')
	{
		// 시리얼이 Y 인경우 시리얼 탭을 보이고 아닌경우 hidden
		if(maPtRecDetailForm.elements['maPtRecDetailDTO.isSerial'].value!='Y')
		{
			$("#maPtRecSerialList_tabList").css("display","none");			
		}
		else
		{	
			$("#maPtRecSerialList_tabList").css("display","block");
			resizeTabFrame();
			maPtRecDetailForm.elements['maPtRecCommonDTO.partId'].value= maPtRecDetailForm.elements['maPtRecDetailDTO.partId'].value;
		}
	}

	if(typeof afterEnhanceAutoCmpt == "function") afterEnhanceAutoCmpt(code);
}


function goUpdate()
{
	afterAutoCmpt('maPtRecDetailDTO.partNo');
}

function afterBtnLoad()
{
	var prRecListStatus = maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListStatus'].value;

    if(prRecListStatus == "W") // 작성중 
    {
	    // 입력 Form disable
        setEnableAll();
    }
    else if(prRecListStatus == "C") // 입고완료 
    {
        setDisableAll();
    }
    else
   	{
    	$('.b_save').show();
   	}
    
}

function afterDisable()
{
	setState();
}

function afterEnable()
{
	setState();
}

function setState()
{
	var prRecListStatus = maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListStatus'].value;

    if(prRecListStatus == "W") // 작성중 
    {
	    $('.b_rec_cancel').hide();
	    $(document).find('.b_rec_complete').show();
    }
    else if(prRecListStatus == "C") // 입고완료 
    {
        $('.b_rec_cancel').show();
        $(document).find('.b_rec_complete').hide();
    }
    else
   	{
    	$('.b_save').show();
   	}
}

function goInput()
{ 
	sequenceNextVal('SQAPRRECLIST_ID');
	
	maPtRecDetailForm.elements['maPtRecDetailDTO.recDate'].value = getToday(); 
	maPtRecDetailForm.elements['maPtRecDetailDTO.deptId'].value = loginUser.deptId;
    maPtRecDetailForm.elements['maPtRecDetailDTO.deptDesc'].value = loginUser.deptDesc;
    maPtRecDetailForm.elements['maPtRecDetailDTO.inspector'].value = loginUser.empId;
    maPtRecDetailForm.elements['maPtRecDetailDTO.inspectorName'].value = loginUser.empName;
    maPtRecDetailForm.elements['maPtRecDetailDTO.wcodeId'].value = loginUser.wcodeId;
    maPtRecDetailForm.elements['maPtRecDetailDTO.wname'].value = loginUser.wname;
	maPtRecDetailForm.elements['maPtRecDetailDTO.recQty'].value = "1"; 
	maPtRecDetailForm.elements['maPtRecDetailDTO.partGrade'].value = '<%=partGrade%>'; 
	maPtRecDetailForm.elements['maPtRecDetailDTO.partGradeDesc'].value = '<%=partGrade%>'; 
	
	valSysDirCode('maPtRecDetailDTO.partGrade', 'maPtRecDetailDTO.partGradeDesc', 'PART_GRADE', true);
	//maPtRecDetailForm.elements['maPtRecDetailDTO.unitPrice'].value = "0"; 
	//maPtRecDetailForm.elements['maPtRecDetailDTO.totPrice'].value = "0"; 
	//공장명
    if(loginUser.plant!='null'){
    	maPtRecDetailForm.elements['maPtRecDetailDTO.plantId'].value = loginUser.plant;
    	maPtRecDetailForm.elements['maPtRecDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }

	// 입고상태 : W=작성중
	maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListStatus'].value = "W"; 
	valSysDirCode('maPtRecDetailDTO.prRecListStatus', 'maPtRecDetailDTO.prRecListStatusDesc', 'PRRECLIST_STATUS', true);
	// 품번생성여부
	maPtRecDetailForm.elements['maPtRecDetailDTO.isMakePartNoId'].value = "N"; 
	maPtRecDetailForm.elements['maPtRecDetailDTO.isMakePartNo'].value = "N"; 
	valSysDirCode('maPtRecDetailDTO.isMakePartNo', 'maPtRecDetailDTO.isMakePartNoId', 'IS_USE', true);
	
	partNoAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value = sequenceVal;
	maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListNo'].value = sequenceVal;
	maPtRecDetailForm.elements['maPtRecCommonDTO.prRecListId'].value = sequenceVal;
	
	setEnableAll();
	// 버튼 비활성화 
	$(document).find('.b_rec_complete').hide();
	$(document).find('.b_rec_cancel').hide();
	$("#maPtRecSerialList_tabList").css("display","none");
}

/**
 * 저장
 */ 
function goSave()
{
	// 입고완료된 정보일 경우 "저장" 불가.
	if(!ckCreate(currentPageId) 
			&& maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListStatus'].value == 'C') 
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0012' />");
		return;
	}
	
	var recQty = maPtRecDetailForm.elements['maPtRecDetailDTO.recQty'].value;
	if(recQty == "" || parseFloat(recQty) == 0 )
    {
    	alertMessage1("<bean:message key='LABEL.recQty'/>"+"<bean:message key='MESSAGE.MSG0246' />");
    	maPtRecDetailForm.elements['maPtRecDetailDTO.recQty'].value = "";
    	maPtRecDetailForm.elements['maPtRecDetailDTO.recQty'].focus();
    	
    	closeModal();
    	return;
    }
		
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPtRecDetailForm.strutsAction.value = '<%=MaPtRecDetailAction.PTREC_DETAIL_INPUT%>';
	else maPtRecDetailForm.strutsAction.value = '<%=MaPtRecDetailAction.PTREC_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPtRecDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtRecDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value = maPtRecDetailForm.elements['maPtRecCommonDTO.prRecListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value);
	//parent.goSearch();
	
	getTopPage().afterSaveAll(currentPageId);

	goUpdate();
	setState();
	setTitle("maPtRecDetailDTO.partNo", "maPtRecDetailDTO.partDesc");
}
 
function sumTotPrice()
{
	 var unitPrice = maPtRecDetailForm.elements['maPtRecDetailDTO.unitPrice'].value;
	 var recQty = maPtRecDetailForm.elements['maPtRecDetailDTO.recQty'].value;

	 var result = intToData(recQty) * intToData(unitPrice);
	 
	 console.log("result:::::"+ result);
	 
	 maPtRecDetailForm.elements['maPtRecDetailDTO.totPrice'].value = result;
	 
	 console.log("최종:::::"+ maPtRecDetailForm.elements['maPtRecDetailDTO.totPrice'].value);
	 setMoneyFormat(maPtRecDetailForm.elements['maPtRecDetailDTO.totPrice'], "3");
}

/**
 * 입고완료 처리 
 */
function goRec_complete()
{
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
	
	if(checkIsUpdate())
	{
		//저장이후 확정 가능합니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0158' />");
		return;
	}	
	
	if(maPtRecDetailForm.elements['maPtRecDetailDTO.isSerial'].value=='Y')
	{
        var actionUrl = contextPath + "/maPtRecDetail.do";
        var param =  "&strutsAction=" + '<%= MaPtRecDetailAction.SERIAL_COUNT %>'
                  +  "&" + FormQueryString(maPtRecDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidSerialCount');
	}
	else
	{
		goRecComplete();
		
	}
}

/**
 * valFailureNo()실행 후 호출
 */
var serialCount;
function setValidSerialCount(ajaxXmlDoc)
{
	serialCount = parseXmlDoc(ajaxXmlDoc, 'DESC');

	
    if(Number(maPtRecDetailForm.elements['maPtRecDetailDTO.recQty'].value)!=Number(serialCount))
    {
    	// 입고수량과 시리얼의 수량은 같아야 합니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0156' />");
    	return;
    }
    
    //입고완료 처리 process
    goRecComplete();
}

/**
 * 입고 Validation 확인후 실행
 */
function goRecComplete()
{									//완료하시겠습니까?
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
        if(result)
        {
        	//기다려주세요
        	setModal('<bean:message key="MESSAGE.MSG0083"/>');
        	
		    maPtRecDetailForm.strutsAction.value = '<%=MaPtRecDetailAction.PTREC_DETAIL_STATUS_UPDATE%>';
		    
		    maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListStatus'].value = "C"; // 입고완료
            maPtRecDetailForm.elements['maPtRecDetailDTO.ptRecMode'].value = "C"; // 입고완료
		    
		    var actionUrl = contextPath + "/maPtRecDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPtRecDetailForm), 'afterSaveStatus');
        }
    }); 
}

/**
 * 입고취소 처리 
 */
function goRec_cancel()
{
	if(maPtRecDetailForm.elements['maPtRecDetailDTO.countWo'].value!='0')
	{
		// 운영설비가 있는경우 입고취소할수 없습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
		return;
	}
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0019'/>", function(result){
        if(result)
        {
		    maPtRecDetailForm.strutsAction.value = '<%=MaPtRecDetailAction.PTREC_DETAIL_STATUS_UPDATE%>';
		    
		    maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListStatus'].value = "W"; // 작성중
		    maPtRecDetailForm.elements['maPtRecDetailDTO.ptRecMode'].value = "R"; // 입고취소
		    
		    var actionUrl = contextPath + "/maPtRecDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPtRecDetailForm), 'afterCancelSaveStatus');
        }
    });
}

/**
 * 입고완료/입고취소(작성중) 처리 후 호출 
 */
function afterSaveStatus()
{
	valSysDirCode('maPtRecDetailDTO.prRecListStatus', 'maPtRecDetailDTO.prRecListStatusDesc', 'PRRECLIST_STATUS', true);
	maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value = maPtRecDetailForm.elements['maPtRecCommonDTO.prRecListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value);
	goUpdate();
	
	// 입력 Form disable
    //setDisableAll();	
	var prreclistId = maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value;
    parent.reloadTabPageAction(prreclistId);
}


/**
 * 입고완료/입고취소(작성중) 처리 후 호출 
 */
function afterCancelSaveStatus()
{
	valSysDirCode('maPtRecDetailDTO.prRecListStatus', 'maPtRecDetailDTO.prRecListStatusDesc', 'PRRECLIST_STATUS', true);

	maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value = maPtRecDetailForm.elements['maPtRecCommonDTO.prRecListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value);
	goUpdate();
	
	var prreclistId = maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value;
    parent.reloadTabPageAction(prreclistId);
}
function goTabPage(pageId)
{
	var form = document.maPtRecDetailForm;

	goCommonTabPage(form, '' , pageId);
	
	maPtRecDetailForm.elements['maPtRecCommonDTO.prRecStatus'].value= maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListStatus'].value;
	maPtRecDetailForm.elements['maPtRecCommonDTO.partId'].value= maPtRecDetailForm.elements['maPtRecDetailDTO.partId'].value;
	if(maPtRecDetailForm.elements['maPtRecDetailDTO.isSerial'].value!='Y')
	{
		$("#maPtRecSerialList_tabList").css("display","none");
	}
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtRecDetailForm.elements['maPtRecDetailDTO.prRecListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtRecDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtRecCommonDTO.prRecListId" />
	<html:hidden property="maPtRecCommonDTO.prRecStatus" />
	<html:hidden property="maPtRecCommonDTO.partId" />
	<html:hidden property="maPtRecDetailDTO.isSerial" />
	<html:hidden property="maPtRecDetailDTO.prRecListId" />
	<html:hidden property="maPtRecDetailDTO.prRecListStatus" />
	<html:hidden property="maPtRecDetailDTO.vendorId" />
	<html:hidden property="maPtRecDetailDTO.partId" />
	<html:hidden property="maPtRecDetailDTO.deptId" />
	<html:hidden property="maPtRecDetailDTO.wcodeId" />
	<html:hidden property="maPtRecDetailDTO.inspector" />
	<html:hidden property="maPtRecDetailDTO.countWo" />
	<html:hidden property="maPtRecDetailDTO.ptRecMode" /><!-- 입고이력저상시 사용 -->
	<html:hidden property="maPtRecDetailDTO.isMakePartNoId" />
	<html:hidden property="maPtRecDetailDTO.partGrade" />
	<html:hidden property="maPtRecDetailDTO.plantId" />
	<html:hidden property="maPtRecDetailDTO.ptpritemId" />
	<html:hidden property="maPtRecDetailDTO.polistId" />
	<html:hidden property="maPtRecDetailDTO.poitemId" />
	<html:hidden property="maPtRecDetailDTO.uom" />
	<html:hidden property="maPtRecDetailDTO.currencyId" />
	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.ptRecListNo"/></label>
				<div id="ptRecListNoDiv" class="input_box">
					<html:text property="maPtRecDetailDTO.prRecListNo" tabindex="10"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.ptRecListStatus"/></label>
				<div class="input_read">
					<html:text property="maPtRecDetailDTO.prRecListStatusDesc" tabindex="20" readonly="true" />
				</div>
			</div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.recDate"/></label>
                <div class="input_box">
                    <html:text property="maPtRecDetailDTO.recDate" tabindex="100" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>			
			<div class="field">
				<label><bean:message key="LABEL.recVendor"/></label>
				<div class="input_box">
                    <html:text property="maPtRecDetailDTO.vendorDesc" tabindex="30"/>
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
                    <html:text property="maPtRecDetailDTO.deptDesc" tabindex="40"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			
			<div class="field">
                <label class="check"><bean:message key="LABEL.isMakePartNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPtRecDetailDTO.isMakePartNo" tabindex="50"/>
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
                    <html:text property="maPtRecDetailDTO.partNo" tabindex="45"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div> 
            <div class="field">
                <label><bean:message key="LABEL.ptDesc"/></label>
                <div class="input_box">
                    <html:text property="maPtRecDetailDTO.partDesc" tabindex="55"/>
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.ptSize"/></label>
                <div class="input_box">
                    <html:text property="maPtRecDetailDTO.partSize" tabindex="60"/>
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.model"/></label>
                <div class="input_read">
                    <html:text property="maPtRecDetailDTO.model" tabindex="63" readonly="true"/>
                </div>
            </div>
            <div class="field">
               <label class="check"><bean:message key="LABEL.wname"/></label>
               <div id="wnameDiv" class="input_box">
                   <html:text property="maPtRecDetailDTO.wname" tabindex="65"/>
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
                    <html:text property="maPtRecDetailDTO.inspectorName" tabindex="68"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>           
             <div class="field">
                <label class="check"><bean:message key="LABEL.recQty"/></label>
                <div class="input_box">
                    <html:text property="maPtRecDetailDTO.recQty" tabindex="70"  
                        onblur="javascript:sumTotPrice();" styleClass="ty_num" />
                </div>
             </div>            			
            <div class="field">
                <label><bean:message key="LABEL.recUnitPrice"/></label>
                <div class="input_box">
                    <html:text property="maPtRecDetailDTO.unitPrice" tabindex="80" 
                        onblur="javascript:sumTotPrice();" styleClass="num"/>
                </div>
            </div>                     
            <div class="field">
                <label><bean:message key="LABEL.recTotPrice"/></label>
                <div class="input_read">
                    <html:text property="maPtRecDetailDTO.totPrice" tabindex="100" readonly="true" styleClass="num"/>
                </div>
            </div>  
            <!-- 자재등급 -->
			 <div class="field">
				<label class="check"><bean:message key="LABEL.partGrade"/></label>
				<div class="input_box">
					<html:text property="maPtRecDetailDTO.partGradeDesc" tabindex="110" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			 </div>
			<!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maPtRecDetailDTO.plantDesc" tabindex="120"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>			
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPtRecDetailDTO.remark" styleClass="ta50" tabindex="190" />
				</div>
			</div>
			<!-- 화폐 단위 -->
			<div class="field">
                <label><bean:message key="LABEL.currency"/></label>
                <div class="input_box">
                    <html:text property="maPtRecDetailDTO.currencyDesc" tabindex="244"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div> 
			<c:set var="filePath" value="enhance/${compName}/part/rec/maPtRecDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/rec/maPtRecDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>