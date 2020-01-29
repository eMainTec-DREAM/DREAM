package dream.consult.comp.wrkcal.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalListDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;

/**
 * 근무일달력 - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultCompWrkcalListDAOTarget"
 * @spring.txbn id="consultCompWrkcalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompWrkcalListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalCommonDTO
     * @return List
     */
    public List findWrkcalList(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                   									");
        query.append("       ''                    seqNo						");
        query.append("		 ,''                    isDelCheck					");
        query.append("       ,x.comp_no	           compNo				        ");
        query.append("       ,(SELECT aa.description                            ");
        query.append("          FROM TACOMP aa                                  ");
        query.append("         WHERE x.comp_no = aa.comp_no) compDesc        	");
        query.append("       ,x.wrkcallist_no                wrkcalListNo     	");
        query.append("       ,x.description               	 wrkcalListDesc     ");
        query.append("       ,(SELECT aa.description                            ");
        query.append("        	FROM TAPLANT aa                                 ");
        query.append("         WHERE x.plant = aa.plant        					");
        query.append("        	 AND x.comp_no = aa.comp_no) plantDesc   		");
        query.append("       ,x.is_use               	 	 isUse				");
        query.append("       ,x.remark               	 	 remark 			");
        query.append("       ,x.wrkcallist_id  				 wrkcalListId		");
        query.append("FROM   TAWRKCALLIST x        								");
        query.append("WHERE  1=1               									");
        query.append(this.getWhere(consultCompWrkcalCommonDTO));
        query.getOrderByQuery("x.wrkcallist_id", consultCompWrkcalCommonDTO.getOrderBy(), consultCompWrkcalCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompWrkcalCommonDTO.getIsLoadMaxCount(), consultCompWrkcalCommonDTO.getFirstRow()));
        
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultCompWrkcalCommonDTO.getWrkcalListId()) && !"".equals(consultCompWrkcalCommonDTO.getCompNo()))
        {
            query.getAndQuery("x.wrkcallist_id", consultCompWrkcalCommonDTO.getWrkcalListId());
            query.getAndQuery("x.comp_no", consultCompWrkcalCommonDTO.getCompNo());
            return query.toString();
        }
        
        query.getAndQuery("x.wrkcallist_id", consultCompWrkcalCommonDTO.getWrkcalListId());
        query.getAndQuery("x.comp_no", consultCompWrkcalCommonDTO.getFilterCompNo());
        query.getLikeQuery("x.description", consultCompWrkcalCommonDTO.getFilterWrkcalNo());
        
        return query.toString();
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultCompWrkcalListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String wrkCalListId, String compNo, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int result = 0;
    	
    	query.append("DELETE FROM TAWRKCALLIST      ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND wrkcallist_id =  ?      ");
        Object[] objects = new Object[]{
        		compNo
        		,wrkCalListId
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        
        query.setClear();
        query.append("DELETE FROM TAWRKCALDAYSET    ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND wrkcallist_id =  ?      ");
        objects = new Object[]{
        		compNo
        		,wrkCalListId
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        query.setClear();
        query.append("DELETE FROM TAWRKCALDOWSET    ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND wrkcallist_id =  ?      ");
        objects = new Object[]{
        		compNo
        		,wrkCalListId
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
    	return result;
    	
    }

	public String findTotalCount(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM   TAWRKCALLIST x ");
        query.append("WHERE  1=1            ");
        query.append(this.getWhere(consultCompWrkcalCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}