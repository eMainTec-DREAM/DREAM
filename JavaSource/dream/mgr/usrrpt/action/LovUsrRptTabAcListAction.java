package dream.mgr.usrrpt.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.LovUsrRptTabAcListDTO;
import dream.mgr.usrrpt.form.LovUsrRptTabAcListForm;
import dream.mgr.usrrpt.service.LovUsrRptTabAcListService;

/**
 * LOV- List Action
 * 
 * @author youngjoo38
 * @version $Id: LovUsrRptTabAcListAction.java,v 1.0 2017/09/12 17:04:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/lovUsrRptTabAcList" name="lovUsrRptTabAcListForm"
 *                input="/dream/mgr/usrrpt/lovUsrRptTabAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovUsrRptTabAcList" path="/dream/mgr/usrrpt/lovUsrRptTabAcList.jsp"
 *                        redirect="false"
 */

public class LovUsrRptTabAcListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        LovUsrRptTabAcListForm lovUsrRptTabAcListForm = (LovUsrRptTabAcListForm) form;
        
        switch (lovUsrRptTabAcListForm.getStrutsAction())
        {
            case LovUsrRptTabAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovUsrRptTabAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUsrRptTabAcListAction.LIST_FIND:
                findList(request, response, lovUsrRptTabAcListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("lovUsrRptTabAcList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovUsrRptTabAcListForm lovUsrRptTabAcListForm) throws IOException
    {
        super.setHeader(request, response, lovUsrRptTabAcListForm.getListId(), lovUsrRptTabAcListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovUsrRptTabAcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovUsrRptTabAcListForm lovUsrRptTabAcListForm, boolean excelExport) throws Exception
    {
    	LovUsrRptTabAcListService lovUsrRptTabAcListService = (LovUsrRptTabAcListService) getBean("lovUsrRptTabAcListService");
    	LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO = lovUsrRptTabAcListForm.getLovUsrRptTabAcListDTO();
    	
    	//Paging
    	lovUsrRptTabAcListDTO.setIsLoadMaxCount("Y".equals(lovUsrRptTabAcListForm.getIsLoadMaxCount())?true:false);
    	lovUsrRptTabAcListDTO.setFirstRow(lovUsrRptTabAcListForm.getFirstRow());
    	lovUsrRptTabAcListDTO.setOrderBy(lovUsrRptTabAcListForm.getOrderBy());
    	lovUsrRptTabAcListDTO.setDirection(lovUsrRptTabAcListForm.getDirection());
    	
        List resultList = lovUsrRptTabAcListService.findList(lovUsrRptTabAcListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(lovUsrRptTabAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovUsrRptTabAcListService.findTotalCount(lovUsrRptTabAcListForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,lovUsrRptTabAcListForm.getListId(),lovUsrRptTabAcListForm.getCurrentPageId(), lovUsrRptTabAcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
