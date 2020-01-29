package common.report.dao;

import java.util.List;

/**
 * report 공통
 * @author javaworker
 * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
 * @since 1.0
 */
public interface ReportDAO
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
    public String[][] executeQuery(String query);
  
    /**
     * Master 쿼리를 수행한다.
     * @author  javaworker
     * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
     * @since   1.0
     * 
     * @param query
     * @return
     */
    public List executeMasterQuery(String query);
}
