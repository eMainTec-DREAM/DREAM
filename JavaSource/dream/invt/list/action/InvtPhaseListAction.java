package dream.invt.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.form.InvtPhaseListForm;
import dream.invt.list.service.InvtPhaseListService;


/**
 * 목록
 * @author  kim21017
 * @version $Id: InvtPhaseListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/invtPhaseList" name="invtPhaseListForm"
 *                input="/dream/invt/list/invtPhaseList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPhaseList" path="/dream/invt/list/invtPhaseList.jsp"
 *                        redirect="false"
 */
public class InvtPhaseListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int INVT_PHASE_LIST_FIND        = 1001;
    /** 삭제 */
    public static final int INVT_PHASE_LIST_DELETE      = 7002;
    /** Edit */
    public static final int INVT_PHASE_LIST_SAVE        = 6003;
    /** 절차 추가하기 */
    public static final int PHASE_INPUT                 = 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPhaseListForm invtPhaseListForm = (InvtPhaseListForm) form;
        
        super.updateAudit(invtPhaseListForm.getInvtCommonDTO().getAuditKey()==""?invtPhaseListForm.getInvtCommonDTO().getAuditKey():invtPhaseListForm.getInvtCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtPhaseListForm.getStrutsAction())
        {
            case InvtListAction.BASE_SET_HEADER:
                super.setHeader(request, response, invtPhaseListForm.getListId(), invtPhaseListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPhaseListAction.INVT_PHASE_LIST_FIND:
                findList(request, response, invtPhaseListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPhaseListAction.INVT_PHASE_LIST_DELETE:
                deleteList(request,invtPhaseListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case InvtPhaseListAction.INVT_PHASE_LIST_SAVE:
                saveList(request, response, invtPhaseListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPhaseListAction.PHASE_INPUT:
                insertPhase(request, response, invtPhaseListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case InvtPhaseListAction.BASE_GRID_EXPORT:
                findList(request,response, invtPhaseListForm, true);
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
     * @author  kim2107
     * @version $Id: InvtPhaseListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPhaseListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtPhaseListForm invtPhaseListForm, boolean excelExport) throws Exception
    {
        InvtPhaseListService invtPhaseListService = (InvtPhaseListService) getBean("invtPhaseListService", request);
        InvtCommonDTO invtCommonDTO = invtPhaseListForm.getInvtCommonDTO();

        //Paging
        invtCommonDTO.setIsLoadMaxCount("Y".equals(invtPhaseListForm.getIsLoadMaxCount())?true:false);
        invtCommonDTO.setFirstRow(invtPhaseListForm.getFirstRow());
        invtCommonDTO.setOrderBy(invtPhaseListForm.getOrderBy());
        invtCommonDTO.setDirection(invtPhaseListForm.getDirection());
        
        User user = getUser(request);
        
        List resultList = invtPhaseListService.findList(invtCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtPhaseListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtPhaseListService.findTotalCount(invtCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtPhaseListForm.getListId(),invtPhaseListForm.getCurrentPageId(), invtPhaseListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: InvtPhaseListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPhaseListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, InvtPhaseListForm invtPhaseListForm) throws Exception
    {
        InvtPhaseListService invtPhaseListService = (InvtPhaseListService) getBean("invtPhaseListService", request);
        
        invtPhaseListService.deleteList(invtPhaseListForm.getDeleteRows(), getUser(request));
        
        setAjaxStatus(request);
    }

    private void saveList(HttpServletRequest request, HttpServletResponse response, InvtPhaseListForm invtPhaseListForm) throws Exception 
    {
        InvtPhaseListService invtPhaseListService = (InvtPhaseListService) getBean("invtPhaseListService", request);
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        invtPhaseListService.savePointList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    
    private void insertPhase(HttpServletRequest request, HttpServletResponse response, InvtPhaseListForm invtPhaseListForm) throws Exception 
    {
        InvtPhaseListService invtPhaseListService = (InvtPhaseListService) getBean("invtPhaseListService", request);
        InvtCommonDTO invtCommonDTO = invtPhaseListForm.getInvtCommonDTO();
        User user = getUser(request);
        
        invtPhaseListService.insertPhase(invtCommonDTO, user);
        
        setAjaxStatus(request);
    }
}