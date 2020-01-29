package dream.pers.appstb.prc.service;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * ���繮�� - ���
 * @author  javaworker
 * @version $Id: AppPrcListService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
 * @since   1.0
 */
public interface AppPrcListService
{     
    /**
     *  �˻�
     * @author  javaworker
     * @version $Id: AppPrcListService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
     * @param appReqCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findAppPrcList(AppReqCommonDTO appReqCommonDTO, User loginUser);

    /**
     *  ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return 
     */
    public int deleteLine(String[] deleteRows, String compNo);    
    
    public String findTotalCount(AppReqCommonDTO appReqCommonDTO, User user) throws Exception;

}