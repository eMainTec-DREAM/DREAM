package dream.doc.img.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import common.bean.User;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.dto.MaDocImgDetailDTO;

/**
 * 사진파일 - 상세 service
 * 
 * @author jung7126
 * @version $Id: MaDocImgDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface MaDocImgDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaDocImgDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgCommonDTO
     * @param compId 
     * @return
     * @throws Exception
     */
    public MaDocImgDetailDTO findDetail(MaDocImgCommonDTO maDocImgCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaDocImgDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @param maDocImgCommonDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(MaDocImgDetailDTO maDocImgDetailDTO, MaDocImgCommonDTO maDocImgCommonDTO, FormFile[] formFiles) throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaDocImgDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @param formFiles 
     * @param strings 
     * @return
     * @throws Exception
     */
    public int updateDetail(MaDocImgDetailDTO maDocImgDetailDTO, FormFile[] formFiles, String[] strings) throws Exception;
    
    /**
     * find Object Type Desc
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param objectType
     * @param compNo
     * @return
     */
    public String findObjectTypeDesc(String objectType, User user);
    
    /**
     * 파일 업로드 삭제
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @param deleteRows
     */
    public void deleteAutoFiles(MaDocImgDetailDTO maDocImgDetailDTO, String[] deleteRows);
    
    /**
     * 파일 업로드 
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @param request
     * @param response
     * @return 
     * @throws IOException 
     * @throws Exception 
     */
    public Map<String, String> uploadAutoFiles(MaDocImgDetailDTO maDocImgDetailDTO, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception;
    
    public Map<String, String> uploadAutoFiles(MaDocImgDetailDTO maDocImgDetailDTO, String url) throws Exception;
}
