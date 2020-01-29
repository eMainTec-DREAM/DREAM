package dream.doc.file.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import common.bean.User;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;

/**
 * 메뉴 - 상세 service
 * 
 * @author jung7126
 * @version $Id: MaDocLibDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface MaDocLibDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaDocLibDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @param compId 
     * @return
     * @throws Exception
     */
    public MaDocLibDetailDTO findDetail(String menuId, User user)throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaDocLibDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @param maDocLibCommonDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(MaDocLibDetailDTO maDocLibDetailDTO, MaDocLibCommonDTO maDocLibCommonDTO, FormFile[] formFiles) throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaDocLibDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @param formFiles 
     * @param strings 
     * @return
     * @throws Exception
     */
    public int updateDetail(MaDocLibDetailDTO maDocLibDetailDTO, FormFile[] formFiles, String[] strings) throws Exception;
    
    
    /**
     * File Upload Dhtmlx Vault
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @param request
     * @param response
     * @return 
     * @throws IOException 
     * @throws Exception 
     */
    public Map<String, String> uploadAutoFiles(MaDocLibDetailDTO maDocLibDetailDTO,HttpServletRequest request, HttpServletResponse response) throws IOException, Exception;
    
    /**
     * Delete File Dhtmlx Valute
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @param deleteRows
     */
    public void deleteAutoFiles(MaDocLibDetailDTO maDocLibDetailDTO, String[] deleteRows);
    
    
    public int uploadFiles(MaDocLibDetailDTO maDocLibDetailDTO,FormFile[] fileList) throws FileNotFoundException, IOException;

}
