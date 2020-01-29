<%--===========================================================================
첨부파일 - 상세
author  jung7126
version $Id: maDocLibDetail.jsp,v 1.5 2014/07/02 04:13:54 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.doc.file.action.MaDocLibDetailAction"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="common.bean.User" %>
<%@ page import="common.bean.MwareConfig"%>

<!DOCTYPE html>
<html:html>
<head>
<title><bean:message key='TAB.maDocLibDetail' />
</title>
<meta name="decorator" content="tabPage">
<style>
.progress { position:relative; width:98%; border: 0px solid #ddd; padding: 1px; border-radius: 3px; }
.bar { background-color: #B4F5B4; width:0%; height:20px; border-radius: 3px; }
.percent { position:absolute; display:inline-block; top:3px; left:48%; }
</style>
<%
	User user = (User)session.getAttribute(session.getId());
	String userSecGrade = user.getSecurGrade();
%>
<script language="javascript">// 저장 시 수행되는 action
var deleteRows;
var myVault;
var docctgAc,docCategDescAc;
var isLoad = false;
function loadPage() 
{
	deleteRows = new Array();
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maDocLibDetailDTO.docNo","maDocLibDetailDTO.description");
	//For Save
	setForUpdate();
	
	setTimeout(loadFileBox, 200);

	docctgAc = new autoC({"maDocLibDetailDTO.docctgDesc":"description"});
    docctgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    docctgAc.setTable("TADOCCTG");
    docctgAc.setKeyName("maDocLibDetailDTO.docctgId");
    docctgAc.setAcResultMap({
        "maDocLibDetailDTO.docctgId":"docctg_id"
    });
    docctgAc.init();
    
	docCategDescAc = new autoC({"maDocLibDetailDTO.docCategDesc":"description"});
    docCategDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "dir_type":"DOC_CATEG"
 	   });
    docCategDescAc.setTable("TACDUSRD");
    docCategDescAc.setKeyName("maDocLibDetailDTO.docCateg");
    docCategDescAc.setAcResultMap({
        "maDocLibDetailDTO.docCateg":"cdusrd_no"
    });
    docCategDescAc.init(); 

    acSysDesc("maDocLibDetailDTO.securGradeDesc","maDocLibDetailDTO.securGrade","SECUR_GRADE", true);
}

function loadFileBox()
{
	var oldStrutsAction = maDocLibDetailForm.strutsAction.value;
	maDocLibDetailForm.strutsAction.value = '<%=MaDocLibDetailAction.DOCLIB_FILE_SAVE%>';
	var actionUrl = contextPath + "/maDocLibDetail.do?"+FormQueryString(maDocLibDetailForm)+"&isUploadPage=true";

	//파일 업로드 객체 생성!
	myVault = new dhtmlXVaultObject({
		container:  "vaultObj",             // html container for vault
	    uploadUrl:  actionUrl,           // html4/html5 upload url
	  //  swfPath:    "../../common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
	    swfPath:    "common/dhtmlxSuite/codebase/dhxvault.swf",             // path to flash uploader
	    swfUrl:     actionUrl        // flash upload url
		,autoStart : false
		,autoRemove : false
		//,buttonUpload  : true
		,buttonClear: false
	});
	myVault.setSkin('dhx_terrace'); //스킨 변경
	myVault.setProgressMode("eta");
	
	maDocLibDetailForm.strutsAction.value = oldStrutsAction;
	
	//Download 설정 세팅
    var downUrl = contextPath + "/download.do"; 
	myVault.setDownloadURL(downUrl+"?physicalFileName={serverName}&fileType=DOCUMENT&isDownloadPage=true");
	
	attachEventAct();
	
	//문서권한 수정 권한
	setSecurity();
	
	//처음 로딩시 파일 로딩
	loadUploadedFile();
}

