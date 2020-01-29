package dream.asset.loc.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.asset.loc.list.dto.LovEqLocListDTO;
import dream.asset.loc.list.form.LovEqLocListForm;
import dream.asset.loc.list.service.LovEqLocListService;

/**
 * 위치분류 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEqLocList" name="lovEqLocListForm"
 *                input="/dream/asset/loc/list/lovEqLocList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/eqLocValLov" name="lovEqLocListForm"
 *                input="/dream/asset/loc/list/eqLocValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqLocPopup" path="/dream/asset/loc/list/lovEqLocPopup.jsp"
 *                        redirect="false"
 */
public class LovEqLocListAction extends SuperAuthAction
{
    public static final int LOV_EQLOC_DEFAULT 	= 1001;
    public static final int LOV_EQLOC_FIND      = 1002;
    public static final int LOV_EQLOC_AC_FIND   = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEqLocListForm lovEqLocListForm = (LovEqLocListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEqLocListForm.getStrutsAction())
        {
            case LovEqLocListAction.LOV_EQLOC_DEFAULT :
            	returnActionForward = mapping.getInputForward();
                break;
            case LovEqLocListAction.LOV_EQLOC_FIND :
                findEqLocList(request, lovEqLocListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqLocListAction.LOV_EQLOC_AC_FIND :
            	findEqLocAcList(request, lovEqLocListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqLocListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqLocListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqLocListForm lovEqLocListForm) throws IOException
    {
        super.setHeader(request, response, lovEqLocListForm.getListId(),lovEqLocListForm.getCurrentPageId()); 
    }

    /**
     * TAEQLOC 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEqLocListForm
     */
    private void findEqLocList(HttpServletRequest request,
            LovEqLocListForm lovEqLocListForm,HttpServletResponse response) throws IOException
    {
        LovEqLocListService lovEqLocListService = (LovEqLocListService)getBean("lovEqLocListService");
        
        LovEqLocListDTO lovEqLocListDTO = lovEqLocListForm.getLovEqLocListDTO();
        
        List resultList = lovEqLocListService.findEqLocList(lovEqLocListDTO, getUser(request));
        
        //super.makeJsonResult(resultList, request, response, lovEqLocListForm.getListId());
        super.makeTreeJsonResult(resultList, request, response, lovEqLocListForm.getListId());
    }
    /**
     * TAEQLOC AC를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEqLocListForm
     */
    private void findEqLocAcList(HttpServletRequest request,
            LovEqLocListForm lovEqLocListForm,HttpServletResponse response) throws IOException
    {
        LovEqLocListService lovEqLocListService = (LovEqLocListService)getBean("lovEqLocListService");
        List resultList = lovEqLocListService.findEqLocAcList(lovEqLocListForm, getUser(request));
        
        //super.makeJsonResult(resultList, request, response, lovEqLocListForm.getListId());
        super.makeTreeJsonResult(resultList, request, response, lovEqLocListForm.getListId());
    }
}