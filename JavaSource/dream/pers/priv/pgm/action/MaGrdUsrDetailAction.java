package dream.pers.priv.pgm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.SuperAuthAction;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.form.MaGrdUsrDetailForm;
import dream.pers.priv.pgm.service.MaGrdUsrDetailService;

/**
 * Į�� ��
 * @author  jung7126
 * @version $Id: MaGrdUsrDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maGrdUsrDetail" name="maGrdUsrDetailForm"
 *                input="/dream/pers/priv/pgm/maGrdUsrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdUsrDetail" path="/dream/pers/priv/pgm/maGrdUsrDetail.jsp"
 *                        redirect="false"
 */
public class MaGrdUsrDetailAction extends SuperAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int GRD_USR_DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int GRD_USR_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int GRD_USR_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdUsrDetailForm maGrdUsrDetailForm = (MaGrdUsrDetailForm) form;
        switch (maGrdUsrDetailForm.getStrutsAction())
        {
            case MaGrdUsrDetailAction.GRD_USR_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maGrdUsrDetailForm);
                returnActionForward = mapping.findForward("maGrdUsrDetail");
                break;
            case MaGrdUsrDetailAction.GRD_USR_DETAIL_UPDATE:
            	updateDetail(maGrdUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaGrdUsrDetailAction.GRD_USR_DETAIL_INPUT:
            	insertDetail(maGrdUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                this.findDetail(request, maGrdUsrDetailForm);
                returnActionForward = mapping.findForward("maGrdUsrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�麰 ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaGrdUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdUsrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaGrdUsrDetailForm maGrdUsrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaGrdUsrDetailService maGrdUsrDetailService = (MaGrdUsrDetailService)getBean("maGrdUsrDetailService");

    	// �Ѱ��� pageId ����
    	MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrDetailForm.getMaGrdUsrCommonDTO();
        
        // ��ȸ�� �� �κ�
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrDetailService.findDetail(maGrdUsrCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maGrdUsrDetailForm.setMaGrdUsrDetailDTO(maGrdUsrDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaGrdUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailForm
     * @param request
     */
    private void updateDetail(MaGrdUsrDetailForm maGrdUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrDetailService maGrdUsrDetailService = (MaGrdUsrDetailService) getBean("maGrdUsrDetailService");
        
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrDetailForm.getMaGrdUsrDetailDTO();
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrDetailService.updateDetail(maGrdUsrDetailDTO,maGrdUsrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaGrdUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailForm
     * @param request
     */
    private void insertDetail(MaGrdUsrDetailForm maGrdUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrDetailService maGrdUsrDetailService = (MaGrdUsrDetailService) getBean("maGrdUsrDetailService");
        
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrDetailForm.getMaGrdUsrDetailDTO();
        
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrDetailService.insertDetail(maGrdUsrDetailDTO, maGrdUsrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}