package dream.work.rpt.energyuseprecon.month.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.energyuseprecon.month.dao.EnergyUsePreConMonthDetailDAO;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthDetailDTO;

/**
 * EnergyUsePreConMonth Page - Detail DAO implements
 * @author ghlee
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="energyUsePreConMonthDetailDAOTarget"
 * @spring.txbn id="energyUsePreConMonthDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EnergyUsePreConMonthDetailDAOSqlImpl  extends BaseJdbcDaoSupportSql implements EnergyUsePreConMonthDetailDAO
{

    /**
     * grid find
     * @author ghlee 
     * @version $Id:$
     * @since   1.0
     * 
     * @param energyUsePreConMonthDetailDTO
     * @return List
     */
    public List findDetail(EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("    SELECT        ");
        query.append("        ''                                                                           seqNo              ");
        query.append("        ,b.eqloc_id                                                             plantId             ");
        query.append("        ,(SELECT full_desc FROM TAEQLOC WHERE comp_no=b.comp_no AND eqloc_id=b.eqloc_id)    plantDesc         ");
        query.append("        ,b.yyyymm                                                              MONTH            ");
        query.append("        ,ISNULL(b.unit_price,0)                                         unitPrice       ");
        query.append("        ,ISNULL(b.tot_value,0)                                          totValue           ");
        query.append("        ,ISNULL(b.unit_price,0)*ISNULL(b.tot_value,0)               totPrice           ");
        query.append("    FROM TASTDCHECKPOINT a INNER JOIN TASTDCHKPOINTRVALUE b                ");
        query.append("    ON a.comp_no=b.comp_no        ");
        query.append("    AND a.std_check_point_id=b.std_check_point_id     ");
        query.append("    WHERE 1=1     ");
        query.append(this.getWhere(energyUsePreConMonthDetailDTO, user));
        query.getOrderByQuery("a.std_check_point_id", "b.yyyymm", energyUsePreConMonthDetailDTO.getOrderBy(), energyUsePreConMonthDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     *   
     * @param energyUsePreConMonthDetailDTO
     * @return
     * @throws Exception
     */
    private String getWhere(EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.append("AND b.tot_value IS NOT NULL");
        
        // 월
        String fromDate = energyUsePreConMonthDetailDTO.getStartDate();
        String toDate   = energyUsePreConMonthDetailDTO.getEndDate();
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND b.yyyymm >= TO_CHAR(TO_DATE('"+fromDate+"','YYYY-MM'),'YYYYMM')     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND b.yyyymm < TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYY-MM'),1),'YYYYMM')        ");
        }     
 
        // 위치
        query.getEqLocLevelQuery("b.eqloc_id", energyUsePreConMonthDetailDTO.getPlantId(), energyUsePreConMonthDetailDTO.getPlantDesc(), user.getCompNo());
        
        // 항목
        query.getCodeLikeQuery("a.check_point_type", "dbo.SFACODE_TO_DESC(a.check_point_type, 'CHECK_POINT_TYPE', 'SYS', '"+user.getCompNo()+"', '"+user.getLangId()+"')", energyUsePreConMonthDetailDTO.getCheckPointType(), energyUsePreConMonthDetailDTO.getCheckPointDesc());
        
        return query.toString();
    }

    public String findTotalCount(
            EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append(" FROM                                 ");
        query.append("    (                                 ");
        
        query.append("    SELECT        ");
        query.append("        ''                                                                           seqNo              ");
        query.append("        ,b.eqloc_id                                                             plantId             ");
        query.append("        ,(SELECT full_desc FROM TAEQLOC WHERE comp_no=b.comp_no AND eqloc_id=b.eqloc_id)    plantDesc         ");
        query.append("        ,b.yyyymm                                                              MONTH            ");
        query.append("        ,ISNULL(b.unit_price,0)                                         totUnitPrice       ");
        query.append("        ,ISNULL(b.tot_value,0)                                          totValue           ");
        query.append("        ,ISNULL(b.unit_price,0)*ISNULL(b.tot_value,0)               totPrice           ");
        query.append("    FROM TASTDCHECKPOINT a INNER JOIN TASTDCHKPOINTRVALUE b                ");
        query.append("    ON a.comp_no=b.comp_no        ");
        query.append("    AND a.std_check_point_id=b.std_check_point_id     ");
        query.append("    WHERE 1=1     ");
        query.append(this.getWhere(energyUsePreConMonthDetailDTO, user));
        
        query.append("    )                                 ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
