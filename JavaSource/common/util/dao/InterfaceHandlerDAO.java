package common.util.dao;

import common.spring.BaseJdbcDaoSupportIntf;

/**
 * Excel Download 클릭 (T2FILE/T2INTERFACE 입력, T2PURCHASE_REQ 에 Last Excel Download Date 입력)
 * @author  hiimkkm
 * @version $Id: InterfaceHandlerDAO.java,v 1.6 2014/05/20 07:26:20 pochul2423 Exp $
 * @since   1.0
 */
public interface InterfaceHandlerDAO extends BaseJdbcDaoSupportIntf
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
    public void insertFileInfo(String docType, String newFileNo, String objectNo, String enterBy, String fileName);

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
    public void insertInterface(String docType, String newFileNo, String enterBy);
}
