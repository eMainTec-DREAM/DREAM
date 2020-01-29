package dream.asset.categ.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dao.LovEqCtgAsmbListDAO;
import dream.asset.categ.list.dto.LovEqCtgAsmbListDTO;

/**
 * 설비종류 작업부위 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEqCtgAsmbListDAOTarget"
 * @spring.txbn id="lovEqCtgAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqCtgAsmbListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqCtgAsmbListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgAsmbListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgAsmbList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT eq_ctg_asmb_id AS EQCTGASMBID,							");
        query.append("       description	AS description,							");
        query.append("       remark			AS remark								");
        query.append("FROM   TAEQCTGASMB x											");
        query.append("WHERE  comp_no  ='"+ loginUser.getCompNo()+"'					");
        query.append("  AND  eqctg_id  ='"+ lovEqCtgAsmbListDTO.getExtCode1()+"'	");
        query.getLikeQuery("description", lovEqCtgAsmbListDTO.getEqCtgAsmbDesc());
        //query.append("ORDER BY ord_no												");
        query.getOrderByQuery("x.eq_ctg_asmb_id", "x.ord_no", lovEqCtgAsmbListDTO.getOrderBy(), lovEqCtgAsmbListDTO.getDirection());
        
        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(lovEqCtgAsmbListDTO.getIsLoadMaxCount(), lovEqCtgAsmbListDTO.getFirstRow()));

    }
    
    @Override
    public List findEqCtgAsmbACList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT");
        query.append("       x.eq_ctg_asmb_id          AS EQ_CTG_ASMB_ID      ");
        query.append("       ,x.p_eq_ctg_asmb_id       AS PEQCTGASMBID        ");
        query.append("       ,x.description            AS DESCRIPTION         ");
        query.append("       ,x.remark                 AS REMARK              ");
        
        query.append("       ,x.ord_no                    ORDNO               ");
        query.append("       ,x.eq_ctg_asmb_id            ID                  ");
        query.append("       ,MIN(y.LVL) OVER()  MINLVL ");
        query.append("       ,y.LVL              LEVEL                                      ");

        query.append("FROM   TAEQCTGASMB x, SFAEQCTGASMB_ALL('"+loginUser.getCompNo()+"',0) y     ");
        query.append("WHERE x.eq_ctg_asmb_id = y.eq_ctg_asmb_id                        ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.eqctg_id", conditionMap);
        query.getLikeQuery("x.description", lovEqCtgAsmbListDTO.getEqCtgAsmbDesc());
        //query.append("ORDER BY 1                                               ");
        
        query.getOrderByQuery("x.eq_ctg_asmb_id", "x.ord_no", lovEqCtgAsmbListDTO.getOrderBy(), lovEqCtgAsmbListDTO.getDirection());
        
        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(lovEqCtgAsmbListDTO.getIsLoadMaxCount(), lovEqCtgAsmbListDTO.getFirstRow()));

    }
    public String findTotalCount(
            LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM   TAEQCTGASMB x                                          ");
        query.append("WHERE  1=1                                                    ");
        query.append("  AND  comp_no  ='"+ user.getCompNo()+"'                      ");
        query.append("  AND  eqctg_id  ='"+ lovEqCtgAsmbListDTO.getExtCode1()+"'    ");
        query.getLikeQuery("description", lovEqCtgAsmbListDTO.getEqCtgAsmbDesc());
        query.append("ORDER BY 1                                                    ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}