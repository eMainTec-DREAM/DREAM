package intf.dream.bee.maptpurreq.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.maptpurreq.dto.BeeMaPtPurReqCommonDTO;
import intf.dream.bee.maptpurreq.form.BeeMaPtPurReqListForm;
import intf.dream.bee.maptpurreq.service.BeeMaPtPurReqListService;

/**
 * �¶��ι��� ��� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeMaPtPurReqList" name="beeMaPtPurReqListForm"
 *                input="/bee/maptpurreq/beeMaPtPurReqList.jsp" scope="request"
 *                validate="false"
 */
public class BeeMaPtPurReqListAction extends IfOnlineAuthAction
{
    //MAPTPURREQ LIST
    public static final String MAPTPURREQ_LIST 						= "MAPTPURREQ_LIST";
    //MAPTPURREQ COUNT
    public static final String MAPTPURREQ_COUNT 					= "MAPTPURREQ_COUNT";
    //MAPTPURREQ DELETE
    public static final String MAPTPURREQ_DELETE 					= "MAPTPURREQ_DELETE";
    //MAPTPURREQ INSERT
    public static final String MAPTPURREQ_INSERT 					= "MAPTPURREQ_INSERT";
    //MAPTPURREQ UPDATE
    public static final String MAPTPURREQ_UPDATE 					= "MAPTPURREQ_UPDATE";
    
    public static final String MAPTPURREQ_CONFIRM 					= "MAPTPURREQ_CONFIRM";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeMaPtPurReqListForm beeMaPtPurReqListForm = (BeeMaPtPurReqListForm) form;
        
        switch (beeMaPtPurReqListForm.getServiceName())
        {
            case BeeMaPtPurReqListAction.MAPTPURREQ_LIST:
            	findMaPtPurReqList(request, response, beeMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeMaPtPurReqListAction.MAPTPURREQ_COUNT:
            	findMaPtPurReqCount(request, response, beeMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeMaPtPurReqListAction.MAPTPURREQ_DELETE:
            	deleteMaPtPurReq(request, response, beeMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeMaPtPurReqListAction.MAPTPURREQ_INSERT:
            	insertMaPtPurReq(request, response, beeMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeMaPtPurReqListAction.MAPTPURREQ_UPDATE:
            	updateMaPtPurReq(request, response, beeMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeMaPtPurReqListAction.MAPTPURREQ_CONFIRM:
            	confirmMaPtPurReq(request, response, beeMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findMaPtPurReqList(HttpServletRequest request, HttpServletResponse response, BeeMaPtPurReqListForm beeMaPtPurReqListForm) throws Exception
    {
    	BeeMaPtPurReqListService beeMaPtPurReqListService = (BeeMaPtPurReqListService) getBean("beeMaPtPurReqListService");
    	BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO = beeMaPtPurReqListForm.getBeeMaPtPurReqCommonDTO();
    	Map map = getMapData(request);

    	beeMaPtPurReqCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeMaPtPurReqCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeMaPtPurReqCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeMaPtPurReqListService.findMaPtPurReqList(beeMaPtPurReqCommonDTO, map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }

    private void findMaPtPurReqCount(HttpServletRequest request, HttpServletResponse response, BeeMaPtPurReqListForm beeMaPtPurReqListForm) throws Exception
    {
    	BeeMaPtPurReqListService beeMaPtPurReqListService = (BeeMaPtPurReqListService) getBean("beeMaPtPurReqListService");
    	BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO = beeMaPtPurReqListForm.getBeeMaPtPurReqCommonDTO();
    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeMaPtPurReqListService.findMaPtPurReqCount(beeMaPtPurReqCommonDTO, map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteMaPtPurReq(HttpServletRequest request, HttpServletResponse response, BeeMaPtPurReqListForm beeMaPtPurReqListForm) throws Exception
    {
    	BeeMaPtPurReqListService beeMaPtPurReqListService = (BeeMaPtPurReqListService) getBean("beeMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = beeMaPtPurReqListService.deleteMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
    private void insertMaPtPurReq(HttpServletRequest request, HttpServletResponse response, BeeMaPtPurReqListForm beeMaPtPurReqListForm) throws Exception
    {
    	BeeMaPtPurReqListService beeMaPtPurReqListService = (BeeMaPtPurReqListService) getBean("beeMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = beeMaPtPurReqListService.insertMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
    private void updateMaPtPurReq(HttpServletRequest request, HttpServletResponse response, BeeMaPtPurReqListForm beeMaPtPurReqListForm) throws Exception
    {
    	BeeMaPtPurReqListService beeMaPtPurReqListService = (BeeMaPtPurReqListService) getBean("beeMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = beeMaPtPurReqListService.updateMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
    private void confirmMaPtPurReq(HttpServletRequest request, HttpServletResponse response, BeeMaPtPurReqListForm beeMaPtPurReqListForm) throws Exception
    {
    	BeeMaPtPurReqListService beeMaPtPurReqListService = (BeeMaPtPurReqListService) getBean("beeMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = beeMaPtPurReqListService.confirmMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
}
