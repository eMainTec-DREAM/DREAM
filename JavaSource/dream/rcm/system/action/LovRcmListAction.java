package dream.rcm.system.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.LovRcmListDTO;
import dream.rcm.system.form.LovRcmListForm;
import dream.rcm.system.service.LovRcmListService;

/**
 * 자산 팝업
 * @author  kim21017
 * @version $Id: LovRcmListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovRcmList" name="lovRcmListForm"
 *                input="/dream/rcm/system/lovRcmListPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovRcmPopup" path="/dream/rcm/system/lovRcmListPopup.jsp"
 *                        redirect="false"
 */
public class LovRcmListAction extends AuthAction
{
    public static final int LOV_ASSET_DEFAULT 	= 1001;
    public static final int LOV_ASSET_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovRcmListForm lovRcmListForm = (LovRcmListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovRcmListForm.getStrutsAction())
        {
            case LovRcmListAction.LOV_ASSET_DEFAULT :
                returnActionForward = mapping.findForward("lovRcmPopup");
                break;
            case LovRcmListAction.LOV_ASSET_FIND :
                findRcmList(request, lovRcmListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovRcmListAction.BASE_SET_HEADER:
                setHeader(request, response, lovRcmListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovRcmListForm lovRcmListForm) throws IOException
    {
        super.setHeader(request, response, lovRcmListForm.getListId(),lovRcmListForm.getCurrentPageId()); 
    }

    /**
     * TAASSET을 검색한다.
     * @author  kim21017
     * @version $Id: LovRcmListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovRcmListForm
     */
    private void findRcmList(HttpServletRequest request,
            LovRcmListForm lovRcmListForm,HttpServletResponse response) throws IOException
    {
        LovRcmListService lovRcmListService = (LovRcmListService)getBean("lovRcmListService");
        LovRcmListDTO lovRcmListDTO = lovRcmListForm.getLovRcmListDTO();
        List resultList = lovRcmListService.findRcmList(lovRcmListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovRcmListForm.getListId());
    	
    }

}