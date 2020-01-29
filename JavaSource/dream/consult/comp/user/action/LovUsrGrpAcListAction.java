package dream.consult.comp.user.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.consult.comp.user.dto.LovUsrGrpAcListDTO;
import dream.consult.comp.user.form.LovUsrGrpAcListForm;
import dream.consult.comp.user.service.LovUsrGrpAcListService;

/**
 * LOV- List Action
 * 
 * @author youngjoo38
 * @version $Id: LovUsrGrpAcListAction.java,v 1.0 2017/09/12 17:04:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/lovUsrGrpAcList" name="lovUsrGrpAcListForm"
 *                input="/dream/consult/comp/user/lovUsrGrpAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovUsrGrpAcList" path="/dream/consult/comp/user/lovUsrGrpAcList.jsp"
 *                        redirect="false"
 */
public class LovUsrGrpAcListAction extends SuperAuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        LovUsrGrpAcListForm lovUsrGrpAcListForm = (LovUsrGrpAcListForm) form;
        
        switch (lovUsrGrpAcListForm.getStrutsAction())
        {
            case LovUsrGrpAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovUsrGrpAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovUsrGrpAcListAction.LIST_FIND:
                findList(request, response, lovUsrGrpAcListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("lovUsrGrpAcList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovUsrGrpAcListForm lovUsrGrpAcListForm) throws IOException
    {
        super.setHeader(request, response, lovUsrGrpAcListForm.getListId(), lovUsrGrpAcListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovUsrGrpAcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovUsrGrpAcListForm lovUsrGrpAcListForm, boolean excelExport) throws Exception
    {
    	LovUsrGrpAcListService lovUsrGrpAcListService = (LovUsrGrpAcListService) getBean("lovUsrGrpAcListService");
    	LovUsrGrpAcListDTO lovUsrGrpAcListDTO = lovUsrGrpAcListForm.getLovUsrGrpAcListDTO();
    	
    	//Paging
    	lovUsrGrpAcListDTO.setIsLoadMaxCount("Y".equals(lovUsrGrpAcListForm.getIsLoadMaxCount())?true:false);
    	lovUsrGrpAcListDTO.setFirstRow(lovUsrGrpAcListForm.getFirstRow());
    	lovUsrGrpAcListDTO.setOrderBy(lovUsrGrpAcListForm.getOrderBy());
    	lovUsrGrpAcListDTO.setDirection(lovUsrGrpAcListForm.getDirection());
    	
        List resultList = lovUsrGrpAcListService.findList(lovUsrGrpAcListForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(lovUsrGrpAcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovUsrGrpAcListService.findTotalCount(lovUsrGrpAcListForm,getUser(request));

//        if(excelExport) super.makeGridExport(resultList, request, response,lovUsrGrpAcListForm.getListId(),lovUsrGrpAcListForm.getCurrentPageId(), lovUsrGrpAcListForm.getFileName());
//        else super.makeJsonResult(resultList, request, response, totalCount);
        
        super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
