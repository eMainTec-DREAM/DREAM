package dream.asset.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrHistDetailDTO;
import dream.asset.list.form.MaEqMstrHistDetailForm;
import dream.asset.list.service.MaEqMstrHistDetailService;

/**
 * ���񺯰��̷�- �� action
 * 
 * @author kim2107
 * @version $Id: MaEqMstrHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqMstrHistDetail" name="maEqMstrHistDetailForm"
 *                input="/dream/asset/list/maEqMstrHistDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrHistDetail" path="/dream/asset/list/maEqMstrHistDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrHistDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int EQ_MSTR_HIST_DETAIL_INIT 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrHistDetailForm maEqMstrHistDetailForm = (MaEqMstrHistDetailForm) form;
        
        switch (maEqMstrHistDetailForm.getStrutsAction())
        {
            case MaEqMstrHistDetailAction.EQ_MSTR_HIST_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maEqMstrHistDetailForm);
                returnActionForward = mapping.findForward("maEqMstrHistDetail");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrHistDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���񺯰��̷� �� ��ȸ
     * @author kim2107
     * @version $Id: MaEqMstrHistDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrHistDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrHistDetailForm maEqMstrHistDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaEqMstrHistDetailService maEqMstrHistDetailService = (MaEqMstrHistDetailService)getBean("maEqMstrHistDetailService");

        // ��ȸ�� �� �κ�
        MaEqMstrHistDetailDTO maEqMstrHistDetailDTO = maEqMstrHistDetailService.findDetail(maEqMstrHistDetailForm.getMaEqMstrHistListDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maEqMstrHistDetailForm.setMaEqMstrHistDetailDTO(maEqMstrHistDetailDTO);
    }
}