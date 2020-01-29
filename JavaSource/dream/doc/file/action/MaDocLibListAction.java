package dream.doc.file.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.form.MaDocLibListForm;
import dream.doc.file.service.MaDocLibListService;

/**
 * 첨부문서 - 목록 action
 * @author  jung7126
 * @version $Id: MaDocLibListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/maEqDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maDocLibList" name="maDocLibListForm"
 *                input="/dream/doc/file/maDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/maWoDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmInsDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmi/maWoResultPmInsDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRprDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmw/maWoResultPmRprDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmRplDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmw/maWoResultPmRplDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmClnDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmw/maWoResultPmClnDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmOilDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmw/maWoResultPmOilDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRprDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/bm/maWoResultBmRprDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmRplDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/bm/maWoResultBmRplDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmOilDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/bm/maWoResultBmOilDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRprDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/cm/maWoResultCmRprDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmRplDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/cm/maWoResultCmRplDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultTrEleDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/maWoResultTrEleDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmGmDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmw/maWoResultPmGmDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPmCalDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmc/maWoResultPmCalDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoMonthDocLibList" name="maDocLibListForm"
 *                input="/dream/work/cal/womonth/maWoMonthDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtDocLibList" name="maDocLibListForm"
 *                input="/dream/part/list/maPtDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaDocLibList" name="maDocLibListForm"
 *                input="/dream/req/qna/maQnaDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaAnsDocLibList" name="maDocLibListForm"
 *                input="/dream/req/qna/maQnaAnsDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoReqDocLibList" name="maDocLibListForm"
 *                input="/dream/req/work/maWoReqDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqWorkDocLibList" name="maDocLibListForm"
 *                input="/dream/req/work/reqWorkDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqWorkResDocLibList" name="maDocLibListForm"
 *                input="/dream/req/work/reqWorkResDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoReqResDocLibList" name="maDocLibListForm"
 *                input="/dream/req/work/maWoReqResDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoDayRptDocLibList" name="maDocLibListForm"
 *                input="/dream/note/dayrpt/maWoDayRptDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMoldDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/maEqMoldDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqToolDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/maEqToolDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqBuildingDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/maEqBuildingDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqVehicleDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/maEqVehicleDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqUtilityDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/maEqUtilityDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqPartDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/maEqPartDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtRepDocLibList" name="maDocLibListForm"
 *                input="/dream/part/rep/maPtRepDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maDocCntrCdDocLibList" name="maDocLibListForm"
 *                input="/dream/doc/data/maDocCntrCdDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaDocLibList" name="maDocLibListForm"
 *                input="/dream/req/qna/maQnaDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maQnaAnsDocLibList" name="maDocLibListForm"
 *                input="/dream/req/qna/maQnaAnsDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtBuyReqHdrDocLibList" name="maDocLibListForm"
 *                input="/dream/part/pur/buy/maPtBuyReqHdrDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPtPurReqDocLibList" name="maDocLibListForm"
 *                input="/dream/part/pur/req/maPtPurReqDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPttMstrDocLibList" name="maDocLibListForm"
 *                input="/dream/tool/list/maPttMstrDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/partAdjStkTakeDocLibList" name="maDocLibListForm"
 *                input="/dream/part/adj/stktake/partAdjStkTakeDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmFfailDocLibList" name="maDocLibListForm"
 *                input="/dream/rcm/ffail/rcmFfailDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmFmeaDocLibList" name="maDocLibListForm"
 *                input="/dream/rcm/fmea/rcmFmeaDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmPmtaskDocLibList" name="maDocLibListForm"
 *                input="/dream/rcm/pmtask/rcmPmtaskDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/rcmSysDocLibList" name="maDocLibListForm"
 *                input="/dream/rcm/system/rcmSysDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assBaseDocLibList" name="maDocLibListForm"
 *                input="/dream/ass/base/assBaseDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/invtDocLibList" name="maDocLibListForm"
 *                input="/dream/invt/list/invtDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/invtPrcTpItemDocLibList" name="maDocLibListForm"
 *                input="/dream/invt/prc/invtPrcTpItemDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/invtPrcDocLibList" name="maDocLibListForm"
 *                input="/dream/invt/list/invtPrcDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultGnlCaDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmc/maWoResultGnlCaDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiDocLibList" name="maDocLibListForm"
 *                input="/dream/work/pmi/list/workPmiDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/workListDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultSclCaDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmc/maWoResultSclCaDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultPrsCaDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/pmc/maWoResultPrsCaDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/eduEmpDocLibList" name="maDocLibListForm"
 *                input="/dream/edu/list/eduEmpDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/certEmpDocLibList" name="maDocLibListForm"
 *                input="/dream/cert/list/certEmpDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/orgEmpTrainDocLibList" name="maDocLibListForm"
 *                input="/dream/org/emp/orgEmpTrainDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/orgEmpCertDocLibList" name="maDocLibListForm"
 *                input="/dream/org/emp/orgEmpCertDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/kacBdDocList" name="maDocLibListForm"
 *                input="/enhance/kac/bd/kacBdDocList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultCmLocBaseDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/cm/maWoResultCmLocBaseDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maStdDocLibList" name="maDocLibListForm"
 *                input="/dream/work/pm/std/maStdDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/appOnlinehelpDocLibList" name="maDocLibListForm"
 *                input="/dream/app/onlinehelp/appOnlinehelpDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiPointDocLibList" name="maDocLibListForm"
 *                input="/dream/work/pmi/list/workPmiPointDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiRInsPointDocLibList" name="maDocLibListForm"
 *                input="/dream/work/pmi/list/workPmiRInsPointDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiRInsPointValueDocLibList" name="maDocLibListForm"
 *                input="/dream/work/pmi/list/workPmiRInsPointValueDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiDInsPointDocLibList" name="maDocLibListForm"
 *                input="/dream/work/pmi/list/workPmiDInsPointDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiCInsPointDocLibList" name="maDocLibListForm"
 *                input="/dream/work/pmi/list/workPmiCInsPointDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmiPInsPointDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/workPmiPInsPointDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListItDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/assetListItDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListGnDocLibList" name="maDocLibListForm"
 *                input="/dream/asset/list/assetListGnDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docNoticeDocLibList" name="maDocLibListForm"
 *                input="/dream/doc/notice/docNoticeDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docNoticeCheckDocLibList" name="maDocLibListForm"
 *                input="/dream/doc/notice/docNoticeCheckDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docManualDocLibList" name="maDocLibListForm"
 *                input="/dream/doc/manual/docManualDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptWorkHistLibList" name="maDocLibListForm"
 *                input="/dream/asset/rpt/assetRptWorkHistLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maHelpDocLibList" name="maDocLibListForm"
 *                input="/dream/consult/program/help/maHelpDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvRecWorkDocLibList" name="maDocLibListForm"
 *                input="/dream/req/work/reqInvRecWorkDocLibList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvWorkDocLibList" name="maDocLibListForm"
 *                input="/dream/req/work/reqInvWorkDocLibList.jsp" scope="request"
 *                validate="false"           
 * @struts:action path="/maBdPointDocLibList" name="maDocLibListForm"
 *                input="/dream/work/rpt/bdpoint/maBdPointDocLibList.jsp" scope="request"
 *                validate="false"           
 * @struts:action path="/workLetPermitDocLibList" name="maDocLibListForm"
 *                input="/dream/work/let/permit/workLetPermitDocLibList.jsp" scope="request"
 *                validate="false"           
 * @struts:action path="/workListEnergyPointDocLibList" name="maDocLibListForm"
 *                input="/dream/work/list/energy/workListEnergyPointDocLibList.jsp" scope="request"
 *                validate="false"           
 * @struts:action path="/maWoDailyLibList" name="maDocLibListForm"
 *                input="/dream/note/daily/maWoDailyLibList.jsp" scope="request"
 *                validate="false"           
 * @struts:action path="/mgrWorkCloseCheckDocLibList" name="maDocLibListForm"
 *                input="/dream/work/close/check/mgrWorkCloseCheckDocLibList.jsp" scope="request"
 *                validate="false"                   
 * @struts.action-forward name="maDocLibList" path="/dream/doc/file/maDocLibList.jsp"
 *                        redirect="false"
 */
