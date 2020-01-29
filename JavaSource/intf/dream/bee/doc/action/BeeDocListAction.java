package intf.dream.bee.doc.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.doc.dto.BeeDocCommonDTO;
import intf.dream.bee.doc.form.BeeDocListForm;
import intf.dream.bee.doc.service.BeeDocListService;

/**
 * �¶��ι��� �������
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/beeDocList" name="beeDocListForm"
 *                input="/bee/doc/beeDocList.jsp" scope="request"
 *                validate="false"
 */
public class BeeDocListAction extends IfOnlineAuthAction {
	// DOC_LIST
	public static final String DOC_LIST 	= "DOC_LIST";
	// DOC_COUNT
	public static final String DOC_COUNT 	= "DOC_COUNT";
	// FILE_URL
	public static final String DOC_FILE 	= "DOC_FILE";

	protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward returnActionForward = null;
		BeeDocListForm beeDocListForm = (BeeDocListForm) form;

		switch (beeDocListForm.getServiceName()) {
			case BeeDocListAction.DOC_LIST:
				findDocList(request, response, beeDocListForm);
				returnActionForward = mapping.findForward("jsonPage");
				break;
			case BeeDocListAction.DOC_COUNT:
				findDocCount(request, response, beeDocListForm);
				returnActionForward = mapping.findForward("jsonPage");
				break;
			case BeeDocListAction.DOC_FILE:
				findFileUrl(request, response, beeDocListForm);
				returnActionForward = mapping.findForward("jsonPage");
				break;
			default:
				returnActionForward = mapping.findForward("jsonPage");
				break;
		}

		return returnActionForward;
	}

	private void findDocList(HttpServletRequest request, HttpServletResponse response, BeeDocListForm beeDocListForm)
			throws Exception {
		BeeDocListService beeDocListService = (BeeDocListService) getBean("beeDocListService");
		BeeDocCommonDTO beeDocCommonDTO = beeDocListForm.getBeeDocCommonDTO();
		Map map = getMapData(request);

		beeDocCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeDocCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeDocCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = beeDocListService.findDocList(beeDocCommonDTO, map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}

	private void findDocCount(HttpServletRequest request, HttpServletResponse response, BeeDocListForm beeDocListForm)
			throws Exception {
		BeeDocListService beeDocListService = (BeeDocListService) getBean("beeDocListService");
		BeeDocCommonDTO beeDocCommonDTO = beeDocListForm.getBeeDocCommonDTO();
		Map map = getMapData(request);

		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = beeDocListService.findDocCount(beeDocCommonDTO, map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
	
	private void findFileUrl(HttpServletRequest request, HttpServletResponse response, BeeDocListForm beeDocListForm)
			throws Exception {
		BeeDocListService beeDocListService = (BeeDocListService) getBean("beeDocListService");
		BeeDocCommonDTO beeDocCommonDTO = beeDocListForm.getBeeDocCommonDTO();
		Map map = getMapData(request);
		
		// ����Ʈ�� ��ȸ�Ѵ�.
		List resultList = beeDocListService.findFileUrl(beeDocCommonDTO, map);

		// ��ȸ�� List �� form�� �����Ѵ�.
		super.makeJsonResult(resultList, request, response);
	}
}
