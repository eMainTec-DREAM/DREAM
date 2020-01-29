package dream.consult.program.uploaddata.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * 업로드데이타 - 목록 dao
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
    
    // 테이블 존재여부 확인
    public String checkTableExist(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user) throws Exception;
    
    // 확정(테이블생성)
    public int completeDetail(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // 코멘트 추가
    public int setComments(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // 상태 변경(테이블생성후)
    public int updateStatus(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // 취소(테이블삭제)
    public int dropTable(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
    // 상태 변경(테이블삭제후)
    public int updateStatusAsW(ConsultProgramUploadDataDTO consultProgramUploadDataDTO, User user);
}