public class MaDocLibListAction extends AuthAction
{
    /** 조회 */
    public static final int DOCLIB_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int DOCLIB_LIST_DELETE 	    = 7002;
    /** 기존 File(DOC) Link */
    public static final int DOCLIB_LIST_LINK 	    = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaDocLibListForm maDocLibListForm = (MaDocLibListForm) form;
        
        super.updateAudit(maDocLibListForm.getMaDocLibCommonDTO().getAuditKey()==""?maDocLibListForm.getMaDocLibCommonDTO().getAuditKey():maDocLibListForm.getMaDocLibCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDocLibListForm.getStrutsAction())
        {
            case MaDocLibListAction.DOCLIB_LIST_FIND:
                findMenuList(request, maDocLibListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocLibListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maDocLibListForm.getListId(),maDocLibListForm.getCurrentPageId()); 
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDocLibListAction.DOCLIB_LIST_DELETE:
            	deleteMenu(request, maDocLibListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaDocLibListAction.DOCLIB_LIST_LINK:
            	linkDoc(request, maDocLibListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaDocLibListAction.BASE_GRID_EXPORT:
            	findMenuList(request, maDocLibListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void linkDoc(HttpServletRequest request, MaDocLibListForm maDocLibListForm) 
    {
    	MaDocLibListService maDocLibListService = (MaDocLibListService) getBean("maDocLibListService");        

    	MaDocLibCommonDTO maDocLibCommonDTO = maDocLibListForm.getMaDocLibCommonDTO();
        
        maDocLibListService.linkList(maDocLibCommonDTO, getUser(request));
        
        setAjaxStatus(request);
	}

	/**
     * grid find
     * @author  kim2107
     * @version $Id: MaDocLibListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDocLibListForm
     * @throws Exception
     */
    private void findMenuList(HttpServletRequest request, MaDocLibListForm maDocLibListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaDocLibListService maDocLibListService = (MaDocLibListService) getBean("maDocLibListService");        

    	MaDocLibCommonDTO maDocLibCommonDTO = maDocLibListForm.getMaDocLibCommonDTO();
    	maDocLibCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        maDocLibCommonDTO.setIsLoadMaxCount("Y".equals(maDocLibListForm.getIsLoadMaxCount())?true:false);
        maDocLibCommonDTO.setFirstRow(maDocLibListForm.getFirstRow());
        maDocLibCommonDTO.setOrderBy(maDocLibListForm.getOrderBy());
        maDocLibCommonDTO.setDirection(maDocLibListForm.getDirection());
        
        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = maDocLibListService.findList(maDocLibCommonDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maDocLibListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maDocLibListService.findTotalCount(maDocLibCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maDocLibListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaDocLibListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deleteMenu(HttpServletRequest request, MaDocLibListForm maDocLibListForm) throws Exception
    {
    	MaDocLibListService maDocLibListService = (MaDocLibListService) getBean("maDocLibListService");        

    	String[] deleteRows = maDocLibListForm.getDeleteRows();
    	MaDocLibCommonDTO maDocLibCommonDTO = maDocLibListForm.getMaDocLibCommonDTO();
        
        maDocLibListService.deleteList(deleteRows, maDocLibCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
