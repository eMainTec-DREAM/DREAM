package dream.consult.program.uploaddata.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.consult.program.uploaddata.dto.LovExcelTabAcListDTO;
import dream.consult.program.uploaddata.form.LovExcelTabAcListForm;
import dream.consult.program.uploaddata.service.LovExcelTabAcListService;

/**
 * Excel Tab 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovExcelTabAcList" name="lovExcelTabAcListForm"
 *                input="/dream/consult/program/uploaddata/lovExcelTabAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovExcelTabAcList" path="/dream/consult/program/uploaddata/lovExcelTabAcList.jsp"
 *                        redirect="false"
 */
public class LovExcelTabAcListAction extends SuperAuthAction
{
    public static final int LOV_EXCELTAB_DEFAULT 	= 1001;
    public static final int LOV_EXCELTAB_FIND     = 1002;
    
    public static final int LOV_EXCELTAB_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovExcelTabAcListForm lovExcelTabAcListForm = (LovExcelTabAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovExcelTabAcListForm.getStrutsAction())
        {
            case LovExcelTabAcListAction.LOV_EXCELTAB_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovExcelTabAcListAction.LOV_EXCELTAB_FIND :
                findExcelTabList(request, lovExcelTabAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovExcelTabAcListAction.LOV_EXCELTAB_AC_FIND :
                findExcelTabAcList(request, lovExcelTabAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovExcelTabAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovExcelTabAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovExcelTabAcListForm lovExcelTabAcListForm) throws IOException
    {
        super.setHeader(request, response, lovExcelTabAcListForm.getListId(),lovExcelTabAcListForm.getCurrentPageId()); 
    }

    /**
     * TAEXCELTAB 를 검색한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovExcelTabAcListForm
     */
    private void findExcelTabList(HttpServletRequest request,
            LovExcelTabAcListForm lovExcelTabAcListForm,HttpServletResponse response) throws IOException
    {
        LovExcelTabAcListService lovExcelTabAcListService = (LovExcelTabAcListService)getBean("lovExcelTabAcListService");
        
        LovExcelTabAcListDTO lovExcelTabAcListDTO = lovExcelTabAcListForm.getLovExcelTabAcListDTO();
        List resultList = lovExcelTabAcListService.findExcelTabList(lovExcelTabAcListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovExcelTabAcListForm.getListId());
    	
    }
    
    private void findExcelTabAcList(HttpServletRequest request,
            LovExcelTabAcListForm lovExcelTabAcListForm,HttpServletResponse response) throws IOException
    {
        LovExcelTabAcListService lovExcelTabAcListService = (LovExcelTabAcListService)getBean("lovExcelTabAcListService");
        
        LovExcelTabAcListDTO lovExcelTabAcListDTO = lovExcelTabAcListForm.getLovExcelTabAcListDTO();
        List resultList = lovExcelTabAcListService.findExcelTabAcList(lovExcelTabAcListDTO, getUser(request),lovExcelTabAcListForm);
        
        super.makeJsonResult(resultList, request, response, lovExcelTabAcListForm.getListId());
        
    }

}