function attachEventAct()
{
	if(typeof myVault == "undefined") return;
	
	myVault.attachEvent("onFileAdd", function(files){

		if(!isLoad) getTopPage().updateArray[currentPageId] = "FILE";  //업로드 셋
	});
	
	myVault.attachEvent("onFileRemove", function(file){

		deleteRows.push(file.serverName);
		getTopPage().updateArray[currentPageId] = "FILE";  //업로드 셋
		return true;
	});
	
	myVault.attachEvent("onUploadFail", function(file, extra){
		alert(extra.info);
	});
}

function loadUploadedFile()
{
	 showFileAttach2(maDocLibDetailForm.elements['maDocLibDetailDTO.docId'].value, "DOCUMENT")
	.done(function(ajaxXmlDoc){
		var aImageNo  = parseXmlDoc(ajaxXmlDoc, 'ARR0');
	    var aFileName = parseXmlDoc(ajaxXmlDoc, 'ARR1');
	    var aFileType = parseXmlDoc(ajaxXmlDoc, 'ARR2');
	    var imageType = parseXmlDoc(ajaxXmlDoc, 'ARR4');
	    var aFileSize = parseXmlDoc(ajaxXmlDoc, 'ARR6');
	    
	    var dataLength = aImageNo.length;
		//JSON 형식으로 세팅
	    var jsonList = new Array();
	    
	    for (var i=0; i<dataLength; i++)
	    {
	    	var jsonObj = new Object();
	    	jsonObj.name = aFileName[i];
	    	jsonObj.serverName = aImageNo[i];
	    	jsonObj.size = aFileSize[i];
	    	
	    	jsonList.push(jsonObj);
	        
	    	if(aImageNo[i] == "")myVault.disable();
	    }
	    
	    var fileList = JSON.stringify(jsonList); //JSON String으로 변환
	    
	    isLoad = true;
		myVault.load(fileList);
		isLoad = false;
        
		if(getTopPage().disableAll || $('.b_save').size() ==0) $('.dhx_vault_button, .dhx_vault_file_delete').hide();
		
		if(typeof resizeTabFrame =="function") resizeTabFrame();
	}); 
}

function setSecurity()
{
	var userSecGrade  = '<%=userSecGrade%>';
	var docSecGrade	  = maDocLibDetailForm.elements['maDocLibDetailDTO.securGrade'].value;
	var securGradeObj = $("input[name='maDocLibDetailDTO.securGradeDesc']");
	if(userSecGrade == "null") 
	{
		if(docSecGrade == 3)
			setEnable(securGradeObj, true);
		else
			//권한이 없으면 문서 권한 수정못하게!
			setEnable(securGradeObj, false);
		
	}
	else if(docSecGrade == "") //유저권한은 있는데 문서 권한 설정이 안되면...수정가능
	{
		setEnable(securGradeObj, true);
	}
	else
	{
		//유저 권한도 있고 문서 권한도 설정되어 있다!
		//유저의 권한이 문서의 권한보다 낮으면 수정못하게!!
		if(Number(userSecGrade) > Number(docSecGrade))
		{
			setEnable(securGradeObj, false);
		}
		else
		{
			//유저의 권한이 문서의 권한보다 높거나 같으면 수정 가능하게.
			setEnable(securGradeObj, true);
		}
	}
}

