package dream.work.rpt.basicunitanalysis.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.basicunitanalysis.dao.BasicUnitAnalysisDetailDAO;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisDetailDTO;

/**
 * 원단위분석 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="basicUnitAnalysisDetailDAOTarget"
 * @spring.txbn id="basicUnitAnalysisDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BasicUnitAnalysisDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements BasicUnitAnalysisDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param basicUnitAnalysisDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                        ");
        query.append("     z.plant  plantId                                         ");
        query.append("     , z.plantDesc                                            ");
        query.append("     , z.wkorDate                                             ");
        query.append("     , z.stdCheckPointId                                      ");
        query.append("     , z.stdCheckPointDesc                                    ");
        query.append("     , z.resultValue                                          ");
        query.append("     , z.productionAmt                                        ");
        query.append("     , ROUND((z.resultValue/ z.productionAmt),2) basicUnit    ");
        query.append("FROM                                                          ");
        query.append("    (                                                         ");
        query.append("        SELECT                                                ");
        query.append("             y.plant                                                                                                          ");
        query.append("            , (SELECT a.description FROM TAPLANT a WHERE a.plant =y.plant) plantDesc                                          ");
        query.append("            , y.wkorDate                                                                                                      ");
        query.append("            , y.stdCheckPointId                                                                                               ");
        query.append("            , (SELECT a.check_point FROM TASTDCHECKPOINT a WHERE a.std_check_point_id = y.stdCheckPointId) stdCheckPointDesc  ");
        query.append("            , y.resultValue                                   ");
        query.append("            , y.equip_id   equipId                            ");
        query.append("            , (SELECT SUM(resultqty) FROM TXDWHP_POPEQACT WHERE eqpid = equip_id)  productionAmt                              ");
        query.append("            FROM                                              ");
        query.append("        (                                                     ");
        query.append("            SELECT x.pm_point_id pmPointId                    ");
        query.append("            , (SELECT a.std_check_point_id FROM TAPMPOINT a WHERE a.pm_point_id = x.pm_point_id) stdCheckPointId              ");
        query.append("            , SUBSTRING(x.actual_date,1,6) wkorDate              ");
        query.append("            , ISNULL(( SUM(x.result_value) + SUM(x.result_value2) + SUM(x.result_value3)),0 ) resultValue                        ");
        query.append("            , (SELECT a.pm_id FROM TAPMPOINT a WHERE a.pm_point_id = x.pm_point_id) pmId                                      ");
        query.append("            , (SELECT a.plant FROM TAEQUIPMENT a WHERE a.equip_id = x.equip_id AND a.plant IS NOT NULL) plant                 ");
        query.append("            , x.equip_id                                      ");
        query.append("            FROM TAPMINSDPOINT x                              ");
        query.append("            WHERE x.actual_date IS NOT NULL                   ");
        query.append("            AND x.equip_id IS NOT NULL                        ");
        query.append("            AND x.comp_no = '"+loginUser.getCompNo()+"'       ");
        query.append("            GROUP BY x.pm_point_id, SUBSTRING(x.actual_date,1,6) , x.equip_id                                                    ");
        query.append("        ) y                                                   ");
        query.append("        WHERE stdCheckPointId IS NOT NULL                     ");
        query.append("        AND plant IS NOT NULL                                 ");
        query.append("    )z                                                        ");
        query.append("WHERE 1=1                                                     ");
        query.append(this.getWhere(basicUnitAnalysisDetailDTO, loginUser));
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // 공장
        query.getAndQuery("z.plant", basicUnitAnalysisDetailDTO.getPlantId());
        
        // 월
        query.getAndDateQuery("z.wkorDate", basicUnitAnalysisDetailDTO.getStartDate().replaceAll("-", ""), basicUnitAnalysisDetailDTO.getEndDate().replaceAll("-", ""));
                
        return query.toString();
    }
    
}