package dream.asset.loc.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.asset.loc.list.dao.LovMesLineListDAO;
import dream.asset.loc.list.dto.LovMesLineListDTO;

/**
 * MESLINE검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovMesLineListDAOTarget"
 * @spring.txbn id="lovMesLineListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovMesLineListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovMesLineListDAO
{
    /**
     * MESLINE 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovMesLineListDTO
     * @return
     */
    public List findMesLineList(LovMesLineListDTO lovMesLineListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("		mes_line_id		AS mesLineId,					");
        query.append("		mes_line_no		AS mesLineNo,					");
        query.append("		mes_line_name	AS mesLineDesc					");
        query.append("FROM TAMESLINE										");
        query.append("WHERE 1=1												");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("mes_line_no", lovMesLineListDTO.getMesLineNo());
        query.getLikeQuery("mes_line_name", lovMesLineListDTO.getMesLineDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findMesLineACList(LovMesLineListDTO lovMesLineListDTO, User user, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT b.*, LEVEL LVL, MIN(LEVEL) OVER() MINLVL FROM (                ");
        query.append("SELECT                                                                                     ");
        query.append("      DISTINCT(mes_line_id) AS ID,                                                  ");
        query.append("        x.mes_line_id mes_line_id,                                                     ");
        query.append("        x.mes_line_no    mes_line_no,                                                ");
        query.append("        x.mes_line_name mes_line_name,                                            ");
        query.append("      x.p_pop_mes_id p_pop_mes_id                                                 ");
        query.append("FROM    TAMESLINE x                                                                   ");
        query.append("WHERE 1=1                                                                                 ");
        query.getAndQuery("comp_no", conditionMap);
        query.append("START WITH  mes_line_id IN (SELECT a.mes_line_id FROM TAMESLINE a ");
        query.append("                         WHERE  1 = 1                                                     ");
        query.getLikeQuery("a.mes_line_no", lovMesLineListDTO.getMesLineNo());
        query.getLikeQuery("a.mes_line_name", lovMesLineListDTO.getMesLineDesc());
        query.append("                         )                                                                      ");
        query.append("CONNECT BY PRIOR p_pop_mes_id = mes_line_id                                ");
        query.append(" ) b                                                                                            ");
        query.append("WHERE 1 = 1                                                                               ");
        query.append("START WITH p_pop_mes_id = 0                                                       ");
        query.append("CONNECT BY PRIOR mes_line_id = p_pop_mes_id                                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}