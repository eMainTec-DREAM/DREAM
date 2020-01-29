/*==================================================== * 이하 action 이 간 경우 모래시계 나타나게 한다.
 * - javaworker -
 *===================================================*/

//=================================================
// 브라우저가 IE 인지 판단
var agent=window.navigator.userAgent.toLowerCase();
var isIE=false;
var isIE10=false;
if (agent.indexOf("msie 7")>-1 || 
    agent.indexOf("msie 8")>-1 || 
    agent.indexOf("msie 9")>-1)
{isIE = true;}
if (agent.indexOf("msie 10")>-1){
	isIE = true;
	isIE10 = true;
} 
if ((navigator.appName=='Netscape' && 
	 navigator.userAgent.search('Trident')>-1) || 
    (agent.indexOf("msie")>-1))
{isIE = true;}
//=================================================
var updateArray = {}; //Updat and Save
var _gCheckArray = {}; //Keep the info if the field is for check when the field is hided by function. (showField, hideField)
var curPageUpdate= false;
var disableAll = false; //Disable All Pages
var disabled = true; //Disable current Pages
var filterParam = ""; //popup Filter
//var selectedId = {}; //after detail Saved
var isHeaderLoaded = {};
var _gridHeightArray = {};
var excelFileName = "gridExcel";
var isLoading = true; //Loading Page 로딩여부
var attachedEventId;
var topDoc = getTopPage();
var myDataProcessor;
var _excelGridId; //excel 버튼이 위치한 gridId
var _comFilterField = []; //List of filter field name
var _sortColumn = []; //sort Params

//이벤트제어변수.
var _LOAD_STAT = false;
function beginLoading()
{
	_LOAD_STAT = true;
	document.body.style.cursor = "wait";
	// 조회중입니다.
	window.status = typeof COMMON_CMSG001 == "undefined"?"조회중입니다.":COMMON_CMSG001;
}

function endLoading()
{
	_LOAD_STAT = false;
	document.body.style.cursor = "default";
	// 완료
	window.status = COMMON_CMSG002;
}

/**
 * boolean fg : true  [모래시계설정]
 *              false [모래시계해제]
 */
function setLoading(fg)
{
	if(fg) 
	{
		beginLoading();
	} 
	else 
	{
		endLoading();
	}
}

//==========================================================================
//==========================================================================
var timerId1 = "";
/**
 * level 1 메세지를 공지한다.
 * ex)  입력, 수정, 저장등의 메세지
 */
function alertMessage1(message)
{
	if(typeof message == 'undefined') return;
	var topPage = getTopPage();
	var containerName = ".top_body";
	var menuWidth	= 105;

	if(topPage.$(containerName).outerHeight() == null) containerName = window;
	
	if(typeof message == "object") message = String(message);

	message = message.replace(/(\n|\r\n)/g,"<br>");
	
	topPage.dhtmlx.message({
		top: topPage.$(containerName).outerHeight()/2,
		left:topPage.$(containerName).outerWidth()/2,	
		text: message,
		id : "MESSAGE",
		expire: 1500,
		type :"error"
	});

	topPage.$('.dhtmlx_message_area').css({
		"left": topPage.$(containerName).outerWidth()/2 - menuWidth + "px",
		"top" : topPage.$(containerName).outerHeight()/2 +"px",
		"width": "280",
		"z-index" : 10000
	});
}

/**
 * Confirm Message
 */
function confirmMessage(message)
{
	return confirm(message);
}

/**
 * validation 용 message
 * process 진행 중단 경고 message
 */
function alertMessage2(message)
{
	if(typeof message == 'undefined') return;
	var topPage = getTopPage();
	var containerName = ".top_body";
	var menuWidth	= 105;
	
	message = message.replace(/(\n|\r\n)/g,"<br>");

	if(topPage.$(containerName).outerHeight() == null) containerName = window;

	topPage.dhtmlx.message({
		id : "loading",
		top: topPage.$(containerName).outerHeight()/2,
		left:topPage.$(containerName).outerWidth()/2,	
		text: message,
		//expire: 1500,
		expire: -1,
		type :"error"
	});

	topPage.$('.dhtmlx_message_area').css({
		"left": topPage.$(containerName).outerWidth()/2 - menuWidth + "px",
		"top" : topPage.$(containerName).outerHeight()/2 +"px",
		"width": "280",
		"z-index" : 10000
	});
}

function CloseAlertMessage2()
{	
	getTopPage().dhtmlx.message.hide("loading");
}

/**
 * Log 표시용
 */
function alertMessage3(message, _callback)
{
	var topPage = getTopPage();
	var containerName = ".top_body";
	var menuWidth	= 350;
	
	message = message.replace(/(\n|\r\n)/g,"<br>");
	
	if(topPage.$(containerName).outerHeight() == null) containerName = window;

	topPage.dhtmlx.alert({
		id : "loading",
//		top: topPage.$(containerName).outerHeight()/2,
//		left:topPage.$(containerName).outerWidth()/2,	
		text: message,
		expire: -1,
		type :"alert"
		,ok:"확인"
		,"width":"30%"
		,callback:function(){
			if(typeof _callback != "undefined") 
				$.globalEval(_callback+"()");
			else 
			{
				if(typeof afterAlertMessage3 == "function")
					afterAlertMessage3();
			}
		}
	});
	topPage.$('.dhtmlx_message_area').css({
		"left": topPage.$(containerName).outerWidth()/2 - menuWidth + "px",
		"top" : topPage.$(containerName).outerHeight()/2 +"px",
		"width":700,
		"z-index" : 10000
	});
}

var timerCnt1 = 0;
var maxTimerCnt1 = 7;   // 최대 반복 횟수
function showMessage(message, _type)
{
	if(timerCnt1 == 0)
		hideCopyRight();
    //var textLine = M$("textLine");
    var textLine = M$("textLine");
    var bottomMsg = M$("bottomMsg");
    textLine.className = "";
    
    if (_type == 'LEVEL_1') 
    {
        //textLine.style.fontWeight = 'bold';
        textLine.style.fontSize = '12';
        textLine.color = '#ae6113';
        bottomMsg.style.visibility = "visible";
    }
    else 
    {
        textLine.style.fontWeight = 'bold';
        textLine.style.fontSize = '15';
        textLine.color = '#ae6113';
        bottomMsg.style.visibility = "visible";
    }
    if (timerCnt1 >= 99)
    {
        clearTimeout(timerId1);
        setTextContent(textLine, "");
        bottomMsg.style.visibility = "hidden";
        
        return;
    }
    if (timerCnt1 >= maxTimerCnt1)
    {
        clearTimeout(timerId1);
        //setTextContent(textLine, message);
        //bottomMsg.style.visibility = "hidden";
        //showCopyRight();
        return;
    }
    if (trim(getTextContent(textLine)) == "")
    {
        setTextContent(textLine, message);
    }
    else
    {
        setTextContent(textLine, "");
        bottomMsg.style.visibility = "hidden";
    }
    
    timerCnt1++;
    timerId1 = setTimeout("showMessage('" + message + "', '"+_type+"');", 500);  
}

/**
 * Message를 clear 한다.
 */
function clearMessage()
{
    timerCnt1 = 99;
    try{
        setTextContent(M$("textLine"), "");
    }catch(e){
        try{
           setTextContent(parent.M$("textLine"), "");
        }catch(e){}
    }
}
//==========================================================================
//==========================================================================



/**
 * form submit 할때 사용한다.
 * submit 커서를 모래시계로 한다.
 */
function formSubmit(formObj, pageMessage) 
{
	beginLoading();
	
	if (pageMessage)
	{
	/*
	   var pageMsgTag = "<input type='hidden' name='pageMessage'>";
	   var pageMsgObj = document.createElement(pageMsgTag);
	 */ 
	   var pageMsgObj = document.createElement("input");
	   pageMsgObj.type = 'hidden';
	   pageMsgObj.name = 'pageMessage';
	   pageMsgObj.value = pageMessage;
	   
	   formObj.appendChild(pageMsgObj); 
	}

	var isDecoArray	= $("input[name='isDecoratorName']");

	if(isDecoArray.length == "0")
	{
		var decoratorNameObj = document.createElement("input");
	    decoratorNameObj.type = 'hidden';
	    decoratorNameObj.name = 'isDecoratorName';
	    decoratorNameObj.value = DECORATOR_NAME;
		
		formObj.appendChild(decoratorNameObj);
	}
	else
	{
		isDecoArray.val(DECORATOR_NAME);
	}
	
	formObj.submit();
}


/**
 * 컨트롤이 필요한 이벤트 목록은 찾으면 계속 늘려간다.
 */
document.onkeydown   = eventWatch;	// 키보드 누름
document.onmousedown = eventWatch;	// 마우스 클릭

function eventWatch()
{
	if(_LOAD_STAT)
	{
		// 데이타를 아직 읽고 있는중입니다!!\n잠시만 기다려주십시요.
		//alertMessage1(COMMON_CMSG003);
		return false;
	}
	else 
	{
		return true;
	}
}

/**
 * body페이지 에러가 나면 호출된다.
 * 오류 페이지 에서 호출하여 모래시계를 해제하여 준다.
 */
function OnBodyError()
{
	setLoading(false);
}

function FormQueryStringPost(form) 
{
	if (typeof form != "object" || form.tagName != "FORM") 
  	{
  		// FormQueryString함수의 인자는 FORM 태그가 아닙니다.
    	alert(COMMON_CMSG022);
    	return "";
	}
  
	var name = new Array(form.elements.length); 
	var value = new Array(form.elements.length); 
	var j = 0;
	var plain_text="";

	//사용가능한 컨트롤을 배열로 생성한다.
	len = form.elements.length; 
	for (i = 0; i < len; i++) 
	{
        switch (form.elements[i].type) 
	    {
            case "button":
	        case "file":
	        case "reset":
	        case "submit":
                break;
	        case "radio":
            case "checkbox":
		        if (form.elements[i].checked == true) 
		        {
					name[j] = form.elements[i].name; 
					value[j] = form.elements[i].value;
					j++;
			    }
			    break;
  		    case "select-one":
  				name[j] = form.elements[i].name; 
				var ind = form.elements[i].selectedIndex;
				if(ind >= 0) 
				{
				    value[j] = form.elements[i].options[ind].value;
						/*
						if (form.elements[i].options[ind].value != '')
							value[j] = form.elements[i].options[ind].value;
						else
							value[j] = form.elements[i].options[ind].text;
						*/
				} 
				else 
				{
				    value[j] = "";
				}
				j++;
				break;
			case "select-multiple":
  				name[j] = form.elements[i].name; 
				var llen = form.elements[i].length;
				var increased = 0;
				for( k = 0; k < llen; k++) 
				{
					if (form.elements[i].options[k].selected) 
					{
						name[j] = form.elements[i].name; 
						if (form.elements[i].options[k].value != '')
							value[j] = form.elements[i].options[k].value;
						else
							value[j] = form.elements[i].options[k].text;
						j++;
						increased++;
					}
				}
				if(increased > 0) j--;
				else value[j] = "";
				j++;
				break;
			default :
  				name[j] = form.elements[i].name; 
				value[j] = form.elements[i].value;
  				j++;
		}
	}

    //QueryString을 조합한다.
	for (i = 0; i < j; i++) 
	{
		// if (name[i] != '') plain_text += name[i]+ "=" + replaceUrlChar(value[i]) + "&";
		 if (name[i] != '') plain_text += name[i]+ "=" + value[i] + "&";
	}
  
    //마지막에 &를 없애기 위함
    if (plain_text != "") plain_text = plain_text.substr(0, plain_text.length -1); 
	return plain_text;
}

//조회 조건 QueryString을 Form Object를 통해 자동으로 만들어 주는 함수
//예) txtname=이경희&rdoYn=1&sltMoney=원화
function FormQueryString(form) 
{
	if (typeof form != "object" || form.tagName != "FORM") 
  	{
  		// FormQueryString함수의 인자는 FORM 태그가 아닙니다.
    	alert(COMMON_CMSG022);
    	return "";
	}
  
	var name = new Array(form.elements.length); 
	var value = new Array(form.elements.length); 
	var j = 0;
	var plain_text="";

	//사용가능한 컨트롤을 배열로 생성한다.
	len = form.elements.length; 
	for (i = 0; i < len; i++) 
	{
        switch (form.elements[i].type) 
	    {
            case "button":
	        case "file":
	        case "reset":
	        case "submit":
                break;
	        case "radio":
            case "checkbox":
		        if (form.elements[i].checked == true) 
		        {
					name[j] = form.elements[i].name; 
					value[j] = form.elements[i].value;
					j++;
			    }
			    break;
  		    case "select-one":
  				name[j] = form.elements[i].name; 
				var ind = form.elements[i].selectedIndex;
				if(ind >= 0) 
				{
				    value[j] = form.elements[i].options[ind].value;
						/*
						if (form.elements[i].options[ind].value != '')
							value[j] = form.elements[i].options[ind].value;
						else
							value[j] = form.elements[i].options[ind].text;
						*/
				} 
				else 
				{
				    value[j] = "";
				}
				j++;
				break;
			case "select-multiple":
  				name[j] = form.elements[i].name; 
				var llen = form.elements[i].length;
				var increased = 0;
				for( k = 0; k < llen; k++) 
				{
					if (form.elements[i].options[k].selected) 
					{
						name[j] = form.elements[i].name; 
						if (form.elements[i].options[k].value != '')
							value[j] = form.elements[i].options[k].value;
						else
							value[j] = form.elements[i].options[k].text;
						j++;
						increased++;
					}
				}
				if(increased > 0) j--;
				else value[j] = "";
				j++;
				break;
			default :
  				name[j] = form.elements[i].name; 
				value[j] = form.elements[i].value;
  				j++;
		}
	}

    //QueryString을 조합한다.
	for (i = 0; i < j; i++) 
	{
		 if (name[i] != '') plain_text += name[i]+ "=" + replaceUrlChar(value[i]) + "&";
		// if (name[i] != '') plain_text += name[i]+ "=" + value[i] + "&";
	}
  
    //마지막에 &를 없애기 위함
    if (plain_text != "") plain_text = plain_text.substr(0, plain_text.length -1); 
	return plain_text;
}

//===============================================================


//===============================================================
//===============================================================



//=====================================================================================

/**
 * 문자열의 앞뒤 공백을 제거한다.
 * -javaworker-
 */
function trim(vString)
{  
	var tString = vString+"";

	//trim left spaces
	if (tString.charAt(0) == " ")
	{
		tString = trim(tString.substring(1, tString.length));
	}
	
	//trim right spaces
	if (tString.charAt(tString.length-1) == " ")
	{
		tString = trim(tString.substring(0, tString.length-1));
	}
	
	return(tString);
}


//===============================================================
//===============================================================
//===============================================================
//===============================================================

/**
 * 모든 페이지에서 호출된다.
 * Log out
 */
function goLogOut(paramStrutsAction)
{
	//if (checkIsUpdate()) return;
	if(checkIsUpdate()){
		getTopPage().dhtmlx.confirm(COMMON_CMSG010, function(result){
		
			if(result)
			{
				
				submitPost(PAGE_TYPE, "strutsAction="+paramStrutsAction, "");
				
				/*var bottomForm = document.bottomForm;
				
				bottomForm.strutsAction.value = paramStrutsAction;
				bottomForm.target = "";
				bottomForm.action = contextPath + "/index.do";
				bottomForm.submit();*/
			}
			
		});
	}
	else
	{
		submitPost(PAGE_TYPE, "strutsAction="+paramStrutsAction, "");
		/*var bottomForm = document.bottomForm;
		
		bottomForm.strutsAction.value = paramStrutsAction;
		bottomForm.target = "";
		bottomForm.action = contextPath + "/index.do";
		bottomForm.submit();*/
	}
}
//===============================================================

function goGaiaLogOut(paramStrutsAction)
{
	//if (checkIsUpdate()) return;
	if(checkIsUpdate()){
		getTopPage().dhtmlx.confirm(COMMON_CMSG010, function(result){
		
			if(result)
			{
				
				submitPost("gaia", "strutsAction="+paramStrutsAction, "");
				
				/*var bottomForm = document.bottomForm;
				
				bottomForm.strutsAction.value = paramStrutsAction;
				bottomForm.target = "";
				bottomForm.action = contextPath + "/index.do";
				bottomForm.submit();*/
			}
			
		});
	}
	else
	{
		submitPost("gaia", "strutsAction="+paramStrutsAction, "");
		/*var bottomForm = document.bottomForm;
		
		bottomForm.strutsAction.value = paramStrutsAction;
		bottomForm.target = "";
		bottomForm.action = contextPath + "/index.do";
		bottomForm.submit();*/
	}
}

/**
 * select box 최 상단에 '-----'
 * 값을 셋팅한다.
 * selectObj : select box Object
 */
function initSelect(selectObj)
{
	//============================================================
	// '-' 열을 현 select box 의 가장 긴 열의 길이와 같게 한다.
	var maxTextLength = 0;

	for (i=0; i<selectObj.options.length; i++) 
	{
		var temLength = getByteLength(selectObj.options[i].text);
		if (maxTextLength < temLength) 
		{
			maxTextLength = temLength;
		}
	}
	
	if(selectObj.options.length == 0)
	{
		var indexPx = selectObj.style.width.indexOf("px");
		var objWidth = selectObj.style.width;
		if(indexPx > -1)
		{
			objWidth = objWidth.substr(0, indexPx);
		}
		maxTextLength = objWidth / 10;
	}
	
	var newText = "";
	for (i=0; i<maxTextLength; i++)
	{
		newText += "--";
	}
	
	//============================================================
	addOption(selectObj, "", newText, 0);
	selectObj.selectedIndex = 0;
}

/**
 * 문자열의 byte 를 계산하여 리턴한다.
 */
function getByteLength(strText) 
{
    var byteLength = 0;
    for (var inx = 0; inx < strText.length; inx++) 
    {
        var oneChar = escape(strText.charAt(inx));
        if ( oneChar.length == 1 ) 
        {
            byteLength ++;
        } 
        else if (oneChar.indexOf("%u") != -1) 
        {
            byteLength += 2;
        } 
        else if (oneChar.indexOf("%") != -1) 
        {
            byteLength += oneChar.length/3;
        }
    }
    return byteLength;
}



//===============================================================
/**
 * Form Object 를 disable 시킨다. 
 */
function objectDisable(obj) 
{
    try 
    {
        obj.disabled = true;
    } 
    catch (e) {}
}
/**
 * Form Object 를 enable 시킨다. 
 */
function objectEnable(obj) 
{
    try 
    {
        obj.disabled = false;
    } 
    catch (e) {}
}

/**
 * document 객체의 IMG 태그 이름이 popupImg 인
 * 경우 disabled=false 시킨다.
 */
function enableImgObject(docObj) 
{
	setImgObject(docObj, false);
}

/**
 * document 객체의 IMG 태그 이름이 popupImg 인
 * 경우 disabled=true 시킨다.
 */
function disableImgObject(docObj) 
{
	setImgObject(docObj, true);
}

function setImgObject(docObj, objStatus)
{
	var allEl = docObj.getElementsByName("popupImg");
	for(var i=0; i<allEl.length; i++)
	{
		allEl[i].disabled = objStatus;
	}
	
	/*
	for(var i=0; i<docObj.all.length; i++)
	{
		if (docObj.all[i].name == "popupImg")
		{
			docObj.all[i].disabled = objStatus;
		}
	}
	*/
}
//===============================================================

//원화 첵크
function dataintComma(valueObj) {
  
	var formnum = valueObj.value;
    
    num1 = formnum.length;        

        FirstNum = formnum.substr(0,1);
        FirstNum2 = formnum.substr(1,num1);
   
        if(FirstNum == "0"){
        	// "입력숫자는 0 으로 시작할 수 없습니다."
			alert(COMMON_CMSG007);
        return FirstNum2;
        	formnum = FirstNum2;
        }

        loop = /^\$|,/g; 
    formnum = formnum.replace(loop, ""); 

        document.forms[0].dollar.value=formnum;
        
        var fieldnum = '' + formnum;    

          if (isNaN(fieldnum)) {
          	// "숫자만 입력하실 수 있습니다."
        	alert(COMMON_CMSG008);        
    formnum == "";
        valueObj.focus();
        return "";
        }
        else {
        var comma = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
        var data = fieldnum.split('.');
        data[0] += '.';
           do {
             data[0] = data[0].replace(comma, '$1,$2');
            } while (comma.test(data[0]));

           if (data.length > 1) {
           return data.join('');
           }
           else {
           return data[0].split('.')[0];
                }
        }
}
//===============================================================


//===============================================================

/**
 * 파라메터로 전송되어진 폼 object의 모든 elements 를
 * enable 상태로 한다.
 * class가 input_read인 상태는 항상 disable이다. 
 * onlyEnable:true 인경우 disable은 해주지않고 enable 만 해준다.
 */
function enableFormObject(obj, onlyEnable)
{
	if(obj)
	{
		for(var i=0;i<obj.elements.length;i++)
		{
			// grid input object 제외
			if (obj.elements[i].className == "gt-editor-text-popup2") continue;
			// class 가  input_read 경우는 무조건 disable 이다.
			if (obj.elements[i].className.indexOf("input_read") > -1 ||
				obj.elements[i].className.indexOf("input_read_line") > -1 ||
				obj.elements[i].className.indexOf("select_read") > -1 ||
				obj.elements[i].className.indexOf("checkbox_read") > -1
				)
			{
				if (!onlyEnable)
				{
					//obj.elements[i].disabled = true;
					obj.elements[i].readOnly = true;
				}
			}
			else
			{
				obj.elements[i].disabled = false;
				obj.elements[i].readOnly = false;
			}
		}
	}
}

/**
 * 파라메터로 전송되어진 폼 object의 모든 elements 를
 * disable 상태로 한다.
 * 
 * 곧 삭제 할것임 readOnlyFormObject 로 대체한다.
 */
/*
function disableFormObject(obj)
{
	if(obj)
	{
		for(var i=0;i<obj.elements.length;i++)
		{
			if (obj.elements[i].type != 'hidden')
			{
				// class 가 input_read 인 경우는 원래 disable 혹은 readOnly 이므로 따로 설정 필요없음
				if (obj.elements[i].className != "input_read")
				{
					obj.elements[i].disabled = true;
					obj.elements[i].readOnly = true;
				}
			}
		}
	}
}
*/

/**
 * 파라메터로 전송되어진 폼 object의 모든 elements 를
 * readOnly 상태로 한다.
 */
function readOnlyFormObject(obj)
{
	if(obj)
	{
		for(var i=0;i<obj.elements.length;i++)
		{
			if (obj.elements[i].type != 'hidden')
			{
				// class 가 input_read 인 경우는 원래 disable 혹은 readOnly 이므로 따로 설정 필요없음
				if (obj.elements[i].className.indexOf("input_read") <= 0 && 
					obj.elements[i].className.indexOf("input_read_line") <= 0 && 
				    obj.elements[i].className.indexOf("select_read") <= 0 &&
					obj.elements[i].className.indexOf("checkbox_read") <= 0)
				{
					// checkbox, radio, select 는 readOnly 설정이 셋팅이 안되므로 disable 로 한다.
					if (obj[i].type == "checkbox" || obj[i].type == "radio" ||
					    obj[i].type == "select-one" || obj[i].type == "select-multiple")
					{
						obj.elements[i].disabled = true;
					}
					else
					{
						obj.elements[i].readOnly = true;
					}
				}
			}
		}
	}
}

/**
 * 파라메터로 전송되어진 폼 object의 모든 elements 를
 * disable 상태로 한다.
 * exceptObjStr 가 포함된 모든 Object는 clear 시키지 않는다.
 */
function clearAllFormObject(obj, exceptObjStr)
{
	//====================================
	// 화면에 첨부파일이 표시 되어있다면 삭제한다.
	//clearAttachFileView();
	//====================================

	if(obj)
	{
		for(var i=0;i<obj.length;i++)
		{
			// name이 . 로 분리되어있는 경우 앞의 이름이 exceptObjStr 와 같은 경우 "" 처리하지 안흔다.
			if (!checkOjbName(obj[i].name, exceptObjStr))
			{
				if (obj[i].type == "checkbox")
				{
					obj[i].checked = false;
				}
				else
				{
					// isDecoratorName 의 값은 삭제하지 않는다.
					if (obj[i].className != "inputB_tb_c" && 
						obj[i].className != "inputB_tb_c_on" &&
						obj[i].name != "isDecoratorName" &&
						obj[i].type != "button")
					{
						obj[i].value = "";
					}
				}
			}
			else
			{
				//alert(obj.elements[i].name);
			}
		}
	}
	
	// 첨부파일 표시된것을 삭제한다.
//    M$("FILE_ATTACH").innerHTML= "";
}

/**
 * objName 과 extName 을 비교하여
 * objName에 extName 이 objName에 포함되어 있다면 true를 리턴한다.
 */
function checkOjbName(objName, extName)
{
	if (objName == null)
	{
		return true;
	}
	
	// extName 이 null 이거나 '' 인경우 모든 object 를 삭제 해야하므로 false를 리턴한다.
	if (extName == null)
	{
		return false;
	}

	var pos = objName.indexOf(".");
	
	if (pos == -1)
	{
		return false;
	}
		
	var token  = objName.substring(0, pos);

	if (token == extName)
	{
		return true;
	}

	return false;	
}

//===============================================================

/**
 * 넘겨진 문자열이 숫자로만 되어있는지 체크한다.
 * false : 숫자외 문자 포함
 * true  : 숫자로만 되어 있음
 */
function checkDigitOnly( digitChar ) 
{
    if ( digitChar == null ) return false ;

    for(var i=0;i<digitChar.length;i++)
    {
       var c=digitChar.charCodeAt(i);
       if( !(  0x30 <= c && c <= 0x39 ) ) 
       {
        return false ;
       }
	}
    return true ;
}

//===============================================================
//===============================================================
//===============================================================
//===============================================================

/**
 * 이전, 다음 페이지 버튼을 제어한다.
 */
function setPrevNextButton(prevButton, nextButton)
{
	// button 이전 다음을 셋팅한다.
	if (prevButton.value == "")
	{
		document.all.prev.disabled = true;
	}
	else
	{
		document.all.prev.disabled = false;
	}
	
	if (nextButton.value == "")
	{
		document.all.next.disabled = true;
	}
	else
	{
		document.all.next.disabled = false;
	}
}

//===============================================================

/**
 * 숫자만 입력 가능하게 한다.(소수 가능)
 * keyDown 이벤트에 입력한다.
 * true : 가능키, false : 입력불가
 */
function onlyNumberInput(event, _minus)
{	
    if(!event) event = window.event;
	var code = getKeyCode(event);

	// ctrl key 인식 ctrl+v, ctrl+c 가능
	if (event.ctrlKey == true) return false;

	// -:189(음수  ie:189, ff:109)
    if (_minus && code == (isIE||isIE10?189:109)) return true;
    
	// del, back, 방향키 전, 후 의 키는 숫자키와 같이 인식 되게 한다.
	// back:8, space:32, del:46, 방향전:37, 방향후:39, Tab:9, .:190(소수점추가), .:110(오른쪽 key pad)
	if (code != 46 && code != 8 && 
		code !=37 && code != 39 && code != 9 && code != 190 && code != 110)
    {
		// 메인키보드 숫자 48 ~ 57
		if (code < 45 || code > 57 || code == 190)
		{
//			console.log(code);
//			console.log((!isIE&&!isIE10));
	        // 숫자키 패드 인식 가능하게 추가 96 ~ 105 까지(ie 의 코드, ff에서는 메인키와 숫자키 같은 코드값이다.)
	        if ( code < 96 || code > 105 || code == 190)
	        {
			    if (event.preventDefault) event.preventDefault();    // Fierfox
			    else event.returnValue = false;  // IE
			    return false;
			} 
		}
	}
	return true;
}

