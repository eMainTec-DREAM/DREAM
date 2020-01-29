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
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataScriptDTO;
import dream.consult.program.uploaddata.form.ConsultProgramUploadDataScriptForm;
import dream.consult.program.uploaddata.service.ConsultProgramUploadDataScriptService;

/**
 * 엑셀 참조데이타 - Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultProgramUploadDataScriptList" name="consultProgramUploadDataScriptForm"
 *                input="/dream/consult/program/uploaddata/consultProgramUploadDataScriptList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultProgramUploadDataScriptDetail" name="consultProgramUploadDataScriptForm"
 *                input="/dream/consult/program/uploaddata/consultProgramUploadDataScriptDetail.jsp" scope="request"
 *                validate="false"
 */
public class ConsultProgramUploadDataScriptAction extends ConsultAuthAction
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
        ConsultProgramUploadDataScriptForm consultProgramUploadDataScriptForm = (ConsultProgramUploadDataScriptForm) form;
        
        switch (consultProgramUploadDataScriptForm.getStrutsAction())
        {
            case ConsultProgramUploadDataScriptAction.BASE_SET_HEADER:
                setHeader(request, response, consultProgramUploadDataScriptForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultProgramUploadDataScriptAction.LIST_FIND:
                findList(request, response, consultProgramUploadDataScriptForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultProgramUploadDataScriptAction.LIST_DELETE:
                deleteList(request, consultProgramUploadDataScriptForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultProgramUploadDataScriptAction.BASE_GRID_EXPORT:
                findList(request, response, consultProgramUploadDataScriptForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case ConsultProgramUploadDataScriptAction.DETAIL_INIT:
                this.findDetail(request, response, consultProgramUploadDataScriptForm);
                returnActionForward = mapping.getInputForward();
                break;
            case ConsultProgramUploadDataScriptAction.DETAIL_INPUT:
                insertDetail(request, response, consultProgramUploadDataScriptForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultProgramUploadDataScriptAction.DETAIL_UPDATE:
                updateDetail(request, response, consultProgramUploadDataScriptForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataScriptForm consultProgramUploadDataScriptForm) throws IOException
    {
        super.setHeader(request, response, consultProgramUploadDataScriptForm.getListId(), consultProgramUploadDataScriptForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataScriptForm consultProgramUploadDataScriptForm, boolean excelExport) throws Exception
    {
        ConsultProgramUploadDataScriptService consultProgramUploadDataScriptService = (ConsultProgramUploadDataScriptService) getBean("consultProgramUploadDataScriptService");
        
        ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO = consultProgramUploadDataScriptForm.getConsultProgramUploadDataScriptDTO();
        ConsultProgramUploadDataDTO consultProgramUploadDataDTO = consultProgramUploadDataScriptForm.getConsultProgramUploadDataDTO();
      
        //Paging
        consultProgramUploadDataScriptDTO.setIsLoadMaxCount("Y".equals(consultProgramUploadDataScriptForm.getIsLoadMaxCount())?true:false);
        consultProgramUploadDataScriptDTO.setFirstRow(consultProgramUploadDataScriptForm.getFirstRow());
        consultProgramUploadDataScriptDTO.setOrderBy(consultProgramUploadDataScriptForm.getOrderBy());
        consultProgramUploadDataScriptDTO.setDirection(consultProgramUploadDataScriptForm.getDirection());
        
        User user = getUser(request);
        List resultList = consultProgramUploadDataScriptService.findList(consultProgramUploadDataDTO, consultProgramUploadDataScriptDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultProgramUploadDataScriptForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultProgramUploadDataScriptService.findTotalCount(consultProgramUploadDataDTO,consultProgramUploadDataScriptDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultProgramUploadDataScriptForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, ConsultProgramUploadDataScriptForm consultProgramUploadDataScriptForm) throws Exception
    {
        ConsultProgramUploadDataScriptService consultProgramUploadDataScriptService = (ConsultProgramUploadDataScriptService) getBean("consultProgramUploadDataScriptService");
        
        String[] deleteRows = consultProgramUploadDataScriptForm.getDeleteRows();
        User user = getUser(request);
        consultProgramUploadDataScriptService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataScriptForm consultProgramUploadDataScriptForm) throws Exception 
    {   
        ConsultProgramUploadDataScriptService consultProgramUploadDataScriptService = (ConsultProgramUploadDataScriptService)getBean("consultProgramUploadDataScriptService");
        
        ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO = consultProgramUploadDataScriptForm.getConsultProgramUploadDataScriptDTO();
        consultProgramUploadDataScriptDTO = consultProgramUploadDataScriptService.findDetail(consultProgramUploadDataScriptDTO, getUser(request));
        consultProgramUploadDataScriptForm.setConsultProgramUploadDataScriptDTO(consultProgramUploadDataScriptDTO);
    }
    
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataScriptForm consultProgramUploadDataScriptForm) throws Exception
    {
        ConsultProgramUploadDataScriptService consultProgramUploadDataScriptService = (ConsultProgramUploadDataScriptService)getBean("consultProgramUploadDataScriptService");
        
        ConsultProgramUploadDataScriptDTO  consultProgramUploadDataScriptDTO = consultProgramUploadDataScriptForm.getConsultProgramUploadDataScriptDTO(); 
        consultProgramUploadDataScriptService.insertDetail(consultProgramUploadDataScriptDTO, getUser(request));
        setAjaxStatus(request);
    }
    
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultProgramUploadDataScriptForm consultProgramUploadDataScriptForm) throws Exception
    {
        ConsultProgramUploadDataScriptService consultProgramUploadDataScriptService = (ConsultProgramUploadDataScriptService)getBean("consultProgramUploadDataScriptService");
        
        ConsultProgramUploadDataScriptDTO  consultProgramUploadDataScriptDTO = consultProgramUploadDataScriptForm.getConsultProgramUploadDataScriptDTO();
        consultProgramUploadDataScriptService.updateDetail( consultProgramUploadDataScriptDTO, getUser(request));
        setAjaxStatus(request);
    }
}