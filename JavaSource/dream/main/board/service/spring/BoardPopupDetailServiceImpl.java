package dream.main.board.service.spring;

import dream.main.board.dao.BoardPopupDetailDAO;
import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.dto.BoardPopupDetailDTO;
import dream.main.board.service.BoardPopupDetailService;

/**
 * 게시판 - 상세
 * @author pochul2423
 * @version $Id: BoardPopupDetailServiceImpl.java,v 1.2 2005/05/26 01:16:18 
 *          Exp $
 * @since 1.0
 * @spring.bean id="boardPopupDetailServiceTarget"
 * @spring.txbn id="boardPopupDetailService"
 * @spring.property name="boardPopupDetailDAO" ref="boardPopupDetailDAO"
 */
public class BoardPopupDetailServiceImpl implements BoardPopupDetailService
{
    private BoardPopupDetailDAO BoardPopupDetailDAO;

    public BoardPopupDetailDAO getBoardPopupDetailDAO()
    {
        return BoardPopupDetailDAO;
    }

    public void setBoardPopupDetailDAO(BoardPopupDetailDAO BoardPopupDetailDAO)
    {
        this.BoardPopupDetailDAO = BoardPopupDetailDAO;
    }

    public BoardPopupDetailDTO findDetail(BoardPopupCommonDTO BoardPopupCommonDTO)
    {
        return BoardPopupDetailDAO.findDetail(BoardPopupCommonDTO);
    }

    public void insertDetail(BoardPopupDetailDTO BoardPopupDetailDTO)
    {
        BoardPopupDetailDAO.insertBoardHdr(BoardPopupDetailDTO);
    }
    
    public void updateDetail(BoardPopupDetailDTO BoardPopupDetailDTO)
    {
        BoardPopupDetailDAO.updateBoardHdr(BoardPopupDetailDTO);
    }
    public void deleteDetail(BoardPopupDetailDTO BoardPopupDetailDTO)
    {
        BoardPopupDetailDAO.deleteBoardHdr(BoardPopupDetailDTO);
    }
    public String findNextReply(String boardNo)
    {
        return BoardPopupDetailDAO.findNextReply(boardNo);
    }
}