/**
 * 정수만 입력 가능하게 한다.
 * keyDown 이벤트에 입력한다.
 * _minus : true(음수입력가능)
 *          false(양수입력불가)
 * return : true(가능키), false(입력불가)
 */
function onlyIntegerInput(event, _minus)
{
    if(!event) event = window.event;
    var code = getKeyCode(event);
    // ctrl key 인식 ctrl+v, ctrl+c 가능
    if (event.ctrlKey == true) return false;

    // -:189(음수  ie:189, ff:109)
    if (_minus && code != (isIE||isIE10?189:109)) return true;
    
    // del, back, 방향키 전, 후 의 키는 숫자키와 같이 인식 되게 한다.
    // back:8, space:32, del:46, 방향전:37, 방향후:39, Tab:9
    if (code != 8 && 
        code != 37 && code != 39 && code != 9)
    {
        // 메인키보드 숫자 48 ~ 57
        if (code <= 46 || code > 57 || code == 190)
        {
            // 숫자키 패드 인식 가능하게 추가 96 ~ 105 까지(ie 의 코드, ff에서는 메인키와 숫자키 같은 코드값이다.)
            if ((!isIE&&!isIE10) || code < 96 || code > 105 || code == 190)
            {
                if (event.preventDefault) event.preventDefault();    // Fierfox
                else event.returnValue = false;  // IE
                return false;
            } 
        }
    }
    return true;
}
/** 
 * 숫자, 소수점(단, 한번) 만 입력 가능하게 한다.
 * keyDown 이벤트에 입력한다.
 */
function onlyFloatInput(event, obj)
{
    if(!event) event = window.event;
	var code = getKeyCode(event);
	
	// 소수점 filtering
	var originData = obj.value;
	var mainData = originData.replace(/\./gi, "");
	
	// "." 인경우 : 190(특수문자-comma), 110(숫자키패드-소수점)  
	if(code == 190 || code == 110)
	{
		// .만 누를경우, .이 두개이상일 경우, .으로 시작할 경우 
		if( originData.length == 0 || originData.length - 1 >= mainData.length || 
				originData.indexOf(".") == 0 )
		{
			window.event.returnValue = false;
			return false;
		}
	}
	// dash filtering
	mainData = originData.replace(/\-/gi, "");
	// "-" 인경우 : 189(특수문자-dash), 109(숫자키패드-빼기) 
	if(code == 189 || code == 109)
	{
		// -이 두개이상일 경우
		if( originData.length - 1 >= mainData.length )
		{
			window.event.returnValue = false;
			return false;
		}
	}
	
	// del, back, 방향키 전, 후 의 키는 숫자키와 같이 인식 되게 한다.
	// back:8, space:32, del:46, 방향전:37, 방향후:39, Tab:9, .:190/110
	if (code != 46 && code != 8 && 
		code != 37 && code != 39 && code != 9 && 
		code != 190 && code != 110 && 
		code != 189 && code != 109) 
	{
		// 숫자키 패드 인식 가능하게 추가 96 ~ 105 까지
		if (code < 96 || code > 105)
		{
			// 메인키보드 숫자 48 ~ 57
			if (code < 48 || code > 57)
			{
			    window.event.returnValue = false;
			    return false;
			} 
		}
	}
    return true;
}

/**
 * 입력된 값이 (소수점, Dash-마이너스값 ) 유효한지 체크한다. 
 */
function validateFloat(numObj, integerCnt, decimalCnt)
{
	if(numObj.value == "") return true;
	
	var rexStr = eval("/^[-]{0,1}[0-9]{1,"+integerCnt+"}([.][0-9]{1,"+decimalCnt+"})?$/");
	
	var sampleStr = "";
	
	var i = 0 ;
	for(i = 1 ; i <= integerCnt ; i++)
	{
		sampleStr += (i+"");
	}
	if(decimalCnt > 0)
	{
		sampleStr += ".";
		for(var k = i ; k < decimalCnt+i ; k++)
		{
			if(k < 10)
			{
				sampleStr += (k+"");
			}else{
				sampleStr += (parseInt(k/10)+"");
			}
		}
	}

	if(!rexStr.test(numObj.value.replace(/\,/g,"")))
	{
		alert("값이 유효하지 않습니다.\n입력 예]"+sampleStr);
		//numObj.value = "0";
		numObj.focus();
		return false;
	}
	return true;
}
//===============================================================

/**
 * Radio에서 선택된 것 값을 리턴한다.
 */
function getRadioValue(field)
{
	if(field == null)
	{
		return "";
	}
	
	//radio가 하나일때
	if(field[0] == null)
	{
		if(field.checked == true)
		{
			return field.value;
		}
		return "";
	}
	//radio가 하나이상일때
	else
	{
		for(var i=0;i<field.length;i++)
		{
			if(field[i].checked == true)
			{
				return field[i].value;
			}
		}
		return "";
	}
}

/**
 * radio 버튼을 체크 되게 한다.
 */
function radioChecked(field , hasValue )
{
	if(field == null ) return;

	if(field[0] == null && field.value == hasValue ) 
	{
		field.checked = true;
		return;
	}

	for(var i = 0; i < field.length ; i++)
	{
		if( field[i].value == hasValue )
		{
			field[i].checked = true;
			return;
		}
	}
}

//===============================================================

//===============================================================
/*
 * 필터에서 검색 조건으로 입력된 입력값이 like검색으로 데이터를 검색할 경우 
 * 입력된 검색 조건 속에 [%]를 [abc12345;]의 키 값으로 변경하여 데이터를 
 * 전송할 수 있도록 [%]를 변경하는 역할을 하는 메서드 이다.

//===============================================================
function convertPersent(temp)
{
 	var colon = temp.replace(/'/gi, "''");
 	var result = colon.replace(/%/gi, "abc12345;");
    return result;

}
 */
//===============================================================
/*
 *	필터로 검색할때 %를 abc12345; 로 바꾸어 준 값을 다시 원복한다.
 
//===============================================================
function replacePersent(temp)
{
	var colon = temp.replace(/''/gi, "'");
 	var result = colon.replace(/abc12345;/gi, "%");
    return result;
}
*/

//===========================================================================
//===========================================================================

/**
 * 첨부파일 이미지를 표시한다.
 */
function clipAttachFile(isFileAttach)
{
	if ("FILEATTACH" == isFileAttach)
	{
		// 첨부파일 이미지를 표시한다.
		var testObj = M$("fileAttachImage");
		var sHTML1  = "<a href='javascript:goViewAttachFile()'>"+
					        "<IMG name='findPreserveDate' src='" + contextPath + "/common/images/b_fileattach.gif' disabled='true' class='input_noberder' " +
							"align='absmiddle' width='20' height='18' border='0' />" + "</a>";
				 
		testObj.innerHTML = sHTML1;
	}
}

function clearAttachFileView()
{
	var testObj = M$("fileAttachImage");
	testObj.innerHTML = "";
}
//===========================================================================
//===========================================================================
/**
 * commonInclude.jsp파일에 정의되어 있는
 * hostIp를 사용한다. request에서 얻어오는 ip를 적용한다. 
 */

function callUrl()
{
	var AIReportURL = "http://" + location.hostname + ":" + location.port;
	return AIReportURL;
}

/**
 * 작업의뢰서 접수, 작업계획, 작업완료에서 사용된는 
 * AIReport를 공통으로 처리(발신,수신)
 */
function openReport(url,param)
{
 	width  = 400;
	height = 200;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	var url   = contextPath + "/report.do?url="+url+"&param="+param;
	window.open(url, "report", features+"scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no,width="+width+",height="+height+"");
}

function openUbiReport(reportName, param )
{
	var width  = 400;
	var height = 200;
	var sleft = (screen.width - width) / 2;
	var stop = (screen.height - height) / 2;
	var features = "left=" + sleft + ",top=" + stop;
	
	var paramArr = param.split("&").join("#");
	paramArr = paramArr.split("=").join("#");
	
	var param = "?" + "strutsAction=0" + 	        
    "&" + "reportName="+reportName +
    "&" + "param1=" + paramArr;
	
	var url   = contextPath + "/ubiReport.do"+param;
	window.open(url, "report", features+"scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no,width="+width+",height="+height+"");
}

/**
 * Open Quick Page 
 */
function openQuickPage(_quickParam, _pageId)
{
	if(_pageId.indexOf("?") > 0)
	{
		var pageParamArr = _pageId.split("?");
		_pageId = pageParamArr[0];
		_quickParam = _quickParam + pageParamArr[1];
	}
	
	var url = '';
	var popWidth = 1010;
	var popHeight = 640;
	
	if(_pageId=='eqLocMng')
	{
		 url = contextPath + "/" + _pageId + ".do";
		_quickParam = _quickParam + "&isDecoratorName=" + "minpopupPage";
	}
	else if(_pageId == "maMyInfo")
	{
		url = contextPath + "/" + _pageId + ".do";
		_quickParam = _quickParam + "&isDecoratorName=" + "tabPage";
	}
	else
	{
		url = contextPath + "/" + _pageId + ".do";

		if(_quickParam.indexOf("isDecoratorName") >= 0)
		{
			popWidth = 1224;
		}
		else _quickParam = _quickParam + "&isDecoratorName=" + "popupPage";
	}
	
	if (_pageId == "woPlanList" || _pageId == "woPlanDetail" || _pageId == "woResultList" || _pageId == "woResultDetail")
	{
		popHeight = 710;
	}
	else if(_pageId=='eqLocMng')
	{
		var popWidth = 1007;
		var popHeight = 790;
	}
	else if(_pageId == "ptPurDetail")
	{
		popHeight = 870;
	}
	// pop up이 중앙에 위치하게 한다.
	var TopPosition  = (screen.height/2 - popHeight/2);
	var LeftPosition = (screen.width/2 - popWidth/2);
	
	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
		      ",top=" + TopPosition + "px,left=" + LeftPosition + "px" +
		      ",toolbar=no,scrollbars=yes,resizable=yes";
	
	openWindowWithPost(url, _pageId, _quickParam, pos);
}

/**
 * 
 * @param _quickParam
 * @param _pageId
 */
function openQuickTabPage(_quickParam, _pageId)
{
	if(_pageId.indexOf("?") > 0)
	{
		var pageParamArr = _pageId.split("?");
		_pageId = pageParamArr[0];
		_quickParam = _quickParam + pageParamArr[1];
	}
	
	var url = contextPath + "/" + _pageId + ".do";
	//_quickParam = _quickParam + "&isDecoratorName=" + "popupPage";
	
	
	var popWidth = 1010;
	var popHeight = 640;

	// pop up이 중앙에 위치하게 한다.
	var TopPosition  = (screen.height/2 - popHeight/2);
	var LeftPosition = (screen.width/2 - popWidth/2);
	
	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
		      ",top=" + TopPosition + "px,left=" + LeftPosition + "px" +
		      ",toolbar=no,scrollbars=yes,resizable=yes";
	
	openWindowWithPost(url, _pageId, _quickParam, pos);
}

/**
 * Windows를 post방식으로 Open
 * @param url
 * @param name
 * @param params
 */
function openWindowWithPost(url, name, params, pos)
{	
	var newWindow = window.open('', name, pos); 
	if (!newWindow) return false;
	var html = "";
	html += "<html><head><meta language='java' contentType='text/html; charset=UTF-8' pageEncoding='utf-8'></head><body><form accept-charset='UTF-8' id='formid' method='post' action='" + url + "'>";
	if(typeof params == 'string'){
		params = params.split('&');
		for (var i=0; i < params.length; i++)
		{
			var tokenIndex = params[i].indexOf("=");
			html += "<input type='hidden' id='" + params[i].substring(0, tokenIndex) + "' name='" + params[i].substring(0, tokenIndex) + "' />";
		}
		html += "</form></body></html>";
		newWindow.document.write(html);
		for (var i=0; i < params.length; i++)
		{
			var tokenIndex = params[i].indexOf("=");
			if (params[i].substring(0, tokenIndex) == "") continue;
			
			newWindow.document.getElementById(params[i].substring(0, tokenIndex)).value = params[i].substring(tokenIndex+1);
		}
	}
	else if(typeof params == 'object'){
		for(var key in params){
			html += "<input type='hidden' id='" + key + "' name='" + key + "' />";
		}
		html += "</form></body></html>";
		newWindow.document.write(html);
		for(var key in params){
			newWindow.document.getElementById(key).value = params[key];
		}
	}
	newWindow.document.getElementById("formid").submit();
	newWindow.focus();
	return newWindow;
}

/**
 * Parameter를 post방식으로 submit
 * @param url
 * @param name
 * @param params
 */
function formSubmit2(_form, _params)
{
	if (_params)
	{
		var params = _params.split('&');
		for (var i=0; i < params.length; i++)
		{
			var tokenIndex = params[i].indexOf("=");
			
			var paramName = params[i].substring(0, tokenIndex);
			var paramValue = params[i].substring(tokenIndex+1);
			
			var pageMsgObj = document.createElement("input");
			pageMsgObj.type = 'hidden';
			pageMsgObj.name = paramName;
			pageMsgObj.value = paramValue;
			
			_form.appendChild(pageMsgObj); 
		}
	}
	formSubmit(_form);
}

//===========================================================================
//===========================================================================
//===========================================================================
//===========================================================================
//===========================================================================
//===========================================================================

/**
 * url 특수문자 변경 메소드
 */
function replaceUrlChar(originData)
{
/*
backs pace  %08  
tab  %09  
linefeed  %0A  
creturn  %0D  

%  %25 
space  %20  
!  %21  
"  %22  
#  %23  
$  %24   
&  %26 
+  %2B
*/
	if (typeof(originData) != 'string') return originData;

    //originData = encodeURI(originData);
    originData = encodeURIComponent(originData);
    //originData = escape(originData);

	/*
	 * 한글문자열이 포함된 경우 encoding 처리가 따로 되기 때문에 
	 * 한글이 아닌경우만 처리한다.
	
	//if (!korean_check(originData))
	{
		originData = originData.split('%').join('%25');
		originData = originData.split('#').join('%23');	
		originData = originData.split('+').join('%2B');
	}
	
	originData = originData.split('&').join('%26');
	 */
	return originData;
}

/**
 * 해당 문자열에 한글이 한글자 라도 있으지 없는지 체크
 * 한글이면 true, 아니면 false 를 리턴
 */
function korean_check(str)
{
    var i;
    var ch;
    
    for (i=0;i<str.length;i++)
    {
        ch = escape(str.charAt(i));        //ISO-Latin-1 문자셋으로 변경
        if (strCharByte(ch) == 2)
        {
			return true;
        }
    }
    return false;
}
function strCharByte(chStr)
{
    if (chStr.substring(0, 2) == '%u')
	{
        if (chStr.substring(2,4) == '00')
        	return 1;
        else
    		return 2;        //한글
    }
    else if (chStr.substring(0,1) == '%')
    {
        if (parseInt(chStr.substring(1,3), 16) > 127)
        	return 2;        //한글
        else
            return 1;
    }
    else
    {
    	return 1;
    }
}

//===========================================================================
//===========================================================================

/**
 * 저장버튼을 누른경우이다.
 * validation check 를 한다.
 * 저장시 validation check를 위해서 0.5 초간의 interval 를 준다. 
 * 만약 validation check 가 실패하였다면 validationFail 가 true로 설정되어 저장이 진행되지 않게 한다.
 */
var validationFail = false; 
var savingFlag = false;	// 저장 처리중 2번 클릭 되는것을 막는다.
function callGoSave()
{
	if (savingFlag) return;

	savingFlag = true;

 	// 저장 버튼을 누른경우 0.5초 정도의 interval 를 발생한다.
	setTimeout("goBeforeSave();", 500);
}

function goBeforeSave()
{
	if (validationFail)
	{
		validationFail = false;
		
		// 저장이 취소 되었으므로 flag 도 false 로 한다.
		savingFlag = false;
		
		return;
	}
	
	savingFlag = false;
	
	goSave();
}

//===========================================================================
//===========================================================================

/**
 * 숫자 문자열을 입력 받아 뒤에 3자리 씩 , 를 입력한다.
 */
function setNumberFormat(originData)
{
	if (originData == null)
	{
		return originData;
	}
	
	// 문자열로 캐스팅
    originData = originData+"";
	originData = originData.split(",").join("");

	var commaIndex = originData.indexOf('.');
	var intStrData;
	var floatStrData;
	
	// . 가 없는 경우
	if (commaIndex == -1)
	{
		intStrData   = originData;
		floatStrData = "";
	}
	else
	{
		// 소숫점 이상 값만을 추출한다.
		intStrData = originData.substring(0, commaIndex) + "";
	
		// 소수점 이하 값을 추출한다.
		floatStrData = originData.substring(commaIndex+1) + "";
	}

	var minus = false;
	//음수일 경우
	if(intStrData.substr(0, 1) == '-')
	{
		minus = true;
		intStrData = intStrData.substr(1, intStrData.length);
	}
	
	var resultData = "";
	var dataLength = intStrData.length;

	// 3자리 이하이면 ,를 붙일필요가 없다.
	if (dataLength <= 3)
	{
		return originData;
	}
	
	for (i=0; i<dataLength/3; i++)
	{
		var tempData = intStrData.substring(dataLength-(i+1)*3, dataLength-i*3);
		if (resultData == "")
		{
			resultData = tempData;
		}
		else
		{
			resultData = tempData + "," + resultData;
		}
	}
	
	if (minus) resultData = "-" + resultData;
	
	if (commaIndex == -1)
	{
		return resultData;
	}
	else
	{
		return resultData + "." + floatStrData;
	}
}


/**
 * 숫자 문자열을 입력 받아 뒤에 3자리 씩 , 를 입력한다. (event type)
 */
function setMoneyFormat(oriObj, decimal)
{
	var prefix = "";
	if( oriObj.value.indexOf("-") > -1 )
	{
		prefix = "-";
	}
	var originData = oriObj.value.replace(/\,|\-/g, "");

	if (originData == null)
	{
		return ;
	}
	
	// 문자열로 캐스팅
	originData = originData + "";

	var commaIndex = originData.indexOf('.');
	var intStrData;
	var floatStrData;
	
	// . 가 없는 경우
	if (commaIndex == -1)
	{
		intStrData   = originData;
		floatStrData = "";
	}
	else
	{
		// 소숫점 이상 값만을 추출한다.
		intStrData = originData.substring(0, commaIndex) + "";
	
		// 소수점 이하 값을 추출한다.
		floatStrData = originData.substring(commaIndex+1) + "";
	}

	var resultData = "";
	var dataLength = intStrData.length;

	if (dataLength <= 3)
	{
		oriObj.value = originData;
	}
	
	for (i=0; i<dataLength/3; i++)
	{
		var tempData = intStrData.substring(dataLength-(i+1)*3, dataLength-i*3);
		if (resultData == "")
		{
			resultData = tempData;
		}
		else
		{
			resultData = tempData + "," + resultData;
		}
	}
	
	if (commaIndex == -1)
	{
		oriObj.value = prefix + resultData;
	}
	else
	{
		if (decimal && floatStrData.length > decimal)
		{
			floatStrData = floatStrData.substring(0, decimal);
		}
		
		oriObj.value = prefix + resultData + "." + floatStrData;
	}
}

/**
 *  콤마(,)가 붙은 숫자를 (,)를 빼고 숫자만 리턴시킨다.
 */
function intToData(number)
{
    number = number+"";
    if (number == null || trim(number) == "") 
    {
        return "";
    }
    
    return number.split(",").join("");
}

//===========================================================================
//===========================================================================
//===========================================================================
//===========================================================================

/**
 * 메세지 공통 처리를 위해서 공통 처리한다.
 * ex) if (checkIsUpdate()) return;
 */
function checkIsUpdate(_contents)
{
	//isUpdating = getCurrentDoc().isUpdating;
	//isUpdating = getIframeContent().isUpdating;
	
	var topPage = getTopPage();
	if(typeof _contents == "undefined") _contents = topPage.document;
	isUpdating = topPage.isUpdating;
	
	isUpdating = ckSubUpdate(_contents);

	return isUpdating;
	/*
	try
	{
		// 수정 중인경우
		if (isUpdating)
		{
			// "수정중입니다. 수정중인 데이타가 모두 사라져도 괜찮겠습니까?"
			if (getTopPage().confirmMessage(COMMON_CMSG010))
			{
				// 수정 중 flag를 false로 하여 다른곳에서 다시 체크 되지 않게 한다.
				isUpdating = false;
				return isUpdating;
			}
			else
			{
				return isUpdating;
			}
		}
		// 수정 중이 아닌경우
		else
		{
			return false;
		}
	}
	catch(e)
	{}
	*/
}

/**
 * form 의 모든 input txt 타입 들의 maxLength를 비교한다.
 * ex) if (checkFormMaxByte(formObj)) return;
 */
function checkFormMaxByte(formObj)
{
	if (typeof formObj != "object" || formObj.tagName != "FORM") 
  	{
  		// FormQueryString함수의 인자는 FORM 태그가 아닙니다.
    	alert(COMMON_CMSG042);
    	return "";
	}
  
	for (i = 0; i < formObj.elements.length; i++) 
	{
		// type 이 text 이고 class가 input_read 가 아니라면 입력 필드 이다.
		if (formObj.elements[i].type == 'text' && 
			formObj.elements[i].className.indexOf("input_read") <= 0 && 
			formObj.elements[i].className.indexOf("select_read") <= 0)
		{
			// 유효하지 않다면 true를 리턴한다.
			if (checkMaxByte(formObj.elements[i])) return true;
		}
	}
	
	// 유효하다면 false를 리턴한다.
	return false;
}

/**
 * 최대 글자 크기를 제어 할때 사용한다.
 * 저장 전에 체크한다.
 * ex) if (checkMaxByte(formObj)) return;
 */
function checkMaxByte(formObj)
{
	var formStringBytes = getByteLength(formObj.value);
	if (formStringBytes > formObj.maxLength)
	{
		var maxHanLength = formObj.maxLength/2;
		var maxEnLength  = formObj.maxLength;
		// 최대 한글[x]글자, 영문(숫자)[x]자 까지 입력 가능합니다.
		alert(COMMON_CMSG043+"[" + maxHanLength + "]"+COMMON_CMSG044+"[" + maxEnLength + "]"+COMMON_CMSG045);
		formObj.focus();
		return true;
	}
	return false;
}

/**
 * 입력 인자값이 숫자 값인지 체크한다.
 */
function numberCheck(numValue) 
{
	var valid = "0123456789";
	var temp;
	for (var i=0; i<numValue.length; i++) 
	{
		temp = "" + numValue.substring(i, i+1);
		if (valid.indexOf(temp) == "-1") return false;	// 숫자 이외의 값이 있음
	}

	// 숫자만 있다.
	return true;
}

/**
 * 필터에서 검색 전에 호출한다.
 */
function beforeFindFilter()
{
	//========================================
	// 필터에서 목록 조회시 창이 바로 닫히지 않기 때문에
	// 창을 옮겨서 닫히는 효과를 나타낸다.
	window.resizeTo(0, 0);
	window.moveTo(9000, -9000);
	//========================================
}

/**
 * Filter에서 opener의 filter 함수를 호출하고, filter창을 닫는다.
 */
function callOpenerFilter()
{
	beforeFindFilter();
	
	// opener 의 filter 함수를 호출한다.
	opener.filter('1');

	// filter 창을 닫는다.
	self.close();
}

/**
 * 팝업창의 크기 재설정
 */
function reSizePopupWindow(win)
{
    if(win.navigator.userAgent.indexOf("98") != -1)
    {
      var winBody = win.document.body; 
      
      /* 새창의 사이즈에 더해줄 marginWidth와 marginHeight */
      //var marginWidth = parseInt(winBody.leftMargin)+parseInt(winBody.rightMargin); 
      //var marginHeight = parseInt(winBody.topMargin)+parseInt(winBody.bottomMargin); 

      /* 새창의 사이즈 설정 */    
      var width = winBody.offsetWidth + 6;    
      var height = winBody.scrollHeight + 29;
      
      if(winBody.scrollHeight < winBody.clientHeight)
      {
        height = winBody.clientHeight + 29;
      }
    
      /* 사이즈 재조정 */
      win.resizeTo(width, height);
      win.scrollbars = true; 
    }
}
//===============================================================================================

/* 마이메뉴 레이어 호출 */
function showSelectLayer(tgtEl)    
{
	document.getElementById(tgtEl).style.display = "block";
	
	if ( navigator.userAgent.indexOf("MSIE") !=-1 && document.getElementById('container') ) 
		document.getElementById('container').style.zIndex = "100";
}

function hideSelectLayer(tgtEl)
{
   document.getElementById(tgtEl).style.display = "none";
   
	if (navigator.userAgent.indexOf("MSIE")!=-1&&document.getElementById('container')) 
		document.getElementById('container').style.zIndex = "0";
}
//===============================================================================================

/**
 * Button 제어 disbale, enable 시킴
 */
function disabledButton(buttonId, isDis)
{
    var buttonObj = M$(buttonId);

    if (!buttonObj)
    { 
        // 버튼이 존재 하지 않습니다.
//        alert("["+buttonId+"] "+COMMON_CMSG046);
        return;
    }

    if (isDis) 
    {
        if (isIE) buttonObj.style.filter = "alpha(opacity=60)";
        else buttonObj.style.opacity = 0.6;

		// Top button 인경우 background 색이 파란색인경우 disable시 구분이 안되서 글자색을 좀더 회색 처리
		if ((buttonObj.className).indexOf("btn_top") > -1) buttonObj.style.color = "#808080";
        
        // disabled 시 image를 변경하던  효과를 준다.
        buttonObj.disabled = isDis;
    }
    else
    {
        if (isIE) buttonObj.style.filter = "";
        else buttonObj.style.opacity = 1;

        // disable 시 변경한 색을 검은색으로 다시 원복 한다.
        if ((buttonObj.className).indexOf("btn_top") > -1) buttonObj.style.color = "#010101";
        
        buttonObj.disabled = isDis;
    }
    
    // 입력버튼 클릭시 App Line View 버튼 없앰
    if ("INPUT" == buttonId && isDis)
    {
    	hideAppLineView();	
    }
}

/**
 * HTML 필수 입력 항목 체크
 * reqName : 필수입력 html object
 * conName : 필수입력 항목 이름
 */
function checkRequireValue(reqName, conName)
{
    var requireObj = M$(reqName);
    var rtnVal = false
    if (trim(requireObj.value) == "")
    {
        //은(는) 필수입력항목 입니다.
        alertMessage1(conName + " " + COMMON_CMSG047);
        try
        {
            requireObj.focus();
        }
        catch(ex){}
        rtnVal = true;

    }

    if(rtnVal) closeModal();
    
    return rtnVal;
}

function checkDateValid(reqName)
{
	var requireObj = M$(reqName);
	
    if (trim(requireObj.value) > "2100" || trim(requireObj.value) < "1900")
    {
        //은(는) 필수입력항목 입니다.
        alertMessage1(conName + COMMON_CMSG006);
        try
        {
            requireObj.focus();
        }
        catch(ex){}
        return true;
    }
    
    return false;
}

/**
 * 변수 str이 문자인지 숫자인지 판별
 */
function isNumber(str) 
{ 
	return (/^[0-9]+$/).test(str); 
}

/**
 * 저장시 session 이나 로긴 유저가 로그아웃되었는지 체크한다.<b>
 * 오류처리 되었다면  
 */
function logOutCheck(pErrMsg)
{
    var tempErrMsg = pErrMsg.replace("\n", "");
    tempErrMsg = tempErrMsg.replace("\n", "");

    if (tempErrMsg == "-9999" || tempErrMsg == "-8888")
    {
        setTimeout("goLoginPage('"+tempErrMsg+"');", 500);
        return true;
    }
    else if (tempErrMsg != "")
    {
        // 셋팅된 오류 메세지를 화면에 띄운다.
        alert(tempErrMsg);
        return true;
    }
    
    return false;
}

/**
 * 로그인 페이지로 이동한다.
 */
