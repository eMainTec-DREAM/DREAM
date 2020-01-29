package dream.part.iss.wo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.iss.wo.dto.PartIssWoItemDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;
import dream.part.iss.wo.form.PartIssWoItemDetailForm;
import dream.part.iss.wo.service.PartIssWoItemDetailService;

/**
 * �������Ȯ�� - �� action
 * 
 * @author hyosung
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partIssWoItemDetail" name="partIssWoItemDetailForm"
 *                input="/dream/part/iss/wo/partIssWoItemDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partIssWoItemDetail" path="/dream/part/iss/wo/partIssWoItemDetail.jsp"
 *                        redirect="false"
 */
public class PartIssWoItemDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTISS_DETAIL_INIT         = 1001;
    /** ���� */
    public static final int PTISS_DETAIL_INPUT        = 1002;
    /** ���� */
    public static final int PTISS_DETAIL_UPDATE       = 1003;
    /** serial check   */
    public static final int PTISS_SERIAL_CHECK        = 2000;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartIssWoItemDetailForm partIssWoItemDetailForm = (PartIssWoItemDetailForm) form;
        
        switch (partIssWoItemDetailForm.getStrutsAction())
        {
            case PartIssWoItemDetailAction.PTISS_DETAIL_INIT:
                this.findDetail(request, partIssWoItemDetailForm);
                returnActionForward = mapping.findForward("partIssWoItemDetail");
                break;
            case PartIssWoItemDetailAction.PTISS_DETAIL_INPUT:
            	insertDetail(partIssWoItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartIssWoItemDetailAction.PTISS_DETAIL_UPDATE:
            	updateDetail(partIssWoItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartIssWoItemDetailAction.PTISS_SERIAL_CHECK:
                serialCheck(partIssWoItemDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("partIssWoItemDetail");
                break;
        }

        return returnActionForward;
    }
    
    

    /**
     * ������� �� ��ȸ
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partIssWoItemDetailForm
     */
    private void findDetail(HttpServletRequest request, PartIssWoItemDetailForm partIssWoItemDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	PartIssWoItemDetailService partIssWoItemDetailService = (PartIssWoItemDetailService)getBean("partIssWoItemDetailService");
    	
    	PartIssWoItemListDTO partIssWoItemListDTO = partIssWoItemDetailForm.getPartIssWoItemListDTO();
    	
    	partIssWoItemListDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        PartIssWoItemDetailDTO partIssWoItemDetailDTO = partIssWoItemDetailService.findDetail(partIssWoItemListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        partIssWoItemDetailForm.setPartIssWoItemDetailDTO(partIssWoItemDetailDTO);
        
    }
    /**
     * detail insert
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailForm
     * @param request
     */
    private void insertDetail(PartIssWoItemDetailForm partIssWoItemDetailForm, HttpServletRequest request) throws Exception
    {
        PartIssWoItemDetailService partIssWoItemDetailService = (PartIssWoItemDetailService) getBean("partIssWoItemDetailService");
        
        PartIssWoItemDetailDTO partIssWoItemDetailDTO = partIssWoItemDetailForm.getPartIssWoItemDetailDTO();
        partIssWoItemDetailDTO.setPtisslistId(partIssWoItemDetailForm.getPartIssWoItemListDTO().getPtisslistId());
        partIssWoItemDetailDTO.setEnterBy(getUser(request).getUserId());
        partIssWoItemDetailDTO.setCompNo(getUser(request).getCompNo());
        
        partIssWoItemDetailService.insertDetail(partIssWoItemDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailForm
     * @param request
     */
    private void updateDetail(PartIssWoItemDetailForm partIssWoItemDetailForm, HttpServletRequest request) throws Exception
    {
    	PartIssWoItemDetailService partIssWoItemDetailService = (PartIssWoItemDetailService) getBean("partIssWoItemDetailService");
        
        PartIssWoItemDetailDTO partIssWoItemDetailDTO = partIssWoItemDetailForm.getPartIssWoItemDetailDTO();
        
        partIssWoItemDetailDTO.setEnterBy(getUser(request).getUserId());
        partIssWoItemDetailDTO.setCompNo(getUser(request).getCompNo());
        
        partIssWoItemDetailService.updateDetail(partIssWoItemDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    
    /**
     * serial check
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailForm
     * @param request
     */
    private void serialCheck(PartIssWoItemDetailForm partIssWoItemDetailForm, HttpServletRequest request) throws Exception
    {
        PartIssWoItemDetailService partIssWoItemDetailService = (PartIssWoItemDetailService) getBean("partIssWoItemDetailService");
        //partIssWoItemListDTO�� ����ִ� 
        PartIssWoItemDetailDTO partIssWoItemDetailDTO = partIssWoItemDetailForm.getPartIssWoItemDetailDTO();
        partIssWoItemDetailDTO.setPtisslistId(partIssWoItemDetailForm.getPartIssWoItemListDTO().getPtisslistId());
        
        
        PartIssWoItemDetailDTO detailDTO=partIssWoItemDetailService.serialCheck(partIssWoItemDetailDTO);
        
        partIssWoItemDetailForm.setPartIssWoItemDetailDTO(detailDTO);
        setAjaxDesc(request, detailDTO.getSerialNo());
        
    }
}