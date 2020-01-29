package dream.org.rpt.empmttr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;
import dream.org.rpt.empmttr.form.OrgRptEmpMttrListForm;
import dream.org.rpt.empmttr.service.OrgRptEmpMttrListService;

/**
 * MTTR(담당자)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/orgRptEmpMttrList" name="orgRptEmpMttrListForm"
 *                input="/dream/org/rpt/empmttr/orgRptEmpMttrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgRptEmpMttrList" path="/dream/org/rpt/empmttr/orgRptEmpMttrList.jsp"
 *                        redirect="false"
 */
public class OrgRptEmpMttrListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EMP_MTTR_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgRptEmpMttrListForm orgRptEmpMttrListForm = (OrgRptEmpMttrListForm) form;
        
        switch (orgRptEmpMttrListForm.getStrutsAction())
        {
        
            case OrgRptEmpMttrListAction.EMP_MTTR_LIST_FIND:
                findList(request,response, orgRptEmpMttrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptEmpMttrListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, orgRptEmpMttrListForm.getListId(), orgRptEmpMttrListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptEmpMttrListAction.BASE_GRID_EXPORT:
            	findList(request,response, orgRptEmpMttrListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param orgRptEmpMttrListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, OrgRptEmpMttrListForm orgRptEmpMttrListForm, boolean excelExport) throws Exception
    {
        OrgRptEmpMttrListService orgRptEmpMttrListService = (OrgRptEmpMttrListService) getBean("orgRptEmpMttrListService");
        
        OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO = orgRptEmpMttrListForm.getOrgRptEmpMttrCommonDTO();
        
        //Paging
        orgRptEmpMttrCommonDTO.setIsLoadMaxCount("Y".equals(orgRptEmpMttrListForm.getIsLoadMaxCount())?true:false);
        orgRptEmpMttrCommonDTO.setFirstRow(orgRptEmpMttrListForm.getFirstRow());
        orgRptEmpMttrCommonDTO.setOrderBy(orgRptEmpMttrListForm.getOrderBy());
        orgRptEmpMttrCommonDTO.setDirection(orgRptEmpMttrListForm.getDirection());
        
        List resultList = orgRptEmpMttrListService.findList(orgRptEmpMttrCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(orgRptEmpMttrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = orgRptEmpMttrListService.findTotalCount(orgRptEmpMttrCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,orgRptEmpMttrListForm.getListId(),orgRptEmpMttrListForm.getCurrentPageId(), orgRptEmpMttrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}