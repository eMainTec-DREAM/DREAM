package intf.dream.android.online.pmi.part.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.pmi.part.form.AnOnPmiPartListForm;
import intf.dream.android.online.pmi.part.service.AnOnPmiPartListService;

/**
 * 온라인버전 Part Change 점검
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnPmiPartList" name="anOnPmiPartListForm"
 *                input="/android/online/pmi/day/anOnPmiPartList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnPmiPartListAction extends IfOnlineAuthAction
{
    //PmiPart List
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
        AnOnPmiPartListForm anOnPmiPartListForm = (AnOnPmiPartListForm) form;
        
        switch (anOnPmiPartListForm.getServiceName())
        {
            case AnOnPmiPartListAction.PMI_LIST:
            	findPmiPartList(request, response, anOnPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPartListAction.POINT_LIST:
            	findPointList(request, response, anOnPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPartListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, anOnPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPartListAction.POINT_COUNT:
            	findWoPointCount(request, response, anOnPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPartListAction.POINT_INSERT:
            	insertPoint(request, response, anOnPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPartListAction.POINT_UPDATE:
            	updatePoint(request, response, anOnPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnPmiPartListAction.CHECK_STATUS:
            	findStatus(request, response, anOnPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findPmiPartList(HttpServletRequest request, HttpServletResponse response, AnOnPmiPartListForm anOnPmiPartListForm) throws Exception
    {
    	AnOnPmiPartListService anOnPmiPartListService = (AnOnPmiPartListService) getBean("anOnPmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPartListService.findPmiPartList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, AnOnPmiPartListForm anOnPmiPartListForm) throws Exception
    {
    	AnOnPmiPartListService anOnPmiPartListService = (AnOnPmiPartListService) getBean("anOnPmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPartListService.findPointList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, AnOnPmiPartListForm anOnPmiPartListForm) throws Exception
    {
    	AnOnPmiPartListService anOnPmiPartListService = (AnOnPmiPartListService) getBean("anOnPmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPartListService.findPointHistList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, AnOnPmiPartListForm anOnPmiPartListForm) throws Exception
    {
    	AnOnPmiPartListService anOnPmiPartListService = (AnOnPmiPartListService) getBean("anOnPmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPartListService.findWoPointCount(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, AnOnPmiPartListForm anOnPmiPartListForm) throws Exception
    {
    	AnOnPmiPartListService anOnPmiPartListService = (AnOnPmiPartListService) getBean("anOnPmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnPmiPartListService.findStatus(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiPartListForm anOnPmiPartListForm) throws Exception
    {
    	AnOnPmiPartListService anOnPmiPartListService = (AnOnPmiPartListService) getBean("anOnPmiPartListService");
    	List list = getListData(request);
    	int qty = anOnPmiPartListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, AnOnPmiPartListForm anOnPmiPartListForm) throws Exception
    {
    	AnOnPmiPartListService anOnPmiPartListService = (AnOnPmiPartListService) getBean("anOnPmiPartListService");
    	List list = getListData(request);
    	int qty = anOnPmiPartListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
