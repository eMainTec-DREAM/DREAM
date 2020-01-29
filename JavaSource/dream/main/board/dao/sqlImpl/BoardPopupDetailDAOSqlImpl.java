package dream.main.board.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;

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
public class BoardPopupDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements BoardPopupDetailDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       CASE x.parent_no     			");
        query.append("       	WHEN '0' THEN ''    		");
        query.append("       	ELSE (SELECT     			");
        query.append("                      y.bd_view_no    ");
        query.append("                  FROM T2BOARD y      ");
        query.append("                 WHERE x.parent_no=y.board_no)	");
        query.append("       END parentNo,					");
        query.append("       x.board_no boardNo,            ");
        query.append("       x.bd_view_no bdViewNo,         ");
        query.append("       x.description,                 ");
        query.append("       x.enter_date enterDate,        ");
        query.append("       x.enter_by enterBy,            ");
        query.append("       dbo.SF2CODETODESC(x.enter_by, 'USER') enterByName,    ");
        query.append("       CASE (                       	");
        query.append("             SELECT COUNT(*)        	");
        query.append("               FROM T2BOARD y       	");
        query.append("              WHERE y.parent_no='" + BoardPopupCommonDTO.getBoardNo() +"'	");
        query.append("            ) WHEN '0' THEN 'Y' ELSE 'N' END deletable,	");
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO T2BOARD (                                 ");
        query.append("    board_no,      description,                       ");
        query.append("    contents,       enter_by,                         ");
        query.append("    enter_date,    parent_no,                         ");
        query.append("    bd_view_no                                        ");
        query.append(")                                                     ");
        query.append("VALUES(                                               ");
        query.append("    ?,                 ?,                             ");
        query.append("    ?,                 ?,                             ");
        query.append("    CONVERT(VARCHAR, getdate(), 112),  ?,             ");
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

        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE T2BOARD                                          ");
        query.append("SET    description    = ?,                              ");
        query.append("       contents       = ?,                              ");
        query.append("       update_date = CONVERT(VARCHAR, getdate(), 112),      ");
        query.append("       update_by      = ?                               ");
        query.append("WHERE  board_no       = ?                               ");
        
        Object[] objects = {
                BoardPopupDetailDTO.getDescription(),
                BoardPopupDetailDTO.getContents(),          
                BoardPopupDetailDTO.getEnterBy(),
                BoardPopupDetailDTO.getBoardNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects)); 
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE T2BOARD                                         ");
        query.append("WHERE  board_no      = ?                               ");
        
        Object[] objects = {
                BoardPopupDetailDTO.getBoardNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects)); 
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT   ");
        query.append("       CASE COUNT(*) WHEN '0' THEN ('" + boardNo +"'+CHR(45)+(COUNT(*)+1)  ) ELSE '" + boardNo +"'+CHR(45)+((MAX(REGEXP_SUBSTR(x.bd_view_no,'[^-]+',1,2)))+1) END     ");
        query.append("  FROM T2BOARD x        ");
        query.append(" WHERE x.parent_no='" + boardNo +"'     ");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}