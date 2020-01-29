package dream.asset.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.LovEqToolAcListDTO;
import dream.asset.list.form.LovEqToolAcListForm;
import dream.asset.list.service.LovEqToolAcListService;

/**
 * LOV- List Action
 * 
 * @author youngjoo38
 * @version $Id: LovEqToolAcListAction.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/lovEqToolAcList" name="lovEqToolAcListForm"
 *                input="/dream/asset/list/lovEqToolAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqToolAcList" path="/dream/asset/list/lovEqToolAcList.jsp"
 *                        redirect="false"
 */

public class LovEqToolAcListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        LovEqToolAcListForm lovEqToolAcListForm = (LovEqToolAcListForm) form;
        
        switch (lovEqToolAcListForm.getStrutsAction())
        {
            case LovEqToolAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqToolAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqToolAcListAction.LIST_FIND:
                findList(request, response, lovEqToolAcListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("lovEqToolAcList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqToolAcListForm lovEqToolAcListForm) throws IOException
    {
        super.setHeader(request, response, lovEqToolAcListForm.getListId(), lovEqToolAcListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovEqToolAcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovEqToolAcListForm lovEqToolAcListForm, boolean excelExport) throws Exception
    {
    	LovEqToolAcListService lovEqToolAcListService = (LovEqToolAcListService) getBean("lovEqToolAcListService");
    	LovEqToolAcListDTO lovEqToolAcListDTO = lovEqToolAcListForm.getLovEqToolAcListDTO();
    	
    	//Paging
    	lovEqToolAcListDTO.setIsLoadMaxCount("Y".equals(lovEqToolAcListForm.getIsLoadMaxCount())?true:false);
    	lovEqToolAcListDTO.setFirstRow(lovEqToolAcListForm.getFirstRow());
    	lovEqToolAcListDTO.setOrderBy(lovEqToolAcListForm.getOrderBy());
    	lovEqToolAcListDTO.setDirection(lovEqToolAcListForm.getDirection());
    	
        List resultList = lovEqToolAcListService.findList(lovEqToolAcListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(lovEqToolAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEqToolAcListService.findTotalCount(lovEqToolAcListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,lovEqToolAcListForm.getListId(),lovEqToolAcListForm.getCurrentPageId(), lovEqToolAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
