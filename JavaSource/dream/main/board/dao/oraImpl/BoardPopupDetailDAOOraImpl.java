package dream.main.board.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;

import dream.main.board.dao.BoardPopupDetailDAO;
import dream.main.board.dto.BoardPopupCommonDTO;
import dream.main.board.dto.BoardPopupDetailDTO;

/**
 * 게시판 - 상세
 * @author pochul2423
 * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
 * @since 1.0
 * @spring.bean id="boardPopupDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BoardPopupDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements BoardPopupDetailDAO
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
    public BoardPopupDetailDTO findDetail(BoardPopupCommonDTO BoardPopupCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       DECODE(x.parent_no,'0','',(SELECT                  ");
        query.append("                                         y.bd_view_no     ");
        query.append("                                    FROM T2BOARD y        ");
        query.append("                                   WHERE x.parent_no=y.board_no )) parentNo,       ");
        query.append("       x.board_no boardNo,            ");
        query.append("       x.bd_view_no bdViewNo,         ");
        query.append("       x.description,                 ");
        query.append("       x.enter_date enterDate,        ");
        query.append("       x.enter_by enterBy,            ");
        query.append("       SF2CODETODESC(x.enter_by , 'USER') enterByName,    ");
        query.append("       DECODE((                       ");
        query.append("               SELECT COUNT(*)        ");
        query.append("               FROM   T2BOARD y       ");
        query.append("               WHERE y.parent_no='" + BoardPopupCommonDTO.getBoardNo() +"'     ");
        query.append("               ),'0','Y','N')deletable,                   ");
        query.append("       x.contents                     ");
        query.append("FROM   T2BOARD x                      ");
        query.append("WHERE  1=1                            ");
        query.append("AND    x.board_no = '" + BoardPopupCommonDTO.getBoardNo() +"' ");
        
        BoardPopupDetailDTO BoardPopupDetailDTO = (BoardPopupDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new BoardPopupDetailDTO()));
        
        return BoardPopupDetailDTO; 
    }

    /**
     * insert Header
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @return 
     */
    public int insertBoardHdr(BoardPopupDetailDTO BoardPopupDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO T2BOARD (                                 ");
        query.append("    board_no,      description,                       ");
        query.append("    contents,       enter_by,                         ");
        query.append("    enter_date,    parent_no,                         ");
        query.append("    bd_view_no                                        ");
        query.append(")                                                     ");
        query.append("VALUES(                                               ");
        query.append("    ?,                 ?,                             ");
        query.append("    ?,                 ?,                             ");
        query.append("    TO_CHAR(SYSDATE, 'YYYYMMDD'),  ?,                 ");
        query.append("    ?                                                 ");
        query.append(")                                                     ");

        Object[] objects = {
                BoardPopupDetailDTO.getBoardNo(),
                BoardPopupDetailDTO.getDescription(),
                BoardPopupDetailDTO.getContents(),
                BoardPopupDetailDTO.getEnterBy(),
                BoardPopupDetailDTO.getParentNo(),
                BoardPopupDetailDTO.getBdViewNo()
        };

        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * update Header
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @return 
     */
    public int updateBoardHdr(BoardPopupDetailDTO BoardPopupDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE T2BOARD                                          ");
        query.append("SET    description    = ?,                              ");
        query.append("       contents       = ?,                              ");
        query.append("       update_date = TO_CHAR(SYSDATE, 'YYYYMMDD'),      ");
        query.append("       update_by      = ?                               ");
        query.append("WHERE  board_no       = ?                               ");
        
        Object[] objects = {
                BoardPopupDetailDTO.getDescription(),
                BoardPopupDetailDTO.getContents(),          
                BoardPopupDetailDTO.getEnterBy(),
                BoardPopupDetailDTO.getBoardNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects); 
    }
    
    /**
     * delete Header
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupDetailDTO
     * @return 
     */
    public int deleteBoardHdr(BoardPopupDetailDTO BoardPopupDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE T2BOARD                                         ");
        query.append("WHERE  board_no      = ?                               ");
        
        Object[] objects = {
                BoardPopupDetailDTO.getBoardNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects); 
    }
    
    /**
     * sequence nextReply
     * @author  pochul2423
     * @version $Id: BoardPopupDetailDAO.java,v 1.3 2014/01/20 00:36:09 pochul2423 Exp $
     * @since   1.0
     * 
     * @param boardNo
     * @return
     */
    public String findNextReply(String boardNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT   ");
        query.append("       DECODE(COUNT(*),'0',('" + boardNo +"'||CHR(45)||(COUNT(*)+1)  ),'" + boardNo +"'||CHR(45)||((MAX(REGEXP_SUBSTR(x.bd_view_no,'[^-]+',1,2)))+1))      ");
        query.append("  FROM T2BOARD x        ");
        query.append(" WHERE x.parent_no='" + boardNo +"'     ");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}