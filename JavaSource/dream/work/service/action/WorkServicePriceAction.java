package dream.work.service.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.service.dto.WorkServicePriceDTO;
import dream.work.service.form.WorkServicePriceForm;
import dream.work.service.service.WorkServicePriceService;

/**
 * ���� ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workServicePriceList" name="workServicePriceForm"
 *                input="/dream/work/service/workServicePriceList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workServicePriceDetail" name="workServicePriceForm"
 *                input="/dream/work/service/workServicePriceDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workServicePriceList" path="/dream/work/service/workServicePriceList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="workServicePriceDetail" path="/dream/work/service/workServicePriceDetail.jsp"
 *                        redirect="false"
 */
public class WorkServicePriceAction extends AuthAction
{
    /** ��� ��ȸ */
    public static final int LIST_FIND 			= 8001;
    /** ��� ���� */
    public static final int LIST_DELETE 		= 7002;
    /** �� ��ȸ */
    public static final int DETAIL_INIT 		= 8002;
    /** �� ���� */
    public static final int DETAIL_INPUT 		= 5001;
    /** �� ���� */
    public static final int DETAIL_UPDATE 		= 6001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkServicePriceForm workServicePriceForm = (WorkServicePriceForm) form;
        
        switch (workServicePriceForm.getStrutsAction())
        {
        
            case WorkServicePriceAction.LIST_FIND:
                findList(request,response, workServicePriceForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkServicePriceAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workServicePriceForm.getListId(), workServicePriceForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkServicePriceAction.LIST_DELETE:
            	deleteList(request,workServicePriceForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkServicePriceAction.DETAIL_INIT:
                // ������ ��ȸ
                findDetail(request, workServicePriceForm);
                returnActionForward = mapping.findForward("workServicePriceDetail");
                break;
            case WorkServicePriceAction.DETAIL_UPDATE:
            	updateDetail(workServicePriceForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkServicePriceAction.DETAIL_INPUT:
            	insertDetail(workServicePriceForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkServicePriceAction.BASE_GRID_EXPORT:
            	findList(request,response, workServicePriceForm, true);
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
     * @param workServicePriceForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkServicePriceForm workServicePriceForm, boolean excelExport) throws Exception
    {
        WorkServicePriceService workServicePriceService = (WorkServicePriceService) getBean("workServicePriceService");
        WorkServicePriceDTO workServicePriceDTO = workServicePriceForm.getWorkServicePriceDTO();
        
    	//Paging
        workServicePriceDTO.setIsLoadMaxCount("Y".equals(workServicePriceForm.getIsLoadMaxCount())?true:false);
        workServicePriceDTO.setFirstRow(workServicePriceForm.getFirstRow());
        workServicePriceDTO.setOrderBy(workServicePriceForm.getOrderBy());
        workServicePriceDTO.setDirection(workServicePriceForm.getDirection());
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = workServicePriceService.findList(workServicePriceDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workServicePriceForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workServicePriceService.findTotalCount(workServicePriceDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workServicePriceForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workServicePriceForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, WorkServicePriceForm workServicePriceForm) throws Exception
    {
    	WorkServicePriceService workServicePriceService = (WorkServicePriceService) getBean("workServicePriceService");
        
    	String[] deleteRows = workServicePriceForm.getDeleteRows();
        User user = getUser(request);
    
    	workServicePriceService.deleteList(deleteRows, user);
    	
    	setAjaxStatus(request);
    }
    

    /**
     * �ܰ���� ���� �� ��ȸ
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workServicePriceForm
     */
    private void findDetail(HttpServletRequest request, WorkServicePriceForm workServicePriceForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkServicePriceService workServicePriceService = (WorkServicePriceService)getBean("workServicePriceService");
    	
    	WorkServicePriceDTO workServicePriceDTO = workServicePriceForm.getWorkServicePriceDTO();
        
        // ��ȸ�� �� �κ�
        workServicePriceDTO = workServicePriceService.findDetail(workServicePriceDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        workServicePriceForm.setWorkServicePriceDTO(workServicePriceDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workServicePriceForm
     * @param request
     */
    private void updateDetail(WorkServicePriceForm workServicePriceForm, HttpServletRequest request) throws Exception
    {
    	WorkServicePriceService workServicePriceService = (WorkServicePriceService) getBean("workServicePriceService");
        
        WorkServicePriceDTO workServicePriceDTO = workServicePriceForm.getWorkServicePriceDTO();
        
        workServicePriceService.updateDetail(workServicePriceDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workServicePriceForm
     * @param request
     */
    private void insertDetail(WorkServicePriceForm workServicePriceForm, HttpServletRequest request) throws Exception
    {
    	WorkServicePriceService workServicePriceService = (WorkServicePriceService) getBean("workServicePriceService");
        
        WorkServicePriceDTO workServicePriceDTO = workServicePriceForm.getWorkServicePriceDTO();
        
        workServicePriceService.insertDetail(workServicePriceDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
}