package dream.work.rpt.org.mtbfmttr.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.rpt.org.mtbfmttr.dao.WorkRptOrgMtbfmttrListDAO;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrCommonDTO;

/**
 * 조직별MTBF,MTTR 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptOrgMtbfmttrListDAOTarget"
 * @spring.txbn id="workRptOrgMtbfmttrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptOrgMtbfmttrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptOrgMtbfmttrListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptOrgMtbfmttrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT											");
        query.append("    ''                             AS SEQNO		");
        query.append("  , v.deptid                       AS DEPTID		");
        query.append("  , v.orgname                      AS ORGNAME		");
        query.append("  , ROUND(  ISNULL(  NULLIF(v.totTime,0)      / NULLIF(v.bmCnt,0)  ,0)  ,2)    AS MTBFHOUR    		");
        query.append("  , ROUND(  ISNULL(  NULLIF(v.totWorkHour,0)  / NULLIF(v.bmCnt,0)  ,0)  ,2)    AS MTTRHOUR    		");
//        query.append("  , ROUND(v.totTime/v.bmCnt,2)     AS MTBFHOUR    ");
//        query.append("  , ROUND(v.totWorkHour/v.bmCnt,2) AS MTTRHOUR    ");
        query.append("FROM (											");
        query.append("	SELECT											");
        query.append("      x.dept_id 					 AS DEPTID		");
        query.append("    , x.description 				 AS ORGNAME		");
        query.append("    , (SELECT 									");
        query.append("           ROUND(ISNULL(SUM(a.work_time),0)/60,1) ");
        query.append("       FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b");
        query.append("       ON a.item_no=b.item_no                     ");
        query.append("       AND a.comp_no=b.comp_no                    ");
        query.append("       WHERE 1=1                                  ");
        query.append(this.getDeptWhere(workRptOrgMtbfmttrCommonDTO,loginUser));
        query.append("       ) 						     AS TOTWORKHOUR ");
        query.append("    , (SELECT 									");
        query.append("           COUNT(eqhistory_id)                    ");
        query.append("        FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b ");
        query.append("        ON a.item_no=b.item_no                    ");
        query.append("        AND a.comp_no=b.comp_no                   ");
        query.append("        WHERE 1=1                                 ");
        query.append(this.getDeptWhere(workRptOrgMtbfmttrCommonDTO,loginUser));
        query.append("       ) 						  	 AS BMCNT		");
        query.append("    , (SELECT 									");
        query.append("    		 ROUND(SUM( (ISNULL(c.dtime,0)+ISNULL(c.ntime,0)+ISNULL(c.etime,0))),1)     /60   / count(distinct(c.plant))	");
        query.append("       FROM TALNWRKTIME c                  		");
        query.append("       WHERE c.comp_no = x.comp_no         		");
        query.append(this.getDateWhere(workRptOrgMtbfmttrCommonDTO,loginUser));
        query.append("       ) 							 AS TOTTIME 	");
        query.append("FROM TADEPT x 									");
        query.append("WHERE 1=1											");
        query.append(this.getWhere(workRptOrgMtbfmttrCommonDTO,loginUser));
        query.append(") v		");
        
        query.getOrderByQuery("v.DEPTID", "v.ORGNAME", workRptOrgMtbfmttrCommonDTO.getOrderBy(), workRptOrgMtbfmttrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptOrgMtbfmttrCommonDTO.getIsLoadMaxCount(), workRptOrgMtbfmttrCommonDTO.getFirstRow()));
    }
    
    public String getDateWhere(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String fromDate = CommonUtil.dateToData(workRptOrgMtbfmttrCommonDTO.getFilterStartDate()+"-01");
    	String toDate   = CommonUtil.dateToData(workRptOrgMtbfmttrCommonDTO.getFilterEndDate()+"-01");
    	
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND c.wrk_date >= '"+fromDate+"'     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND c.wrk_date <= convert(nvarchar,dateadd(day,-1,dateadd(month,1,'"+toDate+"')),112)        ");
        } 
        
        //공장코드
        query.getCodeLikeQuery("c.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = c.plant )", 
                workRptOrgMtbfmttrCommonDTO.getFilterPlantId(), workRptOrgMtbfmttrCommonDTO.getFilterPlantDesc());
        
    	return query.toString();
    }
    public String getWhere(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // 회사번호
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());

    	// 사용여부
    	query.getAndQuery("x.is_use", "Y");
    	
    	// 조직구분
    	query.getCodeLikeQuery("x.dept_categ", "dbo.SFACODE_TO_DESC(x.dept_categ,'DEPT_CATEG','SYS','','"+loginUser.getLangId()+"')", workRptOrgMtbfmttrCommonDTO.getFilterDeptCategId(), workRptOrgMtbfmttrCommonDTO.getFilterDeptCategDesc());
        
    	// 부서
        query.getDeptLevelQuery("x.dept_id", workRptOrgMtbfmttrCommonDTO.getFilterDeptId(), workRptOrgMtbfmttrCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());
        
        //공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
                workRptOrgMtbfmttrCommonDTO.getFilterPlantId(), workRptOrgMtbfmttrCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    public String getDeptWhere(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.wo_type", "BM");
        query.getAndQuery("b.is_last_version", "Y");
        query.append("AND a.wkor_date IS NOT NULL   ");
        query.append("AND a.dept_id IS NOT NULL		");
        query.append("AND b.dept_id IS NOT NULL		");
	
        String fromDate = CommonUtil.dateToData(workRptOrgMtbfmttrCommonDTO.getFilterStartDate()+"-01");
    	String toDate   = CommonUtil.dateToData(workRptOrgMtbfmttrCommonDTO.getFilterEndDate()+"-01");
    	
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >= '"+fromDate+"'     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <= convert(nvarchar,dateadd(day,-1,dateadd(month,1,'"+toDate+"')),112)        ");
        } 
        
        query.append("AND b.dept_id IN (SELECT c.dept_id                                    		");
        query.append("                  FROM dbo.SFADEPT_CALL(x.comp_no, x.dept_id,'') c )    		");
        
        //공장코드
        query.getCodeLikeQuery("b.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = b.plant )", 
                workRptOrgMtbfmttrCommonDTO.getFilterPlantId(), workRptOrgMtbfmttrCommonDTO.getFilterPlantDesc());

    	return query.toString();
    }
    
    @Override
    public String findTotalCount(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT COUNT(1)                                                    ");
        query.append("FROM TADEPT x 	");
        query.append("WHERE 1=1			");
        query.append(this.getWhere(workRptOrgMtbfmttrCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}