package dream.consult.program.uploaddata.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ���ε嵥��Ÿ - ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultProgramUploadDataDAO
{
    public List findList(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);

    public int delete(String id);

    public String findTotalCount(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;

    public int insertDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);

    public int updateDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    
    // ���̺� ���翩�� Ȯ��
    public String checkTableExist(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
    
    // Ȯ��(���̺����)
    public int completeDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // �ڸ�Ʈ �߰�
    public int setComments(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // ���� ����(���̺������)
    public int updateStatus(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // ���(���̺����)
    public int dropTable(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // ���� ����(���̺������)
    public int updateStatusAsW(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
}