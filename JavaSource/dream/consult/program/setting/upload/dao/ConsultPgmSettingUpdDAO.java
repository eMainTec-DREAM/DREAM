package dream.consult.program.setting.upload.dao;

import java.util.List;

import common.bean.User;
import common.exception.SqlIgnoreException;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdDTO;

/**
 * Excel Data Upload - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ConsultPgmSettingUpdDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param consultPgmSettingUpdDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findExldataList(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteExldataList(String table, String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param consultPgmSettingUpdDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public List findExcelColList(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public List getDummyHeader(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;

    public List findExcelHeader(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public ConsultPgmSettingUpdDTO findExcelTab(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public void uploadData(String pgmName, User user) throws SqlIgnoreException;
    
    /**
     * FIND DETAIL
     * @param consultPgmSettingUpdDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param consultPgmSettingUpdDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param consultPgmSettingUpdDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;

    public int updateUpdDate(String excelTabId, User user) throws Exception;

    public void uploadExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;

    public List findExcelExScript(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;

    public List exeScriptToList(String script) throws SqlIgnoreException;

    public String findExcelSheetName(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
}