/*====================================================
 * date util 공통
 *===================================================*/

/**
 * 8자리의 일짜를 년월일 구분을 '-' 로 하여 리턴한다.
 */
function dataToDate(psData) 
{
	if (psData == null || psData == "") 
	{
		return "";
	}

	if (trim(psData) == "" ||  trim(psData).length != 8) 
	{
		return psData;
	}
	
	var year = psData.substr(0, 4);
    var mm   = psData.substr(4, 2);
    var dd   = psData.substr(6, 2);
	
  	return year + "-" + mm + "-" + dd;
}

/**
 * - 로 붙여서 넘어온 일자를 
 * - 를 빼고 일자만 리턴한다.
 */
function dateToData(psDate) 
{
	if (psDate == null || trim(psDate) == "") 
	{
		return "";
	}
	return psDate.split("-").join("");
}

/**
 * 6자리의 일짜를 년월 구분을 '-' 로 하여 리턴한다.
 */
function monthToMonth(psData) 
{
	if (psData == null || psData == "") 
	{
		return "";
	}

	if (trim(psData) == "" ||  trim(psData).length != 6) 
	{
		return psData;
	}
	
	var year = psData.substr(0, 4);
    var mm   = psData.substr(4, 2);
	
  	return year + "-" + mm;
}

/**
 * 8자리의 일짜를 년월일 구분을 '-' 로 하여 리턴한다.
 */
function dataToTime(psData) 
{
    if (psData == null || psData == "") 
    {
        return "";
    }

    if (trim(psData) == "" || (trim(psData).length != 4 &&  trim(psData).length != 6)) 
    {
        return psData;
    }
    
    var hour = psData.substr(0, 2);
    var min  = psData.substr(2, 2);
    
    if (trim(psData).length > 5)
    {
        var ss=psData.substr(4, 2);
        return hour+":"+min+":"+ss;
    }
    else
    {
        return hour+":"+min ;
    }
}

/**
 * - 로 붙여서 넘어온 일자를 
 * - 를 빼고 일자만 리턴한다.
 */
function timeToData(psDate) 
{
    if (psDate == null || trim(psDate) == "") 
    {
        return "";
    }
    return psDate.split(":").join("");
}

/**
 * - 로 붙여서 넘어온 일자를 
 * - 를 빼고 일자만 리턴한다.
 */
var DATE_TIME_TOKEN = " ";	// date 와 time 구분 Token
function datetimeToData(psDatetime) 
{
	if (psDatetime == null || trim(psDatetime) == "") 
	{
		return "";
	}
	var dateTimeArray = psDatetime.split(DATE_TIME_TOKEN).join("");
	return dateToData(dateTimeArray[0])+timeToData(dateTimeArray[1]);
}

/**
 * - 로 붙여서 넘어온 일자를 
 * - 를 빼고 일자만 리턴한다.
 */
function dataToDatetime(psData) 
{
	if (psData == null || trim(psData) == "") 
	{
		return "";
	}
	var _date = psData.substr(0, 8);
	var _time = psData.substr(8, psData.length-8);
	
	return dataToDate(_date)+DATE_TIME_TOKEN+dataToTime(_time);
}

/**
 * - 로 붙여서 넘어온 일자를 
 * - 를 빼고 일자만 리턴한다.
 */
var DATE_TO_DATE_TOKEN = " ~ ";	// date 와 time 구분 Token
function datefromtoToData(psDatefromto) 
{
	if (psDatefromto == null || trim(psDatefromto) == "") 
	{
		return "";
	}
	var datefromtoArray = psDatefromto.split(DATE_TO_DATE_TOKEN).join("");
	return dateToData(datefromtoArray[0])+dateToData(datefromtoArray[1]);
}

/**
 * - 로 붙여서 넘어온 일자를 
 * - 를 빼고 일자만 리턴한다.
 */
function dataToDatefromto(psData) 
{
	if (psData == null || trim(psData) == "") 
	{
		return "";
	}
	var _dateFrom = psData.substr(0, 8);
	var _dateTo = psData.substr(8, psData.length-8);
	
	return dataToDate(_dateFrom)+DATE_TO_DATE_TOKEN+dataToDate(_dateTo);
}

//===============================================================

/**
 * 오늘 일자를 리턴받는다.
 */
function getToday() 
{
	var gubun = "-";

	today = new Date(new Date().getTime()+timeGap);
	
	var year  = today.getFullYear();
	var month = today.getMonth()+1;
	var day   = today.getDate();

	if (month < 10)
	{
		month = "0" + month;
	}
	if (day < 10)
	{
		day = "0" + day;
	}
	return year+gubun+month+gubun+day;
}

/**
 * 현재년도
 */
function getYear() 
{
	today = new Date(new Date().getTime()+timeGap);
	return today.getFullYear();
}

/**
 * 현재월
 */
function getMonth() 
{
	today = new Date(new Date().getTime()+timeGap);
	var month = today.getMonth()+1;

	if (month < 10)
	{
		month = "0" + month;
	}
    return month;
}

function getDay() 
{
	today = new Date(new Date().getTime()+timeGap);
	var day = today.getDate();

	if (day < 10)
	{
		day = "0" + day;
	}
    return day;
}

/**
 * 현재 시간을 리턴한다.
 * 시 분 으로 4자리를 리턴한다.
 * ex) 09:30 오전 9시 30분
 */
