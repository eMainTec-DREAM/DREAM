package intf.dream.cricket.finder.equip.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.equip.form.CricketFinderEquipListForm;
import intf.dream.cricket.finder.equip.service.CricketFinderEquipListService;

/**
 * finder - Equip 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderEquipList" name="cricketFinderEquipListForm"
 *                input="/cricket/finder/equip/cricketFinderEquipList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderEquipListAction extends IfOnlineAuthAction
{
    //EQUIP_FIND
    public static final String EQUIP_FIND 			= "EQUIP";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderEquipListForm cricketFinderEquipListForm = (CricketFinderEquipListForm) form;
        
        switch (cricketFinderEquipListForm.getServiceName())
        {
            case CricketFinderEquipListAction.EQUIP_FIND:
            	findEquipList(request, response, cricketFinderEquipListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEquipList(HttpServletRequest request, HttpServletResponse response, CricketFinderEquipListForm cricketFinderEquipListForm) throws Exception
    {
    	CricketFinderEquipListService cricketFinderEquipListService = (CricketFinderEquipListService) getBean("cricketFinderEquipListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderEquipListService.findEquipList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
