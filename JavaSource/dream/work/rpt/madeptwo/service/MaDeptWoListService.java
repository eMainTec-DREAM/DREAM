package dream.work.rpt.madeptwo.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.madeptwo.dto.MaDeptWoListDTO;

/**
 * 부서별작업분석 service
 * @author  kim21017
 * @version $Id: MaDeptWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaDeptWoListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaDeptWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maDeptWoListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findDeptWoList(MaDeptWoListDTO maDeptWoListDTO, User user);    
    /**
     *  건수차트
     * @author  kim21017
     * @version $Id: MaDeptWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maDeptWoListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findCntChart(MaDeptWoListDTO maDeptWoListDTO);    
    /**
     *  시간차트
     * @author  kim21017
     * @version $Id: MaDeptWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maDeptWoListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findTimeChart(MaDeptWoListDTO maDeptWoListDTO);    
}
