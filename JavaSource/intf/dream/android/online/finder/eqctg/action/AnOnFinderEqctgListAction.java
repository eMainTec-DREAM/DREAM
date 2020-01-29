package intf.dream.android.online.finder.eqctg.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.eqctg.form.AnOnFinderEqctgListForm;
import intf.dream.android.online.finder.eqctg.service.AnOnFinderEqctgListService;

/**
 * finder - Eqctg 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderEqctgList" name="anOnFinderEqctgListForm"
 *                input="/android/online/finder/eqctg/anOnFinderEqctgList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderEqctgListAction extends IfOnlineAuthAction
{
    //EQCTG_FIND
    public static final String EQCTG_FIND 			= "EQCTG";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderEqctgListForm anOnFinderEqctgListForm = (AnOnFinderEqctgListForm) form;
        
        switch (anOnFinderEqctgListForm.getServiceName())
        {
            case AnOnFinderEqctgListAction.EQCTG_FIND:
            	findEqctgList(request, response, anOnFinderEqctgListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findEqctgList(HttpServletRequest request, HttpServletResponse response, AnOnFinderEqctgListForm anOnFinderEqctgListForm) throws Exception
    {
    	AnOnFinderEqctgListService anOnFinderEqctgListService = (AnOnFinderEqctgListService) getBean("anOnFinderEqctgListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderEqctgListService.findEqctgList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
