package dream.part.iss.emg.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.iss.emg.list.dto.LovEmgPartListDTO;
import dream.part.iss.emg.list.form.LovEmgPartListForm;
import dream.part.iss.emg.list.service.LovEmgPartListService;

/**
 * 긴급출고자재 선택팝업
 * @author  jung7126
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEmgPartList" name="lovEmgPartListForm"
 *                input="/dream/part/iss/emg/list/lovEmgPartPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEmgPartPopup" path="/dream/part/iss/emg/list/lovEmgPartPopup.jsp"
 *                        redirect="false"
 */
public class LovEmgPartListAction extends AuthAction
{
    public static final int LOV_EMGPART_DEFAULT 	= 1001;
    
    public static final int LOV_EMGPART_FIND     	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEmgPartListForm lovEmgPartListForm = (LovEmgPartListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEmgPartListForm.getStrutsAction())
        {
            case LovEmgPartListAction.LOV_EMGPART_DEFAULT :
                returnActionForward = mapping.findForward("lovEmgPartPopup");
                break;
            case LovEmgPartListAction.LOV_EMGPART_FIND :
                findEmgPart(request, lovEmgPartListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEmgPartListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEmgPartListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEmgPartListAction.BASE_GRID_EXPORT:
            	findEmgPart(request, lovEmgPartListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default :
            	returnActionForward = mapping.findForward("lovEmgPartPopup");
                break;
        }
        
        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEmgPartListForm lovEmgPartListForm) throws IOException
    {
        super.setHeader(request, response, lovEmgPartListForm.getListId(),lovEmgPartListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEmgPartListForm
     */
    private void findEmgPart(HttpServletRequest request, LovEmgPartListForm lovEmgPartListForm,HttpServletResponse response, boolean excelExport) throws Exception
    {
    	LovEmgPartListService lovEmgPartListService = (LovEmgPartListService)CommonUtil.getBean("lovEmgPartListService", getUser(request));
        
        LovEmgPartListDTO lovEmgPartListDTO = lovEmgPartListForm.getLovEmgPartListDTO();
        
        //Paging
        lovEmgPartListDTO.setIsLoadMaxCount("Y".equals(lovEmgPartListForm.getIsLoadMaxCount())?true:false);
        lovEmgPartListDTO.setFirstRow(lovEmgPartListForm.getFirstRow());
        lovEmgPartListDTO.setOrderBy(lovEmgPartListForm.getOrderBy());
        lovEmgPartListDTO.setDirection(lovEmgPartListForm.getDirection());
        
        User user = getUser(request);
        List resultList = lovEmgPartListService.findEmgPartList(lovEmgPartListDTO, getUser(request));
        
      //Paging
        String totalCount = "";
        
        if(Integer.parseInt(lovEmgPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovEmgPartListService.findTotalCount(lovEmgPartListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, lovEmgPartListForm);
        else super.makeJsonResult(resultList, request, response, totalCount);
    	
    }

}