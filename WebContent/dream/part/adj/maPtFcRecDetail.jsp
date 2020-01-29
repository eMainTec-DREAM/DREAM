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
<%@ page import="dream.part.adj.action.MaPtFcRecDetailAction"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
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
var partNoAc,vendorDescAc,deptAc,mainMngAc,wareHouseAc;

function loadPage() 
{
	setFunction();
	
	setTitle("maPtFcRecDetailDTO.partNo", "maPtFcRecDetailDTO.partNameSize");

	setForUpdate();
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
}

function setFunction()
{
	//부품등급
	acSysDesc("maPtFcRecDetailDTO.partGradeDesc","maPtFcRecDetailDTO.partGrade","PART_GRADE",true);
	
	partNoAc = new autoC({"maPtFcRecDetailDTO.partNo":"part_no"});
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"part_categ":"SPPT"
 	   });
    partNoAc.setAcResultMap({
    	"maPtFcRecDetailDTO.partNameSize":"partNameSize",
        "maPtFcRecDetailDTO.partId":"part_id",
        "maPtFcRecDetailDTO.unitPrice":"last_price",
        "maPtFcRecDetailDTO.currencyId":"currency",
    	"maPtFcRecDetailDTO.currencyDesc":"currencyDesc"
        
        ,"maPtFcRecDetailDTO.partDesc":"description"
        ,"maPtFcRecDetailDTO.partModel":"model"
        ,"maPtFcRecDetailDTO.partSize":"pt_size"
    });
    partNoAc.setKeyName("maPtFcRecDetailDTO.partId");
    partNoAc.init();
	
	vendorDescAc = new autoC({"maPtFcRecDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("maPtFcRecDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "maPtFcRecDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    deptAc = new autoC({"maPtFcRecDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maPtFcRecDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maPtFcRecDetailDTO.deptId":"dept_id"
    });
    deptAc.init();

    wareHouseAc = new autoC({"maPtFcRecDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setKeyName("maPtFcRecDetailDTO.wcodeId");
    wareHouseAc.setAcResultMap({
        "maPtFcRecDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
    acSysDesc("maPtFcRecDetailDTO.currencyDesc","maPtFcRecDetailDTO.currencyId","CURRENCY",true);
    
	/* $("input[name='maPtFcRecDetailDTO.partNo']").on({
		"keyup":function(e){
			valPartNo('maPtFcRecDetailDTO.partId','maPtFcRecDetailDTO.partNo','maPtFcRecDetailDTO.partNameSize', true);
		}
	}); */
}
	
function goUpdate()
{
    // 버튼 활성화 
    var fcRecListStatus = maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListStatus'].value;

    if(fcRecListStatus == "W") // 작성중 
    {
	    $(document).find('.b_rec_complete').show();
	    $(document).find('.b_rec_cancel').hide();
	    $(document).find('.b_save').show();

	    // 입력 Form disable
        setEnable($(".form_box"));
    }
    else if(fcRecListStatus == "C") // 입고완료 
    {
        $(document).find('.b_rec_complete').hide();
        $(document).find('.b_rec_cancel').show();
        $(document).find('.b_save').hide();

        // 입력 Form disable
        setDisable($(".form_box"));
        
        if(partNoAc)partNoAc.destroy();
    }
    else
   	{
    	$(document).find('.b_save').show();
   	}
}

function setPartGrade()
{
	if("N"=='<%=MwareConfig.getIsUsePartGrade()%>')
	{
		//부품등급을 사용하지 않는 경우는 설정에 있는 부품등급을 사용함.
		maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.partGrade'].value = '<%=MwareConfig.getPartGrade()%>'; 
		maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.partGradeDesc'].value = getSysCodeDesc("PART_GRADE", '<%=MwareConfig.getPartGrade()%>');
	} 
	else 
	{
		maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.partGrade'].value = "B";
		maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.partGradeDesc'].value = getSysCodeDesc("PART_GRADE", "B");
	}
}

function goInput()
{ 
	sequenceNextVal('SQAFCRECLIST_ID');
	
	maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.recDate'].value = getToday(); 
	maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.deptId'].value = loginUser.deptId;
    maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.deptDesc'].value = loginUser.deptDesc;
    maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.wcodeId'].value = loginUser.wcodeId;
    maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.wname'].value = loginUser.wname;
	//maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.recQty'].value = "0"; 
	//maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.unitPrice'].value = "0"; 
	//maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.totPrice'].value = "0"; 

	setPartGrade();
	
	// 입고상태 : W=작성중
	maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListStatus'].value = "W"; 
	valSysDirCode('maPtFcRecDetailDTO.fcRecListStatus', 'maPtFcRecDetailDTO.fcRecListStatusDesc', 'FCRECLIST_STATUS', true);

	// 버튼 비활성화 
	$(document).find('.b_rec_complete').hide();
	$(document).find('.b_rec_cancel').hide();
	
	partNoAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListId'].value = sequenceVal;
	maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListNo'].value = sequenceVal;
	maPtFcRecDetailForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
	// 입고완료된 정보일 경우 "저장" 불가.
	if(!ckCreate(currentPageId) 
			&& maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListStatus'].value == 'C') 
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0012' />");
		return;
	}
		
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPtFcRecDetailForm.strutsAction.value = '<%=MaPtFcRecDetailAction.PTFCREC_DETAIL_INPUT%>';
	else maPtFcRecDetailForm.strutsAction.value = '<%=MaPtFcRecDetailAction.PTFCREC_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPtFcRecDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtFcRecDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListId'].value = maPtFcRecDetailForm.elements['maPtFcRecCommonDTO.fcRecListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("maPtFcRecDetailDTO.partNo", "maPtFcRecDetailDTO.partDesc");
}
 
function sumTotPrice()
{
	 var unitPrice = maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.unitPrice'].value;
	 var recQty = maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.recQty'].value;
	
	 var result = intToData(recQty) * intToData(unitPrice);
	 maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.totPrice'].value = result;
	 setMoneyFormat(maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.totPrice'], "3");
}

/**
 * 입고완료 처리 
 */
function goRec_complete()
{
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
        if(result)
        {
		    maPtFcRecDetailForm.strutsAction.value = '<%=MaPtFcRecDetailAction.PTFCREC_DETAIL_STATUS_UPDATE%>';
		    
		    //입고완료
		    maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListStatus'].value = "C";
            //입고완료
		    maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecMode'].value = "C";
		    
		    var actionUrl = contextPath + "/maPtFcRecDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPtFcRecDetailForm), 'afterSaveStatus');
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
		    maPtFcRecDetailForm.strutsAction.value = '<%=MaPtFcRecDetailAction.PTFCREC_DETAIL_STATUS_UPDATE%>';
		    
		    maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListStatus'].value = "W"; // 작성중
		    maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecMode'].value = "R"; // 입고취소

		    var actionUrl = contextPath + "/maPtFcRecDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPtFcRecDetailForm), 'afterSaveStatus');
        }
    });
}

/**
 * 입고완료/입고취소(작성중) 처리 후 호출 
 */
function afterSaveStatus()
{
	valSysDirCode('maPtFcRecDetailDTO.fcRecListStatus', 'maPtFcRecDetailDTO.fcRecListStatusDesc', 'FCRECLIST_STATUS', true);
	if(maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecMode'].value == "R"){
		//입고취소
		alertMessage1("<bean:message key='MESSAGE.MSG0057'/>");
	}else if(maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecMode'].value = "C"){
		//입고완료
		alertMessage1("<bean:message key='MESSAGE.MSG0056'/>");
	}

	maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListId'].value = maPtFcRecDetailForm.elements['maPtFcRecCommonDTO.fcRecListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListId'].value);
    
	goUpdate();
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtFcRecDetailForm.elements['maPtFcRecDetailDTO.fcRecListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtFcRecDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtFcRecCommonDTO.fcRecListId" />
	<html:hidden property="maPtFcRecDetailDTO.fcRecListId" />
	<html:hidden property="maPtFcRecDetailDTO.fcRecListStatus" />
	<html:hidden property="maPtFcRecDetailDTO.partId" />
	<html:hidden property="maPtFcRecDetailDTO.deptId" />
	<html:hidden property="maPtFcRecDetailDTO.wcodeId" />
	<html:hidden property="maPtFcRecDetailDTO.vendorId" />
	<html:hidden property="maPtFcRecDetailDTO.currencyId" />
	<html:hidden property="maPtFcRecDetailDTO.fcRecMode" /><!-- 입고이력저상시 사용 -->
	<html:hidden property="maPtFcRecDetailDTO.partGrade" />
	<div class="article_box">
		<div class="form_box">
		    <div class="field">
                <label class="check"><bean:message key="LABEL.recDate"/></label>
                <div class="input_box">
                    <html:text property="maPtFcRecDetailDTO.recDate" tabindex="10" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>	
            
			<div class="field">
				<label><bean:message key="LABEL.ptRecListNo"/></label>
				<div id="ptRecListNoDiv" class="input_read">
					<html:text property="maPtFcRecDetailDTO.fcRecListNo" tabindex="20" readonly="true"/>
				</div>
			</div>
			<div class="field">
                <label class="check"><bean:message key="LABEL.ptNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPtFcRecDetailDTO.partNo" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
			<div class="field">
				<label><bean:message key="LABEL.ptRecListStatus"/></label>
				<div class="input_read">
					<html:text property="maPtFcRecDetailDTO.fcRecListStatusDesc" tabindex="40" readonly="true" />
				</div>
			</div>
             <div class="field_long">
                <label><bean:message key="LABEL.ptNameSize"/></label>
                <div class="input_read">
                    <html:text property="maPtFcRecDetailDTO.partNameSize" tabindex="50" readonly="true"/>
                </div>
            </div>
			 <!-- 부품명 -->
             <div class="field">
                <label>부품명</label>
                <div class="input_read">
                    <html:text property="maPtFcRecDetailDTO.partDesc" tabindex="50" readonly="true"/>
                </div>
            </div>
			 <!-- 부품 모델 -->
             <div class="field">
                <label>모델</label>
                <div class="input_read">
                    <html:text property="maPtFcRecDetailDTO.partModel" tabindex="50" readonly="true"/>
                </div>
            </div>
			 <!-- 부품 규격 -->
             <div class="field">
                <label>규격</label>
                <div class="input_read">
                    <html:text property="maPtFcRecDetailDTO.partSize" tabindex="50" readonly="true"/>
                </div>
            </div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.recQty"/></label>
                <div class="input_box">
                    <html:text property="maPtFcRecDetailDTO.recQty" tabindex="60"  
                        onblur="javascript:sumTotPrice();" styleClass="num"/>
                </div>
             </div>   
             <!-- 거래처 -->
			<div class="field">
				<label><bean:message key="LABEL.vendor"/></label>
				<div class="input_box">
					<html:text property="maPtFcRecDetailDTO.vendorDesc" tabindex="40" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>     			
            <div class="field hidden">
                <label><bean:message key="LABEL.recUnitPrice"/></label>
                <div class="input_box">
                    <html:text property="maPtFcRecDetailDTO.unitPrice" tabindex="70" 
                        onblur="javascript:sumTotPrice();"  styleClass="num"/>
                </div>
            </div>   
            <div class="field hidden">
                <label><bean:message key="LABEL.recTotPrice"/></label>
                <div class="input_read">
                    <html:text property="maPtFcRecDetailDTO.totPrice" tabindex="80" readonly="true" styleClass="num"/>
                </div>
            </div>  
            <div class="field">
               <label class="check"><bean:message key="LABEL.wname"/></label>
               <div id="wnameDiv" class="input_box">
                   <html:text property="maPtFcRecDetailDTO.wname" tabindex="90"/>
                   <p id="wnameSchBtn" class="open_spop">
                       <a>
                           <span>조회</span>
                       </a>    
                   </p>
               </div> 
             </div>       
             
            <div class="field">
                <label><bean:message key="LABEL.manageDept"/></label>
                <div class="input_box">
                    <html:text property="maPtFcRecDetailDTO.deptDesc" tabindex="100"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			
			<!-- 부품등급 -->
            <div class="field">
                <label>부품등급</label>
                <div class="input_box">
                    <html:text property="maPtFcRecDetailDTO.partGradeDesc" tabindex="105"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPtFcRecDetailDTO.remark" styleClass="ta50" tabindex="110" />
				</div>
			</div>
			<!-- 화폐 단위 -->
			<div class="field">
                <label><bean:message key="LABEL.currency"/></label>
                <div class="input_box">
                    <html:text property="maPtFcRecDetailDTO.currencyDesc" tabindex="244"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div> 
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>