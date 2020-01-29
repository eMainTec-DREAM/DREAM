package intf.dream.android.online.pmi.routine.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.pmi.routine.form.AnOnPmiRoutineListForm;
import intf.dream.android.online.pmi.routine.service.AnOnPmiRoutineListService;

/**
 * 온라인버전 정기점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnPmiRoutineList" name="anOnPmiRoutineListForm"
 *                input="/android/online/pmi/routine/anOnPmiRoutineList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnPmiRoutineListAction extends IfOnlineAuthAction
{
    //PmiRoutine List
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
        AnOnPmiRoutineListForm anOnPmiRoutineListForm = (AnOnPmiRoutineListForm) form;
        
        switch (anOnPmiRoutineListForm.getServiceName())
        {
            case AnOnPmiRoutineListAction.PMI_LIST:
            	findPmiRoutineList(request, response, anOnPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiRoutineListAction.POINT_LIST:
            	findPointList(request, response, anOnPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiRoutineListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, anOnPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiRoutineListAction.POINT_COUNT:
            	findWoPointCount(request, response, anOnPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiRoutineListAction.POINT_INSERT:
            	insertPoint(request, response, anOnPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiRoutineListAction.POINT_UPDATE:
            	updatePoint(request, response, anOnPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiRoutineListAction.CHECK_STATUS:
            	findStatus(request, response, anOnPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiRoutineList(HttpServletRequest request, HttpServletResponse response, AnOnPmiRoutineListForm anOnPmiRoutineListForm) throws Exception
    {
    	AnOnPmiRoutineListService anOnPmiRoutineListService = (AnOnPmiRoutineListService) getBean("anOnPmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiRoutineListService.findPmiRoutineList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, AnOnPmiRoutineListForm anOnPmiRoutineListForm) throws Exception
    {
    	AnOnPmiRoutineListService anOnPmiRoutineListService = (AnOnPmiRoutineListService) getBean("anOnPmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiRoutineListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, AnOnPmiRoutineListForm anOnPmiRoutineListForm) throws Exception
    {
    	AnOnPmiRoutineListService anOnPmiRoutineListService = (AnOnPmiRoutineListService) getBean("anOnPmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiRoutineListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, AnOnPmiRoutineListForm anOnPmiRoutineListForm) throws Exception
    {
    	AnOnPmiRoutineListService anOnPmiRoutineListService = (AnOnPmiRoutineListService) getBean("anOnPmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiRoutineListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, AnOnPmiRoutineListForm anOnPmiRoutineListForm) throws Exception
    {
    	AnOnPmiRoutineListService anOnPmiRoutineListService = (AnOnPmiRoutineListService) getBean("anOnPmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiRoutineListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiRoutineListForm anOnPmiRoutineListForm) throws Exception
    {
    	AnOnPmiRoutineListService anOnPmiRoutineListService = (AnOnPmiRoutineListService) getBean("anOnPmiRoutineListService");
    	List list = getListData(request);
    	int qty = anOnPmiRoutineListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiRoutineListForm anOnPmiRoutineListForm) throws Exception
    {
    	AnOnPmiRoutineListService anOnPmiRoutineListService = (AnOnPmiRoutineListService) getBean("anOnPmiRoutineListService");
    	List list = getListData(request);
    	int qty = anOnPmiRoutineListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
