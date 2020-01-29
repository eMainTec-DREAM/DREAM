package dream.doc.ctg.dao;

import java.util.List;

import common.bean.User;
import dream.doc.ctg.dto.DocCtgDetailDTO;

/**
 * 문서분류체계 - 상세 dao
 * 
 * @author ssong
 * @version $Id: DocCtgDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface DocCtgDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgCommonDTO
     * @return
     */
    public DocCtgDetailDTO findDetail(String docCtgId, User user);
    
    public int insertDetail(DocCtgDetailDTO docCtgDetailDTO);
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgDetailDTO
     * @return
     */
    public int[] updateDetail(final List<DocCtgDetailDTO> list, final User user);
    
}