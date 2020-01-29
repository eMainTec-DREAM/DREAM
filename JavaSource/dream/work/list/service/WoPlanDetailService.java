package dream.work.list.service;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * 작업계획목록 - 상세 service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface WoPlanDetailService 
{    
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @return
     * @throws Exception
     */
    public WoPlanDetailDTO findDetail(WoPlanCommonDTO woPlanCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @param woPlanCommonDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(WoPlanDetailDTO woPlanDetailDTO, WoPlanCommonDTO woPlanCommonDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception;
    /**
     * 점검항목 검사
     * @param woPlanDetailDTO
     * @param user
     * @return
     */
    public String checkPoint(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception;
    
    public int completeDetail(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception;
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    public String woPlanCheck(WoPlanCommonDTO woPlanCommonDTO, User user) throws Exception;
    public void insertWoEquip(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception;
    
    /**
     * 작업계획 완료 취소
     * @author nhkim8548
     * @since   1.0
     * 
     * @param woPlanDetailDTO
     * @throws Exception
     */
    public String reverseWoPlan(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception;
}
