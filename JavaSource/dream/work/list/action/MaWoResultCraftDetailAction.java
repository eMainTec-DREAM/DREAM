package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.MaWoResultCraftDetailForm;
import dream.work.list.service.MaWoResultCraftDetailService;

/**
 * �۾���� - �۾���
 * @author  kim21017
 * @version $Id: MaWoResultCraftDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/maWoResultCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/month/maWoResultMonthCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmInsCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmi/maWoResultPmInsCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRprCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmRprCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRplCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmRplCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmClnCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmClnCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmOilCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmOilCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRprCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRprCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRplCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmOilCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRprCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmRprCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRplCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmRplCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultTrEleCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/maWoResultTrEleCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultGnlCaCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmc/maWoResultGnlCaCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultSclCaCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmc/maWoResultSclCaCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPrsCaCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/pmc/maWoResultPrsCaCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmLocBaseCraftDetail" name="maWoResultCraftDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmLocBaseCraftDetail.jsp" scope="request"
 *                validate="false"
 */
public class MaWoResultCraftDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_RESULT_CRAFT_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int WO_RESULT_CRAFT_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int WO_RESULT_CRAFT_DETAIL_INPUT 	= 5003;
    /** ���� WO �ߺ� EMP �˻� */
    public static final int WO_RESULT_CRAFT_DETAIL_CHECK	= 8004;
    /** ���� WO �ߺ� TIME �˻� */
    public static final int WO_RESULT_CRAFT_DETAIL_TIME_CHECK	= 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultCraftDetailForm maWoResultCraftDetailForm = (MaWoResultCraftDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultCraftDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(maWoResultCraftDetailForm.getMaWoResultCraftDetailDTO().getAuditKey()==""?maWoResultCraftDetailForm.getMaWoResultCraftListDTO().getAuditKey():maWoResultCraftDetailForm.getMaWoResultCraftDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoResultCraftDetailForm.getStrutsAction())
        {
            case MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoResultCraftDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_UPDATE:
            	updateDetail(maWoResultCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INPUT:
            	insertDetail(maWoResultCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_CHECK:
            	validEmp(maWoResultCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_TIME_CHECK:
            	validTime(maWoResultCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾����-�۾��� �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoResultCraftDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultCraftDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoResultCraftDetailForm maWoResultCraftDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoResultCraftDetailService maWoResultCraftDetailService = (MaWoResultCraftDetailService)getBean("maWoResultCraftDetailService");

    	// �۾����Id ����
        String wkOrId = maWoResultCraftDetailForm.getMaWoResultMstrCommonDTO().getWkOrId();
        // �Ѱ��� �۾���id ����
        String woCraftId = maWoResultCraftDetailForm.getMaWoResultCraftListDTO().getWoCraftId();
        
        // ��ȸ�� �� �κ�
        MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = maWoResultCraftDetailService.findDetail(wkOrId, woCraftId, getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoResultCraftDetailForm.setMaWoResultCraftDetailDTO(maWoResultCraftDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoResultCraftDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailForm
     * @param request
     */
    private void updateDetail(MaWoResultCraftDetailForm maWoResultCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultCraftDetailService maWoResultCraftDetailService = (MaWoResultCraftDetailService) getBean("maWoResultCraftDetailService");
        
        MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = maWoResultCraftDetailForm.getMaWoResultCraftDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultCraftDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultCraftDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultCraftDetailService.updateDetail(maWoResultCraftDetailDTO,maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoResultCraftDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultCraftDetailForm
     * @param request
     */
    private void insertDetail(MaWoResultCraftDetailForm maWoResultCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultCraftDetailService maWoResultCraftDetailService = (MaWoResultCraftDetailService) getBean("maWoResultCraftDetailService");
        
        MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = maWoResultCraftDetailForm.getMaWoResultCraftDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultCraftDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultCraftDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultCraftDetailService.insertDetail(maWoResultCraftDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * ��� �ߺ� �˻�
     */
    private void validEmp(MaWoResultCraftDetailForm maWoResultCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultCraftDetailService maWoResultCraftDetailService = (MaWoResultCraftDetailService) getBean("maWoResultCraftDetailService");
        
        MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = maWoResultCraftDetailForm.getMaWoResultCraftDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultCraftDetailForm.getMaWoResultMstrCommonDTO();
        String isValid = maWoResultCraftDetailService.validEmp(maWoResultCraftDetailDTO,maWoResultMstrCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
    /**
     * �۾��ð� �ߺ� �˻�
     */
    private void validTime(MaWoResultCraftDetailForm maWoResultCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultCraftDetailService maWoResultCraftDetailService = (MaWoResultCraftDetailService) getBean("maWoResultCraftDetailService");
        
        MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = maWoResultCraftDetailForm.getMaWoResultCraftDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultCraftDetailForm.getMaWoResultMstrCommonDTO();
        
        String isValid = maWoResultCraftDetailService.validTime(maWoResultCraftDetailDTO,maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
}