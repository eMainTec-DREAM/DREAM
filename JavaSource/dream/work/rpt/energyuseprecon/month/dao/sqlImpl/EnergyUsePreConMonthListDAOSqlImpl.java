package dream.work.rpt.energyuseprecon.month.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.energyuseprecon.month.dao.EnergyUsePreConMonthListDAO;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthCommonDTO;

/**
 * EnergyUsePreConMonth Page - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="energyUsePreConMonthListDAOTarget"
 * @spring.txbn id="energyUsePreConMonthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EnergyUsePreConMonthListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements EnergyUsePreConMonthListDAO
{

    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param energyUsePreConMonthCommonDTO
     * @return List
     */
    public List findList(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        ");
        query.append("    ''                                               seqNo                 ");
        query.append("    ,x.plantId                                    plantId                ");
        query.append("    ,x.plantDesc                                plantDesc             ");
        query.append("    ,x.checkPointType                        checkPointType     ");
        query.append("    ,dbo.SFACODE_TO_DESC(x.checkPointType, 'CHECK_POINT_TYPE', 'SYS', '"+user.getCompNo()+"', '"+user.getLangId()+"')    checkPointDesc      ");
        query.append("    ,x.uom                                        uom                    ");
        query.append("    ,ROUND(AVG(x.totUnitPrice),2)        avgUnitPrice         ");
        query.append("    ,SUM(x.totValue)                          totValue               ");
        query.append("    ,SUM(x.totPrice)                           totPrice               ");
        query.append("FROM (        ");
        query.append("    SELECT        ");
        query.append("        b.eqloc_id                                                             plantId             ");
        query.append("        ,(SELECT full_desc FROM TAEQLOC WHERE comp_no=b.comp_no AND eqloc_id=b.eqloc_id)    plantDesc         ");
        query.append("        ,a.check_point_type                                                 checkPointType ");
        query.append("        ,a.uom                                                                   uom                 ");
        query.append("        ,b.yyyymm                                                              MONTH            ");
        query.append("        ,ISNULL(b.unit_price,0)                                         totUnitPrice       ");
        query.append("        ,ISNULL(b.tot_value,0)                                          totValue           ");
        query.append("        ,ISNULL(b.unit_price,0)*ISNULL(b.tot_value,0)                totPrice           ");
        query.append("        ,b.comp_no           												compNo		");
        query.append("    FROM TASTDCHECKPOINT a INNER JOIN TASTDCHKPOINTRVALUE b                ");
        query.append("    ON a.comp_no=b.comp_no        ");
        query.append("    AND a.std_check_point_id=b.std_check_point_id     ");
        query.append("    WHERE 1=1     ");
        query.append(this.getWhere(energyUsePreConMonthCommonDTO, user));
        query.append(") x       ");
        query.append("GROUP BY      ");
        query.append("    x.plantId                     ");
        query.append("    ,x.plantDesc                ");
        query.append("    ,x.checkPointType        ");
        query.append("    ,x.uom                        ");
        query.append("    ,x.compNo                        ");
        query.getOrderByQuery("x.compNo", "x.plantDesc", energyUsePreConMonthCommonDTO.getOrderBy(), energyUsePreConMonthCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(energyUsePreConMonthCommonDTO.getIsLoadMaxCount(), energyUsePreConMonthCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param energyUsePreConMonthCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.append("AND b.tot_value IS NOT NULL");
        
        // 월
        String fromDate = CommonUtil.dateToData(energyUsePreConMonthCommonDTO.getFilterStartDate()+"-01");
        String toDate   = CommonUtil.dateToData(energyUsePreConMonthCommonDTO.getFilterEndDate()+"-01");
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND b.yyyymm >= CONVERT(VARCHAR,'"+fromDate+"')     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND b.yyyymm < CONVERT(VARCHAR, DATEADD(DAY, -1, DATEADD(MONTH, 1, '"+toDate+"')), 112)        ");
        }        
 
        // 위치
        query.getEqLocLevelQuery("b.eqloc_id", energyUsePreConMonthCommonDTO.getFilterPlantId(), energyUsePreConMonthCommonDTO.getFilterPlantDesc(), user.getCompNo());
        
        // 항목
        query.getCodeLikeQuery("a.check_point_type", "dbo.SFACODE_TO_DESC(a.check_point_type, 'CHECK_POINT_TYPE', 'SYS', '"+user.getCompNo()+"', '"+user.getLangId()+"')", energyUsePreConMonthCommonDTO.getFilterCheckPointType(), energyUsePreConMonthCommonDTO.getFilterCheckPointDesc());
        
        return query.toString();
    }
    
    public String findTotalCount(
            EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                																	");
        query.append("       COUNT(1)                       																	");
        query.append(" FROM                                 																	");
        query.append("    (                                 																	");
        
        query.append("SELECT        																							");
        query.append("    ''                                   seqNo                 											");
        query.append("    ,x.plantId                           plantId                											");
        query.append("    ,x.plantDesc                         plantDesc             											");
        query.append("    ,x.checkPointType                    checkPointType     												");
        query.append("    ,dbo.SFACODE_TO_DESC(x.checkPointType, 'CHECK_POINT_TYPE', 'SYS', '"+user.getCompNo()+"', '"+user.getLangId()+"')    checkPointDesc      ");
        query.append("    ,x.uom                               uom                    											");
        query.append("    ,ROUND(AVG(x.totUnitPrice),2)        avgUnitPrice         											");
        query.append("    ,SUM(x.totValue)                     totValue               											");
        query.append("    ,SUM(x.totPrice)                     totPrice               											");
        query.append("FROM (        																							");
        query.append("    SELECT        																						");
        query.append("        b.eqloc_id                                                             			plantId         ");
        query.append("        ,(SELECT full_desc FROM TAEQLOC WHERE comp_no=b.comp_no AND eqloc_id=b.eqloc_id)	plantDesc       ");
        query.append("        ,a.check_point_type                                                 				checkPointType	");
        query.append("        ,a.uom                                                                   			uom             ");
        query.append("        ,b.yyyymm                                                              			MONTH           ");
        query.append("        ,ISNULL(b.unit_price,0)                                         					totUnitPrice    ");
        query.append("        ,ISNULL(b.tot_value,0)                                          					totValue        ");
        query.append("        ,ISNULL(b.unit_price,0)*ISNULL(b.tot_value,0)                						totPrice        ");
        query.append("    FROM TASTDCHECKPOINT a INNER JOIN TASTDCHKPOINTRVALUE b                								");
        query.append("    ON a.comp_no=b.comp_no        																		");
        query.append("    AND a.std_check_point_id=b.std_check_point_id     													");
        query.append("    WHERE 1=1     																						");
        query.append(this.getWhere(energyUsePreConMonthCommonDTO, user));
        query.append(") x       																								");
        query.append("GROUP BY      																							");
        query.append("    x.plantId                     																		");
        query.append("    ,x.plantDesc                																			");
        query.append("    ,x.checkPointType        																				");
        query.append("    ,x.uom                        																		");
        
        query.append("    ) AS SUB                                 																");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
