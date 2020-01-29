package dream.work.pm.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.LovStdCheckPointListDAO;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;

/**
 * 표준점검항목 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovStdCheckPointListDAOTarget"
 * @spring.txbn id="lovStdCheckPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovStdCheckPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovStdCheckPointListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovStdCheckPointListDTO
     * @param loginUser
     * @return
     */
    public List findStdCheckPointList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                       ");
        query.append("     x.comp_no               ");
        query.append("   , x.std_check_point_id    ");
        query.append("   , x.std_check_point_no    ");
        query.append("   , x.check_point_type      ");
        query.append("   , dbo.SFACODE_TO_DESC( x.check_point_type , 'CHECK_POINT_TYPE','SYS',x.comp_no, '"+loginUser.getLangId()+"')  checkPointTypeDesc  ");
        query.append("   , x.check_point           ");
        query.append("   , x.check_value           ");
        query.append("   , x.uom                   ");
        query.append("   , x.part_id               ");
        query.append("   , x.is_active             ");
        query.append("   , x.remark                ");
        query.append("   , x.bvalue                ");
        query.append("FROM TASTDCHECKPOINT x       ");
        
        query.append("WHERE 1=1                    ");
        query.append(" AND x.is_active = 'Y'       ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("std_check_point_id", lovStdCheckPointListDTO.getStdCheckPointId());

        query.append("ORDER by std_check_point_no  ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

	@Override
	public List findStdCheckPointAcList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User user, Map<String, String> conditionMap) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
	    query.append("SELECT                       ");
        query.append("     x.comp_no               ");
        query.append("   , x.std_check_point_id    ");
        query.append("   , x.std_check_point_no    ");
        query.append("   , x.check_point_type      ");
        query.append("   , dbo.SFACODE_TO_DESC( x.check_point_type , 'CHECK_POINT_TYPE','SYS',x.comp_no, '"+user.getLangId()+"')  checkPointTypeDesc  ");
        query.append("   , x.check_point           ");
        query.append("   , x.check_value           ");
        query.append("   , x.uom                   ");
        query.append("   , x.part_id               ");
        query.append("   , x.is_active             ");
        query.append("   , x.remark                ");
        query.append("   , x.bvalue                ");
        query.append("FROM TASTDCHECKPOINT x       ");
        query.append("WHERE 1 = 1                  ");
        query.append(" AND x.is_active = 'Y'       ");
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.check_point", lovStdCheckPointListDTO.getCheckPoint());
        query.getLikeQuery("x.std_check_point_no", lovStdCheckPointListDTO.getStdCheckPointNo());
        query.getLikeQuery("x.check_point_type", lovStdCheckPointListDTO.getCheckPointType());
        query.getLikeQuery("x.check_value", lovStdCheckPointListDTO.getCheckValue());
        
        //query.getLikeQuery("x.cdusrm_id", lovStdCheckPointListDTO.getCdUsrmId());
        //query.getLikeQuery("x.cdusrd_no", lovStdCheckPointListDTO.getCdUsrdNo());
        //query.getLikeQuery("x.description", lovStdCheckPointListDTO.getCdUsrdDesc());
        
        query.append("ORDER by std_check_point_no  ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
	}
}