<%--===========================================================================
상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.wrkimp.action.MaWrkimpDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!--  -->
<title><bean:message key='LABEL.empNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maWrkimpDetailDTO.gowrkimpNo", "maWrkimpDetailDTO.description");
    
    setForUpdate();
    
    
    wrkImpCreTypeAc = new autoC({"maWrkimpDetailDTO.wrkimpCreTypeDesc":"description"});
    wrkImpCreTypeAc.setAcConditionMap({
            "list_type":"WRKIMP_CRE_TYPE",
            "is_use":"Y"
       });
    wrkImpCreTypeAc.setTable("TACDSYSD");
    wrkImpCreTypeAc.setKeyName("maWrkimpDetailDTO.wrkimpCreTypeId");
    wrkImpCreTypeAc.setAcResultMap({
        "maWrkimpDetailDTO.wrkimpCreTypeId":"cdsysd_no"
    });
    wrkImpCreTypeAc.init();
    
    
    wrkImpStatusTypeAc = new autoC({"maWrkimpDetailDTO.gowrkimpStatusDesc":"description"});
    wrkImpStatusTypeAc.setAcConditionMap({
            "list_type":"GOWRKIMP_STATUS",
            "is_use":"Y"
       });
    wrkImpStatusTypeAc.setTable("TACDSYSD");
    wrkImpStatusTypeAc.setKeyName("maWrkimpDetailDTO.gowrkimpStatus");
    wrkImpStatusTypeAc.setAcResultMap({
        "maWrkimpDetailDTO.gowrkimpStatus":"cdsysd_no"
    });
    wrkImpStatusTypeAc.init();
    
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maWrkimpDetailForm;
	
	if(pageId == "maWrkImpDocLibList")
	{	
		maWrkimpDetailForm.elements['maDocLibCommonDTO.objectId'].value = maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpId'].value;
		maWrkimpDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WI";  // docDesc
		maWrkimpDetailForm.elements['maDocLibCommonDTO.description'].value = maWrkimpDetailForm.elements['maWrkimpDetailDTO.description'].value;
		goCommonTabPage(form, '<%=MaWrkimpDetailAction.BASE_QUICK_SEARCH%>' , pageId); 
	}
	else
	goCommonTabPage(form, '' , pageId);
}

/**
 * 작업중
 */
function goWo()
{
	var wsDate = maWrkimpDetailForm.elements['maWrkimpDetailDTO.workSdate'].value;
	//WorkSDate, workBy, workByName , WK
	if(wsDate == "")maWrkimpDetailForm.elements['maWrkimpDetailDTO.workSdate'].value = getToday();
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatus'].value = "WK";
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.helpdeskStatus'].value = "WK";  //작업중
	
	goSave();
	
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatusDesc'].value = "WK";
    valSysDir('maWrkimpDetailDTO.gowrkimpStatus', 'maWrkimpDetailDTO.gowrkimpStatusDesc', 'GOWRKIMP_STATUS', true);
}

/**
 * 작업완료
 */
function goConfirm()
{
	//workEDate , CP
	
	var wsDate = maWrkimpDetailForm.elements['maWrkimpDetailDTO.workEdate'].value;
	if(wsDate == "")maWrkimpDetailForm.elements['maWrkimpDetailDTO.workEdate'].value = getToday();
	
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatus'].value = "CP";
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.helpdeskStatus'].value = "CP";  //완료
	
	goSave();
	
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatusDesc'].value = "CP";
    valSysDir('maWrkimpDetailDTO.gowrkimpStatus', 'maWrkimpDetailDTO.gowrkimpStatusDesc', 'GOWRKIMP_STATUS', true);
}

/**
 * 검토중
 */
function goApprove()
{
	//ViewDate, ViewBy, ViewByName, RV, writeBy, writeByName, writeDate
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.viewDate'].value = getToday();
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatus'].value = "RV";
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.helpdeskStatus'].value = "RV";  //검토중
	
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.writeDate'].value = getToday();
	
	goSave();
	
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatusDesc'].value = "RV";
    valSysDir('maWrkimpDetailDTO.gowrkimpStatus', 'maWrkimpDetailDTO.gowrkimpStatusDesc', 'GOWRKIMP_STATUS', true);
	
}

