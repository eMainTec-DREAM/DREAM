package dream.pers.appstb.ready.dao;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;

/**
 * ���
 * @author  javaworker
 * @version $Id: maAppLineUsrPopupDetailListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since   1.0
 */
public interface AppReadyListDAO
{
    public List findList(AppReadyCommonDTO appReadyCommonDTO, User user);
    


    public int deleteLine(String id, String compNo);
    /**
     * ������ ���� �� �׼� ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @param apprAction
     * @param apprStatus
     * @return
     */
    public int approveLine(String apprUsrId, User user, String apprAction, String apprStatus, AppReadyCommonDTO appReadyCommonDTO);

    /**
     * ���������� ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprlistId
     * @param compNo
     * @return
     */
    public int updateApprovalLine(String apprUsrId, User user);
    /**
     * ���� ��û ���� ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprlistId
     * @param compNo
     * @param apprStatus
     */
    public int updateApproveList(String apprusrId, User user, String apprStatus);
    
    /**
     * �ݷ�ó��.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprlistId
     * @param compNo
     * @param apprStatus
     */
    public int updateRejectList(String apprusrId, User user);
    
    
    /**
     * ���簡 ����,�ݷ�,�������� Ȯ��.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprUsrId
     * @param user
     */
    public String checkApprUsrType(String apprUsrId, User user);
    
    /**
     * ���Ϸ����� �����ڰ� �����ϴ��� Ȯ��.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprUsrId
     * @param user
     */
    public String isParalApprUser(String apprUsrId, User user);
    
    
    /**
     * �����ڸ� ��� ���·� ����ó����.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprUsrId
     * @param user
     */
    public int updateReferenceLine(String apprUsrId, User user);
    
    
    
    
    public AppReqDetailDTO findListDetail(String apprusrId, User loginUser);
    
    public String findTotalCount(AppReadyCommonDTO appReadyCommonDTO, User user);

    
    
}