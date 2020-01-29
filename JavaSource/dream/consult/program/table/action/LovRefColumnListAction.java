package dream.consult.program.table.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.program.table.dto.LovRefColumnListDTO;
import dream.consult.program.table.form.LovRefColumnListForm;
import dream.consult.program.table.service.LovRefColumnListService;

/**
 * Ref테이블 팝업
 * @author  pochul2423
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovRefColumnList" name="lovRefColumnListForm"
 *                input="/dream/consult/program/table/lovRefColumnList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovRefColumnPopup" path="/dream/consult/program/table/lovRefColumnPopup.jsp"
 *                        redirect="false"
 */
public class LovRefColumnListAction extends AuthAction
{
    public static final int LOV_WKCTR_DEFAULT 	= 1001;
    public static final int LOV_WKCTR_FIND      = 1002;
    public static final int LOV_COL_FIND        = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovRefColumnListForm lovRefColumnListForm = (LovRefColumnListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovRefColumnListForm.getStrutsAction())
        {
            case LovRefColumnListAction.LOV_WKCTR_DEFAULT :
                returnActionForward = mapping.findForward("lovRefColumnPopup");
                break;
            case LovRefColumnListAction.LOV_WKCTR_FIND :
                findWkCtrList(request, lovRefColumnListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovRefColumnListAction.LOV_COL_FIND :
                findColList(request, lovRefColumnListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovRefColumnListAction.BASE_SET_HEADER:
                setHeader(request, response, lovRefColumnListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovRefColumnListForm lovRefColumnListForm) throws IOException
    {
        super.setHeader(request, response, lovRefColumnListForm.getListId(),lovRefColumnListForm.getCurrentPageId()); 
    }

    /**
     * Table 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovRefColumnListForm
     */
    private void findWkCtrList(HttpServletRequest request,
            LovRefColumnListForm lovRefColumnListForm,HttpServletResponse response) throws IOException
    {
        LovRefColumnListService lovRefColumnListService = (LovRefColumnListService)getBean("lovRefColumnListService");
        
        LovRefColumnListDTO lovRefColumnListDTO = lovRefColumnListForm.getLovRefColumnListDTO();
        List resultList = lovRefColumnListService.findWkCtrList(lovRefColumnListDTO, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, lovRefColumnListForm.getListId());
    	
    }
    
    /**
     * Column 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovRefColumnListForm
     */
    private void findColList(HttpServletRequest request,
            LovRefColumnListForm lovRefColumnListForm,HttpServletResponse response) throws IOException
    {
        LovRefColumnListService lovRefColumnListService = (LovRefColumnListService)getBean("lovRefColumnListService");
        
        LovRefColumnListDTO lovRefColumnListDTO = lovRefColumnListForm.getLovRefColumnListDTO();
        List resultList = lovRefColumnListService.findColList(lovRefColumnListDTO, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, lovRefColumnListForm.getListId());
    	
    }

}