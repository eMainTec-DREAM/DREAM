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
<%@ page import="dream.work.pm.list.action.MaPmMstrListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListDInsPointDetailAction" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	List list = (List)request.getAttribute("slideFileList");
%>
<html:html>
<head>
<!-- 점검 디테일 -->
<title><bean:message key='LABEL.pm' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var slideImage = new Array();
<%
if(list != null)
	for(int i= 0; list.size() > i ; i++)
	{
	    Map map = (Map)list.get(i);
%>
	slideImage.push('<%=(String)map.get("FILE_NAME")%>');
<% 
	}
%>	

<!-- //

/** 자동완성 변수 */

var partAc, stdCheckPointAc, uomAc;

function loadPage() 
{	
	setSlideImage();
	
	workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.pmId'].value = workPmListDInsPointDetailForm.elements['maPmMstrCommonDTO.pmId'].value;
	
	if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    setTitle("workPmListDInsPointDetailDTO.pmPointNo", "workPmListDInsPointDetailDTO.checkPoint");
    
    //For Save
    setForUpdate();
    
    // 시행여부 자동완성
    acSysDesc("workPmListDInsPointDetailDTO.isActive","workPmListDInsPointDetailDTO.isActiveId","IS_USE", true);
    
    // 수치/판정 구분
    acSysDesc("workPmListDInsPointDetailDTO.checkType","workPmListDInsPointDetailDTO.checkTypeId","CHECK_TYPE", true);
    
    // 부품코드 자동완성 
    partAc = new autoC({"workPmListDInsPointDetailDTO.partNo":"part_no"});
    partAc.setTable("TAPARTS");
    partAc.setKeyName("workPmListDInsPointDetailDTO.partNo");
    partAc.setAcResultMap({
        "workPmListDInsPointDetailDTO.partId":"part_id"
      , "workPmListDInsPointDetailDTO.partDesc":"description"
    });
    partAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    
    // 표준점검항목 자동완성 
    stdCheckPointAc = new autoC({"workPmListDInsPointDetailDTO.stwrkPointDesc":"check_point"});
    stdCheckPointAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    stdCheckPointAc.setTable("TASTDCHECKPOINT");
    stdCheckPointAc.setKeyName("workPmListDInsPointDetailDTO.stwrkPointId");
    stdCheckPointAc.setAcResultMap({
        "workPmListDInsPointDetailDTO.stwrkPointId":"std_check_point_id"
    });
    stdCheckPointAc.init();
    
    //단위
    uomAc = new autoC({"workPmListDInsPointDetailDTO.uom":"description"});
    uomAc.setAcConditionMap({
    	"is_use":"Y"
    	,"dir_type":"UOM"
    	,"comp_no":loginUser.compNo
  	   });
    uomAc.setTable("TACDUSRD");
    uomAc.init();

}

/**
 * 입력
 */
function goInput()
{
	setEnableAll();
	
    sequenceNextVal('SQAPM_POINT_ID');

    // 시행여부 자동입력
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.isActive'].value = "Y";
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.isActiveId'].value = "Y";
    valSysDir('workPmListDInsPointDetailDTO.isActive', 'workPmListDInsPointDetailDTO.isActiveId', 'IS_USE', true);
    
    // 수치/판정 자동입력
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.checkTypeId'].value = "SEN";
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.checkType'].value = "SEN";
    valSysDir('workPmListDInsPointDetailDTO.checkTypeId', 'workPmListDInsPointDetailDTO.checkType', 'CHECK_TYPE', true);
    
}

function setSequenceVal(sequenceVal)
{
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.pmPointId'].value = sequenceVal;
//    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.pmPointNo'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
    setEnableAll();
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

    if(ckCreate(currentPageId)) workPmListDInsPointDetailForm.strutsAction.value = "<%=WorkPmListDInsPointDetailAction.DETAIL_INPUT%>";
    else workPmListDInsPointDetailForm.strutsAction.value = "<%=WorkPmListDInsPointDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/workPmListDInsPointDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workPmListDInsPointDetailForm),'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    if (parent.findGridRow) {
        parent.findGridRow(workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.pmPointId'].value);
    }
    workPmListDInsPointDetailForm.elements['maPmMstrCommonDTO.pmPointId'].value = workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.pmPointId'].value;
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("workPmListDInsPointDetailDTO.pmPointNo", "workPmListDInsPointDetailDTO.checkPoint");

}

