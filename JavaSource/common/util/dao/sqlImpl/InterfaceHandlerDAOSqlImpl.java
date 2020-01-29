package common.util.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import common.util.dao.InterfaceHandlerDAO;

/**
 * Excel Download Ŭ�� (T2FILE/T2INTERFACE �Է�, T2PURCHASE_REQ �� Last Excel Download Date �Է�)
 * @author  hiimkkm
 * @version $Id: InterfaceHandlerDAO.java,v 1.6 2014/05/20 07:26:20 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="interfaceHandlerDAOTarget"
 * @spring.txbn id="interfaceHandlerDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InterfaceHandlerDAOSqlImpl  extends BaseJdbcDaoSupportSql implements InterfaceHandlerDAO
{
    /**
     * T2FILE �� �������� �Է�
     * @author  hiimkkm
     * @version $Id: InterfaceHandlerDAO.java,v 1.6 2014/05/20 07:26:20 pochul2423 Exp $
     * @since   1.0
     * 
     * @param docType
     * @param newFileNo
     * @param objectNo
     * @param enterBy
     * @param fileName
     */
    public void insertFileInfo(String docType, String newFileNo, String objectNo, String enterBy, String fileName)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        //file_size
        query.append("INSERT INTO T2FILE (                                  ");
        query.append("    doc_type,                         object_no,      ");
        query.append("    file_no,                          enter_by,       ");
        query.append("    enter_date,                       file_type,      ");
        query.append("    file_name,                        enter_time      ");
        query.append(")                                                     ");
        query.append("VALUES  (                                             ");
        query.append("    ?,                                ?,              ");
        query.append("    ?,                                ?,              ");
        query.append("    CONVERT(VARCHAR, GETDATE(), 112),     'xls',          ");
        query.append("    ?,                                LEFT(REPLACE(CONVERT(VARCHAR, GETDATE(), 108),':',''),4)    ");
        query.append(")                                                     ");
        
        Object[] objects = {
                docType,        // Object ����
                objectNo,       // Object No
                newFileNo,      // ���Ϲ�ȣ
                enterBy,        // �Է���
                fileName        // File Name
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * T2INTERFACE �� �Է�
     * @author  hiimkkm
     * @version $Id: InterfaceHandlerDAO.java,v 1.6 2014/05/20 07:26:20 pochul2423 Exp $
     * @since   1.0
     * 
     * @param docType
     * @param newFileNo
     * @param enterBy
     */
    public void insertInterface(String docType, String newFileNo, String enterBy)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO T2INTERFACE (                     ");
        query.append("    file_no,         inter_type,              ");
        query.append("    interface_ynx,   enter_by,                ");
        query.append("    enter_date                                ");
        query.append(")                                             ");
        query.append("VALUES  (                                     ");
        query.append("    ?,               ?,                       ");
        query.append("    'Y',             ?,                       ");
        query.append("    CONVERT(VARCHAR, GETDATE(), 112)              ");
        query.append(")                                             ");

        Object[] objects = new Object[] {  
                newFileNo,  // File No
                docType,    // �������̽� ����
                enterBy     // �Է���
                };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }

}
