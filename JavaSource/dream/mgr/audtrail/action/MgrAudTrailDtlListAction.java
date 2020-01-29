package dream.mgr.audtrail.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDtlListDTO;
import dream.mgr.audtrail.form.MgrAudTrailDtlListForm;
import dream.mgr.audtrail.service.MgrAudTrailDtlListService;

/**
 * MgrAudTrailDtl Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: MgrAudTrailDtlListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/mgrAudTrailDtlList" name="mgrAudTrailDtlListForm"
 *                input="/dream/mgr/audtrail/mgrAudTrailDtlList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrAudTrailDtlList" path="/dream/mgr/audtrail/mgrAudTrailDtlList.jsp"
 *                        redirect="false"
 */
public class MgrAudTrailDtlListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrAudTrailDtlListForm mgrAudTrailDtlListForm = (MgrAudTrailDtlListForm) form;
        
        switch (mgrAudTrailDtlListForm.getStrutsAction())
        {
            case MgrAudTrailDtlListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrAudTrailDtlListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrAudTrailDtlListAction.LIST_FIND:
                findList(request, response, mgrAudTrailDtlListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrAudTrailDtlListAction.BASE_GRID_EXPORT:
                findList(request, response, mgrAudTrailDtlListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrAudTrailDtlListForm mgrAudTrailDtlListForm) throws IOException
    {
        super.setHeader(request, response, mgrAudTrailDtlListForm.getListId(), mgrAudTrailDtlListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrAudTrailDtlListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrAudTrailDtlListForm mgrAudTrailDtlListForm, boolean excelExport) throws Exception
    {
        MgrAudTrailDtlListService mgrAudTrailDtlListService = (MgrAudTrailDtlListService) getBean("mgrAudTrailDtlListService");
        MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO = mgrAudTrailDtlListForm.getMgrAudTrailDtlListDTO();
        MgrAudTrailCommonDTO mgrAudTrailCommonDTO = mgrAudTrailDtlListForm.getMgrAudTrailCommonDTO();
      
        //Paging
        mgrAudTrailCommonDTO.setIsLoadMaxCount("Y".equals(mgrAudTrailDtlListForm.getIsLoadMaxCount())?true:false);
        mgrAudTrailCommonDTO.setFirstRow(mgrAudTrailDtlListForm.getFirstRow());
        mgrAudTrailCommonDTO.setOrderBy(mgrAudTrailDtlListForm.getOrderBy());
        mgrAudTrailCommonDTO.setDirection(mgrAudTrailDtlListForm.getDirection());
        
        User user = getUser(request);
        List resultList = mgrAudTrailDtlListService.findList(mgrAudTrailCommonDTO, mgrAudTrailDtlListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrAudTrailDtlListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrAudTrailDtlListService.findTotalCount(mgrAudTrailCommonDTO,mgrAudTrailDtlListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,mgrAudTrailDtlListForm.getListId(),mgrAudTrailDtlListForm.getCurrentPageId(), mgrAudTrailDtlListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}