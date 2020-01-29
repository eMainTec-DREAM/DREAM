package dream.pers.appreq.service;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 결재요청
 * @author  javaworker
 * @version $Id: AppReqDetailService.java,v 1.1 2013/08/30 09:14:05 javaworker Exp $
 * @since   1.0
 */
public interface AppReqDetailService
{
    /**
     * 결재선 조회
     * @author  javaworker
     * @version $Id: AppReqDetailService.java,v 1.1 2013/08/30 09:14:05 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @return
     */
    List findFlowUserList(AppReqDetailDTO appReqDetailDTO);

    /**
     * 요청문서 상세조회
     * @author  javaworker
     * @version $Id: AppReqDetailService.java,v 1.1 2013/08/30 09:14:05 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    AppReqDetailDTO findMstrDetail(AppReqCommonDTO appReqCommonDTO, User loginUser);

    /**
     * 결재요청 취소
     * @author  javaworker
     * @version $Id: AppReqDetailService.java,v 1.1 2013/08/30 09:14:05 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     */
    void cancelReqDoc(AppReqDetailDTO appReqDetailDTO, User user);

    /**
     * 입력
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @param user
     */
    void insertDetail(AppReqDetailDTO appReqDetailDTO, User user);

    /**
     * 업데이트
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @param user
     */
    void updateDetail(AppReqDetailDTO appReqDetailDTO, User user);

    /**
     * 결재요청
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @param user
     */
    void actionReq(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    
    String checkUsr(AppReqDetailDTO appReqDetailDTO, User user);
}
