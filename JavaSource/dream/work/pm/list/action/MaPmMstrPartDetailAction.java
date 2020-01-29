package dream.work.pm.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;
import dream.work.pm.list.form.MaPmMstrPartDetailForm;
import dream.work.pm.list.service.MaPmMstrPartDetailService;

/**
 * �۾���� - ��������
 * @author  jung7126
 * @version $Id: MaPmMstrPartDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maPmMstrPartDetail" name="maPmMstrPartDetailForm"
 *                input="/dream/work/pm/list/maPmMstrPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmOilPartDetail" name="maPmMstrPartDetailForm"
 *                input="/dream/work/pm/list/work/maPmOilPartDetail.jsp" scope="request"
 *                validate="false"
 */
public class MaPmMstrPartDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PM_MSTR_PART_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int PM_MSTR_PART_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int PM_MSTR_PART_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmMstrPartDetailForm maPmMstrPartDetailForm = (MaPmMstrPartDetailForm) form;

        super.updateAudit(maPmMstrPartDetailForm.getMaPmMstrPartDetailDTO().getAuditKey()==""?maPmMstrPartDetailForm.getMaPmMstrCommonDTO().getAuditKey():maPmMstrPartDetailForm.getMaPmMstrPartDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        
        switch (maPmMstrPartDetailForm.getStrutsAction())
        {
            case MaPmMstrPartDetailAction.PM_MSTR_PART_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPmMstrPartDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPmMstrPartDetailAction.PM_MSTR_PART_DETAIL_UPDATE:
            	updateDetail(maPmMstrPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPartDetailAction.PM_MSTR_PART_DETAIL_INPUT:
            	insertDetail(maPmMstrPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾����-�������� �� ��ȸ
     * @author kim2107
     * @version $Id: MaPmMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrPartDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPmMstrPartDetailForm maPmMstrPartDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPmMstrPartDetailService maPmMstrPartDetailService = (MaPmMstrPartDetailService)getBean("maPmMstrPartDetailService");

    	// �۾����Id ����
        String pmId = maPmMstrPartDetailForm.getMaPmMstrCommonDTO().getPmId();
        // �Ѱ��� ��������id ����
        String pmPartId = maPmMstrPartDetailForm.getMaPmMstrCommonDTO().getPmPartId();
        
        // ��ȸ�� �� �κ�
        MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = maPmMstrPartDetailService.findDetail(pmId, pmPartId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        maPmMstrPartDetailForm.setMaPmMstrPartDetailDTO(maPmMstrPartDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaPmMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailForm
     * @param request
     */
    private void updateDetail(MaPmMstrPartDetailForm maPmMstrPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrPartDetailService maPmMstrPartDetailService = (MaPmMstrPartDetailService) getBean("maPmMstrPartDetailService");
        
        MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = maPmMstrPartDetailForm.getMaPmMstrPartDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPartDetailForm.getMaPmMstrCommonDTO();
        maPmMstrPartDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrPartDetailService.updateDetail(maPmMstrPartDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaPmMstrPartDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailForm
     * @param request
     */
    private void insertDetail(MaPmMstrPartDetailForm maPmMstrPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmMstrPartDetailService maPmMstrPartDetailService = (MaPmMstrPartDetailService) getBean("maPmMstrPartDetailService");
        
        MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = maPmMstrPartDetailForm.getMaPmMstrPartDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPartDetailForm.getMaPmMstrCommonDTO();
        maPmMstrPartDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrPartDetailService.insertDetail(maPmMstrPartDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}