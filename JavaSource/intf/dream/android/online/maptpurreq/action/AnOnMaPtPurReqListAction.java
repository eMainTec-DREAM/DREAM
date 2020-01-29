package intf.dream.android.online.maptpurreq.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.maptpurreq.form.AnOnMaPtPurReqListForm;
import intf.dream.android.online.maptpurreq.service.AnOnMaPtPurReqListService;

/**
 * 온라인버전 출고 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnMaPtPurReqList" name="anOnMaPtPurReqListForm"
 *                input="/android/online/maptpurreq/anOnMaPtPurReqList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnMaPtPurReqListAction extends IfOnlineAuthAction
{
    //MAPTPURREQ LIST
    public static final String MAPTPURREQ_LIST 					= "MAPTPURREQ_LIST";
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
        AnOnMaPtPurReqListForm anOnMaPtPurReqListForm = (AnOnMaPtPurReqListForm) form;
        
        switch (anOnMaPtPurReqListForm.getServiceName())
        {
            case AnOnMaPtPurReqListAction.MAPTPURREQ_LIST:
            	findMaPtPurReqList(request, response, anOnMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnMaPtPurReqListAction.MAPTPURREQ_DELETE:
            	deleteMaPtPurReq(request, response, anOnMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnMaPtPurReqListAction.MAPTPURREQ_INSERT:
            	insertMaPtPurReq(request, response, anOnMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnMaPtPurReqListAction.MAPTPURREQ_UPDATE:
            	updateMaPtPurReq(request, response, anOnMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnMaPtPurReqListAction.MAPTPURREQ_CONFIRM:
            	confirmMaPtPurReq(request, response, anOnMaPtPurReqListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findMaPtPurReqList(HttpServletRequest request, HttpServletResponse response, AnOnMaPtPurReqListForm anOnMaPtPurReqListForm) throws Exception
    {
    	AnOnMaPtPurReqListService anOnMaPtPurReqListService = (AnOnMaPtPurReqListService) getBean("anOnMaPtPurReqListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnMaPtPurReqListService.findMaPtPurReqList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteMaPtPurReq(HttpServletRequest request, HttpServletResponse response, AnOnMaPtPurReqListForm anOnMaPtPurReqListForm) throws Exception
    {
    	AnOnMaPtPurReqListService anOnMaPtPurReqListService = (AnOnMaPtPurReqListService) getBean("anOnMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = anOnMaPtPurReqListService.deleteMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
    private void insertMaPtPurReq(HttpServletRequest request, HttpServletResponse response, AnOnMaPtPurReqListForm anOnMaPtPurReqListForm) throws Exception
    {
    	AnOnMaPtPurReqListService anOnMaPtPurReqListService = (AnOnMaPtPurReqListService) getBean("anOnMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = anOnMaPtPurReqListService.insertMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
    private void updateMaPtPurReq(HttpServletRequest request, HttpServletResponse response, AnOnMaPtPurReqListForm anOnMaPtPurReqListForm) throws Exception
    {
    	AnOnMaPtPurReqListService anOnMaPtPurReqListService = (AnOnMaPtPurReqListService) getBean("anOnMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = anOnMaPtPurReqListService.updateMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
    private void confirmMaPtPurReq(HttpServletRequest request, HttpServletResponse response, AnOnMaPtPurReqListForm anOnMaPtPurReqListForm) throws Exception
    {
    	AnOnMaPtPurReqListService anOnMaPtPurReqListService = (AnOnMaPtPurReqListService) getBean("anOnMaPtPurReqListService");
    	List list = getListData(request);
    	int qty = anOnMaPtPurReqListService.confirmMaPtPurReq(list);
    	setMessage(response, "","OK");
    }
}
