package dream.mgr.contract.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.contract.dto.MgrContractItemDTO;
import dream.mgr.contract.form.MgrContractItemForm;
import dream.mgr.contract.service.MgrContractItemService;

/**
 * �ܰ���� ���� ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrContractItemList" name="mgrContractItemForm"
 *                input="/dream/mgr/contract/mgrContractItemList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrContractItemDetail" name="mgrContractItemForm"
 *                input="/dream/mgr/contract/mgrContractItemDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrContractItemList" path="/dream/mgr/contract/mgrContractItemList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="mgrContractItemDetail" path="/dream/mgr/contract/mgrContractItemDetail.jsp"
 *                        redirect="false"
 */
public class MgrContractItemAction extends AuthAction
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
        MgrContractItemForm mgrContractItemForm = (MgrContractItemForm) form;
        
        switch (mgrContractItemForm.getStrutsAction())
        {
        
            case MgrContractItemAction.LIST_FIND:
                findList(request,response, mgrContractItemForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrContractItemAction.BASE_SET_HEADER:
            	super.setHeader(request, response, mgrContractItemForm.getListId(), mgrContractItemForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrContractItemAction.LIST_DELETE:
            	deleteList(request,mgrContractItemForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrContractItemAction.DETAIL_INIT:
                // ������ ��ȸ
                findDetail(request, mgrContractItemForm);
                returnActionForward = mapping.findForward("mgrContractItemDetail");
                break;
            case MgrContractItemAction.DETAIL_UPDATE:
            	updateDetail(mgrContractItemForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrContractItemAction.DETAIL_INPUT:
            	insertDetail(mgrContractItemForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrContractItemAction.BASE_GRID_EXPORT:
            	findList(request,response, mgrContractItemForm, true);
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
     * @param mgrContractItemForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MgrContractItemForm mgrContractItemForm, boolean excelExport) throws Exception
    {
        MgrContractItemService mgrContractItemService = (MgrContractItemService) getBean("mgrContractItemService");
        MgrContractItemDTO mgrContractItemDTO = mgrContractItemForm.getMgrContractItemDTO();
        
    	//Paging
        mgrContractItemDTO.setIsLoadMaxCount("Y".equals(mgrContractItemForm.getIsLoadMaxCount())?true:false);
        mgrContractItemDTO.setFirstRow(mgrContractItemForm.getFirstRow());
        mgrContractItemDTO.setOrderBy(mgrContractItemForm.getOrderBy());
        mgrContractItemDTO.setDirection(mgrContractItemForm.getDirection());
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = mgrContractItemService.findList(mgrContractItemDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrContractItemForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrContractItemService.findTotalCount(mgrContractItemDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrContractItemForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrContractItemForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MgrContractItemForm mgrContractItemForm) throws Exception
    {
    	MgrContractItemService mgrContractItemService = (MgrContractItemService) getBean("mgrContractItemService");
        
    	String[] deleteRows = mgrContractItemForm.getDeleteRows();
        User user = getUser(request);
    
    	mgrContractItemService.deleteList(deleteRows, user);
    	
    	setAjaxStatus(request);
    }
    

    /**
     * �ܰ���� ���� �� ��ȸ
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrContractItemForm
     */
    private void findDetail(HttpServletRequest request, MgrContractItemForm mgrContractItemForm)throws Exception 
    {   
        // Service ��ü ����
    	MgrContractItemService mgrContractItemService = (MgrContractItemService)getBean("mgrContractItemService");
    	
    	MgrContractItemDTO mgrContractItemDTO = mgrContractItemForm.getMgrContractItemDTO();
        
        // ��ȸ�� �� �κ�
        mgrContractItemDTO = mgrContractItemService.findDetail(mgrContractItemDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        mgrContractItemForm.setMgrContractItemDTO(mgrContractItemDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractItemForm
     * @param request
     */
    private void updateDetail(MgrContractItemForm mgrContractItemForm, HttpServletRequest request) throws Exception
    {
    	MgrContractItemService mgrContractItemService = (MgrContractItemService) getBean("mgrContractItemService");
        
        MgrContractItemDTO mgrContractItemDTO = mgrContractItemForm.getMgrContractItemDTO();
        
        mgrContractItemService.updateDetail(mgrContractItemDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrContractItemForm
     * @param request
     */
    private void insertDetail(MgrContractItemForm mgrContractItemForm, HttpServletRequest request) throws Exception
    {
    	MgrContractItemService mgrContractItemService = (MgrContractItemService) getBean("mgrContractItemService");
        
        MgrContractItemDTO mgrContractItemDTO = mgrContractItemForm.getMgrContractItemDTO();
        
        mgrContractItemService.insertDetail(mgrContractItemDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
}