function getTime(isHMS) 
{
	var timeGubun = ":";
	
	today = new Date(new Date().getTime()+timeGap);

	var hours   = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	
	if (hours < 10)
	{
		hours = "0" + hours;
	}
	if (minutes < 10)
	{
		minutes = "0" + minutes;
	}
	if (seconds < 10)
	{
		seconds = "0" + seconds;
	}
	
	var result;
	if(isHMS === true)
	{
		result = hours+timeGubun+minutes+timeGubun+seconds;
	}
	else
	{
		result = hours+timeGubun+minutes;
	}
	return result;
}

/**
 * 현재시간 - hour시간
 * 시 분 으로 4자리를 리턴한다.
 * ex) 09:30 오전 9시 30분
 */
function getMinusTime(isHMS,hour) 
{
	var timeGubun = ":";
	
	today = new Date(new Date().getTime()+timeGap);
	
	var hours   = today.getHours()-hour;
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	
	if (hours < 10)
	{
		hours = "0" + hours;
	}
	if (minutes < 10)
	{
		minutes = "0" + minutes;
	}
	if (seconds < 10)
	{
		seconds = "0" + seconds;
	}
	
	var result;
	if(isHMS === true)
	{
		result = hours+timeGubun+minutes+timeGubun+seconds;
	}
	else
	{
		result = hours+timeGubun+minutes;
	}
	return result;
}

/**
 * 오늘 날짜에서 지정된 날짜 만큼 더한 날짜를 리턴한다.
 */
function getPlusDay(day)
{
	var gubun = "-";

	var now = new Date(new Date().getTime()+timeGap);
	var newdate = new Date(new Date().getTime()+timeGap);
	
	sdate = newdate.getTime();
	edate = sdate + (day*24*60*60*1000);   
	
	newdate.setTime(edate);
	
	var year  = newdate.getFullYear();
	var month = newdate.getMonth()+1;
	var day   = newdate.getDate();

	if (month < 10)
	{
		month = "0" + month;
	}
	if (day < 10)
	{
		day = "0" + day;
	}
	return year+gubun+month+gubun+day;
}

/**
 * fromDate 와 toDate의 유효성을 검증한다.
 */
function checkTwoDate(fromDateObj, toDateObj, fromTimeObj, toTimeObj) 
{
	fromDateObj.disabled = false;
	if(!validDate(fromDateObj)) return true;	// 시작일자 검증
	if(!validDate(toDateObj)) return true;		// 끝일자 검증
	var sFromDate = trim(dateToData(fromDateObj.value));
	var sToDate   = trim(dateToData(toDateObj.value));
	if (sFromDate == "" && sToDate != "")
	{
		fromDateObj.focus();
		alertMessage1(COMMON_CMSG027); // "검색 시작일자를 입력바랍니다."
		return true;
	}
	if (sFromDate != "" && sToDate == "")
	{
		toDateObj.focus();
		alertMessage1(COMMON_CMSG028); //"검색 끝 일자를 입력바랍니다."
		return true;
	}
	var iFromDate = parseInt(sFromDate);
	var iToDate   = parseInt(sToDate);
	// 검색범위 유효성 검증
	if (iFromDate > iToDate) 
	{
		fromDateObj.focus();
		// '검색 시작 일자가 검색 시작 끝 일자보다 작습니다.'
		alertMessage1(COMMON_CMSG009);
		return true;
	}
	if(fromTimeObj != null && toTimeObj != null)
	{
		if(!chkTime(fromTimeObj, toTimeObj)) return true;
		
		if(iFromDate == iToDate)
		{
			var fromTime = parseInt(fromTimeObj.value.replace(/\:/g,""),10);
			var toTime = parseInt(toTimeObj.value.replace(/\:/g,""),10);

			if(fromTime > toTime)
			{
				alertMessage1(COMMON_CMSG029); //"시작시간이 종료 시간보다 큽니다."
				fromTimeObj.focus();
				return true;
			}
		}
	}
	return false;
}


function dFormat(_str)
{	
	var str  = onlyNum(_str);
	var leng = str.length;

	switch(leng)
	{
		case 1 :  
	  	case 2 : 
	  	case 3 : 
	  	case 4 : break;
	  	case 5 : break;
	  	case 6 : str = str.substring(0, 4) + "-" + str.substring(4);
				 break;  
		case 7 : break;
	  	case 8 :str = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6); 
				 break;
	    default : str = ""; break; 
	}

	return str;
}

/**
 * 날짜 입력시 keyUp event에 준다.
 */
function dateFormat(dateObj, e)
{	
    var e = e || window.event; 
    var code = getKeyCode(e); 

	if( code == 9 || code == 16 || (code >= 37 && code <= 40) ) 
	{
		dateObj.focus();
		return;
	}
	
	var str  = onlyNum(dateObj.value);
	var leng = str.length;
	var n = dateObj.name;
	var f = "document.frm.";
	if (dateObj.value.length == 10)
	{		
		if(!chkDate(str)) dateObj.value = "";
	}
	else
	{
		switch(leng)
		{
			case 1 :  
		  	case 2 : 
		  	case 3 : 
		  	case 4 : dateObj.value = str; break;
		  	case 5 : break;
		  	case 6 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4);
					 if(!chkDate(str)) dateObj.value = "";
					 break;  
			case 7 : break;
		  	case 8 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6); 
					 if(!chkDate(str)) dateObj.value = "";
					 break;
		    default : dateObj.value = ""; break; 
		}
	}
}

