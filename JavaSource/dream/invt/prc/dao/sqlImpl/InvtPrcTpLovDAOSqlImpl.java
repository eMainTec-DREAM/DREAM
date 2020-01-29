package dream.invt.prc.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.prc.dao.InvtPrcTpLovDAO;
import dream.invt.prc.dto.InvtPrcTpLovDTO;

/**
 * �������� �˾�
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="invtPrcTpLovDAOTarget"
 * @spring.txbn id="invtPrcTpLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcTpLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtPrcTpLovDAO
{

    @Override
    public List findTaskMapAcList(InvtPrcTpLovDTO invtPrcTpLovDTO, User loginUser,  Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                     ");
        query.append("       invtprctp_id        ");
        query.append("       ,invtprctp_no      ");
        query.append("       ,description         ");
        query.append("FROM TAINVTPRCTP    ");
        query.append("WHERE 1=1                ");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getLikeQuery("description", invtPrcTpLovDTO.getDescription());
        query.getOrderByQuery("invtprctp_id", "invtprctp_id", invtPrcTpLovDTO.getOrderBy(), invtPrcTpLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtPrcTpLovDTO.getIsLoadMaxCount(), invtPrcTpLovDTO.getFirstRow()));
    }

	@Override
	public String findTotalCount(InvtPrcTpLovDTO invtPrcTpLovDTO, User user, Map<String, String> conditionMap)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                   	");
        query.append("       COUNT(1)			");
        query.append("FROM TAINVTPRCTP    		");
        query.append("WHERE 1=1                	");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getLikeQuery("description", invtPrcTpLovDTO.getDescription());
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}