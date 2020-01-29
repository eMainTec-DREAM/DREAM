package intf.dream.bee.finder.equip.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.equip.form.BeeFinderEquipListForm;
import intf.dream.bee.finder.equip.service.BeeFinderEquipListService;

/**
 * finder - Equip 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderEquipList" name="beeFinderEquipListForm"
 *                input="/bee/finder/equip/beeFinderEquipList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderEquipListAction extends IfOnlineAuthAction
{
    //EQUIP_FIND
    public static final String EQUIP_FIND 			= "EQUIP";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderEquipListForm beeFinderEquipListForm = (BeeFinderEquipListForm) form;
        
        switch (beeFinderEquipListForm.getServiceName())
        {
            case BeeFinderEquipListAction.EQUIP_FIND:
            	findEquipList(request, response, beeFinderEquipListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEquipList(HttpServletRequest request, HttpServletResponse response, BeeFinderEquipListForm beeFinderEquipListForm) throws Exception
    {
    	BeeFinderEquipListService beeFinderEquipListService = (BeeFinderEquipListService) getBean("beeFinderEquipListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = beeFinderEquipListService.findEquipList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
