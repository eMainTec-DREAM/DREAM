package dream.consult.program.report.file.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.report.file.dto.ConsultPgmRptFileDTO;

/**
 * 출력물 설정 파일 - 목록 service 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultPgmRptFileService
{     
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @param 	consultPgmRptFileDTO
     * @param 	user
     * @since   1.0
     * 
     * @return list
     * @throws Exception
     */
    public List<ConsultPgmRptFileDTO> findList(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception;
    
    /**
     * grid delete
     * @author  youngjoo38
     * @version $Id:$
     * @param 	deleteRows
     * @param 	user
     * @since   1.0
     * 
     * @return  
     * @throws Exception
     */
    public int[] deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * grid count
     * @author  youngjoo38
     * @version $Id:$
     * @param 	consultPgmRptFileDTO
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public String findTotalCount(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user);
    
    /**
     * detail find
     * @author  youngjoo38
     * @version $Id:$
     * @param 	consultPgmRptFileDTO
     * @param 	user
     * @since   1.0
     * 
     * @return ConsultPgmRptFileDTO
     * @throws Exception
     */
    public ConsultPgmRptFileDTO findDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception;
    
    /**
     * DETAIL UPDATE 
     * @author  youngjoo38
     * @version $Id:$
     * @param 	consultPgmRptFileDTO
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public int updateDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception;
    
    /**
     * detail insert 
     * @author  youngjoo38
     * @version $Id:$
     * @param 	consultPgmRptFileDTO
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public int insertDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception;
    
    /**
     * detail insert 
     * @author  youngjoo38
     * @version $Id:$
     * @param 	list
     * @param 	user
     * @since   1.0
     * 
     * @return 
     * @throws Exception
     */
    public int[] insertDetail(List<ConsultPgmRptFileDTO> list, User user) throws Exception;

    public String checkDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception;
    
}
