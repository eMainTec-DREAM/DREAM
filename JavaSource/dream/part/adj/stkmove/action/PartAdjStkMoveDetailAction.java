package dream.part.adj.stkmove.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveDetailDTO;
import dream.part.adj.stkmove.form.PartAdjStkMoveDetailForm;
import dream.part.adj.stkmove.service.PartAdjStkMoveDetailService;

/**
 * ����̵� - �� action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partAdjStkMoveDetail" name="partAdjStkMoveDetailForm"
 *                input="/dream/part/adj/stkmove/partAdjStkMoveDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partAdjStkMoveDetail" path="/dream/part/adj/stkmove/partAdjStkMoveDetail.jsp"
 *                        redirect="false"
 */
public class PartAdjStkMoveDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int DETAIL_UPDATE 		= 1002;
    /** �Է� */
    public static final int DETAIL_INPUT 		= 1003;
    /** ����̵� Ȯ�� */
    public static final int DETAIL_CONFIRM_MOVE	= 1004;
    /** ����̵� ��� */
    public static final int DETAIL_CANCEL_MOVE	= 1005;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartAdjStkMoveDetailForm partAdjStkMoveDetailForm = (PartAdjStkMoveDetailForm) form;
        
        switch (partAdjStkMoveDetailForm.getStrutsAction())
        {
            case PartAdjStkMoveDetailAction.DETAIL_INIT:
                this.findDetail(request, partAdjStkMoveDetailForm);
                returnActionForward = mapping.findForward("partAdjStkMoveDetail");
                break;
            case PartAdjStkMoveDetailAction.DETAIL_UPDATE:
            	updateDetail(partAdjStkMoveDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkMoveDetailAction.DETAIL_INPUT:
            	insertDetail(partAdjStkMoveDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkMoveDetailAction.DETAIL_CONFIRM_MOVE:
                moveComp(partAdjStkMoveDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkMoveDetailAction.DETAIL_CANCEL_MOVE:
                moveCancel(partAdjStkMoveDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("partAdjStkMoveDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkMoveDetailForm
     */
    private void findDetail(HttpServletRequest request, PartAdjStkMoveDetailForm partAdjStkMoveDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	PartAdjStkMoveDetailService partAdjStkMoveDetailService = (PartAdjStkMoveDetailService)getBean("partAdjStkMoveDetailService");
    	PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO = partAdjStkMoveDetailForm.getPartAdjStkMoveCommonDTO();
    	partAdjStkMoveCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO = partAdjStkMoveDetailService.findDetail(partAdjStkMoveCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        partAdjStkMoveDetailForm.setPartAdjStkMoveDetailDTO(partAdjStkMoveDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailForm
     * @param request
     */
    private void insertDetail(PartAdjStkMoveDetailForm partAdjStkMoveDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkMoveDetailService partAdjStkMoveDetailService = (PartAdjStkMoveDetailService) getBean("partAdjStkMoveDetailService");
        
        PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO = partAdjStkMoveDetailForm.getPartAdjStkMoveDetailDTO();
        partAdjStkMoveDetailDTO.setCompNo(getUser(request).getCompNo());
        
        partAdjStkMoveDetailService.insertDetail(partAdjStkMoveDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailForm
     * @param request
     */
    private void updateDetail(PartAdjStkMoveDetailForm partAdjStkMoveDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkMoveDetailService partAdjStkMoveDetailService = (PartAdjStkMoveDetailService) getBean("partAdjStkMoveDetailService");
        
        PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO = partAdjStkMoveDetailForm.getPartAdjStkMoveDetailDTO();
        partAdjStkMoveDetailDTO.setCompNo(getUser(request).getCompNo());
        
        partAdjStkMoveDetailService.updateDetail(partAdjStkMoveDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailForm
     * @param request
     */
    private void moveComp(PartAdjStkMoveDetailForm partAdjStkMoveDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkMoveDetailService partAdjStkMoveDetailService = (PartAdjStkMoveDetailService) getBean("partAdjStkMoveDetailService");
        
        PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO = partAdjStkMoveDetailForm.getPartAdjStkMoveDetailDTO();
        
        String[] result = partAdjStkMoveDetailService.moveComp(partAdjStkMoveDetailDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }
    /**
     * detail cancel
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailForm
     * @param request
     */
    private void moveCancel(PartAdjStkMoveDetailForm partAdjStkMoveDetailForm, HttpServletRequest request) throws Exception
    {
        PartAdjStkMoveDetailService partAdjStkMoveDetailService = (PartAdjStkMoveDetailService) getBean("partAdjStkMoveDetailService");
        
        PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO = partAdjStkMoveDetailForm.getPartAdjStkMoveDetailDTO();
        
        String[] result = partAdjStkMoveDetailService.moveCancel(partAdjStkMoveDetailDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }

}