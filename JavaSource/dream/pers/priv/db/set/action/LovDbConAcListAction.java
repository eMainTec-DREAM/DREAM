package dream.pers.priv.db.set.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.priv.db.set.dto.LovDbConAcListDTO;
import dream.pers.priv.db.set.form.LovDbConAcListForm;
import dream.pers.priv.db.set.service.LovDbConAcListService;

/**
 * ÄÁÅÙÃ÷ ¼±ÅÃ ÆË¾÷
 * @author  nhkim8548
 * @version $Id: LovDbConListAction.java,v 1.0 2018/08/06 09:39:40 nhkim8548 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovDbConAcList" name="lovDbConAcListForm"
 *                input="/dream/pers/priv/db/set/lovDbConAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovDbConAcList" path="/dream/pers/priv/db/set/lovDbConAcList.jsp"
 *                        redirect="false"
 */
public class LovDbConAcListAction extends AuthAction
{
    public static final int LOV_CON_DEFAULT	        = 1001;
    /** Á¶È¸ */
    public static final int LOV_CON_FIND			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	LovDbConAcListForm lovDbConAcListForm = (LovDbConAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovDbConAcListForm.getStrutsAction())
        {
        	case LovDbConAcListAction.LOV_CON_DEFAULT :
        		returnActionForward = mapping.findForward("lovDbConAcList");
        		break;
            case LovDbConAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovDbConAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovDbConAcListAction.LOV_CON_FIND :
                findList(request, lovDbConAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("lovDbConAcList");
                break;
        }
        
        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovDbConAcListForm lovDbConAcListForm) throws IOException
    {
        super.setHeader(request, response, lovDbConAcListForm.getListId(), lovDbConAcListForm.getCurrentPageId()); 
    }

    /**
     * ÄÁÅÙÃ÷ ¸®½ºÆ® 
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovDbConAcListForm
     */
    private void findList(HttpServletRequest request, LovDbConAcListForm lovDbConAcListForm, HttpServletResponse response) throws IOException
    {
        LovDbConAcListService lovDbConAcListService = (LovDbConAcListService)getBean("lovDbConAcListService");
        LovDbConAcListDTO lovDbConAcListDTO = lovDbConAcListForm.getLovDbConAcListDTO();
        List resultList = lovDbConAcListService.findList(lovDbConAcListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovDbConAcListForm.getListId());
    }
}