function setEnable(securGradeObj, flag)
{
	securGradeObj.prop("readonly",flag?"":"true").parent().removeClass(flag?'input_read':'input_box').addClass(flag?'input_box':'input_read');
	if(flag)
		securGradeObj.next().show();
	else
		securGradeObj.next().hide();
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQADOC_ID');
	
	maDocLibDetailForm.elements['maDocLibDetailDTO.regDate'].value = getToday();		// loginUser User_Id
	maDocLibDetailForm.elements['maDocLibDetailDTO.regId'].value = loginUser.empId;	// loginUser User_Id
	maDocLibDetailForm.elements['maDocLibDetailDTO.regIdDesc'].value = loginUser.empName;	// loginUser User_Id deptId
	maDocLibDetailForm.elements['maDocLibDetailDTO.deptId'].value = loginUser.deptId;
	maDocLibDetailForm.elements['maDocLibDetailDTO.deptIdDesc'].value = loginUser.deptDesc;

	//기본 세팅(예-작업결과명을 제목에 세팅하고, 보안등급과 문서종류는 일반문서로 세팅)
	//maDocLibDetailForm.elements['maDocLibDetailDTO.docCategDesc'].value = "DOC";  //일반문서 세팅?
	maDocLibDetailForm.elements['maDocLibDetailDTO.securGrade'].value = maDocLibDetailForm.elements['maDocLibCommonDTO.securGrade'].value;
	if(maDocLibDetailForm.elements['maDocLibDetailDTO.securGrade'].value == "") maDocLibDetailForm.elements['maDocLibDetailDTO.securGrade'].value = "3";
	if(maDocLibDetailForm.elements['maDocLibDetailDTO.objectType'].value == "") maDocLibDetailForm.elements['maDocLibDetailDTO.objectType'].value = maDocLibDetailForm.elements['maDocLibCommonDTO.objectType'].value;

	maDocLibDetailForm.elements['maDocLibDetailDTO.description'].value = maDocLibDetailForm.elements['maDocLibCommonDTO.description'].value;
	
	//valUsrDir('maDocLibDetailDTO.docCateg', 'maDocLibDetailDTO.docCategDesc', 'DOC_CATEG', true);
	valSysDirCode('maDocLibDetailDTO.securGrade', 'maDocLibDetailDTO.securGradeDesc', 'SECUR_GRADE', true);
	valSysDirCode('maDocLibDetailDTO.objectType', 'maDocLibDetailDTO.objectTypeDesc', 'OBJECT_TYPE', true);
}

function goUpdate()
{
	
}

function goTabPage(pageId)
{
	goCommonTabPage(maDocLibDetailForm, '' , pageId);
}

