package dream.part.rec.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;
import dream.part.rec.form.MaPtRecSerialListForm;
import dream.part.rec.service.MaPtRecSerialListService;

/**
 * 구매입고 item  목록
 * @author  kim21017
 * @version $Id: MaPtRecSerialListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPtRecSerialList" name="maPtRecSerialListForm"
 *                input="/dream/part/rec/maPtRecSerialList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRecSerialList" path="/dream/part/rec/maPtRecSerialList.jsp"
 *                        redirect="false"
 */
public class MaPtRecSerialListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int BUY_ITEM_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int BUY_ITEM_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRecSerialListForm maPtRecSerialListForm = (MaPtRecSerialListForm) form;
        
        switch (maPtRecSerialListForm.getStrutsAction())
        {
            case MaPtRecListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maPtRecSerialListForm.getListId(), maPtRecSerialListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRecSerialListAction.BUY_ITEM_LIST_FIND:
                findItemList(request, response, maPtRecSerialListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRecSerialListAction.BUY_ITEM_LIST_DELETE:
            	deleteItemList(request,maPtRecSerialListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRecSerialListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, maPtRecSerialListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRecSerialList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPtRecSerialListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtRecSerialListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, MaPtRecSerialListForm maPtRecSerialListForm) throws Exception
    {
        MaPtRecSerialListService maPtRecSerialListService = (MaPtRecSerialListService) getBean("maPtRecSerialListService");
        MaPtRecCommonDTO maPtRecCommonDTO = maPtRecSerialListForm.getMaPtRecCommonDTO();
        maPtRecCommonDTO.setCompNo(getUser(request).getCompNo());
        MaPtRecSerialListDTO maPtRecSerialListDTO = maPtRecSerialListForm.getMaPtRecSerialListDTO();
        List resultList = maPtRecSerialListService.findItemList(maPtRecCommonDTO, maPtRecSerialListDTO);
        
        super.makeJsonResult(resultList, request, response, maPtRecSerialListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPtRecSerialListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtRecSerialListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, MaPtRecSerialListForm maPtRecSerialListForm) throws Exception
    {
    	MaPtRecSerialListService maPtRecSerialListService = (MaPtRecSerialListService) getBean("maPtRecSerialListService");
        
    	maPtRecSerialListService.deleteItemList(maPtRecSerialListForm.getDeleteRows(), maPtRecSerialListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}