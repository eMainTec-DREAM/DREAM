package dream.mgr.lang.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.service.MaEqMstrAsmbListService;
import dream.mgr.lang.dao.MaResDetailDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.dto.MaResDetailDTO;
import dream.mgr.lang.service.MaResDetailService;

/**
 * 언어 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maResDetailServiceTarget"
 * @spring.txbn id="maResDetailService"
 * @spring.property name="maResDetailDAO" ref="maResDetailDAO"
 */
public class MaResDetailServiceImpl implements MaResDetailService
{
    private MaResDetailDAO maResDetailDAO = null;
    
    public MaResDetailDAO getMaResDetailDAO() 
    {
		return maResDetailDAO;
	}

	public void setMaResDetailDAO(MaResDetailDAO maResDetailDAO) 
	{
		this.maResDetailDAO = maResDetailDAO;
	}

	public MaResDetailDTO findDetail(User user, MaResCommonDTO maResCommonDTO)throws Exception
    {
        return maResDetailDAO.findDetail(user, maResCommonDTO);
    }
    
	
	public int updateDetail(MaResDetailDTO maResDetailDTO, User user) throws Exception
    {        
        return maResDetailDAO.updateDetail(maResDetailDTO, user);
    }

	@Override
	public int insertDetail(MaResDetailDTO maResDetailDTO, MaResCommonDTO maResCommonDTO, User user) throws Exception {
		 return maResDetailDAO.insertDetail(maResDetailDTO, user);
	}
	
}
