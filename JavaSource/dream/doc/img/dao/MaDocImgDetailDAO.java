package dream.doc.img.dao;

import java.util.List;

import common.bean.User;
import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.dto.MaDocImgDetailDTO;

/**
 * 첨부문서 - 상세 dao
 * 
 * @author jung7126
 * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface MaDocImgDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @param compNo 
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaDocImgDetailDTO findDetail(String docId, User user);

    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @return
     */
    public int insertDetail(MaDocImgCommonDTO maDocImgCommonDTO, MaDocImgDetailDTO maDocImgDetailDTO);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @return
     */
    public int updateDetail(MaDocImgDetailDTO maDocImgDetailDTO);
    
    public int insertFileInfo(MwareFile mwareFile,MaDocImgDetailDTO maDocImgDetailDTO);
    
    public int deleteFileInfo(String docId);

    public String getFileInfo(String docdataId);
    
    public int insertObjDoc(MaDocImgCommonDTO maDocImgCommonDTO,MaDocImgDetailDTO maDocImgDetailDTO);

    public String findOjbectTypeDesc(String objectType, User loginUser);
    
    public List getImgFileList(String objectId, String objectType, String param);
    
    public String getImageCount(MaDocImgCommonDTO maDocImgCommonDTO, User user);
    
    /**
     * detail copy
     * @author syyang
     * @version $Id: MaDocImgDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maDocImgDetailDTO
     * @return
     */
    public int insertCopyDetail(String newImgId, String oldImgId, String newObjectId, User user);
    public int insertCopyFileInfo(String oldImgdataId, MaDocImgDetailDTO maDocImgDetailDTO, User user);
    
}