package dream.consult.comp.wrkcal.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDowsetListDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetListDTO;

/**
 * 휴무요일 설정  - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalDowsetListDAOTarget"
 * @spring.txbn id="consultCompWrkcalDowsetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalDowsetListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompWrkcalDowsetListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalCommonDTO
     * @return List
     */
    public List findDowsetList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                   						");
        query.append("       ''                     seqNo,			");
        query.append("		 ''                    	isDelCheck,		");
        query.append("       SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+consultCompWrkcalCommonDTO.getUserLang()+"')	dow,	");
        query.append("       x.is_off				isOff,			");
        query.append("       x.wrkcaldowset_id  	wrkcaldowsetId	");
        query.append("FROM   TAWRKCALDOWSET x        				");
        query.append("WHERE  1=1               						");
        query.append(this.getWhere(consultCompWrkcalCommonDTO, consultCompWrkcalDowsetListDTO, user));
        query.getOrderByQuery("x.dow", consultCompWrkcalCommonDTO.getOrderBy(), consultCompWrkcalCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(consultCompWrkcalCommonDTO.getIsLoadMaxCount(), consultCompWrkcalCommonDTO.getFirstRow()));
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDowsetListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String id)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAWRKCALDOWSET			");
    	query.append(" WHERE 1=1                  			");
    	query.append("	 AND wrkcaldowset_id  = '"+id+"'	");

    	return this.getJdbcTemplate().update(query.toString());
    }

    private String getWhere(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", consultCompWrkcalCommonDTO.getCompNo());
        query.getLikeQuery("x.wrkcallist_id", consultCompWrkcalCommonDTO.getWrkcalListId());
        query.getAndQuery("x.wrkcaldowset_id", consultCompWrkcalDowsetListDTO.getWrkcalDowsetId());
        
    	return query.toString();
    }
    
	public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO,
			ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM   TAWRKCALDOWSET x        				");
        query.append("WHERE  1=1               						");
        query.append(this.getWhere(consultCompWrkcalCommonDTO, consultCompWrkcalDowsetListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}