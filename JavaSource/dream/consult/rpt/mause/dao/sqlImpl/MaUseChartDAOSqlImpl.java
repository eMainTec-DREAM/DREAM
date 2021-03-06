package dream.consult.rpt.mause.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.consult.rpt.mause.dao.MaUseChartDAO;
import dream.consult.rpt.mause.dto.MaUseChartDTO;

/**
 * �����Ȳ DAO
 * @author  kim21017
 * @version $Id: MaUseChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maUseChartDAOTarget"
 * @spring.txbn id="maUseChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUseChartDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUseChartDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaUseChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUseChartDTO
     * @return List
     */
    public List findUseList(MaUseChartDTO maUseChartDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        String lang = maUseChartDTO.getUserLang();
        String[] dateArr = maUseChartDTO.getDateArrStr().split(",");
        
        query.append(" SELECT  (select DESCRIPTION from taplant where comp_no = '"+compNo+"' and plant = (select plant from tadept where comp_no = '"+compNo+"' and dept_id = x.dept_id)) AS PLANTDESC 		");
        query.append("        ,(CASE WHEN x.DEPT_ID=0 THEN '"+getLang("all", lang)+"' 		");
        query.append("         ELSE (select DESCRIPTION from tadept where comp_no = '"+compNo+"' and dept_id = x.dept_id) END)     AS DEPTDESC		");
        query.append("        ,(SELECT key_name                                                                                		");
        query.append("          FROM talang                                                                                        		");
        query.append("          WHERE 1=1                                                                                            		");
        query.getAndQuery("lang", lang);
    	query.getAndQuery("key_type", "CODESET");
    	query.append("           AND key_no = 'USINGRPT_TYPE.'+y.cdsysd_no)     AS TITLE    		");
        
		for (int b = 1; b < dateArr.length; b++) {
			
			String date = CommonUtil.convertDate(dateArr[b]);
			query.append(", ISNULL(SUM(CASE x.login_date WHEN '"+date+"' THEN x.d_value ELSE 0 END),0) AS \""+dateArr[b]+"\"	");
		}
		
        query.append("          , y.ord_no AS cdOrdNo                                                                                            		");
        
        query.append("          , (CASE WHEN x.DEPT_ID=0 THEN 0 		");
        query.append("             ELSE (select ORD_NO from tadept where comp_no = '"+compNo+"' and dept_id = x.dept_id) END) AS deptOrdNo    		");
        query.append("          , x.DEPT_ID        AS deptId		");
        
        query.append(" FROM TAUSRPTDAY x inner join TACDSYSD y		");
        query.append("    ON x.usingrpt_type = y.cdsysd_no		");
        
        query.append(" WHERE 1=1		");
        query.append(this.getWhere(maUseChartDTO, user));
        query.append(" GROUP BY y.cdsysd_no, y.ord_no, x.DEPT_ID		");
        query.getOrderByQuery("x.usrptday_id","deptOrdNo ASC, deptDesc, cdOrdNo ASC", maUseChartDTO.getOrderBy(), maUseChartDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maUseChartDTO.getIsLoadMaxCount(), maUseChartDTO.getFirstRow()));
    }
    
    private String getLang(String keyNo, String lang){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT key_name");
    	query.append("FROM TALANG");
    	query.append("WHERE 1=1");
    	query.getAndQuery("lang", lang);
    	query.getAndQuery("key_type", "LABEL");
    	query.append("AND key_no = '"+keyNo +"'");
    	
    	return (String) getJdbcTemplate().queryForObject(query.toString(), String.class); 
    }
    
    private String getWhere(MaUseChartDTO maUseChartDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndDateQuery("x.login_date", maUseChartDTO.getFilterStartDate(), maUseChartDTO.getFilterEndDate());
        query.getAndQuery("y.list_type", "USINGRPT_TYPE");
        query.getAndQuery("y.is_use", "Y");
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MaUseChartDTO maUseChartDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM (       ");
        query.append("    SELECT y.cdsysd_no, y.ord_no, x.DEPT_ID      ");
        query.append("    FROM TAUSRPTDAY x INNER JOIN TACDSYSD y       ");
        query.append("        ON x.usingrpt_type = y.cdsysd_no      ");
        query.append("    WHERE 1=1     ");
        query.append(this.getWhere(maUseChartDTO, user));
        query.append("    GROUP BY y.cdsysd_no, y.ord_no, x.DEPT_ID     ");
        query.append(") a     ");

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    
    

}