function goLoginPage(tempErrMsg)
{
    if (tempErrMsg == "-9999")
    {
        // 세션이 종료되었습니다. 다시 로긴 하셔야만 합니다.\n로긴페이지로 이동합니다.
        alert(COMMON_CMSG020);
    }
    
    if (tempErrMsg == "-8888")
    {
        // 강제 종료 되었습니다. 다시 로긴 하셔야만 합니다.\n로긴페이지로 이동합니다.
        alert(COMMON_CMSG021);
    }
    
    var bottomForm = document.bottomForm;
    bottomForm.target = "";
    bottomForm.action = contextPath + "/"+PAGE_TYPE+".do";
    bottomForm.submit();
}

//==============================================================
/**
 * id 또눈 name으로 html object 조회
 */
function M$(_element) 
{
    var element;
    if (typeof _element == 'string')
    {
        element = document.getElementById(_element);
    }
    if (!element)
    {
        element = document.getElementsByName(_element)[0];
    }
    
    return element;
}

/**
 * html object 에서 textContent추출
 * ie 에서는 innerText, firefox 에서는 textContent
 */
function getTextContent(_obj)
{
    if (isIE) return _obj.innerText;
    else return _obj.textContent;
}
/**
 * html object 에서 textContent 셋팅
 * ie 에서는 innerText, firefox 에서는 textContent
 */
function setTextContent(_obj, _text)
{
    if (isIE) _obj.innerText = _text;
    else _obj.textContent = _text;
}


/**
 * 현재 tag의 위치계산
 * firefox 에서 popup 위치 계산시 parameter로 event를 받지 않으면 사용할수 없기때문에
 * 별도로 계산 필요. 
 */
function getAbsolutePos(el) 
{
    var SL = 0, ST = 0;
    var is_div = /^div$/i.test(el.tagName);
    if (is_div && el.scrollLeft)
        SL = el.scrollLeft;
    if (is_div && el.scrollTop)
        ST = el.scrollTop;
    var r = { x: el.offsetLeft - SL, y: el.offsetTop - ST };
    if (el.offsetParent) {
        var tmp = this.getAbsolutePos(el.offsetParent);
        r.x += tmp.x;
        r.y += tmp.y;
    }
    return r;
}

/**
 * event의 keycode
 */
function getKeyCode(e)
{
	var result;
	if(isIE) result = window.event.keyCode;
	else if(e) result = e.which;
	return result;
}

/**
 * select object의 index에 해당하는 option 삭제
 */
function addOption(selObj, _value, _text, optIndex)
{
    // option index 가 없거나, 현재 select 의 options 최대 index 보다 크면 제일 뒤에 붙인다.
    if (optIndex == undefined || (selObj.options.length-1) < optIndex)
    {
	    var objOption = document.createElement('option');
	
	    objOption.value = _value;
	    objOption.text  = _text;
	    
	    if (isIE) selObj.add(objOption);
	    else selObj.appendChild(objOption);
	}
	else
	{
	   var selOptLength = selObj.options.length;
	   // 마지막 option 같은것 하나 추가
	   addOption(selObj, selObj.options[selOptLength-1].value, selObj.options[selOptLength-1].text);
	
	   for (_i=optIndex; _i<selOptLength; _i++)
	   {
	       // replace 될 option 값 미리 저장
	       var tempValue = selObj.options[_i].value;
	       var tempText  = selObj.options[_i].text;
	   
	       selObj.options[_i].value = _value;
	       selObj.options[_i].text  = _text;
	       
	       _value = tempValue;
	       _text  = tempText;
	   }
	}
}

/**
 * select object의 index에 해당하는 option 삭제
 */
function removeOption(selObj, optIndex)
{
    var optionObjs = selObj.getElementsByTagName("option");
    
    if (isIE) selObj.options.remove(optIndex);
    else selObj.removeChild(optionObjs[optIndex]);
}

/**
 * select object의 index에 해당하는 option 삭제
 */
function changeOption(selObj, _index1, _index2)
{
    var optionObjs = selObj.getElementsByTagName("option");
    
    var objOption1 = document.createElement('option');
    objOption1.value = optionObjs[_index1].value;
    objOption1.text  = optionObjs[_index1].text;

    selObj.options[_index1].value = optionObjs[_index2].value;
    selObj.options[_index1].text  = optionObjs[_index2].text;
    
    selObj.options[_index2].value = objOption1.value;
    selObj.options[_index2].text  = objOption1.text;
}

//==============================================================

/**
 * 각페이지에서 구현한 tab page 호출 스크립트를 호출한다.
 */
var tabIndex = -1;	// 초기값
function callGoTabPage(tabPageId, _tabIndex)
{	
	tabIndex = _tabIndex;
    // 파라메터가 앞의 TAB. 을 없앤다.
    var pageId = tabPageId.replace("TAB.", "");
    //if (goTabPage) goTabPage(pageId);
    if(typeof (goTabPage)=="function") goTabPage(pageId);
    else
    {	
    	var bottomForm = document.bottomForm;
    	
        bottomForm.strutsAction.value = '';
        var url   = contextPath + "/" + pageId + ".do";
        bottomForm.action = url;
        formSubmit(bottomForm);
    }
}

/**
 * 현재 페이지가 첫번째 tab 인지 iframe으로 로딩한(두번째 이상) 페이지 인지 체크
 * 첫번째 페이지 이면 현재 document를 return
 * 두번째이상 이면 tabDiv의 frame document를 return
 * QuickMenu.js 에서 주로 사용
 */
function getCurrentDoc()
{
	if (M$("tabDiv") && M$("tabDiv").style.display == "block")
	{
		return document.getElementById('tabFrame').contentWindow;
	}
	else return this;
}


/**
 * 각 페이지에서 호출(goTabPage function 안에서 호출)
 * tab page 이동시 사용한다.
 */
function goCommonTabPage(formObj, strutsAction, pageId, closePageId)
{	
	if(typeof closePageId != "undefined" && pageId != closePageId)
	{ 
		//$('#tabDivTAB\\.'+closePageId).css("display", "none");
		goClose(closePageId, this);
	}
	else if(typeof closePageId != "undefined" && pageId == closePageId)
	{
		var topPage = getTopPage();
		var tabFTitle = topPage.$('#pg_title,.pwin_header').next();
		if(tabFTitle.is('.sheader_box') && tabFTitle.prop("id") == pageId+"_title") tabFTitle.remove()
	}
	
	$('#tabDivNoAuth').css("display", "none");
	//alert(pageId+ "   "+$('#tabFrameTAB\\.'+pageId)+"   "+$('#tabFrameTAB\\.'+pageId).length);
    if($('#tabFrameTAB\\.'+pageId).length == 0)
    { 
    	$('#tabDivNoAuth').css("display", "");
    	formObj.target = "tabFrameNoAuth_"+currentPageId;
    	formObj.action = contextPath + "/noAuthPageDetail.do?noAuthPageId="+pageId;
    	formObj.submit();
    	return;
    }

    var oldStrutsAction = formObj.elements['strutsAction'].value;
	formObj.elements['strutsAction'].value = strutsAction;
	var url = contextPath + "/" + pageId + ".do";
	formObj.action = url;

	//상세 페이지 내부의 Tab Open할때는 로딩 표시하지 않음
	if(DECORATOR_NAME != "tabPage")setModal();
	
	//Popup, default Page는 그냥 열자!
    if (DECORATOR_NAME != "innerTabPage" && DECORATOR_NAME != "tabPage" && DECORATOR_NAME !="defaultTabPage")
	{    	
    	formObj.target = "tabFrameTAB."+pageId;
	}
    else if(DECORATOR_NAME == "innerTabPage" )
    {
		var isDecoArray	= $("input[name='isDecoratorName']");
		//Decorator 가 지정이 안되어 있으면 innerTabPage안 page(accordion)은 tabPage Deco로 열린다.
		if(isDecoArray.length == "0")
		{
			var decoratorNameObj = document.createElement("input");
		    decoratorNameObj.type = 'hidden';
		    decoratorNameObj.name = 'isDecoratorName';
		    decoratorNameObj.value = "tabPage";
			
			formObj.appendChild(decoratorNameObj);
		}
		else if(isDecoArray.length == "1") //Docorator Name항목이 있으면 tabPage로 Deco 세팅
		{
			$("input[name='isDecoratorName']").val("tabPage");
		}
		
		formObj.target = "tabFrameTAB."+pageId;
    }
    //Decorator Name이 tabPage인 경우, 내부 page는 innerTabPage를 사용해서 로딩한다.
	else
	{
		var isDecoArray	= $(formObj).find("input[name='isDecoratorName']");

		if(isDecoArray.length == "0")
		{
			var decoratorNameObj = document.createElement("input");
		    decoratorNameObj.type = 'hidden';
		    decoratorNameObj.name = 'isDecoratorName';
		    decoratorNameObj.value = "innerTabPage";
			
			formObj.appendChild(decoratorNameObj);
		}
		else if(isDecoArray.length == "1")
		{
			isDecoArray.val("innerTabPage");
		}

		formObj.target = "tabFrameTAB."+pageId;

	}

    //console.log("commonTabPage:"+formObj.strutsAction.value);

    if($("#tabFrameTAB\\."+pageId).contents().find('div').length != 0 && DECORATOR_NAME == "tabPage" && typeof closePageId == "undefined" )
	{
    	var divObj = $('#'+pageId+'_tabList');
    	divObj.find('.ac_menu').trigger("click");
	}
    else formObj.submit();
    
	formObj.target = "";
	formObj.elements['strutsAction'].value = oldStrutsAction;

	$("iframe[name='tabFrameTAB\\."+pageId+"']").parent().parent().find('.ac_menu').addClass("current");

}

/**
 * 레포트를 호출하는 공통 펑션이다. 
 * reportNameValue : mrd파일의 이름이다. mrd파일과 qrd파일은 rd폴더 하위에 존재
 * windowName : report View가 호출되면 브라우저가 열리는데 중복된 브라우저 명을 피하기 위해서 
 *              각 화면의 이름 파라미터로 전송 받아서 setting한다.
 * @param reportNameValue
 * @param windowName
 * @param param1
 * @param param2
 * @param param3
 * @param param4
 * @param param5
 * @param param6
 * @param param7
 * @param param8
 * @param param9
 * @return
 */
function reportCall(reportNameValue,qrdNameValue, param1, param2, param3, param4, param5, param6, param7, param8, param9, param10, param11, param12, param13, param14, param15)
{
	var param1Value = replaceUrlChar(param1);
	var param2Value = replaceUrlChar(param2);
	var param3Value = replaceUrlChar(param3);
	var param4Value = replaceUrlChar(param4);
	var param5Value = replaceUrlChar(param5);
	var param6Value = replaceUrlChar(param6);
	var param7Value = replaceUrlChar(param7);
	var param8Value = replaceUrlChar(param8);
	var param9Value = replaceUrlChar(param9);
	var param10Value = replaceUrlChar(param10);
	var param11Value = replaceUrlChar(param11);
	var param12Value = replaceUrlChar(param12);
	var param13Value = replaceUrlChar(param13);
	var param14Value = replaceUrlChar(param14);
	var param15Value = replaceUrlChar(param15);

	var reportName = reportNameValue;
	var qrdName    = qrdNameValue;
	
	//filter 사이즈
	var width=800;
	var height=800;
	var param;

	//filter가 보여 질 위치를 계산다.
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	url = contextPath + "/report.do";
	//filter에서 수행될 StrutsAction을 파라미터로 전송한다.
	param = "?" + "strutsAction=4001" + 	        
	        "&" + "reportName="+reportName +
	        "&" + "qrdName="+qrdName +
	        "&" + "param1=" + param1Value +
	        "&" + "param2=" + param2Value +
	        "&" + "param3=" + param3Value +
	        "&" + "param4=" + param4Value +
	        "&" + "param5=" + param5Value +
	        "&" + "param6=" + param6Value +
	        "&" + "param7=" + param7Value +
	        "&" + "param8=" + param8Value +
	        "&" + "param9=" + param9Value +
	        "&" + "param10=" + param10Value +
	        "&" + "param11=" + param11Value +
	        "&" + "param12=" + param12Value +
	        "&" + "param13=" + param13Value +
	        "&" + "param14=" + param14Value +
	        "&" + "param15=" + param15Value;

	// RD레포트 화면 열릴때 IE가 아닐경우 확인 메세지 적용
	if(!isIE) 
	{
		// IE가 아닐경우 "이 기능은 Internet Explorer에서만 지원됩니다." 메시지
		alertMessage1(COMMON_MSG1006);
	}
	else
	{
		window.open(url + param, reportName+"_report", 'top=0,left=0,width='+(screen.availWidth-10)+',height='+(screen.availHeight-48)+',fullscreen=no,toolbar=0,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=0');
	}
}

function printSelect(rptNo, form, addParam)
{
	paramObj = getFormValue(form);
	$.each(loginUser, function(key, value){
		paramObj['loginUser.'+key] = value;
	});
	$.each(addParam, function(key, value){
		paramObj[key] = value;
	});
	
	url = contextPath + "/lovMgrRptCpList.do";
	
	param = "strutsAction=1001"+
			"&" + "lovMgrRptCpDTO.rptListNo="+rptNo+
			"&" + "lovMgrRptCpDTO.isUse="+"Y";
	
	ajaxPost(url, param).done(function(d){
		dataObj = JSON.parse(d);
		if(dataObj.total_count==0){
			alertMessage1("사용할수 있는 레포트가 없습니다");
		}
		else if(dataObj.total_count==1){
			data = dataObj.data[0];
			
			printReport({
				rptCpFileId: data.RPTCPFILEID
				,rptListName: data.RPTLISTNAME
				,svrAddr: data.SVRADDR
				,designFile: data.DESIGNFILE
				,queryFile: data.QUERYFILE
				,rptFileType: data.RPTFILETYPE
				,param: encodeURIComponent(JSON.stringify(paramObj))
			})
			.fail(function(d){
				alertMessage1(d);
			});
		}
		else{
			param = "lovMgrRptCpDTO.rptListNo="+rptNo+
					"&" + "lovMgrRptCpDTO.isUse="+"Y"+
					"&" + "lovMgrRptCpDTO.param="+encodeURIComponent(JSON.stringify(paramObj))+
					"&" + "isDecoratorName=popupPage";
			
			openLayerPopup('lovMgrRptCpList', param);
		}
	});
}

function printReport(paramObj)
{
	var deferred = $.Deferred();
	
	if(paramObj.rptFileType=='RDX'&&!isIE) 
	{
		// RDX이면서 IE가 아닐경우 "이 기능은 Internet Explorer에서만 지원됩니다." 메시지
		deferred.reject(COMMON_MSG1006);
		return deferred.promise();
	}
	
	var url = contextPath + "/lovMgrRptCpList.do";
	
	var param = "strutsAction=1002"+
				"&" + "lovMgrRptCpDTO.rptCpFileId="+paramObj.rptCpFileId+
				"&" + "lovMgrRptCpDTO.rptListName="+paramObj.rptListName+
				"&" + "lovMgrRptCpDTO.svrAddr="+paramObj.svrAddr+
				"&" + "lovMgrRptCpDTO.designFile="+paramObj.designFile+
				"&" + "lovMgrRptCpDTO.queryFile="+paramObj.queryFile+
				"&" + "lovMgrRptCpDTO.rptFileType="+paramObj.rptFileType+
				"&" + "lovMgrRptCpDTO.param="+paramObj.param;
	
	$.post(url, param, function(_json){
		jsonObj = JSON.parse(_json);
		
		width=800;
		height=800;
		sleft = (screen.width - width) / 2;
		stop = (screen.height - height) / 2;
		features = "left=" + sleft + ",top=" + stop;
		var pos = 'top=0,left=0,width='+(screen.availWidth-10)+',height='+(screen.availHeight-48)
				+',fullscreen=no,toolbar=0,location=0,directories=0,status=0,menubar=0,resizable=1,scrolling=0';
		
		openWindowWithPost(paramObj.svrAddr, paramObj.rptListName, jsonObj, pos);
		
		deferred.resolve();
	});
	
	return deferred.promise();
}

/**
 * wait 메세지를 보여준다.
 */
function showWaitBox(_show)
{
	if (_show)
	{
		M$("mware_waiting").style.display = "block";
		setLoading(true);
	}
	else
	{
		M$("mware_waiting").style.display = "none";
		setLoading(false);
	}
}

/**
 * 주어진 소수점에서 반올림 한다.
 * 123.45를 첫번째 자리까지 표현할 경우 다음과같이 호출 .
 * roundFloat(123.45, 1);
 */
function roundFloat(floatValue, sosuLen)
{
	// 소수점 filtering
	var mainData = floatValue+"";
	var prefixMinus = "";
	if(floatValue < 0)
	{
		prefixMinus = "-";
		floatValue = mainData.replace(/\-/gi, "");
	}
	
	var sosuValue = 1;
	for(var i = 1 ; i <= sosuLen ; i++)
	{
		sosuValue = sosuValue * 10;
	}
	
	return prefixMinus+Math.round(floatValue*sosuValue)/sosuValue;
}

/**
 * file 이름에서 확장자를 추출한다.
 * @param _fileName
 * @returns
 */
function getFileType(_fileName)
{
	if (_fileName == null || "" == _fileName || _fileName.indexOf(".") == -1) return;
	
	var fileToken = _fileName.split('.'); 
    return fileToken[fileToken.length-1];
}

/**
 * tabTree.js 안의 function을 호출한다.
 * treeInclude 가 있는 page에서 Resize시 호출된다.
 * 이곳에서 다시 재호출하는것은 아주가끔 treeInclude가 로딩되기 전에 treeMoveResize가 호출되어 스크립트 오류가 발생하는경우 방지를 위해서이다.
 * @param event
 */
function treeMoveResize(_event)
{
	try{
		treeMoveResizeOrigin(_event);
	}
	catch(e){}
}

/**
 * 결재문서 상세 보기
 */
function goDetailview(_objectNo, _wfType)
{
    var param = "strutsAction=" + lovBaseDefaultAction;
	var pageId = "";

    // 작업요청 상세
    if ("WOR" == _wfType)
    {
        param = param + "&woReqCommonDTO.reqNo=" + _objectNo;
        pageId = "woReqDetail";
    }
    // 작업설계 상세
    else if ("WOP" == _wfType)
    {
        param = param + "&woPlanCommonDTO.woNo=" + _objectNo;
        pageId = "woPlanDetail";
    }
    // 작업실적 상세
    else if ("WOC" == _wfType)
    {
        param = param + "&woResultCommonDTO.woNo=" + _objectNo;
        pageId = "woResultDetail";
    }
    // 공사관리 상세(공사설계, 공사완료)
    else if ("PJTL" == _wfType || "PJTC" == _wfType)
    {
        param = param + "&woPjtCommonDTO.pjtNo=" + _objectNo;
        pageId = "woPjtDetail";
    }
    // 인수인계
    else if ("BMT" == _wfType)
    {
        param = param + "&bmTransCommonDTO.twNo=" + _objectNo;
        pageId = "bmTransDetail";
    }
    // 자재등록(변경)
    else if ("REG" == _wfType)
    {
        param = param + "&ptRegCommonDTO.partRegNo=" + _objectNo;
        pageId = "ptRegDetail";
    }
    // 비정상운영기기관리(등록), 비정상운영기기관리(해제)
    else if ("BMAI" == _wfType || "BMAC" == _wfType)
    {
        param = param + "&bmAbnCommonDTO.aomNoKey=" + _objectNo;
        pageId = "bmAbnDetail";
    }
    // 일일정비회의
    else if ("BMD" == _wfType)
    {
        param = param + "&bmDayCommonDTO.dmrNo=" + _objectNo;
        pageId = "bmDayDetail";
    }
    // 고장속보
    else if ("BSMR" == _wfType)
    {
        param = param + "&bsmRptCommonDTO.bsmRptNo=" + _objectNo;
        pageId = "bsmRptDetail";
    }
    // 자재구매발주
    else if ("POR" == _wfType)
    {
        param = param + "&ptOrderCommonDTO.selectedPoNo=" + _objectNo;
        pageId = "ptOrderDetail";
    }
    // 발전정지관리
    else if ("BSMM" == _wfType)
    {
        param = param + "&bsmMngCommonDTO.bsmMngNo=" + _objectNo;
        pageId = "bsmMngDetail";
    }
    // 자재구매요청관리
    else if ("PUR" == _wfType)
    {
        param = param + "&ptPurCommonDTO.selectedPrNo=" + _objectNo;
        pageId = "ptPurDetail";
    }
    // 자재반납입고
    else if ("RTN" == _wfType)
    {
        param = param + "&ptRtnCommonDTO.selectedPrtnNo=" + _objectNo;
        pageId = "ptRtnDetail";
    }
    // 자재추가요청
    else if ("PADD" == _wfType)
    {
        param = param + "&ptAddCommonDTO.selectedPrNo=" + _objectNo;
        pageId = "ptAddDetail";
    }
    // 작업허가서
    else if ("PTW" == _wfType)
    {
    	param = param + "&woPtwCommonDTO.ptwNo=" + _objectNo;
    	pageId = "woPtwDetail";
    }
    // 결재공지상세
    else if ("APPNOT" == _wfType)
    {
    	param = param + "&appReqCommonDTO.appDocNo=" + _objectNo
    	              + "&appReqCommonDTO.isCheck=" + "Y";
    	pageId = "appNotDetail";
    }
    // 금일점검
    else if ("INSP" == _wfType)
    {
    	param = param + "&pmiCheckMngDTO.itemNo=" + _objectNo;
    	pageId = "pmiCheckMng";
    }
    // 공지사항
    else if ("NOT" == _wfType)
    {
    	param = param + "&stdNoticeCommonDTO.noticeNo=" + _objectNo;
    	pageId = "stdNoticeDetail";
    }
    // 게시판
    else if ("BOD" == _wfType)
    {
    	param = param + "&boardPopupCommonDTO.boardNo=" + _objectNo;
    	pageId = "boardPopupDetail";
    }
    // Service요청
    else if ("SERV" == _wfType)
    {
    	param = param + "&woServCommonDTO.selectedPrNo=" + _objectNo;
    	pageId = "woServDetail";
    }
    // Request for Revision
    else if ("HSEREV" == _wfType)
    {
    	param = param + "&hseRevCommonDTO.reqNo=" + _objectNo;
    	pageId = "hseRevDetail";
    }
	openQuickPage(param, pageId);
}

/**
 * Member button으로 유저 상세 정보를 팝업으로 보이기
 */
function goMember()
{
	var popWidth  = 700;
	var popHeight = 420;

	// pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);
    
	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	
	var strutsAction = memberPopupAction;

	var url   = contextPath + "/memberPopup.do";
	var param = "?" + "strutsAction=" + strutsAction;
	window.open(url+param, "memberPopup", pos+ "scrollbars=no,status=no,toolbar=no, re	sizable=no,location=no,menu=no");
}

/**
 * Board button으로 게시판 팝업으로 보이기
 */
function goBoard()
{
	var url   = contextPath + "/boardPopupList.do";

    var popWidth = 1020;
    var popHeight = 650;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=no, status=no, resizable=no";
    
    var param = "?" + "strutsAction=" + lovBaseDefaultAction;
  
    window.open(url + param, "BOARD_POPUP", pos);
}

/**
 * 자재구매입고, 자재출고, 무가액입고 날짜 Validation
 * 현재보다 이후의 날짜는 입고,출고 불가능
 * 현재날짜가 달의 1일 or 2일 인 경우 전달 입고,출고 가능
 * 2달이상 이전의 달은 입출고 불가능
 * @author hiimkkm
 * @param _date
 * @returns {Boolean}
 */
function validDateRecIss(_date)
{
	var curDate = dateToData(getToday());
	var curYear = curDate.substr(0, 4);
	var curMon = curDate.substr(4, 2);
	var curDay = curDate.substr(6, 2);

	var year = _date.substr(0, 4);
	var mon = _date.substr(4, 2);
    
    // 입고,출고 일자가 현재일자보다 큰 경우
	if(curDate < _date)
	{
		// 입력일자가 현재일자보다 큽니다. 다시 입력하세요.
		alertMessage1(COMMON_CMSG054);
		return false;
	}
	
	// 같은 년도
	if(curYear == year)
	{
		// 현재월과 입력월이 같은경우
	    if(mon == curMon)
	    {
	    }
	    // 입력월이 현재월의 전달인경우
	    else if(mon == curMon-1)
	    {
	        // 현재일자가 1일 또는 2일인 경우 입력월은 한달전도 가능하다.
	        if(curDay == '01' || curDay =='02')
	        {
	        }
	        // 그이외의 날자는 입고 불가능.
	        else
	        {
	            // 입력월이 적절하지 않습니다. 다시 입력하세요.
	        	alertMessage1(COMMON_CMSG055);
	            return false;
	        }
	    }
	    // 입력월이 현재월의 전달보다 이전달인 경우
	    else
	    {
	        // 입력월이 적절하지 않습니다. 다시 입력하세요.
	    	alertMessage1(COMMON_CMSG055);
	        return false;
	    }
	}
	else
	{
		// 입력년도가 적절하지 않습니다. 다시 입력하세요.
		alertMessage1(COMMON_CMSG056);
        return false;
	}
	
	return true;
}


/**
 * Grid에서 마우스로 Cell 클릭시 ToolTip이 나오도록 한다.
 */
function cellToolTip(grid, cell, value, columnObj)
{
	/*
	 * 1. grid가 수정불가상태이거나, grid는 수정가능이라도 칼럼 자체가 수정불가일 경우
	 *    또는 칼럼에 Editor가 없어서 수정할 수 없는 경우
	 * 2. 칼럼이 존재하고 toolTip 속성이 true인 칼럼
	 * 3. 칼럼에 값이 존재하는 경우
	 * 위 3가지 조건을 만족할 경우에만 TOOLTIP을 보여준다.
	 */
	// 일단 지우고 본다.
	grid.hideCellToolTip();
  
	if ((!grid.editable || !columnObj.editable || columnObj.editor == null)
		&& columnObj && columnObj.toolTip 
		&& value != "" && value != null)
	{
		var wi = getByteLength(value)*8;
		if(wi > columnObj.toolTipWidth)
		{
			wi = columnObj.toolTipWidth;
		}
		grid.showCellToolTip(cell,wi);
	}
}

/*
 * Message 라인에 Copy Right 보여주기
 */
function showCopyRight()
{
	var copyRight = "<div style='width:100%; text-align:center;'><img src='"+contextPath+"/common/images/main/img_copyright.png' height='21'></img></div>";

	messageHtml = jQuery("#bottomMsg").html();
	
	jQuery("#bottomMsg").html(copyRight);
	jQuery("#bottomMsg").css("visibility","visible");
}

/*
 * Message 라인에 Copy Right 숨기기
 */
function hideCopyRight()
{
	jQuery("#bottomMsg").html(messageHtml);
	jQuery("#bottomMsg").css("visibility","hide");
}

/**
 * 필터필드인지 확인
 */
function isFilterField(name)
{
	return _comFilterField.indexOf(name)>=0;
}
	
/**
 * Update Setting
 */
function setForUpdate(pId)
{
	var _pageId = "";

	if(typeof pId == "undefined") _pageId = currentPageId;
	else _pageId = pId;
	
	currentPageId = _pageId;
	
	var topDoc = getTopPage();
	curPageUpdate = true; //LOV용
	
	//set filter field for escape update
	$(".function_box.filter").closest(".section_wrap").find("input,textarea").each(function(index) {
		_comFilterField.push($(this).attr("name"));
	});
	
	$("input[type='text']:not([readonly]),textarea:not([readonly])").bind({
		'keyup':function(e){
			//console.log(pId);
			if($(this).is('[readonly]')) return;
			if(isFilterField($(this).attr("name"))) return;
			if(e.which != 9) //only if not tab key 
			{
				topDoc.updateArray[_pageId] = $(this).attr("name");
			}
		}
	});
	
	$("input[type='checkbox']").bind("click", function(e){
		if(e.which != 9) //only if not tab key 
			topDoc.updateArray[_pageId] = $(this).attr("name");
	});
	
	//생성의 경우 저장 옵션 (Inner tab Page에서는 일괄등록 및 Edit 기능으로 우선 제외)
	if(ckCreate(_pageId) && DECORATOR_NAME != "innerTabPage" && DECORATOR_NAME != "defaultPage") topDoc.updateArray[_pageId] = "INIT";
	
}

