package intf.dream.android.online.doc.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.doc.form.AnOnDocListForm;
import intf.dream.android.online.doc.service.AnOnDocListService;

/**
 * �¶��ι��� �������
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/anOnDocList" name="anOnDocListForm"
 *                input="/android/online/doc/anOnDocList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnDocListAction extends IfOnlineAuthAction {
	// DOC_LIST
	public static final String DOC_LIST = "DOC_LIST";
	// FILE_URL
	public static final String DOC_FILE = "DOC_FILE";

	protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward returnActionForward = null;
		AnOnDocListForm anOnDocListForm = (AnOnDocListForm) form;

		switch (anOnDocListForm.getServiceName()) {
		case AnOnDocListAction.DOC_LIST:
			findDocList(request, response, anOnDocListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnDocListAction.DOC_FILE:
			findFileUrl(request, response, anOnDocListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findDocList(HttpServletRequest request, HttpServletResponse response, AnOnDocListForm anOnDocListForm)
			throws Exception {
		AnOnDocListService anOnDocListService = (AnOnDocListService) getBean("anOnDocListService");

		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = anOnDocListService.findDocList(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	
	private void findFileUrl(HttpServletRequest request, HttpServletResponse response, AnOnDocListForm anOnDocListForm)
			throws Exception {
		AnOnDocListService anOnDocListService = (AnOnDocListService) getBean("anOnDocListService");
		
		Map map = getMapData(request);
		
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = anOnDocListService.findFileUrl(map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
}
