package dream.work.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.WorkListBmRplPartSerialDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.WorkListBmRplPartSerialDetailForm;
import dream.work.list.service.WorkListBmRplPartSerialDetailService;

/**
 * �۾���� - �۾���
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListBmRplPartSerialDetail" name="workListBmRplPartSerialDetailForm"
 *                input="/dream/work/list/bm/rpl/workListBmRplPartSerialDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListCmRplPartSerialDetail" name="workListBmRplPartSerialDetailForm"
 *                input="/dream/work/list/cm/rpl/workListCmRplPartSerialDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListPmRplPartSerialDetail" name="workListBmRplPartSerialDetailForm"
 *                input="/dream/work/list/pmw/rpl/workListPmRplPartSerialDetail.jsp" scope="request"
 *                validate="false"
 */
public class WorkListBmRplPartSerialDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WO_RESULT_PTSERIAL_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int WO_RESULT_PTSERIAL_DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int WO_RESULT_PTSERIAL_DETAIL_INPUT 	= 1003;
    /** ���� WO �ߺ� Serial �˻� */
    public static final int WO_RESULT_PTSERIAL_DETAIL_CHECK		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListBmRplPartSerialDetailForm workListBmRplPartSerialDetailForm = (WorkListBmRplPartSerialDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListBmRplPartSerialDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (workListBmRplPartSerialDetailForm.getStrutsAction())
        {
            case WorkListBmRplPartSerialDetailAction.WO_RESULT_PTSERIAL_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workListBmRplPartSerialDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListBmRplPartSerialDetailAction.WO_RESULT_PTSERIAL_DETAIL_UPDATE:
            	updateDetail(workListBmRplPartSerialDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListBmRplPartSerialDetailAction.WO_RESULT_PTSERIAL_DETAIL_INPUT:
            	insertDetail(workListBmRplPartSerialDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListBmRplPartSerialDetailAction.WO_RESULT_PTSERIAL_DETAIL_CHECK:
            	validEmp(workListBmRplPartSerialDetailForm, request);
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
     * @version $Id: WorkListBmRplPartSerialDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListBmRplPartSerialDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListBmRplPartSerialDetailForm workListBmRplPartSerialDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkListBmRplPartSerialDetailService workListBmRplPartSerialDetailService = (WorkListBmRplPartSerialDetailService)getBean("workListBmRplPartSerialDetailService");

        // ��ȸ�� �� �κ�
        WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO = workListBmRplPartSerialDetailService.findDetail(workListBmRplPartSerialDetailForm.getMaWoResultMstrCommonDTO(), workListBmRplPartSerialDetailForm.getWorkListBmRplPartSerialListDTO(), getUser(request).getCompNo());
        
        // ��ȸ�� ��  �����Ѵ�.
        workListBmRplPartSerialDetailForm.setWorkListBmRplPartSerialDetailDTO(workListBmRplPartSerialDetailDTO);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkListBmRplPartSerialDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailForm
     * @param request
     */
    private void updateDetail(WorkListBmRplPartSerialDetailForm workListBmRplPartSerialDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListBmRplPartSerialDetailService workListBmRplPartSerialDetailService = (WorkListBmRplPartSerialDetailService) getBean("workListBmRplPartSerialDetailService");
        
        WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO = workListBmRplPartSerialDetailForm.getWorkListBmRplPartSerialDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListBmRplPartSerialDetailForm.getMaWoResultMstrCommonDTO();
        workListBmRplPartSerialDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workListBmRplPartSerialDetailService.updateDetail(workListBmRplPartSerialDetailDTO,maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: WorkListBmRplPartSerialDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListBmRplPartSerialDetailForm
     * @param request
     */
    private void insertDetail(WorkListBmRplPartSerialDetailForm workListBmRplPartSerialDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListBmRplPartSerialDetailService workListBmRplPartSerialDetailService = (WorkListBmRplPartSerialDetailService) getBean("workListBmRplPartSerialDetailService");
        
        WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO = workListBmRplPartSerialDetailForm.getWorkListBmRplPartSerialDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListBmRplPartSerialDetailForm.getMaWoResultMstrCommonDTO();
        workListBmRplPartSerialDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        workListBmRplPartSerialDetailDTO.setWopartId(workListBmRplPartSerialDetailForm.getMaWoResultPartDetailDTO().getWoPartId());
        
        workListBmRplPartSerialDetailService.insertDetail(workListBmRplPartSerialDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * Serial �ߺ� �˻�
     */
    private void validEmp(WorkListBmRplPartSerialDetailForm workListBmRplPartSerialDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListBmRplPartSerialDetailService workListBmRplPartSerialDetailService = (WorkListBmRplPartSerialDetailService) getBean("workListBmRplPartSerialDetailService");
        
        WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO = workListBmRplPartSerialDetailForm.getWorkListBmRplPartSerialDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListBmRplPartSerialDetailForm.getMaWoResultMstrCommonDTO();
        String isValid = workListBmRplPartSerialDetailService.validSerial(workListBmRplPartSerialDetailDTO,maWoResultMstrCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}