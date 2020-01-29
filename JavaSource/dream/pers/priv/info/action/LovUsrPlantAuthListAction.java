package dream.pers.priv.info.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;
import dream.pers.priv.info.form.LovUsrPlantAuthListForm;
import dream.pers.priv.info.service.LovUsrPlantAuthListService;

/**
 * 공장권한 팝업
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 * @struts:action path="/lovUsrPlantAuthList" name="lovUsrPlantAuthListForm"
 *                input="/dream/pers/priv/info/lovUsrPlantAuthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovUsrPlantAuthList" path="/dream/pers/priv/info/lovUsrPlantAuthList.jsp"
 *                        redirect="false"
 */
public class LovUsrPlantAuthListAction extends SuperAuthAction
{
    public static final int LOV_PLANT_DEFAULT 	= 1001;
    public static final int LOV_PLANT_FIND 		= 1002;
    public static final int LOV_PLANT_AC_FIND   = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovUsrPlantAuthListForm lovUsrPlantAuthListForm = (LovUsrPlantAuthListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovUsrPlantAuthListForm.getStrutsAction())
        {
            case LovUsrPlantAuthListAction.LOV_PLANT_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovUsrPlantAuthListAction.LOV_PLANT_FIND :
                findUsrPlantAuthList(request, lovUsrPlantAuthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUsrPlantAuthListAction.LOV_PLANT_AC_FIND :
            	findPlantAcList(request, lovUsrPlantAuthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUsrPlantAuthListAction.BASE_SET_HEADER:
                setHeader(request, response, lovUsrPlantAuthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovUsrPlantAuthListForm lovUsrPlantAuthListForm) throws IOException
    {
        super.setHeader(request, response, lovUsrPlantAuthListForm.getListId(),lovUsrPlantAuthListForm.getCurrentPageId()); 
    }

    /**
     * PLANT 검색
     * @author  youngjoo38
     * @version $Id: LovUsrPlantAuthListAction.java,v 1.2 2014/01/28 07:49:29 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovUsrPlantAuthListForm
     */
    private void findUsrPlantAuthList(HttpServletRequest request,
            LovUsrPlantAuthListForm lovUsrPlantAuthListForm,HttpServletResponse response) throws IOException
    {
        LovUsrPlantAuthListService lovUsrPlantAuthListService = (LovUsrPlantAuthListService)getBean("lovUsrPlantAuthListService");
        LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO = lovUsrPlantAuthListForm.getLovUsrPlantAuthListDTO();
        List resultList = lovUsrPlantAuthListService.findUsrPlantAuthList(lovUsrPlantAuthListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovUsrPlantAuthListForm.getListId());
    	
    }
    /**
     * PLANT AC를 검색한다.
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovUsrPlantAuthListForm
     * @param response
     */
    private void findPlantAcList(HttpServletRequest request,
            LovUsrPlantAuthListForm lovUsrPlantAuthListForm,HttpServletResponse response) throws IOException
    {
    	LovUsrPlantAuthListService lovUsrPlantAuthListService = (LovUsrPlantAuthListService)getBean("lovUsrPlantAuthListService");
        List resultList = lovUsrPlantAuthListService.findPlantAcList(lovUsrPlantAuthListForm, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovUsrPlantAuthListForm.getListId());
    }

}