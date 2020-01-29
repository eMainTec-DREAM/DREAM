package dream.work.list.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * 작업계획목록 - 상세 dao
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
    
    // TAWOPLAN에서 TAWORKORDER로 데이터 INSERT (1개)
    public int insertWoPlan(WoPlanDetailDTO woPlanDetailDTO, User user);
    // TAWOPLANCRAFT에서 TAWOCRAFT로 데이터 INSERT (갯수만큼)
    public int insertWoPlanCraft(WoPlanDetailDTO woPlanDetailDTO, User user);
    // TAWOPLANPARTS에서 TAPARTS로 데이터 INSERT (갯수만큼)
    public int insertWoPlanParts(WoPlanDetailDTO woPlanDetailDTO, User user);
    // TAWOPLAN에서의 작업상태를 완료로 변경 (1개)
    public int completeDetail(WoPlanDetailDTO woPlanDetailDTO, User user);

    public int updateReqStatus(WoPlanDetailDTO woPlanDetailDTO, User user);

    public int updateResStatus(WoPlanDetailDTO woPlanDetailDTO, User user);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public String woPlanCheck(WoPlanCommonDTO woPlanCommonDTO, User user) throws Exception;
}