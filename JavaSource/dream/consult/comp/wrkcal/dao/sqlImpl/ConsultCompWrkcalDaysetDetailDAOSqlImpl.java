package dream.consult.comp.wrkcal.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDaysetDetailDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetDetailDTO;

/**
 * 휴무일 설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultCompWrkcalDaysetDetailDAOTarget"
 * @spring.txbn id="consultCompWrkcalDaysetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalDaysetDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompWrkcalDaysetDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public ConsultCompWrkcalDaysetDetailDTO findDetail(String wrkcalDaysetId)
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

        ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO =
        		(ConsultCompWrkcalDaysetDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompWrkcalDaysetDetailDTO()));

        return consultCompWrkcalDaysetDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO)
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
    			consultCompWrkcalCommonDTO.getCompNo(),
    			consultCompWrkcalCommonDTO.getWrkcalListId(),
    			consultCompWrkcalDaysetDetailDTO.getWrkcalDaysetId(),
    			consultCompWrkcalDaysetDetailDTO.getCalDate(),
    			consultCompWrkcalDaysetDetailDTO.getIsOff(),
    			consultCompWrkcalDaysetDetailDTO.getIsRepeat(),
    			consultCompWrkcalDaysetDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWRKCALDAYSET SET		");
    	query.append("		cal_date		= ?, 	");
    	query.append("		is_off			= ?, 	");
    	query.append("		is_repeat		= ?, 	");
    	query.append("		remark			= ? 	");
    	query.append("WHERE wrkcaldayset_id	= ?		");

    	Object[] objects = new Object[] {
    			consultCompWrkcalDaysetDetailDTO.getCalDate(),
    			consultCompWrkcalDaysetDetailDTO.getIsOff(),
    			consultCompWrkcalDaysetDetailDTO.getIsRepeat(),
    			consultCompWrkcalDaysetDetailDTO.getRemark(),
    			consultCompWrkcalDaysetDetailDTO.getWrkcalDaysetId()
    	};

    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}