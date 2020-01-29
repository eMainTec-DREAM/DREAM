/*===========================================================================
AJAX Common Script
author  javaworker
version $Id: ajaxCommon.js,v 1.1 2013/08/30 09:10:43 javaworker Exp $
since   1.0
===========================================================================*/

//================================================================================//
// ************************ Validation Global 변수 선언 시작 ************************ //
// !!! 주석 필수 !!!
//================================================================================//

// 다른곳과 충돌 방지를 위해서  모든 global 변수 앞에는 ajax를 붙인다.

var ajaxGridObj = false;
var ajaxGridRow;

var ajaxKeyId;
var ajaxKeyDesc;
var ajaxIsVal = false;	// validation 여부(없는 코드인경우 alert 여부)
var ajaxExpKeyId;	//확장 조건

var ajaxCodeType;	// validation 후에 호출 펑션 인자로 넣는다.

// validation 중
var ajaxValidating = false;

//============================================
// User Validation에서 여러 유저 검색시 
var ajaxIdArray;	// 유저 ID  array
var ajaxUserArray;	// 유저 이름  array
var ajaxEtcArray;	// 부서/직급 array
//============================================

//================================================================================//
// ************************ Validation Global 변수 선언 완료 ************************ //
//================================================================================//

/**
 * AJAX post 전송
 * actionUrl       : 전송 URL
 * submitParameter : 전송 parameter
 * resultFunction  : 서버응답후 호출되는 function
 */
function XMLHttpPost(actionUrl, submitParameter, resultFunction)
{
	XMLHttpPostRequest(actionUrl, submitParameter, resultFunction, true);
}

/**
 * validation 검증시 사용
 * actionUrl       : 전송 URL
 * submitParameter : 전송 parameter
 * resultFunction  : 서버응답후 호출되는 function
 */
function XMLHttpPostVal(actionUrl, submitParameter, resultFunction)
{
	/*
	 * validation 검증시 global script 변수 를 사용하기 때문에
	 * validation은 한번에 한번씩만 가능하게 한다.
	 */
	ajaxValidating = true;
	XMLHttpPostRequest(actionUrl, submitParameter, resultFunction, false);
}

/**
 * AJAX post 전송
 * actionUrl        : 전송 URL
 * submitParameter  : 전송 parameter
 * callBackFunction : 서버응답후 호출되는 function
 * isAjaxVal : true일 경우 비동기방식으로 전송됨.
 *             false일 경우 동기방식으로 전송됨.
 */
function XMLHttpPostRequest(actionUrl, submitParameter, callBackFunction, isAjaxVal)
{
    var xmlHttpRequest = false;
    
    // IE인경우
    if (window.ActiveXObject)
    {
        xmlHttpRequest = new ActiveXObject('Microsoft.XMLHTTP');
    }
    else
    {
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.overrideMimeType('text/xml');
    }

	actionUrl = actionUrl + "?isAjaxRequest=true";

    //xmlHttpRequest.open('POST', actionUrl, true);
	xmlHttpRequest.open('POST', actionUrl, isAjaxVal);   // firefox 에서 false 로 할때 문제가 있음 확인필요.
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    // 동기 방식인 경우 ie, opera 에서는  onreadystatechange() 가 호출되지만 firefox에서는 호출되지 않는다.
    if (isAjaxVal)
    {
	    xmlHttpRequest.onreadystatechange = function()
	    {
	        if (xmlHttpRequest.readyState == 4)
	        {
	            switch (xmlHttpRequest.status)
	            {
	         		case 404 :
	         			//오류 : URL 이 존재하지않음
	                	alert(COMMON_CMSG036+': ' + actionUrl + COMMON_CMSG037);
	                    break;
	               	case 500 :
	               		//오류 :
	               		eval(callBackFunction + '(xmlHttpRequest.responseXML);');		      
//	                   	alert(COMMON_CMSG036+': ' + xmlHttpRequest.responseText);
	                   	break;
	                case 200 :
	               	default :
	               	    eval(callBackFunction + '(xmlHttpRequest.responseXML);');		        
	                  	break;
	            }
	            
	            // 초기화 전에 codeType을 저장한다.
	            var tempCodeType = ajaxCodeType;
	            
	           	//===============================
	          	// validation 완료후 모든 변수 초기화
	          	defaultAjaxVal();
		        //===============================
		        
//		        try
//		        {
//		        	// after + 첫번째 대문자callBackFunction(codeType) 을 호출한다.
//		        	var tempAfterFunction = "after" + 
//		        						    callBackFunction.substring(0, 1).toUpperCase() + 
//		        						    callBackFunction.substring(1, callBackFunction.length);
//		        	eval(tempAfterFunction + '(tempCodeType);');
//		        }
//		        catch(ex){}
	        }
	    };
    }
    xmlHttpRequest.send(submitParameter);
    
    // 동기방식인 경우 firefox 가 onreadystatechange() 호출 하지 않으므로 같게 하기 위해서 둘다 순차적으로 실행시킨다. 
    if (!isAjaxVal)
    {
        // call back function 호출
        eval(callBackFunction + '(xmlHttpRequest.responseXML);');
    
        // 초기화 전에 codeType을 저장한다.
        var tempCodeType = ajaxCodeType;
        
        //===============================
        // validation 완료후 모든 변수 초기화
        defaultAjaxVal();
        //===============================
        
//        try
//        {
//            // after + 첫번째 대문자callBackFunction(codeType) 을 호출한다.
//            var tempAfterFunction = "after" + 
//                                    callBackFunction.substring(0, 1).toUpperCase() + 
//                                    callBackFunction.substring(1, callBackFunction.length);
//            eval(tempAfterFunction + '(tempCodeType);');
//        }
//        catch(ex){}
    }
}

