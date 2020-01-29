package dream.app.not.service;

import java.util.List;

import dream.app.doc.dto.AppDocReqDTO;
import dream.app.not.dto.AppNotCommonDTO;

/**
 * 통보문서 - 목록
 * @author  javaworker
 * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
 * @since   1.0
 */
public interface AppNotListService
{     
    /**
     * 점검번호 검색
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @param appNotCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findAppNotList(AppNotCommonDTO appNotCommonDTO);    

    /**
     * 점검번호 검색 건수
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @since   1.0
     * 
     * @param appNotCommonDTO
     * @return
     */
    public int findAppNotListCount(AppNotCommonDTO appNotCommonDTO);

    /**
     * 통보문서 확인
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @since   1.0
     * 
     * @param appNotList
     * @param appNotCommonDTO
     */
    public void confirmAppNot(List appNotList, AppNotCommonDTO appNotCommonDTO);
    
    /**
     * 문서 통보처리
     * @author  javaworker
     * @version $Id: AppNotListService.java,v 1.1 2013/08/30 09:13:16 javaworker Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     */
    void inputAppDocNot(AppDocReqDTO appDocReqDTO);
}