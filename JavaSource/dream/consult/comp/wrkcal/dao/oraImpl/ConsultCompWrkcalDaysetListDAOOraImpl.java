package dream.consult.comp.wrkcal.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDaysetListDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetListDTO;

/**
 * 휴무일 설정  - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDaysetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalDaysetListDAOTarget"
 * @spring.txbn id="consultCompWrkcalDaysetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalDaysetListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompWrkcalDaysetListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDaysetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalCommonDTO
     * @return List
     */
    public List findDaysetList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
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
        query.append(this.getWhere(consultCompWrkcalCommonDTO, consultCompWrkcalDaysetListDTO, user));
        query.getOrderByQuery("x.cal_date", consultCompWrkcalCommonDTO.getOrderBy(), consultCompWrkcalCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompWrkcalCommonDTO.getIsLoadMaxCount(), consultCompWrkcalCommonDTO.getFirstRow()));
    }

    private String getWhere(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
        query.getStringEqualQuery("x.comp_no", consultCompWrkcalCommonDTO.getCompNo());
        
        query.getLikeQuery("x.wrkcallist_id", consultCompWrkcalCommonDTO.getWrkcalListId());
        query.getAndQuery("x.wrkcaldayset_id", consultCompWrkcalDaysetListDTO.getWrkcalDaysetId());

    	return query.toString();
    }
    
    
    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDaysetListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String id)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAWRKCALDAYSET			");
    	query.append(" WHERE 1=1                  			");
    	query.append("	 AND wrkcaldayset_id  = '"+id+"'	");

    	return this.getJdbcTemplate().update(query.toString());
    }

	public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO,
			ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAWRKCALDAYSET x                                   ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(consultCompWrkcalCommonDTO, consultCompWrkcalDaysetListDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}