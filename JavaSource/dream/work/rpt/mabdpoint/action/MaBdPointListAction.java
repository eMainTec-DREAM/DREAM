package dream.work.rpt.mabdpoint.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.form.MaBdPointListForm;
import dream.work.rpt.mabdpoint.service.MaBdPointListService;

/**
 * 이상점검조치 - 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maBdPointList" name="maBdPointListForm"
 *                input="/dream/work/rpt/bdpoint/maBdPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBdPointList" path="/dream/work/rpt/bdpoint/maBdPointList.jsp"
 *                        redirect="false"
 */
public class MaBdPointListAction extends AuthAction
{
    /** 조회 */
    public static final int BD_LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBdPointListForm maBdPointListForm = (MaBdPointListForm) form;
        
        super.updateAudit(maBdPointListForm.getMaBdPointCommonDTO().getAuditKey()==""?maBdPointListForm.getMaBdPointCommonDTO().getAuditKey():maBdPointListForm.getMaBdPointCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maBdPointListForm.getStrutsAction())
        {
            case MaBdPointListAction.BD_LIST_FIND:
            	findBdList(request, maBdPointListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointListAction.BASE_SET_HEADER:
                setHeader(request, response, maBdPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointListAction.BASE_GRID_EXPORT:
            	findBdList(request, maBdPointListForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maBdPointList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaBdPointListForm maBdPointListForm) throws IOException
    {
        super.setHeader(request, response, maBdPointListForm.getListId(), maBdPointListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointListForm
     * @throws Exception
     */
    private void findBdList(HttpServletRequest request, MaBdPointListForm maBdPointListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaBdPointListService maBdPointListService = (MaBdPointListService) getBean("maBdPointListService");        

    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointListForm.getMaBdPointCommonDTO();
    	maBdPointCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        maBdPointCommonDTO.setIsLoadMaxCount("Y".equals(maBdPointListForm.getIsLoadMaxCount())?true:false);
        maBdPointCommonDTO.setFirstRow(maBdPointListForm.getFirstRow());
        maBdPointCommonDTO.setOrderBy(maBdPointListForm.getOrderBy());
        maBdPointCommonDTO.setDirection(maBdPointListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maBdPointListService.findList(maBdPointCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maBdPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maBdPointListService.findTotalCount(maBdPointCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maBdPointListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);

    }
}
