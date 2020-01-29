package dream.asset.loc.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.asset.loc.list.dto.LovMesLineListDTO;
import dream.asset.loc.list.form.LovMesLineListForm;
import dream.asset.loc.list.service.LovMesLineListService;

/**
 * MESLINE 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovMesLineList" name="lovMesLineListForm"
 *                input="/dream/asset/loc/list/lovMesLineList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovMesLinePopup" path="/dream/asset/loc/list/lovMesLinePopup.jsp"
 *                        redirect="false"
 */
public class LovMesLineListAction extends SuperAuthAction
{
    public static final int LOV_MESLINE_DEFAULT 	= 1001;
    public static final int LOV_MESLINE_FIND     	= 1002;
    
    public static final int LOV_MESLINE_AC_FIND        = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovMesLineListForm lovMesLineListForm = (LovMesLineListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovMesLineListForm.getStrutsAction())
        {
            case LovMesLineListAction.LOV_MESLINE_DEFAULT :
                returnActionForward = mapping.findForward("lovMesLinePopup");
                break;
            case LovMesLineListAction.LOV_MESLINE_FIND :
                findMesLineList(request, lovMesLineListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovMesLineListAction.LOV_MESLINE_AC_FIND :
                findMesLineACList(request, lovMesLineListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovMesLineListAction.BASE_SET_HEADER:
                setHeader(request, response, lovMesLineListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovMesLineListForm lovMesLineListForm) throws IOException
    {
        super.setHeader(request, response, lovMesLineListForm.getListId(),lovMesLineListForm.getCurrentPageId()); 
    }

    /**
     * TAMESLINE 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovMesLineListForm
     */
    private void findMesLineList(HttpServletRequest request,
            LovMesLineListForm lovMesLineListForm,HttpServletResponse response) throws IOException
    {
        LovMesLineListService lovMesLineListService = (LovMesLineListService)getBean("lovMesLineListService");
        
        LovMesLineListDTO lovMesLineListDTO = lovMesLineListForm.getLovMesLineListDTO();
        List resultList = lovMesLineListService.findMesLineList(lovMesLineListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovMesLineListForm.getListId());
    	
    }
    
    private void findMesLineACList(HttpServletRequest request,
            LovMesLineListForm lovMesLineListForm,HttpServletResponse response) throws IOException
    {
        LovMesLineListService lovMesLineListService = (LovMesLineListService)getBean("lovMesLineListService");
        
        List resultList = lovMesLineListService.findMesLineACList(lovMesLineListForm, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, lovMesLineListForm.getListId());
    }

}