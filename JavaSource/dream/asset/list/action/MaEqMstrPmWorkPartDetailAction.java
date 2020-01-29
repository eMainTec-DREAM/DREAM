package dream.asset.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;
import dream.asset.list.form.MaEqMstrPmWorkPartDetailForm;
import dream.asset.list.service.MaEqMstrPmWorkPartDetailService;

/**
 * ���� �����۾� ��ǰ ��
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmWorkPartDetail" name="maEqMstrPmWorkPartDetailForm"
 *                input="/dream/asset/list/maEqMstrPmWorkPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmWorkPartDetail" path="/dream/asset/list/maEqMstrPmWorkPartDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmWorkPartDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_MSTR_PMWORK_PART_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int EQ_MSTR_PMWORK_PART_DETAIL_UPDATE 			= 1002;
    /** �Է� */
    public static final int EQ_MSTR_PMWORK_PART_DETAIL_INPUT 			= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmWorkPartDetailForm maEqMstrPmWorkPartDetailForm = (MaEqMstrPmWorkPartDetailForm) form;
        switch (maEqMstrPmWorkPartDetailForm.getStrutsAction())
        {
            case MaEqMstrPmWorkPartDetailAction.EQ_MSTR_PMWORK_PART_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqMstrPmWorkPartDetailForm);
                returnActionForward = mapping.findForward("maEqMstrPmWorkPartDetail");
                break;
            case MaEqMstrPmWorkPartDetailAction.EQ_MSTR_PMWORK_PART_DETAIL_UPDATE:
            	updateDetail(maEqMstrPmWorkPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmWorkPartDetailAction.EQ_MSTR_PMWORK_PART_DETAIL_INPUT:
            	insertDetail(maEqMstrPmWorkPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmWorkPartDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾� ��ǰ ��ȸ 
     * @author kim2107
     * @version $Id: MaEqMstrPmWorkPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmWorkPartDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrPmWorkPartDetailForm maEqMstrPmWorkPartDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqMstrPmWorkPartDetailService maEqMstrPmWorkPartDetailService = (MaEqMstrPmWorkPartDetailService)getBean("maEqMstrPmWorkPartDetailService");

    	MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = maEqMstrPmWorkPartDetailForm.getMaEqMstrPmWorkDetailDTO();
        MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO = maEqMstrPmWorkPartDetailForm.getMaEqMstrPmWorkPartListDTO();
        
        // ��ȸ�� �� �κ�
        MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO = maEqMstrPmWorkPartDetailService.findDetail(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqMstrPmWorkPartDetailForm.setMaEqMstrPmWorkPartDetailDTO(maEqMstrPmWorkPartDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrPmWorkPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmWorkPartDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrPmWorkPartDetailForm maEqMstrPmWorkPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmWorkPartDetailService maEqMstrPmWorkPartDetailService = (MaEqMstrPmWorkPartDetailService) getBean("maEqMstrPmWorkPartDetailService");
        
        MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO = maEqMstrPmWorkPartDetailForm.getMaEqMstrPmWorkPartDetailDTO();
        MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = maEqMstrPmWorkPartDetailForm.getMaEqMstrPmWorkDetailDTO();
        
        maEqMstrPmWorkPartDetailService.updateDetail(maEqMstrPmWorkDetailDTO,maEqMstrPmWorkPartDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrPmWorkPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmWorkPartDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrPmWorkPartDetailForm maEqMstrPmWorkPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmWorkPartDetailService maEqMstrPmWorkPartDetailService = (MaEqMstrPmWorkPartDetailService) getBean("maEqMstrPmWorkPartDetailService");
        
        MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO = maEqMstrPmWorkPartDetailForm.getMaEqMstrPmWorkPartDetailDTO();
        MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = maEqMstrPmWorkPartDetailForm.getMaEqMstrPmWorkDetailDTO();
        
        maEqMstrPmWorkPartDetailService.insertDetail(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
}