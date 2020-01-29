package dream.mgr.cal.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.cal.dao.MgrCalCompWkrcalDaysetDetailDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetDetailDTO;

/**
 * 휴무일 설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="mgrCalCompWkrcalDaysetDetailDAOTarget"
 * @spring.txbn id="mgrCalCompWkrcalDaysetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalCompWkrcalDaysetDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCalCompWkrcalDaysetDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrCalCompWkrcalDaysetDetailDTO findDetail(String wrkcalDaysetId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT								        					");
        query.append("       x.cal_date						calDate,   					");
        query.append("       x.is_off						isOff,						");
        query.append("       x.is_repeat					isRepeat,					");
        query.append("       x.remark						remark,						");
        query.append("       x.wrkcaldayset_id				wrkcalDaysetId				");
        query.append("FROM   TAWRKCALDAYSET x											");
        query.append("WHERE  x.wrkcaldayset_id = '"+wrkcalDaysetId+"'					");

        MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO =
        		(MgrCalCompWkrcalDaysetDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MgrCalCompWkrcalDaysetDetailDTO()));

        return mgrCalCompWkrcalDaysetDetailDTO;
    }
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailDTO
     * @return
     */
    public int insertDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWRKCALDAYSET						");
    	query.append("	(comp_no,	wrkcallist_id,	wrkcaldayset_id,	");
    	query.append("	 cal_date,	is_off,			is_repeat,			");
    	query.append("	 remark											");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?												");
    	query.append("	)												");

    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			mgrCalCompWkrcalCommonDTO.getWrkcalListId(),
    			mgrCalCompWkrcalDaysetDetailDTO.getWrkcalDaysetId(),
    			mgrCalCompWkrcalDaysetDetailDTO.getCalDate(),
    			mgrCalCompWkrcalDaysetDetailDTO.getIsOff(),
    			mgrCalCompWkrcalDaysetDetailDTO.getIsRepeat(),
    			mgrCalCompWkrcalDaysetDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDaysetDetailDTO
     * @return
     */
    public int updateDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWRKCALDAYSET SET		");
    	query.append("		cal_date		= ?, 	");
    	query.append("		is_off			= ?, 	");
    	query.append("		is_repeat		= ?, 	");
    	query.append("		remark			= ? 	");
    	query.append("WHERE wrkcaldayset_id	= ?		");

    	Object[] objects = new Object[] {
    			mgrCalCompWkrcalDaysetDetailDTO.getCalDate(),
    			mgrCalCompWkrcalDaysetDetailDTO.getIsOff(),
    			mgrCalCompWkrcalDaysetDetailDTO.getIsRepeat(),
    			mgrCalCompWkrcalDaysetDetailDTO.getRemark(),
    			mgrCalCompWkrcalDaysetDetailDTO.getWrkcalDaysetId()
    	};

    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

}