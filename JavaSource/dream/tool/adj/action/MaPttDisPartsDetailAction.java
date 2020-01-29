package dream.tool.adj.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsDetailDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;
import dream.tool.adj.form.MaPttDisPartsDetailForm;
import dream.tool.adj.service.MaPttDisPartsDetailService;

/**
 * ��
 * @author  kim21017
 * @version $Id: MaPttDisPartsDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPttDisPartsDetail" name="maPttDisPartsDetailForm"
 *                input="/dream/tool/adj/maPttDisPartsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttDisPartsDetail" path="/dream/tool/adj/maPttDisPartsDetail.jsp"
 *                        redirect="false"
 */
public class MaPttDisPartsDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int QNA_ANS_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int QNA_ANS_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int QNA_ANS_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttDisPartsDetailForm maPttDisPartsDetailForm = (MaPttDisPartsDetailForm) form;
        
        super.updateAudit(maPttDisPartsDetailForm.getMaPttDisPartsDetailDTO().getAuditKey()==""?maPttDisPartsDetailForm.getMaPttDisPartsListDTO().getAuditKey():maPttDisPartsDetailForm.getMaPttDisPartsDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPttDisPartsDetailForm.getStrutsAction())
        {
            case MaPttDisPartsDetailAction.QNA_ANS_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPttDisPartsDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPttDisPartsDetailAction.QNA_ANS_DETAIL_UPDATE:
            	updateDetail(maPttDisPartsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttDisPartsDetailAction.QNA_ANS_DETAIL_INPUT:
            	insertDetail(maPttDisPartsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �亯 - �� ��ȸ
     * @author kim2107
     * @version $Id: MaPttDisPartsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPttDisPartsDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPttDisPartsDetailForm maPttDisPartsDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPttDisPartsDetailService maPttDisPartsDetailService = (MaPttDisPartsDetailService)getBean("maPttDisPartsDetailService");
    	MaPttDisCommonDTO maPttDisCommonDTO = maPttDisPartsDetailForm.getMaPttDisCommonDTO();
    	MaPttDisPartsListDTO maPttDisPartsListDTO = maPttDisPartsDetailForm.getMaPttDisPartsListDTO();
    	maPttDisCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        MaPttDisPartsDetailDTO maPttDisPartsDetailDTO = maPttDisPartsDetailService.findDetail(maPttDisPartsListDTO,maPttDisCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        maPttDisPartsDetailForm.setMaPttDisPartsDetailDTO(maPttDisPartsDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPttDisPartsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailForm
     * @param request
     */
    private void updateDetail(MaPttDisPartsDetailForm maPttDisPartsDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttDisPartsDetailService maPttDisPartsDetailService = (MaPttDisPartsDetailService) getBean("maPttDisPartsDetailService");
        
        MaPttDisPartsDetailDTO maPttDisPartsDetailDTO = maPttDisPartsDetailForm.getMaPttDisPartsDetailDTO();
        MaPttDisCommonDTO maPttDisCommonDTO = maPttDisPartsDetailForm.getMaPttDisCommonDTO();
    	maPttDisCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPttDisPartsDetailService.updateDetail(maPttDisPartsDetailDTO,maPttDisCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPttDisPartsDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailForm
     * @param request
     */
    private void insertDetail(MaPttDisPartsDetailForm maPttDisPartsDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttDisPartsDetailService maPttDisPartsDetailService = (MaPttDisPartsDetailService) getBean("maPttDisPartsDetailService");
        
        MaPttDisPartsDetailDTO maPttDisPartsDetailDTO = maPttDisPartsDetailForm.getMaPttDisPartsDetailDTO();
        
        MaPttDisCommonDTO maPttDisCommonDTO = maPttDisPartsDetailForm.getMaPttDisCommonDTO();
    	maPttDisCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPttDisPartsDetailService.insertDetail(maPttDisPartsDetailDTO, maPttDisCommonDTO);
        
        setAjaxStatus(request);
    }

}