/**
 * ajax 공통 처리결과
 * 정상적이지 않다면 오류 처리
 * return false : 검증실패
 *        true  : 검증OK
 */
function checkHttpXml(xmlDoc, valMessage)
{
    // xmlDoc 의 root node
    var data = xmlDoc.getElementsByTagName("DATA").item(0);
    
    // 처리 상태
    var statusVal = data.getAttribute("status");
    // 검증 실패
    if (statusVal == "INVALID")
    {
        //==========================================
        if (!valMessage) valMessage="System Error!"; 
        alert(valMessage);
        //==========================================
        
        // invalid code
        return false;
    }
    else if (statusVal == "SESSION_OUT")
    {
        // 세션이 종료되었습니다. 다시 로긴 하셔야만 합니다.\n로긴페이지로 이동합니다.
        alert(COMMON_CMSG020);
        
        var topPage = getTopPage();
        
        topPage.bottomForm.target = "";
        topPage.bottomForm.action = contextPath + "/"+pageType+".do";
        topPage.bottomForm.submit();
        return true;    
    }
    else if (statusVal == "ERROR")
    {
    	var code = parseXmlDoc(xmlDoc, "DESC");
    	var msg = "<div style='text-align:left;'>*오류확인</div><div style='text-align:left; margin:10px;'>요청하신 내용을 정상 처리하지 못했습니다.<br>재 작업시에도 동일한 문제가 발생할 경우<br>시스템 담당자에게 문의하세요.</div><div style='text-align:center;'>[" + code + "]담당자에게 메일보내기</div>";
    	topDoc.dhtmlx.alert(msg, function(result) {
    		topDoc.confirmResult = false;
    		closeModal();
    		var url = contextPath + "/sendErrorMail.do";
    		var param = "strutsAction=1002&errorLogId="+code;
    		$.post(url, param, function(ajaxXmlDoc){
    			//1:성공, 2:메일서비스 비활성화 상태
    			var descVal = parseXmlDoc(ajaxXmlDoc, 'DESC');
    			if(descVal == "2") {
    				topDoc.dhtmlx.alert("메일서비스를 활성화 하세요", function(result) {
    					
    				});
    			}
    		});
		});
    	
    	return false;
    }
    
    // valid code 
    return true;
}

/**
 * validation 처리 결과 체크
 * xmlDoc을 파싱해서 validation 이 안되었다면
 * valMessage 를 alert로 보여준다.
 * return false : 검증실패
 *        true  : 검증OK
 */
function valCheckHttpXml(xmlDoc, valMessage)
{
	// xmlDoc 의 root node
	var data = xmlDoc.getElementsByTagName("DATA")[0];
	// validation 상태
	var statusVal = data.getAttribute("status");
	
	// 검증 실패
	if (statusVal == "INVALID")
	{
		// ajaxIsVal :  true(validation check!)
		if (!ajaxIsVal)
		{
			validationFail = false;
			return false;
		}
	
		// ID 추출한다.
		var keyIdValue = "";
		if (ajaxGridObj)
		{
			keyIdValue = ajaxGridObj.getColumnValue(ajaxKeyId, ajaxGridRow);
		}
		else
		{
			keyIdValue = M$(ajaxKeyId).value;
		}
		
		//===========================
		// validation 실패 (저장시 체크)
		validationFail = true;
		//===========================
		
		//==========================================
		// ex) 유효하지 않는 코드 입니다. 
		alert("[" + keyIdValue+ "] " + valMessage);
		//==========================================
		
		// 유효하지 않은 id 삭제 및 focus
		if (ajaxGridObj)
		{
			ajaxGridObj.setColumnValue(ajaxKeyId, ajaxGridRow, "");
			valSetDefault(ajaxKeyDesc, ajaxGridRow, ajaxGridObj);
			ajaxGridObj.selectCellByIndex(ajaxGridRow, ajaxGridObj.getColumn(ajaxKeyId).fieldIndex);
		}
		else
		{
			M$(ajaxKeyId).value = "";
			valSetDefault(ajaxKeyDesc);
			M$(ajaxKeyId).focus();
		}
		
		// invalid code
		return false;
	}
	
	//===========================
	// validation 성공 (저장시 체크)
	validationFail = false;
	//===========================
	
	// valid code 
	return true;
}

