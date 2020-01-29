<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsDetailAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrListAction" %>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 파트체인지점검목록 -->
<title><bean:message key='LABEL.woNo' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var empAc, equipAc, closeByAc;

function loadPage() 
{	
    workPmiCInsDetailForm.elements['maPmMstrCommonDTO.pmId'].value = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmId'].value;
    
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    
    //교대조 세팅
    acSysDesc("workPmiCInsDetailDTO.shiftTypeDesc","workPmiCInsDetailDTO.shiftTypeId","SHIFT_TYPE",true);
  
    setDisable(document.getElementsByName("disableDiv"));
    
    setTitle("workPmiCInsDetailDTO.pmInsDListNo");
    //For Save
    setForUpdate();
    
    if(""==workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.startTime'].value
    		&& ""==workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endTime'].value)
    {
    	workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.startTime'].value   = getMinusTime(false,1);
        workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endTime'].value   = getTime(false);
    }
    
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmTypeId'].value   = "CINS";
    
    // 사원 자동완성
    empAc = new autoC({"workPmiCInsDetailDTO.empDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workPmiCInsDetailDTO.empId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workPmiCInsDetailDTO.deptId"
    });
    empAc.setKeyName("workPmiCInsDetailDTO.empId");
    empAc.init();

    // 담당부서 자동완성
    deptAc = new autoC({"workPmiCInsDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workPmiCInsDetailDTO.deptId":"dept_id"
    });
    deptAc.setKeyName("workPmiCInsDetailDTO.deptId");
    deptAc.init();
    
    // 확정자명 자동완성
    closeByAc = new autoC({"workPmiCInsDetailDTO.closeBy":"emp_name"});
    closeByAc.setTable("TAEMP");
    closeByAc.setAcResultMap({
        "workPmiCInsDetailDTO.closeById":"emp_id"
    });
    closeByAc.setKeyName("workPmiCInsDetailDTO.closeById");
    closeByAc.init();
    
    // 작업그룹 자동완성
    wkCtrAc = new autoC({"workPmiCInsDetailDTO.wkCtrDesc":"description"});
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setAcResultMap({
        "workPmiCInsDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrAc.setKeyName("workPmiCInsDetailDTO.wkCtrId");
    wkCtrAc.init();
    
    // 측정시간 자동완성
    acSysDesc("workPmiCInsDetailDTO.measureTime","workPmiCInsDetailDTO.measureTime","MEASURE_TIME",true);
 
    
    
    // 설비 자동완성 
    equipAc = new autoC({"workPmiCInsDetailDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("workPmiCInsDetailDTO.equipId");
    equipAc.setAcResultMap({
        "workPmiCInsDetailDTO.eqLocDesc":"eqLocDesc",
        "workPmiCInsDetailDTO.equipId":"equip_id"
    });
    equipAc.init();

    if(workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusId'].value=="C")
    {
        $('.b_confirm').hide(); 
        setDisableAll();
    }
    else
        setEnableAll();
    
    setWorkTime();
    
    setBtnAsStatus();
    
}

function setBtnAsStatus()
{
	if(workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusId'].value=="C")
	{
		setDisableAll();
		hideBtn('CONFIRM');
		hideBtn("APPROVAL");
	}
	else if(workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusId'].value=="IRWRA"
			|| workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusId'].value=="IRWOA"){
		setDisableAll();
		showBtn("CONFIRM");
		hideBtn("APPROVAL");
	}
	else{
		setEnableAll();
		showBtn("CONFIRM");
		showBtn("APPROVAL");
	}
}

/**
 * 입력
 */
function goInput()
{
	setEnableAll();
    sequenceNextVal('SQAPMINSDLIST_ID');

    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusId'].value = "P";
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusDesc'].value = "계획";
    valSysDir('workPmiCInsDetailDTO.schedStatusId', 'workPmiCInsDetailDTO.schedStatusDesc', 'PMINSDLIST_STATUS', true);
    
    //출력버튼 감추기.
    $(".b_print").css("display","none");
  
    //교대조 세팅
    if(loginUser.shiftType!='null'){
        workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.shiftTypeId'].value = loginUser.shiftType;
        workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.shiftTypeDesc'].value = loginUser.shiftTypeDesc;
    }
 	//담당자 자동완성 (로그인 유저)
	workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.empId'].value = loginUser.empId;
	workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.empDesc'].value   = loginUser.empName;
 	
	//담당부서 자동완성 (로그인 유저)
	
	workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.deptId'].value = loginUser.deptId;
	workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.deptDesc'].value   = loginUser.deptDesc;
	
    //작업시작일자, 종료일자 넣기.
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.startDate'].value   = getToday();
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endDate'].value   = getToday();
    
    //작업시작시간(1시간전), 종료시간(현재시간) 넣기.
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.startTime'].value   = getMinusTime(false,1);
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endTime'].value   = getTime(false);
    
    //작업그룹
/*     if(loginUser.wkctrId!='null')
    {
    	workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.wkCtrId'].value     = loginUser.wkctrId;
        workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.wkCtrDesc'].value   = loginUser.wkctrDesc;
    } */
    if(loginUser.filterWkCtrId!='null')
    {
    	workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.wkCtrId'].value     = loginUser.filterWkCtrId;
        workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.wkCtrDesc'].value   = loginUser.filterWkCtrDesc;
    }
}

function setSequenceVal(sequenceVal)
{
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmInsDListId'].value = sequenceVal;
    workPmiCInsDetailForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = sequenceVal;
    
    workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.wkorDate'].value = getToday();
}

// 작업시간
function setWorkTime(){
    var startDate = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.startDate'].value;
    var startTime = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.startTime'].value;
    var endDate = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endDate'].value;
    var endTime = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endTime'].value;

    if(startDate != "" && startTime != "" && endDate != "" && endTime != "")
    {
        var sDate = new Date();
        sDate.setFullYear(startDate.substring(0,4));
        sDate.setMonth(Number(startDate.substring(5,7)) -1);
        sDate.setDate(startDate.substring(8,10));
        sDate.setHours(startTime.substring(0,2));
        sDate.setMinutes(startTime.substring(3,5));
        var eDate = new Date();
        eDate.setFullYear(endDate.substring(0,4));
        eDate.setMonth(Number(endDate.substring(5,7)) -1);
        eDate.setDate(endDate.substring(8,10));
        eDate.setHours(endTime.substring(0,2));
        eDate.setMinutes(Number(endTime.substring(3,5))+1);
        var caleDate = new Date();
        caleDate.setFullYear(endDate.substring(0,4));
        caleDate.setMonth(Number(endDate.substring(5,7)) -1);
        caleDate.setDate(endDate.substring(8,10));
        caleDate.setHours(endTime.substring(0,2));
        caleDate.setMinutes(Number(endTime.substring(3,5)));
        
        //종료일자가 시작일자보다 큰경우 입력 불가
        if(sDate>caleDate)
        {
            workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endTime'].value = "";
            alertMessage1("<bean:message key='MESSAGE.MSG0110' />");
            workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.endTime'].focus();
            return;
        }
        else
        {
            
            var tempTime = Math.floor((eDate.getTime() - sDate.getTime())/60000);
            
            workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.workTime'].value = setNumberFormat(tempTime);
        }
        
    }
 }

/**
 * 수정
 */
function goUpdate()
{
	if(workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusId'].value=="C")
    {
        $('.b_confirm').hide(); 
    }
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

    if(ckCreate(currentPageId)) workPmiCInsDetailForm.strutsAction.value = "<%=WorkPmiCInsDetailAction.DETAIL_INPUT%>";
    else workPmiCInsDetailForm.strutsAction.value = "<%=WorkPmiCInsDetailAction.DETAIL_UPDATE%>";
    
    // 작업시간 확인
    if(M$('workPmiCInsDetailDTO.workTime').value == '') setWorkTime();
    
    var actionUrl = contextPath + "/workPmiCInsDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workPmiCInsDetailForm),'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    if (parent.findGridRow)
        parent.findGridRow(workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmInsDListId'].value);

    workPmiCInsDetailForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmInsDListId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workPmiCInsDetailDTO.pmInsDListNo");
    
    //저장후 완료버튼 보이기
    $(".b_confirm").css("display","");

}
/**
 * 점검항목의 모든 결과값이 들어있는지 확인.
 */
var isValid = 0;

function checkPoint()
{
   isValid=0;
   
   var actionUrl = contextPath + "/workPmiCInsDetail.do";
    var param =  "&strutsAction=" + '<%= WorkPmiCInsDetailAction.WORK_PMI_DETAIL_CHECKPOINT %>'
              +  "&" + FormQueryString(workPmiCInsDetailForm);
    XMLHttpPostVal(actionUrl, param, 'setValidCheckPoint');
    
}
function setValidCheckPoint(ajaxXmlDoc)
{
   isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
   if(isValid == '0')
    {
       workPmiCInsDetailForm.strutsAction.value = '<%=WorkPmiCInsDetailAction.WORK_PMI_DETAIL_COMPLETE%>';
        var actionUrl = contextPath + "/workPmiCInsDetail.do";
           XMLHttpPost(actionUrl, FormQueryString(workPmiCInsDetailForm), 'afterConfirm');
    }else{
       alertMessage1("<bean:message key='MESSAGE.MSG0112' />");
       return;
    }
   
}


/**
 * 작업완료
 */
 function goConfirm(){
    if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
    }else{
        getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0017'/>", function(result){
            if(result){
               //================================
               // 필수 항목 체크한다.
               //================================
               if(checkValidation()) return;
               //================================
               // 필수 확정항목 체크한다.
               //================================
               if(checkConfirmValidation()) return;
               if(checkRequireValue('workPmiCInsDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
               
               //작업시간이 미래라면 완료 처리 안됨
               var todayStr = getToday().split("-").join("");
               var startDate = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.startDate'].value;
               startDate = startDate.split("-").join("");
               
               if(todayStr < startDate)
               {
                   alertMessage1("<bean:message key='MESSAGE.MSG0034'/>");
                   return;
               }
               checkPoint();
            }
       });
    }
}
 /**
  * 완료후 호출
  */
function afterConfirm(ajaxXmlDoc)
{
   alertMessage1("<bean:message key='MESSAGE.MSG0015'/>");
   //작업상태 = C - 작업완료
   workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.schedStatusId'].value = "C";
   valSysDirCode('workPmiCInsDetailDTO.schedStatusId', 'workPmiCInsDetailDTO.schedStatusDesc', 'PMSCHED_STATUS', true);
   //확정자명 = 로그인 유저
   if (parent.findGridRow) parent.findGridRow(workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmInsDListId'].value);
   
   setBtnAsStatus();
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId) 
{
    var form = document.workPmiCInsDetailForm;
    goCommonTabPage(form, '' , pageId);
       
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmParam'].value;
	var pmId     = workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}

/**
 * Print 버튼 클릭
 */
 function goPrint()
 {
	 reportCall('workPmiCInsDetail','workPmiCInsDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",workPmiCInsDetailForm.elements['workPmiCInsDetailDTO.pmInsDListId'].value); 
 }
 

 function goWopdf(){
	 goPrint();
 }

 function goWopdfLink()
 {
	 goPrint();
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/workPmiCInsDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="workPmiCInsCommonDTO.popupPmId"/>
        <html:hidden property="workPmiCInsCommonDTO.pmInsDListId"/>  <!-- Key -->
        <html:hidden property="workPmiCInsDetailDTO.pmInsDListId"/>  <!-- Key -->
		<html:hidden property="maPmMstrCommonDTO.pmId"/>
		<html:hidden property="workPmiCInsDetailDTO.pmId"/>
		<html:hidden property="workPmiCInsDetailDTO.wkOrId"/>
		<html:hidden property="workPmiCInsDetailDTO.deptId"/>
		<html:hidden property="workPmiCInsDetailDTO.equipId"/>
		<html:hidden property="workPmiCInsDetailDTO.empId"/>
		<html:hidden property="workPmiCInsDetailDTO.eqLocId"/>
		<html:hidden property="workPmiCInsDetailDTO.eqCtgId"/>
		<html:hidden property="workPmiCInsDetailDTO.woTypeId"/>
		<html:hidden property="workPmiCInsDetailDTO.pmTypeId"/>
		<html:hidden property="workPmiCInsDetailDTO.isLawEqId"/>
		<html:hidden property="workPmiCInsDetailDTO.mainMngId"/>
		<html:hidden property="workPmiCInsDetailDTO.subMngId"/>
		<html:hidden property="workPmiCInsDetailDTO.shiftTypeId"/>
		<html:hidden property="workPmiCInsDetailDTO.wkCtrId"/>
		<html:hidden property="workPmiCInsDetailDTO.selectedWkorId"/>
		<html:hidden property="workPmiCInsDetailDTO.eqCtgTypeId"/>
		<html:hidden property="workPmiCInsDetailDTO.closeById"/>
		<html:hidden property="workPmiCInsDetailDTO.schedStatusId"/>
		<html:hidden property="workPmiCInsDetailDTO.productId"/>
		<html:hidden property="workPmiCInsDetailDTO.pmParam"/>
        
		<html:hidden property="workPmiCInsDetailDTO.pmInsDSchedId"/>
        <div class="article_box">
            <div class="form_box">
	        <!-- 결과No -->
            <div class="field">
                <label><bean:message key="LABEL.resultNo"/></label>
                <div class="input_read" name ="disableDiv">
                    <html:text property="workPmiCInsDetailDTO.pmInsDListNo" tabindex="10" readonly="true"/>
                </div>
            </div>
            <!-- WO상태 -->
            <div class="field">
                <label><bean:message key="LABEL.woStatus"/></label>
                <div class="input_read">
                    <html:text property="workPmiCInsDetailDTO.schedStatusDesc" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 작업종류  -->
            <div class="field">
                <label><bean:message key="LABEL.woType"/></label>
                <div class="input_read">
                    <html:text property="workPmiCInsDetailDTO.woTypeDesc" tabindex="30" readonly="true" />
                </div>
            </div>
            <!-- 작업형태  -->
            <div class="field">
                <label><bean:message key="LABEL.pmType"/></label>
                <div class="input_read">
                    <html:text property="workPmiCInsDetailDTO.pmTypeDesc" tabindex="40" readonly="true" />
                </div>
            </div>
            
            <!-- <div class="field_divide"></div> -->
            
            <!-- 작업명 -->
            <div class="field_long">
                <label class="check"><bean:message key="LABEL.woName"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.description" tabindex="50" />
                </div>
            </div>
            <!-- 시설물 -->
            <div class="field">
                <label class="check"><bean:message key="CODESET.EQCTG_TYPE"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.equipDesc" tabindex="60" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!--생산제품 -->
			<div class="field">
				<label><bean:message key="LABEL.prodGoods" /></label>
				<div class="input_read">
					<html:text property="workPmiCInsDetailDTO.productDesc" tabindex="70" readonly="true" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
            <!-- 회차 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.calibPointType"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.workNumber" tabindex="80" styleClass="num"/>
                </div>
            </div>
            
            <!-- <div class="field_blank"></div> -->
            
            <!-- 작업일자 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.wkorDate"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.wkorDate" tabindex="90" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
            <!-- 담당부서 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.manageDept"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.deptDesc" tabindex="100"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 작업그룹 -->
            <div class="field">
                <label><bean:message key="LABEL.wkCtr"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.wkCtrDesc" tabindex="110"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 교대조 -->
            <div class="field">
                <label><bean:message key="LABEL.shiftType"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.shiftTypeDesc" tabindex="100" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 담당자 -->
            <div class="field">
                <label class="lastcheck"><bean:message key="LABEL.manager"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.empDesc" tabindex="110"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            
            <!-- <div class="field_long_blank"></div> -->
            
            <!-- 운전시간 -->
            <div class="field">
               <label><bean:message key="LABEL.operatingTime"/></label>
               <div class="input_box">
                   <html:text property="workPmiCInsDetailDTO.operatingTime" tabindex="120" />
               </div>
            </div>
             
            <!-- 측정시간 -->
            <div class="field">
               <label><bean:message key="LABEL.measureTime"/></label>
               <div class="input_box">
                   <html:text property="workPmiCInsDetailDTO.measureTime" tabindex="130" />
                   <p class="open_spop"><a><span>조회</span></a></p>
               </div>
            </div>
            <!-- 작업시작시간 -->
            <div class="field">
                <label class="lastcheck"><bean:message key="LABEL.woFromTime"/></label>
                <div class="datetime_wrap">
                    <div class="input_box input_carendar">
                        <html:text property="workPmiCInsDetailDTO.startDate"  tabindex="140" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                    <div class="input_box">
                        <html:text property="workPmiCInsDetailDTO.startTime"   tabindex="150"/>
                        <p class="open_time"><span>시간</span></p>
                    </div>
                </div>
            </div>
            <!-- 확정자명 -->
            <div class="field">
                <label><bean:message key="LABEL.closeBy"/></label>
                <div class="input_box" >
                    <html:text property="workPmiCInsDetailDTO.closeBy" tabindex="160" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 작업종료시간-->
            <div class="field">
                <label class="lastcheck"><bean:message key="LABEL.woToTime"/></label>
                <div class="datetime_wrap">
                    <div class="input_box input_carendar">
                        <html:text property="workPmiCInsDetailDTO.endDate" tabindex="170" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                    <div class="input_box">
                        <html:text property="workPmiCInsDetailDTO.endTime" tabindex="180" />
                        <p class="open_time"><span>시간</span></p>
                    </div>
                </div>
            </div>
            <!-- 작업시간(분) -->
            <div class="field">
                <label><bean:message key="LABEL.woTimeMin"/></label>
                <div class="input_box">
                    <html:text property="workPmiCInsDetailDTO.workTime" tabindex="190" styleClass="num"/>
                </div>
            </div>
            <!-- 비고 -->
            <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="workPmiCInsDetailDTO.remark" styleClass="ta50" tabindex="200" />
                </div>
            </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
