package dream.ass.asset.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;
import dream.ass.asset.form.AssAssetScoreCopyLovForm;
import dream.ass.asset.service.AssAssetScoreCopyLovService;

/**
 * 평가결과복사 Lov
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 * 
 * @struts:action path="/assAssetScoreCopyLov" name="assAssetScoreCopyLovForm"
 *                input="/dream/ass/asset/assAssetScoreCopyLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assAssetScoreCopyLov" path="/dream/ass/asset/assAssetScoreCopyLov.jsp"
 *                        redirect="false"
 */
public class AssAssetScoreCopyLovAction extends SuperAuthAction
{
    public static final int LOV_ASS_DEFAULT 	    = 1001;
    public static final int LOV_ASS_FIND	 		= 1002;
    public static final int LOV_ASS_SCORE_FIND	 	= 1003;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        AssAssetScoreCopyLovForm assAssetScoreCopyLovForm = (AssAssetScoreCopyLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (assAssetScoreCopyLovForm.getStrutsAction())
        {
            case AssAssetScoreCopyLovAction.LOV_ASS_DEFAULT :
                returnActionForward = mapping.findForward("assAssetScoreCopyLov");
                break;
            case AssAssetScoreCopyLovAction.LOV_ASS_FIND :
                findAssList(request, assAssetScoreCopyLovForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssAssetScoreCopyLovAction.BASE_SET_HEADER:
                setHeader(request, response, assAssetScoreCopyLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssAssetScoreCopyLovAction.LOV_ASS_SCORE_FIND :
                findAssScoreList(request, assAssetScoreCopyLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, AssAssetScoreCopyLovForm assAssetScoreCopyLovForm) throws IOException
    {
        super.setHeader(request, response, assAssetScoreCopyLovForm.getListId(),assAssetScoreCopyLovForm.getCurrentPageId()); 
    }

    /**
     * 평가결과 목록 검색
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assAssetScoreCopyLovForm
     */
    private void findAssList(HttpServletRequest request,AssAssetScoreCopyLovForm assAssetScoreCopyLovForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
        AssAssetScoreCopyLovService assAssetScoreCopyLovService = (AssAssetScoreCopyLovService)getBean("assAssetScoreCopyLovService");
        AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO = assAssetScoreCopyLovForm.getAssAssetScoreCopyLovDTO();
        
        //Paging
        assAssetScoreCopyLovDTO.setIsLoadMaxCount("Y".equals(assAssetScoreCopyLovForm.getIsLoadMaxCount())?true:false);
        assAssetScoreCopyLovDTO.setFirstRow(assAssetScoreCopyLovForm.getFirstRow());
        assAssetScoreCopyLovDTO.setOrderBy(assAssetScoreCopyLovForm.getOrderBy());
        assAssetScoreCopyLovDTO.setDirection(assAssetScoreCopyLovForm.getDirection());
        
        List resultList = assAssetScoreCopyLovService.findAssList(assAssetScoreCopyLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(assAssetScoreCopyLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assAssetScoreCopyLovService.findTotalCount(assAssetScoreCopyLovForm,getUser(request));

        
        super.makeJsonResult(resultList, request, response, assAssetScoreCopyLovForm.getListId());
    	
    }
    
    /**
     * 평가점수 목록 검색
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assAssetScoreCopyLovForm
     */
    private void findAssScoreList(HttpServletRequest request,AssAssetScoreCopyLovForm assAssetScoreCopyLovForm,HttpServletResponse response) throws IOException
    {
        AssAssetScoreCopyLovService assAssetScoreCopyLovService = (AssAssetScoreCopyLovService)getBean("assAssetScoreCopyLovService");
        
        AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO = assAssetScoreCopyLovForm.getAssAssetScoreCopyLovDTO();
        List resultList = assAssetScoreCopyLovService.findAssScoreList(assAssetScoreCopyLovDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, assAssetScoreCopyLovForm.getListId());
    	
    }

}