function setForNormal(pId)
{
	var _pageId = "";
	
	if(typeof pId == "undefined") _pageId = currentPageId;
	else _pageId = pId;
	
	currentPageId = _pageId;
	
	var topDoc = getTopPage();
	curPageUpdate = false; //LOV용
	$("input[type='text']:not([readonly]),textarea:not([readonly])").unbind( "keyup" );

	$("input[type='checkbox']").unbind( "click" );

}

var saveNew = "";
function goSavenew(pageId)
{
	getTopPage().saveNew = pageId;
	goSaveAll();
}

var saveCnt = 0;
var confirmResult = false;
function goSaveAll(_contents)
{
	var topDoc = getTopPage();
	topDoc.saveCnt = 0;
	$.map( topDoc.updateArray, function( value, key ) {
		topDoc.saveCnt++;
	});
	
	if(topDoc.saveCnt > 0 && !topDoc.confirmResult)
	{
		topDoc.dhtmlx.confirm(COMMON_CMSG100, function(result){
			
			topDoc.confirmResult = result;
			if(topDoc.confirmResult)
			{
				setModal(COMMON_CMSG060);

				goSaveAllAction(_contents);
				saveCnt = 0;
			}
			else saveCnt = 0;
			
		});
	}
	else if(topDoc.saveCnt > 0 && topDoc.confirmResult)
	{
		goSaveAllAction(_contents);
		saveCnt = 0;
	}
	else if(topDoc.saveCnt == 0)
	{
		topDoc.confirmResult = false;
	}
}

/**
 * 모든 수정된 page 저장 
 */
function goSaveAllAction(_contents)
{	
	var topDoc = getTopPage();
	if(typeof _contents == "undefined") _contents = topDoc.document;

	//Is Auto Complete Visible?
	if(myPop && myPop.isVisible())
	{
		alertMessage1(COMMON_CMSG047);
		closeModal();
		return;
	}

	 //모든 Page 조회 후 수정되었으면 저장
	if($(_contents).find("iframe").length > 0)
	{
		$(_contents).find("iframe").not(".dhxeditor_mainiframe").each(function(index){

			if(typeof $(this).attr("name")  == "undefined") return true;
			
			var iframeName = $(this).attr("name").replace("tabFrameTAB.","");
			var flag = false; //수정여부
			
			//저장시 열리지 않은 tab이 있으면 열어주자!
			/*if($(this).contents().find('div').length == 0 && DECORATOR_NAME == "tabPage")
			{
		    	var divObj = $('#'+iframeName+'_tabList');
		    	divObj.find('.ac_menu').trigger("click");
			}*/
			
			if($(this).attr("name").indexOf("popupIframe") != -1)
			{ 
				//iframeName = $(this).contents().find("form").eq(0).attr("name").replace("Form","");
				iframeName = $(this).get(0).contentWindow.currentPageId;
				//iframeName = "maGrdUsrDetail"; //사용자 정의 리스트를 위한 예외사항 추후 수정필요
			}
			
			$.map( topDoc.updateArray, function( value, key ) {
			    if(key == iframeName) //iframe (tabpage) 가 수정중이면...
				{
			    	flag = true;
				}
			    		    
			}); //End of Map.

			//신규 생성 페이지가 있다면 채크
			//if($(this).contents().find("[name='strutsAction']").val() == "0")
			//{
			//	getTopPage().updateArray[iframeName] = "NEW";
			//	flag = true; //신규생성 페이지도 저장시 채크
			//}
			//수정된 페이지라면...
		    if(flag) 
		    {
		    	var targetIfmCnt = $(this).get(0).contentWindow;
		    	if(!targetIfmCnt.disabled ) //Disable 적용 Page는 저장이 안된다.
		    	{
		    		if(typeof targetIfmCnt.checkValidation == "function") 
		    			if(targetIfmCnt.checkValidation()) return;
		    		
		    		if(typeof targetIfmCnt.goSave == "function")
		    			targetIfmCnt.goSave(); //각 페이지의 goSave function 호출
		    	}
		    	else 
		    		getTopPage().afterSaveAll(iframeName);

				//저장한 페이지는 updateArray에서 제외
				//afterSaveAll(iframeName) //각 페이지에서 저장이 끝나면 호출..즉 저장이 끝나야지 updateArray에서 제외
		    }
		    
		    //조회된 페이지 내부의 페이지 다시 조회...반복
		    if($(this).contents().find("iframe[name^='tabFrame']").length > 0)
		    	goSaveAll($(this).contents());
	
		 });
	}
	//내부 page 없는 최상위 Page일 경우.
	if(document == _contents && curPageUpdate)
	{
		 goSave();
		 
		 //afterSaveAll(currentPageId);  //각 페이지에서 저장이 끝나면 호출..즉 저장이 끝나야지 updateArray에서 제외
	}

	//if(saveCnt == 0) alertMessage1("수정된 항목이 없습니다.");
	
	topDoc._result = false;
	_result = false;
}

/**
 * 선택 iframe contents의 내부 Iframe의 수정 여부 확인
 */
var _result = false;
function ckSubUpdate(_contents)
{
	_result = false; 
	 var topDoc = getTopPage();
	 if(typeof _contents == "undefined") _contents = parent.document;

	 if($(_contents).find("iframe[name^='tabFrame']").length > 0 && _result == false)
	 {
		 $(_contents).find("iframe[name^='tabFrame']").each(function(index){

			var iframeName = $(this).attr("name").replace("tabFrameTAB.","");

			//sub pages중 첫번째 업데이트 Flag 발생시 Stop 
			if(!_result) _result = ckUpdate(iframeName);

			if(_result)
			{
				return false;
			}
			
			if(!_result) ckSubUpdate($(this).contents());
			
		 });
	 }
	 //iframe이 없다면..
	 else if($(_contents).find("iframe[name^='tabFrame']").length == 0 && _result == false)
     {
		 _result = ckUpdate(currentPageId);
     }
	 
	 return _result;
}

/**
 * 단위 페이지 업데이트 채크 
 * @param _fileName
 * @returns
 */
function ckUpdate(_fileName)
{
	var _result = false;
	$.map( topDoc.updateArray, function( value, key ) {

		//한 페이지라도 수정중이라면 수정중 Flag를 올려준다.
	    if(key == _fileName) 
		{
	    	_result = true; 
		}
	});
	
	return _result;
}

/*
 * Get Top Page from Iframe
 */
function getTopPage()
{
	var pObj = self;

	//최상위 부모 찾기
	if(pObj != top)
	do{
		/*if(window.opener)
		{
			break;
		}*/
		
		pObj = pObj.parent;
		
	}while(pObj != top);

	return pObj;
}

/**
 * Page ID를 TEMP Array에 저장.
 */
var aboutRemoveUpdate = new Array();
function getSubPageId(pageId, _content)
{
	if(typeof _content == "undefined")
	{
		_content = $(parent.document);
	}

	_content.find("iframe[name^='tabFrame']").each(function(e){
		var iframeName = $(this).attr("name").replace("tabFrameTAB.","");
	    var inPageId;
	    
		if(typeof pageId == "undefined" || pageId == "") inPageId = iframeName;
		else inPageId = pageId;
		if(inPageId == iframeName)
		{
			aboutRemoveUpdate.push(iframeName);
			//alert($(this).contents().find("iframe[name^='tabFrame']").length);
			if($(this).contents().find("iframe[name^='tabFrame']").length > 0)
			{
				getSubPageId("",$(this).contents());
			}
		}
	});
	
	clearUpdateArray();

}

function goCloseAll(pageId)
{
	if(typeof pageId != "undefined") 
		parent.$('#tabFrameTAB\\.'+pageId).contents().find("iframe[id^='tabFrameTAB']").each(function(){
			$(this).contents().find('input').val("");
			$(this).contents().hide();
			$(this).parents("div[id^='tabDivTAB']").slideUp('fast', function(){
	
				$(this).find('iframe').each(function(){
					$(this).contents().find('div').remove();
				});
				
				if(typeof parent.resizeTabFrame == "function")
				{
					parent.resizeTabFrame();
				}
				
				$(this).parent().find('.ac_menu').removeClass("current");	
				$(this).parent().find('.tab_header').children().remove();
			});
		});
	else
		parent.$("iframe[id^='tabFrameTAB']").each(function(){
			$(this).contents().find('input').val("");
			$(this).contents().hide();
			$(this).parents("div[id^='tabDivTAB']").slideUp('fast', function(){
	
				$(this).find('iframe').each(function(){
					$(this).contents().find('div').remove();
				});
				
				if(typeof parent.resizeTabFrame == "function")
				{
					parent.resizeTabFrame();
				}
				
				$(this).parent().find('.ac_menu').removeClass("current");	
				$(this).parent().find('.tab_header').children().remove();
			});
		});
	
	//Close Page
}

/*
 * TAB 전체 닫기
 */
function goClose(pageId, _doc)
{
	//현재 Page와 내부 Page의 수정 여부 확인.
	//if (checkIsUpdate(parent.document)) return;
	if(typeof _doc =="undefined") _doc = parent;

	var topPage = getTopPage();
	if(checkIsUpdate(_doc.document)){
		topPage.dhtmlx.confirm(COMMON_CMSG010, function(result){
			if(result)
			{
				//Close 하는 Page와 내부 Page ID를 TEMP Array에 저장.
				getSubPageId(pageId, $(_doc.document));

				//CREATE?
				if(ckCreate(pageId))
				{	
//					if(typeof _doc.goSearch == "function") _doc.goSearch();
//					$("[name='strutsAction']").val("");
				}
				
				//Close Page
				_doc.$('#tabFrameTAB\\.'+pageId).contents().find('input').val("");
				_doc.$('#tabFrameTAB\\.'+pageId).contents().hide();
				_doc.$('#tabDivTAB\\.'+pageId).hide();
				
				//Tab 닫힐때 Floating Tab Title도 닫혀라!
				var tabFTitle = topPage.$('#pg_title,.pwin_header').next();
				if(tabFTitle.is('.sheader_box') && tabFTitle.prop("id") == pageId+"_title") tabFTitle.remove();

				
				_doc.$('#tabDivTAB\\.'+pageId).hide();
				
				if(typeof _doc.resizeTabFrame == "function")_doc.resizeTabFrame();
				
				if(typeof _doc.afterClose == "function") _doc.afterClose(pageId);
			}
			
		});
	}
	else
	{
		//CREATE?
		if(ckCreate(pageId))
		{	
//			if(typeof _doc.goSearch == "function") _doc.goSearch();
//			$("[name='strutsAction']").val("");
		}

		//Close Page
		//_doc.$('#tabFrameTAB\\.'+pageId).contents().hide();
		_doc.$('#tabFrameTAB\\.'+pageId).contents().find('input').val("");
		_doc.$('#tabFrameTAB\\.'+pageId).contents().hide();
		
		var tabFTitle = topPage.$('#pg_title,.pwin_header').next();
		//console.log("닫고 삭제!");
		if(tabFTitle.is('.sheader_box') && tabFTitle.prop("id") == pageId+"_title") tabFTitle.remove();
		
		
		/*_doc.$('#tabDivTAB\\.'+pageId).slideUp('fast', function(){
			
			if(typeof _doc.resizeTabFrame == "function")
			{
				_doc.resizeTabFrame();;
			}
		});*/
		_doc.$('#tabDivTAB\\.'+pageId).hide();

		//if (window.opener) window.open('', '_self').close();
		if(typeof _doc.resizeTabFrame == "function")_doc.resizeTabFrame();
		
		if(typeof _doc.afterClose == "function") _doc.afterClose(pageId);
	}

}

/**
 * When The Detail Page is scrolled until the title is disappeared, the title with button should be attached to Top page.
 * @returns
 */
function genTabTitle()
{
	var dPageId;
	var topHeight = 20;
	/*$("div[id^='tabDivTAB']").each(function(e){
		if($(this).css("display") == "block") dPageId = $(this).prop("id").replace("tabDivTAB.","");
	});*/
	
	var _tabId = $("div[id^='tabDivTAB']:visible").eq(0).prop("id");
    if(_tabId != null && _tabId != "") dPageId = _tabId.replace("tabDivTAB.","");

	
	if(typeof dPageId == "undefined") return;

	var listHeight = $("#bodyDiv").outerHeight(true) + 20;
	var scrollTop = $("#content,.pwin_content").scrollTop();
	var pgTitle = $('#pg_title,.pwin_header');
    var iframeCnt = $("#tabFrameTAB\\."+dPageId).contents();

    if(scrollTop >= listHeight && !pgTitle.next().is('.sheader_box'))
    {
    	if($('#tabDivTAB\\.'+dPageId).css("display") !="block") return;
	 	var sheadBoxClone = iframeCnt.find('.sheader_box').clone();
	 	sheadBoxClone.find('.function_box').children().remove();
	 	sheadBoxClone.css({
	 		"z-index":"200",
	 		"margin-left": (parseInt(pgTitle.css("margin-left").replace("px","")))+"px",
	 		"height":"40px",
	 		"margin-right" : "47px",
	 		"left":"15px"
	 	}).prop("id",dPageId+"_title");
	 	sheadBoxClone.addClass("dtitle_box").on("click",function(e){
	 		$("#content,.pwin_content").animate({scrollTop:listHeight},100);
	 	});
	 	//sheadBoxClone.addClass("section_wrap");
	 	
	 	sheadBoxClone.find('.function_box').children().remove();
	 	sheadBoxClone.find('.function_box').append(iframeCnt.find('.sheader_box>.function_box').eq(0).children());
	 	
	 	//pgTitle.next().remove();
	 	pgTitle.after(sheadBoxClone);
	 	
	 	$('.sfb_wrap:visible').hide();
    }
    else if(scrollTop < listHeight && pgTitle.next().is('.sheader_box'))
    {
    	//console.log("여기");
    	var sheadBox = pgTitle.next();
    	iframeCnt.find('.sheader_box>.function_box').eq(0).append(sheadBox.find('.function_box').children());
    	
    	pgTitle.next().remove();

    	iframeCnt.find('.sfb_wrap:visible').hide();
    }
}

function clearUpdate(_pageId)
{
	var topDoc = getTopPage();
	var tempArray = {};
	$.map( topDoc.updateArray, function( value, key ) {
		
		var flag = false;
		
		if(key == _pageId) flag = true;

		if(!flag) tempArray[key] = value; 
	});

	//topDoc.updateArray = new Array();
	topDoc.updateArray = {};
	$.map( tempArray, function( value, key ) {
		topDoc.updateArray[key] = value;
	});
}

/**
 * Close 되는 Page들의 ID들을 UpdateArray에서 제거 
 */
function clearUpdateArray()
{
	var topDoc = getTopPage();
	var tempArray = {};
	$.map( topDoc.updateArray, function( value, key ) {
		
		var flag = false;
		$.map( aboutRemoveUpdate, function( removeVal ) {
			//console.log("RemoveUpdate:"+removeVal);
			if(removeVal == key)
			{
				flag = true;
			}
		});
		if(!flag) tempArray[key] = value; 
	});

	//topDoc.updateArray = new Array();
	topDoc.updateArray = {};
	$.map( tempArray, function( value, key ) {
		topDoc.updateArray[key] = value;
	});
	
	aboutRemoveUpdate= new Array();
}

/**
 * 주어진 page ID를 UpdateArray에서 제거
 */
function afterSaveAll(pageId)
{
	$("iframe").not(".dhxeditor_mainiframe").each(function(index){

		if(typeof $(this).attr("name")  == "undefined") return true;
		
		var iframeName = $(this).attr("name").replace("tabFrameTAB.","");
		var flag = false; //수정여부
		
		//저장시 열리지 않은 tab이 있으면 열어주자!
		if($(this).contents().find('div').length == 0 && DECORATOR_NAME == "tabPage")
		{
	    	var divObj = $('#'+iframeName+'_tabList');
	    	divObj.find('.ac_menu').trigger("click");
		}
	});
	
	var topDoc = getTopPage();
	var tempArray = {};
	$.map( topDoc.updateArray, function( value, key ) {
		if(key != pageId)tempArray[key] = value; 
	});
	
	var lftCnt = 0;
	topDoc.updateArray = {};
	$.map( tempArray, function( value, key ) {
		topDoc.updateArray[key] = value;
		lftCnt++;
	});

	if(lftCnt == 0){
		saveCnt = 0;
		topDoc.confirmResult = false;
		alertMessage1(COMMON_CMSG057);

		closeModal();
		
		var pgId = topDoc.saveNew;
		if(pgId!= ""){
			var contentDoc = getParentIframe("tabFrame_"+pgId);
			
			if(typeof contentDoc.afterSavenew == "function") 
			{
				contentDoc.M$('strutsAction').value = "0";
				
				contentDoc.afterSavenew();
			}
			else
			{
				contentDoc.clearAll();
				
				try{contentDoc.goInput();}catch(e){}
			}

			contentDoc.goCloseAll(pgId);
			
			topDoc.saveNew = "";
		}
	}
}

function clearAll()
{
	$("input,textarea").not("[name*='Common']").val("").each(function(e){
		
		chkInputVal($(this));
	});
	
	$("input[type='checkbox']").attr("checked",false);
	//For New Create
	$("input[name='strutsAction']").val("0");
}

/*
 * Page 가 Create된 페이지인지 확인
 */
function ckCreate(pageId, _doc)
{
	if(typeof _doc =="undefined") _doc = parent;

	if(_doc.$('#tabFrameTAB\\.'+pageId).length && _doc.$('#tabFrameTAB\\.'+pageId).contents().find("[name='strutsAction']").val() == "0")
	{
		return true;
	}
	else  if(!_doc.$('#tabFrameTAB\\.'+pageId).length && $("[name='strutsAction']").val() == "0")
	{
		return true;
	}
	else
		return false;
}

/*
 * Set Title
 */
function setTitle(_codeName, _descName)
{	
	if(typeof _codeName == "undefined" && typeof _descName == "undefined")
	{		
		if($("input[name*='Desc'], input[name*='desc']").length > 0 && $(".stitle_tx").text().indexOf(":") == -1)
		{
			$("input[name*='Desc']:text, input[name*='desc']:text").each(function(index){
				if(index == 0 && $(this).val() != "") $('.stitle_tx').append($(this).val()).find('span').before(" : "); 
			});
		}

	}
	else if(typeof _descName == "undefined")
	{
		var code = $("input[name='"+_codeName+"']").val();
		if(typeof code == "undefined"){
			code = $("textarea[name='"+_codeName+"']").val();
		}

		var titleObj = $('.stitle_tx'); // titleHtml = $('.stitle_box').html();
		if(titleObj.length == 0) titleObj = $('.stitle_box');
		var titleHtml = titleObj.html();
		var splitStr = ":";

		if(!isNull(titleHtml))
		{
			titleObj.html(titleHtml.substring(0,titleHtml.indexOf(splitStr)));
			titleObj.append(" : "+code); 	
		}
		else
			titleObj.append(code); 
			
	}
	else 
	{	
		var code = $("input[name='"+_codeName+"']").val();

		if(typeof code == "undefined"){
			code = $("textarea[name='"+_codeName+"']").val();
		}
		var desc = $("input[name='"+_descName+"']").val();
		if(typeof desc == "undefined"){
			desc = $("textarea[name='"+_descName+"']").val();
		}

		if(code != "" || desc != ""){

			desc = cleanXss(desc);

			var titleObj = $('.stitle_tx'); // titleHtml = $('.stitle_box').html();
			if(titleObj.length == 0) titleObj = $('.stitle_box');
			var titleHtml = titleObj.html();
			var splitStr = ":";

			if(!isNull(titleHtml))
			{
				titleObj.html(titleHtml.substring(0,titleHtml.indexOf(splitStr)));
				titleObj.append(" : "+code+" / "+desc); 	
			}
			else
				titleObj.append(code+" / "+desc); 
		}
	}
}

function cleanXss(desc)
{
	if(desc == null) {
		desc = "";
} else {
	desc = desc.replace(/&/gi, "&amp;")
                     .replace(/</gi, "&lt;")             
                     .replace(/>/gi, "&gt;")
                     .replace(/\"/gi, "&quot;");
}
return desc;
}

function addTitle(_inputName)
{
	if(typeof _inputName == "string")
	{
		var code = $("input[name='"+_inputName+"']").val();
		
		$('.stitle_tx').append(" / "+code); 
	}
	else
	{
		for(var i in _inputName)
		{
			var code = $("input[name='"+_inputName[i]+"']").val();
			
			$('.stitle_tx').append(" / "+code); 
		}
	}
}



function getDeletRows(_grid, _chkCol, _keyCol, _keyCol2, _keyCol3)
{	
	var deleteRows = _grid.getCheckedRows(typeof _chkCol == "number"?_chkCol:getIndexById(_grid, _chkCol)).split(",");
    if(deleteRows == "" && !_grid.isItemExists('')) return;

	  //var delArray = new Array();
	  var delRows= "";
	  for(var i = deleteRows.length-1; i >=0; i--)
	  {
		  if(typeof _keyCol2 == "undefined")
		  {
			  var paramQ = "&deleteRows=";
			  var keyVal 	= typeof _keyCol == "number"?_grid.cells(deleteRows[i], _keyCol).getValue():getValueById(_grid, deleteRows[i], _keyCol);
			  
			  delRows = delRows + paramQ + keyVal;
		  }
		  else
		  {
			  var paramQ = "&deleteRows=";
			  var paramExt = "&deleteRowsExt=";
			  
			  var keyVal 	= typeof _keyCol == "number"?_grid.cells(deleteRows[i], _keyCol).getValue():getValueById(_grid, deleteRows[i], _keyCol);
			  var extKeyVal = typeof _keyCol2 == "number"?_grid.cells(deleteRows[i], _keyCol2).getValue():getValueById(_grid, deleteRows[i], _keyCol2);
			  
			  delRows = delRows + paramQ + keyVal + paramExt + extKeyVal;
		  }
		  
		  if(typeof _keyCol3 != "undefined")
		  {
			  var paramExt = "&deleteRowsExt1=";
			  
			  var extKeyVal = typeof _keyCol3 == "number"?_grid.cells(deleteRows[i], _keyCol3).getValue():getValueById(_grid, deleteRows[i], _keyCol3);
			  
			  delRows = delRows + paramExt + extKeyVal;  
		  }
		  
		  //Row 삭제 화면에서 
		  _grid.deleteRow(deleteRows[i]);
	  }
	  
	  if(typeof delRows != "undefined") setCounter(_grid,"gridbox");
/*	  if(_grid.isItemExists(''))
	  {
		 alert( _grid.cells('', _chkCol).getValue());
	  }
	    */

	  return delRows;
}

function getCheckedRows(_grid, _chkCol)
{
	var deleteRows = _grid.getCheckedRows(typeof _chkCol == "number"?_chkCol:getIndexById(_grid, _chkCol)).split(",");
    if(deleteRows == "" && !_grid.isItemExists('')) return [];
    else return deleteRows;
}

function getSelectedAllCol(_grid,_chkCol){
	
	var deleteRows = _grid.getCheckedRows(typeof _chkCol == "number"?_chkCol:getIndexById(_grid, _chkCol)).split(",");
	if(deleteRows == "" && !_grid.isItemExists('')) return;
	var colSize = _grid.getColumnsNum();
	var rowSize = deleteRows.length;
	
	var checkArray = new Array();
	for(var i=0; i<rowSize; i++){
		var myMap = new Map();
		for(var j=0; j<colSize; j++){
			myMap[_grid.getColumnId(j)] = getValueById(_grid, deleteRows[i], _grid.getColumnId(j));
		}
		checkArray[i] = myMap;
	}
	
	var jsonStr = JSON.stringify(checkArray);
	return jsonStr;
}

function getSelectedRows(_grid, _chkCol, _keyCol, _keyCol2, _keyCol3)
{	
	var deleteRows = _grid.getCheckedRows(typeof _chkCol == "number"?_chkCol:getIndexById(_grid, _chkCol)).split(",");
    if(deleteRows == "" && !_grid.isItemExists('')) return;

	  //var delArray = new Array();
	  var delRows= "";
	  for(var i = 0; deleteRows.length > i; i++)
	  {
		  if(typeof _keyCol2 == "undefined")
		  {
			  var paramQ = "&deleteRows=";
			  var keyVal 	= typeof _keyCol == "number"?_grid.cells(deleteRows[i], _keyCol).getValue():getValueById(_grid, deleteRows[i], _keyCol);
			  
			  delRows = delRows + paramQ + keyVal;
		  }
		  else
		  {
			  var paramQ = "&deleteRows=";
			  var paramExt = "&deleteRowsExt=";
			  
			  var keyVal 	= typeof _keyCol == "number"?_grid.cells(deleteRows[i], _keyCol).getValue():getValueById(_grid, deleteRows[i], _keyCol);
			  var extKeyVal = typeof _keyCol2 == "number"?_grid.cells(deleteRows[i], _keyCol2).getValue():getValueById(_grid, deleteRows[i], _keyCol2);
			  
			  delRows = delRows + paramQ + keyVal + paramExt + extKeyVal;
		  }
		  
		  if(typeof _keyCol3 != "undefined")
		  {
			  var paramExt = "&deleteRowsExt1=";
			  
			  var extKeyVal = typeof _keyCol3 == "number"?_grid.cells(deleteRows[i], _keyCol3).getValue():getValueById(_grid, deleteRows[i], _keyCol3);
			  
			  delRows = delRows + paramExt + extKeyVal;  
		  }
		  
		  //Row 삭제 화면에서 
		  //_grid.deleteRow(deleteRows[i]);
	  }
	  
/*	  if(_grid.isItemExists(''))
	  {
		 alert( _grid.cells('', _chkCol).getValue());
	  }
	    */

	  return delRows;
}

function getSelectRows(_grid, _chkCol, _keyCol)
{	
	var selectRows = _grid.getCheckedRows(typeof _chkCol == "number"?_chkCol:getIndexById(_grid, _chkCol)).split(",");
    if(selectRows == "" && !_grid.isItemExists('')) return;

	  var selRows= "";
	  for(var i = 0; selectRows.length > i; i++)
	  {
		var paramQ = "&selectRows=";
		var keyVal 	= typeof _keyCol == "number"?_grid.cells(selectRows[i], _keyCol).getValue():getValueById(_grid, selectRows[i], _keyCol);
		
		if(keyVal != "")
			selRows = selRows + paramQ + keyVal;
	  }
	  return selRows;
}
//baseForm의 selectRows2를 사용
function getSelectRows1(_grid, _chkCol, _keyCol)
{	
	var selectRows = _grid.getCheckedRows(typeof _chkCol == "number"?_chkCol:getIndexById(_grid, _chkCol)).split(",");
    if(selectRows == "" && !_grid.isItemExists('')) return;

	  var selRows= "";
	  for(var i = 0; selectRows.length > i; i++)
	  {
		var paramQ = "&selectRows1=";
		var keyVal 	= typeof _keyCol == "number"?_grid.cells(selectRows[i], _keyCol).getValue():getValueById(_grid, selectRows[i], _keyCol);
		
		if(keyVal != "")
			selRows = selRows + paramQ + keyVal;
	  }
	  return selRows;
}

/**
 * Tree에서 선택된 check box의 값을 Parameter로 반환. 
 */
function getCheckRows(_tree)
{
    var checkRows = _tree.getAllChecked().split(",");
    //var partitialCheck = _tree.getAllPartiallyChecked().split(",");

    //_tree.getAllCheckedBranches()); //체크된 상위 메뉴 포함 .

    //if(checkRows == "" && partitialCheck == "") return;
    if(checkRows == "") return;

	 var chkRows= "";
	 for(var i = 0; checkRows.length > i; i++)
	 {
		 	if(checkRows[i] == "") continue;
	         var paramQ = "&checkRows="+checkRows[i];
	         
	         chkRows = chkRows + paramQ;
	 }
	 
	// for(var j = 0; partitialCheck.length >j; j++)
	// {
	//	 	 if(partitialCheck[j] == "" ) continue;
	//	 	 
	//         var paramQ = "&checkRows="+partitialCheck[j];
	//         
	//         chkRows = chkRows + paramQ;
	// }
	 
	 return chkRows;
}

function trim(value)
{
	value = value.replace(/\s+/, "");//왼쪽 공백제거
	value = value.replace(/\s+$/g, "");//오른쪽 공백제거
	value = value.replace(/\n/g, "");//행바꿈제거
	value = value.replace(/\r/g, "");//엔터제거
	return value;
}

/**
 * 
 * @param _grid
 * @param _param {"URL":"maEqMstrList","CHEIGHT":"75","CALLBACK":"goSearch","ISEDITABLE":"Y"}
 * @returns
 */
function setHeader()
{
	if(arguments.length == 2)
	{
		if(typeof arguments[1] == "string") setHeaderAction(arguments[0],arguments[1]);
		else 
		{
			var _url = currentPageId;
			var _gridId = "gridbox";
			var _callBack = "goSearch";
			var _height = undefined;
			var _editable = undefined;
			var _gridType = undefined;
			
			var paramObj = arguments[1];
			for( var key in paramObj ) {
				 if(key.toUpperCase() == "URL") _url = paramObj[key];
				 else if(key.toUpperCase() == "CALLBACK") _callBack = paramObj[key];
				 else if(key.toUpperCase() == "GRIDID") _gridId = paramObj[key];
				 else if(key.toUpperCase() == "HEIGHT") _height = paramObj[key];
				 else if(key.toUpperCase() == "EDITABLE") _editable = paramObj[key];
				 else if(key.toUpperCase() == "GRIDTYPE") _gridType = paramObj[key];
			}
			
			setHeaderAction(arguments[0],_gridId, _callBack, _url, _height, _editable, _gridType);

		}
	}
	else if(arguments.length == 3)
	{
		setHeaderAction(arguments[0],arguments[1],arguments[2]);
	}
	else if(arguments.length == 4)
	{
		setHeaderAction(arguments[0],arguments[1],arguments[2],arguments[3]);
	}
	else if(arguments.length == 5)
	{
		setHeaderAction(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]);
	}
	else if(arguments.length == 6)
	{
		setHeaderAction(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5]);
	}
	
}


