package dream.main.board.dao;

import java.util.List;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.main.board.dto.BoardPopupCommonDTO;

/**
 * 게시판 - 목록
 * @author  pochul2423
 * @version $Id: BoardPopupListDAO.java,v 1.2 2014/01/14 01:35:49 pochul2423 Exp $
 * @since   1.0
 */
public interface BoardPopupListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  pochul2423
     * @version $Id: BoardPopupListDAO.java,v 1.2 2014/01/14 01:35:49 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupCommonDTO
     * @return List
     */
    public List findBoardPopupList(BoardPopupCommonDTO BoardPopupCommonDTO);
}