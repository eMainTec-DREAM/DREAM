package dream.work.rpt.dailyeng.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.dailyeng.dao.WorkRptDailyEngDetailListDAO;
import dream.work.rpt.dailyeng.dto.WorkRptDailyEngCommonDTO;
import dream.work.rpt.dailyeng.dto.WorkRptDailyEngDetailListDTO;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngDetailListForm;

/**
 * 에너지사용량(일별) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptDailyEngDetailListDAOTarget"
 * @spring.txbn id="workRptDailyEngDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptDailyEngDetailListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptDailyEngDetailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptDailyEngListForm
     * @param loginUser
     * @return List
     */
    public List findDetailList(WorkRptDailyEngDetailListForm workRptDailyEngDetailListForm, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            		");
        query.append("    ''                            seqNo             		");
        query.append("    , SUBSTR(y.usageDate,1,4)||'-'|| 						");
        query.append("      SUBSTR(y.usageDate,5,2)||'-'|| 						");
        query.append("      SUBSTR(y.usageDate,7,2)     USAGEDATE				");
        query.append("    , ROUND(SUM(y.usageAmt),2)    USAGEAMT        		");
        query.append("FROM                                                		");
        query.append("(                                                    		");
        query.append("    SELECT                                         		");
        query.append("        x.usageDate        								");
        query.append("      , x.usageAmt                usageAmt        		");
        query.append("    FROM                                                  ");
        query.append("        (                                                 ");
        query.append("            SELECT                                        ");
        query.append("               x.usage_date       usageDate              	");
        query.append("              , SUM(x.USAGE)      usageAmt                ");
        query.append("            FROM TAFEMSUSAGE x INNER JOIN TAEQUIPMENT y   ");
        query.append("            ON x.comp_no = y.comp_no                      ");
        query.append("            AND x.equip_id = y.equip_id                   ");
        query.append("            WHERE 1=1                                     ");
        query.append(this.getWhere(workRptDailyEngDetailListForm,loginUser));
        query.append("            GROUP BY x.usage_date							");
        query.append("        ) x                                               ");
        query.append("    ) y                                                   ");
        query.append("WHERE 1=1                                                 ");
        query.append("GROUP BY y.usageDate                                      ");
        query.append("ORDER BY y.usageDate                      				");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkRptDailyEngDetailListForm workRptDailyEngDetailListForm,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        WorkRptDailyEngCommonDTO workRptDailyEngCommonDTO = workRptDailyEngDetailListForm.getWorkRptDailyEngCommonDTO();
        WorkRptDailyEngDetailListDTO workRptDailyEngDetailListDTO = workRptDailyEngDetailListForm.getWorkRptDailyEngDetailListDTO();
        
        query.getAndQuery("y.plant", workRptDailyEngDetailListDTO.getPlantId());
        query.getAndQuery("y.eqloc_id", workRptDailyEngDetailListDTO.getEqLocId());
        query.getAndQuery("y.usage_dept", workRptDailyEngDetailListDTO.getUsageDeptId());
        query.getAndQuery("y.eqctg_id", workRptDailyEngDetailListDTO.getEqCtgId());
        query.getAndQuery("y.equip_id", workRptDailyEngDetailListDTO.getEquipId());
        
        query.append("AND y.equip_id IS NOT NULL	");
        query.append("AND y.eqctg_id IS NOT NULL    ");
        query.append("AND y.plant IS NOT NULL		");
        query.append("AND y.usage_dept IS NOT NULL	");
        query.append("AND y.eqloc_id IS NOT NULL    ");
        
        String fromDate = CommonUtil.dateToData(workRptDailyEngCommonDTO.getFilterStartDate());
        String toDate   = CommonUtil.dateToData(workRptDailyEngCommonDTO.getFilterEndDate());
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND x.usage_date >= TO_DATE('"+fromDate+" 00:00:00','YYYYMMDD HH24:MI:SS')     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND x.usage_date <= TO_DATE('"+toDate+" 23:59:59','YYYYMMDD HH24:MI:SS')        ");
        }        
        
        // 공장
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = y.plant )", 
        		workRptDailyEngCommonDTO.getFilterPlant(), workRptDailyEngCommonDTO.getFilterPlantDesc());

        return query.toString();
    }
    
}