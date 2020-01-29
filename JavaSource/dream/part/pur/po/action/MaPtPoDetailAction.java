package dream.part.pur.po.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.pur.po.dto.MaPtPoCommonDTO;
import dream.part.pur.po.dto.MaPtPoDetailDTO;
import dream.part.pur.po.form.MaPtPoDetailForm;
import dream.part.pur.po.service.MaPtPoDetailService;

/**
 * �����̷� - �� action
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtPoDetail" name="maPtPoDetailForm"
 *                input="/dream/part/pur/po/maPtPoDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtPoDetail" path="/dream/part/pur/po/maPtPoDetail.jsp"
 *                        redirect="false"
 */
public class MaPtPoDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTPO_DETAIL_INIT         = 1001;
    /** ���� */
    public static final int PTPO_DETAIL_INPUT        = 1002;
    /** ���� */
    public static final int PTPO_DETAIL_UPDATE       = 1003;
    /** ���¼��� */
    public static final int PTPO_DETAIL_STATUS_UPDATE= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtPoDetailForm maPtPoDetailForm = (MaPtPoDetailForm) form;
        
        switch (maPtPoDetailForm.getStrutsAction())
        {
            case MaPtPoDetailAction.PTPO_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtPoDetailForm);
                returnActionForward = mapping.findForward("maPtPoDetail");
                break;
            case MaPtPoDetailAction.PTPO_DETAIL_INPUT:
            	insertDetail(maPtPoDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPoDetailAction.PTPO_DETAIL_UPDATE:
            	updateDetail(maPtPoDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPoDetailAction.PTPO_DETAIL_STATUS_UPDATE:
                updatePtPoListStatus(maPtPoDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtPoDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �����̷� �� ��ȸ
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtPoDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtPoDetailForm maPtPoDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService)getBean("maPtPoDetailService");
    	
    	MaPtPoCommonDTO maPtPoCommonDTO = maPtPoDetailForm.getMaPtPoCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailService.findDetail(maPtPoCommonDTO.getPoListId(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtPoDetailForm.setMaPtPoDetailDTO(maPtPoDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailForm
     * @param request
     */
    private void insertDetail(MaPtPoDetailForm maPtPoDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) getBean("maPtPoDetailService");
        
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailForm.getMaPtPoDetailDTO();
        
        maPtPoDetailService.insertDetail(maPtPoDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailForm
     * @param request
     */
    private void updateDetail(MaPtPoDetailForm maPtPoDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) getBean("maPtPoDetailService");
        
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailForm.getMaPtPoDetailDTO();
        
        maPtPoDetailService.updateDetail(maPtPoDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail ���� update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailForm
     * @param request
     */
    private void updatePtPoListStatus(MaPtPoDetailForm maPtPoDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) getBean("maPtPoDetailService");
        
        MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailForm.getMaPtPoDetailDTO();
        
        maPtPoDetailService.updatePtPoListStatus(maPtPoDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
}