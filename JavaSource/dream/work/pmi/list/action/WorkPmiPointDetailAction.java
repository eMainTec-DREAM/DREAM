package dream.work.pmi.list.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;
import dream.work.pmi.list.form.WorkPmiPointDetailForm;
import dream.work.pmi.list.service.WorkPmiPointDetailService;

/**
 * �����۾� ����
 * @author  kim21017
 * @version $Id: WorkPmiPointDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workPmiPointDetail" name="workPmiPointDetailForm"
 *                input="/dream/work/pmi/list/workPmiPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiPointValueDetail" name="workPmiPointDetailForm"
 *                input="/dream/work/pmi/list/workPmiPointValueDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiPointDetail" path="/dream/work/pmi/list/workPmiPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmiPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_PMI_POINT_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int WORK_PMI_POINT_DETAIL_UPDATE 	= 6002;
    /** Slide Image ��ȸ */
    public static final int WORK_PMI_POINT_DETAIL_PHOTO     = 8002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiPointDetailForm workPmiPointDetailForm = (WorkPmiPointDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workPmiPointDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workPmiPointDetailForm.getWorkPmiPointDetailDTO().getAuditKey()==""?workPmiPointDetailForm.getWorkPmiPointListDTO().getAuditKey():workPmiPointDetailForm.getWorkPmiPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmiPointDetailForm.getStrutsAction())
        {
            case WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workPmiPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_UPDATE:
            	updateDetail(workPmiPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_PHOTO:
                findSlideImage(request, response, workPmiPointDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾����-�˻��׸� �� ��ȸ
     * @author kim2107
     * @version $Id: WorkPmiPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmiPointDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmiPointDetailForm workPmiPointDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkPmiPointDetailService workPmiPointDetailService = (WorkPmiPointDetailService)getBean("workPmiPointDetailService");

    	// �۾����Id ����
        String pminslistId = workPmiPointDetailForm.getWorkPmiCommonDTO().getPminslistId();
        // �Ѱ��� �����׸�id ����
        String pmInsPointId = workPmiPointDetailForm.getWorkPmiPointListDTO().getPmInsPointId();
        // �Ѱ��� �����׸�id ����
        String pmPointId = workPmiPointDetailForm.getWorkPmiPointListDTO().getPmPointId();
        
        // ��ȸ�� �� �κ�
        WorkPmiPointDetailDTO workPmiPointDetailDTO = workPmiPointDetailService.findDetail(pminslistId, pmInsPointId, pmPointId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        workPmiPointDetailForm.setWorkPmiPointDetailDTO(workPmiPointDetailDTO);
        
        request.setAttribute("slideFileList", workPmiPointDetailDTO.getSlideFileList());
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkPmiPointDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiPointDetailForm
     * @param request
     */
    private void updateDetail(WorkPmiPointDetailForm workPmiPointDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmiPointDetailService workPmiPointDetailService = (WorkPmiPointDetailService) getBean("workPmiPointDetailService");
        
        WorkPmiPointDetailDTO workPmiPointDetailDTO = workPmiPointDetailForm.getWorkPmiPointDetailDTO();
        WorkPmiCommonDTO workPmiCommonDTO = workPmiPointDetailForm.getWorkPmiCommonDTO();
        
        User user = getUser(request);
        
        workPmiPointDetailService.updateDetail(workPmiPointDetailDTO,workPmiCommonDTO,user);
        
        setAjaxStatus(request);
    }
    
    /**
     * ���� �����̵� �̹���
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmiPointDetailForm
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    private void findSlideImage(HttpServletRequest request, HttpServletResponse response, WorkPmiPointDetailForm workPmiPointDetailForm) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service ��ü ����
    	WorkPmiPointDetailService workPmiPointDetailService = (WorkPmiPointDetailService) getBean("workPmiPointDetailService");
        
        // ��ȸ�� �� �κ�
        List list = workPmiPointDetailService.findSlideImage(workPmiPointDetailForm.getWorkPmiPointDetailDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }
    
}