function afterBtnLoad()
{
	var wkStatus = maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatus'].value;
	
	if(wkStatus == "WT")	//작성중
	{
		$('.b_approve').show();
		$('.b_wo').hide();
		$('.b_confirm').hide();
	}
	else if(wkStatus == "RV") //검토중
	{
		$('.b_approve').hide();
		$('.b_wo').show();
		$('.b_confirm').hide();
	}
	else if(wkStatus =="WK") //작업중
	{
		$('.b_approve').hide();
		$('.b_wo').hide();
		$('.b_confirm').show();
	}
	else if(wkStatus =="CP") //작업완료
	{
		$('.b_approve').hide();
		$('.b_wo').hide();
		$('.b_confirm').hide();
		$('.b_save').hide();
	}
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAGOWRKIMP_ID');
}

/**
 * 수정
 */
function goUpdate()
{
    //상태에 따라 readonly?
    //setDisable(document.getElementById("empNoDiv"));
    //setDisable(document.getElementById("deptDiv"));
    //setDisable(document.getElementById("empNameDiv")); 
}

function setSequenceVal(sequenceVal)
{
    maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpId'].value = sequenceVal;
    maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpNo'].value = sequenceVal;
    maWrkimpDetailForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = sequenceVal;

    maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpStatusDesc'].value = "WT";
    valSysDir('maWrkimpDetailDTO.gowrkimpStatus', 'maWrkimpDetailDTO.gowrkimpStatusDesc', 'GOWRKIMP_STATUS', true);
	
    maWrkimpDetailForm.elements['maWrkimpDetailDTO.writeDate'].value = getToday();
	maWrkimpDetailForm.elements['maWrkimpDetailDTO.writeByName'].value = loginUser.empName;
}

