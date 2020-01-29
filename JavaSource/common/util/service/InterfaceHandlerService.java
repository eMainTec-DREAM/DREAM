package common.util.service;

import java.io.IOException;
import java.util.List;

/**
 * Excel Download Ŭ�� (Excel ���� ����, T2FILE/T2INTERFACE �Է�, T2PURCHASE_REQ �� Last Excel Download Date �Է�)
 * @author  hiimkkm
 * @version $Id: InterfaceHandlerService.java,v 1.6 2013/12/17 02:37:21 hiimkkm Exp $
 * @since   1.0
 *
 */
public interface InterfaceHandlerService
{
    /**
     * Excel Download Ŭ��
     * @author  hiimkkm
     * @version $Id: InterfaceHandlerService.java,v 1.6 2013/12/17 02:37:21 hiimkkm Exp $
     * @since   1.0
     * 
     * @param interType
     * @param resultList
     * @param fileName
     * @param enterBy
     * @return
     * @throws IOException
     */
    String excelDownload(String interType, List resultList, String fileName, String enterBy) throws IOException;

}
