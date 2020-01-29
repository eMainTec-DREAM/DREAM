package dream.consult.rpt.maconn.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.consult.rpt.maconn.dao.MaConnChartDAO;
import dream.consult.rpt.maconn.dto.MaConnChartDTO;

/**
 * 立加泅炔 DAO
 * @author  kim21017
 * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maConnChartDAOTarget"
 * @spring.txbn id="maConnChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaConnChartDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaConnChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConnChartDTO
     * @return List
     */
    public List findConnList(MaConnChartDTO maConnChartDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        String deptId = "".equals(maConnChartDTO.getFilterDeptId())?"0":maConnChartDTO.getFilterDeptId();
        String[] dateArr = maConnChartDTO.getDateArrStr().split(",");
        String[] typeArr = {"USR_MCNT","USR_ACNT","USR_LCNT"};//立加措惑, 立加磊荐, 立加冉荐
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                                                                                   ");
        query.append("    (SELECT description FROM TADEPT WHERE comp_no = '"+compNo+"' AND dept_id = b.dept_id)                    AS DEPTDESC ");
        query.append("    , b.dept_id                                                                                              AS ID       ");
        query.append("    , b.lvl                                                                                                  AS LVL      ");
        query.append("    , MIN(b.lvl) OVER()                                                                                      AS MINLVL   ");
        query.append("    , b.dept_id                                                                                              AS DEPTID   ");
        query.append("    , b.p_dept_id                                                                                            AS PDEPTID  ");
        for(int a=0; a<dateArr.length; a++) {
            String date = CommonUtil.convertDate(dateArr[a]);
            for(int b=0; b<typeArr.length; b++){
                query.append(", ISNULL(SUM(CASE WHEN a.login_date = '"+date+"' AND a.usingrpt_type = '"+typeArr[b]+"' THEN a.d_value ELSE 0 END),0) AS \""+typeArr[b]+dateArr[a]+"\"    ");
            }
        }
        query.append("    , (SELECT ORD_NO FROM tadept WHERE comp_no = '"+compNo+"' AND dept_id = b.dept_id)                       AS deptOrdNo");
        query.append("FROM TAUSRPTDAY a INNER JOIN dbo.SFADEPT_ALL('"+compNo+"',"+deptId+") b                                                  ");
        query.append("ON a.comp_no=b.comp_no                                                                                                   ");
        query.append("AND a.dept_id=b.dept_id                                                                                                  ");
        query.append("WHERE a.usingrpt_type IN('USR_MCNT','USR_ACNT','USR_LCNT')                                                               ");
        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("b.is_monitoring", "Y");
        query.getAndDateQuery("a.login_date", maConnChartDTO.getFilterStartDate(), maConnChartDTO.getFilterEndDate());
        query.append("GROUP BY b.dept_id, b.p_dept_id, b.lvl                                                                                   ");
        query.append("ORDER BY deptOrdNo ASC                                                                                                   ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConnChartDTO
     * @return List
     */
    public List findUsrList(MaConnChartDTO maConnChartDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String deptId = maConnChartDTO.getFilterDeptId();
        if(!"".equals(maConnChartDTO.getDeptId())){
        	deptId = maConnChartDTO.getDeptId();
        }else{
        	deptId = "0";
        }
        String[] dateArr = maConnChartDTO.getDateArrStr().split(",");
        String[] typeArr = {"U","C"};//立加磊荐, 立加冉荐
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT	a.dept AS deptDesc																			");
        query.append("			,(SELECT user_name FROM TAUSER																");
        query.append("			  WHERE comp_no = a.comp_no AND user_id= a.user_id) AS userName								");
        for (int i = 1; i < dateArr.length; i++) {
        	String date = CommonUtil.convertDate(dateArr[i]);
        	query.append(",case sum(case a.login_date when '"+date+"' then a.connCnt else 0 end) when 0 then 'N' else 'Y' end AS \""+typeArr[0]+dateArr[i]+"\"");
        	query.append(",sum(case a.login_date when '"+date+"' then a.connCnt else 0 end)	AS \""+typeArr[1]+dateArr[i]+"\"				");
		}
        query.append(",ISNULL(count(a.connCnt),0) total																		");
        query.append(",ISNULL(sum(a.connCnt), 0) totalSum																		");

        query.append("FROM																									");
        query.append("	(SELECT (SELECT description FROM TADEPT WHERE comp_no = tu.comp_no AND dept_id =					");
        query.append("				(SELECT dept_id FROM TAEMP WHERE comp_no=tu.comp_no AND emp_id = tu.emp_id)) dept		");
        query.append("			,tu.emp_id																					");
        query.append("			,tu.user_id																					");
        query.append("			,y.connCnt																					");
        query.append("			,y.login_date																				");
        query.append("			,tu.comp_no																					");
        query.append("	FROM TAUSER tu,																						");
        query.append("			(SELECT w.login_date,w.comp_no, x.user_id, y.emp_id											");
        query.append("					, count(*) connCnt																	");
        query.append("			FROM TALOGINCCLOG w, TAUSER x, TAEMP y, TADEPT z 											");
        query.append("			WHERE w.comp_no = x.comp_no 																");
        query.append("			AND w.user_id = x.user_id  																	");
        query.append("			AND x.comp_no= y.comp_no																	");
        query.append("			AND x.emp_id = y.emp_id																		");
        query.append("			AND y.comp_no = z.comp_no																	");
        query.append("			AND y.dept_id = z.dept_id																	");
        query.getAndDateQuery("w.login_date", maConnChartDTO.getFilterStartDate(), maConnChartDTO.getFilterEndDate());
        for (int i = 1; i < dateArr.length; i++) {
        	String date = CommonUtil.convertDate(dateArr[i]);
        	if (DateUtil.getDayOfWeek(date)==1||DateUtil.getDayOfWeek(date)==7) {
        		query.append("AND w.login_date != '"+date+"'																");
			}
        }
        query.getAndQuery("w.comp_no", maConnChartDTO.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.is_monitor", "Y");
        query.getAndQuery("y.is_join", "Y");
//        query.getAndQuery("z.dept_id", deptId);
        query.getDeptLevelQuery("z.dept_id", deptId, "", maConnChartDTO.getCompNo());
        query.append("			GROUP BY w.login_date,w.comp_no, x.user_id, y.emp_id) y										");
        query.append("	WHERE tu.comp_no = y.comp_no																		");
        query.append("	AND tu.emp_id = y.emp_id																			");
        query.append("  AND (SELECT dept_id FROM TAEMP WHERE comp_no=tu.comp_no AND emp_id = tu.emp_id)");
        query.append("    IN (SELECT dept_id  FROM tadept WHERE 1=1 														");
        query.getAndQuery("comp_no", user.getCompNo());
        query.append(")																										");
        query.append("	) a																									");
        query.append("GROUP BY comp_no,user_id,dept																			");
        query.append("ORDER BY totalSum desc,deptDesc																		");
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * chart find
     * @author  kim21017
     * @version $Id: MaConnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConnChartDTO
     * @return List
     */
    public List findConnChart(MaConnChartDTO maConnChartDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String type = maConnChartDTO.getType();
    	String deptId = maConnChartDTO.getDeptId();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                 ");
    	query.append("SELECT ROW_NUMBER() OVER(ORDER BY x.tday) AS ID					");
    	query.append("		,x.tday DAY													");
    	query.append("      ,SUBSTRING(SUBSTRING(CONVERT(VARCHAR, x.tday, 101),5,8),1,2) + '/' + SUBSTRING(SUBSTRING(CONVERT(VARCHAR, x.tday, 101),5,8),3,4) dayFormat     ");
		query.append("		,ISNULL((select d_value from tausrptday where login_date=x.tday and dept_id="+deptId+" and usingrpt_type='USR_ACNT'),0) cnt1				   ");
		query.append("		,ISNULL((select d_value from tausrptday where login_date=x.tday and dept_id="+deptId+" and usingrpt_type='USR_LCNT'),0) cnt2				   ");
        query.append("FROM TADAY x													");
    	query.append("WHERE 1 = 1									");
    	query.getAndDateQuery("x.tday", maConnChartDTO.getFilterStartDate(), maConnChartDTO.getFilterEndDate());
    	query.append("  AND x.dow NOT IN ('1','7')										");
//    	query.getAndQuery("comp_no", user.getCompNo());
    	query.append("ORDER BY x.tday													");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

}