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
<%@ page import="dream.work.pm.list.action.WorkPmiDInsDetailAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrListAction" %>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 일상점검목록 -->
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
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    workPmiDInsDetailForm.elements['maPmMstrCommonDTO.pmId'].value = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmId'].value;
    
    $("input[name='workPmiDInsDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='workPmiDInsDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });
    
    //교대조 세팅
    acSysDesc("workPmiDInsDetailDTO.shiftTypeDesc","workPmiDInsDetailDTO.shiftType","SHIFT_TYPE",true);
  
    setDisable(document.getElementsByName("disableDiv"));
    
    setTitle("workPmiDInsDetailDTO.pmInsDListNo");
    //For Save
    setForUpdate();
    
//     if(""==workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startTime'].value
//     		&& ""==workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endTime'].value)
//     {
//     	workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startTime'].value   = getMinusTime(false,1);
//         workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endTime'].value   = getTime(false);
//     }
    
    // 사원 자동완성
    empAc = new autoC({"workPmiDInsDetailDTO.empDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workPmiDInsDetailDTO.empId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workPmiDInsDetailDTO.deptId"
    });
    empAc.setKeyName("workPmiDInsDetailDTO.empId");
    empAc.init();

    // 담당부서 자동완성
    deptAc = new autoC({"workPmiDInsDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workPmiDInsDetailDTO.deptId":"dept_id"
    });
    deptAc.setKeyName("workPmiDInsDetailDTO.deptId");
    deptAc.init();
    
    // 확정자명 자동완성
    closeByAc = new autoC({"workPmiDInsDetailDTO.closeByDesc":"emp_name"});
    closeByAc.setTable("TAEMP");
    closeByAc.setAcResultMap({
        "workPmiDInsDetailDTO.closeId":"emp_id"
    });
    closeByAc.setKeyName("workPmiDInsDetailDTO.closeId");
    closeByAc.init();
    
    // 작업그룹 자동완성
    wkCtrAc = new autoC({"workPmiDInsDetailDTO.wkCtrDesc":"description"});
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setAcResultMap({
        "workPmiDInsDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrAc.setKeyName("workPmiDInsDetailDTO.wkCtrId");
    wkCtrAc.init();
    
    // 측정시간 자동완성
    acSysDesc("workPmiDInsDetailDTO.measureTime","workPmiDInsDetailDTO.measureTime","MEASURE_TIME",true);
 
    
    
    // 설비 자동완성 
    equipAc = new autoC({"workPmiDInsDetailDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("workPmiDInsDetailDTO.equipId");
    equipAc.setAcResultMap({
        "workPmiDInsDetailDTO.eqLocDesc":"eqLocDesc",
        "workPmiDInsDetailDTO.equipId":"equip_id"
    });
    equipAc.init();

//     if(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value=="C")
//     {
//         $('.b_confirm').hide(); 
//         setDisableAll();
//     }
//     else
//         setEnableAll();
    
    setWorkTime();
    
    setBtnAsStatus();
}


function setBtnAsStatus()
{
	if(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value=="C")
	{
		setDisableAll();
		hideBtn('CONFIRM');
		hideBtn("APPROVAL");
	}
	else if(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value=="IRWRA"
			|| workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value=="IRWOA"){
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
    
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmId'].value = workPmiDInsDetailForm.elements['workPmiDInsCommonDTO.popupPmId'].value;
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmInsDListNo'].value = workPmiDInsDetailForm.elements['workPmiDInsCommonDTO.popupWorkPointNo'].value;

    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.woType'].value   = "PMI";
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.woTypeDesc'].value   = "PMI";
    valSysDir('workPmiDInsDetailDTO.woType', 'workPmiDInsDetailDTO.woTypeDesc', 'WO_TYPE', true);
    
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmType'].value   = "DINS";
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmTypeDesc'].value   = "DINS";
    valSysDir('workPmiDInsDetailDTO.pmType', 'workPmiDInsDetailDTO.pmTypeDesc', 'PMI_TYPE', true);
    
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value = "P";
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatusDesc'].value = "P";
    valSysDir('workPmiDInsDetailDTO.schedStatus', 'workPmiDInsDetailDTO.schedStatusDesc', 'PMINSDLIST_STATUS', true);
    
    //출력버튼 감추기.
    $(".b_print").css("display","none");
  
    //교대조 세팅
    if(loginUser.shiftType!='null'){
        workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.shiftType'].value = loginUser.shiftType;
        workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.shiftTypeDesc'].value = loginUser.shiftTypeDesc;
    }
 	//담당자 자동완성 (로그인 유저)
	workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.empId'].value = loginUser.empId;
	workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.empDesc'].value   = loginUser.empName;
 	
	//담당부서 자동완성 (로그인 유저)
	
	workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.deptId'].value = loginUser.deptId;
	workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.deptDesc'].value   = loginUser.deptDesc;
	
    //작업시작일자, 종료일자 넣기.
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startDate'].value   = getToday();
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endDate'].value   = getToday();
    
    //작업시작시간(1시간전), 종료시간(현재시간) 넣기.
//     workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startTime'].value   = getMinusTime(false,1);
//     workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endTime'].value   = getTime(false);
    
    //작업그룹
/*     if(loginUser.wkctrId!='null')
    {
    	workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.wkCtrId'].value     = loginUser.wkctrId;
        workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.wkCtrDesc'].value   = loginUser.wkctrDesc;
    } */
    if(loginUser.filterWkCtrId!='null')
    {
    	workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.wkCtrId'].value     = loginUser.filterWkCtrId;
        workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.wkCtrDesc'].value   = loginUser.filterWkCtrDesc;
    }
    
	setWorkTime();

}

function setSequenceVal(sequenceVal)
{
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmInsDListId'].value = sequenceVal;
    workPmiDInsDetailForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = sequenceVal;
    
    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.wkorDate'].value = getToday();
}

// 작업시간
function setWorkTime(){
    var startDate = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startDate'].value;
    var startTime = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startTime'].value;
    var endDate = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endDate'].value;
    var endTime = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endTime'].value;

    workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.workTime'].value = setNumberFormat(getMinInterval(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startDate'], workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startTime'], workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endDate'],workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.endTime']));
    
 }

/**
 * 수정
 */
function goUpdate()
{
	if(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value=="C")
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

    if(ckCreate(currentPageId)) workPmiDInsDetailForm.strutsAction.value = "<%=WorkPmiDInsDetailAction.DETAIL_INPUT%>";
    else workPmiDInsDetailForm.strutsAction.value = "<%=WorkPmiDInsDetailAction.DETAIL_UPDATE%>";
    
    // 작업시간 확인
    if(M$('workPmiDInsDetailDTO.workTime').value == '') setWorkTime();
    
    var actionUrl = contextPath + "/workPmiDInsDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workPmiDInsDetailForm),'afterSave');

}

/**
 * Time 세팅되고 호출되는 Callback
 */
function afterSetTime(_name)
{
	setWorkTime();
}

/**
 * Date 세팅되고 호출되는 Callback
 */
function afterSetDate(_name)
{
	setWorkTime();
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
        parent.findGridRow(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmInsDListId'].value);

    workPmiDInsDetailForm.elements['workPmiDInsCommonDTO.pmInsDListId'].value = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmInsDListId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workPmiDInsDetailDTO.pmInsDListNo");
    
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
   
   var actionUrl = contextPath + "/workPmiDInsDetail.do";
    var param =  "&strutsAction=" + '<%= WorkPmiDInsDetailAction.WORK_PMI_DETAIL_CHECKPOINT %>'
              +  "&" + FormQueryString(workPmiDInsDetailForm);
    XMLHttpPostVal(actionUrl, param, 'setValidCheckPoint');
    
}
function setValidCheckPoint(ajaxXmlDoc)
{
   isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
   if(isValid == '0')
    {
       workPmiDInsDetailForm.strutsAction.value = '<%=WorkPmiDInsDetailAction.WORK_PMI_DETAIL_COMPLETE%>';
        var actionUrl = contextPath + "/workPmiDInsDetail.do";
           XMLHttpPost(actionUrl, FormQueryString(workPmiDInsDetailForm), 'afterConfirm');
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
               if(checkRequireValue('workPmiDInsDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
               
               //작업시간이 미래라면 완료 처리 안됨
               var todayStr = getToday().split("-").join("");
               var startDate = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.startDate'].value;
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
   workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value = "C";
   valSysDirCode('workPmiDInsDetailDTO.schedStatus', 'workPmiDInsDetailDTO.schedStatusDesc', 'PMSCHED_STATUS', true);
   //확정자명 = 로그인 유저
   if (parent.findGridRow) parent.findGridRow(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmInsDListId'].value);
   
//    if(workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.schedStatus'].value=="C")
//    {
//        $('.b_confirm').hide(); 
//    }
   setBtnAsStatus();
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId) 
{
    var form = document.workPmiDInsDetailForm;
    goCommonTabPage(form, '' , pageId);
       
}

/* audit Trail */
function goAudtrailLink()
{	
	var objectId = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmInsDListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmDParam'].value;
	var pmId     = workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}

/**
 * Print 버튼 클릭
 */
 function goPrint()
 {
	 reportCall('workPmiDInsDetail','workPmiDInsDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",workPmiDInsDetailForm.elements['workPmiDInsDetailDTO.pmInsDListId'].value); 
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

    <html:form action="/workPmiDInsDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="workPmiDInsCommonDTO.popupPmId"/>
        <html:hidden property="workPmiDInsCommonDTO.popupWorkPointNo"/>
        <html:hidden property="workPmiDInsCommonDTO.pmInsDListId"/>  <!-- Key -->
        <html:hidden property="workPmiDInsDetailDTO.pmInsDListId"/>  <!-- Key -->
		<html:hidden property="maPmMstrCommonDTO.pmId"/>
		<html:hidden property="workPmiDInsDetailDTO.pmId"/>
		<html:hidden property="workPmiDInsDetailDTO.wkOrId"/>
		<html:hidden property="workPmiDInsDetailDTO.deptId"/>
		<html:hidden property="workPmiDInsDetailDTO.equipId"/>
		<html:hidden property="workPmiDInsDetailDTO.empId"/>
		<html:hidden property="workPmiDInsDetailDTO.eqLocId"/>
		<html:hidden property="workPmiDInsDetailDTO.eqCtgId"/>
		<html:hidden property="workPmiDInsDetailDTO.woType"/>
		<html:hidden property="workPmiDInsDetailDTO.pmType"/>
		<html:hidden property="workPmiDInsDetailDTO.isLawEq"/>
		<html:hidden property="workPmiDInsDetailDTO.mainMngId"/>
		<html:hidden property="workPmiDInsDetailDTO.subMngId"/>
		<html:hidden property="workPmiDInsDetailDTO.shiftType"/>
		<html:hidden property="workPmiDInsDetailDTO.wkCtrId"/>
		<html:hidden property="workPmiDInsDetailDTO.selectedWkorId"/>
		<html:hidden property="workPmiDInsDetailDTO.eqCtgType"/>
		<html:hidden property="workPmiDInsDetailDTO.closeId"/>
		<html:hidden property="workPmiDInsDetailDTO.schedStatus"/>
		<html:hidden property="workPmiDInsDetailDTO.pmDParam"/>
        
        <html:hidden property="workPmiDInsDetailDTO.pmInsDSchedId"/>
        
        <div class="article_box">
            <div class="form_box">
	        <!-- 결과No -->
            <div class="field">
                <label><bean:message key="LABEL.resultNo"/></label>
                <div class="input_read" name ="disableDiv">
                    <html:text property="workPmiDInsDetailDTO.pmInsDListNo" tabindex="10" readonly="true"/>
                </div>
            </div>
            <!-- WO상태 -->
            <div class="field">
                <label><bean:message key="LABEL.woStatus"/></label>
                <div class="input_read">
                    <html:text property="workPmiDInsDetailDTO.schedStatusDesc" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 작업종류  -->
            <div class="field">
                <label><bean:message key="LABEL.woType"/></label>
                <div class="input_read">
                    <html:text property="workPmiDInsDetailDTO.woTypeDesc" tabindex="30" readonly="true" />
                </div>
            </div>
            <!-- 작업형태  -->
            <div class="field">
                <label><bean:message key="LABEL.pmType"/></label>
                <div class="input_read">
                    <html:text property="workPmiDInsDetailDTO.pmTypeDesc" tabindex="40" readonly="true" />
                </div>
            </div>
            
            <!-- <div class="field_divide"></div> -->
            
            <!-- 작업명 -->
            <div class="field_long">
                <label class="check"><bean:message key="LABEL.woName"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.description" tabindex="50" />
                </div>
            </div>
            <!-- 시설물 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.equipDesc"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.equipDesc" tabindex="60" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
            <!-- 회차 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.calibPointType"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.workNumber" tabindex="60" styleClass="num"/>
                </div>
            </div>
            
            <!-- <div class="field_blank"></div> -->
            
            <!-- 작업일자 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.wkorDate"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.wkorDate" tabindex="70" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
            <!-- 담당부서 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.manageDept"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.deptDesc" tabindex="80"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 작업그룹 -->
            <div class="field">
                <label><bean:message key="LABEL.wkCtr"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.wkCtrDesc" tabindex="90"/>
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
                    <html:text property="workPmiDInsDetailDTO.shiftTypeDesc" tabindex="100" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 담당자 -->
            <div class="field">
                <label class="lastcheck"><bean:message key="LABEL.manager"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.empDesc" tabindex="110"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            
            <!-- <div class="field_long_blank"></div> -->
            
            <!-- 운전시간 -->
            <div class="field">
               <label><bean:message key="LABEL.operatingTime"/></label>
               <div class="input_box">
                   <html:text property="workPmiDInsDetailDTO.operatingTime" tabindex="120" />
               </div>
            </div>
             
            <!-- 측정시간 -->
            <div class="field">
               <label><bean:message key="LABEL.measureTime"/></label>
               <div class="input_box">
                   <html:text property="workPmiDInsDetailDTO.measureTime" tabindex="130" />
                   <p class="open_spop"><a><span>조회</span></a></p>
               </div>
            </div>
            <!-- 작업시작시간 -->
            <div class="field">
                <label class="lastcheck"><bean:message key="LABEL.woFromTime"/></label>
                <div class="datetime_wrap">
                    <div class="input_box input_carendar">
                        <html:text property="workPmiDInsDetailDTO.startDate"  tabindex="140" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                    <div class="input_box">
                        <html:text property="workPmiDInsDetailDTO.startTime"   tabindex="150"/>
                        <p class="open_time"><span>시간</span></p>
                    </div>
                </div>
            </div>
            <!-- 확정자명 -->
            <div class="field">
                <label><bean:message key="LABEL.closeBy"/></label>
                <div class="input_box" >
                    <html:text property="workPmiDInsDetailDTO.closeByDesc" tabindex="160" />
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
                        <html:text property="workPmiDInsDetailDTO.endDate" tabindex="170" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                    <div class="input_box">
                        <html:text property="workPmiDInsDetailDTO.endTime" tabindex="180" />
                        <p class="open_time"><span>시간</span></p>
                    </div>
                </div>
            </div>
            <!-- 작업시간(분) -->
            <div class="field">
                <label><bean:message key="LABEL.woTimeMin"/></label>
                <div class="input_box">
                    <html:text property="workPmiDInsDetailDTO.workTime" tabindex="190" styleClass="num"/>
                </div>
            </div>
            <!-- 비고 -->
            <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="workPmiDInsDetailDTO.remark" styleClass="ta50" tabindex="200" />
                </div>
            </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
