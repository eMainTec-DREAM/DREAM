package dream.asset.list.action;


import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.MaEqMstrListForm;
import dream.asset.list.service.MaEqMstrListService;

/**
 * 설비마스터 - 목록 action
 * @author  hyosung
 * @version $Id: MaEqMstrListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqMstrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMachMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqMachMstrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqUtilityMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqUtilityMstrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqBuildingMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqBuildingMstrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqToolMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqToolMstrList.jsp" scope="request"
 *                validate="false"         
 * @struts:action path="/maEqPartMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqPartMstrList.jsp" scope="request"
 *                validate="false"             
 * @struts:action path="/maEqMstrMoldList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqMstrMoldList.jsp" scope="request"
 *                validate="false"     
 * @struts:action path="/maEqMoldMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/maEqMoldMstrList.jsp" scope="request"
 *                validate="false"                       
 * @struts:action path="/assetListITList" name="maEqMstrListForm"
 *                input="/dream/asset/list/assetListITList.jsp" scope="request"
 *                validate="false"    
 * @struts:action path="/assetListGnMstrList" name="maEqMstrListForm"
 *                input="/dream/asset/list/assetListGnMstrList.jsp" scope="request"
 *                validate="false"      
 *      
 * @struts.action-forward name="maEqMstrList" path="/dream/asset/list/maEqMstrList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrListAction extends AuthAction
{
    /** 설비(전체)조회 */
    public static final int EQ_MSTR_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int EQ_MSTR_LIST_DELETE 	= 7002;
    /** 복사 */
    public static final int EQ_MSTR_COPY		 	= 5003;
    /** 생산설비조회 */
    public static final int EQ_MACH_LIST_FIND       = 8004;
    /** 성능조회 */
    public static final int EQ_UTIL_LIST_FIND       = 8005;
    /** 성능조회 */
    public static final int EQ_BUILD_LIST_FIND      = 8006;
    /** 계측기*/
    public static final int EQ_TOOL_LIST_FIND       = 8007;
    /** 자산부품 */
    public static final int EQ_PART_LIST_FIND       = 8008;
    /** 금형 */
    public static final int EQ_MOLD_LIST_FIND       = 8009;
    /** 출력 */
    public static final int EQ_MSTR_QR_INSERT 		= 4001;
    /** 리스트 출력 */
    public static final int EQ_MSTR_QR_LIST_INSERT 	= 4002;
    /** IT 장비 */
    public static final int EQ_IT_LIST_FIND       	= 8015;
    /** 일반자산 */
    public static final int EQ_GN_LIST_FIND			= 8016;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 		= 1017;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrListForm maEqMstrListForm = (MaEqMstrListForm) form;
        
        super.updateAudit(maEqMstrListForm.getMaEqMstrCommonDTO().getAuditKey()==""?maEqMstrListForm.getMaEqMstrCommonDTO().getAuditKey():maEqMstrListForm.getMaEqMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maEqMstrListForm.getStrutsAction())
        {
            case MaEqMstrListAction.EQ_MSTR_LIST_FIND:
            	findEqMstrList(request, maEqMstrListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_MACH_LIST_FIND:   
                findEqMachMstrList(request, maEqMstrListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_UTIL_LIST_FIND:   
                findEqUtilMstrList(request, maEqMstrListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_BUILD_LIST_FIND:
                findEqBuildMstrList(request, maEqMstrListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_TOOL_LIST_FIND:
                findEqToolMstrList(request, maEqMstrListForm, response,false);
                returnActionForward=mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_PART_LIST_FIND:
                findEqPartMstrList(request, maEqMstrListForm, response,false);
                returnActionForward=mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_MOLD_LIST_FIND:
                findEqMoldMstrList(request, maEqMstrListForm, response,false);
                returnActionForward=mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_IT_LIST_FIND:
                findEqITList(request, maEqMstrListForm, response,false);
                returnActionForward=mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_GN_LIST_FIND:
                findEqGNList(request, maEqMstrListForm, response,false);
                returnActionForward=mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.BASE_SET_HEADER:
                setHeader(request, response, maEqMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.EQ_MSTR_LIST_DELETE:
            	deleteEqMstr(request, maEqMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqMstrListAction.EQ_MSTR_QR_INSERT:
            	insertQrCode(request, maEqMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqMstrListAction.EQ_MSTR_QR_LIST_INSERT:
            	insertListQrCode(request, maEqMstrListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrListAction.EQ_MSTR_COPY:
            	copyEquipment(request,response, maEqMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.GET_DATA:
            	getData(request,response, maEqMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqMstrListAction.BASE_GRID_EXPORT:
                
                switch (maEqMstrListForm.getMaEqMstrCommonDTO().getEqCtgType())
                {
                    case "MC":
                        findEqMachMstrList(request, maEqMstrListForm, response,true);
                        break;
                    case "UT":
                        findEqUtilMstrList(request, maEqMstrListForm, response,true);
                        break;
                    case "BD":
                        findEqBuildMstrList(request, maEqMstrListForm, response,true);
                        break;
                    case "TL":
                        findEqToolMstrList(request, maEqMstrListForm, response,true);
                        break;
                    case "PT":
                        findEqPartMstrList(request, maEqMstrListForm, response,true);
                        break;
                    case "MD":
                        findEqMoldMstrList(request, maEqMstrListForm, response,true);
                        break;
                    case "IT":
                        findEqITList(request, maEqMstrListForm, response,true);
                        break;
                    case "GN": 
                        findEqGNList(request, maEqMstrListForm, response,true);
                        break;    
                    default:
                        findEqMstrList(request, maEqMstrListForm,response, true);
                        break;
                }
                
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void findEqPartMstrList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm,HttpServletResponse response, boolean excelExport) throws IOException {
    	MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        //Paging
        maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
        maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
        maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
        maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maEqMstrListService.findEqPartMstrList(maEqMstrCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findPtTotalCount(maEqMstrCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, MaEqMstrListForm maEqMstrListForm) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        super.setHeader(request, response, maEqMstrListForm.getListId(), maEqMstrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrListForm
     * @throws Exception
     */
    private void findEqMstrList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
    	maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
    	maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
    	maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
    	maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findTotalCount(maEqMstrCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrListForm
     * @throws Exception
     */
    private void findEqMachMstrList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
        MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService", request);        

        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
    	maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
    	maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
    	maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maEqMstrListService.findEqMachMstrList(maEqMstrCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findMcTotalCount(maEqMstrCommonDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqUtilityListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrListForm
     * @throws Exception
     */
    private void findEqUtilMstrList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
        MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
    	maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
    	maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
    	maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maEqMstrListService.findEqUtilMstrList(maEqMstrCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findUtTotalCount(maEqMstrCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqBuildingListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrListForm
     * @throws Exception
     */
    private void findEqBuildMstrList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
        MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
    	maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
    	maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
    	maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maEqMstrListService.findEqBuildMstrList(maEqMstrCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findBdTotalCount(maEqMstrCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqToolListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrListForm
     * @throws Exception
     */
    private void findEqToolMstrList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
        MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
    	maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
    	maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
    	maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maEqMstrListService.findEqToolMstrList(maEqMstrCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findTlTotalCount(maEqMstrCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: MaEqMstrMoldListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrListForm
     * @throws Exception
     */
    private void findEqMoldMstrList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
        MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
    	maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
    	maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
    	maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maEqMstrListService.findEqMoldMstrList(maEqMstrCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findMdTotalCount(maEqMstrCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
   /**
    * grid find
    * @author  youngjoo38
    * @version $Id$
    * @since   1.0
    * 
    * @param request
    * @param maEqMstrListForm
    * @throws Exception
    */
   private void findEqITList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
   {
       MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

       MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
       maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
       
       maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
	   maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
	   maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
	   maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
   	
       //리스트를 조회한다.
       List resultList = maEqMstrListService.findEqITList(maEqMstrCommonDTO, getUser(request));

       String totalCount = "";
       if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findITTotalCount(maEqMstrCommonDTO,getUser(request));

       if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
       else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
   }
   
   /**
    * grid find
    * @author  js.lee
    * @version $Id$
    * @since   1.0
    * 
    * @param request
    * @param maEqMstrListForm
    * @throws Exception
    */
   private void findEqGNList(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm, HttpServletResponse response, boolean excelExport) throws IOException
   {
       MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");        

       MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
       maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
       
       maEqMstrCommonDTO.setIsLoadMaxCount("Y".equals(maEqMstrListForm.getIsLoadMaxCount())?true:false);
	   maEqMstrCommonDTO.setFirstRow(maEqMstrListForm.getFirstRow());
	   maEqMstrCommonDTO.setOrderBy(maEqMstrListForm.getOrderBy());
	   maEqMstrCommonDTO.setDirection(maEqMstrListForm.getDirection());
   	
       //리스트를 조회한다.
       List resultList = maEqMstrListService.findEqGNList(maEqMstrCommonDTO, getUser(request));

       String totalCount = "";
       if(Integer.parseInt(maEqMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrListService.findGNTotalCount(maEqMstrCommonDTO,getUser(request));

       if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrListForm);
       else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
   }
   
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaEqMstrListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrListForm
     * @param request
     */
    private void deleteEqMstr(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm) throws Exception
    {
    	MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");
    	String[] deleteRows = maEqMstrListForm.getDeleteRows();    // sheet 내역
        
        maEqMstrListService.deleteEqMstr(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void insertQrCode(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm ) throws Exception
    {	
    	MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");
    	String[] selectRows = maEqMstrListForm.getSelectRows();

    	maEqMstrListService.insertQrCode(selectRows,maEqMstrListForm.getFileName(), getUser(request));
        
        setAjaxStatus(request);
    }
    private void insertListQrCode(HttpServletRequest request, MaEqMstrListForm maEqMstrListForm ) throws Exception
    {	
    	MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
    	maEqMstrListService.insertListQrCode(maEqMstrCommonDTO,maEqMstrListForm.getFileName(), getUser(request));
    	
    	setAjaxStatus(request);
    }
    private void copyEquipment(HttpServletRequest request,HttpServletResponse response, MaEqMstrListForm maEqMstrListForm ) throws Exception
    {	
    	MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");
    	String[] selectRows = maEqMstrListForm.getSelectRows();

    	List eqIdList = maEqMstrListService.copyEquipment(selectRows, getUser(request));
    	Map result = CommonUtil.makeResultJson(eqIdList);
    	
        Gson gson = new Gson();
        String jsonString = gson.toJson(result);
        response.getWriter().print(jsonString);
//        setAjaxStatus(request);
    }
    
    private void getData(HttpServletRequest request, HttpServletResponse response, MaEqMstrListForm maEqMstrListForm) throws Exception
    {
    	MaEqMstrListService maEqMstrListService = (MaEqMstrListService) getBean("maEqMstrListService");
    	
    	User user = getUser(request);
    	MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrListForm.getMaEqMstrCommonDTO();
    	
    	String result = maEqMstrListService.getData(maEqMstrCommonDTO, user);
    	
    	setAjaxDesc(request, result);
    }
}
