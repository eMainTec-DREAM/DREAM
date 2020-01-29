package intf.dream.ant.pmi.day.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import common.util.CommonUtil;
import intf.dream.ant.pmi.day.form.AntPmiDayListForm;
import intf.dream.ant.pmi.day.service.AntPmiDayListService;

/**
 * 일상점검 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antPmiDayList" name="antPmiDayListForm"
 *                input="/ant/pmi/day/antPmiDayList.jsp" scope="request"
 *                validate="false"
 */
public class AntPmiDayListAction extends IfAuthAction
{
    //SCHED_FIND
    public static final String INSD_SCHED 					= "INSD_SCHED";
    //PMLST_FIND
    public static final String INSD_PMLST	 				= "INSD_PMLST";
    //PMI Day
    public static final String INSDLIST_SAVE				= "INSDLIST_SAVE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntPmiDayListForm antPmiDayListForm = (AntPmiDayListForm) form;
        
        switch (antPmiDayListForm.getServiceName())
        {
            case AntPmiDayListAction.INSD_SCHED:
            	findSchedList(request, response, antPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntPmiDayListAction.INSD_PMLST:
            	findPmlstList(request, response, antPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AntPmiDayListAction.INSDLIST_SAVE:
            	saveInsdList(request, response, antPmiDayListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSchedList(HttpServletRequest request, HttpServletResponse response, AntPmiDayListForm antPmiDayListForm)  throws Exception
    {
    	AntPmiDayListService antPmiDayListService = (AntPmiDayListService) getBean("antPmiDayListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antPmiDayListService.findSchedList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmlstList(HttpServletRequest request, HttpServletResponse response, AntPmiDayListForm antPmiDayListForm)  throws Exception
    {
    	AntPmiDayListService antPmiDayListService = (AntPmiDayListService) getBean("antPmiDayListService");

    	Map map = getMapData(request);
    	//리스트를 조회한다.
        List list = antPmiDayListService.findPmlstList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List equipList = antPmiDayListService.findPmequipList(map);
        	List pointList = antPmiDayListService.findPmpointList(map);
        	
        	for (Object obj : list) {
				Map objMap = (Map) obj;
				String hdrId = CommonUtil.convertString(objMap.get("PM_ID"));

				List sendEquipList = new ArrayList();
				List sendPointList = new ArrayList();

				for(Object itemObj : equipList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("PM_ID"));
		    		if(hdrId.equals(item)) sendEquipList.add(itemObjMap);
		    	}
				for(Object itemObj : pointList){
					Map itemObjMap = (Map)itemObj;
		    		String item = CommonUtil.convertString(itemObjMap.get("PM_ID"));
		    		if(hdrId.equals(item)) sendPointList.add(itemObjMap);
		    	}
				objMap.put("EQUIP", CommonUtil.makeNullToBlank(sendEquipList));
				objMap.put("POINT", CommonUtil.makeNullToBlank(sendPointList));

				resultList.add(objMap);
        	}
        }

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void saveInsdList(HttpServletRequest request, HttpServletResponse response, AntPmiDayListForm antPmiDayListForm)  throws Exception
    {
    	AntPmiDayListService antPmiDayListService = (AntPmiDayListService) getBean("antPmiDayListService");

    	List list = getListData(request);
    	int qty = antPmiDayListService.saveInsdList(list);
    	setMessage(response, "","OK");
    }
}
