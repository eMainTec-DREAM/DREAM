package intf.dream.android.online.unplan.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.unplan.form.AnOnUnPlanListForm;
import intf.dream.android.online.unplan.service.AnOnUnPlanListService;

/**
 * 온라인버전 돌발작업 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnUnPlanList" name="anOnUnPlanListForm"
 *                input="/android/online/unplan/anOnUnPlanList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnUnPlanListAction extends IfOnlineAuthAction
{
    //UNPLAN LIST
    public static final String UNPLAN_LIST 			= "UNPLAN_LIST";
    //UNPLAN DELETE
    public static final String UNPLAN_DELETE 		= "UNPLAN_DELETE";
    //UNPLAN INSERT
    public static final String UNPLAN_INSERT 		= "UNPLAN_INSERT";
    //UNPLAN UPDATE
    public static final String UNPLAN_UPDATE 		= "UNPLAN_UPDATE";

    //WO FAIL LIST
    public static final String WO_FAIL_LIST 		= "WO_FAIL_LIST";
    //WO FAIL DELETE
    public static final String WO_FAIL_DELETE 		= "WO_FAIL_DELETE";
    //WO FAIL INSERT
    public static final String WO_FAIL_INSERT 		= "WO_FAIL_INSERT";
    //WO FAIL UPDATE
    public static final String WO_FAIL_UPDATE 		= "WO_FAIL_UPDATE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnUnPlanListForm anOnUnPlanListForm = (AnOnUnPlanListForm) form;
        
        switch (anOnUnPlanListForm.getServiceName())
        {
            case AnOnUnPlanListAction.UNPLAN_LIST:
            	findUnPlanList(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnUnPlanListAction.UNPLAN_DELETE:
            	deleteUnPlan(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnUnPlanListAction.UNPLAN_INSERT:
            	insertUnPlan(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnUnPlanListAction.UNPLAN_UPDATE:
            	updateUnPlan(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            case AnOnUnPlanListAction.WO_FAIL_LIST:
            	findWoFailList(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnUnPlanListAction.WO_FAIL_DELETE:
            	deleteWoFail(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnUnPlanListAction.WO_FAIL_INSERT:
            	insertWoFailList(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnUnPlanListAction.WO_FAIL_UPDATE:
            	updateWoFailList(request, response, anOnUnPlanListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            	
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findUnPlanList(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnUnPlanListService.findUnPlanList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void deleteUnPlan(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");
    	List list = getListData(request);
    	int qty = anOnUnPlanListService.deleteUnPlan(list);
    	setMessage(response, "","OK");
    }
    private void insertUnPlan(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");
    	List list = getListData(request);
    	int qty = anOnUnPlanListService.insertUnPlan(list);
    	setMessage(response, "","OK");
    }
    private void updateUnPlan(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");
    	List list = getListData(request);
    	int qty = anOnUnPlanListService.updateUnPlan(list);
    	setMessage(response, "","OK");
    }
    

    private void findWoFailList(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnUnPlanListService.findWoFailList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteWoFail(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");
    	List list = getListData(request);
    	int qty = anOnUnPlanListService.deleteWoFail(list);
    	setMessage(response, "","OK");
    }
    private void insertWoFailList(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");
    	List list = getListData(request);
    	int qty = anOnUnPlanListService.insertWoFailList(list);
    	setMessage(response, "","OK");
    }
    private void updateWoFailList(HttpServletRequest request, HttpServletResponse response, AnOnUnPlanListForm anOnUnPlanListForm) throws Exception
    {
    	AnOnUnPlanListService anOnUnPlanListService = (AnOnUnPlanListService) getBean("anOnUnPlanListService");
    	List list = getListData(request);
    	int qty = anOnUnPlanListService.updateWoFailList(list);
    	setMessage(response, "","OK");
    }
}
