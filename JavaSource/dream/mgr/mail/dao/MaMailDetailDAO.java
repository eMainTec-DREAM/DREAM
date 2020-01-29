package dream.mgr.mail.dao;

import java.util.List;

import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailDetailDTO;

/**
 * 메일수신자설정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaMailDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return
     */
    public MaMailDetailDTO findDetail(MaMailCommonDTO maMailCommonDTO);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailDTO
     * @return
     */
    public int insertDetail(MaMailDetailDTO maMailDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailDTO
     * @return
     */
    public int updateDetail(MaMailDetailDTO maMailDetailDTO);
    
    
    public List sendMessageQuery(String strQuery);
    
    
    public String findTitleMessageQuery(String strQuery);
    
    /**
     * mail list
     * @author hankyul
     * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return
     */
    public String[] findSendList(MaMailDetailDTO maMailDetailDTO);
    
    public int updateMailSendTime(MaMailDetailDTO maMailDetailDTO);
    
    public String[][] findAllMailingList(MaMailDetailDTO maMailDetailDTO);
    
    public String selectTitleScript(MaMailDetailDTO maMailDetailDTO);
    
    public String selectExeScript(MaMailDetailDTO maMailDetailDTO);
    
    public String selectLinkTitle();
    
    public String[] getStringArrayFromScript(String script);
    
    public String[][] getMultiStringArrayFromScript(String script);
    
}