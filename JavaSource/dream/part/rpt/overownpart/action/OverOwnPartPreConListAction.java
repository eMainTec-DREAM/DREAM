package dream.part.rpt.overownpart.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.rpt.overownpart.dto.OverOwnPartPreConCommonDTO;
import dream.part.rpt.overownpart.form.OverOwnPartPreConListForm;
import dream.part.rpt.overownpart.service.OverOwnPartPreConListService;

/**
 * OverOwnPartPreCon Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/overOwnPartPreConList" name="overOwnPartPreConListForm"
 *                input="/dream/part/rpt/overownpart/overOwnPartPreConList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="overOwnPartPreConList" path="/dream/part/rpt/overownpart/overOwnPartPreConList.jsp"
 *                        redirect="false"
 */
public class OverOwnPartPreConListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        OverOwnPartPreConListForm overOwnPartPreConListForm = (OverOwnPartPreConListForm) form;
        
        switch (overOwnPartPreConListForm.getStrutsAction())
        {
            case OverOwnPartPreConListAction.BASE_SET_HEADER:
                setHeader(request, response, overOwnPartPreConListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OverOwnPartPreConListAction.LIST_FIND:
                findList(request, response, overOwnPartPreConListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case OverOwnPartPreConListAction.BASE_GRID_EXPORT:
                findList(request, response, overOwnPartPreConListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("overOwnPartPreConList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, OverOwnPartPreConListForm overOwnPartPreConListForm) throws IOException
    {
        super.setHeader(request, response, overOwnPartPreConListForm.getListId(), overOwnPartPreConListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param overOwnPartPreConListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, OverOwnPartPreConListForm overOwnPartPreConListForm, boolean excelExport) throws Exception
    {
        OverOwnPartPreConListService overOwnPartPreConListService = (OverOwnPartPreConListService) getBean("overOwnPartPreConListService");
        OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO = overOwnPartPreConListForm.getOverOwnPartPreConCommonDTO();
      
        //Paging
        overOwnPartPreConCommonDTO.setIsLoadMaxCount("Y".equals(overOwnPartPreConListForm.getIsLoadMaxCount())?true:false);
        overOwnPartPreConCommonDTO.setFirstRow(overOwnPartPreConListForm.getFirstRow());
        overOwnPartPreConCommonDTO.setOrderBy(overOwnPartPreConListForm.getOrderBy());
        overOwnPartPreConCommonDTO.setDirection(overOwnPartPreConListForm.getDirection());
        
        User user = getUser(request);
        List resultList = overOwnPartPreConListService.findList(overOwnPartPreConCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(overOwnPartPreConListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = overOwnPartPreConListService.findTotalCount(overOwnPartPreConCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,overOwnPartPreConListForm.getListId(),overOwnPartPreConListForm.getCurrentPageId(), overOwnPartPreConListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}