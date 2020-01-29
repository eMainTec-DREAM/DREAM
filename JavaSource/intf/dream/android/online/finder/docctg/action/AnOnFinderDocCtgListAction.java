package intf.dream.android.online.finder.docctg.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.docctg.form.AnOnFinderDocCtgListForm;
import intf.dream.android.online.finder.docctg.service.AnOnFinderDocCtgListService;

/**
 * finder - dept 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderDocCtgList" name="anOnFinderDocCtgListForm"
 *                input="/android/online/finder/docctg/anOnFinderDocCtgList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderDocCtgListAction extends IfOnlineAuthAction
{
    //DOCCTG_FIND
    public static final String DOCCTG_FIND 			= "DOCCTG";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderDocCtgListForm anOnFinderDocCtgListForm = (AnOnFinderDocCtgListForm) form;
        
        switch (anOnFinderDocCtgListForm.getServiceName())
        {
            case AnOnFinderDocCtgListAction.DOCCTG_FIND:
            	findDocCtgList(request, response, anOnFinderDocCtgListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findDocCtgList(HttpServletRequest request, HttpServletResponse response, AnOnFinderDocCtgListForm anOnFinderDocCtgListForm) throws Exception
    {
    	AnOnFinderDocCtgListService anOnFinderDocCtgListService = (AnOnFinderDocCtgListService) getBean("anOnFinderDocCtgListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderDocCtgListService.findDocCtgList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
