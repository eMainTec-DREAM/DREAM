package dream.consult.comp.plant.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.consult.comp.plant.dto.LovPlantListDTO;
import dream.consult.comp.plant.form.LovPlantListForm;
import dream.consult.comp.plant.service.LovPlantListService;

/**
 * 플랜트 팝업
 * @author  kim21017
 * @version $Id: LovPlantListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovPlantList" name="lovPlantListForm"
 *                input="/dream/consult/comp/plant/lovPlantList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/plantValLov" name="lovPlantListForm"
 *                input="/dream/consult/comp/plant/plantValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovLinePopup" path="/dream/consult/comp/plant/lovPlantPopup.jsp"
 *                        redirect="false"
 */
public class LovPlantListAction extends SuperAuthAction
{
    public static final int LOV_PLANT_DEFAULT 	= 1001;
    public static final int LOV_PLANT_FIND 		= 1002;
    public static final int LOV_PLANT_AC_FIND   = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPlantListForm lovPlantListForm = (LovPlantListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPlantListForm.getStrutsAction())
        {
            case LovPlantListAction.LOV_PLANT_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovPlantListAction.LOV_PLANT_FIND :
                findPlantList(request, lovPlantListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPlantListAction.LOV_PLANT_AC_FIND :
            	findPlantAcList(request, lovPlantListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPlantListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPlantListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPlantListForm lovPlantListForm) throws IOException
    {
        super.setHeader(request, response, lovPlantListForm.getListId(),lovPlantListForm.getCurrentPageId()); 
    }

    /**
     * PLANT 검색
     * @author  kim21017
     * @version $Id: LovPlantListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovPlantListForm
     */
    private void findPlantList(HttpServletRequest request,
            LovPlantListForm lovPlantListForm,HttpServletResponse response) throws IOException
    {
        LovPlantListService lovPlantListService = (LovPlantListService)getBean("lovPlantListService");
        LovPlantListDTO lovPlantListDTO = lovPlantListForm.getLovPlantListDTO();
        List resultList = lovPlantListService.findPlantList(lovPlantListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovPlantListForm.getListId());
    	
    }
    /**
     * PLANT AC를 검색한다.
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovPlantListForm
     * @param response
     */
    private void findPlantAcList(HttpServletRequest request,
            LovPlantListForm lovPlantListForm,HttpServletResponse response) throws IOException
    {
    	LovPlantListService lovPlantListService = (LovPlantListService)getBean("lovPlantListService");
        List resultList = lovPlantListService.findPlantAcList(lovPlantListForm, getUser(request));
        
        //super.makeJsonResult(resultList, request, response, lovEqLocListForm.getListId());
        super.makeJsonResult(resultList, request, response, lovPlantListForm.getListId());
    }

}