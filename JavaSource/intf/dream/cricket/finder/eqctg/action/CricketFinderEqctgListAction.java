package intf.dream.cricket.finder.eqctg.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.eqctg.form.CricketFinderEqctgListForm;
import intf.dream.cricket.finder.eqctg.service.CricketFinderEqctgListService;

/**
 * finder - Eqctg 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderEqctgList" name="cricketFinderEqctgListForm"
 *                input="/cricket/finder/eqctg/cricketFinderEqctgList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderEqctgListAction extends IfOnlineAuthAction
{
    //EQCTG_FIND
    public static final String EQCTG_FIND 			= "EQCTG";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderEqctgListForm cricketFinderEqctgListForm = (CricketFinderEqctgListForm) form;
        
        switch (cricketFinderEqctgListForm.getServiceName())
        {
            case CricketFinderEqctgListAction.EQCTG_FIND:
            	findEqctgList(request, response, cricketFinderEqctgListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqctgList(HttpServletRequest request, HttpServletResponse response, CricketFinderEqctgListForm cricketFinderEqctgListForm) throws Exception
    {
    	CricketFinderEqctgListService cricketFinderEqctgListService = (CricketFinderEqctgListService) getBean("cricketFinderEqctgListService");

    	Map map = getMapData(request);

        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = cricketFinderEqctgListService.findEqctgList(map);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response);
    }
    
}
