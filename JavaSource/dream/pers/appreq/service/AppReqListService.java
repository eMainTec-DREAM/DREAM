package dream.pers.appreq.service;

import java.util.List;

import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * ��û���� - ���
 * @author  javaworker
 * @version $Id: AppReqListService.java,v 1.1 2013/08/30 09:14:06 javaworker Exp $
 * @since   1.0
 */
public interface AppReqListService
{     
    /**
     * ���˹�ȣ �˻�
     * @author  javaworker
     * @version $Id: AppReqListService.java,v 1.1 2013/08/30 09:14:06 javaworker Exp $
     * @param appReqCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findAppReqList(AppReqCommonDTO appReqCommonDTO);    

    /**
     * ���˹�ȣ �˻� �Ǽ�
     * @author  javaworker
     * @version $Id: AppReqListService.java,v 1.1 2013/08/30 09:14:06 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public int findAppReqListCount(AppReqCommonDTO appReqCommonDTO);
}