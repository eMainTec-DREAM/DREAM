package dream.work.pm.std.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.LovStdWrkWorkListDAO;
import dream.work.pm.std.dto.LovStdWrkWorkListDTO;

/**
 * 표준점검항목 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovStdWrkWorkListDAOTarget"
 * @spring.txbn id="lovStdWrkWorkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovStdWrkWorkListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovStdWrkWorkListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovStdWrkWorkListDTO
     * @param loginUser
     * @return
     */
    public List findStdCheckPointList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                       ");
        query.append("     x.comp_no               ");
        query.append("   , x.std_check_point_id    ");
        query.append("   , x.std_check_point_no    ");
        query.append("   , x.check_point_type      ");
        query.append("   , x.check_point           ");
        query.append("   , x.check_value           ");
        query.append("   , x.uom                   ");
        query.append("   , x.part_id               ");
        query.append("   , x.is_active             ");
        query.append("   , x.remark                ");
        query.append("FROM TASTDCHECKPOINT x       ");
        
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        //query.getLikeQuery("std_check_point_id", lovStdWrkWorkListDTO.getStdCheckPointId());

        query.append("ORDER by std_check_point_no  ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

	@Override
	public List findStdCheckPointAcList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User user, Map<String, String> conditionMap) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		
		query.append("SELECT 																								");
		query.append("       x.stwrkwork_id 																				");
		query.append("       ,dbo.SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', x.comp_no, '"+user.getLang()+"')	AS wo_type	");
		query.append("       ,remark 																						");
		query.append("FROM   TASTWRKWORK x																					");
		query.append("WHERE  1=1																							");
		query.getAndQuery("stwrk_id", conditionMap);
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        //query.getAndQuery("stwrk_id", lovStdWrkWorkListDTO.getStWrkId());

        if(!"".equals(lovStdWrkWorkListDTO.getWoType()))
        {
        	query.append("AND UPPER(SFACODE_TO_DESC(x.wo_type, 'WO_TYPE', 'SYS', x.comp_no, '"+user.getLang()+"'))  LIKE UPPER('%"+lovStdWrkWorkListDTO.getWoType()+"%')		");
        }
        
        query.append("ORDER by stwrkwork_id  ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
	}
}