/**
 * 날짜 입력시 keyUp event에 준다.
 */
function datetimeFormat(dateObj, e)
{	
    var e = e || window.event; 
    var code = getKeyCode(e); 

	if( code == 9 || code == 16 || (code >= 37 && code <= 40) ) 
	{
		dateObj.focus();
		return;
	}
	
	var str  = onlyNum(dateObj.value);
	var leng = str.length;
	var n = dateObj.name;
	var f = "document.frm.";
	if (dateObj.value.length == 19)
	{		
		if(!chkDate(str)) dateObj.value = "";
	}
	else
	{
		switch(leng)
		{
			case 1 :  
		  	case 2 : 
		  	case 3 : 
		  	case 4 : dateObj.value = str; break;
		  	case 5 : break;
		  	case 6 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4);
					 if(!chkDate(str)) dateObj.value = "";
					 break;  
			case 7 : break;
		  	case 8 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6); 
					 if(!chkDate(str)) dateObj.value = "";
					 break;
		  	case 9 : break;
		  	case 10 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8); 
					  if(!chkDate(str)) dateObj.value = "";
					  break;
		  	case 11 : break;
		  	case 12 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10); 
					  if(!chkDate(str)) dateObj.value = "";
					  break;
		  	case 13 : break;
		  	case 14 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12) + ":" + str.substring(12); 
				  	  if(!chkDate(str)) dateObj.value = "";
				  	  break;
		    default : dateObj.value = ""; break; 
		}
	}
}

/**
 * 숫자만 리턴한다.
 */
function onlyNum(val)
{
	var num = val;
	var tmp = "";
	for (var i=0; i<num.length; i++)
	{
  		if (num.charAt(i) >= 0 && num.charAt(i) <= 9)
   			tmp = tmp + num.charAt(i);
  		else
   			continue;
	}
	return tmp;
}

/**
 * 날짜 입력시 keyPress event에 준다.
 */
function timeFormat(t, _event)
{

   // if (!onlyIntegerInput(_event,false)) return;

    var code = getKeyCode(_event);
    
    // back:8, del:46, 방향전:37, 방향후:39 아래코드 진행 안되게 한다. 
    if (code == 46 || code == 8 || code == 37 || code == 39 ) return;

 	var  time = parseInt(t.value.replace(/\:/g,""));
 	var  ns = (t.value.replace(/\:/g,"")).toString();

 	if(ns.length>3){
 		ns = ns.substring(0,4);
 	}
 	
 	var nLength = 0;
 	var str="";
 
 	if(isNaN(time))
  	return "";

 	nLength = ns.length;
 	if(nLength > 0)
 	{
  		for(i = 0 ; i < nLength ; i++)
  		{
	   		if((i == 1))
	    		str += ns.substr(i,1) + ":";
	   		else if(i == 3 && nLength > 4)
	    		str += ns.substr(i,1) + ":";
	   		else 
	   			str += ns.substr(i,1);
	  	}
 	}
 	t.value = str;
 	
 	if(nLength == 4)
 	 if(!chkTime2(str)) t.value = "";
 	  	
 	return true;
}
/**
 * 날짜 입력시 keyPress event에 준다.
 */
function timeSecondFormat(t, _event)
{
	
	// if (!onlyIntegerInput(_event,false)) return;
	
	var code = getKeyCode(_event);
	
	// back:8, del:46, 방향전:37, 방향후:39 아래코드 진행 안되게 한다. 
	if (code == 46 || code == 8 || code == 37 || code == 39 ) return;
	
	var  time = parseInt(t.value.replace(/\:/g,""));
	var  ns = (t.value.replace(/\:/g,"")).toString();
	
	if(ns.length>5){
		ns = ns.substring(0,6);
	}
	
	var nLength = 0;
	var str="";
	
	if(isNaN(time))
		return "";
	
	nLength = ns.length;
	if(nLength > 0)
	{
		for(i = 0 ; i < nLength ; i++)
		{
			if((i == 1))
				str += ns.substr(i,1) + ":";
			else if(i == 3 && nLength > 4)
				str += ns.substr(i,1) + ":";
			else if(i == 6 && nLength > 7)
				str += ns.substr(i,1) + ":";
			else 
				str += ns.substr(i,1);
		}
	}
	t.value = str;
	
	if(nLength == 8)
		if(!chkTimeSecond(str)) t.value = "";
	
	return true;
}

function chkTime(t1, t2)
{
	if(t1.value == '' && t2.value == '') return true;
	if(t1.value.length < 5 || t1.value.substr(0,2) > 23 || t1.value.substr(2,1) != ":" || t1.value.substr(3,2) > 59)
	{
		// "올바른 시간형식이 아닙니다."
		alertMessage1(COMMON_CMSG011);
		t1.value = "";
		t1.focus();
		return false;
	}
	if(t2.value.length < 5 || t2.value.substr(0,2) > 23 || t2.value.substr(2,1) != ":" || t2.value.substr(3,2) > 59)
	{
		// "올바른 시간형식이 아닙니다."
		alertMessage1(COMMON_CMSG011);
		t2.value = "";
		t2.focus();
		return false;
	}
	return true;
}

