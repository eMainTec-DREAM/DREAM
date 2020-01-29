package dream.pers.appstb.ready.service;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;

/**
 * ���
 * @author  javaworker
 * @version $Id: AppReadyListService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
 * @since   1.0
 */
public interface AppReadyListService
{     
    /**
     *  �˻�
     * @author  javaworker
     * @version $Id: AppReadyListService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
     * @param user 
     * @param appReqCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findAppReadyList(AppReadyCommonDTO appReadyCommonDTO, User user);

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

    /**
     * ����ó��
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param apprlistIds 
     * @param user
     * @param appReadyCommonDTO 
     * @return 
     */
    public int approveLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception;
    
    public int referenceLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception;

    /**
     * �ݷ�ó��
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprusrIds
     * @param apprlistIds
     * @param compNo
     * @param appReadyCommonDTO 
     * @return 
     */
    public int rejectLine(String[] apprusrIds, String[] apprlistIds,User loginUser, AppReadyCommonDTO appReadyCommonDTO) throws Exception;   
    
    
    /**
     * ���� 
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @param user
     * @throws Exception
     */
    public void apprProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    
    public String findTotalCount(AppReadyCommonDTO appReadyCommonDTO, User user);

    
}