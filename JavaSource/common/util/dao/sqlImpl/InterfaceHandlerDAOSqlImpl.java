package common.util.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import common.util.dao.InterfaceHandlerDAO;

/**
 * Excel Download 클릭 (T2FILE/T2INTERFACE 입력, T2PURCHASE_REQ 에 Last Excel Download Date 입력)
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
     * T2FILE 에 파일정보 입력
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
                docType,        // Object 구분
                objectNo,       // Object No
                newFileNo,      // 파일번호
                enterBy,        // 입력자
                fileName        // File Name
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * T2INTERFACE 에 입력
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
                docType,    // 인터페이스 구분
                enterBy     // 입력자
                };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }

}
