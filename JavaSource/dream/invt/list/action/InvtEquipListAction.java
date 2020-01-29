package dream.invt.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.form.InvtEquipListForm;
import dream.invt.list.service.InvtEquipListService;


/**
 * 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/invtEquipList" name="invtEquipListForm"
 *                input="/dream/invt/list/invtEquipList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtEquipList" path="/dream/invt/list/invtEquipList.jsp"
 *                        redirect="false"
 */
public class InvtEquipListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    /** 신규설비등록 후 저장 */
    public static final int NEWEQ_LIST_INPUT= 5003;
    /** 기존설비등록 저장 */
    public static final int EQ_LIST_INPUT	= 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtEquipListForm invtEquipListForm = (InvtEquipListForm) form;
        
        super.updateAudit(invtEquipListForm.getInvtCommonDTO().getAuditKey()==""?invtEquipListForm.getInvtCommonDTO().getAuditKey():invtEquipListForm.getInvtCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtEquipListForm.getStrutsAction())
        {
            case InvtListAction.BASE_SET_HEADER:
                super.setHeader(request, response, invtEquipListForm.getListId(), invtEquipListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtEquipListAction.LIST_FIND:
                findList(request, response, invtEquipListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtEquipListAction.LIST_DELETE:
            	deleteList(request,invtEquipListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtEquipListAction.NEWEQ_LIST_INPUT:
            	insertNewEqList(request,response,invtEquipListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtEquipListAction.EQ_LIST_INPUT:
            	insertEqList(request,response,invtEquipListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtEquipListAction.BASE_GRID_EXPORT:
            	findList(request,response, invtEquipListForm, true);
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
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param invtEquipListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtEquipListForm invtEquipListForm, boolean excelExport) throws Exception
    {
        InvtEquipListService invtEquipListService = (InvtEquipListService) getBean("invtEquipListService");
        InvtCommonDTO invtCommonDTO = invtEquipListForm.getInvtCommonDTO();

        //Paging
        invtCommonDTO.setIsLoadMaxCount("Y".equals(invtEquipListForm.getIsLoadMaxCount())?true:false);
        invtCommonDTO.setFirstRow(invtEquipListForm.getFirstRow());
        invtCommonDTO.setOrderBy(invtEquipListForm.getOrderBy());
        invtCommonDTO.setDirection(invtEquipListForm.getDirection());
        
        User user = getUser(request);
        
        List resultList = invtEquipListService.findList(invtCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtEquipListService.findTotalCount(invtCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtEquipListForm.getListId(),invtEquipListForm.getCurrentPageId(), invtEquipListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param invtEquipListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, InvtEquipListForm invtEquipListForm) throws Exception
    {
    	InvtEquipListService invtEquipListService = (InvtEquipListService) getBean("invtEquipListService");
        
    	invtEquipListService.deleteList(invtEquipListForm.getDeleteRows(), getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    private void insertNewEqList(HttpServletRequest request, HttpServletResponse response, InvtEquipListForm invtEquipListForm) throws Exception
    {
    	InvtEquipListService invtEquipListService = (InvtEquipListService) getBean("invtEquipListService");
    	InvtCommonDTO invtCommonDTO = invtEquipListForm.getInvtCommonDTO();
    	User user = getUser(request);

    	invtEquipListService.insertNewEqList(invtCommonDTO, user);
    	
    	setAjaxStatus(request);
    }
    
    private void insertEqList(HttpServletRequest request, HttpServletResponse response, InvtEquipListForm invtEquipListForm) throws Exception
    {
    	InvtEquipListService invtEquipListService = (InvtEquipListService) getBean("invtEquipListService");
    	InvtCommonDTO invtCommonDTO = invtEquipListForm.getInvtCommonDTO();
    	User user = getUser(request);
    	
    	invtEquipListService.insertEqList(invtCommonDTO, user);
    	
    	setAjaxStatus(request);
    }
}
