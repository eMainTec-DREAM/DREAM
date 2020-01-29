package dream.part.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.PartListImgLinkUrlDTO;
import dream.part.list.form.PartListImgLinkUrlForm;
import dream.part.list.service.PartListImgLinkUrlService;

/**
 * 부품Image Link Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @struts:action path="/partListImgLinkUrlList" name="partListImgLinkUrlForm"
 *                input="/dream/part/list/partListImgLinkUrlList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/partListImgLinkUrlDetail" name="partListImgLinkUrlForm"
 *                input="/dream/part/list/partListImgLinkUrlDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partListImgLinkUrlDetail" path="/dream/part/list/partListImgLinkUrlDetail.jsp"
 *                        redirect="false"
 */

public class PartListImgLinkUrlAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 저장 */
    public static final int DETAIL_INPUT    = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE   = 6003;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT     = 8001;
    /** 이미지 수신 */
    public static final int GET_IMAGE       = 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartListImgLinkUrlForm partListImgLinkUrlForm = (PartListImgLinkUrlForm) form;
        
        switch (partListImgLinkUrlForm.getStrutsAction())
        {
            case PartListImgLinkUrlAction.BASE_SET_HEADER:
                setHeader(request, response, partListImgLinkUrlForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartListImgLinkUrlAction.LIST_FIND:
                findList(request, response, partListImgLinkUrlForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartListImgLinkUrlAction.LIST_DELETE:
            	deleteList(request, partListImgLinkUrlForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case PartListImgLinkUrlAction.BASE_GRID_EXPORT:
            	findList(request, response, partListImgLinkUrlForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case PartListImgLinkUrlAction.DETAIL_INIT:
                findDetail(request, response, partListImgLinkUrlForm);
                returnActionForward = mapping.findForward("partListImgLinkUrlDetail");
                break;
            case PartListImgLinkUrlAction.DETAIL_INPUT:
                insertDetail(request, response, partListImgLinkUrlForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartListImgLinkUrlAction.DETAIL_UPDATE:
                updateDetail(request, response, partListImgLinkUrlForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartListImgLinkUrlAction.GET_IMAGE:
                getImage(request, response, partListImgLinkUrlForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartListImgLinkUrlForm partListImgLinkUrlForm) throws IOException
    {
        super.setHeader(request, response, partListImgLinkUrlForm.getListId(), partListImgLinkUrlForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, PartListImgLinkUrlForm partListImgLinkUrlForm, boolean excelExport) throws Exception
    {
    	PartListImgLinkUrlService partListImgLinkUrlService = (PartListImgLinkUrlService) getBean("partListImgLinkUrlService");
    	PartListImgLinkUrlDTO partListImgLinkUrlDTO = partListImgLinkUrlForm.getPartListImgLinkUrlDTO();
    	MaPtMstrCommonDTO maPtMstrCommonDTO = partListImgLinkUrlForm.getMaPtMstrCommonDTO();

    	//Paging
    	partListImgLinkUrlDTO.setIsLoadMaxCount("Y".equals(partListImgLinkUrlForm.getIsLoadMaxCount())?true:false);
    	partListImgLinkUrlDTO.setFirstRow(partListImgLinkUrlForm.getFirstRow());
    	partListImgLinkUrlDTO.setOrderBy(partListImgLinkUrlForm.getOrderBy());
    	partListImgLinkUrlDTO.setDirection(partListImgLinkUrlForm.getDirection());
    	
    	partListImgLinkUrlDTO.setPartId(maPtMstrCommonDTO.getPartId());
    	
        List resultList = partListImgLinkUrlService.findList(partListImgLinkUrlDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(partListImgLinkUrlForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partListImgLinkUrlService.findTotalCount(partListImgLinkUrlDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,partListImgLinkUrlForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, PartListImgLinkUrlForm partListImgLinkUrlForm) throws Exception
    {
    	PartListImgLinkUrlService partListImgLinkUrlService = (PartListImgLinkUrlService) getBean("partListImgLinkUrlService");
        String[] deleteRows = partListImgLinkUrlForm.getDeleteRows();
        
        partListImgLinkUrlService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PartListImgLinkUrlForm partListImgLinkUrlForm) throws Exception 
    {   
        PartListImgLinkUrlService partListImgLinkUrlService = (PartListImgLinkUrlService)getBean("partListImgLinkUrlService");
        
        PartListImgLinkUrlDTO partListImgLinkUrlDTO = partListImgLinkUrlForm.getPartListImgLinkUrlDTO(); 

        partListImgLinkUrlDTO = partListImgLinkUrlService.findDetail(partListImgLinkUrlDTO, getUser(request));
        partListImgLinkUrlForm.setPartListImgLinkUrlDTO(partListImgLinkUrlDTO);
    }

    private void insertDetail(HttpServletRequest request, HttpServletResponse response, PartListImgLinkUrlForm partListImgLinkUrlForm) throws Exception
    {
        PartListImgLinkUrlService partListImgLinkUrlService = (PartListImgLinkUrlService)getBean("partListImgLinkUrlService");
        PartListImgLinkUrlDTO  partListImgLinkUrlDTO = partListImgLinkUrlForm.getPartListImgLinkUrlDTO(); 
        
        partListImgLinkUrlService.insertDetail(partListImgLinkUrlDTO, getUser(request));
        setAjaxStatus(request);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response, PartListImgLinkUrlForm partListImgLinkUrlForm) throws Exception
    {
        PartListImgLinkUrlService partListImgLinkUrlService = (PartListImgLinkUrlService)getBean("partListImgLinkUrlService");
        PartListImgLinkUrlDTO  partListImgLinkUrlDTO = partListImgLinkUrlForm.getPartListImgLinkUrlDTO();
        
        partListImgLinkUrlService.updateDetail(partListImgLinkUrlDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void getImage(HttpServletRequest request, HttpServletResponse response, PartListImgLinkUrlForm partListImgLinkUrlForm) throws Exception
    {
        PartListImgLinkUrlService partListImgLinkUrlService = (PartListImgLinkUrlService)getBean("partListImgLinkUrlService");
        PartListImgLinkUrlDTO  partListImgLinkUrlDTO = partListImgLinkUrlForm.getPartListImgLinkUrlDTO();
        
        Map<String,String> rtnMsg = partListImgLinkUrlService.getImage(partListImgLinkUrlDTO, getUser(request));
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(rtnMsg);

        response.getWriter().print(jsonString);
    }
}
