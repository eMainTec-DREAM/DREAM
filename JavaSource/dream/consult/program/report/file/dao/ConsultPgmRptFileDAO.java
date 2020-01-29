package dream.consult.program.report.file.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.program.report.file.dto.ConsultPgmRptFileDTO;

/**
 * 출력물 설정 파일 - 목록 dao 
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ConsultPgmRptFileDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmRptFileDTO
     * @return List
     */
    public List findList(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception;
    
    public String findTotalCount(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user);
    
    public int[] updateDetail(final List<ConsultPgmRptFileDTO> list, User user) throws Exception;
    
    public int[] insertDetail(final List<ConsultPgmRptFileDTO> list, User user) throws Exception;
    
    public int[] deleteList(final List<ConsultPgmRptFileDTO> list, final User user) throws Exception;

    public String getColums(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user);
    
    public String getTables(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user);
    
    public String getOrderBy(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user);
    
    public String getWhere(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user);
    
    public String checkDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user);
}