package intf.dream.bee.wodaily.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.wodaily.dto.BeeWoDailyCommonDTO;
import intf.dream.bee.wodaily.form.BeeWoDailyListForm;
import intf.dream.bee.wodaily.service.BeeWoDailyListService;

/**
 * �¶��ι��� ��ȹ�۾� 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeWoDailyList" name="beeWoDailyListForm"
 *                input="/bee/wodaily/beeWoDailyList.jsp" scope="request"
 *                validate="false"
 */
public class BeeWoDailyListAction extends IfOnlineAuthAction
{
    //WODAILY LIST
    public static final String WODAILY_LIST 				= "WODAILY_LIST";
    //WODAILY COUNT
    public static final String WODAILY_COUNT 				= "WODAILY_COUNT";
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
        BeeWoDailyListForm beeWoDailyListForm = (BeeWoDailyListForm) form;
        
        switch (beeWoDailyListForm.getServiceName())
        {
	        case BeeWoDailyListAction.WODAILY_LIST:
	        	findWoDailyList(request, response, beeWoDailyListForm);
	        	returnActionForward = mapping.findForward("jsonPage");
	        	break;
	        case BeeWoDailyListAction.WODAILY_COUNT:
	        	findWoDailyCount(request, response, beeWoDailyListForm);
	        	returnActionForward = mapping.findForward("jsonPage");
	        	break;
            case BeeWoDailyListAction.WODAILY_DELETE:
            	deleteWoDaily(request, response, beeWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeWoDailyListAction.WODAILY_INSERT:
            	insertWoDaily(request, response, beeWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeWoDailyListAction.WODAILY_UPDATE:
            	updateWoDaily(request, response, beeWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeWoDailyListAction.WODAILY_UNPLAN:
            	findDailyUnPlanList(request, response, beeWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            case BeeWoDailyListAction.WODAILY_ROUTINE:
            	findDailyRoutineList(request, response, beeWoDailyListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }

    private void findWoDailyList(HttpServletRequest request, HttpServletResponse response, BeeWoDailyListForm beeWoDailyListForm) throws Exception
    {
    	BeeWoDailyListService beeWoDailyListService = (BeeWoDailyListService) getBean("beeWoDailyListService");
    	BeeWoDailyCommonDTO beeWoDailyCommonDTO = beeWoDailyListForm.getBeeWoDailyCommonDTO();
    	Map map = getMapData(request);

    	beeWoDailyCommonDTO.setIsLoadMaxCount(true);
    	if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
    		beeWoDailyCommonDTO.setIsLoadMaxCount(false);
    	}
    	beeWoDailyCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));
    	
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeWoDailyListService.findWoDailyList(beeWoDailyCommonDTO, map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }

    private void findWoDailyCount(HttpServletRequest request, HttpServletResponse response, BeeWoDailyListForm beeWoDailyListForm) throws Exception
    {
    	BeeWoDailyListService beeWoDailyListService = (BeeWoDailyListService) getBean("beeWoDailyListService");
    	BeeWoDailyCommonDTO beeWoDailyCommonDTO = beeWoDailyListForm.getBeeWoDailyCommonDTO();
    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeWoDailyListService.findWoDailyList(beeWoDailyCommonDTO, map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    private void deleteWoDaily(HttpServletRequest request, HttpServletResponse response, BeeWoDailyListForm beeWoDailyListForm) throws Exception
    {
    	BeeWoDailyListService beeWoDailyListService = (BeeWoDailyListService) getBean("beeWoDailyListService");
    	List list = getListData(request);
    	int qty = beeWoDailyListService.deleteWoDaily(list);
    	setMessage(response, "","OK");
    }
    private void insertWoDaily(HttpServletRequest request, HttpServletResponse response, BeeWoDailyListForm beeWoDailyListForm) throws Exception
    {
    	BeeWoDailyListService beeWoDailyListService = (BeeWoDailyListService) getBean("beeWoDailyListService");
    	List list = getListData(request);
    	int qty = beeWoDailyListService.insertWoDaily(list);
    	setMessage(response, "","OK"); 
    }
    private void updateWoDaily(HttpServletRequest request, HttpServletResponse response, BeeWoDailyListForm beeWoDailyListForm) throws Exception
    {
    	BeeWoDailyListService beeWoDailyListService = (BeeWoDailyListService) getBean("beeWoDailyListService");
    	List list = getListData(request);
    	int qty = beeWoDailyListService.updateWoDaily(list);
    	setMessage(response, "","OK");
    }
        
    private void findDailyUnPlanList(HttpServletRequest request, HttpServletResponse response, BeeWoDailyListForm beeWoDailyListForm) throws Exception
    {
    	BeeWoDailyListService beeWoDailyListService = (BeeWoDailyListService) getBean("beeWoDailyListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeWoDailyListService.findDailyUnPlanList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
    private void findDailyRoutineList(HttpServletRequest request, HttpServletResponse response, BeeWoDailyListForm beeWoDailyListForm) throws Exception
    {
    	BeeWoDailyListService beeWoDailyListService = (BeeWoDailyListService) getBean("beeWoDailyListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeWoDailyListService.findDailyRoutineList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
