/*====================================================
 * MENU 이동 공통 script
 *===================================================*/

/**
 * 해당 페이지로 이동한다.
 * Menu 선택시 하용, Menu가 달린 모든 페이지와 메인 페이지에서 사용됨
 * - javaworker -
 */
function goMenuPage(pageId)
{	
	var param ="";
	var paramName = "";
	var paramValue = "";
	if(pageId.indexOf("?") >= 0)
	{
		var idParam = pageId.split("?");
		pageId = idParam[0];
		param = idParam[1];
	}
	
	if(param.indexOf("menuId") < 0)
	{
		getTopPage().$("#mainMenu").find(".m_child").map(function(idx){
			
			var _menuId = $(this).prop("id");
			var fileName = findMenuJObj(_menuId).url.split("?")[0];
			if(fileName == pageId)
			{
				param = param + "&menuId="+_menuId;
			}
		});
	}

	if (pageId != null && pageId != "") 
	{	
		if(checkIsUpdate()){
			getTopPage().dhtmlx.confirm(COMMON_CMSG010, function(result){
				if(result)
				{
					beginLoading();
					submitPost(pageId, param, "");
				}
				
			});
		}
		else
		{
			beginLoading();
			submitPost(pageId, param, "");
		}
	}
}

/**
 * 메세지 공통 처리를 위해서 공통 처리한다.
 * ex) if (checkIsUpdate()) return;
 */
function checkIsUpdate()
{
	try
	{
		// 수정 중인경우
		if (isUpdating)
		{
			// "수정중입니다. 수정중인 데이타가 모두 사라져도 괜찮겠습니까?"
			if (confirm(COMMON_CMSG010))
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
}

/**
 * 해당 폼 object의 
 * isDecoratorName  이름이 popupPage 인 경우
 * goMenuPage를 호출하면서 isDecoratorName 값또한 같이 전송한다.
 */
function goDecoMenuPage(formObj, pageId)
{
	//==========================
	if (checkIsUpdate()) return;
	//==========================

	if (formObj.isDecoratorName)
	{
		if (formObj.isDecoratorName.value == "popupPage")
		{
			document.bottomForm.strutsAction.value = "";
			
			// 모래시계
			beginLoading();
			document.bottomForm.target = "";
			document.bottomForm.action = contextPath + "/" + pageId + ".do" +
								"?isDecoratorName=popupPage";
			document.bottomForm.submit();
		}
		else
		{
			goMenuPage(pageId);
		}
	}
}