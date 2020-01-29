package intf.dream.android.offline.pmi.routine.action;


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
import intf.dream.android.offline.pmi.routine.form.AnIfPmiRoutineListForm;
import intf.dream.android.offline.pmi.routine.service.AnIfPmiRoutineListService;

/**
 * 정기점검 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfPmiRoutineList" name="anIfPmiRoutineListForm"
 *                input="/android/offline/pmi/routine/anIfPmiRoutineList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfPmiRoutineListAction extends IfAuthAction
{
    //SCHED_FIND
    public static final String INS_SCHED 				= "INS_SCHED";
    //PMLST_FIND
    public static final String INS_PMLST	 			= "INS_PMLST";
    //PMI Routine
    public static final String INSLIST_SAVE				= "INSLIST_SAVE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfPmiRoutineListForm anIfPmiRoutineListForm = (AnIfPmiRoutineListForm) form;
        
        switch (anIfPmiRoutineListForm.getServiceName())
        {
            case AnIfPmiRoutineListAction.INS_SCHED:
            	findSchedList(request, response, anIfPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPmiRoutineListAction.INS_PMLST:
            	findPmlstList(request, response, anIfPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPmiRoutineListAction.INSLIST_SAVE:
            	saveInsList(request, response, anIfPmiRoutineListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSchedList(HttpServletRequest request, HttpServletResponse response, AnIfPmiRoutineListForm anIfPmiRoutineListForm)  throws Exception
    {
    	AnIfPmiRoutineListService anIfPmiRoutineListService = (AnIfPmiRoutineListService) getBean("anIfPmiRoutineListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfPmiRoutineListService.findSchedList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmlstList(HttpServletRequest request, HttpServletResponse response, AnIfPmiRoutineListForm anIfPmiRoutineListForm)  throws Exception
    {
    	AnIfPmiRoutineListService anIfPmiRoutineListService = (AnIfPmiRoutineListService) getBean("anIfPmiRoutineListService");

    	Map map = getMapData(request);
    	//리스트를 조회한다.
        List list = anIfPmiRoutineListService.findPmlstList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List equipList = anIfPmiRoutineListService.findPmequipList(map);
        	List pointList = anIfPmiRoutineListService.findPmpointList(map);
        	
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
    private void saveInsList(HttpServletRequest request, HttpServletResponse response, AnIfPmiRoutineListForm anIfPmiRoutineListForm)  throws Exception
    {
    	AnIfPmiRoutineListService anIfPmiRoutineListService = (AnIfPmiRoutineListService) getBean("anIfPmiRoutineListService");

    	List list = getListData(request);
    	int qty = anIfPmiRoutineListService.saveInsList(list);
    	setMessage(response, "","OK");
    }
}