function setSequenceVal(sequenceVal)
{
	maDocLibDetailForm.elements['maDocLibDetailDTO.docId'].value = sequenceVal;
	maDocLibDetailForm.elements['maDocLibCommonDTO.docId'].value = sequenceVal;
	
	maDocLibDetailForm.elements['maDocLibDetailDTO.docNo'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{	
	if(ckCreate(currentPageId)) maDocLibDetailForm.strutsAction.value = '<%=MaDocLibDetailAction.DOCLIB_DETAIL_INPUT%>';
	else maDocLibDetailForm.strutsAction.value = '<%=MaDocLibDetailAction.DOCLIB_DETAIL_UPDATE%>';

	var actionUrl = contextPath + "/maDocLibDetail.do";
	
	myVault.upload();
	$("form[name='maDocLibDetailForm']").ajaxForm({ //jquery Form plugin
		beforeSubmit:function(data,frm, opt){ 
			
			var newArray = new Array();

			for(i = deleteRows.length-1; i >= 0 ; i--)
			{
				var newData = new Object();
				newData.name ='deleteRows';
				newData.value = deleteRows[i];

				data.push(newData)
			}
			
			if(isIE)
			{
				setTime = setInterval("prgressBar();", 200);
			}

		},
		uploadProgress: function(event, position, total, percentComplete) {
			prgressBar(percentComplete); 
	    },
		success:function(responseText, statusText){
			afterSave(responseText);
			
			if(isIE)
			{
				clearInterval(setTime);
				prgressBar(100);
			}
			
		},
		error :function(){
			//var files = e.originalEvent.dataTransfer.files;
			//alert(files);
		}
	}).submit();

}

/*
function goViewdwg()
{	
	width  = 800;
	height = 1000;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	var url = contextPath + "/dwg/html/HYViewX.htm";
	window.open(url, "VIEWDWG", features+"scrollbars=yes,status=no,toolbar=no,resizable=yes,location=no,menu=no,width="+width+",height="+height+"");
}
*/

var perVal = 0;
function prgressBar(percentComplete)
{	
	var bar = $('.bar');
	var percent = $('.percent');
	
	if(typeof percentComplete =="undefined")
	{
		perVal = perVal + 1;
		
		if(perVal >= 99) perVal = 0;
	}
	else
	{
		perVal = percentComplete;
	}
	var percentVal = perVal + '%';

    bar.width(percentVal)
    percent.html(percentVal);
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    //if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    maDocLibDetailForm.elements['maDocLibDetailDTO.docId'].value = maDocLibDetailForm.elements['maDocLibCommonDTO.docId'].value;
    if (parent.findGridRow)	parent.findGridRow(maDocLibDetailForm.elements['maDocLibDetailDTO.docId'].value);
	
    getTopPage().afterSaveAll(currentPageId);

    deleteRows = new Array();
    
    //문서 권한 설정 권한 재설정
    setSecurity();

    getTopPage().afterSaveAll(currentPageId);

    
    if(typeof resizeTabFrame =="function") resizeTabFrame();
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maDocLibDetailForm.elements['maDocLibDetailDTO.docId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<%-- <c:import charEncoding="UTF-8" url="/common/jsp/topButtonInclude.jsp"></c:import> --%>
	<html:form action="/maDocLibDetail">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maDocLibCommonDTO.docId" />
	<html:hidden property="maDocLibCommonDTO.objectId" />
	<html:hidden property="maDocLibCommonDTO.objectType" />
	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<html:hidden property="maDocLibDetailDTO.docId" />
	<html:hidden property="maDocLibDetailDTO.securGrade" />
	<html:hidden property="maDocLibDetailDTO.docctgId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label><bean:message key="LABEL.docNo"/></label>
             	 	<div class="input_read">
     					<html:text property="maDocLibDetailDTO.docNo" tabindex="10" styleClass="read"/>
             	 	</div>
             	 </div>
             	 <div class="field">
				   <label><bean:message key="LABEL.docCateg"/></label>
				   <div class="input_box">
						<html:text property="maDocLibDetailDTO.docctgDesc" tabindex="15"/>
						<p class="open_spop"><a><span>조회</span></a></p>
				   </div>
			   </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.docType"/></label>
					<div class="input_box">
						<html:text property="maDocLibDetailDTO.docCategDesc" tabindex="20"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						<html:hidden property="maDocLibDetailDTO.docCateg" />
					</div>
				</div>
				<div class="field">
             	 	<label><bean:message key="LABEL.objectType"/></label>
					<div class="input_read">
						<html:text property="maDocLibDetailDTO.objectTypeDesc" tabindex="25" readonly="true"/>
						<html:hidden property="maDocLibDetailDTO.objectType" />
					</div>
				</div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.docDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maDocLibDetailDTO.description" tabindex="30"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.securGrade"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maDocLibDetailDTO.securGradeDesc" tabindex="40"/>
							<p class="open_spop"><a><span>조회</span></a></p>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.docDeptId"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maDocLibDetailDTO.deptIdDesc" tabindex="50"  styleClass="read"/>
             	 		<html:hidden property="maDocLibDetailDTO.deptId" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.regId"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maDocLibDetailDTO.regIdDesc" tabindex="60"  styleClass="read"/>
             	 		<html:hidden property="maDocLibDetailDTO.regId" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.regDate"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maDocLibDetailDTO.regDate" tabindex="70"  styleClass="read"/>
             	 	</div>
             	 </div>

				  <div class="field_long except">
					<label><bean:message key="LABEL.file"/></label>
					<div class="input_box">
						<div id="maxsize_info">&nbsp;</div>
						<div id="vaultObj" style="width:100%; height:250px;"></div>
					</div>
				 </div>
				<c:set var="filePath" value="enhance/${compName}/part/list/maPtDocLibDetail_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/list/maPtDocLibDetail_${compNo}.jsp"></c:import>
				</c:if>
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
      
         
		<!-- <div id="myForm"></div> -->
					
	</html:form>
</body>
</html:html>
