package dream.pers.appstb.prc.service;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;

/**
 * 결재요청
 * @author  javaworker
 * @version $Id: AppPrcDetailService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
 * @since   1.0
 */
public interface AppPrcDetailService
{
    /**
     * 결재자 상세조회
     * @author  javaworker
     * @version $Id: AppPrcDetailService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    AppPrcDetailDTO findDetail(AppReqCommonDTO appReqCommonDTO);

    /**
     * 결재자 입력
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @param user
     */
    void insertDetail(AppPrcDetailDTO appPrcDetailDTO, User user);

    /**
     * 결제자 수정
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @param user
     */
    void updateDetail(AppPrcDetailDTO appPrcDetailDTO, User user);


	/**
	 * Check App Seq Num
	 * @param appPrcDetailDTO
	 * @param user
	 * @return
	 */
	String checkSeqNum(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO, User user);
	
	String nextAppSeq(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO, User user);
}
