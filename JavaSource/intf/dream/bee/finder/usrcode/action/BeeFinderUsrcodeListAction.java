package intf.dream.bee.finder.usrcode.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.bee.finder.usrcode.form.BeeFinderUsrcodeListForm;
import intf.dream.bee.finder.usrcode.service.BeeFinderUsrcodeListService;

/**
 * finder - syscode 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeFinderUsrcodeList" name="beeFinderUsrcodeListForm"
 *                input="/bee/finder/usrcode/beeFinderUsrcodeList.jsp" scope="request"
 *                validate="false"
 */
public class BeeFinderUsrcodeListAction extends IfOnlineAuthAction
{
    //Usrcode
    public static final String USRCODE_FIND		= "USRCODE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeFinderUsrcodeListForm beeFinderUsrcodeListForm = (BeeFinderUsrcodeListForm) form;
        
        switch (beeFinderUsrcodeListForm.getServiceName())
        {
            case BeeFinderUsrcodeListAction.USRCODE_FIND:
            	findUsrcodeList(request, response, beeFinderUsrcodeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findUsrcodeList(HttpServletRequest request, HttpServletResponse response, BeeFinderUsrcodeListForm beeFinderUsrcodeListForm) throws Exception
    {
    	BeeFinderUsrcodeListService beeFinderUsrcodeListService = (BeeFinderUsrcodeListService) getBean("beeFinderUsrcodeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeFinderUsrcodeListService.findUsrcodeList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