function chkTime2(t1)
{
	if(t1== '') return true;
	if(t1.length < 5 || t1.substr(0,2) > 23 || t1.substr(2,1) != ":" || t1.substr(3,2) > 59)
	{
		// "올바른 시간형식이 아닙니다."
		alertMessage1(COMMON_CMSG011);
		return false;
	}
	
	return true;
}

function chkTimeSecond(t1)
{
	if(t1== '') return true;
	if(t1.length < 8 || t1.substr(0,2) > 23 || t1.substr(2,1) != ":" || t1.substr(3,2) > 59
			|| t1.substr(5,1) != ":" || t1.substr(6,2) > 59)
	{
		// "올바른 시간형식이 아닙니다."
		alertMessage1(COMMON_CMSG011);
		return false;
	}
	
	return true;
}

/**
 * submit하기 바로 전에 호출한다.
 * 입력된 시간이 올바른 형식인지 체크한다.
 * ex) if(!validTime(form.timeType)) return;
 */
function validTime(obj)
{
	var timeStr = obj.value.split(":").join("");

	if (timeStr.length == 0)
	{
		return true;
	}

	if (timeStr.length != 4)
	{
		// "올바른 시간 형식이 아닙니다."
		alertMessage1(COMMON_CMSG011);
		obj.value = "";
		obj.focus();
		return false;
	}

	if (!chkTime2(obj.value))
	{
		obj.value = "";
		obj.focus();
		return false;
	}
	
	return true;
}

/**
 * submit하기 바로 전에 호출한다.
 * 입력된 시간이 올바른 형식인지 체크한다.
 * ex) if(!validTime(form.timeType)) return;
 */
function validTimeSecond(obj)
{
	var timeStr = obj.value.split(":").join("");
	
	if (timeStr.length == 0)
	{
		return true;
	}
	
	if (timeStr.length != 6)
	{
		// "올바른 시간 형식이 아닙니다."
		alertMessage1(COMMON_CMSG011);
		obj.value = "";
		obj.focus();
		return false;
	}
	
	if (!chkTimeSecond(obj.value))
	{
		obj.value = "";
		obj.focus();
		return false;
	}
	
	return true;
}

/**
 * 현재 일자의 유효성을 검증한다.
 * beforeSave 가 true  : 저장 바로 전으로 8자리인지 아닌지도 체크한다.
 *              false : 없거나 false인경우 값을 자릿수는 상관없다.
 */
function chkDate(str)
{	 
	if(str.length == 6)
	{
 		vDate = new Date(str.substring(0,4), (str.substring(4,6)-1));
 		if(vDate.getFullYear() != str.substring(0, 4) || vDate.getMonth() != str.substring(4, 6)-1 )
 		{
 			// "올바른 날짜 형식이 아닙니다."
 			alertMessage1(COMMON_CMSG012);
   			return false;
  		}
		return true;
	}
	else if(str.length == 8)
	{
 		vDate = new Date(str.substring(0, 4), (str.substring(4, 6)-1), str.substring(6));
		if(vDate.getFullYear() != str.substring(0, 4) || vDate.getMonth() != str.substring(4, 6)-1 || vDate.getDate() != str.substring(6))
		{
			// "올바른 날짜 형식이 아닙니다."
			alertMessage1(COMMON_CMSG012);
   			return false;
  		}
  		return true;
	}
	else if(str.length == 10)
	{
		vDate = new Date(str.substring(0, 4), (str.substring(4, 6)-1), str.substring(6, 8), str.substring(8));
		if(vDate.getFullYear() != str.substring(0, 4) || vDate.getMonth() != str.substring(4, 6)-1 || vDate.getDate() != str.substring(6, 8) || vDate.getHours() != str.substring(8))
		{
			// "올바른 날짜 형식이 아닙니다."
			alertMessage1(COMMON_CMSG012);
			return false;
		}
		return true;
	}
	else if(str.length == 12)
	{
		vDate = new Date(str.substring(0, 4), (str.substring(4, 6)-1), str.substring(6, 8), str.substring(8, 10), str.substring(10));
		if(vDate.getFullYear() != str.substring(0, 4) || vDate.getMonth() != str.substring(4, 6)-1 || vDate.getDate() != str.substring(6, 8) || vDate.getHours() != str.substring(8, 10) || vDate.getMinutes() != str.substring(10))
		{
			// "올바른 날짜 형식이 아닙니다."
			alertMessage1(COMMON_CMSG012);
			return false;
		}
		return true;
	}
	else if(str.length == 14)
	{
		vDate = new Date(str.substring(0, 4), (str.substring(4, 6)-1), str.substring(6, 8), str.substring(8, 10), str.substring(10, 12), str.substring(12));
		if(vDate.getFullYear() != str.substring(0, 4) || vDate.getMonth() != str.substring(4, 6)-1 || vDate.getDate() != str.substring(6, 8) || vDate.getHours() != str.substring(8, 10) || vDate.getMinutes() != str.substring(10, 12) || vDate.getSeconds() != str.substring(12))
		{
			// "올바른 날짜 형식이 아닙니다."
			alertMessage1(COMMON_CMSG012);
			return false;
		}
		return true;
	}
	
	return false;
}

/**
 * submit하기 바로 전에 호출한다.
 * 입력된 날짜가 올바른 형식인지 체크한다.
 * ex) if(!validDate(form.dateType)) return;
*/
function validDate(obj)
{
	var dateStr = dateToData(obj.value);

	if (dateStr.length == 0)
	{
		return true;
	}

	if (dateStr.length != 8)
	{
		// "올바른 날짜 형식이 아닙니다."
		alertMessage1(COMMON_CMSG012);
		obj.value = "";
		obj.focus();
		return false;
	}

	if (!chkDate(dateStr))
	{
		obj.value = "";
		obj.focus();
		return false;
	}
	
	return true;
}

