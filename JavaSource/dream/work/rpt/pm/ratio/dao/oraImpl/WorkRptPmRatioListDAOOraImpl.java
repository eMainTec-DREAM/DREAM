package dream.work.rpt.pm.ratio.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pm.ratio.dao.WorkRptPmRatioListDAO;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioCommonDTO;

/**
 * 계획보전율(위치) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPmRatioListDAOTarget"
 * @spring.txbn id="workRptPmRatioListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmRatioListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptPmRatioListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmRatioCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 		");
        query.append("            x.eqloc_id 	eqLocId		");
        query.append("            ,x.full_desc	eqLocDesc	");
        query.append("            ,(SELECT COUNT(1) 		");
        query.append("             FROM TAEQHISTORY a 		");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");
        query.append("                                             SELECT eqloc_id		");
        query.append("                                             FROM TAEQLOC 		");
        query.append("                                            START WITH eqloc_id = x.eqloc_id		");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )		");
        query.append("             AND a.wo_type IN ('PMW', 'PMI', 'BM')		");
        query.append(this.getWhere(workRptPmRatioCommonDTO,loginUser));
        query.append("            ) totCnt		");
        query.append("             ,(SELECT COUNT(1) 		");
        query.append("             FROM TAEQHISTORY a 		");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");
        query.append("                                             SELECT eqloc_id		");
        query.append("                                             FROM TAEQLOC 		");
        query.append("                                            START WITH eqloc_id = x.eqloc_id		");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )		");
        query.append("             AND a.wo_type = 'PMW'		");
        query.append(this.getWhere(workRptPmRatioCommonDTO,loginUser));
        query.append("            ) schedMaintCnt		");
        query.append("             ,(SELECT COUNT(1) 		");
        query.append("             FROM TAEQHISTORY a 		");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");
        query.append("                                             SELECT eqloc_id		");
        query.append("                                             FROM TAEQLOC 		");
        query.append("                                            START WITH eqloc_id = x.eqloc_id		");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )		");
        query.append("             AND a.wo_type = 'PMI'		");
        query.append(this.getWhere(workRptPmRatioCommonDTO,loginUser));
        query.append("            ) prevMaintCnt            		");
        query.append("             ,(SELECT COUNT(1) 		");
        query.append("             FROM TAEQHISTORY a 		");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");
        query.append("                                             SELECT eqloc_id		");
        query.append("                                             FROM TAEQLOC 		");
        query.append("                                            START WITH eqloc_id = x.eqloc_id		");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )		");
        query.append("             AND a.wo_type = 'BM'		");
        query.append(this.getWhere(workRptPmRatioCommonDTO,loginUser));
        query.append("            ) brkMaintCnt     		");
        query.append("FROM TAEQLOC x 		");
        query.append("WHERE x.is_use ='Y'		");
        query.getEqLocLevelQuery("x.eqloc_id", workRptPmRatioCommonDTO.getFilterEqLocId(), workRptPmRatioCommonDTO.getFilterEqLocDesc(), loginUser.getCompNo());
        query.getSysCdQuery("x.eqloc_lvl_type", workRptPmRatioCommonDTO.getFilterEqLocLevel(), workRptPmRatioCommonDTO.getFilterEqLocLevelDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        query.getOrderByQuery("x.full_desc", workRptPmRatioCommonDTO.getOrderBy(), workRptPmRatioCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmRatioCommonDTO.getIsLoadMaxCount(), workRptPmRatioCommonDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        
        query.append("AND a.wkor_date IS NOT NULL      ");
        
        String fromDate = CommonUtil.dateToData(workRptPmRatioCommonDTO.getFilterStartDate());
        String toDate   = CommonUtil.dateToData(workRptPmRatioCommonDTO.getFilterEndDate());
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  TO_CHAR(TO_DATE('"+fromDate+"','YYYY-MM'),'YYYYMMDD')     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <  TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYY-MM'),1),'YYYYMMDD')        ");
        }        
        
        return query.toString();
    }

    @Override
    public String findTotalCount(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 		");
        query.append("       COUNT(1)    	");
        query.append("FROM TAEQLOC x 		");
        query.append("WHERE is_use ='Y'		");
        query.getEqLocLevelQuery("x.eqloc_id", workRptPmRatioCommonDTO.getFilterEqLocId(), workRptPmRatioCommonDTO.getFilterEqLocDesc(), loginUser.getCompNo());
        query.getSysCdQuery("x.eqloc_lvl_type", workRptPmRatioCommonDTO.getFilterEqLocLevel(), workRptPmRatioCommonDTO.getFilterEqLocLevelDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}