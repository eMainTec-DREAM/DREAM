package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.MaWoResultToolDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.service.MaWoResultToolDetailService;

/**
 * 작업결과 투입공기구
 * @author kim2107
 * @version $Id: MaWoResultToolDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultToolDetailServiceTarget"
 * @spring.txbn id="maWoResultToolDetailService"
 * @spring.property name="maWoResultToolDetailDAO" ref="maWoResultToolDetailDAO"
 */
public class MaWoResultToolDetailServiceImpl implements MaWoResultToolDetailService
{
    private MaWoResultToolDetailDAO maWoResultToolDetailDAO = null;
    
    public MaWoResultToolDetailDAO getMaWoResultToolDetailDAO() {
		return maWoResultToolDetailDAO;
	}

	public void setMaWoResultToolDetailDAO(MaWoResultToolDetailDAO maWoResultToolDetailDAO) {
		this.maWoResultToolDetailDAO = maWoResultToolDetailDAO;
	}

	public MaWoResultToolDetailDTO findDetail(String wkOrId, String woToolId, User user)throws Exception
    {
        return maWoResultToolDetailDAO.findDetail(wkOrId, woToolId, user);
    }
    
	public int updateDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception
    {        
        return maWoResultToolDetailDAO.updateDetail(maWoResultToolDetailDTO, maWoResultMstrCommonDTO,user);
    }
	public int insertDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {   		
        return maWoResultToolDetailDAO.insertDetail( maWoResultToolDetailDTO, maWoResultMstrCommonDTO);
    }
	public String getStockQty(MaWoResultToolDetailDTO maWoResultToolDetailDTO, User loginUser){
		return maWoResultToolDetailDAO.getStockQty(maWoResultToolDetailDTO, loginUser);
	}
	
}