/**
 * 두 날짜시간사이의 분을 계산 
 * @param startDate
 * @param endDate
 * @param startTime
 * @param endTime
 * @returns
 */
function getMinInterval(startDateObj, startTimeObj, endDateObj, endTimeObj)
{
	var startDate = $(startDateObj).val();
	var startTime = $(startTimeObj).val();
	var endDate = $(endDateObj).val();
	var endTime = $(endTimeObj).val();
	
	if(startDate =="" || endDate == "" || startTime =="" || endTime=="") return "";
	
	var sDate = new Date(new Date().getTime()+timeGap);
	sDate.setFullYear(startDate.substring(0,4));
	sDate.setMonth(Number(startDate.substring(5,7)) -1);
	sDate.setDate(startDate.substring(8,10));
	sDate.setHours(startTime.substring(0,2));
	sDate.setMinutes(startTime.substring(3,5));
	var eDate = new Date(new Date().getTime()+timeGap);
	eDate.setFullYear(endDate.substring(0,4));
	eDate.setMonth(Number(endDate.substring(5,7)) -1);
	eDate.setDate(endDate.substring(8,10));
	eDate.setHours(endTime.substring(0,2));
	eDate.setMinutes(Number(endTime.substring(3,5)));
	
	var caleDate = new Date(new Date().getTime()+timeGap);
	caleDate.setFullYear(endDate.substring(0,4));
	caleDate.setMonth(Number(endDate.substring(5,7)) -1);
	caleDate.setDate(endDate.substring(8,10));
	caleDate.setHours(endTime.substring(0,2));
	caleDate.setMinutes(Number(endTime.substring(3,5)));
	
	//종료일자가 시작일자보다 큰경우 입력 불가
	if(sDate>caleDate)
	{
		$(endDateObj).val("");
		$(endTimeObj).val("");
		alertMessage1(typeof COMMON_MSG0110 == "undefined"?"종료일이 시작일자보다 작을수 없습니다.":COMMON_MSG0110);
		return "";
	}
	else
	{
		var timeDiff = eDate.getTime() - sDate.getTime();
		if(timeDiff == 0) timeDiff = 60000;
		
		var tempTime = Math.floor((timeDiff)/60000);

		return tempTime;
	}
}


/** 
 * 두 날짜의 사이값을 계산한다.
 */
function  getDayInterval(startDate, endDate)
{
    var endDateYear  = parseInt(endDate.substring(0,4), 10);
    var endDateMonth = parseInt(endDate.substring(4,6), 10);
    var endDateDate  = parseInt(endDate.substring(6,8), 10);

    var startDateYear  = parseInt(startDate.substring(0,4), 10);
    var startDateMonth = parseInt(startDate.substring(4,6), 10);
    var startDateDate  = parseInt(startDate.substring(6,8), 10);

    var endDate = new Date(endDateYear, endDateMonth-1, endDateDate);
    var startDate = new Date(startDateYear, startDateMonth-1, startDateDate);

    var differ = (((((endDate - startDate)/1000)/60)/60)/24);
    
    if (differ < 0)
    {
    	differ = differ * (-1);
    }
    
    return differ;
}

function  getMonthInterval(startDate, endDate)
{
	if(startDate.indexOf("-") > -1) startDate = startDate.split("-").join("");
	if(endDate.indexOf("-") > -1) endDate = endDate.split("-").join("");
	
	var endDateYear,endDateMonth, startDateYear, startDateMonth, endDateDate, startDateDate;

	endDateYear  = parseInt(endDate.substring(0,4), 10);
    endDateMonth = parseInt(endDate.substring(4,6), 10);
    endDateDate  = "01";

    startDateYear  = parseInt(startDate.substring(0,4), 10);
    startDateMonth = parseInt(startDate.substring(4,6), 10);
    startDateDate  = "01";

	var months = 0;
	var endDate = new Date(endDateYear, endDateMonth+1, endDateDate);
    var startDate = new Date(startDateYear, startDateMonth+1, startDateDate);
	
    months = (endDate.getFullYear() - startDate.getFullYear()) * 12;
    months -= startDate.getMonth();
    months += endDate.getMonth();
    
    return months;
}


/**
 * 오늘 날짜에서 지정된 날짜 만큼 뺀 날짜를 리턴한다.
 */
function getMinusDay(day)
{
	var gubun = "-";

	var now = new Date(new Date().getTime()+timeGap);
	var newdate = new Date(new Date().getTime()+timeGap);
	
	sdate = newdate.getTime();
	edate = sdate - (day*24*60*60*1000);   
	
	newdate.setTime(edate);
	
	var year  = newdate.getFullYear();
	var month = newdate.getMonth()+1;
	var day   = newdate.getDate();

	if (month < 10)
	{
		month = "0" + month;
	}
	if (day < 10)
	{
		day = "0" + day;
	}
	return year+gubun+month+gubun+day;
}
/**
 * date-day - 해당날짜기준 day일전 날짜 구하기.
 */
