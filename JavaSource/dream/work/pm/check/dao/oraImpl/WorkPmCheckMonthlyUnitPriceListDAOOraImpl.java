package dream.work.pm.check.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.check.dao.WorkPmCheckMonthlyUnitPriceListDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;

/**
 * WorkPmCheckMonthlyUnitPrice Page - List DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmCheckMonthlyUnitPriceListDAOTarget"
 * @spring.txbn id="workPmCheckMonthlyUnitPriceListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmCheckMonthlyUnitPriceListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkPmCheckMonthlyUnitPriceListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workPmCheckMonthlyUnitPriceListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmCheckMonthlyUnitPriceListDTO
     * @return List
     */
    public List findList(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    ''                            seqNo                   ");
        query.append("   ,''                            isDelCheck              ");
        query.append("   , x.stdchkpointprice_id        stdChkPointPriceId      ");
        query.append("   , x.std_check_point_id         stdCheckPointId         ");
        query.append("   , x.yyyymm                     yyyymm                  ");
        query.append("   , x.unit_price                 unitPrice               ");
        query.append("   , x.REMARK                     remark                  ");
        query.append(" FROM TASTDCHKPOINTPRICE x                                ");
        query.append("WHERE  1=1                                                ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.std_check_point_id", workPmCheckCommonDTO.getCheckPointId());
        query.append(this.getWhere(workPmCheckMonthlyUnitPriceListDTO, user));
        query.getOrderByQuery("x.std_check_point_id", "x.std_Check_point_id DESC", workPmCheckMonthlyUnitPriceListDTO.getOrderBy(), workPmCheckMonthlyUnitPriceListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmCheckMonthlyUnitPriceListDTO.getIsLoadMaxCount(), workPmCheckMonthlyUnitPriceListDTO.getFirstRow()));

    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workPmCheckMonthlyUnitPriceListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(workPmCheckMonthlyUnitPriceListDTO.getStdChkPointPriceId()))
        {
            query.getAndQuery("x.stdchkpointprice_id", workPmCheckMonthlyUnitPriceListDTO.getStdChkPointPriceId());
            return query.toString();
        }
        
        return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: workPmCheckMonthlyUnitPriceListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmCheckMonthlyUnitPriceListDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TASTDCHKPOINTPRICE   ");
        query.append("WHERE stdchkpointprice_id = ?    ");
        query.append("  AND comp_no = ?                ");
        
        Object[] objects = new Object[] {   
                 id
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(WorkPmCheckCommonDTO workPmCheckCommonDTO,
            WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TASTDCHKPOINTPRICE x                               ");
        query.append("WHERE  1=1                                                ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.std_check_point_id", workPmCheckCommonDTO.getCheckPointId());
        query.append(this.getWhere(workPmCheckMonthlyUnitPriceListDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
