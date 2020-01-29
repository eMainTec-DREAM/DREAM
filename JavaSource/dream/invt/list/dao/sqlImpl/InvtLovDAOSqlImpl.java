package dream.invt.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.list.dao.InvtLovDAO;
import dream.invt.list.dto.InvtLovDTO;

/**
 * �������� �˾�
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="invtLovDAOTarget"
 * @spring.txbn id="invtLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtLovDAO
{

    @Override
    public List findTaskMapAcList(InvtLovDTO invtLovDTO, User loginUser,  Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String notIn = conditionMap.get("NOT_IN_STATUS");
        
        query.append("SELECT                                       ");
        query.append("        description AS description      ");
        query.append("        ,invtlist_id  AS invtlistId         ");
        query.append("        ,invtprctp_id AS invtprctpId    ");
        query.append(" FROM TAINVTLIST                       ");
        query.append("WHERE 1=1                                 ");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("invtprctp_id", conditionMap);
        query.getLikeQuery("description", invtLovDTO.getDescription());
        if(!"".equals(notIn) && notIn!=null) {
            query.append("AND invtlist_status NOT IN("+notIn+")    ");
        }
        
        query.getOrderByQuery("invtlist_id", "invtlist_id", invtLovDTO.getOrderBy(), invtLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtLovDTO.getIsLoadMaxCount(), invtLovDTO.getFirstRow()));
    }

	@Override
	public String findTotalCount(InvtLovDTO invtLovDTO, User loginUser, Map<String, String> conditionMap)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String notIn = conditionMap.get("NOT_IN_STATUS");
        
        query.append("SELECT							");
        query.append("        COUNT(1)					");
        query.append(" FROM TAINVTLIST					");
        query.append("WHERE 1=1                    		");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("invtprctp_id", conditionMap);
        query.getLikeQuery("description", invtLovDTO.getDescription());
        if(!"".equals(notIn) && notIn!=null) {
            query.append("AND invtlist_status NOT IN("+notIn+")    ");
        }
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}