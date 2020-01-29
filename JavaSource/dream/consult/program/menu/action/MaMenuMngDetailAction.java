package dream.consult.program.menu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.menu.dto.MaMenuMngDetailDTO;
import dream.consult.program.menu.form.MaMenuMngDetailForm;
import dream.consult.program.menu.service.MaMenuMngDetailService;

/**
 * �޴� - �� action
 * 
 * @author kim2107
 * @version $Id: MaMenuMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maMenuMngDetail" name="maMenuMngDetailForm"
 *                input="/dream/consult/program/menu/maMenuMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMenuMngDetail" path="/dream/consult/program/menu/maMenuMngDetail.jsp"
 *                        redirect="false"
 */
public class MaMenuMngDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int MENU_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int MENU_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int MENU_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaMenuMngDetailForm maMenuMngDetailForm = (MaMenuMngDetailForm) form;
        
        switch (maMenuMngDetailForm.getStrutsAction())
        {
            case MaMenuMngDetailAction.MENU_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maMenuMngDetailForm);
                returnActionForward = mapping.findForward("maMenuMngDetail");
                break;
            case MaMenuMngDetailAction.MENU_DETAIL_INPUT:
            	insertDetail(maMenuMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaMenuMngDetailAction.MENU_DETAIL_UPDATE:
            	updateDetail(maMenuMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maMenuMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaMenuMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMenuMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaMenuMngDetailForm maMenuMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaMenuMngDetailService maMenuMngDetailService = (MaMenuMngDetailService)getBean("maMenuMngDetailService");

        // �Ѱ��� �޴�Id ����
        String menuId = maMenuMngDetailForm.getMaMenuMngCommonDTO().getMenuId();
        
        // ��ȸ�� �� �κ�
        MaMenuMngDetailDTO maMenuMngDetailDTO = maMenuMngDetailService.findDetail(menuId, getUser(request).getLangId());
        
        // ��ȸ�� ��  �����Ѵ�.
        maMenuMngDetailForm.setMaMenuMngDetailDTO(maMenuMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaMenuMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailForm
     * @param request
     */
    private void insertDetail(MaMenuMngDetailForm maMenuMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaMenuMngDetailService maMenuMngDetailService = (MaMenuMngDetailService) getBean("maMenuMngDetailService");
        
        MaMenuMngDetailDTO maMenuMngDetailDTO = maMenuMngDetailForm.getMaMenuMngDetailDTO();
        
        maMenuMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maMenuMngDetailService.insertDetail(maMenuMngDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaMenuMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailForm
     * @param request
     */
    private void updateDetail(MaMenuMngDetailForm maMenuMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMenuMngDetailService maMenuMngDetailService = (MaMenuMngDetailService) getBean("maMenuMngDetailService");
        
        MaMenuMngDetailDTO maMenuMngDetailDTO = maMenuMngDetailForm.getMaMenuMngDetailDTO();
        
        maMenuMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maMenuMngDetailService.updateDetail(maMenuMngDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}