package dream.mgr.cdsys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.cdsys.dto.MgrCdSysDetailDTO;
import dream.mgr.cdsys.form.MgrCdSysDetailForm;
import dream.mgr.cdsys.service.MgrCdSysDetailService;

/**
 * �ý����ڵ� - �� action
 * 
 * @author kim2107
 * @version $Id: MgrCdSysDetailAction.java,v 1.2 2015/12/02 01:21:30 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/mgrCdSysDetail" name="mgrCdSysDetailForm"
 *                input="/dream/mgr/cdsys/mgrCdSysDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCdSysDetail" path="/dream/mgr/cdsys/mgrCdSysDetail.jsp"
 *                        redirect="false"
 */
public class MgrCdSysDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int LISTTYPE_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int LISTTYPE_DETAIL_UPDATE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCdSysDetailForm mgrCdSysDetailForm = (MgrCdSysDetailForm) form;
        
        switch (mgrCdSysDetailForm.getStrutsAction())
        {
            case MgrCdSysDetailAction.LISTTYPE_DETAIL_INIT:
                this.findDetail(request, mgrCdSysDetailForm);
                returnActionForward = mapping.findForward("mgrCdSysDetail");
                break;
            case MgrCdSysDetailAction.LISTTYPE_DETAIL_UPDATE:
            	updateDetail(mgrCdSysDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrCdSysDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ý����ڵ� �� ��ȸ
     * @author kim2107
     * @version $Id: MgrCdSysDetailAction.java,v 1.2 2015/12/02 01:21:30 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mgrCdSysDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrCdSysDetailForm mgrCdSysDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MgrCdSysDetailService mgrCdSysDetailService = (MgrCdSysDetailService)getBean("mgrCdSysDetailService");

        // �Ѱ��� cdSysMId ����
        String cdSysMId = mgrCdSysDetailForm.getMgrCdSysCommonDTO().getCdSysMId();
        
        // ��ȸ�� �� �κ�
        MgrCdSysDetailDTO mgrCdSysDetailDTO = mgrCdSysDetailService.findDetail(cdSysMId, getUser(request).getLangId());
        
        // ��ȸ�� ��  �����Ѵ�.
        mgrCdSysDetailForm.setMgrCdSysDetailDTO(mgrCdSysDetailDTO);
    }
    
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id: MgrCdSysDetailAction.java,v 1.2 2015/12/02 01:21:30 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param mgrCdSysDetailForm
     * @param request
     */
    private void updateDetail(MgrCdSysDetailForm mgrCdSysDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrCdSysDetailService mgrCdSysDetailService = (MgrCdSysDetailService) getBean("mgrCdSysDetailService");
        
        MgrCdSysDetailDTO mgrCdSysDetailDTO = mgrCdSysDetailForm.getMgrCdSysDetailDTO();
        
        mgrCdSysDetailDTO.setEnterBy(getUser(request).getUserId());
        mgrCdSysDetailDTO.setUserLang(getUser(request).getLangId());
        
        mgrCdSysDetailService.updateDetail(mgrCdSysDetailDTO);
        
        setAjaxStatus(request);
    }
}