function setHeaderAction(_grid, _gridId, _callBack, _url, _noHeight, _editable, _gridType)
{
	var actionUrl = currentPageId;
	if(!(typeof _url =="undefined" || _url == "")) actionUrl = _url;  
	var url = contextPath + "/"+actionUrl+".do";
	var _newParam = "strutsAction="+'9010&listId='+_gridId+"&currentPageId="+currentPageId;
	if(_editable == "Y") {
		_newParam = _newParam+"&editableGrid=Y";
	}
	
	if(typeof _gridType !="undefined") _newParam = _newParam + "&gridType="+_gridType;

	//Grid Disable
	_grid.enableEditEvents(false,false,false);
	//_grid.setEditable(false);
	
	$.post(url,_newParam, function(_data){

		var jsonObj = JSON.parse(_data);  
		//console.log(jsonObj.header);

		isHeaderLoaded[currentPageId+"."+_gridId] = "Y";

		//console.log("jsonObj.height:"+jsonObj.height);
		//Height 설정
		if(jsonObj.height != "30" && typeof _noHeight == "undefined")  //높이 세팅 0일때 최소 높이 30으로 세팅됨.
		{
			$('#'+_gridId).css("height",jsonObj.height);
			_gridHeightArray[currentPageId+"."+_gridId] = parseInt(jsonObj.height);
			_grid.setSizes();
		}	
		else if(typeof _noHeight != "undefined")
		{
			$('#'+_gridId).css("height",_noHeight);
			_gridHeightArray[currentPageId+"."+_gridId] = _noHeight;
			_grid.setSizes();
		}
		else
		{
			_gridHeightArray[currentPageId+"."+_gridId] = $('#'+_gridId).css("height").replace("px","");
		}

		
		try{_grid.parse(jsonObj.header,"js");}catch(e){}finally{
		//console.log(_data+"  "+typeof _callBack+"    "+_callBack);
			if(!(typeof _callBack == "undefined" || _callBack =="")) 
			{
				if(DECORATOR_NAME != "innerTabPage") isLoading = false;
				$.globalEval(_callBack+"('"+_gridId+"','"+jsonObj.header+"');");
				
			}
			else if( _callBack == "") 
			{
				//if _blank parameter for callback, do nothing.
			}
			else
			{
				if(typeof goSearch == "function") goSearch();
			}
			
			if(typeof checkActionCall == "function")  checkActionCall();
				
		}	

		_grid.attachEvent("onBeforeSelect",function(new_row,old_row,new_col_index){
			if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
			
			return true;
		}); 
		
		if(typeof resizeTabFrame == "function") resizeTabFrame();

    });
}

function getDecoName()
{
	return DECORATOR_NAME;
}

var listState= 0;
function tabValidationCheck(_grid, pageId, _callback)
{			
	var topPage = getTopPage();
	var selectedId=_grid.getSelectedRowId();
    if(typeof selectedId == "undefined" || selectedId == null) return;
    
/*    _grid.attachEvent("onBeforeSelect", function(new_row,old_row){
    	if(topPage.listState) return false;
    	else return true;
	}); */

//    setTimeout(function(){topPage.listState = false;},2000);
//    topPage.listState = true;
    
	if(checkIsUpdate(document)){  //수정중?
		topPage.dhtmlx.confirm(COMMON_CMSG010, function(result){
			if(result)
			{				
				beforeGoTabPage(_grid, pageId);
				
				$.globalEval(_callback+"('"+pageId+"','"+selectedId+"');");
			}
			
		});
	}
	else
	{
		if(ckCreate(pageId))
		{	
			_grid.deleteRow("");
		}
		$.globalEval(_callback+"('"+pageId+"','"+selectedId+"');");
	}
}

/*
 * when there is something modified, Clear all
 */
function beforeGoTabPage(_grid, pageId)
{
	//Close 하는 Page와 내부 Page ID를 TEMP Array에 저장.
	getSubPageId("", $(document));

	//Close하는 Page와 내부 Page의 ID를 UpdateArray에서 제거.
	//clearUpdateArray();

	//CREATE면 그냥 닫고
	if(ckCreate(pageId))
	{	
		_grid.deleteRow("");
	}
	_result = false;
	getTopPage()._result = false;
}

/*
 * Create Validation
 */
function createValidationCheck(_grid, pageId, _callback)
{    
	if(checkIsUpdate(document)){  //수정중?
		getTopPage().dhtmlx.confirm(COMMON_CMSG010, function(result){
			if(result)
			{
				beforeGoCreate(_grid, pageId);
			    $.globalEval(_callback+"('"+pageId+"');");
			}
			
		});
	}
	else
	{
		//이미 신규생성 페이지가 열려 있다면... confirm
	    /*if(!ckCreate(pageId, this))
	    {
	    	//_grid.deleteRow("");
	    	_grid.addRow("","",0);
	    }*/
	    $.globalEval(_callback+"('"+pageId+"');");
	}
}

function beforeGoCreate(_grid, pageId)
{
	//Close 하는 Page와 내부 Page ID를 TEMP Array에 저장.
	getSubPageId(pageId, $(document));

	//Close하는 Page와 내부 Page의 ID를 UpdateArray에서 제거.
	//clearUpdateArray();
	
	//이미 신규생성 페이지가 열려 있다면... confirm
    /*if(!ckCreate(pageId))
    {
    	_grid.addRow("","",0);
    }*/
    
	_result = false;
	getTopPage()._result = false;
}

/**
 * 비밀번호 변경 팝업
 */
function changePasswordLayer(userId,userNo,userName){
    var url = contextPath + "/maChangePw.do";

    var popWidth = 910;
    var popHeight = 680;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=no, status=no";
    var strutsVal= "1001";
    var param = "strutsAction="+strutsVal+"&maChangePwDTO.userId="+userId+"&maChangePwDTO.userName="+userName+"&maChangePwDTO.userNo="+userNo;
    openLayerPopup("maChangePw", param);

}

function checkValidation()
{
	/*var toReturn;
	$('.check').each(function(){
		
		var labelMsg = $(this).text();
		$(this).next().find("input,textarea").each(function(){
			if($(this).val() == ""){
				alertMessage1(labelMsg + " " + COMMON_CMSG047);
				$(this).focus();
				toReturn = true;
				closeModal();
				
				return false;
			}
		});
	});*/
	
	var toReturn;
	var rsltValue ="";
	var labelMsg = "";
	$('.check').each(function(){
		labelMsg = $(this).text();
		$(this).next().find("input,textarea").each(function(){
			if($(this).val() == ""){
				if("" != rsltValue)
					rsltValue += ", "+ labelMsg;
				else{
					firstField = $(this);
					rsltValue += labelMsg;
				} 
			}
			//return false;
		});
	});
	
	if("" != rsltValue){
		alertMessage3(rsltValue + COMMON_CMSG047, "chkEssentialField");
		
		toReturn = true;
		closeModal();
	}
	
	return toReturn;
}


function chkEssentialField()
{
	//check mandatory field and focus on it.
	var breakout;
	$('.check').each(function(){
		$(this).next().find("input,textarea").each(function(){
			if($(this).val() == ""){
				$(this).focus();
				return breakout = false;
			}
		});
		return breakout;
	});
}

function checkConfirmValidation()
{
	var toReturn;
	$('.lastcheck').each(function(){
		
		var labelMsg = $(this).text();
		$(this).next().find("input").each(function(){
			if($(this).val() == ""){
				alertMessage1(labelMsg + " " + COMMON_CMSG047);
				$(this).focus();
				toReturn = true;
				closeModal();
				
				return false;
			}
		});

	});
	
	return toReturn;
}

function setReadOnly(objName)
{
	if(typeof objName == "undefined")
	{	
		$('.read').prop("readonly","true").parent().removeClass('input_box').addClass('input_read');
	
		$('.read').parent().find('a').hide();
	}
	else
	{
		$('[name="'+objName+'"]').prop("readonly","true").datepicker("destroy").parent().removeClass('input_box').addClass('input_read');
		$('[name="'+objName+'"]').parent().find('a').hide();
	}
}

function setReadable(objName)
{
	$('[name="'+objName+'"]').prop("readonly",false).parent().removeClass('input_read').addClass('input_box');
	$('[name="'+objName+'"]').parent().find('a').show();

}

var readOnlyArr = new Array();
function setDisable(_contents, _noSave) //_noSave :true 일때는 세이브 안되고 평소엔 된다.
{
	//console.log("호출 setDisable:"+currentPageId);
	if(typeof _contents == "undefined") _contents = document;
	if(typeof _noSave == "undefined") _noSave = false;
	
	var topDoc = getTopPage();

	//Read Only 값 저장
	if(readOnlyArr.length == 0)
		$(_contents).find('.input_read').map(function(){
			readOnlyArr.push($(this).find('input,textarea').prop("name"));
		});

	$(_contents).find('.input_box').removeClass('input_box').addClass('input_read').find('input,textarea').attr("readonly",true);//.datepicker("option", "disabled", true );
	$(_contents).find('.hasDatepicker').attr("disabled", false);
	$(_contents).find('.input_read').find('a').hide();   //Hide LOV 
	$(_contents).find('.input_read').find('.open_calendar').hide(); //Hide Calendar
	$(_contents).find('.input_read').find('.open_mon_calendar').hide(); //Hide Calendar
	$(_contents).find('.input_read').find('.open_year_calendar').hide(); //Hide Calendar
	$(_contents).find('.input_read').find('.open_time').hide();

	var funcBox = $(_contents).find('.function_box');
	if(DECORATOR_NAME == "tabPage" && parent.$('#'+currentPageId+'_title').length == 1)
	{
		fncBox = parent.$('#'+currentPageId+'_title').find('.function_box');
	}
	
	//buttion Hide
	funcBox.find('a:not(.fShow):not(.b_linked)').each(function(){
		
		if(!($(this).hasClass("b_close")|| $(this).hasClass("b_pdf")))  //Close Button 제외
			$(this).hide();
	
	});
	
	if(DECORATOR_NAME == "innerTabPage" && parent.$('#'+currentPageId+'_tabList').length == 1)
	{
		var tablistObj = parent.$('#'+currentPageId+'_tabList');
		var tabHeaderObj = tablistObj.find(".tab_header");
		
		tabHeaderObj.find('a:not(.fShow):not(.b_linked)').hide();
		tabHeaderObj.find('.b_excel').show();
	};
	
	//$(_contents).find('.function_box').find('.b_pdf').show();
	//$(_contents).find('.function_box').find('.b_close').show();
	if(_noSave) disabled= true;
	
	if(typeof afterDisable == "function") afterDisable();

}

function setDisableAll(_contents)
{
	//console.log("호출 setDisableAll:"+currentPageId);
	if(typeof _contents == "undefined") _contents = document;
	
	if(typeof setDisable == "function") setDisable(_contents);

	disableAll = true;
	
	$(_contents).find("iframe[name^='tabFrame']").each(function(e){

		if(typeof $(this).get(0).contentWindow.currentPageId == "undefined") return;
		
		$(this).get(0).contentWindow.disableAll = true;
		
		//console.log("iframe:"+$(this).get(0).contentWindow.currentPageId);

		if(typeof $(this).get(0).contentWindow.setDisable == "function")
			$(this).get(0).contentWindow.setDisable();
		
		setDisableAll($(this).contents());
	});
}


function setEnable(_contents)
{
	if(typeof _contents == "undefined") _contents = document;
	
	//Disable되지 않은 항목이 하나라도 있으면 중단.
	//if($(_contents).find('.input_box').length > 0) return;
	var topDoc = getTopPage();

	//Read Only 값 저장
	if(readOnlyArr.length == 0)
		$(_contents).find('.input_read').map(function(){
			readOnlyArr.push($(this).find('input, textarea').prop("name"));
		});
	
	$(_contents).find('.input_read').each(function(e){
		
		var locObj = $(this);
		var flag = false;
		$.map(readOnlyArr, function(val, i){
			//처음에 ReadOnly였던 항목은 빼고 나머지만 Enable시킨다.
			if(locObj.find('input,textarea').prop("name") == val)
			{
				flag = true;
			}
		});
		
		if(!flag)
		{
			$(this).removeClass('input_read').addClass('input_box').find('input,textarea').attr("readonly",false);
			$(this).find('a').show();   //Hide LOV 
			$(this).find('.open_calendar').show(); //Hide Calendar
			$(this).find('.open_time').show();
			$(this).find('.open_calendar').prev().datepicker("option", "disabled", false );
		}
		
	});
	
	var funcBox = $(_contents).find('.function_box');
	if(DECORATOR_NAME == "tabPage" && parent.$('#'+currentPageId+'_title').length == 1)
	{
		fncBox = parent.$('#'+currentPageId+'_title').find('.function_box');
	}
	
	//buttion Show
	funcBox.find('a:not(.b_linked):not(.fHide)').each(function(){
		$(this).show();		
	});
	
	if(DECORATOR_NAME == "innerTabPage" && parent.$('#'+currentPageId+'_tabList').length == 1)
	{
		var tablistObj = parent.$('#'+currentPageId+'_tabList');
		var tabHeaderObj = tablistObj.find(".tab_header");
		
		tabHeaderObj.find('a:not(.b_linked):not(.fHide)').show();
	};
	
	disabled= false;
	
	if(typeof afterEnable == "function") afterEnable();
	
	//파일 업로드 컨트롤
	//if (myVault != null) myVault.enable();
}

function setEnableAll(_contents)
{
	if(typeof _contents == "undefined") _contents = document;
	
	if(typeof setEnable == "function") setEnable(_contents);

	disableAll = false;

	
	$(_contents).find("iframe[name^='tabFrame']").each(function(e){
	
		if(typeof $(this).get(0).contentWindow.currentPageId == "undefined") return;
		
		$(this).get(0).contentWindow.disableAll = false;

		if(typeof $(this).get(0).contentWindow.setEnable == "function")
			$(this).get(0).contentWindow.setEnable();
		
		setEnableAll($(this).contents());

	});
}

/*
 * Hide Button (Must Call in 'afterBtnLoad' Function)
 * ex) hideBtn('create');
 */
function hideBtn(_buttonId)
{
	var fncBox = $('.function_box');
	if(DECORATOR_NAME == "tabPage" && parent.$('#'+currentPageId+'_title').length == 1)
	{
		fncBox = parent.$('#'+currentPageId+'_title').find('.function_box');
	}
	
	var btnClass = "b_"+_buttonId.toLowerCase()
	//buttion Hide
	fncBox.find('a').each(function(){
		//console.log($(this).prop("class"));
		if($(this).hasClass(btnClass))
		{
			if($(this).hasClass("fShow")) $(this).removeClass("fShow");
			
			$(this).addClass("fHide").hide();
		}
	});
	
	if(DECORATOR_NAME == "innerTabPage" && parent.$('#'+currentPageId+'_tabList').length == 1)
	{
		var tablistObj = parent.$('#'+currentPageId+'_tabList');
		var tabHeaderObj = tablistObj.find(".tab_header");
		
		tabHeaderObj.find('a').each(function(e){
			if($(this).hasClass(btnClass))
			{
				if($(this).hasClass("fShow")) $(this).removeClass("fShow");
				
				$(this).addClass("fHide").hide();
			}
		});
		
	};
}

/*
 * Show Button (Must Call in 'afterBtnLoad' Function)
 * ex) showBtn('create');
 */
function showBtn(_buttonId)
{
	var fncBox = $('.function_box');
	if(DECORATOR_NAME == "tabPage" && parent.$('#'+currentPageId+'_title').length == 1)
	{
		fncBox = parent.$('#'+currentPageId+'_title').find('.function_box');
	}
	
	var btnClass = "b_"+_buttonId.toLowerCase()
	//buttion Hide
	fncBox.find('a').each(function(){
		if($(this).hasClass(btnClass))
		{
			if($(this).hasClass("fHide")) $(this).removeClass("fHide");
			
			$(this).addClass("fShow").show();
		}
	});
	
	if(DECORATOR_NAME == "innerTabPage" && parent.$('#'+currentPageId+'_tabList').length == 1)
	{
		var tablistObj = parent.$('#'+currentPageId+'_tabList');
		var tabHeaderObj = tablistObj.find(".tab_header");
		
		tabHeaderObj.find('a').each(function(e){
			if($(this).hasClass(btnClass))
			{
				if($(this).hasClass("fHide")) $(this).removeClass("fHide");
				
				$(this).addClass("fShow").show();
			}
		});
		
	};
}
/**
 * input field를 넘기면 둘러싸고 있는 field를 보여준다.
 * @param fieldName
 */
function showField(fieldName){
	var inputObj = $("[name='"+fieldName+"']");

	if(inputObj.size() >= 1)
    {
		var fieldObj = inputObj.parents('.field,.field_long,.field_img,.field_img_double');
		var fieldLabelObj = fieldObj.find('label');
		
		$.map( _gCheckArray, function( value, key ) {
			if(key == fieldName)  fieldLabelObj.addClass(value);  
		});
		
		fieldObj.show();
    }
}
/**
 * input field를 넘기면 둘러싸고 있는 field를 숨긴다.
 * @param fieldName
 */
function hideField(fieldName){
	var inputObj = $("[name='"+fieldName+"']");
	if(inputObj.size() >= 1)
    {
		var fieldObj = inputObj.parents('.field,.field_long,.field_img,.field_img_double');
		var fieldLabelObj = fieldObj.find('label');
		var labelClass = "";

		if(fieldLabelObj.hasClass("check")) labelClass = "check";
		else if(fieldLabelObj.hasClass("lastcheck")) labelClass = "lastcheck";
		
		fieldLabelObj.removeClass(labelClass);
		
		_gCheckArray[fieldName] = labelClass; 
		
		fieldObj.hide();
    }
}

function setLoading(gridId)
{
	var footer = $('#'+gridId).next();
	if(footer.is('.grid_footer'))
  	{
		footer.html("");
		footer.append("<img align='absbottom' src='"+contextPath+"/common/images/ma/loading.gif'></img>"+COMMON_CMSG001+"..." );
  	}
}

/**
 * set Grid total Count 
 * @param grdObj
 * @param gridId
 */
function setCounter(grdObj, gridId, _custom)
{		
	//grdObj.setAwaitedRowHeight(30); //Important For Smart Rendering ..to apply custom Css, it must be changed.
	//var _rowH = $('#'+gridId).find('.objbox').find('td:visible').eq(0).outerHeight(true);
	var _rowH = $('#'+gridId).find('.objbox').find('td:visible').eq(0).outerHeight(true);

	//높이설정 매우 매우 중요함 
	//console.log(currentPageId+"  "+"_rowH"+_rowH);
	
	grdObj.setAwaitedRowHeight(_rowH);

	var totCnt = $('#'+gridId).val();
	
	if((totCnt < grdObj.getRowsNum() && totCnt == 0) || totCnt == "") totCnt = grdObj.getRowsNum();

	//달력 시간 숨기기
	grdObj.attachEvent("onCalendarShow", function(myCal,rId,colInd){
		myCal.hideTime();
	});
	
	if(!$('#'+gridId).next().is('.grid_footer'))
	{	
		$('#'+gridId).parent().append("<div class='grid_footer'>Total : <strong>"+setNumberFormat(grdObj.getRowsNum())+"</strong></div>");
		
		for(var i = 0; grdObj.getColumnsNum() > i; i++)
  		{
  			//console.log(i+"    "+grdObj.getColType(i));
  			if(grdObj.getColType(i) == "ron" || grdObj.getColType(i) == "edn") grdObj.setNumberFormat("0,000",i,".",",");
  			else if(grdObj.getColType(i) == "dhxCalendar")
  			{
  				grdObj.setDateFormat("%Y-%m-%d","%Y%m%d");
  			}
  		}
	}

	if($('#'+gridId).next().is('.grid_footer'))
  	{
  		$('#'+gridId).next().html("");
  		$('#'+gridId).next().append("Total : <strong>"+setNumberFormat(grdObj.getRowsNum())+"/"+setNumberFormat(totCnt)+"</strong>");
  		
  		//Title Sublist Count
  		if(parent.$('#'+currentPageId+'_tabList > .ac_menu'))
  		{
  			var titleObj = parent.$('#'+currentPageId+'_tabList > .ac_menu');
  			var titleHtml = titleObj.html();

  			if(titleHtml && titleHtml.indexOf(" (") != "-1") titleHtml = titleHtml.substring(0,titleHtml.indexOf(" ("));

  			titleObj.html(titleHtml+" ("+setNumberFormat(totCnt)+")");
  		}
  		
  		var msgTop = 0;
  		var offset;
  		var _curHeight = _gridHeightArray[currentPageId+"."+gridId];
  		var _rowHeight = (grdObj.getRowsNum() + 1)*30 + 30;
  		
  		if(typeof _curHeight == "undefined") return;
  		
  		//console.log(currentPageId+"   디비:"+_curHeight+"   조회된길이"+_rowHeight);
  		
  		if(DECORATOR_NAME == "innerTabPage" && _curHeight >= _rowHeight && !_custom)
  		{  			
  			//console.log("gridHeight:"+gridHeight);
  			$('#'+gridId).css("height",_rowHeight+"px");
  			grdObj.setSizes();

  			if(typeof resizeTabFrame == "function") resizeTabFrame(20);
  		}
  		else if(DECORATOR_NAME == "innerTabPage" && grdObj.getRowsNum() < 3 && _custom) //?
  		{
  			//console.log("gridHeight:"+gridHeight);
  			$('#'+gridId).css("height",_curHeight+"px");
  			grdObj.setSizes();

  			if(typeof resizeTabFrame == "function") resizeTabFrame(20);
  		}
  		else
  		{
  			$('#'+gridId).css("height",_curHeight+"px");
  			grdObj.setSizes();

  			if(typeof resizeTabFrame == "function") 
  				setTimeout(function() {
					resizeTabFrame();
				}, 150);
  		}
  	}
  	
  	/*var topPage = getTopPage();
  	
  	$.map(topPage.selectedId, function(inVal, inId){
		//console.log(inVal+"   "+inId);
		var rowId = getRowIdByValue(grdObj, inId, inVal); //key Value로 row Id 조회
		grdObj.selectRow(rowId-1);
	});
  	
  	topPage.selectedId = {};*/
  	
}

function goDeleteAll()
{
	getTopPage().dhtmlx.confirm(COMMON_CMSG050, function(result){

		if(result)
		{
			goDelete();
		}
		
	});
}

/**
 * Change Grid Column Type 
 * @param _grid
 * @param _colId
 * @param _colType
 */
function setColumnType(_grid, _colId, _colType)
{
	var colIdx = getCoumnIdx(_grid, _colId);
	_grid.setColumnExcellType(colIdx,_colType);
    
	if(_colType == "ednum")
		_grid.setNumberFormat("0,000",colIdx,".",",");
}

/**
 * Add Row in Grid
 * @param _grid
 * @returns
 */
function addRow(_grid)
{
	var date = new Date();  
	var rId = date.getTime();
	_grid.addRow(rId,"");
    
	_grid.selectRowById(rId,true,true,true);
	
	if(typeof afterAddRow == "function") afterAddRow(rId);
	
	return rId;
}

/**
 * Delete Row in Grid
 * @param _grid
 * @returns
 */
function delRow(_grid)
{
	_grid.deleteSelectedItem();
	var rId = _grid.getSelectedRowId();
	
	if(typeof afterDelRow == "function") afterDelRow(rId);
	
	return rId;
}

var _evId;
var _onRowMark;
/**
 * Enable Edit Mode
 * @param _grid
 */
function editRow(_grid)
{
	alertMessage1("수정모드입니다.");

	_grid.enableValidation(true); 
	_grid.enableMarkedCells(true); 
	_grid.clearSelection();
	
	_grid.enableEditEvents(true,false,false);

	_evId = _grid.attachEvent("onCellMarked",function(rowId, columnId){
		if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
		//_grid.mark(rowId,columnId,true);
	}); 
	
	
}

/**
 * Cancel Edit mode
 * @param myGrid
 */
function editcnclRow(_grid)
{
	alertMessage1("수정을 취소하였습니다.");	
	
	_grid.enableEditEvents(false,false,false);
	
	afterEditRow(_grid);
}

function afterEditRow(_grid)
{
	_grid.detachEvent(_evId); //de
	myDataProcessor.detachEvent(_onRowMark); 
	
	//그리드 수정불가로 전환
	_grid.enableEditEvents(false,false,false);
	//_grid.setEditable(false);
		
	_grid.enableMarkedCells(false); 
	
	topDoc.afterSaveAll(currentPageId);
}

/**
 * Set User Defined Properties
 * @param _page
 */
function goColsetting(_page)
{
	var param = "persPrivUsrFieldCommonDTO.pageId="+_page+
		        "&" + "isDecoratorName=popupPage" +
		        "&" + "popupWidth=810";

	openLayerPopup("persPrivUsrFieldList", param);
}

/**
 * Set User Defined Columns
 * @param pageId
 * @returns
 */
function goSetting(pageId, gridId)
{
	 var param = "maGrdUsrCommonDTO.pageId="+pageId+
	 			 "&maGrdUsrCommonDTO.gridObjId="+gridId+
	 			"&" + "isDecoratorName=popupPage";
	 
	openLayerPopup("maGrdUsrDetail", param);
}

/**
 * Online Help
 * @param
 * @returns
 */
