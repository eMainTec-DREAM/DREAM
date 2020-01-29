package mobile.dream.mapm.mains.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.MobileAuthAction;
import mobile.dream.mapm.mains.dto.MaPmInsDetailDTO;
import mobile.dream.mapm.mains.form.MaPmInsDetailForm;
import mobile.dream.mapm.mains.service.MaPmInsDetailService;

/**
 * �������� - �� action
 * 
 * @author jung7126
 * @version $Id: MaPmInsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maPmInsDetail" name="maPmInsDetailForm"
 *                input="/mobile/dream/mapm/mains/maPmInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmInsDetail" path="/mobile/dream/mapm/mains/maPmInsDetail.jsp"
 *                        redirect="false"
 */
public class MaPmInsDetailAction extends MobileAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PM_INS_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int PM_INS_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int PM_INS_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmInsDetailForm maPmInsDetailForm = (MaPmInsDetailForm) form;
        
        switch (maPmInsDetailForm.getStrutsAction())
        {
            case MaPmInsDetailAction.PM_INS_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPmInsDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPmInsDetailAction.PM_INS_DETAIL_INPUT:
            	insertDetail(maPmInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmInsDetailAction.PM_INS_DETAIL_UPDATE:
            	updateDetail(maPmInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �������� �� ��ȸ
     * @author jung7126
     * @version $Id: MaPmInsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmInsDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPmInsDetailForm maPmInsDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPmInsDetailService maPmInsDetailService = (MaPmInsDetailService)getBean("maPmInsDetailService");

        String pmId = maPmInsDetailForm.getMaPmInsCommonDTO().getPmId();
        
        // ��ȸ�� �� �κ�
        MaPmInsDetailDTO maPmInsDetailDTO = maPmInsDetailService.findDetail(pmId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPmInsDetailForm.setMaPmInsDetailDTO(maPmInsDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPmInsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailForm
     * @param request
     */
    private void insertDetail(MaPmInsDetailForm maPmInsDetailForm, HttpServletRequest request) throws Exception
    {
        MaPmInsDetailService maPmInsDetailService = (MaPmInsDetailService) getBean("maPmInsDetailService");
        
        MaPmInsDetailDTO maPmInsDetailDTO = maPmInsDetailForm.getMaPmInsDetailDTO();
        
        maPmInsDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmInsDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPmInsDetailService.insertDetail(maPmInsDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPmInsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailForm
     * @param request
     */
    private void updateDetail(MaPmInsDetailForm maPmInsDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmInsDetailService maPmInsDetailService = (MaPmInsDetailService) getBean("maPmInsDetailService");
        
        MaPmInsDetailDTO maPmInsDetailDTO = maPmInsDetailForm.getMaPmInsDetailDTO();
        
        maPmInsDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmInsDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPmInsDetailService.updateDetail(maPmInsDetailDTO);
        
        setAjaxStatus(request);
    }
}