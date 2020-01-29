package common.util.dao;

import common.spring.BaseJdbcDaoSupportIntf;

/**
 * Excel Download Ŭ�� (T2FILE/T2INTERFACE �Է�, T2PURCHASE_REQ �� Last Excel Download Date �Է�)
 * @author  hiimkkm
 * @version $Id: InterfaceHandlerDAO.java,v 1.6 2014/05/20 07:26:20 pochul2423 Exp $
 * @since   1.0
 */
public interface InterfaceHandlerDAO extends BaseJdbcDaoSupportIntf
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
    public void insertFileInfo(String docType, String newFileNo, String objectNo, String enterBy, String fileName);

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
    public void insertInterface(String docType, String newFileNo, String enterBy);
}
