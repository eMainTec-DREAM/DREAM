package dream.mgr.usrrpt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptJoinDetailDTO;
import dream.mgr.usrrpt.form.MaUserRptJoinDetailForm;
import dream.mgr.usrrpt.service.MaUserRptJoinDetailService;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maUserRptJoinDetail" name="maUserRptJoinDetailForm"
 *                input="/dream/mgr/usrrpt/maUserRptJoinDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptJoinDetail" path="/dream/mgr/usrrpt/maUserRptJoinDetail.jsp"
 *                        redirect="false"
 */
public class MaUserRptJoinDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USER_JOIN_DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int USER_JOIN_DETAIL_UPDATE = 1002;
    /** �Է� */
    public static final int USER_JOIN_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptJoinDetailForm maUserRptJoinDetailForm = (MaUserRptJoinDetailForm) form;
        switch (maUserRptJoinDetailForm.getStrutsAction())
        {
            case MaUserRptJoinDetailAction.USER_JOIN_DETAIL_INIT:
                this.findDetail(request, maUserRptJoinDetailForm);
                returnActionForward = mapping.findForward("maUserRptJoinDetail");
                break;
            case MaUserRptJoinDetailAction.USER_JOIN_DETAIL_UPDATE:
            	updateDetail(maUserRptJoinDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptJoinDetailAction.USER_JOIN_DETAIL_INPUT:
            	insertDetail(maUserRptJoinDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maUserRptJoinDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     *  �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptJoinDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUserRptJoinDetailForm maUserRptJoinDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaUserRptJoinDetailService maUserRptJoinDetailService = (MaUserRptJoinDetailService)getBean("maUserRptJoinDetailService");

        // ��ȸ�� �� �κ�
        MaUserRptJoinDetailDTO maUserRptJoinDetailDTO = maUserRptJoinDetailService.findDetail(maUserRptJoinDetailForm.getMaUserRptCommonDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maUserRptJoinDetailForm.setMaUserRptJoinDetailDTO(maUserRptJoinDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailForm
     * @param request
     */
    private void updateDetail(MaUserRptJoinDetailForm maUserRptJoinDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptJoinDetailService maUserRptJoinDetailService = (MaUserRptJoinDetailService) getBean("maUserRptJoinDetailService");
        
        MaUserRptJoinDetailDTO maUserRptJoinDetailDTO = maUserRptJoinDetailForm.getMaUserRptJoinDetailDTO();
        
        maUserRptJoinDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUserRptJoinDetailService.updateDetail(maUserRptJoinDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailForm
     * @param request
     */
    private void insertDetail(MaUserRptJoinDetailForm maUserRptJoinDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptJoinDetailService maUserRptJoinDetailService = (MaUserRptJoinDetailService) getBean("maUserRptJoinDetailService");
        
        MaUserRptJoinDetailDTO maUserRptJoinDetailDTO = maUserRptJoinDetailForm.getMaUserRptJoinDetailDTO();
        
        maUserRptJoinDetailDTO.setCompNo((getUser(request).getCompNo()));
        maUserRptJoinDetailService.insertDetail(maUserRptJoinDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}