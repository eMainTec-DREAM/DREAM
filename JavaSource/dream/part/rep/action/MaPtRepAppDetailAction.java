package dream.part.rep.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rep.dto.MaPtRepAppDetailDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.form.MaPtRepAppDetailForm;
import dream.part.rep.service.MaPtRepAppDetailService;

/**
 * ��ǰ���� - �� action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtRepAppDetail" name="maPtRepAppDetailForm"
 *                input="/dream/part/rep/maPtRepAppDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRepAppDetail" path="/dream/part/rep/maPtRepAppDetail.jsp"
 *                        redirect="false"
 */
public class MaPtRepAppDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTREPAPP_DETAIL_INIT           = 1001;
    /** ���� */
    public static final int PTREPAPP_DETAIL_INPUT          = 1002;
    /** ���� */
    public static final int PTREPAPP_DETAIL_UPDATE         = 1003;
    /** �ߺ�üũ */
    public static final int PTREPAPP_DETAIL_CHECK          = 1020;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRepAppDetailForm maPtRepAppDetailForm = (MaPtRepAppDetailForm) form;
        
        switch (maPtRepAppDetailForm.getStrutsAction())
        {
            case MaPtRepAppDetailAction.PTREPAPP_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtRepAppDetailForm);
                returnActionForward = mapping.findForward("maPtRepAppDetail");
                break;
            case MaPtRepAppDetailAction.PTREPAPP_DETAIL_INPUT:
            	insertDetail(maPtRepAppDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRepAppDetailAction.PTREPAPP_DETAIL_UPDATE:
            	updateDetail(maPtRepAppDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtRepAppDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ǰ���� �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRepAppDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtRepAppDetailForm maPtRepAppDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtRepAppDetailService maPtRepAppDetailService = (MaPtRepAppDetailService)getBean("maPtRepAppDetailService");
    	
    	MaPtRepCommonDTO maPtRepCommonDTO = maPtRepAppDetailForm.getMaPtRepCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaPtRepAppDetailDTO maPtRepAppDetailDTO = maPtRepAppDetailService.findDetail(maPtRepCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtRepAppDetailForm.setMaPtRepAppDetailDTO(maPtRepAppDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailForm
     * @param request
     */
    private void insertDetail(MaPtRepAppDetailForm maPtRepAppDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRepAppDetailService maPtRepAppDetailService = (MaPtRepAppDetailService) getBean("maPtRepAppDetailService");
        
        MaPtRepAppDetailDTO maPtRepAppDetailDTO = maPtRepAppDetailForm.getMaPtRepAppDetailDTO();
        
        maPtRepAppDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRepAppDetailService.insertDetail(maPtRepAppDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailForm
     * @param request
     */
    private void updateDetail(MaPtRepAppDetailForm maPtRepAppDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtRepAppDetailService maPtRepAppDetailService = (MaPtRepAppDetailService) getBean("maPtRepAppDetailService");
        
        MaPtRepAppDetailDTO maPtRepAppDetailDTO = maPtRepAppDetailForm.getMaPtRepAppDetailDTO();
        
        maPtRepAppDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRepAppDetailService.updateDetail(maPtRepAppDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}