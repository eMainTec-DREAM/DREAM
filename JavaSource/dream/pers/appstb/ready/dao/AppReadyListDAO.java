package dream.pers.appstb.ready.dao;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;

/**
 * 목록
 * @author  javaworker
 * @version $Id: maAppLineUsrPopupDetailListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since   1.0
 */
public interface AppReadyListDAO
{
    public List findList(AppReadyCommonDTO appReadyCommonDTO, User user);
    


    public int deleteLine(String id, String compNo);
    /**
     * 결재자 상태 및 액션 수정
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
     * 다음결재자 선정
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
     * 결재 요청 상태 변경
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
     * 반려처리.
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
     * 결재가 승인,반려,참조인지 확인.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprUsrId
     * @param user
     */
    public String checkApprUsrType(String apprUsrId, User user);
    
    /**
     * 동일레벨에 결재자가 존재하는지 확인.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprUsrId
     * @param user
     */
    public String isParalApprUser(String apprUsrId, User user);
    
    
    /**
     * 참조자를 대기 상태로 변경처리함.
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