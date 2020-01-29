package dream.consult.program.uploaddata.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataColDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.form.ConsultProgramUploadDataColForm;
import dream.consult.program.uploaddata.service.ConsultProgramUploadDataColService;

/**
 * 컬럼 - List Action
 * 
 * @author youngjoo38
 * @version $Id: ConsultProgramUploadDataColAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/consultProgramUploadDataColList" name="consultProgramUploadDataColForm"
 *                input="/dream/consult/program/uploaddata/consultProgramUploadDataColList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultProgramUploadDataColDetail" name="consultProgramUploadDataColForm"
 *                input="/dream/consult/program/uploaddata/consultProgramUploadDataColDetail.jsp" scope="request"
 *                validate="false"
 */
public class ConsultProgramUploadDataColAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 1002;
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT     = 1003;
    /** 저장 */
    public static final int DETAIL_INPUT    = 1004;
    /** 수정 */
    public static final int DETAIL_UPDATE   = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultProgramUploadDataColForm consultProgramUploadDataColForm = (ConsultProgramUploadDataColForm) form;
        
        switch (consultProgramUploadDataColForm.getStrutsAction())
        {
            case ConsultProgramUploadDataColAction.BASE_SET_HEADER:
                setHeader(request, response, consultProgramUploadDataColForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultProgramUploadDataColAction.LIST_FIND:
                findList(request, response, consultProgramUploadDataColForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultProgramUploadDataColAction.LIST_DELETE:
                deleteList(request, consultProgramUploadDataColForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultProgramUploadDataColAction.BASE_GRID_EXPORT:
                findList(request, response, consultProgramUploadDataColForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case ConsultProgramUploadDataColAction.DETAIL_INIT:
                this.findDetail(request, response, consultProgramUploadDataColForm);
                returnActionForward = mapping.getInputForward();
                break;
            case ConsultProgramUploadDataColAction.DETAIL_INPUT:
                insertDetail(request, response, consultProgramUploadDataColForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramUploadDataColAction.DETAIL_UPDATE:
                updateDetail(request, response, consultProgramUploadDataColForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataColForm consultProgramUploadDataColForm) throws IOException
    {
        super.setHeader(request, response, consultProgramUploadDataColForm.getListId(), consultProgramUploadDataColForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultProgramUploadDataColForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataColForm consultProgramUploadDataColForm, boolean excelExport) throws Exception
    {
        ConsultProgramUploadDataColService consultProgramUploadDataColService = (ConsultProgramUploadDataColService) getBean("consultProgramUploadDataColService");
        ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO = consultProgramUploadDataColForm.getConsultProgramUploadDataColDTO();
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataColForm.getConsultProgramUploadDataDTO();
      
        //Paging
        consultProgramUploadDataColDTO.setIsLoadMaxCount("Y".equals(consultProgramUploadDataColForm.getIsLoadMaxCount())?true:false);
        consultProgramUploadDataColDTO.setFirstRow(consultProgramUploadDataColForm.getFirstRow());
        consultProgramUploadDataColDTO.setOrderBy(consultProgramUploadDataColForm.getOrderBy());
        consultProgramUploadDataColDTO.setDirection(consultProgramUploadDataColForm.getDirection());
        
        User user = getUser(request);
        List resultList = consultProgramUploadDataColService.findList(consultProgramUploadDataDTO, consultProgramUploadDataColDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultProgramUploadDataColForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultProgramUploadDataColService.findTotalCount(consultProgramUploadDataDTO,consultProgramUploadDataColDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultProgramUploadDataColForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultProgramUploadDataColForm
     */
    private void deleteList(HttpServletRequest request, ConsultProgramUploadDataColForm consultProgramUploadDataColForm) throws Exception
    {
        ConsultProgramUploadDataColService consultProgramUploadDataColService = (ConsultProgramUploadDataColService) getBean("consultProgramUploadDataColService");
        String[] deleteRows = consultProgramUploadDataColForm.getDeleteRows();
        
        User user = getUser(request);
        consultProgramUploadDataColService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataColForm consultProgramUploadDataColForm) throws Exception 
    {   
        ConsultProgramUploadDataColService consultProgramUploadDataColService = (ConsultProgramUploadDataColService)getBean("consultProgramUploadDataColService");
        
        ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO = consultProgramUploadDataColForm.getConsultProgramUploadDataColDTO();
        consultProgramUploadDataColDTO = consultProgramUploadDataColService.findDetail(consultProgramUploadDataColDTO, getUser(request));
        consultProgramUploadDataColForm.setConsultProgramUploadDataColDTO(consultProgramUploadDataColDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultProgramUploadDataColForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataColForm consultProgramUploadDataColForm) throws Exception
    {
        ConsultProgramUploadDataColService consultProgramUploadDataColService = (ConsultProgramUploadDataColService)getBean("consultProgramUploadDataColService");
        
        ConsultProgramUploadDataColDTO  consultProgramUploadDataColDTO = consultProgramUploadDataColForm.getConsultProgramUploadDataColDTO(); 
        consultProgramUploadDataColService.insertDetail(consultProgramUploadDataColDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultProgramUploadDataColForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataColForm consultProgramUploadDataColForm) throws Exception
    {
        ConsultProgramUploadDataColService consultProgramUploadDataColService = (ConsultProgramUploadDataColService)getBean("consultProgramUploadDataColService");
        
        ConsultProgramUploadDataColDTO  consultProgramUploadDataColDTO = consultProgramUploadDataColForm.getConsultProgramUploadDataColDTO();
        consultProgramUploadDataColService.updateDetail(consultProgramUploadDataColDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}