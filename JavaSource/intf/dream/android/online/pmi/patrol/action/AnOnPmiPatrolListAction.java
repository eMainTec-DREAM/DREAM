package intf.dream.android.online.pmi.patrol.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.pmi.patrol.form.AnOnPmiPatrolListForm;
import intf.dream.android.online.pmi.patrol.service.AnOnPmiPatrolListService;

/**
 * 온라인버전 순회점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnPmiPatrolList" name="anOnPmiPatrolListForm"
 *                input="/android/online/pmi/patrol/anOnPmiPatrolList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnPmiPatrolListAction extends IfOnlineAuthAction
{
    //PmiPatrol List
    public static final String PMI_LIST 					= "PMI_LIST";
    //Point List
    public static final String POINT_LIST					= "POINT_LIST";
    //Point Hist List
    public static final String POINT_HIST_LIST				= "POINT_HIST_LIST";
    //Wopoint count
    public static final String POINT_COUNT					= "POINT_COUNT";
    //INSERT POINT
    public static final String POINT_INSERT					= "POINT_INSERT";
    //UPDATE POINT
    public static final String POINT_UPDATE					= "POINT_UPDATE";
    //CHECK STATUS
    public static final String CHECK_STATUS					= "CHECK_STATUS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnPmiPatrolListForm anOnPmiPatrolListForm = (AnOnPmiPatrolListForm) form;
        
        switch (anOnPmiPatrolListForm.getServiceName())
        {
            case AnOnPmiPatrolListAction.PMI_LIST:
            	findPmiPatrolList(request, response, anOnPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPatrolListAction.POINT_LIST:
            	findPointList(request, response, anOnPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPatrolListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, anOnPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPatrolListAction.POINT_COUNT:
            	findWoPointCount(request, response, anOnPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPatrolListAction.POINT_INSERT:
            	insertPoint(request, response, anOnPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPatrolListAction.POINT_UPDATE:
            	updatePoint(request, response, anOnPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPatrolListAction.CHECK_STATUS:
            	findStatus(request, response, anOnPmiPatrolListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiPatrolList(HttpServletRequest request, HttpServletResponse response, AnOnPmiPatrolListForm anOnPmiPatrolListForm) throws Exception
    {
    	AnOnPmiPatrolListService anOnPmiPatrolListService = (AnOnPmiPatrolListService) getBean("anOnPmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPatrolListService.findPmiPatrolList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, AnOnPmiPatrolListForm anOnPmiPatrolListForm) throws Exception
    {
    	AnOnPmiPatrolListService anOnPmiPatrolListService = (AnOnPmiPatrolListService) getBean("anOnPmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPatrolListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, AnOnPmiPatrolListForm anOnPmiPatrolListForm) throws Exception
    {
    	AnOnPmiPatrolListService anOnPmiPatrolListService = (AnOnPmiPatrolListService) getBean("anOnPmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPatrolListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, AnOnPmiPatrolListForm anOnPmiPatrolListForm) throws Exception
    {
    	AnOnPmiPatrolListService anOnPmiPatrolListService = (AnOnPmiPatrolListService) getBean("anOnPmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPatrolListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, AnOnPmiPatrolListForm anOnPmiPatrolListForm) throws Exception
    {
    	AnOnPmiPatrolListService anOnPmiPatrolListService = (AnOnPmiPatrolListService) getBean("anOnPmiPatrolListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPatrolListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiPatrolListForm anOnPmiPatrolListForm) throws Exception
    {
    	AnOnPmiPatrolListService anOnPmiPatrolListService = (AnOnPmiPatrolListService) getBean("anOnPmiPatrolListService");
    	List list = getListData(request);
    	int qty = anOnPmiPatrolListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiPatrolListForm anOnPmiPatrolListForm) throws Exception
    {
    	AnOnPmiPatrolListService anOnPmiPatrolListService = (AnOnPmiPatrolListService) getBean("anOnPmiPatrolListService");
    	List list = getListData(request);
    	int qty = anOnPmiPatrolListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
