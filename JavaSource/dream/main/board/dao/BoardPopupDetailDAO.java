package dream.main.board.dao;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.dto.BoardPopupDetailDTO;

/**
 * 게시판 - 상세
 * @author pochul2423
 * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
 * @since 1.0
 */
public interface BoardPopupDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * 게시판 상세 Header 조회
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupCommonDTO
     * @return
     */
    public BoardPopupDetailDTO findDetail(BoardPopupCommonDTO BoardPopupCommonDTO);

    /**
     * insert Header
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @return 
     */
    public int insertBoardHdr(BoardPopupDetailDTO BoardPopupDetailDTO);

    /**
     * update Header
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @return 
     */
    public int updateBoardHdr(BoardPopupDetailDTO BoardPopupDetailDTO);
    
    /**
     * delete Header
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @return 
     */
    public int deleteBoardHdr(BoardPopupDetailDTO BoardPopupDetailDTO);
    
    /**
     * sequence nextReply
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param boardNo
     * @return
     */
    public String findNextReply(String boardNo);
}