function goHelp()
{
    var url   = contextPath + "/appOnlinehelpDetail.do";
    var popWidth = 1010;
    var popHeight = 740;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "&strutsAction="+
                "&appOnlinehelpCommonDTO.pageId="+currentPageId;
	//post 전송
	openWindowWithPost(url, "ONLINE_HELP_DETAIL_POPUP", param, pos);
}

function setData(keyCol, keyVal)
{
	//Detail의 자료를 모은다.
	var rObj = {};
    $('input, textarea').map(function(){
    	rObj[this.name] = this.value;
    	//console.log(rObj);
    });
    
    getTopPage().selectedId[keyCol] = keyVal;
    
    if(typeof parent.setDataAction == "function" ) parent.setDataAction(rObj, keyVal);
    else parent.goSearch();
}

/**
 * Column Index 
 * @param _grid
 * @param _colId
 * @returns {Number}
 */
function getCoumnIdx(_grid, _colId)
{
	var rtnIdx;
	for(var i = 0; _grid.getColumnsNum() > i; i++)
	{
		if(_colId.toUpperCase()  == _grid.getColumnId(i).toUpperCase())
		{
			rtnIdx = i;
		} 
	}
	return rtnIdx;
}

/**
 * Set Values From Detail Page Save
 * @param _grid
 * @param keyCol
 * @param keyVal
 * @param valueArray
 */
function setValues(_grid, keyCol, keyVal, keyValMap, arrayData)
{
	var rowId = getRowIdByValue(_grid, keyCol, keyVal); //key Value로 row Id 조회
	var rowsNum =  _grid.getRowsNum();
	
	var addArray = []; //For Create (Added Row)
	for(var i = 0; _grid.getColumnsNum() > i; i++)
	{
		$.map(keyValMap, function(inVal, inId){
			//Column ID 세팅할 값이 매치된다면 세팅
			if(myGrid.getColumnId(i) == inId.toUpperCase())
			{
				$.map(arrayData, function(value, id){
					//console.log("Detail ID:"+id+"  map key:"+inVal);
					if(id == inVal){
						keyValMap[inId] = value;
						addArray[i] = value;
					}
				});
			}
		});
		
		if(!addArray[i]) addArray[i] = "";
			
		//console.log(keyValMap);
	}
	
	//Row가 새로 추가된 경우!
	if(typeof rowId == "undefined")
	{
		var rId = myGrid.uid();
		_grid.deleteRow("");
		_grid.addRow(rId,addArray, 0);
		//_grid.selectRow(rId, false, false, true);
		setValueById(_grid, rId, "SEQNO", rowsNum+1);

		return;
	}
	
	//아니면 수정!
	$.map(keyValMap, function(inVal, inId){
		for(var i = 0; myGrid.getColumnsNum() > i; i++)
		{
			if(inId.toUpperCase()  == _grid.getColumnId(i))
			{
				setValueById(_grid, rowId, inId.toUpperCase(), inVal);
			}

		}
	});
	
	//_grid.selectRow(rowId, false, false, true);
}

/*
 * Unique한 값으로 Row를 검색
 */
function getRowIdByValue(_grid, _columnId, _value)
{
	for(var i = 0; _grid.getRowsNum() > i; i++)
	{ 
		var rowId = _grid.getRowId(i);
		if(typeof rowId == "undefined")continue;
		
		if(typeof _value  == "string")
		{
			if(getValueById(_grid, rowId, _columnId) == _value)
			{
				return rowId;
			}
		}
		else
		{
			var colLength = _columnId.length;
			var cnt= 0;
			for(var j = 0; colLength > j; j++)
			{
				if(getValueById(_grid, rowId, _columnId[j]) == _value[j])
				{
					//console.log("_columnId[j]:"+_columnId[j]+"  _value[j]"+ _value[j]);
					cnt++;
				}
			}
			
			if(cnt == colLength) return rowId;
		}
	}
}
/*
 * Unique한 값으로 Row를 검색
 */
function getRowIdsByValue(_grid, _columnId, _value)
{
	var rowIds = new Array(0);
	for(var i = 0; _grid.getRowsNum() > i; i++)
	{ 
		var rowId = _grid.getRowId(i);
		
		if(typeof rowId == "undefined")continue;
		
		if(typeof _value  == "string")
		{
			if(getValueById(_grid, rowId, _columnId) == _value)
			{
				rowIds.push(rowId);
			}
		}
		else
		{
			var colLength = _columnId.length;
			var cnt= 0;
			for(var j = 0; colLength > j; j++)
			{
				if(getValueById(_grid, rowId, _columnId[j]) == _value[j])
				{
					//console.log("_columnId[j]:"+_columnId[j]+"  _value[j]"+ _value[j]);
					cnt++;
				}
			}
			
			if(cnt == colLength) rowIds.push(rowId);
		}
	}
	return rowIds;
}

function setValueById(_grid, selectedId, columnId, value)
{
	if(typeof columnId =="number"){
		var colIdx = getIndexById(_grid, columnId);
		_grid.cells(selectedId, colIdx).setValue(value);
	}
	else
	{
		for(var i = 0; _grid.getColumnsNum() > i; i ++)
		{
			if(columnId.toUpperCase()  == _grid.getColumnId(i))
			{
				_grid.cells(selectedId, i).setValue(value);
				break;
			} 
		}
	}
}

function getValueById(_grid, selectedId, columnId)
{
	//console.log("getValueById:"+columnId);
	if(typeof columnId =="number")
		return _grid.cells(selectedId,columnId).getValue();
	else
		for(var i = 0; _grid.getColumnsNum() > i; i ++)
		{
			//console.log(columnId.toUpperCase() +"   "+_grid.getColumnId(i).toUpperCase());
			if(columnId.toUpperCase()  == _grid.getColumnId(i).toUpperCase())
			{
				return htmlEnDeCode.htmlDecode(_grid.cells(selectedId, i).getValue());
				break;
			} 
		}
}

function getCellById(_grid, selectedId, columnId)
{
	//console.log("getValueById:"+columnId);
	if(typeof columnId =="number")
		return _grid.cells(selectedId,columnId);
	else
		for(var i = 0; _grid.getColumnsNum() > i; i ++)
		{
			if(columnId.toUpperCase()  == _grid.getColumnId(i))
			{
				return _grid.cells(selectedId, i);
				break;
			} 
		}
}

function getIndexById(_grid, columnId)
{
	for(var i = 0; _grid.getColumnsNum() > i; i ++)
	{
		if(columnId.toUpperCase()  == _grid.getColumnId(i))
		{
			return i;
			break;
		} 
	}
}

function setRowColors(_grid, _columnId, _value, _color)
{
	if(typeof _color == "undefined") _color = "#949494";
	for(var i = 0; _grid.getRowsNum() > i; i++)
	{ 
		var rowValue = getValueById(_grid, _grid.getRowId(i), _columnId);
		if(rowValue == _value)
		{
			_grid.setRowColor(_grid.getRowId(i),_color);
		}
	}
}

function setRowSpan(_grid, columnId)
{
	var preValue = "";
	var sameCnt = 1;
	var fRowId= "";

	for(var i = 0; _grid.getRowsNum() > i; i++)
	{ 
		if(preValue == "")
		{
			preValue = getValueById(_grid, _grid.getRowId(i), columnId);
			if(sameCnt == 1) fRowId = _grid.getRowId(i);
		}
		else 
		{
			if(preValue == getValueById(_grid, _grid.getRowId(i), columnId))
			{
				sameCnt++;
			}
			else
			{
				if(sameCnt != 1)
				{
					_grid.setRowspan(fRowId ,getIndexById(_grid, columnId),sameCnt);
				}

				sameCnt = 1;
				preValue = getValueById(_grid, _grid.getRowId(i), columnId);
				fRowId = _grid.getRowId(i);
			}
		}

		if(i == _grid.getRowsNum()-1 && sameCnt != 1) _grid.setRowspan(fRowId ,getIndexById(_grid, columnId),sameCnt);
	}
}

function setRowSpanStd(_grid, stdColumnId, subColumnIdArr)
{	
	//alert(subColumnIdArr[0]);
	
	var preValue = "";
	var sameCnt = 1;
	var fRowId= "";

	for(var i = 0; _grid.getRowsNum() > i; i++)
	{ 
		if(preValue == "")
		{
			preValue = getValueById(_grid, _grid.getRowId(i), stdColumnId);
			if(sameCnt == 1) fRowId = _grid.getRowId(i);
		}
		else 
		{
			if(preValue == getValueById(_grid, _grid.getRowId(i), stdColumnId))
			{
				sameCnt++;
			}
			else
			{
				if(sameCnt != 1)
				{
					if(typeof subColumnIdArr != "string")
						for(var k=0; subColumnIdArr.length > k; k++) 
						{
							_grid.setRowspan(fRowId ,getIndexById(_grid, subColumnIdArr[k]),sameCnt);
						}
					else
						_grid.setRowspan(fRowId ,getIndexById(_grid, subColumnIdArr),sameCnt);
					
				}

				sameCnt = 1;
				preValue = getValueById(_grid, _grid.getRowId(i), stdColumnId);
				fRowId = _grid.getRowId(i);
			}
		}

		if(i == _grid.getRowsNum()-1 && sameCnt != 1)
		{
			if(typeof subColumnIdArr != "string")
				for(var j=0; subColumnIdArr.length > j; j++) 
				{
					_grid.setRowspan(fRowId ,getIndexById(_grid, subColumnIdArr[j]),sameCnt);
				}
			else
				_grid.setRowspan(fRowId ,getIndexById(_grid, subColumnIdArr),sameCnt);
		}
	}
}

/**
 * val문자열을 days수만큼 연속해서 반환
 */
function getWords(days,val){
	var str = "";
	for(var i=days; i>=0; i--){
		str +=","+val;
	}
	return str;
}
/**
 * fromDate와 toDate사이의 일자를 반환(format:,2016-02-14,2016-02-15)
 */
function getDays(fromDate,toDate,days){
	var str = "";
	var arr1 = new Array();
	arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = toDate.split('-');

	var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);

	for(var i=days; i>=0; i--){
		str +=","+getMinusDay2(dat1,i);
	}
	return str;
}

/**
 * (,2016-04,2016-05) 
 * @param fromDate
 * @param toDate
 * @returns {String}
 */
function getMonths(fromDate,toDate)
{
	var str = "";
	var arr1 = fromDate.split('-');
	var arr2 = toDate.split('-');

	fromDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = fromDate.split('-');
	toDate = arr2[0]+"-"+(arr2[1].substring(0,1)=="0"?arr2[1].substring(1,2):arr2[1])+"-"+(arr2[2].substring(0,1)=="0"?arr2[2].substring(1,2):arr2[2]);
	arr2 = toDate.split('-');

	var fromDat = new Date(arr1[0], arr1[1], arr1[2]);
	var toDat = new Date(arr2[0], arr2[1], arr2[2]);

	var monInterval = getMonthInterval(arr1[0]+arr1[1],arr2[0]+arr2[1]);
	var i = 0;
	
	str +=","+fromDat.getFullYear()+"-"+(fromDat.getMonth() < 10? "0"+fromDat.getMonth(): fromDat.getMonth());
	
	//alert(fromDat.getFullYear()+"   "+fromDat.getDate()+ "    "+fromDat.getMonth());
	
	while(i<monInterval)
	{
		str +=","+fromDat.getFullYear()+"-"+((fromDat.getMonth()+1) < 10? "0"+(fromDat.getMonth()+1): (fromDat.getMonth()+1));
		fromDat.setMonth(fromDat.getMonth() + 1);
		i++;
	}
	return str;
}

/**
 * fromDate와 toDate사이의 일자를 반환(format:,2/16(월),2/17(화))
 */
function getDays2(fromDate,toDate,days,lang){
	var str = "";
	var arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = toDate.split('-');

	var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
	for(var i=days; i>=0; i--){
		var dateArr = getMinusDay2(dat1,i).split("-");
		var dateStr = getMinusDay2(dat1,i).replace(/\-/gi, "");
		var month   = dateArr[1].substring(0,1)=="0"?dateArr[1].substring(1,2):dateArr[1];
		var day     = dateArr[2].substring(0,1)=="0"?dateArr[2].substring(1,2):dateArr[2];
		
		str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"',""+lang+"")+")"; 
	}
	return str;
}

/**
 * Filter 접기
 */
function foldFilter()
{
	var filterObj = $(".filter:not('.not_fold')");
	filterObj.find('.b_fold').removeClass('b_fold').addClass('b_unfold');

/*	filterObj.parents('.section_wrap').find('.article_box, .accordion_wrap').hide("fast", function(){
		if(typeof resizeTabFrame == "function") resizeTabFrame();
	 });*/
	
	filterObj.parents('.section_wrap').find('.article_box, .accordion_wrap').hide();
	if(typeof resizeTabFrame == "function") resizeTabFrame();

	filterObj.find('a').each(function(index){
		if(!$(this).is('.b_unfold,.b_fold,.b_close')) $(this).hide();
	});

}


/**
 * Open Print View
 */
function openPrintView(_url, _param)
{
	
 	width  = 800;
	height = 500;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;

	var _param = "strutsAction=" + BASE_ACTION_REPORT + "&" + _param;
	
	var pos = "left=" + sleft + ",top=" + stop + "scrollbars=yes,status=no,toolbar=no" + 
		      ",resizable=yes,location=no,menu=no,width="+width+"px ,height="+height+"px";
	
	openWindowWithPost(_url, "PRINT", _param, pos);
	
	
	//var _param = "strutsAction=" + BASE_ACTION_REPORT + "&" + _param;
	
	//$.post(_url,_param,function(_data){});
}


function checkListStatus()
{
	var topPage = getTopPage();
	topPage.animStatus = true;
	
	var status = ckSubAnim(document);
	
	if(!status) alertMessage1("조회중입니다.");
	return status;
}
var animStatus = true;
function ckSubAnim(_contents)
{
	 var topDoc = getTopPage();
	 if(typeof _contents == "undefined") _contents = parent.document;

	 if($(_contents).find("iframe[name^='tabFrame']").length > 0)
	 {
		 $(_contents).find("iframe[name^='tabFrame']").each(function(index){

			if($(this).is(':animated')) topDoc.animStatus = false;
			
			ckSubAnim($(this).contents());

		 });
	 }
	 
	 return topDoc.animStatus;
}

function submitPost(_pageId, _param, targetName, formName)
{
	if(typeof formName == "undefined") formName ="bottomForm";
	if(typeof _param == "undefined") _param ="";
	if(typeof targetName == "undefined") targetName ="_self";
	
	var paramArr = _param.split("&");
	var bottomForm = $("form[name='"+formName+"']");
	for(var i = 0; paramArr.length > i; i++)
	{
		var nameValArr = paramArr[i].split("=");
		if(bottomForm.find("input[name='"+nameValArr[0]+"']").length == 0)
		{
			bottomForm.append("<input name='"+nameValArr[0]+"' >");
		}

		if(nameValArr.length > 2)
		{	
			var cnt = 0;
			var nameVal = "";
			for(var key in nameValArr)
			{
				if(cnt != 0)	
				{
					nameVal = nameVal + nameValArr[key];
					if(cnt != nameValArr.length-1) nameVal = nameVal+"=";
				}
					
				cnt++;
			}
		}
		else nameVal = nameValArr[1];
			
		bottomForm.find("input[name='"+nameValArr[0]+"']").hide().val(decodeURIComponent(nameVal));
	}

	bottomForm.attr({"target" : targetName,
					 "action" : contextPath + "/" + _pageId + ".do"
	});	
	bottomForm.submit();
	
	for(var i = 0; paramArr.length > i; i++)
	{
		var nameValArr = paramArr[i].split("=");
		bottomForm.find("input[name='"+nameValArr[0]+"']").remove();
	}
}

var treeModel = function (arrayList, rootId, parentCol, col) { // list,"0","parentColumnId"
	var rootNodes = [];
	var traverse = function (nodes, item, index) {
		if (nodes instanceof Array) {
			return nodes.some(function (node) {
				if (node[col] === item[parentCol]) {
					node.children = node.children || [];
					return node.children.push(arrayList.splice(index, 1)[0]);
				}

				return traverse(node.children, item, index);
			});
		}
	};

	while (arrayList.length > 0) {
		arrayList.some(function (item, index) {
			//if (item[parentCol] === rootId) {
			if (item["MINLVL"] === item["LEVEL"]) {
				return rootNodes.push(arrayList.splice(index, 1)[0]);
			}

			return traverse(rootNodes, item, index);
		});
	}

	return rootNodes;
};

function doGridAction(_action, _grid, _gridId, _url, _param, _keyColumn, _isLoadMaxCount, _pKeyCol)
{	
	if(checkValidation()) return;
	
	if(!(currentPageId+"."+_gridId in isHeaderLoaded))
	{
		alertMessage1("Loading...");
		return;
	}
	
	if(typeof _isLoadMaxCount == "undefined") _isLoadMaxCount = "N";
	if(_action == "ReloadRow")
	{
		var paramFirst = "";
		paramFirst = _param + "&firstRow=0";
		ajaxPost(_url, paramFirst).done(function(_data){
			if(_data.indexOf("id") > 0)_data = _data.replace('id:', '"id":');
			
			//여기서 Row를 찾아서 가야 하는데...
			var jsonObj = JSON.parse(_data);  
			var modRow = jsonObj.data;
			if(modRow.length == 1)
			{
				if(typeof _keyColumn == "string")
				{				
					_keyColumn = _keyColumn.toUpperCase();
					
					var keyValue = modRow[0][_keyColumn];
					var rowId = getRowIdByValue(_grid, _keyColumn, keyValue+"");
					
					if(rowId == undefined)
					{
						var rowIdx = _grid.getRowsNum();
						rowId = (new Date()).valueOf();
						if(_grid.getSelectedRowId() != null) rowIdx = _grid.getRowIndex(_grid.getSelectedRowId())+1;
						
						//console.log(rowId+"   "+rowIdx);
						_grid.addRow(rowId,"", rowIdx);
						//rowId = "";
						
						_grid.selectRowById(rowId);
					}
					
					for(key in modRow[0])
					{
						setValueById(_grid, rowId, key, modRow[0][key]);
						//console.log("RowId:"+rowId+"    key:"+key+"   value:"+modRow[0][key]);
					}
					
					//_grid.setRowId(0, _grid.getRowsNum()+1);
				}
				//_keyColumn이 배열 형태로 들어왔을경우.
				else
				{
					var valueArr = new Array();
					for(keyCol in _keyColumn)
					{
						keyCol = keyCol.toUpperCase();
						
						//console.log("keyCol:"+_keyColumn[keyCol] +"   "+modRow[0][_keyColumn[keyCol]]);
						valueArr.push(modRow[0][_keyColumn[keyCol].toUpperCase()]); 
					}
					//keyCol과 keyCalue 테스트 필요
					var rowId = getRowIdByValue(_grid, _keyColumn, valueArr);
					
					
					if(rowId == undefined)
					{
						var rowIdx = 0;
						rowId = (new Date()).valueOf();
						if(_grid.getSelectedRowId() != null) rowIdx = _grid.getRowIndex(_grid.getSelectedRowId())+1;
						
						_grid.addRow(rowId,"");
						//rowId = "";
						
						_grid.selectRowById(rowId);
					}
					
					for(key in modRow[0])
					{
						setValueById(_grid, rowId, key, modRow[0][key]);
						//console.log("RowId:"+rowId+"    key:"+key+"   value:"+modRow[0][key]);
					}
					
				}
			}
			//console.log("!!!!"+modRow.length);
			//_grid.parse(_data,"js");
			
			setCounter(_grid,_gridId);
			if(typeof afterRowSearch == "function") afterRowSearch(_gridId);
		});
		
	}
	else if(_action == "SearchTree")
	{
		if(DECORATOR_NAME != "innerTabPage")setModal();
		
		_grid.clearAll(); 
		setLoading(_gridId);
		ajaxPost(_url, _param).done(function(_data){
			/*if(_data.indexOf("id") > 0)_data = _data.replace(/id:/g,'"id":');//replace('id:', '"id":');
			
			//console.log(_data);
			
			var jsonObj = JSON.parse(_data);  
			var modRow = jsonObj.data;
			
			var listJsonStr = JSON.stringify(modRow);
			//console.log(listJsonStr);
			var tree = treeModel(modRow,0,"PEQCTGID","EQCTGID");
			
			console.log("!!!!!!!!"+JSON.stringify(tree, null, '   '));*/			
			_grid.parse(_data,"js");
			
			_grid.expandAll(); //펼치기
			setCounter(_grid,_gridId); //TreeGrid는 펼쳐진 Rows만 카운트 함.
			//_grid.collapseAll();
			/*    	setTimeout("_grid.collapseAll();//접기", 200);*/
			
			closeModal();
			
			_strutsAction = "";
			_param.split('&').forEach(function(item, index, array){
		    	arr = item.split('=');
		    	if(arr[0] == "strutsAction") _strutsAction = arr[1];
		    });

			makeGridMap(_grid, _gridId, _strutsAction);

			if(typeof afterSearch == "function") afterSearch(_gridId);
		});
	}
	else if(_action == "ReloadTreeRow")
	{
		ajaxPost(_url, _param).done(function(_data){
			if(_data.indexOf("id") > 0)
			{
				_data = _data.replace('id:', '"id":');
			}
			
			//여기서 Row를 찾아서 가야 하는데...
			var jsonObj = JSON.parse(_data);  
			var modRow = jsonObj;
			if(modRow.length == 1)
			{
				if(typeof _keyColumn == "string")
				{					
					_keyColumn = _keyColumn.toUpperCase();
					
					var keyValue = modRow[0][_keyColumn];
					var rowId = getRowIdByValue(_grid, _keyColumn, keyValue+"");
					
					if(rowId == undefined)
					{
						var rowIdx = 0;
						rowId = (new Date()).valueOf();
						
						if(_grid.getSelectedRowId() != null) rowIdx = _grid.getRowIndex(_grid.getSelectedRowId())+1;
						
						if(typeof _pKeyCol == "undefined") _pKeyCol = "P"+_keyColumn;
						_pKeyCol = _pKeyCol.toUpperCase();

						setCounter(_grid,"gridbox");
						
						_grid.addRow(rowId,"", rowIdx ,modRow[0][_pKeyCol]);
						_grid.expandAll();
						
					}
					
					var nRowId="";
					for(key in modRow[0])
					{
						setValueById(_grid, rowId, key, modRow[0][key]);
						if("id"==key) nRowId = modRow[0][key];
					}
					
					_grid.changeRowId(rowId,nRowId);					
					_grid.selectRowById(nRowId);
				}
				//_keyColumn이 배열 형태로 들어왔을경우.
				else
				{
					var valueArr = new Array();
					for(keyCol in _keyColumn)
					{
						keyCol = keyCol.toUpperCase();
						
						//console.log("keyCol:"+_keyColumn[keyCol] +"   "+modRow[0][_keyColumn[keyCol].toUpperCase()]);
						valueArr.push(modRow[0][_keyColumn[keyCol].toUpperCase()]); 
					}
					//keyCol과 keyCalue 테스트 필요
					var rowId = getRowIdByValue(_grid, _keyColumn, valueArr);
					if(rowId == undefined) rowId = "";
					
					for(key in modRow[0])
					{
						setValueById(_grid, rowId, key, modRow[0][key]);
						//console.log("RowId:"+rowId+"    key:"+key+"   value:"+modRow[0][key]);
					}
					
				}
			}
			//console.log("!!!!"+modRow.length);
			//_grid.parse(_data,"js");
			
			if(typeof afterRowSearch == "function") afterRowSearch(_gridId);
		});
	}
	else if(_action == "Excel")
	{
		setModal();
		var paramFirst = "";
		var totalCount = _grid.getRowsNum();
		
		paramFirst = _param + "&firstRow=0"
				+ "&isTotalCount="+totalCount
				+ "&isLoadMaxCount="+_isLoadMaxCount
				+ "&currentPageId="+currentPageId;
		//_grid.load(_url+"?"+_param + "&isHeaderLoaded="+isHeaderLoaded);
		ajaxPost(_url, paramFirst).done(function(_data){
			closeModal();	
			//if(typeof afterSearch == "function") afterSearch(_gridId);
		
			//if(typeof afterSearchAll == "function" && _isLoadMaxCount == "N")afterSearchAll();
		
		});
	}
	else//일반적인 Search 
	{
		//InnerPage (상세 내부의 Sub List)는 로딩표시하지 않음
		if(DECORATOR_NAME != "innerTabPage")setModal();

		_grid.clearAll(); 
		setLoading(_gridId);
		
		var paramFirst = "";
		var pageNum = 1;
		var totalCount = _grid.getRowsNum();
		var isPageLoading = false;
		var prevParam = "";
		var prvFirstRow = 0;
		
		if(typeof attachedEventId != "undefined") _grid.detachEvent(attachedEventId); 
		/** Paging */
		attachedEventId = _grid.attachEvent("onScroll", function(sLeft,sTop){

			var statArr = _grid.getStateOfView();
			var paramSecond = "";
			
			var totalCount = $('#'+_gridId).val();
			//console.log($('.objbox').find('td:visible').eq(0).outerHeight(true));
			_grid.setAwaitedRowHeight($('.objbox').find('td:visible').eq(0).outerHeight(true));
			//console.log("1."+statArr[0] + "   2."+statArr[1]+"    3."+statArr[2]+"   "+totalCount);
			
			//_grid.setAwaitedRowHeight($('.objbox').find('td').eq(0).outerHeight(true));
			//스크롤이 마지막에 닿으면...
			if(statArr[0]+ 1 + statArr[1] >= statArr[2] &&_isLoadMaxCount == "Y" && totalCount > statArr[2])
			{
				//var totalCount = _grid.getRowsNum();
				paramSecond = _param + "&firstRow="+ statArr[2]
                					 + "&isTotalCount="+totalCount
                					 + "&isLoadMaxCount="+_isLoadMaxCount;
				
				if(!isPageLoading)
				{
					isPageLoading = true;
					setModal();
					ajaxPost(_url, paramSecond).done(function(_data){
						isPageLoading = false;
						_data = _data.replace(/"ID"/gi, "id");
						closeModal();	
						_grid.parse(_data,"js");	
						
						if(typeof afterSearch == "function") afterSearch(_gridId, _data);
						
						if(typeof afterSearchAll == "function" && _isLoadMaxCount == "N") afterSearchAll(_gridId, _data);
					});
				}
				
				//console.log("paramSecond:"+paramSecond);
			}

		});
		
		paramFirst = _param + "&firstRow=0"
							+ "&isTotalCount="+totalCount
							+ "&isLoadMaxCount="+_isLoadMaxCount
							+ "&isGridSearch=true";

		ajaxPost(_url,paramFirst).done(function(_data){
			var res = _data.replace(/"ID"/gi, "id"); 
			var jsonObj = JSON.parse(_data);
			$('#'+_gridId).val(jsonObj.view_count);
			
			//console.log(jsonObj.view_count);

			_grid.parse(res,"js");
			
			closeModal();
			
			_strutsAction = "";
			_param.split('&').forEach(function(item, index, array){
		    	arr = item.split('=');
		    	if(arr[0] == "strutsAction") _strutsAction = arr[1];
		    });

			makeGridMap(_grid, _gridId, _strutsAction);

			
			if(typeof afterSearch == "function") afterSearch(_gridId, _data);
			
			if(typeof afterSearchAll == "function" && _isLoadMaxCount == "N") afterSearchAll(_gridId, _data);
		});
	}
}

