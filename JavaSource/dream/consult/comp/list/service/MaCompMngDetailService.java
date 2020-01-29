package dream.consult.comp.list.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.dto.MaCompMngDetailDTO;

/**
 * 회사설정 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaCompMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaCompMngDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCompMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     * @throws Exception
     */
    public MaCompMngDetailDTO findDetail(MaCompMngCommonDTO maCompMngCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCompMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCompMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user) throws Exception;
    
	public List findSlideImage(MaCompMngCommonDTO maCompMngCommonDTO, MaCompMngDetailDTO maCompMngDetailDTO, User user) throws IOException, Exception;

	public MaCompMngDetailDTO findImage(MaCompMngCommonDTO maCompMngCommonDTO, MaCompMngDetailDTO maCompMngDetailDTO, User user) throws IOException, Exception;
	
	public String[][] getImageCount(MaCompMngCommonDTO maCompMngCommonDTO,  MaCompMngDetailDTO maCompMngDetailDTO, User user);
	
	public void loadLoginImg() throws IOException, Exception;
}
