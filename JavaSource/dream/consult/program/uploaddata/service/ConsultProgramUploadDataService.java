package dream.consult.program.uploaddata.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ���ε嵥��Ÿ - ��� service
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
     * @return ��ȸ ��� 
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

    // ���̺� ���翩�� Ȯ��
    public String checkTableExist(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;

    // Ȯ��(���̺����)
    public int completeDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
    // ���(���̺����)
    public int dropTable(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
}
