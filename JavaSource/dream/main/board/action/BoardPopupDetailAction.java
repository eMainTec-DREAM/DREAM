package dream.main.board.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.dto.BoardPopupDetailDTO;
import dream.main.board.form.BoardPopupDetailForm;
import dream.main.board.service.BoardPopupDetailService;

/**
 * 게시판 - 상세
 * @author  pochul2423
 * @version $Id: BoardPopupDetailAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
 * @since   1.0
 * @struts.action path="/boardPopupDetail" name="boardPopupDetailForm"
 *                input="/dream/main/board/boardPopupDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="boardPopupDetail" path="/dream/main/board/boardPopupDetail.jsp"
 *                        redirect="false"       
 * @struts.action-forward name="ajaxXmlVal" path="/dream/common/ajax/ajaxXmlVal.jsp"
 *                        redirect="false"                               
 */
public class BoardPopupDetailAction extends AuthAction
{
    /*** 조회 **/
    public static final int BD_DETAIL_FIND        = 1001;
    /*** 입력 **/
    public static final int BD_DETAIL_INPUT       = 1002;
    /*** 수정 **/
    public static final int BD_DETAIL_UPDATE      = 1003;
    /*** 삭제 **/
    public static final int BD_DETAIL_DELETE      = 1004;
    /*** 덧글SeqNo **/
    public static final int BD_DETAIL_REPLYSEQ    = 1005;
    /*** 덧글추가 **/
    public static final int BD_DETAIL_REPLY       = 1006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        
        BoardPopupDetailForm BoardPopupDetailForm = (BoardPopupDetailForm) form;

        switch (BoardPopupDetailForm.getStrutsAction())
        {
            case BD_DETAIL_INPUT:
                insertBoardDetail(BoardPopupDetailForm, request);
                returnActionForward = mapping.findForward("boardPopupDetail");
                break;
            case BD_DETAIL_REPLY:
                insertBoardDetail(BoardPopupDetailForm, request);
                returnActionForward = mapping.findForward("boardPopupDetail");
                break;
            case BD_DETAIL_UPDATE:
                updateBoardDetail(BoardPopupDetailForm, request);
                returnActionForward = mapping.findForward("boardPopupDetail");
                break;
            case BD_DETAIL_DELETE:
                deleteBoardDetail(BoardPopupDetailForm, request);
                returnActionForward = mapping.findForward("boardPopupDetail");
                break;
            case BD_DETAIL_REPLYSEQ:
                findNextReply(BoardPopupDetailForm,request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case BD_DETAIL_FIND:
            default:
                findBoardDetail(BoardPopupDetailForm, request);
                returnActionForward = mapping.findForward("boardPopupDetail");
                break;
        }
        
        return returnActionForward;
    }

    /**
     * detail find
     * @author  pochul2423
     * @version $Id: BoardPopupDetailAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailForm
     * @param request
     */
    private void findBoardDetail(BoardPopupDetailForm BoardPopupDetailForm, HttpServletRequest request) throws Exception
    {
        BoardPopupDetailService BoardPopupDetailService = (BoardPopupDetailService) getBean("boardPopupDetailService");
        
        BoardPopupCommonDTO BoardPopupCommonDTO = BoardPopupDetailForm.getBoardPopupCommonDTO();
        
        BoardPopupDetailDTO BoardPopupDetailDTO = BoardPopupDetailService.findDetail(BoardPopupCommonDTO);
        BoardPopupDetailForm.setBoardPopupDetailDTO(BoardPopupDetailDTO);
    }

    /**
     * detail modify
     * @author  pochul2423
     * @version $Id: BoardPopupDetailAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailForm
     * @param request
     */
    private void updateBoardDetail(BoardPopupDetailForm BoardPopupDetailForm, HttpServletRequest request)
    {
        BoardPopupDetailService BoardPopupDetailService = (BoardPopupDetailService) getBean("boardPopupDetailService");
        
        BoardPopupDetailDTO BoardPopupDetailDTO = BoardPopupDetailForm.getBoardPopupDetailDTO(); //상세해더

        //접속한 User 의 아이디를 Setting
        String userId = getUser(request).getUserId();
       
        BoardPopupDetailDTO.setEnterBy(userId);
       
        BoardPopupDetailService.updateDetail(BoardPopupDetailDTO); 
               
        setAjaxStatus(request);
    }

    /**
     * detail delete
     * @author  pochul2423
     * @version $Id: BoardPopupDetailAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailForm
     * @param request
     */
    private void deleteBoardDetail(BoardPopupDetailForm BoardPopupDetailForm, HttpServletRequest request)
    {
        BoardPopupDetailService BoardPopupDetailService = (BoardPopupDetailService) getBean("boardPopupDetailService");
        
        BoardPopupDetailDTO BoardPopupDetailDTO = BoardPopupDetailForm.getBoardPopupDetailDTO(); //상세해더

        //접속한 User 의 아이디를 Setting
        String userId = getUser(request).getUserId();
       
        BoardPopupDetailDTO.setEnterBy(userId);
       
        BoardPopupDetailService.deleteDetail(BoardPopupDetailDTO); 
               
        setAjaxStatus(request);
    }
    
    /**
     * detail save
     * @author  pochul2423
     * @version $Id: BoardPopupDetailAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailForm
     * @param request
     */
    private void insertBoardDetail(BoardPopupDetailForm BoardPopupDetailForm, HttpServletRequest request)
    {
        BoardPopupDetailService BoardPopupDetailService = (BoardPopupDetailService) getBean("boardPopupDetailService");
        
        BoardPopupDetailDTO BoardPopupDetailDTO = BoardPopupDetailForm.getBoardPopupDetailDTO(); //상세해더
        
        //접속한 User 의 아이디를 Setting
        String userId = getUser(request).getUserId();
        BoardPopupDetailDTO.setEnterBy(userId);

        BoardPopupDetailService.insertDetail(BoardPopupDetailDTO);

        setAjaxStatus(request);
    }
    
    /**
     * sequence nextReply
     * @author  pochul2423
     * @version $Id: BoardPopupDetailAction.java,v 1.2 2014/01/14 01:35:45 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param BoardPopupDetailForm
     */
    private void findNextReply(BoardPopupDetailForm BoardPopupDetailForm,HttpServletRequest request)
    {
        BoardPopupDetailService boardPopupDetailService = (BoardPopupDetailService)getBean("boardPopupDetailService");
        
        String boardNo = BoardPopupDetailForm.getBoardPopupCommonDTO().getBoardNo();
      
        String bdViewNo = boardPopupDetailService.findNextReply(boardNo);
        
        setAjaxId(request, boardNo);
        setAjaxDesc(request, bdViewNo);
    }
}