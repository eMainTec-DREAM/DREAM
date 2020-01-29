package dream.consult.comp.user.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.form.ConsultCompUsrGrpListForm;
import dream.consult.comp.user.service.ConsultCompUsrGrpListService;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/consultCompUsrGrpList" name="consultCompUsrGrpListForm"
 *                input="/dream/consult/comp/user/consultCompUsrGrpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompUsrGrpList" path="/dream/consult/comp/user/consultCompUsrGrpList.jsp"
 *                        redirect="false"
 */
public class ConsultCompUsrGrpListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int USRGRP_LIST_FIND    = 8001;
    /** 삭제 */
    public static final int USRGRP_LIST_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompUsrGrpListForm consultCompUsrGrpListForm = (ConsultCompUsrGrpListForm) form;
        
//        super.updateAudit(consultCompUsrGrpListForm.getConsultCompUsrGrpCommonDTO().getAuditKey()==""?consultCompUsrGrpListForm.getConsultCompUsrGrpCommonDTO().getAuditKey():consultCompUsrGrpListForm.getConsultCompUsrGrpCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (consultCompUsrGrpListForm.getStrutsAction())
        {
            case ConsultCompUsrGrpListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompUsrGrpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompUsrGrpListAction.USRGRP_LIST_FIND:
                findList(request, response, consultCompUsrGrpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;        
            case ConsultCompUsrGrpListAction.USRGRP_LIST_DELETE:
                deleteList(request, consultCompUsrGrpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;               
            case ConsultCompUsrGrpListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultCompUsrGrpListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompUsrGrpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompUsrGrpListForm consultCompUsrGrpListForm) throws IOException
    {
        super.setHeader(request, response, consultCompUsrGrpListForm.getListId(), consultCompUsrGrpListForm.getCurrentPageId()); 
    }
    
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompUsrGrpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompUsrGrpListForm consultCompUsrGrpListForm, boolean excelExport) throws Exception
    {
    	ConsultCompUsrGrpListService consultCompUsrGrpListService = (ConsultCompUsrGrpListService) getBean("consultCompUsrGrpListService");        

    	ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = consultCompUsrGrpListForm.getConsultCompUsrGrpCommonDTO();
    	
    	//Paging
    	consultCompUsrGrpCommonDTO.setIsLoadMaxCount("Y".equals(consultCompUsrGrpListForm.getIsLoadMaxCount())?true:false);
    	consultCompUsrGrpCommonDTO.setFirstRow(consultCompUsrGrpListForm.getFirstRow());
    	consultCompUsrGrpCommonDTO.setOrderBy(consultCompUsrGrpListForm.getOrderBy());
    	consultCompUsrGrpCommonDTO.setDirection(consultCompUsrGrpListForm.getDirection());

        //리스트를 조회한다.
        List resultList = consultCompUsrGrpListService.findUseGrpList(consultCompUsrGrpCommonDTO, getUser(request));
        
    	//Paging
    	String totalCount = "";
    	        
    	if(Integer.parseInt(consultCompUsrGrpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompUsrGrpListService.findTotalCount(consultCompUsrGrpCommonDTO,getUser(request));
    	        
//    	if(excelExport) super.makeGridExport(resultList, request, response, consultCompUsrGrpListForm.getListId(),consultCompUsrGrpListForm.getCurrentPageId(), consultCompUsrGrpListForm.getFileName());
//        // 조회한 List 를 form에 셋팅한다.
//    	else super.makeJsonResult(resultList, request, response, totalCount);
    	
    	if(excelExport) CommonUtil.makeGridExport(resultList, request, response, consultCompUsrGrpListForm);
    	else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, ConsultCompUsrGrpListForm consultCompUsrGrpListForm) throws Exception
    {
        ConsultCompUsrGrpListService consultCompUsrGrpListService = (ConsultCompUsrGrpListService) getBean("consultCompUsrGrpListService");        

        String[] deleteRows = consultCompUsrGrpListForm.getDeleteRows();    // sheet 내역
        
        consultCompUsrGrpListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
