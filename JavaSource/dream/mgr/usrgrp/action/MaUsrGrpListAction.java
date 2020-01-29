package dream.mgr.usrgrp.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.form.MaUsrGrpListForm;
import dream.mgr.usrgrp.service.MaUsrGrpListService;

/**
 * 권한그룹 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maUsrGrpList" name="maUsrGrpListForm"
 *                input="/dream/mgr/usrgrp/maUsrGrpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUsrGrpList" path="/dream/mgr/usrgrp/maUsrGrpList.jsp"
 *                        redirect="false"
 */
public class MaUsrGrpListAction extends AuthAction
{
    /** 조회 */
    public static final int USRGRP_LIST_FIND    = 8001;
    /** 삭제 */
    public static final int USRGRP_LIST_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaUsrGrpListForm maUsrGrpListForm = (MaUsrGrpListForm) form;
        
        super.updateAudit(maUsrGrpListForm.getMaUsrGrpCommonDTO().getAuditKey()==""?maUsrGrpListForm.getMaUsrGrpCommonDTO().getAuditKey():maUsrGrpListForm.getMaUsrGrpCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUsrGrpListForm.getStrutsAction())
        {
            case MaUsrGrpListAction.BASE_SET_HEADER:
                setHeader(request, response, maUsrGrpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUsrGrpListAction.USRGRP_LIST_FIND:
                findList(request, response, maUsrGrpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;        
            case MaUsrGrpListAction.USRGRP_LIST_DELETE:
                deleteList(request, maUsrGrpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;               
            case MaUsrGrpListAction.BASE_GRID_EXPORT:
            	findList(request, response, maUsrGrpListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUsrGrpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaUsrGrpListForm maUsrGrpListForm) throws IOException
    {
        super.setHeader(request, response, maUsrGrpListForm.getListId(), maUsrGrpListForm.getCurrentPageId()); 
    }
    
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maUsrGrpListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaUsrGrpListForm maUsrGrpListForm, boolean excelExport) throws Exception
    {
    	MaUsrGrpListService maUsrGrpListService = (MaUsrGrpListService) getBean("maUsrGrpListService");        

    	MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpListForm.getMaUsrGrpCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maUsrGrpCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	
    	//Paging
    	maUsrGrpCommonDTO.setIsLoadMaxCount("Y".equals(maUsrGrpListForm.getIsLoadMaxCount())?true:false);
    	maUsrGrpCommonDTO.setFirstRow(maUsrGrpListForm.getFirstRow());
    	maUsrGrpCommonDTO.setOrderBy(maUsrGrpListForm.getOrderBy());
    	maUsrGrpCommonDTO.setDirection(maUsrGrpListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maUsrGrpListService.findUseGrpList(maUsrGrpCommonDTO, getUser(request));
        
    	//Paging
    	String totalCount = "";
    	        
    	if(Integer.parseInt(maUsrGrpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maUsrGrpListService.findTotalCount(maUsrGrpCommonDTO,getUser(request));
    	        
    	if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maUsrGrpListForm); //makeGridExport(resultList, request, response, maUsrGrpListForm.getListId(),maUsrGrpListForm.getCurrentPageId(), maUsrGrpListForm.getFileName());
        // 조회한 List 를 form에 셋팅한다.
    	else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaUsrGrpListForm maUsrGrpListForm) throws Exception
    {
        MaUsrGrpListService maUsrGrpListService = (MaUsrGrpListService) getBean("maUsrGrpListService");        

        String[] deleteRows = maUsrGrpListForm.getDeleteRows();    // sheet 내역
        
        maUsrGrpListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
