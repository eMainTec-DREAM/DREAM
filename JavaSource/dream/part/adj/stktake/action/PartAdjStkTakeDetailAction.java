package dream.part.adj.stktake.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeDetailDTO;
import dream.part.adj.stktake.form.PartAdjStkTakeDetailForm;
import dream.part.adj.stktake.service.PartAdjStkTakeDetailService;

/**
 * ��ǰ�ǻ� - �� action
 * 
 * @author kim2107
 * @version $Id: PartAdjStkTakeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/partAdjStkTakeDetail" name="partAdjStkTakeDetailForm"
 *                input="/dream/part/adj/stktake/partAdjStkTakeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partAdjStkTakeDetail" path="/dream/part/adj/stktake/partAdjStkTakeDetail.jsp"
 *                        redirect="false"
 */
public class PartAdjStkTakeDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int BUY_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int BUY_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int BUY_DETAIL_INPUT 		= 5003;
    /** ��û�Ϸ� */
    public static final int BUY_DETAIL_CONFIRM		= 6004;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartAdjStkTakeDetailForm partAdjStkTakeDetailForm = (PartAdjStkTakeDetailForm) form;
        
        super.updateAudit(partAdjStkTakeDetailForm.getPartAdjStkTakeDetailDTO().getAuditKey()==""?partAdjStkTakeDetailForm.getPartAdjStkTakeCommonDTO().getAuditKey():partAdjStkTakeDetailForm.getPartAdjStkTakeDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        
        switch (partAdjStkTakeDetailForm.getStrutsAction())
        {
            case PartAdjStkTakeDetailAction.BUY_DETAIL_INIT:
                this.findDetail(request, partAdjStkTakeDetailForm);
                returnActionForward = mapping.findForward("partAdjStkTakeDetail");
                break;
            case PartAdjStkTakeDetailAction.BUY_DETAIL_UPDATE:
            	updateDetail(partAdjStkTakeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkTakeDetailAction.BUY_DETAIL_INPUT:
            	insertDetail(partAdjStkTakeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartAdjStkTakeDetailAction.BUY_DETAIL_CONFIRM:
            	confirmDetail(partAdjStkTakeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("partAdjStkTakeDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���Ž�û ��ȸ
     * @author kim2107
     * @version $Id: PartAdjStkTakeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkTakeDetailForm
     */
    private void findDetail(HttpServletRequest request, PartAdjStkTakeDetailForm partAdjStkTakeDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	PartAdjStkTakeDetailService partAdjStkTakeDetailService = (PartAdjStkTakeDetailService)getBean("partAdjStkTakeDetailService", request);
    	PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = partAdjStkTakeDetailForm.getPartAdjStkTakeCommonDTO();
    	partAdjStkTakeCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO = partAdjStkTakeDetailService.findDetail(partAdjStkTakeCommonDTO,getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        partAdjStkTakeDetailForm.setPartAdjStkTakeDetailDTO(partAdjStkTakeDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: PartAdjStkTakeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailForm
     * @param request
     */
    private void insertDetail(PartAdjStkTakeDetailForm partAdjStkTakeDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkTakeDetailService partAdjStkTakeDetailService = (PartAdjStkTakeDetailService) getBean("partAdjStkTakeDetailService", request);
        
        PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO = partAdjStkTakeDetailForm.getPartAdjStkTakeDetailDTO();
        partAdjStkTakeDetailDTO.setCompNo(getUser(request).getCompNo());
        
        partAdjStkTakeDetailService.insertDetail(partAdjStkTakeDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: PartAdjStkTakeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailForm
     * @param request
     */
    private void updateDetail(PartAdjStkTakeDetailForm partAdjStkTakeDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkTakeDetailService partAdjStkTakeDetailService = (PartAdjStkTakeDetailService) getBean("partAdjStkTakeDetailService", request);
        
        PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO = partAdjStkTakeDetailForm.getPartAdjStkTakeDetailDTO();
        partAdjStkTakeDetailDTO.setCompNo(getUser(request).getCompNo());
        
        partAdjStkTakeDetailService.updateDetail(partAdjStkTakeDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  kim21017
     * @version $Id: PartAdjStkTakeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailForm
     * @param request
     */
    private void confirmDetail(PartAdjStkTakeDetailForm partAdjStkTakeDetailForm, HttpServletRequest request) throws Exception
    {
    	PartAdjStkTakeDetailService partAdjStkTakeDetailService = (PartAdjStkTakeDetailService) getBean("partAdjStkTakeDetailService", request);
        
        PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO = partAdjStkTakeDetailForm.getPartAdjStkTakeDetailDTO();
        
        partAdjStkTakeDetailService.confirmDetail(partAdjStkTakeDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }

}