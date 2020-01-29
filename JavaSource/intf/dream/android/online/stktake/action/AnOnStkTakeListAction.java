package intf.dream.android.online.stktake.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.stktake.form.AnOnStkTakeListForm;
import intf.dream.android.online.stktake.service.AnOnStkTakeListService;

/**
 * 온라인버전 부품실사 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnStkTakeList" name="anOnStkTakeListForm"
 *                input="/android/online/stktake/anOnStkTakeList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnStkTakeListAction extends IfOnlineAuthAction
{
    //STKTAKE LIST
    public static final String STKTAKE_LIST 					= "STKTAKE_LIST";
    //STKTAKE DELETE
    public static final String STKTAKE_DELETE 					= "STKTAKE_DELETE";
    //STKTAKEITEM_LIST
    public static final String STKTAKEITEM_LIST 				= "STKTAKEITEM_LIST";
    //STKTAKEITEM_DELETE
    public static final String STKTAKEITEM_DELETE 				= "STKTAKEITEM_DELETE";
    //STKTAKEITEM_INSERT
    public static final String STKTAKEITEM_INSERT 				= "STKTAKEITEM_INSERT";
    //STKTAKEITEM_UPDATE
    public static final String STKTAKEITEM_UPDATE 				= "STKTAKEITEM_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnStkTakeListForm anOnStkTakeListForm = (AnOnStkTakeListForm) form;
        
        switch (anOnStkTakeListForm.getServiceName())
        {
            case AnOnStkTakeListAction.STKTAKE_LIST:
            	findStkTakeList(request, response, anOnStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnStkTakeListAction.STKTAKE_DELETE:
            	deleteStkTake(request, response, anOnStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnStkTakeListAction.STKTAKEITEM_LIST:
            	findStkTakeItemList(request, response, anOnStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnStkTakeListAction.STKTAKEITEM_DELETE:
            	deleteStkTakeItem(request, response, anOnStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnStkTakeListAction.STKTAKEITEM_INSERT:
            	insertStkTakeItem(request, response, anOnStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnStkTakeListAction.STKTAKEITEM_UPDATE:
            	updateStkTakeItem(request, response, anOnStkTakeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findStkTakeList(HttpServletRequest request, HttpServletResponse response, AnOnStkTakeListForm anOnStkTakeListForm) throws Exception
    {
    	AnOnStkTakeListService anOnStkTakeListService = (AnOnStkTakeListService) getBean("anOnStkTakeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnStkTakeListService.findStkTakeList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteStkTake(HttpServletRequest request, HttpServletResponse response, AnOnStkTakeListForm anOnStkTakeListForm) throws Exception
    {
    	AnOnStkTakeListService anOnStkTakeListService = (AnOnStkTakeListService) getBean("anOnStkTakeListService");
    	List list = getListData(request);
    	int qty = anOnStkTakeListService.deleteStkTake(list);
    	setMessage(response, "","OK");
    }

    private void findStkTakeItemList(HttpServletRequest request, HttpServletResponse response, AnOnStkTakeListForm anOnStkTakeListForm) throws Exception
    {
    	AnOnStkTakeListService anOnStkTakeListService = (AnOnStkTakeListService) getBean("anOnStkTakeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnStkTakeListService.findStkTakeItemList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteStkTakeItem(HttpServletRequest request, HttpServletResponse response, AnOnStkTakeListForm anOnStkTakeListForm) throws Exception
    {
    	AnOnStkTakeListService anOnStkTakeListService = (AnOnStkTakeListService) getBean("anOnStkTakeListService");
    	List list = getListData(request);
    	int qty = anOnStkTakeListService.deleteStkTakeItem(list);
    	setMessage(response, "","OK");
    }
    private void insertStkTakeItem(HttpServletRequest request, HttpServletResponse response, AnOnStkTakeListForm anOnStkTakeListForm) throws Exception
    {
    	AnOnStkTakeListService anOnStkTakeListService = (AnOnStkTakeListService) getBean("anOnStkTakeListService");
    	List list = getListData(request);
    	int qty = anOnStkTakeListService.insertStkTakeItem(list);
    	setMessage(response, "","OK");
    }
    private void updateStkTakeItem(HttpServletRequest request, HttpServletResponse response, AnOnStkTakeListForm anOnStkTakeListForm) throws Exception
    {
    	AnOnStkTakeListService anOnStkTakeListService = (AnOnStkTakeListService) getBean("anOnStkTakeListService");
    	List list = getListData(request);
    	int qty = anOnStkTakeListService.updateStkTakeItem(list);
    	setMessage(response, "","OK");
    }
}
