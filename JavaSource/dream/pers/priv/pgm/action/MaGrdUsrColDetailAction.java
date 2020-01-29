package dream.pers.priv.pgm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.program.page.dto.MaGrdUsrColDetailDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.form.MaGrdUsrColDetailForm;
import dream.pers.priv.pgm.service.MaGrdUsrColDetailService;

/**
 * Į�� ��
 * @author  jung7126
 * @version $Id: MaGrdUsrColDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maGrdUsrColDetail" name="maGrdUsrColDetailForm"
 *                input="/dream/pers/priv/pgm/maGrdUsrColDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdUsrColDetail" path="/dream/pers/priv/pgm/maGrdUsrColDetail.jsp"
 *                        redirect="false"
 */
public class MaGrdUsrColDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int GRD_USR_COL_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int GRD_USR_COL_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int GRD_USR_COL_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdUsrColDetailForm maGrdUsrColDetailForm = (MaGrdUsrColDetailForm) form;
        switch (maGrdUsrColDetailForm.getStrutsAction())
        {
            case MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maGrdUsrColDetailForm);
                returnActionForward = mapping.findForward("maGrdUsrColDetail");
                break;
            case MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_UPDATE:
            	updateDetail(maGrdUsrColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaGrdUsrColDetailAction.GRD_USR_COL_DETAIL_INPUT:
            	insertDetail(maGrdUsrColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maGrdUsrColDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�麰 ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaGrdUsrColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdUsrColDetailForm
     */
    private void findDetail(HttpServletRequest request, MaGrdUsrColDetailForm maGrdUsrColDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaGrdUsrColDetailService maGrdUsrColDetailService = (MaGrdUsrColDetailService)getBean("maGrdUsrColDetailService");

    	// �Ѱ��� pageId ����
        String pgGridColId = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO().getPgGridColId();
        // �Ѱ��� grdColId ����
        String usrPgGridColId = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO().getUsrPgGridColId();
        
        // ��ȸ�� �� �κ�
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = maGrdUsrColDetailService.findDetail(pgGridColId, usrPgGridColId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maGrdUsrColDetailForm.setMaGrdUsrColDetailDTO(maGrdUsrColDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaGrdUsrColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailForm
     * @param request
     */
    private void updateDetail(MaGrdUsrColDetailForm maGrdUsrColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrColDetailService maGrdUsrColDetailService = (MaGrdUsrColDetailService) getBean("maGrdUsrColDetailService");
        
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = maGrdUsrColDetailForm.getMaGrdUsrColDetailDTO();
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrColDetailService.updateDetail(maGrdUsrColDetailDTO,maGrdUsrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaGrdUsrColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailForm
     * @param request
     */
    private void insertDetail(MaGrdUsrColDetailForm maGrdUsrColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdUsrColDetailService maGrdUsrColDetailService = (MaGrdUsrColDetailService) getBean("maGrdUsrColDetailService");
        
        MaGrdUsrColDetailDTO maGrdUsrColDetailDTO = maGrdUsrColDetailForm.getMaGrdUsrColDetailDTO();
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = maGrdUsrColDetailForm.getMaGrdUsrDetailDTO();
        
        maGrdUsrColDetailDTO.setUsrPgGridId(maGrdUsrColDetailForm.getMaGrdUsrDetailDTO().getUsrPgGridId());
        
        MaGrdUsrCommonDTO maGrdUsrCommonDTO = maGrdUsrColDetailForm.getMaGrdUsrCommonDTO();
        maGrdUsrColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdUsrColDetailService.insertDetail(maGrdUsrColDetailDTO, maGrdUsrCommonDTO, getUser(request), maGrdUsrDetailDTO);
        
        setAjaxStatus(request);
    }
}