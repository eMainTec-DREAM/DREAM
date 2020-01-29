package dream.work.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * �۾���ȹ��� - �� dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface WoPlanDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @return
     */
    public WoPlanDetailDTO findDetail(WoPlanCommonDTO woPlanCommonDTO, User user);
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @return
     */
    public int insertDetail(WoPlanDetailDTO woPlanDetailDTO, User loginUser);
    public int insertWoequip(WoPlanDetailDTO woPlanDetailDTO, User user);
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @return
     */
    public int updateDetail(WoPlanDetailDTO woPlanDetailDTO, User user);
    public int updateWoequip(WoPlanDetailDTO woPlanDetailDTO, User user);
    public String selectWoequipCnt(WoPlanDetailDTO woPlanDetailDTO, User user);

    public String checkPoint(WoPlanDetailDTO woPlanDetailDTO,User user);
    
    // TAWOPLAN���� TAWORKORDER�� ������ INSERT (1��)
    public int insertWoPlan(WoPlanDetailDTO woPlanDetailDTO, User user);
    // TAWOPLANCRAFT���� TAWOCRAFT�� ������ INSERT (������ŭ)
    public int insertWoPlanCraft(WoPlanDetailDTO woPlanDetailDTO, User user);
    // TAWOPLANPARTS���� TAPARTS�� ������ INSERT (������ŭ)
    public int insertWoPlanParts(WoPlanDetailDTO woPlanDetailDTO, User user);
    // TAWOPLAN������ �۾����¸� �Ϸ�� ���� (1��)
    public int completeDetail(WoPlanDetailDTO woPlanDetailDTO, User user);

    public int updateReqStatus(WoPlanDetailDTO woPlanDetailDTO, User user);

    public int updateResStatus(WoPlanDetailDTO woPlanDetailDTO, User user);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public String woPlanCheck(WoPlanCommonDTO woPlanCommonDTO, User user) throws Exception;
}