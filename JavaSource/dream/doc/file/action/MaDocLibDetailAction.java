package dream.doc.file.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.google.gson.Gson;

import common.struts.AuthAction;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;
import dream.doc.file.form.MaDocLibDetailForm;
import dream.doc.file.service.MaDocLibDetailService;


/**
 * 첨부문서 - 상세 action
 * 
 * @author jung7126
 * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maEqDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/maEqDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/doc/file/maDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/maWoDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmInsDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmi/maWoResultPmInsDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRprDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmRprDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRplDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmRplDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmClnDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmClnDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmOilDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmOilDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRprDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRprDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmRplDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/bm/maWoResultBmOilDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRprDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmRprDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRplDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmRplDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultTrEleDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/maWoResultTrEleDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmGmDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmw/maWoResultPmGmDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmCalDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmc/maWoResultPmCalDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoMonthDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/cal/womonth/maWoMonthDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/part/list/maPtDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/qna/maQnaDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaAnsDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/qna/maQnaAnsDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoReqDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/work/maWoReqDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoReqResDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/work/maWoReqResDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqWorkDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/work/reqWorkDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqWorkResDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/work/reqWorkResDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoDayRptDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/note/dayrpt/maWoDayRptDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMoldDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/maEqMoldDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqToolDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/maEqToolDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqBuildingDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/maEqBuildingDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqVehicleDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/maEqVehicleDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqUtilityDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/maEqUtilityDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqPartDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/maEqPartDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtRepDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/part/rep/maPtRepDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maDocCntrCdDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/doc/data/maDocCntrCdDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/qna/maQnaDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaAnsDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/qna/maQnaAnsDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtBuyReqHdrDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/part/pur/buy/maPtBuyReqHdrDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtPurReqDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/part/pur/req/maPtPurReqDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPttMstrDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/tool/list/maPttMstrDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/partAdjStkTakeDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/part/adj/stktake/partAdjStkTakeDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmFfailDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/rcm/ffail/rcmFfailDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmFmeaDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/rcm/fmea/rcmFmeaDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmPmtaskDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmSysDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/rcm/system/rcmSysDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assBaseDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/ass/base/assBaseDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/invtDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/invt/list/invtDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/invtPrcTpItemDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/invt/prc/invtPrcTpItemDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/invtPrcDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/invt/list/invtPrcDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultGnlCaDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmc/maWoResultGnlCaDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/pmi/list/workPmiDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/workListDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultSclCaDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmc/maWoResultSclCaDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPrsCaDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/pmc/maWoResultPrsCaDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/eduEmpDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/edu/list/eduEmpDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/certEmpDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/cert/list/certEmpDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/orgEmpTrainDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/org/emp/orgEmpTrainDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/orgEmpCertDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/org/emp/orgEmpCertDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/kacBdDocDetail" name="maDocLibDetailForm"
 *                input="/enhance/kac/bd/kacBdDocDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmLocBaseDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/cm/maWoResultCmLocBaseDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maStdDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/pm/std/maStdDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/appOnlinehelpDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/app/onlinehelp/appOnlinehelpDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiPointDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/pmi/list/workPmiPointDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiRInsPointDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/pmi/list/workPmiRInsPointDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiRInsPointValueDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/pmi/list/workPmiRInsPointValueDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiDInsPointDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/pmi/list/workPmiDInsPointDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiCInsPointDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/pmi/list/workPmiCInsPointDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiPInsPointDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/workPmiPInsPointDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListItDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/assetListItDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListGnDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/list/assetListGnDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docNoticeDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/doc/notice/docNoticeDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docNoticeCheckDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/doc/notice/docNoticeCheckDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docManualDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/doc/manual/docManualDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptWorkHistLibDetail" name="maDocLibDetailForm"
 *                input="/dream/asset/rpt/assetRptWorkHistLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maHelpDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/consult/program/help/maHelpDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvRecWorkDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/work/reqInvRecWorkDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvWorkDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/req/work/reqInvWorkDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maBdPointDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/rpt/bdpoint/maBdPointDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workLetPermitDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/let/permit/workLetPermitDocLibDetail.jsp" scope="request"
 *                validate="false"           
 * @struts:action path="/workListEnergyPointDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/list/energy/workListEnergyPointDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoDailyLibDetail" name="maDocLibDetailForm"
 *                input="/dream/note/daily/maWoDailyLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrWorkCloseCheckDocLibDetail" name="maDocLibDetailForm"
 *                input="/dream/work/close/check/mgrWorkCloseCheckDocLibDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDocLibDetail" path="/dream/doc/file/maDocLibDetail.jsp"
 *                        redirect="false"
 */
