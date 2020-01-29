package dream.mgr.cal.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.cal.dao.MgrCalCompWkrcalDaysetListDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetListDTO;

/**
 * 휴무요일 설정  - 목록 dao
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDaysetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalCompWkrcalDaysetListDAOTarget"
 * @spring.txbn id="mgrCalCompWkrcalDaysetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalCompWkrcalDaysetListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCalCompWkrcalDaysetListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalCommonDTO
     * @return List
     */
    public List findDaysetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                   						");
        query.append("       ''                     seqNo,			");
        query.append("		 ''                    	isDelCheck,		");
        query.append("       x.cal_date				calDate,		");
        query.append("       x.is_off				isOff,			");
        query.append("       x.is_repeat			isRepeat,		");
        query.append("       x.remark				remark,			");
        query.append("       x.wrkcaldayset_id  	wrkcalDaysetId	");
        query.append("FROM   TAWRKCALDAYSET x        				");
        query.append("WHERE  1=1               						");
        query.getLikeQuery("x.wrkcallist_id", mgrCalCompWkrcalCommonDTO.getWrkcalListId());
        query.getAndQuery("x.wrkcaldayset_id", mgrCalCompWkrcalDaysetListDTO.getWrkcalDaysetId());
        query.getOrderByQuery("x.wrkcaldayset_id", "x.cal_date", mgrCalCompWkrcalCommonDTO.getOrderBy(), mgrCalCompWkrcalCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(mgrCalCompWkrcalCommonDTO.getIsLoadMaxCount(), mgrCalCompWkrcalCommonDTO.getFirstRow()));
    }

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAWRKCALDAYSET			");
    	query.append(" WHERE 1=1                  			");
    	query.append("	 AND wrkcaldayset_id  = '"+id+"'	");

    	return this.getJdbcTemplate().update(query.toString());
    }

    @Override
    public String findTotalCount(
            MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO,
            MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        query.append("       count(1)                               ");
        query.append("FROM   TAWRKCALDAYSET x                       ");
        query.append("WHERE  1=1                                    ");
        query.getLikeQuery("x.wrkcallist_id", mgrCalCompWkrcalCommonDTO.getWrkcalListId());
        query.getAndQuery("x.wrkcaldayset_id", mgrCalCompWkrcalDaysetListDTO.getWrkcalDaysetId());

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}