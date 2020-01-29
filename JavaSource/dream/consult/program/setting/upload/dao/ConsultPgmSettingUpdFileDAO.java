package dream.consult.program.setting.upload.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.exception.SqlIgnoreException;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;

/**
 * ¿¢¼¿¾÷·Îµå - »ó¼¼ dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultPgmSettingUpdFileDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @param consultPgmSettingUpdFileCommonDTO 
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @return
     */
    public int insertDetail(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, User user);

    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @return
     */
    public int updateDetail(Object id, User user);
    
    public List getIds(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, User user);

    public List getExcelTableCol(String excelTabId);
    
    public int insertExcelData(String tableName, Map headMap, Map valueMap, User user) throws SqlIgnoreException;

    public int deleteData(String tableName, User user);
}