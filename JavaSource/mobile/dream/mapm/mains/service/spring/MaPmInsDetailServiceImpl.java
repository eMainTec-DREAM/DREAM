package mobile.dream.mapm.mains.service.spring;

import common.bean.User;
import mobile.dream.mapm.mains.dao.MaPmInsDetailDAO;
import mobile.dream.mapm.mains.dto.MaPmInsDetailDTO;
import mobile.dream.mapm.mains.service.MaPmInsDetailService;

/**
 * »ó¼¼ serviceimpl 
 * @author  jung7126
 * @version $Id: MaPmInsDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmInsDetailServiceTarget"
 * @spring.txbn id="maPmInsDetailService"
 * @spring.property name="maPmInsDetailDAO" ref="maPmInsDetailDAO"
 */
public class MaPmInsDetailServiceImpl implements MaPmInsDetailService
{
    private MaPmInsDetailDAO maPmInsDetailDAO = null;
    
    public MaPmInsDetailDAO getMaPmInsDetailDAO() {
		return maPmInsDetailDAO;
	}

	public void setMaPmInsDetailDAO(MaPmInsDetailDAO maPmInsDetailDAO) {
		this.maPmInsDetailDAO = maPmInsDetailDAO;
	}

	public MaPmInsDetailDTO findDetail(String eqCtgId, User user)throws Exception
    {
        return maPmInsDetailDAO.findDetail(eqCtgId,user);
    }
	
	public int insertDetail(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception
    {   maPmInsDetailDAO.insertDetail(maPmInsDetailDTO);
        return maPmInsDetailDAO.createPmSchedule(maPmInsDetailDTO);
    }
	
	public String checkDetail(MaPmInsDetailDTO maPmInsDetailDTO, User user) throws Exception
    {  
		return maPmInsDetailDAO.checkDetail(maPmInsDetailDTO, user);
    }
	
	public int updateDetail(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception
    {   
		maPmInsDetailDAO.updateDetail(maPmInsDetailDTO);
    	if("N".equals(maPmInsDetailDTO.getIsActive())){
    		return maPmInsDetailDAO.deletePmSched(maPmInsDetailDTO);
    	}else if("Y".equals(maPmInsDetailDTO.getIsActive())){
    		maPmInsDetailDAO.createPmSchedule(maPmInsDetailDTO);
    		return maPmInsDetailDAO.createWorkOrder(maPmInsDetailDTO);
    	}else{
    		return 0;
    	}
    }
}
