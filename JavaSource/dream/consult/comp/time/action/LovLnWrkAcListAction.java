package dream.consult.comp.time.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.time.dto.LovLnWrkAcListDTO;
import dream.consult.comp.time.form.LovLnWrkAcListForm;
import dream.consult.comp.time.service.LovLnWrkAcListService;

/**
 * 가동달력 LOV- List Action
 * 
 * @author youngjoo38
 * @version $Id: LovLnWrkAcListAction.java,v 1.0 2017/12/14 11:12:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/lovLnWrkAcList" name="lovLnWrkAcListForm"
 *                input="/dream/consult/comp/time/lovLnWrkAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovLnWrkAcList" path="/dream/consult/comp/time/lovLnWrkAcList.jsp"
 *                        redirect="false"
 */

public class LovLnWrkAcListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        LovLnWrkAcListForm lovLnWrkAcListForm = (LovLnWrkAcListForm) form;
        
        switch (lovLnWrkAcListForm.getStrutsAction())
        {
            case LovLnWrkAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovLnWrkAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovLnWrkAcListAction.LIST_FIND:
                findList(request, response, lovLnWrkAcListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("lovLnWrkAcList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovLnWrkAcListForm lovLnWrkAcListForm) throws IOException
    {
        super.setHeader(request, response, lovLnWrkAcListForm.getListId(), lovLnWrkAcListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovLnWrkAcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovLnWrkAcListForm lovLnWrkAcListForm, boolean excelExport) throws Exception
    {
    	LovLnWrkAcListService lovLnWrkAcListService = (LovLnWrkAcListService) getBean("lovLnWrkAcListService");
    	LovLnWrkAcListDTO lovLnWrkAcListDTO = lovLnWrkAcListForm.getLovLnWrkAcListDTO();
    	
    	//Paging
    	lovLnWrkAcListDTO.setIsLoadMaxCount("Y".equals(lovLnWrkAcListForm.getIsLoadMaxCount())?true:false);
    	lovLnWrkAcListDTO.setFirstRow(lovLnWrkAcListForm.getFirstRow());
    	lovLnWrkAcListDTO.setOrderBy(lovLnWrkAcListForm.getOrderBy());
    	lovLnWrkAcListDTO.setDirection(lovLnWrkAcListForm.getDirection());
    	
        List resultList = lovLnWrkAcListService.findList(lovLnWrkAcListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(lovLnWrkAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovLnWrkAcListService.findTotalCount(lovLnWrkAcListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,lovLnWrkAcListForm.getListId(),lovLnWrkAcListForm.getCurrentPageId(), lovLnWrkAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
