package dream.asset.rpt.bdintensity.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.rpt.bdintensity.form.AssetRptBdIntensityDetailListForm;
import dream.asset.rpt.bdintensity.service.AssetRptBdIntensityDetailListService;

/**
 * 에너지사용량(일별) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptBdIntensityFreqRateDetailChart" name="assetRptBdIntensityDetailListForm"
 *                input="/dream/asset/rpt/bdintensity/assetRptBdIntensityFreqRateDetailChart.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptBdIntensityDuraRateDetailChart" name="assetRptBdIntensityDetailListForm"
 *                input="/dream/asset/rpt/bdintensity/assetRptBdIntensityDuraRateDetailChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptBdIntensityFreqRateDetailChart" path="/dream/asset/rpt/bdintensity/assetRptBdIntensityFreqRateDetailChart.jsp"
 *                        redirect="false"
 * @struts.action-forward name="assetRptBdIntensityDuraRateDetailChart" path="/dream/asset/rpt/bdintensity/assetRptBdIntensityDuraRateDetailChart.jsp"
 *                        redirect="false"
 */
public class AssetRptBdIntensityDetailListAction extends AuthAction
{
    public static final int DETAIL_FREQ_FIND 	= 1001;
    public static final int DETAIL_DURA_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm = (AssetRptBdIntensityDetailListForm) form;
        switch (assetRptBdIntensityDetailListForm.getStrutsAction())
        {
            case AssetRptBdIntensityDetailListAction.DETAIL_FREQ_FIND:
                this.findFreqChart(request, response, assetRptBdIntensityDetailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptBdIntensityDetailListAction.DETAIL_DURA_FIND:
            	this.findDuraChart(request, response, assetRptBdIntensityDetailListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AssetRptBdIntensityDetailListAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptBdIntensityDetailListForm.getListId(), assetRptBdIntensityDetailListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param assetRptBdIntensityDetailListForm
     */
    private void findFreqChart(HttpServletRequest request,HttpServletResponse response, AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm) throws Exception
    {
        AssetRptBdIntensityDetailListService assetRptBdIntensityDetailListService = (AssetRptBdIntensityDetailListService) getBean("assetRptBdIntensityDetailListService",request);
        
        List resultList = assetRptBdIntensityDetailListService.findFreqChart(assetRptBdIntensityDetailListForm, getUser(request));
        
        super.makeJsonResult(resultList, request, response, assetRptBdIntensityDetailListForm.getListId());
    }
    private void findDuraChart(HttpServletRequest request,HttpServletResponse response, AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm) throws Exception
    {
    	AssetRptBdIntensityDetailListService assetRptBdIntensityDetailListService = (AssetRptBdIntensityDetailListService) getBean("assetRptBdIntensityDetailListService",request);
    	
    	List resultList = assetRptBdIntensityDetailListService.findDuraChart(assetRptBdIntensityDetailListForm, getUser(request));
    	
    	super.makeJsonResult(resultList, request, response, assetRptBdIntensityDetailListForm.getListId());
    }
    
}