function afterValSysDir(type)
{
	if(type == "GOWRKIMP_STATUS") afterBtnLoad();
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
    if(ckCreate(currentPageId)) maWrkimpDetailForm.strutsAction.value = "<%=MaWrkimpDetailAction.WRKIMP_DETAIL_INPUT%>";
    else maWrkimpDetailForm.strutsAction.value = '<%=MaWrkimpDetailAction.WRKIMP_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maWrkImpDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maWrkimpDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maWrkimpDetailForm.elements['maWrkimpCommonDTO.gowrkimpId'].value = maWrkimpDetailForm.elements['maWrkimpDetailDTO.gowrkimpId'].value;

     //parent.goSearch();
     if (parent.findGridRow)parent.findGridRow(maWrkimpDetailForm.elements['maWrkimpCommonDTO.gowrkimpId'].value);
     
     setTitle("maWrkimpDetailDTO.gowrkimpNo", "maWrkimpDetailDTO.description");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maWrkImpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWrkimpCommonDTO.compNo" />
	<html:hidden property="maWrkimpCommonDTO.gowrkimpId" />
	<html:hidden property="maWrkimpDetailDTO.gowrkimpId" />
	<html:hidden property="maWrkimpDetailDTO.gowrkimpStatus" />
	<html:hidden property="maWrkimpDetailDTO.reqBy" />
	<html:hidden property="maWrkimpDetailDTO.compNo" />
	<html:hidden property="maWrkimpDetailDTO.helpdeskId" />
	<html:hidden property="maWrkimpDetailDTO.helpdeskStatus" />
	<html:hidden property="maWrkimpDetailDTO.wrkimpCreTypeId" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	
    <div class="article_box">
        <div class="form_box">
            <!-- 개선번호 -->
            <div class="field">
				<label><bean:message key="LABEL.goWrkImpNo"/></label>
				<div class="input_read">
					<html:text property="maWrkimpDetailDTO.gowrkimpNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.proStatus"/></label>
				<div class="input_box">
					<html:text property="maWrkimpDetailDTO.gowrkimpStatusDesc" tabindex="20" readonly="true"/>
					<p class="open_spop"><a href="javascript:lovSysDir('maWrkimpDetailDTO.gowrkimpStatus', 'maWrkimpDetailDTO.gowrkimpStatusDesc','GOWRKIMP_STATUS');"><span>조회</span></a></p>
				</div>
			</div>
            
			<!-- 작성일자 -->
			<div class="field">
				<label><bean:message key="LABEL.updDate"/></label>
				<div class="input_read">
					<html:text property="maWrkimpDetailDTO.writeDate" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 작성자 -->
			<div class="field">
				<label><bean:message key="LABEL.writeBy"/></label>
				<div class="input_box">
					<html:text property="maWrkimpDetailDTO.writeByName" tabindex="40"/>
				</div>
			</div>
			<!-- 제목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="maWrkimpDetailDTO.description" tabindex="50" />
				</div>
			</div>
			
			
			
			<!-- Site -->
            <div class="field_long">
                <label class="check"><bean:message key="LABEL.wrkimpSite"/></label>
                <div class="input_box">
                    <html:text property="maWrkimpDetailDTO.wrkimpSite" tabindex="60" />
                </div>
            </div>
            
            
            <!-- 발생구분 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.wrkimpCreTypeDesc"/></label>
                <div class="input_box">
                    <html:text property="maWrkimpDetailDTO.wrkimpCreTypeDesc" tabindex="70" />
                    <p class="open_spop"><a href="javascript:lovSysDir('maWrkimpDetailDTO.wrkimpCreTypeId', 'maWrkimpDetailDTO.wrkimpCreTypeDesc','WRKIMP_CRE_TYPE');"><span>조회</span></a></p>
                </div>
            </div>
            
            
            
            
			<!-- 작업내용 -->
            <div class="field_long">
                <label class="check"><bean:message key="LABEL.perform"/></label>
                <div class="input_box">
                    <html:textarea property="maWrkimpDetailDTO.perform" styleClass="ta150" tabindex="60" />
                </div>
            </div>
			
			
			
			
			
			
			
			
			<!-- 검토일자 -->
			<div class="field">
				<label><bean:message key="LABEL.viewDate"/></label>
				<div class="input_box">
					<html:text property="maWrkimpDetailDTO.viewDate" tabindex="70"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 검토자 -->
			<div class="field">
				<label><bean:message key="LABEL.viewBy"/></label>
				<div class="input_box">
					<html:text property="maWrkimpDetailDTO.viewByName" tabindex="80"/>
				</div>
			</div>
			
			<!-- 작업일자 -->
			<div class="field">
				<label><bean:message key="LABEL.woDate"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWrkimpDetailDTO.workSdate" tabindex="90" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="maWrkimpDetailDTO.workEdate" tabindex="100" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			
			
			<!-- 소요시간 -->
            <div class="field">
                <label><bean:message key="LABEL.workImpTime"/></label>
                <div class="input_box">
                    <html:text property="maWrkimpDetailDTO.workImpTime" tabindex="110"  styleClass="num"/>
                </div>
            </div>
			
			
			
			
			
			
			<!-- 작업자 -->
			<div class="field">
				<label><bean:message key="LABEL.woCraft"/></label>
				<div class="input_box">
					<html:text property="maWrkimpDetailDTO.workByName" tabindex="120"/>
				</div>
			</div>		
			<div class="field_divide"></div>
			<!-- 요청번호 -->
            <div class="field">
				<label><bean:message key="LABEL.reqNo"/></label>
				<div class="input_read">
					<html:text property="maWrkimpDetailDTO.helpdeskNo" readonly="true"/>
				</div>
			</div>
			<!-- 요청일자 -->
			<div class="field">
				<label><bean:message key="LABEL.reqDate"/></label>
				<div class="input_read">
					<html:text property="maWrkimpDetailDTO.reqDate" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 -->
			<div class="field">
				<label><bean:message key="LABEL.reqBy"/></label>
				<div class="input_read">
					<html:text property="maWrkimpDetailDTO.reqByName"  readonly="true"/>
				</div>
			</div>
			<!-- 요청내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.request"/></label>
				<div class="input_read">
					<html:textarea property="maWrkimpDetailDTO.request" styleClass="ta50" readonly="true"/>
				</div>
			</div>
			
    </div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>             
</body>
</html:html>
