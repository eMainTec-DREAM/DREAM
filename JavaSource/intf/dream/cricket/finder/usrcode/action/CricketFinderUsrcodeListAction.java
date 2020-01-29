package intf.dream.cricket.finder.usrcode.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.finder.usrcode.form.CricketFinderUsrcodeListForm;
import intf.dream.cricket.finder.usrcode.service.CricketFinderUsrcodeListService;

/**
 * finder - syscode 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketFinderUsrcodeList" name="cricketFinderUsrcodeListForm"
 *                input="/cricket/finder/usrcode/cricketFinderUsrcodeList.jsp" scope="request"
 *                validate="false"
 */
public class CricketFinderUsrcodeListAction extends IfOnlineAuthAction
{
    //Usrcode
    public static final String USRCODE_FIND		= "USRCODE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketFinderUsrcodeListForm cricketFinderUsrcodeListForm = (CricketFinderUsrcodeListForm) form;
        
        switch (cricketFinderUsrcodeListForm.getServiceName())
        {
            case CricketFinderUsrcodeListAction.USRCODE_FIND:
            	findUsrcodeList(request, response, cricketFinderUsrcodeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findUsrcodeList(HttpServletRequest request, HttpServletResponse response, CricketFinderUsrcodeListForm cricketFinderUsrcodeListForm) throws Exception
    {
    	CricketFinderUsrcodeListService cricketFinderUsrcodeListService = (CricketFinderUsrcodeListService) getBean("cricketFinderUsrcodeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketFinderUsrcodeListService.findUsrcodeList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