function getMinusDay2(date1,day)
{
	var gubun = "-";
	var setdate = new Date(new Date().getTime()+timeGap);
	if(typeof date1 == "string" )
	{
		var dateArr = new Array();
		dateArr = date1.split('-');

		setdate.setYear(dateArr[0]);
		setdate.setMonth(dateArr[1]-1);
		setdate.setDate(dateArr[2]);
	}else{
		setdate = date1;
	}
	
	var now = new Date(new Date().getTime()+timeGap);
	var newdate = new Date(setdate);
	
	sdate = "";
	edate = "";
	sdate = newdate.getTime(); //date1의 날짜
	edate = sdate - (day*24*60*60*1000);   //day를 뺀 날짜
	
	newdate.setTime(edate);
	
	var year  = newdate.getFullYear();
	var month = newdate.getMonth()+1;
	var day   = newdate.getDate();

	if (month < 10)
	{
		month = "0" + month;
	}
	if (day < 10)
	{
		day = "0" + day;
	}
	return year+gubun+month+gubun+day;
}

if(typeof COMMON_sun == "undefined") COMMON_sun ="일요일";
if(typeof COMMON_mon == "undefined") COMMON_mon ="월요일";
if(typeof COMMON_tue == "undefined") COMMON_tue ="화요일";
if(typeof COMMON_wed == "undefined") COMMON_wed ="수요일";
if(typeof COMMON_thu == "undefined") COMMON_thu ="목요일";
if(typeof COMMON_fri == "undefined") COMMON_fri ="금요일";
if(typeof COMMON_sat == "undefined") COMMON_sat ="토요일";

/**
 * 요일을 구한다. 
 */
var common_comDays = new Array(COMMON_sun, COMMON_mon, COMMON_tue, COMMON_wed, COMMON_thu, COMMON_fri, COMMON_sat);
function setComDays(dateObj, _dayObjId)
{
	var str  = onlyNum(dateObj.value); 
	if(!chkDate(str)) return;

	if(str.length == 8)
	{
		var days = new Date(str.substring(0, 4), str.substring(4, 6) - 1, str.substring(6));
		var dayObj = M$(_dayObjId);
	
		dayObj.value = common_comDays[days.getDay()];
	}
}
/**
 * 요일을 반환
 */
function setComDay(dateStr, lang)
{
	var str  = onlyNum(dateStr); 
	if(!chkDate(str)) return;
	
	if(str.length == 8)
	{
		var days = new Date(str.substring(0, 4), str.substring(4, 6) - 1, str.substring(6));
		return common_comDays[days.getDay()];
	}
}

/**
 * 년도입력시 유효성을 Check한다. (1900~2100의 값만 입력가능하다)
 */
