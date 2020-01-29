package dream.work.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.bean.User;
import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.MaWoResultMstrListForm;
import dream.work.list.service.MaWoResultMstrListService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * 작업결과 - 목록 action
 * @author  kim21017
 * @version $Id: MaWoResultMstrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoResultMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/maWoResultMstrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultBmMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/bm/maWoResultBmMstrList.jsp" scope="request"
 *                validate="false"    
 * @struts:action path="/maWoCmResultMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/cm/maWoCmResultMstrList.jsp" scope="request"
 *                validate="false"  
 * @struts:action path="/workListTiMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/ti/workListTiMstrList.jsp" scope="request"
 *                validate="false" 
 * @struts:action path="/maWoPmwResultMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/pmw/maWoPmwResultMstrList.jsp" scope="request"
 *                validate="false"       
 * @struts:action path="/maWoPmcResultMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/pmc/maWoPmcResultMstrList.jsp" scope="request"
 *                validate="false"   
 * @struts:action path="/maWoPmwOvhResultMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/pmw/maWoPmwOvhResultMstrList.jsp" scope="request"
 *                validate="false"                     
 * @struts:action path="/workListWeekWoList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/week/workListWeekWoList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoResultMstrList" path="/dream/work/list/maWoResultMstrList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maWoResultBmMstrList" path="/dream/work/list/bm/maWoResultBmMstrList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maWoCmResultMstrList" path="/dream/work/list/cm/maWoCmResultMstrList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="workListTiMstrList" path="/dream/work/list/ti/workListTiMstrList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maWoPmwResultMstrList" path="/dream/work/list/pmw/maWoPmwResultMstrList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maWoPmcResultMstrList" path="/dream/work/list/pmc/maWoPmcResultMstrList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maWoPmwOvhResultMstrList" path="/dream/work/list/pmw/maWoPmwOvhResultMstrList.jsp"
 *                        redirect="false"
 * @struts:action path="/workListPmiList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/pmi/workListPmiList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maWoResultTrMstrList" name="maWoResultMstrListForm"
 *                input="/dream/work/list/maWoResultTrMstrList.jsp" scope="request"
 *                validate="false"
 */
public class MaWoResultMstrListAction extends AuthAction
{
    /** 조회 */
    public static final int WO_RESULT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WO_RESULT_LIST_DELETE 		= 7002;
    /** BM조회 */
    public static final int WOBM_RESULT_LIST_FIND 		= 1003;
    /** CM조회 */
    public static final int WOCM_RESULT_LIST_FIND 		= 1004;
    /** PMW조회 */
    public static final int WOPMW_RESULT_LIST_FIND 		= 1005;
    /** PMP조회 */
    public static final int WOPMC_RESULT_LIST_FIND 		= 1006;
    /** TI조회 */
    public static final int WOTI_RESULT_LIST_FIND 		= 1007;
    /** OVH조회 */
    public static final int WOPMWOVH_RESULT_LIST_FIND 	= 1008;
    /** 교육 조회 */
    public static final int WO_TR_LIST_FIND         	= 1020;
    /** 예방점검작업 조회 */
    public static final int WO_RESULT_PMI_LIST_FIND 	= 2001;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 			= 2002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoResultMstrListForm maWoResultMstrListForm = (MaWoResultMstrListForm) form;
        
