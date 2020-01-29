package dream.org.emp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;
import dream.org.emp.form.OrgEmpCertListForm;
import dream.org.emp.service.OrgEmpCertListService;

/**
 * 구매신청 item  목록
 * @author  kim21017
 * @version $Id: OrgEmpCertListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/orgEmpCertList" name="orgEmpCertListForm"
 *                input="/dream/org/emp/orgEmpCertList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgEmpCertList" path="/dream/org/emp/orgEmpCertList.jsp"
 *                        redirect="false"
 */
public class OrgEmpCertListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int BUY_ITEM_LIST_FIND 			= 8001;
    /** 삭제 */
    public static final int BUY_ITEM_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgEmpCertListForm orgEmpCertListForm = (OrgEmpCertListForm) form;
        
        super.updateAudit(orgEmpCertListForm.getOrgEmpCertListDTO().getAuditKey()==""?orgEmpCertListForm.getOrgEmpCertListDTO().getAuditKey():orgEmpCertListForm.getOrgEmpCertListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (orgEmpCertListForm.getStrutsAction())
        {
            case OrgEmpCertListAction.BASE_SET_HEADER:
                super.setHeader(request, response, orgEmpCertListForm.getListId(), orgEmpCertListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgEmpCertListAction.BUY_ITEM_LIST_FIND:
                findItemList(request, response, orgEmpCertListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgEmpCertListAction.BUY_ITEM_LIST_DELETE:
            	deleteItemList(request,orgEmpCertListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case OrgEmpCertListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, orgEmpCertListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("orgEmpCertList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: OrgEmpCertListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param orgEmpCertListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, OrgEmpCertListForm orgEmpCertListForm, boolean excelExport) throws Exception
    {
        OrgEmpCertListService orgEmpCertListService = (OrgEmpCertListService) getBean("orgEmpCertListService");
        MaEmpCommonDTO maEmpCommonDTO = orgEmpCertListForm.getMaEmpCommonDTO();
        maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
        OrgEmpCertListDTO orgEmpCertListDTO = orgEmpCertListForm.getOrgEmpCertListDTO();
        
        // Paging
    	maEmpCommonDTO.setIsLoadMaxCount("Y".equals(orgEmpCertListForm.getIsLoadMaxCount())?true:false);
    	maEmpCommonDTO.setFirstRow(orgEmpCertListForm.getFirstRow());
    	maEmpCommonDTO.setOrderBy(orgEmpCertListForm.getOrderBy());
    	maEmpCommonDTO.setDirection(orgEmpCertListForm.getDirection());
        
        List resultList = orgEmpCertListService.findItemList(maEmpCommonDTO, orgEmpCertListDTO,getUser(request));

        // Paging
        String totalCount = "";
        if(Integer.parseInt(orgEmpCertListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = orgEmpCertListService.findTotalCount(maEmpCommonDTO,orgEmpCertListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,orgEmpCertListForm.getListId(),orgEmpCertListForm.getCurrentPageId(), orgEmpCertListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: OrgEmpCertListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param orgEmpCertListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, OrgEmpCertListForm orgEmpCertListForm) throws Exception
    {
    	OrgEmpCertListService orgEmpCertListService = (OrgEmpCertListService) getBean("orgEmpCertListService");
        
    	orgEmpCertListService.deleteItemList(orgEmpCertListForm.getDeleteRows(), orgEmpCertListForm.getDeleteRowsExt(), getUser(request));
    	
    	setAjaxStatus(request);
    }
}