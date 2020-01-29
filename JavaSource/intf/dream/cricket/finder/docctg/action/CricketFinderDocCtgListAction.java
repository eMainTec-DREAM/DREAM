package intf.dream.cricket.finder.docctg.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.docctg.form.CricketFinderDocCtgListForm;
import intf.dream.cricket.finder.docctg.service.CricketFinderDocCtgListService;

/**
 * finder - dept 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderDocCtgList" name="cricketFinderDocCtgListForm"
 *                input="/cricket/finder/docctg/cricketFinderDocCtgList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderDocCtgListAction extends IfOnlineAuthAction
{
    //DOCCTG_FIND
    public static final String DOCCTG_FIND 			= "DOCCTG";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderDocCtgListForm cricketFinderDocCtgListForm = (CricketFinderDocCtgListForm) form;
        
        switch (cricketFinderDocCtgListForm.getServiceName())
        {
            case CricketFinderDocCtgListAction.DOCCTG_FIND:
            	findDocCtgList(request, response, cricketFinderDocCtgListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findDocCtgList(HttpServletRequest request, HttpServletResponse response, CricketFinderDocCtgListForm cricketFinderDocCtgListForm) throws Exception
    {
    	CricketFinderDocCtgListService cricketFinderDocCtgListService = (CricketFinderDocCtgListService) getBean("cricketFinderDocCtgListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderDocCtgListService.findDocCtgList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
