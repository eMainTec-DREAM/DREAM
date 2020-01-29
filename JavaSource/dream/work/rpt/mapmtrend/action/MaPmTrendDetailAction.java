package dream.work.rpt.mapmtrend.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendDetailDTO;
import dream.work.rpt.mapmtrend.form.MaPmTrendDetailForm;
import dream.work.rpt.mapmtrend.service.MaPmTrendDetailService;

/**
 * ��
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPmTrendDetailChart" name="maPmTrendDetailForm"
 *                input="/dream/work/rpt/pmtrend/maPmTrendDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class MaPmTrendDetailAction extends AuthAction
{
    /** �������˼�ġ���� ��Ʈ ��ȸ */
    public static final int EMP_MTTR_DETAIL_FIND 	= 1001;
    /** ��ġ WO ���� */
    public static final int WO_CREATE               = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmTrendDetailForm maPmTrendDetailForm = (MaPmTrendDetailForm) form;
        switch (maPmTrendDetailForm.getStrutsAction())
        {
            case MaPmTrendDetailAction.EMP_MTTR_DETAIL_FIND:
                // ������ ��ȸ
                this.findDetail(request, response, maPmTrendDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmTrendDetailAction.WO_CREATE:
                createWo(request, maPmTrendDetailForm, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPmTrendDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, maPmTrendDetailForm.getListId(), maPmTrendDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmTrendDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, maPmTrendDetailForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ȸ 
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPmTrendDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, MaPmTrendDetailForm maPmTrendDetailForm) throws Exception
    {
        MaPmTrendDetailService maPmTrendDetailService = (MaPmTrendDetailService) getBean("maPmTrendDetailService");
        
        MaPmTrendDetailDTO maPmTrendDetailDTO = maPmTrendDetailForm.getMaPmTrendDetailDTO();
        MaPmTrendCommonDTO maPmTrendCommonDTO = maPmTrendDetailForm.getMaPmTrendCommonDTO();
                
        List resultList = maPmTrendDetailService.findDetail(maPmTrendCommonDTO, maPmTrendDetailDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, maPmTrendDetailForm.getListId());
    }
    
    private void createWo(HttpServletRequest request, MaPmTrendDetailForm maPmTrendDetailForm, HttpServletResponse response) throws Exception
    {
        MaPmTrendDetailService maPmTrendDetailService = (MaPmTrendDetailService) getBean("maPmTrendDetailService");
        MaPmTrendDetailDTO maPmTrendDetailDTO = maPmTrendDetailForm.getMaPmTrendDetailDTO();
        
        maPmTrendDetailDTO.setCompNo(getUser(request).getCompNo());
        maPmTrendDetailDTO.setUserLang(getUser(request).getLangId());

        maPmTrendDetailService.createWo(maPmTrendDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    
}