public class MaDocLibDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DOCLIB_DETAIL_INIT 		    = 8001;
    /** 저장 */
    public static final int DOCLIB_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DOCLIB_DETAIL_UPDATE 		= 6003;
    /** 파일저장 */
    public static final int DOCLIB_FILE_SAVE            = 5004;
    /** 파일삭제 */
    public static final int DOCLIB_FILE_DELETE          = 1005;
    /** 파일저장 */
    public static final int DOCLIB_FILE_SAVE_TEST       = 5006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocLibDetailForm maDocLibDetailForm = (MaDocLibDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") maDocLibDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(maDocLibDetailForm.getMaDocLibDetailDTO().getAuditKey()==""?maDocLibDetailForm.getMaDocLibCommonDTO().getAuditKey():maDocLibDetailForm.getMaDocLibDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDocLibDetailForm.getStrutsAction())
        {
            case MaDocLibDetailAction.DOCLIB_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maDocLibDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaDocLibDetailAction.DOCLIB_DETAIL_INPUT:
            	insertDetail(maDocLibDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDocLibDetailAction.DOCLIB_DETAIL_UPDATE:
            	updateDetail(maDocLibDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDocLibDetailAction.DOCLIB_FILE_SAVE:
                fileUpload(maDocLibDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocLibDetailAction.DOCLIB_FILE_DELETE:
                fileDelete(maDocLibDetailForm, request, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaDocLibDetailAction.DOCLIB_FILE_SAVE_TEST:
                fileUploadTest(maDocLibDetailForm, request, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void fileDelete(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request, HttpServletResponse response)
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.deleteAutoFiles(maDocLibDetailDTO,maDocLibDetailForm.getDeleteRows());
    }

    private void fileUpload(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        Map<String,String> rtnMsg = maDocLibDetailService.uploadAutoFiles(maDocLibDetailDTO, request, response);
        

        if(rtnMsg.containsValue("ERROR"))
        {
        	Map rtnMap = new HashMap();
        	rtnMap.put("state", false);
        	rtnMap.put("extra", rtnMsg);
        	Gson gson = new Gson();
        	String jsonString = gson.toJson(rtnMap);

        	response.getWriter().print(jsonString);
        }

    }
    
    private void fileUploadTest(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        FormFile[] fileList = maDocLibDetailForm.getFileList();
        
//        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
//        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.uploadFiles(maDocLibDetailDTO,fileList);

    }

    /**
     * 버튼 상세 조회
     * @author jung7126
     * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDocLibDetailForm
     */
    private void findDetail(HttpServletRequest request, MaDocLibDetailForm maDocLibDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService)getBean("maDocLibDetailService");

        // 넘겨진 메뉴Id 구함
        String docId = maDocLibDetailForm.getMaDocLibCommonDTO().getDocId();
        
        // 조회된 상세 부분
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailService.findDetail(docId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maDocLibDetailForm.setMaDocLibDetailDTO(maDocLibDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailForm
     * @param request
     */
    private void insertDetail(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request) throws Exception
    {
        MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        MaDocLibCommonDTO maDocLibCommonDTO = maDocLibDetailForm.getMaDocLibCommonDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.insertDetail(maDocLibDetailDTO, maDocLibCommonDTO, maDocLibDetailForm.getFileList());
        
//        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaDocLibDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailForm
     * @param request
     */
    private void updateDetail(MaDocLibDetailForm maDocLibDetailForm, HttpServletRequest request) throws Exception
    {
    	MaDocLibDetailService maDocLibDetailService = (MaDocLibDetailService) getBean("maDocLibDetailService");
        
        MaDocLibDetailDTO maDocLibDetailDTO = maDocLibDetailForm.getMaDocLibDetailDTO();
        
        maDocLibDetailDTO.setEnterBy(getUser(request).getUserId());
        maDocLibDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maDocLibDetailService.updateDetail(maDocLibDetailDTO, maDocLibDetailForm.getFileList(), maDocLibDetailForm.getDeleteRows());

//        setAjaxStatus(request);
    }
    
}