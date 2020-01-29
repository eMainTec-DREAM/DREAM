package dream.rcm.system.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.form.RcmSysListForm;
import dream.rcm.system.service.RcmSysListService;

/**
 * 사원 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/rcmSysList" name="rcmSysListForm"
 *                input="/dream/rcm/system/rcmSysList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysList" path="/dream/rcm/system/rcmSysList.jsp"
 *                        redirect="false"
 */
public class RcmSysListAction extends AuthAction
{
    /** 조회 */
    public static final int RCM_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int RCM_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysListForm rcmSysListForm = (RcmSysListForm) form;
        
        switch (rcmSysListForm.getStrutsAction())
        {
            case RcmSysListAction.BASE_SET_HEADER:
                setHeader(request, response, rcmSysListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmSysListAction.RCM_LIST_FIND:
                findList(request, response, rcmSysListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;      
            case RcmSysListAction.RCM_LIST_DELETE:
                deleteList(request, rcmSysListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;                
            case RcmSysListAction.BASE_GRID_EXPORT:
            	findList(request, response, rcmSysListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmSysList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmSysListForm rcmSysListForm) throws IOException
    {
        super.setHeader(request, response, rcmSysListForm.getListId(), rcmSysListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmSysListForm rcmSysListForm, boolean excelExport)  throws IOException
    {
    	RcmSysListService rcmSysListService = (RcmSysListService) getBean("rcmSysListService");        

    	RcmSysCommonDTO rcmSysCommonDTO = rcmSysListForm.getRcmSysCommonDTO();
    	//Paging
    	rcmSysCommonDTO.setIsLoadMaxCount("Y".equals(rcmSysListForm.getIsLoadMaxCount())?true:false);
    	rcmSysCommonDTO.setFirstRow(rcmSysListForm.getFirstRow());
    	rcmSysCommonDTO.setOrderBy(rcmSysListForm.getOrderBy());
    	rcmSysCommonDTO.setDirection(rcmSysListForm.getDirection());
    	// 로긴 comp_no 를 셋팅한다.
    	rcmSysCommonDTO.setCompNo((getUser(request).getCompNo()));

        //리스트를 조회한다.
        List resultList = rcmSysListService.findList(rcmSysCommonDTO, getUser(request));
        String totalCount = "";
        if(Integer.parseInt(rcmSysListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = rcmSysListService.findTotalCount(rcmSysCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,rcmSysListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    
    }
    
    /**
     * delete
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, RcmSysListForm rcmSysListForm) throws Exception
    {
        RcmSysListService rcmSysListService = (RcmSysListService) getBean("rcmSysListService");        

        String[] deleteRows = rcmSysListForm.getDeleteRows();    // sheet 내역
        
        rcmSysListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
