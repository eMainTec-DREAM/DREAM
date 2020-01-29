package dream.mgr.exldata.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.User;
import dream.mgr.exldata.dto.MgrExldataUploadDetailDTO;

/**
 * ¿¢¼¿¾÷·Îµå - »ó¼¼ service
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrExldataUploadDetailService
{    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrExldataUploadDetailDTO
     * @param mgrExldataUploadCommonDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO, String[] fileNames, User user) throws Exception;
    
    /**
     * File Upload Dhtmlx Vault
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrExldataUploadDetailDTO
     * @param request
     * @param response
     * @return 
     * @throws IOException 
     * @throws Exception 
     */
    public Map<String, String> uploadAutoFiles(MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO,HttpServletRequest request, HttpServletResponse response, User user) throws Exception;
    
    /**
     * Delete File Dhtmlx Valute
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrExldataUploadDetailDTO
     * @param deleteRows
     */
    public void deleteAutoFiles(MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO, String[] deleteRows) throws Exception;
    
    
}
