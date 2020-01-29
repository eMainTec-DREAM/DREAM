package dream.consult.consult.menu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.consult.menu.dto.McMenuDetailDTO;
import dream.consult.consult.menu.form.McMenuDetailForm;
import dream.consult.consult.menu.service.McMenuDetailService;

/**
 * �޴� - �� action
 * 
 * @author kim2107
 * @version $Id: McMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/mcMenuDetail" name="mcMenuDetailForm"
 *                input="/dream/consult/consult/menu/mcMenuDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mcMenuDetail" path="/dream/consult/consult/menu/mcMenuDetail.jsp"
 *                        redirect="false"
 */
public class McMenuDetailAction extends ConsultAuthAction
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
        McMenuDetailForm mcMenuDetailForm = (McMenuDetailForm) form;
        
        switch (mcMenuDetailForm.getStrutsAction())
        {
            case McMenuDetailAction.MENU_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, mcMenuDetailForm);
                returnActionForward = mapping.findForward("mcMenuDetail");
                break;
            case McMenuDetailAction.MENU_DETAIL_INPUT:
            	insertDetail(mcMenuDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case McMenuDetailAction.MENU_DETAIL_UPDATE:
            	updateDetail(mcMenuDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mcMenuDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: McMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mcMenuDetailForm
     */
    private void findDetail(HttpServletRequest request, McMenuDetailForm mcMenuDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	McMenuDetailService mcMenuDetailService = (McMenuDetailService)getBean("mcMenuDetailService");

        // �Ѱ��� �޴�Id ����
        String menuId = mcMenuDetailForm.getMcMenuCommonDTO().getMenuId();
        
        // ��ȸ�� �� �κ�
        McMenuDetailDTO mcMenuDetailDTO = mcMenuDetailService.findDetail(menuId, getUser(request).getLangId());
        
        // ��ȸ�� ��  �����Ѵ�.
        mcMenuDetailForm.setMcMenuDetailDTO(mcMenuDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: McMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcMenuDetailForm
     * @param request
     */
    private void insertDetail(McMenuDetailForm mcMenuDetailForm, HttpServletRequest request) throws Exception
    {
        McMenuDetailService mcMenuDetailService = (McMenuDetailService) getBean("mcMenuDetailService");
        
        McMenuDetailDTO mcMenuDetailDTO = mcMenuDetailForm.getMcMenuDetailDTO();
        
        mcMenuDetailDTO.setEnterBy(getUser(request).getUserId());
        
        mcMenuDetailService.insertDetail(mcMenuDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: McMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcMenuDetailForm
     * @param request
     */
    private void updateDetail(McMenuDetailForm mcMenuDetailForm, HttpServletRequest request) throws Exception
    {
    	McMenuDetailService mcMenuDetailService = (McMenuDetailService) getBean("mcMenuDetailService");
        
        McMenuDetailDTO mcMenuDetailDTO = mcMenuDetailForm.getMcMenuDetailDTO();
        
        mcMenuDetailDTO.setEnterBy(getUser(request).getUserId());
        
        mcMenuDetailService.updateDetail(mcMenuDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}