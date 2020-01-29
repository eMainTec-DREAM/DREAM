package dream.consult.program.setting.upload.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.User;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;

/**
 * ¿¢¼¿¾÷·Îµå - »ó¼¼ service
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultPgmSettingUpdFileService
{    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @param consultPgmSettingUpdFileCommonDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, String[] fileNames, User user) throws IOException;
    
    /**
     * File Upload Dhtmlx Vault
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @param request
     * @param response
     * @return 
     * @throws IOException 
     * @throws Exception 
     */
    public Map<String, String> uploadAutoFiles(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO,HttpServletRequest request, HttpServletResponse response, User user) throws IOException, Exception;
    
    /**
     * Delete File Dhtmlx Valute
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @param deleteRows
     */
    public void deleteAutoFiles(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, String[] deleteRows);
    
    
    /**
     * Upload Data
     * @author  HN4741
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @param fileName
     * @param excelToTable
     * @param user
     * @return
     */
    public int uploadData(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, String fileName, Map excelToTable, User user);
}
