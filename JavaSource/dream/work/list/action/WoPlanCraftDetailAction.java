package dream.work.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.form.WoPlanCraftDetailForm;
import dream.work.list.service.WoPlanCraftDetailService;

/**
 * �۾���ȹ��� - �۾��� ��
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/woPlanCraftDetail" name="woPlanCraftDetailForm"
 *                input="/dream/work/list/woPlanCraftDetail.jsp" scope="request"
 *                validate="false"
 */
public class WoPlanCraftDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_RESULT_CRAFT_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int WO_RESULT_CRAFT_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int WO_RESULT_CRAFT_DETAIL_INPUT 	= 5003;
    /** ���� WO �ߺ� EMP �˻� */
    public static final int WO_RESULT_CRAFT_DETAIL_CHECK	= 8004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanCraftDetailForm woPlanCraftDetailForm = (WoPlanCraftDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") woPlanCraftDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (woPlanCraftDetailForm.getStrutsAction())
        {
            case WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, woPlanCraftDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_UPDATE:
            	updateDetail(woPlanCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INPUT:
            	insertDetail(woPlanCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_CHECK:
            	validEmp(woPlanCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾���ȹ��� - �۾��� �� ��ȸ
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanCraftDetailForm
     */
    private void findDetail(HttpServletRequest request, WoPlanCraftDetailForm woPlanCraftDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WoPlanCraftDetailService woPlanCraftDetailService = (WoPlanCraftDetailService)getBean("woPlanCraftDetailService");

    	// �۾����Id ����
        String wkOrId = woPlanCraftDetailForm.getWoPlanCommonDTO().getWkOrId();
        // �Ѱ��� �۾���id ����
        String woCraftId = woPlanCraftDetailForm.getWoPlanCraftListDTO().getWoCraftId();
        
        // ��ȸ�� �� �κ�
        WoPlanCraftDetailDTO woPlanCraftDetailDTO = woPlanCraftDetailService.findDetail(wkOrId, woCraftId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        woPlanCraftDetailForm.setWoPlanCraftDetailDTO(woPlanCraftDetailDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailForm
     * @param request
     */
    private void updateDetail(WoPlanCraftDetailForm woPlanCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanCraftDetailService woPlanCraftDetailService = (WoPlanCraftDetailService) getBean("woPlanCraftDetailService");
        
        WoPlanCraftDetailDTO woPlanCraftDetailDTO = woPlanCraftDetailForm.getWoPlanCraftDetailDTO();
        WoPlanCommonDTO woPlanCommonDTO = woPlanCraftDetailForm.getWoPlanCommonDTO();
        woPlanCraftDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanCraftDetailService.updateDetail(woPlanCraftDetailDTO,woPlanCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCraftDetailForm
     * @param request
     */
    private void insertDetail(WoPlanCraftDetailForm woPlanCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanCraftDetailService woPlanCraftDetailService = (WoPlanCraftDetailService) getBean("woPlanCraftDetailService");
        
        WoPlanCraftDetailDTO woPlanCraftDetailDTO = woPlanCraftDetailForm.getWoPlanCraftDetailDTO();
        
        WoPlanCommonDTO woPlanCommonDTO = woPlanCraftDetailForm.getWoPlanCommonDTO();
        woPlanCraftDetailDTO.setEnterBy(getUser(request).getUserId());
        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanCraftDetailService.insertDetail(woPlanCraftDetailDTO, woPlanCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * ��� �ߺ� �˻�
     */
    private void validEmp(WoPlanCraftDetailForm woPlanCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	WoPlanCraftDetailService woPlanCraftDetailService = (WoPlanCraftDetailService) getBean("woPlanCraftDetailService");
        
        WoPlanCraftDetailDTO woPlanCraftDetailDTO = woPlanCraftDetailForm.getWoPlanCraftDetailDTO();
        
        WoPlanCommonDTO woPlanCommonDTO = woPlanCraftDetailForm.getWoPlanCommonDTO();
        String isValid = woPlanCraftDetailService.validEmp(woPlanCraftDetailDTO,woPlanCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}