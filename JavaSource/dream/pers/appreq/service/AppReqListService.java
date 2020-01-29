package dream.pers.appreq.service;

import java.util.List;

import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * 요청문서 - 목록
 * @author  javaworker
 * @version $Id: AppReqListService.java,v 1.1 2013/08/30 09:14:06 javaworker Exp $
 * @since   1.0
 */
public interface AppReqListService
{     
    /**
     * 점검번호 검색
     * @author  javaworker
     * @version $Id: AppReqListService.java,v 1.1 2013/08/30 09:14:06 javaworker Exp $
     * @param appReqCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findAppReqList(AppReqCommonDTO appReqCommonDTO);    

    /**
     * 점검번호 검색 건수
     * @author  javaworker
     * @version $Id: AppReqListService.java,v 1.1 2013/08/30 09:14:06 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public int findAppReqListCount(AppReqCommonDTO appReqCommonDTO);
}