package dream.consult.program.setting.upload.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdDTO;
import dream.consult.program.setting.upload.form.ConsultPgmSettingUpdForm;
import dream.consult.program.setting.upload.service.ConsultPgmSettingUpdService;

/**
 * Excel Data Upload - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultPgmSettingUpdList" name="consultPgmSettingUpdForm"
 *                input="/dream/consult/program/setting/upload/consultPgmSettingUpdList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/consultPgmSettingUpdDetail" name="consultPgmSettingUpdForm"
 *                input="/dream/consult/program/setting/upload/consultPgmSettingUpdDetail.jsp" scope="request"
 *                validate="false"
 */

public class ConsultPgmSettingUpdAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LIST_FIND 				= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 			= 7001;
    /** set header */
    public static final int SET_DUMMY_HEADER		= 1001;
    /** get excel template */
    public static final int TEMPLATE_EXPORT			= 1002;
    /** upload data */
    public static final int UPLOAD_DATA				= 1003;
    
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT             = 8002;
    /** 저장 */
    public static final int DETAIL_INPUT            = 5001;
    /** 수정 */
    public static final int DETAIL_UPDATE           = 6001;
    /** set filed */
    public static final int DETAIL_SET_FIELD        = 1004;
    /** create와 update 구별하기위해 사용 */
    public static final int DETAIL_FAKE_ACTION      = 1005;
    /** 업로드 */
    public static final int DETAIL_UPLOAD           = 5002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmSettingUpdForm consultPgmSettingUpdForm = (ConsultPgmSettingUpdForm) form;
        
        switch (consultPgmSettingUpdForm.getStrutsAction())
        {
            case ConsultPgmSettingUpdAction.BASE_GRID_EXPORT:
                findList(request, response, consultPgmSettingUpdForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case ConsultPgmSettingUpdAction.LIST_FIND:
                findList(request, response, consultPgmSettingUpdForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultPgmSettingUpdAction.LIST_DELETE:
            	deleteList(request, consultPgmSettingUpdForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultPgmSettingUpdAction.TEMPLATE_EXPORT:
                findExcelTemplateData(request, response, consultPgmSettingUpdForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case ConsultPgmSettingUpdAction.SET_DUMMY_HEADER:
                setDummyHeader(request, response, consultPgmSettingUpdForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmSettingUpdAction.UPLOAD_DATA:
            	uploadData(request, response, consultPgmSettingUpdForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmSettingUpdAction.DETAIL_INIT:
                findDetail(request, response, consultPgmSettingUpdForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmSettingUpdAction.DETAIL_INPUT:
                insertDetail(request, response, consultPgmSettingUpdForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmSettingUpdAction.DETAIL_UPDATE:
                updateDetail(request, response, consultPgmSettingUpdForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmSettingUpdAction.DETAIL_UPLOAD:
                uploadDetail(request, response, consultPgmSettingUpdForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmSettingUpdAction.DETAIL_SET_FIELD:
                findField(request, response, consultPgmSettingUpdForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultPgmSettingUpdForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm, boolean excelExport) throws Exception
    {
    	ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) getBean("consultPgmSettingUpdService");
    	ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO();

    	//Paging
    	consultPgmSettingUpdDTO.setIsLoadMaxCount("Y".equals(consultPgmSettingUpdForm.getIsLoadMaxCount())?true:false);
    	consultPgmSettingUpdDTO.setFirstRow(consultPgmSettingUpdForm.getFirstRow());
    	consultPgmSettingUpdDTO.setOrderBy(consultPgmSettingUpdForm.getOrderBy());
    	consultPgmSettingUpdDTO.setDirection(consultPgmSettingUpdForm.getDirection());
    	
        List resultList = consultPgmSettingUpdService.findExldataList(consultPgmSettingUpdDTO, getUser(request));
        //Paging
        String totalCount = "";
        if(Integer.parseInt(consultPgmSettingUpdForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmSettingUpdService.findTotalCount(consultPgmSettingUpdDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultPgmSettingUpdForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultPgmSettingUpdForm
     */
    private void deleteList(HttpServletRequest request, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception
    {
    	ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) getBean("consultPgmSettingUpdService");
    	String table = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO().getTableName();
        String[] deleteRows = consultPgmSettingUpdForm.getDeleteRows();
        
        consultPgmSettingUpdService.deleteExldataList(table, deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
    public int setDummyHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception
    {
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) getBean("consultPgmSettingUpdService");
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO();
        List resultList = null;
        Map resultMap = null;
        
        resultList = consultPgmSettingUpdService.getDummyHeader(consultPgmSettingUpdDTO, getUser(request));
        
        String height = "";
        if(resultList != null && resultList.size() > 1)
        {
            height = String.valueOf(((Map)(resultList.get(resultList.size()-1))).get("HEIGHT"));
        }

        int heightInt = 0;
        if(height != "" && height != "null") heightInt = Math.round(Integer.parseInt(height)) * 30 + 30; //한줄에 30px. + footer 20px
        
        resultMap = CommonUtil.makeHeaderJson(resultList);

       // Gson gson = new Gson();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
        
        JSONObject jsonObj = new JSONObject();
        
        String jsonString = gson.toJson(resultMap).toString(); 
        
        jsonObj.put("header", jsonString);
        jsonObj.put("height", heightInt+"");
        
        response.getWriter().print(jsonObj.toString());
        
        return resultList.size();
    }
    
    /**
     * FIND TEMPLATE
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param consultPgmSettingUpdForm
     */
    private void findExcelTemplateData(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception
    {
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) getBean("consultPgmSettingUpdService");
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO();
        
        List<Map> sheetList = consultPgmSettingUpdService.findExcelTemplateData(consultPgmSettingUpdDTO, getUser(request));
        
        CommonUtil.makeMultiGridExport(sheetList, request, response, consultPgmSettingUpdForm);
    }
    
    private void uploadData(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception
    {
    	ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService) getBean("consultPgmSettingUpdService");
    	ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO();
    	
    	consultPgmSettingUpdService.uploadData(consultPgmSettingUpdDTO, getUser(request));
    	setAjaxStatus(request);
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultPgmSettingUpdForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception 
    {   
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService)getBean("consultPgmSettingUpdService");
        
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO(); 
        
        List resultList = consultPgmSettingUpdService.findExldataDetail(consultPgmSettingUpdDTO, getUser(request));
        super.makeJsonResult(resultList, request, response, consultPgmSettingUpdForm.getListId());
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultPgmSettingUpdForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception
    {
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService)getBean("consultPgmSettingUpdService");
        ConsultPgmSettingUpdDTO  consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO(); 
        
        consultPgmSettingUpdService.insertExldataDetail(consultPgmSettingUpdDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultPgmSettingUpdForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception
    {
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService)getBean("consultPgmSettingUpdService");
        ConsultPgmSettingUpdDTO  consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO();
        
        consultPgmSettingUpdService.updateExldataDetail(consultPgmSettingUpdDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void uploadDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception
    {
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService)getBean("consultPgmSettingUpdService");
        ConsultPgmSettingUpdDTO  consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO();
        
        consultPgmSettingUpdService.uploadExldataDetail(consultPgmSettingUpdDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void findField(HttpServletRequest request, HttpServletResponse response, ConsultPgmSettingUpdForm consultPgmSettingUpdForm) throws Exception 
    {   
        ConsultPgmSettingUpdService consultPgmSettingUpdService = (ConsultPgmSettingUpdService)getBean("consultPgmSettingUpdService");
        
        ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = consultPgmSettingUpdForm.getConsultPgmSettingUpdDTO(); 
        
        List resultList = consultPgmSettingUpdService.findField(consultPgmSettingUpdDTO, getUser(request));
        super.makeJsonResult(resultList, request, response, consultPgmSettingUpdForm.getListId());
    }
}