/**
 * xmlDoc을 파싱한다.
 * tagName의 값들을 배열로 리턴한다.
 */
function parseXmlDoc(xmlDoc, tagName)
{
	// 결과 문자열 배열
	var resultVal = new Array();

	// 해당 tag 이름의 값들을 모두 리턴받는다.
	var tagVal = xmlDoc.getElementsByTagName(tagName);

	for (i=0; i<tagVal.length; i++)
	{
		// xml Node 의 값을 value 를 추출한다.
		resultVal[i] = tagVal[i].childNodes[0].nodeValue;
	}
	
	return resultVal;
}

/**
 * 현재 validation 중이면 true, 아니면 false 리턴
 */
function checkAjaxValidating(pValKeyId, pValGridRow, pValGrid)
{
// validation 은 synchronous 로 변경하였음 필요없음
return false;
	if (ajaxValidating)
	{
		if (pValGrid)
		{
			// 다른 validation 중이라면 현재 것을 없앤다.
			pValGrid.setColumnValue(pValKeyId, pValGridRow, "");
			return true;
		}
		else
		{
			// 다른 validation 중이라면 현재 것을 없앤다.
			M$(pValKeyId).value = "";
			return true;
		}
	}
	else
	{
		return false;
	}
}

/**
 * 모든 validation 끝나고 호출된다.
 */
function defaultAjaxVal()
{
	//================================
	// 모든 변수 초기화 한다.
	ajaxGridObj = false;
	ajaxGridRow = false;
	
	ajaxKeyId = "";
	ajaxKeyDesc = "";
	ajaxCodeType = "";
	ajaxIsVal = false;	// validation 여부(없는 코드인경우 alert 여부)
	//================================
	
   	// validation 체크 완료 표시
    ajaxValidating = false;
}

/**
 * validation 처리 전에 공통 체크
 * pValKeyId    : validation key id (배열 또는 문자열)
 * pValDescId   : validation desc (배열 또는 문자열)
 * pValCodeType : validation Type
 * pValGrid    : sheet object
 * return : true[오류],
 *          false[정상처리]
 */
function valCheckCommon(pValKeyId, pValDescId, pValCodeType, pValGridRow, pValGrid)
{
	//======================================================
	// pValKeyId, pValDescId, pValCodeType 은 필수 항목이다.
	
	// key Id
	if (!pValKeyId || pValKeyId == "") return true;
	
	// code type	
	if (!pValCodeType || pValCodeType == "") return true;

	// desc id
	if (pValDescId)
	{
		// 배열이 아닐 경우 2011.12.26
		if(typeof(pValDescId) == "string")
		{
			// desc id 값이 없다면 리턴한다.
			if (pValDescId == "") return true;
		}
		else
		{
			// 배열 형태이고, 첫번째 배열값이 없다면 오류처리!
			if (pValDescId[0] == "") return true;
		}
	}
	// desc Id가 전송 안된경우
	else return true;
	//======================================================

	// sheet validation
	if (pValGrid)
	{
		// sheet 인 경우 sheet Row가 필수 항목이다.
		if (pValGridRow != 0 && (!pValGridRow || pValGridRow == ""))
		{
			alert("valCheckCommon(" + "SHEET ROW IS INVALID!" + ")");
			return true;
		}
		// key Id 에 값이 없다면 desc 를 '' 셋팅하고 리턴한다.
		if (trim(pValGrid.getColumnValue(pValKeyId, pValGridRow)) == "") 
		{
			valSetDefault(pValDescId, pValGridRow, pValGrid);
			return true;
		}
		
		//====================================================================
		// 다른 validation Check중이라면 
		if (checkAjaxValidating(pValKeyId, pValGridRow, pValGrid)) return;
		//====================================================================
	}
	else
	{
		// readOnly 상태라면  리턴한다.
		//if (M$(pValKeyId).readOnly) return true;
		
		// key Id 에 값이 없다면 desc 를 '' 셋팅하고 리턴한다.
		if (trim(M$(pValKeyId).value) == "")
		{
			// desc id 를 초기화 시킨다.
			valSetDefault(pValDescId);
			return true;
		}
	}
	// validation ok!
	return false;
}

