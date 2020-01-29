package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 - 목록 service
 * @author  kim21017
 * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultMstrListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoResultMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    public List findWoResultPmiMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    public List findWoResultTrMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoResultMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoBmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoResultMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoCmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    public List findWoTiResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoResultMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoPmwResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoResultMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoPmcResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteWoResultMstr(String[] deleteRows, User user) throws Exception;
    /**
     * report
     * @author kim21017
     * @version $Id: MaWoResultMstrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List getReportView(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user, String woType);
    
    /**
     *  grid find
     * @author  js.lee
     * @version $Id: Exp $
     * @param maWoResultMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWoPmwOvhResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user);
    
    /**
     * 엑셀업로드 데이터
     * @author  js.lee
     * @version $Id: Exp $
     * @param maWoResultMstrCommonDTO
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param user
     * @return
     */
    public String getData(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    /**
     * 작업결과서 상태 체크
     * @author  nhkim8548
     * @version $Id: Exp $
     * @since   1.0
     *
     * @param 	wkorId
     * @param 	user
     * @return 	
     */
    public String checkWoResultMstrStatus(String wkorId, User user);
    /**
     * 작업결과서 삭제
     * @author  nhkim8548
     * @version $Id: Exp $
     * @since   1.0
     *
     * @param 	wkorId
     * @param 	user
     * @return 	
     */
    public int delWoResultMstr(String wkorId, User user) throws Exception;
}