function checkDateValid(reqName, conName)
{
	var requireObj = M$(reqName);
	
    if (trim(requireObj.value) > "2100" || trim(requireObj.value) < "1900")
    {
        //올바른 날짜 형식이 아닙니다.
        alertMessage1(conName + COMMON_CMSG012);
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
 * 날짜 입력시 keyUp event에 준다.
 */
function dateFormatMonth(dateObj, e)
{
	
    var e = e || window.event; 
    if (e.keyCode) code = e.keyCode; 
    else if (e.which) code = e.which; 
    
	if( code == 9 || code == 16 || (code >= 37 && code <= 40) ) 
	{
		dateObj.focus();
		return;
	}
	var str  = onlyNum(dateObj.value);
	
	var leng = str.length;
	var n = dateObj.name;
	var f = "document.frm.";
	if (dateObj.value.length == 7)
	{		
		if(!chkDate(str)) dateObj.value = "";
	}
	else
	{
		switch(leng)
		{
			case 0 :
			case 1 : 
		  	case 2 : 
		  	case 3 : 
		  	case 4 : dateObj.value = str; break;
		  	case 5 : break;
		  	case 6 : dateObj.value = str.substring(0, 4) + "-" + str.substring(4);		  	
					 if(!chkDate(str)) dateObj.value = "";
					 break;					  	
		}
	}
}

/**
 * submit하기 바로 전에 호출한다.
 * 입력된 날짜가 올바른 형식인지 체크한다.
 * ex) if(!validDate(form.dateType)) return;
 */
function validDateMonth(obj)
{
	var dateStr = dateToData(obj.value);

	if (dateStr.length == 0)
	{
		return true;
	}

	if (dateStr.length != 6)
	{
		// "올바른 날짜 형식이 아닙니다."
		alert(COMMON_CMSG012);
		obj.value = "";
		obj.focus();
		return false;
	}

	if (!chkDateMonth(dateStr))
	{
		obj.value = "";
		obj.focus();
		return false;
	}
	
	return true;
}

/**
 * 현재 일자의 유효성을 검증한다.
 * beforeSave 가 true  : 저장 바로 전으로 8자리인지 아닌지도 체크한다.
 *              false : 없거나 false인경우 값을 자릿수는 상관없다.
 */
function chkDateMonth(str)
{
	if(str.length == 6)
	{
 		vDate = new Date(str.substring(0,4), (str.substring(4,6)-1));
 		if(vDate.getFullYear() != str.substring(0, 4) || vDate.getMonth() != str.substring(4, 6)-1 )
 		{
 			// "올바른 날짜 형식이 아닙니다."
   			alert(COMMON_CMSG012);
   			return false;
  		}
		return true;
	}
	else if(str.length == 8)
	{ 
 		vDate = new Date(str.substring(0, 4), (str.substring(4, 6)-1), str.substring(6));
 		
		if(vDate.getFullYear() != str.substring(0, 4) || vDate.getMonth() != str.substring(4, 6)-1 || vDate.getDate() != str.substring(6))
		{
			// "올바른 날짜 형식이 아닙니다."
   			alert(COMMON_CMSG012);
   			return false;
  		}
  		return true;
	}
	
	return false;
}

/**
 * 설정날짜 = 현재 월 + mon
 * 날자를 구해 준다.
 */
function getMinusMonth(mon)
{
	var gubun = "-";
	
	var today = new Date(new Date().getTime()+timeGap);

	// 계산한 날짜를 등록한다.
	today.setMonth(today.getMonth() + parseInt(mon));

	var year  = today.getFullYear();
	var month = today.getMonth()+1;
	
	if (month < 10)
	{
		month = "0" + month;
	}
	
	return year+gubun+month;	
}

/**
 * 설정날짜 = 현재 월 + mon + day
 * 날자를 구해 준다.
 * 
 */
function getMinusMonth2(dateObj, mon)
{
	var gubun = "-";
	
	var today = dateObj; 
	
	// 계산한 날짜를 등록한다.
	today.setMonth(today.getMonth() + parseInt(mon));
	
	var year  = today.getFullYear();
	var month = today.getMonth()+1;
	var day   = today.getDate();
	
	if (month < 10)
	{
		month = "0" + month;
	}
	
	if (day < 10)
	{
		day = "0" + day;
	}
	
	return year+gubun+month+gubun+day;	
}

Date.prototype.getWeek = function (dowOffset) {
    dowOffset = typeof(dowOffset) == 'int' ? dowOffset : 0; //default dowOffset to zero
    var newYear = new Date(this.getFullYear(),0,1);
    var day = newYear.getDay() - dowOffset; //the day of week the year begins on
    day = (day >= 0 ? day : day + 7);
    this.setMonth( this.getMonth() - 1);
    var daynum = Math.floor((this.getTime() - newYear.getTime() - 
    (this.getTimezoneOffset()-newYear.getTimezoneOffset())*60000)/86400000) + 1;
    var weeknum;
    //if the year starts before the middle of a week
    if(day < 4) {
        weeknum = Math.floor((daynum+day-1)/7) + 1;
        if(weeknum > 52) {
            nYear = new Date(this.getFullYear() + 1,0,1);
            nday = nYear.getDay() - dowOffset;
            nday = nday >= 0 ? nday : nday + 7;
            weeknum = nday < 4 ? 1 : 53;
        }
    }
    else {
        weeknum = Math.floor((daynum+day-1)/7);
    }
    return weeknum;
}

Date.prototype.getMonday = function(){
	var day = this.getDay(); 
	var date = this.getDate() - day; 
	
	  // Grabbing Start/End Dates 
	var StartDate = new Date(this.setDate(date + 1));  
	//return StartDate.toLocaleDateString(); 
	return StartDate;
}

Date.prototype.getSunday = function(){
	var day = this.getDay(); 
    var date = this.getDate() - day; 

    var EndDate = new Date(this.setDate(date + 7)); 
    return EndDate; 
 }

Date.prototype.format = function(f) {
	if (!this.valueOf()) return " ";

	var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
	var d = this;
	
	return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
		switch ($1) {
			case "yyyy": return d.getFullYear();
			case "yy": return (d.getFullYear() % 1000).zf(2);
			case "MM": return (d.getMonth() + 1).zf(2);
			case "dd": return d.getDate().zf(2);
			case "E": return weekName[d.getDay()];
			case "HH": return d.getHours().zf(2);
			case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
			case "mm": return d.getMinutes().zf(2);
			case "ss": return d.getSeconds().zf(2);
			case "a/p": return d.getHours() < 12 ? "오전" : "오후";
			default: return $1;
		}
	});
}
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};


function getDateOfWeek(w, y) {
    var simple = new Date(y, 0, 1 + (w - 1) * 7);
    var dow = simple.getDay();
    var ISOweekStart = simple;
    if (dow <= 4)
        ISOweekStart.setDate(simple.getDate() - simple.getDay() + 1);
    else
        ISOweekStart.setDate(simple.getDate() + 8 - simple.getDay());
    return ISOweekStart;
}
/**
 * 금 주의 월요일 구하기.
 */
function monOfThisWeek(dateStr)
{
	var str  = onlyNum(dateStr); 
	if(!chkDate(str)) return;

	if(str.length == 8)
	{
		var days = new Date(str.substring(0, 4), str.substring(4, 6) - 1, str.substring(6));
		
		if(days.getDay()=='0'){
			days.setDate(days.getDate()+1);
		}else if(days.getDay()=='1'){
		}else{
			days.setDate(days.getDate()-(days.getDay()-1));
		}
		return leadingZeros(days.getFullYear(),4)+"-"+leadingZeros(days.getMonth()+1,2)+"-"+leadingZeros(days.getDate(),2);
	}else{
		return dateStr;
	}
}

function leadingZeros(n, digits) {

    var zero = '';
    n = n.toString();
    if (n.length < digits) {
        for (i = 0; i < digits - n.length; i++)
            zero += '0';
    }
    return zero + n;
}

/**
 * 오늘 일자(년월일시분초)를 리턴한다.
 */
