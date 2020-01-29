package dream.pers.appstb.prc.service;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * 결재문서 - 목록
 * @author  javaworker
 * @version $Id: AppPrcListService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
 * @since   1.0
 */
public interface AppPrcListService
{     
    /**
     *  검색
     * @author  javaworker
     * @version $Id: AppPrcListService.java,v 1.1 2013/08/30 09:10:38 javaworker Exp $
     * @param appReqCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findAppPrcList(AppReqCommonDTO appReqCommonDTO, User loginUser);

    /**
     *  삭제
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