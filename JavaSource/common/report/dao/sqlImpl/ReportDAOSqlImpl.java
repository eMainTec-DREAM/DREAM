package common.report.dao.sqlImpl;

import java.util.List;

import common.report.dao.ReportDAO;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;

/**
 * report 공통
 * @author javaworker
 * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
 * @since 1.0
 * @spring.bean id="reportDAOTarget"
 * @spring.txbn id="reportDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReportDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReportDAO
{
    /**
     * 조회 쿼리 실행
     * @author  javaworker
     * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
     * @since   1.0
     * 
     * @param query
     * @return
     */
    public String[][] executeQuery(String query)
    {
        List resultList = getJdbcTemplate().queryForList(query);
        return QuerySqlBuffer.toStringArray(resultList);
    }
  
    /**
     * Master 쿼리를 수행한다.
     * @author  javaworker
     * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
     * @since   1.0
     * 
     * @param query
     * @return
     */
    public List executeMasterQuery(String query)
    {
        return getJdbcTemplate().queryForList(query);
    }
}
