package dream.asset.list.action;

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

import common.bean.MwareConfig;
import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.AssetListITDetailDTO;
import dream.asset.list.dto.MaEqBuildMstrDetailDTO;
import dream.asset.list.dto.MaEqDeviceMstrDetailDTO;
import dream.asset.list.dto.MaEqMoldMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqToolMstrDetailDTO;
import dream.asset.list.form.MaEqMstrDetailForm;
import dream.asset.list.service.MaEqMstrDetailService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * 설비마스터 - 상세 action
 * 
 * @author kim2107
 * @version $Id: MaEqMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqMachineMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqMachineMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMoldMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqMoldMstrDetail.jsp" scope="request"
 *                validate="false"               
 * @struts:action path="/maEqToolMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqToolMstrDetail.jsp" scope="request"
 *                validate="false"               
 * @struts:action path="/maEqBuildingMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqBuildingMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqVehicleMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqVehicleMstrDetail.jsp" scope="request"
 *                validate="false"  
 * @struts:action path="/maEqUtilityMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqUtilityMstrDetail.jsp" scope="request"
 *                validate="false"  
 * @struts:action path="/maEqPartMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqPartMstrDetail.jsp" scope="request"
 *                validate="false"                                            
 * @struts:action path="/maEqMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/maEqMstrDetail.jsp" scope="request"
 *                validate="false"                                              
 * @struts:action path="/assetListITDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/assetListITDetail.jsp" scope="request"
 *                validate="false"    
 * @struts:action path="/assetListGnMstrDetail" name="maEqMstrDetailForm"
 *                input="/dream/asset/list/assetListGnMstrDetail.jsp" scope="request"
 *                validate="false"                                            
 * @struts.action-forward name="maEqMstrDetail" path="/dream/asset/list/maEqMstrDetail.jsp"
 *                        redirect="false"
 */
