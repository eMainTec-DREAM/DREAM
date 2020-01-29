package dream.mgr.usage.cal.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDayDTO;
import dream.mgr.usage.cal.form.MgrUsageCalSetDayForm;
import dream.mgr.usage.cal.service.MgrUsageCalSetDayService;

/**
 * ���ΰ�����ȹ - ��� action
 * @author  youngjoo38
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/mgrUsageCalSetDayList" name="mgrUsageCalSetDayForm"
 *                input="/dream/mgr/usage/cal/mgrUsageCalSetDayList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsageCalSetDayDetail" name="mgrUsageCalSetDayForm"
 *                input="/dream/mgr/usage/cal/mgrUsageCalSetDayDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsageCalSetDayList" path="/dream/mgr/usage/cal/mgrUsageCalSetDayList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="mgrUsageCalSetDayDetail" path="/dream/mgr/usage/cal/mgrUsageCalSetDayDetail.jsp"
 *                        redirect="false"
 */
public class MgrUsageCalSetDayAction extends AuthAction
{
    /** ��� ��ȸ */
    public static final int LINE_RUN_PLAN_LIST_FIND 		= 8001;
    /** ��� ���� */
    public static final int LINE_RUN_PLAN_LIST_DELETE 		= 7002;
    /** �� ��ȸ */
    public static final int LINE_RUN_PLAN_DETAIL_INIT 		= 8002;
    /** �� ���� */
    public static final int LINE_RUN_PLAN_DETAIL_INPUT 	    = 5002;
    /** �� ���� */
    public static final int LINE_RUN_PLAN_DETAIL_UPDATE 	= 6003;
    /** �� �ߺ� üũ */
    public static final int LINE_RUN_PLAN_DETAIL_CHECK 	    = 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrUsageCalSetDayForm mgrUsageCalSetDayForm = (MgrUsageCalSetDayForm) form;
        
