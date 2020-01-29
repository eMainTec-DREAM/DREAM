package dream.asset.categ.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class LovEqCtgAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEqCtgAsmbListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgAsmbListDTO
     * @param loginUser
     * @return List
     */
    public List findEqCtgAsmbList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT eq_ctg_asmb_id AS EQCTGASMBID,							");
        query.append("       description	AS description,							");
        query.append("       remark			AS remark								");
        query.append("FROM   TAEQCTGASMB x											");
        query.append("WHERE  comp_no  ='"+ loginUser.getCompNo()+"'					");
        query.append("  AND  eqctg_id  ='"+ lovEqCtgAsmbListDTO.getExtCode1()+"'	");
        query.getLikeQuery("description", lovEqCtgAsmbListDTO.getEqCtgAsmbDesc());
        //query.append("ORDER BY ord_no												");
        query.getOrderByQuery("x.ord_no", lovEqCtgAsmbListDTO.getOrderBy(), lovEqCtgAsmbListDTO.getDirection());
        
        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(lovEqCtgAsmbListDTO.getIsLoadMaxCount(), lovEqCtgAsmbListDTO.getFirstRow()));

    }

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgAsmbListDTO
     * @param loginUser
     * @return List
     */
    @Override
    public List findEqCtgAsmbACList(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT");
        query.append("       eq_ctg_asmb_id          AS eq_ctg_asmb_id      ");
        query.append("       ,p_eq_ctg_asmb_id       AS pEqCtgAsmbId        ");
        query.append("       ,description            AS description         ");
        query.append("       ,remark                 AS remark              ");
        
        query.append("       ,ord_no                    ordNo               ");
        query.append("       ,eq_ctg_asmb_id            ID                  ");
        query.append("       , MIN(LEVEL) OVER()  minLvl   ");
        query.append("       , LEVEL                                        ");

        query.append("FROM   TAEQCTGASMB x                                  ");
        query.append("WHERE  1=1                                            ");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("eqctg_id", conditionMap);
        query.getLikeQuery("description", lovEqCtgAsmbListDTO.getEqCtgAsmbDesc());
        //query.append("ORDER BY 1                                               ");
        
        query.append("START WITH p_eq_ctg_asmb_id = '0'                                     ");
        query.append("CONNECT BY PRIOR eq_ctg_asmb_id = p_eq_ctg_asmb_id        ");

        query.getOrderByQuery("x.ord_no", lovEqCtgAsmbListDTO.getOrderBy(), lovEqCtgAsmbListDTO.getDirection());

        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(lovEqCtgAsmbListDTO.getIsLoadMaxCount(), lovEqCtgAsmbListDTO.getFirstRow()));

    }
    
    public String findTotalCount(
            LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM   TAEQCTGASMB x                                          ");
        query.append("WHERE  1=1                                                    ");
        query.append("  AND  comp_no  ='"+ user.getCompNo()+"'                      ");
        query.append("  AND  eqctg_id  ='"+ lovEqCtgAsmbListDTO.getExtCode1()+"'    ");
        query.getLikeQuery("description", lovEqCtgAsmbListDTO.getEqCtgAsmbDesc());
        query.append("ORDER BY 1                                                    ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}