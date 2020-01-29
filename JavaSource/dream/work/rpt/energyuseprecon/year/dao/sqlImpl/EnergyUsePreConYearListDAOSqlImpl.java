package dream.work.rpt.energyuseprecon.year.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.energyuseprecon.year.dao.EnergyUsePreConYearListDAO;
import dream.work.rpt.energyuseprecon.year.dto.EnergyUsePreConYearCommonDTO;

/**
 * EnergyUsePreConYear Page - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="energyUsePreConYearListDAOTarget"
 * @spring.txbn id="energyUsePreConYearListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EnergyUsePreConYearListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements EnergyUsePreConYearListDAO
{

    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param energyUsePreConYearCommonDTO
     * @return List
     */
    public List findList(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        																							");
        query.append("    ''                                seqNo       														");
        query.append("    ,x.plantId                       	plantId       														");
        query.append("    ,x.plantDesc                    	plantDesc     														");
        query.append("    ,x.checkPointType           		checkPointType        												");
        query.append("    ,x.YEAR                         	YEAR      															");
        query.append("    ,SUM(x.totValue)           		totValue      														");
        query.append("    ,ROUND(SUM(x.totValue)/12,2)    	avgTotValue       													");
        query.append("    ,x.uom                          	uom       															");
        query.append("    ,SUM(x.totPrice)             		totPrice     														");
        query.append("    ,ROUND(SUM(x.totPrice)/12,2)    	avgTotPrice       													");
        query.append("    ,'원'                              priceUom      														");
        query.append("FROM (        																							");
        query.append("    SELECT                																				");
        query.append("        b.eqloc_id                                                             			plantId			");
        query.append("        ,(SELECT full_desc FROM TAEQLOC WHERE comp_no=b.comp_no AND eqloc_id=b.eqloc_id)	plantDesc		");
        query.append("        ,a.check_point_type                                                 				checkPointType	");
        query.append("        ,SUBSTRING(b.yyyymm,1,4)                                           				YEAR			");
        query.append("        ,ISNULL(b.tot_value,0)                                               				totValue		");
        query.append("        ,ISNULL(b.unit_price,0)*ISNULL(b.tot_value,0)                						totPrice		");
        query.append("        ,a.uom                                                                   			uom				");
        query.append("        ,b.comp_no                                                                   		compNo			");
        query.append("    FROM TASTDCHECKPOINT a INNER JOIN TASTDCHKPOINTRVALUE b												");
        query.append("    ON a.comp_no=b.comp_no                																");
        query.append("    AND a.std_check_point_id=b.std_check_point_id             											");
        query.append("    WHERE 1=1             																				");
        query.append(this.getWhere(energyUsePreConYearCommonDTO, user));
        query.append(") x       																								");
        query.append("GROUP BY      																							");
        query.append("    x.plantId     																						");
        query.append("    ,x.plantDesc      																					");
        query.append("    ,x.checkPointType     																				");
        query.append("    ,x.YEAR       																						");
        query.append("    ,x.uom        																						");
        query.append("    ,x.compNo        																						");
        query.getOrderByQuery("x.compNo", "x.year", energyUsePreConYearCommonDTO.getOrderBy(), energyUsePreConYearCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(energyUsePreConYearCommonDTO.getIsLoadMaxCount(), energyUsePreConYearCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param energyUsePreConYearCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.append("AND b.tot_value IS NOT NULL");
        
        // 년
        String fromDate = energyUsePreConYearCommonDTO.getFilterStartDate();
        String toDate   = energyUsePreConYearCommonDTO.getFilterEndDate();
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND SUBSTRING(b.yyyymm,1,4) >= "+fromDate+"     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND SUBSTRING(b.yyyymm,1,4) <= "+toDate+"        ");
        }        
 
        // 위치
        query.getEqLocLevelQuery("b.eqloc_id", energyUsePreConYearCommonDTO.getFilterPlantId(), energyUsePreConYearCommonDTO.getFilterPlantDesc(), user.getCompNo());
        
        // 항목
        query.getCodeLikeQuery("a.check_point_type", "SFACODE_TO_DESC(a.check_point_type, 'CHECK_POINT_TYPE', 'SYS', '"+user.getCompNo()+"', '"+user.getLangId()+"')", energyUsePreConYearCommonDTO.getFilterCheckPointType(), energyUsePreConYearCommonDTO.getFilterCheckPointDesc());
        
        return query.toString();
    }
    
    public String findTotalCount(
            EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                																	");
        query.append("       COUNT(1)                       																	");
        query.append(" FROM                                 																	");
        query.append("    (                                 																	");
        
        query.append("SELECT        																							");
        query.append("    ''                                   	seqNo															");
        query.append("    ,x.plantId                       		plantId															");
        query.append("    ,x.plantDesc                    		plantDesc														");
        query.append("    ,x.checkPointType           			checkPointType													");
        query.append("    ,x.YEAR                         		YEAR															");
        query.append("    ,SUM(x.totValue)            			totValue														");
        query.append("    ,ROUND(SUM(x.totValue)/12,2)    		avgTotValue														");
        query.append("    ,x.uom                          		uom																");
        query.append("    ,SUM(x.totPrice)             			totPrice														");
        query.append("    ,ROUND(SUM(x.totPrice)/12,2)			avgTotPrice														");
        query.append("    ,'원'									priceUom														");
        query.append("FROM (        																							");
        query.append("    SELECT                																				");
        query.append("        b.eqloc_id                                                             			plantId         ");
        query.append("        ,(SELECT full_desc FROM TAEQLOC WHERE comp_no=b.comp_no AND eqloc_id=b.eqloc_id)	plantDesc       ");
        query.append("        ,a.check_point_type                                                 				checkPointType	");
        query.append("        ,SUBSTRING(b.yyyymm,1,4)                                           				YEAR            ");
        query.append("        ,ISNULL(b.tot_value,0)                                               				totValue        ");
        query.append("        ,ISNULL(b.unit_price,0)*ISNULL(b.tot_value,0)                						totPrice        ");
        query.append("        ,a.uom                                                                   			uom             ");
        query.append("    FROM TASTDCHECKPOINT a INNER JOIN TASTDCHKPOINTRVALUE b                        						");
        query.append("    ON a.comp_no=b.comp_no                																");
        query.append("    AND a.std_check_point_id=b.std_check_point_id             											");
        query.append("    WHERE 1=1            	 																				");
        query.append(this.getWhere(energyUsePreConYearCommonDTO, user));
        query.append(") x       																								");
        query.append("GROUP BY      																							");
        query.append("    x.plantId     																						");
        query.append("    ,x.plantDesc      																					");
        query.append("    ,x.checkPointType     																				");
        query.append("    ,x.YEAR       																						");
        query.append("    ,x.uom        																						");
        
        query.append("    ) AS SUB                                																");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