function getNowDateTime(isHMS) 
{
	var hyphen = "-";
	var colon  = ":";
	var result;
	
	today = new Date(new Date().getTime()+timeGap);
	
	var year  = today.getFullYear();
	var month = today.getMonth()+1;
	var day   = today.getDate();
	var hours   = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	
	// 월 이 10보다 작을 경우 앞에 0 을 붙인다.
	if (month < 10)
	{
		month = "0" + month;
	}
	// 일 이 10보다 작을 경우 앞에 0 을 붙인다.
	if (day < 10)
	{
		day = "0" + day;
	}
	
	// 시간 이 10보다 작을 경우 앞에 0 을 붙인다.
	if (hours < 10)
	{
		hours = "0" + hours;
	}
	// 분 이 10보다 작을 경우 앞에 0 을 붙인다.
	if (minutes < 10)
	{
		minutes = "0" + minutes;
	}
	// 초 가 10보다 작을 경우 앞에 0 을 붙인다.
	if (seconds < 10)
	{
		seconds = "0" + seconds;
	}
	
	// 년-월-일 을 result에 담는다.
	result = year+hyphen+month+hyphen+day;
	
	// 날짜와 시간 사이에 공백을 한 칸 추가한다.
	result += " ";
	
	// isHMS가 true인 경우 초까지 덧붙인다.
	if(isHMS === true)
	{
		// 시:분:초 를 result에 덧붙인다.
		result += hours+colon+minutes+colon+seconds;
	}
	else
	{
		// 시:분 을 result에 덧붙인다.
		result += hours+colon+minutes;
	}
	return result;
	
	
}

/**
 * 해당 년/월의 마지막 날을 리턴
 */
function getLastDayOfMonth(year, month){
	var monthLength = new Array(12);
	
	monthLength[0] = "31";
	var iyear = parseInt(year);
	if(((iyear % 4 == 0) && (iyear % 100 != 0)) || (iyear % 400 == 0))
	{
		monthLength[1] = "29";
	}
	else
	{
		monthLength[1] = "28";
	}
	monthLength[2] = "31";
	monthLength[3] = "30";
	monthLength[4] = "31";
	monthLength[5] = "30";
	monthLength[6] = "31";
	monthLength[7] = "31";
	monthLength[8] = "30";
	monthLength[9] = "31";
	monthLength[10] = "30";
	monthLength[11] = "31";
	
	return monthLength[parseInt(month)-1];
}

/**
 * yyyyMM, yyyy-MM에 마지막일자를 더해서 리턴
 * ex) 2018-07 -> 2018-07-31, 201807 -> 20180731
 */
function plusLastDayOfMonth(date){
	
	var hyphen = false;
	var result = "";
	
	if (date.indexOf("-") != -1) {
		date = date.replace(/-/gi,"");
		hyphen = true;
	}
	
	var year = date.substring(0,4);
	var month = date.substring(4);
	
	var day = getLastDayOfMonth(year, month);
	
	if (hyphen) {
		result = year + "-" + month + "-" + day;
	} else{
		result = year + month + day;
	}
	
	return result;
}

/**
 * 작업 일자를 리턴받는다.
 */
function getWorkDay(interval) 
{
	var gubun = "-";

	var today = new Date(new Date().getTime()+timeGap);
	
	var hours = today.getHours();
	var minutes = today.getMinutes();
	if (hours < 10)
	{
		hours = "0" + hours;
	}
	if (minutes < 10)
	{
		minutes = "0" + minutes;
	}
	var hhmm = ""+hours+minutes;
	
	if(workStartBaseTime>hhmm) {
		sdate = today.getTime();
		edate = sdate + (-1*24*60*60*1000);   
		
		today.setTime(edate);
	}
	
	if(typeof interval != 'undefined') {
		sdate = today.getTime();
		edate = sdate + (interval*24*60*60*1000);   
		
		today.setTime(edate);
	}
	
	var year  = today.getFullYear();
	var month = today.getMonth()+1;
	var day   = today.getDate();
	
	if (month < 10)
	{
		month = "0" + month;
	}
	if (day < 10)
	{
		day = "0" + day;
	}
	return year+gubun+month+gubun+day;
}

/**
 * Date From DB Server
 * 
 * @param objName - this object receive data
 * @param format - return date format (yyyy-MM-dd HH:mm:ss)
 * @param intervalType - one of YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND
 * @param interval - size of interval
 * @param callBack - callBack function name
 */
function setTimeStamp(_params){
	if(typeof _params == "object" && typeof _params["objName"] != "undefined")
	{
		_format = _params["format"];
		_intervalType = _params["intervalType"];
		_interval = _params["interval"];
		if(typeof _format == 'undefined' || _format == '') _format = 'yyyy-MM-dd';
		if(typeof _intervalType == 'undefined' || _intervalType == '') _intervalType = 'DAY';
		if(typeof _interval == 'undefined' || _interval == '') _interval = 0;
		
		var url = contextPath + "/getDate.do";
		var param = "strutsAction="+strutsActionGetTimeStamp+"&interval="+_interval+"&intervalType="+_intervalType+"&format="+_format;
		$.post(url, param, function(ajaxXmlDoc){
		    var date = parseXmlDoc(ajaxXmlDoc, 'DESC');
		    M$(_params["objName"]).value = date;
		    
		    if(!(typeof _params["callBack"] == "undefined" || _params["callBack"] == ''))
		    	$.globalEval(_params["callBack"]+"('"+_params["objName"]+"');");
		});
	}
}
