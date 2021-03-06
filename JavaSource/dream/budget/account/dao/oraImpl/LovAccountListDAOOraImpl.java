package dream.budget.account.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.budget.account.dao.LovAccountListDAO;
import dream.budget.account.dto.LovAccountListDTO;

/**
 * ������� �˾�
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovAccountListDAOTarget"
 * @spring.txbn id="lovAccountListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovAccountListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovAccountListDAO
{
    public List findList(LovAccountListDTO lovAccountListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                ");
        query.append("    x.comp_no         ");
        query.append("    ,x.accnt_id       ");
        query.append("    ,x.accnt_no       ");
        query.append("    ,x.description    ");
        query.append("    ,SFACODE_TO_DESC(x.accnt_type,'ACCNT_TYPE','SYS','','"+loginUser.getLangId()+"') accnt_type     ");
        query.append("    ,x.is_use         ");
        query.append("    ,x.remark         ");
        query.append("FROM TAACCOUNT x      ");
        query.append("WHERE 1=1             ");
        query.append(this.getWhere(lovAccountListDTO,loginUser,conditionMap));
        query.getOrderByQuery("x.accnt_no", lovAccountListDTO.getOrderBy(), lovAccountListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovAccountListDTO.getIsLoadMaxCount(), lovAccountListDTO.getFirstRow()));
    }
    
    private String getWhere(LovAccountListDTO lovAccountListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getLikeQuery("x.accnt_no", lovAccountListDTO.getAccntNo());
        query.getLikeQuery("x.description", lovAccountListDTO.getDescription());
        query.getAndQuery("x.accnt_type", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        
        return query.toString();
    }

    @Override
    public String findTotalCount(LovAccountListDTO lovAccountListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT            ");
        query.append("    COUNT(1)      ");
        query.append("FROM TAACCOUNT x  ");
        query.append("WHERE 1=1         ");
        query.append(this.getWhere(lovAccountListDTO,loginUser,conditionMap));
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}