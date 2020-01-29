package dream.mgr.cal.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.cal.dao.MgrCalCompWkrcalListDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;

/**
 * 근무일달력 - 목록 dao
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalCompWkrcalListDAOTarget"
 * @spring.txbn id="mgrCalCompWkrcalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalCompWkrcalListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCalCompWkrcalListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalCommonDTO
     * @return List
     */
    public List findWrkcalList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

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
        query.append(this.getWhere(mgrCalCompWkrcalCommonDTO, user));
//        query.append("ORDER by x.comp_no, x.wrkcallist_id    				   	");
        query.getOrderByQuery("x.wrkcallist_id", "x.comp_no, x.wrkcallist_id", mgrCalCompWkrcalCommonDTO.getOrderBy(), mgrCalCompWkrcalCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(mgrCalCompWkrcalCommonDTO.getIsLoadMaxCount(), mgrCalCompWkrcalCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(mgrCalCompWkrcalCommonDTO.getWrkcalListId()) && !"".equals(user.getCompNo()))
        {
            query.getAndQuery("x.wrkcallist_id", mgrCalCompWkrcalCommonDTO.getWrkcalListId());
            query.getAndQuery("x.comp_no", user.getCompNo());
            return query.toString();
        }
        
        query.getAndQuery("x.wrkcallist_id", mgrCalCompWkrcalCommonDTO.getWrkcalListId());
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.description", mgrCalCompWkrcalCommonDTO.getFilterWrkcalNo());
        
        return query.toString();
    }

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String wrkCalListId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

         int result = 0;
    	
    	query.append("DELETE FROM TAWRKCALLIST      ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND wrkcallist_id =  ?      ");
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,wrkCalListId
        };
        result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
        
        query.setClear();
        query.append("DELETE FROM TAWRKCALDAYSET    ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND wrkcallist_id =  ?      ");
        objects = new Object[]{
        		user.getCompNo()
        		,wrkCalListId
        };
        result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
        query.setClear();
        query.append("DELETE FROM TAWRKCALDOWSET    ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND wrkcallist_id =  ?      ");
        objects = new Object[]{
        		user.getCompNo()
        		,wrkCalListId
        };
        
        result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
        return result;
        
    }

    @Override
    public String findTotalCount(
            MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                    ");
        query.append("       count(1)                                           ");
        query.append("FROM   TAWRKCALLIST x                                     ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(mgrCalCompWkrcalCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}