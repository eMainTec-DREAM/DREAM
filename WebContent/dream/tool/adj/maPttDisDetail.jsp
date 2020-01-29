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
<%@ page import="dream.tool.adj.action.MaPttDisDetailAction"%>
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
var deptAc,mainMngAc,wareHouseAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maPttDisPartsList");
		goUpdate(); 
	}
	
	setTitle("maPttDisDetailDTO.ptdisuselistId", "maPttDisDetailDTO.description");
	
	setForUpdate();
	
	deptAc = new autoC({"maPttDisDetailDTO.exeDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttDisDetailDTO.exeDept":"dept_id"
    });
    deptAc.setKeyName("maPttDisDetailDTO.exeDept");
    deptAc.init();
    
    mainMngAc = new autoC({"maPttDisDetailDTO.exeByName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPttDisDetailDTO.exeDept"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttDisDetailDTO.exeBy":"emp_id"
    });
    mainMngAc.setKeyName("maPttDisDetailDTO.exeBy");
    mainMngAc.init();
    
    wareHouseAc = new autoC({"maPttDisDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttDisDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.setKeyName("maPttDisDetailDTO.wcodeId");
    wareHouseAc.init();
}


	
function goUpdate()
{
    //수정시 readonly설정 
    //maPttDisDetailForm.elements['maPttDisDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  
    // 버튼 활성화 
    var ptDisStatus = maPttDisDetailForm.elements['maPttDisDetailDTO.ptDisStatus'].value;

    if(ptDisStatus == "W") // 작성중 
    {
	    $(document).find('.b_confirmdis').show();
	    $(document).find('.b_save').show();

	    // 입력 Form disable
        setEnable($(".form_box"));
    }
    else if(ptDisStatus == "C") // 반납완료 
    {
        $(document).find('.b_confirmdis').hide();
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
	sequenceNextVal('SQAPTDISUSELIST_ID');
	
	maPttDisDetailForm.elements['maPttDisDetailDTO.disDate'].value = getToday(); 
	maPttDisDetailForm.elements['maPttDisDetailDTO.exeDept'].value = loginUser.deptId;
    maPttDisDetailForm.elements['maPttDisDetailDTO.exeDeptDesc'].value = loginUser.deptDesc;

    maPttDisDetailForm.elements['maPttDisDetailDTO.wcodeId'].value = loginUser.twcodeId;
    maPttDisDetailForm.elements['maPttDisDetailDTO.wname'].value = loginUser.twname;
    
    maPttDisDetailForm.elements['maPttDisDetailDTO.exeBy'].value = loginUser.empId;
    maPttDisDetailForm.elements['maPttDisDetailDTO.exeByName'].value = loginUser.userName;
	//maPttDisDetailForm.elements['maPttDisDetailDTO.recQty'].value = "0"; 
	//maPttDisDetailForm.elements['maPttDisDetailDTO.unitPrice'].value = "0"; 
	//maPttDisDetailForm.elements['maPttDisDetailDTO.totPrice'].value = "0"; 

	// 출고상태 : W=작성중
	maPttDisDetailForm.elements['maPttDisDetailDTO.ptDisStatus'].value = "W"; 
	valSysDirCode('maPttDisDetailDTO.ptDisStatus', 'maPttDisDetailDTO.ptDisStatusDesc', 'PTDISUSE_STATUS', true);

	// 버튼 비활성화 
/* 	$(document).find('.b_rec_complete').hide(); */
	$(document).find('.b_confirmdis').hide();
}

function setSequenceVal(sequenceVal)
{
	maPttDisDetailForm.elements['maPttDisDetailDTO.ptdisuselistId'].value = sequenceVal;
	maPttDisDetailForm.elements['maPttDisCommonDTO.ptdisuselistId'].value = sequenceVal;
	goTabPage("maPttDisPartsList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maPttDisDetailForm;

	goCommonTabPage(form, '' , pageId);
    
}

/**
 * 저장
 */ 
function goSave()
{
	// 반납완료된 정보일 경우 "저장" 불가.
	if(!ckCreate(currentPageId) 
			&& maPttDisDetailForm.elements['maPttDisDetailDTO.ptDisStatus'].value == 'C') 
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0012' />");
		return;
	}
		
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPttDisDetailForm.strutsAction.value = '<%=MaPttDisDetailAction.PTDIS_DETAIL_INPUT%>';
	else maPttDisDetailForm.strutsAction.value = '<%=MaPttDisDetailAction.PTDIS_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPttDisDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPttDisDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPttDisDetailForm.elements['maPttDisDetailDTO.ptdisuselistId'].value = maPttDisDetailForm.elements['maPttDisCommonDTO.ptdisuselistId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPttDisDetailForm.elements['maPttDisDetailDTO.ptdisuselistId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("maPttDisDetailDTO.ptdisuselistId", "maPttDisDetailDTO.description");
}
 


/**
 * 폐기완료 처리 
 */
function goConfirmdis()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0090'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;

				 maPttDisDetailForm.strutsAction.value = '<%=MaPttDisDetailAction.PTDIS_COMPLETE%>';
				 var actionUrl = contextPath + "/maPttDisDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maPttDisDetailForm), 'afterConfirm');
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

		//폐기 처리했습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0086'/>");
		
		//완료!
		maPttDisDetailForm.elements['maPttDisDetailDTO.ptDisStatus'].value = "C";
		valSysDirCode('maPttDisDetailDTO.ptDisStatus', 'maPttDisDetailDTO.ptDisStatusDesc', 'PTDISUSE_STATUS', true);

		
		if (parent.findGridRow)	parent.findGridRow(maPttDisDetailForm.elements['maPttDisCommonDTO.ptdisuselistId'].value);

        $(document).find('.b_confirmdis').hide();
        $(document).find('.b_save').hide();

        // 입력 Form disable
        setDisable($(".form_box"));
	}
	else if("F" == rtnValue[0])
	{
		//1건 이상의 자재를 선택해주세요.
		alertMessage1("<bean:message key='MESSAGE.MSG0088'/>");
	}
	else
	{
		//폐기에 문제가 있습니다. 사이트 관리자에게 문의하세요.
		alertMessage1("<bean:message key='MESSAGE.MSG0087'/>");
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
		maPttDisDetailForm.elements['maPttDisDetailDTO.ptDisStatus'].value = "X";
		valSysDirCode('maPttDisDetailDTO.ptDisStatus', 'maPttDisDetailDTO.ptDisStatusDesc', 'PTDISUSE_STATUS', true);

		
		if (parent.findGridRow)	parent.findGridRow(maPttDisDetailForm.elements['maPttDisCommonDTO.ptdisuselistId'].value);
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
    var objectId = maPttDisDetailForm.elements['maPttDisDetailDTO.ptdisuselistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPttDisDetail">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPttDisCommonDTO.ptdisuselistId" />
	<html:hidden property="maPttDisDetailDTO.ptDisStatus" />
	<html:hidden property="maPttDisDetailDTO.exeDept" />
	<html:hidden property="maPttDisDetailDTO.wcodeId" />
	<html:hidden property="maPttDisDetailDTO.exeBy" />

	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.ptDisuseListId"/></label>
				<div class="input_read">
					<html:text property="maPttDisDetailDTO.ptdisuselistId" tabindex="10" readonly="true"/>
				</div>
			</div>
			<div class="field" class="check">
				<label><bean:message key="LABEL.disUseStatus"/></label>
				<div class="input_read">
					<html:text property="maPttDisDetailDTO.ptDisStatusDesc" tabindex="20" readonly="true" />
				</div>
			</div>
			<div class="field_long">
			<label class="check"><bean:message key="LABEL.title"/></label>
                <div class="input_box">
                    <html:text property="maPttDisDetailDTO.description" tabindex="30"/>
                </div>
            </div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.disUseDate"/></label>
                <div class="input_box">
                    <html:text property="maPttDisDetailDTO.disDate" tabindex="40" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>			
            <div class="field">
               <label class="check"><bean:message key="LABEL.wname"/></label>
               <div id="wnameDiv" class="input_box">
                   <html:text property="maPttDisDetailDTO.wname" tabindex="50"/>
                   <p id="wnameSchBtn" class="open_spop">
                       <a>
                           <span>조회</span>
                       </a>    
                   </p>
               </div> 
             </div>   

            <div class="field">
                <label><bean:message key="LABEL.exeDept"/></label>
                <div class="input_box">
                    <html:text property="maPttDisDetailDTO.exeDeptDesc" tabindex="60"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			  
            <div class="field">
                <label  class="check"><bean:message key="LABEL.exeBy"/></label>
                <div class="input_box">
                    <html:text property="maPttDisDetailDTO.exeByName" tabindex="70"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div> 
            <!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPttDisDetailDTO.remark" styleClass="ta50" tabindex="80" />
				</div>
			</div>    
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>