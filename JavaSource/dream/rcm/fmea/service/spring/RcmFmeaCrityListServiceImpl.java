package dream.rcm.fmea.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.fmea.dao.RcmFmeaCrityListDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;
import dream.rcm.fmea.service.RcmFmeaCrityListService;


/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: RcmFmeaCrityListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFmeaCrityListServiceTarget"
 * @spring.txbn id="rcmFmeaCrityListService"
 * @spring.property name="rcmFmeaCrityListDAO" ref="rcmFmeaCrityListDAO"
 */
public class RcmFmeaCrityListServiceImpl implements RcmFmeaCrityListService
{
    private RcmFmeaCrityListDAO rcmFmeaCrityListDAO = null;

    public RcmFmeaCrityListDAO getRcmFmeaCrityListDAO() {
		return rcmFmeaCrityListDAO;
	}
	public void setRcmFmeaCrityListDAO(RcmFmeaCrityListDAO rcmFmeaCrityListDAO) {
		this.rcmFmeaCrityListDAO = rcmFmeaCrityListDAO;
	}
	
	public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO)
    {      
        return rcmFmeaCrityListDAO.findList(rcmFmeaCommonDTO, rcmFmeaCrityListDTO);
    }
	
	public int deleteList(String[] deleteRows) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + rcmFmeaCrityListDAO.deleteList(deleteRows[i] );
        }
        
        return result;
    }

	public void insertCrity(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		rcmFmeaCrityListDAO.insertCrity(rcmFmeaCommonDTO, rcmFmeaCrityListDTO);
	}
	@Override
	public void deleteCrity(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		rcmFmeaCrityListDAO.deleteCrity(rcmFmeaCommonDTO, rcmFmeaCrityListDTO);
	}
	@Override
	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, RcmFmeaCrityListDTO rcmFmeaCrityListDTO, User user) {
		 return rcmFmeaCrityListDAO.findTotalCount(rcmFmeaCommonDTO, rcmFmeaCrityListDTO, user);
	}
}

