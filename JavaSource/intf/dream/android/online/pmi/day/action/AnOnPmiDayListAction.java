package intf.dream.android.online.pmi.day.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.pmi.day.form.AnOnPmiDayListForm;
import intf.dream.android.online.pmi.day.service.AnOnPmiDayListService;

/**
 * 온라인버전 일상점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnPmiDayList" name="anOnPmiDayListForm"
 *                input="/android/online/pmi/day/anOnPmiDayList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnPmiDayListAction extends IfOnlineAuthAction
{
    //PmiDay List
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
        AnOnPmiDayListForm anOnPmiDayListForm = (AnOnPmiDayListForm) form;
        
        switch (anOnPmiDayListForm.getServiceName())
        {
            case AnOnPmiDayListAction.PMI_LIST:
            	findPmiDayList(request, response, anOnPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiDayListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, anOnPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiDayListAction.POINT_LIST:
            	findPointList(request, response, anOnPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiDayListAction.POINT_COUNT:
            	findWoPointCount(request, response, anOnPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiDayListAction.POINT_INSERT:
            	insertPoint(request, response, anOnPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiDayListAction.POINT_UPDATE:
            	updatePoint(request, response, anOnPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiDayListAction.CHECK_STATUS:
            	findStatus(request, response, anOnPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiDayList(HttpServletRequest request, HttpServletResponse response, AnOnPmiDayListForm anOnPmiDayListForm) throws Exception
    {
    	AnOnPmiDayListService anOnPmiDayListService = (AnOnPmiDayListService) getBean("anOnPmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiDayListService.findPmiDayList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, AnOnPmiDayListForm anOnPmiDayListForm) throws Exception
    {
    	AnOnPmiDayListService anOnPmiDayListService = (AnOnPmiDayListService) getBean("anOnPmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiDayListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, AnOnPmiDayListForm anOnPmiDayListForm) throws Exception
    {
    	AnOnPmiDayListService anOnPmiDayListService = (AnOnPmiDayListService) getBean("anOnPmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiDayListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, AnOnPmiDayListForm anOnPmiDayListForm) throws Exception
    {
    	AnOnPmiDayListService anOnPmiDayListService = (AnOnPmiDayListService) getBean("anOnPmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiDayListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, AnOnPmiDayListForm anOnPmiDayListForm) throws Exception
    {
    	AnOnPmiDayListService anOnPmiDayListService = (AnOnPmiDayListService) getBean("anOnPmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiDayListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiDayListForm anOnPmiDayListForm) throws Exception
    {
    	AnOnPmiDayListService anOnPmiDayListService = (AnOnPmiDayListService) getBean("anOnPmiDayListService");
    	List list = getListData(request);
    	int qty = anOnPmiDayListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiDayListForm anOnPmiDayListForm) throws Exception
    {
    	AnOnPmiDayListService anOnPmiDayListService = (AnOnPmiDayListService) getBean("anOnPmiDayListService");
    	List list = getListData(request);
    	int qty = anOnPmiDayListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
