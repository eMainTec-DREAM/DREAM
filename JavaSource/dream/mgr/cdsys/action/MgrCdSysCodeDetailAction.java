package dream.mgr.cdsys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.form.MgrCdSysCodeDetailForm;
import dream.mgr.cdsys.service.MgrCdSysCodeDetailService;

/**
 * �ý��� �ڵ� - �з� �˾�
 * @author  youngjoo38
 * @version $Id: MgrCdSysCodeDetailAction.java,v 1.0 2015/12/04 09:09:30 youngjoo38 Exp $
 * @since   1.0
 * @struts:action path="/mgrCdSysCodeDetail" name="mgrCdSysCodeDetailForm"
 *                input="/dream/mgr/cdsys/mgrCdSysCodeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCdSysCodeDetail" path="/dream/mgr/cdsys/mgrCdSysCodeDetail.jsp"
 *                        redirect="false"
 */
public class MgrCdSysCodeDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int LISTTYPE_CODE_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int LISTTYPE_CODE_DETAIL_UPDATE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCdSysCodeDetailForm mgrCdSysCodeDetailForm = (MgrCdSysCodeDetailForm) form;
        switch (mgrCdSysCodeDetailForm.getStrutsAction())
        {
            case MgrCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, mgrCdSysCodeDetailForm);
                returnActionForward = mapping.findForward("mgrCdSysCodeDetail");
                break;
            case MgrCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_UPDATE:
            	updateDetail(mgrCdSysCodeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrCdSysCodeDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ý����ڵ� �з� ��ȸ
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrCdSysCodeDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrCdSysCodeDetailForm mgrCdSysCodeDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MgrCdSysCodeDetailService mgrCdSysCodeDetailService = (MgrCdSysCodeDetailService)getBean("mgrCdSysCodeDetailService");

    	MgrCdSysCommonDTO mgrCdSysCommonDTO = mgrCdSysCodeDetailForm.getMgrCdSysCommonDTO();
    	MgrCdSysCodeListDTO mgrCdSysCodeListDTO = mgrCdSysCodeDetailForm.getMgrCdSysCodeListDTO();
    	
        // ��ȸ�� �� �κ�
        MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO = mgrCdSysCodeDetailService.findDetail(mgrCdSysCommonDTO, mgrCdSysCodeListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        mgrCdSysCodeDetailForm.setMgrCdSysCodeDetailDTO(mgrCdSysCodeDetailDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCodeDetailForm
     * @param request
     */
    private void updateDetail(MgrCdSysCodeDetailForm mgrCdSysCodeDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrCdSysCodeDetailService mgrCdSysCodeDetailService = (MgrCdSysCodeDetailService) getBean("mgrCdSysCodeDetailService");
        
        MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO = mgrCdSysCodeDetailForm.getMgrCdSysCodeDetailDTO();
        MgrCdSysCommonDTO mgrCdSysCommonDTO = mgrCdSysCodeDetailForm.getMgrCdSysCommonDTO();
        
        mgrCdSysCodeDetailService.updateDetail(mgrCdSysCodeDetailDTO,mgrCdSysCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}