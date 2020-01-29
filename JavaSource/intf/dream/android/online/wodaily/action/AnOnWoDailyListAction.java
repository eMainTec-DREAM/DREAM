package intf.dream.android.online.wodaily.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.wodaily.form.AnOnWoDailyListForm;
import intf.dream.android.online.wodaily.service.AnOnWoDailyListService;

/**
 * �¶��ι��� ��ȹ�۾� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnWoDailyList" name="anOnWoDailyListForm"
 *                input="/android/online/wodaily/anOnWoDailyList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnWoDailyListAction extends IfOnlineAuthAction
{
    //WODAILY LIST
    public static final String WODAILY_LIST 				= "WODAILY_LIST";
    //WODAILY DELETE 
    public static final String WODAILY_DELETE 				= "WODAILY_DELETE";
    //WODAILY INSERT
    public static final String WODAILY_INSERT 				= "WODAILY_INSERT";
    //WODAILY UPDATE 
    public static final String WODAILY_UPDATE 				= "WODAILY_UPDATE";
    //�����۾� ����Ʈ
    public static final String WODAILY_UNPLAN 				= "WODAILY_UNPLAN";
    //�������� ����Ʈ
    public static final String WODAILY_ROUTINE 				= "WODAILY_ROUTINE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnWoDailyListForm anOnWoDailyListForm = (AnOnWoDailyListForm) form;
        
        switch (anOnWoDailyListForm.getServiceName())
        {
            case AnOnWoDailyListAction.WODAILY_LIST:
            	findWoDailyList(request, response, anOnWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnOnWoDailyListAction.WODAILY_DELETE:
            	deleteWoDaily(request, response, anOnWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnWoDailyListAction.WODAILY_INSERT:
            	insertWoDaily(request, response, anOnWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnWoDailyListAction.WODAILY_UPDATE:
            	updateWoDaily(request, response, anOnWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnWoDailyListAction.WODAILY_UNPLAN:
            	findDailyUnPlanList(request, response, anOnWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case AnOnWoDailyListAction.WODAILY_ROUTINE:
            	findDailyRoutineList(request, response, anOnWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findWoDailyList(HttpServletRequest request, HttpServletResponse response, AnOnWoDailyListForm anOnWoDailyListForm) throws Exception
    {
    	AnOnWoDailyListService anOnWoDailyListService = (AnOnWoDailyListService) getBean("anOnWoDailyListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnWoDailyListService.findWoDailyList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteWoDaily(HttpServletRequest request, HttpServletResponse response, AnOnWoDailyListForm anOnWoDailyListForm) throws Exception
    {
    	AnOnWoDailyListService anOnWoDailyListService = (AnOnWoDailyListService) getBean("anOnWoDailyListService");
    	List list = getListData(request);
    	int qty = anOnWoDailyListService.deleteWoDaily(list);
    	setMessage(response, "","OK");
    }
    private void insertWoDaily(HttpServletRequest request, HttpServletResponse response, AnOnWoDailyListForm anOnWoDailyListForm) throws Exception
    {
    	AnOnWoDailyListService anOnWoDailyListService = (AnOnWoDailyListService) getBean("anOnWoDailyListService");
    	List list = getListData(request);
    	int qty = anOnWoDailyListService.insertWoDaily(list);
    	setMessage(response, "","OK"); 
    }
    private void updateWoDaily(HttpServletRequest request, HttpServletResponse response, AnOnWoDailyListForm anOnWoDailyListForm) throws Exception
    {
    	AnOnWoDailyListService anOnWoDailyListService = (AnOnWoDailyListService) getBean("anOnWoDailyListService");
    	List list = getListData(request);
    	int qty = anOnWoDailyListService.updateWoDaily(list);
    	setMessage(response, "","OK");
    }
        
    private void findDailyUnPlanList(HttpServletRequest request, HttpServletResponse response, AnOnWoDailyListForm anOnWoDailyListForm) throws Exception
    {
    	AnOnWoDailyListService anOnWoDailyListService = (AnOnWoDailyListService) getBean("anOnWoDailyListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnWoDailyListService.findDailyUnPlanList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findDailyRoutineList(HttpServletRequest request, HttpServletResponse response, AnOnWoDailyListForm anOnWoDailyListForm) throws Exception
    {
    	AnOnWoDailyListService anOnWoDailyListService = (AnOnWoDailyListService) getBean("anOnWoDailyListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = anOnWoDailyListService.findDailyRoutineList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
