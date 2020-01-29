package dream.consult.program.report.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.report.dto.ConsultPgmRptDTO;

/**
 * Report List - 목록 service 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultPgmRptService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @param consultPgmRptDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List<ConsultPgmRptDTO> findList(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception;
    
    public int[] deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(ConsultPgmRptDTO consultPgmRptDTO, User user);
    
    public ConsultPgmRptDTO findDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception;
    
    public int updateDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception;
    
    public int insertDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception;
    
    public int[] insertDetail(List<ConsultPgmRptDTO> list, User user) throws Exception;
    
    public String checkDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception;
}
