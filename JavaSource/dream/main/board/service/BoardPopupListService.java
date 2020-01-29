package dream.main.board.service;

import java.util.List;

import dream.main.board.dto.BoardPopupCommonDTO;

/**
 * 게시판- 목록
 * @author  pochul2423
 * @version $Id: BoardPopupListService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
 * @since   1.0
 */
public interface BoardPopupListService
{     
    /**
     *  grid find
     * @author  pochul2423
     * @version $Id: BoardPopupListService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @param BoardPopupCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBoardPopupList(BoardPopupCommonDTO BoardPopupCommonDTO);    

}