var _actionMap = {}; // gridId : searchAction Map
var _headerMap = {}; // gridId : excelHeader Map
function makeGridMap(_grid, _gridId, _strutsAction)
{
	var spans = [];
	for (var i=0; i<_grid.hdr.rows.length-1; i++){
		spans[i]=[];
		for (var j=0; j<_grid._cCount; j++){
			var cell = _grid.hdr.rows[i+1].childNodes[j];
			if (!spans[i][j])
				spans[i][j]=[0,0];
			if (cell)
				spans[i][cell._cellIndexS]=[cell.colSpan, cell.rowSpan];
		}
	}
    
    var headList = [];
	if(typeof _grid != "undefined") {
		for(var cin=0; cin<_grid.getColumnsNum(); cin++){
			if(_grid.isColumnHidden(cin)==true) continue;
			var headMap = {};
			var addHeadList = [];
			headMap.HIDDEN = "FALSE";
			headMap.ID = _grid.getColumnId(cin);
			for(var ind=0; ind<_grid.hdr.rows.length-1; ind++){
				if(ind==0){
					headMap.VALUE = _grid.getColumnLabel(cin,ind);
					headMap.SPANS = spans[ind][cin];
				}
				else {
					var addHeadMap = {};
					addHeadMap.VALUE = _grid.getColumnLabel(cin,ind);
					addHeadMap.SPANS = spans[ind][cin];
					addHeadList.push(addHeadMap);
				}
			}
			headMap.ADDHEADS = addHeadList;
			headMap.WIDTH = ""+_grid.getColWidth(cin);
			headMap.TYPE = _grid.getColType(cin);
			headMap.ALIGN = _grid.cellAlign[cin];
			headList.push(headMap);
		}
	}
	_actionMap[_gridId] = _strutsAction;
	_headerMap[_gridId] = headList;
}

/**
 * Grid Session Check
 * @param _data
 * @returns
 */
function checkSession(_data)
{
	if(_data.indexOf("data") > 0 && _data.indexOf("message") == -1) return true;

	var topDoc = getTopPage();
	var jsonObj = JSON.parse(_data);  

	if(jsonObj.gridException) //window.location.replace("http://"+serverName+":"+serverPort+contextPath+"/index.do");
	{	
		alert(COMMON_CMSG020);
	    
		topDoc.bottomForm.target = "";
		topDoc.bottomForm.action = contextPath + "/"+PAGE_TYPE+".do";
		topDoc.bottomForm.submit();
	    return false;  
	}
	else if(jsonObj.message)
	{
		alert(jsonObj.message);
		return false;
	}
	else return true;
}

/**
 * Grid Error Check
 * @param _data
 * @returns
 */
function checkError(_data)
{
	if(_data.indexOf("data") > 0 && _data.indexOf("message") == -1) return true;

	var topDoc = getTopPage();
	var jsonObj = JSON.parse(_data);

	if(jsonObj.errorLogId)
	{
		alertErrorMessage(COMMON_CMSG104, jsonObj.errorLogId);
		return false;
	}
	else return true;
}

function alertErrorMessage(_message, _errorLogId)
{
	var msg = "<div style='text-align:left;'>*"+COMMON_CMSG103+"</div><div style='text-align:left; margin:10px;'>"+_message.replace(/(\\n|\n|\r\n)/g,'<br>')+"</div><div style='text-align:center;'>[" + _errorLogId + "]"+COMMON_CMSG105+"</div>";
	setModal();
	topDoc.dhtmlx.alert(msg, function(result) {
		var url = contextPath + "/sendErrorMail.do";
		var param = "strutsAction=1002&errorLogId="+_errorLogId;
		$.post(url, param, function(ajaxXmlDoc){
			//1:성공, 2:메일서비스 비활성화 상태
		    var descVal = parseXmlDoc(ajaxXmlDoc, 'DESC');
		    if(descVal == "2") {
		    	topDoc.dhtmlx.alert(COMMON_MSG209, function(result) {
		    		
		    	});
		    }
		    closeModal();
		});
	});
}


function setGridUpdate(_url, _strutsAction, _grid, _callback, _jsonValid)
{
	curPageUpdate = true; //저장 설정
	var topDoc = getTopPage();	//최상위 페이지
	var param = "?strutsAction="+_strutsAction; //Grid 단위로 저장하므로 추가 param 제공 안함
	
	//make grid Editable
	_grid.setEditable(true);
	_grid.enableEditEvents(true,false,false);
    
	myDataProcessor = new dataProcessor(_url+param); // lock feed url
	myDataProcessor.setTransactionMode("POST",true); // set mode as send-all-by-post
	myDataProcessor.setUpdateMode("off"); // disable auto-update
	myDataProcessor.enableDataNames(true); //Column Id로 parameter 구성

	//dhxCalendar만 수정 update
	/*var newArr = [];
	for(var i = 0; _grid.getColumnsNum() > i; i++)
	{
		//console.log(i+"    "+grdObj.getColType(i));
		//alert(_grid.getColType(i));
		if(_grid.getColType(i) == "dhxCalendar" || _grid.getColType(i) == "dhxCalendarA") newArr.push(true);
		else newArr.push(false);
	}

function setValidate(proG)
{
	proG.setVerificator(getCoumnIdx(myGrid, "RSLTSTATUSDESC"),not_empty);
	proG.setVerificator(getCoumnIdx(myGrid, "RESULTVALUE"),not_empty);
}
	myDataProcessor.setDataColumns(newArr);  //Only second third column updateable
*/	
	if(typeof _jsonValid != "undefined")
	{
		var validRule = JSON.parse(_jsonValid);
		for (var key in validRule)  // key : maPtMstrList.partNo, value : part_no
		{
			var cIdx = getCoumnIdx(_grid, key.toUpperCase());
			if("not_empty" == validRule[key]) myDataProcessor.setVerificator(cIdx,not_empty);
		}
	}
	/*myDataProcessor.attachEvent("onRowMark",function(id){
		if (this.is_invalid(id)=="invalid") return false;
		return true;
	});*/
	//myDataProcessor.setDataColumns([false,false,false, true,true,true]);
	
	myDataProcessor.attachEvent("onBeforeUpdate", function(id, state, data){
		setModal(COMMON_CMSG060);
	    return true;
	});
	
	myDataProcessor.attachEvent("onAfterUpdate", function(id, action, tid, response){
		/*	//저장후 update row를 정상화 (자동으로 되어야 하는데 안됨 추후 업데이트 필요)
		for(var i = 0; _grid.getRowsNum()>i; i++)
		{
			myDataProcessor.setUpdated(_grid.getRowId(i), false);	
			
			/** dataProcessor를 재 생성하지 않고 다시 sendData를 할 경우 1번째 row만 send 되는 문제가 있었음.
			 *	dataProcessor 안의 _in_progress 배열에 값이 있으면 send하지 않는 데
			 *	update 이후에 dataProcessor에서 이 배열의 값을 비워주지 않기 때문에 강제로 비워주는 이 코드를 작성함.
			 *	dhtmlx dataProcessor 공식 API에 없는 기능을 강제로 처리한 것이기 때문에
			 *	추후 문제가 발생 시 해당 코드의 재 확인 필요.
			 */
			/*delete myDataProcessor._in_progress[_grid.getRowId(i)];
		}
*/
		topDoc.afterSaveAll(currentPageId);

		//_grid.setEditable(false);
		if(typeof _callback == "function") _callback(response);
		else if(typeof _callback == "string") $.globalEval( _callback+"();" ); //Callback 호출
	});

	_onRowMark = myDataProcessor.attachEvent("onRowMark", function(id, state, mode, invalid){
		topDoc.updateArray[currentPageId] = id; //Row가 업데이트 되면 페이지 저장 Flag
    	return true;
	});
	myDataProcessor.init(_grid); // link dataprocessor to the grid 
	
	return myDataProcessor;
}

function not_empty(value,id,ind){
	if (value==""){ 

		alertMessage1("필수사항입니다.");
		
		closeModal();
	}
	return value!="";
}

/*function setCalendar(_obj)
{
	//console.log("뉴 칼랜더 오픈");
	var queryDate = _obj.val()==""?getToday():_obj.val(),
    dateParts = queryDate.match(/(\d+)/g)
    realDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]);  
	
	_obj.datepicker({
	    numberOfMonths: 3,
	    showCurrentAtPos: 1,
	    stepMonths: 3, 
	    dateFormat:"yy-mm-dd",
	    setDate:realDate,
	    onSelect: function(date, obj){
	    	if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";

	    	if(typeof afterSetDate == "function") afterSetDate($(this).attr("name"));
	    	
	    	//$(calObj).datepicker("destroy"); jQuery Error 발생 
	    },
	    onClose: function(selectedDate) {
	    	//calObj.datepicker("destroy");
	    },
	    beforeShow: function (input, inst) {
	    	setTimeout(function () {
	    		if(inst.dpDiv.css("top") == "0px")
		            inst.dpDiv.css({
		                top: 25
		            });
	        }, 0);
	    	
	    	console.log("!!!");
	    }
	    //showButtonPanel: true
	  });
}*/

function setCalendar()
{	
	$('.b_date').each(function(e){
		var calObj = $(this).next().find('input');

		var queryDate = calObj.val()==""?getToday():calObj.val(),
	    dateParts = queryDate.match(/(\d+)/g)
	    realDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]);  
		
		calObj.datepicker({
		    numberOfMonths: 3,
		    showCurrentAtPos: 1,
		    stepMonths: 3, 
		    dateFormat:"yy-mm-dd",
		    //changeMonth: true,
		   // changeYear: true,
		    setDate:realDate,
		    onSelect: function(date, obj){
		    	if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";
		    	
		    	if(typeof afterSetDate == "function") afterSetDate($(this).attr("name"));
		    	
		    	chkInputVal($(this));
		    	//$(calObj).datepicker("destroy"); jQuery Error 발생 
		    },
		    onClose: function(selectedDate) {
		    	//calObj.datepicker("destroy");
		    },
		    beforeShow: function (input, inst) {
		    	if(typeof beforeShowDate == "function") beforeShowDate($(this).attr("name"), input);
		    	
		    	setTimeout(function () {
		    		if(inst.dpDiv.css("top") == "0px")
			            inst.dpDiv.css({
			                top: 25
			            });
		        }, 0);
		    }
		    //showButtonPanel: true
		  });
	});
	
	$('.open_calendar').each(function(e){
		var calObj = $(this).prev();

		var queryDate = calObj.val()==""?getToday():calObj.val(),
	    dateParts = queryDate.match(/(\d+)/g)
	    realDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]);  
		
		$(this).prev().datepicker({
		    numberOfMonths: 3,
		    showCurrentAtPos: 1,
		    stepMonths: 3, 
		    dateFormat:"yy-mm-dd",
		    //changeMonth: true,
		   // changeYear: true,
		    setDate:realDate,
		    onSelect: function(date, obj){
		    	if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";
		    	
		    	if(typeof afterSetDate == "function") afterSetDate($(this).attr("name"));
		    	
		    	chkInputVal($(this));
		    	
		    	//$(calObj).datepicker("destroy"); jQuery Error 발생 
		    },
		    onClose: function(selectedDate) {
		    	//calObj.datepicker("destroy");
		    },
		    beforeShow: function (input, inst) {
		    	
		    	if(typeof beforeShowDate == "function") beforeShowDate($(this).attr("name"), input);
		    	
		    	setTimeout(function () {
		    		if(inst.dpDiv.css("top") == "0px")
			            inst.dpDiv.css({
			                top: 25
			            });
		        }, 0);
		    }
		    //showButtonPanel: true
		  });
	});
	
	$('.open_calendar').bind("click",function(e){
		$(this).prev().datepicker("show");
	});
	
	/*$('.open_calendar').bind("click",function(e){

		var calObj = $(this).prev();

		var queryDate = calObj.val()==""?getToday():calObj.val(),
	    dateParts = queryDate.match(/(\d+)/g)
	    realDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]);  
		
		$(this).prev().datepicker({
		    numberOfMonths: 3,
		    showCurrentAtPos: 1,
		    stepMonths: 3, 
		    dateFormat:"yy-mm-dd",
		    //changeMonth: true,
		   // changeYear: true,
		    setDate:realDate,
		    onSelect: function(date, obj){
		    	if(curPageUpdate) getTopPage().updateArray[currentPageId] = "DATE";
		    	
		    	if(typeof afterSetDate == "function") afterSetDate($(this).attr("name"));
		    	
		    	//$(calObj).datepicker("destroy"); jQuery Error 발생 
		    },
		    onClose: function(selectedDate) {
		    	//calObj.datepicker("destroy");
		    }
		    //showButtonPanel: true
		  });
		
		$(this).prev().datepicker("show");
	});*/

	$('.open_calendar').prev().on({
		"keydown":function(e){
			onlyNumberInput(e);
		},
		"keyup"  :function(e){
			dateFormat(this, e);
		},
		"blur"   :function(e){
			 if($(this).val().length != 10)  $(this).val("");
		}
	});
	
	$('.datetime').on({
		"keydown":function(e){
			onlyNumberInput(e);
		},
		"keyup"  :function(e){
			datetimeFormat(this, e);
		},
		"blur"   :function(e){
			 if($(this).val().length != 10 && $(this).val().length != 19)  $(this).val("");
		}
	});
	$('.datetime').trigger('keyup');
}

var _excelExForm, _excelFileName, _excelPageId, _excelHeadList, _excelStrutsAction, _excelYn;
/**
 * Excel Server Side Export
 * @param pageId
 * @param form
 * @param params{fileName}
 */
function excelServerAction(pageId, form, params)
{
	var fileName;
	var excelYn = "N";
	if(typeof _params == "string") {
		fileName = params;
	}
	else if(typeof params == "object") {
		fileName = params.fileName;
		excelYn = params.excelYn;
	}
	
	if(typeof fileName == "undefined") fileName = "ExcelList";

	_excelExForm = form;
	_excelFileName = fileName;
	_excelPageId = pageId;
	_excelYn = excelYn;
	if(typeof _excelGridId != "undefined" && _excelGridId != "") {
		var strutsAction = _actionMap[_excelGridId];
		var headList = _headerMap[_excelGridId];
		if(typeof strutsAction != "undefined"){
			_excelStrutsAction = strutsAction;
		}
		if(typeof headList != "undefined"){
			_excelHeadList = headList;
		}
		else if (typeof headList == "undefined")
		{
			alertMessage1(COMMON_CSMG013);
			return;
		}
	}
	
	alertMessage1(COMMON_CMSG061);
	
	var popWidth = 450;
	var popHeight = 350;

	// pop up이 중앙에 위치하게 한다.
	var TopPosition  = (screen.height/2 - popHeight/2);
	var LeftPosition = (screen.width/2 - popWidth/2);
	
	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
		      ",top=" + TopPosition + "px,left=" + LeftPosition + "px" +
		      ",toolbar=no,scrollbars=yes,resizable=yes";
	var url = contextPath + "/excelExport.do";
	
	openWindowWithPost(url, "excelDownload_"+currentPageId, encodeURI(FormQueryStringPost(form)), pos);
}

function excelServerExe()
{
	form = _excelExForm;
	fileName = _excelFileName;
	excelYn = _excelYn;
	headList = _excelHeadList;
	strutsAction = _excelStrutsAction;
	
	if($(form).find("input[name='currentPageId']").length == 0) $(form).append("<input type='hidden' name='currentPageId'/>");
	if($(form).find("input[name='isDownloadPage']").length == 0)$(form).append("<input type='hidden' name='isDownloadPage'/>");
	if($(form).find("input[name='fileName']").length == 0)$(form).append("<input type='hidden' name='fileName'/>");
	if($(form).find("input[name='stAct']").length == 0)$(form).append("<input type='hidden' name='stAct'/>");
	if($(form).find("input[name='headList']").length == 0)$(form).append("<input type='hidden' name='headList'/>");
	if($(form).find("input[name='excelYn']").length == 0)$(form).append("<input type='hidden' name='excelYn'/>");

	var url = contextPath + "/" + _excelPageId + ".do";
	
	var oldStAction = form.strutsAction.value;
	
	form.action = url;
	form.currentPageId.value = currentPageId;
	form.isDownloadPage.value = true;
	form.strutsAction.value = baseGridExportAction;
	if(typeof strutsAction == "undefined") {
		if(excelYn == "Y") {
			form.strutsAction.value = oldStAction;
		}
		form.stAct.value = oldStAction;
	}
	else {
		if(excelYn == "Y") {
			form.strutsAction.value = strutsAction;
		}
		form.stAct.value = strutsAction;
	}
	form.fileName.value = fileName;
	form.headList.value = JSON.stringify(headList);
	form.excelYn.value = excelYn;
	form.target = "excelDownload_"+currentPageId;
	form.submit();
	
	form.target = "";
	form.isDownloadPage.value = false;
	form.headList.value = "";
	form.excelYn.value = "";
	form.strutsAction.value = oldStAction;
}

/**
 * Export Excel
 * @param _grid
 */
function excelAction(_grid, title)
{
	alertMessage1(COMMON_CMSG061);
	if(typeof title =="undefined") title = "ExcelList";
	excelFileName = title;
	_grid.toExcel(contextPath + "/generate");
}

/**
 * 탭페이지 숨기기
 * @param pageId
 */
function hideTabPage(pageId)
{
	$('#'+pageId+'_tabList').hide();
}

/**
 * 탭페이지 보이기
 * @param pageId
 */
function hideTabPage(pageId){
	$('#'+pageId+'_tabList').show();
}

/**
 * Cookie 세팅 setCoo
 * @param name
 * @param value
 * @param expiredays
 */
function setCookie(name, value, expiredays) 
{
	var today = new Date();
	today.setDate(today.getDate() + expiredays);
	document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';'
}

/**
 * Get Cookie
 * @param name
 * @returns
 */
function getCookie(name)
{    
	var wcname = name + '=';
	var wcstart, wcend, end;
	var i = 0;   
	while(i <= document.cookie.length) 
	{            
	   wcstart = i;  
	   wcend   = (i + wcname.length);            
	 if(document.cookie.substring(wcstart, wcend) == wcname) 
	 {                    
	    if((end = document.cookie.indexOf(';', wcend)) == -1)                           
	  	end = document.cookie.length;                    
	  	return document.cookie.substring(wcend, end);            
	 }           
	 i = document.cookie.indexOf('', i) + 1;            
	  
	 if(i == 0)break;    
	
	}    
	return '';
}

/**
 * Paging Page Sorting Function (Search All with Sorting)
 * @param _url
 * @param _grid
 * @param form
 * @param keyId
 * @param ind
 * @param direction
 * @returns {Boolean}
 */
function sortColumn(_url, _grid, form, keyId, ind, direction, _gridId)
{
	//수정시 Sorting Prevented
	//if(_grid.isEditable) return;
	
	if(typeof _gridId == "undefined") _gridId = "gridbox";
	strutsAction = _actionMap[_gridId];
	if(typeof strutsAction == "undefined") return;
	
	$(form).find("input[name='strutsAction']").val(strutsAction);
	
	var colId = '"'+_grid.getColumnId(ind)+'"';
    var url = contextPath + "/"+_url+".do";
    var _dir = direction;
    
    if($(form).find("input[name='orderBy']").length == 0) $(form).append("<input type='hidden' name='orderBy'/>");
	if($(form).find("input[name='direction']").length == 0)$(form).append("<input type='hidden' name='direction'/>");
	
	//var _idx = _sortColumn.findIndex(x => x.gridId == _gridId);
	/*var _idx = _sortColumn.findIndex(function(item, i){
	  return item.gridId === _gridId
	});*/

	var _idx = -1;
	for (var i = 0; i < _sortColumn.length; ++i) {
	    if (_sortColumn[i].gridId == _gridId) {
	    	_idx = i;
	        break;
	    }
	}
	
	var orderByObj = $(form).find("input[name='orderBy']");
	var directionObj = $(form).find("input[name='direction']");
	
	orderByObj.val("");
	directionObj.val("");
	
	if(_idx !== undefined && _idx !== -1)
	{
		orderByObj.val(_sortColumn[_idx].orderBy);
		directionObj.val(_sortColumn[_idx].direction);
	}
	
	if(orderByObj.val() != "")
	{
		var cId = orderByObj.val();
		var cIdArr = cId.split(",");
		var dir = directionObj.val();
		var dirArr = dir.split(",");

		if(cIdArr.indexOf(colId) > -1)
		{
			var dupColIdx = cIdArr.indexOf(colId);
			cIdArr.splice(dupColIdx, 1);
			dirArr.splice(dupColIdx,1);

			orderByObj.val(cIdArr.join());
			directionObj.val(dirArr.join());
		}

	    if(directionObj.val() != "") direction =  direction + "," + directionObj.val();
		if(orderByObj.val() != "") colId =  colId + "," + orderByObj.val();

		directionObj.val(direction);
		orderByObj.val(colId);	
		
		_sortColumn[_idx].direction = direction;
		_sortColumn[_idx].orderBy = colId;
	}
	else
	{
		directionObj.val(direction);
		orderByObj.val(colId);
		
		var _sortParam = new Object();
		_sortParam.gridId = _gridId;
		_sortParam.direction = direction;
		_sortParam.orderBy = colId;
		_sortColumn.push(_sortParam);
	}
	
    doGridAction("Search", _grid, _gridId, url, FormQueryString(form), keyId ,"Y");
    _grid.setSortImgState(true,ind,_dir); 
    return false;
}

/*==============메뉴 기능===============*/
function bindMouseOpen()
{
	$('.b_menuopen').bind("click",function(){

		var menuId = $(this).parent().find('a').prop("id"); 
		var fileName = findMenuUrl(menuId);
		
		if(fileName.isExLink == "Y")
	    {
			if(fileName.isGetLink=="Y"){
				var url = fileName.exLink;  
	            window.open(url, "_blank");  
	    	}else{
		    	openELink(fileName.exLink,fileName.pValue);
	    	}
	    }
	    else
	    { 		    	
	    	$.globalEval("openQuickPage('', '"+fileName.url+"&menuId="+menuId+"');");
	    }
	}).css("cursor","pointer");
}

/**
 * 메뉴구분(main,user,linked 펼치기 닫기
 */
function openMenu(id){
	 var obj = document.getElementById(id);
	if (obj.style.display == "none")
    {
        obj.style.display = "";
    }
    else
    {
        obj.style.display = "none";
    }
}

function openToMenu(_menuId)
{
	$('#mainMenu').find('li > a').each(function(e){
    	if(_menuId == $(this).prop("id"))
    	{ 
    		$(this).addClass('selected').parents("ul").css('display','block');
    		if($(this).parents("ul").prev().is("a")) $(this).parents("ul").prev().addClass("open");
    		
    		$(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>"));
    		selectedMenuName = $(this).text();
    		bindMouseOpen();	
    	}
    });
}

function findMenuUrl(_menuId)
{
	var rtnValue;
	for(var i in menuJArray)
    {
        if(menuJArray[i].id == _menuId)rtnValue =  menuJArray[i];
    }
	return rtnValue;
}

/**
 * Javascript Object Menu Object 
 * @param _menuId
 * @returns
 */
function findMenuJObj(_menuId)
{
	if(typeof menuJArray == "undefined") return;
	var rtnValue;
	for(var i in menuJArray)
    {
         if(menuJArray[i].id == _menuId) rtnValue =  menuJArray[i];
    }
	return rtnValue;
}

/*================메뉴 기능 끝==================*/

/**
 * List Action 
 */
function doListAction(_action, _list, _url, _param, _isLoadMaxCount)
{
	var _url 			= contextPath + "/"+_url+".do";
	var containerID 	= $('.dhx_list').prop("id");
	var loadPage = new Array();
	var pageNum = $('[name="pageNum"]');
	
	//setModal();
	
	if(typeof _isLoadMaxCount == "undefined") _isLoadMaxCount = "N";
	if(_action == "Search")
	{
		_list.clearAll();
		
		//loadPage.push("1");
		var paramFirst = _param + "&firstRow="+"0"
			+ "&isLoadMaxCount="+ _isLoadMaxCount
			+ "&isGridSearch=true";

		$.post(_url,paramFirst, function(_data){
			if(checkSession(_data) && checkError(_data))_list.parse(_data,"json");

		}).done(function(e){
			
			var itemHeight		= $('.dhx_list_default_item').outerHeight();//- 0.25;
			var _loadedDataCnt  = _list.dataCount();			
			
			//스크롤 하면 페이징 채크 및 조회 
			if(_isLoadMaxCount == "Y")
				$('#'+containerID).scroll(function() {
	
					var scrolledHeight 	= $('#'+containerID).scrollTop() + parseInt($('#'+containerID).css("height").replace("px","")) + 50;
					var _curPageNum 	= Math.floor((scrolledHeight / itemHeight)/maxLoadCount + 1);
					//조회된 Row를 넘어선 스크롤...그러면 다시 불러오자! (아래로 스크롤)
					if(loadPage.filter(function(x){return x == _curPageNum}) == "") //현제 페이지가 조회된 페이지 목록에 없다면...
					{
						loadPage.push(_curPageNum);
						pageNum.val(_curPageNum);
						
						var _firstRow = (_curPageNum-1)* maxLoadCount;
						var paramFirst = _param + "&firstRow="+_firstRow
						+ "&isLoadMaxCount="+ _isLoadMaxCount
						+ "&isGridSearch=true";
						
						$.post(_url,paramFirst, function(_data){
							if(checkSession(_data) && checkError(_data))_list.parse(_data,"json");
						}).done(function(e){
							if(typeof afterPagingSearch == "function") afterPagingSearch();
						});
					    
					}
				});
			

			var pHeight = "100";
			if(DECORATOR_NAME.indexOf("mobilePopup")>=0) 
			{
				var srchBoxHeight = 0;
				if($('.psrch_box').length)
				{
					srchBoxHeight = $('.psrch_box').outerHeight();
				}

				pHeight = $(parent.document).find('body').css('height').replace("px","")/2 - 65 - srchBoxHeight;
			}	
			else
			{				
				var headerName = "list_header";			
				if($('.footer_wrap').length)
				{
					var footerSet = $('.footer_wrap').offset();
					var viewHeight = footerSet.top == 0? $( document ).height():footerSet.top;

					pHeight = viewHeight -( $('.'+headerName).offset().top + $('.'+headerName).outerHeight());
				}

			}


			//리스트 높이 설정
			$('#'+containerID).css({"height":pHeight+"px"});

			//Put Total Count 
			$('.count_box').empty().append(COMMON_totCnt+" : "+_list.dataCount());


			if(pageNum.val() != "" && "1" != pageNum.val())
			{
				loadPage.push(pageNum.val());
				fRow = maxLoadCount * (pageNum.val()-1);
				var paramFirst = _param + "&firstRow="+fRow
				+ "&isLoadMaxCount="+ _isLoadMaxCount
				+ "&isGridSearch=true";
				
				$.post(_url,paramFirst, function(_data){
					if(checkSession(_data) && checkError(_data))_list.parse(_data,"json");
				}).done(function(e){
					if(typeof afterSearch == "function") afterSearch();
				});
			}
			else 
			{
				if(typeof afterSearch == "function") afterSearch();
			}
			
			if(typeof resizeTabFrame == "function") resizeTabFrame();
			
			//closeModal();	
		});
	}
}

function goCommonMobilePage(_form, _strutsAction, _pageId)
{
	var url = contextPath + "/" + _pageId + ".do";
	
	_form.elements['strutsAction'].value = _strutsAction;
	_form.elements['pageNum'].value = _form.elements['pageNum'].value;
	_form.action = url;
	_form.submit();
}

function reloadPage()
{
	var listObj, keyId, keyObjName;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) listObj = arguments[i];
		else if(i == 1) keyId = arguments[i];
		else if(i == 2) keyObjName = arguments[i]; 
    }

	if(typeof keyId  == "string")
	{
		if($('[name="'+keyObjName+'"]').val() == "") return;

		for(var i = 0; listObj.dataCount() > i; i++)
		{
			var _id = listObj.idByIndex(i);
			var itemObj = listObj.get(_id);
		
			//console.log(itemObj[keyId]+"    "+$('[name="'+keyObjName+'"]').val());
			if(itemObj[keyId] == $('[name="'+keyObjName+'"]').val())
			{
				
				listObj.show(_id);
				listObj.select(_id);
				$('[name="'+keyObjName+'"]').val("")
				return;
			}
		}
	}
	
}