/**
 * pValDescId : 배열 혹은 문자열
 * 위의 값을 초기화 빈문자열로 셋팅한다.
 */
function valSetDefault(pValDescId, pValGridRow, pValGrid)
{
	if (pValGrid)
	{
		// 배열이 아니라면 2011.12.26
		if (typeof(pValDescId) == "string")
		{
			pValGrid.setColumnValue(pValDescId, pValGridRow, "");
		}
		else
		{
			for (i=0; i<pValDescId.length; i++)
			{
				pValGrid.setColumnValue(pValDescId[i], pValGridRow, "");
			}
		}
	}
	else
	{
		// 배열이 아니라면 2011.12.26
		if (typeof(pValDescId) == "string") 
		{
			M$(pValDescId).value = "";
		}
		else
		{
			for (i=0; i<pValDescId.length; i++)
			{
				if(pValDescId[i]!=""&&typeof(pValDescId[i]) == "string"){
					M$(pValDescId[i]).value = "";
				}
			}
		}
	}
}

//============================================================
// For GRID
//============================================================

/**
 * AJAX post 전송
 * actionUrl        : 전송 URL
 * submitParameter  : 전송 parameter
 * callBackFunction : 서버응답후 호출되는 function
 * _isAsync         : 동기방식, 비동기방식 여부
 */
function ajaxForGrid(actionUrl, submitParameter, callBackFunction, _grid, ajaxRetType, _isAsync)
{
    var xmlHttpRequest = false;
    
    // IE인경우
    if (window.ActiveXObject)
    {
        xmlHttpRequest = new ActiveXObject('Microsoft.XMLHTTP');
    }
    else
    {
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.overrideMimeType('text/xml');
    }
	actionUrl = actionUrl + "?isGridSearch=true";

    xmlHttpRequest.open('POST', actionUrl, _isAsync==false?false:true);
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttpRequest.onreadystatechange = function()
    {
		//_grid.showWaiting();
        if (xmlHttpRequest.readyState == 4)
        {
            switch (xmlHttpRequest.status)
            {
         		case 404 :
         			//오류 : URL 이 존재하지않음
                	alert(COMMON_CMSG036+': ' + actionUrl +COMMON_CMSG037);
                    break;
               	case 500 :
               		//오류 :	       
               		if(hostIp !='0.0.0.0')
               		{
               			var url   = contextPath + "/common/jsp/error500.jsp";

	               	    var popWidth = 700;
	               	    var popHeight = 450;
	
	               	    // pop up이 중앙에 위치하게 한다.
	               	    var TopPosition  = (screen.height/2 - popHeight/2);
	               	    var LeftPosition = (screen.width/2 - popWidth/2);
	
	               	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	               	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	               	    pos = pos + ",toolbar=no, scrollbars=no, status=no, resizable=yes";
	               	    
	               	    window.open(url , "ERROR", pos);
               		}
               		else
               		{
               			alert(COMMON_CMSG036+': '+ xmlHttpRequest.responseText);
               		}
                   	//alert(COMMON_CMSG036+': '+ xmlHttpRequest.responseText);
                   	break;
                case 200 :
               	default :
                   	eval(callBackFunction + '(_grid, xmlHttpRequest.' + ajaxRetType + ');');		        
                  	break;
            }
        }
    };
    xmlHttpRequest.send(submitParameter);
}

//============================================================
// Call Mware Util Class
//============================================================

/**
 * class.mthod 호출
 * _className  : 호출되는 class name
 * _methodName : 호출되는 method name
 * _params     : method paramters
 */
function callMwareUtil(_className, _methodName, _paramValue, _paramType)
{
    var submitParameter = "className=" + _className +
                    "&" + "methodName=" + _methodName;
    
    if (_paramValue)
    {
	    for (var _i=0,_j=_paramValue.length; _i<_j; _i++)
	    {
	        submitParameter += "&" + "value=" + _paramValue[_i];
	    }
	}
	
    if (_paramType)
    {
        for (var _i=0,_j=_paramType.length; _i<_j; _i++)
        {
            submitParameter += "&" + "type=" + _paramType[_i];
        }
    }
    
    var actionUrl = contextPath + "/callMwareUtil";

    var xmlHttpRequest = false;
    
    // IE인경우
    if (window.ActiveXObject)
    {
        xmlHttpRequest = new ActiveXObject('Microsoft.XMLHTTP');
    }
    else
    {
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.overrideMimeType('text/xml');
    }

    actionUrl = actionUrl + "?isAjaxRequest=true";

    xmlHttpRequest.open('POST', actionUrl, false);
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    xmlHttpRequest.send(submitParameter);
    
    // 동기방식
    return parseXmlDoc(xmlHttpRequest.responseXML, 'DESC');
}
