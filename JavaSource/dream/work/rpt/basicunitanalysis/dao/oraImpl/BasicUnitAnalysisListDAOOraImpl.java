package dream.work.rpt.basicunitanalysis.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.basicunitanalysis.dao.BasicUnitAnalysisListDAO;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisCommonDTO;

/**
 * 원단위분석 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="basicUnitAnalysisListDAOTarget"
 * @spring.txbn id="basicUnitAnalysisListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BasicUnitAnalysisListDAOOraImpl extends BaseJdbcDaoSupportOra implements BasicUnitAnalysisListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param basicUnitAnalysisCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
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
        query.append("            , SUBSTR(x.actual_date,1,6) wkorDate              ");
        query.append("            , NVL(( SUM(x.result_value) + SUM(x.result_value2) + SUM(x.result_value3)),0 ) resultValue                        ");
        query.append("            , (SELECT a.pm_id FROM TAPMPOINT a WHERE a.pm_point_id = x.pm_point_id) pmId                                      ");
        query.append("            , (SELECT a.plant FROM TAEQUIPMENT a WHERE a.equip_id = x.equip_id AND a.plant IS NOT NULL) plant                 ");
        query.append("            , x.equip_id                                      ");
        query.append("            FROM TAPMINSDPOINT x                              ");
        query.append("            WHERE x.actual_date IS NOT NULL                   ");
        query.append("            AND x.equip_id IS NOT NULL                        ");
        query.append("            AND x.comp_no = '"+loginUser.getCompNo()+"'       ");
        query.append("            GROUP BY x.pm_point_id, SUBSTR(x.actual_date,1,6) , x.equip_id                                                    ");
        query.append("        ) y                                                   ");
        query.append("        WHERE stdCheckPointId IS NOT NULL                     ");
        query.append("        AND plant IS NOT NULL                                 ");
        query.append("    )z                                                        ");
        query.append("WHERE 1=1                                                     ");
        query.append(this.getWhere(basicUnitAnalysisCommonDTO, loginUser));
        
        return getJdbcTemplate().queryForList(query.toString(basicUnitAnalysisCommonDTO.getIsLoadMaxCount(), basicUnitAnalysisCommonDTO.getFirstRow()));
    }
    
    public String getWhere(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        // 월
        query.getAndDateQuery("z.wkorDate", basicUnitAnalysisCommonDTO.getFilterStartDate(), basicUnitAnalysisCommonDTO.getFilterEndDate());
        
        // 공장
        if (!"".equals(basicUnitAnalysisCommonDTO.getFilterPlantId()))
        {
            query.getAndQuery("z.plant", basicUnitAnalysisCommonDTO.getFilterPlantId());
        }
        else if(!"".equals(basicUnitAnalysisCommonDTO.getFilterPlantDesc()))
        {
            query.getLikeQuery("z.plantDesc", basicUnitAnalysisCommonDTO.getFilterPlantDesc());
        }
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM (                                                    ");
        query.append("        SELECT                                            ");
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
        query.append("            , SUBSTR(x.actual_date,1,6) wkorDate              ");
        query.append("            , NVL(( SUM(x.result_value) + SUM(x.result_value2) + SUM(x.result_value3)),0 ) resultValue                        ");
        query.append("            , (SELECT a.pm_id FROM TAPMPOINT a WHERE a.pm_point_id = x.pm_point_id) pmId                                      ");
        query.append("            , (SELECT a.plant FROM TAEQUIPMENT a WHERE a.equip_id = x.equip_id AND a.plant IS NOT NULL) plant                 ");
        query.append("            , x.equip_id                                      ");
        query.append("            FROM TAPMINSDPOINT x                              ");
        query.append("            WHERE x.actual_date IS NOT NULL                   ");
        query.append("            AND x.equip_id IS NOT NULL                        ");
        query.append("            AND x.comp_no = '"+loginUser.getCompNo()+"'       ");
        query.append("            GROUP BY x.pm_point_id, SUBSTR(x.actual_date,1,6) , x.equip_id                                                    ");
        query.append("        ) y                                                   ");
        query.append("        WHERE stdCheckPointId IS NOT NULL                     ");
        query.append("        AND plant IS NOT NULL                                 ");
        query.append("    )z                                                        ");
        query.append("WHERE 1=1                                                     ");
        query.append(this.getWhere(basicUnitAnalysisCommonDTO, loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}