public class MaEqMstrDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_DETAIL_INIT 	    = 8001;
    /** 저장 */
    public static final int EQ_MSTR_DETAIL_INPUT 		= 5000;
    /** 수정 */
    public static final int EQ_MSTR_DETAIL_UPDATE 		= 6000;
    /** Slide Image 조회 */
    public static final int EQ_MSTR_DETAIL_PHOTO        = 8004;
    /** 완료 */
    public static final int EQ_MSTR_DETAIL_COMPLETE     = 6001;
    /** 복사 */
    public static final int EQ_MSTR_DETAIL_COPY		    = 5001;
    /** 중복체크 */
    public static final int EQ_NO_DETAIL_CHECK		    = 5002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrDetailForm maEqMstrDetailForm = (MaEqMstrDetailForm) form;

        super.updateAudit(maEqMstrDetailForm.getMaEqMstrDetailDTO().getAuditKey()==""?maEqMstrDetailForm.getMaEqMstrCommonDTO().getAuditKey():maEqMstrDetailForm.getMaEqMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrDetailForm.getStrutsAction())
        {
            case MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maEqMstrDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaEqMstrDetailAction.EQ_MSTR_DETAIL_INPUT:
            	insertDetail(maEqMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrDetailAction.EQ_MSTR_DETAIL_UPDATE:
            	updateDetail(maEqMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrDetailAction.EQ_MSTR_DETAIL_PHOTO:
                findSlideImage(maEqMstrDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrDetailAction.EQ_MSTR_DETAIL_COMPLETE:
            	completeDetail(maEqMstrDetailForm, request,response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrDetailAction.EQ_MSTR_DETAIL_COPY:
            	copyDetail(maEqMstrDetailForm, request,response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrDetailAction.BASE_ACTION_REPORT:
            	findReport(request, maEqMstrDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case MaEqMstrDetailAction.EQ_NO_DETAIL_CHECK:
                validItemNo(request, maEqMstrDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void findSlideImage(MaEqMstrDetailForm maEqMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, URISyntaxException, StorageException
    {
        // Service 객체 생성
        MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)getBean("maEqMstrDetailService", request);

        // 조회된 상세 부분
        List list = maEqMstrDetailService.findSlideImage(maEqMstrDetailForm.getMaEqMstrCommonDTO(), getUser(request).getCompNo());

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(list);

        response.getWriter().print(jsonString);
    }
    
    private void completeDetail(MaEqMstrDetailForm maEqMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
    	// Service 객체 생성
    	MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)getBean("maEqMstrDetailService", request);
    	MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailForm.getMaEqMstrDetailDTO();
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrDetailForm.getMaEqMstrCommonDTO();
        maEqMstrDetailService.completeDetail(maEqMstrCommonDTO, maEqMstrDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * 설비마스터 상세 조회
     * @author kim2107
     * @version $Id: MaEqMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaEqMstrDetailForm maEqMstrDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)getBean("maEqMstrDetailService", request);
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrDetailForm.getMaEqMstrCommonDTO();
        // 조회된 상세 부분
        MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailService.findDetail(maEqMstrCommonDTO, getUser(request));
        
        maEqMstrCommonDTO.setEquipDesc(maEqMstrDetailDTO.getEquipDesc());
        maEqMstrCommonDTO.setDeptId(maEqMstrDetailDTO.getDeptId());
        
        if("MD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null)
        {
        	//금형 상세 부분
        	MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO = maEqMstrDetailService.findMoldDetail(maEqMstrCommonDTO, getUser(request));
        	maEqMstrDetailForm.setMaEqMoldMstrDetailDTO(maEqMoldMstrDetailDTO);
        } 
        else if("TL".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null)
        {
        	//계측기 상세 부분
        	MaEqToolMstrDetailDTO maEqToolMstrDetailDTO = maEqMstrDetailService.findToolDetail(maEqMstrCommonDTO, getUser(request));
        	maEqMstrDetailForm.setMaEqToolMstrDetailDTO(maEqToolMstrDetailDTO);
        } 
        else if("BD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null)
        {
        	//건물 상세 부분
        	MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO = maEqMstrDetailService.findBuildDetail(maEqMstrCommonDTO, getUser(request));
        	maEqMstrDetailForm.setMaEqBuildMstrDetailDTO(maEqBuildMstrDetailDTO);
        }
        else if("PT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null)
        {
        	//건물 상세 부분
        	MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO = maEqMstrDetailService.findDeviceDetail(maEqMstrCommonDTO, getUser(request));
        	maEqMstrDetailForm.setMaEqDeviceMstrDetailDTO(maEqDeviceMstrDetailDTO);
        }
        else if("IT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null)
        {
        	// IT장비 상세 부분
        	AssetListITDetailDTO assetListITDetailDTO = maEqMstrDetailService.findITDetail(maEqMstrCommonDTO, getUser(request));
        	maEqMstrDetailForm.setAssetListITDetailDTO(assetListITDetailDTO);
        }
        
        request.setAttribute("slideFileList", maEqMstrDetailDTO.getSlideFileList());
        // 조회된 상세  셋팅한다.
        maEqMstrDetailForm.setMaEqMstrDetailDTO(maEqMstrDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaEqMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailForm
     * @param request
     */
    private void insertDetail(MaEqMstrDetailForm maEqMstrDetailForm, HttpServletRequest request) throws Exception
    {
        MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService) getBean("maEqMstrDetailService", request);
        
        MaEqMstrDetailDTO     maEqMstrDetailDTO     = maEqMstrDetailForm.getMaEqMstrDetailDTO();
        MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO = maEqMstrDetailForm.getMaEqMoldMstrDetailDTO();
        MaEqToolMstrDetailDTO maEqToolMstrDetailDTO = maEqMstrDetailForm.getMaEqToolMstrDetailDTO();
        MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO = maEqMstrDetailForm.getMaEqBuildMstrDetailDTO();
        MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO = maEqMstrDetailForm.getMaEqDeviceMstrDetailDTO();
        MaEqMstrCommonDTO     maEqMstrCommonDTO     = maEqMstrDetailForm.getMaEqMstrCommonDTO();
        AssetListITDetailDTO  assetListITDetailDTO = maEqMstrDetailForm.getAssetListITDetailDTO();

        maEqMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrDetailService.insertDetail(maEqMstrDetailDTO,getUser(request));

        
        if("MD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 금형
        	maEqMstrDetailService.insertMoldDetail(maEqMstrDetailDTO, maEqMoldMstrDetailDTO,getUser(request));
        } 
        else if("TL".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 계측기
        	maEqMstrDetailService.insertToolDetail(maEqMstrDetailDTO, maEqToolMstrDetailDTO,getUser(request));
        }
        else if("BD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 건물
        	maEqMstrDetailService.insertBuildDetail(maEqMstrDetailDTO, maEqBuildMstrDetailDTO,getUser(request));
       }
        else if("PT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 자재
        	maEqMstrDetailService.insertDeviceDetail(maEqMstrDetailDTO, maEqDeviceMstrDetailDTO,getUser(request));
        }
        else if("IT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// IT장비
        	maEqMstrDetailService.insertITDetail(maEqMstrDetailDTO, assetListITDetailDTO,getUser(request));
        }
        if("N".equals(MwareConfig.getIsUseAssetRevision())){
        	maEqMstrDetailService.insertRevisionHistAndUpdateMstr(maEqMstrCommonDTO, maEqMstrDetailDTO, getUser(request));
        }
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaEqMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailForm
     * @param request
     */
    private void updateDetail(MaEqMstrDetailForm maEqMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService) getBean("maEqMstrDetailService", request);
        
        MaEqMstrDetailDTO maEqMstrDetailDTO 		= maEqMstrDetailForm.getMaEqMstrDetailDTO();
        MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO = maEqMstrDetailForm.getMaEqMoldMstrDetailDTO();
        MaEqToolMstrDetailDTO maEqToolMstrDetailDTO = maEqMstrDetailForm.getMaEqToolMstrDetailDTO();
        MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO = maEqMstrDetailForm.getMaEqBuildMstrDetailDTO();
        MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO = maEqMstrDetailForm.getMaEqDeviceMstrDetailDTO();
        MaEqMstrCommonDTO     maEqMstrCommonDTO     = maEqMstrDetailForm.getMaEqMstrCommonDTO();
        AssetListITDetailDTO  assetListITDetailDTO = maEqMstrDetailForm.getAssetListITDetailDTO();

        maEqMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        if("MD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 금형
        	maEqMstrDetailService.updateMoldDetail(maEqMstrDetailDTO, maEqMoldMstrDetailDTO,getUser(request));
        }
        else if("TL".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 계측기
        	maEqMstrDetailService.updateToolDetail(maEqMstrDetailDTO, maEqToolMstrDetailDTO,getUser(request));
        }
        else if("BD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 건물
        	maEqMstrDetailService.updateBuildDetail(maEqMstrDetailDTO, maEqBuildMstrDetailDTO,getUser(request));
        }
        else if("PT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 자재
        	maEqMstrDetailService.updateDeviceDetail(maEqMstrDetailDTO, maEqDeviceMstrDetailDTO,getUser(request));
        }
        else if("IT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// IT장비
        	maEqMstrDetailService.updateITDetail(maEqMstrDetailDTO, assetListITDetailDTO,getUser(request));
        }
        
        maEqMstrDetailService.updateDetail(maEqMstrDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail copy
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEqMstrDetailForm
     * @param request
     */
    private void copyDetail(MaEqMstrDetailForm maEqMstrDetailForm, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService) getBean("maEqMstrDetailService", request);
        
    	MaEqMstrCommonDTO     maEqMstrCommonDTO     = maEqMstrDetailForm.getMaEqMstrCommonDTO();
        MaEqMstrDetailDTO     maEqMstrDetailDTO     = maEqMstrDetailForm.getMaEqMstrDetailDTO();
        
        if("MD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 금형
        	maEqMstrDetailService.insertCopyMoldDetail(maEqMstrDetailDTO, getUser(request));
        } 
        else if("TL".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 계측기
        	maEqMstrDetailService.insertCopyToolDetail(maEqMstrDetailDTO, getUser(request));
        }
        else if("BD".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 건물
        	maEqMstrDetailService.insertCopyBuildDetail(maEqMstrDetailDTO, getUser(request));
       }
        else if("PT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// 자재
        	maEqMstrDetailService.insertCopyDeviceDetail(maEqMstrDetailDTO, getUser(request));
        }
        else if("IT".equals(maEqMstrCommonDTO.getSelectedEqCtgTypeId())&&maEqMstrCommonDTO.getSelectedEqCtgTypeId()!=null){
        	// IT장비
        	maEqMstrDetailService.insertCopyITDetail(maEqMstrDetailDTO, getUser(request));
        }
        
        String result = maEqMstrDetailService.copyDetail(maEqMstrDetailDTO,maEqMstrCommonDTO, getUser(request))+"";
        
        setAjaxStatus(request, result);
        
    }

    /**
     * Report 를 조회한다.
     * @param request
     * @param eqMstrListForm
     * @throws DRException 
     * @throws JRException 
     * @throws IOException 
     */
    private void findReport(HttpServletRequest request, MaEqMstrDetailForm maEqMstrDetailForm) throws JRException, DRException, IOException
    {
    	MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService) getBean("maEqMstrDetailService", request);
    	MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailForm.getMaEqMstrDetailDTO();
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrDetailForm.getMaEqMstrCommonDTO();
        maEqMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        maEqMstrDetailDTO.setUserLang(getUser(request).getLangId());
        maEqMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        maEqMstrDetailDTO.setEquipId(maEqMstrCommonDTO.getEquipId());
        
        List reportList = maEqMstrDetailService.getReportView(maEqMstrDetailDTO,getUser(request));

        Map map = (Map)reportList.get(0);
	    Gson gson = new Gson();
	    String strJson = gson.toJson(map);
	    
	    //Master, Detail 형식 
	    ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
	    String destFileName = rs.viewReport("maEqMstrDetail",strJson, getUser(request));

	    System.out.println(destFileName+"!!!!");
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
    
    private void validItemNo(HttpServletRequest request, MaEqMstrDetailForm maEqMstrDetailForm) throws Exception
    {
        MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService) getBean("maEqMstrDetailService", request);
        
        MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailForm.getMaEqMstrDetailDTO();
        
        String isValid = maEqMstrDetailService.validItemNo(maEqMstrDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
}