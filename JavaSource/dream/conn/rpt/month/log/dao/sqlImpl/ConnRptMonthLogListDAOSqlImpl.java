package dream.conn.rpt.month.log.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.conn.rpt.month.log.dao.ConnRptMonthLogListDAO;
import dream.conn.rpt.month.log.dto.ConnRptMonthLogListDTO;

/**
 * 월별접속현황 DAO
 * @author  sy.yang
 * @version $Id: ConnRptMonthLogListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 * @spring.bean id="connRptMonthLogListDAOTarget"
 * @spring.txbn id="connRptMonthLogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConnRptMonthLogListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConnRptMonthLogListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: ConnRptMonthLogListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param connRptMonthLogListDTO
     * @return List
     */
    public List findConnList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        String deptId = "".equals(connRptMonthLogListDTO.getFilterDeptId())?"0":connRptMonthLogListDTO.getFilterDeptId();
        String[] dateArr = connRptMonthLogListDTO.getDateArrStr().split(",");
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  														");
        query.append("SELECT                                                                                                               	  	");
        query.append("    (SELECT description FROM TADEPT WHERE comp_no = '"+compNo+"' AND dept_id = b.dept_id)             	AS DEPTDESC 	");
        query.append("    , b.dept_id                                                                                           AS ID       	");
        query.append("    , b.lvl                                                                                              	AS LVL      	");
        query.append("    , MIN(b.lvl) OVER()                                                                                  	AS MINLVL   	");
        query.append("    , b.dept_id                                                                                         	AS DEPTID   	");
        query.append("    , b.p_dept_id                                                                                        	AS PDEPTID  	");
        for(int a=0; a<dateArr.length; a++) {
            String date = CommonUtil.convertDate(dateArr[a]);
            query.append("    ,(SELECT                                                                                                     		");
            query.append("           COUNT(1)                                                                      		                      	");
            query.append("      FROM                                                                                    	                    ");
            query.append("        ( SELECT SUBSTRING(w.login_date,1,6) login_date, w.comp_no, y.emp_id, z.dept_id           	      			");
            query.append("          FROM TALOGINCCLOG w, TAUSER x, TAEMP y, TADEPT z                                               	            ");
            query.append("          WHERE w.comp_no = x.comp_no                         	         			          	        			");
            query.append("           AND w.user_id = x.user_id                              	         			          	        		");
            query.append("           AND x.comp_no = y.comp_no                              	         			          	         		 	");
            query.append("           AND x.emp_id = y.emp_id		          	         			          	         			    		");
            query.append("           AND y.comp_no = z.comp_no                              	         			          	         		 	");
            query.append("           AND y.dept_id = z.dept_id		          	         			          	         			    		");
            query.append("           AND z.is_use = 'Y'		          	         			          	         			    				");
            query.append("    	     AND x.is_monitor = 'Y'   		     																		");
            query.append("    	     AND z.is_monitoring = 'Y'   		     																	");
            query.append("           AND SUBSTRING(w.login_date,1,6) = '"+date+"'                                                          	  	");
            query.append("        ) a              																								");
            query.append("      WHERE 1=1                                                                 										");
            query.append(this.getWhere(connRptMonthLogListDTO, user,"b.dept_id"));
            query.append("     ) 																						AS \""+dateArr[a]+"\"	");
        }
        query.append("    , (SELECT ORD_NO FROM tadept WHERE comp_no = '"+compNo+"' AND dept_id = b.dept_id)					AS deptOrdNo	");
        query.append("FROM tadept a, dbo.SFADEPT_ALL('"+compNo+"',0) b                                                  						");
        query.append("WHERE a.comp_no = b.comp_no                                                              									");
        query.append(" ANd a.dept_id = b.dept_id                                                              									");
        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("a.is_use", "Y");
        query.getAndQuery("a.is_monitoring", "Y");
        query.append("  AND a.dept_id IN ( SELECT dept_id FROM dbo.SFADEPT_CALL('"+compNo+"',"+deptId+",''))									");
        if(!"".equals(connRptMonthLogListDTO.getFilterEmpId())){
        	query.append("  AND a.dept_id IN ( SELECT dept_id FROM dbo.SFADEPT_CALL('"+compNo+"',(SELECT dept_id FROM TAEMP where comp_no='"+compNo+"' and emp_id="+connRptMonthLogListDTO.getFilterEmpId()+"),''))									");
        }
        query.append("GROUP BY b.dept_id, b.p_dept_id, b.lvl                                                                                   	");
        query.append("ORDER BY deptOrdNo ASC                                                                                                   	");
         
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user, String deptId)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        String empId = connRptMonthLogListDTO.getFilterEmpId();
        deptId = "".equals(deptId)?"0":deptId;

        // 회사
        query.getAndQuery("a.comp_no",compNo);
        // 사원
        query.getAndQuery("a.emp_id",empId);
        // 조직
        query.append("AND a.dept_id IN (SELECT dept_id FROM SFADEPT_CALL('"+compNo+"',"+deptId+",'') )		");

        return query.toString();
    }
    
    /**
     * chart find
     * @author  sy.yang
     * @version $Id: ConnRptMonthLogListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param connRptMonthLogListDTO
     * @return List
     */
    public List findConnChart(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String startDate = connRptMonthLogListDTO.getFilterStartDate().replace("-", "");
    	String endDate = connRptMonthLogListDTO.getFilterEndDate().replace("-", "");
    	String deptId = "";
    	//목록에서 선택한 부서할 경우
        if(!"".equals(connRptMonthLogListDTO.getDeptId()))
    		deptId = connRptMonthLogListDTO.getDeptId();
        else{
        	if(!"".equals(connRptMonthLogListDTO.getFilterDeptId()))
        		deptId = connRptMonthLogListDTO.getFilterDeptId();
        }    	
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                			 		");
    	query.append("SELECT ROW_NUMBER() OVER(ORDER BY x.tmonth) 						AS ID			");
    	query.append("		,x.tmonth 													AS month		");
    	query.append("      ,SUBSTRING(CONVERT(VARCHAR, x.tmonth, 101),1,4)+'-'+SUBSTRING(CONVERT(VARCHAR, x.tmonth, 101),5,6)	AS monthFormat	");
    	query.append("    ,(SELECT                                                          			");
        query.append("           COUNT(1)     															");
        query.append("      FROM                                                                     	");
        query.append("    	( SELECT   																	");
        query.append("    	  	SUBSTRING(w.login_date,1,6) login_date, w.comp_no, y.emp_id, z.dept_id	");
        query.append("    	  FROM TALOGINCCLOG w, TAUSER x, TAEMP y, TADEPT z 							");
        query.append("    	  WHERE w.comp_no = x.comp_no    											");
        query.append("    	   AND w.user_id = x.user_id												");
        query.append("    	    AND x.comp_no = y.comp_no  												");
        query.append("    	     AND x.emp_id = y.emp_id      											");
        query.append("    	      AND y.comp_no = z.comp_no												");
        query.append("    	       AND y.dept_id = z.dept_id      										");
        query.append("    	        AND z.is_use = 'Y'		     										");
        query.append("    	         AND x.is_monitor = 'Y'   		     											");
        query.append("    	) a  																		");
        query.append("      WHERE 1=1                                                                 	");
        query.append("       AND a.login_date = x.tmonth                                               	");
        query.append(this.getWhere(connRptMonthLogListDTO, user, deptId));
        query.append("     ) 															AS cnt			");
        query.append("FROM TAMONTH x																	");
    	query.append("WHERE 1 = 1																		");
    	query.getAndDateQuery("x.tmonth", startDate, endDate);
    	query.append("ORDER BY x.tmonth																	");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findUsrList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        String filterDeptId = connRptMonthLogListDTO.getFilterDeptId();
        String[] dateArr = connRptMonthLogListDTO.getDateArrStr().split(",");
        String[] typeArr = {"U","C"};//접속자수, 접속횟수
        String startDate = connRptMonthLogListDTO.getFilterStartDate().replace("-", "")+"01";
        String endDate = connRptMonthLogListDTO.getFilterEndDate().replace("-", "")+"31";
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  									");
        query.append("SELECT	a.dept AS deptDesc																			");
        query.append("			,(SELECT emp_name 																			");
        query.append("			  FROM TAEMP																				");
        query.append("			  WHERE comp_no = a.comp_no																	");
        query.append("			   AND user_id = a.user_id																	");
        query.append("			   AND emp_id = a.emp_id) 											AS empName				");
        for (int i = 0; i < dateArr.length; i++) {
        	String date = CommonUtil.convertDate(dateArr[i]);
        	query.append(",case sum(case SUBSTRING(a.login_date,1,6) when '"+date+"' then a.connCnt else 0 end) when 0 then 'N' else 'Y' end AS \""+typeArr[0]+dateArr[i]+"\"");
        	query.append(",sum(case SUBSTRING(a.login_date,1,6) when '"+date+"' then a.connCnt else 0 end)	AS \""+typeArr[1]+dateArr[i]+"\"				");
		}
        query.append(",ISNULL(count(a.connCnt),0) total																		");
        query.append(",ISNULL(sum(a.connCnt), 0) totalSum																	");

        query.append("FROM (																								");
        query.append("	SELECT 																								");
        query.append("			(SELECT description 																		");
        query.append("			 FROM TADEPT  																				");
        query.append("			 WHERE comp_no = tu.comp_no 																");
        query.append("			  AND dept_id =	(SELECT dept_id FROM TAEMP WHERE comp_no = tu.comp_no AND emp_id = tu.emp_id)	");
        query.append("			) dept																						");
        query.append("			,(SELECT ord_no 																			");
        query.append("			 FROM TADEPT  																				");
        query.append("			 WHERE comp_no = tu.comp_no 																");
        query.append("			  AND dept_id =	(SELECT dept_id FROM TAEMP WHERE comp_no = tu.comp_no AND emp_id = tu.emp_id)	");
        query.append("			) ordNo																						");
        query.append("			,tu.emp_id																					");
        query.append("			,tu.user_id																					");
        query.append("			,y.connCnt																					");
        query.append("			,y.login_date																				");
        query.append("			,tu.comp_no																					");
        query.append("	FROM TAUSER tu LEFT OUTER JOIN (SELECT 																");
        query.append("										w.login_date,w.comp_no, x.user_id, count(*) connCnt				");
        query.append("									FROM TALOGINCCLOG w, TAUSER x, TAEMP y, TADEPT z 					");
        query.append("									WHERE w.comp_no = x.comp_no 										");
        query.append("									 AND w.user_id = x.user_id  										");
        query.append("									 AND x.comp_no= y.comp_no											");
        query.append("									 AND x.emp_id = y.emp_id											");
        query.append("									 AND y.comp_no = z.comp_no											");
        query.append("									 AND y.dept_id = z.dept_id											");
        query.getAndDateQuery("w.login_date", startDate, endDate);
        query.getAndQuery("w.comp_no", user.getCompNo());
        query.append("									GROUP BY w.login_date,w.comp_no, x.user_id) y						");
        query.append("	ON tu.comp_no = y.comp_no																			");
        query.append("	AND tu.user_id = y.user_id																			");
        query.append("	WHERE 1 = 1																							");
        query.getAndQuery("tu.emp_id", connRptMonthLogListDTO.getFilterEmpId());
        query.getAndQuery("tu.is_monitor", "Y");
        query.append("  AND (SELECT dept_id FROM TAEMP WHERE comp_no=tu.comp_no AND emp_id = tu.emp_id)						");
        query.append("    IN (SELECT dept_id  FROM tadept WHERE 1=1 														");
        query.getAndQuery("comp_no", user.getCompNo());
        query.getAndQuery("is_use", "Y");
        query.getAndQuery("is_monitoring", "Y");
         //목록에서 선택한 부서할 경우
        if(!"".equals(connRptMonthLogListDTO.getDeptId())){
        	query.append("AND dept_id IN (SELECT dept_id FROM SFADEPT_CALL('"+user.getCompNo()+"',"+connRptMonthLogListDTO.getDeptId()+",'') )		");
        }else{
        	if ("".equals(filterDeptId)) {
        		query.append("AND dept_id IN (SELECT dept_id FROM SFADEPT_CALL('"+user.getCompNo()+"',0,'') )					");
        	}else{
        		query.append("AND dept_id IN (SELECT dept_id FROM SFADEPT_CALL('"+user.getCompNo()+"',"+filterDeptId+",'') )	");
        	}
        }
        query.append(")																										");
        query.append("	) a																									");
        query.append("GROUP BY comp_no, user_id, dept, ordNo, emp_id														");
        query.append("ORDER BY totalSum desc, ordNo, deptDesc																");
        
    	
    	return getJdbcTemplate().queryForList(query.toString());
	}
}