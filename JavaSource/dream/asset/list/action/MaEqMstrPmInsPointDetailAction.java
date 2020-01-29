package dream.asset.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;
import dream.asset.list.form.MaEqMstrPmInsPointDetailForm;
import dream.asset.list.service.MaEqMstrPmInsPointDetailService;

/**
 * ���� �������� �׸� ��
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPmInsPointDetail" name="maEqMstrPmInsPointDetailForm"
 *                input="/dream/asset/list/maEqMstrPmInsPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPmInsPointDetail" path="/dream/asset/list/maEqMstrPmInsPointDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPmInsPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_MSTR_PMINS_POINT_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int EQ_MSTR_PMINS_POINT_DETAIL_UPDATE 			= 1002;
    /** �Է� */
    public static final int EQ_MSTR_PMINS_POINT_DETAIL_INPUT 			= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPmInsPointDetailForm maEqMstrPmInsPointDetailForm = (MaEqMstrPmInsPointDetailForm) form;
        switch (maEqMstrPmInsPointDetailForm.getStrutsAction())
        {
            case MaEqMstrPmInsPointDetailAction.EQ_MSTR_PMINS_POINT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqMstrPmInsPointDetailForm);
                returnActionForward = mapping.findForward("maEqMstrPmInsPointDetail");
                break;
            case MaEqMstrPmInsPointDetailAction.EQ_MSTR_PMINS_POINT_DETAIL_UPDATE:
            	updateDetail(maEqMstrPmInsPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPmInsPointDetailAction.EQ_MSTR_PMINS_POINT_DETAIL_INPUT:
            	insertDetail(maEqMstrPmInsPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPmInsPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���� ��ȸ 
     * @author kim2107
     * @version $Id: MaEqMstrPmInsPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPmInsPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrPmInsPointDetailForm maEqMstrPmInsPointDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqMstrPmInsPointDetailService maEqMstrPmInsPointDetailService = (MaEqMstrPmInsPointDetailService)getBean("maEqMstrPmInsPointDetailService");

    	MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = maEqMstrPmInsPointDetailForm.getMaEqMstrPmInsDetailDTO();
        MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO = maEqMstrPmInsPointDetailForm.getMaEqMstrPmInsPointListDTO();
        
        // ��ȸ�� �� �κ�
        MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO = maEqMstrPmInsPointDetailService.findDetail(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqMstrPmInsPointDetailForm.setMaEqMstrPmInsPointDetailDTO(maEqMstrPmInsPointDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrPmInsPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmInsPointDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrPmInsPointDetailForm maEqMstrPmInsPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmInsPointDetailService maEqMstrPmInsPointDetailService = (MaEqMstrPmInsPointDetailService) getBean("maEqMstrPmInsPointDetailService");
        
        MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO = maEqMstrPmInsPointDetailForm.getMaEqMstrPmInsPointDetailDTO();
        MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = maEqMstrPmInsPointDetailForm.getMaEqMstrPmInsDetailDTO();
        
        maEqMstrPmInsPointDetailService.updateDetail(maEqMstrPmInsDetailDTO,maEqMstrPmInsPointDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrPmInsPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPmInsPointDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrPmInsPointDetailForm maEqMstrPmInsPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrPmInsPointDetailService maEqMstrPmInsPointDetailService = (MaEqMstrPmInsPointDetailService) getBean("maEqMstrPmInsPointDetailService");
        
        MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO = maEqMstrPmInsPointDetailForm.getMaEqMstrPmInsPointDetailDTO();
        MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = maEqMstrPmInsPointDetailForm.getMaEqMstrPmInsDetailDTO();
        
        maEqMstrPmInsPointDetailService.insertDetail(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
}