        super.updateAudit(maWoResultMstrListForm.getMaWoResultMstrCommonDTO().getAuditKey()==""?maWoResultMstrListForm.getMaWoResultMstrCommonDTO().getAuditKey():maWoResultMstrListForm.getMaWoResultMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWoResultMstrListForm.getStrutsAction())
        {
            case MaWoResultMstrListAction.WO_RESULT_LIST_FIND:
            	findWoResultMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WO_RESULT_PMI_LIST_FIND:
                findWoResultPmiMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WO_TR_LIST_FIND:
                findWoResultTrMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoResultMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WO_RESULT_LIST_DELETE:
            	deleteWoResultMstr(request, maWoResultMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoResultMstrListAction.BASE_GRID_EXPORT:
                switch (maWoResultMstrListForm.getStAct())
                {
                    case MaWoResultMstrListAction.WO_RESULT_LIST_FIND:
                        findWoResultMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WO_RESULT_PMI_LIST_FIND:
                        findWoResultPmiMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WO_TR_LIST_FIND:
                        findWoResultTrMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WOBM_RESULT_LIST_FIND:
                        findWoBmResultMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WOCM_RESULT_LIST_FIND:
                        findWoCmResultMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WOTI_RESULT_LIST_FIND:
                        findWoTiResultMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WOPMW_RESULT_LIST_FIND:
                        findWoPmwResultMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WOPMC_RESULT_LIST_FIND:
                        findWoPmcResultMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                    case MaWoResultMstrListAction.WOPMWOVH_RESULT_LIST_FIND:
                        findWoPmwOvhResultMstrList(request, maWoResultMstrListForm, response, true);
                        break;
                }
            	returnActionForward =new ActionForward("/gridExport");
                break;
            case MaWoResultMstrListAction.BASE_ACTION_REPORT:
            	findReport(request, maWoResultMstrListForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case MaWoResultMstrListAction.WOBM_RESULT_LIST_FIND:
            	findWoBmResultMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WOCM_RESULT_LIST_FIND:
            	findWoCmResultMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WOTI_RESULT_LIST_FIND:
            	findWoTiResultMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WOPMW_RESULT_LIST_FIND:
            	findWoPmwResultMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WOPMC_RESULT_LIST_FIND:
            	findWoPmcResultMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.WOPMWOVH_RESULT_LIST_FIND:
            	findWoPmwOvhResultMstrList(request, maWoResultMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoResultMstrListAction.GET_DATA:
            	getData(request,response, maWoResultMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoResultMstrListForm maWoResultMstrListForm) throws IOException
    {
        super.setHeader(request, response, maWoResultMstrListForm.getListId(), maWoResultMstrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoResultMstrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoResultMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
    	maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
    	maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
    	maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoResultMstrList(maWoResultMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoResultMstrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoBmResultMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
    	maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
    	maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
    	maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoBmResultMstrList(maWoResultMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"BM");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoResultMstrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoCmResultMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
    	maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
    	maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
    	maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoCmResultMstrList(maWoResultMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"CM");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

    private void findWoTiResultMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
    	maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
    	maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
    	maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoTiResultMstrList(maWoResultMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"TI");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoResultMstrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoPmwResultMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
    	maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
    	maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
    	maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoPmwResultMstrList(maWoResultMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"PMW");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoResultMstrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoPmcResultMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
    	maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
    	maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
    	maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoPmcResultMstrList(maWoResultMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"PMC");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    private void findWoResultPmiMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
        MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());

        maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
        maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
        maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
        maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoResultPmiMstrList(maWoResultMstrCommonDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"PMI");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }


    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaWoResultMstrListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoResultTrMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse 

            response, boolean excelExport) throws IOException
    {
        MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());

        maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
        maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
        maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
        maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
        maWoResultMstrCommonDTO.setFilterWoTypeId("TR");
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoResultTrMstrList(maWoResultMstrCommonDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount =maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"TR");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaWoResultMstrListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrListForm
     * @param request
     */
    private void deleteWoResultMstr(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm) throws Exception
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");
    	String[] deleteRows = maWoResultMstrListForm.getDeleteRows();    // sheet 내역
        
        maWoResultMstrListService.deleteWoResultMstr(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * Report 를 조회한다.
     * @param request
     * @param maWoResultMstrDetailForm
     * @throws DRException 
     * @throws JRException 
     * @throws IOException 
     */
    private void findReport(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm) throws JRException, DRException, IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setEnterBy(getUser(request).getUserId());
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	maWoResultMstrCommonDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maWoResultMstrListService.getReportView(maWoResultMstrCommonDTO);
        
//        dynamicJasperReport namicJasperReport = new dynamicJasperReport();
//        viewReport("maWoResultMstrListSub2",reportList);
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);

        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
       // String destFileName = rs.viewReport("maWoResultMstrList",strJson, getUser(request));
        String destFileName = rs.viewReport("maWoResultMstrList",reportList, getUser(request));

        System.out.println("!!!!"+destFileName);
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
    
    /**
     * grid find
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoResultMstrListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoPmwOvhResultMstrList(HttpServletRequest request, MaWoResultMstrListForm maWoResultMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService");        

    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(maWoResultMstrListForm.getIsLoadMaxCount())?true:false);
    	maWoResultMstrCommonDTO.setFirstRow(maWoResultMstrListForm.getFirstRow());
    	maWoResultMstrCommonDTO.setOrderBy(maWoResultMstrListForm.getOrderBy());
    	maWoResultMstrCommonDTO.setDirection(maWoResultMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maWoResultMstrListService.findWoPmwOvhResultMstrList(maWoResultMstrCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maWoResultMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoResultMstrListService.findTotalCount(maWoResultMstrCommonDTO,getUser(request),"PMW");

        if(excelExport) super.makeGridExport(resultList, request, response,maWoResultMstrListForm.getListId(),maWoResultMstrListForm.getCurrentPageId(), maWoResultMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * Excel 업로드 데이터
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param maEqMstrListForm
     * @throws Exception
     */
    private void getData(HttpServletRequest request, HttpServletResponse response, MaWoResultMstrListForm maWoResultMstrListForm) throws Exception
    {
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) getBean("maWoResultMstrListService"); 
    	
    	User user = getUser(request);
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = maWoResultMstrListForm.getMaWoResultMstrCommonDTO();
    	
    	String result = maWoResultMstrListService.getData(maWoResultMstrCommonDTO, user);
    	
    	setAjaxDesc(request, result);
    }
}
