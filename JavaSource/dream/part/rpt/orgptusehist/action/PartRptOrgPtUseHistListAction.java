package dream.part.rpt.orgptusehist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistCommonDTO;
import dream.part.rpt.orgptusehist.form.PartRptOrgPtUseHistListForm;
import dream.part.rpt.orgptusehist.service.PartRptOrgPtUseHistListService;

/**
 * PartRptOrgPtUseHist Page - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partRptOrgPtUseHistList" name="partRptOrgPtUseHistListForm"
 *                input="/dream/part/rpt/orgptusehist/partRptOrgPtUseHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partRptOrgPtUseHistList" path="/dream/part/rpt/orgptusehist/partRptOrgPtUseHistList.jsp"
 *                        redirect="false"
 */
public class PartRptOrgPtUseHistListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartRptOrgPtUseHistListForm partRptOrgPtUseHistListForm = (PartRptOrgPtUseHistListForm) form;
        
        switch (partRptOrgPtUseHistListForm.getStrutsAction())
        {
            case PartRptOrgPtUseHistListAction.BASE_SET_HEADER:
                setHeader(request, response, partRptOrgPtUseHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartRptOrgPtUseHistListAction.LIST_FIND:
                findList(request, response, partRptOrgPtUseHistListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartRptOrgPtUseHistListAction.BASE_GRID_EXPORT:
                findList(request, response, partRptOrgPtUseHistListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partRptOrgPtUseHistList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartRptOrgPtUseHistListForm partRptOrgPtUseHistListForm) throws IOException
    {
        super.setHeader(request, response, partRptOrgPtUseHistListForm.getListId(), partRptOrgPtUseHistListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partRptOrgPtUseHistListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, PartRptOrgPtUseHistListForm partRptOrgPtUseHistListForm, boolean excelExport) throws Exception
    {
        PartRptOrgPtUseHistListService partRptOrgPtUseHistListService = (PartRptOrgPtUseHistListService) getBean("partRptOrgPtUseHistListService");
        PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO = partRptOrgPtUseHistListForm.getPartRptOrgPtUseHistCommonDTO();
      
        //Paging
        partRptOrgPtUseHistCommonDTO.setIsLoadMaxCount("Y".equals(partRptOrgPtUseHistListForm.getIsLoadMaxCount())?true:false);
        partRptOrgPtUseHistCommonDTO.setFirstRow(partRptOrgPtUseHistListForm.getFirstRow());
        partRptOrgPtUseHistCommonDTO.setOrderBy(partRptOrgPtUseHistListForm.getOrderBy());
        partRptOrgPtUseHistCommonDTO.setDirection(partRptOrgPtUseHistListForm.getDirection());
        
        User user = getUser(request);
        List resultList = partRptOrgPtUseHistListService.findList(partRptOrgPtUseHistCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(partRptOrgPtUseHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partRptOrgPtUseHistListService.findTotalCount(partRptOrgPtUseHistCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,partRptOrgPtUseHistListForm.getListId(),partRptOrgPtUseHistListForm.getCurrentPageId(), partRptOrgPtUseHistListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}