/**
 * 저장후생성후 호출
 */
function afterSavenew ()
{
	sequenceNextVal('SQAPM_POINT_ID');

	//점검순서, 점검항목
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.stepNum'].value = "";
    workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.checkPoint'].value = "";
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.pmPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

function loadSlideImages()
{
	var url = contextPath + "/workPmListDInsPointDetail.do";

    var oldSAction = workPmListDInsPointDetailForm.elements['strutsAction'].value;
    workPmListDInsPointDetailForm.elements['strutsAction'].value = '<%=WorkPmListDInsPointDetailAction.DETAIL_PHOTO%>'

    $.post(url,FormQueryString(workPmListDInsPointDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    workPmListDInsPointDetailForm.elements['strutsAction'].value = oldSAction;
}

/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" + workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.pmPointId'].value +
                "&" + "maDocImgCommonDTO.objectType=PM_POINT"+
                "&" + "maDocImgCommonDTO.objectDesc="+ workPmListDInsPointDetailForm.elements['workPmListDInsPointDetailDTO.checkPoint'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/workPmListDInsPointDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="maPmMstrCommonDTO.pmPointId" />              <!-- Key -->
        <html:hidden property="workPmListDInsPointDetailDTO.pmPointId" />   <!-- Key -->
        <html:hidden property="maPmMstrCommonDTO.pmId" />             
        <html:hidden property="workPmListDInsPointDetailDTO.checkPointId" />    
        <html:hidden property="workPmListDInsPointDetailDTO.pmId" />    
        <html:hidden property="workPmListDInsPointDetailDTO.eqAsmbId" />    
        <html:hidden property="workPmListDInsPointDetailDTO.checkMethodId" />    
        <html:hidden property="workPmListDInsPointDetailDTO.checkTypeId" />    
        <html:hidden property="workPmListDInsPointDetailDTO.stwrkPointId" />    
        <html:hidden property="workPmListDInsPointDetailDTO.isActiveId" />    
        
        <div class="article_box">
            <div class="form_box">
				<!-- 사진 -->
				<div class="field_img">
					<label><bean:message key="LABEL.photo"/></label>
					<div class="img_box" name="workPmListDInsPointDetailDTO.imgslide">
						<div class="function_box manual"><div class="fb_group1"><a class="b_photo"></a></div> </div>
						<div class="img_prev">
							<a></a>
						</div>
						<div class="img_next">
							<a></a>
						</div>
					</div>
				</div>
                <!-- 점검순서 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.pmStepNum"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.stepNum" tabindex="10" styleClass="num"/>
                    </div>
                </div>
                <!-- 점검항목 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.checkPoint"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.checkPoint" tabindex="20"/>
                    </div>
                </div>
                <!-- 점검부위 -->
                <div class="field">
                    <label><bean:message key="LABEL.pmAsmb"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.eqAsmbDesc" tabindex="30"/>
                    </div>
                </div>
                <!-- 적정기준 -->
                <div class="field">
                    <label><bean:message key="LABEL.fitBasis"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.fitBasis" tabindex="40"/>
                    </div>
                </div>
                <!-- 점검방법 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkMethod"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.checkMethod" tabindex="50"/>
                    </div>
                </div>
                <!-- 수치/판정 구분 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.checkType"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.checkType" tabindex="60"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 시행여부 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.isActive"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.isActive" tabindex="70"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 하한값 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkMin"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.checkMin" tabindex="80" styleClass="ty_num"/>
                    </div>
                </div>
                <!-- 표준점검항목 -->
                <div class="field">
                    <label><bean:message key="LABEL.stdPmCheckPoint"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.stwrkPointDesc" tabindex="90"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 기준값 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkBasisVal"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.checkBasisVal" tabindex="100" styleClass="ty_num"/>
                    </div>
                </div>
                <!-- 단위 -->
                <div class="field">
                    <label><bean:message key="LABEL.uom"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.uom" tabindex="110"/>
                        <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                    </div>
                </div>                
                <!-- 상한값 -->
                <div class="field">
                    <label><bean:message key="LABEL.checkMax"/></label>
                    <div class="input_box">
                        <html:text property="workPmListDInsPointDetailDTO.checkMax" tabindex="120" styleClass="ty_num"/>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="workPmListDInsPointDetailDTO.remark" styleClass="ta50" tabindex="130" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
