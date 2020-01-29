package dream.work.pm.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmEqClnDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.MaPmEqClnDetailForm;
import dream.work.pm.list.service.MaPmEqClnDetailService;

/**
 * ���漳��
 * @author  kim21017
 * @version $Id: MaPmEqClnDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPmEqClnDetail" name="maPmEqClnDetailForm"
 *                input="/dream/work/pm/list/work/maPmEqClnDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmEqOilDetail" name="maPmEqClnDetailForm"
 *                input="/dream/work/pm/list/work/maPmEqOilDetail.jsp" scope="request"
 *                validate="false"
 */
public class MaPmEqClnDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PM_EQ_CLN_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int PM_EQ_CLN_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int PM_EQ_CLN_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmEqClnDetailForm maPmEqClnDetailForm = (MaPmEqClnDetailForm) form;
        
        super.updateAudit(maPmEqClnDetailForm.getMaPmEqClnDetailDTO().getAuditKey()==""?maPmEqClnDetailForm.getMaPmMstrCommonDTO().getAuditKey():maPmEqClnDetailForm.getMaPmEqClnDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPmEqClnDetailForm.getStrutsAction())
        {
            case MaPmEqClnDetailAction.PM_EQ_CLN_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPmEqClnDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPmEqClnDetailAction.PM_EQ_CLN_DETAIL_UPDATE:
            	updateDetail(maPmEqClnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmEqClnDetailAction.PM_EQ_CLN_DETAIL_INPUT:
            	insertDetail(maPmEqClnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �� ��ȸ
     * @author kim2107
     * @version $Id: MaPmEqClnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmEqClnDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPmEqClnDetailForm maPmEqClnDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPmEqClnDetailService maPmEqClnDetailService = (MaPmEqClnDetailService)getBean("maPmEqClnDetailService");

    	// �۾����Id ����
        String pmId = maPmEqClnDetailForm.getMaPmMstrCommonDTO().getPmId();
        // �Ѱ��� ��������id ����
        String pmEquipId = maPmEqClnDetailForm.getMaPmMstrCommonDTO().getPmEquipId();
        
        // ��ȸ�� �� �κ�
        MaPmEqClnDetailDTO maPmEqClnDetailDTO = maPmEqClnDetailService.findDetail(pmId, pmEquipId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        maPmEqClnDetailForm.setMaPmEqClnDetailDTO(maPmEqClnDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPmEqClnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailForm
     * @param request
     */
    private void updateDetail(MaPmEqClnDetailForm maPmEqClnDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmEqClnDetailService maPmEqClnDetailService = (MaPmEqClnDetailService) getBean("maPmEqClnDetailService");
        
        MaPmEqClnDetailDTO maPmEqClnDetailDTO = maPmEqClnDetailForm.getMaPmEqClnDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmEqClnDetailForm.getMaPmMstrCommonDTO();
        maPmEqClnDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmEqClnDetailService.updateDetail(maPmEqClnDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPmEqClnDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailForm
     * @param request
     */
    private void insertDetail(MaPmEqClnDetailForm maPmEqClnDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPmEqClnDetailService maPmEqClnDetailService = (MaPmEqClnDetailService) getBean("maPmEqClnDetailService");
        
        MaPmEqClnDetailDTO maPmEqClnDetailDTO = maPmEqClnDetailForm.getMaPmEqClnDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmEqClnDetailForm.getMaPmMstrCommonDTO();
        maPmEqClnDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmEqClnDetailService.insertDetail(maPmEqClnDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}