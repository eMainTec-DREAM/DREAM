package dream.consult.program.lang.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.consult.program.lang.dto.LovLangListDTO;
import dream.consult.program.lang.form.LovLangListForm;
import dream.consult.program.lang.service.LovLangListService;

/**
 * 다국어 팝업
 * @author  kim21017
 * @version $Id: LovLangListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovLangList" name="lovLangListForm"
 *                input="/dream/consult/program/lang/lovLangList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/langValLov" name="lovLangListForm"
 *                input="/dream/consult/program/lang/langValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovLangPopup" path="/dream/consult/program/lang/lovLangPopup.jsp"
 *                        redirect="false"
 */
public class LovLangListAction extends AuthAction
{
    public static final int LOV_LANG_DEFAULT 	= 1001;
    public static final int LOV_LANG_FIND 		= 1002;
    public static final int LOV_LANG_AC_FIND	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovLangListForm lovLangListForm = (LovLangListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovLangListForm.getStrutsAction())
        {
            case LovLangListAction.LOV_LANG_DEFAULT :
                returnActionForward = mapping.findForward("lovLangPopup");
                break;
            case LovLangListAction.LOV_LANG_FIND :
                findLangList(request, lovLangListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovLangListAction.LOV_LANG_AC_FIND :
                findLangAcList(request, lovLangListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovLangListAction.BASE_SET_HEADER:
                setHeader(request, response, lovLangListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovLangListForm lovLangListForm) throws IOException
    {
        super.setHeader(request, response, lovLangListForm.getListId(),lovLangListForm.getCurrentPageId()); 
    }

    /**
     * TALANG을 검색한다.
     * @author  kim21017
     * @version $Id: LovLangListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovLangListForm
     * @throws Exception 
     */
    private void findLangList(HttpServletRequest request,
            LovLangListForm lovLangListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        LovLangListService lovLangListService = (LovLangListService)getBean("lovLangListService");
        LovLangListDTO lovLangListDTO = lovLangListForm.getLovLangListDTO();
        
        //Paging
        lovLangListDTO.setIsLoadMaxCount("Y".equals(lovLangListForm.getIsLoadMaxCount())?true:false);
        lovLangListDTO.setFirstRow(lovLangListForm.getFirstRow());
        lovLangListDTO.setOrderBy(lovLangListForm.getOrderBy());
        lovLangListDTO.setDirection(lovLangListForm.getDirection());
        
        List resultList = lovLangListService.findLangList(lovLangListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovLangListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovLangListService.findTotalCount(lovLangListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovLangListForm.getListId(),lovLangListForm.getCurrentPageId(), lovLangListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }
    /**
     * TALANG AC LOV을 검색한다.
     * @author  kim21017
     * @version $Id: LovLangListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovLangListForm
     * @throws Exception 
     */
    private void findLangAcList(HttpServletRequest request,
            LovLangListForm lovLangListForm,HttpServletResponse response,  boolean excelExport) throws Exception
    {
        LovLangListService lovLangListService = (LovLangListService)getBean("lovLangListService");
        LovLangListDTO lovLangListDTO = lovLangListForm.getLovLangListDTO();
        
        //Paging
        lovLangListDTO.setIsLoadMaxCount("Y".equals(lovLangListForm.getIsLoadMaxCount())?true:false);
        lovLangListDTO.setFirstRow(lovLangListForm.getFirstRow());
        lovLangListDTO.setOrderBy(lovLangListForm.getOrderBy());
        lovLangListDTO.setDirection(lovLangListForm.getDirection());
        
        List resultList = lovLangListService.findLangAcList(lovLangListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovLangListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovLangListService.findTotalCount(lovLangListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,lovLangListForm.getListId(),lovLangListForm.getCurrentPageId(), lovLangListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }
}