package common.report.dao;

import java.util.List;

/**
 * report ����
 * @author javaworker
 * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
 * @since 1.0
 */
public interface ReportDAO
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
    public String[][] executeQuery(String query);
  
    /**
     * Master ������ �����Ѵ�.
     * @author  javaworker
     * @version $Id: ReportDAO.java,v 1.1 2013/08/30 09:14:50 javaworker Exp $
     * @since   1.0
     * 
     * @param query
     * @return
     */
    public List executeMasterQuery(String query);
}
