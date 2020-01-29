package dream.pers.appln.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrDetailDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;
import dream.pers.appln.form.MaAppLineUsrDetailForm;
import dream.pers.appln.service.MaAppLineUsrDetailService;

/**
 * ��
 * @author  kim21017
 * @version $Id: MaAppLineUsrDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maAppLineUsrDetail" name="maAppLineUsrDetailForm"
 *                input="/dream/pers/appln/maAppLineUsrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maAppLineUsrPopupDetail" name="maAppLineUsrDetailForm"
 *                input="/dream/pers/appln/maAppLineUsrPopupDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maAppLineUsrDetail" path="/dream/pers/appln/maAppLineUsrDetail.jsp"
 *                        redirect="false"
 */
public class MaAppLineUsrDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int QNA_ANS_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int QNA_ANS_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int QNA_ANS_DETAIL_INPUT 		= 5003;
    /** �ߺ����� */
    public static final int QNA_ANS_DETAIL_SEQ 			= 1004;
    
    /** apprseq*/
    public static  final int APPR_SEQ                   = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaAppLineUsrDetailForm maAppLineUsrDetailForm = (MaAppLineUsrDetailForm) form;
        
        super.updateAudit(maAppLineUsrDetailForm.getMaAppLineUsrDetailDTO().getAuditKey()==""?maAppLineUsrDetailForm.getMaAppLineUsrListDTO().getAuditKey():maAppLineUsrDetailForm.getMaAppLineUsrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maAppLineUsrDetailForm.getStrutsAction())
        {
            case MaAppLineUsrDetailAction.QNA_ANS_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maAppLineUsrDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaAppLineUsrDetailAction.QNA_ANS_DETAIL_UPDATE:
            	updateDetail(maAppLineUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaAppLineUsrDetailAction.QNA_ANS_DETAIL_INPUT:
            	insertDetail(maAppLineUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaAppLineUsrDetailAction.QNA_ANS_DETAIL_SEQ:
            	checkAppSeq(maAppLineUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            
            case MaAppLineUsrDetailAction.APPR_SEQ: 
                appSeq(maAppLineUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * appr seq
     * @author  kim21017
     * @version $Id: MaAppLineUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailForm
     * @param request
     */
    private void appSeq(MaAppLineUsrDetailForm maAppLineUsrDetailForm, HttpServletRequest request) throws Exception
    {
        MaAppLineUsrDetailService maAppLineUsrDetailService = (MaAppLineUsrDetailService) getBean("maAppLineUsrDetailService");
        
        MaAppLineUsrDetailDTO maAppLineUsrDetailDTO = maAppLineUsrDetailForm.getMaAppLineUsrDetailDTO();
        
        MaAppLineCommonDTO maAppLineCommonDTO = maAppLineUsrDetailForm.getMaAppLineCommonDTO();
        maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
        String resultStr = maAppLineUsrDetailService.appSeq(maAppLineUsrDetailDTO, maAppLineCommonDTO);
        setAjaxDesc(request, resultStr);
    }
    
    
   
    /**
     * �亯 - �� ��ȸ
     * @author kim2107
     * @version $Id: MaAppLineUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maAppLineUsrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaAppLineUsrDetailForm maAppLineUsrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaAppLineUsrDetailService maAppLineUsrDetailService = (MaAppLineUsrDetailService)getBean("maAppLineUsrDetailService");
    	MaAppLineCommonDTO maAppLineCommonDTO = maAppLineUsrDetailForm.getMaAppLineCommonDTO();
    	MaAppLineUsrListDTO maAppLineUsrListDTO = maAppLineUsrDetailForm.getMaAppLineUsrListDTO();
    	maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
    	maAppLineCommonDTO.setUserLang(getUser(request).getLangId());
        // ��ȸ�� �� �κ�
        MaAppLineUsrDetailDTO maAppLineUsrDetailDTO = maAppLineUsrDetailService.findDetail(maAppLineUsrListDTO,maAppLineCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        maAppLineUsrDetailForm.setMaAppLineUsrDetailDTO(maAppLineUsrDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaAppLineUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailForm
     * @param request
     */
    private void updateDetail(MaAppLineUsrDetailForm maAppLineUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineUsrDetailService maAppLineUsrDetailService = (MaAppLineUsrDetailService) getBean("maAppLineUsrDetailService");
        
        MaAppLineUsrDetailDTO maAppLineUsrDetailDTO = maAppLineUsrDetailForm.getMaAppLineUsrDetailDTO();
        MaAppLineCommonDTO maAppLineCommonDTO = maAppLineUsrDetailForm.getMaAppLineCommonDTO();
    	maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maAppLineUsrDetailService.updateDetail(maAppLineUsrDetailDTO,maAppLineCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaAppLineUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailForm
     * @param request
     */
    private void insertDetail(MaAppLineUsrDetailForm maAppLineUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineUsrDetailService maAppLineUsrDetailService = (MaAppLineUsrDetailService) getBean("maAppLineUsrDetailService");
        
        MaAppLineUsrDetailDTO maAppLineUsrDetailDTO = maAppLineUsrDetailForm.getMaAppLineUsrDetailDTO();
        
        MaAppLineCommonDTO maAppLineCommonDTO = maAppLineUsrDetailForm.getMaAppLineCommonDTO();
    	maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maAppLineUsrDetailService.insertDetail(maAppLineUsrDetailDTO, maAppLineCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * check seq
     * @author  kim21017
     * @version $Id: MaAppLineUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailForm
     * @param request
     */
    private void checkAppSeq(MaAppLineUsrDetailForm maAppLineUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaAppLineUsrDetailService maAppLineUsrDetailService = (MaAppLineUsrDetailService) getBean("maAppLineUsrDetailService");
        
        MaAppLineUsrDetailDTO maAppLineUsrDetailDTO = maAppLineUsrDetailForm.getMaAppLineUsrDetailDTO();
        
        MaAppLineCommonDTO maAppLineCommonDTO = maAppLineUsrDetailForm.getMaAppLineCommonDTO();
    	maAppLineCommonDTO.setCompNo(getUser(request).getCompNo());
    	String resultStr = maAppLineUsrDetailService.checkAppSeq(maAppLineUsrDetailDTO, maAppLineCommonDTO);
        
    	setAjaxDesc(request, resultStr);
    }
}