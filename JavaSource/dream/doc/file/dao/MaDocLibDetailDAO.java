package dream.doc.file.dao;

import common.bean.User;
import common.file.MwareFile;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;

/**
 * 첨부문서 - 상세 dao
 * 
 * @author jung7126
 * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface MaDocLibDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @param compNo 
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaDocLibDetailDTO findDetail(String docId, User user);

    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @param maDocLibCommonDTO 
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @return
     */
    public int insertDetail(MaDocLibCommonDTO maDocLibCommonDTO, MaDocLibDetailDTO maDocLibDetailDTO);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaDocLibDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maDocLibDetailDTO
     * @return
     */
    public int updateDetail(MaDocLibDetailDTO maDocLibDetailDTO);
    
    public int insertFileInfo(MwareFile mwareFile,MaDocLibDetailDTO maDocLibDetailDTO);
    
    public int deleteFileInfo(String docId);

    public String getFileInfo(String docdataId);
    
    public int insertObjDoc(MaDocLibCommonDTO maDocLibCommonDTO,MaDocLibDetailDTO maDocLibDetailDTO);

    public String[] getWkorId(String mobinsWkorId);
    
    public String[] getWkorDesc(String wkorId);
    
}