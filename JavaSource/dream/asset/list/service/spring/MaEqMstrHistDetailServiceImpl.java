package dream.asset.list.service.spring;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrHistDetailDAO;
import dream.asset.list.dto.MaEqMstrHistDetailDTO;
import dream.asset.list.dto.MaEqMstrHistListDTO;
import dream.asset.list.service.MaEqMstrHistDetailService;

/**
 * 설비변경이력 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaEqMstrHistDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrHistDetailServiceTarget"
 * @spring.txbn id="maEqMstrHistDetailService"
 * @spring.property name="maEqMstrHistDetailDAO" ref="maEqMstrHistDetailDAO"
 */
public class MaEqMstrHistDetailServiceImpl implements MaEqMstrHistDetailService
{
    private MaEqMstrHistDetailDAO maEqMstrHistDetailDAO = null;
    
    public MaEqMstrHistDetailDAO getMaEqMstrHistDetailDAO() {
		return maEqMstrHistDetailDAO;
	}

	public void setMaEqMstrHistDetailDAO(MaEqMstrHistDetailDAO maEqMstrHistDetailDAO) {
		this.maEqMstrHistDetailDAO = maEqMstrHistDetailDAO;
	}

	public MaEqMstrHistDetailDTO findDetail(MaEqMstrHistListDTO maEqmstrHistListDTO, User user)throws Exception
    {
        return maEqMstrHistDetailDAO.findDetail(maEqmstrHistListDTO.getEqalthistId(),user);
    }

}
