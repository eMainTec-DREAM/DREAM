package dream.main.board.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

import dream.main.board.dao.BoardPopupListDAO;
import dream.main.board.dto.BoardPopupCommonDTO;

/**
 * �Խ��� - ���
 * @author  pochul2423
 * @version $Id: BoardPopupListDAO.java,v 1.2 2014/01/14 01:35:49 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="boardPopupListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BoardPopupListDAOOraImpl extends BaseJdbcDaoSupportOra implements BoardPopupListDAO
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
    public List findBoardPopupList(BoardPopupCommonDTO BoardPopupCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                       ");
        query.append("        x.bd_view_no,         ");
        query.append("        x.enter_date,         ");
        query.append("        x.enter_by,           ");
        query.append("        DECODE(x.parent_no,'0',x.description,'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'||x.description)description,             ");
        query.append("       (SELECT wm_concat(file_name)                   ");
        query.append("        FROM   T2FILE                                 ");
        query.append("        WHERE  doc_type = 'BOD'                       ");
        query.append("        AND    object_no = x.board_no                 ");
        query.append("        ) attached,                                   ");
        query.append("        x.parent_no,          ");
        query.append("        x.board_no            ");
        query.append("FROM    T2BOARD x             ");
        query.append("WHERE 1=1                     ");
        query.append(this.getWhere(BoardPopupCommonDTO));
        query.append("START WITH x.parent_no='0'    ");
        query.append("CONNECT BY PRIOR x.board_no = x.parent_no       ");
        query.append("ORDER SIBLINGS BY TO_NUMBER(REGEXP_SUBSTR(x.bd_view_no, '[^-]+', 1, 1 )) DESC, TO_NUMBER(REGEXP_SUBSTR (x.bd_view_no, '[^-]+', 1, 2 )) ASC");

        return getJdbcTemplate().queryForList(query.toString());
    }
   
    /**
     * Filter ����
     * @author  pochul2423
     * @version $Id: BoardPopupListDAO.java,v 1.2 2014/01/14 01:35:49 pochul2423 Exp $
     * @since   1.0
     * 
     * @param BoardPopupCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(BoardPopupCommonDTO BoardPopupCommonDTO)
    {        
        QueryBuffer queryBuffer = new QueryBuffer();

        // CommonDTO�� boardNo�� �ִ°��� �󼼿��� ���� �Ǿ List�� �� Row���� ����ȸ�ϴ� ����̴�.
        if (!"".equals(BoardPopupCommonDTO.getBoardNo()))
        {
            queryBuffer.getAndQuery("x.board_no", BoardPopupCommonDTO.getBoardNo()); 
            return queryBuffer.toString();
        }

        queryBuffer.getAndQuery("x.bd_view_no", BoardPopupCommonDTO.getFilterBoardNo()); //�Խù�ȣ
        queryBuffer.getAndQuery("x.description", BoardPopupCommonDTO.getDescription()); //������ ������ȣ
        queryBuffer.getAndQuery("x.enter_by",    BoardPopupCommonDTO.getEnterBy());     //����
        queryBuffer.getAndDateQuery("x.enter_date", BoardPopupCommonDTO.getEnterDateFrom(), BoardPopupCommonDTO.getEnterDateTo());
        
        return queryBuffer.toString();
    }   
}