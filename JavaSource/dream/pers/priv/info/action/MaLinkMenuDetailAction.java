package dream.pers.priv.info.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.priv.info.dto.MaLinkMenuDetailDTO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.form.MaLinkMenuDetailForm;
import dream.pers.priv.info.service.MaLinkMenuDetailService;


/**
 * ��
 * @author  jung7126
 * @version $Id: MaLinkMenuDetailAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maLinkMenuDetail" name="maLinkMenuDetailForm"
 *                input="/dream/pers/priv/info/maLinkMenuDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLinkMenuDetail" path="/dream/pers/priv/info/maLinkMenuDetail.jsp"
 *                        redirect="false"
 */
public class MaLinkMenuDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int LINK_MENU_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int LINK_MENU_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int LINK_MENU_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaLinkMenuDetailForm maLinkMenuDetailForm = (MaLinkMenuDetailForm) form;
        switch (maLinkMenuDetailForm.getStrutsAction())
        {
            case MaLinkMenuDetailAction.LINK_MENU_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maLinkMenuDetailForm);
                returnActionForward = mapping.findForward("maLinkMenuDetail");
                break;
            case MaLinkMenuDetailAction.LINK_MENU_DETAIL_UPDATE:
            	updateDetail(maLinkMenuDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLinkMenuDetailAction.LINK_MENU_DETAIL_INPUT:
            	insertDetail(maLinkMenuDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maLinkMenuDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȭ�麰 ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaLinkMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLinkMenuDetailForm
     */
    private void findDetail(HttpServletRequest request, MaLinkMenuDetailForm maLinkMenuDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaLinkMenuDetailService maLinkMenuDetailService = (MaLinkMenuDetailService)getBean("maLinkMenuDetailService");

    	// �Ѱ��� pageId ����
    	MaMyInfoCommonDTO maMyInfoCommonDTO = maLinkMenuDetailForm.getMaMyInfoCommonDTO();
        
        // ��ȸ�� �� �κ�
        MaLinkMenuDetailDTO maLinkMenuDetailDTO = maLinkMenuDetailService.findDetail(maMyInfoCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maLinkMenuDetailForm.setMaLinkMenuDetailDTO(maLinkMenuDetailDTO);
    }
    /**
     * detail update
     * @author  jung7126
     * @version $Id: MaLinkMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailForm
     * @param request
     */
    private void updateDetail(MaLinkMenuDetailForm maLinkMenuDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLinkMenuDetailService maLinkMenuDetailService = (MaLinkMenuDetailService) getBean("maLinkMenuDetailService");
        
        MaLinkMenuDetailDTO maLinkMenuDetailDTO = maLinkMenuDetailForm.getMaLinkMenuDetailDTO();
        MaMyInfoCommonDTO maMyInfoCommonDTO = maLinkMenuDetailForm.getMaMyInfoCommonDTO();
        
        maLinkMenuDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maLinkMenuDetailService.updateDetail(maLinkMenuDetailDTO,maMyInfoCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  jung7126
     * @version $Id: MaLinkMenuDetailAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailForm
     * @param request
     */
    private void insertDetail(MaLinkMenuDetailForm maLinkMenuDetailForm, HttpServletRequest request) throws Exception
    {
    	MaLinkMenuDetailService maLinkMenuDetailService = (MaLinkMenuDetailService) getBean("maLinkMenuDetailService");
        
        MaLinkMenuDetailDTO maLinkMenuDetailDTO = maLinkMenuDetailForm.getMaLinkMenuDetailDTO();
        
        MaMyInfoCommonDTO maMyInfoCommonDTO = maLinkMenuDetailForm.getMaMyInfoCommonDTO();
        maLinkMenuDetailDTO.setEnterBy(getUser(request).getUserId());
        maLinkMenuDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maLinkMenuDetailService.insertDetail(maLinkMenuDetailDTO, maMyInfoCommonDTO);
        
        setAjaxStatus(request);
    }
}