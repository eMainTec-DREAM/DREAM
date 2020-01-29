package common.report.dao.oraImpl;

import java.util.List;

import common.report.dao.ReportDAO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * report ����
 * @author javaworker
 * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
 * @since 1.0
 * @spring.bean id="reportDAOTarget"
 * @spring.txbn id="reportDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReportDAOOraImpl extends BaseJdbcDaoSupportOra implements ReportDAO
{
    /**
     * ��ȸ ���� ����
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
        return QueryBuffer.toStringArray(resultList);
    }
  
    /**
     * Master ������ �����Ѵ�.
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
