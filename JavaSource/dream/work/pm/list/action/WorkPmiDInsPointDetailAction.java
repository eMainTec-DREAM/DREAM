package dream.work.pm.list.action;

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
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsPointDetailDTO;
import dream.work.pm.list.form.WorkPmiDInsPointDetailForm;
import dream.work.pm.list.service.WorkPmiDInsPointDetailService;

/**
 * WorkPmiDInsPoint Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiDInsPointDetail" name="workPmiDInsPointDetailForm"
 *                input="/dream/work/pmi/list/workPmiDInsPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiDInsPointDetail" path="/dream/work/pmi/list/workPmiDInsPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmiDInsPointDetailAction extends AuthAction
{
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT                = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 6003;
    /** eqAsmbId 가져오기 */
    public static final int GET_ID                      = 8004;
    /** Slide Image 조회 */
    public static final int DETAIL_PHOTO        		= 8002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiDInsPointDetailForm workPmiDInsPointDetailForm = (WorkPmiDInsPointDetailForm) form;

        super.updateAudit(workPmiDInsPointDetailForm.getWorkPmiDInsPointDetailDTO().getAuditKey()==""?workPmiDInsPointDetailForm.getWorkPmiDInsCommonDTO().getAuditKey():workPmiDInsPointDetailForm.getWorkPmiDInsPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (workPmiDInsPointDetailForm.getStrutsAction())
        {
            case WorkPmiDInsPointDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmiDInsPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmiDInsPointDetailAction.DETAIL_INPUT:
                insertDetail(request, response, workPmiDInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiDInsPointDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, workPmiDInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiDInsPointDetailAction.GET_ID:
                getId(request, response, workPmiDInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiDInsPointDetailAction.DETAIL_PHOTO:
                findSlideImage(request, response, workPmiDInsPointDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmiDInsPointDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointDetailForm workPmiDInsPointDetailForm) throws Exception 
    {   
        WorkPmiDInsPointDetailService workPmiDInsPointDetailService = (WorkPmiDInsPointDetailService)getBean("workPmiDInsPointDetailService");
        
        WorkPmiDInsCommonDTO workPmiDInsCommonDTO = workPmiDInsPointDetailForm.getWorkPmiDInsCommonDTO(); 

        User user = getUser(request);
        WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO = workPmiDInsPointDetailService.findDetail(workPmiDInsCommonDTO, user);
        workPmiDInsPointDetailForm.setWorkPmiDInsPointDetailDTO(workPmiDInsPointDetailDTO);
        
        request.setAttribute("slideFileList", workPmiDInsPointDetailDTO.getSlideFileList());
        
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmiDInsPointDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointDetailForm workPmiDInsPointDetailForm) throws Exception
    {
        WorkPmiDInsPointDetailService workPmiDInsPointDetailService = (WorkPmiDInsPointDetailService)getBean("workPmiDInsPointDetailService");
        WorkPmiDInsPointDetailDTO  workPmiDInsPointDetailDTO = workPmiDInsPointDetailForm.getWorkPmiDInsPointDetailDTO(); 
        
        User user = getUser(request);
        workPmiDInsPointDetailService.insertDetail(workPmiDInsPointDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmiDInsPointDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointDetailForm workPmiDInsPointDetailForm) throws Exception
    {
        WorkPmiDInsPointDetailService workPmiDInsPointDetailService = (WorkPmiDInsPointDetailService)getBean("workPmiDInsPointDetailService");
        WorkPmiDInsPointDetailDTO  workPmiDInsPointDetailDTO = workPmiDInsPointDetailForm.getWorkPmiDInsPointDetailDTO();
        
        User user = getUser(request);
        workPmiDInsPointDetailService.updateDetail(workPmiDInsPointDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * GET ID
     * @param request
     * @param response
     * @param workPmiDInsPointDetailForm
     * @throws Exception
     */
    private void getId(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointDetailForm workPmiDInsPointDetailForm) throws Exception
    {
        WorkPmiDInsPointDetailService workPmiDInsPointDetailService = (WorkPmiDInsPointDetailService)getBean("workPmiDInsPointDetailService");
        WorkPmiDInsPointDetailDTO  workPmiDInsPointDetailDTO = workPmiDInsPointDetailForm.getWorkPmiDInsPointDetailDTO();
        
        User user = getUser(request);
        workPmiDInsPointDetailService.getId(workPmiDInsPointDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * 점검 슬라이드 이미지
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmiDInsPointDetailForm
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    private void findSlideImage(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointDetailForm workPmiDInsPointDetailForm) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service 객체 생성
    	WorkPmiDInsPointDetailService workPmiDInsPointDetailService = (WorkPmiDInsPointDetailService)getBean("workPmiDInsPointDetailService");
        
        // 조회된 상세 부분
        List list = workPmiDInsPointDetailService.findSlideImage(workPmiDInsPointDetailForm.getWorkPmiDInsPointDetailDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }
}
