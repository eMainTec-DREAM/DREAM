package intf.dream.bee.initcode.action;

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
import intf.dream.bee.initcode.form.BeeInitcodeListForm;
import intf.dream.bee.initcode.service.BeeInitcodeListService;

/**
 * initdownload
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/beeInitcodeList" name="beeInitcodeListForm"
 *                input="/bee/initcode/beeInitcodeList.jsp"
 *                scope="request" validate="false"
 */
public class BeeInitcodeListAction extends IfAuthAction {
	// LANG
	public static final String LANG_FIND 				= "LANG";
	// TACDSYSD
	public static final String CDSYSD_FIND 				= "SYSCODE";
	

	protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward returnActionForward = null;
		BeeInitcodeListForm beeInitcodeListForm = (BeeInitcodeListForm) form;

		switch (beeInitcodeListForm.getServiceName()) {
		case BeeInitcodeListAction.LANG_FIND:
			findLangList(request, response, beeInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case BeeInitcodeListAction.CDSYSD_FIND:
			findSyscodeList(request, response, beeInitcodeListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findLangList(HttpServletRequest request, HttpServletResponse response,
			BeeInitcodeListForm beeInitcodeListForm) throws IOException, ParseException {
		BeeInitcodeListService beeInitcodeListService = (BeeInitcodeListService) getBean("beeInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = beeInitcodeListService.findLangList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void findSyscodeList(HttpServletRequest request, HttpServletResponse response,
			BeeInitcodeListForm beeInitcodeListForm) throws IOException, ParseException {
		BeeInitcodeListService beeInitcodeListService = (BeeInitcodeListService) getBean("beeInitcodeListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = beeInitcodeListService.findSyscodeList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

}
