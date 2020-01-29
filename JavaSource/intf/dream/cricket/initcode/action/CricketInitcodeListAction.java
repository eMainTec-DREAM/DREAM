package intf.dream.cricket.initcode.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;

import common.struts.IfAuthAction;
import intf.dream.cricket.initcode.form.CricketInitcodeListForm;
import intf.dream.cricket.initcode.service.CricketInitcodeListService;

/**
 * initdownload
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/cricketInitcodeList" name="cricketInitcodeListForm"
 *                input="/cricket/initcode/cricketInitcodeList.jsp"
 *                scope="request" validate="false"
 */
public class CricketInitcodeListAction extends IfAuthAction {
	// LANG
	public static final String LANG_FIND 				= "LANG";
	// TACDSYSD
	public static final String CDSYSD_FIND 				= "SYSCODE";
	

	protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward returnActionForward = null;
		CricketInitcodeListForm cricketInitcodeListForm = (CricketInitcodeListForm) form;

		switch (cricketInitcodeListForm.getServiceName()) {
		case CricketInitcodeListAction.LANG_FIND:
			findLangList(request, response, cricketInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case CricketInitcodeListAction.CDSYSD_FIND:
			findSyscodeList(request, response, cricketInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findLangList(HttpServletRequest request, HttpServletResponse response,
			CricketInitcodeListForm cricketInitcodeListForm) throws IOException, ParseException {
		CricketInitcodeListService cricketInitcodeListService = (CricketInitcodeListService) getBean("cricketInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = cricketInitcodeListService.findLangList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findSyscodeList(HttpServletRequest request, HttpServletResponse response,
			CricketInitcodeListForm cricketInitcodeListForm) throws IOException, ParseException {
		CricketInitcodeListService cricketInitcodeListService = (CricketInitcodeListService) getBean("cricketInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = cricketInitcodeListService.findSyscodeList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

}
