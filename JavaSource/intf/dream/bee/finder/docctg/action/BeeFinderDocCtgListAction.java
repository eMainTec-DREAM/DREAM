package intf.dream.bee.finder.docctg.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.docctg.form.BeeFinderDocCtgListForm;
import intf.dream.bee.finder.docctg.service.BeeFinderDocCtgListService;

/**
 * finder - dept 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderDocCtgList" name="beeFinderDocCtgListForm"
 *                input="/bee/finder/docctg/beeFinderDocCtgList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderDocCtgListAction extends IfOnlineAuthAction
{
    //DOCCTG_FIND
    public static final String DOCCTG_FIND 			= "DOCCTG";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderDocCtgListForm beeFinderDocCtgListForm = (BeeFinderDocCtgListForm) form;
        
        switch (beeFinderDocCtgListForm.getServiceName())
        {
            case BeeFinderDocCtgListAction.DOCCTG_FIND:
            	findDocCtgList(request, response, beeFinderDocCtgListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findDocCtgList(HttpServletRequest request, HttpServletResponse response, BeeFinderDocCtgListForm beeFinderDocCtgListForm) throws Exception
    {
    	BeeFinderDocCtgListService beeFinderDocCtgListService = (BeeFinderDocCtgListService) getBean("beeFinderDocCtgListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderDocCtgListService.findDocCtgList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
