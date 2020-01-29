package dream.work.rpt.pm.ratio.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pm.ratio.dao.WorkRptPmRatioDetailDAO;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioDetailDTO;

/**
 * 계획보전율(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPmRatioDetailDAOTarget"
 * @spring.txbn id="workRptPmRatioDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmRatioDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptPmRatioDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmRatioDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String [] monthArr = DateUtil.getArrayBetweenMonth(workRptPmRatioDetailDTO.getStartDate().replaceAll("-", ""), workRptPmRatioDetailDTO.getEndDate().replaceAll("-", ""));
        for(int i=0; i<monthArr.length; i++) {
        	workRptPmRatioDetailDTO.setStartDate(monthArr[i]);
        	workRptPmRatioDetailDTO.setEndDate(monthArr[i]);
      
        if(i != 0) {
            query.append("UNION ALL");
        }
        query.append("SELECT        														 ");
        query.append("    x.month             						month				     ");
        query.append("    ,NVL(totCnt,0)  	 						totCnt                   ");
        query.append("    ,NVL(schedMaintCnt,0)                     schedMaintCnt       	 ");
        query.append("    ,NVL(prevMaintCnt,0)                      prevMaintCnt             ");
        query.append("    ,NVL(brkMaintCnt,0)                       brkMaintCnt              ");
        query.append("FROM (        ");
        query.append("SELECT 		");
        query.append("            x.eqloc_id 	eqLocId		");
        query.append("            ,x.full_desc	eqLocDesc	");
        query.append("    		  ,SUBSTR('"+monthArr[i]+"',1,4)||'-'||SUBSTR('"+monthArr[i]+"',5,2)	month         ");
        query.append("            ,(SELECT COUNT(1) 		");
        query.append("             FROM TAEQHISTORY a 		");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");			
        query.append("                                             SELECT eqloc_id								");
        query.append("                                             FROM TAEQLOC 								");
        query.append("                                            START WITH eqloc_id = x.eqloc_id				");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )				");
        query.append("             AND a.wo_type IN ('PMW', 'PMI', 'BM')		");
        query.append(this.getWhere(workRptPmRatioDetailDTO,loginUser));
        query.append("            ) totCnt					");
        query.append("             ,(SELECT COUNT(1) 		");
        query.append("             FROM TAEQHISTORY a 		");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");		
        query.append("                                             SELECT eqloc_id								");
        query.append("                                             FROM TAEQLOC 								");
        query.append("                                            START WITH eqloc_id = x.eqloc_id				");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )												");
        query.append("             AND a.wo_type = 'PMW'														");
        query.append(this.getWhere(workRptPmRatioDetailDTO,loginUser));
        query.append("            ) schedMaintCnt																");
        query.append("             ,(SELECT COUNT(1) 															");
        query.append("             FROM TAEQHISTORY a 															");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");			
        query.append("                                             SELECT eqloc_id								");
        query.append("                                             FROM TAEQLOC 								");
        query.append("                                            START WITH eqloc_id = x.eqloc_id				");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )		");
        query.append("             AND a.wo_type = 'PMI'		");
        query.append(this.getWhere(workRptPmRatioDetailDTO,loginUser));
        query.append("            ) prevMaintCnt            		");
        query.append("             ,(SELECT COUNT(1) 				");
        query.append("             FROM TAEQHISTORY a 				");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' AND b.is_last_version ='Y'		");
        query.append("             WHERE eqloc_id IN (		");
        query.append("                                             SELECT eqloc_id								");
        query.append("                                             FROM TAEQLOC 								");
        query.append("                                            START WITH eqloc_id = x.eqloc_id				");
        query.append("                                            CONNECT BY PRIOR eqloc_id = p_eqloc_id 		");
        query.append("                                          )		");
        query.append("             AND a.wo_type = 'BM'					");
        query.append(this.getWhere(workRptPmRatioDetailDTO,loginUser));
        query.append("            ) brkMaintCnt     		");
        query.append("FROM TAEQLOC x 						");
        query.append("WHERE x.is_use ='Y'					");
        query.append("AND x.eqloc_id ='"+workRptPmRatioDetailDTO.getEqLocId()+"'					");
        query.append(") x       ");

        }
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        
        query.append("    AND a.wkor_date IS NOT NULL       ");
        
        String fromDate = CommonUtil.dateToData(workRptPmRatioDetailDTO.getStartDate());
        String toDate   = CommonUtil.dateToData(workRptPmRatioDetailDTO.getEndDate());
        
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
    
}