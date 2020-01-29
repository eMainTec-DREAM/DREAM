package common.util.service;

import java.io.IOException;
import java.util.List;

/**
 * Excel Download 클릭 (Excel 파일 저장, T2FILE/T2INTERFACE 입력, T2PURCHASE_REQ 에 Last Excel Download Date 입력)
 * @author  hiimkkm
 * @version $Id: InterfaceHandlerService.java,v 1.6 2013/12/17 02:37:21 hiimkkm Exp $
 * @since   1.0
 *
 */
public interface InterfaceHandlerService
{
    /**
     * Excel Download 클릭
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
