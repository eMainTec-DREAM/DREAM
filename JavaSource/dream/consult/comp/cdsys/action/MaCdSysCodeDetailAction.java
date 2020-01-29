package dream.consult.comp.cdsys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.comp.cdsys.dto.MaCdSysCodeDetailDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.form.MaCdSysCodeDetailForm;
import dream.consult.comp.cdsys.service.MaCdSysCodeDetailService;

/**
 * �ý��� �ڵ� - �з� �˾�
 * @author  kim21017
 * @version $Id: MaCdSysCodeDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maCdSysCodeDetail" name="maCdSysCodeDetailForm"
 *                input="/dream/consult/comp/cdsys/maCdSysCodeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdSysCodeDetail" path="/dream/consult/comp/cdsys/maCdSysCodeDetail.jsp"
 *                        redirect="false"
 */
public class MaCdSysCodeDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int LISTTYPE_CODE_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int LISTTYPE_CODE_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int LISTTYPE_CODE_DETAIL_INPUT 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdSysCodeDetailForm maCdSysCodeDetailForm = (MaCdSysCodeDetailForm) form;
        switch (maCdSysCodeDetailForm.getStrutsAction())
        {
            case MaCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maCdSysCodeDetailForm);
                returnActionForward = mapping.findForward("maCdSysCodeDetail");
                break;
            case MaCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_UPDATE:
            	updateDetail(maCdSysCodeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INPUT:
            	insertDetail(maCdSysCodeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maCdSysCodeDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ý����ڵ� �з� ��ȸ
     * @author kim2107
     * @version $Id: MaCdSysCodeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCdSysCodeDetailForm
     */
    private void findDetail(HttpServletRequest request, MaCdSysCodeDetailForm maCdSysCodeDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaCdSysCodeDetailService maCdSysCodeDetailService = (MaCdSysCodeDetailService)getBean("maCdSysCodeDetailService");

    	MaCdSysCommonDTO maCdSysCommonDTO = maCdSysCodeDetailForm.getMaCdSysCommonDTO();
    	MaCdSysCodeListDTO maCdSysCodeListDTO = maCdSysCodeDetailForm.getMaCdSysCodeListDTO();
    	
        // ��ȸ�� �� �κ�
        MaCdSysCodeDetailDTO maCdSysCodeDetailDTO = maCdSysCodeDetailService.findDetail(maCdSysCommonDTO, maCdSysCodeListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maCdSysCodeDetailForm.setMaCdSysCodeDetailDTO(maCdSysCodeDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaCdSysCodeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailForm
     * @param request
     */
    private void updateDetail(MaCdSysCodeDetailForm maCdSysCodeDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCdSysCodeDetailService maCdSysCodeDetailService = (MaCdSysCodeDetailService) getBean("maCdSysCodeDetailService");
        
        MaCdSysCodeDetailDTO maCdSysCodeDetailDTO = maCdSysCodeDetailForm.getMaCdSysCodeDetailDTO();
        MaCdSysCommonDTO maCdSysCommonDTO = maCdSysCodeDetailForm.getMaCdSysCommonDTO();
        
        maCdSysCodeDetailService.updateDetail(maCdSysCodeDetailDTO,maCdSysCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaCdSysCodeDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailForm
     * @param request
     */
    private void insertDetail(MaCdSysCodeDetailForm maCdSysCodeDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCdSysCodeDetailService maCdSysCodeDetailService = (MaCdSysCodeDetailService) getBean("maCdSysCodeDetailService");
        
        MaCdSysCodeDetailDTO maCdSysCodeDetailDTO = maCdSysCodeDetailForm.getMaCdSysCodeDetailDTO();
        
        MaCdSysCommonDTO maCdSysCommonDTO = maCdSysCodeDetailForm.getMaCdSysCommonDTO();
        
        maCdSysCodeDetailService.insertDetail(maCdSysCodeDetailDTO, maCdSysCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}