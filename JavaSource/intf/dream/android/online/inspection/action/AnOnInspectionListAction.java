package intf.dream.android.online.inspection.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.inspection.form.AnOnInspectionListForm;
import intf.dream.android.online.inspection.service.AnOnInspectionListService;

/**
 * �¶��ι��� ���� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnInspectionList" name="anOnInspectionListForm"
 *                input="/android/online/inspection/anOnInspectionList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnInspectionListAction extends IfOnlineAuthAction
{
    //Inspection List
    public static final String INSPECTION_LIST 				= "INSPECTION_LIST";
    //Point List
    public static final String POINT_LIST					= "POINT_LIST";
    //Point hist List
    public static final String POINT_HIST_LIST				= "POINT_HIST_LIST";
    //Wopoint count
    public static final String WOPOINT_COUNT				= "WOPOINT_COUNT";
    //INSERT POINT
    public static final String POINT_INSERT					= "POINT_INSERT";
    //UPDATE POINT
    public static final String POINT_UPDATE					= "POINT_UPDATE";
    //CHECK STATUS
    public static final String CHECK_STATUS					= "CHECK_STATUS";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnInspectionListForm anOnInspectionListForm = (AnOnInspectionListForm) form;
        
        switch (anOnInspectionListForm.getServiceName())
        {
            case AnOnInspectionListAction.INSPECTION_LIST:
            	findInspectionList(request, response, anOnInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnInspectionListAction.POINT_LIST:
            	findPointList(request, response, anOnInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnInspectionListAction.POINT_HIST_LIST:
            	findPointHistList(request, response, anOnInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnInspectionListAction.WOPOINT_COUNT:
            	findWoPointCount(request, response, anOnInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnInspectionListAction.POINT_INSERT:
            	insertPoint(request, response, anOnInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnInspectionListAction.POINT_UPDATE:
            	updatePoint(request, response, anOnInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnInspectionListAction.CHECK_STATUS:
            	findStatus(request, response, anOnInspectionListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findInspectionList(HttpServletRequest request, HttpServletResponse response, AnOnInspectionListForm anOnInspectionListForm) throws Exception
    {
    	AnOnInspectionListService anOnInspectionListService = (AnOnInspectionListService) getBean("anOnInspectionListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnInspectionListService.findInspectionList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointList(HttpServletRequest request, HttpServletResponse response, AnOnInspectionListForm anOnInspectionListForm) throws Exception
    {
    	AnOnInspectionListService anOnInspectionListService = (AnOnInspectionListService) getBean("anOnInspectionListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnInspectionListService.findPointList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findPointHistList(HttpServletRequest request, HttpServletResponse response, AnOnInspectionListForm anOnInspectionListForm) throws Exception
    {
    	AnOnInspectionListService anOnInspectionListService = (AnOnInspectionListService) getBean("anOnInspectionListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnInspectionListService.findPointHistList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    private void findWoPointCount(HttpServletRequest request, HttpServletResponse response, AnOnInspectionListForm anOnInspectionListForm) throws Exception
    {
    	AnOnInspectionListService anOnInspectionListService = (AnOnInspectionListService) getBean("anOnInspectionListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnInspectionListService.findWoPointCount(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    private void findStatus(HttpServletRequest request, HttpServletResponse response, AnOnInspectionListForm anOnInspectionListForm) throws Exception
    {
    	AnOnInspectionListService anOnInspectionListService = (AnOnInspectionListService) getBean("anOnInspectionListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnInspectionListService.findStatus(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    private void insertPoint(HttpServletRequest request, HttpServletResponse response, AnOnInspectionListForm anOnInspectionListForm) throws Exception
    {
    	AnOnInspectionListService anOnInspectionListService = (AnOnInspectionListService) getBean("anOnInspectionListService");
    	List list = getListData(request);
    	int qty = anOnInspectionListService.insertPoint(list);
    	setMessage(response, "","OK");
    }
    private void updatePoint(HttpServletRequest request, HttpServletResponse response, AnOnInspectionListForm anOnInspectionListForm) throws Exception
    {
    	AnOnInspectionListService anOnInspectionListService = (AnOnInspectionListService) getBean("anOnInspectionListService");
    	List list = getListData(request);
    	int qty = anOnInspectionListService.updatePoint(list);
    	setMessage(response, "","OK");
    }
}