        switch (mgrUsageCalSetDayForm.getStrutsAction())
        {
            case MgrUsageCalSetDayAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUsageCalSetDayForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsageCalSetDayAction.LINE_RUN_PLAN_LIST_FIND:
                findList(request, response, mgrUsageCalSetDayForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrUsageCalSetDayAction.LINE_RUN_PLAN_LIST_DELETE:
            	deleteList(request, mgrUsageCalSetDayForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_INIT:
                findDetail(request, mgrUsageCalSetDayForm);
                returnActionForward = mapping.findForward("mgrUsageCalSetDayDetail");
                break;
            case MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_INPUT:
            	insertDetail(mgrUsageCalSetDayForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_UPDATE:
            	updateDetail(mgrUsageCalSetDayForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_CHECK:
                validLineRunPlan(mgrUsageCalSetDayForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetDayAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrUsageCalSetDayForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUsageCalSetDayForm mgrUsageCalSetDayForm) throws IOException
    {
        super.setHeader(request, response, mgrUsageCalSetDayForm.getListId(), mgrUsageCalSetDayForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrUsageCalSetDayForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrUsageCalSetDayForm mgrUsageCalSetDayForm, boolean excelExport) throws IOException
    {
    	MgrUsageCalSetDayService mgrUsageCalSetDayService = (MgrUsageCalSetDayService) getBean("mgrUsageCalSetDayService");        

    	MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = mgrUsageCalSetDayForm.getMgrUsageCalSetDayDTO();

    	//comp_no �� �����Ѵ�.
    	mgrUsageCalSetDayDTO.setCompNo((getUser(request).getCompNo()));
    	
    	//Paging
    	mgrUsageCalSetDayDTO.setIsLoadMaxCount("Y".equals(mgrUsageCalSetDayForm.getIsLoadMaxCount())?true:false);
    	mgrUsageCalSetDayDTO.setFirstRow(mgrUsageCalSetDayForm.getFirstRow());
    	mgrUsageCalSetDayDTO.setOrderBy(mgrUsageCalSetDayForm.getOrderBy());
    	mgrUsageCalSetDayDTO.setDirection(mgrUsageCalSetDayForm.getDirection());
        
    	User user = getUser(request);
    	
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = mgrUsageCalSetDayService.findList(mgrUsageCalSetDayDTO, user);
      
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(mgrUsageCalSetDayForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsageCalSetDayService.findTotalCount(mgrUsageCalSetDayDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrUsageCalSetDayForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MgrUsageCalSetDayForm mgrUsageCalSetDayForm) throws Exception
    {
    	MgrUsageCalSetDayService mgrUsageCalSetDayService = (MgrUsageCalSetDayService) getBean("mgrUsageCalSetDayService");        

        String[] deleteRows = mgrUsageCalSetDayForm.getDeleteRows();    // sheet ����
        
        mgrUsageCalSetDayService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    

    /**
     * ���ΰ�����ȹ �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrUsageCalSetDayForm
     */
    private void findDetail(HttpServletRequest request, MgrUsageCalSetDayForm mgrUsageCalSetDayForm)throws Exception 
    {   
        // Service ��ü ����
    	MgrUsageCalSetDayService mgrUsageCalSetDayService = (MgrUsageCalSetDayService)getBean("mgrUsageCalSetDayService");

    	MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = mgrUsageCalSetDayForm.getMgrUsageCalSetDayDTO();

    	// ����
    	User user = getUser(request);
    	
        // ��ȸ�� �� �κ�
        mgrUsageCalSetDayDTO = mgrUsageCalSetDayService.findDetail(mgrUsageCalSetDayDTO, user);
        
        // ��ȸ�� ��  �����Ѵ�.
        mgrUsageCalSetDayForm.setMgrUsageCalSetDayDTO(mgrUsageCalSetDayDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayForm
     * @param request
     */
    private void insertDetail(MgrUsageCalSetDayForm mgrUsageCalSetDayForm, HttpServletRequest request) throws Exception
    {
        MgrUsageCalSetDayService mgrUsageCalSetDayService = (MgrUsageCalSetDayService) getBean("mgrUsageCalSetDayService");
        
        MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = mgrUsageCalSetDayForm.getMgrUsageCalSetDayDTO();

    	// ����
    	User user = getUser(request);
    	
        mgrUsageCalSetDayDTO.setCompNo((getUser(request).getCompNo()));
        
        mgrUsageCalSetDayService.insertDetail(mgrUsageCalSetDayDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayForm
     * @param request
     */
    private void updateDetail(MgrUsageCalSetDayForm mgrUsageCalSetDayForm, HttpServletRequest request) throws Exception
    {
    	MgrUsageCalSetDayService mgrUsageCalSetDayService = (MgrUsageCalSetDayService) getBean("mgrUsageCalSetDayService");
        
        MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = mgrUsageCalSetDayForm.getMgrUsageCalSetDayDTO();
        
        mgrUsageCalSetDayDTO.setCompNo((getUser(request).getCompNo()));

    	// ����
    	User user = getUser(request);
    	
        mgrUsageCalSetDayService.updateDetail(mgrUsageCalSetDayDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUsageCalSetDayForm
     * @param request
     */
    private void validLineRunPlan(MgrUsageCalSetDayForm mgrUsageCalSetDayForm, HttpServletRequest request) throws Exception
    {
    	MgrUsageCalSetDayService mgrUsageCalSetDayService = (MgrUsageCalSetDayService) getBean("mgrUsageCalSetDayService");
        
        MgrUsageCalSetDayDTO mgrUsageCalSetDayDTO = mgrUsageCalSetDayForm.getMgrUsageCalSetDayDTO();
        
        mgrUsageCalSetDayDTO.setCompNo((getUser(request).getCompNo()));

    	// ����
    	User user = getUser(request);
    	
        String isValid = mgrUsageCalSetDayService.validLineRunPlan(mgrUsageCalSetDayDTO, user);
        
        setAjaxDesc(request, isValid);
    }
}
