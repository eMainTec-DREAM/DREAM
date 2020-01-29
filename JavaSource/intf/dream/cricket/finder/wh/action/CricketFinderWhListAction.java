package intf.dream.cricket.finder.wh.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.wh.form.CricketFinderWhListForm;
import intf.dream.cricket.finder.wh.service.CricketFinderWhListService;

/**
 * finder - Wh 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderWhList" name="cricketFinderWhListForm"
 *                input="/cricket/finder/wh/cricketFinderWhList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderWhListAction extends IfOnlineAuthAction
{
    //WH_FIND
    public static final String WH_FIND 			= "WAREHOUSE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderWhListForm cricketFinderWhListForm = (CricketFinderWhListForm) form;
        
        switch (cricketFinderWhListForm.getServiceName())
        {
            case CricketFinderWhListAction.WH_FIND:
            	findWhList(request, response, cricketFinderWhListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findWhList(HttpServletRequest request, HttpServletResponse response, CricketFinderWhListForm cricketFinderWhListForm) throws Exception
    {
    	CricketFinderWhListService cricketFinderWhListService = (CricketFinderWhListService) getBean("cricketFinderWhListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = cricketFinderWhListService.findWhList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
