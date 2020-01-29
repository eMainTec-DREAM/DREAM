package dream.conn.rpt.month.log.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.conn.rpt.month.log.dao.ConnRptMonthLogListDAO;
import dream.conn.rpt.month.log.dto.ConnRptMonthLogListDTO;

/**
 * 접속현황 DAO
 * @author  sy.yang
 * @version $Id: ConnRptMonthLogListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 * @spring.bean id="connRptMonthLogListDAOTarget"
 * @spring.txbn id="connRptMonthLogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConnRptMonthLogListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConnRptMonthLogListDAO
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
    	QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        String deptId = "".equals(connRptMonthLogListDTO.getFilterDeptId())?"0":connRptMonthLogListDTO.getFilterDeptId();
        String[] dateArr = connRptMonthLogListDTO.getDateArrStr().split(",");
        
        query.append("SELECT                                                                                                                   	");
        query.append("    (SELECT description FROM TADEPT WHERE comp_no = '"+compNo+"' AND dept_id = b.dept_id)                    AS DEPTDESC 	");
        query.append("    , b.dept_id                                                                                              AS ID       	");
        query.append("    , b.lvl                                                                                                  AS LVL      	");
        query.append("    , MIN(b.lvl) OVER()                                                                                      AS MINLVL   	");
        query.append("    , b.dept_id                                                                                              AS DEPTID   	");
        query.append("    , b.p_dept_id                                                                                            AS PDEPTID  	");
        for(int a=0; a<dateArr.length; a++) {
            String date = CommonUtil.convertDate(dateArr[a]);
            query.append("    ,(SELECT                                                                     										");
            query.append("           COUNT(1)     																								");
            query.append("      FROM                                                                     										");
            query.append("    	( SELECT SUBSTR(w.login_date,0,6) login_date, w.comp_no, y.emp_id, z.dept_id  									");
            query.append("    	  FROM TALOGINCCLOG w, TAUSER x, TAEMP y, TADEPT z                               								");
            query.append("        WHERE w.comp_no = x.comp_no                         	         			          	        				");
            query.append("         AND w.user_id = x.user_id                              	         			          	        			");
            query.append("         AND x.comp_no = y.comp_no                              	         			          	         		 	");
            query.append("         AND x.emp_id = y.emp_id		          	         			          	         			    			");
            query.append("         AND y.comp_no = z.comp_no                              	         			          	         		 	");
            query.append("         AND y.dept_id = z.dept_id		          	         			          	         			    		");
            query.append("           AND z.is_use = 'Y'		          	         			          	         			    				");
            query.append("    	     AND x.is_monitor = 'Y'   		     																		");
            query.append("    	     AND z.is_monitoring = 'Y'   		     																	");
            query.append("    	     AND SUBSTR(w.login_date,0,6) = '"+date+"'    																");
            query.append("    	) a  																											");
            query.append("      WHERE 1=1                                                                 										");
            query.append(this.getWhere(connRptMonthLogListDTO, user,"b.dept_id"));
            query.append("     ) 																						AS \""+dateArr[a]+"\"	");
        } 
        query.append("FROM                                                                                             							");
        query.append("    ( SELECT comp_no, dept_id, p_dept_id, ord_no, LEVEL AS lvl                                   							");
        query.append("      FROM TADEPT                                                                               							");
        query.append("      WHERE 1 = 1                                                                                							");
        query.getAndQuery("comp_no", compNo);
        query.getAndQuery("is_use", "Y");
        query.getAndQuery("is_monitoring", "Y");
        query.append("      START WITH p_dept_id = '0'                                                          								");
        query.append("      CONNECT BY PRIOR dept_id = p_dept_id                                                       							");
        query.append("    ) b                                                                                       							");
        query.append("WHERE 1=1                                                               													");        
        if(!"0".equals(deptId)){
        	query.append(" AND b.dept_id IN ( SELECT dept_id FROM TADEPT WHERE comp_no='"+compNo+"' START WITH dept_id="+deptId+" CONNECT BY PRIOR dept_id = p_dept_id )	");
        }
        if(!"".equals(connRptMonthLogListDTO.getFilterEmpId())){
        	query.append(" AND b.dept_id IN ( SELECT dept_id                                                                                    ");
        query.append("						  FROM TADEPT WHERE comp_no='"+compNo+"'                                                                                       							");
        query.append("						  START WITH dept_id=(SELECT dept_id FROM TAEMP where comp_no='"+compNo+"' and emp_id="+connRptMonthLogListDTO.getFilterEmpId()+")	");
        query.append("						   CONNECT BY PRIOR dept_id = p_dept_id                                                             ");
        query.append("						)																									");
        }
        query.append("GROUP BY b.comp_no, b.dept_id, b.p_dept_id, b.lvl, b.ord_no                                                               ");
        query.append("ORDER BY TO_NUMBER(b.ord_no)															                                   	");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    public String getWhere(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user, String deptId)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        String empId = connRptMonthLogListDTO.getFilterEmpId();
        
        // 회사
        query.getAndQuery("a.comp_no",compNo);
        // 사원
        query.getAndQuery("a.emp_id",empId);
        // 조직
        query.append("AND a.dept_id IN ( SELECT dept_id                     	");
        query.append("					 FROM TADEPT                          	");
        query.append("					 WHERE comp_no = '"+compNo+"'			");
        query.append("                    AND  is_use = 'Y'             		");
        query.getAndQuery("is_monitoring", "Y");
        if(!"".equals(deptId)){
        	query.append("					START WITH dept_id = "+deptId+"  	");
        }
        query.append("					 CONNECT BY PRIOR dept_id = p_dept_id	");
        query.append("				  )											");
        
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
    	QueryBuffer query = new QueryBuffer();

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
    	
    	query.append("SELECT ROW_NUMBER() OVER(ORDER BY x.tmonth) 							AS ID					");
    	query.append("		,x.tmonth 														AS month				");
    	query.append("		,TO_CHAR(TO_DATE(x.tmonth,'yyyymm'),'yyyy-mm') 					AS monthFormat			");
        query.append("    ,(SELECT                                                                     				");
        query.append("           COUNT(1)     																		");
        query.append("      FROM                                                                     				");
        query.append("    	( SELECT   																				");
        query.append("    	  	SUBSTR(w.login_date,0,6) login_date, w.comp_no, x.user_id, y.emp_id, z.dept_id		");
        query.append("    	  FROM TALOGINCCLOG w, TAUSER x, TAEMP y, TADEPT z 										");
        query.append("    	  WHERE w.comp_no = x.comp_no    														");
        query.append("    	   AND w.user_id = x.user_id															");
        query.append("    	    AND x.comp_no= y.comp_no  															");
        query.append("    	     AND x.emp_id = y.emp_id 															");
        query.append("    	      AND y.comp_no = z.comp_no															");
        query.append("    	       AND y.dept_id = z.dept_id      													");
        query.append("    	        AND z.is_use = 'Y'		     													");
        query.append("    	         AND x.is_monitor = 'Y'   		     											");
        query.append("    	) a  																					");
        query.append("      WHERE 1=1                                                                 				");
        query.append("       AND a.login_date = x.tmonth                                               				");
        query.append(this.getWhere(connRptMonthLogListDTO, user, deptId ));
        query.append("     ) 																AS cnt					");
        query.append("FROM TAMONTH x  																				");
    	query.append("WHERE 1 = 1																					");
    	query.getAndDateQuery("x.tmonth", startDate, endDate);
    	query.append("ORDER BY x.tmonth																				");
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * grid find
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param connRptMonthLogListDTO
     * @return List
     */
    public List findUsrList(ConnRptMonthLogListDTO connRptMonthLogListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String filterDeptId = connRptMonthLogListDTO.getFilterDeptId();
        String[] dateArr = connRptMonthLogListDTO.getDateArrStr().split(",");
        String[] typeArr = {"U","C"};//접속자수, 접속횟수
        String startDate = connRptMonthLogListDTO.getFilterStartDate().replace("-", "")+"01";
        String endDate = connRptMonthLogListDTO.getFilterEndDate().replace("-", "")+"31";
        
        query.append("SELECT	a.dept AS deptDesc																			");
        query.append("			,(SELECT emp_name 																			");
        query.append("			  FROM TAEMP																				");
        query.append("			  WHERE comp_no = a.comp_no																	");
        query.append("			   AND user_id = a.user_id																	");
        query.append("			   AND emp_id = a.emp_id) 										AS empName					");
        for (int i = 0; i < dateArr.length; i++) {
        	String date = CommonUtil.convertDate(dateArr[i]);
        	query.append(",decode(sum(decode(substr(a.login_date,0,6),'"+date+"',a.connCnt,0)),0,'N','Y') AS \""+typeArr[0]+dateArr[i]+"\"");
        	query.append(",sum(decode(substr(a.login_date,0,6),'"+date+"',a.connCnt,0))	AS \""+typeArr[1]+dateArr[i]+"\"	");
		}
        query.append(",NVL(count(a.connCnt),0) total																		");
        query.append(",NVL(sum(a.connCnt), 0) totalSum																		");

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
        query.append("  AND (SELECT dept_id FROM TAEMP WHERE comp_no=tu.comp_no AND emp_id = tu.emp_id)");
        query.append("    IN (SELECT dept_id  FROM tadept WHERE 1=1 														");
        query.getAndQuery("comp_no", user.getCompNo());
        query.getAndQuery("is_use", "Y");
        query.getAndQuery("is_monitoring", "Y");
        //목록에서 선택한 부서할 경우
        if(!"".equals(connRptMonthLogListDTO.getDeptId())){
    		query.append("START WITH dept_id = "+connRptMonthLogListDTO.getDeptId()+"										");
        }else{
        	if ("".equals(filterDeptId)) {
        		query.append("START WITH p_dept_id = 0 																		");
        	}else{
        		query.append("START WITH dept_id = "+filterDeptId+" 														");
        	}
        }
        query.append("CONNECT BY PRIOR dept_id = p_dept_id																	");
        query.append(")																										");
        query.append("	) a																									");
        query.append("GROUP BY comp_no, user_id, dept, ordNo, emp_id																	");
        query.append("ORDER BY totalSum desc, ordNo, deptDesc																		");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
}