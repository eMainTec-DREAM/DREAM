package dream.org.emp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;
import dream.org.emp.form.OrgEmpTrainListForm;
import dream.org.emp.service.OrgEmpTrainListService;
import dream.pers.priv.db.set.service.PersPrivDbSetListService;

/**
 * 구매신청 item  목록
 * @author  kim21017
 * @version $Id: OrgEmpTrainListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/orgEmpTrainList" name="orgEmpTrainListForm"
 *                input="/dream/org/emp/orgEmpTrainList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgEmpTrainList" path="/dream/org/emp/orgEmpTrainList.jsp"
 *                        redirect="false"
 */
public class OrgEmpTrainListAction extends AuthAction
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
        OrgEmpTrainListForm orgEmpTrainListForm = (OrgEmpTrainListForm) form;
        
        super.updateAudit(orgEmpTrainListForm.getOrgEmpTrainListDTO().getAuditKey()==""?orgEmpTrainListForm.getOrgEmpTrainListDTO().getAuditKey():orgEmpTrainListForm.getOrgEmpTrainListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (orgEmpTrainListForm.getStrutsAction())
        {
            case OrgEmpTrainListAction.BASE_SET_HEADER:
                super.setHeader(request, response, orgEmpTrainListForm.getListId(), orgEmpTrainListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgEmpTrainListAction.BUY_ITEM_LIST_FIND:
                findItemList(request, response, orgEmpTrainListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgEmpTrainListAction.BUY_ITEM_LIST_DELETE:
            	deleteItemList(request,orgEmpTrainListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case OrgEmpTrainListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, orgEmpTrainListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("orgEmpTrainList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: OrgEmpTrainListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param orgEmpTrainListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, OrgEmpTrainListForm orgEmpTrainListForm, boolean excelExport) throws Exception
    {
        OrgEmpTrainListService orgEmpTrainListService = (OrgEmpTrainListService) getBean("orgEmpTrainListService");
        MaEmpCommonDTO maEmpCommonDTO = orgEmpTrainListForm.getMaEmpCommonDTO();
        maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
        OrgEmpTrainListDTO orgEmpTrainListDTO = orgEmpTrainListForm.getOrgEmpTrainListDTO();

        // Paging
    	maEmpCommonDTO.setIsLoadMaxCount("Y".equals(orgEmpTrainListForm.getIsLoadMaxCount())?true:false);
    	maEmpCommonDTO.setFirstRow(orgEmpTrainListForm.getFirstRow());
    	maEmpCommonDTO.setOrderBy(orgEmpTrainListForm.getOrderBy());
    	maEmpCommonDTO.setDirection(orgEmpTrainListForm.getDirection());
        
        List resultList = orgEmpTrainListService.findItemList(maEmpCommonDTO, orgEmpTrainListDTO,getUser(request));
        
        // Paging
        String totalCount = "";
        if(Integer.parseInt(orgEmpTrainListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = orgEmpTrainListService.findTotalCount(maEmpCommonDTO, orgEmpTrainListDTO, getUser(request));
        if(excelExport) super.makeGridExport(resultList, request, response, orgEmpTrainListForm.getListId(),orgEmpTrainListForm.getCurrentPageId(), orgEmpTrainListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: OrgEmpTrainListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param orgEmpTrainListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, OrgEmpTrainListForm orgEmpTrainListForm) throws Exception
    {
    	OrgEmpTrainListService orgEmpTrainListService = (OrgEmpTrainListService) getBean("orgEmpTrainListService");
    	
    	String[] deleteRows = orgEmpTrainListForm.getDeleteRows();
    	
    	User user = getUser(request);
    	
    	orgEmpTrainListService.deleteItemList(deleteRows, user);
    	
    	setAjaxStatus(request);
     }
}