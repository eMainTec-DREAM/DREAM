package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.form.MaWoResultPartDetailForm;
import dream.work.list.service.MaWoResultPartDetailService;

/**
 * �۾���� - ��������
 * @author  kim21017
 * @version $Id: MaWoResultPartDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultPartDetail" name="maWoResultPartDetailForm"
 *                input="/dream/work/list/maWoResultPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultMonthPartDetail" name="maWoResultPartDetailForm"
 *                input="/dream/work/list/month/maWoResultMonthPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRplPartDetail" name="maWoResultPartDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmRplPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplPartDetail" name="maWoResultPartDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRplPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilPartDetail" name="maWoResultPartDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmOilPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRplPartDetail" name="maWoResultPartDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmRplPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultPartDetail" path="/dream/work/list/maWoResultPartDetail.jsp"
 *                        redirect="false"
 */
public class MaWoResultPartDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_RESULT_PART_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int WO_RESULT_PART_DETAIL_UPDATE 	= 6002;
    /** �Է� */
    public static final int WO_RESULT_PART_DETAIL_INPUT 	= 5003;
    /** ���Ȯ�� */
    public static final int WO_RESULT_PART_STOCK_CHECK		= 8004;
    /** ����Ȯ�� */
    public static final int WO_RESULT_PART_QTY_CHECK		= 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultPartDetailForm maWoResultPartDetailForm = (MaWoResultPartDetailForm) form;
        
        super.updateAudit(maWoResultPartDetailForm.getMaWoResultPartDetailDTO().getAuditKey()==""?maWoResultPartDetailForm.getMaWoResultPartListDTO().getAuditKey():maWoResultPartDetailForm.getMaWoResultPartDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maWoResultPartDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (maWoResultPartDetailForm.getStrutsAction())
        {
            case MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWoResultPartDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_UPDATE:
            	updateDetail(maWoResultPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_INPUT:
            	insertDetail(maWoResultPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultPartDetailAction.WO_RESULT_PART_STOCK_CHECK:
            	getStockQty(maWoResultPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWoResultPartDetailAction.WO_RESULT_PART_QTY_CHECK:
                checkQty(maWoResultPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾����-�������� �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoResultPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultPartDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWoResultPartDetailForm maWoResultPartDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWoResultPartDetailService maWoResultPartDetailService = (MaWoResultPartDetailService)getBean("maWoResultPartDetailService");

    	// �۾����Id ����
        String wkOrId = maWoResultPartDetailForm.getMaWoResultMstrCommonDTO().getWkOrId();
        // �Ѱ��� ��������id ����
        String woPartId = maWoResultPartDetailForm.getMaWoResultPartListDTO().getWoPartId();
        
        // ��ȸ�� �� �κ�
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartDetailService.findDetail(wkOrId, woPartId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maWoResultPartDetailForm.setMaWoResultPartDetailDTO(maWoResultPartDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoResultPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailForm
     * @param request
     */
    private void updateDetail(MaWoResultPartDetailForm maWoResultPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultPartDetailService maWoResultPartDetailService = (MaWoResultPartDetailService) getBean("maWoResultPartDetailService");
        
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartDetailForm.getMaWoResultPartDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPartDetailForm.getMaWoResultMstrCommonDTO();
//        maWoResultPartDetailDTO.setEnterBy(getUser(request).getUserId());
//        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultPartDetailService.updateDetail(maWoResultPartDetailDTO,maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaWoResultPartDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailForm
     * @param request
     */
    private void insertDetail(MaWoResultPartDetailForm maWoResultPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultPartDetailService maWoResultPartDetailService = (MaWoResultPartDetailService) getBean("maWoResultPartDetailService");
        
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartDetailForm.getMaWoResultPartDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultPartDetailForm.getMaWoResultMstrCommonDTO();
        maWoResultPartDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maWoResultPartDetailService.insertDetail(maWoResultPartDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * ���Ȯ��
     */
    private void getStockQty(MaWoResultPartDetailForm maWoResultPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWoResultPartDetailService maWoResultPartDetailService = (MaWoResultPartDetailService) getBean("maWoResultPartDetailService");
        
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartDetailForm.getMaWoResultPartDetailDTO();
        String isValid = maWoResultPartDetailService.getStockQty(maWoResultPartDetailDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
    /**
     * ����Ȯ��
     */
    private void checkQty(MaWoResultPartDetailForm maWoResultPartDetailForm, HttpServletRequest request) throws Exception
    {
        MaWoResultPartDetailService maWoResultPartDetailService = (MaWoResultPartDetailService) getBean("maWoResultPartDetailService");
        
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = maWoResultPartDetailForm.getMaWoResultPartDetailDTO();
        String isValid = maWoResultPartDetailService.checkQty(maWoResultPartDetailDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}