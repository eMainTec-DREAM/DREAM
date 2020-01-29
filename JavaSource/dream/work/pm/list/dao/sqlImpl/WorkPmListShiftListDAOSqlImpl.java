package dream.work.pm.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.WorkPmListShiftListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * ±≥¥Î¡∂  dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workPmListShiftListDAOTarget"
 * @spring.txbn id="workPmListShiftListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListShiftListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmListShiftListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findShiftList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("       ''                             seqNo,          ");
        query.append("       ''                             isDelCheck,     ");
        query.append("       x.pm_wrkshift_id               pmWrkShiftId,   ");
        query.append("       dbo.SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', '"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"') shiftTypeDesc,   ");
        query.append("       x.is_active                    isActive,       ");
        query.append("       x.REMARK                       remark          ");
        query.append("FROM   TAPMWRKSHIFT x                                 ");
        query.append("WHERE 1=1                                             ");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.pm_wrkshift_id", "x.is_active", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteShiftList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAPMWRKSHIFT                  ");
        query.append("WHERE  pm_wrkshift_id     = '"+id+"'      ");
        query.append("  AND  comp_no        = '"+compNo+"'      ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        if (!"".equals(maPmMstrCommonDTO.getPmWrkShiftId()))
        {
            query.getAndQuery("x.pm_wrkshift_id", maPmMstrCommonDTO.getPmWrkShiftId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    @Override
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1)           ");
        query.append("  FROM TAPMWRKSHIFT x		");
        query.append(" WHERE 1 = 1              ");
        query.append(this.getWhere(maPmMstrCommonDTO, loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}