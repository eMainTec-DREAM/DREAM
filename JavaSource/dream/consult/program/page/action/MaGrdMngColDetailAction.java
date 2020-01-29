package dream.consult.program.page.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaGrdMngColDetailDTO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.form.MaGrdMngColDetailForm;
import dream.consult.program.page.service.MaGrdMngColDetailService;

/**
 * Į�� ��
 * @author  jung7126
 * @version $Id: MaGrdMngColDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maGrdMngColDetail" name="maGrdMngColDetailForm"
 *                input="/dream/consult/program/page/maGrdMngColDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgMngGrdColDetail" name="maGrdMngColDetailForm"
 *                input="/dream/consult/program/page/maPgMngGrdColDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPgGrdColMngDetail" name="maGrdMngColDetailForm"
 *                input="/dream/consult/program/page/maPgGrdColMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maGrdMngColDetail" path="/dream/consult/program/page/maGrdMngColDetail.jsp"
 *                        redirect="false"
 */
public class MaGrdMngColDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int GRD_COL_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int GRD_COL_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int GRD_COL_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaGrdMngColDetailForm maGrdMngColDetailForm = (MaGrdMngColDetailForm) form;
        switch (maGrdMngColDetailForm.getStrutsAction())
        {
            case MaGrdMngColDetailAction.GRD_COL_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maGrdMngColDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaGrdMngColDetailAction.GRD_COL_DETAIL_UPDATE:
            	updateDetail(maGrdMngColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaGrdMngColDetailAction.GRD_COL_DETAIL_INPUT:
            	insertDetail(maGrdMngColDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�麰 ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaGrdMngColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maGrdMngColDetailForm
     */
    private void findDetail(HttpServletRequest request, MaGrdMngColDetailForm maGrdMngColDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaGrdMngColDetailService maGrdMngColDetailService = (MaGrdMngColDetailService)getBean("maGrdMngColDetailService");

    	// �Ѱ��� pageId ����
        String pgGridId = maGrdMngColDetailForm.getMaGrdMngCommonDTO().getPgGridId();
        // �Ѱ��� grdColId ����
        String grdColId = maGrdMngColDetailForm.getMaGrdMngCommonDTO().getPgGridColId();
        
        // ��ȸ�� �� �κ�
        MaGrdMngColDetailDTO maGrdMngColDetailDTO = maGrdMngColDetailService.findDetail(pgGridId, grdColId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maGrdMngColDetailForm.setMaGrdMngColDetailDTO(maGrdMngColDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaGrdMngColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailForm
     * @param request
     */
    private void updateDetail(MaGrdMngColDetailForm maGrdMngColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdMngColDetailService maGrdMngColDetailService = (MaGrdMngColDetailService) getBean("maGrdMngColDetailService");
        
        MaGrdMngColDetailDTO maGrdMngColDetailDTO = maGrdMngColDetailForm.getMaGrdMngColDetailDTO();
        MaGrdMngCommonDTO maGrdMngCommonDTO = maGrdMngColDetailForm.getMaGrdMngCommonDTO();
        maGrdMngColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdMngColDetailService.updateDetail(maGrdMngColDetailDTO,maGrdMngCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaGrdMngColDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailForm
     * @param request
     */
    private void insertDetail(MaGrdMngColDetailForm maGrdMngColDetailForm, HttpServletRequest request) throws Exception
    {
    	MaGrdMngColDetailService maGrdMngColDetailService = (MaGrdMngColDetailService) getBean("maGrdMngColDetailService");
        
        MaGrdMngColDetailDTO maGrdMngColDetailDTO = maGrdMngColDetailForm.getMaGrdMngColDetailDTO();
        
        MaGrdMngCommonDTO maGrdMngCommonDTO = maGrdMngColDetailForm.getMaGrdMngCommonDTO();
        maGrdMngColDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maGrdMngColDetailService.insertDetail(maGrdMngColDetailDTO, maGrdMngCommonDTO);
        
        setAjaxStatus(request);
    }
}