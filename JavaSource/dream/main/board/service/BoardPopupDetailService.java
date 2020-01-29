package dream.main.board.service;

import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.dto.BoardPopupDetailDTO;

/**
 * 게시판 - 상세
 * @author  pochul2423
 * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
 * @since   1.0
 *
 */
public interface BoardPopupDetailService
{

    /**
     * 공지사항 Detail 해더 조회
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupCommonDTO
     * @return
     */
    BoardPopupDetailDTO findDetail(BoardPopupCommonDTO BoardPopupCommonDTO);

    /**
     * 공지사항 Detail Sheet 저장
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO :헤더 부분
     * @return 
     */
    void insertDetail(BoardPopupDetailDTO BoardPopupDetailDTO);

    /**
     * 상세내역에 값을 수정한다.
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @throws Exception
     */
    void updateDetail(BoardPopupDetailDTO BoardPopupDetailDTO);
    
    /**
     * 상세내역에 값을 삭제한다.
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @throws Exception
     */
    void deleteDetail(BoardPopupDetailDTO BoardPopupDetailDTO);
    
    /**
     * sequence nextReply
     * @author  pochul2423
     * @version $Id: BoardPopupDetailService.java,v 1.2 2014/01/14 01:35:48 pochul2423 Exp $
     * @since   1.0
     * 
     * @param sequenceName
     * @return
     */
    String findNextReply(String boardNo);
}