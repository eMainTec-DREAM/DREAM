package dream.org.rpt.empmttr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrDetailDTO;
import dream.org.rpt.empmttr.form.OrgRptEmpMttrDetailForm;
import dream.org.rpt.empmttr.service.OrgRptEmpMttrDetailService;

/**
 * MTTR(담당자) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/orgRptEmpMttrDetailList" name="orgRptEmpMttrDetailForm"
 *                input="/dream/org/rpt/empmttr/orgRptEmpMttrDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/orgRptEmpMttrDetailChart" name="orgRptEmpMttrDetailForm"
 *                input="/dream/org/rpt/empmttr/orgRptEmpMttrDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class OrgRptEmpMttrDetailAction extends AuthAction
{
    public static final int EMP_MTTR_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgRptEmpMttrDetailForm orgRptEmpMttrDetailForm = (OrgRptEmpMttrDetailForm) form;
        switch (orgRptEmpMttrDetailForm.getStrutsAction())
        {
            case OrgRptEmpMttrDetailAction.EMP_MTTR_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, orgRptEmpMttrDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptEmpMttrDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, orgRptEmpMttrDetailForm.getListId(), orgRptEmpMttrDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptEmpMttrDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, orgRptEmpMttrDetailForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param orgRptEmpMttrDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, OrgRptEmpMttrDetailForm orgRptEmpMttrDetailForm, boolean excelExport) throws Exception
    {
        OrgRptEmpMttrDetailService orgRptEmpMttrDetailService = (OrgRptEmpMttrDetailService) getBean("orgRptEmpMttrDetailService");
        
        OrgRptEmpMttrDetailDTO orgRptEmpMttrDetailDTO = orgRptEmpMttrDetailForm.getOrgRptEmpMttrDetailDTO();
        OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO = orgRptEmpMttrDetailForm.getOrgRptEmpMttrCommonDTO();

        //Paging
        orgRptEmpMttrDetailDTO.setIsLoadMaxCount("Y".equals(orgRptEmpMttrDetailForm.getIsLoadMaxCount())?true:false);
        orgRptEmpMttrDetailDTO.setFirstRow(orgRptEmpMttrDetailForm.getFirstRow());
        orgRptEmpMttrDetailDTO.setOrderBy(orgRptEmpMttrDetailForm.getOrderBy());
        orgRptEmpMttrDetailDTO.setDirection(orgRptEmpMttrDetailForm.getDirection());
        
        List resultList = orgRptEmpMttrDetailService.findDetail(orgRptEmpMttrCommonDTO, orgRptEmpMttrDetailDTO, getUser(request));

        //Paging
        String totalCount = "";        
        if(Integer.parseInt(orgRptEmpMttrDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = orgRptEmpMttrDetailService.findTotalCount(orgRptEmpMttrCommonDTO, orgRptEmpMttrDetailDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,orgRptEmpMttrDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}