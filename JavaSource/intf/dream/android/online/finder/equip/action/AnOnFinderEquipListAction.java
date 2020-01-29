package intf.dream.android.online.finder.equip.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.equip.form.AnOnFinderEquipListForm;
import intf.dream.android.online.finder.equip.service.AnOnFinderEquipListService;

/**
 * finder - Equip 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderEquipList" name="anOnFinderEquipListForm"
 *                input="/android/online/finder/equip/anOnFinderEquipList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderEquipListAction extends IfOnlineAuthAction
{
    //EQUIP_FIND
    public static final String EQUIP_FIND 			= "EQUIP";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderEquipListForm anOnFinderEquipListForm = (AnOnFinderEquipListForm) form;
        
        switch (anOnFinderEquipListForm.getServiceName())
        {
            case AnOnFinderEquipListAction.EQUIP_FIND:
            	findEquipList(request, response, anOnFinderEquipListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEquipList(HttpServletRequest request, HttpServletResponse response, AnOnFinderEquipListForm anOnFinderEquipListForm) throws Exception
    {
    	AnOnFinderEquipListService anOnFinderEquipListService = (AnOnFinderEquipListService) getBean("anOnFinderEquipListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderEquipListService.findEquipList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