function chkFilter()
{
	var ifm = getIframeContent();
	var rtnVal = false;
	
	if($(ifm.document).find('.filter').size()) rtnVal =  true;

	if($("[name='multiSelect']").val() == "Y") rtnVal =  true;
	else if($("[name='multiSelect']").val() == "N") rtnVal =  false;

	return rtnVal;
}

function getRowData(_gridObj, _rowId)
{
	var rtnObj = {};
	for(var i = 0; _gridObj.getColumnsNum() > i; i++)
	{
		rtnObj[_gridObj.getColumnId(i)] = htmlEnDeCode.htmlDecode(_gridObj.cells(_rowId, i).getValue());
	}
	return rtnObj;
}

/**
 * 
 * @param _listType
 * cdsysdNo
 * listType
 * description
 * param1
 * param2
 * codeLabel -- > 'LABEL.partNo'
 * @returns
 */
function getSysCode(_listType)
{
	return getSysCodeWLang(_listType, loginUser.langId);
}

function getSysCodeWLang(_listType, _langId)
{
	var rtnArray = new Array();
	for(var i in topDoc.jSysCode)
	{
		if(_listType == topDoc.jSysCode[i].LISTTYPE && _langId == topDoc.jSysCode[i].LANG) rtnArray.push(topDoc.jSysCode[i]);
	}
	
	return rtnArray;
}

/**
 * Get SysCode Description 
 * @param _listType
 * @param _code
 * @returns
 */
function getSysCodeDesc(_listType, _code)
{
	var _rtnVal = "No Code";
	var _codeList = getSysCodeWLang(_listType, loginUser.langId);
	for(var i in _codeList)
	{
		if(_codeList[i].CDSYSDNO == _code.toUpperCase()) _rtnVal = _codeList[i].KEYNAME;
	}
	
	return _rtnVal;
}

/**
 * Ac Value Setting Common
 * @param _gridObj
 * @param _idColName
 */
function setAcValue(_gridObj, _idColName)
{
	
	var selectedId = _gridObj.getSelectedRowId();
	if(selectedId==null) return;
	
	var pKeyCode = ""; //description
	var ifm = getIframeContent();
    var topPage = getTopPage();
	
    if(typeof ifm.beforeSetAcValue == "function") ifm.beforeSetAcValue(_idColName);

    if(typeof _idColName == "undefined") _idColName = "";
	var returnArray = new Array();
	var resultColMap = JSON.parse($("[name='resultCol']").val());

	for (var key in resultColMap)  // key : maPtMstrList.partNo, value : part_no
	{
		//console.log("!!!"+resultColMap[key].toUpperCase() +"    "+key);
		if(resultColMap[key].toUpperCase() == $("[name='keyCode']").val().toUpperCase()) pKeyCode = key;
	}

	//console.log("common pKeyCode:"+$("[name='keyCode']").val());
	var grdObj = topPage.gGridObj[pKeyCode];
	var cellObj =topPage.gCellObj[pKeyCode];
	var spId = selectedId.split(",");
	var rtnJsonArry = new Array();
	var isFilter = false;
	for (var key in resultColMap)
	{
		isFilter = isFilter||ifm.isFilterField(key);
	}
    if(spId.length != 1)
    {
    	for(var i in spId)  //LOV에서 선택된 값들의 key들...
    	{
    		rtnJsonArry.push(getRowData(_gridObj, spId[i]));  //Row Data 밀어 넣음 
    	} 
    	
    	for (var key in resultColMap)  // key : maPtMstrList.partNo, value : part_no
		{
    		//Grid에서 팝업을 오픈한 경우.
    		if(grdObj && cellObj)
    		{
    			//Cell내부에 삽입한 input에도 값을 넣어주고, Grid Cell에도 값을 세팅한다. 
    			$(cellObj).find('input').val(getValueById(_gridObj, spId[i], resultColMap[key].toUpperCase()));
				setValueById(grdObj, cellObj.parentNode.idd, key, getValueById(_gridObj, spId[i], resultColMap[key].toUpperCase()));
				
    		}
    		else 
    		{
    			if($(ifm.document).find("[name='"+key+"']").length) //화면에 맞는 Input이 있다면...
				{
					var rtnOne= new Array(); 
					for(var i in spId)  //LOV에서 선택된 값들의 key들...
			    	{
				    	rtnOne.push(getValueById(_gridObj, spId[i], resultColMap[key].toUpperCase()));
			    	} 
	
					$(ifm.document).find("[name='"+key+"']").val(rtnOne.join("+"))
					chkInputVal($(ifm.document).find("[name='"+key+"']"));
				}
    		}
			//수정페이지인경우 && 필터가 아닌경우
			if(ifm.curPageUpdate&&!isFilter) getTopPage().updateArray[ifm.currentPageId] = key;
	
			if(resultColMap[key].toUpperCase() == $("[name='keyCode']").val().toUpperCase()) pKeyCode = key;
			
		}
    }
    else
    {
    	for(var i in spId)  //LOV에서 선택된 값들의 key들...
    	{
    		rtnJsonArry.push(getRowData(_gridObj, spId[i]));  //Row Data 밀어 넣음 
    	} 
    	
		for (var key in resultColMap)  // key : maPtMstrList.partNo, value : part_no
		{	
			//Grid에서 팝업을 오픈한 경우.
			if(grdObj && cellObj)
    		{
				$(cellObj).find('input').val(getValueById(_gridObj, spId[i], resultColMap[key].toUpperCase()));
				setValueById(grdObj, cellObj.parentNode.idd, key, getValueById(_gridObj, spId[i], resultColMap[key].toUpperCase()));
				
    		}
    		else 
    		{    			
    			if($(ifm.document).find("[name='"+key+"']").length)
    			{
    				$(ifm.document).find("[name='"+key+"']").val(getValueById(_gridObj, selectedId, resultColMap[key].toUpperCase()));
    				chkInputVal($(ifm.document).find("[name='"+key+"']"));
    			}
    		}
			//수정페이지인경우 && 필터가 아닌경우
			if(ifm.curPageUpdate&&!isFilter) getTopPage().updateArray[ifm.currentPageId] = key;

			if(resultColMap[key].toUpperCase() == $("[name='keyCode']").val().toUpperCase()) pKeyCode = key;

		}
    }

    var chName = $("[name='chName']").val();
    if(chName != "" )
    {
    	var topPage = getTopPage();
    	var colChJsonObj = JSON.parse(topPage.gColMap[chName]);
		for (var key in colChJsonObj)
		{
			$(ifm.document).find("[name='"+key+"']").val("");
		}
		
		ifm.setReadable(chName);
    }
    
	//After All the process is ended, use this function.
	if(typeof ifm.afterAutoCmpt =="function") ifm.afterAutoCmpt(pKeyCode, rtnJsonArry);
	
	if(typeof ifm.setAcLovValue =="function")ifm.setAcLovValue(rtnJsonArry, pKeyCode);
	else if(typeof ifm.setLovValue =="function")ifm.setLovValue(rtnJsonArry, pKeyCode);

    closeLayerPopup();
}

/**
 * Main Menu Display
 * @param _action
 * @returns
 */
function menuDisplay(_action)
{
	if(_action == "EXPAND")
	{
		if($('.b_leftunfold').size() > 0)
		{
			$('#content,#pg_title').css("marginLeft","246px");
		
			$('#nav_left').css("width","245px");
			$('.gridbox.gridbox_dhx_skyblue').css("width","100%");
			
			$('.b_leftunfold').addClass('b_leftfold').removeClass('b_leftunfold');
			$('.mboxfold').addClass('mbox').removeClass('mboxfold');
			
			$('.temp').addClass('m_current').removeClass('temp').next().show();
			$('.b_menuopen').show();
		}
	}
	else
	{
		if($('.b_leftfold').size() > 0)
		{
			$('#content,#pg_title').css("marginLeft","41px");
			
			$('#nav_left').css("width","40px");
			$('.gridbox.gridbox_dhx_skyblue').css("width","100%");
			$('.mbox').addClass('mboxfold').removeClass('mbox');
			$('.smb').hide();
	
			$('.m_current').removeClass('m_current').addClass('temp');
			
			$('.b_leftfold').addClass('b_leftunfold').removeClass('b_leftfold');
			$('.b_menuopen').hide();
		}
	}
}

// Revision 완료
function revCompleted(revStatus, objId, objNo, revhistId, objType)
{
	if(checkIsUpdate(document))
  	{
  		alertMessage1(COMMON_MSG0033);
  	}
  	else
  	{
  		if(checkValidation()) return;
  		
  		setModal(COMMON_CMSG060);
  		
  		var sAction;
  		var actionUrl;
  		if(revStatus == "W")
  	  	{
  			actionUrl = contextPath + "/commRevRegislate.do";
  			sAction = "1003";
  	  	} 
  		else if(revStatus == "P")
  	  	{
  			actionUrl = contextPath + "/commRevRevision.do";
  			sAction = "2003";
  	  	}else{
  	  		return ;
  	  	}
  		var param = "&" + "strutsAction="+sAction;
  		param += "&" + "commRevCommonDTO.objectId="+objId;
  		param += "&" + "commRevCommonDTO.objectNo="+objNo;
  		param += "&" + "commRevCommonDTO.revisionhistId="+revhistId;
  		param += "&" + "commRevCommonDTO.revisionObjType="+objType;
  		
  		/*ajaxPost(actionUrl, param).done(function(_data){
  			closeModal();
			if(typeof afterRevcompleted == "function") afterRevcompleted(_data);
  		});*/
  		
  		$.post(actionUrl, param, function(_data){
  			closeModal();
			if(typeof afterRevcompleted == "function") afterRevcompleted(_data);
  		});
  		
  	 }
}

function openRev(url, revhistId, desc, pageId)
{
	var param = "strutsAction=2001";
	
	param += "&" + "popupWidth=800";
	param += "&" + "commRevCommonDTO.oldRevisionhistId="+revhistId;
	param += "&" + "commRevCommonDTO.description="+desc;
	param += "&" + "commRevCommonDTO.param="+pageId;
	
	openLayerPopup(url, param);
}

function openRevHistory(objId, objNo, revObjType)
{
	var url   = contextPath + "/commRevHistList.do";
 	var popWidth = 850;
 	var popHeight = 540;
     // pop up이 중앙에 위치하게 한다.
     var TopPosition  = (screen.height/2 - popHeight/2);
     var LeftPosition = (screen.width/2 - popWidth/2);

     var pos = "width=" + popWidth + ",height=" + popHeight + "" +
               ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
     pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
 	
 	var param = "isDecoratorName=popupPage";
 	param += "&commRevHistCommonDTO.strutsAction=";
 	param += "&commRevHistCommonDTO.objectId=" + objId;
	param += "&commRevHistCommonDTO.objectNo=" + objNo;
	param += "&commRevHistCommonDTO.revisionObjType=" + revObjType;

 	//post 전송
 	openWindowWithPost(url, "REVISION_HISTORY_LIST_POPUP", param, pos);
}

function revDisplayCtrl(revStatus, _isLastVer, _revType)
{
	if(typeof _revType == "undefined") _revType = "ASSET";
	if((isAssetRev == "Y" && _revType == "ASSET") || (isPmRev == "Y" && _revType == "PM"))
	{
		revCtrlAction(revStatus,_isLastVer);
	}
	else
	{
		hideBtn("REVCOMPLETED");
		hideBtn("REVISION");
		hideBtn("APPROVAL");
	}
}

function revCtrlAction(revStatus, _isLastVer)
{
	//console.log('revStatus : ' + revStatus);
	if(_isLastVer == "Y")
	{
		showBtn("REVISION");
		showBtn("COLSETTING");
		
		setDisableAll();
	}
	else if(revStatus == "C" || revStatus == "PA" || revStatus == "RA")
	{
		setDisableAll();
	}
	else if(revStatus == "W" || revStatus == "P" || revStatus == "DA")
	{
		hideBtn("REVISION");
		hideBtn("REVISIONHISTORY");
		hideBtn("PMSCHED");
		showBtn("APPROVAL");
		
		setEnableAll();
	}
	else
	{
		hideBtn("APPROVAL");
		hideBtn("REVISIONHISTORY");
		hideBtn("REVISION");
		hideBtn("REVCOMPLETED");
	}
}

function logScreenTrace(objectId, objectNo )
{
	try{
		var actionUrl = contextPath + "/logScreenTrace.do";
		var param = "&" + "strutsAction=1001";
		param += "&" + "commLogScrnTraceDTO.pageName="+currentPageId;
		param += "&" + "commLogScrnTraceDTO.objectId="+objectId;
		param += "&" + "commLogScrnTraceDTO.objectNo="+objectNo;
		param += "&" + "commLogScrnTraceDTO.description="+$('.stitle_tx').text();
		param += "&" + "commLogScrnTraceDTO.contents="+ $( "div.article_box" ).html();
		
		XMLHttpPost(actionUrl, param, '');
    }catch(e){
    }
}

/**
 * 결재
 * @param objectId
 * @param apprType
 * @param desc
 * @returns
 */
function appAction(objectId, apprType, desc, _jsonParam)
{
	if(typeof _jsonParam == "undefined") _jsonParam = {};
	
	 var param = "strutsAction="+strutsActionApproval + 
		        "&appReqCommonDTO.objectId="+objectId +
	 			"&appReqCommonDTO.apprType=" + apprType +
	 			"&appReqCommonDTO.title=" + desc +
	 			"&appReqDetailDTO.jsonParam=" + _jsonParam; //Extra Info. ex) {"apprType":"ED","equipId":"20192"}
	  
	 openLayerPopup("appReqDetail",param);
}

/**
 * Check Object Text Length
 * @param _obj
 * @param _lenLimit
 */
function chkLength(_obj, _lenLimit)
{
	var textLength = _obj.val().length;
    var actualLen = 0;
    var korCnt = 0;
    if(textLength == 0) return;
    
    for(var k=0; k < textLength; k++)
    {
    	actualLen++;

        if(escape(_obj.val().charAt(k)).length > 4)
        {
        	actualLen= actualLen + 2;
        	korCnt = korCnt + 2;
        }
    }

    // 제한된 길이보다 입력된 길이가 큰 경우 제한 길이만큼만 자르고 텍스트영역에 넣음
    if (actualLen > _lenLimit) {
        _obj.val(_obj.val().substr(0, _lenLimit - korCnt));
    } 
}

function bindLenLimit(_obj, _lenLimit)
{
	//input Length 0일때는 적용 안함  2019-08-27 정현
	if(_lenLimit == "null" || _lenLimit == "0") return;

	_obj.on({
		keyup: function(){
			chkLength($(this), _lenLimit);
	 	},
	 	blur: function(){
	 		chkLength($(this), _lenLimit);
	 	}
	 	
	 });
}

var htmlEnDeCode = (function() {
    var charToEntityRegex,
        entityToCharRegex,
        charToEntity,
        entityToChar;

    function resetCharacterEntities() {
        charToEntity = {};
        entityToChar = {};
        // add the default set
        addCharacterEntities({
            '&amp;'     :   '&',
            '&gt;'      :   '>',
            '&lt;'      :   '<',
            '&quot;'    :   '"',
            '&#39;'     :   "'"
        });
    }

    function addCharacterEntities(newEntities) {
        var charKeys = [],
            entityKeys = [],
            key, echar;
        for (key in newEntities) {
            echar = newEntities[key];
            entityToChar[key] = echar;
            charToEntity[echar] = key;
            charKeys.push(echar);
            entityKeys.push(key);
        }
        charToEntityRegex = new RegExp('(' + charKeys.join('|') + ')', 'g');
        entityToCharRegex = new RegExp('(' + entityKeys.join('|') + '|&#[0-9]{1,5};' + ')', 'g');
    }

    function htmlEncode(value){
        var htmlEncodeReplaceFn = function(match, capture) {
            return charToEntity[capture];
        };

        return (!value) ? value : String(value).replace(charToEntityRegex, htmlEncodeReplaceFn);
    }
    
    function htmlDecode(value) {
        var htmlDecodeReplaceFn = function(match, capture) {
            return (capture in entityToChar) ? entityToChar[capture] : String.fromCharCode(parseInt(capture.substr(2), 10));
        };

        return (!value) ? value : String(value).replace(entityToCharRegex, htmlDecodeReplaceFn);
    }

    resetCharacterEntities();

    return {
        htmlEncode: htmlEncode,
        htmlDecode: htmlDecode
    };
})();

/**
 * Ajax Common Function 
 * @param _url
 * @param _data
 * @param _paramObj
 * @returns
 */
function ajaxPost(_url, _data, _paramObj){
	var deferred = $.Deferred();
	
	var _callBack; //Callback Function Name
	var _dataType; //Data Type (xml,html,script,json,text,jsonp)
	var _async = true;  //is Async(default : true)
	
	if(typeof _paramObj == "string")
	{ 
		_callBack = _paramObj;
	}
	else 
	{
		for( var key in _paramObj ) {
			 if(key.toUpperCase() == "ASYNC") _async = _paramObj[key];
			 else if(key.toUpperCase() == "CALLBACK") _callBack = _paramObj[key];
			 else if(key.toUpperCase() == "DATATYPE") _dataType = _paramObj[key];
		}
	}

	$.ajax({
		method:"POST",
		url:_url,
		data:_data+'&isAjax=true',
		dataType:_dataType,//xml,html,script,json,text,jsonp
		async:_async,
		success:_callBack
	}).done(function(d){
		closeModal();
		resBody = JSON.parse(d)
		if(typeof _callBack != "undefined") $.globalEval(_callBack+"("+d+");");
		
		alertMessage1(resBody.message);
		
		deferred.resolve(d);
	}).fail(function(e){
		closeModal();
		resBody = JSON.parse(e.responseText)
		if(e.status == 500) { //Internal Server Error
			alertErrorMessage(resBody.message, resBody.ext.errorLogId);
		}
		else if(e.status == 401) { //Unauthorized
			alert(resBody.message);
			topDoc.bottomForm.target = "";
			topDoc.bottomForm.action = contextPath + "/"+PAGE_TYPE+".do";
			topDoc.bottomForm.submit();
		}
		else {
			alertMessage2(resBody.message);
		}
		deferred.reject(e);
	});
	return deferred.promise();
}

/**
 * 결재 버튼 클릭시 호출 
 * @param _pageId
 * @returns
 */
function goBeforeApproval(_pageId)
{
	if(typeof enhanceGoApproval == "function")
	{
		enhanceGoApproval(_pageId);
	}
	else goApproval(_pageId);
}



var _IfmLoadCnt = 0;
/**
 * CALLBACK : callback function with page name param
 * @param _pageId
 * @param _jsonParam
 * @returns
 */
function checkIframeLoaded(_pageId, _jsonParam, _ifmStatus) { //ex) checkIframeLoaded("pageId", 100); --> CallBack (afterIframeLoaded(_pageId));
   _IfmLoadCnt++;
   var time = 300;
   var _callback;
   
   if(typeof _ifmStatus == "undefined") _ifmStatus = "loading";
   
   // Get a handle to the iframe element
    var iframe = document.getElementById('tabFrameTAB.'+_pageId);  
    if(_IfmLoadCnt == 10) return;

    if(!iframe){
       window.setTimeout(function() {
          checkIframeLoaded(_pageId, _jsonParam);
          }, time);
       return;
    }
    
    if(typeof _jsonParam == "object")
    {
       _callback = _jsonParam.CALLBACK;
    }
    else if(typeof _jsonParam == "string")
    {
       _callback = _jsonParam;
    }
    
    var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;
    if(iframeDoc.readyState == _ifmStatus){
        iframe.contentWindow.onload = function(){
           console.log("Loaded Iframe");
        }; 
        
        if(iframeDoc.readyState == "complete")
        {           
           if(typeof afterIframeLoaded == "function") afterIframeLoaded(_pageId);
           
           console.log(_callback);
           
           if(typeof _callback != "undefined") $.globalEval(_callback+"('"+_pageId+"');");
        }
        else
           checkIframeLoaded(_pageId, _jsonParam, "complete");
        // The loading is complete, call the function we want executed once the iframe is loaded
        
        return;
    } 

    window.setTimeout(function() {
      checkIframeLoaded(_pageId, _jsonParam, _ifmStatus);
      }, time);
   return;
}

/**
 * Search Page 
 * @param pageId
 * @returns windows
 */
function searchPage(pageId)
{
	var _topPage = getTopPage(); //top Page Window
	//var _topDoc = _topPage.document; //top Page Document

	if(pageId == _topPage.currentPageId)
	{
		return _topPage;
	}
	else
	{
		var _ifPage = getParentIframe(pageId); //iframe Page Window
		//var _ifDoc = _ifPage.document; //iframe Page Document

		if(typeof _ifPage == "undefined" && window.opener)
		{
			if(typeof window.opener.searchPage == "function")
			{
				return window.opener.searchPage(pageId);
			}
			else return window.opener;
		}
		else
		{
			if(typeof _ifPage == "undefined") _ifPage = _topPage;
			
			return _ifPage;
		}
	}
}

var _exeFuncTimer={};
/**
 * 특정 page의 
 * @param _winObj
 * @param _paramObj
 * @returns
 * ex) exeFunc("_fileName","afterExeFunc"); afterExeFunc(_fileName){ searchPage(_fileName).{Function Name}({params for Function}); }
 */
function excFunc(_pageId, _paramObj)
{
	var _winObj = searchPage(_pageId);
	var _docObj = _winObj.document;
	var _callback = "afterExeFunc";
	var _loadCnt = _exeFuncTimer[_pageId];

	if(typeof _loadCnt == "undefined") _exeFuncTimer[_pageId] = 1;
	else _exeFuncTimer[_pageId] = _loadCnt + 1;
	
	if(typeof _paramObj == "object")_callback = _paramObj.CALLBACK;
	else if(typeof _paramObj == "string") _callback = _paramObj;

	if(_loadCnt > 10) return;

	if(_docObj.readyState == "complete" && $(_docObj).find('div').length > 0) $.globalEval("if(typeof "+_callback+" == 'function') "+_callback+"('"+_pageId+"');");
	else
	{
		window.setTimeout(function() {
			excFunc(_pageId, _paramObj)
		}, 500);
	}
}

/**
 * When Page is loaded for input or search, this function set the value in the input object.
 * @param _inputName
 * @param _val
 * @returns
 */
function setInitVal(_inputName, _val)
{
	var _inputObj = $('input[name="'+_inputName+'"]');
	
	//Linked 메뉴를 사용해서 화면이 열렸으면 아무값도 세팅하지 않는다. 
	if(_isLinked == "Y" || _val == "null" || _val == "") return;
	//값이 세팅되어 있지 않을때만 세팅한다. 
	if(_inputObj.val() == "") _inputObj.val(_val); 
}

/*
 * Form을 JSON으로 변경한다.
 */
function getFormValue(_formname)
{
   var data = '';

   $.each( _formname, function(key, val){
	   data += ',"'+val['name']+'":"'+val['value']+'"';
   });
   
   data = '{'+ data.substr(1) +'}'; 

   console.log( JSON.parse(data) );
   console.log( JSON.stringify(JSON.parse(data) ) );
   console.log( JSON.parse(JSON.stringify(JSON.parse(data) ) ) );
   
   return JSON.parse(data);
}

/*
 * JSON 값을 form에 세팅한다.
 */
function setFormValue(_jsondata)
{
   var _inputObj;
   var _key = "";
    
   for(i=0; i<Object.keys(_jsondata).length; i++)
   {
      _inputObj = $('input[name="'+Object.getOwnPropertyNames(_jsondata)[i]+'"]');
      _key = Object.getOwnPropertyNames(_jsondata)[i];
      
      if("strutsAction"!=_key && "currentPageId"!=_key)
         _inputObj.val(_jsondata[Object.getOwnPropertyNames(_jsondata)[i]]);
   }
}

/**
 * Slide Image 공통 Function
 * @returns
 */
function setSlideImage()
{
	 var change_img_time 	= 5000;	
	 var transition_speed	= 500;
	 
	 $('.img_box').children('img').remove();
	 
	for(var i=0; slideImage.length > i ; i++)
	{
		var onError = "document.forms[0].photo"+i+".src='"+contextPath+"/common/images/ma/no_image.png'";
		if(_fileDir == "AZURE")
			$('.img_box').append("<img src='"+slideImage[i]+"' name='photo"+i+"' onerror="+onError+">");
		else
			$('.img_box').append("<img src='"+contextPath+"/dream/imgSlide/"+slideImage[i]+"' name='photo"+i+"' onerror="+onError+">");
	}
	
	var slideShow = $('.img_box'),
	    listItems = slideShow.children('img'),
	    listLen	  = listItems.length,
        i 		  = 0,
		changeList = function (e) {
			if(typeof e =="undefined") e = 1;
			$('.img_prev,.img_next').hide();
			listItems.eq(i).fadeOut(transition_speed, function () {
				i = i + e;
				if(i < 0) i = listLen - 1;
				if (i == listLen) {
					i = 0;
				}
				$('.img_prev,.img_next').hide();
				listItems.eq(i).fadeIn(transition_speed, function(){
					$('.img_prev,.img_next').show();
				});
			});

        };
		
    listItems.not(':first').hide();
    
   // var go = setInterval(changeList, change_img_time);
	 
	$('.img_prev').on("click",function(e){
	//	clearInterval(go)

		changeList(-1);
	});
	
	$('.img_next').on("click",function(e){
	//	clearInterval(go)

		changeList(1);
	});
	$('.img_box').children('img').on("click",function(e){
		window.open($('.img_box').children('img:visible').attr("src"), '_blank'); 
	});
}


/**
 * Null Check 
 * @param _obj
 * @returns
 */
function isNull(_obj)
{
	return (_obj == undefined || _obj == null || _obj == "null" || _obj == "") ? true : false;
}


/**
 * Put X mark in the input field.
 * @returns
 */
function putXinput()
{
	//console.log("current Page:"+currentPageId+"    X 넣기 시작!");
	var _inObj = $('input:not([readonly]):not([type=hidden])');
	_inObj.after("<p class='tx_clear_r'><a><span>삭제</span></a></p>");
	
	$('.tx_clear_r').on({
		"click":function(e){
			$(this).prev().val("");
			$(this).prev().trigger("customE");
			
			chkInputVal($(this).prev());
			
			if(curPageUpdate) topDoc.updateArray[currentPageId] = $(this).prev().attr("name");
		}
	}).each(function(index){
		var nxtObj = $(this).next();
		if(nxtObj.is("p"))$(this).attr("class","tx_clear");
		
		if(nxtObj.is("p") && nxtObj.is(".tx_clear_r,.tx_clear")) $(this).remove();

		chkInputVal($(this).prev());
	});
	
	_inObj.on({
		"keyup":function(e){
			chkInputVal($(this));
		}
	});
}

/**
 * Control X mark  
 * @param _inputObj
 * @returns
 */
function chkInputVal(_inputObj)
{
	if(_inputObj.next().is(".tx_clear_r,.tx_clear"))
	{
		if(_inputObj.val() == "") _inputObj.next().hide();
		else _inputObj.next().show();
	}
}

function goDeleteAction(_grid, _url, _param, _checkColumn, _keyColumn)
{
	var deferred = $.Deferred();
	
	var delArray = getSelectedRows(_grid, _checkColumn, _keyColumn); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	ajaxPost(_url,_param+delArray)
	.done(function(d){
		JSON.parse(d).data.forEach(function(element){
			_rowId = getRowIdByValue(_grid, _keyColumn, ""+element[_keyColumn.toUpperCase()]);
			_grid.deleteRow(_rowId);
		});
		goClose(beforePageId);
		
		deferred.resolve(d);
	})
	.fail(function(e){
		deferred.reject(e);
	});
	
	return deferred.promise();
}

/*
 * Common Function for Value setting 
 */
function setVal(_inputName, _val)
{
   var _inputObj = $('[name="'+_inputName+'"]');
   
   _inputObj.val(_val);
   //'X' mark Chedk 2
   chkInputVal(_inputObj);
}