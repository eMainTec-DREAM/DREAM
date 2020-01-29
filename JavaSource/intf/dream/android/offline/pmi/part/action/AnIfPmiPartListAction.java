package intf.dream.android.offline.pmi.part.action;


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
import intf.dream.android.offline.pmi.part.form.AnIfPmiPartListForm;
import intf.dream.android.offline.pmi.part.service.AnIfPmiPartListService;

/**
 * Part Change 점검 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfPmiPartList" name="anIfPmiPartListForm"
 *                input="/android/offline/pmi/day/anIfPmiPartList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfPmiPartListAction extends IfAuthAction
{
    //SCHED_FIND
    public static final String INSD_SCHED 					= "INSD_SCHED";
    //PMLST_FIND
    public static final String INSD_PMLST	 				= "INSD_PMLST";
    //PMI Part
    public static final String INSDLIST_SAVE				= "INSDLIST_SAVE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfPmiPartListForm anIfPmiPartListForm = (AnIfPmiPartListForm) form;
        
        switch (anIfPmiPartListForm.getServiceName())
        {
            case AnIfPmiPartListAction.INSD_SCHED:
            	findSchedList(request, response, anIfPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPmiPartListAction.INSD_PMLST:
            	findPmlstList(request, response, anIfPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            case AnIfPmiPartListAction.INSDLIST_SAVE:
            	saveInsdList(request, response, anIfPmiPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 

            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findSchedList(HttpServletRequest request, HttpServletResponse response, AnIfPmiPartListForm anIfPmiPartListForm)  throws Exception
    {
    	AnIfPmiPartListService anIfPmiPartListService = (AnIfPmiPartListService) getBean("anIfPmiPartListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfPmiPartListService.findSchedList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void findPmlstList(HttpServletRequest request, HttpServletResponse response, AnIfPmiPartListForm anIfPmiPartListForm)  throws Exception
    {
    	AnIfPmiPartListService anIfPmiPartListService = (AnIfPmiPartListService) getBean("anIfPmiPartListService");

    	Map map = getMapData(request);
    	//리스트를 조회한다.
        List list = anIfPmiPartListService.findPmlstList(map);
        List resultList = new ArrayList();
        
        if (list.size() > 0) {
        	List equipList = anIfPmiPartListService.findPmequipList(map);
        	List pointList = anIfPmiPartListService.findPmpointList(map);
        	
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
    private void saveInsdList(HttpServletRequest request, HttpServletResponse response, AnIfPmiPartListForm anIfPmiPartListForm)  throws Exception
    {
    	AnIfPmiPartListService anIfPmiPartListService = (AnIfPmiPartListService) getBean("anIfPmiPartListService");

    	List list = getListData(request);
    	int qty = anIfPmiPartListService.saveInsdList(list);
    	setMessage(response, "","OK");
    }
}
