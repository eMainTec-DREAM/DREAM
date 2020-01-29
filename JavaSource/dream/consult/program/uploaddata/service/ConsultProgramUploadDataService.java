package dream.consult.program.uploaddata.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * 업로드데이타 - 목록 service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultProgramUploadDataService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param consultProgramUploadDataDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int delete(String[] deleteRows) throws Exception;

    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
    
    public ConsultProgramUploadDataDTO findDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user)throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramUploadDataDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;

    // 테이블 존재여부 확인
    public String checkTableExist(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;

    // 확정(테이블생성)
    public int completeDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
    // 취소(테이블삭제)
    public int dropTable(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
}
