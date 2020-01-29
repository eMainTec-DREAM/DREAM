package dream.app.not.service;

import java.util.List;

import dream.app.doc.dto.AppDocReqDTO;
import dream.app.not.dto.AppNotCommonDTO;

/**
 * �뺸���� - ���
 * @author  javaworker
 * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
 * @since   1.0
 */
public interface AppNotListService
{     
    /**
     * ���˹�ȣ �˻�
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @param appNotCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findAppNotList(AppNotCommonDTO appNotCommonDTO);    

    /**
     * ���˹�ȣ �˻� �Ǽ�
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @since   1.0
     * 
     * @param appNotCommonDTO
     * @return
     */
    public int findAppNotListCount(AppNotCommonDTO appNotCommonDTO);

    /**
     * �뺸���� Ȯ��
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @since   1.0
     * 
     * @param appNotList
     * @param appNotCommonDTO
     */
    public void confirmAppNot(List appNotList, AppNotCommonDTO appNotCommonDTO);
    
    /**
     * ���� �뺸ó��
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     */
    void inputAppDocNot(AppDocReqDTO appDocReqDTO);
}