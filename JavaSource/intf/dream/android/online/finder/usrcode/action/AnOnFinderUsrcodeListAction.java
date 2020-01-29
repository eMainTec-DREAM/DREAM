package intf.dream.android.online.finder.usrcode.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.finder.usrcode.form.AnOnFinderUsrcodeListForm;
import intf.dream.android.online.finder.usrcode.service.AnOnFinderUsrcodeListService;

/**
 * finder - syscode 
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anOnFinderUsrcodeList" name="anOnFinderUsrcodeListForm"
 *                input="/android/online/finder/usrcode/anOnFinderUsrcodeList.jsp" scope="request"
 *                validate="false"
 */
public class AnOnFinderUsrcodeListAction extends IfOnlineAuthAction
{
    //Usrcode
    public static final String USRCODE_FIND		= "USRCODE";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnOnFinderUsrcodeListForm anOnFinderUsrcodeListForm = (AnOnFinderUsrcodeListForm) form;
        
        switch (anOnFinderUsrcodeListForm.getServiceName())
        {
            case AnOnFinderUsrcodeListAction.USRCODE_FIND:
            	findUsrcodeList(request, response, anOnFinderUsrcodeListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void findUsrcodeList(HttpServletRequest request, HttpServletResponse response, AnOnFinderUsrcodeListForm anOnFinderUsrcodeListForm) throws Exception
    {
    	AnOnFinderUsrcodeListService anOnFinderUsrcodeListService = (AnOnFinderUsrcodeListService) getBean("anOnFinderUsrcodeListService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anOnFinderUsrcodeListService.findUsrcodeList(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    
}
