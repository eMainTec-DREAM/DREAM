package dream.consult.comp.wrkcal.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDowsetDetailDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetDetailDTO;

/**
 * 회사설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultCompWrkcalDowsetDetailDAOTarget"
 * @spring.txbn id="consultCompWrkcalDowsetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalDowsetDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompWrkcalDowsetDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public ConsultCompWrkcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT								        					");
        query.append("       x.dow							dow,    					");
        query.append("		 dbo.SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+lang+"') dowDesc,	");
        query.append("       x.is_off						isOff,						");
        query.append("       x.wrkcaldowset_id              wrkcalDowsetId              ");
        query.append("FROM   TAWRKCALDOWSET x											");
        query.append("WHERE  x.wrkcaldowset_id = '"+wrkcalDowsetId+"'					");

        ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO =
        		(ConsultCompWrkcalDowsetDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompWrkcalDowsetDetailDTO()));

        return consultCompWrkcalDowsetDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO, ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWRKCALDOWSET						");
    	query.append("	(comp_no,	wrkcallist_id,	wrkcaldowset_id,	");
    	query.append("	 dow,		is_off     							");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?								");
    	query.append("	)												");

    	Object[] objects = new Object[] {
    			consultCompWrkcalCommonDTO.getCompNo(),
    			consultCompWrkcalCommonDTO.getWrkcalListId(),
    			consultCompWrkcalDowsetDetailDTO.getWrkcalDowsetId(),
    			consultCompWrkcalDowsetDetailDTO.getDow(),
    			consultCompWrkcalDowsetDetailDTO.getIsOff()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWRKCALDOWSET SET		");
    	query.append("		dow				= ?, 	");
    	query.append("		is_off			= ? 	");
    	query.append("WHERE wrkcaldowset_id	= ?		");

    	Object[] objects = new Object[] {
    			consultCompWrkcalDowsetDetailDTO.getDow(),
    			consultCompWrkcalDowsetDetailDTO.getIsOff(),
    			consultCompWrkcalDowsetDetailDTO.getWrkcalDowsetId()
    	};

    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}