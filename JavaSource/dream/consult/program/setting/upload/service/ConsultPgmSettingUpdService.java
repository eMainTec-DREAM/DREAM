/**
 * 
 */
package dream.consult.program.setting.upload.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdDTO;
/**
 * Excel Data Upload - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultPgmSettingUpdService {
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
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteExldataList(String table, String[] deleteRows, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param consultPgmSettingUpdDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public List getDummyHeader(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public List findExcelTemplateData(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public void uploadData(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    /**
     * FIND DETAIL
     * @param consultPgmSettingUpdDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    /**
     * INSERT
     * @param consultPgmSettingUpdDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    /**
     * UPDATE
     * @param consultPgmSettingUpdDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public void uploadExldataDetail(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
    
    public List findField(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO, User user) throws Exception;
}
